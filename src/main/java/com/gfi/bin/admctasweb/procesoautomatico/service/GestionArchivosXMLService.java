package com.gfi.bin.admctasweb.procesoautomatico.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface GestionArchivosXMLService {
	
	/**
	 * Metodo principal del Proceso Automático, lee archivos XML del directorio donde se han descomprimido, los datos leídos se guardan en BD
	 * 
	 * @return List<BitacoraCargaAutomaticaModel>
	 * @throws ServiceException
	 */
	List<BitacoraCargaAutomaticaModel> leerArchivosXMLRepositorio() throws ServiceException;
}