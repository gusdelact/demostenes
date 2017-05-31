package com.gfi.bin.admctasweb.operativos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.catalogos.model.SimilaridadModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.dao.ExcluNomContratoDAO;
import com.gfi.bin.admctasweb.operativos.dao.PersonaCorporativaDAO;
import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;
import com.gfi.bin.admctasweb.operativos.model.PersonaCorporativaModel;
import com.gfi.bin.admctasweb.operativos.service.PersonaCorporativaService;

/**Implementación de servicios para personas corporativas
 * @author ESS3VAVC
 *
 */
@Service
public class PersonaCorporativaServiceImpl implements PersonaCorporativaService {

	@Autowired
	PersonaCorporativaDAO personaCorporativaDAO;
	
	@Autowired
	ExcluNomContratoDAO excluNomContratoDAO;
	
	/**
	 * Obtiene Coincidencia de personas de acuerdo a los filtros proporcionados
	 * 
	 * @param rfc
	 * @param nombre
	 * @param tipoPersona (Física o Moral)
	 * @return
	 * @throws ServiceException
	 */
	public List<PersonaCorporativaModel> obtenerPersonas(String rfc, String nombre, Integer similaridad) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			List<PersonaCorporativaModel> personas = new ArrayList<PersonaCorporativaModel>();
			
			List<ExclusionModel> listaExclusiones = excluNomContratoDAO.obtenerExclusiones(true);
			
			if(StringUtils.isNotBlank(nombre))
				personas = personaCorporativaDAO.obtenerPersonas(rfc, nombre, similaridad, listaExclusiones);
			else if(StringUtils.isNotBlank(rfc))//Se agrega caso en que solo venga RFC
				personas = personaCorporativaDAO.obtenerPersonas(rfc, similaridad);
			
			//Si no encuentra por persona, trata solo por rfc si es válido
//			if((personas == null || personas.isEmpty()) && StringUtils.isNotBlank(rfc))
//			{ 
//				personas = personaCorporativaDAO.obtenerPersonas(rfc, similaridad);
//			}
			
			return personas;
		} 
		catch (DAOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new ServiceException(e);
		}
	}

	/**
	 * Servicio para búsqueda de personas por RFC
	 * @param rfc
	 * @param similaridad
	 * @return
	 * @throws ServiceException
	 */
	public List<PersonaCorporativaModel> obtenerPersonas(String rfc, Integer similaridad) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return personaCorporativaDAO.obtenerPersonas(rfc, similaridad);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	/**
	 * Obtiene porcentaje de coincidencia para las consultas de personas
	 */
	public SimilaridadModel obtenerSimilaridad() throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return personaCorporativaDAO.obtenerSimilaridad();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	/**
	 * Obtiene un registro de Persona Corporativa por su Id.
	 * @return PersonaCorporativaModel
	 * @throws ServiceException
	 */
	public PersonaCorporativaModel obtenerPersonaPorId(Long idPersona)
			throws ServiceException {
		try {
			return personaCorporativaDAO.obtenerPersonaPorId(idPersona);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * Modifica el porcentaje de similaridad guardado en la BD
	 */
	@Override
	public boolean modificarSimilaridad(SimilaridadModel similaridadModel) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return personaCorporativaDAO.modificarSimilaridad(similaridadModel);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

}
