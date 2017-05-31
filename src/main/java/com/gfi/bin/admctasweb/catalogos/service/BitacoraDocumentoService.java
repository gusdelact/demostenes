package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoEliminadoModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.reportes.model.BitacoraDoctosEliminadosModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface BitacoraDocumentoService {
	
	/**
	 * Insertar documento eliminado
	 * 
	 * @param DocumentoEliminadoModel
	 */
	void guardar(DocumentoEliminadoModel documentoEliminadoModel) throws ServiceException;
	
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
	List<DocumentoEliminadoModel> consultarDocsEliminados(BitacoraDoctosEliminadosModel parametros) throws ServiceException;
	
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
	byte[] generarReporte(BitacoraDoctosEliminadosModel vo, String nombrePlantilla, String pathLogo, String usuarioSesion) throws ServiceException;
}