package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.AutoridadesModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public interface AutoridadesService {

	/**
	 * Insertar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean insertarAutoridad(AutoridadesModel autoridadesModel) throws ServiceException;
	
	/**
	 * Actualizar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean actualizarAutoridad(AutoridadesModel autoridadesModel) throws ServiceException;
	
	/**
	 * Eliminar Autoridades
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean eliminarAutoridad(AutoridadesModel autoridadesModel) throws ServiceException;
	
	/**
	 * Consultar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return List<AutoridadesModel>
	 * @throws ServiceException
	 */
	List<AutoridadesModel> consultarAutoridades(AutoridadesModel autoridadesModel) throws ServiceException;

	/**
	 * Consultar Autoridades por Clave
	 * 
	 * @param AutoridadesModel
	 * @return AutoridadesModel
	 * @throws ServiceException
	 */
	AutoridadesModel consultarAutoridadPorClave(AutoridadesModel autoridadesModel) throws ServiceException;

	/**
	 * Existe en el Cat&aacute;logo de Autoridades 
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean existeAutoridad(AutoridadesModel autoridadesModel) throws ServiceException;
		
}
