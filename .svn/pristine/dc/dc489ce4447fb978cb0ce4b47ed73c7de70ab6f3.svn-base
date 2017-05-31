/**
 * 
 */
package com.gfi.corp.component.genericgridman.service.impl;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperXlsxExporterBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilders;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.datasource.DRDataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gfi.corp.component.genericgridman.dto.ColumnaExport;
import com.gfi.corp.component.genericgridman.dto.ExportarGridReq;
import com.gfi.corp.component.genericgridman.dto.MapaValoresExport;
import com.gfi.corp.component.genericgridman.service.ExporterService;
//import com.gfi.corp.icap.insumos.service.CartCrService;
//import com.gfi.corp.icap.insumos.service.ConsolidadoService;
//import com.gfi.corp.icap.insumos.service.InsCartValContService;
//import com.gfi.corp.icap.insumos.service.InsFwdService;
//import com.gfi.corp.icap.insumos.service.InsIcapEmisorService;
//import com.gfi.corp.icap.insumos.service.InsOpcionService;
//import com.gfi.corp.icap.insumos.service.InsPacService;
//import com.gfi.corp.icap.insumos.service.InsR1Service;
//import com.gfi.corp.icap.insumos.service.InsSwapService;
//import com.gfi.corp.icap.insumos.service.InsValFixmonService;
//import com.gfi.corp.icap.insumos.service.InsValVpdService;
//import com.gfi.corp.icap.insumos.service.PrestInterService;
//import com.gfi.corp.icap.insumos.service.InsBrasService;
//import com.gfi.corp.icap.insumos.service.InsBrasDetService;
//import com.gfi.corp.icap.insumos.service.InsVecAnaService;
//import com.gfi.corp.icap.insumos.service.InsCartValService;
//import com.gfi.corp.icap.calculadoras.service.CalculadorasService;
//import com.gfi.corp.icap.catalogos.service.IcCalFormF5Service;
//import com.gfi.corp.icap.catalogos.service.IcapInstService;
//import com.gfi.corp.icap.insumos.service.InsGlobService;
//import com.gfi.corp.icap.insumos.service.InsSiarService;
//import com.gfi.corp.icap.insumos.service.InsSumService;
//import com.gfi.corp.icap.insumos.service.InsTitCirService;
//import com.gfi.corp.icap.procesos.service.ProcesosService;
//import com.gfi.corp.icap.catalogos.service.InsumoEmpService;

/**
 * implementación del Servicio de Reportes.
 * @author LUGL4884
 *
 */
@Service(ExporterService.EXPORTER_SERVICE)
public class ExporterServiceImpl implements ExporterService {
	private final static Logger logger = Logger.getLogger(ExporterServiceImpl.class);
	
//	@Autowired
//	private PrestInterService prestInterService;
//	
//	@Autowired
//	private CartCrService cartCrService;
//	
//	@Autowired
//	private InsFwdService insFwdService;
//	
//	@Autowired
//	private InsOpcionService insOpcionService;
//	
//	@Autowired
//	private InsSwapService insSwapService;
//
//	@Autowired
//	private InsBrasService insBrasService;
//	
//	@Autowired
//	private InsBrasDetService insBrasDetService;	
//	
//	@Autowired
//	private InsVecAnaService insVecAnaService;
//
//	@Autowired
//	private InsCartValService insCartValService;
//
//	@Autowired
//	private IcapInstService icapInstService;
//	
//	@Autowired
//	private ConsolidadoService consolidadoService;
//
//	@Autowired
//	private InsGlobService insGlobService;
//	
//	@Autowired
//	private InsSiarService insSiarService;	
//
//	@Autowired
//	private InsSumService insSumService;	
//
//	@Autowired
//	private InsTitCirService insTitCirService;	
//	
//	@Autowired
//	private InsValFixmonService insValFixmonService;	
//	
//	@Autowired
//	private InsValVpdService insValVpdService;	
//	
//	@Autowired
//	private InsIcapEmisorService insIcapEmisorService;	
//	
//	@Autowired
//	private InsCartValContService insCartValContService;
//	
//	@Autowired
//	private InsPacService insPacService;
//	
//	@Autowired
//	private InsR1Service insR1Service;
//
//	@Autowired
//	private InsumoEmpService insumoEmpService;
//	
//	@Autowired
//	private IcCalFormF5Service icCalFormF5Service;
//	
//	@Autowired
//	private ProcesosService procesosService;
//	
//	@Autowired
//	private CalculadorasService calculadorasService;
	
