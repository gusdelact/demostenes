package com.gfi.bin.admctasweb.operativos.model;

public class ExclusionModel {
	private Integer idExclusion;
	private String descripcionExclusion;
	private Boolean activo;
	
	public Integer getIdExclusion() {
		return idExclusion;
	}
	public void setIdExclusion(Integer idExclusion) {
		this.idExclusion = idExclusion;
	}
	public String getDescripcionExclusion() {
		return descripcionExclusion;
	}
	public void setDescripcionExclusion(String descripcionExclusion) {
		this.descripcionExclusion = descripcionExclusion;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}