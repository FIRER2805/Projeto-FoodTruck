package model.bo;

import java.util.ArrayList;
import java.util.Random;

import model.dao.EntregaDAO;
import model.dao.UsuarioDAO;
import model.dao.VendaDAO;
import model.vo.EntregaVO;
import model.vo.SituacaoEntregaVO;
import model.vo.UsuarioVO;
import model.vo.VendaVO;

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

	public boolean atualizarSituacaoEntregaBO(VendaVO vendaVO) {
		boolean retorno = false;
		EntregaDAO entregaDAO = new EntregaDAO();
		VendaBO vendaBO = new VendaBO();
		boolean resultado = vendaBO.verificarVendaParaAtualizarSituacaoEntrega(vendaVO);
		if(resultado)
		{
			retorno = entregaDAO.atualizarSituacaoEntregaDAO(vendaVO);
		}
		return retorno;
	}

	public void cancelaEntrega(int id) {
		VendaDAO vendaDAO = new VendaDAO();
		EntregaDAO entregaDAO = new EntregaDAO();
		if(vendaDAO.verificarExistenciaRegistroPorIdVendaDAO(id))
		{
			if(!vendaDAO.verificaCancelamentoPorIdVendaDAO(id))
			{
				if(!entregaDAO.entregaCancelada(id))
				{
					entregaDAO.cancelarEntrega(id);
				}
				else 
				{
					System.out.println("Entrega já cancelada");
				}
			}
			else 
			{
				System.out.println("Venda já cancelada");
			}
		}
		else 
		{
			System.out.println("Venda não encontrada");
		}
		
	}

}
