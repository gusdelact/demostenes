package com.gfi.bin.admctasweb.operativos.model;

import java.sql.Timestamp;

/**
 * Bean para almacenar datos de un contrato de una persona corporativa
 * @author ESS3VAVC
 *
 */
public class ContratoModel {

	private long idContrato;
	private String cliente;
	private String tipoContrato;
	private Timestamp fechaAlta;
	private Timestamp fechaBaja;
	private String situacionContrato;
	private String tipoContratante;
	private String producto;
	private String cveContratante;
	private String nombreContrato;
	
	
	
	/**
	 * @return the idContrato
	 */
	public long getIdContrato() {
		return idContrato;
	}
	/**
	 * @param idContrato the idContrato to set
	 */
	public void setIdContrato(long idContrato) {
		this.idContrato = idContrato;
	}
	/**
	 * @return the cliente
	 */
	public String getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	/**
	 * @return the tipoContrato
	 */
	public String getTipoContrato() {
		return tipoContrato;
	}
	/**
	 * @param tipoContrato the tipoContrato to set
	 */
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	/**
	 * @return the fechaAlta
	 */
	public Timestamp getFechaAlta() {
		return fechaAlta;
	}
	/**
	 * @param fechaAlta the fechaAlta to set
	 */
	public void setFechaAlta(Timestamp fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	/**
	 * @return the fechaBaja
	 */
	public Timestamp getFechaBaja() {
		return fechaBaja;
	}
	/**
	 * @param fechaBaja the fechaBaja to set
	 */
	public void setFechaBaja(Timestamp fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	/**
	 * @return the situacionContrato
	 */
	public String getSituacionContrato() {
		return situacionContrato;
	}
	/**
	 * @param situacionContrato the situacionContrato to set
	 */
	public void setSituacionContrato(String situacionContrato) {
		this.situacionContrato = situacionContrato;
	}
	/**
	 * @return the tipoContratante
	 */
	public String getTipoContratante() {
		return tipoContratante;
	}
	/**
	 * @param tipoContratante the tipoContratante to set
	 */
	public void setTipoContratante(String tipoContratante) {
		this.tipoContratante = tipoContratante;
	}
	/**
	 * @return the producto
	 */
	public String getProducto() {
		return producto;
	}
	/**
	 * @param producto the producto to set
	 */
	public void setProducto(String producto) {
		this.producto = producto;
	}
	/**
	 * @return the cveContratante
	 */
	public String getCveContratante() {
		return cveContratante;
	}
	/**
	 * @param cveContratante the cveContratante to set
	 */
	public void setCveContratante(String cveContratante) {
		this.cveContratante = cveContratante;
	}
	
	public String getNombreContrato() {
		return nombreContrato;
	}
	public void setNombreContrato(String nombreContrato) {
		this.nombreContrato = nombreContrato;
	}
}