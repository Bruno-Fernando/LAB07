package usuario;

import java.util.Set;

import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;


public class Noob2 implements TipoDeUsuarioIF{
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
	
	public void compraJogo(Jogo jogo) {
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
}
