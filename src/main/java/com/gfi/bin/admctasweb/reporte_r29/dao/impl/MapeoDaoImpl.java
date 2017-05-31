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
import com.gfi.bin.admctasweb.reporte_r29.dao.MapeoDao;
import com.gfi.bin.admctasweb.reporte_r29.mapper.MapeoMapper;
import com.gfi.bin.admctasweb.reporte_r29.model.MapeoModel;

/**
 * @author LUGL4884
 * Clase que Implementa la persistencia del Mapeo.
 */
@Component
@Repository
public class MapeoDaoImpl implements MapeoDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(MapeoDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.MapeoDao#guardarMapeo(MapeoModel)
	 */
	public String guardarMapeo(MapeoModel mapeo) throws DAOException {
		
		LOGGER.info("En el método guardarMapeo");
		String result =  "";
		
		// cambiar el inserty JAPL
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO CNBV_CAT_MAPEO ( ");
		query.append( "ID_CATALOGO, CVE_CORPORATIVA, CVE_SITI, DESCRIPCION ) ");
	
		query.append(" VALUES (?,?,?,? )");
		
		try {
			jdbcTemplateCorp.update(query.toString(),
					mapeo.getIdCatalogo(), mapeo.getCveCorporativa(), mapeo.getCveSiti(), mapeo.getDescripcion());
		
			result = "";
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			if(e.getMessage().indexOf("restricción única")>-1){
							
				return "El registro con Catalogo y CveCorporativa ["+mapeo.getIdCatalogo() + " - "+ mapeo.getCveCorporativa()+"] ya existe, favor de verificar";
			}else{		
				result = "Error en el proceso Conversión, reportar a Sistemas";
				throw new DAOException(e);
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.MapeoDao#modificarMapeo(MapeoModel)
	 */
	public boolean modificarMapeo(MapeoModel mapeo)  throws DAOException{
		LOGGER.info("En el método modificarMapeo");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE CNBV_CAT_MAPEO SET ");
		query.append("ID_CATALOGO = ?, CVE_CORPORATIVA=?, CVE_SITI=?, DESCRIPCION = ? ");		
		query.append(" WHERE ID_CATALOGO = '" + mapeo.getIdCatalogo() +"' AND CVE_CORPORATIVA = '" + mapeo.getCveCorporativa() + "' ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), 
							new Object[]{ mapeo.getIdCatalogo(), mapeo.getCveCorporativa(), mapeo.getCveSiti(), mapeo.getDescripcion()					
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
	 * @see com.gfi.bin.admctasweb.catalogos.dao.MapeoDao#eliminarMapeo(MapeoModel)
	 */
	public boolean eliminarMapeo(MapeoModel mapeo)  throws DAOException{
		LOGGER.info("En el método eliminarMapeo");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM CNBV_CAT_MAPEO ");
		query.append(" WHERE ID_CATALOGO = ? AND CVE_CORPORATIVA = ? ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), new Object[]{mapeo.getIdCatalogo(), mapeo.getCveCorporativa()});
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.MapeoDaoImpl#buscarMapeoPorLlave(int, String )
	 */
	public MapeoModel buscarMapeoPorLlave(int idCatalogo, String cveCorporativa ) throws DAOException{
		LOGGER.info("En el método buscarMapeoPorLlave");
		MapeoModel mapeo=  new MapeoModel();
		
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ID_CATALOGO, CVE_CORPORATIVA, CVE_SITI, DESCRIPCION ");
		query.append(" FROM CNBV_CAT_MAPEO ");
		query.append(" WHERE ID_CATALOGO = ? AND CVE_CORPORATIVA = ? ");
		try {
			mapeo = jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{idCatalogo, cveCorporativa }, new MapeoMapper());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return mapeo;
	}

}