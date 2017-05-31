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
@XmlRootElement(name=Constantes.TAG_ARRAY_OF_REQUERIMIENTO_DESCARGADO)
@XmlAccessorType(XmlAccessType.FIELD)
public class RequerimientosDescargadosModel {
	
	@XmlElement(name=Constantes.TAG_REQUERIMIENTO_DESCARGADO)
	private List<RequerimientoDescargadoModel> listaRequerimientosDescargados;
	
	private String carpetaUbicacion;
	
	private Integer idEmpresa;

	public List<RequerimientoDescargadoModel> getListaRequerimientosDescargados() {
		return listaRequerimientosDescargados;
	}

	public void setListaRequerimientosDescargados(
			List<RequerimientoDescargadoModel> listaRequerimientosDescargados) {
		this.listaRequerimientosDescargados = listaRequerimientosDescargados;
	}

	public String getCarpetaUbicacion() {
		return carpetaUbicacion;
	}

	public void setCarpetaUbicacion(String carpetaUbicacion) {
		this.carpetaUbicacion = carpetaUbicacion;
	}

	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
}