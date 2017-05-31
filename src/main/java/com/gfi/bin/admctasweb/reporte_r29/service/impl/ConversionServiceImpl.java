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

import com.gfi.bin.admctasweb.reporte_r29.dao.ConversionDao;
import com.gfi.bin.admctasweb.reporte_r29.model.ConversionModel;
import com.gfi.bin.admctasweb.reporte_r29.service.ConversionService;
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
@Service(value = "ConversionService")
public class ConversionServiceImpl implements ConversionService {
	
	@Autowired
	private ConversionDao conversionDao;
	
	@Autowired
	private ConfigParams configParams;
	
	@Autowired
	private JasperService jasperService;
	
	@Autowired
	private RespuestaOficioDAO respuestaOficioDAO;

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public String guardarConversion(ConversionModel conversion) throws ServiceException {
		String result = "";
		
		try{
			result = conversionDao.guardarConversion(conversion);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean modificarConversion(ConversionModel conversion) throws ServiceException {
		Boolean result = false;
		
		try{
			result = this.conversionDao.modificarConversion(conversion);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean eliminarConversion(ConversionModel conversion) throws ServiceException {
		Boolean result = false;
		
		try{
			result = conversionDao.eliminarConversion(conversion);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public ConversionModel buscarConversionPorLlave( int idCatalogo ) throws ServiceException {
		ConversionModel conversion = new ConversionModel();
		
		try{
			conversion = conversionDao.buscarConversionPorLlave( idCatalogo );
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return conversion;
	}

	public int buscarRegistroConversion( int idCatalogo ) throws ServiceException {
		ConversionModel conversion = new ConversionModel();
		int registro=0;
		try{
			registro = conversionDao.buscarRegistroConversion( idCatalogo );
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return registro;
	}
}
