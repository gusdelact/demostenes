/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.reporte_r29.model.MapeoModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;


/**
 * @author LUGL4884
 * Clase de Servicios para Mapeo
 *
 */
public interface MapeoService {
	
	/**
	 * Servicio que guarda un registro de Mapeo.
	 * @param mapeo
	 * @return mapeo
	 */
	public String guardarMapeo(MapeoModel mapeo) throws ServiceException;
	
	/**
	 * Servicio la actualiza un registro de Mapeo.
	 * @param mapeo
	 * @return boolean
	 */
	public boolean modificarMapeo(MapeoModel mapeo) throws ServiceException;
	
	/**
	 * Servicio que Elimina un registro de Mapeo.
	 * @param mapeo
	 * @return boolean
	 */
	public boolean eliminarMapeo(MapeoModel mapeo) throws ServiceException;
	
	/**
	 * Servicio que Busca un registro de Mapeo de acuerdo a su Llave Primaria.
	 * @param idCatalogo
	 * @param cveCorporativa
	 * @return Periodo
	 */
	public MapeoModel buscarMapeoPorLlave(int idCatalogo, String cveCorporativa) throws ServiceException;


		
}
