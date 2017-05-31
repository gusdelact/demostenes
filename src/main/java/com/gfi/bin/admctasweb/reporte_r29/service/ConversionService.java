/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.reporte_r29.model.ConversionModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;


/**
 * @author LUGL4884
 * Clase de Servicios para Captura Manua
 *
 */
public interface ConversionService {
	
	/**
	 * Servicio que guarda un registro de Conversion.
	 * @param conversion
	 * @return boolean
	 */
	public String guardarConversion(ConversionModel conversion) throws ServiceException;
	
	/**
	 * Servicio la actualiza un registro de Conversion.
	 * @param conversion
	 * @return boolean
	 */
	public boolean modificarConversion(ConversionModel conversion) throws ServiceException;
	
	/**
	 * Servicio que Elimina un registro de SucursL.
	 * @param conversion
	 * @return boolean
	 */
	public boolean eliminarConversion(ConversionModel conversion) throws ServiceException;
	
	/**
	 * Servicio que Busca un registro de Conversion de acuerdo a su Llave Primaria.
	 * @param idCastalogo
	 * @return Conversion
	 */
	public ConversionModel buscarConversionPorLlave(int idCatalogo) throws ServiceException;

	/**
	 * Servicio que Busca si existe un registro de Conversion de acuerdo a su Llave Primaria.
	 * @param idCastalogo
	 * @return registro
	 */
	public int buscarRegistroConversion(int idCatalogo) throws ServiceException;

		
}
