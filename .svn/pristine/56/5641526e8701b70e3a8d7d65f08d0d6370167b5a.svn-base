package com.gfi.bin.admctasweb.jasper.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.jasper.ReportDataSource;
import com.gfi.bin.admctasweb.jasper.service.JasperService;
/**
 * Implementa los metodos a utilizar para la generacion de reportes via jasper reports 
 *  @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 *
 */
@Service
public class JasperServiceImpl implements JasperService{
	
	
	/**
	 * Metodo encargado de compilar un jrxml, y generar un reporte PDF, Excel, CSV genera la ruta del archivo generado
	 * los campos field de llenan con un archivo bean vo o entity, los nombres de los campos deben coincidir con los field del reporte
	 * @param plantilla archivo jrxml
	 *
	 * @param formato tipo de formato generado
	 * @param lista una lista que contiene el bean que llena los fields del reporte
	 * @param pars parametros que se usan dentro de los campos parameter del reporte
	 * @return
	 */
	public  byte[] generarReporteBean(String plantilla,FormatoReporte formato,List<? extends Serializable> lista, HashMap<String, Object> pars) {
		byte[] archivo = null;
		InputStream in = null;
		try{
			in = this.getClass().getClassLoader().getResourceAsStream(plantilla+".jrxml");

			JasperDesign jd  = JRXmlLoader.load(in);  
			JasperReport compilado = JasperCompileManager.compileReport(jd);
			
			JasperPrint print;
			if(lista!=null&&!lista.isEmpty()){
				 print = JasperFillManager.fillReport(compilado, pars,  new ReportDataSource(lista));
			}else{
				 print = JasperFillManager.fillReport(compilado, pars,  new JREmptyDataSource() );
			}
			

			switch(formato) {
			case PDF:				
				archivo = JasperExportManager.exportReportToPdf(print);		    	
		    	break;
			case XLS:
				JRXlsExporter xlsExporter = new JRXlsExporter();
				ByteArrayOutputStream  xlsReport = new ByteArrayOutputStream();
				xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);            
				xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				xlsExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[]{"Hoja1"});

				xlsExporter.exportReport();
				archivo = xlsReport.toByteArray();
				break;
			case CSV:
				ByteArrayOutputStream  csvReport = new ByteArrayOutputStream();
				JRCsvExporter exporter = new JRCsvExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, csvReport);            
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
				exporter.exportReport();
				archivo = csvReport.toByteArray();
				break;
			case PDFVIEW:
				JasperViewer.viewReport (print);
				break;
			default:
				throw new ServiceException("No se ha implementado el archivo: " + formato);
			}
		}
		catch (Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		finally{
			if(in != null )
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		return archivo;
	}
	
	/**
	 * Metodo encargado de compilar un jrxml, y generar un reporte PDF, Excel, CSV genera la ruta del archivo generado
	 * este metodo llena los campos field a travez de una consulta que se encuentra dentro del mismo reporte
	 * @param plantilla
	 * @param formato
	 * @param lista
	 * @param pars
	 * @return
	 */
	public byte[] generarReporteSqlJasper(String plantilla, FormatoReporte formato, HashMap<String, Object> params,	Connection connection) {
		byte[] archivo = null;
		InputStream in = null;
		try{			
			in = this.getClass().getClassLoader().getResourceAsStream(plantilla+".jrxml");

			JasperDesign jd  = JRXmlLoader.load(in);  
			JasperReport compilado = JasperCompileManager.compileReport(jd);
			JasperPrint print = JasperFillManager.fillReport(compilado, params,  connection);
			
			switch(formato) {
			case PDF:
				archivo = JasperExportManager.exportReportToPdf(print);		    	
		    	break;
			case XLS:
				//Para Archivos de Excel
				JRXlsExporter xlsExporter = new JRXlsExporter();
				ByteArrayOutputStream  xlsReport = new ByteArrayOutputStream();
				xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);            
				xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				xlsExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[]{"Hoja1"});

				xlsExporter.exportReport();
				archivo = xlsReport.toByteArray();
				break;
			case CSV:
				ByteArrayOutputStream  csvReport = new ByteArrayOutputStream();
				JRCsvExporter exporter = new JRCsvExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, csvReport);            
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
				exporter.exportReport();
				archivo = csvReport.toByteArray();
				break;
			case PDFVIEW:
				//Para archivo csv
				JasperViewer.viewReport (print);
				break;
			default:
				throw new ServiceException("No se ha implementado el archivo: " + formato);
			}
		}
		catch (Exception e){
			System.out.println(e);
			e.printStackTrace();
		}
		finally{
			if(in != null )
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return archivo;
	}


}
