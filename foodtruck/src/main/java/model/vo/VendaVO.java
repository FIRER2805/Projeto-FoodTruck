package model.vo;

import java.time.LocalDateTime;

public class VendaVO {
	private int idVenda;
	private int idUsuarioVO;
	private int numeroPedido;
	private LocalDateTime dataVenda;
	private LocalDateTime dataCancelamento;
	private boolean flagEntrega;
	private double taxaEntrega;
	public VendaVO(int idVenda, int usuarioVO, int numeroPedido, LocalDateTime dataVenda,
			LocalDateTime dataCancelamento, boolean flagEntrega, double taxaEntrega) {
		super();
		this.idVenda = idVenda;
		this.idUsuarioVO = usuarioVO;
		this.numeroPedido = numeroPedido;
		this.dataVenda = dataVenda;
		this.dataCancelamento = dataCancelamento;
		this.flagEntrega = flagEntrega;
		this.taxaEntrega = taxaEntrega;
	}
	public VendaVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}
	public int getIdUsuarioVO() {
		return idUsuarioVO;
	}
	public void setIdUsuarioVO(int idUsuarioVO) {
		this.idUsuarioVO = idUsuarioVO;
	}
	public int getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public LocalDateTime getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(LocalDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}
	public LocalDateTime getDataCancelamento() {
		return dataCancelamento;
	}
	public void setDataCancelamento(LocalDateTime dataCancelamento) {
		this.dataCancelamento = dataCancelamento;
	}
	public boolean isFlagEntrega() {
		return flagEntrega;
	}
	public void setFlagEntrega(boolean flagEntrega) {
		this.flagEntrega = flagEntrega;
	}
	public double getTaxaEntrega() {
		return taxaEntrega;
	}
	public void setTaxaEntrega(double taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}
}
