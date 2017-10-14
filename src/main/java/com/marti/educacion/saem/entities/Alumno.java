package com.marti.educacion.saem.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ALUMNO")
public class Alumno {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
    private int id;
    
	@Column(name="AP_PATERNO")
    private String apPaterno;
    
	@Column(name="AP_MATERNO")
    private String apMaterno;
    
	@Column(name="NOMBRE")
    private String nombre;
	
	@Column(name="FECHA_INGRESO")
	private Date fechaIngreso;
    
	@JoinColumn(referencedColumnName="ID")
	@OneToOne(fetch=FetchType.LAZY)
	private Grado grado;
	
	@Column(name="SEMAFORO_ID")
	private long idSemaforo;
	
	@Column(name="BECA_ID")
	private long beca;
	
	@Column(name="SALDO")
	private double saldo;
	
	@Column(name="ACTIVO")
    private int activo;
    
	/*@OneToOne
    private Semaforo semaforo;*/

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

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	/*public long getIdGrado() {
		return idGrado;
	}

	public void setIdGrado(long idGrado) {
		this.idGrado = idGrado;
	}*/

	public Grado getGrado() {
		return grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public long getIdSemaforo() {
		return idSemaforo;
	}

	public void setIdSemaforo(long idSemaforo) {
		this.idSemaforo = idSemaforo;
	}

	public long getBeca() {
		return beca;
	}

	public void setBeca(long beca) {
		this.beca = beca;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	
	
}
