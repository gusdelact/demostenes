/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoridadesModel extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cveAutoridad;
	private String nomAutoridad;
	private String sitAutoridad;
	
	/**
	 * @return the cveAutoridad
	 */
	public String getCveAutoridad() {
		return this.cveAutoridad != null ? cveAutoridad.trim().toUpperCase() : null;
	}
	/**
	 * @param cveAutoridad the cveAutoridad to set
	 */
	public void setCveAutoridad(String cveAutoridad) {
		this.cveAutoridad = cveAutoridad != null ? cveAutoridad.trim().toUpperCase() : null;
	}
	/**
	 * @return the nomAutoridad
	 */
	public String getNomAutoridad() {
		return this.nomAutoridad != null ? nomAutoridad.trim().toUpperCase() : null;
	}
	/**
	 * @param nomAutoridad the nomAutoridad to set
	 */
	public void setNomAutoridad(String nomAutoridad) {
		this.nomAutoridad = nomAutoridad != null ? nomAutoridad.trim().toUpperCase() : null;
	}	
	/**
	 * @return the sitAutoridad
	 */
	public String getSitAutoridad() {
		return this.sitAutoridad != null ? sitAutoridad.trim().toUpperCase() : null;
	}
	/**
	 * @param sitAutoridad the sitAutoridad to set
	 */
	public void setSitAutoridad(String sitAutoridad) {
		this.sitAutoridad = sitAutoridad != null ? sitAutoridad.trim().toUpperCase() : null;
	}
	
	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		
		value.append(String.format("%ncveAutoridad:[%s]%n", this.cveAutoridad));
		value.append(String.format("nomAutoridad:[%s]%n", this.nomAutoridad));
		value.append(String.format("sitAutoridad:[%s]%n", this.sitAutoridad));
		value.append(String.format("fhAlta      :[%s]%n", this.getFhAlta()));
		value.append(String.format("cveUsuAlta  :[%s]%n", this.getCveUsuAlta()));
		value.append(String.format("fhUltMod    :[%s]%n", this.getFhUltMod()));
		value.append(String.format("cveUsuMod   :[%s]%n", this.getCveUsuMod()));

		return value.toString();
	}
	
}
