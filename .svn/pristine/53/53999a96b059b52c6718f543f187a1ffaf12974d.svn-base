package com.gfi.bin.admctasweb.catalogos.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.catalogos.dao.BitacoraDocumentoDAO;
import com.gfi.bin.admctasweb.catalogos.model.DocumentoEliminadoModel;
import com.gfi.bin.admctasweb.catalogos.service.BitacoraDocumentoService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.ConfigParams;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.jasper.service.JasperService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraDoctosEliminadosModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Service
public class BitacoraDocumentoServiceImpl implements BitacoraDocumentoService {
	
	@Autowired
	private BitacoraDocumentoDAO bitacoraDocumentoDAO;
	@Autowired
	private ConfigParams configParams;
	@Autowired
	private BasicDataSource corpDS;
	@Autowired
	private JasperService jasperService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraDocumentoServiceImpl.class);
	
	/**
	 * Insertar documento eliminado
	 * 
	 * @param DocumentoEliminadoModel
	 */
	public void guardar(DocumentoEliminadoModel documentoEliminadoModel)
			throws ServiceException {
		try{
			LOGGER.info("Se va a guardar en Bitacora documento eliminado...");
			bitacoraDocumentoDAO.guardar(documentoEliminadoModel);
			
			LOGGER.info("Se guardó en Bitacora documento eliminado...");
			
		}catch(DAOException daoException){
			LOGGER.error(daoException.getLocalizedMessage());
			throw new ServiceException(daoException);
		}
	}

	/**
	 * M&eacute;todo que permite consultar los documentos eliminados
	 * con los filtros de n&uacute;mero de oficio, tipo de oficio, fecha de inicio y fecha de fin
	 * 
	 * @author MUDF4038 - Fernando Munive Dorantes
	 * 
	 * @param BitacoraDoctosEliminadosModel
	 * @return List<DocumentoEliminadoModel>
	 * @throws ServiceException 
	 */
	public List<DocumentoEliminadoModel> consultarDocsEliminados(BitacoraDoctosEliminadosModel parametros) throws ServiceException {
		List<DocumentoEliminadoModel> docsEliminadosList = null;
		try {
			docsEliminadosList = bitacoraDocumentoDAO.consultarDocsEliminados(parametros);
		} catch (DAOException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new ServiceException(e);
		}
		return docsEliminadosList;
	}
	
	/**
	 * M&eacute;todo que permite generar el reporte de la Bit&aacute;cora Documentos Eliminados
	 * 
	 *  @author MUDF4038 - Fernando Munive Dorantes
	 * 
	 * @param String where
	 * @param String nombrePlantilla
	 * @param String pathLogo
	 * @param String usuarioSesion
	 * @return byte[]
	 */
	public byte[] generarReporte(BitacoraDoctosEliminadosModel vo, String nombrePlantilla, String pathLogo, String usuarioSesion) throws ServiceException {
		byte[] reporte = null;
		
		//Se obtienen los datos del reporte
		List<DocumentoEliminadoModel> documentos = this.consultarDocsEliminados(vo);
		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("paramUsuarioSesion", usuarioSesion);		
		parametros.put("paramLogoPath", pathLogo);
		
		//jasperService.generarReporteBean(plantilla, formato, lista, pars)
		reporte = jasperService.generarReporteBean(configParams.getRutaPlantillas() + nombrePlantilla, 
												   FormatoReporte.PDF,
												   documentos,
												   parametros
												   );
		return reporte;
	}	
}