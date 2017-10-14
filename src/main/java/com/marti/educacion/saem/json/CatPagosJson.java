package com.marti.educacion.saem.json;

public class CatPagosJson {

	private int id;
	private String concepto;
	private Double monto;
	private String fecha;
	private String beca;
	private String url;
	private String generaAdeudo;
	private String pagoUnico;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getBeca() {
		return beca;
	}
	public void setBeca(String beca) {
		this.beca = beca;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getGeneraAdeudo() {
		return generaAdeudo;
	}
	public void setGeneraAdeudo(String generaAdeudo) {
		this.generaAdeudo = generaAdeudo;
	}
	public String getPagoUnico() {
		return pagoUnico;
	}
	public void setPagoUnico(String pagoUnico) {
		this.pagoUnico = pagoUnico;
	}
	
	
	
}
