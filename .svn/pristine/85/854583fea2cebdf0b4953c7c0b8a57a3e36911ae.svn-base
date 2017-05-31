Ext.define('gfi.bin.admctasweb.view.catalogos.OficioView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.oficioview',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],
	
	//Configuración del Componente.
	config : {
		header: 'Consulta de Oficios',
		formType: gfi.corp.component.form.Types.CONSULTA,
		grupo: 'admctasweb', 
		clave: 'oficios',
		modelClass : 'gfi.bin.admctasweb.model.catalogos.Oficio',
		primaryKeys : ['numOficio','tipoOficio'],
		baseUrl: 'catalogos/oficio/',
		secured: false
	},

	getWidth	: function() {
		return 850;
	},

	
	//Construimos los items de la Forma
	buildItems : function() {
		return [{
    		xtype: 'fieldset',
    		title: 'Información de Estatus y Seguimiento',
    		layout: 'hbox',
    		collapsible: false,
    		height: 47,
    		width : 675,
    		//disabled: true,
    		items: [ {
    			xtype : 'combobox',
    			fieldLabel : 'Situación:',
    			name : 'sitOficio',
    			id : 'sitOficioOfiView',
    			store:  Ext.create('Ext.data.Store', {
    			    fields: ['sit', 'sitname'],
    			    data : [
    			        {"sit":"PE", "sitname":"Pendiente"},
    			        {"sit":"TE", "sitname":"Terminado"},
    			        {"sit":"EN", "sitname":"Enviado"}
    			    ]
    			}),
    			displayField: 'sitname',
    			valueField: 'sit',
    			labelWidth: 60,
    			width : 135,
    			readOnly : true
    		},{
	            xtype: 'tbspacer',
	            width: 20
	        },{
    			xtype : 'combobox',
    			fieldLabel : 'Tipo Caso:',
    			name : 'tipoCaso',
    			id : 'tipoCasoOfiView',
    			store:  Ext.create('Ext.data.Store', {
    			    fields: ['tip', 'tipCas'],
    			    data : [
    			        {"tip":"PO", "tipCas":"Positivo"},
    			        {"tip":"NE", "tipCas":"Negativo"},
    			        {"tip":"",   "tipCas":"-"},
    			    ]
    			}),
    			displayField: 'tipCas',
    			valueField: 'tip',
    			labelWidth: 60,
    			width : 135,
    			readOnly : true
    		},{
	            xtype: 'tbspacer',
	            width: 20
	        },{
				xtype : 'textfield',
				fieldLabel : 'Seguimiento:',
				name : 'descEstatus',
				labelWidth: 75,
				width : 340,
				readOnly : true			
			}]
    	}, {
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items : [{
				xtype : 'textfield',
				fieldLabel : 'Número de Oficio:',
				name : 'numOficio',
				labelWidth : 140,
				width : 320,
				validateBlank : true,
				formStatus : [ gfi.component.BasicForm.STATUS_NEW ]
			},{
				xtype : 'tbspacer',
				width : 20,
			},{
			    xtype: 'radiogroup',
			    id: "tipoOficioOfiView",
			    items: [
			        { boxLabel: 'Judicial',   		name: 'tipoOficio',	inputValue: 'JU'},
			        { boxLabel: 'Hacendario',		name: 'tipoOficio', inputValue: 'HA'},
			        { boxLabel: 'Aseguramiento',	name: 'tipoOficio', inputValue: 'AS' },
			        { boxLabel: 'PLD', 				name: 'tipoOficio', inputValue: 'PL' }
			    ],
			    width: 500,
			    formStatus : [ gfi.component.BasicForm.STATUS_NEW ]
		    }]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Número de Folio:',
			name : 'numFolio',
			labelWidth: 140,
			width : 250,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Número de Expediente:',
			name : 'numExped',
			labelWidth: 140,
			width : 250,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Número de Registro:',
			name : 'numRegistro',
			labelWidth: 140,
			width : 250,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items: [{
	            		xtype: 'gfibuscadortrigger', 
	            		id : 'busAutoridadOfiView',
	            		grupo: 'admctasweb', 
	            		clave: 'autoridades',
	            		fieldLabel : 'Autoridad:',
	            		name: 'cveAutoridad',
	            		linkProperty: 'cveAutoridad',
	            		controls: [{
	            			ref: 'txAutoridad', 
	            			selector: '#txAutoridad',
	            			linkProperty: 'nomAutoridad'
	            		}],
	            		labelWidth: 140,
	            		width : 250,
	        			formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			              gfi.component.BasicForm.STATUS_UPDATE]
	            	}, {
	    	            xtype: 'splitter'
	    	        }, {
	        			xtype : 'textfield',
	        			itemId : 'txAutoridad',
	        			width : 405,
	        			formStatus : [gfi.component.BasicForm.STATUS_NEW,
	    				              gfi.component.BasicForm.STATUS_UPDATE]
	        		}]
		}, {
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items: [{
	            		xtype: 'gfibuscadortrigger', 
	            		id : 'busEmpresaOfiView',
	            		grupo: 'corp', 
	            		clave: 'empresa',
	            		fieldLabel : 'Empresa Solicitada:',
	            		name: 'idEmpresa',
	            		linkProperty: 'idEmpresa',
	            		controls: [{
	            			ref: 'txEmpresa', 
	            			selector: '#txEmpresa',
	            			linkProperty: 'nombre'
	            		}],
	            		labelWidth: 140,
	            		width : 250,
	        			formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			              gfi.component.BasicForm.STATUS_UPDATE]
	            	}, {
	    	            xtype: 'splitter'
	    	        }, {
	        			xtype : 'textfield',
	        			itemId : 'txEmpresa',
	        			width : 405,
	        			formStatus : [gfi.component.BasicForm.STATUS_NEW,
	    				              gfi.component.BasicForm.STATUS_UPDATE]
	        		}]
		}, {
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items: [{
				xtype : 'datefield',
				fieldLabel : 'Fecha Oficio:',
				name : 'fhOficio',
				labelWidth: 140,
				width : 250,
				formStatus : [gfi.component.BasicForm.STATUS_NEW,
				              gfi.component.BasicForm.STATUS_UPDATE]
			}, {
	            xtype: 'splitter'
	        }, {
				xtype : 'datefield',
				fieldLabel : 'Fecha Recepción:',
				name : 'fhRecepcion',
				labelWidth: 100,
				width : 210,
				formStatus : [gfi.component.BasicForm.STATUS_NEW,
				              gfi.component.BasicForm.STATUS_UPDATE]
			}, {
	            xtype: 'tbspacer',
	            width : 100
	        }, {
				xtype : 'textfield',
				fieldLabel : 'Dias Plazo:',
				name : 'numDiasPzo',
				width : 95,
				labelWidth: 60,
				formStatus : [gfi.component.BasicForm.STATUS_NEW,
				              gfi.component.BasicForm.STATUS_UPDATE]
			}]
	    }, {
    		xtype: 'gfibuscadortrigger', 
    		grupo: 'admctasweb', 
    		clave: 'dirSolicitantes',
    		fieldLabel : 'Dirección Solicitante:',
    		name: 'txDireccion',
    		linkProperty: 'direccion',
    		labelWidth: 140,
    		width : 660,
    		controls: [{
    			ref: 'gerencia', 
    			selector: '#txGer',
    			linkProperty: 'gerencia',
    		},{
    			ref: 'subgerencia', 
    			selector: '#txSub',
    			linkProperty: 'subgerencia',
    		},{
    			ref: 'nombreAtencion', 
    			selector: '#txAtNo',
    			linkProperty: 'nombreAtencion',
    		},{
    			ref: 'puestoAtencion', 
    			selector: '#txAtPu',
    			linkProperty: 'puestoAtencion',
    		}],
    	    //Se sobreescribe evento del buscador para setear como filtro al oficio
            onTriggerClick: function() {
            	var me = this;

            	var tipoOficio = Ext.getCmp('tipoOficioOfiView').getChecked()[0];
            	
            	if (tipoOficio == 'undefined' || tipoOficio == null){
            		Ext.Msg.alert('AdmCtasWeb', 'Seleccione un Tipo de Oficio');
            	}else {
            		var filtro = new Ext.util.Filter({
                        property: 'tipoOficio',
                        operator: 'like',
                        value: tipoOficio.inputValue
                    });	                    	
                	me.setDefaultFilters(filtro);
                	me.search();
            	}
            },
    		formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
    	}, {
			xtype : 'textfield',
			fieldLabel : 'Gerencia Solicitante:',
			name : 'txGerencia',
			itemId : 'txGer',
			labelWidth: 140,
			size : 76,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Subgerencia Solicitante:',
			name : 'txSubgeren',
			itemId : 'txSub',
			labelWidth: 140,
			size : 76,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Atención a:',
			name : 'txAtnNom',
			itemId : 'txAtNo',
			labelWidth: 140,
			size : 76,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Puesto:',
			name : 'txAtnPue',
			itemId : 'txAtPu',
			labelWidth: 140,
			size : 76,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		},{
			xtype : 'textfield',
			fieldLabel : 'Folio Siara:',
			name : 'folioSiara',
			itemId : 'solicitudSiara',
			labelWidth: 140,
			size : 76,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		},{
			xtype : 'fieldcontainer',
			layout : 'hbox',
			items : [
					{
						xtype : 'gfibuscadortrigger',
						fieldLabel : 'Tipo Operación',
						id : 'busTipoOperacionOficioView',
						name : 'tipoOper',
						itemId : 'tipoOper',
						grupo : 'admctasweb',
						clave : 'mapeo',
						editable : false,
						labelWidth : 140,
						width : 250,
						maxLength : 3,
						maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
						defaultFilters : [ new Ext.util.Filter(
								{
									property : 'idCatalogo',
									operator : '=',
									value : '7'
								}) ],
						formStatus : [
								gfi.component.BasicForm.STATUS_NEW,
								gfi.component.BasicForm.STATUS_UPDATE ],
						linkProperty : 'cveSiti',
						controls : [ {
							ref : 'txtipoOperOficioForm',
							selector : '#txtipoOperOficioForm',
							linkProperty : 'descripcion'
						} ]
					},
					{
						xtype : 'splitter'
					},
					{
						xtype : 'textfield',
						itemId : 'txtipoOperOficioForm',
						name : 'descTipoOperacion',
						listeners : {
							change : function(field,
									newValue, oldValue) {
								field.setValue(newValue
										.toUpperCase());
							}
						},
						width : 405
					} ],
		},{
			xtype : 'fieldcontainer',
			layout : 'hbox',
			items : [
					{
						xtype : 'gfibuscadortrigger',
						itemId : 'autoridad',
						grupo : 'admctasweb',
						clave : 'mapeo',
						fieldLabel : 'Autoridad Xml:',
						name : 'cveAutoridadXml',
						linkProperty : 'cveSiti',
						labelWidth : 140,
						width : 250,
						editable : false,
						maxLength : 15,
						maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres o digitos revasado.',
						defaultFilters : [ 
						        new Ext.util.Filter({
									property : 'idCatalogo',
									operator : '=',
									value : '10'
								}) 
						],
						controls : [ {
							ref : 'txAutoridadXmlOficioForm',
							selector : '#txAutoridadXmlOficioForm',
							linkProperty : 'descripcion'
						} ],

						formStatus : [
								gfi.component.BasicForm.STATUS_NEW,
								gfi.component.BasicForm.STATUS_UPDATE ]
					},
					{
						xtype : 'splitter'
					},
					{
						xtype : 'textfield',
						itemId : 'txAutoridadXmlOficioForm',
						name : 'nombreAutXml',
						listeners : {
							change : function(field,
									newValue, oldValue) {
								field.setValue(newValue
										.toUpperCase());
							}
						},
						width : 405
					} ]
		},{
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items: [{
	            		xtype: 'textfield',
	            		fieldLabel : 'Caractér del Titular:',
	            		name: 'caracter',
	            		itemId : 'cveCaracter',
//	            		grupo: 'admctasweb', 
//	            		clave: 'mapeo',
	            		editable : false,
	            		linkProperty: 'cveSiti',
	            		controls: [{
	            			ref: 'txCaracterview', 
	            			selector: '#txCaracterview',
	            			linkProperty: 'descripcion'
	            		}],
	            		labelWidth: 140,
	            		width : 250,
	            		defaultFilters: [
    		 				new Ext.util.Filter({
    		 					property: 'idCatalogo',
    		 					operator: '=',
    		 					value: '9'
    		 				})
    		     		],
	        			formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			              gfi.component.BasicForm.STATUS_UPDATE]
	            	},{
	    	            xtype: 'splitter'
	    	        }, {
	        			xtype : 'textfield',
	        			itemId : 'descCaracter',
	        			width : 405,
	        			formStatus : [gfi.component.BasicForm.STATUS_NEW,
	    				              gfi.component.BasicForm.STATUS_UPDATE]
	        		}]
		}
