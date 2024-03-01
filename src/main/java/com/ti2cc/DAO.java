package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "postgre";
		String password = "andreluiz27";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("conexao efetuada com sucesso!");
		} catch (ClassNotFoundException e) {
			System.err.println("Driver de conexão não encontrado" + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão não efetuada com sucesso" + e.getMessage());
		}
		
		return status;
	}
}