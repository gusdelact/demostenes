/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.catalogos.model.AutoridadesModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public class AutoridadesMapper implements RowMapper<AutoridadesModel>{

	public AutoridadesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AutoridadesModel autoridadesModel = new AutoridadesModel();
		try {
			//estas mutaciones podrian lanzar un IllegalArgumentException si los valores son nulos
			autoridadesModel.setCveAutoridad(rs.getString("CVE_AUTORIDAD"));
			autoridadesModel.setNomAutoridad(rs.getString("NOM_AUTORIDAD"));
			autoridadesModel.setSitAutoridad(rs.getString("SIT_AUTORIDAD"));
			autoridadesModel.setFhAlta(rs.getDate("FH_ALTA"));
			autoridadesModel.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
			autoridadesModel.setFhUltMod(rs.getDate("FH_ULT_MOD"));
			autoridadesModel.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getCause());
		}
		return autoridadesModel;
	}

}
