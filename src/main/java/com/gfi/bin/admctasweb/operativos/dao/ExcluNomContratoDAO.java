package com.gfi.bin.admctasweb.operativos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;

public interface ExcluNomContratoDAO {
	
	/**
	 * Método que devuelve las exclusiones con estado recibido en el parámetro
	 * 
	 * @param estatus
	 * @return List<ExclusionModel>
	 * @throws DAOException
	 */
	List<ExclusionModel> obtenerExclusiones(Boolean estatus) throws DAOException;
	
	/**
	 * Método que devuelve todas las exclusiones sin importar su estado
	 * 
	 * @return List<ExclusionModel>
	 * @throws DAOException
	 */
	List<ExclusionModel> obtenerExclusiones() throws DAOException;
	
	/**
	 * Método que busca exclusiones por nombre
	 * 
	 * @param nombreExclusion
	 * @return List<ExclusionModel>
	 * @throws DAOException
	 */
	List<ExclusionModel> obtenerExclusionPorNombre(String nombreExclusion) throws DAOException;
	
	/**
	 * Método que busca una exclusión por el id
	 * 
	 * @param idExclusion
	 * @return ExclusionModel
	 * @throws DAOException
	 */
	ExclusionModel obtenerExclusionPorId(Integer idExclusion) throws DAOException;
	
	/**
	 * Método que guarda una nueva exclusion
	 * 
	 * @param exclusionModel
	 * @return Boolean
	 * @throws DAOException
	 */
	Boolean insertarExclusion(ExclusionModel exclusionModel) throws DAOException;
	
	/**
	 * Método que actualiza una exclusión
	 * 
	 * @param exclusionModel
	 * @return Boolean
	 * @throws DAOException
	 */
	Boolean actualizarExclusion(ExclusionModel exclusionModel) throws DAOException;
	
	/**
	 * Método que elimina una exclusión
	 * 
	 * @param exclusionModel
	 * @return Boolean
	 * @throws DAOException
	 */
	Boolean eliminarExclusion(Integer idExclusion) throws DAOException;
	
	/**
	 * Obtiene el siguiente valor de la secuencia para la inserción de registros en Exclusiones
	 * 
	 * @return Integer
	 * @throws DAOException
	 */
	Integer obtenerSiguienteValorSecuencia() throws DAOException;
}