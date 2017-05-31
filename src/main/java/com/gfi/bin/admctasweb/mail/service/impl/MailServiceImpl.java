package com.gfi.bin.admctasweb.mail.service.impl;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.gfi.bin.admctasweb.catalogos.model.DestinatarioModel;
import com.gfi.bin.admctasweb.catalogos.service.DestinatarioService;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.mail.model.AttachmentMail;
import com.gfi.bin.admctasweb.mail.service.EmailMessageProvider;
import com.gfi.bin.admctasweb.mail.service.MailService;

@Service(value = "mailService")
public class MailServiceImpl implements MailService {

	private static final Log log = LogFactory.getLog(MailServiceImpl.class);

	private JavaMailSenderImpl mailSender;
	
	/** correo electrónico del remitente */
	private String from;
	
	/** flag para indicar si está activo el servicio */
	public boolean active = true;

	private static final File[] NO_ATTACHMENTS = null;
	
	@Autowired
	private DestinatarioService destinatarioService;
	

	/**Envío de email 
	 * @param to correo electrónico del destinatario
	 * @param subject asunto del mensaje
	 * @param text cuerpo del mensaje
	 */
	public void send(String to, String subject, String text) {
		send(to, subject, text, NO_ATTACHMENTS);
	}

	/**Envío de email usando plantilla html
	 * @param to correo electrónico del destinatario
	 * @param subject asunto del mensaje
	 * @param text cuerpo del mensaje
	 * @param attachments ficheros que se anexarón al mensaje 
	 */
	public void send(String to, String subject, String text, File... attachments) {
		
		Assert.hasLength(to, "email 'to' needed");
		Assert.hasLength(subject, "email 'subject' needed");
		Assert.hasLength(text, "email 'text' needed");

		// asegurando la trazabilidad
		if (log.isDebugEnabled()) {
			final boolean usingPassword = !"".equals(mailSender.getPassword());
			log.debug("Sending email to: '" + to + "' [through host: '" + mailSender.getHost() + ":"
					+ mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"
					+ usingPassword + "].");
			log.debug("isActive: " + active);
		}
		// el servicio esta activo?
		if (!active) return;

		// plantilla para el envío de email
		final MimeMessage message = mailSender.createMimeMessage();

		try {
			// el flag a true indica que va a ser multipart
			final MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setFrom(getFrom());
			helper.setText(text);

			// adjuntando los ficheros
			if (attachments != null) {
				for (int i = 0; i < attachments.length; i++) {
					FileSystemResource file = new FileSystemResource(attachments[i]);
					helper.addAttachment(attachments[i].getName(), file);
					if (log.isDebugEnabled()) {
						log.debug("File '" + file + "' attached.");
					}
				}
			}

		} catch (MessagingException e) {
			new RuntimeException(e);
		}
		
	//ENVío
		this.mailSender.send(message);
	}

	/**Envío de email usando velocity sin datos adjuntos
	 * @param to correo electrónico del destinatario
	 * @param messageProvider messageProvider 
	 */
	public void send(String to, EmailMessageProvider messageProvider) {
		// chequeo de parómetros 
		Assert.hasLength(to, "email 'to' needed");
		Assert.notNull(messageProvider);

		// asegurando la trazabilidad
		if (log.isDebugEnabled()) {
			final boolean usingPassword = !"".equals(mailSender.getPassword());
			log.debug("Sending email to: '" + to + "' [through host: '" + mailSender.getHost() + ":"
					+ mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"
					+ usingPassword + "].");
			log.debug("isActive: " + active);
		}
		// el servicio esta activo?
		if (!active) return;

		// plantilla para el envío de email
		final MimeMessage message = mailSender.createMimeMessage();

		try {
			// el flag a true indica que va a ser multipart
			final MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject(messageProvider.getSubject());
			helper.setFrom(getFrom());
			helper.setText( String.format(messageProvider.getBody().toString()),true);

			// aóadiendo los ficheros "en lónea"
			if (messageProvider.getInlineFiles() != null) {
				for (String key : messageProvider.getInlineFiles().keySet()) {
					Resource value = messageProvider.getInlineFiles().get(key);
					helper.addInline(key, value);
					if (log.isDebugEnabled()) {
						log.debug("File '" + value + "' added.");
					}
				}
			}

		} catch (MessagingException e) {
			new RuntimeException(e);
		}

		// el envío
		this.mailSender.send(message);
	}
	
	

