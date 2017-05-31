package com.gfi.bin.admctasweb.reportes.dao;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoListModel;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.model.OficioSeguimientoModel;

/**
 * Clase DAO para la Bitacora de Seguimiento.
 * @author LUGL4884
 */
public interface BitacoraSeguimientoDAO {
	
	/**
	 * Insertar registro con nuevo estatus para llevar bitácora de seguimiento de Oficio.
	 * @param seguimiento
	 */
	void guardar(BitacoraSeguimientoModel seguimiento) throws DAOException;
	
	/**
	 * Método que realiza la busqueda de Oficios por filtros de Seguimiento.
	 * @param seguimiento
	 * @return List<OficioSeguimientoModel>
	 * @throws ServiceException
	 */
	public List<OficioSeguimientoModel> buscarOficiosSeguimiento(BitacoraSeguimientoListModel bitacora) throws DAOException;
	
	/**
	 * Busca los registros de la Bitácora de Seguimiento por Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @throws ServiceException
	 */
	List<BitacoraSeguimientoModel> buscarSeguimientoPorOficio(String numOficio, String tipoOficio) throws DAOException;
}
