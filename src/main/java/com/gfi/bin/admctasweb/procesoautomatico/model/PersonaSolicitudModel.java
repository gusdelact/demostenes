package com.gfi.bin.admctasweb.procesoautomatico.model;

import java.util.List;

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
@XmlRootElement(name=Constantes.TAG_PERSONAS_SOLICITUD, namespace=Constantes.XML_NAMESPACE)
@XmlAccessorType (XmlAccessType.FIELD)
public class PersonaSolicitudModel {
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_PERSONA_ID, namespace=Constantes.XML_NAMESPACE)
	private Integer personaId;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_CARACTER, namespace=Constantes.XML_NAMESPACE)
	private String caracter;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_PERSONA, namespace=Constantes.XML_NAMESPACE)
	private String persona;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_PATERNO, namespace=Constantes.XML_NAMESPACE)
	private String paterno;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_MATERNO, namespace=Constantes.XML_NAMESPACE)
	private String Materno;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_NOMBRE, namespace=Constantes.XML_NAMESPACE)
	private String nombre;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_RFC, namespace=Constantes.XML_NAMESPACE)
	private String rfc;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_RELACION, namespace=Constantes.XML_NAMESPACE)
	private String relacion;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_DOMICILIO, namespace=Constantes.XML_NAMESPACE)
	private String domicilio;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD_COMPLEMENTARIOS, namespace=Constantes.XML_NAMESPACE)
	private String complementarios;
	
	@XmlElement(name=Constantes.TAG_CUENTAS_CONOCIDAS, namespace=Constantes.XML_NAMESPACE)
	private List<CuentasConocidasModel> listaCuentasConocidas;
	
	public Integer getPersonaId() {
		return personaId;
	}
	public void setPersonaId(Integer personaId) {
		this.personaId = personaId;
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
		return Materno;
	}
	public void setMaterno(String materno) {
		Materno = materno;
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
	public String getRelacion() {
		return relacion;
	}
	public void setRelacion(String relacion) {
		this.relacion = relacion;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getComplementarios() {
		return complementarios;
	}
	public void setComplementarios(String complementarios) {
		this.complementarios = complementarios;
	}
	public List<CuentasConocidasModel> getListaCuentasConocidas() {
		return listaCuentasConocidas;
	}
	public void setListaCuentasConocidas(
			List<CuentasConocidasModel> listaCuentasConocidas) {
		this.listaCuentasConocidas = listaCuentasConocidas;
	}
}