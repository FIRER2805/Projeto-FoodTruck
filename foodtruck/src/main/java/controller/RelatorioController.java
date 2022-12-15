package controller;

import java.util.ArrayList;

import model.bo.RelatorioBO;
import model.dto.VendasCanceladasDTO;

public class RelatorioController {
	public static void relatorioPedidos() 
	{
		RelatorioBO relatorioBO = new RelatorioBO();
		relatorioBO.relatorioPedidos();
	}
	
	public static void acompanhamentoPedido(int id)
	{
		RelatorioBO relatorioBO = new RelatorioBO();
		relatorioBO.acompanhamentoPedido(id);
	}
}
