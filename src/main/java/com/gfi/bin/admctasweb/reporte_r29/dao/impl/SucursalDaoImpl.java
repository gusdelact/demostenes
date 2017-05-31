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
import com.gfi.bin.admctasweb.reporte_r29.dao.SucursalDao;
import com.gfi.bin.admctasweb.reporte_r29.mapper.CapturaManualContratoMapper;
import com.gfi.bin.admctasweb.reporte_r29.mapper.CapturaManualMapper;
import com.gfi.bin.admctasweb.reporte_r29.mapper.SucursalMapper;
import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualModel;
import com.gfi.bin.admctasweb.reporte_r29.model.SucursalModel;

/**
 * @author LUGL4884
 * Clase que Implementa la persistencia de la Sucursal.
 */
@Component
@Repository
public class SucursalDaoImpl implements SucursalDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(SucursalDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.SucursalDao#guardarSucursal(SucursalModel)
	 */
	public String guardarSucursal(SucursalModel sucursal) throws DAOException {
		
		LOGGER.info("En el método guardarSucursal");
		String result =  "";
		
		// cambiar el inserty JAPL
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO CNBV_SUCURSAL ( ");
		query.append( "ID_EMPRESA,"
				+ " ID_SUCURSAL, "
				+ "ESTADO, "
				+ "LOCALIDAD, "
				+ "CODIGO_POSTAL, "
				+ "NOM_SUCURSAL ) ");
	
		query.append(" VALUES (?,?,?,?,?,?)");
		
		try {
			jdbcTemplateCorp.update(query.toString(),
					sucursal.getIdEmpresa(), 
					sucursal.getIdSucursal(),
					sucursal.getEstado(), 
					sucursal.getLocalidad(), 
					sucursal.getCodigoPostal(),
					sucursal.getNomSucursal());
		
			result = "";
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			if(e.getMessage().indexOf("restricción única")>-1){
							
				return "El registro con Sucursal ["+ sucursal.getIdEmpresa() +"] ya existe, favor de verificar";
			}else{		
				result = "Error en el proceso Sucursal, reportar a Sistemas";
				throw new DAOException(e);
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.SucursalDao#modificarSucusal(SucursalModel)
	 */
	public boolean modificarSucursal(SucursalModel sucursal)  throws DAOException{
		LOGGER.info("En el método modificarSucursal");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE CNBV_SUCURSAL SET ");
		query.append("ID_EMPRESA = ?, ID_SUCURSAL = ?, ESTADO = ?, LOCALIDAD = ?, CODIGO_POSTAL = ?, NOM_SUCURSAL = ? ");		
		query.append(" WHERE ID_EMPRESA = '" + sucursal.getIdEmpresa() + "' "); 
		query.append(" AND ID_SUCURSAL = '" + sucursal.getIdSucursal() + "' ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), 
							new Object[]{ sucursal.getIdEmpresa(), sucursal.getIdSucursal(),
								sucursal.getEstado(), sucursal.getLocalidad(),
								sucursal.getCodigoPostal(), sucursal.getNomSucursal()								
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
	 * @see com.gfi.bin.admctasweb.catalogos.dao.SucursalDao#eliminarSucursal(SucursalModel)
	 */
	public boolean eliminarSucursal(SucursalModel sucursal)  throws DAOException{
		LOGGER.info("En el método eliminarSucursal");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM CNBV_SUCURSAL ");
		query.append(" WHERE ID_EMPRESA = ? "); 
		query.append(" AND ID_SUCURSAL = ? ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), new Object[]{sucursal.getIdEmpresa(),sucursal.getIdSucursal()});
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.SucursalDaoImpl#buscarSucursalPorLlave(int, String )
	 */
	public SucursalModel buscarSucursalPorLlave(String idEmpresa, String idSucursal ) throws DAOException{
		LOGGER.info("En el método buscarSucursalPorLlave");
		SucursalModel sucursal=  new SucursalModel();
		
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT A.ID_EMPRESA, ID_SUCURSAL, ESTADO, LOCALIDAD, CODIGO_POSTAL, NOM_SUCURSAL, B.DESCRIPCION ");
		query.append(" FROM CNBV_SUCURSAL A, CNBV_CAT_MAPEO B");
		query.append(" WHERE A.ID_EMPRESA = ? "); 
		query.append(" AND ID_SUCURSAL = ? ");
		query.append(" AND A.ID_EMPRESA = B.CVE_CORPORATIVA ");
		query.append(" AND B.ID_CATALOGO = 2" ); 
		
		try {
			sucursal = jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{idEmpresa, idSucursal }, new SucursalMapper());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return sucursal;
	}

}