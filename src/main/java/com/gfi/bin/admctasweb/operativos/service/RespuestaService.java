/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.RespuestaModel;

/**
 * @author lugl4884
 * Servicios para la Generación de Respuestas CNBV
 *
 */
public interface RespuestaService {
	
	/**
	 * Servicio que genera un registro de Respuesta.
	 * @param respuesta
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean guardarRespuesta(RespuestaModel respuesta) throws ServiceException;
	
	/**
	 * Servicio que Elimina un registro de Respuesta.
	 * @param respuesta
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean eliminarRespuesta(RespuestaModel respuesta) throws ServiceException;
	
	/**
	 * Servicio que Busca las Respuestas relacionadas a un Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<RespuestaModel>
	 * @throws ServiceException
	 */
	List<RespuestaModel> buscarRespuestasPorOficio(String numOficio,
			String tipoOficio) throws ServiceException;
	
	/**
	 * Servicio que valida la existencia de un registro de Respuesta.
	 * @param respuesta
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean existeRespuesta(RespuestaModel respuesta) throws ServiceException; 
	
	/**
	 * Servicio que Arma el objeto de Respuesta para cada tipo (Positiva,Negativa,Cliente).
	 * @param respuesta
	 * @return RespuestaModel
	 * @throws ServiceException
	 */
	RespuestaModel generaObjetoRespuesta(RespuestaModel respuesta, String tipoResp) throws Exception; 

}