//		,{
//			xtype : 'textfield',
//    		fieldLabel : 'Monto Inicial $',
//    		name: 'montoInicial',
//    		itemId : 'montoInicial',
//    		labelWidth: 140,
//    		maxLength 	: 25,
//	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
//	        msgTarget 	: 'under',
//    		formStatus : [
//							gfi.component.BasicForm.STATUS_NEW,
//							gfi.component.BasicForm.STATUS_UPDATE ],
//    		width : 660
//	    }
		,{
			xtype : 'textfield',
    		fieldLabel : 'Monto Requerido $',
    		name: 'montoRequerido',
    		itemId : 'montoRequerido',
    		labelWidth: 140,
    		maxLength 	: 25,
	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        msgTarget 	: 'under',
    		formStatus : [
							gfi.component.BasicForm.STATUS_NEW,
							gfi.component.BasicForm.STATUS_UPDATE ],
    		width : 660
	    },{
			xtype : 'textfield',
			fieldLabel : 'Número Oficio Requerido Operacíon',
			name : 'referencia2',
			itemId : 'numOfiRequeridoOperacionOficioView',
			labelWidth : 140,
			maxLength : 25,
			maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
			msgTarget : 'under',
			formStatus : [
					gfi.component.BasicForm.STATUS_NEW,
					gfi.component.BasicForm.STATUS_UPDATE ],
			width : 660
		},{
			xtype : 'fieldcontainer',
			layout : 'hbox',
			items : [
					{
						xtype : 'datefield',
						fieldLabel : 'Fecha Requerimiento:',
						name : 'fRequerimiento',
						format : 'Ymd',
						labelWidth : 140,
						width : 250,
						readOnly : true,
						formStatus : [
								gfi.component.BasicForm.STATUS_NEW,
								gfi.component.BasicForm.STATUS_UPDATE ]
					},
					{
						xtype : 'splitter'
					},
					{
						xtype : 'datefield',
						fieldLabel : 'Fecha Aplicación:',
						name : 'fAplicacion',
						format : 'Ymd',
						labelWidth : 100,
						width : 210,
						formStatus : [
								gfi.component.BasicForm.STATUS_NEW,
								gfi.component.BasicForm.STATUS_UPDATE ]
					} ]
		},{
			xtype : 'textfield',
			fieldLabel : 'Monto Operación $',
			name : 'montoSolicitado',
			itemId : 'montoSolicitado',
			maskRe : /[0-9]/,
			labelWidth : 140,
			maxLength : 25,
			maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
			msgTarget : 'under',
			formStatus : [
					gfi.component.BasicForm.STATUS_NEW,
					gfi.component.BasicForm.STATUS_UPDATE ],
			width : 660
		},
