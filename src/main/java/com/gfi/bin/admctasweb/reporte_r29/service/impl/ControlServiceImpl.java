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

import com.gfi.bin.admctasweb.reporte_r29.dao.ControlDao;
import com.gfi.bin.admctasweb.reporte_r29.model.ControlModel;
import com.gfi.bin.admctasweb.reporte_r29.service.ControlService;
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
@Service(value = "ControlService")
public class ControlServiceImpl implements ControlService {
	
	@Autowired
	private ControlDao controlDao;
	
	@Autowired
	private ConfigParams configParams;
	
	@Autowired
	private JasperService jasperService;
	
	@Autowired
	private RespuestaOficioDAO respuestaOficioDAO;

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public String guardarControl(ControlModel control) throws ServiceException {
		String result ="";
		
		try{
			result = controlDao.guardarControl(control);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean modificarControl(ControlModel control) throws ServiceException {
		Boolean result = false;
		
		try{
			result = this.controlDao.modificarControl(control);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean eliminarControl(ControlModel control) throws ServiceException {
		Boolean result = false;
		
		try{
			result = controlDao.eliminarControl(control);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public ControlModel buscarControlPorLlave( String  idEmpresaCx, int cvePeriodoCx, int numIntentoCx) throws ServiceException {
		ControlModel control = new ControlModel();
		
		try{
			control = controlDao.buscarControlPorLlave( idEmpresaCx , cvePeriodoCx, numIntentoCx);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return control;
	}


}
