/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.ImpresionDictamenJurModel;

/**
 * Servicios para la Impresión de Dictamen Jurídico.
 * @author LUGL4884
 *
 */
public interface ImpresionDictamenJurService {
	
	/**
	 * Servicio que Busca las Respuestas relacionadas a un Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<ImpresionDictamenJurModel>
	 * @throws ServiceException
	 */
	List<ImpresionDictamenJurModel> buscarDictamenJur(String numOficio, String tipoOficio) throws ServiceException; 
	
	/**
	 * Servicio que valida que se hayan creado Todos los dictamenes Jur. del Oficio y los marca como Impresos.
	 * @param numOficio
	 * @param tipoOficio
	 * @param numConsec
	 * @param usuario
	 * @return int
	 * @throws ServiceException
	 */
	int marcarDictamenJur(String numOficio, String tipoOficio, Long numConsec, String usuario) throws ServiceException;
	
	/**
	 * Servicio que regresa el número de Dictamenes Jurídicos por Oficio que no se han Impreso.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int buscarDictamenSinImp(String numOficio, String tipoOficio) throws ServiceException;
	
	/**
	 * Servicio que envia una Notificación por correo cuando se realiza la Impresión de Todos los Dictamenes del Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @param operacion
	 * @throws ServiceException 
	 */
	void enviarEmailSeguimientoImpDic(String numOficio, String tipoOficio, String operacion) throws ServiceException;

}