	/**Envío de email usando velocity con datos adjuntos
	 * @param to correo electrónico del destinatario
	 * @param messageProvider messageProvider 
	 * @throws IOException 
	 */
	public void send(String to, EmailMessageProvider messageProvider, AttachmentMail... attachments) throws IOException {
		// chequeo de parámetros 
				Assert.hasLength(to, "email 'to' needed");
				Assert.notNull(messageProvider);

				// asegurando la trazabilidad
				if (log.isDebugEnabled()) {
					final boolean usingPassword = !"".equals(mailSender.getPassword());
					log.debug("Sending email to: '" + to + "' [through host: '" + mailSender.getHost() + ":"
							+ mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"
							+ usingPassword + "].");
					log.debug("isActive: " + active);
				}
				// el servicio esta activo?
				if (!active) return;

				// plantilla para el envío de email
				final MimeMessage message = mailSender.createMimeMessage();

				try {
					// el flag a true indica que va a ser multipart
					final MimeMessageHelper helper = new MimeMessageHelper(message,true);
					helper.setTo(to);
					helper.setSubject(messageProvider.getSubject());
					helper.setFrom(getFrom());
					helper.setText( String.format(messageProvider.getBody().toString()),true);

					//añadiendo los recursos en linea
					if (messageProvider.getInlineFiles() != null) {
						for (String key : messageProvider.getInlineFiles().keySet()) {
							Resource value = messageProvider.getInlineFiles().get(key);
							helper.addInline(key, value);
							if (log.isDebugEnabled()) {
								log.debug("File '" + value + "' added.");
							}
						}
					}
					
					// adjuntando los ficheros
					if (attachments != null) {
						for (int i = 0; i < attachments.length; i++) {
							//below is the different part
					        File someFile = new File(attachments[i].getName().trim()+".pdf");
					        FileOutputStream fos = new FileOutputStream(someFile);
					        fos.write(attachments[i].getAttachmentBytes());
					        fos.flush();
					        fos.close();
							//helper.addAttachment(attachments[i].getName(), new ByteArrayResource( attachments[i].getAttachmentBytes() ));
							helper.addAttachment(attachments[i].getName(), someFile );
							if (log.isDebugEnabled()) {
								log.debug("File '" + someFile + "' attached.");
							}
						}
					}

				} catch (MessagingException e) {
					new RuntimeException(e);
				}

				//Envio de email
				this.mailSender.send(message);
		
	}
	
	/**
	 * Se encarga del envío de email usando plantillas velocity
	 * @param String []to
	 * @param messageProvider
	 * @param attachments
	 * @throws IOException 
	 */
	@Override
	public void send(String[] to, EmailMessageProvider messageProvider,	AttachmentMail... attachments) throws IOException {
		// chequeo de parámetros 
		Assert.noNullElements(to);
		Assert.notNull(messageProvider);

		// asegurando la trazabilidad
		if (log.isDebugEnabled()) {
			final boolean usingPassword = !"".equals(mailSender.getPassword());
			log.debug("Sending email to: '" + to.length + "' [through host: '" + mailSender.getHost() + ":"
					+ mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"
					+ usingPassword + "].");
			log.debug("isActive: " + active);
		}
		// el servicio esta activo?
		if (!active) return;

		// plantilla para el envío de email
		final MimeMessage message = mailSender.createMimeMessage();

		try {
			// el flag a true indica que va a ser multipart
			final MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject(messageProvider.getSubject());
			helper.setFrom(getFrom());
			helper.setText( String.format(messageProvider.getBody().toString()),true);

			//añadiendo los recursos en linea
			if (messageProvider.getInlineFiles() != null) {
				for (String key : messageProvider.getInlineFiles().keySet()) {
					Resource value = messageProvider.getInlineFiles().get(key);
					helper.addInline(key, value);
					if (log.isDebugEnabled()) {
						log.debug("File '" + value + "' added.");
					}
				}
			}
			
			// adjuntando los ficheros
			if (attachments != null) {
				for (int i = 0; i < attachments.length; i++) {
					//below is the different part
			        File someFile = new File(attachments[i].getName().trim()+".pdf");
			        FileOutputStream fos = new FileOutputStream(someFile);
			        fos.write(attachments[i].getAttachmentBytes());
			        fos.flush();
			        fos.close();
					//helper.addAttachment(attachments[i].getName(), new ByteArrayResource( attachments[i].getAttachmentBytes() ));
					helper.addAttachment(attachments[i].getName(), someFile );
					if (log.isDebugEnabled()) {
						log.debug("File '" + someFile + "' attached.");
					}
				}
			}

		} catch (MessagingException e) {
			new RuntimeException(e);
		}

		//Envio de email
		this.mailSender.send(message);
		
	}
	
