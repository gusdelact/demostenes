package com.gfi.bin.admctasweb.reportes.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.catalogos.service.OficioService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.ConfigParams;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.jasper.service.JasperService;
import com.gfi.bin.admctasweb.reportes.dao.BitacoraSeguimientoDAO;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoListModel;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.model.OficioSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;

/**
 * Implementación de los Servicios de la Bitácora de Seguimiento.
 * @author LUGL4884
 */

@Service
public class BitacoraSeguimientoServiceImpl implements BitacoraSeguimientoService {
	private static final Logger log = LoggerFactory.getLogger(BitacoraSeguimientoServiceImpl.class);
	
	@Autowired
	private BitacoraSeguimientoDAO bitacoraSeguimientoDAO;
	
	@Autowired
	private OficioService oficioService;
	
	@Autowired
	JasperService jasperService;
	
	@Autowired
	private ConfigParams configParams;
	
	
	public void guardar(BitacoraSeguimientoModel seguimiento) throws ServiceException {
		try{
			log.debug("Inicia guardar bitacora seguimiento...");
			String tipoCasoResp = "";
			String cveEstatusPend = "";
			
			seguimiento.setCveUsuario(Util.usuarioSesion());
			seguimiento.setFhRegistro(new Timestamp(new Date().getTime()));
			
			//Consultamos el estatus pendiente.
			if (seguimiento.getTipoCaso() != null){
				tipoCasoResp = seguimiento.getTipoCaso();
			}
			cveEstatusPend = Util.devuelveEstatusPend(seguimiento.getCveEstatus(), tipoCasoResp);
			
			// Cambiamos estatus en el Oficio.
			this.oficioService.modificarSeguimientoOficio(
					seguimiento.getNumOficio(), seguimiento.getTipoOficio(),
					seguimiento.getCveEstatus(), cveEstatusPend);
			
			//Insertamos Estatus Actual en la Bitacora de Seguimiento.
			this.bitacoraSeguimientoDAO.guardar(seguimiento); 
			
			//Si el seguimiento NO es de Registro de Información Pendiente(REGOFIP) guardamos el estatus pendiente.
			if(!seguimiento.getCveEstatus().equals(Constantes.REGISTRO_OFICIO_INFO_PEND)) {
				// Seteamos el Estatus Pendiente de seguimiento.
				seguimiento.setCveEstatus(cveEstatusPend);
				
				//Insertamos el Estatus Pendiende en la Bitacora de Seguimiento.
				this.bitacoraSeguimientoDAO.guardar(seguimiento);
			}
			
			log.debug("Termina guardar bitacora seguimiento...");
		}catch(DAOException daoException){
			log.error(daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}

	
	public List<OficioSeguimientoModel> buscarOficiosSeguimiento(BitacoraSeguimientoListModel bitacora) throws ServiceException {
		List<OficioSeguimientoModel> oficioList = null;
		try {
			oficioList = this.bitacoraSeguimientoDAO.buscarOficiosSeguimiento(bitacora);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return oficioList;
	}

	public List<BitacoraSeguimientoModel> buscarSeguimientoPorOficio(
			String numOficio, String tipoOficio) throws ServiceException {
		List<BitacoraSeguimientoModel> listSeguimiento = new ArrayList<BitacoraSeguimientoModel>();
		
		try {
			listSeguimiento = this.bitacoraSeguimientoDAO.buscarSeguimientoPorOficio(numOficio, tipoOficio);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return listSeguimiento;
	}

	public byte[] generarReporteSeguimiento(BitacoraSeguimientoListModel bitacora, String logoPath) throws ServiceException {
		List<OficioSeguimientoModel> oficioList = null;
		String plantilla = "bitacoraSeguimiento";	
		//String pathImg = "";
		
		try {
			//Consultamos los oficios de acuerdo a los filtros de busqueda.
			oficioList = this.bitacoraSeguimientoDAO.buscarOficiosSeguimiento(bitacora);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}	
		
		//Parametros del reporte.
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("usuario", Util.usuarioSesion());
		parametros.put("logo", logoPath);
		
		//Llamado al metodo que genera el reporte.
		return jasperService.generarReporteBean(configParams.getRutaPlantillas() + plantilla, 
										FormatoReporte.PDF,
										oficioList,
										parametros);
	}
}