package com.gfi.bin.admctasweb.operativos.service;

import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.ArhivoCnbvModel;
import com.gfi.bin.admctasweb.operativos.model.OficioCnbvModel;
import com.gfi.bin.admctasweb.util.Response;

/**
 * Interface que detalla las operaciones para la generacion del archivo a CNBV
 * "Generacion de archivo CNBV" interfaz ArchivoCnbvService
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public interface ArchivoCnbvService {
	/**
	 * Consulta los oficios que se utilizaran para la generacion del archivo, que se envia a la CNBV
	 * @param vo
	 * @return
	 * @throws ServiceException
	 */
	Response buscarDatosOficioAndRegistro(ArhivoCnbvModel vo) throws ServiceException;

	/**
	 * Se encarga de generar el archivo txt para la CNBV, respetando todas la validaciones pertinentes
	 * @param listaOficiosSelect
	 * @return
	 * @throws ServiceException 
	 */
	Response generarArchivoCnbv(List<OficioCnbvModel> listaOficiosSelect) throws ServiceException;
}
