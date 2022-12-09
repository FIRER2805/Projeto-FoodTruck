package model.vo;

import java.time.LocalDateTime;

public class ProdutoVO {
	private int idProduto;
	private TipoProdutoVO tipoProduto;
	private String nome;
	private double preco;
	private LocalDateTime dataCadastro;
	private LocalDateTime dataExclusao;

	public ProdutoVO(int idProduto, TipoProdutoVO tipoProduto, String nome, double preco, LocalDateTime dataCadastro,
			LocalDateTime dataExclusao) {
		super();
		this.idProduto = idProduto;
		this.tipoProduto = tipoProduto;
		this.nome = nome;
		this.preco = preco;
		this.dataCadastro = dataCadastro;
		this.dataExclusao = dataExclusao;
	}

	public ProdutoVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public TipoProdutoVO getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProdutoVO tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataExclusao() {
		return dataExclusao;
	}

	public void setDataExclusao(LocalDateTime dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
	
	public void imprimir() {
		System.out.printf("\n %3d  %-13s  %-20s  %-11s  %-25s  %-13s",
				this.getIdProduto(),
				this.getTipoProduto(),
				this.getNome(),
				this.getPreco(),
				this.getDataCadastro(),
				this.getDataExclusao());
	}
	/*
	this.idProduto = idProduto;
	this.tipoProduto = tipoProduto;
	this.nome = nome;
	this.preco = preco;
	this.dataCadastro = dataCadastro;
	this.dataExclusao = dataExclusao;
	*/
	@Override
	public String toString()
	{
		return "id: " + this.getIdProduto()
		+ "\ntipo produto: " + this.getTipoProduto()
		+ "\nnome: " + this.getNome()
		+ "\npreço: " + this.getPreco()
		+ "\ndata cadastro: " + this.getDataCadastro()
		+ "\ndata exclusão: " + this.getDataExclusao();
	}
}
