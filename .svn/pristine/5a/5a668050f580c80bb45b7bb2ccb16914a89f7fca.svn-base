package com.gfi.bin.admctasweb.operativos.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gfi.bin.admctasweb.catalogos.dao.OficioDao;
import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.service.DocumentoService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.service.OficioRespuestaNegativaService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Service
public class OficioRespuestaNegativaServiceImpl implements
		OficioRespuestaNegativaService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OficioRespuestaNegativaServiceImpl.class);
	
	@Autowired
	private OficioDao oficioDAO;
	
	@Autowired
	private DocumentoService documentoService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimientoService;
	
	/**
	 * Realiza la actualización de todos los oficios que contengan el mismo archivo TXT, se les actualiza 
	 * el numero de folio, se registra un nuevo documento para cada oficio y se agrega el archivo seleccionado
	 * al repositorio de acuerdo a la fecha de cada oficio
	 * 
	 * @param nombreArchivo
	 * @param nombreAcuse
	 * @param multipartFile
	 * @return Boolean
	 * @throws ServiceException
	 */
	@Override
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public Boolean modificarOficiosRespuestasNegativas(String nombreArchivo, String nombreAcuse, MultipartFile multipartFile) throws ServiceException{
		Boolean resultado = true;
		
		List<OficioModel> listaOficios = buscarOficiosPorNombreArchivo(nombreArchivo);
		resultado = modificarAcusesOficios(listaOficios, nombreAcuse);
		
		if(resultado == false){
			LOGGER.debug("Resultado false, despues de modificarAcusesOficios");
			return resultado;
		}
		
		resultado = documentoService.guardarDocumentosArchivo(listaOficios, multipartFile);
		if(resultado == false){
			LOGGER.debug("Resultado false, despues de guardarDocumentos y archivo");
			return resultado;
		}
		
		return resultado;
	}
	
	//Mover try catch de acuerdo a lo que se necesita, si quieren que
	//cuando todos ya tienen CVE_ESTATUS = 'REGACUC', entonces se marca error si el try está dentro del for
	//Si el try catch está fuera del for, con el primero que falle, se mostrará el error en pantalla
	/**
	 * Se actualiza cada oficio con el nuevo valor de acuse y actualizando el estatus a TIENE ACUSE,
	 * también se guarda en la bitácora de seguimiento con estatus REGISTRO ACUSE COMPLETO
	 * 
	 * @param listaOficios
	 * @param nuevoAcuse
	 * @return boolean
	 * @throws ServiceException
	 */
	private boolean modificarAcusesOficios(List<OficioModel> listaOficios, String nuevoAcuse) throws ServiceException {
		boolean resultado = true;
		
		for(OficioModel oficio : listaOficios){
			try {
				LOGGER.debug("Se va a actualizar oficio; numOficio="+oficio.getNumOficio()+
						", tipoOficio="+oficio.getTipoOficio()+", nuevoAcuse: "+nuevoAcuse);
				resultado = oficioDAO.modificarAcuseOficio(oficio.getNumOficio(), oficio.getTipoOficio(), nuevoAcuse, Constantes.TIENE_ACUSE);
				
				/**
				 * Se persiste seguimiento de oficio
				 */
				BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
				seguimiento.setNumOficio(oficio.getNumOficio());
				seguimiento.setTipoOficio(oficio.getTipoOficio());
				seguimiento.setCveEstatus(Constantes.REGISTRO_ACUSE_COMP);
				LOGGER.debug("Se va a guardar Seguimiento; numOficio: "+seguimiento.getNumOficio()+", tipoOficio: "+seguimiento.getTipoOficio()+", cveEstatus: "+seguimiento.getCveEstatus());
				bitacoraSeguimientoService.guardar(seguimiento);
				
				if(resultado == false){
					LOGGER.error("Error, falló actualización de oficio actual...");
					return resultado;
				}
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return resultado;
	}
	
	/**
	 * Busca oficios por nombre de archivo
	 * 
	 * @param nombreArchivo
	 * @return List<OficioModel>
	 * @throws ServiceException
	 */
	private List<OficioModel> buscarOficiosPorNombreArchivo(String nombreArchivo) throws ServiceException{
		List<OficioModel> listaOficios = null;
		try {
			LOGGER.debug("Se van a consultar oficios con nombreArchivo="+nombreArchivo);
			
			listaOficios = oficioDAO.buscarOficiosPorNombreArchivo(nombreArchivo);
			LOGGER.debug("Cantidad de oficios = "+listaOficios.size());
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return listaOficios;
	}
}