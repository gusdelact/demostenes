package com.gfi.bin.admctasweb.catalogos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.PersonaListModel;
import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.catalogos.service.PersonaService;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;


/**
 * Clase encarga de controlar todas la peticiones de la vista 'PersonaView'
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Controller
public class PersonaController {
	private final Logger log = LoggerFactory.getLogger(PersonaController.class);
	
	@Autowired
	private PersonaService personaService;
	/**
	 * Crea o Actualiza un registro de Persona.
	 * @param vo
	 * @return
	 * @throws Exception
	 */ 
	@RequestMapping(value = "/catalogos/persona/update.htm",method = RequestMethod.POST)	
	public @ResponseBody ExtjsResponseMsg actualizarPersona(@RequestBody PersonaListModel vo) throws Exception {
		log.debug("## --> PersonaController.savePersona() ##");
		
		PersonaModel persona = new PersonaModel();
		List<PersonaModel> lista = new ArrayList<PersonaModel>();
		PersonaListModel personaList = new PersonaListModel();
		String msg = "";
		
		//Si la lista contiene elementos Actualizamos las Personas.
		if(vo.getPersonaList() != null){
			
			Iterator<PersonaModel> it = vo.getPersonaList().iterator();
			while (it.hasNext()){
				persona = it.next();
				
				//Creamos o Actualizamos las Personas.
				if (this.personaService.existePersona(persona.getNumOficio(), persona.getTipoOficio(), persona.getNumConsec())) {
					persona.setCveUsuMod(Util.usuarioSesion());
					persona.setFhUltMod(new Date());
					
					this.personaService.actualizarPersona(persona).getData();
					msg = "La Persona se actualizó correctamente";
				}else {
					persona.setNumConsec(Long.valueOf(this.personaService.obtenerConsecutivo(persona.getNumOficio(), persona.getTipoOficio())));
					persona.setCveUsuAlta(Util.usuarioSesion());
					persona.setFhAlta(new Date());
					
					this.personaService.guardarPersona(persona).getData();
					msg = "La Persona se guardó correctamente";
				}
			}
		}
		
		lista = this.personaService.buscarPersonasPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		
		personaList.setNumOficio(vo.getNumOficio());
		personaList.setTipoOficio(vo.getTipoOficio());
		personaList.setPersonaList(lista);

		ExtjsResponseMsg response = new ExtjsResponseMsg(personaList, msg);

		return response;
	}
	
	/**
	 * Elimina un registro de Persona
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/persona/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg eliminarPersona(@RequestBody PersonaModel vo) throws Exception {
		List<PersonaModel> lista = new ArrayList<PersonaModel>();
		PersonaListModel personaList = new PersonaListModel();
		
		//Eliminamos el registro de Persona.
		this.personaService.eliminarPersona(vo);
		
		//Obtenemos la lista de Personas Actualizada.
		lista = this.personaService.buscarPersonasPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		
		personaList.setNumOficio(vo.getNumOficio());
		personaList.setTipoOficio(vo.getTipoOficio());
		personaList.setPersonaList(lista);

		ExtjsResponseMsg response = new ExtjsResponseMsg(personaList, new String("La Persona se eliminó correctamente."));
		
		return response;
	}
	
	/**
	 * Obtiene la Lista de personas relacionadas a un Oficio.
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/persona/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg obtenerListaPersonas(@RequestBody PersonaListModel vo) throws Exception{
		log.info("Entro al metodo obtenerPersonasPorOficio" + vo);
		
		List<PersonaModel> personaList = personaService.buscarPersonasPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		
		PersonaListModel personas = new PersonaListModel();
		personas.setNumOficio(vo.getNumOficio());
		personas.setTipoOficio(vo.getTipoOficio());
		personas.setPersonaList(personaList);

		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(personas);
		
		return response;
	}
	
	/**
	 * Obtiene datos completos de la persona realiza la busqueda por id
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/personaView/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg obtenerPersona(@RequestBody PersonaModel vo) throws Exception{
		log.info("Entro al metodo read persona" + vo);		
		PersonaModel resp = (PersonaModel) personaService.buscarPersonaId(vo).getData();
		ExtjsResponseMsg response = new ExtjsResponseMsg(resp);
		
		return response;
	}
	
	
}
