package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVo;

public class UsuarioDAO {

	public UsuarioVo realizarLoginDAO(UsuarioVo usuarioVo) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		
		String query = "SELECT u.idUsuario, tipo.descricao, u.nome, u.cpf, u.email, "
				+ "u.telefone, u.datacadastro, u.dataexpiracao "
				+ "FROM Usuario u, TipoUsuario tipo "
				+ "WHERE u.login like '" + usuarioVo.getLogin() + "' "
				+ "AND u.senha like '" + usuarioVo.getSenha() + "' "
				+ "AND u.idTipoUsuario = tipo.idTipoUsuario";
		
		try 
		{
			resultado = stmt.executeQuery(query);	
			if(resultado.next())
			{
				usuarioVo.setIdUsuario(Integer.parseInt(resultado.getString(1)));
				usuarioVo.setTipoUsuario(TipoUsuarioVO.valueOf(resultado.getString(2)));
				usuarioVo.setNome(resultado.getString(3));
				usuarioVo.setCpf(resultado.getString(4));
				usuarioVo.setEmail(resultado.getString(5));
				usuarioVo.setTelefone(resultado.getString(6));
				usuarioVo.setDataCadastro(LocalDateTime.parse(resultado.getString(7),
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				if(resultado.getString(8) != null)
				{
					usuarioVo.setDataExpiracao(LocalDateTime.parse(resultado.getString(8),
							DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				}
			}
		}
		catch(SQLException erro)
		{
			System.out.println("Erro ao executar a query do metodo realizarLoginDAO!");
			System.out.println("Erro: " + erro.getMessage());
		}
		finally 
		{
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return usuarioVo;
	}
}
