package com.gfi.bin.admctasweb.catalogos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public interface PersonaDao {
	
	/**
	 * Metodo encargado de obtener todas las personas de la tabla CNBV_PERSONA
	 * @return List<PersonaModel>
	 */
	List<PersonaModel> getAllPersonas();
	
	/** 
	 * Inserta un renglon de la tabla CNBV_PERSONA
	 * @param persona
	 * @throws DAOException 
	 */
	void guardarPersona(PersonaModel persona) throws DAOException;
	
	/** 
	 * Actualiza un renglon de la tabla CNBV_PERSONA
	 * @param persona
	 */
	void actualizarPersona(PersonaModel persona) throws DAOException;
	
	/**
	 * Elimina un registro de Persona.
	 * @param persona
	 * @return boolean
	 */
	boolean eliminarPersona(PersonaModel persona) throws DAOException;
	
	/** 
	 * Busqueda por la llave primaria compuesta 'numOficio,tipoOficio,numConsec' de la tabla CNBV_PERSONA
	 * @param persona
	 */
	PersonaModel buscarPersonaId(PersonaModel persona);
	
	/**
	 * Ejecuta una busqueda de personas relacionadas a un Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<PersonaModel>
	 */
	List<PersonaModel> buscarPersonasPorOficio(String numOficio, String tipoOficio) throws DAOException;
	
	/**
	 * Consulta si existe una Persona de acuerdo a su PK.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int existePersona(String numOficio, String tipoOficio, Long numConsec) throws DAOException;
	
	/**
	 * Obtiene el siguiente consecutivo de la Persona.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int obtenerConsecutivo(String numOficio, String tipoOficio) throws DAOException;
	
	/**
	 * Obtiene el id de la persona
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int obtenerIDPersona(String numOficio, String tipoOficio,Long numConsec);

}
