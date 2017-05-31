package com.gfi.bin.admctasweb.reportes.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoListModel;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.model.OficioSeguimientoModel;

/**
 * Servicios para la Bitacora de Seguimiento de Oficios.
 * @author LUGL4884
 */
public interface BitacoraSeguimientoService {
	
	/**
	 * Guarda un registro den la Bitacora de Seguimiento de Oficios.
	 * @param seguimineto
	 * @throws ServiceException
	 */
	void guardar(BitacoraSeguimientoModel seguimiento) throws ServiceException;
	
	/**
	 * Método que realiza la busqueda de Oficios por filtros de Seguimiento.
	 * @param bitacora
	 * @return List<OficioSeguimientoModel>
	 * @throws ServiceException
	 */
	public List<OficioSeguimientoModel> buscarOficiosSeguimiento(BitacoraSeguimientoListModel bitacora) throws ServiceException;
	
	/**
	 * Busca los registros de la Bitácora de Seguimiento por Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @throws ServiceException
	 */
	List<BitacoraSeguimientoModel> buscarSeguimientoPorOficio(String numOficio, String tipoOficio) throws ServiceException;
	
	/**
	 * Método que genera reporte Jasper de la Bitacora de Seguimiento.
	 * @param bitacora
	 * @param logoPath
	 * @return
	 * @throws ServiceException
	 */
	byte[] generarReporteSeguimiento(BitacoraSeguimientoListModel bitacora, String logoPath)throws ServiceException;

	
}