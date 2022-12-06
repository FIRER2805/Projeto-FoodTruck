package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.VendaVO;

public class VendaDAO {

	public VendaVO cadastrarVenda(VendaVO venda) {
		String insert = "insert into venda(idusuario, numeropedido, datavenda, datacancelamento,"
				+ "flagentrega, taxaentrega) values(?,?,?, null, ?,?);";
		Connection conn = Banco.getConnection();
		PreparedStatement prestmt = Banco.getPreparedStatementWithPk(conn, insert);
		try 
		{
			prestmt.setInt(1, venda.getIdUsuarioVO());
			prestmt.setInt(2, venda.getNumeroPedido());
			prestmt.setObject(3, venda.getDataVenda());
			prestmt.setBoolean(4, venda.isFlagEntrega());
			prestmt.setDouble(5, venda.getTaxaEntrega());
			prestmt.executeUpdate();
			ResultSet resultado = prestmt.getGeneratedKeys();
			if(resultado.next())
			{
				venda.setIdVenda(Integer.parseInt(resultado.getString(1)));
			}
			return venda;
		}
		catch(SQLException erro)
		{
			System.out.println("Erro no método cadastrarVenda da classe VendaDAO");
			System.out.println(erro.getMessage());
			return null;
		}
	}

	public int cancelarVenda(int idVenda) {
		String update = "update venda set datacancelamento = ? where idvenda = ?";
		
		return 0;
	}

}
