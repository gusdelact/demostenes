/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.comunes.model.ItemModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public class DireccionesSolicitantesCatalogoMapper implements RowMapper<ItemModel>{

	public ItemModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		ItemModel itemModel = new ItemModel();
		
		itemModel.setClave(rs.getString("Id_Config_Dir"));
		itemModel.setDescripcion(rs.getString("Tx_Gerencia")==null?"N/A":rs.getString("Tx_Gerencia"));	
		
		return itemModel;
	}

}
