package model.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import model.dao.Banco;
import model.vo.EntregaVO;

public class EntregaDAO {

	public boolean cadastrarEntregaDAO(EntregaVO entregaVO) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean retorno = false;
		
		String query = "INSERT INTO entrega (idVenda, idEntregador, idSituacaoEntrega) VALUES ("
				+ entregaVO.getIdVenda() + ", "
				+ entregaVO.getIdEntregador() + ", "
				+ entregaVO.getSituacao().getValor() + ")";
		
		try 
		{
			int resultado = stmt.executeUpdate(query);
			if(resultado == 1)
			{
				retorno = true;
			}
		} 
		catch(SQLException erro)
		{
			System.out.println("Erro ao executar a query do método cadastrarEntregaDAO da classe EntregaDAO!");
			System.out.println("Erro: " + erro.getMessage());
		}
		finally 
		{
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return retorno;
	}

}
