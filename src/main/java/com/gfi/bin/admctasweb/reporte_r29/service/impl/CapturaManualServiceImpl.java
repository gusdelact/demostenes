/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.reporte_r29.dao.CapturaManualDao;
import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualContratoModel;
import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualModel;
import com.gfi.bin.admctasweb.reporte_r29.service.CapturaManualService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.ConfigParams;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.jasper.service.JasperService;
import com.gfi.bin.admctasweb.operativos.dao.RespuestaOficioDAO;
import com.gfi.bin.admctasweb.operativos.model.RespuestaOficioModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;

/**
 * @author LUGL4884
 *
 */
@Component
@Service(value = "CapturaManualService")
public class CapturaManualServiceImpl implements CapturaManualService {
	
	@Autowired
	private CapturaManualDao capturaManualDao;
	
	@Autowired
	private ConfigParams configParams;
	
	@Autowired
	private JasperService jasperService;
	
	@Autowired
	private RespuestaOficioDAO respuestaOficioDAO;

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean guardarCapturaManual(CapturaManualModel capturaManual) throws ServiceException {
		Boolean result = false;
		
		try{
			result = capturaManualDao.guardarCapturaManual(capturaManual);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean modificarCapturaManual(CapturaManualModel capturaManual) throws ServiceException {
		Boolean result = false;
		
		try{
			result = this.capturaManualDao.modificarCapturaManual(capturaManual);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean eliminarCapturaManual(CapturaManualModel capturaManual) throws ServiceException {
		Boolean result = false;
		
		try{
			result = capturaManualDao.eliminarCapturaManual(capturaManual);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public CapturaManualModel buscarCapturaManualPorLlave(String numOficio, String tipoOficio, long idCotitular, 
            String idEmpresa, long cvePeriodo, int numIntento ) throws ServiceException {
		CapturaManualModel oficio = new CapturaManualModel();
		
		try{
			oficio = capturaManualDao.buscarCapturaManualPorLlave(numOficio, tipoOficio, idCotitular, idEmpresa, cvePeriodo, numIntento );
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return oficio;
	}

	public CapturaManualModel buscarCapturaManualContrato(String idCuenta, String idEmpresa) throws ServiceException {

		CapturaManualModel oficio = new CapturaManualModel();
		
		try{
			oficio = capturaManualDao.buscarCapturaManualContrato(idCuenta, idEmpresa);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return oficio;
	}


}
