package com.marti.educacion.saem.json;

public class PagoGradoTableJson {

	private static final long serialVersionUID = 1L;
	private Integer idPagoGrado;
	private String concepto;
	private Double monto;
	private String grado;
	private String mes;
	private int anio;
	private String fechaLimite;
	
	
	public String getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
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
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	
	
}
