package com.gfi.bin.admctasweb.operativos.service;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.mapper.CnbvDictamenModel;
import com.gfi.bin.admctasweb.util.Response;
/**
 * Servicio encargado de gestionar operaciones relacionadas al
 * "Registro de dictamen Juridico"
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public interface CnbvDictamenService {
	
	/**
	 * Busca todos los dictamenes juridicos existentes de la tabla CNBV_DICTAMEN
	 * @return
	 * @throws ServiceException 
	 */
	Response getAllCnbvDictamen() throws ServiceException;
	/**
	 * Guarda en la tabla CNBV_DICTAMEN, llave primaria numOficio,tipoOficio,numConsec
	 * @param cnbvDictamen
	 * @return response
	 * @throws ServiceException
	 */
	Response guardarCnbvDictamen(CnbvDictamenModel cnbvDictamen) throws ServiceException;
	/**
	 * Actualiza en la tabla CNBV_DICTAMEN, llave primaria numOficio,tipoOficio,numConsec
	 * @param cnbvDictamen
	 * @return
	 * @throws ServiceException
	 */
	Response actualizarCnbvDictamen(CnbvDictamenModel cnbvDictamen) throws ServiceException;	
	/**
	 * Consulta si existe un dictamen juridico de acuerdo a su PK.
	 * @param numOficio
	 * @param tipoOficio
	 * @return Boolean
	 * @throws ServiceException 
	 */
	boolean existeCnbvDictamen(String numOficio, String tipoOficio, Long numConsec) throws ServiceException;
		
	/**
	 * Obtiene el siguiente consecutivo del dictamen juridico.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int obtenerConsecutivo(String numOficio, String tipoOficio) throws ServiceException;
	
	/**
	 * Consulta si existe un dictamen juridico de acuerdo a su PK.Incluye los datos de la cabezera del oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @return Boolean
	 * @throws ServiceException 
	 */
	Response buscarDatosOficioAndRegistro(CnbvDictamenModel vo) throws ServiceException;
	
	/**
	 * Generación del reporte metodo que se ocupa para vizualisar el reporte en pantalla
	 * @param modelo
	 * @param nombrePlantilla
	 * @return
	 */
	byte[] generarReporte(CnbvDictamenModel modelo, String nombrePlantilla);
	
	/**
	 * Metodo que invoca al store procedure  ADMIPROD.SP_CNBV_REG_DIC_JUR para obtener el numero de respuestas faltantes
	 * @param numOficio
	 * @return
	 * @throws ServiceException
	 */
	int obtenerRespuestasFaltantesDeRegistro(String numOficio) throws ServiceException;
	

}
