package controller;

import model.bo.VendaBO;
import model.vo.VendaVO;

public class VendaController {

	public VendaVO cadastrarVenda(VendaVO venda) {
		VendaBO vendaBO = new VendaBO();
		return vendaBO.cadastrarVenda(venda);
	}

	public static boolean cancelarVenda(VendaVO vendaVO) {
		VendaBO vendaBO = new VendaBO();
		return vendaBO.cancelarVenda(vendaVO);
	}
	
}
