package model.vo;

import java.time.LocalDateTime;

public class EntregaVO {
	private int idEntrega;
	private EntregaVO entrega;
	private int idEntregador;
	private SituacaoEntregaVO situacao;
	private LocalDateTime dataEntrega;

	public EntregaVO(int idEntrega, EntregaVO entrega, int idEntregador, SituacaoEntregaVO situacao,
			LocalDateTime dataEntrega) {
		super();
		this.idEntrega = idEntrega;
		this.entrega = entrega;
		this.idEntregador = idEntregador;
		this.situacao = situacao;
		this.dataEntrega = dataEntrega;
	}

	public EntregaVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdEntrega() {
		return idEntrega;
	}

	public void setIdEntrega(int idEntrega) {
		this.idEntrega = idEntrega;
	}

	public EntregaVO getEntrega() {
		return entrega;
	}

	public void setEntrega(EntregaVO entrega) {
		this.entrega = entrega;
	}

	public int getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(int idEntregador) {
		this.idEntregador = idEntregador;
	}

	public SituacaoEntregaVO getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoEntregaVO situacao) {
		this.situacao = situacao;
	}

	public LocalDateTime getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDateTime dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
}
