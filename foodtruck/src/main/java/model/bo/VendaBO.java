package model.bo;

import model.dao.EntregaDAO;
import model.dao.ProdutoDAO;
import model.dao.UsuarioDAO;
import model.dao.VendaDAO;
import model.vo.ItemVendaVO;
import model.vo.VendaVO;

public class VendaBO {

	public VendaVO cadastrarVenda(VendaVO venda) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		if (usuarioDAO.verificarExistenciaResgistroPorCpfDAO(venda.getIdUsuarioVO())) {
			ProdutoDAO produtoDAO = new ProdutoDAO();
			boolean listaItensEValida = true;
			for (ItemVendaVO itemVendaVO : venda.getListaItemVendaVO()) {
				if (!produtoDAO.verificaExistenciaRegistroProdutoPorIdProdutoDAO(itemVendaVO.getIdProduto())) {
					System.out.println("o produto de id " + itemVendaVO.getIdProduto() + "não existe na base de dados.");
					listaItensEValida = false;
				}
			}
			if (listaItensEValida) {
				VendaDAO vendaDAO = new VendaDAO();
				venda = vendaDAO.cadastrarVenda(venda);
				if (venda.getIdVenda() != 0) {
					boolean resultado = vendaDAO.cadastrarItemVenda(venda);
					if (!resultado) {
						System.out.println("\nNão foi possível incluir algum item do produto.");
					}
					if (venda.isFlagEntrega()) {
						EntregaBO entregaBO = new EntregaBO();
						resultado = entregaBO.cadastrarEntregaBO(venda.getIdVenda());
						if (!resultado) {
							System.out.println("\nNão foi possível cadastrar entrega.");
						}
					}
				} else {
					System.out.println("\nNão foi possível cadastrar a venda");
				}
			}
		} else {
			System.out.println("O usuário desta venda não existe na base de dados");
		}
		return venda;
	}

	public boolean cancelarVenda(VendaVO venda) {
		VendaDAO vendaDAO = new VendaDAO();
		if(vendaDAO.verificarExistenciaRegistroPorIdVendaDAO(venda.getIdVenda()))
		{
			if(!vendaDAO.verificaCancelamentoPorIdVendaDAO(venda.getIdVenda()))
			{
				if(vendaDAO.dataExclusaoValida(venda))
				{
					vendaDAO.cancelarVenda(venda);
				}
			}
		}
		return vendaDAO.cancelarVenda(venda);
	}

	public boolean verificarVendaParaAtualizarSituacaoEntrega(VendaVO vendaVO) {
		VendaDAO vendaDAO = new VendaDAO();
		EntregaDAO entregaDAO = new EntregaDAO();
		boolean retorno = false;
		if (vendaDAO.verificarExistenciaRegistroPorIdVendaDAO(vendaVO.getIdVenda())) {
			if (vendaDAO.verificarVendaPossuiEntrega(vendaVO.getIdVenda())) {
				if (!vendaDAO.verificaCancelamentoPorIdVendaDAO(vendaVO.getIdVenda())) {
					if (entregaDAO.consultarEntregaPorIdVendaDAO(vendaVO.getIdVenda()).getDataEntrega() == null) {
						retorno = true;
					} else {
						System.out.println("\nVenda já teve a entrega realizada.");
					}
				} else {
					System.out.println("\nVenda já se encontra cancelada na base de dados.");
				}
			} else {
				System.out.println("\nVenda não possui entrega.");
			}
		} else {
			System.out.println("\nVenda não localizada na base da dados.");
		}
		return retorno;
	}

}
