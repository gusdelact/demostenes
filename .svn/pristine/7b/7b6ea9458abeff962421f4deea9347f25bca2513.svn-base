package com.gfi.bin.admctasweb.procesoautomatico.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public class BitacoraCargaAutomaticaMapper implements RowMapper<BitacoraCargaAutomaticaModel>{
	
	/**
	 * Realiza la conversión de Resultset a objeto de tipo BitacoraCargaAutomaticaModel
	 * 
	 * @param Restultset
	 * @param int
	 * @return BitacoraCargaAutomaticaModel
	 */
	public BitacoraCargaAutomaticaModel mapRow(ResultSet rs, int rownum) throws SQLException{
		BitacoraCargaAutomaticaModel bitacoraCargaAutomaticaModel = new BitacoraCargaAutomaticaModel();
		
		bitacoraCargaAutomaticaModel.setIdCarga(rs.getInt("ID_CARGA"));
		bitacoraCargaAutomaticaModel.setIdEmpresa(rs.getInt("ID_EMPRESA"));
		bitacoraCargaAutomaticaModel.setExcelConciliador(rs.getString("EXCEL_CONCILIA"));
		bitacoraCargaAutomaticaModel.setFechaHoraRegistro(rs.getDate("FH_REG"));
		
		return bitacoraCargaAutomaticaModel;
	}
}