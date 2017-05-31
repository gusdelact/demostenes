package com.gfi.bin.admctasweb.catalogos.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;

/**
 * @author LUGL4884
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentoModel extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String numOficio;
	private String tipoOficio;
	private Integer numDocto;
	private String cveDocto;
	private String nomDocto;
	private CommonsMultipartFile file;
	
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
	 * @return the numDocto
	 */
	public Integer getNumDocto() {
		return numDocto;
	}
	/**
	 * @param numDocto the numDocto to set
	 */
	public void setNumDocto(Integer numDocto) {
		this.numDocto = numDocto;
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
	 * @return the file
	 */
	public CommonsMultipartFile getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	
	
}
