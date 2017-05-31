package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public class DocumentoMapper implements RowMapper<DocumentoModel> {
	
	/**
	 * Realiza la conversión de Resultset a objeto de tipo DocumentoModel
	 * 
	 * @param Restultset
	 * @param int
	 * @return DocumentoModel
	 */
	public DocumentoModel mapRow(ResultSet rs, int rownum) throws SQLException{
		DocumentoModel documento = new DocumentoModel();
		
		documento.setNumOficio(rs.getString("NUM_OFICIO"));
		documento.setTipoOficio(rs.getString("TIPO_OFICIO"));
		documento.setNumDocto(rs.getInt("NUM_DOCTO"));
		documento.setCveDocto(rs.getString("CVE_DOCTO"));
		documento.setNomDocto(rs.getString("NOM_DOCTO"));
		documento.setFhAlta(rs.getDate("FH_ALTA"));
		documento.setCveUsuAlta(rs.getString("CVE_USU_ALTA"));
		documento.setFhUltMod(rs.getDate("FH_ULT_MOD"));
		documento.setCveUsuMod(rs.getString("CVE_USU_MOD"));
		
		return documento;
	}
}