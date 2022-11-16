package model.bo;

import java.util.ArrayList;

import model.dao.UsuarioDAO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVo;

public class UsuarioBO {

	public UsuarioVo realizarLoginBO(UsuarioVo usuarioVo) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.realizarLoginDAO(usuarioVo);
	}

	public ArrayList<TipoUsuarioVO> consultarTipoUsuarioBO() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.consultarTipoUsuarioDAO();
	}

	public UsuarioVo cadastrarUsuarioBO(UsuarioVo usuarioVo) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if(usuarioDAO.verificarExistenciaRegistroPorCpfDao(usuarioVo)) 
		{
			System.out.println("\nUsuário já cadastrado");
		}
		else 
		{
			usuarioVo = usuarioDAO.cadastrarUsuarioDAo(usuarioVo);
		}
		return null;
	}

}
