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
		PreparedStatement prstmt = Banco.getPreparedStatement(conn, query);
		try {
			// inserindo os valores da query
			prstmt.setInt(1, produto.getTipoProduto().getValor());
			prstmt.setString(2, produto.getNome());
			prstmt.setDouble(3, produto.getPreco());
			prstmt.execute();
			System.out.println("produto cadastrado com sucesso!");
		}
		// mostra os erros caso ocorra algum
		catch (SQLException erro) {
			System.out.println("erro no metodo cadastrarProdutoDAO");
			System.out.println(erro.getMessage());
		} finally {
			Banco.closePreparedStatement(prstmt);
			Banco.closeConnection(conn);
		}
	}

	public ArrayList<ProdutoVO> ConsultarTodosProdutosDAO() {
		String query = "select * from produto;";
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ArrayList<ProdutoVO> produtos = new ArrayList<ProdutoVO>();
		ResultSet resultado = null;
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				ProdutoVO produto = new ProdutoVO();
				TipoProdutoVO tipoProduto = TipoProdutoVO.getTipoProdutoVOPorValor(resultado.getInt(2));
				produto.setIdProduto(resultado.getInt(1));
				produto.setTipoProduto(tipoProduto);
				produto.setNome(resultado.getString(3));
				produto.setPreco(resultado.getDouble(4));
				produto.setDataCadastro(LocalDateTime.parse(resultado.getString(5),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				if (resultado.getString(6) != null)
					produto.setDataExclusao(LocalDateTime.parse(resultado.getString(6),
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				produtos.add(produto);
			}
		} catch (SQLException erro) {
			System.out.println("Ocorreu um erro no método ConsultarTodosProdutosDAO");
			System.out.println(erro.getMessage());
			produtos = null;
		}
		return produtos;
	}

	public ProdutoVO consultaUmProduto(int id) {
		String query = "select * from produto where idproduto = ?;";
		Connection conn = Banco.getConnection();
		PreparedStatement prstmt = Banco.getPreparedStatement(conn, query);
		ResultSet resultado = null;
		ProdutoVO produto = new ProdutoVO();
		try {
			prstmt.setInt(1, id);
			resultado = prstmt.executeQuery();
			resultado.next();
			produto.setIdProduto(resultado.getInt(1));
			TipoProdutoVO tipoProduto = TipoProdutoVO.getTipoProdutoVOPorValor(resultado.getInt(2));
			produto.setTipoProduto(tipoProduto);
			produto.setNome(resultado.getString(3));
			produto.setPreco(resultado.getDouble(4));
			produto.setDataCadastro(
					LocalDateTime.parse(resultado.getString(5), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			if (resultado.getString(6) != null)
				produto.setDataExclusao(LocalDateTime.parse(resultado.getString(6),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		} catch (SQLException erro) {
			System.out.println("erro no metodo ConsultaUmProduto na classe ProdutoDAO");
			System.out.println(erro.getMessage());
			produto = null;
		}
		return produto;
	}

	public int atualizarProduto(ProdutoVO produto) {
		String query = "UPDATE produto SET idtipoproduto = ?, nome = ?, preco = ?, "
				+ "datacadastro = ? WHERE idproduto = ? ;";
		Connection conn = Banco.getConnection();
		PreparedStatement prstmt = Banco.getPreparedStatement(conn, query);
		try {
			prstmt.setInt(1, produto.getTipoProduto().getValor());
			prstmt.setString(2, produto.getNome());
			prstmt.setDouble(3, produto.getPreco());
			prstmt.setString(4, produto.getDataCadastro().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			prstmt.setInt(5, produto.getIdProduto());
			return prstmt.executeUpdate();
		} catch (SQLException erro) {
			System.out.println("Erro no metodo atualizarProduto da classe ProdutoDAO");
			System.out.println(erro.getMessage());
			return 0;
		}
	}

	public void excluirProduto(int idProduto) {
		String query = "UPDATE produto set dataexclusao = now() where idproduto = ?";
		Connection conn = Banco.getConnection();
		PreparedStatement prstmt = Banco.getPreparedStatement(conn, query);
		try {
			prstmt.setInt(1, idProduto);
			prstmt.executeUpdate();
			System.out.println("Produto excluido com sucesso!");
		} catch (SQLException erro) {
			System.out.println("Erro no metodo ExcluirProduto da classe ProdutoDAO");
			System.out.println(erro.getMessage());
		}
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosVigentesDAO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<ProdutoVO> listaProdutosVO = new ArrayList<ProdutoVO>();
		String query = "SELECT p.idProduto, tipo.descricao, p.nome, p.preco, p.datacadastro "
				+ "FROM produto p, tipoProduto tipo " + "WHERE p.idTipoProduto = tipo.idTipoProduto "
				+ "AND p.dataExclusao is NULL";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				ProdutoVO produtovo = new ProdutoVO();
				produtovo.setIdProduto(Integer.parseInt(resultado.getString(1)));
				produtovo.setTipoProduto(TipoProdutoVO.valueOf(resultado.getString(2)));
				produtovo.setNome(resultado.getString(3));
				produtovo.setPreco(Double.parseDouble(resultado.getString(4)));
				produtovo.setDataCadastro(LocalDateTime.parse(resultado.getString(5),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				listaProdutosVO.add(produtovo);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao executar a query do método consultarTodosProdutosVigentesDAO!");
			System.out.println("Erro: " + e.getMessage());
		}
		finally 
		{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaProdutosVO;
	}

	public boolean verificaExistenciaRegistroProdutoPorIdProdutoDAO(int idProduto) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		ResultSet resultado = null;
		String query = "SELECT idProduto FROM produto WHERE idProduto = " + idProduto;
		try 
		{
			resultado = stmt.executeQuery(query);
			if(resultado.next())
			{
				retorno = true;
			}
		} 
		catch(SQLException erro)
		{
			System.out.println("Erro ao executar a query do método verificarExistenciaRegistroProdutoPorIdProdutoDAO da classe ProdutoDAO");
			System.out.println("Erro: " + erro.getMessage());
		}
		finally
		{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
}
