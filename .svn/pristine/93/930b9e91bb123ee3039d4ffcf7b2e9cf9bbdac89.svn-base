package com.gfi.bin.admctasweb.procesoautomatico.service;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContextTest.xml"})
public class ValidacionCargaAutomaticaTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidacionCargaAutomaticaTest.class);
	
	@Autowired
	private ValidacionCargaAutomatica validacionCargaAutomatica;
	
	@Test
	public void validarPrecondicionesTest(){
		LOGGER.debug("Inicia validarPrecondicionesTest");
		
		Boolean resultado = false;
		
		try{
			resultado = validacionCargaAutomatica.validarPrecondiciones();
			
			LOGGER.debug("Restultado es: "+resultado);
		}catch(ServiceException serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
			serviceException.printStackTrace();
		}
		
		Assert.assertTrue(resultado);
	}
	
	@Test
	public void conexionSFTP(){
		Session session = null;
		ChannelSftp sftpChannel = null;
		InputStream in = null;
		try {
			JSch jsch = new JSch();
//			String knownHostsFilename = "/home/username/.ssh/known_hosts";
//			jsch.setKnownHosts( knownHostsFilename );
			
			session = jsch.getSession( "tortoise", "172.20.25.158" );    
			//UserInfo ui = new MyUserInfo();
			//session.setUserInfo(ui);
			
	        session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword( "tortoise" );			  
			session.connect();

			Channel channel = session.openChannel( "sftp" );
			channel.connect();

			sftpChannel = (ChannelSftp) channel;

			sftpChannel.get("ftproot/ejemplo.pdf", "C:\\Interacciones\\" );
			// OR
			in = sftpChannel.get( "ftproot/ejemplo.pdf" );
			// process inputstream as needed		
					
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			 
			if(sftpChannel != null)
				sftpChannel.exit();
			if(session != null)
				session.disconnect();	
			if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	
	@SuppressWarnings("unused")
	private class MyUserInfo implements UserInfo
	{

		@Override
		public String getPassphrase() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPassword() {
			// TODO Auto-generated method stub
			return "tortoise";
		}

		@Override
		public boolean promptPassword(String message) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean promptPassphrase(String message) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean promptYesNo(String message) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public void showMessage(String message) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
}