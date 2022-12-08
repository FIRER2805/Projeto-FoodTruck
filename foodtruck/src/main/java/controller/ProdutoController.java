package controller;

import java.util.ArrayList;

import model.bo.ProdutoBO;
import model.vo.ProdutoVO;

public class ProdutoController {
	
	// cadastrar um produto
	public static void cadastraProduto(ProdutoVO produto)
	{
		ProdutoBO produtoBO = new ProdutoBO();
		produtoBO.cadastrarProdutoBO(produto);
	}
	
	public static ArrayList<ProdutoVO> consultarTodosProdutos()
	{
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarTodosProdutosBO();
	}
	
	public static ProdutoVO consultarUmProduto(int id)
	{
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarUmProduto(id);
	}

	public static int atualizarProduto(ProdutoVO produto) {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.atualizarProduto(produto);
	}

	public static void excluirProduto(int idProduto) {
		ProdutoBO produtoBO = new ProdutoBO();
		produtoBO.excluirProduto(idProduto);
		
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosVigentesController() {
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarTodosProdutosVigentesBO();
	}
}
