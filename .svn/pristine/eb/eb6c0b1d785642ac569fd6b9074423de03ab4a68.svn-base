package com.gfi.bin.admctasweb.operativos.model;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;
import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 * Bean que para almacenar datos de respuestas de oficios positivos
 * @author ESS3VAVC
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaOficioModel extends BaseModel{
	
	private String numOficio;
	private String tipoOficio;
	private Integer idRespuesta;
	
	private String observaciones;
	private String tipoRequerimiento;
	private String tipoSolicitud;
	private String respuestaEnviada;
	
	private Integer idDireccion;
	
	
	//Campos adicionales del oficio
    private String claveAutoridad;
    private String tipoCaso;
    private Date fechaOficio;
    private String direccion;
    private String gerencia;
    
    //Se añade campo para apoderado
    private String apoderado;
	
    //Listas para combos
	private List<ItemModel> requerimientosList;
	private List<ItemModel> solicitudesList;
	private List<ItemModel> direccionesList;
    
    
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
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the tipoRequerimiento
	 */
	public String getTipoRequerimiento() {
		return tipoRequerimiento;
	}
	/**
	 * @param tipoRequerimiento the tipoRequerimiento to set
	 */
	public void setTipoRequerimiento(String tipoRequerimiento) {
		this.tipoRequerimiento = tipoRequerimiento;
	}
	/**
	 * @return the tipoSolicitud
	 */
	public String getTipoSolicitud() {
		return tipoSolicitud;
	}
	/**
	 * @param tipoSolicitud the tipoSolicitud to set
	 */
	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}
	/**
	 * @return the respuestaEnviada
	 */
	public String getRespuestaEnviada() {
		return respuestaEnviada;
	}
	/**
	 * @param respuestaEnviada the respuestaEnviada to set
	 */
	public void setRespuestaEnviada(String respuestaEnviada) {
		this.respuestaEnviada = respuestaEnviada;
	}
	/**
	 * @return the claveAutoridad
	 */
	public String getClaveAutoridad() {
		return claveAutoridad;
	}
	/**
	 * @param claveAutoridad the claveAutoridad to set
	 */
	public void setClaveAutoridad(String claveAutoridad) {
		this.claveAutoridad = claveAutoridad;
	}
	/**
	 * @return the tipoCaso
	 */
	public String getTipoCaso() {
		return tipoCaso;
	}
	/**
	 * @param tipoCaso the tipoCaso to set
	 */
	public void setTipoCaso(String tipoCaso) {
		this.tipoCaso = tipoCaso;
	}
	/**
	 * @return the fechaOficio
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFechaOficio() {
		return fechaOficio;
	}
	/**
	 * @param fechaOficio the fechaOficio to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFechaOficio(Date fechaOficio) {
		this.fechaOficio = fechaOficio;
	}
	/**
	 * @return the idRespuesta
	 */
	public Integer getIdRespuesta() {
		return idRespuesta;
	}
	/**
	 * @param idRespuesta the idRespuesta to set
	 */
	public void setIdRespuesta(Integer idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	/**
	 * @return the requerimientosList
	 */
	public List<ItemModel> getRequerimientosList() {
		return requerimientosList;
	}
	/**
	 * @param requerimientosList the requerimientosList to set
	 */
	public void setRequerimientosList(List<ItemModel> requerimientosList) {
		this.requerimientosList = requerimientosList;
	}
	/**
	 * @return the solicitudesList
	 */
	public List<ItemModel> getSolicitudesList() {
		return solicitudesList;
	}
	/**
	 * @param solicitudesList the solicitudesList to set
	 */
	public void setSolicitudesList(List<ItemModel> solicitudesList) {
		this.solicitudesList = solicitudesList;
	}
	/**
	 * @return the idDireccion
	 */
	public Integer getIdDireccion() {
		return idDireccion;
	}
	/**
	 * @param idDireccion the idDireccion to set
	 */
	public void setIdDireccion(Integer idDireccion) {
		this.idDireccion = idDireccion;
	}
	/**
	 * @return the direccionesList
	 */
	public List<ItemModel> getDireccionesList() {
		return direccionesList;
	}
	/**
	 * @param direccionesList the direccionesList to set
	 */
	public void setDireccionesList(List<ItemModel> direccionesList) {
		this.direccionesList = direccionesList;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @return the gerencia
	 */
	public String getGerencia() {
		return gerencia;
	}
	/**
	 * @param gerencia the gerencia to set
	 */
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	/**
	 * @return the apoderado
	 */
	public String getApoderado() {
		return apoderado;
	}
	/**
	 * @param apoderado the apoderado to set
	 */
	public void setApoderado(String apoderado) {
		this.apoderado = apoderado;
	}

}
