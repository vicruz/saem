package com.marti.educacion.saem.dto;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class PagoGradoForm {

	private Integer idPagoGrado;
	private Integer idConcepto;
	private String concepto;
	
	@NumberFormat(style = Style.NUMBER, pattern = "$#,#00.00")
	private Double monto;
	
	@NumberFormat(style = Style.NUMBER, pattern = "$#,#00.00")
	private Double pago;
	
	public Integer getIdPagoGrado() {
		return idPagoGrado;
	}
	public void setIdPagoGrado(Integer idPagoGrado) {
		this.idPagoGrado = idPagoGrado;
	}
	public Integer getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
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
	public Double getPago() {
		return pago;
	}
	public void setPago(Double pago) {
		this.pago = pago;
	}
	
}
