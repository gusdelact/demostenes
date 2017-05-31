package com.gfi.bin.admctasweb.operativos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;
import com.gfi.bin.admctasweb.operativos.model.RespuestaOficioModel;

/**
 * Dao para Respuestas de Oficio
 * @author ESS3VAVC
 *
 */
public interface RespuestaOficioDAO {

	/**
	 * Guarda respuesta de un oficio en BD
	 * @param respuestaOficioModel
	 * @return
	 * @throws DAOException
	 */
	RespuestaOficioModel guardarRespuestaOficio(RespuestaOficioModel respuestaOficioModel)throws DAOException;
	
	/**
	 * Modifica la respuesta de un Oficio
	 * @param respuestaOficioModel
	 * @return
	 * @throws DAOException
	 */
	boolean modificarRespuestaOficio(RespuestaOficioModel respuestaOficioModel)throws DAOException;
	
	/**
	 * Obtiene la respuesta de Oficio por Id
	 * @param respuestaOficioModel
	 * @return
	 * @throws DAOException
	 */
	RespuestaOficioModel obtenerRespuestaOficioPorId(RespuestaOficioModel respuestaOficioModel)throws DAOException;
	
	/**
	 * Obtiene catálogo de Requerimientos
	 * @param tipoOficio
	 * @return
	 * @throws DAOException
	 */
	List<ItemModel> obtenerRequerimientos(String tipoOficio)throws DAOException;
	
	/**
	 * 
	 * @param tipoOficio
	 * @return
	 * @throws DAOException
	 */
	List<ItemModel> obtenerSolicitudes(String tipoOficio, String idRequerimiento)throws DAOException;
	
	/**
	 * Obtiene el item o registro de un requerimiento
	 * @param tipoOficio
	 * @param idRequerimiento
	 * @return
	 * @throws DAOException
	 */
	ItemModel obtenerRequerimientoPorId(String tipoOficio, String idRequerimiento)throws DAOException;

	/**
	 * Obtiene las respuestas para el reporte de casos especiales
	 * @param numOficio
	 * @param tipoOficio
	 * @return
	 */
	List<RespuestaOficioModel> buscarRespuestasParaOficio(String numOficio,
			String tipoOficio);
}
