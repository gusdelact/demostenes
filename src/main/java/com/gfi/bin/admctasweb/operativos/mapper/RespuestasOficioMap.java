package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import com.gfi.bin.admctasweb.operativos.model.RespuestaOficioModel;

/**
 * Mapea la respuesta de un Oficio para casos especiales
 * @author ESS3VAVC
 *
 */
public class RespuestasOficioMap implements RowMapper<RespuestaOficioModel>{

	LobHandler lobHandler = new DefaultLobHandler();	
	
	/**
	 * Mapeo de la respuesta de un Oficio
	 */
	public RespuestaOficioModel mapRow(ResultSet rs, int rowNum)throws SQLException {
		// TODO Auto-generated method stub
		RespuestaOficioModel respuestaOficioModel = new RespuestaOficioModel();
		
		respuestaOficioModel.setNumOficio(rs.getString("NUM_OFICIO"));
		respuestaOficioModel.setTipoOficio(rs.getString("TIPO_OFICIO"));
		respuestaOficioModel.setIdRespuesta(rs.getInt("ID_RESP"));
		respuestaOficioModel.setObservaciones(lobHandler.getClobAsString(rs, "TX_OBSERVACION"));
		respuestaOficioModel.setTipoRequerimiento(rs.getString("TIPO_REQ"));
		respuestaOficioModel.setTipoSolicitud(rs.getString("TIPO_SOL"));
		respuestaOficioModel.setRespuestaEnviada(rs.getString("RESP_ENVIO"));
		
		respuestaOficioModel.setIdDireccion(rs.getObject("ID_CONFIG_DIR")!= null?rs.getInt("ID_CONFIG_DIR"):null);
		
		respuestaOficioModel.setFhAlta(rs.getDate("FH_ALTA"));
		respuestaOficioModel.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
		respuestaOficioModel.setFhUltMod(rs.getDate("FH_ULT_MOD"));
		respuestaOficioModel.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		
	
		return respuestaOficioModel;
	}

}
