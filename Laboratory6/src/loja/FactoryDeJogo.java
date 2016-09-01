package loja;

import java.util.Set;

import jogo.Jogabilidade;
import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.Rpg;

public class FactoryDeJogo {
	private Jogo criarJogoRPG(String nome, double preco, Set<Jogabilidade> tiposJogabilidades) throws Exception{
		Jogo novo = new Rpg(nome,preco, tiposJogabilidades);
		return novo;
	}
	private Jogo criarJogoPlataforma(String nome, double preco, Set<Jogabilidade> tiposJogabilidades) throws Exception{
		Jogo novo = new Plataforma(nome, preco, tiposJogabilidades);
		return novo;
	}
	private Jogo criarJogoLuta(String nome, double preco, Set<Jogabilidade> tiposJogabilidades) throws Exception{
		Jogo novo = new Luta(nome, preco, tiposJogabilidades);
		return novo;
	}
	public Jogo criaJogo(String nome, double preco, Set<Jogabilidade> tiposJogabilidades,String estilo ) throws Exception{
		if (estilo.equals("rpg")) {
			return criarJogoRPG(nome, preco, tiposJogabilidades);
		} else if (estilo.equals("plataforma")) {
			return criarJogoPlataforma(nome, preco, tiposJogabilidades);
		} else if (estilo.equals("luta")) {
			return criarJogoLuta(nome, preco, tiposJogabilidades);
		}
		return null;
	}

}
