package com.gfi.bin.admctasweb.operativos.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.SimilaridadModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.PersonaCorporativaModel;

/**
 * Clase de servicios para persona corporativa
 * @author ESS3VAVC
 *
 */
public interface PersonaCorporativaService {
	
	/**
	 * Obtiene Coincidencia de personas de acuerdo a los filtros proporcionados
	 * 
	 * @param rfc
	 * @param nombre
	 * @param tipoPersona (Física o Moral)
	 * @return
	 * @throws ServiceException
	 */
	List<PersonaCorporativaModel> obtenerPersonas(String rfc, String nombre, Integer similaridad) throws ServiceException;

	/**
	 * Servicio para búsqueda de personas por RFC
	 * @param rfc
	 * @param similaridad
	 * @return
	 * @throws ServiceException
	 */
	List<PersonaCorporativaModel> obtenerPersonas(String rfc, Integer similaridad)throws ServiceException;
	
	/**
	 * 
	 * @return
	 * @throws ServiceException
	 */
	SimilaridadModel obtenerSimilaridad()throws ServiceException;
	
	/**
	 * Obtiene un registro de Persona Corporativa por su Id.
	 * @return PersonaCorporativaModel
	 * @throws DAOException
	 */
	PersonaCorporativaModel obtenerPersonaPorId(Long idPersona) throws ServiceException;
	
	/**
	 * Modifica el porcentaje de similaridad guardado en la BD
	 * @param similaridad
	 * @return
	 * @throws ServiceException
	 */
	boolean modificarSimilaridad(SimilaridadModel similaridadModel) throws ServiceException;
}
