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
		String username = "postgres";
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
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return status;
	}
	
	public boolean inserirUsuario(Usuario usuario) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO usuario (codigo, login, senha, sexo)"
					+ "VALUES (" + usuario.getCodigo() + ", '" + usuario.getLogin() +"', '"
					+ usuario.getSenha() + "' , '" + usuario.getSexo() + "');");
			
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public boolean atualizarUsuario(Usuario usuario) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE usuario SET login = '" + usuario.getLogin() +"', senha = '" + usuario.getSenha() + "' , sexo = '" + usuario.getSexo() + "'" 
			+"WHERE codigo = " + usuario.getCodigo();
			
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public boolean excluirUsuario(int codigo) {
		boolean status = false;
		
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM usuario WHERE codigo =" + codigo;
			
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return status;
	}
	
	public Usuario[] getUsuarios() {
		Usuario[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario");
			if(rs.next()) {
				rs.last();
				usuarios = new Usuario[rs.getRow()];
				rs.beforeFirst();
				
				for(int i = 0; rs.next(); i++) {
					usuarios[i] = new Usuario(rs.getInt("codigo"), rs.getString("login"),
							rs.getString("senha"), rs.getString("sexo").charAt(0));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return usuarios;
	}
	
	public Usuario[] getUsuariosMasculinos() {
		Usuario[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario WHERE usuario.sexo LIKE 'M'");
			if(rs.next()) {
				rs.last();
				usuarios = new Usuario[rs.getRow()];
				rs.beforeFirst();
				
				for(int i = 0; rs.next(); i++) {
					usuarios[i] = new Usuario(rs.getInt("codigo"), rs.getString("login"),
							rs.getString("senha"), rs.getString("sexo").charAt(0));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return usuarios;
	}
}