//	    {
//			xtype : 'textfield',
//    		fieldLabel : 'Monto Solicitado $',
//    		name: 'montoSolicitado',
//    		itemId : 'montoSolicitado',
//    		labelWidth: 140,
//    		maxLength 	: 25,
//	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
//	        msgTarget 	: 'under',
//    		formStatus : [
//							gfi.component.BasicForm.STATUS_NEW,
//							gfi.component.BasicForm.STATUS_UPDATE ],
//    		width : 660
//		},
		
		{
	    	 xtype: 'fieldcontainer',
		     layout: 'hbox',
		     items: [{
		            		xtype: 'gfibuscadortrigger', 
		            		fieldLabel : 'Moneda',
		            		name : 'monedaRequerida',
		            		id : 'descMonedav',
		            		itemId : 'descMonedav',
		            		linkProperty : 'descripcion',
		            		grupo: 'admctasweb', 
		            		clave: 'mapeo',
		            		editable : false,
		            		labelWidth : 140,													
		            		width : 250,
		            		defaultFilters: [
		            		                 new Ext.util.Filter({
		            		                	 property: 'idCatalogo',
		            		                	 operator: '=',
		            		                	 value: '6'
		            		                 })
		            		                 ],        		
		            	    formStatus : [gfi.component.BasicForm.STATUS_NEW,
		            		              gfi.component.BasicForm.STATUS_UPDATE],
		            		linkProperty: 'cveSiti',
		            		controls: [{
		            		       	 ref: 'txMonedaform', 
		            		       	 selector: '#txMonedav',
		            		       	 linkProperty: 'descripcion'
		            		          }]
		        	   },
		        	   {
		        				xtype: 'splitter'
		        	   },{
		        		   xtype : 'textfield',
		        		   itemId : 'txMonedav',
		        		   name : 'descMoneda',
		        		   width : 405,    			
//		        		   formStatus : [gfi.component.BasicForm.STATUS_NEW,
//		        		                 gfi.component.BasicForm.STATUS_UPDATE]
		          }],		
		}
