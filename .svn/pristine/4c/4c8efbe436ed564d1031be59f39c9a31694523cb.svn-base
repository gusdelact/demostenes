package com.gfi.bin.admctasweb.comunes.model;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 * Clase base para Modelo. Contiene datos comunes de Auditoría de las tablas
 * @author ESS3VAVC
 *
 */
public class BaseModel {

	/**
	 * Fecha en que se realiza registro
	 */
	private Date fhAlta;	
	/**
	 * Clave del usuario que realiza el registro
	 */
	private String cveUsuAlta;	
	/**
	 * Fecha de última modificación de registro
	 */
	private Date fhUltMod;	
	/**
	 * Clave del usuario que realizó última modificación
	 */
	private String cveUsuMod;
	/**
	 * @return the fhAlta
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhAlta() {
		return fhAlta;
	}
	/**
	 * @param fhAlta the fhAlta to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhAlta(Date fhAlta) {
		this.fhAlta = fhAlta;
	}
	/**
	 * @return the cveUsuAlta
	 */
	public String getCveUsuAlta() {
		return cveUsuAlta;
	}
	/**
	 * @param cveUsuAlta the cveUsuAlta to set
	 */
	public void setCveUsuAlta(String cveUsuAlta) {
		this.cveUsuAlta = cveUsuAlta;
	}
	/**
	 * @return the fhUltMod
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhUltMod() {
		return fhUltMod;
	}
	/**
	 * @param fhUltMod the fhUltMod to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhUltMod(Date fhUltMod) {
		this.fhUltMod = fhUltMod;
	}
	/**
	 * @return the cveUsuMod
	 */
	public String getCveUsuMod() {
		return cveUsuMod;
	}
	/**
	 * @param cveUsuMod the cveUsuMod to set
	 */
	public void setCveUsuMod(String cveUsuMod) {
		this.cveUsuMod = cveUsuMod;
	}	
	
	
	
}
