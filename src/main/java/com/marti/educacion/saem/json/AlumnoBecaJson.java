package com.marti.educacion.saem.json;

import java.io.Serializable;

public class AlumnoBecaJson implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double porcentaje;
	private String inicio;
	private String fin;
	private String urlBorrar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
	public String getUrlBorrar() {
		return urlBorrar;
	}
	public void setUrlBorrar(String urlBorrar) {
		this.urlBorrar = urlBorrar;
	}

}
