Ext.define('gfi.icb.controller.MyController', {
    extend: 'Ext.app.Controller',
    
views:  ['gfi.bin.admctasweb.view.reportes.R29.FormPrincipalExtraccionReporteR29',
         'gfi.bin.admctasweb.view.reportes.R29.IntentosRealizadosGrid',
         'gfi.bin.admctasweb.view.reportes.R29.BitacoraExtraccionGrid',
         'gfi.bin.admctasweb.view.reportes.R29.FileDownloadReporteR29'],
stores: ['gfi.bin.admctasweb.store.reportes.R29.StoreIntentosRealizadosGrid',
         'gfi.bin.admctasweb.store.reportes.R29.StoreBitacoraExtraccionGrid',
          'gfi.bin.admctasweb.store.reportes.R29.StorePeriodos',
          'gfi.bin.admctasweb.store.reportes.R29.StorePeriodosReporteR29',
          'gfi.bin.admctasweb.store.reportes.R29.StorePeriodosReporteR29Intentos'],
models: ['gfi.bin.admctasweb.model.reportes.R29.Intento',
         'gfi.bin.admctasweb.model.reportes.R29.Bitacora',
         'gfi.bin.admctasweb.model.reportes.R29.Periodo'],

refs: [{
	ref: 'formPrincipalExtraccionReporteR29',
	selector: 'formPrincipalExtraccionReporteR29'
	},{
	ref: 'intentosRealizadosGrid',
	selector: 'intentosRealizadosGrid'
	},{
	ref: 'bitacoraExtraccionGrid',
	selector: 'bitacoraExtraccionGrid'
}],
  

init: function () {

console.log('My Controller ***** Aseguramiento - Controller Inicializado');

	this.control({
	  'formPrincipalExtraccionReporteR29 button[action=validaInformacion]': {
	      click: this.validaInformacion
	  },
	  'formPrincipalExtraccionReporteR29 button[action=integraInformacion]': {
	      click: this.integraInformacion
	  },
	  'formPrincipalExtraccionReporteR29 button[action=cierraPeriodo]': {
	      click: this.cierraPeriodo
	  },
	  'formPrincipalExtraccionReporteR29 button[action=descargarArchivoValidaciones]': {
	      click: this.descargarArchivoValidaciones
	  },
	  'formPrincipalExtraccionReporteR29 button[action=descargaReporteR29]': {
	      click: this.descargaReporteR29
	  },
	  'formPrincipalExtraccionReporteR29 button[action=descargaRegistrosTabla]': {
	      click: this.descargaRegistrosTabla
	  },
	  'bitacoraExtraccionGrid dataview': {
	  	itemclick: this.cargaDatosCamposHidden
	  },
	  'intentosRealizadosGrid dataview': {
	  	itemclick: this.muestraBitacora
	  },
	});
},

descargarArchivoValidaciones : function(){

var gridIntentosRealizados = this.getIntentosRealizadosGrid();
var record = gridIntentosRealizados.getSelectionModel().getSelection();

if(record != 0){
	
	var idEmpresa  = record[0].get('idEmpresa');
 	var cvePeriodo = record[0].get('cvePeriodo');
 	var numIntento = record[0].get('numIntento');
 	var idGrupo    = record[0].get('idGrupo');
 	
 /*Pregunta de Confirmación de Descarga*/
	
	Ext.Msg.show({
      title: 'Advertencia',
      msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;\">Se procederá a la descarga del archivo ReporteR29.<br>¿Desea continuar con el proceso?</span>',
      width: 400,
      closable: false,
      buttons: Ext.Msg.YESNO,
      buttonText:
              {
                  yes: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Si</span>',
                  no: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">No</span>'
              },
      multiline: false,
      fn: function (btnText) {
          if (btnText === "yes") {
          	
          	/*Extraemos parametros que seran enviados para la descarga del reporte*/
          	
          	Ext.getBody().mask('Cargando...');
              Ext.Msg.show({
                  title: 'Informaci&oacute;n',
                  msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">El archivo esta siendo descargado en su equipo, esta acción puede tardar unos minutos.</span>',
                  buttons: Ext.Msg.OK,
                  icon: Ext.MessageBox.INFO
              });
              var downloader = Ext.getCmp('descargaReporteR29');
              
              
              
              downloader.load({
                      url: 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/generaArchivoValidaciones.htm',
                      success: function (response)
                      {
                      	

                      },
                      failure: function (response)
                      {
                          Ext.Msg.alert('ERROR', '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Fallo en al peticion de descarga de archivo, consulte al administrador</span>' + response);
                      },
                      params:
                              {
                              	idEmpresa : idEmpresa,
                              	cvePeriodo : cvePeriodo,
                              	numIntento : numIntento,
                              	idGrupo : idGrupo
                              }
                          
                      });
          	

          }
          else if (btnText === "no") {
              /*Cierra pantalla de aviso*/
          }
      },
      icon: Ext.Msg.QUESTION
  });
	
	
}else{
	
	Ext.Msg.alert('ERROR','<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Favor de seleccionar el periodo de la tabla REGISTRO DE INTENTOS REALIZADOS.</span>');
	        	
}

},


descargaRegistrosTabla : function(){
	
	var gridIntentosRealizados = this.getIntentosRealizadosGrid();
	  var record = gridIntentosRealizados.getSelectionModel().getSelection();
	  
	  if(record != 0){
	  	
	  	   var idEmpresa  = record[0].get('idEmpresa');
	     	var cvePeriodo = record[0].get('cvePeriodo');
	     	var numIntento = record[0].get('numIntento');
	     	var nombreReporte = 'INFORMACION_REGISTROS.txt';
	     	
	     /*Pregunta de Confirmación de Descarga*/
	     		
	     		Ext.Msg.show({
	              title: 'Advertencia',
	              msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;\">Se procederá a la descarga de la información de la tabla Registros.<br>¿Desea continuar con el proceso?</span>',
	              width: 400,
	              closable: false,
	              buttons: Ext.Msg.YESNO,
	              buttonText:
	                      {
	                          yes: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Si</span>',
	                          no: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">No</span>'
	                      },
	              multiline: false,
	              fn: function (btnText) {
	                  if (btnText === "yes") {
	                  	
	                  	/*Extraemos parametros que seran enviados para la descarga del reporte*/
	                  	
	                  	Ext.getBody().mask('Cargando...');
	                      Ext.Msg.show({
	                          title: 'Informaci&oacute;n',
	                          msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">El archivo esta siendo descargado en su equipo, esta acción puede tardar unos minutos.</span>',
	                          buttons: Ext.Msg.OK,
	                          icon: Ext.MessageBox.INFO
	                      });
	                      var downloader = Ext.getCmp('descargaReporteR29');
	                      downloader.load({
	                              url: 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/generaArchivo.htm',
	                              success: function (response)
	                              {
	                              	

	                              },
	                              failure: function (response)
	                              {
	                                  Ext.Msg.alert('ERROR', '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Fallo en al peticion de descarga de archivo, consulte al administrador</span>' + response);
	                              },
	                              params:
	                                      {
			                                	idEmpresa : idEmpresa,
			                                	cvePeriodo : cvePeriodo,
			                                	numIntento : numIntento,
			                                	nombreReporte : nombreReporte
	                                      }
	                                  
	                              });
	                  	

	                  }
	                  else if (btnText === "no") {
	                      /*Cierra pantalla de aviso*/
	                  }
	              },
	              icon: Ext.Msg.QUESTION
	          });
	     		

	  	
	  }else{
	  	
	  	Ext.Msg.alert('ERROR','<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Favor de seleccionar el periodo a descargar de la tabla REGISTRO DE INTENTOS REALIZADOS.</span>');
	  	        	
	  }  
	
	
	
	
},


descargaReporteR29 : function(){

	var gridIntentosRealizados = this.getIntentosRealizadosGrid();
  var record = gridIntentosRealizados.getSelectionModel().getSelection();
  
  if(record != 0){
  	
  	var idEmpresa  = record[0].get('idEmpresa');
     	var cvePeriodo = record[0].get('cvePeriodo');
     	var numIntento = record[0].get('numIntento');
     	var nombreReporte = 'SITI_R29.txt';
     	
     /*Pregunta de Confirmación de Descarga*/
     	
//     	var sitPeriodo  = record[0].get('sitPeriodo');
//     	if(sitPeriodo == 'CE'){
     		
     		Ext.Msg.show({
              title: 'Advertencia',
              msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;\">Se procederá a la descarga del archivo ReporteR29.<br>¿Desea continuar con el proceso?</span>',
              width: 400,
              closable: false,
              buttons: Ext.Msg.YESNO,
              buttonText:
                      {
                          yes: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Si</span>',
                          no: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">No</span>'
                      },
              multiline: false,
              fn: function (btnText) {
                  if (btnText === "yes") {
                  	
                  	/*Extraemos parametros que seran enviados para la descarga del reporte*/
                  	
                  	Ext.getBody().mask('Cargando...');
                      Ext.Msg.show({
                          title: 'Informaci&oacute;n',
                          msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">El archivo esta siendo descargado en su equipo, esta acción puede tardar unos minutos.</span>',
                          buttons: Ext.Msg.OK,
                          icon: Ext.MessageBox.INFO
                      });
                      var downloader = Ext.getCmp('descargaReporteR29');
                      downloader.load({
                              url: 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/generaArchivo.htm',
                              success: function (response)
                              {
                              	

                              },
                              failure: function (response)
                              {
                                  Ext.Msg.alert('ERROR', '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Fallo en al peticion de descarga de archivo, consulte al administrador</span>' + response);
                              },
                              params:
                                      {
		                                	idEmpresa : idEmpresa,
		                                	cvePeriodo : cvePeriodo,
		                                	numIntento : numIntento,
		                                	nombreReporte: nombreReporte,
                                      }
                                  
                              });
                  	

                  }
                  else if (btnText === "no") {
                      /*Cierra pantalla de aviso*/
                  }
              },
              icon: Ext.Msg.QUESTION
          });
     		
//     	}else{
//     		
//     		Ext.Msg.alert('INFO','<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">El periodo seleccionado no puede ser descargado, hasta que sea cerrado.</span>');
//     		
//     	}
  	
  }else{
  	
  	Ext.Msg.alert('ERROR','<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Favor de seleccionar el periodo a descargar de la tabla REGISTRO DE INTENTOS REALIZADOS.</span>');
  	        	
  }  

},


cierraPeriodo : function(){


var gridIntentosRealizados = this.getIntentosRealizadosGrid();
var record = gridIntentosRealizados.getSelectionModel().getSelection();

if(record != 0){
	
	 var idEmpresa  = record[0].get('idEmpresa');
   var cvePeriodo = record[0].get('cvePeriodo');
   var numIntento = record[0].get('numIntento');
   
   
  Ext.Msg.show({
      title: 'Advertencia',
      msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;\">Se procederá al cierre del periodo e intento del registro seleccionado.<br>¿Desea continuar con el proceso?</span>',
      width: 400,
      closable: false,
      buttons: Ext.Msg.YESNO,
      buttonText:
              {
                  yes: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Si</span>',
                  no: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">No</span>'
              },
      multiline: false,
      fn: function (btnText) {
          if (btnText === "yes") {
          	
          	
          	Ext.Ajax.request({
					url : 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/cierraPeriodo.htm',
					loadMask : true,
					scope : this,
					success : function(response) {
				     
					var objResponse = Ext.JSON.decode(response.responseText);
						 
						 Ext.Msg.show({
                        title: 'INFO',
                        msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">El proceso de cierre del registro concluyo con el siguiente resultado: </br>' + objResponse.messages[1]+ '.</span>',
                        buttons: Ext.Msg.OK,
                        icon: Ext.Msg.INFO,
                        height : 150,
                        width : 600
                    });

					},
					failure : function(response) {
						Ext.Msg
								.alert(
										'ERROR',
										'<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Fallo en al petición de cierre de periodo.</span>'
												+ response.responseText);
					},
					params : {
						idEmpresa : idEmpresa,
						cvePeriodo : cvePeriodo,
						numIntento : numIntento						
					}
				});
          	

          	

          }
          else if (btnText === "no") {
              /*Cierra pantalla de aviso*/

          }
      },
      icon: Ext.Msg.QUESTION
  });
	
}else{
	
	Ext.Msg.alert('ERROR','<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Favor de seleccionar el periodo a cerrar de la tabla REGISTRO DE INTENTOS REALIZADOS.</span>');
	        	
}

},


/*Funcion que carga los campos hidden para la actualizacion del campo Caracteristicas Cotitular del registro
* que se haya seleccionado de la tabla Registros*/

cargaDatosCamposHidden : function(){    	

var gridBitacoraExtraccion = this.getBitacoraExtraccionGrid();
var record = gridBitacoraExtraccion.getSelectionModel().getSelection();

var idEmpresa  = record[0].get('idEmpresa');
var cvePeriodo = record[0].get('cvePeriodo');
var numIntento = record[0].get('numIntento');

var noOficio  = record[0].get('numOficio');
var tipoOficio = record[0].get('tipoOficio');
var idCotitular = record[0].get('idCotitular');
var idCuenta = record[0].get('idCuenta');        

/*Seteamos los campos hidden que seran enviados cuando se quiera actualizar el campo Caracteristicas Cotitular*/
Ext.getCmp('hiddenTxtIdEmpresaBitacoraExtraccion').setValue(idEmpresa);
Ext.getCmp('hiddenTxtCvePeriodoBitacoraExtraccion').setValue(cvePeriodo);
Ext.getCmp('hiddenTxtNumIntentoBitacoraExtraccion').setValue(numIntento);

Ext.getCmp('hiddenTxtNumOficioBitacoraExtraccion').setValue(noOficio);
Ext.getCmp('hiddenTxtTipoOficioBitacoraExtraccion').setValue(tipoOficio);
Ext.getCmp('hiddenTxtIdCotitularBitacoraExtraccion').setValue(idCotitular);
Ext.getCmp('hiddenTxtIdCuentaBitacoraExtraccion').setValue(idCuenta);

},


validaInformacion : function(){


var gridIntentosRealizados = this.getIntentosRealizadosGrid();
var record = gridIntentosRealizados.getSelectionModel().getSelection();

if(record != 0){
	
	 var idEmpresa  = record[0].get('idEmpresa');
   var cvePeriodo = record[0].get('cvePeriodo');
   var numIntento = record[0].get('numIntento');
   var bCommit    = 'V'; 
   
   /*Hacemos extraccion del grid Intentos realizados para su posterior recarga*/
	 
	 var gridIntentosRealizados = this.getIntentosRealizadosGrid();
	 var storeIntentosRealizadosGrid = gridIntentosRealizados.getStore();
   
  Ext.Msg.show({
      title: 'Advertencia',
      msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;\">Se procederá a la validación de los registros del periodo e intento seleccionado.<br>¿Desea continuar con el proceso?</span>',
      width: 400,
      closable: false,
      buttons: Ext.Msg.YESNO,
      buttonText:
              {
                  yes: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Si</span>',
                  no: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">No</span>'
              },
      multiline: false,
      fn: function (btnText) {
          if (btnText === "yes") {
          	
				Ext.Ajax.request({
					url : 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/validaInformacion.htm',
					loadMask : true,
					scope : this,
					success : function(response) {
						
						var objResponse = Ext.JSON.decode(response.responseText);
						 
						 Ext.Msg.show({
                         title: 'INFO',
                         msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">La validación de los registros del periodo seleccionado concluyo con exito </br> descargue el archivo de validación para ver el resultado.</span>',
                         buttons: Ext.Msg.OK,
                         icon: Ext.Msg.INFO
                     });
						 
						 storeIntentosRealizadosGrid.reload();
						 
					},
					failure : function(response) {
						Ext.Msg
								.alert(
										'ERROR',
										'<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Fallo en al petición de registro de intento</span>'
												+ response.responseText);
					},
					params : {
						idEmpresa : idEmpresa,
						cvePeriodo : cvePeriodo,
						numIntento : numIntento,
						bCommit : bCommit								
					}
				});
          	
          	

          }
          else if (btnText === "no") {
              /*Cierra pantalla de aviso*/

          }
      },
      icon: Ext.Msg.QUESTION
  });
	
}else{
	
	Ext.Msg.alert('ERROR','<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Favor de seleccionar el periodo a validar de la tabla REGISTROS DE INTENTOS SELECCIONADOS.</span>');
	        	
}

},




/*Funcion que integra la informacion en la tabla CNBV_BITACORA*/
integraInformacion: function () {

var gridIntentosRealizados = this.getIntentosRealizadosGrid();
var record = gridIntentosRealizados.getSelectionModel().getSelection();

if(record != 0){

	var idEmpresa  = record[0].get('idEmpresa');
	var cvePeriodo = record[0].get('cvePeriodo');
	var fechaInicial = Ext.Date.format(record[0].get('fechaInicial'), 'd/m/Y');
	var fechaFinal =   Ext.Date.format(record[0].get('fechaFinal'), 'd/m/Y');
	var numIntento = record[0].get('numIntento');
	
	
	Ext.Msg.show({
      title: 'Advertencia',
      msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;\">Se procederá a la extracción del registro seleccionado.<br>¿Desea continuar con el proceso?</span>',
      width: 400,
      closable: false,
      buttons: Ext.Msg.YESNO,
      buttonText:
              {
                  yes: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Si</span>',
                  no: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">No</span>'
              },
      multiline: false,
      fn: function (btnText) {
          if (btnText === "yes") {
          	
          	
				Ext.Ajax.request({
					url : 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/integraInformacion.htm',
					loadMask : true,
					scope : this,
					success : function(response) {
						
						var objResponse = Ext.JSON.decode(response.responseText);
						 
						 Ext.Msg.show({
                         title: 'INFO',
                         msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">La extracción del registro concluyo con el siguiente resultado: </br>' + objResponse.messages[1]+ '.</span>',
                         buttons: Ext.Msg.OK,
                         icon: Ext.Msg.INFO
                     });

					},
					failure : function(response) {
						Ext.Msg
								.alert(
										'ERROR',
										'<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Fallo en al petición de extracción de registro</span>');
					},
					params : {
						idEmpresa    : idEmpresa,
						cvePeriodo   : cvePeriodo,
						fechaInicial : fechaInicial,
						fechaFinal   : fechaFinal,
						numIntento   : numIntento
					}
				});
          	
          	

          }
          else if (btnText === "no") {
              /*Cierra pantalla de aviso*/

          }
      },
      icon: Ext.Msg.QUESTION
  });
	
	
}else{
	
	Ext.Msg.alert('ERROR','<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Favor de seleccionar el registro a extraer de la tabla REGISTRO DE INTENTOS REALIZADOS.</span>');
}

},

muestraBitacora: function(){

var gridIntentosRealizados = this.getIntentosRealizadosGrid();
var record = gridIntentosRealizados.getSelectionModel().getSelection();

if(record != 0){
	
	/*Extraemos valores correspondientes para pasarlos a los campos hidden
	 * que seran los que se manden por el store*/
	
	var idEmpresa  = record[0].get('idEmpresa');
	var cvePeriodo = record[0].get('cvePeriodo');
	var numIntento = record[0].get('numIntento');
	
	Ext.getCmp('hiddenTxtIdEmpresa').setValue(idEmpresa);
	Ext.getCmp('hiddenTxtCvePeriodo').setValue(cvePeriodo);
	Ext.getCmp('hiddenTxtNumIntento').setValue(numIntento);
	
	/*Ejecutamos store de Bitacora Extraccion para buscar registros en la tabla CNBV_BITACORA de acuerdo a los parametros hidden*/
	
	var gridBitacoraExtraccion = this.getBitacoraExtraccionGrid();
	var storeBitacoraExtraccion = gridBitacoraExtraccion.getStore();
	
	storeBitacoraExtraccion.load({
		callback : function(rs, opts, success) {

			if (success && rs.length > 0) {
				
//				Ext.Msg.show({
//							title : 'REGISTROS ENCONTRADOS',
//							msg : '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Búsqueda exitosa, consulte la tabla con los resultados.</span>',
//							buttons : Ext.Msg.OK,
//							icon : Ext.Msg.INFO
//						});
				
				Ext.getCmp('idBtnExtraccion').disable();
				Ext.getCmp('idBtnValidaInformacion').enable();
				Ext.getCmp('idBtnCierraPeriodo').enable();
				Ext.getCmp('idBtnDescargaReporte').enable();
				Ext.getCmp('idBtnExtraeArchivoValidaciones').enable();
				Ext.getCmp('idBtnDescargaRegistrosTabla').enable();
				
				
			} else {
				if (rs != null) {

					if (rs.length === 0) {
						Ext.Msg.show({
									title : 'REGISTROS NO ENCONTRADOS',
									msg : '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">No se encontraron resultados de acuerdo al registro seleccionado, proceda a la extracción.</span>',
									buttons : Ext.Msg.OK,
									icon : Ext.Msg.INFO
								});
						
						Ext.getCmp('idBtnExtraccion').enable();
						Ext.getCmp('idBtnValidaInformacion').disable();
						Ext.getCmp('idBtnCierraPeriodo').disable();
						Ext.getCmp('idBtnDescargaReporte').disable();
						Ext.getCmp('idBtnExtraeArchivoValidaciones').disable();
						Ext.getCmp('idBtnDescargaRegistrosTabla').disable();
						
					}

				} else {

					Ext.Msg.show({
								title : 'ERROR',
								msg : '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Error en el proceso de busqueda, consulte al administrador.</span>',
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.INFO
							});
				}
			}
		},
		scope : this
	});
}
}

    
});