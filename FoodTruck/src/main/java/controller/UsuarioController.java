package controller;

import model.bo.UsuarioBO;
import model.vo.UsuarioVo;

public class UsuarioController {

	public UsuarioVo realizarLoginController(UsuarioVo usuarioVo) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.realizarLoginBO(usuarioVo);
	}

}
