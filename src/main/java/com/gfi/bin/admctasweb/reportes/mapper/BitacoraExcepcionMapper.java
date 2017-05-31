/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionModel;

/**
 * @author MUDF4038
 *
 */
public class BitacoraExcepcionMapper implements RowMapper<BitacoraExcepcionModel> {

	@Override
	public BitacoraExcepcionModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		BitacoraExcepcionModel bitExcepcion = new BitacoraExcepcionModel();
		try {
			//estas mutaciones podrian lanzar un IllegalArgumentException si los valores son nulos
			bitExcepcion.setIdContrato(rs.getLong("ID_CONTRATO"));
			bitExcepcion.setfExcepcion(rs.getDate("F_EXCEPCION"));
			bitExcepcion.sethExcepcion(rs.getInt("H_EXCEPCION"));
			bitExcepcion.setCveTipoExcepcion(rs.getString("CVE_TIP_EXCEP"));
			bitExcepcion.setDescExcepcion(rs.getString("DESC_EXCEPCION"));
			bitExcepcion.setCveUsuario(rs.getString("CVE_USUARIO"));
			bitExcepcion.setMotivo(rs.getString("TX_MOTIVO"));
			bitExcepcion.setSitExcepcion(rs.getString("SIT_EXCEPCION"));
			bitExcepcion.setTitular(rs.getString("TITULAR"));
			bitExcepcion.setDescUsuario(rs.getString("USUARIO"));
			bitExcepcion.setSituacion(rs.getString("SITUACION"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getCause());
		}
		return bitExcepcion;
	}
}
