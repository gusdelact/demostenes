package com.gfi.bin.admctasweb.mail.model;
/**
 * Modelo de datos a usar en el envio de correo electronico
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public class DatosEmail {
	private String nombre;
	private String para;
	private String asunto;
	private String mensaje;
	private String msgAdicional;
	private String[] destinatarios;
	
	public DatosEmail() {
		super();
	}
	
	public DatosEmail(String para, String asunto, String mensaje) {
		super();
		this.para = para;
		this.asunto = asunto;
		this.mensaje = mensaje;
	}
	
	public DatosEmail(String nombre, String para) {
		super();
		this.nombre = nombre;
		this.para = para;
	}
	
	public DatosEmail(String nombre, String[] destinatarios) {
		super();
		this.nombre = nombre;
		this.destinatarios = destinatarios;
	}

	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMsgAdicional() {
		return msgAdicional;
	}

	public void setMsgAdicional(String msgAdicional) {
		this.msgAdicional = msgAdicional;
	}

	public String[] getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(String[] destinatarios) {
		this.destinatarios = destinatarios;
	}
}