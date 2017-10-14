package com.marti.educacion.saem.dto;

import javax.validation.constraints.NotNull;

public class GradoForm {

	private Integer gradoId;
	
	@NotNull
	private String nombre;

	public Integer getGradoId() {
		return gradoId;
	}

	public void setGradoId(Integer gradoId) {
		this.gradoId = gradoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
