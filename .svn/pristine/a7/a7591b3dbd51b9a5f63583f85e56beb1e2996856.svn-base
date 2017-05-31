package com.gfi.bin.admctasweb.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reportes.model.IntentoReporteR29;
import com.gfi.bin.admctasweb.reportes.model.Periodo;

public class PeriodoMapper implements RowMapper<Periodo>{
	
	 public Periodo mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Periodo objetoPeriodo = new Periodo();
			try {
						objetoPeriodo.setCvePeriodo(rs.getInt("cve_periodo"));

			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException(e.getCause());
			}
			return objetoPeriodo;
		}
	
	

}
