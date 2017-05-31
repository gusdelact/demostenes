/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.reporte_r29.model.PeriodoModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;


/**
 * @author LUGL4884
 * Clase de Servicios para Periodo
 *
 */
public interface PeriodoService {
	
	/**
	 * Servicio que guarda un registro de Periodo.
	 * @param periodo
	 * @return periodo
	 */
	public String guardarPeriodo(PeriodoModel periodo) throws ServiceException;
	
	/**
	 * Servicio la actualiza un registro de Periodo.
	 * @param periodo
	 * @return boolean
	 */
	public boolean modificarPeriodo(PeriodoModel periodo) throws ServiceException;
	
	/**
	 * Servicio que Elimina un registro de Periodo.
	 * @param periodo
	 * @return boolean
	 */
	public boolean eliminarPeriodo(PeriodoModel periodo) throws ServiceException;
	
	/**
	 * Servicio que Busca un registro de Periodo de acuerdo a su Llave Primaria.
	 * @param idEmpresa
	 * @param cvePeriodo
	 * @return Periodo
	 */
	public PeriodoModel buscarPeriodoPorLlave(String idEmpresa, int cvePeriodo) throws ServiceException;


		
}
