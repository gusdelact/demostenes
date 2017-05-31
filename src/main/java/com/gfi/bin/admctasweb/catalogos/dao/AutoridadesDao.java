package com.gfi.bin.admctasweb.catalogos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.AutoridadesModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public interface AutoridadesDao {

	/**
	 * Insertar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean insertarAutoridad(AutoridadesModel autoridadesModel) throws DAOException;
	
	/**
	 * Actualizar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean actualizarAutoridad(AutoridadesModel autoridadesModel) throws DAOException;
	
	/**
	 * Eliminar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean eliminarAutoridad(AutoridadesModel autoridadesModel) throws DAOException;

	/**
	 * Consultar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return List<AutoridadesModel>
	 * @throws DAOException
	 */
	List<AutoridadesModel> consultarAutoridades(AutoridadesModel autoridadesModel) throws DAOException;

	/**
	 * Consultar Autoridades por Clave
	 * 
	 * @param AutoridadesModel
	 * @return AutoridadesModel
	 * @throws DAOException
	 */
	AutoridadesModel consultarAutoridadPorClave(AutoridadesModel autoridadesModel) throws DAOException;

	/**
	 * Existe en el cat&aacute;logo de autoridades 
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean existeAutoridad(AutoridadesModel autoridadesModel) throws DAOException;
	
}
