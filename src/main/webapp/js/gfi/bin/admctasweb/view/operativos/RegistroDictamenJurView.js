Ext.define('gfi.bin.admctasweb.view.operativos.RegistroDictamenJurView', {
	extend : 'gfi.corp.component.form.BasicForm',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.operativos.registroDictamen.RequerimientoAutoridadPnl',
	            'gfi.bin.admctasweb.view.operativos.registroDictamen.TipoOperacionOcontratoPnl'],

	config : {
		header : 'Registro de Dictamen Juridico',
		formType: gfi.FormTypes.CATALOGO,
		baseUrl : 'operativos/regdictamenjuri/',
		secured : false,
		pack : 'stretch',
		grupo: 'admctasweb', 
		clave: 'dictamenJur',
		primaryKeys : [ 'numOficio', 'tipoOficio', 'numConsec' ],
		modelClass : 'gfi.bin.admctasweb.model.operativos.CnbvDictamen'
	},
	
	
	userActions: {
		borrar: {
			createButton: false,
			},
		imprimir: {
			createButton: true,
			buttonText: 'Visualizar',
			url:'reporte.htm',
			pack: 'center',
			statusConfig: [gfi.component.BasicForm.STATUS_ACTIVE] ,
			sync: true,
		},
		buscarRegFaltantes : {
			createButton: false,
			buttonText  : 'buscarRegFaltantes',
			url:'buscarRegFaltantes.htm',
			formStatus		: [],
			secured: false,
			sync: false,
		},
	},
	

	doImprimir : function (options)
	{
		var me = this;	
		
		modelo = me.getValues();	
		//Esta concatena la base url con la definida para la accion (/reportes/respuestasOficios/reporte.htm)
		var url = me.getUrl(me.status, options.action);
		
		var win = new Ext.Window({
			  title: 'Visualizador PDF',
			  modal : true,
			  maximizable: true,
			  width: 700,
			  height:  640,
			  plain:true,
			  html: Ext.String.format( '<iframe src="{0}?numOficio={1}&tipoOficio={2}&numConsec={3}" width="100%" height="100%" />', url,
					  encodeURIComponent(modelo['numOficio']), modelo['tipoOficio'], modelo['numConsec']),
					  						
			  listeners: {
				      render: function(comp) {
				                     
				          var mask = new Ext.LoadMask(comp.el, {msg: "Cargando Documento..."});
				          mask.show();
							if ( navigator.appName == "Netscape"){
								setTimeout(function (target) {
									target.destroy();
							     }, 1500, mask);
							 }                    
				 
				     }
			  }
				  
				  
			});
			win.show();	
		
	},
	
	completeImprimir : function (completed, options){
		var me = this;
		if(completed)
		{
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		}
		else
		{
			me.showErrorMessage(options.error || options.message);
		}					
	},
	
	//Acción para buscar los registros faltantes
	doBuscarRegFaltantes : function (options)
	{
		var me = this;
		modelo = me.getValues();
		options.model = me.getValues();
	    me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscarRegFaltantes);
	},
	
	completeBuscarRegFaltantes : function(completed, options) {
		console.log('archivoCnbv.completeBuscarRegFaltantes... ', completed, options);		
		var me = this;
		if(completed)
		{
			Ext.getCmp('respFaltantes').setValue("");
			var info = options.info;
			var respuestas = info.get('respFaltantes');
			console.log('respuestas faltantes ... ', respuestas);	
			Ext.getCmp('respFaltantes').setValue(respuestas);
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		}
		else
		{
			me.showErrorMessage(options.error || options.message);
		}	
		
		
	},
	
    
    doAceptar : function (options)
    {
       var me = this;
       modelo = me.getValues();
       
       var levParc =    Ext.getCmp('levantamtoParcialEmb').getValue();
       var  embParc=    Ext.getCmp('embargoParcialCta').getValue();
       

       
       if(levParc){
    	   if(modelo['montoLevParcialEmb'] == null || modelo['montoLevParcialEmb'] == '' ){
    			Ext.Msg.alert('AdmCtasWeb', 'El monto del levantamiento parcial no puede ir vacio');
    		   return;
    	   }else if(modelo['monedaLevParcialEmb']== null || modelo['monedaLevParcialEmb'] == '' ){
    		   Ext.Msg.alert('AdmCtasWeb', 'La moneda del levantamiento parcial no puede ir vacio');
    		   return;
    	   }
       }
       
       if(embParc){
    	   if(modelo['montoEmbargoParcial'] == null || modelo['montoEmbargoParcial'] == '' ){
    			Ext.Msg.alert('AdmCtasWeb', 'El monto del embargo parcial no puede ir vacio');
    		   return;
    	   }else if(modelo['monedaEmbargoParcial']== null || modelo['monedaEmbargoParcial'] == '' ){
    		   Ext.Msg.alert('AdmCtasWeb', 'La moneda del embargo parcial no puede ir vacia');
    		   return;
    	   }
       }
       
       //options.model = me.getValues();
       var completed = me.actions.aceptar.doAction.call(me, options);
       me.actions.aceptar.completeAction.call(me, completed, options);
    },
	
	
	
	buildItems : function() {
		var me = this;
						return [
								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'gfibuscadortrigger',
												id : 'numOficioDicJur',
												grupo : 'admctasweb',
												clave : 'oficios',
												fieldLabel : 'Número de Oficio:',
												emptyText : 'Seleccione un Oficio',
												name : 'numOficio',
												editable : false,
												linkProperty : 'numOficio',
												 
												controls : [
														{
															ref : 'tipoOficio',
															selector : '#tipoOficio2',
															linkProperty : 'tipoOficio'
														},
														{
															ref : 'numExped',
															selector : '#numExped',
															linkProperty : 'numExped'
														},
														{
															ref : 'numFolio',
															selector : '#numFolio',
															linkProperty : 'numFolio'
														},
														{
															ref : 'numRegistro',
															selector : '#numRegistro',
															linkProperty : 'numRegistro'
														},
														{
															ref : 'fhOficio',
															selector : '#fhOficio',
															linkProperty : 'fhOficio'
														},
														{
															ref : 'fhRecepcion',
															selector : '#fhRecepcion',
															linkProperty : 'fhRecepcion'
														},
														{
															ref : 'numDiasPzo',
															selector : '#numDiasPzo',
															linkProperty : 'numDiasPzo'
														},
														{
															ref : 'idEmpresa',
															selector : '#idEmpresa',
															linkProperty : 'idEmpresa'
														},{
															ref : 'descEmpresa',
															selector : '#descEmpresa',
															linkProperty : 'descEmpresa'
														}

												],
												
											   selectRecord: function (model, format){

						                 			var meb = this, result;              		
						                 			meb.setEventInfo();
							                		result = meb.fireEvent('onSelectRecord', model, meb.eventInfo);
							                		
							                		if (result) {
							                			meb.selectedRecord = model;
							                			meb.displayRecord(meb.getResultText(model, format), model, meb.eventInfo);
							                			meb.displayControls(model, meb.eventInfo);
							                		}
							                		me.executeAction('buscarRegFaltantes');
							                		return result;
											   },
												
												
												onTriggerClick : function() {
													var me = this;

													//var idEmpresa = Ext.getCmp('idEmpresa').getValue();

//													if (idEmpresa == null
//															|| idEmpresa == '') {
//														Ext.Msg.alert('AdmCtasWeb',
//																		'Seleccione una Empresa');
//													} else {
														me.setDefaultFilters([
//																		new Ext.util.Filter(
//																				{
//																					property : 'idEmpresa',
//																					operator : '=',
//																					value : idEmpresa
//																				}),
																		new Ext.util.Filter(
																				{
																					property : 'tipoCaso',
																					operator : '=',
																					value : 'PO'
																				}) ]);
														me.search();

													//}
												},
												labelWidth : 140,
												width : 380,
												formStatus : [gfi.component.BasicForm.STATUS_NEW,
																gfi.component.BasicForm.STATUS_UPDATE
																 ]
											}, ]
								},{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [ {
										xtype : 'textfield',
										fieldLabel : 'Empresa solicitante:',
										name : 'idEmpresa',
										id : 'idEmpresaDicJur',
										itemId : 'idEmpresa',
										labelWidth : 140,
										width : 250,
										formStatus : [
												]
									}, {
										xtype : 'splitter'
									}, {
										xtype : 'textfield',
										itemId : 'descEmpresa',
										id : 'descEmpresa',
										name : 'descEmpresa',
										width : 405,
										formStatus : [
														 ]
									} ]
								},
								{
									xtype : 'fieldcontainer',
									fieldLabel : 'Tipo de Oficio',
									allowBlank : false,
									collapsible : false,
									width : 380,
									
									labelWidth : 140,
									formStatus : [
													 ],
									items : [ {
										xtype : 'radiogroup',
										vertical : true,
										formStatus : [
														 ],
										name : 'groupOficios',
										id : 'groupOficiosDicJur',
										//itemId : 'groupOficios',
										items : [ {
											boxLabel : 'Judicial',
											itemId : 'tipoOficio2',
											name : 'tipoOficio',
											id : 'tipoOficio2',
											inputValue : 'JU'
										}, {
											boxLabel : 'Hacendario',
											name : 'tipoOficio',
											
											inputValue : 'HA'
										}, {
											boxLabel : 'Aseguramiento',
											name : 'tipoOficio',
											
											inputValue : 'AS'
										}, {
											boxLabel : 'PLD',
											name : 'tipoOficio',
										
											inputValue : 'PL'
										} ],
									} ],
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Número de Expediente:',
									name : 'numExped',
									id : 'numExped',
									itemId : 'numExped',
									labelWidth : 140,
									width : 250,
									formStatus : [
											]
								},										
								{
									xtype : 'textfield',
									fieldLabel : 'Número de Folio:',
									name : 'numFolio',
									id : 'numFolio',
									itemId : 'numFolio',
									labelWidth : 140,
									width : 250,
									formStatus : [
											]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Número de Registro:',
									name : 'numRegistro',
									id : 'numRegistro',
									itemId : 'numRegistro',
									labelWidth : 140,
									width : 250,
									formStatus : [
											 ]
								},
								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'datefield',
												fieldLabel : 'Fecha Oficio:',
												name : 'fhOficio',
												id : 'fhOficio',
												itemId : 'fhOficio',
												format : 'd/m/Y',
												labelWidth : 140,
												width : 250,
												formStatus : [
														]
											},
											{
												xtype : 'splitter'
											},
											{
												xtype : 'datefield',
												fieldLabel : 'Fecha Recepción:',
												name : 'fhRecepcion',
												id : 'fhRecepcion',
												itemId : 'fhRecepcion',
												format : 'd/m/Y',
												labelWidth : 100,
												width : 210,
												formStatus : [
														]
											},
											{
												xtype : 'splitter',
												width : 100,
											},
											{
												xtype : 'textfield',
												fieldLabel : 'Dias Plazo:',
												name : 'numDiasPzo',
												id : 'numDiasPzo',
												itemId : 'numDiasPzo',
												width : 95,
												labelWidth : 60,
												formStatus : [
														]
											},
											
											]
								},{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'gfibuscadortrigger',
												id : 'respuestas',
												grupo : 'admctasweb',
												clave : 'respuestas',
												fieldLabel : 'Consecutivo:',
												name : 'numConsec',
												linkProperty : 'numConsec',
												editable : false,
												controls : [ {
													ref : 'nombreTitular',
													selector : '#nombreTitular',
													linkProperty : 'nombreTitular'
												} ],
												onTriggerClick : function() {
													var me = this;
																						
													var numOficio = Ext.getCmp('numOficioDicJur').getValue();
													if (  (numOficio == null || numOficio == '') ){
														Ext.Msg.alert('AdmCtasWeb', 'Debe seleccionar un oficio');
													} else {
														var tipoOficio=  Ext.getCmp('groupOficiosDicJur').items.get(0).getGroupValue();
														
													//	console.log('tipoOficio- '+ tipoOficio + ' idEmpresa-' + idEmpresa + ' numOficio-' + numOficio );
														me.setDefaultFilters([
																		new Ext.util.Filter({
																					property : 'numOficio',
																					operator : '=',
																					value : numOficio
																				}),
																				
																		new Ext.util.Filter({
																							property : 'tipoOficio',
																							operator : '=',
																							value : tipoOficio
																						}),
																						
																	    new Ext.util.Filter({
																							property : 'tipoCaso',
																							operator : '=',
																							value : 'PO'
																						})
																		]);
													me.search();

													}
												},
												labelWidth : 140,
												width : 250,
												formStatus : [
																gfi.component.BasicForm.STATUS_NEW,
																gfi.component.BasicForm.STATUS_UPDATE ]
											},
											{
												xtype : 'splitter'
											},
											{
												xtype : 'textfield',
												itemId : 'nombreTitular',
												id : 'nombreTitular',
												name : 'nombreTitular',
												width : 405,
												formStatus : [
																 ]
											} ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Respuestas faltantes de registro:',
									name : 'respFaltantes',
									id : 'respFaltantes',
									itemId : 'respFaltantes',
									width : 200,
									labelWidth : 140,
									fieldStyle: 'color: red; border-color: red;',
									formStatus : [
											]
								},			
											
								{
									xtype : 'tabpanel',
									itemId : 'maintabpanel',
									frame : false,
									defaults : {
										bodyPadding : 10,
										autoScroll : true
									},
									items : [
											{
												title : 'Requerimiento de la autoridad',
												xtype : 'reqAutoridad'
											},
											{
												title : 'Tipo de Operación o contrato al que aplica la institución según oficio',
												xtype : 'tipoOperacionOcontrato'
											}, {
												title : 'Comentarios',
												xtype : 'container',
												width : 840,
												layout : {
													type : 'table',
													columns : 1,
													tdAttrs : {
														style : {
															align : 'center'
														}
													},
													tableAttrs : {
														style : {
															width : '100%',
															align : 'center'
														}
													}
												},
												defaults : {
													padding : 5,
													margin : 5,
													border : true,
													bodyStyle : 'padding:10px',
													anchor : '100%'
												},
												items : [ {
													xtype : 'textareafield',
													name : 'comentarios',
													id : 'comentarios',
													formStatus : [
																	gfi.component.BasicForm.STATUS_NEW,
																	gfi.component.BasicForm.STATUS_UPDATE ],
													width : '90%'
												} ],
											} ]
								}
								,{
									xtype : 'fieldset',
									title : 'Fechas de Control',
									collapsible : true,
									collapsed : true,
									items : [ {
										xtype : 'fieldcontainer',
										layout : 'hbox',
										items : [ {
											xtype : 'datefield',
											fieldLabel : 'Fecha de Creaci\u00f3n:',
											name : 'fhAlta',
											labelWidth : 115,
											width : 200,
											format : 'd/m/Y',
											readOnly : true
										}, {
											xtype : 'splitter'
										}, {
											xtype : 'datefield',
											fieldLabel : 'Fecha de Modificaci\u00f3n:',
											name : 'fhUltMod',
											labelWidth : 130,
											width : 215,
											format : 'd/m/Y',
										
											readOnly : true
										} ]
									},
									{
										xtype : 'fieldcontainer',
										layout : 'hbox',
										items : [ , {
											xtype : 'textfield',
											fieldLabel : 'Usuario de Creaci\u00f3n:',
											name : 'cveUsuAlta',
											labelWidth : 115,
											width : 200,
											readOnly : true
										},{
											xtype : 'splitter'
										}, {
											xtype : 'textfield',
											fieldLabel : 'Usuario de Modificaci\u00f3n:',
											name : 'cveUsuMod',
											labelWidth : 130,
											width : 215,
											readOnly : true
										} ]
									} ]
								}

						];
					}
		});
