package com.gfi.bin.admctasweb.operativos.model;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;

/**
 * Modelo para almacenar datos que usan en la búsqueda de personas corporativas
 * @author ESS3VAVC
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaBusquedaModel extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String paterno;
	private String materno;
	private String rfc;
	private String tipoPersona;
	private Integer similaridad;
	private String objetivoBusqueda;
	
	private List<PersonaCorporativaModel> personaList;
	
	//Para búsqueda de contratos
	private Long idPersona;
	private List<ContratoModel> contratosList;
	private List<ContratoCambiosModel> contratosCambiosList;
	
	//Se agrega para contener el valor en caso de haber obtenido el registro de la tabla Contrato
	private Long idContrato;
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the paterno
	 */
	public String getPaterno() {
		return paterno;
	}
	/**
	 * @param paterno the paterno to set
	 */
	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}
	/**
	 * @return the materno
	 */
	public String getMaterno() {
		return materno;
	}
	/**
	 * @param materno the materno to set
	 */
	public void setMaterno(String materno) {
		this.materno = materno;
	}
	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}
	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	/**
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}
	/**
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}
	/**
	 * @return the similaridad
	 */
	public Integer getSimilaridad() {
		return similaridad;
	}
	/**
	 * @param similaridad the similaridad to set
	 */
	public void setSimilaridad(Integer similaridad) {
		this.similaridad = similaridad;
	}
	
	public List<PersonaCorporativaModel> getPersonaList() {
		return personaList;
	}
	
	public void setPersonaList(List<PersonaCorporativaModel> personaList) {
		this.personaList = personaList;
	}
	/**
	 * @return the contratosList
	 */
	public List<ContratoModel> getContratosList() {
		return contratosList;
	}
	/**
	 * @param contratosList the contratosList to set
	 */
	public void setContratosList(List<ContratoModel> contratosList) {
		this.contratosList = contratosList;
	}
	/**
	 * @return the contratosCambiosList
	 */
	public List<ContratoCambiosModel> getContratosCambiosList() {
		return contratosCambiosList;
	}
	/**
	 * @param contratosCambiosList the contratosCambiosList to set
	 */
	public void setContratosCambiosList(
			List<ContratoCambiosModel> contratosCambiosList) {
		this.contratosCambiosList = contratosCambiosList;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getObjetivoBusqueda() {
		return objetivoBusqueda;
	}
	public void setObjetivoBusqueda(String objetivoBusqueda) {
		this.objetivoBusqueda = objetivoBusqueda;
	}
	public Long getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}
}