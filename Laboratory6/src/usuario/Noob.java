package usuario;

import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Noob extends Usuario {
	public static final double DESCONTO_NOOB = 0.9;

	public Noob(String nome, String login) throws StringInvalidaException {
		super(nome, login);
		setXp2(0);
	}
	
	public void recompensar(String nomeJogo,int scoreObtido,boolean zerou) throws Exception{
		Jogo jogo = this.buscaJogo(nomeJogo);
		if(jogo == null){
			throw new Exception();
		}
		Set<Jogabilidade> jogabilidades = jogo.getJogabilidade();
		int recompensa = 0;
		for (Jogabilidade jogabilidade : jogabilidades) {
			if(jogabilidade.equals("OFFLINE")){
				recompensa += 30;
			}
			if(jogabilidade.equals("MULTIPLAYER")){
				recompensa += 10;
			}
		}
		
		setXp2(recompensa + getXp2() + jogo.registraJogada(scoreObtido, zerou));
	}
	
	public void punir(String nomeJogo, int scoreObtido, boolean zerou) throws Exception{
		Jogo jogo = this.buscaJogo(nomeJogo);
		if(jogo == null){
			throw new Exception();
		}
		Set<Jogabilidade> jogabilidades = jogo.getJogabilidade();
		int punicao = 0;
		for (Jogabilidade jogabilidade : jogabilidades) {
			if(jogabilidade.equals("ONLINE")){
				punicao -= 10;
			}
			else if(jogabilidade.equals("COMPETITIVO")){
				punicao -= 20;
			}
			else if(jogabilidade.equals("COOPERATIVO")){
				punicao -= 50;
			}
		}
		
		setXp2(punicao + getXp2() + jogo.registraJogada(scoreObtido, zerou));

	}
	/*
	@Override
	public void compraJogo(Jogo jogo) throws Exception {
		double custo = jogo.getPreco() * DESCONTO_NOOB;
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp =  parteInteira * 10;
			setXp2(getXp2() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}

	}*/

	@Override
	public String toString() {
		String myString = "Jogador noob: " + this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - " + this.getXp2() + " x2p"+ FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;

		Iterator itr = getMeusJogos().iterator();
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		myString += FIM_DE_LINHA;
		myString += "Total de pre�o dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------";
		return myString;
	}

}