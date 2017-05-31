Ext.define('gfi.bin.admctasweb.view.operativos.ContratosForm', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.contratos',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger,', 'gfi.bin.admctasweb.view.operativos.ContratoGrid',
	            'gfi.bin.admctasweb.view.operativos.ContratoCambiosGrid'],
	
		config : {
			
			header: null,
			formType: gfi.FormTypes.CUSTOM,
			baseUrl: 'operativos/respuesta/',
			secured: false,	
//			primaryKeys: ['numOficio', 'tipoOficio', 'numConsec', 'idContrato'],		
//			modelClass: 'gfi.bin.admctasweb.model.operativos.Respuesta',
			pack: 'stretch',
			subFormConfig :{
				embedded: true,
				usePropertyName: false
			},			
			userActions: {
				registrarRespNeg: {
					//createButton: false,
					buttonText: 'Registra Respuesta Negativa',
					statusConfig: 'Buscar',
					url: 'negativa.htm',
					sync: false,
					pack: 'center',
				},				
				registrarRespPosCte: {
					//createButton: false,
					buttonText: 'Registra Respuesta Positiva a Cliente',
					statusConfig: 'Buscar',
					url: 'cliente.htm',
					sync: false,
					pack: 'center',
				},				
				registrarRespPosCta: {
					//createButton: false,
					buttonText: 'Registra Respuesta Positiva a Cuenta',
					statusConfig: 'Buscar',
					url: 'positiva.htm',
					sync: false,
					pack: 'center',
				},			
				
				buscarRespuestas: {
					//buttonText: 'Buscar respuestas',
					createButton: false,
					statusConfig: 'Buscar',
					url: 'read.htm',
					sync: false,
				},		

				crearReporte: {
					buttonText: 'Imprimir Contratos',					
					statusConfig: 'Buscar',
					url: 'reportecontratos.htm',
					sync: false,
				},		
				
				
				limpiar : {
					createButton: false,
					//subFormExecution: true,
					validateStatus: false,
					secured: false,
				}
			
			}
			
		},
		
		getMinWidth : function() {
			return 600;
		},

		getWidth : function() {
			return 600;
		},
			
		//Registra una respuesta negativa
		doRegistrarRespNeg : function (options)
		{
			var me = this;
			//Verifica selección de Oficio y persona
			var consecutivo = Ext.getCmp('numConsecPersona').getValue();
			if(consecutivo == null || consecutivo == ''){
				me.showMessage('text', 'No ha consultado la Persona de un Oficio', false, null);
				return;
			}
			
			if(!me.oficioPendiente())
			{
				me.showMessage('text', 'El Oficio no se encuentra PENDIENTE', false, null);
				return;
			}
			
//			var cuenta = Ext.getCmp('cuenta').getValue();
//			if(cuenta != null && cuenta != '')
//			{
//				me.showMessage('text', 'La Persona tiene una Cuenta asociada, solo puede registrar respuesta Positiva', false, null);
//				return;
//			}
			
			//Verifica si se asoció persona al oficio
			var cliente = Ext.getCmp('cliente').getValue();
			if(cliente != null && cliente != '')
			{
				me.showMessage('text', 'La Persona esta marcada como Cliente del Grupo. Verifique.', false, null);
				return;
			}
			
			//Verifica el tipo de respuesta
		   var respuesta = Ext.create('gfi.bin.admctasweb.model.operativos.Respuesta', {
			   numOficio : 	Ext.getCmp('oficio').getValue(),
			   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
			   numConsec :	consecutivo,
			   idContrato: 	0,//Se envia cero porque no se selecciona contrato como tal para respuesta negativa
			   tipoRespuesta : 1//Se envia respuesta total por defecto
		   });				   
		
		   options.model = respuesta;
		   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeRegistrarRespNeg);			

				   
		},
		
		completeRegistrarRespNeg : function(completed, options)
		{
			var me = this;
			if(completed)
			{
				if (options.message) {
					me.showTextMessage(options.message, false);
				}
				//me.executeAction('buscarRespuestas');
				var info = options.info;//Se accede a este objeto porque el retorno no coincide con el modelo de esta pantalla (PersonaBusqueda)				
//				var grid = Ext.getCmp('gridRespuestas');				
//				grid.setRespuestas(info['respuestaList']);	
				
				var buscadorEventos = Ext.getCmp('oficio');
				buscadorEventos.loadRecord(new Ext.util.Filter({property: 'numOficio', value: info['numOficio']}));
				
				
			}
			else
			{
				me.showErrorMessage(options.error || options.message);
			}
			//options.action.superclass.completeAction.call(me, completed, options);
		},
		
		//Registra respuesta Positiva para cliente
		doRegistrarRespPosCte : function (options)
		{
			var me = this;
			//Verifica selección de Oficio y persona
			var consecutivo = Ext.getCmp('numConsecPersona').getValue();
			if(consecutivo == null || consecutivo == ''){
				me.showMessage('text', 'No ha consultado la Persona de un Oficio', false, null);
				return;
			}
			
			if(!me.oficioPendiente())
			{
				me.showMessage('text', 'El Oficio no se encuentra PENDIENTE', false, null);
				return;
			}
			
			
			
			//Verifica si se asoció persona al oficio
			var cliente = Ext.getCmp('cliente').getValue();
			if(cliente == null || cliente == '')
			{
				me.showMessage('text', 'Primero debe marcar a la Persona como Cliente del Grupo. Verifique.', false, null);
				return;
			}
			
			//Verifica el tipo de respuesta
		   var respuesta = Ext.create('gfi.bin.admctasweb.model.operativos.Respuesta', {
			   numOficio : 	Ext.getCmp('oficio').getValue(),
			   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
			   numConsec :	consecutivo,
			   idContrato: 	0,//Se envia cero porque no se selecciona contrato como tal para respuesta cliente
			   tipoRespuesta : 1,//Se envia 1 por defecto
		   });				   
		
		   options.model = respuesta;
		   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeRegistrarRespPosCte);			
			
		},
		
		completeRegistrarRespPosCte : function(completed, options)
		{
			var me = this;
			if(completed)
			{
				if (options.message) {
					me.showTextMessage(options.message, false);
				}
				//me.executeAction('buscarRespuestas');
				var info = options.info;//Se accede a este objeto porque el retorno no coincide con el modelo de esta pantalla (PersonaBusqueda)				
//				var grid = Ext.getCmp('gridRespuestas');				
//				grid.setRespuestas(info['respuestaList']);		
				
				var buscadorEventos = Ext.getCmp('oficio');
				buscadorEventos.loadRecord(new Ext.util.Filter({property: 'numOficio', value: info['numOficio']}));
				
			}
			else
			{
				me.showErrorMessage(options.error || options.message);
			}
			//options.action.superclass.completeAction.call(me, completed, options);			
		},
		
		doRegistrarRespPosCta : function (options)
		{
			var me = this;
			
			//Verifica selección de Oficio y persona
			var consecutivo = Ext.getCmp('numConsecPersona').getValue();
			if(consecutivo == null || consecutivo == ''){
				me.showMessage('text', 'No ha consultado la Persona de un Oficio', false, null);
				return;
			}
			
			if(!me.oficioPendiente())
			{
				me.showMessage('text', 'El Oficio no se encuentra PENDIENTE', false, null);
				return;
			}
			
			var idContrato = null;
			var tipoBusqueda = null;
			//Verifica si se seleccionó un contrato
			if(Ext.getCmp('gridContrato').getSelectionModel().hasSelection())
			{
				 var row = Ext.getCmp('gridContrato').getSelectionModel().getSelection()[0];
				idContrato = row.get('idContrato');
				tipoBusqueda = 'CONTRATOS';
			}
			else if(Ext.getCmp('gridContratoCambios').getSelectionModel().hasSelection())
			{
				 var row = Ext.getCmp('gridContratoCambios').getSelectionModel().getSelection()[0];
				 idContrato = row.get('idContrato');
				 tipoBusqueda = 'CCAMBIOS';
			}
			else
			{
				me.showMessage('text', 'Seleccione un Contrato', false, null);
				return;			
			}
					
		   var row = Ext.getCmp('gridPersonas').getSelectionModel().getSelection()[0];
		   var respuesta = Ext.create('gfi.bin.admctasweb.model.operativos.Respuesta', {
			   numOficio : 	Ext.getCmp('oficio').getValue(),
			   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
			   numConsec :	consecutivo,
			   idContrato: 	idContrato,
			   tipoRespuesta : 1, //Tipo de respuesta total por defecto
			   tipoBusqueda : tipoBusqueda,
			   idPersona : row.get('idPersona')//Solo se envia para respuestas positivas
		   });				   
		
		   options.model = respuesta;
		   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeRegistrarRespPosCta);			
			
			
		},
		
		completeRegistrarRespPosCta : function(completed, options)
		{
			var me = this;
			if(completed)
			{
				if (options.message) {
					me.showTextMessage(options.message, false);
				}
				//me.executeAction('buscarRespuestas');
				var info = options.info;//Se accede a este objeto porque el retorno no coincide con el modelo de esta pantalla (PersonaBusqueda)				
//				var grid = Ext.getCmp('gridRespuestas');				
//				grid.setRespuestas(info['respuestaList']);
				
				var buscadorEventos = Ext.getCmp('oficio');
				buscadorEventos.loadRecord(new Ext.util.Filter({property: 'numOficio', value: info['numOficio']}));
				
			}
			else
			{
				me.showErrorMessage(options.error || options.message);
			}
			//options.action.superclass.completeAction.call(me, completed, options);			
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
				var info = options.info;//Se accede a este objeto porque no está definido el modelo de esta pantalla
										//De otra forma se podría obtener de la forma : options.info.get('respuestaList')
				var grid = Ext.getCmp('gridRespuestas');				
				grid.setRespuestas(info['respuestaList']);
				//grid.setRespuestas(options.info.get('respuestaList'));
			}
			else
			{
				me.showErrorMessage(options.error || options.message);
			}	
		},
		
		doLimpiar: function (options) {
			var me = this;			
			me.mainPanel.getForm().reset(options.reset);

			var grid1 = Ext.getCmp('gridContrato');
			grid1.getStore().removeAll();
			grid1.getStore().sync();
			
			var grid2 = Ext.getCmp('gridContratoCambios');
			grid2.getStore().removeAll();
			grid2.getStore().sync();

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
			}
		},
		
		doCrearReporte : function(options)
		{
			var me = this;	
			
			if(Ext.getCmp('gridPersonas').getSelectionModel().hasSelection())			
			{			
				var row = Ext.getCmp('gridPersonas').getSelectionModel().getSelection()[0];
				var idPersona = row.get('idPersona');
				var idContrato = row.get('idContrato');
				
				//Esta concatena la base url con la definida para la accion (/operativos/respuestasOficios/reporte.htm)
				var url = me.getUrl(me.status, options.action);
				
				var win = new Ext.Window({
					  title: 'Visualizador PDF',
					  modal : true,
					  maximizable: true,
					  width: 980,
					  height:  640,
					  plain:true,
					  html: Ext.String.format( '<iframe src="{0}?idPersona={1}&idContrato={2}" width="100%" height="100%" />', url, idPersona, idContrato),
							  						
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
			}
			else
			{
				me.showMessage('text', 'No ha realizado la Búsqueda de Contratos de una Persona', false, null);				
			}
		},
		
		completeCrearReporte : function (completed, options){
			
		},
		
		
		
		
		buildItems: function() {
			var me = this;
			return [Ext.create('gfi.bin.admctasweb.view.operativos.ContratoGrid', {
						id : 'gridContrato',
						title : 'DPVista, Bursátil, Fideicomisos, Créditos',
						layout: 'fit',
						formStatus: [gfi.component.BasicForm.STATUS_NEW,
						          	 gfi.component.BasicForm.STATUS_UPDATE],
						          	 
			          	listeners: {
			          		itemclick: function(dv, record, item, index, e) {
			          			Ext.getCmp('gridContratoCambios').getSelectionModel().deselectAll(true);						                            
			          		}	
			          	}
						          	 
					}),
					{ 
						xtype: "tbspacer",
						height : 10
					},
					Ext.create('gfi.bin.admctasweb.view.operativos.ContratoCambiosGrid', {
						id : 'gridContratoCambios',
						title : 'Central de Cambios',
						layout: 'fit',
						formStatus: [gfi.component.BasicForm.STATUS_NEW,
						          	 gfi.component.BasicForm.STATUS_UPDATE],
			          	listeners: {
			          		itemclick: function(dv, record, item, index, e) {
			          			Ext.getCmp('gridContrato').getSelectionModel().deselectAll(true);                                   
			          		}	
			          	}

					}),		  
					
//                	{
//                        xtype: 'fieldcontainer',
//                        layout: 'hbox',
//                        items: [
//            			    	{
//            			    		xtype: 'fieldset',
//            			    		title: 'Tipo de Respuesta',
//            			    		collapsible: false,
//            			    		width: 260,
//            			    		items: [{
//            			    		    xtype: 'radiogroup',
//            			    		    columns: 2,
//            			    		    vertical : true,
//            			    		    id: "rgTipoRespuesta",
//            			    		    items: [
//            			    		        { boxLabel: 'Total', name: 'tipoRespuesta', inputValue: '1'},
//            			    		        { boxLabel: 'Parcial',  name: 'tipoRespuesta', inputValue: '2'}
//            			    		    ],
//            			    		    width: 250
//            			    		    //formStatus : [gfi.component.BasicForm.SEARCH]
//            			    	    }]
//            			    	},{
//                                    xtype: 'splitter'
//                                },     
//                               
//                            	{
//                                    xtype: 'fieldcontainer',
//                                    layout: 'hbox',
//                                    padding : '15 0 0 0',
//                                    items: [                      
//                                    {
//                                		xtype : 'button',
//                                		text : 'Registra Respuesta Negativa',
//                                		arrowAlign: 'bottom',
//                                		width : 200,
//                                	    handler: function() {me.executeAction('registrarRespNeg');}
//                            		}, {
//                                        xtype: 'splitter'
//                                    }, {
//                                		xtype : 'button',
//                                		text : 'Registra Respuesta Positiva a Cuenta',
//                                		arrowAlign: 'bottom',
//                                		width : 220,
//                                	    handler: function() { me.executeAction('registrarRespPosCta');}
//                            		},{
//                                        xtype: 'splitter'
//                                    },{
//                                		xtype : 'button',
//                                		text : 'Registra Respuesta Positiva a Cliente',
//                                		arrowAlign: 'bottom',
//                                		width : 220,
//                                	    handler: function() { me.executeAction('registrarRespPosCte');}
//                            		}
//                                    
//                                    ]
//                                },	                                
//                        ]
//                    },	
                    
                    
                    
        ];
       
    },
    
    //Verifica si el oficio está en estatus pendiente
    oficioPendiente : function()
    {
    	return Ext.getCmp('sitOficio').getValue()=='PE';    	
    }
    		
});

