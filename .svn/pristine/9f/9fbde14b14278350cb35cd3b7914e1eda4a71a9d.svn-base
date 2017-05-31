/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public class CasosEspecialesMapper implements RowMapper<CasosEspecialesModel>{

	public CasosEspecialesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		CasosEspecialesModel casosEspeciales = new CasosEspecialesModel();
		try {
			//estas mutaciones podrian lanzar un IllegalArgumentException si los valores son nulos
			casosEspeciales.setNumOficio(rs.getString("NUM_OFICIO"));
			casosEspeciales.setTipoOficio(rs.getString("TIPO_OFICIO"));
			if (rs.findColumn("DESC_TIPO_OFICIO") > 0) {
				casosEspeciales.setDescTipoOficio(rs.getString("DESC_TIPO_OFICIO"));
			}						
			casosEspeciales.setNumFolio(rs.getString("NUM_FOLIO"));
			casosEspeciales.setNumExped(rs.getString("NUM_EXPED"));
			casosEspeciales.setNumRegistro(rs.getString("NUM_REGISTRO"));
			casosEspeciales.setFhRecepcion(rs.getDate("F_RECEPCION"));
			casosEspeciales.setFhEnvio(rs.getDate("FH_ENVIO"));
			casosEspeciales.setCveAutoridad(rs.getString("CVE_AUTORIDAD"));
			casosEspeciales.setNomAutoridad(rs.getString("NOM_AUTORIDAD"));
			casosEspeciales.setIdEmpresa(rs.getInt("ID_EMPRESA"));
			casosEspeciales.setDescEmpresa(rs.getString("DESC_EMPRESA"));
			casosEspeciales.setCveEmpresa(rs.getString("CVE_EMPRESA"));
			casosEspeciales.setSitOficio(rs.getString("SIT_OFICIO"));
			casosEspeciales.setTipoCaso(rs.getString("TIPO_CASO"));
			casosEspeciales.setTieneAcuse("V".equals(rs.getString("TIENE_ACUSE")));
			casosEspeciales.setAcuseFlag(rs.getString("TIENE_ACUSE"));
			
			casosEspeciales.setNumConsec(rs.getInt("NUM_CONSEC"));
			casosEspeciales.setNombTitular(rs.getString("NOMB_TITULAR"));
			casosEspeciales.setCveEstatus( rs.getString("CVE_ESTATUS") );
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getCause());
		}
		return casosEspeciales;
	}

}
