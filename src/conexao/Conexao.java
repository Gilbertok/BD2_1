package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private Connection conexao;
	
	private final String URL = "jdbc:mysql://localhost:3306/BD2?useTimezone=true&serverTimezone=UTC";
	
	private final String USER = "root";
	private final String PASSWORD = "root";
	private final String TP_CONEXAO = "com.mysql.cj.jdbc.Driver";
	
	public Connection abrirConexao(){
		try{
			Class.forName(TP_CONEXAO);
			conexao = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Conectou!");
		}catch(ClassNotFoundException | SQLException ex){
			ex.printStackTrace();
		}
		return conexao;
	}
	
	public void fecharConexao(){
		if(conexao != null){
			try {
				this.conexao.close();
				System.out.println("Fechei conexão!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
