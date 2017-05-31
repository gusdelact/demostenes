package com.gfi.bin.admctasweb.procesoautomatico.dao;

import java.util.Date;
import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface BitacoraCargaAutomaticaDAO {
	
	/**
	 * Guarda registro de carga automática en bitácora
	 * 
	 * @param bitacoraCargaAutomaticaModel
	 * @return Boolean
	 * @throws DAOException
	 */
	Boolean guardarCargaAutomatica(BitacoraCargaAutomaticaModel bitacoraCargaAutomaticaModel) throws DAOException;
	
	/**
	 * Busca registros de carga automática en bitácora por fecha
	 * 
	 * @param fecha
	 * @return List<BitacoraCargaAutomaticaModel>
	 * @throws DAOException
	 */
	List<BitacoraCargaAutomaticaModel> buscarCargaAutomaticaPorFecha(Date fecha) throws DAOException;
	
	/**
	 * Obtiene el siguiente valor de la secuencia para la inserción de registros en bitacora de carga automática
	 * 
	 * @return Integer
	 * @throws DAOException
	 */
	Integer obtenerSiguienteValorSecuencia() throws DAOException;
}