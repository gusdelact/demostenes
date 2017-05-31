package com.gfi.bin.admctasweb.procesoautomatico.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.procesoautomatico.dao.BitacoraCargaAutomaticaDAO;
import com.gfi.bin.admctasweb.procesoautomatico.mapper.BitacoraCargaAutomaticaMapper;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;

/**
 * @author ESS3ESPP
 *
 */
@Repository
public class BitacoraCargaAutomaticaDAOImpl implements BitacoraCargaAutomaticaDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraCargaAutomaticaDAOImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private OracleSequenceMaxValueIncrementer cargaAutomaticaIncrementer;
	
	/**
	 * Guarda registro de carga automática en bitácora
	 * 
	 * @param bitacoraCargaAutomaticaModel
	 * @return Boolean
	 * @throws DAOException
	 */
	public Boolean guardarCargaAutomatica(BitacoraCargaAutomaticaModel bitacoraCargaAutomatica) throws DAOException {
		LOGGER.debug("Inicia guardarCargaAutomatica");
		Boolean resultado = false;
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO CNBV_BIT_CGA_AUT");
		query.append("(ID_CARGA, ID_EMPRESA, EXCEL_CONCILIA, FH_REG)");
		query.append("values (?, ?, ?, ?)");
		
		try{
			int res = jdbcTemplateCorp.update(query.toString(), new Object[] 
								   {bitacoraCargaAutomatica.getIdCarga(), 
									bitacoraCargaAutomatica.getIdEmpresa(),
									bitacoraCargaAutomatica.getExcelConciliador(),
									bitacoraCargaAutomatica.getFechaHoraRegistro()});
			resultado = res>0;
		}catch(DataAccessException dataAccessException){
			LOGGER.error(dataAccessException.getLocalizedMessage());
			throw new DAOException(dataAccessException);
		}
		return resultado;
	}
	
	/**
	 * Busca registros de carga automática en bitácora por fecha
	 * 
	 * @param fecha
	 * @return List<BitacoraCargaAutomaticaModel>
	 * @throws DAOException
	 */
	public List<BitacoraCargaAutomaticaModel> buscarCargaAutomaticaPorFecha(Date fecha) throws DAOException {
		LOGGER.debug("Inicia buscarCargaAutomaticaPorFecha, fecha es: "+fecha);
		List<BitacoraCargaAutomaticaModel> listaCargasAutomaticas =  new ArrayList<BitacoraCargaAutomaticaModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ID_CARGA, ID_EMPRESA, EXCEL_CONCILIA, FH_REG ");
		query.append(" FROM CNBV_BIT_CGA_AUT ");
		query.append(" WHERE FH_REG = ? ");
		
		listaCargasAutomaticas = jdbcTemplateCorp.query(query.toString(), new Object[]{fecha}, new BitacoraCargaAutomaticaMapper());
		
		return listaCargasAutomaticas;
	}
	
	/**
	 * Obtiene el siguiente valor de la secuencia para la inserción de registros en bitacora de carga automática
	 * 
	 * @return Integer
	 * @throws DAOException
	 */
	public Integer obtenerSiguienteValorSecuencia() throws DAOException{
		LOGGER.debug("Inicia obtenerSiguienteValorSecuencia");
		Integer valorSecuencia = 0;
		try{
			valorSecuencia = cargaAutomaticaIncrementer.nextIntValue();
			
		}catch(DataAccessException dataAccessException){
			LOGGER.error("Error al obtener siguiente valor de secuencia");
			
			throw new DAOException(dataAccessException);
		}
		return valorSecuencia;
	}
}