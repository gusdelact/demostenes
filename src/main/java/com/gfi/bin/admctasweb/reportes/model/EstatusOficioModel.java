package com.gfi.bin.admctasweb.reportes.model;

import java.util.Date;

/**
 * 
 * @author ESS3ESPP
 *
 */
public class EstatusOficioModel {
	private String numeroOficio;
	private String tipoOficio;
	
	private String claveEstatus;
	private Date fechaHoraRegistro;
	private String claveUsuario;
	
	public String getNumeroOficio() {
		return numeroOficio;
	}
	public void setNumeroOficio(String numeroOficio) {
		this.numeroOficio = numeroOficio;
	}
	public String getTipoOficio() {
		return tipoOficio;
	}
	public void setTipoOficio(String tipoOficio) {
		this.tipoOficio = tipoOficio;
	}
	public String getClaveEstatus() {
		return claveEstatus;
	}
	public void setClaveEstatus(String claveEstatus) {
		this.claveEstatus = claveEstatus;
	}
	public Date getFechaHoraRegistro() {
		return fechaHoraRegistro;
	}
	public void setFechaHoraRegistro(Date fechaHoraRegistro) {
		this.fechaHoraRegistro = fechaHoraRegistro;
	}
	public String getClaveUsuario() {
		return claveUsuario;
	}
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}
}