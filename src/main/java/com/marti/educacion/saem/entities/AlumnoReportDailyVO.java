package com.marti.educacion.saem.entities;

public class AlumnoReportDailyVO {
	
	private String nombre;
	private String concepto;
	private String grado;
	private float pago;
	private boolean saldo;

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public float getPago() {
		return pago;
	}
	public void setPago(float pago) {
		this.pago = pago;
	}
	public boolean isSaldo() {
		return saldo;
	}
	public void setSaldo(boolean saldo) {
		this.saldo = saldo;
	}
	
}
