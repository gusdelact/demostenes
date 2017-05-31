/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoEliminadoModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public class DocumentoEliminadoMapper implements RowMapper<DocumentoEliminadoModel> {

	public DocumentoEliminadoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DocumentoEliminadoModel docEliminado = new DocumentoEliminadoModel();
		try {
			//estas mutaciones podrian lanzar un IllegalArgumentException si los valores son nulos
			docEliminado.setNumOficio(rs.getString("NUM_OFICIO"));
			docEliminado.setTipoOficio(rs.getString("TIPO_OFICIO"));
			if (rs.findColumn("DESC_TIPO_OFICIO") > 0) {
				docEliminado.setDescTipoOficio(rs.getString("DESC_TIPO_OFICIO"));
			}			
			docEliminado.setCveDocto(rs.getString("CVE_DOCTO"));
			if (rs.findColumn("DESC_CVE_DOCTO") > 0) {
				docEliminado.setDescCveDocto(rs.getString("DESC_CVE_DOCTO"));
			}
			docEliminado.setNomDocto(rs.getString("NOM_DOCTO"));
			docEliminado.setFhElim(rs.getDate("FH_ELIMINA"));
			docEliminado.setCveUsuElim(rs.getString("CVE_USU_ELIM"));
			if(rs.getObject("FH_ELIMINA") != null)
				docEliminado.setFhElimTime(rs.getTimestamp("FH_ELIMINA"));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException(e.getCause());
		}
		return docEliminado;		
	}

}
