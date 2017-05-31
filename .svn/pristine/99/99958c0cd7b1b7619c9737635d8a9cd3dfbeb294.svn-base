package com.gfi.bin.admctasweb.comun.dao;

import java.util.Date;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;
/**
 * Dao que contiene las operaciones a las tablas comunes de la base de datos
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public interface ComunDao {
	/**
	 * 
	 * @param idEmpresa
	 * @param cveDiaLiquida
	 * @return
	 */
	Date getDateFechaEmpresa(Integer idEmpresa, String cveDiaLiquida);

	/**
	 * Obtiene la empresa asociada a un oficio en el objeto ItemModel que contiene la clave y descripción
	 * @param numOficio
	 * @param tipoOficio
	 * @return
	 * @throws DAOException
	 */
	ItemModel obtenerEmpresaOficio(String numOficio, String tipoOficio)throws DAOException;
	
}
