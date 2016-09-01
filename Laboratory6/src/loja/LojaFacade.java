package loja;

public class LojaFacade {
	private LojaController controller;
	
	public void adicionaUsuario(String nome, String login, String tipo){
		controller.adicionaUsuario(nome, login, tipo);
	}
	
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser){
		controller.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
	}

	public void registraJogada(String login, String nomeJogo, int score, boolean venceu){
		controller.registraJogada(login, nomeJogo, score, venceu);
	}
	
	public void adicionaCredito(String login, double credito){
		controller.adicionaCredito(login, credito);
	}
	
	public void buscaUsuario(String login){
		controller.buscaUsuario(login);
	}
	
	public void upgrade(String login) throws Exception{
		controller.upgrade(login);
	}
	
	public void confereCredito(String login){
		controller.confereCredito(login);
	}
	
	public void informacaoUsuarios(){
		controller.informacaoUsuarios();
	}
	
	public void getX2p(String login){
		controller.getX2p(login);
	}
}
