package com.gfi.bin.admctasweb.catalogos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.RespContrato;
import com.gfi.corp.component.genericgridman.dto.GenericGridReq;

public interface RespContratoDao {
	
	List<RespContrato> consultaContratosRespuesta(GenericGridReq gridReq, String numOficio) throws Exception;
	
	boolean insertarContratoRespuesta(RespContrato respContrato, String cveUsuario) throws Exception;
	
	boolean actualizarContratoRespuesta(RespContrato respContrato, String cveUsuario) throws Exception;
	
	boolean eliminarContratoRespuesta(RespContrato respContrato) throws Exception;
}