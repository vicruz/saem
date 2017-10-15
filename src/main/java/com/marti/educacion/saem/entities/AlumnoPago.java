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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*@Entity
@Table(name="ALUMNO_PAGO")*/
public class AlumnoPago {
/*
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="ID_ALUMNO")
	private Integer idAlumno;
	
	@Column(name="ID_PAGO_GRADO", updatable=false, insertable=false)
	private Integer idPagoGrado;
	
	@JoinColumn(name="ID_PAGO_GRADO",referencedColumnName="ID")
	@OneToOne(fetch=FetchType.LAZY)
	private PagoGrado pagoGrado;
	
	@Column(name="MONTO")
	private Double monto;
	
	@Column(name="PAGO")
	private Double pago;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_PAGO")
	private Date fechaPago;
	
	@Column(name="ID_SEMAFORO")
	private Integer idSemaforo;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_LIMITE")
	private Date fechaLimite;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}

	public PagoGrado getPagoGrado() {
		return pagoGrado;
	}

	public void setPagoGrado(PagoGrado pagoGrado) {
		this.pagoGrado = pagoGrado;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Double getPago() {
		return pago;
	}

	public void setPago(Double pago) {
		this.pago = pago;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Integer getIdSemaforo() {
		return idSemaforo;
	}

	public void setIdSemaforo(Integer idSemaforo) {
		this.idSemaforo = idSemaforo;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}*/
}
