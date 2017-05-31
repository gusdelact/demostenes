/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.reporte_r29.dao.ControlDao;
import com.gfi.bin.admctasweb.reporte_r29.mapper.ControlMapper;
import com.gfi.bin.admctasweb.reporte_r29.model.ControlModel;

/**
 * @author LUGL4884
 * Clase que Implementa la persistencia del Control.
 */
@Component
@Repository
public class ControlDaoImpl implements ControlDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControlDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.ControlDao#guardarControl(ControlModel)
	 */
public String guardarControl(ControlModel control) throws DAOException {
		
		LOGGER.info("En el método guardarControl");
		String result =  "";
		
		// cambiar el inserty JAPL
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO CNBV_CTRL_PERIODO ( ");
		query.append( "ID_EMPRESA, CVE_PERIODO, NUM_INTENTO, CVE_USU_ALTA, " 
				+ " SITUACION ) ");
	
		query.append(" VALUES (?,?,?,?,? )");
		
		try {
			jdbcTemplateCorp.update(query.toString(),
				control.getIdEmpresaCx(), control.getCvePeriodoCx(), control.getNumIntentoCx(), " ", //, control.getFAlta(),
				control.getSituacionCx() );
		
			result = "";
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			if(e.getMessage().indexOf("restricción única")>-1){
				
				return "El registro con Empresa, Periodo e Intento  ["+control.getIdEmpresaCx() +" - "+ control.getCvePeriodoCx() +" - "+ control.getNumIntentoCx()+"] ya existe, favor de verificar";
			}else{		
				result = "Error en el proceso Control, reportar a Sistemas";
				throw new DAOException(e);
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.ControlDao#modificarControl(controlModel)
	 */
	public boolean modificarControl(ControlModel control)  throws DAOException{
		LOGGER.info("En el método modificarcontrol");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE CNBV_CTRL_PERIODO SET ");
		query.append(" ID_EMPRESA = ?, CVE_PERIODO=?, NUM_INTENTO=?, Situacion=? ");		
		query.append(" WHERE ID_EMPRESA = '" + control.getIdEmpresaCx() +"' AND CVE_PERIODO = '" + control.getCvePeriodoCx() + "'"
				+ " AND NUM_INTENTO = '" + control.getNumIntentoCx() +  "'");
		
		try {
			jdbcTemplateCorp.update(query.toString(), 
							new Object[]{ control.getIdEmpresaCx(), control.getCvePeriodoCx(), control.getNumIntentoCx(), control.getSituacionCx()					
							}
			);
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
	
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.ControlDao#eliminarControl(ControlModel)
	 */
	public boolean eliminarControl(ControlModel control)  throws DAOException{
		LOGGER.info("En el método eliminarControl");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM CNBV_CTRL_PERIODO ");
		query.append(" WHERE ID_EMPRESA = ? AND CVE_PERIODO = ? AND NUM_INTENTO = ? ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), new Object[]{control.getIdEmpresaCx(), control.getCvePeriodoCx(),
				control.getNumIntentoCx()});
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.ControlDaoImpl#buscarControlPorLlave(String , int )
	 */
	public ControlModel buscarControlPorLlave(String idEmpresaCx, int cvePeriodoCx, int numIntentoCx ) throws DAOException{
		LOGGER.info("En el método buscarControlPorLlave");
		ControlModel control=  new ControlModel();
		
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT A.ID_EMPRESA, A.CVE_PERIODO, C.DESC_PERIODO, A.NUM_INTENTO, A.SITUACION, "
				+ " B.DESCRIPCION ");
		query.append(" FROM CNBV_CTRL_PERIODO A , CNBV_CAT_MAPEO B, CNBV_PERIODO C ");
		query.append(" WHERE A.ID_EMPRESA = ? ");
		query.append(" AND A.CVE_PERIODO =  ? ");
		query.append(" AND A.NUM_INTENTO =  ? ");
		query.append(" AND A.ID_EMPRESA = B.CVE_CORPORATIVA ");
		query.append(" AND B.ID_CATALOGO = 2 " ); 
		query.append(" AND A.ID_EMPRESA = C.ID_EMPRESA " ); 
		query.append(" AND A.CVE_PERIODO = C.CVE_PERIODO " ); 
		
		try {
			control = jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{idEmpresaCx, cvePeriodoCx, numIntentoCx }, new ControlMapper());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return control;
	}

}