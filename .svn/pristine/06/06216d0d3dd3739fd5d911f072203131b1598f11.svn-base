/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.dao;

import java.util.Date;
import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.model.ArhivoCnbvModel;
import com.gfi.bin.admctasweb.reporte_r29.model.SucursalModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;

/**
 * Interfaz DAO para el manejo de Sucursal.
 * @author Jose Antonio Pavon Lopez
 *
 */
public interface SucursalDao {
	
	/**
	 * Realiza el guardado de un registro de Sucursal en la BD.
	 * @param sucursal
	 * @return boolean
	 */
	public String guardarSucursal(SucursalModel sucursal) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Sucursal en la BD.
	 * @param sucursal
	 * @return boolean
	 */
	public boolean modificarSucursal(SucursalModel sucursalManual) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Sucutrsal en la BD.
	 * @param Sucursal
	 * @return boolean
	 */
	public boolean eliminarSucursal(SucursalModel sucutrsal) throws DAOException;
	
	/**
	 * Realiza la consulta de una Sucursal por su PK en la BD.
	 * @param idEmpresa a Buscar   // cambiar parametros
	 * @param tidSucursal a Buscar    // ver si cambia este parametro
	 * @return SucursalModel
	 */
	public SucursalModel buscarSucursalPorLlave(String idEmpresa, String idSucursal ) throws DAOException;
	
}