/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author lugl4884
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaListModel {
	
	private String numOficio;
	private String tipoOficio;
	private Long numConsec;
	private List<PersonaModel> personaList;
	
	
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
	 * @return the numConsec
	 */
	public Long getNumConsec() {
		return numConsec;
	}
	/**
	 * @param numConsec the numConsec to set
	 */
	public void setNumConsec(Long numConsec) {
		this.numConsec = numConsec;
	}
	/**
	 * @return the personaList
	 */
	public List<PersonaModel> getPersonaList() {
		return personaList;
	}
	/**
	 * @param personaList the personaList to set
	 */
	public void setPersonaList(List<PersonaModel> personaList) {
		this.personaList = personaList;
	}

}
