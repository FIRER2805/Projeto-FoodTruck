package model.vo;

public class ItemVendaVO {
	private int idItemVenda;
	private VendaVO venda;
	private int idProduto;
	private int quantidade;

	public ItemVendaVO(int idItemVenda, VendaVO venda, int idProduto, int quantidade) {
		super();
		this.idItemVenda = idItemVenda;
		this.venda = venda;
		this.idProduto = idProduto;
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

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
}
