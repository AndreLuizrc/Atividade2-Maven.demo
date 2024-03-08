package com.ti2cc;

import java.util.Scanner;

public class Principal {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static Usuario InsertUser() {
		Usuario user = new Usuario();
		System.out.println("Digite o código do usuário: ");
		user.setCodigo(sc.nextInt());
		sc.nextLine();
		System.out.println("Digite o login: ");
		user.setLogin(sc.nextLine());
		System.out.println("Digite a senha: ");
		user.setSenha(sc.nextLine());
		System.out.println("Digite o sexo: ");
		user.setSexo(sc.nextLine().charAt(0));
		
		return user;
	};
	
	public static Usuario UpdateUser(int codigo) {
		Usuario user = new Usuario();
		user.setCodigo(codigo);
		System.out.println("Digite o login: ");
		user.setLogin(sc.nextLine());
		System.out.println("Digite a senha: ");
		user.setSenha(sc.nextLine());
		System.out.println("Digite o sexo: ");
		user.setSexo(sc.nextLine().charAt(0));
		
		return user;
	};
	
	public static void main(String [] args) {
		DAO dao = new DAO();
		int opcao;
		boolean status;
		
		dao.conectar();
		
		System.out.println("1 - Criar Usuario\n"
						  + "2 - Buscar usuario\n" 
						  + "3 - Atualizar usuario\n"
						  + "4 - Deletar usuario\n"
						  + "5 - Buscar todos os usuarios");
		
		opcao = sc.nextInt();
		
		switch(opcao) {
			case 1:
				Usuario usuario = InsertUser();
				status = dao.inserirUsuario(usuario);
				if(status == true) {
					System.out.println("Usuario cadastrado com sucesso!");
				}
				break;
			case 2:
				int num;
				Usuario user = null;
				System.out.println("Digite o id do usuario: ");
				num = sc.nextInt();
				user = dao.getUsuarioId(num);
				System.out.println(user);
				break;
			case 3:
				int num_att;
				Usuario user_att = null;
				System.out.println("Digite o id do usuario que deseja atualizar: ");
				num_att = sc.nextInt();
				sc.nextLine();
				user_att = UpdateUser(num_att);
				status = dao.atualizarUsuario(user_att);
				if(status == true) {
					System.out.println("Usuario atualizado com sucesso!");
				}
				break;
			case 4:
				int num_delet;
				System.out.println("Digite o id do usuario: ");
				num_delet = sc.nextInt();
				status = dao.excluirUsuario(num_delet);
				if(status == true) {
					System.out.println("Usuario deletado com sucesso!");
				}
				break;
			case 5:
				dao.getUsuarios();
				break;
				
		}

		
		dao.close();
	}
		
		
}
	
