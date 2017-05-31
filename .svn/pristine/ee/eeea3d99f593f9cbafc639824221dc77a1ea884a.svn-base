/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reporte_r29.model.ConversionModel;

/**
 * @author LUGL4884
 *
 */
public class ConversionMapper implements RowMapper<ConversionModel> {

	public ConversionModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ConversionModel conversionModel = new ConversionModel();
		
		conversionModel.setIdCatalogo(rs.getInt("ID_CATALOGO"));
		conversionModel.setDescCatalogo(rs.getString("DESC_CATALOGO"));
		
		return conversionModel;
	}

}