	/* (non-Javadoc)
	 * @see com.gfi.corp.component.genericgridman.service.ExporterService#generarReporte
	 */
	public byte [] generarExportacion(ExportarGridReq req) throws Exception {
		logger.debug("En el método: ExporterServiceImpl.generarReporte");
		
		ByteArrayOutputStream  outputReport = new ByteArrayOutputStream();
		 
		JasperReportBuilder report = DynamicReports.report();
		
    	StyleBuilders stl = DynamicReports.stl;
		StyleBuilder centeredStyle = stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER);	
		
		StyleBuilder columnTitleStyle  = stl.style(centeredStyle)
						.setFontSize(10)
		                .setBackgroundColor(Color.ORANGE);
		
		//Propiedades para la exportación a Excel.
		JasperXlsxExporterBuilder xlsxExporter = DynamicReports.export.xlsxExporter(outputReport)
				.setDetectCellType(true)
				.setIgnorePageMargins(true)
				.setWhitePageBackground(false)
				.setRemoveEmptySpaceBetweenColumns(true)
				.setFontSizeFixEnabled(true);		
		
		//Generamos las columnas y el DataSource del Reporte.
		MapaValoresExport mapa = this.generarDataSource(req);
		
		//Generamos las columnas del reporte dinamico.
		List<ColumnaExport> columnas = mapa.getColumnas();
		
		Iterator<ColumnaExport> it = columnas.iterator();
		while(it.hasNext()){
			ColumnaExport col = it.next();
			report.addColumn(Columns.column(col.getTitulo(), col.getCampo(), DataTypes.stringType()));
		}

		//Orientación del Reporte.
		if(columnas.size() > 6){
			report.setPageFormat(150 * columnas.size(), 50 * columnas.size(), PageOrientation.PORTRAIT);
		}else{
			report.setPageFormat(PageType.LETTER);
		}  		
    	
    	report.setColumnTitleStyle(columnTitleStyle).highlightDetailEvenRows();
    	
    	//Creamos el dataSource del reporte.
    	report.setDataSource(mapa.getDataSource());
    	
    	//Generamos el tipo de reporte correspondiente.
    	if( req.getTipo() != null && req.getTipo().equals("PDF")){
    		report.toPdf( outputReport );
    	}else {
    		report.ignorePagination();
    		report.toXlsx(xlsxExporter);
    	}	
    	
    	byte [] respuesta = outputReport.toByteArray();
    	outputReport.close();
		    
