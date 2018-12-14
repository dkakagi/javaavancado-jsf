package br.com.allianz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Dao {

	protected Connection cn;
	protected PreparedStatement stmt;
	protected ResultSet rs;
	
	protected void abrirConexao() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_livros", "root", "p@ssword");
	}
	
	protected void fecharConexao() throws Exception{
		if(cn != null && !cn.isClosed()) {
			cn.close();
		}
	}
}
