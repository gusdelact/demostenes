package com.gfi.bin.admctasweb.catalogos.service;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;

public interface EstatusSeguimientoService {

	/**
	 * Obtiene un item del catálogo de seguimiento por clave
	 * @param clave
	 * @return
	 * @throws ServiceException
	 */
	ItemModel obtenerEstatusPorClave(String clave)throws ServiceException;
	
	
	
	
}
