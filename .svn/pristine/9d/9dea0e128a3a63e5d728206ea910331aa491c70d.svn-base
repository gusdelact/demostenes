package com.gfi.bin.admctasweb.catalogos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.dao.PersonaDao;
import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.catalogos.service.PersonaService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.util.Response;

/**
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Service(value = "personaService")
public class PersonaServiceImpl implements PersonaService {
	
	@Autowired
	private PersonaDao personaDao;

	public Response getAllPersonas() {
		Response resp = new Response(true);
		List<PersonaModel> personas =  new ArrayList<PersonaModel>();
		
		personas = this.personaDao.getAllPersonas();
		resp.setData(personas);
		return resp;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public Response guardarPersona(PersonaModel persona) throws ServiceException{
		Response resp = new Response(true);
		try {
			personaDao.guardarPersona(persona);
			resp.setData(persona);
		} catch (Exception e) {
			resp.setSuccess(false);
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return resp;
	}
	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public Response actualizarPersona(PersonaModel persona) throws ServiceException{
		Response resp = new Response(true);
		try {
			personaDao.actualizarPersona(persona);
			resp.setData(persona);
		} catch (Exception e) {
			resp.setSuccess(false);
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return resp;
	}
	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean eliminarPersona(PersonaModel persona) throws ServiceException{
		Boolean result = false;
		
		try{
			result = this.personaDao.eliminarPersona(persona);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	public Response buscarPersonaId(PersonaModel persona) throws ServiceException{
		Response resp = new Response(true);
		try {
			resp.setData(personaDao.buscarPersonaId(persona));
		} catch (Exception e) {
			resp.setSuccess(false);
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return resp;
	}
	
	public boolean existePersona(String numOficio, String tipoOficio,
			Long numConsec) throws ServiceException {
		Boolean existe = false;
		
		try {
			
			if(this.personaDao.existePersona(numOficio, tipoOficio, numConsec) > 0){
				existe = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
		return existe;
	}

	public List<PersonaModel> buscarPersonasPorOficio(String numOficio,	String tipoOficio) throws ServiceException {
		List<PersonaModel> listPer = new ArrayList<PersonaModel>();
		
		try {
			listPer = this.personaDao.buscarPersonasPorOficio(numOficio, tipoOficio);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return listPer;
	}
	
	public int obtenerConsecutivo(String numOficio, String tipoOficio) throws ServiceException {
		try {
			return this.personaDao.obtenerConsecutivo(numOficio, tipoOficio);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}

}
