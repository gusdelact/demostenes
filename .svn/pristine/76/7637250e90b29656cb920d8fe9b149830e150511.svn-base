package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.ParametrosDao;
import com.gfi.bin.admctasweb.catalogos.mapper.ParametrosMapper;
import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Component
@Repository
public class ParametrosDaoImpl implements ParametrosDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParametrosDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;

	/**
	 * Insertar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws DAOException
	 */	
	public boolean insertarParametro(ParametrosModel parametrosModel) throws DAOException {
		
		boolean resultado = false;
		
		if (parametrosModel != null && parametrosModel.getCveGpoParam() != null && parametrosModel.getCveParam() != null) {		
			
			StringBuilder sqlInsert = new StringBuilder();
			sqlInsert.append("INSERT INTO CNBV_PARAMETRO");
			sqlInsert.append(" (CVE_GPO_PARAM, CVE_PARAMETRO, TX_PARAMETRO, SIT_PARAMETRO,");
			sqlInsert.append("  FH_ALTA, CVE_USU_ALTA)");
			sqlInsert.append(" VALUES(?, ?, ?, ?, ?, ?)");
			
			LOGGER.info(sqlInsert.toString());
			
			try {
				this.jdbcTemplateCorp.update(sqlInsert.toString(), new Object[] {
					parametrosModel.getCveGpoParam(), parametrosModel.getCveParam(), 
					parametrosModel.getDescParam(), parametrosModel.getSitParam(),
					parametrosModel.getFhAlta(), parametrosModel.getCveUsuAlta()
					});
				
				resultado = true;
			} catch (DataAccessException e) {
				String cadenaError = "Error al insertar en la tabla de Par\u00e1metros de Oficios CNBV";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError,e);
			}
		} else {
			String cadenaError = "La Clave de Grupo y/o la Clave de Par\u00e1metro no pueden ser nulas";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return resultado;
	}

	/**
	 * Actualizar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws DAOException
	 */	
	public boolean actualizarParametro(ParametrosModel parametrosModel) throws DAOException {
		
		boolean resultado = false;
		
		if (parametrosModel != null && parametrosModel.getCveGpoParam() != null && parametrosModel.getCveParam() != null) {
			
			StringBuilder sqlUpdate = new StringBuilder();
			sqlUpdate.append("UPDATE CNBV_PARAMETRO");
			sqlUpdate.append(" SET TX_PARAMETRO = ?,");
			sqlUpdate.append("     SIT_PARAMETRO = ?,");
			sqlUpdate.append("     FH_ULT_MOD = ?,");
			sqlUpdate.append("     CVE_USU_MOD = ?");
			sqlUpdate.append(" WHERE CVE_GPO_PARAM = ? AND CVE_PARAMETRO = ?");

			LOGGER.info(sqlUpdate.toString());
			
			try {
				this.jdbcTemplateCorp.update(sqlUpdate.toString(), new Object[] {
					parametrosModel.getDescParam(), parametrosModel.getSitParam(),
					parametrosModel.getFhUltMod(), parametrosModel.getCveUsuMod(),
					parametrosModel.getCveGpoParam(), parametrosModel.getCveParam()
					});
				
				resultado = true;
			} catch (DataAccessException e) {
				String cadenaError = "Error al actualiza en la tabla de Par\u00e1metros de Oficios CNBV";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError,e);
			}
		} else {
			String cadenaError = "La Clave de Grupo y/o la Clave de Par\u00e1metro no pueden ser nulas";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return resultado;
	}

	/**
	 * Eliminar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws DAOException
	 */	
	public boolean eliminarParametro(ParametrosModel parametrosModel) throws DAOException {
		
		boolean resultado = false;

		if (parametrosModel != null && parametrosModel.getCveGpoParam() != null && parametrosModel.getCveParam() != null) {

			StringBuffer sqlDelete = new StringBuffer();
			sqlDelete.append("DELETE FROM CNBV_PARAMETRO");
			sqlDelete.append(" WHERE CVE_GPO_PARAM = ? AND CVE_PARAMETRO = ?");

			LOGGER.info(sqlDelete.toString());
			
			try {
				this.jdbcTemplateCorp.update(sqlDelete.toString(), new Object[] {
					parametrosModel.getCveGpoParam(), parametrosModel.getCveParam()
					});
				
				resultado = true;
			} catch (DataAccessException e) {
				String cadenaError = "Error al eliminar en la tabla de Par\u00e1metros de Oficios CNBV";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError,e);
			}
		} else {
			String cadenaError = "La Clave de Grupo y/o la Clave de Par\u00e1metro no pueden ser nulas";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}		
		return resultado;
	}

	/**
	 * Consultar Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return List<ParametrosModel>
	 * @throws DAOException
	 */	
	public List<ParametrosModel> consultarParametros(ParametrosModel parametrosModel) throws DAOException {
		
		List<ParametrosModel> parametrosModelList = null;

		StringBuilder query = new StringBuilder();
		query.append("SELECT CVE_GPO_PARAM, CVE_PARAMETRO, TX_PARAMETRO, SIT_PARAMETRO, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD");
		query.append(" FROM CNBV_PARAMETRO WHERE 1=1");

		if (parametrosModel != null) {			
			if (parametrosModel.getCveGpoParam() != null && StringUtils.isNotBlank(parametrosModel.getCveGpoParam())) {
				query.append(" AND CVE_GPO_PARAM = '" + parametrosModel.getCveGpoParam() + "'");
			}
			if (parametrosModel.getCveParam() != null && StringUtils.isNotBlank(parametrosModel.getCveParam())) {
				query.append(" AND CVE_PARAMETRO = '" + parametrosModel.getCveParam() + "'");
			} 
			if (parametrosModel.getDescParam() != null && StringUtils.isNotBlank(parametrosModel.getDescParam())) {
				query.append(" AND TX_PARAMETRO LIKE '%" + parametrosModel.getDescParam() + "%'");
			} 
			if (parametrosModel.getSitParam() != null && StringUtils.isNotBlank(parametrosModel.getSitParam())) {
				query.append(" AND SIT_PARAMETRO = '" + parametrosModel.getSitParam() + "'");
			}
		}
		query.append(" ORDER BY CVE_GPO_PARAM, CVE_PARAMETRO");
		
		LOGGER.info(query.toString());

		try {				
			parametrosModelList = (List<ParametrosModel>)
					this.jdbcTemplateCorp.query(query.toString(), new ParametrosMapper());
			
		} catch (DataAccessException e) {
			String cadenaError = "No existen datos en la tabla de Par\u00e1metros de Oficios CNBV";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError, e);
		}
		return parametrosModelList;
	}

	/**
	 * Consultar Par&aacute;metros de Oficios CNBV por Clave
	 * 
	 * @param ParametrosModel
	 * @return ParametrosModel
	 * @throws DAOException
	 */	
	public ParametrosModel consultarParametroPorClave(ParametrosModel parametrosModel) throws DAOException {		
		
		if (parametrosModel != null && parametrosModel.getCveGpoParam() != null && parametrosModel.getCveParam() != null) {
			StringBuilder query = new StringBuilder();
			query.append("SELECT CVE_GPO_PARAM, CVE_PARAMETRO, TX_PARAMETRO, SIT_PARAMETRO, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD");
			query.append(" FROM CNBV_PARAMETRO");
			query.append(" WHERE CVE_GPO_PARAM = '" + parametrosModel.getCveGpoParam() + "'");
			query.append(" AND CVE_PARAMETRO = '" + parametrosModel.getCveParam() + "'");
			
			LOGGER.info(query.toString());

			try {				
				parametrosModel = jdbcTemplateCorp.queryForObject(query.toString(), new ParametrosMapper());
				
			} catch (DataAccessException e) {
				String cadenaError = "No existen datos en la tabla de Par\u00e1metros de Oficios CNBV";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError, e);
			}
		} else {
			String cadenaError = "La Clave de Grupo y/o la Clave de Par\u00e1metro no pueden ser nulas";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return parametrosModel;
	}

	/**
	 * Existe en el Cat&aacute;logo de Par&aacute;metros de Oficios CNBV
	 * 
	 * @param ParametrosModel
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean existeParametro(ParametrosModel parametrosModel) throws DAOException {
		boolean existe = false;
		
		if (parametrosModel != null && parametrosModel.getCveGpoParam() != null && parametrosModel.getCveParam() != null){
			StringBuilder query = new StringBuilder();
			query.append(" SELECT COUNT(*)");
			query.append(" FROM CNBV_PARAMETRO"); 
			query.append(" WHERE CVE_GPO_PARAM = ?");
			query.append(" AND CVE_PARAMETRO = ?");
			
			LOGGER.info(query.toString());
			
			try {
				existe = (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{parametrosModel.getCveGpoParam(), parametrosModel.getCveParam()}, Integer.class)) > 0 ? true: false;   
			} catch (DataAccessException e) {
				String cadenaError = "No existen datos en la tabla de Par\u00e1metros de Oficios CNBV";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError, e);
			}
		} else{
			String cadenaError = "La Clave de Grupo y/o la Clave de Par\u00e1metro no pueden ser nulas";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return existe;
	}
	
	/**
	 * Obtiene el folio para la generacion del archivo CNBV
	 * @param tipo
	 * @param idEmpresa
	 * @param dateFormatddMMyyyy
	 * @param cveUsuAlta
	 * @return
	 * @throws DAOException
	 */
	@Override
	public String obtenerFolioArchivoCnbv(String tipo,Integer idEmpresa, String fechaddMMyyyy, String cveUsuAlta) {
		String respuesta = "";		
		try {
			
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplateCorp)
													.withSchemaName("ADMIPROD")
													.withCatalogName("PKG_ADMCTAWEB")
													.withProcedureName("STP_FOLIOARCHIVO");
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("PEGPOPARAM", tipo+idEmpresa);
			inParamMap.put("PECVEPARAM", fechaddMMyyyy);
			inParamMap.put("PECVEUSU", cveUsuAlta);
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);

			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			respuesta = (String) simpleJdbcCallResult.get("PSRESULTADO");

			return respuesta;			
		} 
		catch (DataAccessException e) {
			LOGGER.error("Error al obtener el folio del archivo" + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return "";
	}
	

}

