package com.marti.educacion.saem.json;

import java.io.Serializable;

public class AlumnoDescuentoJson implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private double descuento;
	private String inicio;
	private String fin;
	private String urlBorrar;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
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
