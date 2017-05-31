package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.util.Response;
/**
 * Servicio encargado de realizar operaciones sobre la entidad CNBV_PERSONA
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public interface PersonaService {
	
	/**
	 * Busca todas las personas existentes de la tabla CNBV_PERSONA
	 * @return
	 */
	Response getAllPersonas();
	/**
	 * Guarda en la tabla CNBV_PERSONA, llave primaria numOficio,tipoOficio,numConsec
	 * @param persona
	 * @return response
	 * @throws ServiceException
	 */
	Response guardarPersona(PersonaModel persona) throws ServiceException;
	
	/**
	 * Actualiza en la tabla CNBV_PERSONA, llave primaria numOficio,tipoOficio,numConsec
	 * @param persona
	 * @return
	 * @throws ServiceException
	 */
	Response actualizarPersona(PersonaModel persona) throws ServiceException;
	
	/**
	 * Elimina un registro de Persona.
	 * @param persona
	 * @return boolean
	 */
	boolean eliminarPersona(PersonaModel persona) throws ServiceException;
	
	/**
	 * Ejecuta una busqueda personalizada en la tabla  CNBV_PERSONA llave primaria numOficio,tipoOficio,numConsec
	 * @param persona
	 * @return
	 * @throws ServiceException 
	 */
	Response buscarPersonaId(PersonaModel persona) throws ServiceException;
	
	/**
	 * Consulta si existe una Persona de acuerdo a su PK.
	 * @param numOficio
	 * @param tipoOficio
	 * @return Boolean
	 * @throws ServiceException 
	 */
	boolean existePersona(String numOficio, String tipoOficio, Long numConsec) throws ServiceException;
	
	/**
	 * Ejecuta una busqueda de personas relacionadas a un Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<PersonaModel>
	 * @throws ServiceException 
	 */
	List<PersonaModel> buscarPersonasPorOficio(String numOficio, String tipoOficio) throws ServiceException;
	
	/**
	 * Obtiene el siguiente consecutivo de la Persona.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int obtenerConsecutivo(String numOficio, String tipoOficio) throws ServiceException;	

}
