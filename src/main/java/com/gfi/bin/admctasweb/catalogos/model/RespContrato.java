package com.gfi.bin.admctasweb.catalogos.model;

import java.io.Serializable;
import java.util.Date;

public class RespContrato implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String numOficio;	
	private String tipoOficio;
	private Long idContrato;
	private Long montoInicial;
	private Date fhAlta;
	private String cveUsuAlta;
	private Date fhMod;
	private String cveUsuMod;
	private Integer recordCount;
	
	public RespContrato() {
		super();
	}

	public String getNumOficio() {
		return numOficio;
	}

	public void setNumOficio(String numOficio) {
		this.numOficio = numOficio;
	}

	public String getTipoOficio() {
		return tipoOficio;
	}

	public void setTipoOficio(String tipoOficio) {
		this.tipoOficio = tipoOficio;
	}

	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	public Date getFhAlta() {
		return fhAlta;
	}

	public void setFhAlta(Date fhAlta) {
		this.fhAlta = fhAlta;
	}

	public String getCveUsuAlta() {
		return cveUsuAlta;
	}

	public void setCveUsuAlta(String cveUsuAlta) {
		this.cveUsuAlta = cveUsuAlta;
	}

	public Date getFhMod() {
		return fhMod;
	}

	public void setFhMod(Date fhMod) {
		this.fhMod = fhMod;
	}

	public String getCveUsuMod() {
		return cveUsuMod;
	}

	public void setCveUsuMod(String cveUsuMod) {
		this.cveUsuMod = cveUsuMod;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}	

	public Long getMontoInicial() {
		return montoInicial;
	}

	public void setMontoInicial(Long montoInicial) {
		this.montoInicial = montoInicial;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "RespContrato [numOficio=" + numOficio + ", tipoOficio="
				+ tipoOficio + ", idContrato=" + idContrato + ", montoInicial="
				+ montoInicial + ", fhAlta=" + fhAlta + ", cveUsuAlta="
				+ cveUsuAlta + ", fhMod=" + fhMod + ", cveUsuMod=" + cveUsuMod
				+ ", recordCount=" + recordCount + "]";
	}	
}