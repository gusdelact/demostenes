package com.gfi.bin.admctasweb.operativos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.mapper.CnbvDictamenModel;


/**
 * Clase encargada de definir las diferentes operaciones que se pueden realizar con la 
 * tabla "CNBV_DICTAMEN" - CnbvDictamenModel
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public interface  CnbvDictamenDao {

	/**
	 * Metodo encargado de obtener todas los registro de la tabla "CNBV_DICTAMEN"
	 * @return List<PersonaModel>
	 */
	List<CnbvDictamenModel> getAllCnbvDictamen();
	
	/** 
	 * Inserta un renglon de la tabla CNBV_DICTAMEN
	 * @param persona
	 */
	void guardarCnbvDictamen(CnbvDictamenModel cnbvDictamen);
	
	/** 
	 * Actualiza un renglon de la tabla CNBV_DICTAMEN
	 * @param cnbvDictamen
	 */
	void actualizarCnbvDictamen(CnbvDictamenModel cnbvDictamen);
	
	/** 
	 * Busqueda del campo impreso por contraloria  'IMPRESO_POR_CONTRALORIA' de un dictamen juridico por la llave primaria compuesta
	 *  'numOficio,tipoOficio,numConsec' de la tabla CNBV_DICTAMEN
	 * @param cnbvDictamen
	 */
	String buscarFlagContraloriaCnbvDictamen(CnbvDictamenModel cnbvDictamen);
	
	/**
	 * Consulta si existe una dictamen juridico de acuerdo a su PK.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 */
	int existeCnbvDictamen(String numOficio, String tipoOficio, Long numConsec);
	
	/**
	 * Obtiene el siguiente consecutivo para el dictamen juridico
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int obtenerConsecutivo(String numOficio, String tipoOficio);

	/**
	 * Consulta si existe una dictamen juridico de acuerdo a su PK. Incluye los datos de la cabecera del oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @return CnbvDictamenModel
	 */
	CnbvDictamenModel buscarDatosOficioAndRegistro(CnbvDictamenModel cnbvDictamen);
	/**
	 * Invoca al store procedure ADMIPROD.SP_CNBV_REG_DIC_JUR , cuando regresa 0 significa que todos las respuestas ya fueron registradas
	 * cualquier otro valor equivale al numero de respuestas faltantes por registrar 
	 * @param numOficio
	 * @return
	 */
	int obtenerFlagRegistroJur(String numOficio);
	
}
