package com.gfi.bin.admctasweb.mail.service.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.tools.generic.DateTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.gfi.bin.admctasweb.mail.model.DatosEmail;
import com.gfi.bin.admctasweb.mail.service.EmailMessageProvider;
import com.gfi.bin.admctasweb.util.Constantes;

public class VelocityEmailMessageProviderImpl implements EmailMessageProvider {

	private Map<String,Resource> inlineFiles;
	
	private String subject;
	private String body;
	private String template;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	
	/**
	 * Encargado de llenar los datos del email
	 */
	public void fillblanks(String templateVelocity, DatosEmail datosEmail,Map<String,Object> model) throws IOException {	
		setTemplate(templateVelocity);
		model.put("sysDate", new DateTool());
		model.put("datosEmail", datosEmail);		
		this.body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, Constantes.ENCODIG_UTF_8, model);		
	}

	public String getBody() {
		return body;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}

	
	public Map<String, Resource> getInlineFiles() {
		return inlineFiles;
	}
	
	public void setInlineFiles(Map<String, Resource> inlineFiles) {
		this.inlineFiles = inlineFiles;
	}

	public void setTemplate(String template){
		this.template = template;
	}

	@Override
	public void changeSubject(String subject){
		setSubject(subject);
	}
}