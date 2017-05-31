package com.gfi.bin.admctasweb.procesoautomatico.service;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface ValidacionCargaAutomatica {
	
	/**
	 * Valida que existan las condiciones necesarias para ejecutar sin problemas la carga automática, 
	 * se valida que exista la plantilla para generar el resumen (archivo xls), si no existe, se envía
	 * mensaje de correo a un grupo de usuarios para informar del problema
	 * 
	 * @return Boolean
	 * @throws ServiceException
	 */
	Boolean validarPrecondiciones() throws ServiceException;
}