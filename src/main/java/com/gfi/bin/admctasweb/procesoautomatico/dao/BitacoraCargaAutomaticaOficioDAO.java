package com.gfi.bin.admctasweb.procesoautomatico.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaOficioModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface BitacoraCargaAutomaticaOficioDAO {
	
	/**
	 * Guarda registro de carga automática de bitácoraOficio
	 * 
	 * @param bitacoraCargaAutomaticaOficio
	 * @return Boolean
	 * @throws DAOException
	 */
	Boolean guardarCargaAutomaticaOficio(BitacoraCargaAutomaticaOficioModel bitacoraCargaAutomaticaOficio) throws DAOException;
	
	/**
	 * Busca registros de carga automática Oficio por carga
	 * 
	 * @return List<BitacoraCargaAutomaticaOficioModel>
	 * @throws DAOException
	 */
	List<BitacoraCargaAutomaticaOficioModel> buscarOficiosCargaAutomaticaPorCarga(Integer idCarga, Integer idEmpresa) throws DAOException;
}