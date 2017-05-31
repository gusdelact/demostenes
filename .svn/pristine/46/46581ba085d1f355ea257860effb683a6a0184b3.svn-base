/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reporte_r29.model.ControlModel;

/**
 * @author LUGL4884
 *
 */
public class ControlMapper implements RowMapper<ControlModel> {

	public ControlModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ControlModel controlModel = new ControlModel();
		
		controlModel.setIdEmpresaCx(rs.getString("ID_EMPRESA"));
		controlModel.setCvePeriodoCx(rs.getInt("CVE_PERIODO"));
		controlModel.setNumIntentoCx(rs.getInt("NUM_INTENTO"));
//		controlModel.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
//		controlModel.setFAlta(rs.getDate("F_ALTA"));
		controlModel.setSituacionCx(rs.getString("SITUACION"));
		controlModel.setTxInstitucionCx(rs.getString("DESCRIPCION"));
		controlModel.setDescPeriodoCx(rs.getString("DESC_PERIODO"));
		
		controlModel.setTxInstitucion(controlModel.getTxInstitucionCx());
		controlModel.setDescPeriodo(controlModel.getDescPeriodoCx());
		controlModel.setSituacion(controlModel.getSituacionCx());
		
		return controlModel;
	}

}
