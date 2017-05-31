package com.gfi.bin.admctasweb.comunes.exception;

import java.util.Map;
import java.util.TreeMap;

/**
 * Clase de Excepción para servicios
 * @author PCS
 *
 */
public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    protected Map<String, String> messages;

    public ServiceException() {
        super();
        messages = new TreeMap<String, String>();
    }

    public ServiceException(String message) {
        super(message);
        messages = new TreeMap<String, String>();
    }

    public ServiceException(Map<String, String> messages) {
        this.messages = messages;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }

}
