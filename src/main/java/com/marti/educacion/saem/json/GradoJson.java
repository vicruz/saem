package com.marti.educacion.saem.json;

import java.io.Serializable;

public class GradoJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idGrado;
	private String grado;
	private String urlEditar;
	public int getIdGrado() {
		return idGrado;
	}
	public void setIdGrado(int idGrado) {
		this.idGrado = idGrado;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public String getUrlEditar() {
		return urlEditar;
	}
	public void setUrlEditar(String urlEditar) {
		this.urlEditar = urlEditar;
	}
	
}