		return respuesta;
	}
	
	@SuppressWarnings({ "rawtypes" })
	private MapaValoresExport generarDataSource(ExportarGridReq req) throws Exception {
		logger.debug("generarDataSource para el Grid: " + req.getCveGrid());
		MapaValoresExport mapa = new MapaValoresExport();
		List<List<ColumnaExport>> columnasList = new ArrayList<List<ColumnaExport>>();
		List<List<String>> valoresList = new ArrayList<List<String>>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Long idCgaInsumo = 0L;
		Integer idEmpresa = (Integer) ((ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes()).getRequest().getSession().getAttribute("idEmpresa");
		
		List listaReg = new ArrayList();
		Field[] propiedades = null;
		
		//Obtenemos los parametros Extra si son necesarios.
		List<String> extParamList = this.armaExtParams(req.getExtParam());
		
		//Obtenemos los registros dependiendo de la Clave del Grid.
//		if(req.getCveGrid().equals("PrestInterGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = prestInterService.consultarPrestInter(req.getGridParams(), idCgaInsumo).getPrestInterList();
//		}else if(req.getCveGrid().equals("CartCrGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = cartCrService.consultarCartCr(req.getGridParams(), idCgaInsumo).getCartCrList();
//		}else if(req.getCveGrid().equals("InsFwdGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insFwdService.consultarInsFwd(req.getGridParams(), idCgaInsumo).getInsFwdList();
//		}else if(req.getCveGrid().equals("InsOpcionGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insOpcionService.consultarInsOpcion(req.getGridParams(), idCgaInsumo).getInsOpcionList();
//		}else if(req.getCveGrid().equals("InsSwapGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insSwapService.consultarInsSwap(req.getGridParams(), idCgaInsumo).getInsSwapList();
//		}else if(req.getCveGrid().equals("InsBrasGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insBrasService.consultarInsBras(req.getGridParams(), idCgaInsumo).getInsBrasList();
//		}else if(req.getCveGrid().equals("InsBrasDetGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insBrasDetService.consultarInsBrasDet(req.getGridParams(), idCgaInsumo).getInsBrasDetList();
//		}else if(req.getCveGrid().equals("InsVecAnaGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insVecAnaService.consultarInsVecAna(req.getGridParams(), idCgaInsumo).getInsVecAnaList();
//		}else if(req.getCveGrid().equals("IcapInstGrid")){
//			listaReg = this.icapInstService.consultarIcapInst(req.getGridParams()).getIcapInstList();
//		}else if(req.getCveGrid().equals("InsCartValGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insCartValService.consultarInsCartVal(req.getGridParams(), idCgaInsumo).getInsCartValList();
//		}else if(req.getCveGrid().equals("ConsolidadoGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = consolidadoService.consultarConsolidado(req.getGridParams(), idCgaInsumo).getConsolidadoList();
//		}else if(req.getCveGrid().equals("InsGlobGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insGlobService.consultarInsGlob(req.getGridParams(), idCgaInsumo).getInsGlobList();
//		}else if(req.getCveGrid().equals("InsSiarGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insSiarService.consultarInsSiar(req.getGridParams(), idCgaInsumo).getInsSiarList();
//		}else if(req.getCveGrid().equals("InsSumGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insSumService.consultarInsSum(req.getGridParams(), idCgaInsumo).getInsSumList();
//		}else if(req.getCveGrid().equals("InsTitCirGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insTitCirService.consultarInsTitCir(req.getGridParams(), idCgaInsumo).getInsTitCirList();
//		}else if(req.getCveGrid().equals("InsValVpdGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insValVpdService.consultarInsValVpd(req.getGridParams(), idCgaInsumo).getInsValVpdList();
//		}else if(req.getCveGrid().equals("InsValFixmonGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insValFixmonService.consultarInsValFixmon(req.getGridParams(), idCgaInsumo).getInsValFixmonList();
//		}else if(req.getCveGrid().equals("InsIcapEmisorGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insIcapEmisorService.consultarInsIcapEmisor(req.getGridParams(), idCgaInsumo).getInsIcapEmisorList();
//		}else if(req.getCveGrid().equals("InsCartValContGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insCartValContService.consultarInsCartValCont(req.getGridParams(), idCgaInsumo).getInsCartValContList();
//		}else if(req.getCveGrid().equals("InsPacGrid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insPacService.consultarInsPac(req.getGridParams(), idCgaInsumo).getInsPacList();
//		}else if(req.getCveGrid().equals("InsR1Grid")){
//			if (!extParamList.isEmpty()) {
//				idCgaInsumo = Long.parseLong(extParamList.get(0));
//			}
//			
//			listaReg = insR1Service.consultarInsR1(req.getGridParams(), idCgaInsumo).getInsR1List();
//		}else if(req.getCveGrid().equals("InsumoEmpGrid")){
//			listaReg = insumoEmpService.consultarInsumoEmp(req.getGridParams()).getInsumoEmpList();
//		}else if(req.getCveGrid().equals("IcCalFormF5Grid")){
//			listaReg = icCalFormF5Service.consultarIcCalFormF5(req.getGridParams()).getIcCalFormF5List();
//		}else if(req.getCveGrid().equals("CruceCatIcapGrid")){
//			idCgaInsumo = Long.parseLong(extParamList.get(0));
//			listaReg = procesosService.consultaCruceConsolidado(req.getGridParams(), idEmpresa, idCgaInsumo).getConsolidVsIcap();
//		}else if(req.getCveGrid().equals("ValidacionContGrid")){
//			idCgaInsumo = Long.parseLong(extParamList.get(0));
//			listaReg = procesosService.consultaValidacionConsolidado(req.getGridParams(), idEmpresa, idCgaInsumo).getConsolidVsCartVal();
//		}else if (req.getCveGrid().equals("CalReq1Grid")) {
//			String rc = extParamList.get(0);
//			Long idProceso = Long.parseLong(extParamList.get(1));
//			
//			listaReg = calculadorasService.consultaRc0104Req1(req.getGridParams(), idEmpresa, idProceso, rc).getRc0104Req1();
//		}else if (req.getCveGrid().equals("CalReq2Grid")) {
//			String rc = extParamList.get(0);
//			Long idProceso = Long.parseLong(extParamList.get(1));
//			
//			listaReg = calculadorasService.consultaRc0104Req2(req.getGridParams(), idEmpresa, idProceso, rc).getRc0104Req2();
//		}else if (req.getCveGrid().equals("CalReq3Grid")) {
//			String rc = extParamList.get(0);
//			Long idProceso = Long.parseLong(extParamList.get(1));
//			
//			listaReg = calculadorasService.consultaRc0104Req3(req.getGridParams(), idEmpresa, idProceso, rc).getRc0104Req3();
//		}else if (req.getCveGrid().equals("CalReq4Grid")) {
//			String rc = extParamList.get(0);
//			Long idProceso = Long.parseLong(extParamList.get(1));
//			
//			listaReg = calculadorasService.consultaRc0104Req4(req.getGridParams(), idEmpresa, idProceso, rc).getRc0104Req4();
//		}else if (req.getCveGrid().equals("CalReq5Grid")) {
//			String rc = extParamList.get(0);
//			Long idProceso = Long.parseLong(extParamList.get(1));
//			
//			listaReg = calculadorasService.consultaRc0104Req5(req.getGridParams(), idEmpresa, idProceso, rc).getRc0104Req5();
//		}else if (req.getCveGrid().equals("CalReq6Grid")) {
//			String rc = extParamList.get(0);
//			Long idProceso = Long.parseLong(extParamList.get(1));
//			
//			listaReg = calculadorasService.consultaRc0104Req6(req.getGridParams(), idEmpresa, idProceso, rc).getRc0104Req6();
//		}else if (req.getCveGrid().equals("CalRc05Grid")) {
//			Long idProceso = Long.parseLong(extParamList.get(0));
//			
//			listaReg = calculadorasService.consultaCalRc05(req.getGridParams(), idEmpresa, idProceso).getCalRc05();
//		}else if (req.getCveGrid().equals("CalRc06Grid")) {
//			Long idProceso = Long.parseLong(extParamList.get(0));
//			
//			listaReg = calculadorasService.consultaCalRc06(req.getGridParams(), idProceso).getCalRc06();
//		}else if (req.getCveGrid().equals("CalRc07Grid")) {
//			Long idProceso = Long.parseLong(extParamList.get(0));
//			
//			listaReg = calculadorasService.consultaCalRc07(req.getGridParams(), idProceso).getCalRc07();
//		}else if (req.getCveGrid().equals("CalRc08Grid")) {
//			Long idProceso = Long.parseLong(extParamList.get(0));
//			
//			listaReg = calculadorasService.consultaCalRc08(req.getGridParams(), idProceso).getCalRc08();
//		}else if (req.getCveGrid().equals("CalRc12CompGrid")) {
//			Long idProceso = Long.parseLong(extParamList.get(0));
//			
//			listaReg = calculadorasService.consultaCalRc12Comp(req.getGridParams(), idEmpresa, idProceso).getCalRc12Comp();
//		}else if (req.getCveGrid().equals("CalRc12IngrGrid")) {
//			Long idProceso = Long.parseLong(extParamList.get(0));
//			
//			listaReg = calculadorasService.consultaCalRc12Ingr(req.getGridParams(), idEmpresa, idProceso).getCalRc12Ingr();
//		}else if (req.getCveGrid().equals("CalRc12RiesGrid")) {
//			Long idProceso = Long.parseLong(extParamList.get(0));
//			listaReg = calculadorasService.consultaCalRc12(req.getGridParams(), idEmpresa, idProceso).getCalRc12Ries();
//		}else if (req.getCveGrid().equals("ValidadorGrid")) {
//			Long idProceso = Long.parseLong(extParamList.get(0));
//			
//			listaReg = this.procesosService.consultaValidador(req.getGridParams(), idProceso).getValidador();
//		}else if (req.getCveGrid().equals("FormularioGrid")) {
//			String rc = extParamList.get(0);
//			Long idProceso = Long.parseLong(extParamList.get(1));
//			
//			listaReg = this.procesosService.consultarFormularioData(req.getGridParams(), idProceso, idEmpresa, rc).getFormDataList();
//		}else {
//			throw new Exception("No se ha configurado la exportacion de este Grid");
//		}
		
		if(!listaReg.isEmpty()){
			//Iteramos registros para obtener columnas y valores.
			for(Object obj : listaReg){
				propiedades = obj.getClass().getDeclaredFields();
				List<ColumnaExport> columnas = new ArrayList<ColumnaExport>();
				List<String> valores = new ArrayList<String>();
				
				for (int i = 0; i < propiedades.length; i++){
					Field field = propiedades[i];
					field.setAccessible(true);
					
					if(!field.getName().equalsIgnoreCase("serialVersionUID") && !field.getName().equalsIgnoreCase("recordCount")){
						ColumnaExport columna = new ColumnaExport();
						columna.setTitulo(field.getName());
						columna.setCampo(field.getName());
						columna.setTipo(field.getType().getCanonicalName());
						
						//logger.debug("Nombre de Columna:" + field.getName());
						//logger.debug("Tipo de Columna:" + field.getType().getCanonicalName());
						columnas.add(columna);
						
						//logger.debug("Valor de la columna: " + field.get(obj));
						if(field.getType().getCanonicalName().equals("java.util.Date")){
							valores.add(field.get(obj) == null ? "" : formatter.format(field.get(obj)));
						}else{
							valores.add(String.valueOf(field.get(obj) == null ? "" : field.get(obj)));	
						}		
					}
				}
				
				columnasList.add(columnas);
				valoresList.add(valores);
			}
			
			//Generamos el JRDataSource para el reporte.
			List<ColumnaExport> columnasExp = columnasList.get(0);
			String[] headers = new String[columnasExp.size()];
			
			for( int  i  = 0; i < columnasExp.size(); i++ ) {
	    		headers[i] = columnasExp.get(i).getCampo();
	    	}
			
			DRDataSource dataSource = new DRDataSource(headers);
			
			//Agregamos los valores al DS
			for(List<String> valores : valoresList){
				dataSource.add(valores.toArray());
			}
			
			//Seteamos las propiedades al objeto de respuesta
			mapa.setColumnas(columnasExp);
			mapa.setDataSource(dataSource);
		}else{
			ColumnaExport columna = new ColumnaExport();
			columna.setCampo("mensaje");
			columna.setTitulo("mensaje");
			columna.setTipo("String");
			
			DRDataSource dataSource = new DRDataSource(columna.getCampo());
			dataSource.add("No existen registros a exportar");
			
			List<ColumnaExport> columnaList = new ArrayList<ColumnaExport>();
			columnaList.add(columna);
			
			//Seteamos las propiedades al objeto de respuesta
			mapa.setColumnas(columnaList);
			mapa.setDataSource(dataSource);
		}
		
		return mapa;
	}
	
	/**
	 * Arma una Lista de parametros Extra usados en la Exportacion de un Grid.
	 * @param jsonStr la cadena JSON de parametros extras.
	 */
	private List<String> armaExtParams(String jsonStr){
		List<String> paramList = new ArrayList<String>();
		
		jsonStr = jsonStr.replace("{", "");
		jsonStr = jsonStr.replace("}", "");
		
		if(!jsonStr.trim().isEmpty()){
			String[] cadenaArray = jsonStr.split(",");
			
			for(int i=0; i < cadenaArray.length; i++ ){
				String str = cadenaArray[i];
				
				String[] strArray = str.split(":");
				paramList.add(strArray[1].trim().replace("\"", ""));
				
				logger.debug("propiedad: " + strArray[0]);
				logger.debug("valor: " + strArray[1]);
			}
		}
		
		return paramList;
	}
	
}
