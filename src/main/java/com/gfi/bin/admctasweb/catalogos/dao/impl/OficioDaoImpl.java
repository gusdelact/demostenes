/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.OficioDao;
import com.gfi.bin.admctasweb.catalogos.mapper.OficioMapper;
import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.enums.SituacionOficioEnum;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.mapper.OficioCnbvMapper;
import com.gfi.bin.admctasweb.operativos.model.ArhivoCnbvModel;
import com.gfi.bin.admctasweb.operativos.model.OficioCnbvModel;
import com.gfi.bin.admctasweb.reportes.mapper.CasosEspecialesMapper;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;

/**
 * @author LUGL4884
 * Clase que Implementa la persistencia de los Oficios.
 */
@Component
@Repository
public class OficioDaoImpl implements OficioDao {
	private static final Logger LOGGER = LoggerFactory.getLogger(OficioDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;

	private String convertido;
	DateFormat fecha = new SimpleDateFormat("yyyyMMdd");
	long valor=0;

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.OficioDao#guardarOficio(OficioModel)
	 */
	public boolean guardarOficio(OficioModel oficio) throws DAOException {
		
		LOGGER.info("En el método guardarOficio");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO CNBV_OFICIO ( ");
		query.append(" NUM_OFICIO, " + 
				     " TIPO_OFICIO, " +
				     " NUM_FOLIO, " + 
				     " NUM_EXPED, " + 
				     " NUM_REGISTRO, " +
				     " CVE_TIPO_OPERACION, " +//
				     " CVE_AUTORIDAD, " +
				     " CVE_AUTORIDAD_XML, " +
				     " NOMBRE_AUT_XML, " +
				     " ID_EMPRESA," + 
				     " F_OFICIO," + 
				     " F_RECEPCION," + 
				     " NUM_DIAS_PZO," +
				     " TX_DIRECCION," +
				     " TX_GERENCIA," + 
				     " TX_SUBGEREN," + 
				     " TX_ATN_NOM," + 
				     " TX_ATN_PUE," + 
				     " B_TUR_AUDIT," +
				     " SIT_OFICIO, " +
				     " FH_ALTA," +
				     " CVE_USU_ALTA,"+
				     " FOLIO_SIARA," + 
				     " CARACTER," + 
				     " DESC_CARACTER," +
				     " MONTO_SOLICITADO," +
				     " OFICIO_REQ_OPE," + 
				     " F_REQUERIMIENTO," +
				     " F_APLICACION, " + 
				     " MONTO_REQ_AUTO, " +
				     //" MONTO_INICIAL, " + 
				     " MONEDA_REQ_AUTO) ");
				     //" SALDO_DESPUES_OPE)" );
		
		query.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"); 
		
		LOGGER.info("Validamos el oficio requerido");
		String oficioReqOper =  oficio.getReferencia2()!=null&&oficio.getReferencia2().length() > 25 ? "" : oficio.getReferencia2();
		LOGGER.info("Valor asignado a la variable oficioReqOper:"+oficioReqOper);
		
		try {
			
			jdbcTemplateCorp.update(query.toString(), oficio.getNumOficio(), 
													  oficio.getTipoOficio(), 
													  oficio.getNumFolio(), 
													  oficio.getNumExped(),
													  oficio.getNumRegistro(), 
													  oficio.getTipoOper(), 
													  oficio.getCveAutoridad(), 
													  oficio.getCveAutoridadXml(),
													  oficio.getNombreAutXml(), 
													  oficio.getIdEmpresa(), 
													  oficio.getFhOficio(), 
													  oficio.getFhRecepcion(),
													  oficio.getNumDiasPzo(), 
													  oficio.getTxDireccion(), 
													  oficio.getTxGerencia(), 
													  oficio.getTxSubgeren(), 
													  oficio.getTxAtnNom(),
													  oficio.getTxAtnPue(),
													  oficio.getbTurAudit(), 
													  oficio.getSitOficio(),
													  oficio.getFhAlta(),
  													  oficio.getCveUsuAlta(),
													  oficio.getFolioSiara(), 
													  oficio.getCaracter(), 
													  oficio.getDescCaracter(), 
													  oficio.getMontoSolicitado(),
													  oficioReqOper, 
													  oficio.getfRequerimiento(), 
													  oficio.getfAplicacion(), 
													  oficio.getMontoRequerido(), 
													  //oficio.getMontoInicial(),
													  oficio.getMonedaRequerida()
//													  , 
//													  oficio.getSaldoDespuesOper()
													  );
			result = true;
		LOGGER.info(query.toString());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}
	
	public boolean guardarCaracter(OficioModel oficio) throws DAOException {
		//JAPL
		LOGGER.info("En el método guardarCaracter");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
				
		convertido = (String) fecha.format(oficio.getFhOficio());
		valor= Long.valueOf(convertido);
				
		query.append(" UPDATE CNBV_OFICIO SET  FOLIO_SIARA ='"+ oficio.getFolioSiara()+"', "
				//+ " OFICIO_REQ_OPE ='"+ oficio.getReferencia2()+"', "
				+ " NOMBRE_AUT_XML ='"+ oficio.getNombreAutXml()+"', "
				+ " F_REQUERIMIENTO ='"+ valor+"', "
				+ " F_APLICACION ='"+ oficio.getfAplicacion()+"', "
				//+ " MONTO_SOLICITADO ='"+ oficio.getMontoSolicitado()+"', "
				//+ " MONTO_INICIA ='"+ oficio.getMontoInicial()+"', "
				//+ " MONTO_REQ_AUTO ='"+ oficio.getMontoRequerido()+"', "
				+ " MONEDA_REQ_AUTO ='"+ oficio.getMonedaRequerida()+"', "
				+ " DESC_CARACTER = '"+ oficio.getCaracter());
		query.append("', CARACTER = (SELECT DISTINCT CVE_SITI FROM CNBV_CAT_MAPEO "
				+ " WHERE DESCRIPCION = '" + oficio.getCaracter() + "' "
				+ " AND ID_CATALOGO = 9 ) "); 
		query.append(", CVE_AUTORIDAD_XML = (SELECT DISTINCT CVE_SITI FROM CNBV_CAT_MAPEO "
                + " WHERE DESCRIPCION = '" + oficio.getNombreAutXml() + "' "
                + " AND ID_CATALOGO = 10 ) , CVE_TIPO_OPERACION = '' "); 
		/*if(oficio.getContratos()!=null){
			query.append(", MONEDA_REQ_AUTO = (SELECT DISTINCT A.CVE_SITI FROM CNBV_CAT_MAPEO A, CONTRATO B "
					+ " WHERE A.CVE_CORPORATIVA = B.CVE_MONEDA  AND B.ID_CONTRATO ='" + oficio.getContratos() + "' "
					+ " AND A.ID_CATALOGO = 6 )  "); 
		}*/
		query.append(" WHERE NUM_OFICIO = '" + oficio.getNumOficio() + "' "); 
		query.append(" AND TIPO_OFICIO = '" + oficio.getTipoOficio() + "' "); 
							
		try {
			jdbcTemplateCorp.update(query.toString());
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.OficioDao#modificarOficio(OficioModel)
	 */
	public boolean modificarOficio(OficioModel oficio)  throws DAOException{
		LOGGER.info("En el método modificarOficio");
		
		Boolean result =  false;

//		StringBuilder query = new StringBuilder();
//		query.append(" UPDATE CNBV_OFICIO SET ");
//		query.append(" NUM_OFICIO =     ?, TIPO_OFICIO =   ?, NUM_FOLIO =   ?, NUM_EXPED =    ?, NUM_REGISTRO = ?, CVE_AUTORIDAD = ?, ");
//		query.append(" ID_EMPRESA =     ?, F_OFICIO =      ?, F_RECEPCION = ?, NUM_DIAS_PZO = ?, TX_DIRECCION = ?, TX_GERENCIA =   ?, "); 
//		query.append(" TX_SUBGEREN =    ?, TX_ATN_NOM =    ?, TX_ATN_PUE =  ?, B_TUR_AUDIT =  ?, SIT_OFICIO =   ?, FH_ENVIO =      ?, "); 
//		query.append(" CVE_USU_ENVIO =  ?, FH_ULT_MOD =    ?, CVE_USU_MOD = ?, TX_NOM_ARCH =  ?, TX_NOM_ACU =   ?, CVE_USU_ACU =   ?, "); 
//		query.append(" FH_REG_ACU =     ?, F_VENCIMIENTO = ?, TIENE_ACUSE = ?, TIPO_CASO =    ?, ");
		
		
//		// JAPL
//		query.append(" CVE_ESTATUS = ?, F_APLICACION = ?, MONTO_SOLICITADO = ?, MONTO_INICIAL = ?, MONTO_REQ_AUTO = ?, ");
//		query.append(" FOLIO_SIARA = ?, OFICIO_REQ_OPE = ?, NOMBRE_AUT_XML = ?, F_REQUERIMIENTO = ? , ");
//		query.append(" DESC_CARACTER = ?, CARACTER = ?, MONEDA_REQ_AUTO = ?, CVE_AUTORIDAD = ? )");
//		// JAPL
		
		StringBuilder query = new StringBuilder();
		query.append("UPDATE CNBV_OFICIO SET ");
		query.append("NUM_OFICIO=        ?, TIPO_OFICIO=       ?, NUM_FOLIO=      ?, NUM_EXPED=      ?, NUM_REGISTRO=  ?,  CVE_TIPO_OPERACION= ?, " +
					 "CVE_AUTORIDAD=     ?, CVE_AUTORIDAD_XML= ?, NOMBRE_AUT_XML= ?, ID_EMPRESA=     ?, F_OFICIO=      ?,  F_RECEPCION=        ?, " +
					 "NUM_DIAS_PZO=      ?, TX_DIRECCION=      ?, TX_GERENCIA=    ?, TX_SUBGEREN=    ?, TX_ATN_NOM=    ?,  TX_ATN_PUE=         ?, B_TUR_AUDIT=      ?, " +
					 "SIT_OFICIO=        ?, FH_ULT_MOD=        ?, CVE_USU_MOD=    ?, FOLIO_SIARA=    ?, CARACTER=      ?,  DESC_CARACTER=      ?, MONTO_SOLICITADO= ?, " +
					 "OFICIO_REQ_OPE=    ?, F_REQUERIMIENTO=   ?, F_APLICACION=   ?, MONTO_REQ_AUTO= ?, MONEDA_REQ_AUTO= ?");
		query.append(" WHERE NUM_OFICIO = '" + oficio.getNumOficio() + "' "); 
		query.append(" AND TIPO_OFICIO = '" + oficio.getTipoOficio() + "' "); 
		
//		try {
//			jdbcTemplateCorp.update(query.toString(), 
//							new Object[]{oficio.getNumOficio(),          oficio.getTipoOficio(),
//										 oficio.getNumFolio(),           oficio.getNumExped(),	     oficio.getNumRegistro(),
//										 oficio.getCveAutoridad(),       oficio.getIdEmpresa(),      oficio.getFhOficio(),
//										 oficio.getFhRecepcion(),        oficio.getNumDiasPzo(),     oficio.getTxDireccion(),
//										 oficio.getTxGerencia(),	     oficio.getTxSubgeren(),     oficio.getTxAtnNom(),
//										 oficio.getTxAtnPue(),           oficio.getbTurAudit(),      oficio.getSitOficio(),
//										 oficio.getFhEnvio(),            oficio.getCveUsuEnvio(),    oficio.getFhUltMod(), 
//										 oficio.getCveUsuMod(),	         oficio.getTxNomArch(),      oficio.getTxNomAcu(), 
//										 oficio.getCveUsuAcu(),          oficio.getFhRegAcu(),       oficio.getFhVencimiento(), 
//										 oficio.getTieneAcuse()?"V":"F", oficio.getTipoCaso(),       oficio.getCveEstatus(),
//										
//										//JAPL
//										 oficio.getfAplicacion(),        oficio.getMontoSolicitado(), oficio.getMontoInicial(),
//										 oficio.getMontoRequerido(),     oficio.getDescCaracter(),    oficio.getCaracter(),
//									  	 oficio.getMonedaRequerida(),    oficio.getCveAutoridadXml()
//								        //JAPL
//							}
//			);
//			result = true;
//		} catch (DataAccessException e) {
//			LOGGER.error(e.getLocalizedMessage());
//			throw new DAOException(e);
//		}
		
		
		try {
			jdbcTemplateCorp.update(
					query.toString(),
					new Object[] {  oficio.getNumOficio(),
									oficio.getTipoOficio(),  
									oficio.getNumFolio(),
									oficio.getNumExped(),    
									oficio.getNumRegistro(),
									oficio.getTipoOper(),    
									oficio.getCveAutoridad(),
									oficio.getCveAutoridadXml(),
									oficio.getNombreAutXml(),
									oficio.getIdEmpresa(),   
									oficio.getFhOficio(),
									oficio.getFhRecepcion(), 
									oficio.getNumDiasPzo(),
									oficio.getTxDireccion(), 
									oficio.getTxGerencia(),
									oficio.getTxSubgeren(),  
									oficio.getTxAtnNom(),
									oficio.getTxAtnPue(),    
									oficio.getbTurAudit(),
									oficio.getSitOficio(),  
									oficio.getFhUltMod(),
									oficio.getCveUsuMod(), 
									oficio.getFolioSiara(),
									oficio.getCaracter(),   
									oficio.getDescCaracter(),
									oficio.getMontoSolicitado(),
									oficio.getReferencia2(),
									oficio.getfRequerimiento(),
									oficio.getfAplicacion(),
									oficio.getMontoRequerido(),
									//oficio.getMontoInicial(),
									oficio.getMonedaRequerida()
									//, 
									//oficio.getSaldoDespuesOper()
									

					});
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.OficioDao#eliminarOficio(OficioModel)
	 */
	public boolean eliminarOficio(OficioModel oficio)  throws DAOException{
		LOGGER.info("En el método eliminarOficio");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" DELETE FROM CNBV_OFICIO ");
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		
		try {
			jdbcTemplateCorp.update(query.toString(), new Object[]{oficio.getNumOficio(), oficio.getTipoOficio()});
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.OficioDaoImpl#buscarOficioPorLlave(String, String)
	 */
	public OficioModel buscarOficioPorLlave(String numOficio, String tipoOficio) throws DAOException{
		LOGGER.info("En el método buscarOficioPorLlave");
		OficioModel oficio =  new OficioModel();
		
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT" +
				"		NUM_OFICIO," +
				"		TIPO_OFICIO," +
				"		NUM_FOLIO," +
				"		NUM_EXPED," +
				"		NUM_REGISTRO," +
				"		CVE_AUTORIDAD," +
				"		ID_EMPRESA," +
				"		F_OFICIO," +
				"		F_RECEPCION," +
				"		NUM_DIAS_PZO," +
				"		TX_DIRECCION," +
				"		TX_GERENCIA," +
				"		TX_SUBGEREN," +
				"		TX_ATN_NOM," +
				"		TX_ATN_PUE," +
				"		B_TUR_AUDIT," +
				"		SIT_OFICIO," +
				"		FH_ENVIO," +
				"		CVE_USU_ENVIO," +
				"		FH_ALTA," +
				"		CVE_USU_ALTA," +
				"		FH_ULT_MOD," +
				"		CVE_USU_MOD," +
				"		TX_NOM_ARCH," +
				"		TX_NOM_ACU," +
				"		CVE_USU_ACU," +
				"		FH_REG_ACU," +
				"		F_VENCIMIENTO," +
				"		TIENE_ACUSE," +
				"		TIPO_CASO," +
				"		CVE_ESTATUS," +
				"		FOLIO_SIARA," +
				"		CARACTER," +
				"		MONTO_SOLICITADO," +
				//"		MONTO_INICIAL," +
				"		OFICIO_REQ_OPE," +
				"	    F_REQUERIMIENTO," +
				"		F_APLICACION," +
				"		MONTO_REQ_AUTO," +
				"		MONEDA_REQ_AUTO," +
				"		PKG_REP_R29.FUNOBTMAPEO_DESCINV(6,MONEDA_REQ_AUTO) AS DESC_MONEDA," +
				//"		SALDO_DESPUES_OPE," +
				"       CVE_TIPO_OPERACION," +
				"		PKG_REP_R29.FUNOBTMAPEO_DESCINV(7,CVE_TIPO_OPERACION) AS DESC_TIPO_OPERACION," +
				"		CVE_AUTORIDAD_XML," +
				"		NOMBRE_AUT_XML," +
				"		DESC_CARACTER");
		query.append(" FROM CNBV_OFICIO ");
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		
		try {
			oficio = jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio}, new OficioMapper());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return oficio;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.OficioDao#modificarSeguimientoOficio
	 */
	public boolean modificarSeguimientoOficio(String numOficio,	String tipoOficio, String cveEstatus, String cveEstatusPend) throws DAOException {
		LOGGER.info("En el método modificarSeguimientoOficio");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE CNBV_OFICIO ");
		query.append(" SET CVE_ESTATUS = ?, "); 
		query.append(" CVE_ESTATUS_PEND = ? "); 
		query.append(" WHERE NUM_OFICIO = '" + numOficio + "' "); 
		query.append(" AND TIPO_OFICIO = '" + tipoOficio + "' "); 
		
		try {
			jdbcTemplateCorp.update(query.toString(), new Object[]{cveEstatus,cveEstatusPend});
			
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.OficioDAO#buscarOficiosCasosEspeciales(CasosEspecialesListModel)
	 */
	public List<CasosEspecialesModel> buscarOficiosCasosEspeciales (CasosEspecialesListModel parametros) throws DAOException {

		StringBuilder query = new StringBuilder();
		
		query.append("SELECT o.NUM_OFICIO, o.TIPO_OFICIO,");
		query.append(" DECODE(o.TIPO_OFICIO,'AS','ASEGURAMIENTO','JU','JUDICIAL','HA','HACENDARIO','PL','PLD','OTRO: '||o.TIPO_OFICIO) AS DESC_TIPO_OFICIO,");
		query.append(" o.NUM_FOLIO, o.NUM_EXPED, o.NUM_REGISTRO, o.F_RECEPCION, o.FH_ENVIO,");
		query.append(" o.CVE_AUTORIDAD, a.NOM_AUTORIDAD,");
		query.append(" o.ID_EMPRESA, e.DESC_EMPRESA, e.CVE_EMPRESA,");
		query.append(" o.SIT_OFICIO, o.TIPO_CASO, o.TIENE_ACUSE,");
		query.append(" resp.NUM_CONSEC, resp.NOMB_TITULAR, o.CVE_ESTATUS ");
		 
		query.append(" FROM CNBV_OFICIO o, CNBV_AUTORIDAD a, EXP_EMPRESA e, CNBV_RESPUESTA resp ");
		query.append(" WHERE o.TIPO_CASO = '" + Constantes.TIPO_CASO_POSITIVO + "'");
		
		query.append(" AND o.NUM_OFICIO    =  resp.NUM_OFICIO (+)");
		query.append(" AND o.TIPO_OFICIO   =  resp.TIPO_OFICIO (+)");
		query.append(" AND o.tipo_caso     =  resp.TIPO_CASO(+) ");
		
		if (parametros != null) {
			if(StringUtils.isNotBlank(parametros.getNumOficio())){
				query.append(" AND o.NUM_OFICIO = :numOficio ");
			}
			if (StringUtils.isNotBlank(parametros.getTipoOficio())) {
				query.append(" AND o.TIPO_OFICIO = :tipoOficio");
			}
			if (parametros.getfIniRecepcion() != null && parametros.getfFinRecepcion() != null) {
				query.append(" AND TRUNC(o.F_RECEPCION) BETWEEN :fIniRecepcion AND :fFinRecepcion ");
			}
			if (parametros.getfIniRespuesta() != null && parametros.getfFinRespuesta() != null) {
				query.append(" AND TRUNC(o.FH_ENVIO) BETWEEN :fIniRespuesta AND :fFinRespuesta ");
			}
			if (parametros.getIdEmpresa() != null) {
				query.append(" AND o.ID_EMPRESA = :idEmpresa");
			}
			if (StringUtils.isNotBlank(parametros.getCveAutoridad()) ) {
				query.append(" AND o.CVE_AUTORIDAD = :cveAutoridad");
			}
			if (parametros.getNumConsec() != null) {
				query.append(" AND  resp.NUM_CONSEC = :numConsec");
			}
		}
		query.append(" AND o.CVE_AUTORIDAD = a.CVE_AUTORIDAD(+)");
		query.append(" AND o.ID_EMPRESA = e.ID_EMPRESA");
		query.append(" ORDER BY o.F_RECEPCION, o.NUM_OFICIO, o.TIPO_OFICIO");
		
		Map<String, Object> mapaParametros = new HashMap<String, Object>();
		mapaParametros.put("numOficio", parametros.getNumOficio());
		mapaParametros.put("tipoOficio", parametros.getTipoOficio());
		mapaParametros.put("fIniRecepcion", parametros.getfIniRecepcion());
		mapaParametros.put("fFinRecepcion", parametros.getfFinRecepcion());
		mapaParametros.put("fIniRespuesta", parametros.getfIniRespuesta());
		mapaParametros.put("fFinRespuesta", parametros.getfFinRespuesta());		
		mapaParametros.put("idEmpresa", parametros.getIdEmpresa());
		mapaParametros.put("cveAutoridad", parametros.getCveAutoridad());
		mapaParametros.put("numConsec", parametros.getNumConsec() );
				
		try {			
			return corpNamedTemplate.query(query.toString(), mapaParametros, new CasosEspecialesMapper());
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
		
	}

	/**
	 * Actualiza la situación de un oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @param cveEstatus
	 * @return
	 * @throws DAOException
	 */	
	@Override
	public boolean modificarSituacionOficio(String numOficio, String tipoOficio, String situacion) throws DAOException {
		
		StringBuffer sql = new StringBuffer("UPDATE CNBV_OFICIO SET SIT_OFICIO = ?");
		
		//sql.append("UPDATE CNBV_OFICIO SET SIT_OFICIO = ? WHERE NUM_OFICIO = ? AND TIPO_OFICIO = ?");

		if(SituacionOficioEnum.ENVIADO.getValor().equals(situacion)){
			sql.append(", FH_ENVIO = SYSDATE");
			sql.append(", CVE_USU_ENVIO = '" + Util.usuarioSesion() + "'");
		}
		sql.append(" WHERE NUM_OFICIO = ? AND TIPO_OFICIO = ?");
		
		
		try {
			int resultado = jdbcTemplateCorp.update(sql.toString(), new Object[]{situacion, numOficio, tipoOficio});
			return resultado > 0;
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}
/**
 * Busca los oficios para la generacion del archivo cnbv
 */
	@Override
	public List<OficioCnbvModel> buscarArhivoCnbvModel(ArhivoCnbvModel objSearch) {
		List<OficioCnbvModel> oficioList = new ArrayList<OficioCnbvModel>();	
		
		StringBuilder query = new StringBuilder();
		query.append("select DECODE(o.TIPO_OFICIO,'AS','ASEGURAMIENTO','JU','JUDICIAL','HA','HACENDARIO','PL','PLD','OTRO: '||o.TIPO_OFICIO) AS DESC_TIPO_OFICIO, ");
		query.append("       e.DESC_EMPRESA, e.CVE_EMPRESA, ");
		query.append("       o.* ");
		query.append("    from CNBV_OFICIO o, EXP_EMPRESA e ");
		query.append("    where o.ID_EMPRESA  = e.ID_EMPRESA ");
		query.append("      and o.TIPO_CASO   = '"+Constantes.TIPO_CASO_NEGATIVO+"'");
		
		if (objSearch.getIdEmpresa() != null) {
			query.append(" AND o.ID_EMPRESA = :idEmpresa");
		}
		if (objSearch.getTipoOficio() != null && StringUtils.isNotBlank(objSearch.getTipoOficio())) {
			query.append(" AND o.TIPO_OFICIO = :tipoOficio");
		}
		if (objSearch.getSitOficio() != null && StringUtils.isNotBlank(objSearch.getSitOficio())) {
			query.append(" AND o.SIT_OFICIO = :sitOficio");
		}
	
		if (objSearch.getFhVencimientoIni() != null && objSearch.getFhVencimientoFin() != null) {
			query.append(" AND o.F_OFICIO BETWEEN :fhVencimientoIni AND :fhVencimientoFin ");
		}else if (objSearch.getFhVencimientoIni() != null) {
			query.append(" AND o.F_OFICIO = :fhVencimientoIni ");
		}
		      
		if (objSearch.getTipoOficio() != null && objSearch.getTipoOficio().equals(Constantes.TIPO_OFICIO_AS) ) {
			query.append(" ORDER BY o.NUM_REGISTRO");
		}else{
			query.append(" ORDER BY o.CVE_USU_ALTA,o.FH_ALTA ");
		}
		
		
		
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("idEmpresa", objSearch.getIdEmpresa());
		parametros.put("tipoOficio", objSearch.getTipoOficio());
		parametros.put("sitOficio", objSearch.getSitOficio());
		parametros.put("fhVencimientoIni", objSearch.getFhVencimientoIni());
		parametros.put("fhVencimientoFin", objSearch.getFhVencimientoFin());
		
			
		oficioList = this.corpNamedTemplate.query(query.toString(), parametros, new OficioCnbvMapper());
			
		return oficioList;
	}
	
	@Override
	public boolean updateArchivotoCnbv(Date fechaEnvio,String cveUsuEnvio,String txNomArch,String numOficio ,String tipoOficio ){
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE CNBV_OFICIO "+
           " SET SIT_OFICIO = :sitOficio, "+
           "     FH_ENVIO   = :fhEnvio, "+
           "  CVE_USU_ENVIO = :cveUsuEnvio,"+
           "    TX_NOM_ARCH = :txNomArch"+
         " WHERE NUM_OFICIO = :numOficio "+
          " AND TIPO_OFICIO = :tipoOficio");
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("sitOficio", Constantes.SITUACION_OFICIO_ENVIADO );
		parametros.put("fhEnvio",  fechaEnvio );
		parametros.put("cveUsuEnvio", cveUsuEnvio );
		parametros.put("txNomArch", txNomArch );
		parametros.put("numOficio", numOficio );
		parametros.put("tipoOficio", tipoOficio );
	
		try {
			int resultado = corpNamedTemplate.update(sql.toString(), parametros);
			return resultado > 0;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		
		return false;	
	}
	
	/**
	 * Busca todos los oficios que tengan asociado el nombre de archivo de texto que llega como parámetro
	 * 
	 * @param nombreArchivo
	 * @return List<OficioModel>
	 * @throws DAOException
	 */
	public List<OficioModel> buscarOficiosPorNombreArchivo(String nombreArchivo) throws DAOException{
		LOGGER.info("En el método buscarOficioPorNombreArchivo");
		List<OficioModel> oficios =  new ArrayList<OficioModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ");
		query.append(" NUM_OFICIO, TIPO_OFICIO, NUM_FOLIO, NUM_EXPED, NUM_REGISTRO, CVE_AUTORIDAD, ID_EMPRESA, ");
		query.append(" F_OFICIO, F_RECEPCION, NUM_DIAS_PZO, TX_DIRECCION, TX_GERENCIA, TX_SUBGEREN, TX_ATN_NOM, "); 
		query.append(" TX_ATN_PUE, B_TUR_AUDIT, SIT_OFICIO, FH_ENVIO, CVE_USU_ENVIO, FH_ALTA, CVE_USU_ALTA, "); 
		query.append(" FH_ULT_MOD, CVE_USU_MOD, TX_NOM_ARCH, TX_NOM_ACU, CVE_USU_ACU, FH_REG_ACU, F_VENCIMIENTO, "); 
		query.append(" TIENE_ACUSE, TIPO_CASO, CVE_ESTATUS, FOLIO_SIARA, CARACTER, MONTO_SOLICITADO, OFICIO_REQ_OPE, ");
		query.append(" F_REQUERIMIENTO, F_APLICACION, MONTO_REQ_AUTO, MONEDA_REQ_AUTO, PKG_REP_R29.FUNOBTMAPEO_DESCINV(6,MONEDA_REQ_AUTO) AS DESC_MONEDA, ");
		query.append(" CVE_TIPO_OPERACION, PKG_REP_R29.FUNOBTMAPEO_DESCINV(7,CVE_TIPO_OPERACION) AS DESC_TIPO_OPERACION, CVE_AUTORIDAD_XML, NOMBRE_AUT_XML, DESC_CARACTER ");
		query.append(" FROM CNBV_OFICIO ");
		query.append(" WHERE TX_NOM_ARCH = ? ");
		
		try {
			oficios = jdbcTemplateCorp.query(query.toString(), new Object[]{nombreArchivo}, new OficioMapper());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return oficios;
	}
	
	/**
	 * Modifica el nombre del acuse, se asigna la cadena del parámetro acuse
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param acuse
	 * @param tieneAcuse
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean modificarAcuseOficio(String numOficio, String tipoOficio, String acuse, String tieneAcuse) throws DAOException {
		LOGGER.info("En el método modificarAcuseOficio");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE CNBV_OFICIO ");
		query.append(" SET TX_NOM_ACU = ?,");
		query.append(" TIENE_ACUSE = ? ");
		query.append(" WHERE NUM_OFICIO = '" + numOficio + "' ");
		query.append(" AND TIPO_OFICIO = '" + tipoOficio + "' ");
		
		try {
			jdbcTemplateCorp.update(query.toString(), new Object[]{acuse, tieneAcuse});
			
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}

}