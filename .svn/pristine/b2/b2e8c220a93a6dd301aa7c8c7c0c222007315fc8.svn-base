package com.gfi.bin.admctasweb.operativos.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.catalogos.dao.OficioDao;
import com.gfi.bin.admctasweb.catalogos.dao.ParametrosDao;
import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.catalogos.service.ParametrosService;
import com.gfi.bin.admctasweb.comun.dao.ComunDao;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.mail.model.AttachmentMail;
import com.gfi.bin.admctasweb.mail.service.EmailMessageProvider;
import com.gfi.bin.admctasweb.mail.service.MailService;
import com.gfi.bin.admctasweb.operativos.model.ArhivoCnbvModel;
import com.gfi.bin.admctasweb.operativos.model.OficioCnbvModel;
import com.gfi.bin.admctasweb.operativos.service.ArchivoCnbvService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.DateUtil;
import com.gfi.bin.admctasweb.util.FileUtil;
import com.gfi.bin.admctasweb.util.LectorPropertiesUtil;
import com.gfi.bin.admctasweb.util.Response;
import com.gfi.bin.admctasweb.util.SFTPUtil;
import com.gfi.bin.admctasweb.util.Util;

/**
 * Implementacion de los servicios encargados de gestionar operaciones relacionadas al
 * "Generacion de archivo CNBV" interfaz ArchivoCnbvService
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Service(value = "archivoCnbvService")
public class ArchivoCnbvServiceImpl implements ArchivoCnbvService {
	private final Logger log = LoggerFactory.getLogger(ArchivoCnbvServiceImpl.class);
	
	@Autowired
	private OficioDao oficioDao;
	@Autowired
	private ComunDao comunDao;
	@Autowired
	private ParametrosService parametrosService;
	
	@Autowired
	private ParametrosDao parametrosDao;
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimientoService;
	
	@Autowired
	private MailService mailService;
	@Autowired
	private EmailMessageProvider velocityEmailMessageProvider;
	private static final String CVE_EMAIL = Constantes.GENERACION_ARCHIVO_CNBV_COMP;
	
	/**
	 * Consulta los oficios que se utilizaran para la generacion del archivo, que se envia a la CNBV
	 * @param vo
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Response buscarDatosOficioAndRegistro(ArhivoCnbvModel vo) throws ServiceException {
		List<OficioCnbvModel> list = new ArrayList<OficioCnbvModel>();		
		list = oficioDao.buscarArhivoCnbvModel(vo);		
		Response resp = new Response(false);
		try{
			resp.setData(list);
			resp.setSuccess(true);
			resp.setMsg("");
		}catch(Exception e){
			log.error(e.getLocalizedMessage());
			e.printStackTrace();
		}	
		return resp;
	}
		
	/**
	 * Se encarga de generar el archivo txt para la CNBV, respetando todas la validaciones pertinentes
	 * @param listaOficiosSelect
	 * @return
	 * @throws ServiceException 
	 */
	@Override
	public Response generarArchivoCnbv(List<OficioCnbvModel> listaOficiosSelect) throws ServiceException{
		Response resp = new Response(true);
		String separador = ";";
		String leyenda = "";
		Date sysdate = null;
		String folioArch = "";
		String ruta =  LectorPropertiesUtil.obtenerValoresEtiqueta(
                Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES,
                Constantes.KEY_SFTP_RUTA_REPOSITORIO_FINAL) + "/";
		try {
			ParametrosModel parametros = new ParametrosModel("RESPUESTA","SEPARA");
			List<ParametrosModel> listResult = parametrosService.consultarParametros(parametros);
			if(listResult.size()>0){
				separador = listResult.get(0).getDescParam();
			}
			parametros = new ParametrosModel("LEYENDA","PLD");
			listResult = parametrosService.consultarParametros(parametros);
			if(listResult.size()>0){
				leyenda = listResult.get(0).getDescParam();
			}
			sysdate = comunDao.getDateFechaEmpresa(Constantes.ID_EMPRESA_BANCO, Constantes.CVE_FECHA_HOY);
			
			log.debug("Token separador > " + separador);
			
			String fechaddMMyyyy = DateUtil.obtenerDatoFechaString(sysdate, "ddMMyyyy");
			folioArch = parametrosDao.obtenerFolioArchivoCnbv(obtieneTipoArchivo(listaOficiosSelect.get(0).getTipoOficio() ), 
						listaOficiosSelect.get(0).getIdEmpresa() , fechaddMMyyyy , Util.usuarioSesion() );
			
			//Obtiene la ruta de la base de datos
			parametros = new ParametrosModel("RUTAOFICIO","RUTA");			
//			listResult = parametrosService.consultarParametros(parametros);
//			
//			if(listResult.size()>0){
//				ruta = listResult.get(0).getDescParam();
//			}
		} catch (Exception e1) {
			resp.setSuccess(false);
			log.error( e1.getLocalizedMessage() );
			throw new ServiceException("Ocurrio un error al obtener parametria del archivo");
		}
		log.info("Se obtuvo toda la parametria satisfactoriamente");
		
		File fichero = null;
		File ficheroLocal = null;
		Writer pw = null;
        Writer w = null;
        String nombreArchivo = contruyeNombreArchivo(new Date(), listaOficiosSelect.get(0).getTipoOficio(), listaOficiosSelect.get(0).getIdEmpresa(), folioArch) + ".txt";
        String pathFile = ruta + obtieneComplementoRuta( listaOficiosSelect.get(0).getFhOficio() );       
        log.debug("Path del archivo ->" + pathFile);
        String pathSystem = "";
        try {
        	pathSystem =  FilenameUtils.separatorsToUnix( pathFile );            
			SFTPUtil.crearDirectorioRemoto(pathSystem);
		} catch (Exception e1) {
			if(e1.getMessage().equals("No se puede crear un archivo que ya existe. ")){
				log.info("Ya existe el directorio");
			}else if(e1.getMessage().equals("Bad message")){
				log.info("Ya existe el directorio");
			}else{
				resp.setSuccess(false);
				log.error( "No se pudo crear la carpeta destino del archivo" +e1.getLocalizedMessage() );
				throw new ServiceException("No se pudo crear la carpeta destino del archivo");
			}
		}
        
        //FileUtil.crearDirectorios(pathFile);
        try
        {
            fichero = new File(nombreArchivo);
            ficheroLocal = new File(nombreArchivo);
            
            FileOutputStream is = new FileOutputStream(ficheroLocal);            
            OutputStreamWriter osw = new OutputStreamWriter(is);  
            w = new BufferedWriter(osw);
            
            FileOutputStream is2 = new FileOutputStream(fichero);            
            OutputStreamWriter osw2 = new OutputStreamWriter(is2);  
            pw = new BufferedWriter(osw2);
            
           
            int consecutivo = 1;
			for (OficioCnbvModel oficioModel : listaOficiosSelect) {  
				int anioFechaArchivo = DateUtil.obtenerAnio(oficioModel.getFhOficio());
				if(oficioModel.getTipoOficio().equals(Constantes.TIPO_OFICIO_AS	)){
					String linea = oficioModel.getNumRegistro() + separador +
							anioFechaArchivo + separador +  
		        			oficioModel.getNumOficio() + separador + 
		        			oficioModel.getNumFolio()  + separador + 
		        			leyenda + separador +
		        			consecutivo;
					pw.write( linea + "\n");	
					 w.write(linea + "\n");
					}else{
					String linea =  oficioModel.getNumFolio() + separador +
								anioFechaArchivo + separador +  
			        			oficioModel.getNumOficio() + separador + 
			        			oficioModel.getNumExped()  + separador + 
			        			leyenda + separador +
			        			consecutivo;
					  pw.write( linea + "\n");	
					  w.write(linea + "\n" );
				}				
				
		        oficioDao.updateArchivotoCnbv(new Date(), Util.usuarioSesion(), nombreArchivo, oficioModel.getNumOficio(), oficioModel.getTipoOficio());
		        guardarBitacora(oficioModel.getNumOficio(), oficioModel.getTipoOficio());
		        consecutivo++;
			}			
        } catch (Exception e) {
            e.printStackTrace();
            resp.setSuccess(false);
			log.error( e.getLocalizedMessage() );
			throw new ServiceException("Ocurrio un error al crear el archivo");
        } finally {
           try {        
           if (null != pw){
              pw.close();
           }
           if (null != w){
        	   w.close();
            }
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        
			FileInputStream f;
			try {
				f = new FileInputStream(fichero);
				SFTPUtil.subirArchivo(f, pathSystem+fichero.getName());
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new ServiceException("Ocurrio un error al crear el archivo, en la ruta " + pathSystem);
			}
			
		resp.setMsg(pathFile+nombreArchivo);
		resp.setData(ficheroLocal);
		try{			
			AttachmentMail[] fileAttachment = null;
			if(ficheroLocal!=null){
				fileAttachment =  new AttachmentMail[1];
				fileAttachment[0] = new AttachmentMail( ficheroLocal.getName() , FileUtil.converterFileToArray(ficheroLocal) );
				
			}	
			String operacion = Constantes.GENERACION_ARCHIVO_CNBV + " " + ficheroLocal.getName();
			enviarEmailSeguimiento(operacion,fileAttachment);
		}catch(Exception e){
			resp.setMsg("Se creó el archivo con éxito, ocurrio un error al enviar el email de notificación");
			throw new ServiceException(resp.getMsg());
		}		
		
		return resp;
	}
	
	/**
	 * Busca un prefijo para los distintos tipod de archivo, esto para la creacion del nombre
	 * del archivo que se envia a la CNBV
	 * @param tipoOficio
	 * @return
	 */
	private String obtieneTipoArchivo(String tipoOficio){
		String tipo = "";
		if(tipoOficio.equals(Constantes.TIPO_OFICIO_AS)){
			tipo = "ASE";
		}else if(tipoOficio.equals(Constantes.TIPO_OFICIO_JU)){
			tipo = "JUD";
		}else if(tipoOficio.equals(Constantes.TIPO_OFICIO_HA)){
			tipo = "HAC";
		}else if(tipoOficio.equals(Constantes.TIPO_OFICIO_PLD_PERSISTIR)){
			tipo = "PLD";
		}
		return tipo;
	}
	
	/**
	 * Metodo que genera el nombre del archivo
	 * @param sysdate
	 * @param tipoOficio
	 * @param idEmpresa
	 * @param num
	 * @return
	 */
	private String contruyeNombreArchivo(Date sysdate, String tipoOficio, Integer idEmpresa, String num){		
		String nombreArchivo = DateUtil.obtenerDatoFecha(sysdate, "yyMMdd") + obtieneTipoArchivo(tipoOficio) + "_" + idEmpresa + "_"+ num;
		return nombreArchivo;		
	}
	/**
	 * Metodo que devuelve parte del path donde sera creado el archivo este corresponde
	 * al año del oficio, en formato yyyyMM al final agrega un separador
	 * @param fecha
	 * @return
	 */
	private String obtieneComplementoRuta(Date fecha){
		return DateUtil.obtenerDatoFechaString(fecha, "yyyyMM") + "/";
	}
	
	/**
	 * Metodo de inserción de bitacora se seguimiento 
	 * @param numOficio
	 * @param tipoOficio
	 * @throws ServiceException 
	 */
	private void guardarBitacora(String numOficio, String tipoOficio) throws ServiceException{		
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		seguimiento.setNumOficio(numOficio);
		seguimiento.setTipoOficio(tipoOficio);
		seguimiento.setCveEstatus(Constantes.GENERACION_ARCHIVO_CNBV_COMP);		
		this.bitacoraSeguimientoService.guardar(seguimiento);
	}
	
	/**
	 * Metodo encargado de envíar el correo de notificacion
	 * @param numOficio
	 * @param tipoOficio
	 * @param operacion
	 * @throws ServiceException
	 */
	public void enviarEmailSeguimiento( String operacion, AttachmentMail[] attachments) throws ServiceException {
		String templateVelocity = "mail/notificacionAdmCtasWeb.vm";
	
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("tipoNotificacion", operacion);
			model.put("usuarioGenera", Util.usuarioSesion());

			
			velocityEmailMessageProvider.fillblanks(templateVelocity, null, model);
			velocityEmailMessageProvider.changeSubject(operacion + "." );

			mailService.send(velocityEmailMessageProvider, CVE_EMAIL, attachments);
			
		} catch (Exception e) {
			final String msg = "Excepción en el envío de mail de Notificación.";
			log.warn(msg, e);
			throw new ServiceException(e);
		}
	}
}
