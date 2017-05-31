/* 
 @autor Oscar Fabian Cobos Ortiz
 */
Ext
		.define(
				'gfi.bin.admctasweb.view.reportes.R29.FormPrincipalExtraccionReporteR29',
				{
					extend : 'Ext.Panel',
					alias : 'widget.formPrincipalExtraccionReporteR29',
					id : 'formPrincipalExtraccionReporteR29',
					layout : 'fit',
					items : [ {
						xtype : 'form',
						layout : "border",
						id : 'formPrincipalExtraccionReporteR29North',
						frame : false,
						items : [ {
							region : 'north',
							xtype : 'form',
							id : 'panelFormPrincipalExtraccionReporteR29North',
							height : 650,
							margins : '3 5 5 5', // 'top right button left'
							frame : false,
							items : [
									{
										xtype : 'fieldset',
										margin : '3 5 5 5', // 'top right button left'
										height : 85,
										id : 'fieldsetGeneraIntentoFormPrincipalExtraccionReporteR29North',
										title : 'Genera Intento',
										items : [ {
											xtype : 'form',
											frame : false,
											border : false,
											id : 'formFieldSetGeneraIntento',
											layout : 'column',
											items : [
													{
														width : 350,
														border : false,
														items : [ {
															xtype : 'combo',
															fieldLabel : '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Empresa</span>',
															width : 330,
															emptyText : 'Selecciona Empresa...',
															allowBlank : false,
															blankText : 'El campo Empresa es requerido.',
															queryMode : 'local',
															labelWidth : 80,
															id : 'cbEmpresaGeneraIntento',
															store : Ext
																	.create(
																			'Ext.data.Store',
																			{
																				fields : [
																						'value',
																						'label' ],
																				data : [
																						{
																							'value' : '1',
																							'label' : 'Interacciones Casa de Bolsa'
																						},
																						{
																							'value' : '2',
																							'label' : 'Banco Interacciones'
																						}

																				]
																			}),
															displayField : 'label',
															valueField : 'value',
															autoSelect : true,
															forceSelection : true,
															editable : false,
															listeners:{
							        			                change: function(field, newValue, oldValue){
							        			                	
							        			                	var comboPeriodoGeneraIntento = Ext.getCmp('dfPeriodoGeneraIntento');
							        			                	var storeComboPeriodoGeneraIntento = comboPeriodoGeneraIntento.getStore();
							        			                	storeComboPeriodoGeneraIntento.reload();
							        			                    
							        			                }
							        			             }
														} ]
													},
													{
														width : 350,
														border : false,
														items : [ 
														         
																	{
																		xtype : 'combo',
																		fieldLabel : '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Periodo</span>',
																		width : 330,
																		emptyText : 'Selecciona Periodo...',
																		allowBlank : false,
																		blankText : 'El campo Periodo es requerido.',
																		queryMode : 'local',
																		labelWidth : 80,
																		id : 'dfPeriodoGeneraIntento',
																		store : 'gfi.bin.admctasweb.store.reportes.R29.StorePeriodosReporteR29', 
																		displayField : 'cvePeriodo',
																		valueField : 'cvePeriodo',
																		autoSelect : true,
																		forceSelection : true,
																		editable : false
																	}]
													},
													{
														width : 350,
														border : false,
														items : [
																{
																	xtype : 'button',
																	text : 'Registra Intento',
																	handler : function() {

																		/*Realiza el envio de la informacion del intento*/

																		var idEmpresa = Ext.getCmp('cbEmpresaGeneraIntento').getValue();
																		var cvePeriodo =  Ext.getCmp('dfPeriodoGeneraIntento').getValue(); 
																			
																		if ((idEmpresa != null)	&& (cvePeriodo != null)) {

																			Ext.Ajax.request({
																						url : 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/generaIntento.htm',
																						loadMask : true,
																						scope : this,
																						success : function(response) {
																							
																							 var objResponse = Ext.JSON.decode(response.responseText);
																							 
																							 Ext.Msg.show({
										                                                        title: 'INFO',
										                                                        msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">La generacion de intento concluyo con el siguiente resultado: </br>' + objResponse.messages[1]+ '.</span>',
										                                                        buttons: Ext.Msg.OK,
										                                                        icon: Ext.Msg.INFO
										                                                    });
																						},
																						failure : function(response) {
																							
																							Ext.Msg.alert('ERROR',
																										   '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Fallo en al petición de registro de intento</span>');
																						},
																						params : {
																							idEmpresa : idEmpresa,
																							cvePeriodo : cvePeriodo
																						}
																					});

																		} else {

																			Ext.Msg
																					.alert(
																							'ERROR',
																							'<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Favor de ingresar los parametros correspondientes de Empresa y Periodo.</span>');

																		}
																	}
																},
																{
																	xtype : 'textfield',
																	id : 'hiddenTxtIdEmpresa',
																	hidden : true
																},
																{
																	xtype : 'textfield',
																	id : 'hiddenTxtCvePeriodo',
																	hidden : true
																},
																{
																	xtype : 'textfield',
																	id : 'hiddenTxtNumIntento',
																	hidden : true
																},
																{
																	xtype : 'textfield',
																	id : 'hiddenTxtIdEmpresaBitacoraExtraccion',
																	hidden : true
																},
																{
																	xtype : 'textfield',
																	id : 'hiddenTxtCvePeriodoBitacoraExtraccion',
																	hidden : true
																},
																{
																	xtype : 'textfield',
																	id : 'hiddenTxtNumIntentoBitacoraExtraccion',
																	hidden : true
																},{
																	xtype : 'textfield',
																	id : 'hiddenTxtNumOficioBitacoraExtraccion',
																	hidden : true
																},
																{
																	xtype : 'textfield',
																	id : 'hiddenTxtTipoOficioBitacoraExtraccion',
																	hidden : true
																},
																{
																	xtype : 'textfield',
																	id : 'hiddenTxtIdCotitularBitacoraExtraccion',
																	hidden : true
																},
																{
																	xtype : 'textfield',
																	id : 'hiddenTxtIdCuentaBitacoraExtraccion',
																	hidden : true
																},
																{
												                    xtype: 'FileDownloadReporteR29',
												                    id: 'descargaReporteR29'
												                },
																{
												                    xtype: 'FileDownloadReporteR29',
												                    id: 'descargarArchivoValidaciones'
												                }]
													} ]
										} ]
									},
									{
										xtype : 'fieldset',
										margin : '3 5 5 5', // 'top right button left'
										height : 280,
										id : 'fieldsetHistoricoIntentosFormPrincipalExtraccionReporteR29North',
										title : 'Historico Intentos',
										items : [
												{
													xtype : 'form',
													frame : false,
													border : false,
													id : 'formFieldSetHistoricoIntentos',
													layout : 'column',
													items : [
															{
																width : 350,
																border : false,
																items : [ {
																	xtype : 'combo',
																	fieldLabel : '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Empresa</span>',
																	width : 330,
																	emptyText : 'Selecciona Empresa...',
																	allowBlank : false,
																	blankText : 'El campo Empresa es requerido.',
																	queryMode : 'local',
																	labelWidth : 80,
																	id : 'cbEmpresaIntentosRealizadosForm',
																	store : Ext
																			.create(
																					'Ext.data.Store',
																					{
																						fields : [
																								'value',
																								'label' ],
																						data : [
																								{
																									'value' : '1',
																									'label' : 'Interacciones Casa de Bolsa'
																								},
																								{
																									'value' : '2',
																									'label' : 'Banco Interacciones'
																								} ]
																					}),
																	displayField : 'label',
																	valueField : 'value',
																	autoSelect : true,
																	forceSelection : true,
																	editable : false,
																	listeners:{
									        			                change: function(field, newValue, oldValue){
									        			                	
									        			                	var comboPeriodoGeneraIntento = Ext.getCmp('dfPeriodoIntentosRealizadosForm');
									        			                	var storeComboPeriodoGeneraIntento = comboPeriodoGeneraIntento.getStore();
									        			                	storeComboPeriodoGeneraIntento.reload();
									        			                    
									        			                }
									        			             }
																} ]
															},
															{
																width : 350,
																border : false,
																items : [ 
																         
																			{
																				xtype : 'combo',
																				fieldLabel : '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Periodo</span>',
																				width : 330,
																				emptyText : 'Selecciona Periodo...',
																				allowBlank : false,
																				blankText : 'El campo Periodo es requerido.',
																				queryMode : 'local',
																				labelWidth : 80,
																				id : 'dfPeriodoIntentosRealizadosForm',
																				store : 'gfi.bin.admctasweb.store.reportes.R29.StorePeriodosReporteR29Intentos', 
																				displayField : 'cvePeriodo',
																				valueField : 'cvePeriodo',
																				autoSelect : true,
																				forceSelection : true,
																				editable : false
																			}
																          
																          
//																          {
//																	xtype : 'datefield',
//																	id : 'dfPeriodoIntentosRealizadosForm',
//																	fieldLabel : '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Periodo</span>',
//																	labelWidth : 105,
//																	width : 250,
//																	editable : false,
//																	maxValue : new Date(),
//																	emptyText : 'Seleccione Periodo...',
//																	allowBlank : false,
//																	blankText : 'El campo periodo es requerido.',
//																	format : 'Y/m'
//																} 
																          
																          
																          
																          ]
															},
															{
																width : 350,
																border : false,
																items : [ {
																	xtype : 'button',
																	text : 'Busca Historicos',
																	handler : function() {

																		/*Realiza el envio de los parametros de busqueda para extraer los intentos realizados*/

																		var idEmpresa  = Ext.getCmp('cbEmpresaIntentosRealizadosForm').getValue();
																		var cvePeriodo = Ext.getCmp('dfPeriodoIntentosRealizadosForm').getValue(); 
																		
																		if ((idEmpresa != null) && (cvePeriodo != null)) {

																			var varGridIntentosRealizados = Ext.getCmp('intentosRealizadosGrid');
																			var storeGridIntentosRealizados = varGridIntentosRealizados.getStore();

																			storeGridIntentosRealizados
																					.load({
																						callback : function(rs,opts,success) {

																							if (success && rs.length > 0) {
//																								Ext.Msg.show({
//																											title : 'REGISTROS ENCONTRADOS',
//																											msg : '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Búsqueda exitosa, consulte la tabla con los resultados.</span>',
//																											buttons : Ext.Msg.OK,
//																											icon : Ext.Msg.INFO
//																										});
																							} else {
																								if (rs != null) {

																									if (rs.length === 0) {
																										Ext.Msg
																												.show({
																													title : 'REGISTROS NO ENCONTRADOS',
																													msg : '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">No se encontraron registros de acuerdo a los criterios de búsqueda, intentelo nuevamente.</span>',
																													buttons : Ext.Msg.OK,
																													icon : Ext.Msg.INFO
																												});
																										
																										/*Limpiamos Grid Registros*/
																										
																										var grid =Ext.getCmp('bitacoraExtraccionGrid');
																										grid.getStore().removeAll();
																										
																										/*Desabilitamos botones*/
																										
																										Ext.getCmp('idBtnValidaInformacion').disable();
																										Ext.getCmp('idBtnCierraPeriodo').disable();
																										Ext.getCmp('idBtnDescargaReporte').disable();
																										Ext.getCmp('idBtnExtraeArchivoValidaciones').disable();
																										
																										
																										
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

																		} else {

																			Ext.Msg
																					.alert(
																							'ERROR',
																							'<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Favor de ingresar los parametros correspondientes de Empresa y Periodo.</span>');

																		}
																	}
																} ]
															} ]
												},
												{
													xtype : 'form',
													frame : false,
													border : false,
													id : 'formFieldSetHistoricoIntentosGrid',
													layout : 'fit',
													items : [ {

														xtype : 'intentosRealizadosGrid'

													} ],
													dockedItems : {
														xtype : 'toolbar',
														dock : 'bottom',
														items : [
																'->',
																'-',
																'-',
																{
																	text : '<span style=\"color:#190707;font-weight:bold;font-size:10px;\">Extracción</span>',
																	id: 'idBtnExtraccion',
																	iconCls : 'icon-limpiar',
																	action : 'integraInformacion',
																	disabled : true
																}, '-', '-', ]
													}
												} ]
									},
									{
										xtype : 'fieldset',
										margin : '3 5 5 5', // 'top right button left'
										height : 260,
										id : 'fieldsetBitacoraExtraccionFormPrincipalExtraccionReporteR29North',
										title : 'Bitacora Extracción',
										items : [ {
											xtype : 'form',
											frame : false,
											border : false,
											id : 'formFieldSetBitacoraExtraccionGrid',
											layout : 'fit',
											items : [ {

												xtype : 'bitacoraExtraccionGrid'

											} ],
											dockedItems : {
												xtype : 'toolbar',
												dock : 'bottom',
												items : [{
															text : '<span style=\"color:#190707;font-weight:bold;font-size:10px;\">Descarga Registros Tabla</span>',
															iconCls : 'icon-limpiar',
															action : 'descargaRegistrosTabla',
															id: 'idBtnDescargaRegistrosTabla',
															disabled : true
														},
														'->',
														'-',
														'-',
														{
															text : '<span style=\"color:#190707;font-weight:bold;font-size:10px;\">Descarga Archivo Validaciones</span>',
															iconCls : 'icon-limpiar',
															action : 'descargarArchivoValidaciones',
															id: 'idBtnExtraeArchivoValidaciones',
															disabled : true
														},
														
														'-',
														'-',
														{
															text : '<span style=\"color:#190707;font-weight:bold;font-size:10px;\">Valida Información Registros</span>',
															iconCls : 'icon-limpiar',
															action : 'validaInformacion',
															id: 'idBtnValidaInformacion',
															disabled : true
														},
														
														'-',
														'-',
														
														{
															text : '<span style=\"color:#190707;font-weight:bold;font-size:10px;\">Cierra Periodo</span>',
															iconCls : 'icon-limpiar',
															action : 'cierraPeriodo',
															id: 'idBtnCierraPeriodo',
															disabled : true
														},
														'-',
														'-',
														{
															text : '<span style=\"color:#190707;font-weight:bold;font-size:10px;\">Descarga Reporte R29</span>',
															iconCls : 'icon-limpiar',
															action : 'descargaReporteR29',
															id: 'idBtnDescargaReporte',
															disabled : true
														} ]
											}
										} ]
							   }]
						} ]
					} ]
				});
