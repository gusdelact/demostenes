package com.gfi.bin.admctasweb.catalogos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContextTest.xml"})
//@Transactional
public class PersonasServiceTest {
	
	@Autowired
	PersonaService personaService;
	
	@BeforeTransaction
	public void verificarEstadoInicial(){
		System.out.println("<!------------------------Antes de la transaccion------------------------>");
	}
	
	@Test
	public void pruebaSearch(){
		System.out.println("pruebaSearch");
		System.out.println("" + personaService.getAllPersonas());
	}
	
	
	@Test
	public void pruebaUpdate() throws ServiceException{
		System.out.println("pruebaUpdate");
		PersonaModel persona = new PersonaModel();
		persona.setApMaterno("Castellanos");
		persona.setApPaterno("Reyes");
		persona.setCveUsuAlta("MANU05");
		persona.setCveUsuMod("MANU05");
		persona.setFhAlta(null);
		persona.setFhUltMod(new Date());
		persona.setIdPersona(1L);
		persona.setNombre("MANUEL");
		persona.setNumConsec(2L);
		persona.setNumDictamen("3DI");
		persona.setNumOficio("214-1-458037/2006");
		persona.setRfc("RFCPRUEBA");
		persona.setSitRespuesta("1");
		persona.setTipoOficio("AS");
		persona.setTipoPersona("3");
		persona.setCuenta("302616516");
		System.out.println("weboles" + personaService.actualizarPersona(persona));
	}
	
	@Test
	public void pruebaSave() throws ServiceException{
		System.out.println("pruebaSave");
		PersonaModel persona = new PersonaModel();
		persona.setApMaterno("Castellanos");
		persona.setApPaterno("Reyes");
		persona.setCveUsuAlta("BEEN0035");
		persona.setCveUsuMod("MANU05");
		persona.setFhAlta(new Date());
		persona.setFhUltMod(null);
		persona.setIdPersona(1L);
		persona.setNombre("MANUEL");
		persona.setNumConsec(2L);
		persona.setNumDictamen("3DI");
		persona.setNumOficio("214-1-458037/2006");
		persona.setRfc("RFCPRUEBA");
		persona.setSitRespuesta("1");
		persona.setTipoOficio("AS");
		persona.setTipoPersona("3");
		//System.out.println("----- " + personaService.guardarPersona(persona));
	
		
	}	
	
	@Test
	public void pruebaPersonasPorOficio(){
		
		List<PersonaModel> listPer = new ArrayList<PersonaModel>();
		try {
			listPer = this.personaService.buscarPersonasPorOficio("214-2/SJ-43526/2006", "JU");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PersonaModel persona = new PersonaModel();
		Iterator<PersonaModel> it = listPer.iterator();
		while (it.hasNext()){
			persona = it.next();
			
			System.out.println(persona.getNumOficio());
			System.out.println(persona.getTipoOficio());
			System.out.println(persona.getNumConsec());
			System.out.println(persona.getNombre());
			System.out.println(persona.getApPaterno());
			System.out.println(persona.getApMaterno());
		}
	}
	
	
	@AfterTransaction
	public void verificarEstadoFinal(){
		System.out.println("<!***********************Despues de la transaccion************************>");
	}


}
