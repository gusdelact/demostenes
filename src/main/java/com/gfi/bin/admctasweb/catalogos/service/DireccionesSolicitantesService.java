package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.DireccionesSolicitantesModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public interface DireccionesSolicitantesService {

	/**
	 * Insertar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws ServiceException
	 */
	DireccionesSolicitantesModel insertarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws ServiceException;
	
	/**
	 * Actualizar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean actualizarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws ServiceException;
	
	/**
	 * Eliminar Direcciones Solicitantes
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean eliminarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws ServiceException;
	
	/**
	 * Consultar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return List<DireccionesSolicitantesModel>
	 * @throws ServiceException
	 */
	List<DireccionesSolicitantesModel> consultarDireccionesSolicitantes(DireccionesSolicitantesModel dsModel) throws ServiceException;

	/**
	 * Consultar Direcci&oacute;n Solicitante por ID
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return DireccionesSolicitantesModel
	 * @throws ServiceException
	 */
	DireccionesSolicitantesModel consultarDireccionSolicitantePorID(DireccionesSolicitantesModel dsModel) throws ServiceException;

	/**
	 * Existe en el Cat&aacute;logo de Direcciones Solicitantes 
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean existeDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws ServiceException;
	
	/**
	 * Obtiene direcciones por tipo de oficio
	 * @param tipoOficio
	 * @return
	 * @throws ServiceException
	 */
	List<ItemModel> obtenerDireccionesPorTipoOficio(String tipoOficio) throws ServiceException;
	
}
