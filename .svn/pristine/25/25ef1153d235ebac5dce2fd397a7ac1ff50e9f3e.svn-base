package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.operativos.model.ContratoModel;

public class ContratoMapper implements RowMapper<ContratoModel> {

	public ContratoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ContratoModel contratoModel = new ContratoModel();
		
		contratoModel.setIdContrato(rs.getLong("ID_CONTRATO"));
		contratoModel.setTipoContrato(rs.getString("DESC_TIPO_CONTRA"));
		contratoModel.setProducto(rs.getString("DESC_PRODUCTO"));
		contratoModel.setFechaAlta(rs.getTimestamp("F_ALTA"));
		contratoModel.setFechaBaja(rs.getTimestamp("F_BAJA"));
		contratoModel.setCliente(rs.getString("NOMBRE"));
		contratoModel.setSituacionContrato(rs.getString("SIT_CONTRATO"));
		contratoModel.setTipoContratante(rs.getString("TIPO_CONTRATANTE"));
		contratoModel.setCveContratante(rs.getString("CVE_CONTRATANTE"));
		
		contratoModel.setNombreContrato(rs.getString("NOMB_CONTRATO"));
		
		return contratoModel;
	}
}