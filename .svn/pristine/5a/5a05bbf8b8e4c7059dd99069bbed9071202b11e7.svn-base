package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.DestinatarioDAO;
import com.gfi.bin.admctasweb.catalogos.mapper.DestinatarioMapper;
import com.gfi.bin.admctasweb.catalogos.model.DestinatarioModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.util.Constantes;

import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;

/**
 * DAO para destinatarios
 * @author ESS3VAVC
 *
 */
@Repository
public class DestinatarioDAOImpl implements DestinatarioDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	/**
	 * Bean que se utiliza para obtener valor de sequencia para generar ID de destinatario
	 */
	@Autowired
	OracleSequenceMaxValueIncrementer destinatarioIncrementer;
	
	/**
	 * Busca destinatarios en la BD
	 */
	public List<DestinatarioModel> buscarDestinatario(DestinatarioModel destinatarioModel) throws DAOException{
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID_DESTINATARIO, \n");
		sql.append("  CVE_ESTATUS, \n");
		sql.append("  NOMBRE_DESTINAT, \n");
		sql.append("  CORREO, \n");
		sql.append("  SIT_NOTIF, \n");
		sql.append("  FH_ALTA, \n");
		sql.append("  CVE_USU_ALTA, \n");
		sql.append("  FH_ULT_MOD, \n");
		sql.append("  CVE_USU_MOD \n");
		sql.append(" FROM CNBV_DESTINATARIOS \n");
		sql.append(" WHERE ID_DESTINATARIO = ID_DESTINATARIO \n");
		
		if(destinatarioModel != null && StringUtils.isNotBlank(destinatarioModel.getNombre()))
		{
			sql.append(" AND NOMBRE_DESTINAT LIKE '%" + destinatarioModel.getNombre()  + "%' \n");
		}
		
		if(destinatarioModel != null && StringUtils.isNotBlank(destinatarioModel.getCorreo()))
		{
			sql.append(" AND CORREO LIKE '%" + destinatarioModel.getCorreo() + "%'");
		}
		
		try {
			return jdbcTemplateCorp.query(sql.toString(), new DestinatarioMapper());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
		
	}
	
	/**
	 * Obtiene lista de destinatarios por evento de notificación
	 * Sirve para obtener las personas a las que se les debe enviar notificación según un estatus o evento de oficio
	 */
	public List<DestinatarioModel> buscarDestinatariosPorEstatus(String claveEstatus) throws DAOException{
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID_DESTINATARIO, \n");
		sql.append("  CVE_ESTATUS, \n");
		sql.append("  NOMBRE_DESTINAT, \n");
		sql.append("  CORREO, \n");
		sql.append("  SIT_NOTIF, \n");
		sql.append("  FH_ALTA, \n");
		sql.append("  CVE_USU_ALTA, \n");
		sql.append("  FH_ULT_MOD, \n");
		sql.append("  CVE_USU_MOD \n");
		sql.append(" FROM CNBV_DESTINATARIOS \n");
		sql.append(" WHERE SIT_NOTIF = '");
		sql.append(Constantes.SITUACION_NOTIFICACION_AC);
		sql.append("' AND CVE_ESTATUS = ? \n");

		List<DestinatarioModel> destinatarios = null;
		try{
			destinatarios = (List<DestinatarioModel>) 
					jdbcTemplateCorp.query(sql.toString(), 
							new Object[] {claveEstatus}, 
							new DestinatarioMapper());
		}catch(DataAccessException e){

		}
		return destinatarios;
	}
	
	/**
	 * Alta de nuevo de Destinatario en la BD
	 */
	public int guardarDestinatario(DestinatarioModel destinatarioModel) throws DAOException{
	
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT \n");
		sql.append("INTO CNBV_DESTINATARIOS \n");
		sql.append("  ( \n");
		sql.append("    ID_DESTINATARIO, \n");
		sql.append("    CVE_ESTATUS, \n");
		sql.append("    NOMBRE_DESTINAT, \n");
		sql.append("    CORREO, \n");
		sql.append("    SIT_NOTIF, \n");
		sql.append("    FH_ALTA, \n");
		sql.append("    CVE_USU_ALTA, \n");
		sql.append("    FH_ULT_MOD, \n");
		sql.append("    CVE_USU_MOD \n");
		sql.append("  ) \n");
		sql.append("  VALUES \n");
		sql.append("  ( \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ? \n");
		sql.append("  )");

		try {
			int llave = destinatarioIncrementer.nextIntValue();
			jdbcTemplateCorp.update(sql.toString(), new Object[] {llave, 
					destinatarioModel.getCveEstatus(),destinatarioModel.getNombre(), destinatarioModel.getCorreo(),
					destinatarioModel.getSituacion(), new java.sql.Timestamp(destinatarioModel.getFhAlta().getTime()),
					destinatarioModel.getCveUsuAlta(), destinatarioModel.getFhUltMod(),
					destinatarioModel.getCveUsuMod()
				});
			return llave;
		} 
		catch (DataAccessException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new DAOException(e);
		}			
	}
	
	/**
	 * Modificación de destinatario
	 */
	public boolean modificarDestinatario(DestinatarioModel destinatarioModel) throws DAOException{
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE CNBV_DESTINATARIOS \n");
		sql.append("SET CVE_ESTATUS       = ?, \n");
		sql.append("  NOMBRE_DESTINAT     = ?, \n");
		sql.append("  CORREO              = ?, \n");
		sql.append("  SIT_NOTIF           = ?, \n");
		sql.append("  FH_ULT_MOD          = ?, \n");
		sql.append("  CVE_USU_MOD = ? \n");
		sql.append("WHERE ID_DESTINATARIO = ?");
		
		try {
			int resultado = jdbcTemplateCorp.update(sql.toString(),  new Object[] {destinatarioModel.getCveEstatus(), 
																	destinatarioModel.getNombre(),
																	destinatarioModel.getCorreo(),
																	destinatarioModel.getSituacion(),
																	destinatarioModel.getFhUltMod(),
																	destinatarioModel.getCveUsuMod(),
																	destinatarioModel.getIdDestinatario()
																	});
			return resultado > 0;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException (e);
		}
	
	}

	/**
	 * Obtiene destinatario por ID
	 */
	public DestinatarioModel obtenerDestinatario(Integer idDestinatario) throws DAOException {
		// TODO Auto-generated method stub
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ID_DESTINATARIO, \n");
		sql.append("  CVE_ESTATUS, \n");
		sql.append("  NOMBRE_DESTINAT, \n");
		sql.append("  CORREO, \n");
		sql.append("  SIT_NOTIF, \n");
		sql.append("  FH_ALTA, \n");
		sql.append("  CVE_USU_ALTA, \n");
		sql.append("  FH_ULT_MOD, \n");
		sql.append("  CVE_USU_MOD \n");
		sql.append("FROM CNBV_DESTINATARIOS \n");
		sql.append("WHERE ID_DESTINATARIO = ?");

		try {
			return jdbcTemplateCorp.queryForObject(sql.toString(), new Object[]{idDestinatario}, new DestinatarioMapper());
		} 
		catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}		
	}
	
}
