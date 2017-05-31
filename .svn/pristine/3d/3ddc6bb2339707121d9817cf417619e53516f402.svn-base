package com.gfi.bin.admctasweb.procesoautomatico.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.procesoautomatico.dao.BitacoraCargaAutomaticaPersonaDAO;
import com.gfi.bin.admctasweb.procesoautomatico.mapper.BitacoraCargaAutomaticaPersonaMapper;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaPersonaModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Repository
public class BitacoraCargaAutomaticaPersonaDAOImpl implements BitacoraCargaAutomaticaPersonaDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraCargaAutomaticaPersonaDAOImpl.class);
	
	@Autowired
	JdbcTemplate jdbcTemplateCorp;
	
	/**
	 * Guarda registro de carga automática de bitácoraPersona
	 * 
	 * @param bitacoraCargaAutomaticaPersona
	 * @return Boolean
	 * @throws DAOException
	 */
	@Override
	public Boolean guardarCargaAutomaticaPersona(BitacoraCargaAutomaticaPersonaModel bitacoraCargaAutomaticaPersona) throws DAOException{
		LOGGER.debug("Inicia guardarCargaAutomaticaPersona");
		Boolean resultado = false;
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO CNBV_BIT_CGA_AUT_PERSONA");
		query.append("(ID_CARGA, ID_EMPRESA, NUM_OFICIO, TIPO_OFICIO, NUM_CONSEC_PERSONA, OBSERVACIONES)");
		query.append("values (?, ?, ?, ?, ?, ?)");
		
		try{
			int res = jdbcTemplateCorp.update(query.toString(), new Object[] 
								   {bitacoraCargaAutomaticaPersona.getIdCarga(), 
									bitacoraCargaAutomaticaPersona.getIdEmpresa(),
									bitacoraCargaAutomaticaPersona.getNumeroOficio(),
									bitacoraCargaAutomaticaPersona.getTipoOficio(),
									bitacoraCargaAutomaticaPersona.getNumeroConsecutivoPersona(),
									bitacoraCargaAutomaticaPersona.getObservaciones()});
			resultado = res>0;
		}catch(DataAccessException dataAccessException){
			LOGGER.error(dataAccessException.getLocalizedMessage());
			throw new DAOException(dataAccessException);
		}
		
		return resultado;
	}
	
	/**
	 * Busca registros de carga automática Persona por carga
	 * 
	 * @param idCarga
	 * @param idEmpresa
	 * @param numeroOficio
	 * @param tipoOficio
	 * @return List<BitacoraCargaAutomaticaPersonaModel>
	 * @throws DAOException
	 */
	@Override
	public List<BitacoraCargaAutomaticaPersonaModel> buscarPersonasCargaAutomaticaPorCarga(Integer idCarga, Integer idEmpresa, String numeroOficio, String tipoOficio) throws DAOException {
		LOGGER.debug("Inicia buscarOficiosCargaAutomaticaPorCarga");
		List<BitacoraCargaAutomaticaPersonaModel> listaPersonasCarga =  new ArrayList<BitacoraCargaAutomaticaPersonaModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ID_CARGA, ID_EMPRESA, NUM_OFICIO, TIPO_OFICIO, NUM_CONSEC_PERSONA, OBSERVACIONES");
		query.append(" FROM CNBV_BIT_CGA_AUT_PERSONA ");
		query.append(" WHERE ID_CARGA = ? ");
		query.append(" AND ID_EMPRESA = ? ");
		query.append(" AND NUM_OFICIO = ? ");
		query.append(" AND TIPO_OFICIO = ? ");
		
		listaPersonasCarga = jdbcTemplateCorp.query(query.toString(), new Object[]{idCarga, idEmpresa, numeroOficio, tipoOficio}, new BitacoraCargaAutomaticaPersonaMapper());
		
		return listaPersonasCarga;
	}
}