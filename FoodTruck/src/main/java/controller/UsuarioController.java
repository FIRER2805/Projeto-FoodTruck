package controller;

import java.util.ArrayList;

import model.bo.UsuarioBO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVo;

public class UsuarioController {

	public UsuarioVo realizarLoginController(UsuarioVo usuarioVo) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.realizarLoginBO(usuarioVo);
	}

	public ArrayList<TipoUsuarioVO> consultarTipoUsuario() {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTipoUsuarioBO();
	}

	public UsuarioVo cadastrarUsuarioController(UsuarioVo usuarioVo) {
		UsuarioBO usuarioBO = new UsuarioBO();
		return usuarioBO.consultarTipoUsuarioBO(usuarioVo);
	}

}
