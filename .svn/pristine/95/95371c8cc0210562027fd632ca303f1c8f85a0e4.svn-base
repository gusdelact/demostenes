Ext.define('gfi.bin.admctasweb.view.operativos.GeneracionRespuestasView', {
	extend : 'gfi.corp.component.form.BasicForm',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.operativos.BusquedaPersonaForm',
	            'gfi.bin.admctasweb.view.operativos.ContratosForm',
	            'gfi.bin.admctasweb.view.operativos.RespuestaForm'],
	
	config : {
		header: 'Generación de respuestas para la CNBV',
		formType: gfi.FormTypes.CUSTOM,
		//primaryKeys: ['numOficio','tipoOficio'],
		//modelClass : 'gfi.bin.admctasweb.model.catalogos.PersonaList',
		baseUrl: 'operativos/respuesta/',
		secured: false,
		
		userActions: {
			buscarRespuestas: {
				//buttonText: 'Buscar respuestas',
				createButton: false,
				statusConfig: 'Buscar',
				url: 'read.htm',
				sync: false,
			},
			
			limpiar : {
				createButton: false,
				subFormExecution: true,
				validateStatus: false,
				secured: false,
			}
		}
	},
	
	getMinWidth : function() {
		return 1100;
	},

	getWidth : function() {
		return 1100;
	},

	doLimpiar: function (options) {
		this.mainPanel.getForm().reset(options.reset);
		return true; 
	},
	
	completeLimpiar: function (completed, options) {
		var me = this;
	
		if (completed) {			
			if (options.reset) {
				me.changeStatus(gfi.component.BasicForm.STATUS_SEARCH);
			}
			
			/*
			 * Inicializa los grids de la metadata... Recarga la configuración incial del catálogo
			 */
			me.clearGridMetadata();
			me.executeActionSubForms('limpiar', completed, options);//Se limpian las subformas
		}
	},
	
	doBuscarRespuestas : function(options)
	{
		var me = this;
		var respuesta = Ext.create('gfi.bin.admctasweb.model.operativos.RespuestaList', {
		   numOficio : 	Ext.getCmp('oficio').getValue(),
		   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
		});				   
	
	   options.model = respuesta;
	   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscarRespuestas);			
	   
	},
	
	completeBuscarRespuestas : function(completed, options) 
	{
		var me = this;
		if(completed)
		{
			var info = options.info;
			var grid = Ext.getCmp('gridRespuestas');				
			grid.setRespuestas(info['respuestaList']);
			//grid.setRespuestas(options.info.get('respuestaList'));
		}
		else
		{
			me.showErrorMessage(options.error || options.message);
		}	
	},
	
	buildItems : function() {
		var me = this;
		return [ 		        
				{
			        xtype: 'fieldcontainer',
			        layout: 'hbox',
			        items: [
							{
								xtype: 'gfibuscadortrigger', 
								id: 'oficio',
								grupo: 'admctasweb', 
								//allowBlank : false,
								allowSearchAll : false,
								clave: 'oficiosFast',
								fieldLabel : 'Número de Oficio:',
								emptyText : 'SELECCIONE UN OFICIO',
								//name: 'numOficio',
								linkProperty: 'numOficio',
//								defaultFilters: [
//									new Ext.util.Filter({
//										property: 'fhOficio',
//			                            operator: '>=',
//			                            value: '01/08/2014',
//									})
//					    		],
								controls: [{
									ref: 'tipoOficio',
									selector: '#rdTipoOficio',
									linkProperty: 'tipoOficio'
								},{
									ref: 'sitOficio',
									selector: '#sitOficio',
									linkProperty: 'sitOficio'
								}],
								labelWidth: 100,
								width : 410,
								
		                		//Se sobreescribe evento de seleccionar oficio para ejecutar búsqueda automática de respuestas
		                	    selectRecord: function (model, format){
		                	    	
		                	    	me.executeAction('limpiar');
		                	    	
		                 			var meb = this, result;              		
		                 			meb.setEventInfo();
			                		result = meb.fireEvent('onSelectRecord', model, meb.eventInfo);
			                		
			                		if (result) {
			                			meb.selectedRecord = model;
			                			meb.displayRecord(meb.getResultText(model, format), model, meb.eventInfo);
			                			meb.displayControls(model, meb.eventInfo);
			                		}
			                					                		
			                		me.executeAction('buscarRespuestas');
			                		return result;
		                	    },
		                	    
							},		 			            	
			            	{
			    	            xtype: 'splitter'
			    	        },
							{
								xtype : 'combobox',
								fieldLabel : 'Situación:',
								//name : 'situacion',
								itemId : 'sitOficio',
								id : 'sitOficio',
								store:  Ext.create('Ext.data.Store', {
								    fields: ['sit', 'sitname'],
								    data : [
								        {"sit":"PE", "sitname":"PENDIENTE"},
								        {"sit":"TE", "sitname":"TERMINADO"},
								        {"sit":"EN", "sitname":"ENVIADO"}
								        //...
								    ]
								}),
								displayField: 'sitname',
								valueField: 'sit',
								fieldStyle: 'color: Crimson; ',
								labelWidth: 60,
								width : 410,
								readOnly : true
								//formStatus : [gfi.component.BasicForm.SEARCH]
							},			    	       			    	        
			    	        ]
				},				
		        
		    	{
		    		xtype: 'fieldset',
		    		title: 'Tipo de Oficio',
		    		collapsible: false,
		    		width: 825,
		    		items: [{
		    		    xtype: 'radiogroup',
		    		    id: "tipoGroup",
		    		    items: [
		    		        { boxLabel: 'Judicial',  itemId : 'rdTipoOficio', name: 'tipoOficio', inputValue: 'JU'},
		    		        { boxLabel: 'Hacendario',  name: 'tipoOficio', inputValue: 'HA'},
		    		        { boxLabel: 'Aseguramiento', name: 'tipoOficio', inputValue: 'AS' },
		    		        { boxLabel: 'PLD', name: 'tipoOficio', inputValue: 'PL' }
		    		    ],
		    		    width: 800,
		    		    formStatus : [gfi.component.BasicForm.SEARCH]
		    	    }]
		    	}, 
		
		    Ext.create('Ext.tab.Panel', {	    		
		    		id: 'mainTab',
		    		frame : false,
				    width: 1050,
				    height: 560,
				    activeTab: 0,
				    autoScroll : true,
		            defaults:{
		                bodyStyle:'padding:10px',
		            },
				    
				    items: [
				        {
				            title: 'Datos de Búsqueda',
				            xtype : 'busquedaPersona'
				        },
				        {
				            title: 'Resultados de la Búsqueda',
				            xtype : 'contratos'
				        },
				        {
				            title: 'Respuestas del Oficio',
				            xtype : 'respuestaform'
				        }
				        
				    ],
				    renderTo : Ext.getBody()
				})			
		];
	}
});