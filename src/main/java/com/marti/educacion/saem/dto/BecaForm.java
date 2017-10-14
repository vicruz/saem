package com.marti.educacion.saem.dto;

import javax.validation.constraints.NotNull;

public class BecaForm {

	Integer becaId;
	
	@NotNull
	String fechaInicio;
	
	@NotNull
	String fechaFin;
	
	@NotNull
	Double porcentaje;
	
	public Integer getBecaId() {
		return becaId;
	}
	public void setBecaId(Integer becaId) {
		this.becaId = becaId;
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
	public Double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(Double porcentaje) {
		this.porcentaje = porcentaje;
	}
}
