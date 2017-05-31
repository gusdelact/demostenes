/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.dao;

import java.util.Date;
import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.model.ArhivoCnbvModel;
import com.gfi.bin.admctasweb.reporte_r29.model.MapeoModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;

/**
 * Interfaz DAO para el manejo de Mapeo.
 * @author Jose Antonio Pavon Lopez
 *
 */
public interface MapeoDao {
	
	/**
	 * Realiza el guardado de un registro de Mapeo en la BD.
	 * @param Mapeo
	 * @return boolean
	 */
	public String guardarMapeo(MapeoModel mapeoManual) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Mapeo en la BD.
	 * @param mapeo
	 * @return boolean
	 */
	public boolean modificarMapeo(MapeoModel mapeoManual) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Sucutrsal en la BD.
	 * @param Mapeo
	 * @return boolean
	 */
	public boolean eliminarMapeo(MapeoModel mapeoManual) throws DAOException;
	
	/**
	 * Realiza la consulta de una Mapeo por su PK en la BD.
	 * @param idCatalogo Numero del catalogo a Buscar   // cambiar parametros
	 * @param cveCorporativa a Buscar    // ver si cambia este parametro
	 * @return MapeoModel
	 */
	public MapeoModel buscarMapeoPorLlave(int idCatalogo, String cveCorporativa ) throws DAOException;
	
}