//		,
//	    {
//			xtype : 'textfield',
//    		fieldLabel : 'Saldo después Oper $',
//    		name: 'saldoDespuesOper',
//    		itemId : 'saldoOper',
//    		id:'saldoV',
//    		labelWidth: 140,
//    		maxLength 	: 25,
//	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
//	        msgTarget 	: 'under',
//    		formStatus : [gfi.component.BasicForm.STATUS_NEW,
//							gfi.component.BasicForm.STATUS_UPDATE ],
//    		width : 660
//	    }		
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
					disabled : true,
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
					disabled : true,
					readOnly : true
				} ]
			}, {
				xtype : 'fieldcontainer',
				layout : 'hbox',
				items : [ , {
					xtype : 'textfield',
					fieldLabel : 'Usuario de Creaci\u00f3n:',
					name : 'cveUsuAlta',
					labelWidth : 115,
					width : 200,
					disabled : true
				},{
					xtype : 'splitter'
				}, {
					xtype : 'textfield',
					fieldLabel : 'Usuario de Modificaci\u00f3n:',
					name : 'cveUsuMod',
					labelWidth : 130,
					width : 215,
					disabled : true
				} ]
			} ]
		}
		];
	},
	
	//Se sobreescriben las Acciones de la Forma.
	
	//Completar Selección.
	completeSeleccionar: function (completed, options) {
		var me = this;
		var buscadorAut = Ext.getCmp('busAutoridadOfiView');
		var buscadorEmp = Ext.getCmp('busEmpresaOfiView');
		
		buscadorAut.loadRecord( new Ext.util.Filter({property: 'cveAutoridad', value: options.model.get('cveAutoridad')}) );
		buscadorEmp.loadRecord( new Ext.util.Filter({property: 'idEmpresa', value: options.model.get('idEmpresa')}) );
		
		me.actions.seleccionar.completeAction.call(me, completed, options);
    }
	
});

