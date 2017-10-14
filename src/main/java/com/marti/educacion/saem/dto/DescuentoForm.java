package com.marti.educacion.saem.dto;

import javax.validation.constraints.NotNull;

public class DescuentoForm {

	Integer descuentoId;
	
	@NotNull
	String fechaInicio;
	
	@NotNull
	String fechaFin;
	
	@NotNull
	Double monto;
	
	boolean activo;

	public Integer getDescuentoId() {
		return descuentoId;
	}

	public void setDescuentoId(Integer descuentoId) {
		this.descuentoId = descuentoId;
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

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
