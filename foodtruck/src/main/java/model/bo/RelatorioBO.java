package model.bo;

import java.util.ArrayList;

import model.dao.RelatorioDAO;
import model.dto.VendasCanceladasDTO;
import relatorio.Relatorio;

public class RelatorioBO {
	public void relatorioPedidos()
	{
		Relatorio.listaPedidos();
	}
	public void acompanhamentoPedido(int id)
	{
		Relatorio.acompanhamentoPedido(id);
	}
}
