package com.gfi.bin.admctasweb.operativos.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.mapper.SimilaridadMapper;
import com.gfi.bin.admctasweb.catalogos.model.SimilaridadModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.dao.PersonaCorporativaDAO;
import com.gfi.bin.admctasweb.operativos.mapper.PersonaCorporativaMapper;
import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;
import com.gfi.bin.admctasweb.operativos.model.PersonaCorporativaModel;

/**
 * Implementación de métodos de dao para personas corporativas
 * @author ESS3VAVC
 *
 */
@Repository
public class PersonaCorporativaDAOImpl implements PersonaCorporativaDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	private final Logger logger = LoggerFactory.getLogger(PersonaCorporativaDAOImpl.class);
	
	/**
	 * Búsqued de personas corporativas por parámetros
	 * @param rfc
	 * @param nombre
	 * @param tipoPersona
	 * @param listaExclusiones
	 * @return
	 * @throws DAOException
	 */	
	public List<PersonaCorporativaModel> obtenerPersonas(String rfc, String nombre, Integer similaridad, List<ExclusionModel> listaExclusiones) throws DAOException {
		logger.debug("obtenerPersonas(Metodo rfc,nombre,similaridad); rfc: "+rfc+", nombre: "+nombre+", similaridad: "+similaridad+", listaExclusiones: "+listaExclusiones);
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.id_persona, \n");
		sql.append("  p.nombre, \n");
		sql.append("  p.sit_persona, \n");
		sql.append("  r.SIGLAS_RFC, \n");
		sql.append("  r.f_RFC, \n");
		sql.append("  r.HOMOCLAVE_RFC, \n");
		sql.append("  null as ID_CONTRATO \n");
		sql.append("FROM persona p, \n");
		sql.append("  rfc r \n");
		sql.append("WHERE p.id_persona = r.id_persona(+) \n");
		
		
		String parametroBusqueda = "";
		if(StringUtils.isNotBlank(rfc)){//Por nombre y RFC concatenados
			parametroBusqueda = nombre + rfc;
			sql.append("AND utl_match.jaro_winkler_similarity(REPLACE(lower(?), ' ', NULL), REPLACE(lower(p.nombre \n");
			sql.append("  ||r.SIGLAS_RFC \n");
			sql.append("  ||r.f_RFC \n");
			sql.append("  ||r.HOMOCLAVE_RFC), ' ', NULL)) >= ?");
			
			logger.debug("==> Por nombre y rfc concatenados");
		}
		else{//Solo por nombre
			parametroBusqueda = nombre;
			sql.append("AND utl_match.jaro_winkler_similarity(REPLACE(lower(?), ' ', NULL), REPLACE(lower(p.nombre), ' ', NULL)) >= ?");
			
			logger.debug("==> Sólo por nombre");
		}
		
		sql.append("\n UNION ALL "
				+ "\n SELECT cont.id_titular, cont.nomb_contrato, p.sit_persona, r.SIGLAS_RFC, r.f_RFC, r.HOMOCLAVE_RFC, cont.id_contrato as ID_CONTRATO"
				+ "\n FROM persona p, contrato cont, rfc r"
				+ "\n WHERE p.id_persona = cont.id_titular(+)"
				+ "\n AND cont.id_titular = r.id_persona(+)"
				+ "\n AND utl_match.jaro_winkler_similarity(lower(?), ");
		
		/**
		 * Concatenar replace con cada texto que se va a quitar
		 */
		
		StringBuffer excluir = new StringBuffer("lower(cont.nomb_contrato)");
		for(ExclusionModel exclusion : listaExclusiones){	
			StringBuffer palabraExcluir = new StringBuffer();
			logger.debug("palabra a excluir: "+exclusion.getDescripcionExclusion());
			palabraExcluir.append("REPLACE( \n");
			palabraExcluir.append(excluir);
			palabraExcluir.append("\n, lower('"+exclusion.getDescripcionExclusion()+"'), '')");
			
			excluir = palabraExcluir;
		}
		sql.append(excluir);
		sql.append(") >= ?");
		
		try {
			logger.debug("==> Se va a ejecutar consulta(Metodo rfc,nombre,similaridad): "+sql.toString());
			logger.debug("Se ejecuta enviando 4 parametros; parametroBusqueda: "+parametroBusqueda+", similaridad: "+similaridad+", nombre: "+nombre+", similaridad: "+similaridad);
			
			return jdbcTemplateCorp.query(sql.toString(), new Object[] {parametroBusqueda, similaridad, nombre, similaridad}, new PersonaCorporativaMapper());
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Búsqueda de persona por similaridad con RFC
	 */
	public List<PersonaCorporativaModel> obtenerPersonas(String rfc, Integer similaridad)throws DAOException {
		logger.debug("obtenerPersonas (Metodo rfc,similaridad); rfc: "+rfc+", similaridad: "+similaridad);
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT p.id_persona, \n");
		sql.append("  p.nombre, \n");
		sql.append("  p.sit_persona, \n");
		sql.append("  r.SIGLAS_RFC, \n");
		sql.append("  r.f_RFC, \n");
		sql.append("  r.HOMOCLAVE_RFC \n");
		sql.append("FROM persona p, \n");
		sql.append("  rfc r \n");
		sql.append("WHERE p.id_persona = r.id_persona(+) \n");
		sql.append("AND utl_match.jaro_winkler_similarity(REPLACE(lower(?), ' ',NULL) ,REPLACE(lower(r.SIGLAS_RFC \n");
		sql.append("  ||r.f_RFC \n");
		sql.append("  ||r.HOMOCLAVE_RFC), ' ', NULL)) >= ?");

		try {
			logger.debug("==> Se va a ejecutar consulta(Metodo rfc,similaridad): "+sql.toString());
			return jdbcTemplateCorp.query(sql.toString(), new Object[] {rfc, similaridad}, new PersonaCorporativaMapper());
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Obtiene porcentaje de coincidencia para las consultas almacenado en la base
	 * @return
	 * @throws DAOException
	 */
	public SimilaridadModel obtenerSimilaridad() throws DAOException {
		String sql = "select * from CNBV_PORCENT_SIMIL where rownum = ?";
		try {
			return jdbcTemplateCorp.queryForObject(sql, new Integer[]{1}, new SimilaridadMapper());
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}
	
	/**
	 * Obtiene un registro de Persona Corporativa por su Id.
	 * @return PersonaCorporativaModel
	 * @throws DAOException
	 */
	public PersonaCorporativaModel obtenerPersonaPorId(Long idPersona) throws DAOException {
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT P.ID_PERSONA, P.NOMBRE, P.SIT_PERSONA, ");
		sql.append("R.SIGLAS_RFC, R.F_RFC, R.HOMOCLAVE_RFC ");
		sql.append("FROM PERSONA P, RFC R ");
		sql.append("WHERE P.ID_PERSONA = R.ID_PERSONA(+) ");
		sql.append("AND P.ID_PERSONA = ? ");
		
		try {
			return jdbcTemplateCorp.queryForObject(sql.toString(), new Object[]{idPersona}, new PersonaCorporativaMapper());
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}

	/**
	 * Actualiza el % de similitud. Se espera que solo exista un registro, en otro caso actuliza todo que exista.
	 */
	@Override
	public boolean modificarSimilaridad(SimilaridadModel similaridadModel) throws DAOException {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE CNBV_PORCENT_SIMIL \n");
		sql.append("SET PORCENT_SIMIL   = ?, \n");
		sql.append("  FH_ULT_MOD        = ?, \n");
		sql.append("  CVE_USU_MOD       = ? \n");
		sql.append("WHERE PORCENT_SIMIL = PORCENT_SIMIL");

		try {
			int resultado = jdbcTemplateCorp.update(sql.toString(), similaridadModel.getSimilaridad(), 
													similaridadModel.getFhUltMod(), 
													similaridadModel.getCveUsuMod());		
			return resultado > 0;
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}
}