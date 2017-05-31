package com.gfi.bin.admctasweb.util;

import java.io.Serializable;

/**
 * Clase respuesta, encargada de encapsular acciones mediante una bandera "success", un mensaje "msg" y objeto de respuesta "data"
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 * 
 */
public class Response implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private boolean success;
	private String msg;
	private Object data;	
	
	public Response() {
	
	}	
	
	public Response(boolean success) {
		super();
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
