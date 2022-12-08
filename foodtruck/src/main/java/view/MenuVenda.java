package view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ProdutoController;
import controller.VendaController;
import model.vo.ItemVendaVO;
import model.vo.ProdutoVO;
import model.vo.TipoUsuarioVO;
import model.vo.UsuarioVO;
import model.vo.VendaVO;

public class MenuVenda {
	private static final int OPCAO_MENU_CADASTRAR_VENDA = 1;
	private static final int OPCAO_MENU_CANCELAR_VENDA = 2;
	private static final int OPCAO_MENU_SITUACAO_ENTREGA = 3;
	private static final int OPCAO_MENU_VENDA_VOLTAR = 9;

	private static int NUMERO_PEDIDO = 0;
	private static double PERCENTUAL = 0.05;

	Scanner teclado = new Scanner(System.in);

	public void apresentarMenuVenda(UsuarioVO usuarioVO) {
		int opcao = this.apresentarOpcoesMenu(usuarioVO);
		while (opcao != OPCAO_MENU_VENDA_VOLTAR) {
			switch (opcao) {
			case OPCAO_MENU_CADASTRAR_VENDA:
				if (!usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ENTREGADOR)) {
					this.cadastrarVenda(usuarioVO);
				}
				break;
			case OPCAO_MENU_CANCELAR_VENDA:
				if (!usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ENTREGADOR)) {
					this.cancelarVenda();
				}
				break;
			case OPCAO_MENU_SITUACAO_ENTREGA:
				if (!usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.CLIENTE)) {
					this.atualizarSituacaoEntrega();
				}
				break;
			default:
				System.out.println("\nOpção Inválida!!!");
				break;
			}
			opcao = this.apresentarOpcoesMenu(usuarioVO);
		}
	}

	private void cadastrarVenda(UsuarioVO usuarioVO) {
		ArrayList<ProdutoVO> listaProdutosVO = this.listarProdutos();
		VendaVO vendaVO = new VendaVO();
		if (usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.CLIENTE)) {
			vendaVO.setIdUsuarioVO(usuarioVO.getIdUsuario());
		} else {
			System.out.print("Informe o código do cliente: ");
			vendaVO.setIdUsuarioVO(Integer.parseInt(teclado.nextLine()));
		}
		vendaVO.setNumeroPedido(this.gerarNumeroPedido());
		vendaVO.setDataVenda(LocalDateTime.now());
		double subTotal = 0;
		ArrayList<ItemVendaVO> listaItemVendaVO = new ArrayList<ItemVendaVO>();
		String continuar = "N";
		do {
			ItemVendaVO itemVendaVO = new ItemVendaVO();
			subTotal += this.cadastrarItemVendaVO(itemVendaVO, listaProdutosVO);
			listaItemVendaVO.add(itemVendaVO);
			System.out.print("Deseja incluir mais um item? [S - N]: ");
			continuar = teclado.nextLine();
		} while (continuar.equalsIgnoreCase("S"));
		vendaVO.setListaItemVendaVO(listaItemVendaVO);

		System.out.print("Pedido é para Entregar [S - N]: ");
		String opcaoEntrega = teclado.nextLine();
		if (opcaoEntrega.toUpperCase().equals("5") || opcaoEntrega.toUpperCase().equals("N")) {
			double taxaEntrega = subTotal * PERCENTUAL;
			double totalConta = subTotal;
			if (opcaoEntrega.toUpperCase().equals("s")) {
				vendaVO.setFlagEntrega(true);
				vendaVO.setTaxaEntrega(taxaEntrega);
				totalConta += taxaEntrega;
			}
			System.out.println("Total da Conta - R$" + totalConta);
			if (this.validarCamposCadastro(vendaVO)) {
				VendaController vendaController = new VendaController();
				vendaVO = vendaController.cadastrarVenda(vendaVO);
				if (vendaVO.getIdVenda() != 0) {
					System.out.println("\nvenda cadastrada com Sucesso.");
				} else {
					System.out.println("Não foi possível cadastrar a venda");
				}
			} else {
				System.out.println("voce digitou uma opção inválida. ");
			}

		}
	}

	private boolean validarCamposCadastro(VendaVO vendaVO) {
		boolean resultado = true;
		System.out.println();
		if(vendaVO.getIdUsuarioVO() == 0) {
		     System.out.println("O campo código do usuário é obrigatorio.");
		     resultado = false;
		}
		if(vendaVO.getListaItemVendaVO() == null || vendaVO.getListaItemVendaVO().isEmpty()) {
		     System.out.println("O campo dos produtos vendidos é obrigatório.");
		     resultado = false;
		}
		 return resultado;
	}

	private int gerarNumeroPedido() {
		if (NUMERO_PEDIDO == 99) {
			NUMERO_PEDIDO = 0;
		} else {
			NUMERO_PEDIDO++;
		}
		return NUMERO_PEDIDO;
	}

	private ArrayList<ProdutoVO> listarProdutos() {
		ProdutoController produtoController = new ProdutoController();
		ArrayList<ProdutoVO> listaProdutosVO = produtoController.consultarTodosProdutosVigentesController();
		System.out.print("\n---------- Lista de Produtos ----------");
		System.out.printf("\nx3s %-13s %-20s %-7s %-24s ", "ID", "TIPO PRODUTO", "NOME", "PREÇO", "DATA CADASTRO");
		for (int i = 0; i < listaProdutosVO.size(); i++) {
			listaProdutosVO.get(i).imprimir();
		}
		System.out.println("\n");
		return listaProdutosVO;
	}

	private double cadastrarItemVendaVO(ItemVendaVO itemVendaVO, ArrayList<ProdutoVO> listaProdutosVO) {
		System.out.print("Informe o código do produto: ");
		System.out.print("Informe a quantidade do produto: ");
		itemVendaVO.setQuantidade(Integer.parseInt(teclado.nextLine()));
		double valor = 0;
		for (ProdutoVO produto : listaProdutosVO) {
			if (produto.getIdProduto() == itemVendaVO.getIdProduto()) {
				valor = produto.getPreco() * itemVendaVO.getQuantidade();
			}
		}
		return valor;
	}

	private int apresentarOpcoesMenu(UsuarioVO usuarioVO) {
		System.out.println("\n---------- Sistema Foodtruck ----------");
		System.out.println("---------- Menu Vendas ----------");
		System.out.println("\nOpções: ");
		if (!usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.ENTREGADOR)) {
			System.out.println(OPCAO_MENU_CADASTRAR_VENDA + " - Cadastrar Venda");
			System.out.println(OPCAO_MENU_CANCELAR_VENDA + " - Cancelar Venda");
		}
		if (!usuarioVO.getTipoUsuario().equals(TipoUsuarioVO.CLIENTE)) {
			System.out.println(OPCAO_MENU_SITUACAO_ENTREGA + " - Situação da Entrega");
		}
		System.out.println(OPCAO_MENU_VENDA_VOLTAR + " - Voltar");
		System.out.println("\nDigite um opção: ");
		return Integer.parseInt(teclado.nextLine());
	}
}
