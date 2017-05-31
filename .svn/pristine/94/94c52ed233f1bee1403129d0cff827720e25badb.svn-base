package com.gfi.bin.admctasweb.procesoautomatico.service.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientosDescargadosModel;
import com.gfi.bin.admctasweb.procesoautomatico.service.RequerimientosDescargadosService;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Service
public class RequerimientosDescargadosServiceImpl implements RequerimientosDescargadosService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequerimientosDescargadosServiceImpl.class);
	
	/**
	 * Obtiene la información contenida en un XML con la estructura de arreglo de requerimientos descargados
	 * 
	 * @param archivoXML
	 * @return RequerimientosDescargadosModel
	 * @throws ServiceException
	 */
	@Override
	public RequerimientosDescargadosModel leerArchivoXML(File archivoXML) throws ServiceException {
		RequerimientosDescargadosModel requerimientosDescargadosModel = null;
		
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(RequerimientosDescargadosModel.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			requerimientosDescargadosModel = (RequerimientosDescargadosModel) jaxbUnmarshaller.unmarshal(archivoXML);
			LOGGER.debug("requerimientosDescargadosModel: "+requerimientosDescargadosModel);

		}catch(JAXBException jaxbException){
			LOGGER.error("jaxbException: "+jaxbException.getLocalizedMessage());

			return null;
		}
		return requerimientosDescargadosModel;
	}
}