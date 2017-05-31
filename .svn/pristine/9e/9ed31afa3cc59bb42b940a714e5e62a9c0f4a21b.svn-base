package com.gfi.bin.admctasweb.catalogos.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 * 
 * @author ESS3ESPP
 *
 */
public class DocumentoEliminadoModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String numOficio;
	private String tipoOficio;
	private String descTipoOficio;
	private String cveDocto;
	private String descCveDocto;
	private String nomDocto;
	private Date fhElim;
	private String cveUsuElim;

	
	private Timestamp fhElimTime;
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
	 * @return the descTipoOficio
	 */
	public String getDescTipoOficio() {
		return descTipoOficio;
	}

	/**
	 * @param descTipoOficio the descTipoOficio to set
	 */
	public void setDescTipoOficio(String descTipoOficio) {
		this.descTipoOficio = descTipoOficio;
	}

	/**
	 * @return the cveDocto
	 */
	public String getCveDocto() {
		return cveDocto;
	}

	/**
	 * @param cveDocto the cveDocto to set
	 */
	public void setCveDocto(String cveDocto) {
		this.cveDocto = cveDocto;
	}

	/**
	 * @return the descCveDocto
	 */
	public String getDescCveDocto() {
		return descCveDocto;
	}

	/**
	 * @param descCveDocto the descCveDocto to set
	 */
	public void setDescCveDocto(String descCveDocto) {
		this.descCveDocto = descCveDocto;
	}

	/**
	 * @return the nomDocto
	 */
	public String getNomDocto() {
		return nomDocto;
	}

	/**
	 * @param nomDocto the nomDocto to set
	 */
	public void setNomDocto(String nomDocto) {
		this.nomDocto = nomDocto;
	}

	/**
	 * @return the fhElim
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhElim() {
		return fhElim;
	}

	/**
	 * @param fhElim the fhElim to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhElim(Date fhElim) {
		this.fhElim = fhElim;
	}

	/**
	 * @return the cveUsuElim
	 */
	public String getCveUsuElim() {
		return cveUsuElim;
	}

	/**
	 * @param cveUsuElim the cveUsuElim to set
	 */
	public void setCveUsuElim(String cveUsuElim) {
		this.cveUsuElim = cveUsuElim;
	}


	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		
		value.append(String.format("%nnumOficio :[%s]%n", this.numOficio));
		value.append(String.format("tipoOficio:[%s]%n", this.tipoOficio));
		value.append(String.format("cveDocto  :[%s]%n", this.cveDocto));
		value.append(String.format("nomDocto  :[%s]%n", this.nomDocto));
		value.append(String.format("fhElim    :[%s]%n", this.fhElim));
		value.append(String.format("cveUsuElim:[%s]%n", this.cveUsuElim));

		return value.toString();
	}

	/**
	 * @return the fhElimTime
	 */
	public Timestamp getFhElimTime() {
		return fhElimTime;
	}

	/**
	 * @param fhElimTime the fhElimTime to set
	 */
	public void setFhElimTime(Timestamp fhElimTime) {
		this.fhElimTime = fhElimTime;
	}

}
