/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionListModel;
import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public interface BitacoraExcepcionService {

	/**
	 * 
	 * @param BitacoraExcepcionModel
	 * @return List<BitacoraExcepcionModel>
	 * @throws ServiceException
	 */
	List<BitacoraExcepcionModel> consultarBitacoraExcepcion(BitacoraExcepcionListModel parametros) throws ServiceException;
	
	/**
	 * M&eacute;todo que permite generar el reporte de la Bit&aacute;cora de Excepciones
	 * 
	 *  @author MUDF4038 - Fernando Munive Dorantes
	 * 
	 * @param String where
	 * @param String nombrePlantilla
	 * @param String pathLogo
	 * @param String usuarioSesion
	 * @return byte[]
	 * @throws ServiceException
	 */
	byte[] generarReporte(String where, String nombrePlantilla, String pathLogo, String usuarioSesion) throws ServiceException;

	/**
	 * Permite generar el reporte de la Bit&aacute;cora de Excepciones
	 * 
	 *  @author MREYES - Manuel Reyes Castellanos
	 * 
	 * @param List<BitacoraExcepcionModel> lista
	 * @param String nombrePlantilla
	 * @param String pathLogo
	 * @param String usuarioSesion
	 * @return byte[]
	 * @throws ServiceException
	 */
	byte[] generarReporteBean(List<BitacoraExcepcionModel> lista,
			String nombrePlantilla, String pathLogo, String usuarioSesion)
			throws ServiceException;
	
}
