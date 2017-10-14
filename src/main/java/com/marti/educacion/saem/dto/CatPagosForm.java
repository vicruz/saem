package com.marti.educacion.saem.dto;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class CatPagosForm {
	
	private Integer id;
	
	private String concepto;
	
	@NumberFormat(style = Style.NUMBER, pattern = "$#,#00.00")
	private Double monto;
	
	private Boolean beca;
	
	private Boolean generaAdeudo;
	
	private Boolean pagoUnico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	public Boolean getBeca() {
		return beca;
	}

	public void setBeca(Boolean beca) {
		this.beca = beca;
	}

	public Boolean getGeneraAdeudo() {
		return generaAdeudo;
	}

	public void setGeneraAdeudo(Boolean generaAdeudo) {
		this.generaAdeudo = generaAdeudo;
	}

	public Boolean getPagoUnico() {
		return pagoUnico;
	}

	public void setPagoUnico(Boolean pagoUnico) {
		this.pagoUnico = pagoUnico;
	}

	@Override
	public String toString() {
		return "CatPagosForm [id=" + id + ", concepto=" + concepto + ", monto=" + monto + ", beca=" + beca
				+ ", generaAdeudo=" + generaAdeudo + ", pagoUnico=" + pagoUnico + "]";
	}

}
