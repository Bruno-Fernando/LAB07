package loja;

import usuario.Noob;
import usuario.Usuario;

public class FactoryDeUsuario {
	
	private Usuario noob(String nome, String login) throws Exception{
		Usuario novouser = new Noob(nome,login);
		return novouser;
	}
	
	public Usuario criaUsuario(String nome, String login, String tipo) throws Exception{
		if(tipo.equalsIgnoreCase("noob")){
			return noob(nome,login);			
		}
		return null;
	}

}
