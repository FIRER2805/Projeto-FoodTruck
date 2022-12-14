package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.vo.EntregaVO;
import model.vo.ItemVendaVO;
import model.vo.VendaVO;

public class VendaDAO {

	private static final int ENTREGA_CANCELADA = 6;
	
	public VendaVO cadastrarVenda(VendaVO venda) {
		String query = "INSERT INTO venda (idUsuario, numeroPedido, dataVenda, flagEntrega";
		if(venda.isFlagEntrega())
		{
			query += ", taxaEntrega) VALUES (?,?,?,?,?)";
		}
		else
		{
			query += ") VALUES (?,?,?,?)";
		}
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try 
		{
			pstmt.setInt(1, venda.getIdUsuarioVO());
			pstmt.setInt(2, venda.getNumeroPedido());
			pstmt.setObject(3, venda.getDataVenda());
			if(venda.isFlagEntrega())
			{
				pstmt.setInt(4, 1);
				pstmt.setDouble(5, venda.getTaxaEntrega());
			}
			else 
			{
				pstmt.setInt(4, 0);
			}
			pstmt.execute();
			ResultSet resultado = pstmt.getGeneratedKeys();
			if(resultado.next())
			{
				venda.setIdVenda(resultado.getInt(1));
			}
		}
		catch(SQLException erro)
		{
			System.out.println("Erro ao executar a query do método cadastrarVenda da classe vendaDAO!");
			System.out.println("Erro: " + erro.getMessage());
		}
		finally 
		{
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return venda;
	}

	public boolean cancelarVenda(VendaVO venda) {
		String update = "UPDATE venda set datacancelamento = ? WHERE idvenda = ?";
		String update2 = "UPDATE entrega set idsituacaoentrega = ? WHERE idvenda = ?";
		boolean retorno = false;
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, update);
		PreparedStatement pstmt2 = Banco.getPreparedStatement(conn, update2);
		try 
		{
			pstmt.setString(1, venda.getDataCancelamento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
			pstmt.setInt(2, venda.getIdVenda());
			pstmt2.setInt(1, ENTREGA_CANCELADA);
			pstmt2.setInt(2, venda.getIdVenda());
			if(pstmt.executeUpdate() != 0 && pstmt2.executeUpdate() != 0)
			{
				retorno = true;
			}
		}
		catch(SQLException erro)
		{
			System.out.println("Erro no método cancelarVenda da classe VendaDAO");
			System.out.println("Erro: " + erro.getMessage());
		}
		finally 
		{
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean cadastrarItemVenda(VendaVO venda) {
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = null;
		int contador = 0;
		boolean retorno = false;
		try 
		{
			for(ItemVendaVO item : venda.getListaItemVendaVO())
			{
				String query = "INSERT INTO itemVenda (idVenda, idProduto, quantidade) VALUES(?,?,?)";
				pstmt = Banco.getPreparedStatementWithPk(conn, query);
				pstmt.setInt(1, venda.getIdVenda());
				pstmt.setInt(2, item.getIdProduto());
				pstmt.setInt(3, item.getQuantidade());
				pstmt.execute();
				ResultSet resultado = pstmt.getGeneratedKeys();
				if(resultado.next())
				{
					item.setIdItemVenda(resultado.getInt(1));
					contador++;
				}
			}
			if(contador == venda.getListaItemVendaVO().size())
			{
				retorno = true;
			}
			else 
			{
				System.out.println("Nem todos os produto foram cadastrador.");
			}
		}
		catch (SQLException erro)
		{
			System.out.println("Erro ao executar a query do método cadastrarItemVendaDAO da classe VendaDAO!");
			System.out.println("Erro: " + erro.getMessage());
		}
		finally 
		{
			Banco.closeStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

	public boolean verificarExistenciaRegistroPorIdVendaDAO(int idVenda) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String query = "SELECT idVenda FROM venda WHERE idvenda = " + idVenda;
		try 
		{
			resultado = stmt.executeQuery(query);
			if(resultado.next())
			{
				retorno = true;
			}
		}
		catch (SQLException erro)
		{
			System.out.println("ERRO ao executar a query do método verificarExistenciaRegistroPorIdVendaDAO da classe VendaDAO!");
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

	public boolean verificarVendaPossuiEntrega(int idVenda) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String query = "SELECT flagEntrega from venda WHERE idvenda = " + idVenda;
		try 
		{
			resultado = stmt.executeQuery(query);
			if(resultado.next())
			{
				if(resultado.getString(1).equals("1"))
				{
					retorno = true;
				}
			}
		}
		catch(SQLException erro)
		{
			System.out.println("Erro ao executar a query do método verificarVendaPossuiEntrega!");
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

	public boolean verificaCancelamentoPorIdVendaDAO(int idVenda) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		String query = "SELECT datacancelamento from venda WHERE idvenda = " + idVenda;
		try 
		{
			resultado = stmt.executeQuery(query);
			if(resultado.next())
			{
				if(resultado.getString(1) != null)
				{
					retorno = true;
				}
			}
		}
		catch(SQLException erro)
		{
			System.out.println("Erro ao executar a query do método verificaCancelamentoPorIdVendaDAO!");
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

	public boolean dataExclusaoValida(VendaVO venda) {
		String query = "SELECT datavenda FROM venda WHERE idvenda = ? ";
		boolean retorno = false;
		Connection conn = Banco.getConnection();
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		ResultSet resultado = null;
		try 
		{
			pstmt.setInt(1, venda.getIdVenda());
			resultado = pstmt.executeQuery(query);
			if(resultado != null && resultado.next())
			{
				// verifica se a data de venda é antes da data de exclusão
				if(LocalDateTime.parse(resultado.getString(1),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).isBefore(venda.getDataCancelamento()))
				{
					retorno = true;
				}
			}
		}
		catch(SQLException erro)
		{
			System.out.println("Erro no método dataExclusaoValida da classe VendaDAO");
			System.out.println("Erro: " + erro.getMessage());
		}
		finally 
		{
			Banco.closeResultSet(resultado);
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}
}
