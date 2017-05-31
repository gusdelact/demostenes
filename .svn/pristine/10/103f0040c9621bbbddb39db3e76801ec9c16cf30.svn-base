/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.dao.RespuestaDao;
import com.gfi.bin.admctasweb.operativos.mapper.RespuestaMapper;
import com.gfi.bin.admctasweb.operativos.model.RespuestaModel;

/**
 * @author lugl4884
 *
 */
@Component
@Repository
public class RespuestaDaoImpl implements RespuestaDao {
	
	final Logger log = LoggerFactory.getLogger(RespuestaDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.dao.RespuestaDao#guardarRespuesta
	 */
	public boolean guardarRespuesta(RespuestaModel respuesta) throws DAOException {
		log.info("En el método guardarRespuesta");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO CNBV_RESPUESTA ( ");
		query.append(" NUM_OFICIO, TIPO_OFICIO, NUM_CONSEC, ID_CONTRATO, TIPO_RESPUESTA, ");
		query.append(" TIPO_CASO, SIT_ENVIO, B_MEDIO_ELEC, NOMB_TITULAR, TIPO_TITULAR, "); 
		query.append(" SIT_CUENTA, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD) "); 
		query.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
		
		try {
			jdbcTemplateCorp.update(query.toString(), respuesta.getNumOficio(), respuesta.getTipoOficio(),
							respuesta.getNumConsec(), respuesta.getIdContrato(), respuesta.getTipoRespuesta(),
							respuesta.getTipoCaso(), respuesta.getSitEnvio(), respuesta.getbMedioElec(),
							respuesta.getNomTitular(), respuesta.getTipoTitular(), respuesta.getSitCuenta(), 
							respuesta.getFhAlta(), respuesta.getCveUsuAlta(), respuesta.getFhUltMod(), respuesta.getCveUsuMod());
			result = true;
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.dao.RespuestaDao#eliminarRespuesta
	 */
	public boolean eliminarRespuesta(RespuestaModel respuesta) throws DAOException {
		log.info("En el método eliminarRespuesta");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM CNBV_RESPUESTA ");
		query.append(" WHERE NUM_OFICIO = ? AND TIPO_OFICIO = ? AND NUM_CONSEC = ? AND ID_CONTRATO = ? ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), respuesta.getNumOficio(), respuesta.getTipoOficio(),
							respuesta.getNumConsec(), respuesta.getIdContrato());
			result = true;
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.dao.RespuestaDao#buscarRespuestasPorOficio
	 */
	public List<RespuestaModel> buscarRespuestasPorOficio(String numOficio,
			String tipoOficio) throws DAOException {
		log.info("En el método buscarRespuestasPorOficio");
		List<RespuestaModel> listResp =  new ArrayList<RespuestaModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT NUM_OFICIO, TIPO_OFICIO, NUM_CONSEC, ID_CONTRATO, ");
		query.append(" TIPO_RESPUESTA, TIPO_CASO, SIT_ENVIO, B_MEDIO_ELEC, "); 
		query.append(" NOMB_TITULAR, TIPO_TITULAR, SIT_CUENTA, FH_ALTA, "); 
		query.append(" CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD "); 
		query.append(" FROM CNBV_RESPUESTA "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		
		listResp = jdbcTemplateCorp.query(query.toString(), new Object[]{numOficio,tipoOficio}, new RespuestaMapper());
		
		return listResp;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.dao.RespuestaDao#existeRespuesta
	 */
	public int existeRespuesta(RespuestaModel respuesta) throws DAOException {
		log.info("En el método existeRespuesta");
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(*)");
		query.append(" FROM CNBV_RESPUESTA "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		query.append(" AND NUM_CONSEC = ? "); 
		//query.append(" AND ID_CONTRATO = ? "); 
		
		return (jdbcTemplateCorp.queryForObject(
				query.toString(),new Object[] { respuesta.getNumOficio(),
						respuesta.getTipoOficio(), respuesta.getNumConsec()}, Integer.class));
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.dao.RespuestaDao#marcarTipoOficio
	 */
	@Override
	public int marcarTipoOficio(String numOficio, String tipoOficio)throws DAOException {
		// TODO Auto-generated method stub
		BigDecimal respuesta = new BigDecimal(0);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplateCorp)
												.withSchemaName("ADMIPROD")
												.withCatalogName("PKG_ADMCTAWEB")
												.withProcedureName("SP_CNBV_RESPUESTA");
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("peNumOficio", numOficio);
			inParamMap.put("peTipoOficio", tipoOficio);
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);

			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			respuesta = (BigDecimal) simpleJdbcCallResult.get("PSCODRESP");

			return respuesta.intValue();			
		} 
		catch (DataAccessException e) {
			log.error("Error al marcar el Tipo de Caso del Oficio" + e.getLocalizedMessage());
			throw new DAOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.dao.RespuestaDao#buscarRespuestasPorCaso
	 */
	public List<RespuestaModel> buscarRespuestasPorCaso(String numOficio,
			String tipoOficio, String tipoCaso) throws DAOException {
		log.info("En el método buscarRespuestasPorCaso");
		List<RespuestaModel> listResp =  new ArrayList<RespuestaModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT NUM_OFICIO, TIPO_OFICIO, NUM_CONSEC, ID_CONTRATO, ");
		query.append(" TIPO_RESPUESTA, TIPO_CASO, SIT_ENVIO, B_MEDIO_ELEC, "); 
		query.append(" NOMB_TITULAR, TIPO_TITULAR, SIT_CUENTA, FH_ALTA, "); 
		query.append(" CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD "); 
		query.append(" FROM CNBV_RESPUESTA "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		query.append(" AND TIPO_CASO = ? "); 
		
		listResp = jdbcTemplateCorp.query(query.toString(), new Object[]{numOficio,tipoOficio,tipoCaso}, new RespuestaMapper());
		
		return listResp;
	}
	
	
}
