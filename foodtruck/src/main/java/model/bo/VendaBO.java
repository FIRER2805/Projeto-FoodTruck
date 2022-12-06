package model.bo;

import model.dao.VendaDAO;
import model.vo.VendaVO;

public class VendaBO {

	public VendaVO cadastrarVenda(VendaVO venda) {
		VendaDAO vendaDAO = new VendaDAO();
		return vendaDAO.cadastrarVenda(venda);
	}

	public int cancelarVenda(int idVenda) {
		VendaDAO vendaDAO = new VendaDAO();
		return vendaDAO.cancelarVenda(idVenda);
	}

}
