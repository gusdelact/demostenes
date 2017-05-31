package com.gfi.bin.admctasweb.operativos.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.mapper.CnbvDictamenMapper;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.dao.CnbvDictamenDao;
import com.gfi.bin.admctasweb.operativos.mapper.CnbvDictamenModel;

/**
 * Implementacion de las diferentes operaciones que se pueden realizar con la 
 * tabla "CNBV_DICTAMEN" - CnbvDictamenModel interfaz CnbvDictamenDao
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Repository
public class CnbvDictamenDaoImpl implements CnbvDictamenDao {

	final Logger log = LoggerFactory.getLogger(CnbvDictamenDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	/**
	 * Metodo encargado de obtener todas los registro de la tabla "CNBV_DICTAMEN"
	 * @return List<PersonaModel>
	 */
	public List<CnbvDictamenModel> getAllCnbvDictamen() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * ");
		query.append(" FROM CNBV_DICTAMEN");
		return (List<CnbvDictamenModel>) this.jdbcTemplateCorp.query(query.toString(), new CnbvDictamenMapper());
	}
	
	/** 
	 * Inserta un renglon de la tabla CNBV_DICTAMEN
	 * @param persona
	 */
	public void guardarCnbvDictamen(CnbvDictamenModel cnbvDictamen) {
		StringBuilder strB = new StringBuilder();  
		strB.append("INSERT INTO CNBV_DICTAMEN ( ");
		   strB.append(" NUM_OFICIO, TIPO_OFICIO, NUM_CONSEC,  ");
		   strB.append(" ID_PERSONA, INFO_GRAL_CUENTA, DOC_IDENTIF_CUENTA,  ");
		   strB.append(" COPIA_SIMPLE, COPIA_CERTIFICADA, ESTADOS_CUENTA,  ");
		   strB.append(" TARJETA_REG_FIRMA, EMBARGO_TOTAL_CTA, EMBARGO_PARCIAL_CTA,  ");
		   strB.append(" MONTO_EMBARGO_PARCIAL, MONEDA_EMBARGO_PARCIAL, BLOQUEO_APERTURA_CTA,  ");
		   strB.append(" LEVANTAMTO_PARCIAL_EMB, MONTO_LEV_PARCIAL_EMB, MONEDA_LEV_PARCIAL_EMB,  ");
		   strB.append(" LEVANTAMTO_TOTAL_EMB, REPORTES_OPER_PLD, OTROS,  ");
		   strB.append(" ESPECIFICAR, CUENTA_CHEQUES, INVERSIONES,  ");
		   strB.append(" INTERMEDIACION_BURSATIL, CREDITOS, CAJAS_SEGURIDAD,  ");
		   strB.append(" OPERACION_DIVISAS, OTROS2, ESPECIFICAR2,  ");
		   strB.append(" TRANSF_MONEDA_NAL, TRANSF_MONEDA_EXT, OPERACIONES_RELEVANTES,  ");
		   strB.append(" OPERACIONES_INUSUALES, FIDEICOMISOS, TIPO_FIDEICOMISOS,  ");
		   strB.append(" CARACTER_CONTRATO, EMBARGO_PARCIAL, EMBARGO_PARCIAL_MONTO,  ");
		   strB.append(" EMBARGO_PARCIAL_PORC, EMBARGO_TOTAL, OTROS3,  ");
		   strB.append(" ESPECIFICAR3, APLICA_CTAS_MONEDA_NAL, APLICA_CTAS_MONEDA_EXT,  ");
		   strB.append(" COMENTARIOS, IMPRESO_POR_CONTRALORIA, FH_ALTA,  ");
		   strB.append(" CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD, CVE_USU_IMPRESION, FH_IMPRESION)  ");
		strB.append(" VALUES ( ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ");
		    strB.append(" ?, ?, ?, ?, ? ) ");
		    
			jdbcTemplateCorp.update(strB.toString(), new Object[] { 
				cnbvDictamen.getNumOficio(),
				cnbvDictamen.getTipoOficio(),
				cnbvDictamen.getNumConsec(),
				cnbvDictamen.getIdPersona(),
				cnbvDictamen.getInfoGralCuenta(),
				cnbvDictamen.getDocIdentifCuenta(),
				cnbvDictamen.getCopiaSimple(),
				cnbvDictamen.getCopiaCertificada(),
				cnbvDictamen.getEstadosCuenta(),
				cnbvDictamen.getTarjetaRegFirma(),
				cnbvDictamen.getEmbargoTotalCta(),
				cnbvDictamen.getEmbargoParcialCta(),
				cnbvDictamen.getMontoEmbargoParcial(),
				cnbvDictamen.getMonedaEmbargoParcial(),
				cnbvDictamen.getBloqueoAperturaCta(),
				cnbvDictamen.getLevantamtoParcialEmb(),
				cnbvDictamen.getMontoLevParcialEmb(),
				cnbvDictamen.getMonedaLevParcialEmb(),
				cnbvDictamen.getLevantamtoTotalEmb(),
				cnbvDictamen.getReportesOperPld(),
				cnbvDictamen.getOtros(),
				cnbvDictamen.getEspecificar(),
				cnbvDictamen.getCuentaCheques(),
				cnbvDictamen.getInversiones(),
				cnbvDictamen.getIntermediacionBursatil(),
				cnbvDictamen.getCreditos(),
				cnbvDictamen.getCajasSeguridad(),
				cnbvDictamen.getOperacionDivisas(),
				cnbvDictamen.getOtros2(),
				cnbvDictamen.getEspecificar2(),
				cnbvDictamen.getTransfMonedaNal(),
				cnbvDictamen.getTransfMonedaExt(),
				cnbvDictamen.getOperacionesRelevantes(),
				cnbvDictamen.getOperacionesInusuales(),
				cnbvDictamen.getFideicomisos(),
				cnbvDictamen.getTipoFideicomisos(),
				cnbvDictamen.getCaracterContrato(),
				cnbvDictamen.getEmbargoParcial(),
				cnbvDictamen.getEmbargoParcialMonto(),
				cnbvDictamen.getEmbargoParcialPorc(),
				cnbvDictamen.getEmbargoTotal(),
				cnbvDictamen.getOtros3(),
				cnbvDictamen.getEspecificar3(),
				cnbvDictamen.getAplicaCtasMonedaNal(),
				cnbvDictamen.getAplicaCtasMonedaExt(),
				cnbvDictamen.getComentarios(),
				cnbvDictamen.getImpresoPorContraloria(),
				cnbvDictamen.getFhAlta(),
				cnbvDictamen.getCveUsuAlta(),
				cnbvDictamen.getFhUltMod(),
				cnbvDictamen.getCveUsuMod(),
				cnbvDictamen.getCveUsuImpresion(),	
				cnbvDictamen.getFhImpresion()
			});  	
	}
	
	/** 
	 * Actualiza un renglon de la tabla CNBV_DICTAMEN
	 * @param cnbvDictamen
	 */
	public void actualizarCnbvDictamen(CnbvDictamenModel cnbvDictamen) {
		StringBuilder strB = new StringBuilder();  
		strB.append("UPDATE CNBV_DICTAMEN ");
		strB.append(" SET    ID_PERSONA              = ?, ");
		       strB.append(" INFO_GRAL_CUENTA        = ?, ");
		       strB.append(" DOC_IDENTIF_CUENTA      = ?, ");
		       strB.append(" COPIA_SIMPLE            = ?, ");
		       strB.append(" COPIA_CERTIFICADA       = ?, ");
		       strB.append(" ESTADOS_CUENTA          = ?, ");
		       strB.append(" TARJETA_REG_FIRMA       = ?, ");
		       strB.append(" EMBARGO_TOTAL_CTA       = ?, ");
		       strB.append(" EMBARGO_PARCIAL_CTA     = ?, ");
		       strB.append(" MONTO_EMBARGO_PARCIAL   = ?, ");
		       strB.append(" MONEDA_EMBARGO_PARCIAL  = ?, ");
		       strB.append(" BLOQUEO_APERTURA_CTA    = ?, ");
		       strB.append(" LEVANTAMTO_PARCIAL_EMB  = ?, ");
		       strB.append(" MONTO_LEV_PARCIAL_EMB   = ?, ");
		       strB.append(" MONEDA_LEV_PARCIAL_EMB  = ?, ");
		       strB.append(" LEVANTAMTO_TOTAL_EMB    = ?, ");
		       strB.append(" REPORTES_OPER_PLD       = ?, ");
		       strB.append(" OTROS                   = ?, ");
		       strB.append(" ESPECIFICAR             = ?, ");
		       strB.append(" CUENTA_CHEQUES          = ?, ");
		       strB.append(" INVERSIONES             = ?, ");
		       strB.append(" INTERMEDIACION_BURSATIL = ?, ");
		       strB.append(" CREDITOS                = ?, ");
		       strB.append(" CAJAS_SEGURIDAD         = ?, ");
		       strB.append(" OPERACION_DIVISAS       = ?, ");
		       strB.append(" OTROS2                  = ?, ");
		       strB.append(" ESPECIFICAR2            = ?, ");
		       strB.append(" TRANSF_MONEDA_NAL       = ?, ");
		       strB.append(" TRANSF_MONEDA_EXT       = ?, ");
		       strB.append(" OPERACIONES_RELEVANTES  = ?, ");
		       strB.append(" OPERACIONES_INUSUALES   = ?, ");
		       strB.append(" FIDEICOMISOS            = ?, ");
		       strB.append(" TIPO_FIDEICOMISOS       = ?, ");
		       strB.append(" CARACTER_CONTRATO       = ?, ");
		       strB.append(" EMBARGO_PARCIAL         = ?, ");
		       strB.append(" EMBARGO_PARCIAL_MONTO   = ?, ");
		       strB.append(" EMBARGO_PARCIAL_PORC    = ?, ");
		       strB.append(" EMBARGO_TOTAL           = ?, ");
		       strB.append(" OTROS3                  = ?, ");
		       strB.append(" ESPECIFICAR3            = ?, ");
		       strB.append(" APLICA_CTAS_MONEDA_NAL  = ?, ");
		       strB.append(" APLICA_CTAS_MONEDA_EXT  = ?, ");
		       strB.append(" COMENTARIOS             = ?, ");
		       strB.append(" IMPRESO_POR_CONTRALORIA = ?, ");
//		       strB.append(" FH_ALTA                 = ?, ");
//		       strB.append(" CVE_USU_ALTA            = ?, ");
		       strB.append(" FH_ULT_MOD              = ?, ");
		       strB.append(" CVE_USU_MOD             = ?, ");
		       strB.append(" CVE_USU_IMPRESION       = ?, ");
		       strB.append(" FH_IMPRESION            = ? ");
		       
		strB.append(" WHERE  NUM_OFICIO              = ? ");
		strB.append(" AND    TIPO_OFICIO             = ? ");
		strB.append(" AND    NUM_CONSEC              = ? ");
		
		jdbcTemplateCorp.update(strB.toString(), new Object[] { 		
			cnbvDictamen.getIdPersona(),
			cnbvDictamen.getInfoGralCuenta(),
			cnbvDictamen.getDocIdentifCuenta(),
			cnbvDictamen.getCopiaSimple(),
			cnbvDictamen.getCopiaCertificada(),
			cnbvDictamen.getEstadosCuenta(),
			cnbvDictamen.getTarjetaRegFirma(),
			cnbvDictamen.getEmbargoTotalCta(),
			cnbvDictamen.getEmbargoParcialCta(),
			cnbvDictamen.getMontoEmbargoParcial(),
			cnbvDictamen.getMonedaEmbargoParcial(),
			cnbvDictamen.getBloqueoAperturaCta(),
			cnbvDictamen.getLevantamtoParcialEmb(),
			cnbvDictamen.getMontoLevParcialEmb(),
			cnbvDictamen.getMonedaLevParcialEmb(),
			cnbvDictamen.getLevantamtoTotalEmb(),
			cnbvDictamen.getReportesOperPld(),
			cnbvDictamen.getOtros(),
			cnbvDictamen.getEspecificar(),
			cnbvDictamen.getCuentaCheques(),
			cnbvDictamen.getInversiones(),
			cnbvDictamen.getIntermediacionBursatil(),
			cnbvDictamen.getCreditos(),
			cnbvDictamen.getCajasSeguridad(),
			cnbvDictamen.getOperacionDivisas(),
			cnbvDictamen.getOtros2(),
			cnbvDictamen.getEspecificar2(),
			cnbvDictamen.getTransfMonedaNal(),
			cnbvDictamen.getTransfMonedaExt(),
			cnbvDictamen.getOperacionesRelevantes(),
			cnbvDictamen.getOperacionesInusuales(),
			cnbvDictamen.getFideicomisos(),
			cnbvDictamen.getTipoFideicomisos(),
			cnbvDictamen.getCaracterContrato(),
			cnbvDictamen.getEmbargoParcial(),
			cnbvDictamen.getEmbargoParcialMonto(),
			cnbvDictamen.getEmbargoParcialPorc(),
			cnbvDictamen.getEmbargoTotal(),
			cnbvDictamen.getOtros3(),
			cnbvDictamen.getEspecificar3(),
			cnbvDictamen.getAplicaCtasMonedaNal(),
			cnbvDictamen.getAplicaCtasMonedaExt(),
			cnbvDictamen.getComentarios(),
			cnbvDictamen.getImpresoPorContraloria(),
//			cnbvDictamen.getFhAlta(),
//			cnbvDictamen.getCveUsuAlta(),
			cnbvDictamen.getFhUltMod(),
			cnbvDictamen.getCveUsuMod(),
			cnbvDictamen.getCveUsuImpresion(),	
			cnbvDictamen.getFhImpresion(),
			
			
			cnbvDictamen.getNumOficio(),
			cnbvDictamen.getTipoOficio(),
			cnbvDictamen.getNumConsec()
		});  	
		
	}
	
	/**
	 * Consulta si existe una dictamen juridico de acuerdo a su PK. Incluye los datos de la cabecera del oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @return CnbvDictamenModel
	 */
	public CnbvDictamenModel buscarDatosOficioAndRegistro(CnbvDictamenModel cnbvDictamen ){
		StringBuilder query = new StringBuilder();
		query.append(" SELECT CO.NUM_EXPED, CO.NUM_FOLIO, CO.NUM_REGISTRO, CO.F_OFICIO, ");
		query.append(" CO.F_RECEPCION, CO.NUM_DIAS_PZO, CO.ID_EMPRESA, EE.DESC_EMPRESA, ");
		query.append(" (CP.NOMBRE || ' ' || CP.AP_PATERNO || ' ' || CP.AP_MATERNO) AS NOMB_TITULAR, CP.RFC,");
		query.append(" (SELECT DESC_MONEDA FROM MONEDA WHERE CVE_MONEDA = CD.MONEDA_EMBARGO_PARCIAL) AS DESC_MONEDA_EMBARGO_PARCIAL, ");
		query.append(" (SELECT DESC_MONEDA FROM MONEDA WHERE CVE_MONEDA = CD.MONEDA_LEV_PARCIAL_EMB) AS DESC_MONEDA_LEV_PARCIAL_EMB, ");
		query.append(" CD.* ");
		query.append(" FROM CNBV_OFICIO CO, CNBV_PERSONA CP, ");
		query.append(" EXP_EMPRESA EE, CNBV_DICTAMEN CD ");  
		query.append(" WHERE CO.TIPO_OFICIO = CP.TIPO_OFICIO ");
		query.append(" AND CO.NUM_OFICIO = CP.NUM_OFICIO ");
		query.append(" AND CO.ID_EMPRESA = EE.ID_EMPRESA ");
		query.append(" AND CO.NUM_OFICIO = CD.NUM_OFICIO ");
		query.append(" AND CO.TIPO_OFICIO = CD.TIPO_OFICIO ");
		query.append(" AND CP.NUM_CONSEC = CD.NUM_CONSEC ");
		query.append(" AND CO.TIPO_CASO = 'PO' ");
		query.append(" AND CD.NUM_OFICIO = ? ");
		query.append(" AND CD.TIPO_OFICIO = ? ");
		query.append(" AND CD.NUM_CONSEC = ?");

		try{
			return jdbcTemplateCorp.queryForObject(query.toString(), 
											new Object[] { cnbvDictamen.getNumOficio(), cnbvDictamen.getTipoOficio(), cnbvDictamen.getNumConsec() }, 
											new CnbvDictamenMapper());
		} catch (EmptyResultDataAccessException e) {			
			log.error("La consulta viene vacia " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		
			return null;
	}


	/** 
	 * Busqueda del campo impreso por contraloria  'IMPRESO_POR_CONTRALORIA' de un dictamen juridico por la llave primaria compuesta
	 *  'numOficio,tipoOficio,numConsec' de la tabla CNBV_DICTAMEN
	 * @param cnbvDictamen
	 * @return V si esta marcada como impresa
	 */
	public String buscarFlagContraloriaCnbvDictamen( CnbvDictamenModel cnbvDictamen ) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT IMPRESO_POR_CONTRALORIA ");
		query.append(" FROM CNBV_DICTAMEN dic ");
	
		if (cnbvDictamen != null
				&& StringUtils.isNotBlank(cnbvDictamen.getTipoOficio())) {
			query.append(" WHERE dic.TIPO_OFICIO = ? ");
		}

		if (cnbvDictamen != null
				&& StringUtils.isNotBlank(cnbvDictamen.getNumOficio())) {
			query.append(" AND dic.NUM_OFICIO = ? ");
		}

		if (cnbvDictamen != null && cnbvDictamen.getNumConsec() != null) {
			query.append(" AND dic.NUM_CONSEC = ? ");
		}
		try{
			return jdbcTemplateCorp.queryForObject(query.toString(), new Object[] {
				cnbvDictamen.getTipoOficio(), cnbvDictamen.getNumOficio(),
				cnbvDictamen.getNumConsec() }, String.class);
		} catch (EmptyResultDataAccessException e) {
			log.error("La consulta viene vacia " + e.getLocalizedMessage());		    
		}
		
			return null;
	}

	/**
	 * Consulta si existe una dictamen juridico de acuerdo a su PK.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 */
	public int existeCnbvDictamen(String numOficio, String tipoOficio,Long numConsec) {
		StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(*)");
		query.append(" FROM CNBV_DICTAMEN "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		query.append(" AND NUM_CONSEC = ? "); 
		
		return (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio,numConsec}, Integer.class));
	}	
	/**
	 * Obtiene el siguiente consecutivo para el dictamen juridico
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	public int obtenerConsecutivo(String numOficio, String tipoOficio){
		StringBuilder query = new StringBuilder();
		query.append(" SELECT NVL(MAX(NUM_CONSEC),0) + 1 ");
		query.append(" FROM CNBV_DICTAMEN "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 		
		return (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio}, Integer.class));
	}
	
	/**
	 * Invoca al store procedure ADMIPROD.SP_CNBV_REG_DIC_JUR , cuando regresa 0 significa que todos las respuestas ya fueron registradas
	 * cualquier otro valor equivale al numero de respuestas faltantes por registrar 
	 * @param numOficio
	 * @return
	 */
	@Override
	public int obtenerFlagRegistroJur(String numOficio) {
		
		BigDecimal respuesta = new BigDecimal(0);		
		try {
			
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplateCorp)
													.withSchemaName("ADMIPROD")
													.withCatalogName("PKG_ADMCTAWEB")
													.withProcedureName("SP_CNBV_REG_DIC_JUR");
			
			Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("peNumOficio", numOficio);
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);

			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			respuesta = (BigDecimal) simpleJdbcCallResult.get("PSCODRESP");

			return respuesta.intValue();			
		} 
		catch (EmptyResultDataAccessException e) {			
			log.error("Error al marcar el Dictamen Jurídico" + e.getLocalizedMessage());
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;		
	}
	
}
