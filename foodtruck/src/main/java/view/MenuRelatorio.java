package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import controller.RelatorioController;
import model.dao.Banco;
import model.dto.VendasCanceladasDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

public class MenuRelatorio {

	private static final int OPCAO_MENU_RELATORIO_PEDIDOS = 1;
	private static final int OPCAO_MENU_RELATORIO_ACOPANHAMENTO_PEDIDO = 2;
	private static final int OPCAO_MENU_VOLTAR = 9;

	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuRelatorio() {
		int opcao = this.apresentarOpcoesMenu();
		while (opcao != OPCAO_MENU_VOLTAR) {
			switch (opcao) {
			case OPCAO_MENU_RELATORIO_PEDIDOS:
				RelatorioController.relatorioPedidos();
				/*String printFileName;
                try {
                    String currentPath = "C:\\Users\\Gabriel\\Downloads\\Projeto-FoodTruck-main\\foodtruck";
                    try {
                        currentPath = new java.io.File(".").getCanonicalPath();
                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                    }
                    System.out.println(currentPath);
                    JasperRunManager.runReportToPdfFile(currentPath + "/relatorio011.jasper", currentPath + "/report1.pdf", null, Banco.getConnection());
                    System.out.println("Relatorio gerado em " + currentPath + "/report1.pdf");
                } catch (JRException ex) {
                    System.out.println("Não foi possivel imprimir, por favor verifique o modelo de impressão");
                }*/
				break;
			case OPCAO_MENU_RELATORIO_ACOPANHAMENTO_PEDIDO:
				System.out.println("Digite o id do pedido:");
				RelatorioController.acompanhamentoPedido(Integer.parseInt(teclado.nextLine()));
				/*String printFileName2;
                try {
                    String currentPath = "C:\\Users\\Gabriel\\Downloads\\Projeto-FoodTruck-main\\foodtruck";
                    try {
                        currentPath = new java.io.File(".").getCanonicalPath();
                    } catch (IOException ex) {
                        System.out.println(ex.toString());
                    }
                    System.out.println(currentPath);
                    HashMap<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("idvenda", 2);
                    JasperRunManager.runReportToPdfFile(currentPath + "/report2.jasper", currentPath + "/report2.pdf", parameters, Banco.getConnection());
                    System.out.println("Relatorio 2 gerado em " + currentPath + "/report2.pdf");
                } catch (JRException ex) {
                    System.out.println("Não foi possivel imprimir 2, por favor verifique o modelo de impressão");
                }*/
				break;
			default: {
				System.out.println("\nOpção inválida!");
			}
			}
			opcao = this.apresentarOpcoesMenu();
		}
	}

	private int apresentarOpcoesMenu() {
		System.out.println("\n---------Sistema FoodTruck---------");
		System.out.println("---------Menu de Relatórios----------");
		System.out.println("\nOpções: ");
		System.out.println(OPCAO_MENU_RELATORIO_PEDIDOS + " - Relatório pedidos");
		System.out.println(OPCAO_MENU_RELATORIO_ACOPANHAMENTO_PEDIDO + " - Acompanhamento de pedido");
		System.out.println(OPCAO_MENU_VOLTAR + " - Voltar");
		System.out.println("\nDigite uma opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
}
