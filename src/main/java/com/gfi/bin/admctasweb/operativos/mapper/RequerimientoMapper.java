package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.comunes.model.ItemModel;

public class RequerimientoMapper implements RowMapper<ItemModel>{

	/**
	 * Mapeo de un tipo de Requerimiento
	 */
	public ItemModel mapRow(ResultSet rs, int rowNum)throws SQLException {
		// TODO Auto-generated method stub
		ItemModel itemModel = new ItemModel();
		
		itemModel.setClave(rs.getString("ID_TIP_REQ"));
		itemModel.setDescripcion(rs.getString("DESC_TIP_REQ"));
		
		return itemModel;
	}

}
