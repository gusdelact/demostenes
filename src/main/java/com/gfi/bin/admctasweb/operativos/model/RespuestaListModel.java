/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Modelo para almacenar datos que usan en la búsqueda de Respuestas.
 * @author lugl4884
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaListModel {
	
	private String numOficio;
	private String tipoOficio;
	private List<RespuestaModel> respuestaList;
	private List<RespuestaModel> borrarList;
	
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
	 * @return the respuestaList
	 */
	public List<RespuestaModel> getRespuestaList() {
		return respuestaList;
	}
	/**
	 * @param respuestaList the respuestaList to set
	 */
	public void setRespuestaList(List<RespuestaModel> respuestaList) {
		this.respuestaList = respuestaList;
	}
	/**
	 * @return the borrarList
	 */
	public List<RespuestaModel> getBorrarList() {
		return borrarList;
	}
	/**
	 * @param borrarList the borrarList to set
	 */
	public void setBorrarList(List<RespuestaModel> borrarList) {
		this.borrarList = borrarList;
	}

}
