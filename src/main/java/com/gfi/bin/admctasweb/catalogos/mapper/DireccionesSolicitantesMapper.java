/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.catalogos.model.DireccionesSolicitantesModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public class DireccionesSolicitantesMapper implements RowMapper<DireccionesSolicitantesModel>{

	public DireccionesSolicitantesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DireccionesSolicitantesModel dsModel = new DireccionesSolicitantesModel();
		try {
			//estas mutaciones podrian lanzar un IllegalArgumentException si los valores son nulos
			dsModel.setIdConfiguracion(rs.getInt("ID_CONFIG_DIR"));
			dsModel.setTipoOficio(rs.getString("TIPO_OFICIO"));
			dsModel.setDireccion(rs.getString("TX_DIRECCION"));
			dsModel.setGerencia(rs.getString("TX_GERENCIA"));
			dsModel.setSubgerencia(rs.getString("TX_SUBGERENCIA"));
			dsModel.setNombreAtencion(rs.getString("TX_ATN_NOMBRE"));
			dsModel.setPuestoAtencion(rs.getString("TX_ATN_PUESTO"));
			dsModel.setSituacion(rs.getString("SIT_CONFIG_DIR"));
			dsModel.setNivel(rs.getInt("NIVEL"));
			dsModel.setFhAlta(rs.getDate("FH_ALTA"));
			dsModel.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
			dsModel.setFhUltMod(rs.getDate("FH_ULT_MOD"));
			dsModel.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getCause());
		}
		return dsModel;
	}

}
