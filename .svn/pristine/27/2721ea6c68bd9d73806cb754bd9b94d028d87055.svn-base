package com.gfi.bin.admctasweb.catalogos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.catalogos.dao.DestinatarioDAO;
import com.gfi.bin.admctasweb.catalogos.model.DestinatarioModel;
import com.gfi.bin.admctasweb.catalogos.service.DestinatarioService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * Servicio para Destinatarios
 * @author ESS3VAVC
 *
 */
@Service
public class DestinatarioServiceImpl implements DestinatarioService {
	
	@Autowired
	private DestinatarioDAO destinatarioDAO;
	
	/**
	 * Realiza la búsqueda de destinatarios de acuerdo a los parámetros de búsqueda proporcionados
	 * @param destinatarioModel
	 * @return
	 */
	public List<DestinatarioModel> buscarDestinatario(DestinatarioModel destinatarioModel) throws ServiceException{
		try {
			return destinatarioDAO.buscarDestinatario(destinatarioModel);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Realiza la búsqueda de destinatarios por clave de estatus
	 * @param claveEstatus
	 * @return List<DestinatarioModel>
	 */
	public List<DestinatarioModel> buscarDestinatariosPorEstatus(String claveEstatus) throws ServiceException{
		try {
			return destinatarioDAO.buscarDestinatariosPorEstatus(claveEstatus);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Servicio para guardar un Destinatario
	 * @param destinatarioModel
	 * @return
	 */
	public int guardarDestinatario(DestinatarioModel destinatarioModel) throws ServiceException{
		
		
		
		try {
			return destinatarioDAO.guardarDestinatario(destinatarioModel);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
		
	
	}
	
	/**
	 * Servicio para actualizar los datos del destinatario
	 * @param destinatarioModel
	 * @return
	 */	
	public boolean modificarDestinatario(DestinatarioModel destinatarioModel) throws ServiceException{
		boolean guardo = false;
		
		try {
			guardo = destinatarioDAO.modificarDestinatario(destinatarioModel);
		} 
		catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
		
		return guardo;
	
	}

	/**
	 * Obtiene Destinatario por ID
	 */
	public DestinatarioModel obtenerDestinatario(Integer idDestinatario) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return destinatarioDAO.obtenerDestinatario(idDestinatario);
		} 
		catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	
}
