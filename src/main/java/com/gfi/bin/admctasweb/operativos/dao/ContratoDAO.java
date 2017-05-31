package com.gfi.bin.admctasweb.operativos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.model.ContratoCambiosModel;
import com.gfi.bin.admctasweb.operativos.model.ContratoModel;

/**
 * Interfaz para acceder a BD para obtener contratos de una persona
 * @author ESS3VAVC
 *
 */
public interface ContratoDAO {

	/**
	 * Obtiene los contratos DPVISTA, BURSATIL, FIDEICOMISOS y CREDITOS
	 * @return
	 * @throws DAOException
	 */
	List<ContratoModel> obtenerContratos(Long idPersona, Long idContrato)throws DAOException;
	
	/**
	 * Obtiene contratos de Central de Cambios de una persona
	 * @return
	 * @throws DAOException
	 */
	List<ContratoCambiosModel> obtenerContratosCambios(Long idPersona)throws DAOException;
	
	/**
	 * Obtiene un registro de Contrato DPVISTA, BURSATIL, FIDEICOMISOS y CREDITOS por su Id.
	 * @return ContratoModel
	 * @throws DAOException
	 */
	ContratoModel obtenerContratoPorId(Long idContrato, Long idPersona) throws DAOException; 
	
	/**
	 * Obtiene un registro de Contrato de Central de Cambios por su Id.
	 * @return ContratoCambiosModel
	 * @throws DAOException
	 */
	ContratoCambiosModel obtenerContratoCambiosPorId(Long idContrato) throws DAOException;
}
