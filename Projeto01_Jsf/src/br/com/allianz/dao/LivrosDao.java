package br.com.allianz.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.allianz.models.Livro;

public class LivrosDao extends Dao{

	//Método para incluir um novo livro
	public void IncluirLivro(Livro livro) throws Exception{
		try {
			abrirConexao();
			String sql = "INSERT INTO LIVROS (TITULO,AUTOR,DATAPUB,PRECO) VALUES (?,?,?,?)";
			stmt = cn.prepareStatement(sql);
			stmt.setString(1, livro.getTitulo());
			stmt.setString(2, livro.getAutor());
			stmt.setDate(3, new Date(livro.getDataPublicacao().getTime()));
			stmt.setDouble(4, livro.getPreco());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao();
		}
	}
	
	public List<Livro> listarLivros() throws Exception{
		List<Livro> livros = new ArrayList<>();
		try {
			abrirConexao();
			stmt = cn.prepareStatement("SELECT * FROM LIVROS");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getInt("ID"));
				livro.setTitulo(rs.getString("TITULO"));
				livro.setAutor(rs.getString("AUTOR"));
				livro.setDataPublicacao(rs.getDate("DATAPUB"));
				livro.setPreco(rs.getDouble("PRECO"));
				livros.add(livro);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			fecharConexao();
		}
		return livros;
	}
}
