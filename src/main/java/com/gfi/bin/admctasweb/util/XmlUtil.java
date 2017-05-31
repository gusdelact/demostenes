
package com.gfi.bin.admctasweb.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfi.bin.admctasweb.procesoautomatico.model.ExpedienteModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientosDescargadosModel;

/**
 * 
 * @author ESS3ESPP
 *
 */
public class XmlUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(XmlUtil.class);

	/**
	 * Realiza la transformación de archivo XML a ExpedienteModel
	 * 
	 * @param rutaArchivoXML
	 * @return ExpedienteModel
	 */
	public static ExpedienteModel xmlToExpedienteModel(String rutaArchivoXML){
		ExpedienteModel expedienteModel = null;
		File archivoXML = new File(rutaArchivoXML);
		InputStream inputStream = null;

		try{
			inputStream = new FileInputStream(archivoXML);

			JAXBContext jaxbContext = JAXBContext.newInstance(ExpedienteModel.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			expedienteModel = (ExpedienteModel) jaxbUnmarshaller.unmarshal(archivoXML);
			LOGGER.debug("expedienteModel: "+expedienteModel);

		}catch(FileNotFoundException fnfException){
			LOGGER.error("FileNotFoundException: "+fnfException.getLocalizedMessage());

		}catch(JAXBException jaxbException){
			LOGGER.error("Excepcion: "+jaxbException.getLocalizedMessage());

			return null;
		}finally{
			try{
				if(inputStream != null){
					inputStream.close();
				}
			}catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
		return expedienteModel;
	}

	/**
	 * Realiza la transformación de archivo XML a Objeto RequerimientosDescargadosModel
	 * 
	 * @param rutaArchivoXML
	 * @return RequerimientosDescargadosModel
	 */
	public static RequerimientosDescargadosModel xmlToRequerimientosDescargados(String rutaArchivoXML){
		RequerimientosDescargadosModel requerimientosDescargadosModel = null;
		File archivoXML = new File(rutaArchivoXML);
		InputStream inputStream = null;

		try{
			inputStream = new FileInputStream(archivoXML);

			JAXBContext jaxbContext = JAXBContext.newInstance(RequerimientosDescargadosModel.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			requerimientosDescargadosModel = (RequerimientosDescargadosModel) jaxbUnmarshaller.unmarshal(inputStream);
			LOGGER.debug("requerimientosDescargadosModel: "+requerimientosDescargadosModel);

		}catch(FileNotFoundException fnfException){
			LOGGER.error("FileNotFoundException: "+fnfException.getLocalizedMessage());

		}catch(JAXBException jaxbException){
			LOGGER.error("jaxbException: "+jaxbException.getLocalizedMessage());

			return null;
		}finally{
			try{
				if(inputStream != null){
					inputStream.close();
				}
			}catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
		return requerimientosDescargadosModel;
	}
}