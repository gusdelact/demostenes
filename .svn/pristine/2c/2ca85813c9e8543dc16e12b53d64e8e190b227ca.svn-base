package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
/**
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public class PersonaMapper implements RowMapper<PersonaModel> {

	public PersonaModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		PersonaModel persona = new PersonaModel();

		persona.setApMaterno(rs.getString("AP_MATERNO"));
		persona.setApPaterno(rs.getString("AP_PATERNO"));
		persona.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
		persona.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		persona.setFhAlta(rs.getDate("FH_ALTA"));
		persona.setFhUltMod(rs.getDate("FH_ULT_MOD"));
		persona.setIdPersona(rs.getObject("ID_PERSONA")!= null?rs.getLong("ID_PERSONA"):null);//Se modifica para evitar que se obtenga valor 0 cuando es nulo en la BD
		persona.setNombre(rs.getString("NOMBRE"));
		persona.setNumConsec(rs.getLong("NUM_CONSEC"));
		persona.setNumDictamen(rs.getString("NUM_DICTAMEN"));
		persona.setNumOficio(rs.getString("NUM_OFICIO"));
		persona.setRfc(rs.getString("RFC"));
		persona.setSitRespuesta(rs.getString("SIT_RESPUESTA"));
		persona.setTipoOficio(rs.getString("TIPO_OFICIO"));
		persona.setTipoPersona(rs.getString("TIPO_PERSONA"));
		persona.setFhAlta(rs.getDate("FH_ALTA"));
		persona.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
		persona.setFhUltMod(rs.getDate("FH_ULT_MOD"));
		persona.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		persona.setCuenta(rs.getString("CUENTA"));
		
		return persona;
	}

}
