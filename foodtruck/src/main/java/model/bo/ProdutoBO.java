package model.bo;

import java.util.ArrayList;

import model.dao.ProdutoDAO;
import model.vo.ProdutoVO;

public class ProdutoBO {

	public void cadastrarProdutoBO(ProdutoVO produto) {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		produtoDAO.cadastrarProdutoDAO(produto);
	}

	public ArrayList<ProdutoVO> consultarTodosProdutosBO() {
		ProdutoDAO produtoDAO = new ProdutoDAO();
		return produtoDAO.ConsultarTodosProdutosDAO();
	}
	
	public ProdutoVO consultarUmProduto(int id)
	{
		ProdutoDAO produtoDAO = new ProdutoDAO();
		return produtoDAO.consultaUmProduto(id);
	}
	
}
