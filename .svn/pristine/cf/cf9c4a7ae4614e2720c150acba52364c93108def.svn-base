/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public class ParametrosMapper implements RowMapper<ParametrosModel>{

	public ParametrosModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ParametrosModel parametrosModel = new ParametrosModel();
		try {
			//estas mutaciones podrian lanzar un IllegalArgumentException si los valores son nulos
			parametrosModel.setCveGpoParam(rs.getString("CVE_GPO_PARAM"));
			parametrosModel.setCveParam(rs.getString("CVE_PARAMETRO"));
			parametrosModel.setDescParam(rs.getString("TX_PARAMETRO"));
			parametrosModel.setSitParam(rs.getString("SIT_PARAMETRO"));
			parametrosModel.setFhAlta(rs.getDate("FH_ALTA"));
			parametrosModel.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
			parametrosModel.setFhUltMod(rs.getDate("FH_ULT_MOD"));
			parametrosModel.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getCause());
		}
		return parametrosModel;
	}

}
