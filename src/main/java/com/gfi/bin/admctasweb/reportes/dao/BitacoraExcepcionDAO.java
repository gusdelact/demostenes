/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionListModel;
import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public interface BitacoraExcepcionDAO {

	/**
	 * 
	 * @param BitacoraExcepcionListModel
	 * @return List<BitacoraExcepcionModel>
	 * @throws DAOException
	 */
	List<BitacoraExcepcionModel> consultarBitacoraExcepcion(BitacoraExcepcionListModel parametros) throws DAOException;
	
}
