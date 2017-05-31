/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.ImpresionDictamenJurModel;

/**
 * Interfaz de Acceso a Datos para la Impresión de Dictamen Jurídico.
 * @author LUGL4884
 *
 */
public interface ImpresionDictamenJurDao {
	
	/**
	 * Servicio que Busca las Respuestas relacionadas a un Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<ImpresionDictamenJurModel>
	 * @throws DAOException
	 */
	List<ImpresionDictamenJurModel> buscarDictamenJur(String numOficio, String tipoOficio) throws DAOException;
	
	/**
	 * Servicio que valida que se hayan creado Todos los dictamenes Jur. del Oficio y los marca como Impresos.
	 * @param numOficio
	 * @param tipoOficio
	 * @param numConsec
	 * @param usuario
	 * @return int
	 * @throws DAOException
	 */
	int marcarDictamenJur(String numOficio, String tipoOficio, Long numConsec, String usuario) throws DAOException;
	
	/**
	 * Servicio que regresa el número de Dictamenes Jurídicos por Oficio que no se han Impreso.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int buscarDictamenSinImp(String numOficio, String tipoOficio) throws DAOException;

}
