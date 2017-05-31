package com.gfi.bin.admctasweb.operativos.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.ContratoCambiosModel;
import com.gfi.bin.admctasweb.operativos.model.ContratoModel;

/**
 * Servicio para obtener contratos de una persona
 * @author ESS3VAVC
 *
 */
public interface ContratoService {
	/**
	 * Obtiene los contratos DPVISTA, BURSATIL, FIDEICOMISOS y CREDITOS
	 * @return
	 * @throws ServiceException
	 */
	List<ContratoModel> obtenerContratos(Long idPersona, Long idContrato)throws ServiceException;
	
	/**
	 * Obtiene contratos de Central de Cambios de una persona
	 * @return
	 * @throws ServiceException
	 */
	List<ContratoCambiosModel> obtenerContratosCambios(Long idPersona)throws ServiceException;
	
	/**
	 * Obtiene un registro de Contrato DPVISTA, BURSATIL, FIDEICOMISOS y CREDITOS por su Id.
	 * @return ContratoModel
	 * @throws ServiceException
	 */
	ContratoModel obtenerContratoPorId(Long idContrato, Long idPersona) throws ServiceException; 
	
	/**
	 * Obtiene un registro de Contrato de Central de Cambios por su Id.
	 * @return ContratoCambiosModel
	 * @throws ServiceException
	 */
	ContratoCambiosModel obtenerContratoCambiosPorId(Long idContrato) throws ServiceException;
	
	/**
	 * Genera reporte de Contratos de una persona corporativa
	 * @param idPersona
	 * @return
	 * @throws ServiceException
	 */
	byte [] generarReporte(Long idPersona, Long idContrato, String rutaLogo) throws ServiceException;

}
