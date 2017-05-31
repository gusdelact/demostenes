package com.gfi.bin.admctasweb.catalogos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.DestinatarioModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;

/**
 * DAO para destinatarios
 * @author ESS3VAVC
 *
 */
public interface DestinatarioDAO {
	
	/**
	 * Busca destinatarios en la BD
	 */
	public List<DestinatarioModel> buscarDestinatario(DestinatarioModel destinatarioModel) throws DAOException;
	
	/**
	 * Busca destinatarios por clave de estatus
	 * @param claveEstatus
	 * @return List<DestinatarioModel>
	 * @throws DAOException
	 */
	List<DestinatarioModel> buscarDestinatariosPorEstatus(String claveEstatus) throws DAOException;
	
	/**
	 * Obtiene Destinatario por ID
	 * @param idDestinatario
	 * @return
	 * @throws DAOException
	 */
	public DestinatarioModel obtenerDestinatario(Integer idDestinatario)throws DAOException;
	
	/**
	 * Alta de nuevo Destinatario en la BD
	 * @param destinatarioModel
	 * @return
	 */
	public int guardarDestinatario(DestinatarioModel destinatarioModel)throws DAOException;
	
	/**
	 * Actualización de datos de un Destinatario 
	 * @param destinatarioModel
	 * @return
	 */
	public boolean modificarDestinatario(DestinatarioModel destinatarioModel)throws DAOException;
}
