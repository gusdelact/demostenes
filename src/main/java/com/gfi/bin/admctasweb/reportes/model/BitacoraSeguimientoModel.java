/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Modelo para la Bitacora de Seguimiento de Oficios.
 * @author LUGL4884
 *
 */
public class BitacoraSeguimientoModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String numOficio;
	private String tipoOficio;
	private String cveEstatus;
	private Timestamp fhRegistro;
	private String cveUsuario;
	private String descripcion;
	private String tipoCaso; //Se utiliza para validar el estatus Pendiente.
	
	/**
	 * @return the numOficio
	 */
	public String getNumOficio() {
		return numOficio;
	}
	/**
	 * @param numOficio the numOficio to set
	 */
	public void setNumOficio(String numOficio) {
		this.numOficio = numOficio;
	}
	/**
	 * @return the tipoOficio
	 */
	public String getTipoOficio() {
		return tipoOficio;
	}
	/**
	 * @param tipoOficio the tipoOficio to set
	 */
	public void setTipoOficio(String tipoOficio) {
		this.tipoOficio = tipoOficio;
	}
	/**
	 * @return the cveEstatus
	 */
	public String getCveEstatus() {
		return cveEstatus;
	}
	/**
	 * @param cveEstatus the cveEstatus to set
	 */
	public void setCveEstatus(String cveEstatus) {
		this.cveEstatus = cveEstatus;
	}
	/**
	 * @return the fhRegistro
	 */
	public Timestamp getFhRegistro() {
		return fhRegistro;
	}
	/**
	 * @param fhRegistro the fhRegistro to set
	 */
	public void setFhRegistro(Timestamp fhRegistro) {
		this.fhRegistro = fhRegistro;
	}
	/**
	 * @return the cveUsuario
	 */
	public String getCveUsuario() {
		return cveUsuario;
	}
	/**
	 * @param cveUsuario the cveUsuario to set
	 */
	public void setCveUsuario(String cveUsuario) {
		this.cveUsuario = cveUsuario;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the tipoCaso
	 */
	public String getTipoCaso() {
		return tipoCaso;
	}
	/**
	 * @param tipoCaso the tipoCaso to set
	 */
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	
}
