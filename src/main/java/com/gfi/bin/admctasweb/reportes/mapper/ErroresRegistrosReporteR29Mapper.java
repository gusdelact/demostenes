package com.gfi.bin.admctasweb.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reportes.model.ErroresRegistrosReporteR29;

public class ErroresRegistrosReporteR29Mapper implements RowMapper<ErroresRegistrosReporteR29> {
	
	  public ErroresRegistrosReporteR29 mapRow(ResultSet rs, int rowNum) throws SQLException {
		  ErroresRegistrosReporteR29 objErroresRegistroReporteR29 = new ErroresRegistrosReporteR29();
			try {
				
				objErroresRegistroReporteR29.setCvePeriodo(rs.getInt("CVE_PERIODO"));
				objErroresRegistroReporteR29.setNumIntento(rs.getInt("NUM_INTENTO"));
				objErroresRegistroReporteR29.setNumOficio(rs.getString("NUM_OFICIO"));
				objErroresRegistroReporteR29.setDescripcionError(rs.getString("DESCRIPCION"));

			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException(e.getCause());
			}
			return objErroresRegistroReporteR29;
		}

}
