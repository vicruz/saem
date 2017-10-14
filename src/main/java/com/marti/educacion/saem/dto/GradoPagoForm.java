package com.marti.educacion.saem.dto;

public class GradoPagoForm {

	private Integer pagoId;
	private String fechaInicio;
	private String fechaFin;
	private Integer limite;
	private Integer grupoId;
	public Integer getPagoId() {
		return pagoId;
	}
	public void setPagoId(Integer pagoId) {
		this.pagoId = pagoId;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getLimite() {
		return limite;
	}
	public void setLimite(Integer limite) {
		this.limite = limite;
	}
	public Integer getGrupoId() {
		return grupoId;
	}
	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}
	@Override
	public String toString() {
		return "GradoPagoForm [pagoId=" + pagoId + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", limite=" + limite + ", grupoId=" + grupoId + "]";
	}
}
