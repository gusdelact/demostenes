package com.gfi.bin.admctasweb.procesoautomatico.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaPersonaModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public class BitacoraCargaAutomaticaPersonaMapper implements RowMapper<BitacoraCargaAutomaticaPersonaModel>{
	
	/**
	 * Realiza la conversión de Resultset a objeto de tipo BitacoraCargaAutomaticaPersonaModel
	 * 
	 * @param Restultset
	 * @param int
	 * @return BitacoraCargaAutomaticaPersonaModel
	 */
	public BitacoraCargaAutomaticaPersonaModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		BitacoraCargaAutomaticaPersonaModel bitacoraCargaAutomaticaPersonaModel = new BitacoraCargaAutomaticaPersonaModel();
		
		bitacoraCargaAutomaticaPersonaModel.setIdCarga(rs.getInt("ID_CARGA"));
		bitacoraCargaAutomaticaPersonaModel.setIdEmpresa(rs.getInt("ID_EMPRESA"));
		bitacoraCargaAutomaticaPersonaModel.setNumeroOficio(rs.getString("NUM_OFICIO"));
		bitacoraCargaAutomaticaPersonaModel.setTipoOficio(rs.getString("TIPO_OFICIO"));
		bitacoraCargaAutomaticaPersonaModel.setNumeroConsecutivoPersona(rs.getInt("NUM_CONSEC_PERSONA"));
		bitacoraCargaAutomaticaPersonaModel.setObservaciones(rs.getString("OBSERVACIONES"));
		
		return bitacoraCargaAutomaticaPersonaModel;
	}
}