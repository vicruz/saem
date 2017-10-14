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

@Entity
@Table(name="ALUMNO_PAGO_BITACORA")
public class AlumnoPagoBitacora {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_ALUMNO_PAGO_BITACORA")
	private Integer idAlumnoPagoBitacora;
	
	@JoinColumn(name="ID_ALUMNO_PAGO",referencedColumnName="ID")
	@OneToOne(fetch=FetchType.LAZY)
	private AlumnoPago alumnoPago;
	
	@Column(name="PAGO")
    private Double pago;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHA_PAGO")
    private Date fechaPago;
    
    @Column(name="SALDO")
    private Boolean saldo;

	public Integer getIdAlumnoPagoBitacora() {
		return idAlumnoPagoBitacora;
	}

	public void setIdAlumnoPagoBitacora(Integer idAlumnoPagoBitacora) {
		this.idAlumnoPagoBitacora = idAlumnoPagoBitacora;
	}

	public AlumnoPago getAlumnoPago() {
		return alumnoPago;
	}

	public void setAlumnoPago(AlumnoPago alumnoPago) {
		this.alumnoPago = alumnoPago;
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

	public Boolean getSaldo() {
		return saldo;
	}

	public void setSaldo(Boolean saldo) {
		this.saldo = saldo;
	}
    
}
