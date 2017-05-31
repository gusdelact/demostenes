package com.gfi.bin.admctasweb.operativos.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;
import com.gfi.bin.admctasweb.operativos.model.RespuestaOficioModel;

/**
 * Servicio para respuestas de oficios positivos
 * @author ESS3VAVC
 *
 */
public interface RespuestaOficioService {

	/**
	 * Servicio para guardar respuesta de un oficio
	 * @param respuestaOficioModel
	 * @return
	 * @throws ServiceException
	 */
	RespuestaOficioModel guardarRespuestaOficio(RespuestaOficioModel respuestaOficioModel)throws ServiceException;
	
	/**
	 * Modifica la respuesta de un Oficio
	 * @param respuestaOficioModel
	 * @return
	 * @throws ServiceException
	 */
	boolean modificarRespuestaOficio(RespuestaOficioModel respuestaOficioModel)throws ServiceException;
	
	/**
	 * Obtiene la respuesta de Oficio por Id
	 * @param respuestaOficioModel
	 * @return
	 * @throws ServiceException
	 */
	RespuestaOficioModel obtenerRespuestaOficioPorId(RespuestaOficioModel respuestaOficioModel)throws ServiceException;
	
	/**
	 * Obtiene los requerimientos por Oficio
	 * @param tipoOficio
	 * @return
	 * @throws ServiceException
	 */
	List<ItemModel> obtenerRequerimientos(String tipoOficio)throws ServiceException;
	
	/**
	 * Obtiene tipo de Solicitudes de un Requerimiento
	 * @param tipoOficio
	 * @param idRequerimiento
	 * @return
	 * @throws ServiceException
	 */
	List<ItemModel> obtenerSolicitudes(String tipoOficio, String idRequerimiento)throws ServiceException;

	/**
	 * Actualiza la situación del oficio a enviado
	 * Se actualiza la tabla de respuesta de Oficio y tabla de Oficio
	 * @return
	 * @throws ServiceException
	 */
	boolean actualizarOficioEnviado(RespuestaOficioModel respuestaOficioModel) throws ServiceException;
	
	/**
	 * Genera reporte de respuesta de un oficio
	 * @param respuestaOficioModel
	 * @return
	 * @throws ServiceException
	 */
	byte[] generarReporte(String numOficio, String tipoOficio, Integer idRespuesta)throws ServiceException;
	
	/**
	 * Obtiene catalogo de Direcciones por tipo de Oficio
	 * @param tipoOficio
	 * @return
	 * @throws ServiceException
	 */
	List<ItemModel> obtenerDireccionesPorTipoOficio(String tipoOficio) throws ServiceException;
	
	/**
	 * Se encarga de generar cadena de respuesta con datos del oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @return
	 * @throws ServiceException
	 */
	String armarCadenaDatosOficio(RespuestaOficioModel respuestaOficioModel) throws ServiceException;
	
}
