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

import com.gfi.bin.admctasweb.reporte_r29.dao.MapeoDao;
import com.gfi.bin.admctasweb.reporte_r29.model.MapeoModel;
import com.gfi.bin.admctasweb.reporte_r29.service.MapeoService;
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
@Service(value = "MaspeoService")
public class MapeoServiceImpl implements MapeoService {
	
	@Autowired
	private MapeoDao mapeoDao;
	
	@Autowired
	private ConfigParams configParams;
	
	@Autowired
	private JasperService jasperService;
	
	@Autowired
	private RespuestaOficioDAO respuestaOficioDAO;

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public String guardarMapeo(MapeoModel mapeo) throws ServiceException {
		String result = "";
		
		try{
			result = mapeoDao.guardarMapeo(mapeo);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean modificarMapeo(MapeoModel mapeo) throws ServiceException {
		Boolean result = false;
		
		try{
			result = this.mapeoDao.modificarMapeo(mapeo);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean eliminarMapeo(MapeoModel mapeo) throws ServiceException {
		Boolean result = false;
		
		try{
			result = mapeoDao.eliminarMapeo(mapeo);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public MapeoModel buscarMapeoPorLlave( int  idCatalogo, String cveCorporativa ) throws ServiceException {
		MapeoModel mapeo = new MapeoModel();
		
		try{           
			mapeo = mapeoDao.buscarMapeoPorLlave( idCatalogo , cveCorporativa );
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return mapeo;
	}

}
