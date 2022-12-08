package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.ItemVendaVO;
import model.vo.VendaVO;

public class VendaDAO {

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

	public int cancelarVenda(int idVenda) {
		String update = "update venda set datacancelamento = ? where idvenda = ?";
		
		return 0;
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

}
