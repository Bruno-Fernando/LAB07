package usuario;

import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Veterano extends Usuario {
	public static final double DESCONTO_VETERANO = 0.8;

	public Veterano(String nome, String login) throws StringInvalidaException {
		super(nome, login);
		setXp2(1000);
	}
	
	public void recompensar(String nomeJogo,int scoreObtido,boolean zerou) throws Exception{
		Jogo jogo = this.buscaJogo(nomeJogo);
		if(jogo == null){
			throw new Exception();
		}
		Set<Jogabilidade> jogabilidades = jogo.getJogabilidade();
		int recompensa = 0;
		for (Jogabilidade jogabilidade : jogabilidades) {
			if(jogabilidade.equals("ONLINE")){
				recompensa += 10;
			}
			if(jogabilidade.equals("COOPERATIVO")){
				recompensa += 20;
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
			if(jogabilidade.equals("OFFLINE")){
				punicao -= 20;
			}
			else if(jogabilidade.equals("COMPETITIVO")){
				punicao -= 20;
			}
			
		}
		
		setXp2(punicao + getXp2() + jogo.registraJogada(scoreObtido, zerou));

	}
/*
	@Override
	public void compraJogo(Jogo jogo) throws Exception {
		double custo = jogo.getPreco() * DESCONTO_VETERANO;
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp =  parteInteira * 15;
			setXp2(getXp2() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}
	}*/

	@Override
	public String toString() {
		String myString = "Jogador noob: " + this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - " + this.getXp2() + " x2p" + FIM_DE_LINHA;
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
