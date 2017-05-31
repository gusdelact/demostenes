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
import com.gfi.bin.admctasweb.operativos.dao.ImpresionDictamenJurDao;
import com.gfi.bin.admctasweb.operativos.mapper.ImpresionDictamenJurMapper;
import com.gfi.bin.admctasweb.operativos.model.ImpresionDictamenJurModel;

/**
 * @author LUGL4884
 *
 */
@Component
@Repository
public class ImpresionDictamenJurDaoImpl implements ImpresionDictamenJurDao {
	
	final Logger log = LoggerFactory.getLogger(ImpresionDictamenJurDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.dao.ImpresionDictamenJurDao#buscarDictamenJur
	 */
	public List<ImpresionDictamenJurModel> buscarDictamenJur(String numOficio, String tipoOficio) throws DAOException {
		log.info("En el método buscarDictamenJur");
		List<ImpresionDictamenJurModel> impresionDictamenList =  new ArrayList<ImpresionDictamenJurModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT CD.NUM_OFICIO, CD.TIPO_OFICIO, CD.NUM_CONSEC, ");
		query.append(" CR.NOMB_TITULAR, CD.FH_ALTA, CD.IMPRESO_POR_CONTRALORIA "); 
		query.append(" FROM CNBV_DICTAMEN CD, CNBV_RESPUESTA CR "); 
		query.append(" WHERE CD.NUM_OFICIO = CR.NUM_OFICIO "); 
		query.append(" AND CD.TIPO_OFICIO = CR.TIPO_OFICIO "); 
		query.append(" AND CD.NUM_CONSEC = CR.NUM_CONSEC "); 
		query.append(" AND CD.NUM_OFICIO = ? "); 
		query.append(" AND CD.TIPO_OFICIO = ? "); 
		query.append(" ORDER BY CD.FH_ALTA DESC ");
		
		log.info(query.toString());
		
		impresionDictamenList = jdbcTemplateCorp.query(query.toString(), new Object[]{numOficio,tipoOficio}, new ImpresionDictamenJurMapper());
		
		return impresionDictamenList;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.dao.ImpresionDictamenJurDao#marcarDictamenJur
	 */
	public int marcarDictamenJur(String numOficio, String tipoOficio, Long numConsec, String usuario) throws DAOException {
		BigDecimal respuesta = new BigDecimal(0);
		
		try {
			
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplateCorp)
													.withSchemaName("ADMIPROD")
													.withCatalogName("PKG_ADMCTAWEB")
													.withProcedureName("SP_CNBV_DICTAMEN");
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("peNumOficio", numOficio);
			inParamMap.put("peTipoOficio", tipoOficio);
			inParamMap.put("peNumConsec", numConsec);
			inParamMap.put("peUsuario", usuario);
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);

			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			respuesta = (BigDecimal) simpleJdbcCallResult.get("PSCODRESP");

			return respuesta.intValue();			
		} 
		catch (DataAccessException e) {
			log.error("Error al marcar el Dictamen Jurídico" + e.getLocalizedMessage());
			throw new DAOException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.dao.ImpresionDictamenJurDao#buscarDictamenSinImp
	 */
	public int buscarDictamenSinImp(String numOficio, String tipoOficio) throws DAOException {
		log.info("En el método buscarDictamenSinImp");
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(*)");
		query.append(" FROM CNBV_DICTAMEN "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		query.append(" AND IMPRESO_POR_CONTRALORIA = 'F' "); 
		
		return (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio}, Integer.class));
	}

}
