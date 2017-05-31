/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CasosEspecialesListModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fIniRecepcion;
	private Date fFinRecepcion;
	private Date fIniRespuesta;
	private Date fFinRespuesta;
	private Integer idEmpresa;
	private Integer numConsec;
	
	private String cveAutoridad;
	private String tipoOficio;
	private String numOficio;
	
	
	private List<CasosEspecialesModel> casoEspecialList;
	private List<CasosEspecialesModel> casoEspecialModList;
	
	/**
	 * 
	 */
	public CasosEspecialesListModel() {
		
	}

	/**
	 * @return the fIniRecepcion
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getfIniRecepcion() {
		return fIniRecepcion;
	}

	/**
	 * @param fIniRecepcion the fIniRecepcion to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setfIniRecepcion(Date fIniRecepcion) {
		this.fIniRecepcion = fIniRecepcion;
	}

	/**
	 * @return the fFinRecepcion
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getfFinRecepcion() {
		return fFinRecepcion;
	}

	/**
	 * @param fFinRecepcion the fFinRecepcion to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setfFinRecepcion(Date fFinRecepcion) {
		this.fFinRecepcion = fFinRecepcion;
	}

	/**
	 * @return the fIniRespuesta
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getfIniRespuesta() {
		return fIniRespuesta;
	}

	/**
	 * @param fIniRespuesta the fIniRespuesta to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setfIniRespuesta(Date fIniRespuesta) {
		this.fIniRespuesta = fIniRespuesta;
	}

	/**
	 * @return the fFinRespuesta
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getfFinRespuesta() {
		return fFinRespuesta;
	}

	/**
	 * @param fFinRespuesta the fFinRespuesta to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setfFinRespuesta(Date fFinRespuesta) {
		this.fFinRespuesta = fFinRespuesta;
	}

	/**
	 * @return the idEmpresa
	 */
	public Integer getIdEmpresa() {
		return idEmpresa;
	}

	/**
	 * @param idEmpresa the idEmpresa to set
	 */
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	/**
	 * @return the cveAutoridad
	 */
	public String getCveAutoridad() {
		return cveAutoridad;
	}

	/**
	 * @param cveAutoridad the cveAutoridad to set
	 */
	public void setCveAutoridad(String cveAutoridad) {
		this.cveAutoridad = cveAutoridad;
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
	 * @return the casoEspecialList
	 */
	public List<CasosEspecialesModel> getCasoEspecialList() {
		return casoEspecialList;
	}

	/**
	 * @param casoEspecialList the casoEspecialList to set
	 */
	public void setCasoEspecialList(List<CasosEspecialesModel> casoEspecialList) {
		this.casoEspecialList = casoEspecialList;
	}

	/**
	 * @return the casoEspecialModList
	 */
	public List<CasosEspecialesModel> getCasoEspecialModList() {
		return casoEspecialModList;
	}

	/**
	 * @param casoEspecialModList the casoEspecialModList to set
	 */
	public void setCasoEspecialModList(
			List<CasosEspecialesModel> casoEspecialModList) {
		this.casoEspecialModList = casoEspecialModList;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();

		value.append(String.format("%nIniRecepcion :[%s]%n", this.fIniRecepcion));
		value.append(String.format("fFinRecepcion:[%s]%n", this.fFinRecepcion));
		value.append(String.format("fIniRespuesta:[%s]%n", this.fIniRespuesta));
		value.append(String.format("fFinRespuesta:[%s]%n", this.fFinRespuesta));
		value.append(String.format("idEmpresa    :[%d]%n", this.idEmpresa));
		value.append(String.format("cveAutoridad :[%s]%n", this.cveAutoridad));
		value.append(String.format("tipoOficio   :[%s]%n", this.tipoOficio));
		value.append(String.format("casoEspecialList   : [size: %d]%n", this.casoEspecialList != null ? this.casoEspecialList.size() : 0));
		value.append(String.format("casoEspecialModList: [size: %d]%n", this.casoEspecialModList != null ? this.casoEspecialModList.size() : 0));

		return value.toString();
	}

	public Integer getNumConsec() {
		return numConsec;
	}

	public void setNumConsec(Integer numConsec) {
		this.numConsec = numConsec;
	}
	
}
