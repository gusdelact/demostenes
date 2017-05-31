package com.gfi.bin.admctasweb.operativos.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;
import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 *Clase persistente para la tabla de la base de datos "CNBV_OFICIO". 
 *Utilizada para la creacion del archivo para la CNBV
 *  @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OficioCnbvModel extends BaseModel implements Serializable {
	
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
	private Integer numDiasPzo;	
	private String txDireccion;	
	private String txGerencia;	
	private String txSubgeren;	
	private String txAtnNom;	
	private String txAtnPue;
	private String bTurAudit;
	private String sitOficio;	
	private Date fhEnvio;	
	private String cveUsuEnvio;	
	private String txNomArch;	
	private String txNomAcu;	
	private String cveUsuAcu;	
	private Date fhRegAcu;	
	private Date fhVencimiento;
	private boolean tieneAcuse;
	private String tipoCaso;
	private String cveEstatus;
	
	//Campos que se ocupan para descripciones
	private boolean selected;
	private String descTipoOficio;
	private String descEmpresa;
	private Date fechaEmpresa;
	
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
	 * @return the numDiasPzo
	 */
	public Integer getNumDiasPzo() {
		return numDiasPzo;
	}
	/**
	 * @param numDiasPzo the numDiasPzo to set
	 */
	public void setNumDiasPzo(Integer numDiasPzo) {
		this.numDiasPzo = numDiasPzo;
	}
	/**
	 * @return the txDireccion
	 */
	public String getTxDireccion() {
		return txDireccion;
	}
	/**
	 * @param txDireccion the txDireccion to set
	 */
	public void setTxDireccion(String txDireccion) {
		this.txDireccion = txDireccion;
	}
	/**
	 * @return the txGerencia
	 */
	public String getTxGerencia() {
		return txGerencia;
	}
	/**
	 * @param txGerencia the txGerencia to set
	 */
	public void setTxGerencia(String txGerencia) {
		this.txGerencia = txGerencia;
	}
	/**
	 * @return the txSubgeren
	 */
	public String getTxSubgeren() {
		return txSubgeren;
	}
	/**
	 * @param txSubgeren the txSubgeren to set
	 */
	public void setTxSubgeren(String txSubgeren) {
		this.txSubgeren = txSubgeren;
	}
	/**
	 * @return the txAtnNom
	 */
	public String getTxAtnNom() {
		return txAtnNom;
	}
	/**
	 * @param txAtnNom the txAtnNom to set
	 */
	public void setTxAtnNom(String txAtnNom) {
		this.txAtnNom = txAtnNom;
	}
	/**
	 * @return the txAtnPue
	 */
	public String getTxAtnPue() {
		return txAtnPue;
	}
	/**
	 * @param txAtnPue the txAtnPue to set
	 */
	public void setTxAtnPue(String txAtnPue) {
		this.txAtnPue = txAtnPue;
	}
	/**
	 * @return the bTurAudit
	 */
	public String getbTurAudit() {
		return bTurAudit;
	}
	/**
	 * @param bTurAudit the bTurAudit to set
	 */
	public void setbTurAudit(String bTurAudit) {
		this.bTurAudit = bTurAudit;
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
	 * @return the cveUsuEnvio
	 */
	public String getCveUsuEnvio() {
		return cveUsuEnvio;
	}
	/**
	 * @param cveUsuEnvio the cveUsuEnvio to set
	 */
	public void setCveUsuEnvio(String cveUsuEnvio) {
		this.cveUsuEnvio = cveUsuEnvio;
	}
	/**
	 * @return the txNomArch
	 */
	public String getTxNomArch() {
		return txNomArch;
	}
	/**
	 * @param txNomArch the txNomArch to set
	 */
	public void setTxNomArch(String txNomArch) {
		this.txNomArch = txNomArch;
	}
	/**
	 * @return the txNomAcu
	 */
	public String getTxNomAcu() {
		return txNomAcu;
	}
	/**
	 * @param txNomAcu the txNomAcu to set
	 */
	public void setTxNomAcu(String txNomAcu) {
		this.txNomAcu = txNomAcu;
	}
	/**
	 * @return the cveUsuAcu
	 */
	public String getCveUsuAcu() {
		return cveUsuAcu;
	}
	/**
	 * @param cveUsuAcu the cveUsuAcu to set
	 */
	public void setCveUsuAcu(String cveUsuAcu) {
		this.cveUsuAcu = cveUsuAcu;
	}
	/**
	 * @return the fhRegAcu
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhRegAcu() {
		return fhRegAcu;
	}
	/**
	 * @param fhRegAcu the fhRegAcu to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhRegAcu(Date fhRegAcu) {
		this.fhRegAcu = fhRegAcu;
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
	 * @return the tieneAcuse
	 */
	public boolean getTieneAcuse() {
		return tieneAcuse;
	}
	/**
	 * @param tieneAcuse the tieneAcuse to set
	 */
	public void setTieneAcuse(boolean tieneAcuse) {
		this.tieneAcuse = tieneAcuse;
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
	public String getDescTipoOficio() {
		return descTipoOficio;
	}
	public void setDescTipoOficio(String descTipoOficio) {
		this.descTipoOficio = descTipoOficio;
	}
	public String getDescEmpresa() {
		return descEmpresa;
	}
	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public Date getFechaEmpresa() {
		return fechaEmpresa;
	}
	public void setFechaEmpresa(Date fechaEmpresa) {
		this.fechaEmpresa = fechaEmpresa;
	}

}
