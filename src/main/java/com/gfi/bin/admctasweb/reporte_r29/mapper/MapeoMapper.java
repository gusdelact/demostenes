/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reporte_r29.model.MapeoModel;

/**
 * @author LUGL4884
 *
 */
public class MapeoMapper implements RowMapper<MapeoModel> {

	public MapeoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		MapeoModel mapeoModel = new MapeoModel();
		
		mapeoModel.setIdCatalogo(rs.getInt("ID_CATALOGO"));
		mapeoModel.setCveCorporativa(rs.getString("CVE_CORPORATIVA"));
		mapeoModel.setCveSiti(rs.getString("CVE_SITI"));
		mapeoModel.setDescripcion(rs.getString("DESCRIPCION"));
		
		return mapeoModel;
	}

}
