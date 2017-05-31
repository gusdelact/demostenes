/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.dao.OficioDao;
import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.service.OficioService;
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
@Service(value = "OficioService")
public class OficioServiceImpl implements OficioService {
	
	@Autowired
	private OficioDao oficioDao;
	
	@Autowired
	private ConfigParams configParams;
	
	@Autowired
	private JasperService jasperService;
	
	@Autowired
	private RespuestaOficioDAO respuestaOficioDAO;

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean guardarOficio(OficioModel oficio) throws ServiceException {
		Boolean result = false;
		
		try{
			result = oficioDao.guardarOficio(oficio);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean modificarOficio(OficioModel oficio) throws ServiceException {
		Boolean result = false;
		
		try{
			result = this.oficioDao.modificarOficio(oficio);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean eliminarOficio(OficioModel oficio) throws ServiceException {
		Boolean result = false;
		
		try{
			result = oficioDao.eliminarOficio(oficio);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public OficioModel buscarOficioPorLlave(String numOficio, String tipoOficio) throws ServiceException {
		OficioModel oficio = new OficioModel();
		
		try{
			oficio = oficioDao.buscarOficioPorLlave(numOficio, tipoOficio);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return oficio;
	}
	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = { ServiceException.class })
	public boolean modificarSeguimientoOficio(String numOficio,
			String tipoOficio, String cveEstatus, String cveEstatusPend) throws ServiceException {
		Boolean result = false;
		
		try{
			result = oficioDao.modificarSeguimientoOficio(numOficio, tipoOficio, cveEstatus, cveEstatusPend);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	public List<CasosEspecialesModel> buscarOficiosCasosEspeciales (CasosEspecialesListModel parametros) throws ServiceException {
		List<CasosEspecialesModel> casosEspecialesList = null;
		try {
			casosEspecialesList = this.oficioDao.buscarOficiosCasosEspeciales(parametros);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return casosEspecialesList;
	}

	/**
	 * Actualiza la situación de un oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @param situacion
	 * @return
	 * @throws ServiceException
	 */	
	@Override
	public boolean modificarSituacionOficio(String numOficio, String tipoOficio, String situacion) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return oficioDao.modificarSituacionOficio(numOficio, tipoOficio, situacion);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public byte[] generarReporteCasosEspeciales(CasosEspecialesListModel parametros, String nombrePlantilla, String pathLogo, String usuarioSesion,FormatoReporte formato) throws ServiceException {
		byte[] reporte = null;
		
		HashMap<String, Object> mapaParametros = new HashMap<String, Object>();
		mapaParametros.put("paramUsuarioSesion", usuarioSesion);
		mapaParametros.put("paramLogoPath", pathLogo);
		
		List<CasosEspecialesModel> oficios = this.buscarOficiosCasosEspeciales(parametros);
		for (CasosEspecialesModel casosEspecialesModel : oficios) {
			casosEspecialesModel.setRespuestas(obtenerRespuestas(casosEspecialesModel.getNumOficio(),
					casosEspecialesModel.getTipoOficio()));
		}
		
		reporte = jasperService.generarReporteBean(configParams.getRutaPlantillas() + nombrePlantilla, 
										 formato, 
										 oficios, 
										 mapaParametros);
		return reporte;
	}	
	
	private String obtenerRespuestas(String numOficio, String tipoOficio){
		StringBuilder sb = new StringBuilder();
		List<RespuestaOficioModel> list = respuestaOficioDAO.buscarRespuestasParaOficio(numOficio,tipoOficio);
		for (RespuestaOficioModel respuestaOficioModel : list) {
			sb.append(respuestaOficioModel.getIdRespuesta() + "-" + respuestaOficioModel.getObservaciones() + "<br>");
		}
		return sb.toString();
	}

}
