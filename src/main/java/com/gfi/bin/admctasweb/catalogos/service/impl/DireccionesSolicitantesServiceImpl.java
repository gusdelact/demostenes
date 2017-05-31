package com.gfi.bin.admctasweb.catalogos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.dao.DireccionesSolicitantesDao;
import com.gfi.bin.admctasweb.catalogos.model.DireccionesSolicitantesModel;
import com.gfi.bin.admctasweb.catalogos.service.DireccionesSolicitantesService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */

@Service
public class DireccionesSolicitantesServiceImpl implements DireccionesSolicitantesService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DireccionesSolicitantesServiceImpl.class);

	@Autowired
	private DireccionesSolicitantesDao direccionesDAO;
	
	/**
	 * Insertar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public DireccionesSolicitantesModel insertarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws ServiceException {
		
		try {
			return this.direccionesDAO.insertarDireccionSolicitante(dsModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		
	}
	
	/**
	 * Actualizar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean actualizarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws ServiceException {
		boolean resultado = false;
		try {
			resultado = this.direccionesDAO.actualizarDireccionSolicitante(dsModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return resultado;
	}

	/**
	 * Eliminar Direcciones Solicitantes
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean eliminarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws ServiceException {
		boolean resultado = false;
		try {
			resultado = this.direccionesDAO.eliminarDireccionSolicitante(dsModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return resultado;
	}

	/**
	 * Consultar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return List<DireccionesSolicitantesModel>
	 * @throws ServiceException
	 */	
	public List<DireccionesSolicitantesModel> consultarDireccionesSolicitantes(DireccionesSolicitantesModel dsModel) throws ServiceException {
		List<DireccionesSolicitantesModel> dsModelList = null;
		try {
			dsModelList = this.direccionesDAO.consultarDireccionesSolicitantes(dsModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return dsModelList;
	}

	/**
	 * Consultar Direcci&oacute;n Solicitante por ID
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return DireccionesSolicitantesModel
	 * @throws ServiceException
	 */	
	public DireccionesSolicitantesModel consultarDireccionSolicitantePorID(DireccionesSolicitantesModel dsModel) throws ServiceException {
		try {
			dsModel = this.direccionesDAO.consultarDireccionSolicitantePorID(dsModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return dsModel;
	}

	/**
	 * Existe en el Cat&aacute;logo de Direcciones Solicitantes 
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean existeDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws ServiceException {
		boolean existe = false;
		try {
			existe = this.direccionesDAO.existeDireccionSolicitante(dsModel);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return existe;
	}

	/**
	 * Obtiene direcciones por tipo de oficio
	 * @param tipoOficio
	 * @return
	 * @throws ServiceException
	 */	
	@Override
	public List<ItemModel> obtenerDireccionesPorTipoOficio(String tipoOficio) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return direccionesDAO.obtenerDireccionesPorTipoOficio(tipoOficio);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
}
