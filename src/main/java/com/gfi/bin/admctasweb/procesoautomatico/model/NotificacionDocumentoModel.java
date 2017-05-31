package com.gfi.bin.admctasweb.procesoautomatico.model;

/**
 * 
 * @author ESS3ESPP
 *
 */
public class NotificacionDocumentoModel {
	private Integer numDocto;
	private String nomDocto;
	
	private String observaciones;

	public Integer getNumDocto() {
		return numDocto;
	}
	public void setNumDocto(Integer numDocto) {
		this.numDocto = numDocto;
	}
	public String getNomDocto() {
		return nomDocto;
	}
	public void setNomDocto(String nomDocto) {
		this.nomDocto = nomDocto;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}