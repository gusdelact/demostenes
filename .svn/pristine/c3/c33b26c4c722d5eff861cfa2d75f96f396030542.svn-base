package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gfi.bin.admctasweb.operativos.model.ContratoCambiosModel;

public class ContratoCambiosMapper implements RowMapper<ContratoCambiosModel> {

	public ContratoCambiosModel mapRow(ResultSet rs, int rowNum)throws SQLException {
		// TODO Auto-generated method stub
		ContratoCambiosModel contratoCambiosModel = new ContratoCambiosModel();
		
		contratoCambiosModel.setIdContrato(rs.getLong("ID_CONTRATO"));
		contratoCambiosModel.setFechaOperacion(rs.getTimestamp("F_OPERACION"));
		contratoCambiosModel.setMoneda(rs.getString("DESC_C_MONEDA"));
		contratoCambiosModel.setSituacionContrato(rs.getString("SIT_CONTRATO"));
		
		return contratoCambiosModel;
	}

}
