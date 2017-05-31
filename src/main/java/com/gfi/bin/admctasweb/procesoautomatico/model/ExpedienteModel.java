package com.gfi.bin.admctasweb.procesoautomatico.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;
import com.gfi.bin.admctasweb.util.Constantes;

/**
 * 
 * @author ESS3ESPP
 *
 */
@XmlRootElement(name=Constantes.TAG_EXPEDIENTE, namespace=Constantes.XML_NAMESPACE)
@XmlAccessorType (XmlAccessType.FIELD)
public class ExpedienteModel extends BaseModel{
	
	@XmlElement(name=Constantes.TAG_NUMERO_OFICIO, namespace=Constantes.XML_NAMESPACE)
	private String numeroOficio;
	
	@XmlElement(name=Constantes.TAG_AREA_DESCRIPCION, namespace=Constantes.XML_NAMESPACE)
	private String tipoOficio;
	
	@XmlElement(name=Constantes.TAG_FOLIO, namespace=Constantes.XML_NAMESPACE)
	private String numeroFolio;
	
	@XmlElement(name=Constantes.TAG_NUMERO_EXPEDIENTE, namespace=Constantes.XML_NAMESPACE)
	private String numeroExpediente;
	
	@XmlElement(name=Constantes.TAG_FECHA_PUBLICACION, namespace=Constantes.XML_NAMESPACE)
	private Date fechaOficio;
	
	@XmlElement(name=Constantes.TAG_DIAS_PLAZO, namespace=Constantes.XML_NAMESPACE)
	private Integer numeroDiasPlazo;
	
	@XmlElement(name=Constantes.TAG_REFERENCIA, namespace=Constantes.XML_NAMESPACE)
	private String referencia;
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_ESPECIFICA, namespace=Constantes.XML_NAMESPACE)
	private List<SolicitudEspecificaModel> listaSolicitudEspecificaModel;
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_PARTES, namespace=Constantes.XML_NAMESPACE)
	private List<SolicitudPartesModel> listaSolicitudPartesModel;
	
	// conceptos nuevos a extraer. JAPL
	@XmlElement(name=Constantes.TAG_SOLICITUD_SIARA, namespace=Constantes.XML_NAMESPACE)
	private String solicitudSiara;
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_PARTES_CARACTER, namespace=Constantes.XML_NAMESPACE)
	private String caracter;
	
	@XmlElement(name=Constantes.TAG_REFERENCIA2, namespace=Constantes.XML_NAMESPACE)
	private String referenciaDos;
	
	@XmlElement(name=Constantes.TAG_AUTORIDAD_NOMBRE, namespace=Constantes.XML_NAMESPACE)
	private String autoridadNombre;
	
	/*@XmlElement(name=Constantes.TAG_SOLCITUD_PARTES_CARACTER, namespace=Constantes.XML_NAMESPACE)
	private String descCaracter;
	
	public String getDescCaracter() {
		return descCaracter;
	}

	public void setDescCaracter(String descCaracter) {
		this.descCaracter = descCaracter;
	}*/

	

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public String getReferenciaDos() {
		return referenciaDos;
	}

	public void setReferenciaDos(String referenciaDos) {
		this.referenciaDos = referenciaDos;
	}

	public String getAutoridadNombre() {
		return autoridadNombre;
	}

	public void setAutoridadNombre(String autoridadNombre) {
		this.autoridadNombre = autoridadNombre;
	}

	// JAPL
	
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
	
	public String getNumeroFolio() {
		return numeroFolio;
	}
	
	public String getNumeroExpediente() {
		return numeroExpediente;
	}

	public void setNumeroExpediente(String numeroExpediente) {
		this.numeroExpediente = numeroExpediente;
	}

	public void setNumeroFolio(String numeroFolio) {
		this.numeroFolio = numeroFolio;
	}

	public Date getFechaOficio() {
		return fechaOficio;
	}

	public void setFechaOficio(Date fechaOficio) {
		this.fechaOficio = fechaOficio;
	}

	public Integer getNumeroDiasPlazo() {
		return numeroDiasPlazo;
	}

	public void setNumeroDiasPlazo(Integer numeroDiasPlazo) {
		this.numeroDiasPlazo = numeroDiasPlazo;
	}

	public List<SolicitudEspecificaModel> getListaSolicitudEspecificaModel() {
		return listaSolicitudEspecificaModel;
	}

	public void setListaSolicitudEspecificaModel(
			List<SolicitudEspecificaModel> listaSolicitudEspecificaModel) {
		this.listaSolicitudEspecificaModel = listaSolicitudEspecificaModel;
	}

	public List<SolicitudPartesModel> getListaSolicitudPartesModel() {
		return listaSolicitudPartesModel;
	}

	public void setListaSolicitudPartesModel(
			List<SolicitudPartesModel> listaSolicitudPartesModel) {
		this.listaSolicitudPartesModel = listaSolicitudPartesModel;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	/**
	 * @return the solicitudSiara
	 */
	public String getSolicitudSiara() {
		return solicitudSiara;
	}

	/**
	 * @param solicitudSiara the solicitudSiara to set
	 */
	public void setSolicitudSiara(String solicitudSiara) {
		this.solicitudSiara = solicitudSiara;
	}

	
}