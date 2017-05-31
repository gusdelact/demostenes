/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.dao;

import java.util.Date;
import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.model.ArhivoCnbvModel;
import com.gfi.bin.admctasweb.operativos.model.OficioCnbvModel;
import com.gfi.bin.admctasweb.reporte_r29.model.ConversionModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;

/**
 * Interfaz DAO para el manejo de Conversion.
 * @author Jose Antonio Pavon Lopez
 *
 */
public interface ConversionDao {
	
	/**
	 * Realiza el guardado de un registro de Conversion en la BD.
	 * @param conversion
	 * @return boolean
	 */
	public String guardarConversion(ConversionModel conversion) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Conversion en la BD.
	 * @param conversion
	 * @return boolean
	 */
	public boolean modificarConversion(ConversionModel conversionManual) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Conversion en la BD.
	 * @param Conversion
	 * @return boolean
	 */
	public boolean eliminarConversion(ConversionModel capturaManual) throws DAOException;
	
	/**
	 * Realiza la consulta de una Conversion por su PK en la BD.
	 * 
	 * @param idCatalogo a Buscar    // ver si cambia este parametro
	 * @return ConversionModel
	 */
	public ConversionModel buscarConversionPorLlave(int idCatalogo ) throws DAOException;
	
	/**
	 * Realiza la consulta de un registro Conversion por su PK en la BD.
	 * 
	 * @param idCatalogo a Buscar    // ver si cambia este parametro
	 * @return registro
	 */
	public int buscarRegistroConversion(int idCatalogo ) throws DAOException;
	
}