	/**
	 * Encargado de enviar un email dependiendo la clave de estatus, busca en la tabla cnbv_destinatarios los destinatarios
	 * los destinatarios deben de tener un estatus 'ac' 
	 * @param messageProvider
	 * @param cveEstatus
	 * @param attachments
	 * @throws IOException
	 * @throws ServiceException 
	 */
	@Override
	public void send(EmailMessageProvider messageProvider, String cveEstatus, AttachmentMail... attachments) throws IOException, ServiceException {
		
		String to[] = buscarDestinatarios(cveEstatus);
		Assert.notNull(messageProvider);

		// asegurando la trazabilidad
		if (log.isDebugEnabled()) {
			final boolean usingPassword = !"".equals(mailSender.getPassword());
			log.debug("Sending email to: '" + to.length + "' [through host: '" + mailSender.getHost() + ":"
					+ mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"
					+ usingPassword + "].");
			log.debug("isActive: " + active);
		}
		// el servicio esta activo?
		if (!active) return;

		// plantilla para el envío de email
		final MimeMessage message = mailSender.createMimeMessage();

		try {
			// el flag a true indica que va a ser multipart
			final MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject(messageProvider.getSubject());
			helper.setFrom(getFrom());
			helper.setText( String.format(messageProvider.getBody().toString()),true);

			//añadiendo los recursos en linea
			if (messageProvider.getInlineFiles() != null) {
				for (String key : messageProvider.getInlineFiles().keySet()) {
					Resource value = messageProvider.getInlineFiles().get(key);
					helper.addInline(key, value);
					if (log.isDebugEnabled()) {
						log.debug("File '" + value + "' added.");
					}
				}
			}
			
			// adjuntando los ficheros
			if (attachments != null) {
				for (int i = 0; i < attachments.length; i++) {
					//below is the different part
					String nameFile = attachments[i].getName().trim();
					
			        File someFile = new File(nameFile.replaceAll("/", "-") +".pdf");
			        FileOutputStream fos = new FileOutputStream(someFile);
			        fos.write(attachments[i].getAttachmentBytes());
			        fos.flush();
			        fos.close();
					//helper.addAttachment(attachments[i].getName(), new ByteArrayResource( attachments[i].getAttachmentBytes() ));
					helper.addAttachment(attachments[i].getName(), someFile );
					if (log.isDebugEnabled()) {
						log.debug("File '" + someFile + "' attached.");
					}
				}
			}

		} catch (MessagingException e) {
			new RuntimeException(e);
		}

		//Envio de email
		this.mailSender.send(message);
		
	}
	
	/**Envío de email enviando una Lista de Archivos como Atachments.
	 * @param messageProvider
	 * @param cveEstatus
	 * @param attachments
	 * @throws IOException 
	 */
	@Override
	public void send(EmailMessageProvider messageProvider, String cveEstatus, List<File> attachments) throws IOException, ServiceException {
		
		String to[] = buscarDestinatarios(cveEstatus);
		Assert.notNull(messageProvider);

		// asegurando la trazabilidad
		if (log.isDebugEnabled()) {
			final boolean usingPassword = !"".equals(mailSender.getPassword());
			log.debug("Sending email to: '" + to.length + "' [through host: '" + mailSender.getHost() + ":"
					+ mailSender.getPort() + "', username: '" + mailSender.getUsername() + "' usingPassword:"
					+ usingPassword + "].");
			log.debug("isActive: " + active);
		}
		// el servicio esta activo?
		if (!active) return;

		// plantilla para el envío de email
		final MimeMessage message = mailSender.createMimeMessage();

		try {
			// el flag a true indica que va a ser multipart
			final MimeMessageHelper helper = new MimeMessageHelper(message,true);
			helper.setTo(to);
			helper.setSubject(messageProvider.getSubject());
			helper.setFrom(getFrom());
			helper.setText( String.format(messageProvider.getBody().toString()),true);

			//añadiendo los recursos en linea
			if (messageProvider.getInlineFiles() != null) {
				for (String key : messageProvider.getInlineFiles().keySet()) {
					Resource value = messageProvider.getInlineFiles().get(key);
					helper.addInline(key, value);
					if (log.isDebugEnabled()) {
						log.debug("File '" + value + "' added.");
					}
				}
			}
			
			// adjuntando los ficheros
			if (attachments != null) {
				File archivo = null;
				Iterator<File> it = attachments.iterator();
				while(it.hasNext()) {
					archivo = it.next();
					helper.addAttachment(archivo.getName(), archivo );
				}
			}

		} catch (MessagingException e) {
			new RuntimeException(e);
		}

		//Envio de email
		this.mailSender.send(message);
	}
	
	/**
	 * Metodo que se ocupa para buscar mediante una clave los destinatarios correspondientes para el correo
	 * @param cveEstatus
	 * @return
	 * @throws ServiceException
	 */
	private String[] buscarDestinatarios(String cveEstatus) throws ServiceException{
		String[] destinatarios = null;
		List<DestinatarioModel> listDest = destinatarioService.buscarDestinatariosPorEstatus(cveEstatus);
	
		if(listDest!=null && listDest.size()>0){
			destinatarios = new String[listDest.size()];
			for (int i = 0; i < listDest.size();) {
				String correo = listDest.get(i).getCorreo();
				destinatarios[i++] = correo;			
			}
		}	
		return destinatarios;
		
	}

	public void setFrom(String from) {
		this.from = from;
	}
	
	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public String getFrom() {
		return from;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
