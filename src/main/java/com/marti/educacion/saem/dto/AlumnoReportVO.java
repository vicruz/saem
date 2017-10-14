package com.marti.educacion.saem.dto;

public class AlumnoReportVO {

	private String fConcepto;
	private Double fMonto;
	private Double fPago;
	private String fFechaPago;
	//private String fSaldo;
	private Double fAdeudo;
	private String fEstatus;

	public String getfConcepto() {
		return fConcepto;
	}
	public void setfConcepto(String fConcepto) {
		this.fConcepto = fConcepto;
	}
	public Double getfMonto() {
		return fMonto;
	}
	public void setfMonto(Double fMonto) {
		this.fMonto = fMonto;
	}
	public Double getfPago() {
		return fPago;
	}
	public void setfPago(Double fPago) {
		this.fPago = fPago;
	}
	public String getfFechaPago() {
		return fFechaPago;
	}
	public void setfFechaPago(String fFechaPago) {
		this.fFechaPago = fFechaPago;
	}
	public Double getfAdeudo() {
		return fAdeudo;
	}
	public void setfAdeudo(Double fAdeudo) {
		this.fAdeudo = fAdeudo;
	}
	/*public String getfSaldo() {
		return fSaldo;
	}
	public void setfSaldo(String fSaldo) {
		this.fSaldo = fSaldo;
	}*/
	public String getfEstatus() {
		return fEstatus;
	}
	public void setfEstatus(String fEstatus) {
		this.fEstatus = fEstatus;
	}
	
}
