package com.gfi.bin.admctasweb.procesoautomatico.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.dao.DocumentoDAO;
import com.gfi.bin.admctasweb.catalogos.dao.OficioDao;
import com.gfi.bin.admctasweb.catalogos.dao.PersonaDao;
import com.gfi.bin.admctasweb.catalogos.model.DireccionesSolicitantesModel;
import com.gfi.bin.admctasweb.catalogos.model.DocumentoModel;
import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.catalogos.service.DireccionesSolicitantesService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaOficioModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaPersonaModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.ExpedienteModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.PersonaSolicitudModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientoDescargadoModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientosDescargadosModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.SolicitudEspecificaModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.SolicitudPartesModel;
import com.gfi.bin.admctasweb.procesoautomatico.service.GestionArchivosXMLService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.LectorPropertiesUtil;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.bin.admctasweb.util.XmlUtil;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Service
public class GestionArchivosXMLServiceImpl implements GestionArchivosXMLService {

	private static final Logger LOGGER = LoggerFactory.getLogger(GestionArchivosXMLServiceImpl.class);

	@Autowired
	private OficioDao oficioDAO;

	@Autowired
	private PersonaDao personaDAO;
	
	@Autowired
	private DocumentoDAO documentoDAO;
	
	@Autowired
	private DireccionesSolicitantesService direccionesSolicitantesService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimientoService;
	
	private List<BitacoraCargaAutomaticaModel> listaCgaAutEmpresas;
	private BitacoraCargaAutomaticaModel bitacoraCargaAutomatica;
	
	private List<RequerimientosDescargadosModel> listaRequerimientosDescargados;
	private RequerimientosDescargadosModel requerimientosDescargados;
	
	private List<BitacoraCargaAutomaticaOficioModel> listaOficiosCgaAut;
	private BitacoraCargaAutomaticaOficioModel bitCargaAutOficio;
	
	private List<BitacoraCargaAutomaticaPersonaModel> listaPersonasCgaAut;
	private BitacoraCargaAutomaticaPersonaModel bitCargaAutPersona;
	
	private String carpetaTipoOficio;
	private Integer consecutivoPersona;
	
	private static final String SITUACION_DIRECCION_BUSQUEDA = "AC";
	
