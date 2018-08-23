package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dominio.Livro;

public class LivroDao implements IDao<Livro> {

	private Connection connection;

	public LivroDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void save(Livro t) {

		String SQL = "insert into livro (titulo, autor, editora," + "avaliacao, preco) values (?,?,?,?,?)";
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(SQL);
			pstmt.setString(1, t.getTitulo());
			pstmt.setString(2, t.getAutor());
			pstmt.setString(3, t.getEditora());
			pstmt.setInt(4, t.getAvaliacao());
			pstmt.setDouble(5, t.getPreco());
			pstmt.execute();
			System.out.println("Livro inserido com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Livro getOne(int id) {

		Livro p = null;
		try {
			Statement sta = connection.createStatement();
			ResultSet elements = sta.executeQuery("select * from livro where id = " + id);
			while (elements.next()) {
				p = new Livro();
				p.setId(elements.getInt("id"));
				p.setAutor(elements.getString("autor"));
				p.setTitulo(elements.getString("titulo"));
				p.setEditora(elements.getString("editora"));
				p.setAvaliacao(elements.getInt("avaliacao"));
				p.setPreco(elements.getDouble("preco"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;
	}

	@Override
	public List<Livro> list() {
				
		List<Livro> livros = new ArrayList<>();
		
		try {
			Statement sta = connection.createStatement();
			ResultSet elements = sta.executeQuery("select * from livro");
			while (elements.next()) {
				Livro p = new Livro();
				p.setId(elements.getInt("id"));
				p.setAutor(elements.getString("autor"));
				p.setTitulo(elements.getString("titulo"));
				p.setEditora(elements.getString("editora"));
				p.setAvaliacao(elements.getInt("avaliacao"));
				p.setPreco(elements.getDouble("preco"));
				livros.add(p);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return livros;
	}

	@Override
	public void update(Livro t) {

		String SQL = "Update livro set titulo=?,autor=?,editora=?," + 
		"avaliacao=?,preco=? where id = ?";
		try {
			PreparedStatement pstmt = this.connection.prepareStatement(SQL);
			pstmt.setString(1, t.getTitulo());
			pstmt.setString(2, t.getAutor());
			pstmt.setString(3, t.getEditora());
			pstmt.setInt(4, t.getAvaliacao());
			pstmt.setDouble(5, t.getPreco());
			pstmt.setInt(6, t.getId());
			pstmt.execute();// Executa o SQL
			System.out.println("Livro atualizado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void datele(int id) {
		String SQL = "Delete from livro where livro.id = ?";

		try {
			PreparedStatement pstmt = this.connection.prepareStatement(SQL);
			pstmt.setInt(1, id);
			pstmt.execute();
			System.out.println("Livro excluído com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
