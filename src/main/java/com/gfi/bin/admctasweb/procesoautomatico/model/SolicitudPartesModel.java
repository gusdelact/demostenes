package com.gfi.bin.admctasweb.procesoautomatico.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.gfi.bin.admctasweb.util.Constantes;

/**
 * 
 * @author ESS3ESPP
 *
 */
@XmlRootElement(name=Constantes.TAG_SOLICITUD_PARTES, namespace=Constantes.XML_NAMESPACE)
@XmlAccessorType (XmlAccessType.FIELD)
public class SolicitudPartesModel {
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_PARTES_PARTE_ID, namespace=Constantes.XML_NAMESPACE)
	private Integer parteId;
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_PARTES_CARACTER, namespace=Constantes.XML_NAMESPACE)
	private String caracter;
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_PARTES_PERSONA, namespace=Constantes.XML_NAMESPACE)
	private String persona;
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_PARTES_PATERNO, namespace=Constantes.XML_NAMESPACE)
	private String paterno;
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_PARTES_MATERNO, namespace=Constantes.XML_NAMESPACE)
	private String materno;
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_PARTES_NOMBRE, namespace=Constantes.XML_NAMESPACE)
	private String nombre;
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_PARTES_RFC, namespace=Constantes.XML_NAMESPACE)
	private String rfc;

	public Integer getParteId() {
		return parteId;
	}

	public void setParteId(Integer parteId) {
		this.parteId = parteId;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
}