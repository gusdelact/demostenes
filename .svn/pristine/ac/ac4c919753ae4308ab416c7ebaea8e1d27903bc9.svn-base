/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.reporte_r29.model.SucursalModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;


/**
 * @author LUGL4884
 * Clase de Servicios para Captura Sucursal
 */
public interface SucursalService {
	
	/**
	 * Servicio que guarda un registro de Sucursal.
	 * @param sucursal
	 * @return boolean
	 */
	public String guardarSucursal(SucursalModel sucursal) throws ServiceException;
	
	/**
	 * Servicio la actualiza un registro de Sucursal.
	 * @param sucursal
	 * @return boolean
	 */
	public boolean modificarSucursal(SucursalModel sucursal) throws ServiceException;
	
	/**
	 * Servicio que Elimina un registro de SucursL.
	 * @param sucursal
	 * @return boolean
	 */
	public boolean eliminarSucursal(SucursalModel sucursal) throws ServiceException;
	
	/**
	 * Servicio que Busca un registro de Sucursal de acuerdo a su Llave Primaria.
	 * @param idEmpresa
	 * @param idSucursal
	 * @return Sucursal
	 */
	public SucursalModel buscarSucursalPorLlave(String idEmpresa, String idSucursal ) throws ServiceException;


		
}
