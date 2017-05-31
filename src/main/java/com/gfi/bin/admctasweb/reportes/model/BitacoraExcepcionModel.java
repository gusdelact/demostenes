/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.model;

import java.io.Serializable;
import java.util.Date;

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
public class BitacoraExcepcionModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long idContrato;
	private Date fExcepcion;
	private Integer hExcepcion;
	private String cveTipoExcepcion;
	private String DescExcepcion;
	private String cveUsuario;
	private String motivo;
	private String sitExcepcion;
	private String titular;
	private String descUsuario;
	private String situacion;

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
	 * @return the fExcepcion
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getfExcepcion() {
		return fExcepcion;
	}
	/**
	 * @param fExcepcion the fExcepcion to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setfExcepcion(Date fExcepcion) {
		this.fExcepcion = fExcepcion;
	}
	/**
	 * @return the hExcepcion
	 */
	public Integer gethExcepcion() {
		return hExcepcion;
	}
	/**
	 * @param hExcepcion the hExcepcion to set
	 */
	public void sethExcepcion(Integer hExcepcion) {
		this.hExcepcion = hExcepcion;
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
	 * @return the descExcepcion
	 */
	public String getDescExcepcion() {
		return DescExcepcion;
	}
	/**
	 * @param descExcepcion the descExcepcion to set
	 */
	public void setDescExcepcion(String descExcepcion) {
		DescExcepcion = descExcepcion;
	}
	/**
	 * @return the cveUsuario
	 */
	public String getCveUsuario() {
		return cveUsuario;
	}
	/**
	 * @param cveUsuario the cveUsuario to set
	 */
	public void setCveUsuario(String cveUsuario) {
		this.cveUsuario = cveUsuario;
	}
	/**
	 * @return the motivo
	 */
	public String getMotivo() {
		return motivo;
	}
	/**
	 * @param motivo the motivo to set
	 */
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	/**
	 * @return the sitExcepcion
	 */
	public String getSitExcepcion() {
		return sitExcepcion;
	}
	/**
	 * @param sitExcepcion the sitExcepcion to set
	 */
	public void setSitExcepcion(String sitExcepcion) {
		this.sitExcepcion = sitExcepcion;
	}
	/**
	 * @return the titular
	 */
	public String getTitular() {
		return titular;
	}
	/**
	 * @param titular the titular to set
	 */
	public void setTitular(String titular) {
		this.titular = titular;
	}
	/**
	 * @return the descUsuario
	 */
	public String getDescUsuario() {
		return descUsuario;
	}
	/**
	 * @param descUsuario the descUsuario to set
	 */
	public void setDescUsuario(String descUsuario) {
		this.descUsuario = descUsuario;
	}
	/**
	 * @return the situacion
	 */
	public String getSituacion() {
		return situacion;
	}
	/**
	 * @param situacion the situacion to set
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		
		value.append(String.format("%nidContrato      :[%d]%n", this.idContrato));
		value.append(String.format("fExcepcion      :[%s]%n", Util.dateToString(this.fExcepcion, Constantes.FORMATO_DDMMYYYY)));
		value.append(String.format("hExcepcion      :[%d]%n", this.hExcepcion));
		value.append(String.format("cveTipoExcepcion:[%s]%n", this.cveTipoExcepcion));
		value.append(String.format("cveUsuario      :[%s]%n", this.cveUsuario));
		value.append(String.format("motivo          :[%s]%n", this.motivo));
		value.append(String.format("sitExcepcion    :[%s]%n", this.sitExcepcion));
		value.append(String.format("titular         :[%s]%n", this.titular));
		value.append(String.format("descUsuario     :[%s]%n", this.descUsuario));
		value.append(String.format("situacion       :[%s]%n", this.situacion));
		return value.toString();
	}

}
