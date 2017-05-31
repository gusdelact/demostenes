/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.RespuestaModel;

/**
 * @author lugl4884
 *
 */
public interface RespuestaDao {
	
	/**
	 * Genera un registro de Respuesta.
	 * @param respuesta
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean guardarRespuesta(RespuestaModel respuesta) throws DAOException;
	
	/**
	 * Elimina un registro de Respuesta.
	 * @param respuesta
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean eliminarRespuesta(RespuestaModel respuesta) throws DAOException;
	
	/**
	 * Busca las Respuestas relacionadas a un Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<RespuestaModel>
	 * @throws DAOException
	 */
	public List<RespuestaModel> buscarRespuestasPorOficio(String numOficio,
			String tipoOficio) throws DAOException;
	
	/**
	 * Servicio que valida la existencia de un registro de Respuesta.
	 * @param respuesta
	 * @return boolean
	 * @throws ServiceException
	 */
	public int existeRespuesta(RespuestaModel respuesta) throws DAOException;
	
	/**
	 * Ejecuta procedimiento alamacenado para actualizar el tipo de caso de oficio a Positivo o Negativo
	 * @param numOficio
	 * @param tipoOficio
	 * @return
	 * @throws DAOException
	 */
	public int marcarTipoOficio(String numOficio, String tipoOficio)throws DAOException;
	
	/**
	 * Busca las Respuestas relacionadas a un Oficio y su Tipo de Caso.
	 * @param numOficio
	 * @param tipoOficio
	 * @param tipoCaso
	 * @return List<RespuestaModel>
	 * @throws DAOException
	 */
	public List<RespuestaModel> buscarRespuestasPorCaso(String numOficio,
			String tipoOficio, String tipoCaso) throws DAOException;

}
