package com.gfi.bin.admctasweb.jasper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.jasper.service.JasperService;
import com.gfi.bin.admctasweb.jasper.service.impl.JasperServiceImpl;
import com.gfi.bin.admctasweb.operativos.mapper.CnbvDictamenModel;
import com.gfi.bin.admctasweb.operativos.service.RegistroDictamenServiceTest;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContextTest.xml"})
@Transactional
public class ReportesServiceTest {
	
	@Autowired
	BasicDataSource corpDS;
	
	final Logger log = LoggerFactory.getLogger(RegistroDictamenServiceTest.class);
	
	@BeforeTransaction
	public void verificarEstadoInicial(){
		System.out.println("<!------------------------Antes de la transaccion------------------------>");
	}
	
	@AfterTransaction
	public void verificarEstadoFinal(){
		System.out.println("<!***********************Despues de la transaccion************************>");
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	//@Test
	public void generarReporte() throws SQLException {
		log.info("corpDS " + corpDS);
		String nombrePlantilla ="BitacoraDoctosEliminados";
		String nombreArchivo = "BitacoraDoctosEliminadosPDF";
		HashMap<String, Object> parametros = new HashMap<String, Object>(); 	
		parametros.put("numOficio", "214-2-JGO-558088-2006");
		
	
		 List<CnbvDictamenModel> datos = new ArrayList<CnbvDictamenModel>();
		 datos.add(new CnbvDictamenModel("214-2-JGO-558088-2006", "ha", 10L));
		String ruta = "C:\\";
		JasperService ser = new JasperServiceImpl();
		ser.generarReporteBean( (ruta + nombrePlantilla),  FormatoReporte.PDF, datos, parametros);		
		ser.generarReporteSqlJasper( (ruta + nombrePlantilla), FormatoReporte.PDF, parametros, corpDS.getConnection());
		log.debug( nombreArchivo+".pdf" );
	}
	@Test
	public void  cambiaModelo(){
		CnbvDictamenModel cnbvDictamenModel = new CnbvDictamenModel("ofi", "1", 2L);
		Method[] methods = CnbvDictamenModel.class.getMethods();

		for(Method method : methods){
		    System.out.println("method = " + method.getName());
		}
		
		try {          
            Field properties[] = cnbvDictamenModel.getClass().getDeclaredFields();
            for (int i = 0; i < properties.length; i++) {
                Field field = properties[i];
                if( field.getType().isAssignableFrom(String.class) ){
                	System.out.println(field.getName() +" > "+field.getType()+" > " +field.getType().getName());
                	String evaluar = BeanUtils.getProperty(cnbvDictamenModel, field.getName());
                    if( evaluar!=null && evaluar.equals("1") ){
                    	BeanUtils.setProperty(cnbvDictamenModel,  field.getName(), "X");
                    }
                }
                
            }
            log.debug("->"+cnbvDictamenModel);
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
		
	}
}
