package com.gfi.bin.admctasweb.operativos.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.dao.ExcluNomContratoDAO;
import com.gfi.bin.admctasweb.operativos.mapper.ExclusionMapper;
import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;

/**
 * Implementación de métodos para acceder a tabla de exclusiones para la búsqueda de personas en Contratos
 * 
 * @author ESS3ESPP
 *
 */
@Repository
public class ExcluNomContratoDAOImpl implements ExcluNomContratoDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private OracleSequenceMaxValueIncrementer exclusionesIncrementer;
	
	private final Logger logger = LoggerFactory.getLogger(ExcluNomContratoDAOImpl.class);
	
	@Override
	public List<ExclusionModel> obtenerExclusiones(Boolean estatus) throws DAOException {
		logger.info("obtenerExclusiones con estatus: "+estatus);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id_exclusion, \n");
		sql.append(" desc_exclusion, \n");
		sql.append(" activo \n");
		sql.append("FROM cnbv_cat_exclu_nom_cto \n");
		sql.append("WHERE activo = ? ");
		
		sql.append(" order by length(desc_exclusion) desc");
		
		try {
			return jdbcTemplateCorp.query(sql.toString(), new Object[] {estatus}, new ExclusionMapper());
		} 
		catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public List<ExclusionModel> obtenerExclusiones() throws DAOException {
		logger.info("obtenerExclusiones");
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id_exclusion, \n");
		sql.append(" desc_exclusion, \n");
		sql.append(" activo \n");
		sql.append("FROM cnbv_cat_exclu_nom_cto \n");
		
		try {
			return jdbcTemplateCorp.query(sql.toString(), new ExclusionMapper());
		} 
		catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public List<ExclusionModel> obtenerExclusionPorNombre(String nombreExclusion) throws DAOException {
		logger.info("obtenerExclusionPorNombre, nombre: "+nombreExclusion);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id_exclusion, \n");
		sql.append(" desc_exclusion, \n");
		sql.append(" activo \n");
		sql.append("FROM cnbv_cat_exclu_nom_cto \n");
		sql.append("WHERE desc_exclusion like '%"+nombreExclusion+"%'");
		
		try {
			return jdbcTemplateCorp.query(sql.toString(), new ExclusionMapper());
		} 
		catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	public ExclusionModel obtenerExclusionPorId(Integer idExclusion) throws DAOException{
		logger.info("obtenerExclusionPorId, id: "+idExclusion);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT id_exclusion, \n");
		sql.append(" desc_exclusion, \n");
		sql.append(" activo \n");
		sql.append("FROM cnbv_cat_exclu_nom_cto \n");
		sql.append("WHERE id_exclusion = ? ");
		
		try {
			return jdbcTemplateCorp.queryForObject(sql.toString(), new Object[] {idExclusion}, new ExclusionMapper());
		} 
		catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Boolean insertarExclusion(ExclusionModel exclusionModel) throws DAOException {
		logger.debug("insertarExclusion; exclusionModel.getId(): "+exclusionModel.getIdExclusion()+", exclusionModel.getDescripcion(): "+exclusionModel.getDescripcionExclusion()+", exclusionModel.getActivo(): "+exclusionModel.getActivo());
		Boolean resultado =  false;
		
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO CNBV_CAT_EXCLU_NOM_CTO");
		query.append(" (ID_EXCLUSION, DESC_EXCLUSION, ACTIVO)");
		query.append(" VALUES (?, ?, ?)");
		
		try {
			int res = jdbcTemplateCorp.update(query.toString(), 
										exclusionModel.getIdExclusion(), 
										exclusionModel.getDescripcionExclusion(), 
										exclusionModel.getActivo()
									);
			resultado = res > 0;
		} catch (DataAccessException e) {
			logger.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		return resultado;
	}

	@Override
	public Boolean actualizarExclusion(ExclusionModel exclusionModel) throws DAOException {
		logger.debug("actualizarExclusion; exclusionModel.getId(): "+exclusionModel.getIdExclusion()+", exclusionModel.getDescripcion(): "+exclusionModel.getDescripcionExclusion()+", exclusionModel.getActivo(): "+exclusionModel.getActivo());
		Boolean resultado =  false;
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE CNBV_CAT_EXCLU_NOM_CTO");
		query.append(" SET DESC_EXCLUSION = ?, ");
		query.append(" ACTIVO = ? ");
		query.append(" WHERE ID_EXCLUSION = ?");
		
		try {
			int res = jdbcTemplateCorp.update(query.toString(), 
										exclusionModel.getDescripcionExclusion(), 
										exclusionModel.getActivo(),
										exclusionModel.getIdExclusion()
									);
			resultado = res > 0;
		} catch (DataAccessException e) {
			logger.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		return resultado;
	}
	
	public Boolean eliminarExclusion(Integer idExclusion) throws DAOException{
		logger.debug("eliminarExclusion; idExclusion: "+idExclusion);
		Boolean resultado =  false;
		
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM CNBV_CAT_EXCLU_NOM_CTO");
		query.append(" WHERE ID_EXCLUSION = ?");
		
		try {
			int res = jdbcTemplateCorp.update(query.toString(), idExclusion);
			
			resultado = res > 0;
		} catch (DataAccessException e) {
			logger.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		return resultado;
	}
	
	@Override
	public Integer obtenerSiguienteValorSecuencia() throws DAOException{
		logger.debug("Inicia obtenerSiguienteValorSecuencia Exclusiones");
		Integer valorSecuencia = 0;
		try{
			logger.debug("incrementerName: "+exclusionesIncrementer.getIncrementerName());
			valorSecuencia = exclusionesIncrementer.nextIntValue();
			
			logger.debug("valorSecuencia: "+valorSecuencia);
		}catch(DataAccessException dataAccessException){
			logger.error("Error al obtener siguiente valor de secuencia de Exclusiones");
			
			throw new DAOException(dataAccessException);
		}
		return valorSecuencia;
	}
}