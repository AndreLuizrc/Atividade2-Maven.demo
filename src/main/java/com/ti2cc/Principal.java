package com.ti2cc;

public class Principal {
	
	public static void main(String [] args) {
		DAO dao = new DAO();
		
		dao.conectar();
		
		Usuario usuario = new Usuario(11, "pablo", "pablo", 'M');
		
		//Inserir Usuarios
		if(dao.inserirUsuario(usuario) == true) {
			System.out.println("Usuário cadastrado com sucesso: " + usuario.toString());
		}
		
		//Bsucar usuarios masculinos
		System.out.println("Usuarios do sexo masculino: ");
		Usuario[] usuarios = dao.getUsuariosMasculinos();
		for(int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}
		
		//Atualizar senha usuario
		usuario.setSenha("123456");
		dao.atualizarUsuario(usuario);
		
		//Mostrar todos usuarios
		System.out.println("Usuarios do sistema: ");
		usuarios = dao.getUsuarios();
		for(int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}
		
		//excluir usuario
		dao.excluirUsuario(usuario.getCodigo());
		
		//Mostrar todos usuarios
		System.out.println("Usuarios do sistema: ");
		usuarios = dao.getUsuarios();
		for(int i = 0; i < usuarios.length; i++) {
			System.out.println(usuarios[i].toString());
		}
		
		dao.close();
	}
}
