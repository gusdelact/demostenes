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

import com.gfi.bin.admctasweb.reporte_r29.dao.SucursalDao;
import com.gfi.bin.admctasweb.reporte_r29.model.SucursalModel;
import com.gfi.bin.admctasweb.reporte_r29.service.SucursalService;
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
@Service(value = "SucursalService")
public class SucursalServiceImpl implements SucursalService {
	
	@Autowired
	private SucursalDao sucursalDao;
	
	@Autowired
	private ConfigParams configParams;
	
	@Autowired
	private JasperService jasperService;
	
	@Autowired
	private RespuestaOficioDAO respuestaOficioDAO;

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public String guardarSucursal(SucursalModel sucursal) throws ServiceException {
		String result = "";
		
		try{
			result = sucursalDao.guardarSucursal(sucursal);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean modificarSucursal(SucursalModel sucursal) throws ServiceException {
		Boolean result = false;
		
		try{
			result = this.sucursalDao.modificarSucursal(sucursal);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean eliminarSucursal(SucursalModel sucursal) throws ServiceException {
		Boolean result = false;
		
		try{
			result = sucursalDao.eliminarSucursal(sucursal);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public SucursalModel buscarSucursalPorLlave( String idEmpresa, String idSucursal ) throws ServiceException {
		SucursalModel sucursal = new SucursalModel();
		
		try{
			sucursal = sucursalDao.buscarSucursalPorLlave( idEmpresa, idSucursal );
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return sucursal;
	}

}
