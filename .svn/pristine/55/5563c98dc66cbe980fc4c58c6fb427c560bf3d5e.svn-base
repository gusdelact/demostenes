Ext.define('gfi.bin.admctasweb.view.operativos.RespuestasOficiosView', {
	extend : 'gfi.corp.component.form.BasicForm',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.corp.component.form.Types',
		        'gfi.bin.admctasweb.view.catalogos.RespContratoGrid'],
	
	config : {
		header: 'Registro de Observaciones e Impresión de Respuestas',
		formType: gfi.FormTypes.CATALOGO,
		grupo: 'admctasweb', 
		clave: 'respuestaOficio',
		modelClass : 'gfi.bin.admctasweb.model.operativos.RespuestaOficio',
		primaryKeys : ['numOficio','tipoOficio', 'idRespuesta'],
		baseUrl: 'operativos/respuestasOficios/',
		secured: false,
		
		//Solo se permite la Opción de Crear y Modificar
		userActions: {
			borrar: {//Se sobreescribe acción para ocultar el botón
				createButton: false,
			},
			
			obtenerRequerimientos : {
				//buttonText: '',
				createButton: false,
				statusConfig: [gfi.component.BasicForm.STATUS_NEW, gfi.component.BasicForm.STATUS_UPDATE],
				url: 'obtenerrequerimientos.htm',
				sync: false,
			},
			
			obtenerSolicitudes : {
				//buttonText: '',
				createButton: false,
				statusConfig: [gfi.component.BasicForm.STATUS_NEW, gfi.component.BasicForm.STATUS_UPDATE],
				url: 'obtenersolicitudes.htm',
				sync: false,
			},
			marcarOficio: {
				buttonText: 'Marcar Oficio como Cerrado',
				statusConfig: 'Activo',
				url: 'situacionenvio.htm',
				sync: false,
				//pack: 'center',
			},	
			obtenerDescripcion : {
				createButton: false,
				statusConfig: [gfi.component.BasicForm.STATUS_NEW, gfi.component.BasicForm.STATUS_UPDATE],
				url: 'obtenerdescripcion.htm',
				sync: false,
				//pack: 'center',
			},	
					
			imprimir: {
				buttonText: 'Imprimir',
				statusConfig: 'Activo',
				url: 'reporte.htm',
				sync: true,
				//pack: 'center',
			},			
			
		},		
	},
	
	getMinWidth : function() {
		return 890;
	},

	getWidth : function() {
		return 890;
	},
	
	doObtenerRequerimientos : function (options)
	{
		var me = this;
//		var req = Ext.create('gfi.bin.admctasweb.model.operativos.RespuestaOficio', {
//			tipoOficio : Ext.getCmp('respOfiTipoOficio').getValue()
//		});				   
		
		options.model = me.getValues();
		me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeObtenerRequerimientos);			
		
	},
	
	completeObtenerRequerimientos : function(completed, options)
	{
		var me = this;
		if(completed)
		{
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
			var info = options.info;			
    		var data = info.get('requerimientosList');
    		data = data || [];
    		
			var cboReq = Ext.getCmp('tipoRequerimiento');
    		cboReq.clearValue();
    		cboReq.getStore().getProxy().data = data;              		
    		cboReq.getStore().load();
    		
//    		dataDir = info.get('direccionesList');
//    		dataDir = dataDir || [];
//			var cboDir = Ext.getCmp('respOfiDireccion');
//    		cboDir.clearValue();
//			cboDir.getStore().getProxy().data = dataDir;              		
//			cboDir.getStore().load();
   		
    		var cboSol = Ext.getCmp('tipoSolicitud');
    		cboSol.clearValue();
    		cboSol.getStore().removeAll();  
    		
    		var txtObservaciones = Ext.getCmp('respOfiObservaciones');
    		txtObservaciones.reset();
    		
    		var txtApoderado = Ext.getCmp('respOfiApoderado');
    		txtApoderado.reset();
    		
		}
		else
		{
			me.showErrorMessage(options.error || options.message);
		}			
	},
	
	doObtenerSolicitudes : function (options)
	{
		var me = this;
		options.model = me.getValues();
		me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeObtenerSolicitudes);			
		
	},
	
	completeObtenerSolicitudes : function(completed, options)
	{
		var me = this;
		if(completed)
		{
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
			var info = options.info;//Se accede a este objeto porque el retorno no coincide con el modelo de esta pantalla				
    		var data = info.get('solicitudesList');
    		data = data || [];
    		
			var cboSol = Ext.getCmp('tipoSolicitud');
			cboSol.clearValue();
			cboSol.getStore().getProxy().data = data;              		
			cboSol.getStore().load();
			
			//Se precargan datos del oficio
			if(me.status == 'Nuevo'){
				var dataOficio = info.get('observaciones');
				var txtObservaciones = Ext.getCmp('respOfiObservaciones');
				txtObservaciones.setValue(dataOficio);
				
				var txtApoderado = Ext.getCmp('respOfiApoderado');
				txtApoderado.setValue(info.get('apoderado'));
			}
		}
		else
		{
			me.showErrorMessage(options.error || options.message);
		}			
	},
		
	doMarcarOficio : function (options)
	{
		var me = this;
		if(me.respuestaEnviada()){
			me.showMessage('text', 'El Oficio ya ha sido Marcado como Enviado', false, null);
			return;
		}

        Ext.MessageBox.confirm(
				me.getMessageTitle(), 
				'<p align="center">\u00BFEsta seguro que desea Marcar al Oficio como Cerrado?</p>', 
				function(btn) {
					if (btn === 'yes') 
					{						
						options.model = me.getValues();
						me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeMarcarOficio);			
					}
				}, me);
	},
	
	completeMarcarOficio : function (completed, options){
		var me = this;
		if(completed)
		{
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
			Ext.getCmp('respOfiEnvio').setValue(options.info.get('respuestaEnviada'));
		}
		else
		{
			me.showErrorMessage(options.message);
		}			
		
	},
	
	doImprimir : function (options)
	{
		var me = this;	
		
//		if(me.respuestaEnviada()){
//			me.showMessage('text', 'El Oficio ha sido Marcado como Enviado', false, null);
//			return;
//		}
		
		modelo = me.getValues();	
		//Esta concatena la base url con la definida para la accion (/operativos/respuestasOficios/reporte.htm)
		var url = me.getUrl(me.status, options.action);
		
		var win = new Ext.Window({
			  title: 'Visualizador PDF',
			  modal : true,
			  maximizable: true,
			  width: 700,
			  height:  640,
			  plain:true,
			  html: Ext.String.format( '<iframe src="{0}?numOficio={1}&tipoOficio={2}&idRespuesta={3}" width="100%" height="100%" />', url,
					  encodeURIComponent(modelo['numOficio']), modelo['tipoOficio'], modelo['idRespuesta']),
					  						
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
	
	doObtenerDescripcion : function(options)
	{
		var me = this;
		options.model = me.getValues();
		me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeObtenerDescripcion);			
		
	},
	
	completeObtenerDescripcion: function (completed, options){
		var me = this;
		if(completed)
		{
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
			if(me.status == 'Nuevo'){
				var dataOficio = options.info.get('observaciones');
				var txtObservaciones = Ext.getCmp('respOfiObservaciones');
				txtObservaciones.setValue(dataOficio);
				
				var txtApoderado = Ext.getCmp('respOfiApoderado');
				txtApoderado.setValue(options.info.get('apoderado'));				
			}
		}
		else
		{
			me.showErrorMessage(options.error || options.message);
		}			
		
	},
	
	
	completeSeleccionar: function (completed, options){
		var me = this;	
		
		//Carga de stores de combos
		var dataReq = options.model.get('requerimientosList');
		dataReq = dataReq || [];
		var cboReq = Ext.getCmp('tipoRequerimiento');
//		cboReq.clearValue();
		cboReq.getStore().getProxy().data = dataReq;
		cboReq.getStore().load();
		
		var dataSol = options.model.get('solicitudesList');
		dataSol = dataSol || [];
		var cboSol = Ext.getCmp('tipoSolicitud');
//		cboSol.clearValue();
		cboSol.getStore().getProxy().data = dataSol;              		
		cboSol.getStore().load();	
			
//		var dataDir = options.model.get('direccionesList');
//		dataDir = dataDir || [];
//		var cboDir = Ext.getCmp('respOfiDireccion');
//		cboDir.clearValue();
//		cboDir.getStore().getProxy().data = dataDir;
//		cboDir.getStore().load();
		
		me.contratos();
		
		
		me.actions.seleccionar.completeAction.call(me, completed, options);		
    },
	
    //Se sobreescribe para limpiar stores de combos cuando se va a crear una nueva respuesta
    completeCrear: function (completed, options){
    	var me = this;	
    	
		var cboReq = Ext.getCmp('tipoRequerimiento');
		cboReq.clearValue();
		cboReq.getStore().removeAll();  
		
		var cboSol = Ext.getCmp('tipoSolicitud');
		cboSol.clearValue();
		cboSol.getStore().removeAll();             		

//		var cboDir = Ext.getCmp('respOfiDireccion');
//		cboDir.clearValue();
//		cboDir.getStore().removeAll();
		
    	me.actions.crear.completeAction.call(me, completed, options);
    },
    
    doEditar : function (options)
    {
    	var me = this;
    	
		if(me.respuestaEnviada()){
			me.showMessage('text', 'El Oficio ha sido Marcado como Enviado', false, null);
			return;
		}
    	
    	//options.model = me.getValues();
    	var completed = me.actions.editar.doAction.call(me, options);
    	me.actions.editar.completeAction.call(me, completed, options);
    },
    
    
    
    
    
	buildItems : function() {
		var me = this;
		return [ 
				{
					xtype : 'hiddenfield',
					//xtype : 'textfield',
					fieldLabel : 'Id respuesta:',
					name : 'idRespuesta',
					itemId : 'txIdRespuesta',
					labelWidth : 110,
					width : 417,
					readOnly : true
				},	
				
				{
					xtype : 'hiddenfield',
					//xtype : 'textfield',
					fieldLabel : 'Envio:',
					id: 'respOfiEnvio',
					name : 'respuestaEnviada',
					labelWidth : 110,
					width : 417,
				},		        
				
				
				{
					xtype: 'gfibuscadortrigger', 
					id: 'respOfiOficio',
					grupo: 'admctasweb',
					clave: 'oficios',
					fieldLabel : 'Número de Oficio:',
					emptyText : 'SELECCIONE UN OFICIO',
					name: 'numOficio',
					linkProperty: 'numOficio',
					controls: [{
						ref: 'tipoOficio',
						selector: '#cboTipoOficio',
						linkProperty: 'tipoOficio'
					},{
						ref: 'cveAutoridad',
						selector: '#txAutoridad',
						linkProperty: 'cveAutoridad'
					},{
						ref: 'fhOficio',
						selector: '#dateFechaOficio',
						linkProperty: 'fhOficio'
					},{
						ref: 'tipoCaso',
						selector: '#cboTipoResp',
						linkProperty: 'tipoCaso'
					},
					
					{
						ref: 'direccion',
						selector: '#txtDireccion',
						linkProperty: 'direccion'
					},
					{
						ref: 'gerencia',
						selector: '#txtGerencia',
						linkProperty: 'gerencia'
					},
					
					],
					labelWidth: 110,
					width : 417,
            		formStatus: [gfi.component.BasicForm.STATUS_NEW],
             		//Se establece filtro por defecto para buscar oficios positivos
            		defaultFilters: [new Ext.util.Filter({
            		                     property: 'tipoCaso',
            		                     operator: '=',
            		                     value: 'PO'
            		                 }),
            		                 new Ext.util.Filter({
            		                     property: 'sitOficio',
            		                     operator: 'in',
            		                     value: "'TE', 'TA'"
            		                 })],            		
             		//Se sobreescribe evento de selección para cargar combo de requerimiento          	 
            	    selectRecord: function (model, format){
            	    	
             			var meb = this, result;              		
             			meb.setEventInfo();
                		result = meb.fireEvent('onSelectRecord', model, meb.eventInfo);
                		
                		if (result) {
                			meb.selectedRecord = model;
                			meb.displayRecord(meb.getResultText(model, format), model, meb.eventInfo);
                			meb.displayControls(model, meb.eventInfo);
                		}
                		//Carga de combo de requerimiento segun tipo de oficio
                		me.executeAction('obtenerRequerimientos');
                		return result;
            	    },
				},		
            	{
                    xtype: 'fieldcontainer',
                    layout: 'hbox',
                    items: [                               
					{
						xtype : 'combobox',
						fieldLabel : 'Tipo de Oficio:',
						id : 'respOfiTipoOficio',
						name : 'tipoOficio',
						itemId : 'cboTipoOficio',
						store:  Ext.create('Ext.data.Store', {
						    fields: ['tipo', 'tipoName'],
						    data : [
						        {"tipo":"JU", "tipoName":"Judicial"},
						        {"tipo":"HA", "tipoName":"Hacendario"},
						        {"tipo":"AS", "tipoName":"Aseguramiento"},
						        {"tipo":"PL", "tipoName":"PLD"}
						        //...
						    ]
						}),
						displayField: 'tipoName',
						valueField: 'tipo',
						labelWidth: 110,
						width : 417,
						//readOnly : true
						formStatus : [gfi.component.BasicForm.SEARCH]
					},
            		{
                        xtype: 'tbspacer',
                        width : 15
                    }, 
                    {
                		xtype : 'textfield',
                		fieldLabel : 'Clave de Autoridad:',                		
                		name : 'claveAutoridad',
                		itemId : 'txAutoridad',
                		labelWidth: 110,
                		width : 417,
                		formStatus : [gfi.component.BasicForm.SEARCH]
            		}
                    ]
                },
            	{
                    xtype: 'fieldcontainer',
                    layout: 'hbox',
                    items: [                               
					{
						xtype : 'combobox',
						fieldLabel : 'Tipo de Respuesta:',
						name : 'tipoCaso',
						itemId : 'cboTipoResp',
						store:  Ext.create('Ext.data.Store', {
						    fields: ['tipo', 'tipoName'],
						    data : [
						        {"tipo":"PO", "tipoName":"Positivo"},
						        {"tipo":"NE", "tipoName":"Negativo"},
						        //...
						    ]
						}),
						displayField: 'tipoName',
						valueField: 'tipo',
						labelWidth: 110,
						width : 417,
						//readOnly : true
						formStatus : [gfi.component.BasicForm.SEARCH]
					},
            		{
                        xtype: 'tbspacer',
                        width : 15
                    }, 
                    {
    					xtype : 'datefield',
    					fieldLabel : 'Fecha de Oficio:',
    					itemId : 'dateFechaOficio',
    					name : 'fechaOficio',
    					labelWidth: 110,
    					width : 417,
    					format : 'd/m/Y',
    					formStatus : [gfi.component.BasicForm.SEARCH]
    				}                    
                   ]
                },
            	{
                    xtype: 'fieldcontainer',
                    layout: 'hbox',
                    items: [                               
        				{
        					xtype : 'textfield',
        					fieldLabel : 'Dirección:',
        					//id : 'respOfiDireccion',
        					itemId : 'txtDireccion',
        					name : 'direccion',      					
        					labelWidth: 110,
        					width : 417,
        					formStatus : [gfi.component.BasicForm.SEARCH]        				
        			   },      
	                   {
	                        xtype: 'tbspacer',
	                        width : 15
	                    }, 
	                    {
	                		xtype : 'textfield',
	                		fieldLabel : 'Gerencia:',                		
	                		name : 'gerencia',
	                		itemId : 'txtGerencia',
	                		labelWidth: 110,
	                		width : 417,
	                		formStatus : [gfi.component.BasicForm.SEARCH]
	            		}
                    ]
                },
            	{
                    xtype: 'fieldcontainer',
                    layout: 'hbox',
                    items: [   
                            
					{
						xtype : 'combobox',
						fieldLabel : 'Requerimiento:',
						id : 'tipoRequerimiento',
						name : 'tipoRequerimiento',
						editable : false,
						emptyText : 'Seleccione',
						queryMode: 'local',
						store: Ext.create('gfi.bin.admctasweb.store.operativos.ComboStore', {storeId : 'storeTipReq'}),
						displayField: 'descripcion',
						valueField: 'clave',
						labelWidth: 110,
						width : 417,						
						listeners: {
						    select: function(combo, record, index) {
						    	//Carga de combo de solicitudes
						    	me.executeAction('obtenerSolicitudes');
						    
						    }
						  },						
						
	             		 formStatus: [gfi.component.BasicForm.STATUS_NEW,
	             		          	 gfi.component.BasicForm.STATUS_UPDATE]
					},                           
					{
						xtype: 'tbspacer',
						width : 15
					}, 
					{
						xtype : 'combobox',
						fieldLabel : 'Solicitud:',
						id : 'tipoSolicitud',
						name : 'tipoSolicitud',
						editable : false,
						emptyText : 'Seleccione',
						queryMode: 'local',
						store: Ext.create('gfi.bin.admctasweb.store.operativos.ComboStore', {storeId : 'storeTipSol'}), 
						displayField: 'descripcion',
						valueField: 'clave',
						labelWidth: 110,
						width : 417,
						listeners: {
						    select: function(combo, record, index) {
						    	//Carga de combo de solicitudes
						    	me.executeAction('obtenerDescripcion');
						    
						    }
						  },						
						
	             		 formStatus: [gfi.component.BasicForm.STATUS_NEW,
	             		          	 gfi.component.BasicForm.STATUS_UPDATE]
					},
                   ]
                },                 
           
                new Ext.panel.Panel({
                    title: 'Observaciones',
                    renderTo: Ext.getBody(),
                    width: 850,
                    height: 250,
                    frame: false,
                    layout: 'fit',
                    items: [
                    {
                        xtype: 'htmleditor',
                        name      : 'observaciones',
                        id 	   : 'respOfiObservaciones',
                        height : 250,
                        //enableFont : false, //Causa error en la app
                        enableAlignments : false,//La alineación ya esta configurada en jasper
                        enableFontSize: false,
                        enableLinks : false,
                        fontFamilies : ['Tahoma'],//Por ahora jasper solo puede generar reporte pdf con esta fuente
                        enableSourceEdit : false,
    	           		formStatus: [gfi.component.BasicForm.STATUS_NEW,
    	          		          	 gfi.component.BasicForm.STATUS_UPDATE],
                    },  
                    
                    ]
                    
                }),
                
				{ 
					xtype: "tbspacer",
					height : 5
				},
                
                {
            		xtype : 'textfield',
            		fieldLabel : 'Apoderado:',  
            		id : 'respOfiApoderado',
            		name : 'apoderado',
            		labelWidth: 70,
            		width : 850,
	           		formStatus: [gfi.component.BasicForm.STATUS_NEW,
	          		          	 gfi.component.BasicForm.STATUS_UPDATE],
        		},
        		{
					xtype	: 'respContratogrid',
					name	: 'gridrespContrato',
					itemId	: 'gridrespContrato',
				    height	: 250
				}
                
                
		];
	},
	
    respuestaEnviada : function()
    {
    	return Ext.getCmp('respOfiEnvio').getValue()=='V';
    },
    
    //Ejecuta la Visualización de los Insumos seleccionados.
   	contratos : function (){
		var me = this;
		var numOficio = Ext.getCmp('respOfiOficio').getValue();
		
		var insGrid = me.down('#gridrespContrato');
		
		//Agrego parametros al Store y recargamos el grid.
		insGrid.getStore().proxy.extraParams = {numOficio: numOficio};
		insGrid.getStore().load();
	}
	
		
});
