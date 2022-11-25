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
	
	public static ArrayList<ProdutoVO> consultarTodosUsuarios()
	{
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarTodosProdutosBO();
	}
	
	public static ProdutoVO consultarUmUsuario(int id)
	{
		ProdutoBO produtoBO = new ProdutoBO();
		return produtoBO.consultarUmProduto(id);
	}
}
