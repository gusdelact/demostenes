/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.operativos.model.ImpresionDictamenJurModel;

/**
 * @author LUGL4884
 *
 */
public class ImpresionDictamenJurMapper implements RowMapper<ImpresionDictamenJurModel> {

	public ImpresionDictamenJurModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ImpresionDictamenJurModel impDictamen = new ImpresionDictamenJurModel();
		
		impDictamen.setNumOficio(rs.getString("NUM_OFICIO"));
		impDictamen.setTipoOficio(rs.getString("TIPO_OFICIO"));
		impDictamen.setNumConsec(rs.getLong("NUM_CONSEC"));
		impDictamen.setNombTitular(rs.getString("NOMB_TITULAR"));
		impDictamen.setFhAlta(rs.getDate("FH_ALTA"));
		impDictamen.setImpresoContr(rs.getString("IMPRESO_POR_CONTRALORIA"));
		
		return impDictamen;
	}

}
