package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.catalogos.model.DestinatarioModel;

/**
 * Clase para mapear datos de ResultSet a bean de datos de Destinatario
 * @author ESS3VAVC
 *
 */

public class DestinatarioMapper implements RowMapper<DestinatarioModel> {
	
	
	public DestinatarioModel mapRow(ResultSet rs, int rownum) throws SQLException{
		
		DestinatarioModel destinatarioModel = new DestinatarioModel();
		
		destinatarioModel.setIdDestinatario(rs.getInt("ID_DESTINATARIO"));
		destinatarioModel.setCorreo(rs.getString("CORREO"));
		destinatarioModel.setFhAlta(rs.getDate("FH_ALTA"));
		destinatarioModel.setFhUltMod(rs.getDate("FH_ULT_MOD"));
		destinatarioModel.setCveEstatus(rs.getString("CVE_ESTATUS"));
		destinatarioModel.setNombre(rs.getString("NOMBRE_DESTINAT"));
		destinatarioModel.setSituacion(rs.getString("SIT_NOTIF"));
		destinatarioModel.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
		destinatarioModel.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		
		
		return destinatarioModel;
	
	}
}

