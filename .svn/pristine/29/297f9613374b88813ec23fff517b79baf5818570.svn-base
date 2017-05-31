package com.gfi.bin.admctasweb.operativos.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;

public interface ExcluNomContratoService {
	
	/**
	 * Método que devuelve las exclusiones con estado recibido en el parámetro
	 * 
	 * @param estatus
	 * @return List<ExclusionModel>
	 * @throws ServiceException
	 */
	List<ExclusionModel> obtenerExclusiones(Boolean estatus) throws ServiceException;
	
	/**
	 * Método que devuelve todas las exclusiones sin importar su estado
	 * 
	 * @return List<ExclusionModel>
	 * @throws ServiceException
	 */
	List<ExclusionModel> obtenerExclusiones() throws ServiceException;
	
	/**
	 * Método que busca exclusiones por nombre
	 * 
	 * @param nombreExclusion
	 * @return List<ExclusionModel>
	 * @throws ServiceException
	 */
	List<ExclusionModel> obtenerExclusionPorNombre(String nombreExclusion) throws ServiceException;
	
	/**
	 * Método que busca una exclusión por el id
	 * 
	 * @param idExclusion
	 * @return ExclusionModel
	 * @throws ServiceException
	 */
	ExclusionModel obtenerExclusionPorId(Integer idExclusion) throws ServiceException;
	
	/**
	 * Método que guarda una nueva exclusion
	 * 
	 * @param exclusionModel
	 * @return Boolean
	 * @throws ServiceException
	 */
	Boolean insertarExclusion(ExclusionModel exclusionModel) throws ServiceException;
	
	/**
	 * Método que actualiza una exclusión
	 * 
	 * @param exclusionModel
	 * @return Boolean
	 * @throws ServiceException
	 */
	Boolean actualizarExclusion(ExclusionModel exclusionModel) throws ServiceException;
	
	/**
	 * Método que elimina una exclusión
	 * 
	 * @param exclusionModel
	 * @return Boolean
	 * @throws DAOException
	 */
	Boolean eliminarExclusion(Integer idExclusion) throws ServiceException;
	
	/**
	 * Obtiene el siguiente valor de la secuencia para la inserción de registros en Exclusiones
	 * 
	 * @return Integer
	 * @throws ServiceException
	 */
	Integer obtenerSiguienteValorSecuencia() throws ServiceException;
}