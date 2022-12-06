package view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ProdutoController;
import model.vo.ProdutoVO;
import model.vo.TipoProdutoVO;

public class MenuProduto {

	// opções do menu
	private final int CADASTRAR_PRODUTO = 1;
	private final int CONSULTAR_PRODUTO = 2;
	private final int ATUALIZAR_PRODUTO = 3;
	private final int EXCLUIR_PRODUTO = 4;
	private final int VOLTAR = 9;
	
	// opções do submenu consultar usuario
	private final int CONSULTAR_UM_USUARIO = 1;
	private final int CONSULTAR_TODOS_USUARIOS = 2;
	private final int SUBMENU_VOLTAR = 9;

	// apresenta o menu
	public void apresentarMenuProduto() {
		int opcao = 0;
		while (opcao != VOLTAR) {
			opcao = this.menuEscolhido();
			switch (opcao) {
			case CADASTRAR_PRODUTO:
				this.cadastrarProduto();
				break;
			case CONSULTAR_PRODUTO:
				this.subMenuConsultarProdutos();
				break;
			case ATUALIZAR_PRODUTO:
				this.AtualizarProduto();
				break;
			case EXCLUIR_PRODUTO:
				this.excluir_produto();
				break;
			default:
				System.out.println("opção invalida!");
				break;
			}
		}
	}

	private void excluir_produto() {
		ProdutoController.excluirProduto(this.idProduto());
	}

	private void AtualizarProduto() {
		if(ProdutoController.atualizarProduto(this.produtoAtualizado()) > 0)
			System.out.println("Produto atualizado com sucesso!");
		else
			System.out.println("Não foi possivel cadastrar o produto");
	}

	// retorna as informações que devem ser atualizadas no produto
	private ProdutoVO produtoAtualizado() {
		Scanner scan = new Scanner(System.in);
		ProdutoVO produtoVO = new ProdutoVO();
		produtoVO.setIdProduto(this.idProduto());
		System.out.println("Informe o tipo do produto:");
		produtoVO.setTipoProduto(this.mostraTiposProduto());
		System.out.println("Informe o nome do produto:");
		produtoVO.setNome(scan.nextLine());
		System.out.println("Informe o preço do produto:");
		produtoVO.setPreco(Double.parseDouble(scan.nextLine()));
		System.out.println("Informe a data de cadastro no formato 'yyyy-MM-dd HH:mm:ss':");
		produtoVO.setDataCadastro(LocalDateTime.parse(scan.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		System.out.println("Informe a data de exclusão no formato 'yyyy-MM-dd HH:mm:ss':");
		produtoVO.setDataExclusao(LocalDateTime.parse(scan.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		return produtoVO;
	}

	public int menuEscolhido() {
		Scanner scan = new Scanner(System.in);
		System.out.println("---------- SISTEMA FOODTRUCK ----------");
		System.out.println("---------- OPÇÕES DE PRODUTO ----------");

		// opções
		System.out.println(CADASTRAR_PRODUTO + " - cadastrar produto");
		System.out.println(CONSULTAR_PRODUTO + " - consultar produto");
		System.out.println(ATUALIZAR_PRODUTO + " - atualizar produto");
		System.out.println(EXCLUIR_PRODUTO + " - excluir produto");
		System.out.println(VOLTAR + " - voltar");

		// espaço para digitar a opcão
		System.out.println("digite uma opção: ");
		return scan.nextInt();
	}

	// cadastra produto
	public void cadastrarProduto() {
		Scanner scan = new Scanner(System.in);
		ProdutoVO produto = new ProdutoVO();
		
		// preenche informações do produto
		System.out.println("Digite o nome do produto:");
		produto.setNome(scan.nextLine());
		System.out.println("Digite o preco do produto:");
		produto.setPreco(Double.parseDouble(scan.nextLine()));
		System.out.println("Digite o tipo de produto");
		produto.setTipoProduto(mostraTiposProduto());
		
		// envia o objeto do produtoVo preenchido para a camada controller
		ProdutoController.cadastraProduto(produto);
	}

	// submenu de cadastrar produto
	public TipoProdutoVO mostraTiposProduto() {
		Scanner scan = new Scanner(System.in);
		for(int i = 1; i <= TipoProdutoVO.values().length; i++)
		{
			System.out.println(i + " - " + TipoProdutoVO.getTipoProdutoVOPorValor(i));
		}
		TipoProdutoVO tipo = TipoProdutoVO.getTipoProdutoVOPorValor(Integer.parseInt(scan.nextLine()));
		return tipo;
	}
	
	// submenu de consultar produto
	public void subMenuConsultarProdutos()
	{
		int opcao = 0;
		while(opcao != SUBMENU_VOLTAR)
		{
			opcao = this.subMenuApresentaOpcoes();
			switch(opcao)
			{
			case CONSULTAR_UM_USUARIO:
				this.apresentarUmProduto(idProduto());
				break;
			case CONSULTAR_TODOS_USUARIOS:
				this.apresentarTodosProdutos();
				break;
				default:
					System.out.println("opcão invalida");
					break;
			}
		}
	}
	
	private void apresentarUmProduto(int id) {
		System.out.println(ProdutoController.consultarUmProduto(id));
	}

	// retorna um menu para escolher o id do produto
	private int idProduto() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Digite o id do produto: ");
		return Integer.parseInt(scan.nextLine());
	}

	private void apresentarTodosProdutos() {
		ArrayList<ProdutoVO> produtos = ProdutoController.consultarTodosProdutos();
		for(int i = 0; i < produtos.size(); i++)
		{
			System.out.println(produtos.get(i));
		}
	}

	private int subMenuApresentaOpcoes()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println(CONSULTAR_UM_USUARIO + " - consultar um produto");
		System.out.println(CONSULTAR_TODOS_USUARIOS + " - consultar todos os produtos");
		System.out.println(SUBMENU_VOLTAR + " - voltar");
		System.out.println("digite uma opção: ");
		return Integer.parseInt(scan.nextLine());
	}
}