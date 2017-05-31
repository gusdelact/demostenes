/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualContratoModel;
import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;


/**
 * @author LUGL4884
 * Clase de Servicios para Captura Manua
 *
 */
public interface CapturaManualService {
	
	/**
	 * Servicio que guarda un registro de Captura Manual.
	 * @param apturaManual
	 * @return boolean
	 */
	public boolean guardarCapturaManual(CapturaManualModel capturaManual) throws ServiceException;
	
	/**
	 * Servicio la actualiza un registro de Captura Manual.
	 * @param capturaManual
	 * @return boolean
	 */
	public boolean modificarCapturaManual(CapturaManualModel capturaManual) throws ServiceException;
	
	/**
	 * Servicio que Elimina un registro de Captura Manual.
	 * @param capturaManual
	 * @return boolean
	 */
	public boolean eliminarCapturaManual(CapturaManualModel capturaManual) throws ServiceException;
	
	/**
	 * Servicio que Busca un registro de Captura Manual de acuerdo a su Llave Primaria.
	 * @param numOficio  
	 * @param tipoOficio
	 * @param idCotitular
	 * @param idEmpresa
	 * @param cvePeriodo
	 * @param numIntento
	 * @return CapturaManualModel
	 */
	public CapturaManualModel buscarCapturaManualPorLlave(String numOficio, String tipoOficio, long idCotitular, 
            String idEmpresa, long cvePeriodo, int numIntento ) throws ServiceException;

	/**
	 * Servicio que Busca un registro de Captura Manual de acuerdo a su contrato.
	 * @param idCuenta  
	 * @param idEmpresa
	 * @return CapturaManualModel
	 * @throws ServiceException 
	 */
	public CapturaManualModel buscarCapturaManualContrato(String idCuenta, String idEmpresa) throws ServiceException;
	
		
}
