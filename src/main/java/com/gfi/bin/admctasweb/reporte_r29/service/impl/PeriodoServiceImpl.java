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

import com.gfi.bin.admctasweb.reporte_r29.dao.PeriodoDao;
import com.gfi.bin.admctasweb.reporte_r29.model.PeriodoModel;
import com.gfi.bin.admctasweb.reporte_r29.service.PeriodoService;
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
@Service(value = "PeriodoService")
public class PeriodoServiceImpl implements PeriodoService {
	
	@Autowired
	private PeriodoDao periodoDao;
	
	@Autowired
	private ConfigParams configParams;
	
	@Autowired
	private JasperService jasperService;
	
	@Autowired
	private RespuestaOficioDAO respuestaOficioDAO;

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public String guardarPeriodo(PeriodoModel periodo) throws ServiceException {
		String result = "";
		
		try{
			result = periodoDao.guardarPeriodo(periodo);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean modificarPeriodo(PeriodoModel periodo) throws ServiceException {
		Boolean result = false;
		
		try{
			result = this.periodoDao.modificarPeriodo(periodo);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean eliminarPeriodo(PeriodoModel periodo) throws ServiceException {
		Boolean result = false;
		
		try{
			result = periodoDao.eliminarPeriodo(periodo);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public PeriodoModel buscarPeriodoPorLlave( String  idEmpresa, int cvePeriodo ) throws ServiceException {
		PeriodoModel periodo = new PeriodoModel();
		
		try{
			periodo = periodoDao.buscarPeriodoPorLlave( idEmpresa , cvePeriodo );
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return periodo;
	}


}
