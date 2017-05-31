/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.ConfigParams;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.jasper.service.JasperService;
import com.gfi.bin.admctasweb.reportes.dao.BitacoraExcepcionDAO;
import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionListModel;
import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraExcepcionService;

/**
 * @author MUDF4038 - Munive Dorantes Fernando
 *
 */
@Service
public class BitacoraExcepcionServiceImpl implements BitacoraExcepcionService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraExcepcionServiceImpl.class);
	
	@Autowired
	private BitacoraExcepcionDAO bitExcepcionDAO;
	@Autowired
	private ConfigParams configParams;
	@Autowired
	private BasicDataSource corpDS;
	@Autowired
	private JasperService jasperService;
	

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.reportes.service.BitacoraExcepcionService#consultarBitacoraExcepcion(com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionListModel)
	 */
	public List<BitacoraExcepcionModel> consultarBitacoraExcepcion(BitacoraExcepcionListModel parametros) throws ServiceException {
		
		List<BitacoraExcepcionModel> bitExcepcionList = null;
		try {
			bitExcepcionList = this.bitExcepcionDAO.consultarBitacoraExcepcion(parametros);
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return bitExcepcionList;
	}

	public byte[] generarReporte(String where, String nombrePlantilla, String pathLogo, String usuarioSesion) throws ServiceException {
		byte[] reporte = null;
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("paramUsuarioSesion", usuarioSesion);
		parametros.put("paramWhere", where);
		parametros.put("paramLogoPath", pathLogo);
		
		try {
			reporte = jasperService.generarReporteSqlJasper(configParams.getRutaPlantillas() + nombrePlantilla, 
															FormatoReporte.PDF, 
															parametros, 
															corpDS.getConnection());
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return reporte;
	}	
	
	@Override
	public byte[] generarReporteBean(List<BitacoraExcepcionModel> lista, String nombrePlantilla, String pathLogo, String usuarioSesion) throws ServiceException {
		byte[] reporte = null;
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("paramUsuarioSesion", usuarioSesion);
		parametros.put("paramLogoPath", pathLogo);
		
		reporte = jasperService.generarReporteBean(configParams.getRutaPlantillas() + nombrePlantilla, FormatoReporte.PDF, lista, parametros);
		return reporte;
	}	
}
