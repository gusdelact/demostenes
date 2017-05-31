package com.gfi.bin.admctasweb.catalogos.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.catalogos.dao.RespContratoDao;
import com.gfi.bin.admctasweb.catalogos.model.RespContrato;
import com.gfi.bin.admctasweb.catalogos.service.RespContratoService;
import com.gfi.corp.component.genericgridman.dto.GenericGridReq;

@Service(RespContratoService.CAT_RESP_CTO_SERVICE)
public class RespContratoServiceImpl implements RespContratoService{
	private final static Logger logger = Logger.getLogger(RespContratoServiceImpl.class);
	
	@Autowired
	private RespContratoDao respContratoDao;

	public List<RespContrato> consultaContratosRespuesta(GenericGridReq gridReq, String numOficio) throws Exception {
		logger.debug("En el método: RespContratoServiceImpl.consultaContratosRespuesta");
		
		List<RespContrato> list = new ArrayList<RespContrato>();
		
		list = this.respContratoDao.consultaContratosRespuesta(gridReq, numOficio);
		
		return list;
	}

	public boolean insertarContratoRespuesta(List<RespContrato> respContratos, String cveUsuario) throws Exception {
		logger.debug("En el método: RespContratoServiceImpl.insertarContratoRespuesta");		
		boolean respuesta = false;
		
		Iterator<RespContrato> iterator = respContratos.iterator();
		while(iterator.hasNext()){
			respuesta = this.respContratoDao.insertarContratoRespuesta(iterator.next(), cveUsuario);
		}
		
		return respuesta;
	}

	public boolean actualizarContratoRespuesta(List<RespContrato> respContratos, String cveUsuario) throws Exception {
		logger.debug("En el método: RespContratoServiceImpl.actualizarContratoRespuesta");		
		boolean respuesta = false;
		
		Iterator<RespContrato> iterator = respContratos.iterator();
		while(iterator.hasNext()){
			respuesta = this.respContratoDao.actualizarContratoRespuesta(iterator.next(), cveUsuario);
		}
		
		return respuesta;
	}

	public boolean eliminarContratoRespuesta(List<RespContrato> respContratos) throws Exception {
		logger.debug("En el método: RespContratoServiceImpl.eliminarContratoRespuesta");		
		boolean respuesta = false;
		
		Iterator<RespContrato> iterator = respContratos.iterator();
		while(iterator.hasNext()){
			respuesta = this.respContratoDao.eliminarContratoRespuesta(iterator.next());
		}
		
		return respuesta;
	}
}