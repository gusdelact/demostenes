/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.dao;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.reporte_r29.model.ControlModel;

/**
 * Interfaz DAO para el manejo de Control.
 * @author Jose Antonio Pavon Lopez
 *
 */
public interface ControlDao {
	
	/**
	 * Realiza el guardado de un registro de Control en la BD.
	 * @param Control
	 * @return boolean
	 */
	public String guardarControl(ControlModel control) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Control en la BD.
	 * @param control
	 * @return boolean
	 */
	public boolean modificarControl(ControlModel control) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Control en la BD.
	 * @param Control
	 * @return boolean
	 */
	public boolean eliminarControl(ControlModel control) throws DAOException;
	
	/**
	 * Realiza la consulta de una Control por su PK en la BD.
	 * @param idEmpresa a Buscar   
	 * @param cvePeriodo a Buscar    
	 * @param numIntento a Buscar   
	 * @return ControlModel
	 */
	public ControlModel buscarControlPorLlave(String idEmpresaCx, int cvePeriodoCx, int numIntentoCx ) throws DAOException;
	
}