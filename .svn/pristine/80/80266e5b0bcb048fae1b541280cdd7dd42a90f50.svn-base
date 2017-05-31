package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.catalogos.model.RespContrato;

public class RespContratoMapper implements RowMapper<RespContrato>{

	@Override
	public RespContrato mapRow(ResultSet rs, int rowNum) throws SQLException {
		RespContrato respContrato = new RespContrato();
		
		respContrato.setNumOficio(rs.getString("NUM_OFICIO"));
		respContrato.setTipoOficio(rs.getString("TIPO_OFICIO"));
		respContrato.setIdContrato(rs.getLong("ID_CONTRATO"));
		respContrato.setFhAlta(rs.getTimestamp("FH_ALTA"));
		respContrato.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
		respContrato.setFhMod(rs.getTimestamp("FH_ULT_MOD"));
		respContrato.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		respContrato.setMontoInicial(rs.getLong("MONTO_INICIAL"));
		respContrato.setRecordCount(rs.getInt("RECORDCOUNT"));
		
		return respContrato;
	}
}