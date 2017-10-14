package com.marti.educacion.saem.json;

import java.io.Serializable;

public class AlumnoJson implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String apPaterno;
	private String apMaterno;
	private String nombre;
	private String grado;
	private String semaforo;
	private String url;
	private String urlEditar;
	private String activo;
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public String getSemaforo() {
		return semaforo;
	}
	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlEditar() {
		return urlEditar;
	}
	public void setUrlEditar(String urlEditar) {
		this.urlEditar = urlEditar;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
}
