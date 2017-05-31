/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.DireccionesSolicitantesDao;
import com.gfi.bin.admctasweb.catalogos.mapper.DireccionesSolicitantesCatalogoMapper;
import com.gfi.bin.admctasweb.catalogos.mapper.DireccionesSolicitantesMapper;
import com.gfi.bin.admctasweb.catalogos.model.DireccionesSolicitantesModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Component
@Repository
public class DireccionesSolicitantesDaoImpl implements DireccionesSolicitantesDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DireccionesSolicitantesDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private OracleSequenceMaxValueIncrementer direccionesSolicitantesIncrementer;
	
	/**
	 * Insertar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws DAOException
	 */	
	public DireccionesSolicitantesModel insertarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws DAOException {
		
		if (dsModel != null && dsModel.getTipoOficio() != null) {		
			
			StringBuilder sqlInsert = new StringBuilder();
			sqlInsert.append("INSERT INTO CNBV_DIRECCIONES");
			sqlInsert.append(" (ID_CONFIG_DIR, TIPO_OFICIO,");
			sqlInsert.append("  TX_DIRECCION, TX_GERENCIA, TX_SUBGERENCIA,");
			sqlInsert.append("  TX_ATN_NOMBRE, TX_ATN_PUESTO,");
			sqlInsert.append("  SIT_CONFIG_DIR, NIVEL,");
			sqlInsert.append("  FH_ALTA, CVE_USU_ALTA)");
			sqlInsert.append(" VALUES(");
			sqlInsert.append(" ?,");
			sqlInsert.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");			
			
			LOGGER.info(sqlInsert.toString());
			
			int llave = direccionesSolicitantesIncrementer.nextIntValue();
			dsModel.setIdConfiguracion(llave);
			
			try {
				this.jdbcTemplateCorp.update(sqlInsert.toString(), new Object[] { 
					dsModel.getIdConfiguracion(),
					dsModel.getTipoOficio(), dsModel.getDireccion(), 
					dsModel.getGerencia(), dsModel.getSubgerencia(), 
					dsModel.getNombreAtencion(), dsModel.getPuestoAtencion(),
					dsModel.getSituacion(), dsModel.getNivel(), 
					dsModel.getFhAlta(), dsModel.getCveUsuAlta()
					});
				return dsModel;
				
			} catch (DataAccessException e) {
				String cadenaError = "Error al insertar en la tabla de Direcciones Solicitantes";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError,e);
			}
		} else {
			String cadenaError = "La Situaci\u00f3n de Direcciones Solicitantes no puede ser nula";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		
	}

	/**
	 * Actualizar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws DAOException
	 */	
	public boolean actualizarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws DAOException {
		
		boolean resultado = false;
		
		if (dsModel != null && dsModel.getIdConfiguracion() != null) {
			
			StringBuilder sqlUpdate = new StringBuilder();
			sqlUpdate.append("UPDATE CNBV_DIRECCIONES");
			sqlUpdate.append(" SET TIPO_OFICIO = ?,");
			sqlUpdate.append("     TX_DIRECCION = ?,");
			sqlUpdate.append("     TX_GERENCIA = ?,");
			sqlUpdate.append("     TX_SUBGERENCIA = ?,");
			sqlUpdate.append("     TX_ATN_NOMBRE = ?,");
			sqlUpdate.append("     TX_ATN_PUESTO = ?,");
			sqlUpdate.append("     SIT_CONFIG_DIR = ?,");
			sqlUpdate.append("     NIVEL = ?,");
			sqlUpdate.append("     FH_ULT_MOD = ?,");
			sqlUpdate.append("     CVE_USU_MOD = ?");
			sqlUpdate.append(" WHERE ID_CONFIG_DIR = ?");

			LOGGER.info(sqlUpdate.toString());
			
			try {
				this.jdbcTemplateCorp.update(sqlUpdate.toString(), new Object[] {
					dsModel.getTipoOficio(), dsModel.getDireccion(), 
					dsModel.getGerencia(), dsModel.getSubgerencia(), 
					dsModel.getNombreAtencion(), dsModel.getPuestoAtencion(),
					dsModel.getSituacion(), dsModel.getNivel(), 
					dsModel.getFhUltMod(), dsModel.getCveUsuMod(),
					dsModel.getIdConfiguracion()
					});
				
				resultado = true;
			} catch (DataAccessException e) {
				String cadenaError = "Error al actualiza en la tabla de Direcciones Solicitantes";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError,e);
			}
		} else {
			String cadenaError = "El Identificador de Direcciones Solicitantes no puede ser nulo";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return resultado;
	}

	/**
	 * Eliminar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws DAOException
	 */	
	public boolean eliminarDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws DAOException {
		
		boolean resultado = false;

		if (dsModel != null && dsModel.getIdConfiguracion() != null) {

			StringBuffer sqlDelete = new StringBuffer();
			sqlDelete.append("DELETE FROM CNBV_DIRECCIONES");
			sqlDelete.append(" WHERE ID_CONFIG_DIR = ?");

			LOGGER.info(sqlDelete.toString());
			
			try {
				this.jdbcTemplateCorp.update(sqlDelete.toString(), new Object[] {
					dsModel.getIdConfiguracion()
					});
				
				resultado = true;
			} catch (DataAccessException e) {
				String cadenaError = "Error al eliminar en la tabla de Direcciones Solicitantes";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError,e);
			}
		} else {
			String cadenaError = "El Identificador de Direcciones Solicitantes no puede ser nulo";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}		
		return resultado;
	}

	/**
	 * Consultar Direcciones Solicitantes
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return List<DireccionesSolicitantesModel>
	 * @throws DAOException
	 */	
	public List<DireccionesSolicitantesModel> consultarDireccionesSolicitantes(DireccionesSolicitantesModel dsModel) throws DAOException {
		List<DireccionesSolicitantesModel> dsModelList = null;
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT ID_CONFIG_DIR, TIPO_OFICIO, TX_DIRECCION, TX_GERENCIA, TX_SUBGERENCIA, TX_ATN_NOMBRE, TX_ATN_PUESTO, SIT_CONFIG_DIR, NIVEL, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD");
		query.append(" FROM CNBV_DIRECCIONES WHERE 1=1");
		
		if (dsModel != null) {
			if (dsModel.getIdConfiguracion() != null) {
				query.append(" AND ID_CONFIG_DIR = " + dsModel.getIdConfiguracion());
			} 
			if (dsModel.getTipoOficio() != null && StringUtils.isNotBlank(dsModel.getTipoOficio()) ) {
				query.append(" AND TIPO_OFICIO = '" + dsModel.getTipoOficio() + "'");
			} 
			if (dsModel.getDireccion() != null && StringUtils.isNotBlank(dsModel.getDireccion())) {
				query.append(" AND TX_DIRECCION LIKE '%" + dsModel.getDireccion() + "%'");
			} 
			if (dsModel.getGerencia() != null && StringUtils.isNotBlank(dsModel.getGerencia())) {
				query.append(" AND TX_GERENCIA LIKE '%" + dsModel.getGerencia() + "%'");
			} 
			if (dsModel.getSubgerencia() != null && StringUtils.isNotBlank(dsModel.getGerencia())) {
				query.append(" AND TX_SUBGERENCIA LIKE '%" + dsModel.getGerencia() + "%'");
			} 
			if (dsModel.getNombreAtencion() != null && StringUtils.isNotBlank(dsModel.getNombreAtencion())) {
				query.append(" AND TX_ATN_NOMBRE LIKE '%" + dsModel.getNombreAtencion() + "%'");
			} 
			if (dsModel.getPuestoAtencion() != null && StringUtils.isNotBlank(dsModel.getPuestoAtencion())) {
				query.append(" AND TX_ATN_PUESTO LIKE '%" + dsModel.getPuestoAtencion() + "%'");
			} 
			if (dsModel.getSituacion() != null && StringUtils.isNotBlank(dsModel.getSituacion())) {
				query.append(" AND SIT_CONFIG_DIR = '" + dsModel.getSituacion() + "'");
			}
		}
		query.append(" ORDER BY ID_CONFIG_DIR");

		LOGGER.info(query.toString());

		try {				
			dsModelList = (List<DireccionesSolicitantesModel>)
					this.jdbcTemplateCorp.query(query.toString(), new DireccionesSolicitantesMapper());
			
		} catch (DataAccessException e) {
			String cadenaError = "No existen datos en la tabla de Direcciones Solicitantes";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError, e);
		}	
		return dsModelList;
	}

	/**
	 * Consultar Direcci&oacute;n Solicitante por ID
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return DireccionesSolicitantesModel
	 * @throws DAOException
	 */	
	public DireccionesSolicitantesModel consultarDireccionSolicitantePorID(DireccionesSolicitantesModel dsModel) throws DAOException {		
		
		if (dsModel != null && dsModel.getIdConfiguracion() != null) {
			StringBuilder query = new StringBuilder();
			query.append("SELECT ID_CONFIG_DIR, TIPO_OFICIO, TX_DIRECCION, TX_GERENCIA, TX_SUBGERENCIA, TX_ATN_NOMBRE, TX_ATN_PUESTO, SIT_CONFIG_DIR, NIVEL, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD");
			query.append(" FROM CNBV_DIRECCIONES");
			query.append(" WHERE ID_CONFIG_DIR = " + dsModel.getIdConfiguracion());
			
			LOGGER.info(query.toString());

			try {				
				dsModel = jdbcTemplateCorp.queryForObject(query.toString(), new DireccionesSolicitantesMapper());
				
			} catch (DataAccessException e) {
				String cadenaError = "No existen datos en la tabla de Direcciones Solicitantes";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError, e);
			}
		} else {
			String cadenaError = "El Identificador de Direcciones Solicitantes no puede ser nulo";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return dsModel;
	}

	/**
	 * Existe en el Cat&aacute;logo de Direcciones Solicitantes 
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return boolean
	 * @throws DAOException
	 */
	@SuppressWarnings("deprecation")	
	public boolean existeDireccionSolicitante(DireccionesSolicitantesModel dsModel) throws DAOException {
		boolean existe = false;
		
		if (dsModel != null && dsModel.getIdConfiguracion() != null){
			StringBuilder query = new StringBuilder();
			query.append(" SELECT COUNT(*)");
			query.append(" FROM CNBV_DIRECCIONES");
			query.append(" WHERE ID_CONFIG_DIR = ?");
			
			LOGGER.info(query.toString());
			
			try {
				existe = (jdbcTemplateCorp.queryForInt(query.toString(), new Object[]{dsModel.getIdConfiguracion()})) > 0 ? true: false;   
			} catch (DataAccessException e) {
				String cadenaError = "No existen datos en la tabla de Direcciones Solicitantes";
				LOGGER.error(cadenaError);
				throw new DAOException(cadenaError, e);
			}
		} else{
			String cadenaError = "El Identificador de Direcciones Solicitantes no puede ser nulo";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError);
		}
		return existe;
	}

	/**
	 * Obtiene Direcciones por tipo de Oficio
	 * @param tipoOficio
	 * @return
	 * @throws DAOException
	 */	
	@Override
	public List<ItemModel> obtenerDireccionesPorTipoOficio(String tipoOficio) throws DAOException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT TO_CHAR(Id_Config_Dir) Id_Config_Dir, \n");
		sql.append("  Tx_Gerencia \n");
		sql.append("FROM Cnbv_Direcciones \n");
		sql.append("WHERE Tipo_Oficio  = ? \n");
		sql.append("AND SIT_CONFIG_DIR = 'AC' \n");
		sql.append("ORDER BY 1");
		
		try {
			return jdbcTemplateCorp.query(sql.toString(), new Object[]{tipoOficio}, new DireccionesSolicitantesCatalogoMapper());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}
}
