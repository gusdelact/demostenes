package com.gfi.bin.admctasweb.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * Clase que contiene metodos para el manejo de archivos
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public class FileUtil {
	private static final int BUFFER = 1024;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
	
	/**
	 * Crea una copia de archivo
	 * @param origen
	 * @param destino
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void copy(File origen, File destino) throws IOException {

		byte buf[] = new byte[BUFFER];
		int cant;
		FileInputStream in = new FileInputStream(origen);
		FileOutputStream out = new FileOutputStream(destino);

		while ((cant = in.read(buf, 0, BUFFER)) != -1) {
			out.write(buf, 0, cant);
		}

	}
	
	/**
	 * Genera un objeto de tipo File a partir de un objeto MultipartFile
	 * 
	 * @param multipart
	 * @throws IOException
	 */
	public static File multipartFileToFile(MultipartFile multipartFile) throws IOException {
		String nombreArchivo = multipartFile.getOriginalFilename();
		
		File file = new File(nombreArchivo);
		FileOutputStream fos = new FileOutputStream(file);
		fos.write(multipartFile.getBytes());
		fos.flush();
		fos.close();
		
		return file;
	}
	
	/**
	 * Copia un archivo a un archivo destino elimina el origen
	 * @param origen
	 * @param destino
	 * @throws IOException
	 */
	public static void move(File origen, File destino) throws IOException{
		copy(origen, destino);
		origen.delete();
	}
	
	/**
	 * Convierte un archivo en un arreglo de bytes
	 * @param archivo
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	 @SuppressWarnings("resource")
	public static byte[] obtenArchivoPdf(String archivo) throws FileNotFoundException, IOException {
	        File file = new File(archivo);
	 
	        FileInputStream fis = new FileInputStream(file);
	       
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();
	        
	        byte[] buf = new byte[1024];
	        try {
	            for (int readNum; (readNum = fis.read(buf)) != -1;) {
	                bos.write(buf, 0, readNum); //no doubt here is 0
	            }
	        } catch (IOException ex) {
	        	 ex.getCause();
	        }
	        byte[] bytes = bos.toByteArray();
	 
	       return bytes;
	    }
	 
	 /**
		 * Convierte un archivo en un arreglo de bytes
		 * @param archivo
		 * @return
		 * @throws FileNotFoundException
		 * @throws IOException
		 */
		 @SuppressWarnings("resource")
		public static byte[] converterFileToArray(File file) throws FileNotFoundException, IOException {
		      
		        FileInputStream fis = new FileInputStream(file);
		       
		        ByteArrayOutputStream bos = new ByteArrayOutputStream();
		        
		        byte[] buf = new byte[1024];
		        try {
		            for (int readNum; (readNum = fis.read(buf)) != -1;) {
		                bos.write(buf, 0, readNum); //no doubt here is 0
		            }
		        } catch (IOException ex) {
		        	 ex.getCause();
		        }
		        byte[] bytes = bos.toByteArray();
		 
		       return bytes;
		    }
	 
	/**
	 * Convierte un arreglo de bytes en un archivo 
	 * @param nombreArchivo
	 * @param extencion
	 * @param contenido
	 * @return
	 * @throws IOException
	 */
	public static File convertirArrayToFile(String nombreArchivo,String extencion, byte[] contenido) throws IOException {

		File someFile = new File(nombreArchivo + extencion);
		FileOutputStream fos = new FileOutputStream(someFile);
		fos.write(contenido);
		fos.flush();
		fos.close();
		return someFile;
	}
	
	/**
	 * Realiza la copia de un archivo
	 * 
	 * @param archivoFuente
	 * @param archivoDestino
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static void copiarArchivo(File archivoFuente, File archivoDestino) throws IOException{
		if(!archivoDestino.exists()){
			archivoDestino.createNewFile();
		}

		FileChannel origen = null;
		FileChannel destino = null;
		try{
			origen = new FileInputStream(archivoFuente).getChannel();
			destino = new FileOutputStream(archivoDestino).getChannel();

			long count = 0;
			long size = origen.size();

			while((count += destino.transferFrom(origen, count, size-count))<size);
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			
			if(origen != null) {
				origen.close();
			}
			if(destino != null) {
				destino.close();
			}
		}
	}
	
	/**
	 * Elimina todos los archivos del directorio indicado, incluyendo los subdirectorios
	 * 
	 * @param rutaComprimidos
	 * @return Boolean
	 */
	public static Boolean eliminarArchivos(String rutaComprimidos){
		LOGGER.debug("Ruta de archivos a eliminar: "+rutaComprimidos);

		Boolean bandera = true;
		File folderComprimidos = new File(rutaComprimidos);
		File[] listaArchivosDirectorio = folderComprimidos.listFiles();
		String nombreArchivoActual = null;

		for(int i=0; i<listaArchivosDirectorio.length; i++){
			File archivoActual = listaArchivosDirectorio[i];

			if(archivoActual.isFile()){
				nombreArchivoActual = archivoActual.getName();
				LOGGER.debug("Se va a eliminar: "+nombreArchivoActual);

				if(archivoActual.delete()){
					LOGGER.debug("Se eliminó correctamente el archivo: "+nombreArchivoActual);
				}else{
					bandera = false;
					LOGGER.debug("No se pudo eliminar el archivo: "+nombreArchivoActual);
				}
			}
			else if(archivoActual.isDirectory()){
				eliminarArchivos(rutaComprimidos+File.separator+archivoActual.getName());
			}
		}
		return bandera;
	}
	
	/**
	 * Crea una estructura de directorios
	 * @param rutaDirectorios
	 */
	public static void crearDirectorios(String rutaDirectorios){
		File files = new File(rutaDirectorios);
		LOGGER.debug("RutaDirectorios a crear: "+rutaDirectorios);
    	if (!files.exists()) {
    		if (files.mkdirs()) {
    			System.out.println("Multiple directories are created!");
    			
    		} else {
    			System.out.println("Failed to create multiple directories!");
    		}
    	}
	}
}
