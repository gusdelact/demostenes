package com.gfi.bin.admctasweb.procesoautomatico.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaOficioModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public class BitacoraCargaAutomaticaOficioMapper implements RowMapper<BitacoraCargaAutomaticaOficioModel>{
	
	/**
	 * Realiza la conversión de Resultset a objeto de tipo BitacoraCargaAutomaticaOficioModel
	 * 
	 * @param Restultset
	 * @param int
	 * @return BitacoraCargaAutomaticaOficioModel
	 */
	public BitacoraCargaAutomaticaOficioModel mapRow(ResultSet rs, int rownum) throws SQLException{
		BitacoraCargaAutomaticaOficioModel bitacoraCargaAutomaticaOficioModel = new BitacoraCargaAutomaticaOficioModel();
		
		bitacoraCargaAutomaticaOficioModel.setIdCarga(rs.getInt("ID_CARGA"));
		bitacoraCargaAutomaticaOficioModel.setIdEmpresa(rs.getInt("ID_EMPRESA"));
		bitacoraCargaAutomaticaOficioModel.setNumeroOficio(rs.getString("NUM_OFICIO"));
		bitacoraCargaAutomaticaOficioModel.setTipoOficio(rs.getString("TIPO_OFICIO"));
		bitacoraCargaAutomaticaOficioModel.setExisteXML(rs.getString("EXISTE_XML"));
		
		return bitacoraCargaAutomaticaOficioModel;
	}
}