package com.marti.educacion.saem.json;

public class EstadisticaDataJson {
	
	private String label;
	private Double value;
	
	public EstadisticaDataJson(){}
	
	public EstadisticaDataJson(String label, Double value){
		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "EstadisticaDataJson [label=" + label + ", value=" + value + "]";
	}
	
	
}
