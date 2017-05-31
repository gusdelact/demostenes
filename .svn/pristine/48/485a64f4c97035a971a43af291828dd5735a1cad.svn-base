package com.gfi.bin.admctasweb.catalogos.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DireccionesSolicitantesModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer idConfiguracion;
	private String tipoOficio;
	private String direccion;
	private String gerencia;
	private String subgerencia;
	private String nombreAtencion;
	private String puestoAtencion;
	private String situacion;
	private Integer nivel;
	
	/**
	 * @return the idConfiguracion
	 */
	public Integer getIdConfiguracion() {
		return idConfiguracion;
	}
	/**
	 * @param idConfiguracion the idConfiguracion to set
	 */
	public void setIdConfiguracion(Integer idConfiguracion) {
		this.idConfiguracion = idConfiguracion;
	}
	/**
	 * @return the tipoOficio
	 */
	public String getTipoOficio() {
		return this.tipoOficio != null ? tipoOficio.trim().toUpperCase() : null;
	}
	/**
	 * @param tipoOficio the tipoOficio to set
	 */
	public void setTipoOficio(String tipoOficio) {
		this.tipoOficio = tipoOficio != null ? tipoOficio.trim().toUpperCase() : null;		
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return this.direccion != null ? direccion.trim().toUpperCase() : null;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion != null ? direccion.trim().toUpperCase() : null;
	}
	/**
	 * @return the gerencia
	 */
	public String getGerencia() {
		return this.gerencia != null ? gerencia.trim().toUpperCase() : null;
	}
	/**
	 * @param gerencia the gerencia to set
	 */
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia != null ? gerencia.trim().toUpperCase() : null;
	}
	/**
	 * @return the subgerencia
	 */
	public String getSubgerencia() {
		return this.subgerencia != null ? subgerencia.trim().toUpperCase() : null;
	}
	/**
	 * @param subgerencia the subgerencia to set
	 */
	public void setSubgerencia(String subgerencia) {
		this.subgerencia = subgerencia != null ? subgerencia.trim().toUpperCase() : null;
	}
	/**
	 * @return the nombreAtencion
	 */
	public String getNombreAtencion() {
		return this.nombreAtencion != null ? nombreAtencion.trim().toUpperCase() : null;
	}
	/**
	 * @param nombreAtencion the nombreAtencion to set
	 */
	public void setNombreAtencion(String nombreAtencion) {
		this.nombreAtencion = nombreAtencion != null ? nombreAtencion.trim().toUpperCase() : null;
	}
	/**
	 * @return the puestoAtencion
	 */
	public String getPuestoAtencion() {
		return this.puestoAtencion != null ? puestoAtencion.trim().toUpperCase() : null;
	}
	/**
	 * @param puestoAtencion the puestoAtencion to set
	 */
	public void setPuestoAtencion(String puestoAtencion) {
		this.puestoAtencion = puestoAtencion != null ? puestoAtencion.trim().toUpperCase() : null;
	}
	/**
	 * @return the situacion
	 */
	public String getSituacion() {
		return this.situacion != null ? situacion.trim().toUpperCase() : null;
	}
	/**
	 * @param situacion the situacion to set
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion != null ? situacion.trim().toUpperCase() : null;
	}
	/**
	 * @return the nivel
	 */
	public Integer getNivel() {
		return nivel;
	}
	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		
		value.append(String.format("%nIdConfiguracion  :[%d]%n", this.idConfiguracion));
		value.append(String.format("tipoOficio       :[%s]%n", this.tipoOficio));
		value.append(String.format("direccion        :[%s]%n", this.direccion));
		value.append(String.format("gerencia         :[%s]%n", this.gerencia));
		value.append(String.format("subgerencia      :[%s]%n", this.subgerencia));
		value.append(String.format("nombreAtencion   :[%s]%n", this.nombreAtencion));
		value.append(String.format("puestoAtencion   :[%s]%n", this.puestoAtencion));
		value.append(String.format("situacion        :[%s]%n", this.situacion));
		value.append(String.format("nivel            :[%d]%n", this.nivel));
		value.append(String.format("fhAlta           :[%s]%n", this.getFhAlta()));
		value.append(String.format("cveUsuAlta       :[%s]%n", this.getCveUsuAlta()));
		value.append(String.format("fhUltMod         :[%s]%n", this.getFhUltMod()));
		value.append(String.format("cveUsuMod        :[%s]%n", this.getCveUsuMod()));

		return value.toString();
	}
	
}
