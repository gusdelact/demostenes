package com.gfi.bin.admctasweb.catalogos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoEliminadoModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.reportes.model.BitacoraDoctosEliminadosModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface BitacoraDocumentoDAO {
	
	/**
	 * Inserta documento eliminado
	 * 
	 * @param DocumentoEliminadoModel
	 */
	void guardar(DocumentoEliminadoModel documentoEliminadoModel) throws DAOException;
	
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
	List<DocumentoEliminadoModel> consultarDocsEliminados(BitacoraDoctosEliminadosModel parametros) throws DAOException;
}