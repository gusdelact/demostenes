/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualModel;

/**
 * @author LUGL4884
 *
 */
public class CapturaManualMapper implements RowMapper<CapturaManualModel> {

	public CapturaManualModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		CapturaManualModel capturaManualModel = new CapturaManualModel();
		
		capturaManualModel.setIdEmpresa(rs.getString("ID_EMPRESA"));
		capturaManualModel.setCvePeriodo(rs.getInt("CVE_PERIODO"));
		capturaManualModel.setDescPeriodo(rs.getString("DESC_PERIODO"));
		capturaManualModel.setNumIntento(rs.getInt("NUM_INTENTO"));
		capturaManualModel.setSituacion(rs.getString("SITUACION"));
		capturaManualModel.setInstitucion(rs.getString("INSTITUCION"));
		capturaManualModel.setMedioSolicitado(rs.getInt("MEDIO_SOL"));
		capturaManualModel.setAutoridad(rs.getString("AUTORIDAD"));
		capturaManualModel.setDescripcion(rs.getString("DESCRIPCION"));
		capturaManualModel.setNumOficio(rs.getString("NUM_OFICIO"));
		capturaManualModel.setTipoOficio(rs.getString("TIPO_OFICIO"));
		capturaManualModel.setFechaSolicitud(rs.getLong("F_SOLICITUD"));
		capturaManualModel.setFolioSiara(rs.getString("FOLIO_SIARA"));
		capturaManualModel.setMontoSolicitado(rs.getLong("MONTO_SOLICITADO"));		
		capturaManualModel.setPersoTitular(rs.getString("PERSO_TITULAR"));
		capturaManualModel.setCaracTitular(rs.getString("CARAC_TITULAR"));
		capturaManualModel.setRfcTitular(rs.getString("RFC_TITULAR"));
		System.out.println(" = "+capturaManualModel.getRfcTitular().length()+"=="+capturaManualModel.getRfcTitular());
		if(capturaManualModel.getRfcTitular().length() == 12){
			capturaManualModel.setRfcTitular("_"+capturaManualModel.getRfcTitular());
		}
		System.out.println(" = "+capturaManualModel.getRfcTitular().length()+"=="+capturaManualModel.getRfcTitular());
		capturaManualModel.setRazonTitular(rs.getString("RAZON_TITULAR"));		
		capturaManualModel.setNombreTitular(rs.getString("NOMBRE_TITULAR"));		
		capturaManualModel.setPaternoTitular(rs.getString("PATERNO_TITULAR"));		
		capturaManualModel.setMaternoTitular(rs.getString("MATERNO_TITULAR"));		
		capturaManualModel.setIdCotitular(rs.getLong("ID_COTITULAR"));	
		capturaManualModel.setPersoCoti(rs.getInt("PERSO_COTI"));	
		capturaManualModel.setCaracCoti(rs.getString("CARAC_COTI"));		
		capturaManualModel.setRfcCoti(rs.getString("RFC_COTITULAR"));		
		capturaManualModel.setRazonCoti(rs.getString("RAZON_COTI"));		
		capturaManualModel.setNombreCoti(rs.getString("NOMBRE_COTI"));		
		capturaManualModel.setPaternoCoti(rs.getString("PATERNO_COTI"));		
		capturaManualModel.setMaternoCoti(rs.getString("MATERNO_COTI"));		
		capturaManualModel.setIdSucursal(rs.getString("ID_SUCURSAL"));		
		capturaManualModel.setEstado(rs.getInt("ESTADO"));
		capturaManualModel.setLocalidad(rs.getLong("LOCALIDAD"));
		capturaManualModel.setCodigoPostal(rs.getString("CODIGO_POSTAL"));
		capturaManualModel.setModalidad(rs.getString("MODALIDAD"));
		capturaManualModel.setTipoNivel(rs.getString("TIPO_NIVEL"));
		capturaManualModel.setIdCuenta(rs.getInt("ID_CUENTA"));
		capturaManualModel.setProducto(rs.getString("PRODUCTO"));
		capturaManualModel.setMonedaCuenta(rs.getInt("MONEDA_CUENTA"));		
		capturaManualModel.setMontoInicial(rs.getLong("MONTO_INICIAL"));	
		capturaManualModel.setOperacion(rs.getInt("OPERACION"));	
		capturaManualModel.setOficioReqOpe(rs.getString("OFICIO_REQ_OPE"));
		capturaManualModel.setFecharRequerimiento(rs.getLong("F_REQUERIMIENTO"));
		capturaManualModel.setSiaraIndirecto(rs.getString("SIARA_INDIRECTO"));
		capturaManualModel.setFechaAplicacion(rs.getLong("F_APLICACION"));
		capturaManualModel.setMontoOperacion(rs.getLong("MONTO_OPERCION"));
		capturaManualModel.setMonedaOperacion(rs.getInt("MONEDA_OPERACION"));
		capturaManualModel.setSaldoOperacion(rs.getLong("SALDO_OPERACION"));
//		capturaManualModel.setFecha(rs.getDate("FECHA"));
		
		return capturaManualModel;
	}

}
