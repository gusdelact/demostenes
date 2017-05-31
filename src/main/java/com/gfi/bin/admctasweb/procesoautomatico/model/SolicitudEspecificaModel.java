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
@XmlRootElement(name=Constantes.TAG_SOLICITUD_ESPECIFICA, namespace=Constantes.XML_NAMESPACE)
@XmlAccessorType (XmlAccessType.FIELD)
public class SolicitudEspecificaModel {
	
	@XmlElement(name=Constantes.TAG_SOLICITUD_ESPECIFICA_ID, namespace=Constantes.XML_NAMESPACE)
	private Integer solicitudEspecificaId;
	
	@XmlElement(name=Constantes.TAG_INSTRUCCIONES_CUENTAS_POR_CONOCER, namespace=Constantes.XML_NAMESPACE)
	private String instruccionesCuentasPorConocer;
	
	@XmlElement(name=Constantes.TAG_PERSONAS_SOLICITUD, namespace=Constantes.XML_NAMESPACE)
	private List<PersonaSolicitudModel> listaPersonasSolicitud;
	
	
	public Integer getSolicitudEspecificaId() {
		return solicitudEspecificaId;
	}
	public void setSolicitudEspecificaId(Integer solicitudEspecificaId) {
		this.solicitudEspecificaId = solicitudEspecificaId;
	}
	public String getInstruccionesCuentasPorConocer() {
		return instruccionesCuentasPorConocer;
	}
	public void setInstruccionesCuentasPorConocer(
			String instruccionesCuentasPorConocer) {
		this.instruccionesCuentasPorConocer = instruccionesCuentasPorConocer;
	}
	public List<PersonaSolicitudModel> getListaPersonasSolicitud() {
		return listaPersonasSolicitud;
	}
	public void setListaPersonasSolicitud(
			List<PersonaSolicitudModel> listaPersonasSolicitud) {
		this.listaPersonasSolicitud = listaPersonasSolicitud;
	}
}
