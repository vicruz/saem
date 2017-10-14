package com.marti.educacion.saem.json;

import java.io.Serializable;

public class PagoGradoJson implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idPagoGrado;
	private String concepto;
	private Double monto;
	
	private Integer pagoId;
	private String mes;
	private Integer anio;
	private String fechaLimite;
	private String urlBorrar;
	
	public Integer getIdPagoGrado() {
		return idPagoGrado;
	}
	public void setIdPagoGrado(Integer idPagoGrado) {
		this.idPagoGrado = idPagoGrado;
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
	public Integer getPagoId() {
		return pagoId;
	}
	public void setPagoId(Integer pagoId) {
		this.pagoId = pagoId;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public String getUrlBorrar() {
		return urlBorrar;
	}
	public void setUrlBorrar(String urlBorrar) {
		this.urlBorrar = urlBorrar;
	}
}
