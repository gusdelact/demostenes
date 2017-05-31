/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;

/**
 * Mapper para la Bitacora de Seguimiento de Oficios.
 * @author LUGL4884
 */
public class BitacoraSeguimientoMapper implements RowMapper<BitacoraSeguimientoModel> {

	public BitacoraSeguimientoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		try {
			seguimiento.setNumOficio(rs.getString("NUM_OFICIO"));
			seguimiento.setTipoOficio(rs.getString("TIPO_OFICIO"));
			seguimiento.setCveEstatus(rs.getString("CLAVE_ESTATUS"));
			seguimiento.setDescripcion(rs.getString("DESCRIPCION"));
			seguimiento.setFhRegistro(rs.getTimestamp("FH_REGISTRO"));
			seguimiento.setCveUsuario(rs.getString("CLAVE_USUARIO"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getCause());
		}
		return seguimiento;
	}

}
