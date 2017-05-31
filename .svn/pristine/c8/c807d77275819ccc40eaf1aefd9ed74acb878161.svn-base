package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;

/**
 * Mapeo de un item de Solicitud
 * @author ESS3VAVC
 *
 */
public class SolicitudMapper implements RowMapper<ItemModel>{

	/**
	 * Mapeo de un tipo de solicitud
	 */
	public ItemModel mapRow(ResultSet rs, int rowNum)throws SQLException {
		// TODO Auto-generated method stub
		ItemModel itemModel = new ItemModel();
		
		itemModel.setClave(rs.getString("ID_TIP_SOL"));
		itemModel.setDescripcion(rs.getString("DESC_TIP_SOL"));
		
		return itemModel;
	}

}
