package model.bo;

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
		}else {
			System.out.println("O usuário desta venda não existe na base de dados");
		}
		return venda;
	}

	public int cancelarVenda(int idVenda) {
		VendaDAO vendaDAO = new VendaDAO();
		return vendaDAO.cancelarVenda(idVenda);
	}

}
