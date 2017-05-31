package com.gfi.bin.admctasweb.procesoautomatico.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface NotificacionProcesoAutomaticoService {
	
	/**
	 * Envía correo a usuarios configurados notificando el resumen del proceso automático
	 * 
	 * @param listaBitacoraCargaAutomatica
	 * @throws ServiceException
	 */
	void enviarNotificacion(List<BitacoraCargaAutomaticaModel> listaBitacoraCargaAutomatica) throws ServiceException;
}