Ext.define('gfi.bin.admctasweb.view.operativos.registroDictamen.RequerimientoAutoridadPnl', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.reqAutoridad',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger', 'gfi.bin.admctasweb.view.operativos.PersonaCorpGrid'],
	
		config : {
			header: null,
			formType: gfi.FormTypes.CATALOGO,
			baseUrl: 'operativos/regdictamenjuri/',
			secured: false,				

			pack: 'stretch',
			subFormConfig :{
				embedded: true,
				usePropertyName: false
			},
			primaryKeys : [ 'numOficio', 'tipoOficio', 'numConsec' ],
			modelClass : 'gfi.bin.admctasweb.model.operativos.CnbvDictamen'
		},
		

		userActions : {
			borrar : {
				createButton : false,
			},
			editar : {
				createButton : false,
			},
	
			aceptar : {
				createButton : false,
			},
			crear : {
				createButton : false,
			},
		},

	    
		getMinWidth : function() {
			return 600;
		},

		getWidth : function() {
			return 600;
		},
							
		buildItems : function() {
						return [
								{
									xtype : 'container',
									width : 940,
									renderTo : Ext.getBody(),
									layout : {
										type : 'table',
										columns : 3,
										tdAttrs: {											
                                            style: {
                                            	//verticalAlign : 'top',
                                                align: 'center'
                                            }
                                        },
                                        tableAttrs: {
                                            style: {
                                            	 width: '100%',
                                                align: 'center'
                                            }
                                        }
									},
									defaults : {
										padding: 2, margin: 2,
										bodyStyle : 'padding:2px',
										anchor:'100%'
									},
									items : [  {
										xtype : 'checkbox',
										inputValue: '1',
										id : 'docIdentifCuenta',
										name : 'docIdentifCuenta',
										boxLabel : 'Documentación de identificación de la(s) cuenta(s)',
										labelAlign : 'right',
										labelWidth : 200,										
										colspan : 1,
										rowspan: 2,
										cellCls: 'highlight',
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										 listeners: {
											 click: {
										            element: 'el', //bind to the underlying el property on the panel
										            fn: function(){ console.log('click el'); 
										            var val =    Ext.getCmp('docIdentifCuenta').getValue();
										            if(val){
										            	Ext.getCmp('copiaSimple').setValue(true);
										            	Ext.getCmp('copiaCertificada').setValue(false);
										            }else{
										            	Ext.getCmp('copiaSimple').setValue(false);
										            	Ext.getCmp('copiaCertificada').setValue(false);
										            }
										            
										            }
										        }
										    }
										
									}, {
										xtype : 'checkbox',
										inputValue: '1',
										id : 'copiaSimple',
										name : 'copiaSimple',
										boxLabel : 'Copia simple',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 2,
										listeners: {
											 click: {
										            element: 'el', //bind to the underlying el property on the panel
										            fn: function(){ 
										            var csimple =    Ext.getCmp('copiaSimple').getValue();
										            var ccertif =    Ext.getCmp('copiaCertificada').getValue();
											            if(csimple){										            	
											            	Ext.getCmp('copiaCertificada').setValue(false);
											            }
											            if(!csimple && !ccertif){
											            	Ext.getCmp('docIdentifCuenta').setValue(false);
											            }else if(csimple || ccertif){
											            	Ext.getCmp('docIdentifCuenta').setValue(true);
											            }
										            }
										        }
										    }
									}, {
										xtype : 'checkbox',
										inputValue: '1',
										id : 'copiaCertificada',
										name : 'copiaCertificada',
										boxLabel : 'Copia certificada',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 2,
										listeners: {
											 click: {
										            element: 'el', //bind to the underlying el property on the panel
										            fn: function(){ 
										            	 var csimple =    Ext.getCmp('copiaSimple').getValue();
												         var ccertif =    Ext.getCmp('copiaCertificada').getValue();
											            if(ccertif){										            	
											            	Ext.getCmp('copiaSimple').setValue(false);
											            }		
											            if(!csimple && !ccertif){
											            	Ext.getCmp('docIdentifCuenta').setValue(false);
											            }else if(csimple || ccertif){
											            	Ext.getCmp('docIdentifCuenta').setValue(true);
											            }
										            }
										        }
										    }
									},{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'infoGralCuenta',
										name : 'infoGralCuenta',
										boxLabel : 'Información general de la(s) cuenta(s)',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 3
									}, {
										xtype : 'checkbox',
										inputValue: '1',
										id : 'estadosCuenta',
										name : 'estadosCuenta',
										boxLabel : 'Estados de cuenta',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 3
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'tarjetaRegFirma',
										name : 'tarjetaRegFirma',
										boxLabel : 'Tarjeta de registro de firmas',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 3
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'embargoTotalCta',
										name : 'embargoTotalCta',
										boxLabel : 'Embargo total de la(s) cuenta(s)',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 3,
										listeners: {
											 click: {
										            element: 'el',
										            fn: function(){ 
										            	 var embTotal =    Ext.getCmp('embargoTotalCta').getValue();
											            if(embTotal){										            	
											            	Ext.getCmp('embargoParcialCta').setValue(false);
											            	Ext.getCmp('montoEmbargoParcial').setDisabled(true);
											            	Ext.getCmp('montoEmbargoParcial').setValue(null);
											            	Ext.getCmp('monedaEmbargoParcial').setDisabled(true);
											            	Ext.getCmp('monedaEmbargoParcial').setValue(null);
											            	
											            	Ext.getCmp('descMonedaEmbargoParcial').setDisabled(true);
											            	Ext.getCmp('descMonedaEmbargoParcial').setValue(null);
											            }
										            }
										        }
										    }
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'embargoParcialCta',
										name : 'embargoParcialCta',
										boxLabel : 'Embargo parcial de la(s) cuenta(s)',
										labelAlign : 'right',
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										labelWidth : 200,
										listeners: {
											 click: {
										            element: 'el',
										            fn: function(){ 
										            	 var embParc =    Ext.getCmp('embargoParcialCta').getValue();
											            if(embParc){										            	
											            	Ext.getCmp('embargoTotalCta').setValue(false);
											            	Ext.getCmp('montoEmbargoParcial').setDisabled(false);
											            	Ext.getCmp('monedaEmbargoParcial').setDisabled(false);
											            	Ext.getCmp('descMonedaEmbargoParcial').setDisabled(false);
											            }
										            }
										        }
										    }
									},
									{
												xtype : 'textfield',
						                		fieldLabel : 'Monto $:',
						                		name: 'montoEmbargoParcial',
						                		itemId : 'montoEmbargoParcial',
						                		id:'montoEmbargoParcial',
						                		labelWidth: 100,
						                		maxLength 	: 18,
						            	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
						            	        msgTarget 	: 'under',
						                		formStatus : [
																gfi.component.BasicForm.STATUS_NEW,
																gfi.component.BasicForm.STATUS_UPDATE ],
						                		width : 300
									},
									{
										xtype : 'gfibuscadortrigger',
										fieldLabel : 'Moneda:',
										name : 'descMonedaEmbargoParcial',
										id : 'descMonedaEmbargoParcial',
										itemId : 'descMonedaEmbargoParcial',
										linkProperty : 'descMoneda',
										grupo : 'corp',
										clave : 'moneda',
										editable : false,
										fieldLabel : 'Moneda:',
										labelWidth : 140,													
										width : 350,
										controls : [
													{
														ref : 'clave',
														selector : '#monedaEmbargoParcial',
														linkProperty : 'clave'
													}
													]
										
									},		
									 
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'bloqueoAperturaCta',
										name : 'bloqueoAperturaCta',
										boxLabel : 'Bloqueo de futuras aperturas de cuentas',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 3										
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'levantamtoParcialEmb',
										name : 'levantamtoParcialEmb',
										boxLabel : 'Levantamiento parcial del embargo',
										labelAlign : 'right',
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										labelWidth : 200,
										listeners: {
											 click: {
										            element: 'el',
										            fn: function(){ 
										            	 var levParc =    Ext.getCmp('levantamtoParcialEmb').getValue();
											            if(levParc){										            	
											            	Ext.getCmp('levantamtoTotalEmb').setValue(false);
											            	Ext.getCmp('montoLevParcialEmb').setDisabled(false);
											            	Ext.getCmp('monedaLevParcialEmb').setDisabled(false);
											            	Ext.getCmp('descMonedaLevParcialEmb').setDisabled(false);
											            }
										            }
										        }
										    }
									},
									{
										xtype : 'textfield',
				                		fieldLabel : 'Monto $:',
				                		name: 'montoLevParcialEmb',
				                		itemId : 'montoLevParcialEmb',
				                		id:'montoLevParcialEmb',
				                		labelWidth: 100,
//				                		regex:'/^\d+(\.\d)?$/',
//				                		regexText:'Ingrese solo valores decimales',
//				                		msgTarget 	: 'under',
				                		formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
				                		width : 300
									},
									
									{
										xtype : 'gfibuscadortrigger',
										fieldLabel : 'Moneda:',
										name : 'descMonedaLevParcialEmb',
										id : 'descMonedaLevParcialEmb',
										itemId : 'descMonedaLevParcialEmb',
										linkProperty : 'descMoneda',
										grupo : 'corp',
										clave : 'moneda',
										editable : false,
										fieldLabel : 'Moneda:',
										labelWidth : 140,													
										width : 350,
										controls : [
													{
														ref : 'clave',
														selector : '#monedaLevParcialEmb',
														linkProperty : 'clave'
													}
													]
										
									},		
									
										
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'levantamtoTotalEmb',
										name : 'levantamtoTotalEmb',
										boxLabel : 'Levantamiento total del embargo',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 3,
										listeners: {
											 click: {
										            element: 'el',
										            fn: function(){ 
										            	 var levParc =    Ext.getCmp('levantamtoTotalEmb').getValue();
											            if(levParc){										            	
											            	Ext.getCmp('levantamtoParcialEmb').setValue(false);
											            	Ext.getCmp('montoLevParcialEmb').setDisabled(true);
											            	Ext.getCmp('montoLevParcialEmb').setValue(null);
											            	Ext.getCmp('monedaLevParcialEmb').setDisabled(true);
											            	Ext.getCmp('monedaLevParcialEmb').setValue(null);
											            	
											            	Ext.getCmp('descMonedaLevParcialEmb').setDisabled(true);
											            	Ext.getCmp('descMonedaLevParcialEmb').setValue(null);
											            }
										            }
										        }
										    }
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'reportesOperPld',
										name : 'reportesOperPld',
										boxLabel : 'Reporte de operaciones de prevención de lavado de dinero',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 3
									}, {
										xtype : 'checkbox',
										inputValue: '1',
										id : 'otros',
										name : 'otros',
										boxLabel : 'Otros',
										labelAlign : 'right',
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										labelWidth : 200,
										listeners: {
											 click: {
										            element: 'el',
										            fn: function(){ 
										            	 var otros =    Ext.getCmp('otros').getValue();
											            if(otros){										            	
											            	Ext.getCmp('especificar').setDisabled(false);
											            }else{
											        		Ext.getCmp('especificar').setDisabled(true);	
											            	Ext.getCmp('especificar').setValue(null);				
											            }
										            }
											 }
										 }
										
									}, {
										xtype : 'textareafield',
										id   : 'especificar',
										name : 'especificar',
										fieldLabel : 'Especificar:',
										width : 400,
										maxLength 	: 2000,
				            	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
				            	        msgTarget 	: 'under',
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 2
									},{
										xtype : 'hiddenfield',
										itemId : 'monedaEmbargoParcial',
										id : 'monedaEmbargoParcial',
										name : 'monedaEmbargoParcial',
										width : 200,
										formStatus : [
														 ]
									},		
									 {
										xtype : 'hiddenfield',
										itemId : 'monedaLevParcialEmb',
										id : 'monedaLevParcialEmb',
										name : 'monedaLevParcialEmb',
										width : 200,
										formStatus : [
														 ]
									},										
												
									]

								},
								
						];

					},	
   			
});



					