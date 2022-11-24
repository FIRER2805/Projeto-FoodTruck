package model.vo;

public class ItemVendaVO {
	private int idItemVenda;
	private VendaVO venda;
	private ProdutoVO produto;
	private int quantidade;

	public ItemVendaVO(int idItemVenda, VendaVO venda, ProdutoVO produto, int quantidade) {
		super();
		this.idItemVenda = idItemVenda;
		this.venda = venda;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public ItemVendaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdItemVenda() {
		return idItemVenda;
	}

	public void setIdItemVenda(int idItemVenda) {
		this.idItemVenda = idItemVenda;
	}

	public VendaVO getVenda() {
		return venda;
	}

	public void setVenda(VendaVO venda) {
		this.venda = venda;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
