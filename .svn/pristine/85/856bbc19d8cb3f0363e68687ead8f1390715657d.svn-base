package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.BitacoraDocumentoDAO;
import com.gfi.bin.admctasweb.catalogos.mapper.DocumentoEliminadoMapper;
import com.gfi.bin.admctasweb.catalogos.model.DocumentoEliminadoModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.reportes.model.BitacoraDoctosEliminadosModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Repository
public class BitacoraDocumentoDAOImpl implements BitacoraDocumentoDAO {
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;

	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraDocumentoDAOImpl.class);
	
	/**
	 * Inserta documento eliminado
	 * 
	 * @param DocumentoEliminadoModel
	 */
	public void guardar(DocumentoEliminadoModel documentoEliminadoModel) throws DAOException {
		StringBuilder sentenciaCnbvDocumento = new StringBuilder();
		sentenciaCnbvDocumento.append("INSERT INTO CNBV_BIT_DOCTO");
		sentenciaCnbvDocumento.append(" (NUM_OFICIO, TIPO_OFICIO, CVE_DOCTO, NOM_DOCTO, FH_ELIMINA, CVE_USU_ELIM)");
		sentenciaCnbvDocumento.append(" values (?, ?, ?, ?, ?, ?)");
		
		try{
			if(documentoEliminadoModel != null){
				
				jdbcTemplateCorp.update(sentenciaCnbvDocumento.toString(), 
					new Object[] {documentoEliminadoModel.getNumOficio(), documentoEliminadoModel.getTipoOficio(), 
								  documentoEliminadoModel.getCveDocto(), documentoEliminadoModel.getNomDocto(), 
								  documentoEliminadoModel.getFhElim(), documentoEliminadoModel.getCveUsuElim()}
				);
			}
			else{
				LOGGER.error("Se recibe objeto documentoEliminadoModel == null (Nada que guardar)");
			}
		}catch(DataAccessException e){
			LOGGER.error(e.getLocalizedMessage());
			throw new DAOException(e);
		}
	}
	
	/**
	 * M&eacute;todo que permite consultar los documentos eliminados
	 * con los filtros de n&uacute;mero de oficio, tipo de oficio, fecha de inicio y fecha de fin
	 * 
	 * @author MUDF4038 - Fernando Munive Dorantes
	 * 
	 * @param BitacoraDoctosEliminadosModel
	 * @return List<DocumentoEliminadoModel>
	 * @throws DAOException
	 */
	public List<DocumentoEliminadoModel> consultarDocsEliminados(BitacoraDoctosEliminadosModel parametros) throws DAOException {
		
		StringBuilder query = new StringBuilder();
		query.append("SELECT NUM_OFICIO, TIPO_OFICIO,"); 
		query.append("DECODE(TIPO_OFICIO,'AS','ASEGURAMIENTO','JU','JUDICIAL','HA','HACENDARIO','PL','PLD','OTRO: '||TIPO_OFICIO) AS DESC_TIPO_OFICIO,"); 
		query.append("CVE_DOCTO,");
		query.append("DECODE(CVE_DOCTO,'OF','OFICIO','DA','DOCTO AUTORIDAD','EN','DOCTO ENVIADO A SITI','OTRO: '||CVE_DOCTO) AS DESC_CVE_DOCTO,"); 
		query.append("NOM_DOCTO, FH_ELIMINA, CVE_USU_ELIM");
		query.append(" FROM CNBV_BIT_DOCTO WHERE 1=1");
		
		if (parametros != null) {
			if (StringUtils.isNotBlank(parametros.getNumOficio())) {
				query.append(" AND NUM_OFICIO = :numOficio ");
			}
//			if (StringUtils.isNotBlank(parametros.getTipoOficio())) {
//				query.append(" AND TIPO_OFICIO = :tipoOficio ");
//			}
			if (parametros.getfInicio() != null && parametros.getfFin() != null) {
				query.append(" AND TRUNC(FH_ELIMINA) BETWEEN :fechInicio AND :fechaFin ");
			}
		}
		query.append(" ORDER BY NUM_OFICIO, TIPO_OFICIO, CVE_DOCTO, NOM_DOCTO");

		Map<String, Object> mapaParametros = new HashMap<String, Object>();
		mapaParametros.put("numOficio", parametros.getNumOficio());
		mapaParametros.put("tipoOficio", parametros.getTipoOficio());
		mapaParametros.put("fechInicio", parametros.getfInicio());
		mapaParametros.put("fechaFin", parametros.getfFin());

		try {		
			return corpNamedTemplate.query(query.toString(), mapaParametros, new DocumentoEliminadoMapper());
		
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}
}