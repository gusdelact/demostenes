package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.DestinatarioModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * Servicio para Destinatarios
 * @author ESS3VAVC
 *
 */
public interface DestinatarioService {
	
	/**
	 * Realiza la búsqueda de destinatarios de acuerdo a los parámetros de búsqueda proporcionados
	 * @param destinatarioModel
	 * @return
	 */
	public List<DestinatarioModel> buscarDestinatario(DestinatarioModel destinatarioModel) throws ServiceException;
	
	/**
	 * Busca destinatarios por clave de estatus
	 * @param claveEstatus
	 * @return List<DestinatarioModel>
	 * @throws DAOException
	 */
	List<DestinatarioModel> buscarDestinatariosPorEstatus(String claveEstatus) throws ServiceException;
	
	/**
	 * 
	 * @param Obtiene Destinatario por identificador
	 * @return
	 * @throws ServiceException
	 */
	public DestinatarioModel obtenerDestinatario(Integer idDestinatario) throws ServiceException;

	/**
	 * Servicio para guardar un Destinatario
	 * @param destinatarioModel
	 * @return
	 */
	public int guardarDestinatario(DestinatarioModel destinatarioModel)throws ServiceException;
	
	/**
	 * Servicio para actualizar los datos del destinatario
	 * @param destinatarioModel
	 * @return
	 */	
	public boolean modificarDestinatario(DestinatarioModel destinatarioModel)throws ServiceException;
	
	
	
}
