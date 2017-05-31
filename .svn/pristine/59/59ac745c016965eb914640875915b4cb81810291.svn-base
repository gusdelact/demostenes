package com.gfi.bin.admctasweb.comunes.exception;

import java.util.Map;
import java.util.TreeMap;

/**
 * Clase de excepción para daos
 * @author ESS3VAVC
 *
 */
public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    protected Map<String, String> messages;

    public DAOException() {
        super();
        messages = new TreeMap<String, String>();
    }

    public DAOException(String message) {
        super(message);
        messages = new TreeMap<String, String>();
    }

    public DAOException(Map<String, String> messages) {
        this.messages = messages;
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }
	
	

}
