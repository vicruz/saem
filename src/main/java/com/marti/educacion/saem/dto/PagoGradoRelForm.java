package com.marti.educacion.saem.dto;

import java.util.List;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class PagoGradoRelForm {

	private Integer idGrado;
	private Integer idPago;
	private String fechaLimite;
	private Integer mes;
	private String anio;

	@NumberFormat(style = Style.NUMBER, pattern = "$#,#00.00")
	private Double monto;
	
	private List<Integer> idGradoLst;
	
	public String getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	
	public Integer getIdGrado() {
		return idGrado;
	}
	public void setIdGrado(Integer idGrado) {
		this.idGrado = idGrado;
	}
	public Integer getIdPago() {
		return idPago;
	}
	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}
	
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public List<Integer> getIdGradoLst() {
		return idGradoLst;
	}
	public void setIdGradoLst(List<Integer> idGradoLst) {
		this.idGradoLst = idGradoLst;
	}
	@Override
	public String toString() {
		return "PagoGradoRelForm [idGrado=" + idGrado + ", idPago=" + idPago + ", fechaLimite=" + fechaLimite + ", Mes="
				+ mes + ", anio=" + anio + ", monto=" + monto +"]";
	}
	
	
}
