/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitacoraExcepcionListModel {

	private Integer idEmpresa;
	private Long idContrato;
	private String cveTipoExcepcion;
	private Date fInicio;
	private Date fFin;
	
	private List<BitacoraExcepcionModel> bitExcepcionList;
	
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
	 * @return the idContrato
	 */
	public Long getIdContrato() {
		return idContrato;
	}
	/**
	 * @param idContrato the idContrato to set
	 */
	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}
	/**
	 * @return the cveTipoExcepcion
	 */
	public String getCveTipoExcepcion() {
		return cveTipoExcepcion;
	}
	/**
	 * @param cveTipoExcepcion the cveTipoExcepcion to set
	 */
	public void setCveTipoExcepcion(String cveTipoExcepcion) {
		this.cveTipoExcepcion = cveTipoExcepcion;
	}
	/**
	 * @return the fInicio
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getfInicio() {
		return fInicio;
	}
	/**
	 * @param fInicio the fInicio to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}
	/**
	 * @return the fFin
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getfFin() {
		return fFin;
	}
	/**
	 * @param fFin the fFin to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}
	/**
	 * @return the bitExcepcionList
	 */
	public List<BitacoraExcepcionModel> getBitExcepcionList() {
		return bitExcepcionList;
	}
	/**
	 * @param bitExcepcionList the bitExcepcionList to set
	 */
	public void setBitExcepcionList(List<BitacoraExcepcionModel> bitExcepcionList) {
		this.bitExcepcionList = bitExcepcionList;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		
		value.append(String.format("%nidEmpresa       :[%d]%n", this.idEmpresa));
		value.append(String.format("idContrato      :[%d]%n", this.idContrato));
		value.append(String.format("cveTipoExcepcion:[%s]%n", this.cveTipoExcepcion));
		value.append(String.format("fInicio         :[%s]%n", Util.dateToString(this.fInicio, Constantes.FORMATO_DDMMYYYY)));
		value.append(String.format("fFin            :[%s]%n", Util.dateToString(this.fFin, Constantes.FORMATO_DDMMYYYY)));
		value.append(String.format("bitExcepcionList: [size: %d]%n", this.bitExcepcionList!=null ? this.bitExcepcionList.size() : 0));
		return value.toString();		
	}

}
