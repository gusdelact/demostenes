/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.operativos.model.OficioCnbvModel;

/**
 *Mapper usado para el modelo de un oficio 
 *Se ocupa en el flujo de negocio generacion de archivo CNBV
 *  @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public class OficioCnbvMapper implements RowMapper<OficioCnbvModel> {

	public OficioCnbvModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		OficioCnbvModel oficioModel = new OficioCnbvModel();
		
		oficioModel.setNumOficio(rs.getString("NUM_OFICIO"));
		oficioModel.setTipoOficio(rs.getString("TIPO_OFICIO"));
		oficioModel.setNumFolio(rs.getString("NUM_FOLIO"));
		oficioModel.setNumExped(rs.getString("NUM_EXPED"));
		oficioModel.setNumRegistro(rs.getString("NUM_REGISTRO"));
		oficioModel.setCveAutoridad(rs.getString("CVE_AUTORIDAD"));
		oficioModel.setIdEmpresa(rs.getInt("ID_EMPRESA"));
		oficioModel.setFhOficio(rs.getDate("F_OFICIO"));
		oficioModel.setFhRecepcion(rs.getDate("F_RECEPCION"));
		oficioModel.setNumDiasPzo(rs.getInt("NUM_DIAS_PZO"));
		oficioModel.setTxDireccion(rs.getString("TX_DIRECCION"));
		oficioModel.setTxGerencia(rs.getString("TX_GERENCIA"));
		oficioModel.setTxSubgeren(rs.getString("TX_SUBGEREN"));
		oficioModel.setTxAtnNom(rs.getString("TX_ATN_NOM"));
		oficioModel.setTxAtnPue(rs.getString("TX_ATN_PUE"));
		oficioModel.setbTurAudit(rs.getString("B_TUR_AUDIT"));
		oficioModel.setSitOficio(rs.getString("SIT_OFICIO"));
		oficioModel.setFhEnvio(rs.getDate("FH_ENVIO"));
		oficioModel.setCveUsuEnvio(rs.getString("CVE_USU_ENVIO"));
		oficioModel.setFhAlta(rs.getDate("FH_ALTA"));
		oficioModel.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
		oficioModel.setFhUltMod(rs.getDate("FH_ULT_MOD"));
		oficioModel.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		oficioModel.setTxNomArch(rs.getString("TX_NOM_ARCH"));
		oficioModel.setTxNomAcu(rs.getString("TX_NOM_ACU"));
		oficioModel.setCveUsuAcu(rs.getString("CVE_USU_ACU"));
		oficioModel.setFhRegAcu(rs.getDate("FH_REG_ACU"));
		oficioModel.setFhVencimiento(rs.getDate("F_VENCIMIENTO"));
		oficioModel.setTieneAcuse("V".equals(rs.getString("TIENE_ACUSE")));
		oficioModel.setTipoCaso(rs.getString("TIPO_CASO"));
		oficioModel.setCveEstatus(rs.getString("CVE_ESTATUS"));
		
		oficioModel.setDescTipoOficio(rs.getString("DESC_TIPO_OFICIO"));
		oficioModel.setDescEmpresa(rs.getString("DESC_EMPRESA"));

		return oficioModel;
	}

}
