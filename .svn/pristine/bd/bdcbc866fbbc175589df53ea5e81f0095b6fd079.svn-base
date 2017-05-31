/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reporte_r29.model.PeriodoModel;

/**
 * @author LUGL4884
 *
 */
public class PeriodoMapper implements RowMapper<PeriodoModel> {

	public PeriodoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		PeriodoModel periodoModel = new PeriodoModel();
		
		periodoModel.setIdEmpresaP(rs.getString("ID_EMPRESA"));
		periodoModel.setCvePeriodoP(rs.getInt("CVE_PERIODO"));
		periodoModel.setDescPeriodoP(rs.getString("DESC_PERIODO"));
		periodoModel.setFechaInicial(rs.getString("F_INICIAL"));
		periodoModel.setFechaFinal(rs.getString("F_FINAL"));
		periodoModel.setTxInstitucionP(rs.getString("DESCRIPCION"));
		periodoModel.setEstatus(rs.getString("ESTATUS"));
		
		return periodoModel;
	}

}