package model.bo;

import java.util.ArrayList;
import java.util.Random;

import model.dao.UsuarioDAO;
import model.vo.EntregaVO;
import model.vo.SituacaoEntregaVO;
import model.vo.UsuarioVO;

public class EntregaBO {

	public boolean cadastrarEntregaBO(int idVenda) {
		boolean retorno = true;
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		ArrayList<UsuarioVO> listaEntregadores = usuarioDAO.consultarListaEntregadores();
		if(listaEntregadores.isEmpty())
		{
			System.out.println("Não existem entregadores cadastros na base de dados!");
			retorno = false;
		}
		else 
		{
			Random gerador = new Random();
			UsuarioVO entregador = listaEntregadores.get(gerador.nextInt(listaEntregadores.size()));
			EntregaVO entregaVO = new EntregaVO(0, idVenda,entregador.getIdUsuario(), SituacaoEntregaVO.PREPARANDO_PEDIDO, null);
			EntregaDAO entregaDAO = new EntregaDAO();
			boolean resultado = entregaDAO.cadastrarEntregaDAO(entregaVO);
			if(!resultado)
			{
				System.out.println("Houve um problema ao tentar cadastrar a entrega!");
				retorno = false;
			}
		}
		return retorno;
	}

}
