package com.gfi.bin.admctasweb.catalogos.dao.util;

public class RespContratoQuery {
//	public static final String SELECT_RESP_CONTRATO_ORI = 
//			" SELECT NUM_OFICIO, TIPO_OFICIO, ID_CONTRATO, " +
//			"		 FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD " +
//			" FROM CNBV_RESP_CONTRATO ";
	
	public static final String SELECT_RESP_CONTRATO_ORI = 
			" SELECT NUM_OFICIO, TIPO_OFICIO, ID_CONTRATO, " +
			"		 FH_ALTA, CVE_USU_ALTA, FH_ULT_MOD, CVE_USU_MOD, MONTO_INICIAL " +
			" FROM CNBV_RESP_CONTRATO WHERE NUM_OFICIO = :NUM_OFICIO";
	
	public static final String SELECT_RESP_CONTRATO = 
			"select * from (" + SELECT_RESP_CONTRATO_ORI + ") where 1=1";
	
	public static final String INSERT_RESP_CONTRATO = 
			" INSERT INTO CNBV_RESP_CONTRATO ( " +
			"   NUM_OFICIO, " +
			"	TIPO_OFICIO, " +
			"   ID_CONTRATO, " +
			"	FH_ALTA, " +
			"   CVE_USU_ALTA, " +
			"   MONTO_INICIAL " +
			"  ) " +
			"  VALUES " +
			"  ( " +
			"   :NUM_OFICIO, " +
			"	:TIPO_OFICIO, " +
			"   :ID_CONTRATO, " +
			"   SYSDATE, " +
			"   :CVE_USU_ALTA, " +
			"   :MONTO_INICIAL " +
			"  )";

	public static final String UPDATE_RESP_CONTRATO = 
			" UPDATE CNBV_RESP_CONTRATO " +
			" SET    NUM_OFICIO   = :NUM_OFICIO, " +
			"		 TIPO_OFICIO  = :TIPO_OFICIO, " +
			"		 ID_CONTRATO  = :ID_CONTRATO, " +
			"		 FH_ULT_MOD   = SYSDATE, " +
			"		 CVE_USU_MOD  = :CVE_USU_MOD, " +
			"		 MONTO_INICIAL  = :MONTO_INICIAL " +
			" WHERE  NUM_OFICIO   = :NUM_OFICIO " +
			"		 AND    TIPO_OFICIO  = :TIPO_OFICIO " +
			"		 AND    ID_CONTRATO  = :ID_CONTRATO ";
			
			
	public static final String DELETE_RESP_CONTRATO = 
			"DELETE " +
			"  CNBV_RESP_CONTRATO " +
			"WHERE NUM_OFICIO   = :NUM_OFICIO " +
			"		 AND    TIPO_OFICIO  = :TIPO_OFICIO " +
			"		 AND    ID_CONTRATO  = :ID_CONTRATO ";
}