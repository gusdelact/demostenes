package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.operativos.model.RespuestaModel;

public class RespuestaMapper implements RowMapper<RespuestaModel> {

	public RespuestaModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		RespuestaModel respuesta = new RespuestaModel();
		
		respuesta.setNumOficio(rs.getString("NUM_OFICIO"));
		respuesta.setTipoOficio(rs.getString("TIPO_OFICIO"));
		respuesta.setNumConsec(rs.getLong("NUM_CONSEC"));
		respuesta.setIdContrato(rs.getLong("ID_CONTRATO"));
		Long tipoRespuesta = rs.getLong("TIPO_RESPUESTA");
		respuesta.setTipoRespuesta(tipoRespuesta);
		respuesta.setTipoCaso(rs.getString("TIPO_CASO"));
		respuesta.setSitEnvio(rs.getString("SIT_ENVIO"));
		respuesta.setbMedioElec(rs.getString("B_MEDIO_ELEC"));
		respuesta.setNomTitular(rs.getString("NOMB_TITULAR"));
		respuesta.setTipoTitular(rs.getLong("TIPO_TITULAR"));
		Long sitCuenta = rs.getLong("SIT_CUENTA");
		respuesta.setSitCuenta(sitCuenta);
		respuesta.setFhAlta(rs.getDate("FH_ALTA"));
		respuesta.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
		respuesta.setFhUltMod(rs.getDate("FH_ULT_MOD"));
		respuesta.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		
		String descSitCuenta = "No Aplica";
		if(sitCuenta != null)
		{
			if(sitCuenta == 1)
				descSitCuenta = "Vigente";
			else if(sitCuenta == 2)
				descSitCuenta = "Cancelada";
		}
		respuesta.setDescSitCuenta(descSitCuenta);

		String descTipoRespuesta = "";
		if(tipoRespuesta != null)
		{
			if(tipoRespuesta == 1)
				descTipoRespuesta = "Total";
			else if(tipoRespuesta == 2)
				descTipoRespuesta = "Parcial";
			else if(tipoRespuesta == 3)
				descTipoRespuesta = "Complementaria";
			else if(tipoRespuesta == 4)
				descTipoRespuesta = "Aclaratoria";
			
		}
		respuesta.setDescTipoRespuesta(descTipoRespuesta);
		
		return respuesta;
 	}

}
