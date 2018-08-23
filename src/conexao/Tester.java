package conexao;

import dao.LivroDao;
import dominio.Livro;

public class Tester {

	public static void main(String[] args) {

		Livro livro = new Livro("Getting real","37 signals","37 signals",4,25.87);
		
		Conexao c = new Conexao();
		LivroDao ldao = new LivroDao(c.abrirConexao());
		
		// Salvar
		ldao.save(livro);
		System.out.println(ldao.getOne(1));
		
		livro = ldao.getOne(1);
		
		// Alterar
		livro.setAutor("Alterado");
		ldao.update(livro);
		System.out.println(ldao.getOne(1));
		
		// Deletar
		//ldao.datele(1);
		
		// Listar
		System.out.println(ldao.list());
		
		c.fecharConexao();

	}

}
