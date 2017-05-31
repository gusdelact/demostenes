package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.catalogos.model.SimilaridadModel;

/**
 * Clase para mapear datos de ResultSet a bean de datos de Destinatario
 * @author ESS3VAVC
 *
 */

public class SimilaridadMapper implements RowMapper<SimilaridadModel> {
	
	
	public SimilaridadModel mapRow(ResultSet rs, int rownum) throws SQLException{
		
		SimilaridadModel similaridadModel = new SimilaridadModel();
		
		similaridadModel.setSimilaridad(rs.getInt("PORCENT_SIMIL"));
		similaridadModel.setFhAlta(rs.getDate("FH_ALTA"));
		similaridadModel.setFhUltMod(rs.getDate("FH_ULT_MOD"));
		similaridadModel.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
		similaridadModel.setCveUsuMod(rs.getString("CVE_USU_MOD"));
				
		return similaridadModel;	
	}
}

