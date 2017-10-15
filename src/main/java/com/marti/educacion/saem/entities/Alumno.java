package com.marti.educacion.saem.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ALUMNO")
public class Alumno {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ALUMNO_ID")
    private int alumnoId;
    
	@Column(name="AP_PATERNO")
    private String apPaterno;
    
	@Column(name="AP_MATERNO")
    private String apMaterno;
    
	@Column(name="ALUMNO_NOMBRE")
    private String alumnoNombre;
	
	@Column(name="FECHA_INGRESO")
	private Date fechaIngreso;
	
	@Column(name="MATRICULA")
	private String matricula;
	
	@Column(name="FECHA_NACIMIENTO")
	private Date fechaNacimiento;
	
	@Column(name="ESCUELA_PROCEDENCIA")
	private String escuelaProcedencia;
	
	@Column(name="ALUMNO_DIRECCION")
	private String alumnoDireccion;
	
	@Column(name="ALUMNO_TELEFONO")
	private String alumnoTelefono;
	
	@Column(name="TUTOR_NOMBRE")
	private String tutorNombre;
	
	@Column(name="TUTOR_TELEFONO")
	private String tutorTelefono;

	public int getAlumnoId() {
		return alumnoId;
	}

	public void setAlumnoId(int alumnoId) {
		this.alumnoId = alumnoId;
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

	public String getAlumnoNombre() {
		return alumnoNombre;
	}

	public void setAlumnoNombre(String alumnoNombre) {
		this.alumnoNombre = alumnoNombre;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getEscuelaProcedencia() {
		return escuelaProcedencia;
	}

	public void setEscuelaProcedencia(String escuelaProcedencia) {
		this.escuelaProcedencia = escuelaProcedencia;
	}

	public String getAlumnoDireccion() {
		return alumnoDireccion;
	}

	public void setAlumnoDireccion(String alumnoDireccion) {
		this.alumnoDireccion = alumnoDireccion;
	}

	public String getAlumnoTelefono() {
		return alumnoTelefono;
	}

	public void setAlumnoTelefono(String alumnoTelefono) {
		this.alumnoTelefono = alumnoTelefono;
	}

	public String getTutorNombre() {
		return tutorNombre;
	}

	public void setTutorNombre(String tutorNombre) {
		this.tutorNombre = tutorNombre;
	}

	public String getTutorTelefono() {
		return tutorTelefono;
	}

	public void setTutorTelefono(String tutorTelefono) {
		this.tutorTelefono = tutorTelefono;
	}	
	
}
