package com.gfi.bin.admctasweb.catalogos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.dao.ParametrosDao;
import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.catalogos.service.ParametrosService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * Servicio para administración de Parametros del sistema
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Service
public class ParametrosServiceImpl implements ParametrosService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParametrosServiceImpl.class);

	@Autowired
	private ParametrosDao parametrosDAO;
	
	/**
	 * Insertar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean insertarParametro(ParametrosModel parametrosModel) throws ServiceException {
		boolean resultado = false;
		try {
			resultado = this.parametrosDAO.insertarParametro(parametrosModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return resultado;
	}
	
	/**
	 * Actualizar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean actualizarParametro(ParametrosModel parametrosModel) throws ServiceException {
		boolean resultado = false;
		try {
			resultado = this.parametrosDAO.actualizarParametro(parametrosModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return resultado;
	}

	/**
	 * Eliminar Par&aacute;metros de Oficios CNBV
	 * @param ParametrosModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean eliminarParametro(ParametrosModel parametrosModel) throws ServiceException {
		boolean resultado = false;
		try {
			resultado = this.parametrosDAO.eliminarParametro(parametrosModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return resultado;
	}

	/**
	 * Consultar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return List<ParametrosModel>
	 * @throws ServiceException
	 */	
	public List<ParametrosModel> consultarParametros(ParametrosModel parametrosModel) throws ServiceException {
		List<ParametrosModel> parametrosModelList = null;
		try {
			parametrosModelList = this.parametrosDAO.consultarParametros(parametrosModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return parametrosModelList;
	}

	/**
	 * Consultar Par&aacute;metros de Oficios CNBV por Clave
	 * 
	 * @param ParametrosModel
	 * @return ParametrosModel
	 * @throws ServiceException
	 */	
	public ParametrosModel consultarParametroPorClave(ParametrosModel parametrosModel) throws ServiceException {
		try {
			parametrosModel = this.parametrosDAO.consultarParametroPorClave(parametrosModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return parametrosModel;
	}
	
	/**
	 * Existe en el Cat&aacute;logo de Par&aacute;metros de Oficios CNBV 
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	public boolean existeParametro(ParametrosModel parametrosModel) throws ServiceException {
		boolean existe = false;
		try {
			existe = this.parametrosDAO.existeParametro(parametrosModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return existe;		
	}
	
}
