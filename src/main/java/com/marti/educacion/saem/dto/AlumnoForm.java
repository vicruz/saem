package com.marti.educacion.saem.dto;

import javax.validation.constraints.NotNull;

public class AlumnoForm {
	
	private Integer id;
	
	@NotNull
	private String apPaterno;
	
	@NotNull
	private String apMaterno;
	
	@NotNull
	private String nombre;
	
	private Integer gradoId;
	private Integer semaforoId;
	private Boolean becaId;
	private Boolean activo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getApPaterno() {
		return apPaterno;
	}
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}
	public String getApMaterno() {
		return apMaterno;
	}
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getGradoId() {
		return gradoId;
	}
	public void setGradoId(Integer gradoId) {
		this.gradoId = gradoId;
	}
	public Integer getSemaforoId() {
		return semaforoId;
	}
	public void setSemaforoId(Integer semaforoId) {
		this.semaforoId = semaforoId;
	}
	public Boolean getBecaId() {
		return becaId;
	}
	public void setBecaId(Boolean becaId) {
		this.becaId = becaId;
	}
	
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "AlumnoForm [apPaterno=" + apPaterno + ", apMaterno=" + apMaterno + ", nombre=" + nombre + ", gradoId="
				+ gradoId + ", semaforoId=" + semaforoId + ", becaId=" + becaId + "]";
	}

}
