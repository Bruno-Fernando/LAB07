package usuario;

import jogo.Jogo;

public interface TipoDeUsuarioIF {
	void compraJogo(Jogo jogo);
	
	void recompensar(String nomeJogo,int scoreObtido,boolean zerou) throws Exception;
	
	void punir(String nomeJogo,int scoreObtido,boolean zerou) throws Exception;
}
