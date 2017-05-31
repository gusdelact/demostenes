/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.dao.impl;

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

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.reporte_r29.dao.CapturaManualDao;
import com.gfi.bin.admctasweb.reporte_r29.mapper.CapturaManualContratoMapper;
import com.gfi.bin.admctasweb.reporte_r29.mapper.CapturaManualMapper;
import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualModel;

/**
 * @author LUGL4884 Clase que Implementa la persistencia de la Captura Manual.
 */
@Component
@Repository
public class CapturaManualDaoImpl implements CapturaManualDao {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CapturaManualDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplateCorp;

	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gfi.bin.admctasweb.catalogos.dao.OficioDao#guardarOficio(OficioModel)
	 */
	public boolean guardarCapturaManual(CapturaManualModel capturaManual)
			throws DAOException {

		LOGGER.info("En el método guardarCapturaManual capa dao");
		Boolean result = false;

		if (capturaManual.getRfcTitular().length() == 12) {
			capturaManual.setRfcTitular("_" + capturaManual.getRfcTitular());
		}
		// se pone -1 para evitar error de llave en EXTJS JAPL
		if (capturaManual.getIdCotitular() == 0) {
			capturaManual.setIdCotitular(-1);
		}

		// cambiar el inserty JAPL
		StringBuilder query = new StringBuilder();
		query.append(" INSERT INTO CNBV_DIRECTO ( ");
		query.append("ID_EMPRESA," + " CVE_PERIODO, " + "NUM_INTENTO, "
				+ "SITUACION, " + "INSTITUCION, " + "MEDIO_SOL, "
				+ "AUTORIDAD, " + "DESCRIPCION, " + "NUM_OFICIO, ");
		query.append("TIPO_OFICIO," + " F_SOLICITUD," + " FOLIO_SIARA,"
				+ " MONTO_SOLICITADO," + " PERSO_TITULAR," + " CARAC_TITULAR,"
				+ " RFC_TITULAR, ");
		query.append("RAZON_TITULAR," + " NOMBRE_TITULAR,"
				+ " PATERNO_TITULAR," + " MATERNO_TITULAR," + " ID_COTITULAR,"
				+ " PERSO_COTI," + " CARAC_COTI, ");
		query.append("RFC_COTITULAR," + " RAZON_COTI," + " NOMBRE_COTI,"
				+ " PATERNO_COTI," + " MATERNO_COTI," + " ID_SUCURSAL,"
				+ " ESTADO, " + "LOCALIDAD, ");
		query.append("CODIGO_POSTAL," + " MODALIDAD," + " TIPO_NIVEL,"
				+ " ID_CUENTA," + " PRODUCTO," + " MONEDA_CUENTA,"
				+ " MONTO_INICIAL," + " OPERACION, ");
		query.append("OFICIO_REQ_OPE," + " F_REQUERIMIENTO,"
				+ " SIARA_INDIRECTO," + " F_APLICACION," + " MONTO_OPERCION,"
				+ " MONEDA_OPERACION, ");
		query.append("SALDO_OPERACION )"); // , F_FECHA ) ");

		query.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
				+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," + "?,?,?,?,?)"); // ,?)");

		LOGGER.info("Valores que se insertaran en la base de datos: ");
		LOGGER.info("Id Empresa: " + capturaManual.getIdEmpresa());
		LOGGER.info("Cve Periodo:" + capturaManual.getCvePeriodo());
		LOGGER.info("Numero de intento: " + capturaManual.getNumIntento());
		LOGGER.info("Situacion: " + capturaManual.getSituacion());
		LOGGER.info("Institucion: " + capturaManual.getInstitucion());
		LOGGER.info("Medio Solicitado: " + capturaManual.getMedioSolicitado());
		LOGGER.info("Autoridad: " + capturaManual.getAutoridad());
		LOGGER.info("Descripcion: " + capturaManual.getDescripcion());
		LOGGER.info("Numero de oficio: " + capturaManual.getNumOficio());
		LOGGER.info("Tipo de Oficio: " + capturaManual.getTipoOficio());
		LOGGER.info("Fecha de Solicitud: " + capturaManual.getFechaSolicitud());
		LOGGER.info("Folio Siara: " + capturaManual.getFolioSiara());
		LOGGER.info("Monto Solicitado: " + capturaManual.getMontoSolicitado());
		LOGGER.info("Persona Tiitular: " + capturaManual.getPersoTitular());
		LOGGER.info("Carac Titular: " + capturaManual.getCaracTitular());
		LOGGER.info("Rfc titular: " + capturaManual.getRfcTitular());
		LOGGER.info("Razon titular: " + capturaManual.getRazonTitular());
		LOGGER.info("Nombre Titular: " + capturaManual.getNombreTitular());
		LOGGER.info("Paterno Titular: " + capturaManual.getPaternoTitular());
		LOGGER.info("Materno Titular: " + capturaManual.getMaternoTitular());
		LOGGER.info("Id Cotitular: " + capturaManual.getIdCotitular());
		LOGGER.info("Perso Cotitular: " + capturaManual.getPersoCoti());
		LOGGER.info("Carac cotitular: " + capturaManual.getCaracCoti());
		LOGGER.info("Rfc Cotitular: " + capturaManual.getRfcCoti());
		LOGGER.info("Razon Cotitular: " + capturaManual.getRazonCoti());
		LOGGER.info("Nombre Cotitular: " + capturaManual.getNombreCoti());
		LOGGER.info("Paterno cotitular: " + capturaManual.getPaternoCoti());
		LOGGER.info("Materno Cotitular: " + capturaManual.getMaternoCoti());
		LOGGER.info("Id Sucursal: " + capturaManual.getIdSucursal());
		LOGGER.info("Estado: " + capturaManual.getEstado());
		LOGGER.info("Localidad: " + capturaManual.getLocalidad());
		LOGGER.info("Codigo Postal: " + capturaManual.getCodigoPostal());
		LOGGER.info("Modalidad: " + capturaManual.getModalidad());
		LOGGER.info("Tipo Nivel: " + capturaManual.getTipoNivel());
		LOGGER.info("Id Cuenta: " + capturaManual.getIdCuenta());
		LOGGER.info("Producto: " + capturaManual.getProducto());
		LOGGER.info("Moneda Cuenta: " + capturaManual.getMonedaCuenta());
		LOGGER.info("Monto Inicial: " + capturaManual.getMontoInicial());
		LOGGER.info("Operacion: " + capturaManual.getOperacion());
		LOGGER.info("Of. Req: " + capturaManual.getOficioReqOpe());
		LOGGER.info("Fecha Req: " + capturaManual.getFecharRequerimiento());
		LOGGER.info("Siara indirecto: " + capturaManual.getSiaraIndirecto());
		LOGGER.info("Fecha Aplicacion: " + capturaManual.getFechaAplicacion());
		LOGGER.info("Monto Operacion : " + capturaManual.getMontoOperacion());
		LOGGER.info("Moneda Operacion: " + capturaManual.getMonedaOperacion());
		LOGGER.info("Saldo Operacion: " + capturaManual.getSaldoOperacion());

		try {
			jdbcTemplateCorp.update(query.toString(),
					capturaManual.getIdEmpresa(),
					capturaManual.getCvePeriodo(),
					capturaManual.getNumIntento(),
					capturaManual.getSituacion(),
					capturaManual.getInstitucion(),
					capturaManual.getMedioSolicitado(),
					capturaManual.getAutoridad(),
					capturaManual.getDescripcion(),
					capturaManual.getNumOficio(),
					capturaManual.getTipoOficio(),
					capturaManual.getFechaSolicitud(),
					capturaManual.getFolioSiara(),
					capturaManual.getMontoSolicitado(),
					capturaManual.getPersoTitular(),
					capturaManual.getCaracTitular(),
					capturaManual.getRfcTitular(),
					capturaManual.getRazonTitular(),
					capturaManual.getNombreTitular(),
					capturaManual.getPaternoTitular(),
					capturaManual.getMaternoTitular(),
					capturaManual.getIdCotitular(),
					capturaManual.getPersoCoti(), capturaManual.getCaracCoti(),
					capturaManual.getRfcCoti(), capturaManual.getRazonCoti(),
					capturaManual.getNombreCoti(),
					capturaManual.getPaternoCoti(),
					capturaManual.getMaternoCoti(),
					capturaManual.getIdSucursal(), capturaManual.getEstado(),
					capturaManual.getLocalidad(),
					capturaManual.getCodigoPostal(),
					capturaManual.getModalidad(), capturaManual.getTipoNivel(),
					capturaManual.getIdCuenta(), capturaManual.getProducto(),
					capturaManual.getMonedaCuenta(),
					capturaManual.getMontoInicial(),
					capturaManual.getOperacion(),
					capturaManual.getOficioReqOpe(),
					capturaManual.getFecharRequerimiento(),
					capturaManual.getSiaraIndirecto(),
					capturaManual.getFechaAplicacion(),
					capturaManual.getMontoOperacion(),
					capturaManual.getMonedaOperacion(),
					capturaManual.getSaldoOperacion());
			// ,capturaManual.getFecha() );
			// return saldoOperacion;
			// }
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gfi.bin.admctasweb.catalogos.dao.CapturaManualDao#modificarCapturaManual
	 * (CapturaManualModel)
	 */
	public boolean modificarCapturaManual(CapturaManualModel capturaManual)
			throws DAOException {
		LOGGER.info("En el método modificarCapturaManual");
		Boolean result = false;

		if (capturaManual.getRfcTitular().length() == 12) {
			capturaManual.setRfcTitular("_" + capturaManual.getRfcTitular());
		}
		// se pone -1 para evitar error de llave en EXTJS JAPL
		if (capturaManual.getIdCotitular() == 0) {
			capturaManual.setIdCotitular(-1);
		}
		StringBuilder query = new StringBuilder();
		query.append("UPDATE CNBV_DIRECTO SET ");
		query.append("ID_EMPRESA = ?, CVE_PERIODO = ?, NUM_INTENTO = ?, SITUACION = ?, INSTITUCION = ?, MEDIO_SOL = ?, AUTORIDAD = ?, DESCRIPCION = ?, NUM_OFICIO = ?, ");
		query.append("TIPO_OFICIO = ?, F_SOLICITUD = ?, FOLIO_SIARA = ?, MONTO_SOLICITADO = ?, PERSO_TITULAR = ?, CARAC_TITULAR = ?, RFC_TITULAR = ?, ");
		query.append("RAZON_TITULAR = ?, NOMBRE_TITULAR = ?, PATERNO_TITULAR = ?, MATERNO_TITULAR = ?, ID_COTITULAR = ?, PERSO_COTI = ?, CARAC_COTI = ?, ");
		query.append("RFC_COTITULAR = ?, RAZON_COTI = ?, NOMBRE_COTI = ?, PATERNO_COTI = ?, MATERNO_COTI = ?, ID_SUCURSAL = ?, ESTADO = ?, LOCALIDAD = ?, ");
		query.append("CODIGO_POSTAL = ?, MODALIDAD = ?, TIPO_NIVEL = ?, ID_CUENTA = ?, PRODUCTO = ?, MONEDA_CUENTA = ?, MONTO_INICIAL = ?, OPERACION = ?, ");
		query.append("OFICIO_REQ_OPE = ?, F_REQUERIMIENTO = ?, SIARA_INDIRECTO = ?, F_APLICACION = ?, MONTO_OPERCION = ?, MONEDA_OPERACION = ?, ");
		query.append("SALDO_OPERACION = ?  "); // , F_FECHA = ? ");
		query.append(" WHERE NUM_OFICIO = '" + capturaManual.getNumOficio()
				+ "' ");
		query.append(" AND TIPO_OFICIO = '" + capturaManual.getTipoOficio()
				+ "' ");
		query.append(" AND ID_EMPRESA = '" + capturaManual.getIdEmpresa()
				+ "' ");
		query.append(" AND CVE_PERIODO = '" + capturaManual.getCvePeriodo()
				+ "' ");
		query.append(" AND NUM_INTENTO = '" + capturaManual.getNumIntento()
				+ "' ");
		query.append(" AND ID_COTITULAR = '" + capturaManual.getIdCotitular()
				+ "' ");
		query.append(" AND ID_CUENTA = '" + capturaManual.getIdCuenta() + "' ");

		LOGGER.info("Consulta a ejecutar: " + query.toString());

		try {
			jdbcTemplateCorp.update(
					query.toString(),
					new Object[] { capturaManual.getIdEmpresa(),
							capturaManual.getCvePeriodo(),
							capturaManual.getNumIntento(),
							capturaManual.getSituacion(),
							capturaManual.getInstitucion(),
							capturaManual.getMedioSolicitado(),
							capturaManual.getAutoridad(),
							capturaManual.getDescripcion(),
							capturaManual.getNumOficio(),
							capturaManual.getTipoOficio(),
							capturaManual.getFechaSolicitud(),
							capturaManual.getFolioSiara(),
							capturaManual.getMontoSolicitado(),
							capturaManual.getPersoTitular(),
							capturaManual.getCaracTitular(),
							capturaManual.getRfcTitular(),
							capturaManual.getRazonTitular(),
							capturaManual.getNombreTitular(),
							capturaManual.getPaternoTitular(),
							capturaManual.getMaternoTitular(),
							capturaManual.getIdCotitular(),
							capturaManual.getPersoCoti(),
							capturaManual.getCaracCoti(),
							capturaManual.getRfcCoti(),
							capturaManual.getRazonCoti(),
							capturaManual.getNombreCoti(),
							capturaManual.getPaternoCoti(),
							capturaManual.getMaternoCoti(),
							capturaManual.getIdSucursal(),
							capturaManual.getEstado(),
							capturaManual.getLocalidad(),
							capturaManual.getCodigoPostal(),
							capturaManual.getModalidad(),
							capturaManual.getTipoNivel(),
							capturaManual.getIdCuenta(),
							capturaManual.getProducto(),
							capturaManual.getMonedaCuenta(),
							capturaManual.getMontoInicial(),
							capturaManual.getOperacion(),
							capturaManual.getOficioReqOpe(),
							capturaManual.getFecharRequerimiento(),
							capturaManual.getSiaraIndirecto(),
							capturaManual.getFechaAplicacion(),
							capturaManual.getMontoOperacion(),
							capturaManual.getMonedaOperacion(),
							capturaManual.getSaldoOperacion() // ,
																// capturaManual.getFecha()
					});
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gfi.bin.admctasweb.catalogos.dao.CapturaManualDao#eliminarCapturaManual
	 * (CapturaManualModel)
	 */
	public boolean eliminarCapturaManual(CapturaManualModel capturaManual)
			throws DAOException {
		LOGGER.info("En el método eliminarCapturaManual");
		Boolean result = false;

		StringBuilder query = new StringBuilder();
		query.append(" UPDATE CNBV_DIRECTO " + "SET SITUACION = 'CA'");
		query.append(" WHERE NUM_OFICIO = '" + capturaManual.getNumOficio()
				+ "'");
		query.append(" AND TIPO_OFICIO  = '" + capturaManual.getTipoOficio()
				+ "'");
		query.append(" AND ID_COTITULAR = " + capturaManual.getIdCotitular());
		query.append(" AND ID_EMPRESA   = '" + capturaManual.getIdEmpresa()
				+ "'");
		query.append(" AND CVE_PERIODO  = " + capturaManual.getCvePeriodo());
		query.append(" AND NUM_INTENTO  = " + capturaManual.getNumIntento());

		try {
			jdbcTemplateCorp.update(query.toString());
			result = true;
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gfi.bin.admctasweb.catalogos.dao.CapturaManualDaoImpl#
	 * buscarCapturaManualPorLlave(String, String, long, String, long, int )
	 */
	public CapturaManualModel buscarCapturaManualPorLlave(String numOficio,
			String tipoOficio, long idCotitular, String idEmpresa,
			long cvePeriodo, int numIntento) throws DAOException {

		LOGGER.info("En el método buscarCapturaManualPorLlave capa dao");
		LOGGER.info(" Parametros con los que se va a ejecutar la consulta :");
		LOGGER.info(" Numero Oficio: " + numOficio);
		LOGGER.info(" Tipo Oficio: " + tipoOficio);
		LOGGER.info(" Id Cotitular: " + idCotitular);
		LOGGER.info(" Id Empresa: " + idEmpresa);
		LOGGER.info(" Cve Periodo: " + cvePeriodo);
		LOGGER.info(" Num Intento: " + numIntento);

		CapturaManualModel capturaManual = new CapturaManualModel();

		StringBuilder query = new StringBuilder();
		query.append("SELECT a.id_empresa, a.cve_periodo, c.desc_periodo, a.num_intento, "
				+ "a.situacion, institucion, medio_sol, autoridad, descripcion, "
				+ "num_oficio, tipo_oficio, f_solicitud, folio_siara, monto_solicitado, "
				+ "perso_titular, carac_titular, rfc_titular, razon_titular, "
				+ "nombre_titular, paterno_titular, materno_titular, id_cotitular, "
				+ "perso_coti, carac_coti, rfc_cotitular, razon_coti, nombre_coti, "
				+ "paterno_coti, materno_coti, id_sucursal, estado, localidad, "
				+ "codigo_postal, modalidad, tipo_nivel, id_cuenta, producto, "
				+ "moneda_cuenta, monto_inicial, operacion, oficio_req_ope, "
				+ "f_requerimiento, siara_indirecto, f_aplicacion, monto_opercion, "
				+ "moneda_operacion, saldo_operacion, f_fecha ");
		query.append("FROM cnbv_directo a,  cnbv_periodo c "
				+ "WHERE a.id_empresa = c.id_empresa "
				+ "AND a.cve_periodo = c.cve_periodo ");
		query.append(" AND NUM_OFICIO = '" + numOficio + "' ");
		query.append(" AND TIPO_OFICIO = '" + tipoOficio + "' ");
		query.append(" AND ID_COTITULAR = " + idCotitular);
		query.append(" AND A.ID_EMPRESA = " + idEmpresa);
		query.append(" AND A.CVE_PERIODO = " + cvePeriodo);
		query.append(" AND A.NUM_INTENTO = " + numIntento);

		LOGGER.info("Consulta a ejecutar: " + query.toString());

		try {
			capturaManual = jdbcTemplateCorp.queryForObject(query.toString(),
					new CapturaManualMapper());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}

		return capturaManual;
	}

	public CapturaManualModel buscarCapturaManualContrato(String idCuenta,
			String idEmpresa) throws DAOException {
		LOGGER.info("En el método buscarContrato capa dao");
		CapturaManualModel capturaManual = new CapturaManualModel();

		StringBuilder query = new StringBuilder();

		query.append("SELECT "
				+ "CTTO.ID_CONTRATO,"
				+ "PKG_REP_R29.FUNOBTMAPEO(6,CTTO.CVE_MONEDA)        AS MONEDA_CUENTA, "
				+ "PKG_REP_R29.FUNOBTMAPEO_DESC(6,CTTO.CVE_MONEDA)   AS ASEGURA, "
				+ "PKG_REP_R29.FUNOBTMAPEO(5,CTTO.CVE_PRODUCTO)      AS PRODUCTO, "
				+ "PKG_REP_R29.FUNOBTMAPEO_DESC(5,CTTO.CVE_PRODUCTO) AS DESCRIPCION_PRODUCTO, "
				+ "PKG_REP_R29.FUNOBTMAPEO(4,CTTO.CVE_PRODUCTO)      AS MODALIDAD, "
				+ "PKG_REP_R29.FUNOBTMAPEO_DESC(4,CTTO.CVE_PRODUCTO) AS DESCRIPCION_MODALIDAD, "
				+ "SUCU.CODIGO_POSTAL                                AS CODIGO_POSTAL, "
				+ "SUCU.LOCALIDAD                                    AS LOCALIDAD, "
				+ "SUCU.ESTADO                                       AS ESTADO, "
				+ "SUCU.NOM_SUCURSAL                                 AS NOMBRE_SUCURSAL, "
				+ "SUCU.ID_SUCURSAL                                  AS ID_SUCURSAL, "
				+ "DECODE(CTTO.CVE_TIP_CONTRATO,'CDVI',404,406)      AS TIPO_NIVEL, "
				+ "PKG_REP_R29.FUNOBTMAPEO_DESCINV(14,DECODE(CTTO.CVE_TIP_CONTRATO,'CDVI',404,406)) AS DESCRIPCION_TIPO_NIVEL, "
				+ "PKG_REP_R29.FUNOBTMAPEO(8,TITU.CVE_PER_JURIDICA)  AS PERSO_TITULAR, "
				+ "PKG_REP_R29.FUNOBTMAPEO_DESC(8,TITU.CVE_PER_JURIDICA) AS DESC_TITULAR, "
				+ "RFCT.SIGLAS_RFC ||RFCT.F_RFC || RFCT.HOMOCLAVE_RFC  AS  RFC_TITULAR, "
				+ "MORA.NOM_RAZON_SOCIAL AS RAZON_TITULAR, "
				+ "FISI.NOMBRE_PERSONA AS NOMBRE_TITULAR, "
				+ "FISI.APELLIDO_PATERNO AS PATERNO_TITULAR, "
				+ "FISI.APELLIDO_MATERNO AS MATERNO_TITULAR, "
				+ "0 AS ID_COTITULAR, " + "0 PERSO_COTI, "
				+ "'' AS DESC_COTI, " + "'' AS RFC_COTITULAR, "
				+ "'' AS RAZON_COTI, " + "'' AS NOMBRE_COTI, "
				+ "'' AS PATERNO_COTI, " + "'' AS MATERNO_COTI "
				+ "FROM CONTRATO       CTTO, " + "CNBV_CAT_MAPEO MAPE, "
				+ "CNBV_SUCURSAL  SUCU, " + "PERSONA        TITU, "
				+ "RFC            RFCT, " + "PERSONA_FISICA FISI, "
				+ "PERSONA_MORAL  MORA " + "WHERE CTTO.ID_CONTRATO     ="
				+ idCuenta + " AND CTTO.ID_EMPRESA	     =" + idEmpresa
				+ " AND TITU.ID_PERSONA      = CTTO.ID_TITULAR "
				+ "AND MAPE.ID_CATALOGO     = 11 "
				+ "AND MAPE.CVE_CORPORATIVA = CTTO.ID_ENTIDAD "
				+ "AND SUCU.ID_EMPRESA      = CTTO.ID_EMPRESA "
				+ "AND SUCU.ID_SUCURSAL     = MAPE.CVE_SITI "
				+ "AND RFCT.ID_PERSONA      = TITU.ID_PERSONA "
				+ "AND FISI.ID_PERSONA   (+)= TITU.ID_PERSONA "
				+ "AND MORA.ID_PERSONA   (+)= TITU.ID_PERSONA ");

		LOGGER.info("Consulta a ejecutar: " + query.toString());

		try {
			// idCuenta= idCuenta.subSequence(0,
			// idCuenta.length()-1).toString();
			capturaManual = jdbcTemplateCorp.queryForObject(query.toString(),
					new CapturaManualContratoMapper());
		} catch (DataAccessException e) {
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}

		return capturaManual;
	}
}