package com.marti.educacion.saem.entities.vo;

import java.util.Date;

public class AlumnoPagoBitacoraVO {

	private Integer alumnoPago;
    private Double pago;
    private Date fechaPago;

    public AlumnoPagoBitacoraVO(){    }
    
	public AlumnoPagoBitacoraVO(Integer alumnoPago, Double pago, Date fechaPago) {
		this.alumnoPago = alumnoPago;
		this.pago = pago;
		this.fechaPago = fechaPago;
	}

	public Integer getAlumnoPago() {
		return alumnoPago;
	}

	public void setAlumnoPago(Integer alumnoPago) {
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
    
}
