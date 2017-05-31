package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public interface ParametrosService {

	/**
	 * Insertar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean insertarParametro(ParametrosModel parametrosModel) throws ServiceException;
	
	/**
	 * Actualizar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean actualizarParametro(ParametrosModel parametrosModel) throws ServiceException;
	
	/**
	 * Eliminar Par&aacute;metros de Oficios CNBV
	 * @param ParametrosModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean eliminarParametro(ParametrosModel parametrosModel) throws ServiceException;
	
	/**
	 * Consultar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return List<ParametrosModel>
	 * @throws ServiceException
	 */
	List<ParametrosModel> consultarParametros(ParametrosModel parametrosModel) throws ServiceException;

	/**
	 * Consultar Par&aacute;metros de Oficios CNBV por Clave
	 * 
	 * @param ParametrosModel
	 * @return ParametrosModel
	 * @throws ServiceException
	 */
	ParametrosModel consultarParametroPorClave(ParametrosModel parametrosModel) throws ServiceException;

	/**
	 * Existe en el Cat&aacute;logo de Par&aacute;metros de Oficios CNBV 
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean existeParametro(ParametrosModel parametrosModel) throws ServiceException;
	
	
}
