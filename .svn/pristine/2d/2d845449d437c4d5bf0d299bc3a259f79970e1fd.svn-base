package com.gfi.bin.admctasweb.catalogos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public interface ParametrosDao {

	/**
	 * Insertar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean insertarParametro(ParametrosModel parametrosModel) throws DAOException;
	
	/**
	 * Actualizar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean actualizarParametro(ParametrosModel parametrosModel) throws DAOException;
	
	/**
	 * Eliminar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean eliminarParametro(ParametrosModel parametrosModel) throws DAOException;

	/**
	 * Consultar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return List<ParametrosModel>
	 * @throws DAOException
	 */
	List<ParametrosModel> consultarParametros(ParametrosModel parametrosModel) throws DAOException;

	/**
	 * Consultar Par&aacute;metros de Oficios CNBV por Clave
	 * 
	 * @param ParametrosModel
	 * @return ParametrosModel
	 * @throws DAOException
	 */
	ParametrosModel consultarParametroPorClave(ParametrosModel parametrosModel) throws DAOException;

	/**
	 * Existe en el Cat&aacute;logo de Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean existeParametro(ParametrosModel parametrosModel) throws DAOException;

	/**
	 * Obtiene el folio para la generacion del archivo CNBV
	 * @param tipo
	 * @param idEmpresa
	 * @param dateFormatddMMyyyy
	 * @param cveUsuAlta
	 * @return
	 * @throws DAOException
	 */
	String obtenerFolioArchivoCnbv(String tipo, Integer idEmpresa,
			String fechaddMMyyyy, String cveUsuAlta);
	
}
