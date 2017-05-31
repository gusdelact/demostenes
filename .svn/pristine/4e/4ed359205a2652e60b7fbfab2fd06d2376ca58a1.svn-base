package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.AutoridadesDao;
import com.gfi.bin.admctasweb.catalogos.mapper.AutoridadesMapper;
import com.gfi.bin.admctasweb.catalogos.model.AutoridadesModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Component
@Repository
public class AutoridadesDaoImpl implements AutoridadesDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoridadesDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;

	/**
	 * Insertar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws DAOException
	 */	
	public boolean insertarAutoridad(AutoridadesModel autoridadesModel) throws DAOException {
		
		boolean resultado = false;
		
		if (autoridadesModel != null && autoridadesModel.getCveAutoridad() != null) {		
			
			StringBuilder sqlInsert = new StringBuilder();
			sqlInsert.append("INSERT INTO CNBV_AUTORIDAD");
			sqlInsert.append(" (CVE_AUTORIDAD, NOM_AUTORIDAD, SIT_AUTORIDAD,");
			sqlInsert.append("  FH_ALTA, CVE_USU_ALTA)");
			sqlInsert.append(" VALUES(?, ?, ?, ?, ?)");
			
			LOGGER.info(sqlInsert.toString());
			
			try {
				this.jdbcTemplateCorp.update(sqlInsert.toString(), new Object[] {
					autoridadesModel.getCveAutoridad(), autoridadesModel.getNomAutoridad(), autoridadesModel.getSitAutoridad(),
					autoridadesModel.getFhAlta(), autoridadesModel.getCveUsuAlta()
					});
				
				resultado = true;
			} catch (DataAccessException e) {
				String cadenaError = "Error al insertar en la tabla de Autoridades";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError,e);
			}
		} else {
			String cadenaError = "La Clave de Autoridad no puede ser nula";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return resultado;
	}

	/**
	 * Actualizar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws DAOException
	 */	
	public boolean actualizarAutoridad(AutoridadesModel autoridadesModel) throws DAOException {
		
		boolean resultado = false;
		
		if (autoridadesModel != null && autoridadesModel.getCveAutoridad() != null) {
			
			StringBuilder sqlUpdate = new StringBuilder();
			sqlUpdate.append("UPDATE CNBV_AUTORIDAD");
			sqlUpdate.append(" SET NOM_AUTORIDAD = ?,");
			sqlUpdate.append("     SIT_AUTORIDAD = ?,");
			sqlUpdate.append("     FH_ULT_MOD = ?,");
			sqlUpdate.append("     CVE_USU_MOD = ?");
			sqlUpdate.append(" WHERE CVE_AUTORIDAD = ?");

			LOGGER.info(sqlUpdate.toString());
			
			try {
				this.jdbcTemplateCorp.update(sqlUpdate.toString(), new Object[] {
					autoridadesModel.getNomAutoridad(), autoridadesModel.getSitAutoridad(),
					autoridadesModel.getFhUltMod(),autoridadesModel.getCveUsuMod(),
					autoridadesModel.getCveAutoridad()
					});
				
				resultado = true;
			} catch (DataAccessException e) {
				String cadenaError = "Error al actualiza en la tabla de Autoridades";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError,e);
			}
		} else {
			String cadenaError = "La Clave de Autoridad no puede ser nula";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return resultado;
	}

	/**
	 * Eliminar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws DAOException
	 */	
	public boolean eliminarAutoridad(AutoridadesModel autoridadesModel) throws DAOException {
		
		boolean resultado = false;

		if (autoridadesModel != null && autoridadesModel.getCveAutoridad() != null) {

			StringBuffer sqlDelete = new StringBuffer();
			sqlDelete.append("DELETE FROM CNBV_AUTORIDAD");
			sqlDelete.append(" WHERE CVE_AUTORIDAD = ?");

			LOGGER.info(sqlDelete.toString());
			
			try {
				this.jdbcTemplateCorp.update(sqlDelete.toString(), new Object[] {
					autoridadesModel.getCveAutoridad()
					});
				
				resultado = true;
			} catch (DataAccessException e) {
				String cadenaError = "Error al eliminar en la tabla de Autoridades";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError,e);
			}
		} else {
			String cadenaError = "La Clave de Autoridad no puede ser nula";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}		
		return resultado;
	}

	/**
	 * Consultar Autoridades
	 * 
	 * @param AutoridadesModel
	 * @return List<AutoridadesModel>
	 * @throws DAOException
	 */	
	public List<AutoridadesModel> consultarAutoridades(AutoridadesModel autoridadesModel) throws DAOException {
		
		List<AutoridadesModel> autoridadesModelList = null;

		StringBuilder query = new StringBuilder();
		query.append("SELECT CVE_AUTORIDAD, NOM_AUTORIDAD, SIT_AUTORIDAD, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD");
		query.append(" FROM CNBV_AUTORIDAD WHERE 1=1");

		if (autoridadesModel != null) {
			if (autoridadesModel.getCveAutoridad() != null && StringUtils.isNotBlank(autoridadesModel.getCveAutoridad())) {
				query.append(" AND CVE_AUTORIDAD = '" + autoridadesModel.getCveAutoridad() + "'");
			} 
			if (autoridadesModel.getNomAutoridad() != null && StringUtils.isNotBlank(autoridadesModel.getNomAutoridad())) {
				query.append(" AND NOM_AUTORIDAD LIKE '%" + autoridadesModel.getNomAutoridad() + "%'");
			} 
			if (autoridadesModel.getSitAutoridad() != null && StringUtils.isNotBlank(autoridadesModel.getSitAutoridad())) {
				query.append(" AND SIT_AUTORIDAD = '" + autoridadesModel.getSitAutoridad() + "'");
			}
		}
		query.append(" ORDER BY CVE_AUTORIDAD");

		LOGGER.info(query.toString());

		try {				
			autoridadesModelList = (List<AutoridadesModel>)
					this.jdbcTemplateCorp.query(query.toString(), new AutoridadesMapper());
		} catch (DataAccessException e) {
			String cadenaError = "No existen datos en la tabla de Autoridades";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError, e);
		}
		return autoridadesModelList;
	}

	/**
	 * Consultar Autoridades por Clave
	 * 
	 * @param AutoridadesModel
	 * @return AutoridadesModel
	 * @throws DAOException
	 */	
	public AutoridadesModel consultarAutoridadPorClave(AutoridadesModel autoridadesModel) throws DAOException {		
		
		if (autoridadesModel != null && autoridadesModel.getCveAutoridad() != null) {
			StringBuilder query = new StringBuilder();
			query.append("SELECT CVE_AUTORIDAD, NOM_AUTORIDAD, SIT_AUTORIDAD, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD");
			query.append(" FROM CNBV_AUTORIDAD");
			query.append(" WHERE CVE_AUTORIDAD = '" + autoridadesModel.getCveAutoridad() + "'");
			
			LOGGER.info(query.toString());

			try {				
				autoridadesModel = jdbcTemplateCorp.queryForObject(query.toString(), new AutoridadesMapper());
				
			} catch (DataAccessException e) {
				String cadenaError = "No existen datos en la tabla de Autoridades";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError, e);
			}
		} else {
			String cadenaError = "La Clave de Autoridad no puede ser nula";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return autoridadesModel;
	}

	/**
	 * Existe en el cat&aacute;logo de autoridades 
	 * 
	 * @param AutoridadesModel
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean existeAutoridad(AutoridadesModel autoridadesModel) throws DAOException {
		boolean existe = false;
		
		if (autoridadesModel != null && autoridadesModel.getCveAutoridad() != null){
			StringBuilder query = new StringBuilder();
			query.append(" SELECT COUNT(*)");
			query.append(" FROM CNBV_AUTORIDAD"); 
			query.append(" WHERE CVE_AUTORIDAD = ?");
			
			LOGGER.info(query.toString());
			
			try {
				existe = (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{autoridadesModel.getCveAutoridad()}, Integer.class)) > 0 ? true: false;   
			} catch (DataAccessException e) {
				String cadenaError = "No existen datos en la tabla de Autoridades";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError, e);
			}
		} else{
			String cadenaError = "La Clave de Autoridad no puede ser nula";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return existe;
	}
}
