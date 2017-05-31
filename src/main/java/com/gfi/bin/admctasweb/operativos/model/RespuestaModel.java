package com.gfi.bin.admctasweb.operativos.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;

/**
 * Bean para almacenar los objetos de Respuesta
 * @author LUGL4884
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaModel extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String numOficio;
	private String tipoOficio;
	private Long numConsec;
	private Long idContrato;
	private Long tipoRespuesta;
	private String tipoCaso;
	private String sitEnvio;
	private String bMedioElec;
	private String nomTitular;
	private Long tipoTitular;
	private Long sitCuenta;
	private String tipoBusqueda;
	
	//Se agrega para almacenar descripción de la situación de la cuenta
	private String descSitCuenta;
	//Se agrega para almacenar descripción del tipo de respuesta
	private String descTipoRespuesta;
	
	//Se añade para conocer id de persona cuando se selecciona contrato
	private Long idPersona;
	
	
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
	 * @return the numConsec
	 */
	public Long getNumConsec() {
		return numConsec;
	}
	/**
	 * @param numConsec the numConsec to set
	 */
	public void setNumConsec(Long numConsec) {
		this.numConsec = numConsec;
	}
	/**
	 * @return the idContrato
	 */
	public Long getIdContrato() {
		return idContrato;
	}
	/**
	 * @param idContrato the idContrato to set
	 */
	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}
	/**
	 * @return the tipoRespuesta
	 */
	public Long getTipoRespuesta() {
		return tipoRespuesta;
	}
	/**
	 * @param tipoRespuesta the tipoRespuesta to set
	 */
	public void setTipoRespuesta(Long tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
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
	/**
	 * @return the sitEnvio
	 */
	public String getSitEnvio() {
		return sitEnvio;
	}
	/**
	 * @param sitEnvio the sitEnvio to set
	 */
	public void setSitEnvio(String sitEnvio) {
		this.sitEnvio = sitEnvio;
	}
	/**
	 * @return the bMedioElec
	 */
	public String getbMedioElec() {
		return bMedioElec;
	}
	/**
	 * @param bMedioElec the bMedioElec to set
	 */
	public void setbMedioElec(String bMedioElec) {
		this.bMedioElec = bMedioElec;
	}
	/**
	 * @return the nomTitular
	 */
	public String getNomTitular() {
		return nomTitular;
	}
	/**
	 * @param nomTitular the nomTitular to set
	 */
	public void setNomTitular(String nomTitular) {
		this.nomTitular = nomTitular;
	}
	/**
	 * @return the tipoTitular
	 */
	public Long getTipoTitular() {
		return tipoTitular;
	}
	/**
	 * @param tipoTitular the tipoTitular to set
	 */
	public void setTipoTitular(Long tipoTitular) {
		this.tipoTitular = tipoTitular;
	}
	/**
	 * @return the sitCuenta
	 */
	public Long getSitCuenta() {
		return sitCuenta;
	}
	/**
	 * @param sitCuenta the sitCuenta to set
	 */
	public void setSitCuenta(Long sitCuenta) {
		this.sitCuenta = sitCuenta;
	}
	/**
	 * @return the tipoBusqueda
	 */
	public String getTipoBusqueda() {
		return tipoBusqueda;
	}
	/**
	 * @param tipoBusqueda the tipoBusqueda to set
	 */
	public void setTipoBusqueda(String tipoBusqueda) {
		this.tipoBusqueda = tipoBusqueda;
	}
	/**
	 * @return the idPersona
	 */
	public Long getIdPersona() {
		return idPersona;
	}
	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	/**
	 * @return the descSitCuenta
	 */
	public String getDescSitCuenta() {
		return descSitCuenta;
	}
	/**
	 * @param descSitCuenta the descSitCuenta to set
	 */
	public void setDescSitCuenta(String descSitCuenta) {
		this.descSitCuenta = descSitCuenta;
	}
	/**
	 * @return the descTipoRespuesta
	 */
	public String getDescTipoRespuesta() {
		return descTipoRespuesta;
	}
	/**
	 * @param descTipoRespuesta the descTipoRespuesta to set
	 */
	public void setDescTipoRespuesta(String descTipoRespuesta) {
		this.descTipoRespuesta = descTipoRespuesta;
	}

}
