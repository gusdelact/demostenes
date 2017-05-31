package com.gfi.bin.admctasweb.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reportes.model.BitacoraReporteR29;
import com.gfi.bin.admctasweb.reportes.model.IntentoReporteR29;

public class IntentoReporteR29Mapper  implements RowMapper<IntentoReporteR29>{
	
	
    public IntentoReporteR29 mapRow(ResultSet rs, int rowNum) throws SQLException {
    	IntentoReporteR29 objIntentoReporteR29 = new IntentoReporteR29();
		try {
					objIntentoReporteR29.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
					objIntentoReporteR29.setIdEmpresa(rs.getString("ID_EMPRESA"));
					objIntentoReporteR29.setCvePeriodo(rs.getInt("CVE_PERIODO"));
					objIntentoReporteR29.setNumIntento(rs.getInt("NUM_INTENTO"));
					objIntentoReporteR29.setSitPeriodo(rs.getString("SITUACION"));
					objIntentoReporteR29.setFechaInicial(rs.getDate("F_INICIAL"));
					objIntentoReporteR29.setFechaFinal(rs.getDate("F_FINAL"));
					objIntentoReporteR29.setIdGrupo(rs.getInt("ID_GRUPO"));

		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getCause());
		}
		return objIntentoReporteR29;
	}

}
