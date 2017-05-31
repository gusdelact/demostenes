/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualModel;

/**
 * @author LUGL4884
 *
 */
public class CapturaManualContratoMapper implements RowMapper<CapturaManualModel> {

	public CapturaManualModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		CapturaManualModel capturaManualModel = new CapturaManualModel();
		
		capturaManualModel.setIdCuenta(rs.getInt("ID_CONTRATO"));
		capturaManualModel.setMonedaCuenta(rs.getInt("MONEDA_CUENTA"));
		capturaManualModel.setTxMonedaAsegurada(rs.getString("ASEGURA"));
		capturaManualModel.setProducto(rs.getString("PRODUCTO"));
		capturaManualModel.setTxProducto(rs.getString("DESCRIPCION_PRODUCTO"));
		capturaManualModel.setModalidad(rs.getString("MODALIDAD"));
		capturaManualModel.setTxModalidad(rs.getString("DESCRIPCION_MODALIDAD"));
		capturaManualModel.setCodigoPostal(rs.getString("CODIGO_POSTAL"));
		capturaManualModel.setLocalidad(rs.getLong("LOCALIDAD"));
		capturaManualModel.setEstado(rs.getInt("ESTADO"));
		capturaManualModel.setTxSucursal(rs.getString("NOMBRE_SUCURSAL"));
		capturaManualModel.setIdSucursal(rs.getString("ID_SUCURSAL"));
		capturaManualModel.setTipoNivel(rs.getString("TIPO_NIVEL"));
		capturaManualModel.setTxTipoNivel(rs.getString("DESCRIPCION_TIPO_NIVEL"));
		capturaManualModel.setPersoTitular(rs.getString("PERSO_TITULAR"));
		capturaManualModel.setTxPersonaTitular(rs.getString("DESC_TITULAR"));
		capturaManualModel.setRfcTitular(rs.getString("RFC_TITULAR"));
		capturaManualModel.setRazonTitular(rs.getString("RAZON_TITULAR"));
		capturaManualModel.setNombreTitular(rs.getString("NOMBRE_TITULAR"));		
		capturaManualModel.setPaternoTitular(rs.getString("PATERNO_TITULAR"));		
		capturaManualModel.setMaternoTitular(rs.getString("MATERNO_TITULAR"));		
		capturaManualModel.setIdCotitular(rs.getLong("ID_COTITULAR"));	
		capturaManualModel.setPersoCoti(rs.getInt("PERSO_COTI"));
		capturaManualModel.setTxPersonaCoti(rs.getString("DESC_COTI"));
		capturaManualModel.setRfcCoti(rs.getString("RFC_COTITULAR"));		
		capturaManualModel.setRazonCoti(rs.getString("RAZON_COTI"));		
		capturaManualModel.setNombreCoti(rs.getString("NOMBRE_COTI"));		
		capturaManualModel.setPaternoCoti(rs.getString("PATERNO_COTI"));		
		capturaManualModel.setMaternoCoti(rs.getString("MATERNO_COTI"));
		
		return capturaManualModel;
	}

}
