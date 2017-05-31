package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.DocumentoDAO;
import com.gfi.bin.admctasweb.catalogos.mapper.DocumentoMapper;
import com.gfi.bin.admctasweb.catalogos.model.DocumentoModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * @author LUGL4884
 *
 */
@Repository
public class DocumentoDAOImpl implements DocumentoDAO {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentoDAOImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	
	/**
	 * Guardar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	public boolean guardarDocumento(DocumentoModel documento) throws DAOException{
		LOGGER.info("En el método guardarDocumento");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append("Insert into CNBV_DOCUMENTO ( ");
		query.append("NUM_OFICIO, TIPO_OFICIO, NUM_DOCTO, ");
		query.append("CVE_DOCTO, NOM_DOCTO, FH_ALTA, ");
		query.append("CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD) ");
		query.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		try{
			if(documento != null){
				
				jdbcTemplateCorp.update(query.toString(), new Object[] {documento.getNumOficio(), 
										documento.getTipoOficio(), documento.getNumDocto(), 
										documento.getCveDocto(), documento.getNomDocto(), documento.getFhAlta(),
										documento.getCveUsuAlta(), documento.getFhUltMod(), documento.getCveUsuMod()});
				
				result = true;
			}
			else{
				LOGGER.error("Documento Nulo (Nada que guardar)");
			}
		}catch(DataAccessException e){
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}

	/**
	 * Modificar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	public boolean modificarDocumento(DocumentoModel documento) throws DAOException{
		
		LOGGER.info("En el método modificarDocumento");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append(" UPDATE CNBV_DOCUMENTO  ");
		query.append(" SET CVE_DOCTO = ?, NOM_DOCTO = ?, ");
		query.append(" FH_ULT_MOD = ?, CVE_USU_MOD = ? ");
		query.append(" WHERE NUM_OFICIO = ? AND TIPO_OFICIO = ? AND NUM_DOCTO = ?");
		
		try{
			if(documento != null){
				
				jdbcTemplateCorp.update(query.toString(), new Object[] {documento.getCveDocto(), 
					documento.getNomDocto(), documento.getFhUltMod(), documento.getCveUsuMod(), 
					documento.getNumOficio(), documento.getTipoOficio(), documento.getNumDocto()}	);
				
				result = true;
				
			}else{
				LOGGER.error("Documento Nulo");
			}
		}catch(DataAccessException e){
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
		
		return result;
	}

	/**
	 * Eliminar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	public boolean eliminarDocumento(DocumentoModel documento) throws DAOException {
		
		LOGGER.info("En el método eliminarDocumento");
		Boolean result =  false;
		
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM CNBV_DOCUMENTO");
		query.append(" WHERE NUM_OFICIO=? and TIPO_OFICIO=? and NUM_DOCTO=?");
		
		try{
			if(documento != null){
				jdbcTemplateCorp.update(query.toString(), 
						   new Object[] {documento.getNumOficio(), documento.getTipoOficio(), documento.getNumDocto()}
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
	
	/**
	 * Ejecuta la busqueda de un Documento por su PK
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param numDocto
	 * @return DocumentoModel
	 * @throws DAOException 
	 */
	public DocumentoModel buscarDocumentoPorId(String numOficio,
			String tipoOficio, Integer numDocto) throws DAOException {
		LOGGER.info("En el método buscarDocumentoPorId");
		DocumentoModel documento =  new DocumentoModel();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT NUM_OFICIO, TIPO_OFICIO, NUM_DOCTO, CVE_DOCTO, ");
		query.append(" NOM_DOCTO, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD "); 
		query.append(" FROM CNBV_DOCUMENTO "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		query.append(" AND NUM_DOCTO = ? "); 
		
		documento = jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio,numDocto}, new DocumentoMapper());
		
		return documento;
	}
	
	/**
	 * Ejecuta una busqueda de Documentos relacionadas a un Oficio.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<DocumentoModel>
	 * @throws ServiceException 
	 */
	public List<DocumentoModel> buscarDocumentosPorOficio(String numOficio,
			String tipoOficio) throws DAOException {
		LOGGER.info("En el método buscarDocumentosPorOficio");
		List<DocumentoModel> listDoctos =  new ArrayList<DocumentoModel>();
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT NUM_OFICIO, TIPO_OFICIO, NUM_DOCTO, CVE_DOCTO, ");
		query.append(" NOM_DOCTO, FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD "); 
		query.append(" FROM CNBV_DOCUMENTO "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		
		listDoctos = jdbcTemplateCorp.query(query.toString(), new Object[]{numOficio,tipoOficio}, new DocumentoMapper());
		
		return listDoctos;
	}
	
	/**
	 * Valida que exista un Documento en base a su PK.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param numDocto
	 * @return int
	 * @throws ServiceException 
	 */
	public int existeDocumento(String numOficio, String tipoOficio,
			Integer numDocto) throws DAOException {
		LOGGER.info("En el método existeDocumento");
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT COUNT(*)");
		query.append(" FROM CNBV_DOCUMENTO "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		query.append(" AND NUM_DOCTO = ? "); 
		
		return (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio,numDocto}, Integer.class));
	}
	
	/**
	 * Obtiene el siguiente consecutivo del Documento.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	public int obtenerConsecutivo(String numOficio, String tipoOficio) throws DAOException {
		LOGGER.info("En el método obtenerConsecutivo");
		
		StringBuilder query = new StringBuilder();
		query.append(" SELECT NVL(MAX(NUM_DOCTO),0) + 1 ");
		query.append(" FROM CNBV_DOCUMENTO "); 
		query.append(" WHERE NUM_OFICIO = ? "); 
		query.append(" AND TIPO_OFICIO = ? "); 
		
		return (jdbcTemplateCorp.queryForObject(query.toString(), new Object[]{numOficio,tipoOficio}, Integer.class));
	}
}