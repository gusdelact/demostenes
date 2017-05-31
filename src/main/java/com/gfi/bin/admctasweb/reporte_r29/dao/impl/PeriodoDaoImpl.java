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
import com.gfi.bin.admctasweb.reporte_r29.dao.PeriodoDao;
import com.gfi.bin.admctasweb.reporte_r29.mapper.PeriodoMapper;
import com.gfi.bin.admctasweb.reporte_r29.model.PeriodoModel;

/**
 * @author LUGL4884
 * Clase que Implementa la persistencia del Periodo.
 */
@Component
@Repository
public class PeriodoDaoImpl implements PeriodoDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.PeriodoDao#guardarPeriodo(PeriodoModel)
	 */
	public String guardarPeriodo(PeriodoModel periodo) throws DAOException {
		
		LOGGER.info("En el método guardarPeriodo");
		String result =  "";
		
		// cambiar el inserty JAPL
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO CNBV_PERIODO ( ");
		query.append( "ID_EMPRESA, CVE_PERIODO, DESC_PERIODO, F_INICIAL, F_FINAL, ESTATUS ) ");
	
		query.append(" VALUES (?,?,?,?,?,?)");
		
		try {
			jdbcTemplateCorp.update(query.toString(),
					periodo.getIdEmpresaP(), periodo.getCvePeriodoP(), periodo.getDescPeriodoP(), periodo.getFechaInicial(), periodo.getFechaFinal(), 
					"NP");
		
			result = "";
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			if(e.getMessage().indexOf("restricción única")>-1){
							
				return "El registro con Empresa y Periodo ["+periodo.getIdEmpresaP() + " - "+ periodo.getCvePeriodoP()+"] ya existe, favor de verificar";
			}else{		
				result = "Error en el proceso Periodo, reportar a Sistemas";
				throw new DAOException(e);
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.PeriodoDao#modificarPeriodo(PeriodoModel)
	 */
	public boolean modificarPeriodo(PeriodoModel periodo)  throws DAOException{
		LOGGER.info("En el método modificarPeriodo");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE CNBV_PERIODO SET ");
		query.append("ID_EMPRESA = ?, CVE_PERIODO = ?, DESC_PERIODO = ? , F_INICIAL = ?, F_FINAL=?, ESTATUS=? ");		
		query.append(" WHERE ID_EMPRESA = '" + periodo.getIdEmpresaP() +"' AND CVE_PERIODO = '" + periodo.getCvePeriodoP() + "' ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), 
							new Object[]{ periodo.getIdEmpresaP(), periodo.getCvePeriodoP(),
								periodo.getDescPeriodoP(),
								periodo.getFechaInicial(), periodo.getFechaFinal(),
								periodo.getEstatus()
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
	 * @see com.gfi.bin.admctasweb.catalogos.dao.PeriodoDao#eliminarPeriodo(PeriodoModel)
	 */
	public boolean eliminarPeriodo(PeriodoModel periodo)  throws DAOException{
		LOGGER.info("En el método eliminarPeriodo");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM CNBV_Periodo ");
		query.append(" WHERE ID_EMPRESA = ? AND CVE_PERIODO = ? ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), new Object[]{periodo.getIdEmpresaP(), periodo.getCvePeriodoP()});
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.PeriodoDaoImpl#buscarPeriodoPorLlave(String , int )
	 */
	public PeriodoModel buscarPeriodoPorLlave(String idEmpresa, int cvePeriodo ) throws DAOException{
		LOGGER.info("En el método buscarPeriodoPorLlave");
		PeriodoModel periodo=  new PeriodoModel();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT A.ID_EMPRESA, A.CVE_PERIODO, A.DESC_PERIODO, "
				+ " TO_CHAR(TO_DATE(A.F_INICIAL,'dd-mm-yy'), 'dd/mm/yyyy') as F_INICIAL, "
				+ " TO_CHAR(TO_DATE(A.F_FINAL,'dd-mm-yy'), 'dd/mm/yyyy') as F_FINAL,"
				+ " B.DESCRIPCION, ESTATUS ");
		query.append(" FROM CNBV_PERIODO A, CNBV_CAT_MAPEO B ");
		query.append(" WHERE A.ID_EMPRESA = ? AND A.CVE_PERIODO =  ? ");
		query.append(" AND A.ID_EMPRESA = B.CVE_CORPORATIVA ");
		query.append(" AND B.ID_CATALOGO = 2" ); 
		
		try {
			periodo = jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{idEmpresa, cvePeriodo }, new PeriodoMapper());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return periodo;
	}

}