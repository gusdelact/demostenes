package com.gfi.bin.admctasweb.catalogos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.catalogos.dao.EstatusSeguimientoDAO;
import com.gfi.bin.admctasweb.catalogos.service.EstatusSeguimientoService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;

@Service
public class EstatusSeguimientoServiceImpl implements EstatusSeguimientoService {

	@Autowired
	EstatusSeguimientoDAO estatusSeguimientoDAO;
	
	/**
	 * Obtiene un item del catálogo de seguimiento por clave
	 * @param clave
	 * @return
	 * @throws ServiceException
	 */	
	@Override
	public ItemModel obtenerEstatusPorClave(String clave) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return estatusSeguimientoDAO.obtenerEstatusPorClave(clave);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

}
