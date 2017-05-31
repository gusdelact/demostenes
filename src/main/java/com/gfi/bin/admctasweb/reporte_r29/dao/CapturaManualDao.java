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
import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;

/**
 * Interfaz DAO para el manejo de Captura Manual.
 * @author Jose Antonio Pavon Lopez
 *
 */
public interface CapturaManualDao {
	
	/**
	 * Realiza el guardado de un registro de Captura Manual en la BD.
	 * @param capturaManual
	 * @return boolean
	 */
	public boolean guardarCapturaManual(CapturaManualModel capturaManual) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Captura Manual en la BD.
	 * @param capturaManual
	 * @return boolean
	 */
	public boolean modificarCapturaManual(CapturaManualModel capturaManual) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Captura Manual en la BD.
	 * @param Captura Manual
	 * @return boolean
	 */
	public boolean eliminarCapturaManual(CapturaManualModel capturaManual) throws DAOException;
	
	/**
	 * Realiza la consulta de una Captura Manual por su PK en la BD.
	 * @param numOficio Numero del Oficio a Buscar   // cambiar parametros
	 * @param tipoOficio Tipo del Oficio a Buscar    // ver si cambia este parametro
	 * @return Captura ManualModel
	 */
	public CapturaManualModel buscarCapturaManualPorLlave(String numOficio, String tipoOficio, long idCotitular, 
            String idEmpresa, long cvePeriodo, int numIntento ) throws DAOException;
	// cambiar los parametros del metodo... JAPL
	/**
	 * Realiza la consulta de una Captura Manual por Contrato en la BD.
	 * @param idCuenta
	 * @param IdEmpresa 
	 * @return Captura ManualModel
	 */
	public CapturaManualModel buscarCapturaManualContrato(String idCuenta, String idEmpresa ) throws DAOException;


}