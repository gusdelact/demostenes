package com.gfi.bin.admctasweb.operativos.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.dao.ContratoDAO;
import com.gfi.bin.admctasweb.operativos.mapper.ContratoCambiosMapper;
import com.gfi.bin.admctasweb.operativos.mapper.ContratoMapper;
import com.gfi.bin.admctasweb.operativos.model.ContratoCambiosModel;
import com.gfi.bin.admctasweb.operativos.model.ContratoModel;

/**
 * Implementación de métodos para acceder a la BD para obtener contratos de una persona
 * @author ESS3VAVC
 *
 */
@Repository
public class ContratoDAOImpl implements ContratoDAO {

	@Autowired
	private JdbcTemplate jdbcTemplateCorp;

	private final Logger logger = LoggerFactory.getLogger(ContratoDAOImpl.class);
	
	/**
	 * Obtiene los contratos DPVISTA, BURSATIL, FIDEICOMISOS y CREDITOS
	 * @return
	 * @throws DAOException
	 */	
	public List<ContratoModel> obtenerContratos(Long idPersona, Long idContrato) throws DAOException {
		logger.debug("obtenerContratos; idPersona: "+idPersona+", idContrato: "+idContrato);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT c.id_contrato, \n");
		sql.append("  TC.DESC_TIPO_CONTRA, \n");
		sql.append("  PR.DESC_PRODUCTO, \n");
		sql.append("  co.f_alta, \n");
		sql.append("  co.f_baja, \n");
		sql.append("  p.nombre, \n");
		sql.append("  co.sit_contrato, \n");
		sql.append("  t.desc_contratante tipo_contratante, \n");
		sql.append("  t.cve_contratante, \n");
		sql.append("  co.nomb_contrato \n");
		sql.append("FROM contratante c, \n");
		sql.append("  contrato co, \n");
		sql.append("  tipo_contratante t, \n");
		sql.append("  persona p, \n");
		sql.append("  TIPO_CONTRATO tc, \n");
		sql.append("  producto PR \n");
		sql.append("WHERE c.id_persona      = p.id_persona \n");
		sql.append("AND c.id_contrato       = co.id_contrato \n");
		sql.append("AND c.cve_contratante   = t.cve_contratante \n");
		sql.append("AND CO.CVE_TIP_CONTRATO = TC.CVE_TIP_CONTRATO \n");
		sql.append("AND CO.CVE_PRODUCTO     = PR.CVE_PRODUCTO \n");
		sql.append("AND p.ID_PERSONA        = ? \n");
		
		if(idContrato != 0){
			sql.append("AND co.id_contrato	= ?");
		}
		
		try {
			logger.debug("sql: "+sql.toString());
			if(idContrato != 0){
				logger.debug("Se ejecuta consulta enviando idPersona e idContrato");
				return jdbcTemplateCorp.query(sql.toString(), new Object[] {idPersona, idContrato}, new ContratoMapper());
			}
			else{
				logger.debug("Se ejecuta consulta enviando sólo idPersona");
				return jdbcTemplateCorp.query(sql.toString(), new Object[] {idPersona}, new ContratoMapper());
			}
		} 
		catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Obtiene contratos de Central de Cambios de una persona
	 * @return
	 * @throws DAOException
	 */	
	public List<ContratoCambiosModel> obtenerContratosCambios(Long idPersona) throws DAOException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT C.ID_CONTRATO, \n");
		sql.append("  C.F_OPERACION, \n");
		sql.append("  M.DESC_C_MONEDA, \n");
		sql.append("  c.SIT_CONTRATO \n");
		sql.append("FROM DI_CONTRATO C, \n");
		sql.append("  MONEDA M \n");
		sql.append("WHERE C.CVE_MONEDA_BASE = M.CVE_MONEDA \n");
		sql.append("AND C.ID_CLIENTE        = ? \n");
		sql.append("ORDER BY C.F_OPERACION DESC");
		
		try {
			return jdbcTemplateCorp.query(sql.toString(), new Object[] {idPersona}, new ContratoCambiosMapper());
		} 
		catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Obtiene un registro de Contrato DPVISTA, BURSATIL, FIDEICOMISOS y CREDITOS por su Id.
	 * @return ContratoModel
	 * @throws DAOException
	 */
	public ContratoModel obtenerContratoPorId(Long idContrato, Long idPersona)
			throws DAOException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT c.id_contrato, \n");
		sql.append("  TC.DESC_TIPO_CONTRA, \n");
		sql.append("  PR.DESC_PRODUCTO, \n");
		sql.append("  co.f_alta, \n");
		sql.append("  co.f_baja, \n");
		sql.append("  p.nombre, \n");
		sql.append("  co.sit_contrato, \n");
		sql.append("  t.desc_contratante tipo_contratante, \n");
		sql.append("  t.cve_contratante, \n");
		sql.append("  co.nomb_contrato \n");
		sql.append("FROM contratante c, \n");
		sql.append("  contrato co, \n");
		sql.append("  tipo_contratante t, \n");
		sql.append("  persona p, \n");
		sql.append("  TIPO_CONTRATO tc, \n");
		sql.append("  producto PR \n");
		sql.append("WHERE c.id_persona      = p.id_persona \n");
		sql.append("AND c.id_contrato       = co.id_contrato \n");
		sql.append("AND c.cve_contratante   = t.cve_contratante \n");
		sql.append("AND CO.CVE_TIP_CONTRATO = TC.CVE_TIP_CONTRATO \n");
		sql.append("AND CO.CVE_PRODUCTO     = PR.CVE_PRODUCTO \n");
		sql.append("AND C.ID_CONTRATO 		= ? ");
		sql.append("AND p.ID_PERSONA        = ?");
		
		
		try {
			return jdbcTemplateCorp.queryForObject(sql.toString(), new Object[]{idContrato, idPersona}, new ContratoMapper());
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Obtiene un registro de Contrato de Central de Cambios por su Id.
	 * @return ContratoCambiosModel
	 * @throws DAOException
	 */
	public ContratoCambiosModel obtenerContratoCambiosPorId(Long idContrato)
			throws DAOException {
		logger.debug("obtenerContratoCambiosPorId; idContrato: "+idContrato);
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT C.ID_CONTRATO, ");
		sql.append("C.F_OPERACION, ");
		sql.append("M.DESC_C_MONEDA, ");
		sql.append("C.SIT_CONTRATO ");
		sql.append("FROM DI_CONTRATO C, MONEDA M ");
		sql.append("WHERE C.CVE_MONEDA_BASE = M.CVE_MONEDA ");
		sql.append("AND C.ID_CONTRATO = ? ");
		
		try {
			return jdbcTemplateCorp.queryForObject(sql.toString(), new Object[]{idContrato}, new ContratoCambiosMapper());
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}
}