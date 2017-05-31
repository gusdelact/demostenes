package com.gfi.bin.admctasweb.operativos.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import net.sf.jasperreports.engine.JRParameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.catalogos.controller.DestinatarioController;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.ConfigParams;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.jasper.service.JasperService;
import com.gfi.bin.admctasweb.operativos.dao.ContratoDAO;
import com.gfi.bin.admctasweb.operativos.model.ContratoCambiosModel;
import com.gfi.bin.admctasweb.operativos.model.ContratoModel;
import com.gfi.bin.admctasweb.operativos.model.PersonaBusquedaModel;
import com.gfi.bin.admctasweb.operativos.service.ContratoService;
import com.gfi.bin.admctasweb.util.Util;

/**
 * Implementación de servicios para obtener contratos de una persona
 * @author ESS3VAVC
 *
 */
@Service
public class ContratoServiceImpl implements ContratoService {

	@Autowired
	ContratoDAO contratoDAO; 
	
	@Autowired
	JasperService jasperService;
	
	@Autowired
	private ConfigParams configParams;
	
	
	private static final String NOMBRE_PLANTILLA = "contratos";
	
	private static final String NOMBRE_PLANTILLA_VISTA = "contratosVista.jasper";
	private static final String NOMBRE_PLANTILLA_CENTRAL = "contratosCentral.jasper";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DestinatarioController.class);

	
	/**
	 * Obtiene los contratos DPVISTA, BURSATIL, FIDEICOMISOS y CREDITOS
	 * @return
	 * @throws ServiceException
	 */
	public List<ContratoModel> obtenerContratos(Long idPersona, Long idContrato)throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return contratoDAO.obtenerContratos(idPersona, idContrato);
		} catch (DAOException e) {
			LOGGER.debug(e.getLocalizedMessage());
			throw new ServiceException(e);
		}
	}

	/**
	 * Obtiene contratos de Central de Cambios de una persona
	 * @return
	 * @throws ServiceException
	 */
	public List<ContratoCambiosModel> obtenerContratosCambios(Long idPersona)throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return contratoDAO.obtenerContratosCambios(idPersona);
		} 
		catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	/**
	 * Obtiene un registro de Contrato DPVISTA, BURSATIL, FIDEICOMISOS y CREDITOS por su Id.
	 * @return ContratoModel
	 * @throws ServiceException
	 */
	public ContratoModel obtenerContratoPorId(Long idContrato, Long idPersona)
			throws ServiceException {
		try {
			return contratoDAO.obtenerContratoPorId(idContrato, idPersona);
		} 
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Obtiene un registro de Contrato de Central de Cambios por su Id.
	 * @return ContratoCambiosModel
	 * @throws ServiceException
	 */
	public ContratoCambiosModel obtenerContratoCambiosPorId(Long idContrato)
			throws ServiceException {
		try {
			return contratoDAO.obtenerContratoCambiosPorId(idContrato);
		} 
		catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Genera reporte de Contratos de una persona corporativa
	 * @param idPersona
	 * @return
	 * @throws ServiceException
	 */	
	@Override
	public byte[] generarReporte(Long idPersona, Long idContrato, String rutaLogo) throws ServiceException {
		// TODO Auto-generated method stub
		byte reporte [] = null;
		LOGGER.debug("idPersona: "+idPersona);
		LOGGER.debug("idContrato: "+idContrato);
		
		if(idPersona != null){
			List<ContratoModel> contratos = this.obtenerContratos(idPersona, idContrato);
			List<ContratoCambiosModel> contratosCambios = null;
			
			if(idContrato == 0){
				LOGGER.debug("Se va a consultar contratosCambios...");
				contratosCambios = this.obtenerContratosCambios(idPersona);
			}
			PersonaBusquedaModel personaBusquedaModel = new PersonaBusquedaModel();
			personaBusquedaModel.setIdPersona(idPersona);
			personaBusquedaModel.setContratosList(contratos);
			personaBusquedaModel.setContratosCambiosList(contratosCambios);
			
			//A pesar de que el reporte se genera para una sola persona, se envía el bean adentro de lista
			List<PersonaBusquedaModel> lista = new ArrayList<PersonaBusquedaModel>();
			lista.add(personaBusquedaModel);
						
			InputStream pathContratosVista = this.getClass().getClassLoader().getResourceAsStream(configParams.getRutaPlantillas() + NOMBRE_PLANTILLA_VISTA);
			InputStream pathContratosCentral = this.getClass().getClassLoader().getResourceAsStream(configParams.getRutaPlantillas() + NOMBRE_PLANTILLA_CENTRAL);
			
			
			//Parámetros
			HashMap<String, Object> parametros = new HashMap<String, Object>();
			parametros.put("paramUsuarioSesion", Util.usuarioSesion());
			parametros.put("paramLogoPath", rutaLogo);
			parametros.put("SUBREPORT_VISTA_DIR", pathContratosVista);
			parametros.put("SUBREPORT_CENTRAL_DIR", pathContratosCentral);
			parametros.put(JRParameter.REPORT_LOCALE, new Locale("es", "MX"));
			
			
			return jasperService.generarReporteBean(configParams.getRutaPlantillas() + NOMBRE_PLANTILLA, 
					FormatoReporte.PDF,
					lista,
					parametros);
		}
		
		return reporte;
	}

}
