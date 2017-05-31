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
import com.gfi.bin.admctasweb.procesoautomatico.dao.BitacoraCargaAutomaticaOficioDAO;
import com.gfi.bin.admctasweb.procesoautomatico.mapper.BitacoraCargaAutomaticaOficioMapper;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaOficioModel;

/**
 * @author ESS3ESPP
 *
 */
@Repository
public class BitacoraCargaAutomaticaOficioDAOImpl implements BitacoraCargaAutomaticaOficioDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraCargaAutomaticaOficioModel.class);
	
	@Autowired
	JdbcTemplate jdbcTemplateCorp;
	
	/**
	 * Guarda registro de carga automática de bitácoraOficio
	 * 
	 * @param bitacoraCargaAutomaticaOficio
	 * @return Boolean
	 * @throws DAOException
	 */
	@Override
	public Boolean guardarCargaAutomaticaOficio(BitacoraCargaAutomaticaOficioModel bitacoraCargaAutomaticaOficio) throws DAOException{
		LOGGER.debug("Inicia guardarCargaAutomaticaOficio");
		Boolean resultado = false;
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO CNBV_BIT_CGA_AUT_OFICIO");
		query.append("(ID_CARGA, ID_EMPRESA, NUM_OFICIO, TIPO_OFICIO, EXISTE_XML)");
		query.append("values (?, ?, ?, ?, ?)");
		
		try{
			int res = jdbcTemplateCorp.update(query.toString(), new Object[] 
								   {bitacoraCargaAutomaticaOficio.getIdCarga(), 
									bitacoraCargaAutomaticaOficio.getIdEmpresa(),
									bitacoraCargaAutomaticaOficio.getNumeroOficio(),
									bitacoraCargaAutomaticaOficio.getTipoOficio(),
									bitacoraCargaAutomaticaOficio.getExisteXML()});
			resultado = res>0;
		}catch(DataAccessException dataAccessException){
			LOGGER.error(dataAccessException.getLocalizedMessage());
			throw new DAOException(dataAccessException);
		}
		
		return resultado;
	}
	
	/**
	 * Busca registros de carga automática Oficio por carga
	 * 
	 * @return List<BitacoraCargaAutomaticaOficioModel>
	 * @throws DAOException
	 */
	@Override
	public List<BitacoraCargaAutomaticaOficioModel> buscarOficiosCargaAutomaticaPorCarga(Integer idCarga, Integer idEmpresa) throws DAOException {
		LOGGER.debug("Inicia buscarOficiosCargaAutomaticaPorCarga");
		List<BitacoraCargaAutomaticaOficioModel> listaOficiosCarga =  new ArrayList<BitacoraCargaAutomaticaOficioModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ID_CARGA, ID_EMPRESA, NUM_OFICIO, TIPO_OFICIO, EXISTE_XML");
		query.append(" FROM CNBV_BIT_CGA_AUT_OFICIO ");
		query.append(" WHERE ID_CARGA = ? ");
		query.append(" AND ID_EMPRESA = ? ");
		
		listaOficiosCarga = jdbcTemplateCorp.query(query.toString(), new Object[]{idCarga, idEmpresa}, new BitacoraCargaAutomaticaOficioMapper());
		
		return listaOficiosCarga;
	}
}