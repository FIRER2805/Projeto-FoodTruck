package controller;

import model.bo.VendaBO;
import model.vo.VendaVO;

public class VendaController {

	public VendaVO cadastrarVenda(VendaVO venda) {
		VendaBO vendaBO = new VendaBO();
		return vendaBO.cadastrarVenda(venda);
	}

	public static int cancelarVenda(int idVenda) {
		VendaBO vendaBO = new VendaBO();
		return vendaBO.cancelarVenda(idVenda);
	}
	
}
