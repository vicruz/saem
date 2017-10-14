package com.marti.educacion.saem.json;

import java.util.ArrayList;
import java.util.List;

public class EstadisticaJson {

	private Double total;
	private List<String> colors;
	private List<EstadisticaDataJson> data;
	
	public EstadisticaJson(){
		colors = new ArrayList<String>();
		data = new ArrayList<EstadisticaDataJson>();
	}
	
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public List<String> getColors() {
		return colors;
	}
	public void setColors(List<String> colors) {
		this.colors = colors;
	}
	public List<EstadisticaDataJson> getData() {
		return data;
	}
	public void setData(List<EstadisticaDataJson> data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "EstadisticaJson [total = "+ total + ", colors=" + colors + ", data=" + data + "]";
	}
	
	
	
}
