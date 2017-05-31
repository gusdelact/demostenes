package com.gfi.bin.admctasweb.mail.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.mail.model.AttachmentMail;

public interface MailService {

	/**Envío de email 
	 * @param to correo electrónico del destinatario
	 * @param subject asunto del mensaje
	 * @param text cuerpo del mensaje
	 */
	public void send(String to, String subject, String text);
	
	/** envío de email usando plantilla html
	 * @param to correo electrónico del destinatario
	 * @param subject asunto del mensaje
	 * @param text cuerpo del mensaje
	 * @param attachments ficheros que se anexarón al mensaje 
	 */
	public void send(String to, String subject, String text, File... attachments);
	
	/**Envio de email usando velocity sin datos adjuntos
	 * @param to correo electrónico del destinatario
	 * @param messageProvider messageProvider 
	 */
	
	public void send(String to, EmailMessageProvider messageProvider);
	/**
	 * Se encarga del envio de email usando plantillas velocity
	 * @param to
	 * @param messageProvider
	 * @param attachments
	 * @throws IOException 
	 */
	public void send(String to, EmailMessageProvider messageProvider, AttachmentMail... attachments) throws IOException;
	
	/**
	 * Se encarga del envio de email usando plantillas velocity
	 * @param String []to
	 * @param messageProvider
	 * @param attachments
	 * @throws IOException 
	 */
	public void send(String[] to, EmailMessageProvider messageProvider, AttachmentMail... attachments) throws IOException;
	
	/**
	 * Encargado de enviar un email dependiendo la clave de estatus, busca en la tabla cnbv_destinatarios los destinatarios
	 * los destinatarios deben de tener un estatus 'ac' 
	 * @param messageProvider
	 * @param cveEstatus
	 * @param attachments
	 * @throws IOException
	 * @throws ServiceException 
	 */
	void send(EmailMessageProvider messageProvider, String cveEstatus, AttachmentMail... attachments) throws IOException, ServiceException;

	/**Envio de email enviando una Lista de Archivos como Atachments.
	 * @param messageProvider
	 * @param cveEstatus
	 * @param attachments
	 * @throws IOException, ServiceException
	 */
	void send(EmailMessageProvider messageProvider, String cveEstatus, List<File> attachments) throws IOException, ServiceException;


}