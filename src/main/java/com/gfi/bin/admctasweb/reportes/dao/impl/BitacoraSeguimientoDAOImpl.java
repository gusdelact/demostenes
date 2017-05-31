package com.gfi.bin.admctasweb.reportes.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.reportes.dao.BitacoraSeguimientoDAO;
import com.gfi.bin.admctasweb.reportes.mapper.BitacoraSeguimientoMapper;
import com.gfi.bin.admctasweb.reportes.mapper.OficioSeguimientoMapper;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoListModel;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.model.OficioSeguimientoModel;

/**
 * @author LUGL4884
 *
 */
@Repository
public class BitacoraSeguimientoDAOImpl implements BitacoraSeguimientoDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(BitacoraSeguimientoDAOImpl.class);
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.reportes.dao.BitacoraSeguimientoDAO#guardar
	 */
	public void guardar(BitacoraSeguimientoModel seguimiento) throws DAOException {
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO CNBV_BIT_SEGUIMIENTO(NUM_OFICIO, "); 
		query.append(" TIPO_OFICIO, CLAVE_ESTATUS, FH_REGISTRO, CLAVE_USUARIO) ");
		query.append(" VALUES(?, ?, ?, ?, ?) ");

		try {
			jdbcTemplateCorp.update(query.toString(), new Object[] {
					seguimiento.getNumOficio(), seguimiento.getTipoOficio(),
					seguimiento.getCveEstatus(), seguimiento.getFhRegistro(),
					seguimiento.getCveUsuario() });
		} catch (DataAccessException e) {
			log.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.OficioDAO#buscarOficiosSeguimiento(BitacoraSeguimientoListModel bitacora)
	 */
	public List<OficioSeguimientoModel> buscarOficiosSeguimiento(BitacoraSeguimientoListModel bitacora) throws DAOException {
		List<OficioSeguimientoModel> oficioList = null;

		StringBuilder query = new StringBuilder();
		query.append(" SELECT ");
		query.append(" CO.NUM_OFICIO, CO.TIPO_OFICIO, CO.NUM_FOLIO, CO.NUM_EXPED, CO.NUM_REGISTRO, ");
		query.append(" CO.CVE_AUTORIDAD, CO.ID_EMPRESA, CO.F_OFICIO, CO.F_RECEPCION, CO.SIT_OFICIO, "); 
		query.append(" CO.FH_ENVIO, CO.F_VENCIMIENTO, CO.TIPO_CASO, CO.CVE_ESTATUS, CES.DESCRIPCION TX_ESTATUS, "); 
		query.append(" CO.CVE_ESTATUS_PEND, CEP.DESCRIPCION TX_ESTATUS_PEND "); 
		query.append(" FROM CNBV_OFICIO CO, CNBV_ESTATUS_SEG CES, CNBV_ESTATUS_SEG CEP");
		query.append(" WHERE CO.CVE_ESTATUS = CES.CVE_ESTATUS ");
		query.append(" AND CO.CVE_ESTATUS_PEND = CEP.CVE_ESTATUS ");
		
		if(StringUtils.isNotBlank(bitacora.getNumOficio())){
			query.append(" AND CO.NUM_OFICIO = :numOficio ");
		}
		
		if(StringUtils.isNotBlank(bitacora.getTipoOficio()) && !bitacora.getTipoOficio().equalsIgnoreCase("undefined")){
			query.append(" AND CO.TIPO_OFICIO = :tipoOficio ");
		}
		
		if(StringUtils.isNotBlank(bitacora.getCveEstatus())){
			query.append(" AND (CO.CVE_ESTATUS = :cveEstatus ");
			query.append(" OR CO.CVE_ESTATUS_PEND = :cveEstatus) ");
		}
		
		if (bitacora.getFhRecepIni() != null && bitacora.getFhRecepFin() != null) {
			query.append(" AND TRUNC(CO.F_RECEPCION) BETWEEN :fhRecepIni AND :fhRecepFin ");
		}else if (bitacora.getFhRecepIni() != null) {
			query.append(" AND TRUNC(CO.F_RECEPCION) = :fhRecepIni ");
		}
		
		if (bitacora.getFhEnvIni() != null && bitacora.getFhEnvFin() != null) {
			query.append(" AND TRUNC(CO.FH_ENVIO) BETWEEN :fhEnvIni AND :fhEnvFin ");
		}else if (bitacora.getFhEnvIni() != null) {
			query.append(" AND TRUNC(CO.FH_ENVIO) = :fhEnvIni ");
		}
		
		if (bitacora.getFhVenIni() != null && bitacora.getFhVenFin() != null) {
			query.append(" AND TRUNC(CO.F_VENCIMIENTO) BETWEEN :fhVenIni AND :fhVenFin ");
		}else if (bitacora.getFhVenIni() != null) {
			query.append(" AND TRUNC(CO.F_VENCIMIENTO) = :fhVenIni ");
		}
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("numOficio", bitacora.getNumOficio());
		parametros.put("tipoOficio", bitacora.getTipoOficio());
		parametros.put("cveEstatus", bitacora.getCveEstatus());
		parametros.put("fhRecepIni", bitacora.getFhRecepIni());
		parametros.put("fhRecepFin", bitacora.getFhRecepFin());
		parametros.put("fhEnvIni", bitacora.getFhEnvIni());
		parametros.put("fhEnvFin", bitacora.getFhEnvFin());
		parametros.put("fhVenIni", bitacora.getFhVenIni());
		parametros.put("fhVenFin", bitacora.getFhVenFin());
		
		log.info(query.toString());
		
		try {				
			oficioList = this.corpNamedTemplate.query(query.toString(), parametros, new OficioSeguimientoMapper());
			
		} catch (DataAccessException e) {
			log.error("No existen Oficios a recuperar con los filtros seleccionados");
			throw new DAOException(e.getLocalizedMessage());
		}
		
		return oficioList;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.reportes.dao.BitacoraSeguimientoDAO#buscarSeguimientoPorOficio
	 */
	public List<BitacoraSeguimientoModel> buscarSeguimientoPorOficio(
			String numOficio, String tipoOficio) throws DAOException {
		log.info("En el método buscarSeguimientoPorOficio");
		List<BitacoraSeguimientoModel> listSeguimiento =  new ArrayList<BitacoraSeguimientoModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT CBS.NUM_OFICIO, CBS.TIPO_OFICIO, CBS.CLAVE_ESTATUS, ");
		query.append(" CES.DESCRIPCION, CBS.FH_REGISTRO, CBS.CLAVE_USUARIO "); 
		query.append(" FROM CNBV_BIT_SEGUIMIENTO CBS, CNBV_ESTATUS_SEG CES "); 
		query.append(" WHERE CBS.CLAVE_ESTATUS = CES.CVE_ESTATUS ");
		query.append(" AND NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		query.append(" ORDER BY CES.ORDEN_PROCESO DESC ");
		
		listSeguimiento = jdbcTemplateCorp.query(query.toString(), new Object[]{numOficio,tipoOficio}, new BitacoraSeguimientoMapper());
		
		return listSeguimiento;
	}

}