	/**
	 * Metodo principal del Proceso Automático, lee archivos XML del directorio donde se han descomprimido, los datos leídos se guardan en BD
	 * 
	 * @return List<BitacoraCargaAutomaticaModel>
	 * @throws ServiceException
	 */
	@Transactional (value="corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class})
	public List<BitacoraCargaAutomaticaModel> leerArchivosXMLRepositorio() throws ServiceException{
		LOGGER.debug("Comienza leerArchivosXMLRepositorio");
		String rutaDescomprimidos = LectorPropertiesUtil.obtenerValoresEtiqueta(
				Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_DESCOMPRIMIDOS);
		
		
		listaCgaAutEmpresas = new  ArrayList<BitacoraCargaAutomaticaModel>();
		bitacoraCargaAutomatica = null;
		listaRequerimientosDescargados = null;
		requerimientosDescargados = null;
		listaOficiosCgaAut = new ArrayList<BitacoraCargaAutomaticaOficioModel>();
		bitCargaAutOficio = null;
		listaPersonasCgaAut = null;
		bitCargaAutPersona = null;
		carpetaTipoOficio = null;
		
		List<BitacoraCargaAutomaticaModel> listaCargaAutomatica = leerArchivosXMLDirectorio(rutaDescomprimidos, null, 0, null);
		
		//Insertar oficios que no tienen archivo XML y todos los documentos...
		guardarOficiosSinXMLDocumentos(listaCargaAutomatica);
		
		return listaCargaAutomatica;
	}
	
	/**
	 * Lee el directorio definido de archivos extraídos, se lee cada archivo XML 
	 * y se obtiene su información para luego ser tratada y persistida de acuerdo a las reglas de negocio
	 * 
	 * @param rutaArchivosLeerXML
	 * @param carpetaActual
	 * @param idEmpresa
	 * @param excelConciliador
	 * @return List<BitacoraCargaAutomaticaModel>
	 * @throws ServiceException
	 */
	private List<BitacoraCargaAutomaticaModel> leerArchivosXMLDirectorio(String rutaArchivosLeerXML, String carpetaActual, Integer idEmpresa, String excelConciliador) throws ServiceException{
		String rutaArchivosActual = rutaArchivosLeerXML;
		
		File folderDescomprimidos = new File(rutaArchivosActual);
		File[] listaArchivosDirectorio = folderDescomprimidos.listFiles();
		String nombreArchivoActual = null;
		ExpedienteModel expedienteModel = null;
		
		OficioModel oficioPersistir = null;
		PersonaModel personaPersistir = null;
		
		LOGGER.debug("Comienza leerArchivosXMLDirectorio");
		LOGGER.debug("Ubicación archivos descomprimidos: "+rutaArchivosActual);
		
		if(carpetaActual != null && Constantes.EMPRESA_CASA.equals(carpetaActual.toUpperCase())){
			idEmpresa = Constantes.ID_EMPRESA_CASA;
			LOGGER.debug("=====================> EMPRESA ES: "+carpetaActual.toUpperCase()+", IdEmpresa: "+idEmpresa);
		}
		else if(carpetaActual != null && Constantes.EMPRESA_BANCO.equals(carpetaActual.toUpperCase())){
			idEmpresa = Constantes.ID_EMPRESA_BANCO;
			LOGGER.debug("=====================> EMPRESA ES: "+carpetaActual.toUpperCase()+", IdEmpresa: "+idEmpresa);
		}
		else if(carpetaActual != null && Constantes.EMPRESA_ASEGURADORA.equals(carpetaActual.toUpperCase())){
			idEmpresa = Constantes.ID_EMPRESA_ASEGURADORA;
			LOGGER.debug("=====================> EMPRESA ES: "+carpetaActual.toUpperCase()+", IdEmpresa: "+idEmpresa);
		}
		
		if(carpetaActual != null && (Constantes.EMPRESA_CASA.equals(carpetaActual.toUpperCase())||Constantes.EMPRESA_BANCO.equals(carpetaActual.toUpperCase())||Constantes.EMPRESA_ASEGURADORA.equals(carpetaActual.toUpperCase()))){
			bitacoraCargaAutomatica = new BitacoraCargaAutomaticaModel();
			bitacoraCargaAutomatica.setIdEmpresa(idEmpresa);
			bitacoraCargaAutomatica.setFechaHoraRegistro(new Date());
			
			listaOficiosCgaAut = new ArrayList<BitacoraCargaAutomaticaOficioModel>();
			listaRequerimientosDescargados = new ArrayList<RequerimientosDescargadosModel>();
			
			bitacoraCargaAutomatica.setListaRequerimientosDescargados(listaRequerimientosDescargados);
			listaCgaAutEmpresas.add(bitacoraCargaAutomatica);
		}
		if(carpetaActual != null && (Constantes.TIPO_OFICIO_ASEGURAMIENTO.equals(carpetaActual.toUpperCase())||Constantes.TIPO_OFICIO_HACENDARIO.equals(carpetaActual.toUpperCase())||Constantes.TIPO_OFICIO_JUDICIAL.equals(carpetaActual.toUpperCase())||Constantes.TIPO_OFICIO_PLD.equals(carpetaActual.toUpperCase()))){
			if(Constantes.TIPO_OFICIO_ASEGURAMIENTO.equals(carpetaActual.toUpperCase())){
				carpetaTipoOficio = Constantes.TIPO_OFICIO_AS;
			}else if(Constantes.TIPO_OFICIO_HACENDARIO.equals(carpetaActual.toUpperCase())){
				carpetaTipoOficio = Constantes.TIPO_OFICIO_HA;
			}else if(Constantes.TIPO_OFICIO_JUDICIAL.equals(carpetaActual.toUpperCase())){
				carpetaTipoOficio = Constantes.TIPO_OFICIO_JU;
			}else if(Constantes.TIPO_OFICIO_PLD.equals(carpetaActual.toUpperCase())){
				carpetaTipoOficio = Constantes.TIPO_OFICIO_PLD_PERSISTIR;
			}
		}
		
		for(int i=0; i<listaArchivosDirectorio.length; i++){
			File archivoActual = listaArchivosDirectorio[i];
			nombreArchivoActual = archivoActual.getName();
			consecutivoPersona = 0;
			
			if(archivoActual.isFile()){
				if (nombreArchivoActual.endsWith(Constantes.EXTENSION_XML.toLowerCase()) || nombreArchivoActual.endsWith(Constantes.EXTENSION_XML.toUpperCase())){
					LOGGER.debug("Archivo actual: "+archivoActual+" es archivo... XML");
					try{
						expedienteModel = XmlUtil.xmlToExpedienteModel(archivoActual.getAbsolutePath());
						if(expedienteModel == null){ //Significa que hubo excepción al leer(porque no tiene la estructura de un XML correcto esperado)
							requerimientosDescargados = XmlUtil.xmlToRequerimientosDescargados(archivoActual.getAbsolutePath());
							requerimientosDescargados.setCarpetaUbicacion(carpetaTipoOficio);
							requerimientosDescargados.setIdEmpresa(idEmpresa);
							listaRequerimientosDescargados.add(requerimientosDescargados);
							
							continue;
						}
						LOGGER.debug("=== TipoOficio: "+expedienteModel.getTipoOficio());
						LOGGER.debug("=== NumeroOficio: "+expedienteModel.getNumeroOficio());
						LOGGER.debug("=== DiasPlazo: "+expedienteModel.getNumeroDiasPlazo());
						
						//Persistir en BD
						oficioPersistir = new OficioModel();
						//Campos obligatorios en tabla
						oficioPersistir.setNumOficio(expedienteModel.getNumeroOficio().trim());
						if(Constantes.TIPO_OFICIO_ASEGURAMIENTO.equals(expedienteModel.getTipoOficio().trim())){
							oficioPersistir.setTipoOficio(Constantes.TIPO_OFICIO_AS);
							oficioPersistir.setNumFolio(expedienteModel.getNumeroExpediente().trim());
							oficioPersistir.setNumRegistro(expedienteModel.getNumeroFolio().trim());
						}else if(Constantes.TIPO_OFICIO_HACENDARIO.equals(expedienteModel.getTipoOficio().trim())){
							oficioPersistir.setTipoOficio(Constantes.TIPO_OFICIO_HA);
							oficioPersistir.setNumFolio(expedienteModel.getNumeroFolio().trim());
							oficioPersistir.setNumExped(expedienteModel.getNumeroExpediente().trim());
						}else if(Constantes.TIPO_OFICIO_JUDICIAL.equals(expedienteModel.getTipoOficio().trim())){
							oficioPersistir.setTipoOficio(Constantes.TIPO_OFICIO_JU);
							oficioPersistir.setNumFolio(expedienteModel.getNumeroFolio().trim());
							oficioPersistir.setNumExped(expedienteModel.getNumeroExpediente().trim());
						}else if(expedienteModel.getTipoOficio().toUpperCase().trim().startsWith(Constantes.TIPO_OFICIO_OPERACIONES_ILICITAS)){
							oficioPersistir.setTipoOficio(Constantes.TIPO_OFICIO_PLD_PERSISTIR);
							oficioPersistir.setNumFolio(expedienteModel.getNumeroFolio().trim());
							oficioPersistir.setNumExped(expedienteModel.getNumeroExpediente().trim());
						}
						
						// Lectura CNBV faltante JAPL
						
						oficioPersistir.setFolioSiara(expedienteModel.getSolicitudSiara());
						oficioPersistir.setReferencia2(expedienteModel.getReferenciaDos());
						oficioPersistir.setNombreAutXml(expedienteModel.getAutoridadNombre());
						
						
						// JAPL
						
						oficioPersistir.setIdEmpresa(idEmpresa);//Se obtiene a partir de la carpeta contenedora del XML
						oficioPersistir.setFhOficio(expedienteModel.getFechaOficio());
						oficioPersistir.setFhRecepcion(expedienteModel.getFechaOficio());
						oficioPersistir.setNumDiasPzo(expedienteModel.getNumeroDiasPlazo());
						oficioPersistir.setbTurAudit(Constantes.TURNAR_AUDITORIA_FALSO);//Todos comienzan como F-also
						oficioPersistir.setSitOficio(Constantes.SITUACION_OFICIO_PENDIENTE);//Todos comienzan como PE-ndientes
						oficioPersistir.setFhAlta(new Date());
						oficioPersistir.setCveUsuAlta(Util.usuarioSesion());
												
						/**
						 * Se obtienen direcciones
						 */
						OficioModel oficioConDireccion = obtenerDireccionSolicitante(oficioPersistir.getTipoOficio());
						if(oficioConDireccion != null){
							oficioPersistir.setTxGerencia(oficioConDireccion.getTxGerencia());
							oficioPersistir.setTxSubgeren(oficioConDireccion.getTxSubgeren());
							oficioPersistir.setTxDireccion(oficioConDireccion.getTxDireccion());
							oficioPersistir.setTxAtnPue(oficioConDireccion.getTxAtnPue());
							oficioPersistir.setTxAtnNom(oficioConDireccion.getTxAtnNom());
						}
						
						/**
						 * Guardar en bitacoraCargaAutomaticaOficio
						 */
						listaPersonasCgaAut = new ArrayList<BitacoraCargaAutomaticaPersonaModel>();
						
						bitCargaAutOficio = new BitacoraCargaAutomaticaOficioModel();
						bitCargaAutOficio.setIdEmpresa(oficioPersistir.getIdEmpresa());
						bitCargaAutOficio.setNumeroOficio(oficioPersistir.getNumOficio());
						bitCargaAutOficio.setTipoOficio(oficioPersistir.getTipoOficio());
						bitCargaAutOficio.setExisteXML("S");
						bitCargaAutOficio.setDiasPlazo(oficioPersistir.getNumDiasPzo());
						bitCargaAutOficio.setNumeroFolio(oficioPersistir.getNumFolio());
//						bitCargaAutOficio.setReferencia(expedienteModel.getReferencia().trim().toUpperCase().contains(TEXTO_TAG_REFERENCIA)? expedienteModel.getReferencia().trim() : "");
						bitCargaAutOficio.setReferencia(StringUtils.isNotBlank(expedienteModel.getReferencia())? expedienteModel.getReferencia().trim() : "");
						bitCargaAutOficio.setListaPersonas(listaPersonasCgaAut);
						
						listaOficiosCgaAut.add(bitCargaAutOficio);
						
						/**
						 * Se persiste Oficio
						 */
						oficioDAO.guardarOficio(oficioPersistir);
						
						/**
						 * Se persiste seguimiento de oficio
						 */
						BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
						seguimiento.setNumOficio(oficioPersistir.getNumOficio());
						seguimiento.setTipoOficio(oficioPersistir.getTipoOficio());
						seguimiento.setCveEstatus(oficioConDireccion!=null? Constantes.REGISTRO_OFICIO_INFO_COMP : Constantes.REGISTRO_OFICIO_INFO_PEND);
						LOGGER.debug("Se va a guardar Seguimiento; numOficio: "+seguimiento.getNumOficio()+", tipoOficio: "+seguimiento.getTipoOficio()+", cveEstatus: "+seguimiento.getCveEstatus());
						bitacoraSeguimientoService.guardar(seguimiento);
						
						
						LOGGER.debug("\n\n\n=== ListaSolicitudEspecificaModel: "+expedienteModel.getListaSolicitudEspecificaModel());
						if(expedienteModel.getListaSolicitudEspecificaModel() != null){
							for(SolicitudEspecificaModel solicitudEspecificaModel : expedienteModel.getListaSolicitudEspecificaModel()){
								LOGGER.debug("=== Id: "+solicitudEspecificaModel.getSolicitudEspecificaId());
								LOGGER.debug("=== Instrucciones por conocer: "+solicitudEspecificaModel.getInstruccionesCuentasPorConocer());
								
								LOGGER.debug("=== ListaPersonasSolicitud: "+solicitudEspecificaModel.getListaPersonasSolicitud());
								for(PersonaSolicitudModel personaSolicitudModel : solicitudEspecificaModel.getListaPersonasSolicitud()){
									consecutivoPersona = consecutivoPersona+1;
									//Se va a persistir objeto PERSONA en BD
									personaPersistir = new PersonaModel();
									SolicitudPartesModel personaSolicitudPartes = null;
									
									String valores = LectorPropertiesUtil.obtenerValoresEtiqueta
											(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_NOMBRE_PERSONA);
									String[] arrayValores = valores.split(",");
									if(existeEnArreglo(personaSolicitudModel.getNombre(), arrayValores)){
										if(expedienteModel.getListaSolicitudPartesModel()!=null){
											personaSolicitudPartes = obtenerSolicitudParte(personaSolicitudModel.getPersonaId(), expedienteModel.getListaSolicitudPartesModel());
										}
									}
									
									//Llenar objeto personaPersistir, datos comunes...
									personaPersistir.setNumOficio(oficioPersistir.getNumOficio());
									personaPersistir.setTipoOficio(oficioPersistir.getTipoOficio());//Revisar porque los tipos vienen en nombres completos y la BD soporta 2 caracteres
									personaPersistir.setNumConsec(new Long(consecutivoPersona));
									
									//Datos de personaSolicitudPartes ó
									if(personaSolicitudPartes != null){//Obtener datos de personaSolicitudPartes (No vienen los datos en sección SolicitudEspecífica)
//										personaPersistir.setIdPersona(new Long(personaSolicitudPartes.getParteId()));
										personaPersistir.setTipoPersona(Constantes.CONTENIDO_PERSONA_FISICA.equals(personaSolicitudPartes.getPersona().trim()) ? Constantes.PERSONA_FISICA : Constantes.PERSONA_MORAL);
										personaPersistir.setApPaterno(personaSolicitudPartes.getPaterno().trim());
										personaPersistir.setApMaterno(personaSolicitudPartes.getMaterno().trim());
										personaPersistir.setNombre(personaSolicitudPartes.getNombre().trim());
										personaPersistir.setRfc(StringUtils.deleteWhitespace(personaSolicitudPartes.getRfc()));
										// JAPL se actualiza la descripcion y la clave de Caracteristica
										oficioPersistir.setCaracter(personaSolicitudPartes.getCaracter());
									}//Datos de personaSolicitudEspecífica
									else{//Obtener datos de personaSolicitudModel (Vienen los datos en sección SolicitudEspecífica)
//										personaPersistir.setIdPersona(new Long(personaSolicitudModel.getPersonaId()));
										personaPersistir.setTipoPersona(Constantes.CONTENIDO_PERSONA_FISICA.equals(personaSolicitudModel.getPersona().trim()) ? Constantes.PERSONA_FISICA : Constantes.PERSONA_MORAL);
										personaPersistir.setApPaterno(personaSolicitudModel.getPaterno().trim());
										personaPersistir.setApMaterno(personaSolicitudModel.getMaterno().trim());
										personaPersistir.setNombre(personaSolicitudModel.getNombre().trim());
										personaPersistir.setRfc(StringUtils.deleteWhitespace(personaSolicitudModel.getRfc()));
										// JAPL se actualiza la descripcion y la clave de Caracteristica
										oficioPersistir.setCaracter(personaSolicitudModel.getCaracter());
									}
																	
									if(personaSolicitudModel.getListaCuentasConocidas()!=null)
										personaPersistir.setCuenta(personaSolicitudModel.getListaCuentasConocidas().get(0).getCuenta().get(0).trim());
									personaPersistir.setCveUsuAlta(Util.usuarioSesion());
									personaPersistir.setFhAlta(new Date());
									
									//JAPL se busca la clave de caracter, contrato, autoridad y se actualiza en oficio
									oficioPersistir.setContratos(personaPersistir.getCuenta());
									oficioDAO.guardarCaracter(oficioPersistir);
									// JAPL 
									
									//Verificar si tiene más de 14 caracteres en rfc
									if(personaPersistir.getRfc().length() >= Constantes.LONGITUD_RFC_PERMITIDO || null != personaPersistir.getCuenta()){
										bitCargaAutPersona = new BitacoraCargaAutomaticaPersonaModel();
										bitCargaAutPersona.setIdEmpresa(oficioPersistir.getIdEmpresa());
										bitCargaAutPersona.setNumeroOficio(oficioPersistir.getNumOficio());
										bitCargaAutPersona.setTipoOficio(oficioPersistir.getTipoOficio());
										bitCargaAutPersona.setNumeroConsecutivoPersona(personaPersistir.getNumConsec().intValue());
										bitCargaAutPersona.setCuenta(personaPersistir.getCuenta());
										bitCargaAutPersona.setObservaciones(personaPersistir.getRfc().length() >= Constantes.LONGITUD_RFC_PERMITIDO ? 
													"El tamaño del RFC:'"+ personaPersistir.getRfc()+"' es mayor al permitido: "+Constantes.LONGITUD_RFC_PERMITIDO 
													:	"");
										listaPersonasCgaAut.add(bitCargaAutPersona);
									}
									LOGGER.debug("Se va a persistir persona: "+personaPersistir);
									personaDAO.guardarPersona(personaPersistir);
								}
							}
						}
					}catch(DAOException daoException){
						LOGGER.error("Error en acceso a datos: "+daoException.getLocalizedMessage());
						
						bitCargaAutOficio.setObservaciones("Ya existe un oficio con ésta llave;  NumOficio:"+oficioPersistir.getNumOficio()+", TipoOficio: "+oficioPersistir.getTipoOficio());
					}catch(Exception exception){
						LOGGER.error(exception.getLocalizedMessage());
						exception.printStackTrace();
					}
				}
				else if(nombreArchivoActual.endsWith(Constantes.EXTENSION_XLS.toLowerCase()) || nombreArchivoActual.endsWith(Constantes.EXTENSION_XLS.toUpperCase())){
					excelConciliador = nombreArchivoActual;
					bitacoraCargaAutomatica.setExcelConciliador(excelConciliador);
				}
			}
			else if(archivoActual.isDirectory()){
				LOGGER.debug("Archivo actual: "+archivoActual+" es directorio...");
				LOGGER.debug("rutaArchivosActual: "+rutaArchivosActual+File.separator+archivoActual.getName()+", archivoActual.getName(): "+archivoActual.getName());
				leerArchivosXMLDirectorio(rutaArchivosActual+File.separator+archivoActual.getName(), archivoActual.getName(), idEmpresa, excelConciliador);
			}
		}
		
		bitacoraCargaAutomatica.setListaOficios(listaOficiosCgaAut);
		
		LOGGER.debug("Termina leerArchivosXMLDirectorio");
		return listaCgaAutEmpresas;
	}
	
	/**
	 * Guarda los oficios sin XML y los documentos tanto para oficios con XML como para los que no tienen XML
	 * 
	 * @param listaCargaAutomatica
	 * @throws ServiceException
	 */
	private void guardarOficiosSinXMLDocumentos(List<BitacoraCargaAutomaticaModel> listaCargaAutomatica) throws ServiceException{
		//Lista de documentos que estuvieron repetidos, agregarlos en cada carga de empresa
		
		for(BitacoraCargaAutomaticaModel cargaAutomatica : listaCargaAutomatica){
			List<OficioModel> oficiosSinXML = new ArrayList<OficioModel>();
			List<DocumentoModel> listaDocumentos = new ArrayList<DocumentoModel>();
			DocumentoModel documento = null;
			
			//Aqui cada carga tiene una lista de oficios y una lista de RequerimsDescargados
			if(cargaAutomatica.getListaRequerimientosDescargados() != null){
				for(RequerimientosDescargadosModel requerimientos : cargaAutomatica.getListaRequerimientosDescargados()){
					if(requerimientos.getListaRequerimientosDescargados()!=null){
						for(RequerimientoDescargadoModel requerimiento : requerimientos.getListaRequerimientosDescargados()){
							
							String[] archivos = new String[3];
							archivos[0] = requerimiento.getXmlFileName().trim();
							archivos[1] = requerimiento.getOficioFileName().trim();
							archivos[2] = requerimiento.getRequerimientoFileName().trim();
							
							String nombreArchivoOficio = archivos[1];
							String nombreArchivoXML = archivos[0];
							
							//Por cada requerimiento obtener
							if("".equals(nombreArchivoXML)){
								OficioModel oficio = new OficioModel();
								
								//Campos obligatorios en tabla
								oficio.setNumOficio(requerimiento.getNoOficio().trim());
								oficio.setTipoOficio(requerimientos.getCarpetaUbicacion());
								oficio.setIdEmpresa(requerimientos.getIdEmpresa());
								oficio.setFhOficio(requerimiento.getFechaPublicacion());
								oficio.setFhRecepcion(requerimiento.getFechaPublicacion());
								oficio.setNumDiasPzo(requerimiento.getPlazoRespuesta());
								oficio.setbTurAudit(Constantes.TURNAR_AUDITORIA_FALSO);//Todos comienzan como F-also
								oficio.setSitOficio(Constantes.SITUACION_OFICIO_PENDIENTE);//Todos comienzan como PE-ndientes
								
								if(Constantes.TIPO_OFICIO_AS.equals(requerimientos.getCarpetaUbicacion())){
									oficio.setNumFolio(requerimiento.getNoExpediente().trim());
									oficio.setNumRegistro(requerimiento.getIdFolio().trim());
								} else{
									oficio.setNumFolio(requerimiento.getIdFolio().trim());
									oficio.setNumExped(requerimiento.getNoExpediente().trim());
								}
								
								oficio.setFhAlta(new Date());
								oficio.setCveUsuAlta(Util.usuarioSesion());
								
								oficiosSinXML.add(oficio);
							}
							
							Integer numeroDocumento = 1;
							for(int i=0; i<archivos.length; i++){
								if(archivos[i] != null && !archivos[i].equals("")){
									documento = new DocumentoModel();
									
									documento.setNumOficio(requerimiento.getNoOficio());
									documento.setTipoOficio(requerimientos.getCarpetaUbicacion());
									
									documento.setCveUsuAlta(Util.usuarioSesion());
									documento.setFhAlta(new Date());
									
									documento.setNumDocto(numeroDocumento);
									documento.setNomDocto(archivos[i]);
									
									if(archivos[i].toUpperCase().endsWith(Constantes.EXTENSION_DOC_XML)){
										documento.setCveDocto(Constantes.CLAVE_DOCTO_DA);
									}
									else if(archivos[i].toUpperCase().endsWith(Constantes.EXTENSION_DOC_TIF_ENC)){
										String nombreArchivoTIF = archivos[i];
										nombreArchivoTIF = nombreArchivoTIF.substring(0, nombreArchivoTIF.lastIndexOf('.'));//Quitando el .enc del final
										
										documento.setNomDocto(nombreArchivoOficio + Constantes.GUION_BAJO + nombreArchivoTIF);
										documento.setCveDocto(Constantes.CLAVE_DOCTO_DA);
									}
									else if(archivos[i].toUpperCase().endsWith(Constantes.EXTENSION_DOC_DOC) || archivos[i].toUpperCase().endsWith(Constantes.EXTENSION_PDF)){
										documento.setCveDocto(Constantes.CLAVE_DOCTO_OF);
									}
									
									listaDocumentos.add(documento);
									
									numeroDocumento++;
								}
							}
						}
					}
				}
			}
			
			//Se van a guardar los oficios que no tienen archivo XML
			LOGGER.debug("Cantidad OFICIOS SIN XML por empresa "+cargaAutomatica.getIdEmpresa()+": "+oficiosSinXML.size());
			List<BitacoraCargaAutomaticaOficioModel> listaBitacoraOficios = cargaAutomatica.getListaOficios();
			if(listaBitacoraOficios==null){
				listaBitacoraOficios = new ArrayList<BitacoraCargaAutomaticaOficioModel>();
			}
			
			BitacoraCargaAutomaticaOficioModel bitacoraOficio = null;
			for(OficioModel oficioSinXMLGuardar : oficiosSinXML){
				try{
					bitacoraOficio = new BitacoraCargaAutomaticaOficioModel();
					bitacoraOficio.setIdEmpresa(oficioSinXMLGuardar.getIdEmpresa());
					bitacoraOficio.setNumeroOficio(oficioSinXMLGuardar.getNumOficio());
					bitacoraOficio.setTipoOficio(oficioSinXMLGuardar.getTipoOficio());
					bitacoraOficio.setExisteXML("N");
					bitacoraOficio.setDiasPlazo(oficioSinXMLGuardar.getNumDiasPzo());
					bitacoraOficio.setNumeroFolio(oficioSinXMLGuardar.getNumFolio());
					
					LOGGER.debug("Se va a guardar oficio sin xml; NumOficio:"+oficioSinXMLGuardar.getNumOficio()+", TipoOficio:"+oficioSinXMLGuardar.getTipoOficio());
					oficioDAO.guardarOficio(oficioSinXMLGuardar);
					
				}catch(DAOException daoException){
					bitacoraOficio.setObservaciones("Ya existe un oficio con ésta llave;  NumOficio:"+oficioSinXMLGuardar.getNumOficio()+", TipoOficio: "+oficioSinXMLGuardar.getTipoOficio());
					
					LOGGER.error("Error al persistir oficio sin XML");
					daoException.printStackTrace();
				}
				listaBitacoraOficios.add(bitacoraOficio);
				
				/**
				 * Se persiste seguimiento de oficio, aquí por no tener XML se guarda con seguimiento de oficio con información pendiente
				 */
				BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
				seguimiento.setNumOficio(oficioSinXMLGuardar.getNumOficio());
				seguimiento.setTipoOficio(oficioSinXMLGuardar.getTipoOficio());
				seguimiento.setCveEstatus(Constantes.REGISTRO_OFICIO_INFO_PEND);
				try{
					LOGGER.debug("Se va a guardar bitacora de seguimiento para documento sin XML; NumOficio:"+oficioSinXMLGuardar.getNumOficio()+", TipoOficio:"+oficioSinXMLGuardar.getTipoOficio());
					bitacoraSeguimientoService.guardar(seguimiento);
				}catch(ServiceException serviceException){
					LOGGER.error("Error al persistir Bitacora de seguimiento");
					serviceException.printStackTrace();
				}
			}

			//Se van a guardar los documentos, es una lista por cada empresa
			LOGGER.debug("CantidadDoctos empresa "+cargaAutomatica.getIdEmpresa()+": "+listaDocumentos.size());
			for(DocumentoModel documentoGuardar : listaDocumentos){
				try{
					LOGGER.debug("Se va a guardar documento; NumOficio:"+documentoGuardar.getNumOficio()+", TipoOficio:"+documentoGuardar.getTipoOficio()+", NomDocto: "+documentoGuardar.getNomDocto()+", NumDocto: "+documentoGuardar.getNumDocto()+", CveDocto: "+documentoGuardar.getCveDocto());
					documentoDAO.guardarDocumento(documentoGuardar);
				}catch(DAOException daoException){
					LOGGER.error("Error al persistir documento...");
					daoException.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Evalúa si un texto existe en un arreglo que también se recibe como parámetro, el resultado
	 * se devuelve en un objeto de tipo Boolean
	 * 
	 * @param texto
	 * @param arregloValores
	 * @return Boolean
	 */
	private Boolean existeEnArreglo(String texto, String[] arregloValores){
		for(String textoActual : arregloValores){
			if(texto.toUpperCase().trim().equals(textoActual.toUpperCase().trim())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca a partir de un id y una lista de objetos SolicitudPartesModel, un objeto de éste tipo, 
	 * si no se hallan coincidencias, el resultado devuelto es nulo
	 * 
	 * @param personaId
	 * @param listaSolicitudesPartes
	 * @return SolicitudPartesModel
	 */
	private SolicitudPartesModel obtenerSolicitudParte(Integer personaId, List<SolicitudPartesModel> listaSolicitudesPartes){
		for(SolicitudPartesModel solicitudParte : listaSolicitudesPartes){
			if(personaId == solicitudParte.getParteId()){
				return solicitudParte;
			}
		}
		return null;
	}
	
	/**
	 * Obtiene un objeto de tipo OficioModel a partir del tipo de oficio, realiza una búsqueda de direcciones solicitantes, 
	 * si se halla más de un resultado ó ninguno, se devuelve nulo, en caso de encontrar sólo una coincidencia, entonces se
	 * devuelve ese resultado
	 * 
	 * @param tipoOficio
	 * @return OficioModel
	 */
	private OficioModel obtenerDireccionSolicitante(String tipoOficio){
		OficioModel oficioResultado = null;

		DireccionesSolicitantesModel direccionBusqueda = new DireccionesSolicitantesModel();
		direccionBusqueda.setTipoOficio(tipoOficio);
		direccionBusqueda.setSituacion(SITUACION_DIRECCION_BUSQUEDA);
		try{
			List<DireccionesSolicitantesModel> listaDirecciones = direccionesSolicitantesService.consultarDireccionesSolicitantes(direccionBusqueda);

			LOGGER.debug("Cantidad direcciones: "+listaDirecciones.size());

			if(listaDirecciones!=null && listaDirecciones.size() == 1){
				DireccionesSolicitantesModel direccion = listaDirecciones.get(0);

				oficioResultado = new OficioModel();
				oficioResultado.setTxGerencia(direccion.getGerencia());
				oficioResultado.setTxSubgeren(direccion.getSubgerencia());
				oficioResultado.setTxDireccion(direccion.getDireccion());
				oficioResultado.setTxAtnPue(direccion.getPuestoAtencion());
				oficioResultado.setTxAtnNom(direccion.getNombreAtencion());
			}
		}catch(ServiceException serviceException){
			LOGGER.error("Error al obtener direcciones solicitantes para tipoOficio='"+tipoOficio+"'");
		}

		return oficioResultado;
	}
}