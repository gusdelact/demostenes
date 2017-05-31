/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 * @author LUGL4884
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitacoraSeguimientoListModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String numOficio;
	private String tipoOficio;
	private String cveEstatus;
	private Date fhRecepIni;
	private Date fhRecepFin;
	private Date fhEnvIni;
	private Date fhEnvFin;
	private Date fhVenIni;
	private Date fhVenFin;
	private List<OficioSeguimientoModel> oficioList;
	private List<BitacoraSeguimientoModel> bitacoraList;
	
	
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
	 * @return the fhRecepIni
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhRecepIni() {
		return fhRecepIni;
	}
	/**
	 * @param fhRecepIni the fhRecepIni to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhRecepIni(Date fhRecepIni) {
		this.fhRecepIni = fhRecepIni;
	}
	/**
	 * @return the fhRecepFin
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhRecepFin() {
		return fhRecepFin;
	}
	/**
	 * @param fhRecepFin the fhRecepFin to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhRecepFin(Date fhRecepFin) {
		this.fhRecepFin = fhRecepFin;
	}
	/**
	 * @return the fhEnvIni
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhEnvIni() {
		return fhEnvIni;
	}
	/**
	 * @param fhEnvIni the fhEnvIni to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhEnvIni(Date fhEnvIni) {
		this.fhEnvIni = fhEnvIni;
	}
	/**
	 * @return the fhEnvFin
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhEnvFin() {
		return fhEnvFin;
	}
	/**
	 * @param fhEnvFin the fhEnvFin to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhEnvFin(Date fhEnvFin) {
		this.fhEnvFin = fhEnvFin;
	}
	/**
	 * @return the fhVenIni
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhVenIni() {
		return fhVenIni;
	}
	/**
	 * @param fhVenIni the fhVenIni to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhVenIni(Date fhVenIni) {
		this.fhVenIni = fhVenIni;
	}
	/**
	 * @return the fhVenFin
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhVenFin() {
		return fhVenFin;
	}
	/**
	 * @param fhVenFin the fhVenFin to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhVenFin(Date fhVenFin) {
		this.fhVenFin = fhVenFin;
	}
	/**
	 * @return the oficioList
	 */
	public List<OficioSeguimientoModel> getOficioList() {
		return oficioList;
	}
	/**
	 * @param oficioList the oficioList to set
	 */
	public void setOficioList(List<OficioSeguimientoModel> oficioList) {
		this.oficioList = oficioList;
	}
	/**
	 * @return the bitacoraList
	 */
	public List<BitacoraSeguimientoModel> getBitacoraList() {
		return bitacoraList;
	}
	/**
	 * @param bitacoraList the bitacoraList to set
	 */
	public void setBitacoraList(List<BitacoraSeguimientoModel> bitacoraList) {
		this.bitacoraList = bitacoraList;
	}
	
}
