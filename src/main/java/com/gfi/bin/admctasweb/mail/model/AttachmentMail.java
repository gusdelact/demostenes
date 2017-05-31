package com.gfi.bin.admctasweb.mail.model;

import java.io.InputStream;
import java.io.Serializable;

public class AttachmentMail implements Serializable{
	private static final long serialVersionUID = 4571059384399940186L;
	
	private String name;
	private InputStream attachmentStream;
	private byte[] attachmentBytes;
	
	public AttachmentMail(){
		
	}
	
	public AttachmentMail(String name, byte[] attachmentBytes) {
		super();
		this.name = name;
		this.attachmentBytes = attachmentBytes;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InputStream getAttachmentStream() {
		return attachmentStream;
	}
	public void setAttachmentStream(InputStream attachmentStream) {
		this.attachmentStream = attachmentStream;
	}
	public byte[] getAttachmentBytes() {
		return attachmentBytes;
	}
	public void setAttachmentBytes(byte[] attachmentBytes) {
		this.attachmentBytes = attachmentBytes;
	}

}
