package com.gfi.bin.admctasweb.catalogos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.DireccionesSolicitantesModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public interface DireccionesSolicitantesDao {

	/**
	 * Insertar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws DAOException
	 */
	DireccionesSolicitantesModel insertarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws DAOException;
	
	/**
	 * Actualizar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean actualizarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws DAOException;
	
	/**
	 * Eliminar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean eliminarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws DAOException;

	/**
	 * Consultar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return List<DireccionesSolicitantesModel>
	 * @throws DAOException
	 */
	List<DireccionesSolicitantesModel> consultarDireccionesSolicitantes(DireccionesSolicitantesModel dsModel) throws DAOException;

	/**
	 * Consultar Direcci&oacute;n Solicitante por ID
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return DireccionesSolicitantesModel
	 * @throws DAOException
	 */
	DireccionesSolicitantesModel consultarDireccionSolicitantePorID(DireccionesSolicitantesModel dsModel) throws DAOException;

	/**
	 * Existe en el Cat&aacute;logo de Direcciones Solicitantes 
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws DAOException
	 */
	boolean existeDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws DAOException;	
	
	/**
	 * Obtiene Direcciones por tipo de Oficio
	 * @param tipoOficio
	 * @return
	 * @throws DAOException
	 */
	List<ItemModel> obtenerDireccionesPorTipoOficio(String tipoOficio)throws DAOException;
}
