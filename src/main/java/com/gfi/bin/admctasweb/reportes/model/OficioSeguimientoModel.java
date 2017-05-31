/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.model;

import java.io.Serializable;
import java.util.Date;

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
public class OficioSeguimientoModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numOficio;	
	private String tipoOficio;	
	private String numFolio;	
	private String numExped;	
	private String numRegistro;	
	private String cveAutoridad;	
	private Integer idEmpresa;	
	private Date fhOficio;	
	private Date fhRecepcion;	
	private String sitOficio;	
	private Date fhEnvio;	
	private Date fhVencimiento;
	private String tipoCaso;
	private String cveEstatus;
	private String txEstatus;
	private String cveEstatusPend;
	private String txEstatusPend;
	
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
	 * @return the numFolio
	 */
	public String getNumFolio() {
		return numFolio;
	}
	/**
	 * @param numFolio the numFolio to set
	 */
	public void setNumFolio(String numFolio) {
		this.numFolio = numFolio;
	}
	/**
	 * @return the numExped
	 */
	public String getNumExped() {
		return numExped;
	}
	/**
	 * @param numExped the numExped to set
	 */
	public void setNumExped(String numExped) {
		this.numExped = numExped;
	}
	/**
	 * @return the numRegistro
	 */
	public String getNumRegistro() {
		return numRegistro;
	}
	/**
	 * @param numRegistro the numRegistro to set
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}
	/**
	 * @return the cveAutoridad
	 */
	public String getCveAutoridad() {
		return cveAutoridad;
	}
	/**
	 * @param cveAutoridad the cveAutoridad to set
	 */
	public void setCveAutoridad(String cveAutoridad) {
		this.cveAutoridad = cveAutoridad;
	}
	/**
	 * @return the idEmpresa
	 */
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	/**
	 * @return the fhOficio
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhOficio() {
		return fhOficio;
	}
	/**
	 * @param fhOficio the fhOficio to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhOficio(Date fhOficio) {
		this.fhOficio = fhOficio;
	}
	/**
	 * @return the fhRecepcion
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhRecepcion() {
		return fhRecepcion;
	}
	/**
	 * @param fhRecepcion the fhRecepcion to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhRecepcion(Date fhRecepcion) {
		this.fhRecepcion = fhRecepcion;
	}
	/**
	 * @return the sitOficio
	 */
	public String getSitOficio() {
		return sitOficio;
	}
	/**
	 * @param sitOficio the sitOficio to set
	 */
	public void setSitOficio(String sitOficio) {
		this.sitOficio = sitOficio;
	}
	/**
	 * @return the fhEnvio
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhEnvio() {
		return fhEnvio;
	}
	/**
	 * @param fhEnvio the fhEnvio to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhEnvio(Date fhEnvio) {
		this.fhEnvio = fhEnvio;
	}
	/**
	 * @return the fhVencimiento
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhVencimiento() {
		return fhVencimiento;
	}
	/**
	 * @param fhVencimiento the fhVencimiento to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhVencimiento(Date fhVencimiento) {
		this.fhVencimiento = fhVencimiento;
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
	 * @return the cveEstatusPend
	 */
	public String getCveEstatusPend() {
		return cveEstatusPend;
	}
	/**
	 * @param cveEstatusPend the cveEstatusPend to set
	 */
	public void setCveEstatusPend(String cveEstatusPend) {
		this.cveEstatusPend = cveEstatusPend;
	}
	/**
	 * @return the txEstatus
	 */
	public String getTxEstatus() {
		return txEstatus;
	}
	/**
	 * @param txEstatus the txEstatus to set
	 */
	public void setTxEstatus(String txEstatus) {
		this.txEstatus = txEstatus;
	}
	/**
	 * @return the txEstatusPend
	 */
	public String getTxEstatusPend() {
		return txEstatusPend;
	}
	/**
	 * @param txEstatusPend the txEstatusPend to set
	 */
	public void setTxEstatusPend(String txEstatusPend) {
		this.txEstatusPend = txEstatusPend;
	}

}
