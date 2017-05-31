package com.gfi.bin.admctasweb.procesoautomatico.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaPersonaModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface BitacoraCargaAutomaticaPersonaDAO {
	
	/**
	 * Guarda registro de carga automática de bitácoraPersona
	 * 
	 * @param bitacoraCargaAutomaticaPersona
	 * @return Boolean
	 * @throws DAOException
	 */
	Boolean guardarCargaAutomaticaPersona(BitacoraCargaAutomaticaPersonaModel bitacoraCargaAutomaticaPersona) throws DAOException;
	
	
	/**
	 * Busca registros de carga automática Persona por carga
	 * 
	 * @param idCarga
	 * @param idEmpresa
	 * @param numeroOficio
	 * @param tipoOficio
	 * @return List<BitacoraCargaAutomaticaPersonaModel>
	 * @throws DAOException
	 */
	List<BitacoraCargaAutomaticaPersonaModel> buscarPersonasCargaAutomaticaPorCarga(Integer idCarga, Integer idEmpresa, String numeroOficio, String tipoOficio) throws DAOException;
}