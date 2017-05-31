package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.RespContrato;
import com.gfi.corp.component.genericgridman.dto.GenericGridReq;

public interface RespContratoService {
	
	public static final String CAT_RESP_CTO_SERVICE = "respContratoService";
	
	List<RespContrato> consultaContratosRespuesta(GenericGridReq gridReq, String numOficio) throws Exception;
	
	boolean insertarContratoRespuesta(List<RespContrato> respContratos, String cveUsuario) throws Exception;
	
	boolean actualizarContratoRespuesta(List<RespContrato> respContratos, String cveUsuario) throws Exception;
	
	boolean eliminarContratoRespuesta(List<RespContrato> respContratos) throws Exception;
}