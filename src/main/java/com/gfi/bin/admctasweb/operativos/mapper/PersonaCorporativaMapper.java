package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.operativos.model.PersonaCorporativaModel;


public class PersonaCorporativaMapper implements RowMapper<PersonaCorporativaModel>{

	public PersonaCorporativaModel mapRow(ResultSet rs, int rowNum)throws SQLException {
		
		PersonaCorporativaModel personaCorporativaModel = new PersonaCorporativaModel();
		
		personaCorporativaModel.setIdPersona(rs.getLong("ID_PERSONA"));
		personaCorporativaModel.setNombre(rs.getString("NOMBRE"));
		personaCorporativaModel.setSituacion(rs.getString("SIT_PERSONA"));
		personaCorporativaModel.setSiglasRFC(rs.getString("SIGLAS_RFC"));
		personaCorporativaModel.setFechaRFC(rs.getString("F_RFC"));
		personaCorporativaModel.setHomoclaveRFC(rs.getString("HOMOCLAVE_RFC"));
		
		personaCorporativaModel.setRfc(personaCorporativaModel.getSiglasRFC() + 
										personaCorporativaModel.getFechaRFC() + 
										personaCorporativaModel.getHomoclaveRFC());
		try{
			personaCorporativaModel.setIdContrato(rs.getString("ID_CONTRATO"));
		}catch(SQLException sqlException){
			personaCorporativaModel.setIdContrato("");
		}
		return personaCorporativaModel;
	}
}