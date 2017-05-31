package com.gfi.bin.admctasweb.operativos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.dao.ExcluNomContratoDAO;
import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;
import com.gfi.bin.admctasweb.operativos.service.ExcluNomContratoService;

@Service
public class ExcluNomContratoServiceImpl implements ExcluNomContratoService {
	
	private final Logger logger = LoggerFactory.getLogger(ExcluNomContratoServiceImpl.class);
	
	@Autowired
	private ExcluNomContratoDAO excluNomContratoDAO;
	
	@Override
	public List<ExclusionModel> obtenerExclusiones(Boolean estatus) throws ServiceException {
		logger.debug("obtenerExclusiones; estatus: "+estatus);
		try{
			return excluNomContratoDAO.obtenerExclusiones(estatus);
		}catch(DAOException daoException){
			logger.error("Error al obtenerExclusiones: "+daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}

	@Override
	public List<ExclusionModel> obtenerExclusiones() throws ServiceException {
		logger.debug("obtenerExclusiones");
		try{
			return excluNomContratoDAO.obtenerExclusiones();
		}catch(DAOException daoException){
			logger.error("Error al obtenerExclusiones: "+daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}

	@Override
	public List<ExclusionModel> obtenerExclusionPorNombre(String nombreExclusion) throws ServiceException {
		logger.debug("obtenerExclusionPorNombre; nombreExclusion: "+nombreExclusion);
		try{
			return excluNomContratoDAO.obtenerExclusionPorNombre(nombreExclusion);
		}catch(DAOException daoException){
			logger.error("Error al obtenerExclusionPorNombre: "+daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}

	@Override
	public ExclusionModel obtenerExclusionPorId(Integer idExclusion) throws ServiceException {
		logger.debug("obtenerExclusionPorId; idExclusion: "+idExclusion);
		try{
			return excluNomContratoDAO.obtenerExclusionPorId(idExclusion);
		}catch(DAOException daoException){
			logger.error("Error al obtenerExclusionPorId: "+daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}

	@Override
	public Boolean insertarExclusion(ExclusionModel exclusionModel) throws ServiceException {
		logger.debug("insertarExclusion; exclusionModel: "+exclusionModel);
		try{
			Integer idSecuencia = excluNomContratoDAO.obtenerSiguienteValorSecuencia();
			
			exclusionModel.setIdExclusion(idSecuencia);
			
			return excluNomContratoDAO.insertarExclusion(exclusionModel);
		}catch(DAOException daoException){
			logger.error("Error al insertarExclusion: "+daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}

	@Override
	public Boolean actualizarExclusion(ExclusionModel exclusionModel) throws ServiceException {
		logger.debug("actualizarExclusion; exclusionModel: "+exclusionModel);
		try{
			return excluNomContratoDAO.actualizarExclusion(exclusionModel);
		}catch(DAOException daoException){
			logger.error("Error al actualizarExclusion: "+daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}
	
	@Override
	public Boolean eliminarExclusion(Integer idExclusion) throws ServiceException{
		logger.debug("eliminarExclusion; idExclusion: "+idExclusion);
		try{
			return excluNomContratoDAO.eliminarExclusion(idExclusion);
		}catch(DAOException daoException){
			logger.error("Error al eliminarExclusion: "+daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}
	
	@Override
	public Integer obtenerSiguienteValorSecuencia() throws ServiceException {
		logger.debug("obtenerSiguienteValorSecuencia");
		try{
			return excluNomContratoDAO.obtenerSiguienteValorSecuencia();
		}catch(DAOException daoException){
			logger.error("Error al obtenerSiguienteValorSecuencia: "+daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}
}