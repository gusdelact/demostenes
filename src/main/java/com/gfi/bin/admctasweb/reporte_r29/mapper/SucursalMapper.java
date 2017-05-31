/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reporte_r29.model.SucursalModel;

/**
 * @author LUGL4884
 *
 */
public class SucursalMapper implements RowMapper<SucursalModel> {

	public SucursalModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		SucursalModel sucursalModel = new SucursalModel();
		
		sucursalModel.setIdEmpresa(rs.getString("ID_EMPRESA"));
		sucursalModel.setIdSucursal(rs.getString("ID_SUCURSAL"));
		sucursalModel.setEstado(rs.getInt("ESTADO"));
		sucursalModel.setLocalidad(rs.getLong("LOCALIDAD"));
		sucursalModel.setCodigoPostal(rs.getString("CODIGO_POSTAL"));
		sucursalModel.setNomSucursal(rs.getString("NOM_SUCURSAL"));	
		sucursalModel.setTxInstitucion(rs.getString("DESCRIPCION"));
		return sucursalModel;
	}

}
