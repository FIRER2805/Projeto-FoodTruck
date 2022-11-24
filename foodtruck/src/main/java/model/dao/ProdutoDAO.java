package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.vo.ProdutoVO;
import model.vo.TipoProdutoVO;

public class ProdutoDAO {

	// cadastrar um produto
	public void cadastrarProdutoDAO(ProdutoVO produto) {
		// query
		String query = "insert into produto(idtipoproduto,nome,preco,datacadastro,dataexclusao) values(?,?,?,now(),null)";
		
		// conecao e preparacao
		Connection conn = Banco.getConnection();
		PreparedStatement prstmt = Banco.getPreparedStatement(conn,query);
		try {
			// inserindo os valores da query
			prstmt.setInt(1, produto.getTipoProduto().getValor());
			prstmt.setString(2, produto.getNome());
			prstmt.setDouble(3, produto.getPreco());
			prstmt.execute();
			System.out.println("produto cadastrado com sucesso!");
		}
		// mostra os erros caso ocorra algum
		catch(SQLException erro)
		{
			System.out.println("erro no metodo cadastrarProdutoDAO");
			System.out.println(erro.getMessage());
		}
		finally 
		{
			Banco.closePreparedStatement(prstmt);
			Banco.closeConnection(conn);
		}
	}

	public ArrayList<ProdutoVO> ConsultarTodosProdutosDAO() {
		String query = "select * from produto";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		ResultSet resultado = null;
		try 
		{
			resultado = stmt.executeQuery(query);
			while(resultado.next())
			{
				ProdutoVO produto = new ProdutoVO();
				TipoProdutoVO tipoProduto = TipoProdutoVO.getTipoProdutoVOPorValor(resultado.getInt(2));
				produto.setIdProduto(resultado.getInt(1));
				produto.setTipoProduto(tipoProduto);
				produto.setNome(resultado.getString(3));
				produto.setPreco(resultado.getDouble(4));
				produto.setDataCadastro(LocalDateTime.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				if(resultado.getString(6) != null)
					produto.setDataExclusao(LocalDateTime.parse(resultado.getString(6), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				produtos.add(produto);
			}
		}
		catch(SQLException erro)
		{
			System.out.println("Ocorreu um erro no método ConsultarTodosProdutosDAO");
			System.out.println(erro.getMessage());
			produtos = null;
		}
		return produtos;
	}

}
