/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.reporte_r29.model.ControlModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;


/**
 * @author LUGL4884
 * Clase de Servicios para Control
 *
 */
public interface ControlService {
	
	/**
	 * Servicio que guarda un registro de Control.
	 * @param control
	 * @return control
	 */
	public String guardarControl(ControlModel Control) throws ServiceException;
	
	/**
	 * Servicio la actualiza un registro de Control.
	 * @param vo
	 * @return boolean
	 */
	public boolean modificarControl(ControlModel vo) throws ServiceException;
	
	/**
	 * Servicio que Elimina un registro de Control.
	 * @param control
	 * @return boolean
	 */
	public boolean eliminarControl(ControlModel control) throws ServiceException;
	
	/**
	 * Servicio que Busca un registro de Control de acuerdo a su Llave Primaria.
	 * @param idEmpresa
	 * @param cvePeriodo
	 * @param numIntento
	 * @return control
	 */
	public ControlModel buscarControlPorLlave(String idEmpresaCx, int cvePeriodoCx, int numIntentoCx ) throws ServiceException;


		
}
