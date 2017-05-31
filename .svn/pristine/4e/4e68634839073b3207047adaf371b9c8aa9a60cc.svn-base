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
@XmlRootElement(name=Constantes.TAG_CUENTAS_CONOCIDAS, namespace=Constantes.XML_NAMESPACE)
@XmlAccessorType(XmlAccessType.FIELD)
public class CuentasConocidasModel {
	
	@XmlElement(name=Constantes.TAG_CUENTAS_CONOCIDAS_ENTIDAD, namespace=Constantes.XML_NAMESPACE)
	private String entidad;
	
	@XmlElement(name=Constantes.TAG_CUENTAS_CONOCIDAS_CUENTA, namespace=Constantes.XML_NAMESPACE)
	private List<String> cuenta;
	
	@XmlElement(name=Constantes.TAG_CUENTAS_CONOCIDAS_INSTRUCCIONES, namespace=Constantes.XML_NAMESPACE)
	private String instrucciones;
	
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public List<String> getCuenta() {
		return cuenta;
	}
	public void setCuenta(List<String> cuenta) {
		this.cuenta = cuenta;
	}
	public String getInstrucciones() {
		return instrucciones;
	}
	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}
}