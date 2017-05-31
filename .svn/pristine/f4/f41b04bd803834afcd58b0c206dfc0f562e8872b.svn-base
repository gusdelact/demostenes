package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.PersonaDao;
import com.gfi.bin.admctasweb.catalogos.mapper.PersonaMapper;
import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;

/**
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Repository
public class PersonaDaoImpl implements PersonaDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonaDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	

	public List<PersonaModel> getAllPersonas() {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * ");
		query.append(" FROM CNBV_PERSONA  WHERE  rownum <= 3");
		return (List<PersonaModel>) this.jdbcTemplateCorp.query(query.toString(), new PersonaMapper());

	}

	public void guardarPersona(PersonaModel persona) throws DAOException{
		try{
			StringBuilder strB = new StringBuilder();
			strB.append("INSERT INTO CNBV_PERSONA ( ");
			strB.append("NUM_OFICIO, TIPO_OFICIO, NUM_CONSEC, ");
			strB.append("ID_PERSONA, TIPO_PERSONA, NOMBRE, ");
			strB.append("AP_PATERNO, AP_MATERNO, RFC, ");
			strB.append("NUM_DICTAMEN, SIT_RESPUESTA, FH_ALTA, ");
			strB.append("CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD, CUENTA) ");
			strB.append("VALUES ( ?, ?, ?,");
			strB.append("  ?, ?, ?,");
			strB.append(" ?, ?, ?,");
			strB.append(" ?, ?, ?,");
			strB.append("?, ?, ?, ?) ");

			jdbcTemplateCorp.update(strB.toString(), new Object[] { 
				persona.getNumOficio(),
				persona.getTipoOficio(),
				persona.getNumConsec(),
				persona.getIdPersona(),
				persona.getTipoPersona(),
				persona.getNombre(),
				persona.getApPaterno(),
				persona.getApMaterno(),
				persona.getRfc(),
				persona.getNumDictamen(),
				persona.getSitRespuesta(),
				persona.getFhAlta(),
				persona.getCveUsuAlta(),
				persona.getFhUltMod(),
				persona.getCveUsuMod(),
				persona.getCuenta()
			});
		}catch(DataAccessException dataAccessException){
			LOGGER.error(dataAccessException.getLocalizedMessage());
			throw new DAOException(dataAccessException);
		}
	}

	public void actualizarPersona(PersonaModel persona) throws DAOException{
		try{

			StringBuilder strB = new StringBuilder();  
			strB.append("UPDATE CNBV_PERSONA ");

			strB.append(" SET ID_PERSONA    = ?, ");
			strB.append(" TIPO_PERSONA  = ?, ");
			strB.append(" NOMBRE        = ?, ");
			strB.append(" AP_PATERNO    = ?, ");
			strB.append(" AP_MATERNO    = ?, ");
			strB.append(" RFC           = ?, ");
			//		 strB.append(" NUM_DICTAMEN  = ?, ");
			strB.append(" SIT_RESPUESTA = ?, ");
			//		 strB.append(" FH_ALTA       = ?, ");
			//		 strB.append(" CVE_USU_ALTA  = ?, ");
			strB.append(" FH_ULT_MOD    = ?, ");
			strB.append(" CVE_USU_MOD   = ?, ");
			strB.append(" CUENTA		 = ? ");
			strB.append("WHERE  NUM_OFICIO    = ? ");
			strB.append("AND    TIPO_OFICIO   = ? ");
			strB.append("AND    NUM_CONSEC    = ? ");


			jdbcTemplateCorp.update(strB.toString(), new Object[] { 

				persona.getIdPersona(),
				persona.getTipoPersona(),
				persona.getNombre(),
				persona.getApPaterno(),
				persona.getApMaterno(),
				persona.getRfc(),
				//			persona.getNumDictamen(),
				persona.getSitRespuesta(),
				//			persona.getFhAlta(),
				//			persona.getCveUsuAlta(),
				persona.getFhUltMod(),
				persona.getCveUsuMod(),
				persona.getCuenta(),
				persona.getNumOficio(),
				persona.getTipoOficio(),
				persona.getNumConsec()
			});
		}catch(DataAccessException dataAccessException){
			LOGGER.error(dataAccessException.getLocalizedMessage());
			throw new DAOException(dataAccessException);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.impl.PersonaDaoImpl#eliminarPersona
	 */
	public boolean eliminarPersona(PersonaModel persona) throws DAOException {
		
		LOGGER.info("En el método eliminarPersona");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM CNBV_PERSONA ");
		query.append("WHERE NUM_OFICIO = ? and TIPO_OFICIO = ? and NUM_CONSEC = ?");
		
		try{
			if(persona != null){
				jdbcTemplateCorp.update(query.toString(), 
						   new Object[] {persona.getNumOficio(), persona.getTipoOficio(), persona.getNumConsec()}
				);
				
				result = true;
			}else{
				LOGGER.error("Documento Nulo (Nada que eliminar)");
			}
		}catch(DataAccessException e){
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}

	public PersonaModel buscarPersonaId(PersonaModel persona) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * ");
		query.append(" FROM CNBV_PERSONA ");
		query.append(" WHERE 1 = 1");
		
		if(persona != null && StringUtils.isNotBlank(persona.getTipoOficio())){
			query.append(" AND    TIPO_OFICIO   = ? \n");
		}
		
		if(persona != null && StringUtils.isNotBlank(persona.getNumOficio())){
			query.append(" AND    NUM_OFICIO   = ? \n");
		}
		
		if(persona != null && persona.getNumConsec() != null ){
			query.append(" AND    NUM_CONSEC   = ? \n");
		}
	
			return jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{persona.getTipoOficio(),
				persona.getNumOficio(),
				persona.getNumConsec()
				}, new PersonaMapper());
			
		
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.impl.PersonaDaoImpl#existePersona
	 */
	public int existePersona(String numOficio, String tipoOficio,
			Long numConsec) {
		LOGGER.info("En el método existePersona");
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(*)");
		query.append(" FROM CNBV_PERSONA "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		query.append(" AND NUM_CONSEC = ? "); 
		
		return (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio,numConsec}, Integer.class));
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.PersonaDao#buscarPersonasPorOficio
	 */
	public List<PersonaModel> buscarPersonasPorOficio(String numOficio,
			String tipoOficio) {
		LOGGER.info("En el método buscarPersonasPorOficio");
		List<PersonaModel> listPer =  new ArrayList<PersonaModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT NUM_OFICIO, TIPO_OFICIO, NUM_CONSEC, ID_PERSONA, ");
		query.append(" TIPO_PERSONA, NOMBRE, AP_PATERNO, AP_MATERNO, RFC, NUM_DICTAMEN,");
		query.append(" SIT_RESPUESTA, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD, CUENTA "); 
		query.append(" FROM CNBV_PERSONA "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		
		listPer = jdbcTemplateCorp.query(query.toString(), new Object[]{numOficio,tipoOficio}, new PersonaMapper());
		
		return listPer;
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.catalogos.dao.PersonaDao#obtenerConsecutivo
	 */
	public int obtenerConsecutivo(String numOficio, String tipoOficio) throws DAOException {
		LOGGER.info("En el método obtenerConsecutivo");
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT NVL(MAX(NUM_CONSEC),0) + 1 ");
		query.append(" FROM CNBV_PERSONA "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		
		return (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio}, Integer.class));
	}
	
	public int obtenerIDPersona(String numOficio, String tipoOficio,Long numConsec) {
		LOGGER.info("En el método obtenerIDPersona");
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT ID_PERSONA");
		query.append(" FROM CNBV_PERSONA "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		query.append(" AND NUM_CONSEC = ? "); 
		
		return (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio,numConsec}, Integer.class));
	}

}
