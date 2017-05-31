package com.gfi.bin.admctasweb.operativos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.SimilaridadModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;
import com.gfi.bin.admctasweb.operativos.model.PersonaCorporativaModel;

/**
 * @author ESS3VAVC
 *
 */
public interface PersonaCorporativaDAO {

	/**
	 * Búsqued de personas corporativas por parámetros
	 * @param rfc
	 * @param nombre
	 * @param tipoPersona
	 * @param listaExclusiones
	 * @return
	 * @throws DAOException
	 */
	List<PersonaCorporativaModel> obtenerPersonas(String rfc, String nombre, Integer similaridad, List<ExclusionModel> listaExclusiones) throws DAOException;
	
	/**
	 * Busca persona por RC
	 * @param rfc
	 * @param similaridad
	 * @return
	 * @throws DAOException
	 */
	List<PersonaCorporativaModel> obtenerPersonas(String rfc, Integer similaridad) throws DAOException;
	
	/**
	 * Obtiene porcentaje de coincidencia para las consultas almacenado en la base
	 * @return
	 * @throws DAOException
	 */
	SimilaridadModel obtenerSimilaridad()throws DAOException;
	
	/**
	 * Obtiene un registro de Persona Corporativa por su Id.
	 * @return PersonaCorporativaModel
	 * @throws DAOException
	 */
	PersonaCorporativaModel obtenerPersonaPorId(Long idPersona) throws DAOException;
	
	/**
	 * Modifica similaridad en BD
	 * @param similaridad
	 * @return
	 * @throws DAOException
	 */
	boolean modificarSimilaridad(SimilaridadModel similaridadModel) throws DAOException;
	
}
