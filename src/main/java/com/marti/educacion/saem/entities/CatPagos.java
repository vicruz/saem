package com.marti.educacion.saem.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CAT_PAGOS")
public class CatPagos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name="CONCEPTO")
    private String concepto;
	
	@Column(name="MONTO")
    private Double monto;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHA_ALTA")
    private Date fechaAlta;
	
	@Column(name="APLICA_BECA")
	private int aplicaBeca;
	
	@Column(name="GENERA_ADEUDO")
	private int generaAdeudo;
	
	@Column(name="PAGO_UNICO")
	private int pagoUnico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public int getAplicaBeca() {
		return aplicaBeca;
	}

	public void setAplicaBeca(int aplicaBeca) {
		this.aplicaBeca = aplicaBeca;
	}

	public int getGeneraAdeudo() {
		return generaAdeudo;
	}

	public void setGeneraAdeudo(int generaAdeudo) {
		this.generaAdeudo = generaAdeudo;
	}

	public int getPagoUnico() {
		return pagoUnico;
	}

	public void setPagoUnico(int pagoUnico) {
		this.pagoUnico = pagoUnico;
	}
    

}
