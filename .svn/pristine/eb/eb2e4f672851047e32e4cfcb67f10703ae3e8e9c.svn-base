package com.gfi.bin.admctasweb.procesoautomatico.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.dao.BitacoraCargaAutomaticaDAO;
import com.gfi.bin.admctasweb.procesoautomatico.dao.BitacoraCargaAutomaticaOficioDAO;
import com.gfi.bin.admctasweb.procesoautomatico.dao.BitacoraCargaAutomaticaPersonaDAO;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaOficioModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaPersonaModel;
import com.gfi.bin.admctasweb.procesoautomatico.service.BitacoraCargaAutomaticaService;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Service
public class BitacoraCargaAutomaticaServiceImpl implements BitacoraCargaAutomaticaService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraCargaAutomaticaServiceImpl.class);
	
	@Autowired
	private BitacoraCargaAutomaticaDAO bitacoraCargaAutomaticaDAO;
	
	@Autowired
	private BitacoraCargaAutomaticaOficioDAO bitacoraCargaAutomaticaOficioDAO;
	
	@Autowired
	private BitacoraCargaAutomaticaPersonaDAO bitacoraCargaAutomaticaPersonaDAO;
	
	/**
	 * Guarda registro de carga automática en bitácora (Carga, Oficios y Personas)
	 * 
	 * @param listaBitCargaAutomatica
	 * @return List<BitacoraCargaAutomaticaModel>
	 * @throws ServiceException
	 */
	@Override
	public List<BitacoraCargaAutomaticaModel> guardarCargaAutomatica(List<BitacoraCargaAutomaticaModel> listaBitCargaAutomatica) throws ServiceException {
		LOGGER.debug("Comienza guardarCargaAutomatica");
		Integer idCargaAutomatica = 0;
		try{
			idCargaAutomatica = bitacoraCargaAutomaticaDAO.obtenerSiguienteValorSecuencia();

			for(BitacoraCargaAutomaticaModel cargaAutomatica : listaBitCargaAutomatica){
				cargaAutomatica.setIdCarga(idCargaAutomatica);
				bitacoraCargaAutomaticaDAO.guardarCargaAutomatica(cargaAutomatica);
				
				if(cargaAutomatica.getListaOficios() != null)
					for(BitacoraCargaAutomaticaOficioModel oficioCargaAutomatica : cargaAutomatica.getListaOficios()){
						oficioCargaAutomatica.setIdCarga(cargaAutomatica.getIdCarga());
						bitacoraCargaAutomaticaOficioDAO.guardarCargaAutomaticaOficio(oficioCargaAutomatica);
						
						if(oficioCargaAutomatica.getListaPersonas()!=null)
							for(BitacoraCargaAutomaticaPersonaModel personaCargaAutomatica : oficioCargaAutomatica.getListaPersonas()){
								personaCargaAutomatica.setIdCarga(oficioCargaAutomatica.getIdCarga());
								bitacoraCargaAutomaticaPersonaDAO.guardarCargaAutomaticaPersona(personaCargaAutomatica);
							}
					}
			}
		}
		catch(DAOException daoException){
			LOGGER.error(daoException.getLocalizedMessage());
		}
		return listaBitCargaAutomatica;
	}
	
	/**
	 * Obtiene el siguiente valor de la secuencia para la inserción de registros en bitacora de carga automática
	 * 
	 * @return Integer
	 * @throws ServiceException
	 */
	@Override
	public Integer obtenerSiguienteValorSecuencia() throws ServiceException {
		LOGGER.debug("Comienza obtenerSiguienteValorSecuencia");
		Integer valorSecuencia = 0;
		try{
			valorSecuencia = bitacoraCargaAutomaticaDAO.obtenerSiguienteValorSecuencia();
			
		}catch(DAOException daoException){
			LOGGER.error("Error al obtener valor secuencia");
			throw new ServiceException(daoException);
		}
		return valorSecuencia;
	}
}