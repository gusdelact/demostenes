/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;
import com.gfi.bin.admctasweb.util.Constantes;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParametrosModel extends BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cveGpoParam;
	private String cveParam;
	private String descParam;
	private String sitParam;


	public ParametrosModel(String cveGpoParam, String cveParam,
			String descParam, String sitParam) {
		super();
		this.cveGpoParam = cveGpoParam;
		this.cveParam = cveParam;
		this.descParam = descParam;
		this.sitParam = sitParam;
	}
	
	/**
	 * Constructor que crea un bean de Parametro con situacion Activo
	 * @param cveGpoParam
	 */
	public ParametrosModel(String cveGpoParam) {
		super();
		this.cveGpoParam = cveGpoParam;		
		this.sitParam = Constantes.SIT_PARAMETRO;
	}
	
	/**
	 * Constructor que crea un bean de Parametro con situacion Activo
	 * @param cveGpoParam
	 */
	public ParametrosModel(String cveGpoParam,String cveParam) {
		super();
		this.cveGpoParam = cveGpoParam;		
		this.cveParam = cveParam;	
		this.sitParam = Constantes.SIT_PARAMETRO;
	}
	
	public ParametrosModel() {
	}

	/**
	 * @return the cveGpoParam
	 */
	public String getCveGpoParam() {
		return cveGpoParam;
	}

	/**
	 * @param cveGpoParam the cveGpoParam to set
	 */
	public void setCveGpoParam(String cveGpoParam) {
		this.cveGpoParam = cveGpoParam;
	}

	/**
	 * @return the cveParam
	 */
	public String getCveParam() {
		return cveParam;
	}

	/**
	 * @param cveParam the cveParam to set
	 */
	public void setCveParam(String cveParam) {
		this.cveParam = cveParam;
	}

	/**
	 * @return the descParam
	 */
	public String getDescParam() {
		return descParam;
	}

	/**
	 * @param descParam the descParam to set
	 */
	public void setDescParam(String descParam) {
		this.descParam = descParam;
	}

	/**
	 * @return the sitParam
	 */
	public String getSitParam() {
		return sitParam;
	}

	/**
	 * @param sitParam the sitParam to set
	 */
	public void setSitParam(String sitParam) {
		this.sitParam = sitParam;
	}
	
}
