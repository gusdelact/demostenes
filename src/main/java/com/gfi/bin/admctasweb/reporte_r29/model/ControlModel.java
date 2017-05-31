package com.gfi.bin.admctasweb.reporte_r29.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;
import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 * Clase Modelo para el Objeto Oficio.
 * @author LUGL4884
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ControlModel extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private String	idEmpresaCx;
	private int cvePeriodoCx;
	private int numIntentoCx;
	private String cveUsuAltaCx;
	private String situacionCx;
	private String txInstitucionCx;
	private String descPeriodoCx;
	
	private String	idEmpresa;
	private int cvePeriodo;
	private int numIntento;
	private String cveUsuAlta;
	private String situacion;
	private String txInstitucion;
	private String descPeriodo;
	
	public String getIdEmpresaCx() {
		return idEmpresaCx;
	}
	public void setIdEmpresaCx(String idEmpresaCx) {
		this.idEmpresaCx = idEmpresaCx;
	}
	public int getCvePeriodoCx() {
		return cvePeriodoCx;
	}
	public void setCvePeriodoCx(int cvePeriodoCx) {
		this.cvePeriodoCx = cvePeriodoCx;
	}
	public int getNumIntentoCx() {
		return numIntentoCx;
	}
	public void setNumIntentoCx(int numIntentoCx) {
		this.numIntentoCx = numIntentoCx;
	}
	public String getCveUsuAltaCx() {
		return cveUsuAltaCx;
	}
	public void setCveUsuAltaCx(String cveUsuAltaCx) {
		this.cveUsuAltaCx = cveUsuAltaCx;
	}
	public String getSituacionCx() {
		return situacionCx;
	}
	public void setSituacionCx(String situacionCx) {
		this.situacionCx = situacionCx;
	}
	public String getTxInstitucionCx() {
		return txInstitucionCx;
	}
	public void setTxInstitucionCx(String txInstitucionCx) {
		this.txInstitucionCx = txInstitucionCx;
	}
	public String getDescPeriodoCx() {
		return descPeriodoCx;
	}
	public void setDescPeriodoCx(String descPeriodoCx) {
		this.descPeriodoCx = descPeriodoCx;
	}
	public String getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getCvePeriodo() {
		return cvePeriodo;
	}
	public void setCvePeriodo(int cvePeriodo) {
		this.cvePeriodo = cvePeriodo;
	}
	public int getNumIntento() {
		return numIntento;
	}
	public void setNumIntento(int numIntento) {
		this.numIntento = numIntento;
	}
	public String getCveUsuAlta() {
		return cveUsuAlta;
	}
	public void setCveUsuAlta(String cveUsuAlta) {
		this.cveUsuAlta = cveUsuAlta;
	}
	public String getSituacion() {
		return situacion;
	}
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}
	public String getTxInstitucion() {
		return txInstitucion;
	}
	public void setTxInstitucion(String txInstitucion) {
		this.txInstitucion = txInstitucion;
	}
	public String getDescPeriodo() {
		return descPeriodo;
	}
	public void setDescPeriodo(String descPeriodo) {
		this.descPeriodo = descPeriodo;
	}
	
	
}
