/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.mail.model.AttachmentMail;
import com.gfi.bin.admctasweb.mail.service.EmailMessageProvider;
import com.gfi.bin.admctasweb.mail.service.MailService;
import com.gfi.bin.admctasweb.operativos.dao.ImpresionDictamenJurDao;
import com.gfi.bin.admctasweb.operativos.model.ImpresionDictamenJurModel;
import com.gfi.bin.admctasweb.operativos.service.ImpresionDictamenJurService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;

/**
 * @author LUGL4884
 *
 */
@Service
public class ImpresionDictamenJurServiceImpl implements ImpresionDictamenJurService {
	
	final Logger log = LoggerFactory.getLogger(ImpresionDictamenJurServiceImpl.class);
	
	@Autowired
	private ImpresionDictamenJurDao impresionDictamenJurDao;
	
	@Autowired
	private EmailMessageProvider velocityEmailMessageProvider;
	
	@Autowired
	private MailService mailService;
	

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.service.ImpresionDictamenJurService#buscarDictamenJur
	 */
	public List<ImpresionDictamenJurModel> buscarDictamenJur(String numOficio,	String tipoOficio) throws ServiceException {
		try{
			return this.impresionDictamenJurDao.buscarDictamenJur(numOficio, tipoOficio);
		}catch(DAOException e){
			log.error(e.getLocalizedMessage());
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.service.ImpresionDictamenJurService#marcarDictamenJur
	 */
	public int marcarDictamenJur(String numOficio, String tipoOficio, Long numConsec, String usuario) throws ServiceException {
		try{
			return this.impresionDictamenJurDao.marcarDictamenJur(numOficio, tipoOficio, numConsec, usuario);
		}catch(DAOException e){
			log.error(e.getLocalizedMessage());
			throw new ServiceException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.service.ImpresionDictamenJurService#buscarDictamenSinImp
	 */
	public int buscarDictamenSinImp(String numOficio, String tipoOficio) throws ServiceException {
		try{
			return this.impresionDictamenJurDao.buscarDictamenSinImp(numOficio, tipoOficio);
		}catch(DAOException e){
			log.error(e.getLocalizedMessage());
			throw new ServiceException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.operativos.service.ImpresionDictamenJurService#enviarEmailSeguimientoImpDic
	 */
	public void enviarEmailSeguimientoImpDic(String numOficio, String tipoOficio, String operacion) throws ServiceException {
		String templateVelocity = "mail/impresionDictamenJuridico.vm";
		AttachmentMail[] fileAttachment = null;
		String txTipoOficio = "";
		
		txTipoOficio = Util.obtenerDescTipoOficio(tipoOficio);
		
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("numOficio", numOficio);
			model.put("tipoOficio", txTipoOficio);
			
			velocityEmailMessageProvider.fillblanks(templateVelocity, null, model);
			velocityEmailMessageProvider.changeSubject(operacion + " " + numOficio);

			mailService.send(velocityEmailMessageProvider, Constantes.IMPRESION_DICTAMEN_COMP, fileAttachment);
			
		} catch (Exception e) {
			final String msg = "Excepción en el envío de mail de Notificación.";
			log.warn(msg, e);
			throw new ServiceException(e);
		}
	}

}
