package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;

public class ExclusionMapper implements RowMapper<ExclusionModel> {

	public ExclusionModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ExclusionModel exclusionModel = new ExclusionModel();
		
		exclusionModel.setIdExclusion(rs.getInt("ID_EXCLUSION"));
		exclusionModel.setDescripcionExclusion(rs.getString("DESC_EXCLUSION"));
		exclusionModel.setActivo(rs.getBoolean("ACTIVO"));
		
		return exclusionModel;
	}
}