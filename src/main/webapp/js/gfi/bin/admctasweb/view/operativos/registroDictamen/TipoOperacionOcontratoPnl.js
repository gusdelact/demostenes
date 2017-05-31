Ext.define('gfi.bin.admctasweb.view.operativos.registroDictamen.TipoOperacionOcontratoPnl', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.tipoOperacionOcontrato',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger'
	            ],
	
	
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
									id : 'container2',
									width : 940,								
									layout : {
										type : 'table',
										columns : 2,
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
									items : [  
									         {
										xtype : 'checkbox',
										inputValue: '1',
										id : 'cuentaCheques',
										name : 'cuentaCheques',
										boxLabel : 'Cuenta Cheques',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1
									}, {
										xtype : 'checkbox',
										inputValue: '1',
										id : 'transfMonedaNal',
										name : 'transfMonedaNal',
										boxLabel : 'Transferencias Moneda Nacional',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1
									},{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'inversiones',
										name : 'inversiones',
										boxLabel : 'Inversiones',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1
									}, {
										xtype : 'checkbox',
										inputValue: '1',
										id : 'transfMonedaExt',
										name : 'transfMonedaExt',
										boxLabel : 'Transferencias Moneda Extranjera',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'intermediacionBursatil',
										name : 'intermediacionBursatil',
										boxLabel : 'Contratos de intermediación Bursatil',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'operacionesRelevantes',
										name : 'operacionesRelevantes',
										boxLabel : 'Operaciones Relevantes',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'creditos',
										name : 'creditos',
										boxLabel : 'Créditos',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1
									},
									{	
										xtype : 'checkbox',
										inputValue: '1',
										id : 'operacionesInusuales',
										name : 'operacionesInusuales',
										boxLabel : 'Operaciones Inusuales',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1
									},
									{

										xtype : 'checkbox',
										inputValue: '1',
										id : 'cajasSeguridad',
										name : 'cajasSeguridad',
										boxLabel : 'Cajas de Seguridad',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'fideicomisos',
										name : 'fideicomisos',
										boxLabel : 'Fideicomisos',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1										
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'operacionDivisas',
										name : 'operacionDivisas',
										boxLabel : 'Operación de Divisas',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1			
									},
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'otros2',
										name : 'otros2',
										boxLabel : 'Otros. (Tipo de operación o contrato). Especificar:',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1,
										listeners: {
											 click: {
										            element: 'el',
										            fn: function(){ 
										            	 var otros2 =    Ext.getCmp('otros2').getValue();
											            if(otros2){										            	
											            	Ext.getCmp('especificar2').setDisabled(false);
											            }else{
											        		Ext.getCmp('especificar2').setDisabled(true);	
											            	Ext.getCmp('especificar2').setValue(null);				
											            }
										            }
											 }
										 }
									},
									{
								        xtype     : 'textareafield',
								        id 		  : 'especificar2',
								        name      : 'especificar2',
								        fieldLabel: null,
								        maxLength 	: 1000,
				            	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
				            	        msgTarget 	: 'under',
								        formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
								        width    : '90%'
								    },
									{
										xtype : 'checkbox',
										inputValue: '1',
										id : 'fideicomisos',
										name : 'fideicomisos',
										boxLabel : 'Fideicomisos',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 2
									},
									{
										xtype : 'textfield',
				                		fieldLabel : 'Tipo de fideicomiso:',
				                		name: 'tipoFideicomisos',
				                		itemId : 'tipoFideicomisos',
				                		id:'tipoFideicomisos',
				                		labelWidth: 100,
				                		maxLength 	: 50,
				            	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
				            	        msgTarget 	: 'under',
				                		formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
				                		width : 300
									},
									{
										xtype : 'textfield',
				                		fieldLabel : 'Cáracter dentro del contrato:',
				                		name: 'caracterContrato',
				                		itemId : 'caracterContrato',
				                		id:'caracterContrato',
				                		labelWidth: 100,
				                		maxLength 	: 50,
				            	        maxLengthText : 'M\u00E1ximo 50 n\u00FAmero de caracteres revasado.',
				            	        msgTarget 	: 'under',
				                		formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
				                		width : 300
									},
//									{
//										xtype : 'checkbox',
//										inputValue: '1',
//										id : 'embargoParcial',
//										name : 'embargoParcial',
//										boxLabel : 'Embargo Parcial',
//										labelAlign : 'right',
//										labelWidth : 200,
//										formStatus : [
//														gfi.component.BasicForm.STATUS_NEW,
//														gfi.component.BasicForm.STATUS_UPDATE ],
//										colspan : 1
//									},	
//									{
//							            xtype: 'fieldcontainer',
//							            width:500,
//
//							            layout: 'hbox',
//							            items: [
//							             {
//											xtype : 'textfield',
//					                		fieldLabel : '$',
//					                		name: 'embargoParcialMonto',
//					                		itemId : 'embargoParcialMonto',
//					                		id:'embargoParcialMonto',
//					                		labelWidth: 20,
//					                		maxLength 	: 18,
//					            	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
//					            	        msgTarget 	: 'under',
//					                		formStatus : [
//															gfi.component.BasicForm.STATUS_NEW,
//															gfi.component.BasicForm.STATUS_UPDATE ],
//					                		width : 100
//										}, {
//							                xtype: 'splitter'
//							            }, {
//							                xtype: 'label',
//							                forId: 'myFieldId',
//							                text: 'o',
//							            },{
//							                xtype: 'splitter'
//							            },{
//					                		xtype : 'textfield',
//					                		fieldLabel : '%',
//					                		name: 'embargoParcialPorc',
//					                		itemId : 'embargoParcialPorc',
//					                		id:'embargoParcialPorc',
//					                		labelWidth: 20,
//					                		maxLength 	: 3,
//					            	        maxLengthText : 'M\u00E1ximo 3 n\u00FAmero de caracteres revasado.',
//					            	        msgTarget 	: 'under',
//					                		formStatus : [
//															gfi.component.BasicForm.STATUS_NEW,
//															gfi.component.BasicForm.STATUS_UPDATE ],
//					                		width : 100
//										}
//							            
//							            ]
//							        },//Termina fiel container
//							        {
//										xtype : 'checkbox',
//										inputValue: '1',
//										id : 'embargoTotal',
//										name : 'embargoTotal',
//										boxLabel : 'Embargo Total',
//										labelAlign : 'right',
//										labelWidth : 150,
//										formStatus : [
//														gfi.component.BasicForm.STATUS_NEW,
//														gfi.component.BasicForm.STATUS_UPDATE ],
//										colspan : 2
//									},	
							        {
										xtype : 'checkbox',
										inputValue: '1',
										id : 'otros3',
										name : 'otros3',
										boxLabel : 'Otros. Especificar:',
										labelAlign : 'right',
										labelWidth : 200,
										formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
										colspan : 1,
										listeners: {
											 click: {
										            element: 'el',
										            fn: function(){ 
										            	 var otros3 =    Ext.getCmp('otros3').getValue();
											            if(otros3){										            	
											            	Ext.getCmp('especificar3').setDisabled(false);
											            }else{
											        		Ext.getCmp('especificar3').setDisabled(true);	
											            	Ext.getCmp('especificar3').setValue(null);				
											            }
										            }
											 }
										 }
									},
									{
								        xtype     : 'textareafield',
								        id 		  : 'especificar3',
								        name      : 'especificar3',
								        fieldLabel: null,
								        maxLength 	: 500,
				            	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
				            	        msgTarget 	: 'under',
								        formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
								        width    : '90%'
								    },	
									 {
											xtype : 'label',
											text : '*Aplica a cuentas aperturadas en Moneda:',
											align: 'center',
											colspan : 2
										},
										{
											xtype : 'checkbox',
											inputValue: '1',
											id : 'aplicaCtasMonedaNal',
											name : 'aplicaCtasMonedaNal',
											boxLabel : 'Nacional',
											labelAlign : 'right',
											labelWidth : 200,
											formStatus : [
															gfi.component.BasicForm.STATUS_NEW,
															gfi.component.BasicForm.STATUS_UPDATE ],
											colspan : 1
									    },	
										{
											xtype : 'checkbox',
											inputValue: '1',
											id : 'aplicaCtasMonedaExt',
											name : 'aplicaCtasMonedaExt',
											boxLabel : 'Extranjera',
											labelAlign : 'right',
											labelWidth : 200,
											formStatus : [
															gfi.component.BasicForm.STATUS_NEW,
															gfi.component.BasicForm.STATUS_UPDATE ],
											colspan : 1
									    }	
									
									]

								},	

											
											
									      
								
								
								
						];

					},	
   			
});



					