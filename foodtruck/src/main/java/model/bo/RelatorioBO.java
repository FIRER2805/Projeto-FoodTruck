package model.bo;

import java.util.ArrayList;

import model.dao.RelatorioDAO;
import model.dto.VendasCanceladasDTO;

public class RelatorioBO {
	public ArrayList<VendasCanceladasDTO> gerarRelatorioVendasCanceladasBO() {
		RelatorioDAO relatorioDAO = new RelatorioDAO();
	
		return relatorioDAO.gerarRelatorioVendasCanceladasDAO();
	}
}
