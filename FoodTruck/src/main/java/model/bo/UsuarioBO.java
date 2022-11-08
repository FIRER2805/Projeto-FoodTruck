package model.bo;

import model.dao.UsuarioDAO;
import model.vo.UsuarioVo;

public class UsuarioBO {

	public UsuarioVo realizarLoginBO(UsuarioVo usuarioVo) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.realizarLoginDAO(usuarioVo);
	}

}
