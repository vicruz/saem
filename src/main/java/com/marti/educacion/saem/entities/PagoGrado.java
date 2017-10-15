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
@Table(name="PAGO_GRADO")*/
public class PagoGrado {
/*
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@JoinColumn(name="ID_PAGO", referencedColumnName="ID")
	@OneToOne(fetch=FetchType.EAGER)
	private CatPagos catPago;
	
	@Column(name="ID_GRADO")
	private Integer idGrado;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_LIMITE")
	private Date fechaLimite;
	
	@Column(name="MES_CORRESPONDE")
	private Integer mes_corresponde;
	
	@Column(name="ANIO_CORRESPONDE")
	private Integer anio_corresponde;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CORRESPONDE")
	private Date fechaCorresponde;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CatPagos getCatPago() {
		return catPago;
	}

	public void setCatPago(CatPagos catPago) {
		this.catPago = catPago;
	}

	public Integer getIdGrado() {
		return idGrado;
	}

	public void setIdGrado(Integer idGrado) {
		this.idGrado = idGrado;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public Integer getMes_corresponde() {
		return mes_corresponde;
	}

	public void setMes_corresponde(Integer mes_corresponde) {
		this.mes_corresponde = mes_corresponde;
	}

	public Integer getAnio_corresponde() {
		return anio_corresponde;
	}

	public void setAnio_corresponde(Integer anio_corresponde) {
		this.anio_corresponde = anio_corresponde;
	}

	public Date getFechaCorresponde() {
		return fechaCorresponde;
	}

	public void setFechaCorresponde(Date fechaCorresponde) {
		this.fechaCorresponde = fechaCorresponde;
	}
*/    
}
