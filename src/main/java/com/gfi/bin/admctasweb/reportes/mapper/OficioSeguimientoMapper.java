/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reportes.model.OficioSeguimientoModel;

/**
 * @author LUGL4884
 *
 */
public class OficioSeguimientoMapper implements RowMapper<OficioSeguimientoModel> {

	public OficioSeguimientoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		OficioSeguimientoModel oficioSeguimiento = new OficioSeguimientoModel();
		
		oficioSeguimiento.setNumOficio(rs.getString("NUM_OFICIO"));
		oficioSeguimiento.setTipoOficio(rs.getString("TIPO_OFICIO"));
		oficioSeguimiento.setNumFolio(rs.getString("NUM_FOLIO"));
		oficioSeguimiento.setNumExped(rs.getString("NUM_EXPED"));
		oficioSeguimiento.setNumRegistro(rs.getString("NUM_REGISTRO"));
		oficioSeguimiento.setCveAutoridad(rs.getString("CVE_AUTORIDAD"));
		oficioSeguimiento.setIdEmpresa(rs.getInt("ID_EMPRESA"));
		oficioSeguimiento.setFhOficio(rs.getDate("F_OFICIO"));
		oficioSeguimiento.setFhRecepcion(rs.getDate("F_RECEPCION"));
		oficioSeguimiento.setSitOficio(rs.getString("SIT_OFICIO"));
		oficioSeguimiento.setFhEnvio(rs.getDate("FH_ENVIO"));
		oficioSeguimiento.setFhVencimiento(rs.getDate("F_VENCIMIENTO"));
		oficioSeguimiento.setTipoCaso(rs.getString("TIPO_CASO"));
		oficioSeguimiento.setCveEstatus(rs.getString("CVE_ESTATUS"));
		oficioSeguimiento.setTxEstatus(rs.getString("TX_ESTATUS"));
		oficioSeguimiento.setCveEstatusPend(rs.getString("CVE_ESTATUS_PEND"));
		oficioSeguimiento.setTxEstatusPend(rs.getString("TX_ESTATUS_PEND"));
		
		return oficioSeguimiento;
	}

}
