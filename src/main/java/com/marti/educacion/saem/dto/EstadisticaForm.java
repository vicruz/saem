package com.marti.educacion.saem.dto;

public class EstadisticaForm {
	
	private String fechaInicio;
	private String fechaFin;
	
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
	
	@Override
	public String toString() {
		return "EstadisticaForm [fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
	

}
