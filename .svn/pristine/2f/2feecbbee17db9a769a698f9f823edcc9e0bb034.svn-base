package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.operativos.mapper.CnbvDictamenModel;
/**
 * Mapper del modelo CnbvDictamenModel
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public class CnbvDictamenMapper implements RowMapper<CnbvDictamenModel> {

	public CnbvDictamenModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	
		CnbvDictamenModel pojo = new CnbvDictamenModel();
		
		pojo.setAplicaCtasMonedaExt( rs.getString("APLICA_CTAS_MONEDA_EXT") );
		pojo.setAplicaCtasMonedaNal( rs.getString("APLICA_CTAS_MONEDA_NAL") );
		pojo.setBloqueoAperturaCta( rs.getString("BLOQUEO_APERTURA_CTA") );
		pojo.setCajasSeguridad( rs.getString("CAJAS_SEGURIDAD") );
		pojo.setCaracterContrato( rs.getString("CARACTER_CONTRATO") );
		pojo.setComentarios( rs.getString("COMENTARIOS") );
		pojo.setCopiaCertificada( rs.getString("COPIA_CERTIFICADA") );
		pojo.setCopiaSimple( rs.getString("COPIA_SIMPLE") );
		pojo.setCreditos( rs.getString("CREDITOS") );
		pojo.setCuentaCheques( rs.getString("CUENTA_CHEQUES") );
		pojo.setCveUsuAlta( rs.getString("CVE_USU_ALTA") );
		pojo.setCveUsuMod( rs.getString("CVE_USU_MOD") );
		pojo.setDocIdentifCuenta( rs.getString("DOC_IDENTIF_CUENTA") );
		pojo.setEmbargoParcial( rs.getString("EMBARGO_PARCIAL") );
		pojo.setEmbargoParcialCta( rs.getString("EMBARGO_PARCIAL_CTA") );
		pojo.setEmbargoParcialMonto( rs.getBigDecimal("EMBARGO_PARCIAL_MONTO") );
		pojo.setEmbargoParcialPorc( rs.getBigDecimal("EMBARGO_PARCIAL_PORC") );
		pojo.setEmbargoTotal( rs.getString("EMBARGO_TOTAL") );
		pojo.setEmbargoTotalCta( rs.getString("EMBARGO_TOTAL_CTA") );
		pojo.setEspecificar( rs.getString("ESPECIFICAR") );
		pojo.setEspecificar2( rs.getString("ESPECIFICAR2") );
		pojo.setEspecificar3( rs.getString("ESPECIFICAR3") );
		pojo.setEstadosCuenta( rs.getString("ESTADOS_CUENTA") );
		pojo.setFhAlta( rs.getTimestamp("FH_ALTA") );
		pojo.setFhUltMod( rs.getDate("FH_ULT_MOD") );
		pojo.setFideicomisos( rs.getString("FIDEICOMISOS") );
		pojo.setIdPersona( rs.getLong("ID_PERSONA") );
		pojo.setImpresoPorContraloria( rs.getString("IMPRESO_POR_CONTRALORIA") );
		pojo.setInfoGralCuenta( rs.getString("INFO_GRAL_CUENTA") );
		pojo.setIntermediacionBursatil( rs.getString("INTERMEDIACION_BURSATIL") );
		pojo.setInversiones( rs.getString("INVERSIONES") );
		pojo.setLevantamtoParcialEmb( rs.getString("LEVANTAMTO_PARCIAL_EMB") );
		pojo.setLevantamtoTotalEmb( rs.getString("LEVANTAMTO_TOTAL_EMB") );
		pojo.setMonedaEmbargoParcial( rs.getLong("MONEDA_EMBARGO_PARCIAL") );
		pojo.setMonedaLevParcialEmb( rs.getLong("MONEDA_LEV_PARCIAL_EMB") );
		pojo.setMontoEmbargoParcial( rs.getBigDecimal("MONTO_EMBARGO_PARCIAL") );
		pojo.setMontoLevParcialEmb( rs.getBigDecimal("MONTO_LEV_PARCIAL_EMB") );
		pojo.setNumConsec( rs.getLong("NUM_CONSEC") );
		pojo.setNumOficio( rs.getString("NUM_OFICIO") );
		pojo.setOperacionDivisas( rs.getString("OPERACION_DIVISAS") );
		pojo.setOperacionesInusuales( rs.getString("OPERACIONES_INUSUALES") );
		pojo.setOperacionesRelevantes( rs.getString("OPERACIONES_RELEVANTES") );
		pojo.setOtros( rs.getString("OTROS") );
		pojo.setOtros2( rs.getString("OTROS2") );
		pojo.setOtros3( rs.getString("OTROS3") );
		pojo.setReportesOperPld( rs.getString("REPORTES_OPER_PLD") );
		pojo.setTarjetaRegFirma( rs.getString("TARJETA_REG_FIRMA") );
		pojo.setTipoFideicomisos( rs.getString("TIPO_FIDEICOMISOS") );
		pojo.setTipoOficio( rs.getString("TIPO_OFICIO") );
		pojo.setTransfMonedaExt( rs.getString("TRANSF_MONEDA_EXT") );
		pojo.setTransfMonedaNal( rs.getString("TRANSF_MONEDA_NAL") );
		
		pojo.setCveUsuImpresion( rs.getString("CVE_USU_IMPRESION") );
		pojo.setFhImpresion( rs.getTimestamp("FH_IMPRESION") );
		
		//Mappers para la cabezera
		pojo.setNumExped( rs.getString("NUM_EXPED") );
		pojo.setNumFolio( rs.getString("NUM_FOLIO") );
		pojo.setNumRegistro( rs.getString("NUM_REGISTRO") );
		pojo.setFhOficio( rs.getDate("F_OFICIO") );
		pojo.setFhRecepcion( rs.getDate("F_RECEPCION") );
		pojo.setNumDiasPzo( rs.getLong("NUM_DIAS_PZO") );
		pojo.setIdEmpresa( rs.getLong("ID_EMPRESA") );
		pojo.setNombre( rs.getString("DESC_EMPRESA") );
		pojo.setDescEmpresa(rs.getString("DESC_EMPRESA") );
		pojo.setNombreTitular( rs.getString("NOMB_TITULAR") );
		pojo.setRfc(rs.getString("RFC"));
		
		pojo.setDescMonedaEmbargoParcial( rs.getString("DESC_MONEDA_EMBARGO_PARCIAL") );
		pojo.setDescMonedaLevParcialEmb( rs.getString("DESC_MONEDA_LEV_PARCIAL_EMB") );
		
		
		return pojo;
	}

}
