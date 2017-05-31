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
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.reporte_r29.dao.ConversionDao;
import com.gfi.bin.admctasweb.reporte_r29.mapper.ConversionMapper;
import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualModel;
import com.gfi.bin.admctasweb.reporte_r29.model.ConversionModel;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * @author LUGL4884
 * Clase que Implementa la persistencia de la Conversion.
 */
@Component
@Repository
public class ConversionDaoImpl implements ConversionDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConversionDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.ConversionDao#guardarConversion(ConversionModel)
	 */
	public String guardarConversion(ConversionModel conversion) throws DAOException {
		
		LOGGER.info("En el método guardarConversion");
		String result =  "";
		
		// cambiar el inserty JAPL
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO CNBV_CONVERSION ( ");
		query.append( "ID_CATALOGO, "
				+ " DESC_CATALOGO ) ");
	
		query.append(" VALUES (?,? ) ");
		
		try {
			jdbcTemplateCorp.update(query.toString(),
					conversion.getIdCatalogo(), 
					conversion.getDescCatalogo());
		
			result = "";
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			if(e.getMessage().indexOf("restricción única")>-1){
				
				return "El registro con Catalogo ["+conversion.getIdCatalogo()+"] ya existe, favor de verificar";
			}else{		
				result = "Error en el proceso Conversión, reportar a Sistemas";
				throw new DAOException(e);
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.ConversionDao#modificarConversion(ConversionModel)
	 */
	public boolean modificarConversion(ConversionModel conversion)  throws DAOException{
		LOGGER.info("En el método modificarConversion");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE CNBV_CONVERSION SET ");
		query.append("ID_CATALOGO = ?, DESC_CATALOGO = ? ");		
		query.append(" WHERE ID_CATALOGO = '" + conversion.getIdCatalogo() + "' ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), 
							new Object[]{ conversion.getIdCatalogo(), conversion.getDescCatalogo()					
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
	 * @see com.gfi.bin.admctasweb.catalogos.dao.ConversionDao#eliminarConversion(ConversionModel)
	 */
	public boolean eliminarConversion(ConversionModel conversion)  throws DAOException{
		LOGGER.info("En el método eliminarConversion");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM CNBV_CONVERSION ");
		query.append(" WHERE ID_CATALOGO = ? ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), new Object[]{conversion.getIdCatalogo()});
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.ConversionDaoImpl#buscarConversionPorLlave( int )
	 */
	public ConversionModel buscarConversionPorLlave(int idCatalogo ) throws DAOException{
		LOGGER.info("En el método buscarConversionPorLlave");
		ConversionModel conversion=  new ConversionModel();
		
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ID_CATALOGO, DESC_CATALOGO ");
		query.append(" FROM CNBV_CONVERSION ");
		query.append(" WHERE ID_CATALOGO = ? ");
		try {
			conversion = jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{idCatalogo }, new ConversionMapper());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return conversion;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.ConversionDaoImpl#buscarConversionPorLlave( int )
	 */
	public int buscarRegistroConversion(int idCatalogo ) throws DAOException{
		LOGGER.info("En el método buscarRegistroConversion");
		ConversionModel conversion=  new ConversionModel();
		
		int registro;
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(ID_CATALOGO) as ID_CATALOGO, DESC_CATALOGO ");
		query.append(" FROM CNBV_CONVERSION ");
		query.append(" WHERE ID_CATALOGO = ? ");
		try {
			conversion = jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{idCatalogo }, new ConversionMapper());
			registro=conversion.getIdCatalogo();
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return registro;
	}
}