package com.gfi.bin.admctasweb.catalogos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.dao.AutoridadesDao;
import com.gfi.bin.admctasweb.catalogos.model.AutoridadesModel;
import com.gfi.bin.admctasweb.catalogos.service.AutoridadesService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */

@Service
public class AutoridadesServiceImpl implements AutoridadesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutoridadesServiceImpl.class);

	@Autowired
	private AutoridadesDao autoridadesDAO;
	
	/**
	 * Insertar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean insertarAutoridad(AutoridadesModel autoridadesModel) throws ServiceException {
		boolean resultado = false;
		try {
			resultado = this.autoridadesDAO.insertarAutoridad(autoridadesModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return resultado;
	}
	
	/**
	 * Actualizar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean actualizarAutoridad(AutoridadesModel autoridadesModel) throws ServiceException {
		boolean resultado = false;
		try {
			resultado = this.autoridadesDAO.actualizarAutoridad(autoridadesModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return resultado;
	}

	/**
	 * Eliminar Autoridades
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean eliminarAutoridad(AutoridadesModel autoridadesModel) throws ServiceException {
		boolean resultado = false;
		try {
			resultado = this.autoridadesDAO.eliminarAutoridad(autoridadesModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return resultado;
	}

	/**
	 * Consultar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return List<AutoridadesModel>
	 * @throws ServiceException
	 */	
	public List<AutoridadesModel> consultarAutoridades(AutoridadesModel autoridadesModel) throws ServiceException {
		List<AutoridadesModel> autoridadesModelList = null;
		try {
			autoridadesModelList = this.autoridadesDAO.consultarAutoridades(autoridadesModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return autoridadesModelList;
	}

	/**
	 * Consultar Autoridades por Clave
	 * 
	 * @param AutoridadesModel
	 * @return AutoridadesModel
	 * @throws ServiceException
	 */	
	public AutoridadesModel consultarAutoridadPorClave(AutoridadesModel autoridadesModel) throws ServiceException {
		try {
			autoridadesModel = this.autoridadesDAO.consultarAutoridadPorClave(autoridadesModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return autoridadesModel;
	}

	/**
	 * Existe en el Cat&aacute;logo de Autoridades 
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	public boolean existeAutoridad(AutoridadesModel autoridadesModel) throws ServiceException {
		boolean existe = false;
		try {
			existe = this.autoridadesDAO.existeAutoridad(autoridadesModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return existe;		
	}

}
