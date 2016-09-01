package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int xp2;

	public Usuario(String nome, String login) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		meusJogos = new HashSet<Jogo>();
		this.credito = 0;
	}

	//public abstract void compraJogo(Jogo jogo) throws Exception;

	public void setXp2(int novoValor) {
		this.xp2 = novoValor;
	}

	public int getXp2() {
		return this.xp2;
	}

	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setCredito(double novoValor) {
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}

	public void registradaJogada(String nomeJogo, int score, boolean venceu) throws Exception {
		Jogo jogo = this.buscaJogo(nomeJogo);
		if (jogo == null) {
			throw new Exception();
		}
		setXp2(getXp2() + jogo.registraJogada(score, venceu));
	}
	//public abstract void recompensar(String nomeJogo,int scoreObtido,boolean zerou) throws Exception;
	//public abstract void punir(String nomeJogo,int scoreObtido,boolean zerou) throws Exception;
	
/*	public void recompensar(String nomeJogo,int scoreObtido,boolean zerou) throws Exception{
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
	*/
	public Jogo buscaJogo(String nomeJogo) {
		Jogo buscado = null;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			if (achado.getNome().equals(nomeJogo)) {
				buscado = achado;
			}
		}
		return buscado;
	}

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}

	public double calculaPrecoTotal() {
		double total = 0;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			total += achado.getPreco();
		}
		return total;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;
			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin());
		} else {
			return false;
		}
	}
}
