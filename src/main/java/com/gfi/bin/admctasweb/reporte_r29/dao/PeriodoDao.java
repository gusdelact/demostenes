/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.dao;

import java.util.Date;
import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.model.ArhivoCnbvModel;
import com.gfi.bin.admctasweb.reporte_r29.model.PeriodoModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;

/**
 * Interfaz DAO para el manejo de Periodo.
 * @author Jose Antonio Pavon Lopez
 *
 */
public interface PeriodoDao {
	
	/**
	 * Realiza el guardado de un registro de Periodo en la BD.
	 * @param Periodo
	 * @return boolean
	 */
	public String guardarPeriodo(PeriodoModel periodo) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Periodo en la BD.
	 * @param periodo
	 * @return boolean
	 */
	public boolean modificarPeriodo(PeriodoModel periodo) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Periodo en la BD.
	 * @param Periodo
	 * @return boolean
	 */
	public boolean eliminarPeriodo(PeriodoModel periodo) throws DAOException;
	
	/**
	 * Realiza la consulta de una Periodo por su PK en la BD.
	 * @param idEmpresa a Buscar   // cambiar parametros
	 * @param cvePeriodo a Buscar    // ver si cambia este parametro
	 * @return PeriodoModel
	 */
	public PeriodoModel buscarPeriodoPorLlave(String idEmpresa, int cvePeriodo ) throws DAOException;
	
}