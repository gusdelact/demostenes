package com.gfi.bin.admctasweb.procesoautomatico.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface BitacoraCargaAutomaticaService {
	
	/**
	 * Guarda registro de carga automática en bitácora (Carga, Oficios y Personas)
	 * 
	 * @param listaBitCargaAutomatica
	 * @return List<BitacoraCargaAutomaticaModel>
	 * @throws ServiceException
	 */
	List<BitacoraCargaAutomaticaModel> guardarCargaAutomatica(List<BitacoraCargaAutomaticaModel> listaBitCargaAutomatica) throws ServiceException;
	
	/**
	 * Obtiene el siguiente valor de la secuencia para la inserción de registros en bitacora de carga automática
	 * 
	 * @return Integer
	 * @throws ServiceException
	 */
	Integer obtenerSiguienteValorSecuencia() throws ServiceException;
}