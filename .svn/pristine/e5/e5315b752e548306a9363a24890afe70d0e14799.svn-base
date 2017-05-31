Ext.define('gfi.bin.admctasweb.view.operativos.ArchivoCnbvView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.archivoCnbv',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.operativos.ArchivoCnbvGrid'],

	config : {
		header		: 'Archivo para la CNBV',
		formType	: gfi.FormTypes.CUSTOM,
		baseUrl		: 'operativos/archivoCnbv/',
		modelClass 	: 'gfi.bin.admctasweb.model.operativos.ArhivoCnbvModel',
		pack		: 'stretch',
		secured		: false,

		userActions: {
			buscar: {
				createButton	: true,
				statusConfig	: 'Buscar',
				url				: 'buscar.htm',
				sync			: false,
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH]
			},
			generar: {
				buttonText		: 'Generar Archivo',
				createButton	: true,
				statusConfig	: 'Buscar',
				url				: 'generarArchivo.htm',
				sync			: false,
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH]
			},
			imprimir: {
				buttonText		: 'Descargar',
				createButton	: true,
				statusConfig	: 'Buscar',
				url				: 'descargar.htm',
				sync			: false,
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH]
			},
			limpiar : {
				createButton: true,
				buttonText  : 'Limpiar',
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH],
				secured: false,
			},
		},	
	},
	
	buildItems : function() {
    	var me = this;
    	
    	//Ligamos el Grid de Documentos Eliminados a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.operativos.ArchivoCnbvGrid', {
    		layout		: 'fit',
    		formStatus	: [gfi.component.BasicForm.STATUS_NEW,
    		          	   gfi.component.BasicForm.STATUS_UPDATE]
		}));
		
		return [ 	
		{
	        xtype	: 'fieldcontainer',
	        layout	: 'hbox',
	        items	: [{
	            		xtype		: 'gfibuscadortrigger', 
	            		grupo		: 'corpo', 
	            		clave		: 'empresa',
	            		fieldLabel 	: 'Empresa:',
	            		name		: 'idEmpresa',
	            		linkProperty: 'idEmpresa',
	            		controls	: [{
	            			ref			: 'nombre', 
	            			selector	: '#nombre',
	            			linkProperty: 'nombre'
	            		}],
	            		labelWidth	: 140,
	            		width 		: 264,
	            		defaultFilters: [
	             		 				new Ext.util.Filter({
	             		 					property: 'idEmpresa',
	             		 					operator: 'in',
	             		 					value: '1,2,83510'
	             		 				})
	             		     		],
	            	}, {
	    	            xtype: 'splitter'
	    	        }, {
	        			xtype 		: 'textfield',
	        			itemId 		: 'nombre',
	        			name 		: 'nombre',
	        			width 		: 405,
	        			formStatus 	: [gfi.component.BasicForm.STATUS_OTHER]
	        		}]
		},	
		{
	        xtype		: 'fieldcontainer',
	        fieldLabel	: 'Fecha oficio del',
	        layout		: 'hbox',
	        labelWidth	: 140,
	        items		: [
	    		{
	    			xtype			: 'datefield',
	    			name 			: 'fhVencimientoIni',
	    			labelWidth		: 20,
	    			width			: 120,
	    			maxWidth		: 120,
	    			maxLength 		: 10,
	    	        maxLengthText	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	    	        msgTarget 		: 'under',
	    	        value			: new Date(),
	    		},
	    		{xtype: 'splitter'},
	    		{
	    			xtype			: 'datefield',
	    			fieldLabel		: 'Al',
	    			name 			: 'fhVencimientoFin',
	    			labelWidth		: 20,
	    			width			: 120,
	    			maxWidth		: 120,
	    			maxLength 		: 10,
	    	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	    	        msgTarget 		: 'under',
	    	        value			: new Date(),
	    		}
			]
		},	
		{
	        xtype		:'fieldcontainer',	        
	        fieldLabel	: 'Tipo de Oficio',	        
	        allowBlank	: false,
	        collapsible	: false,
	        defaultType	: 'textfield',
	        width		: 300,
	        labelWidth	: 140,
	        items 		:[{
			    xtype		: 'radiogroup',
			    vertical	: true,
			    items		: [			        
			        { boxLabel: 'Judicial', name: 'tipoOficio', inputValue: 'JU', width:80,checked:true},
			        { boxLabel: 'Hacendario', name: 'tipoOficio', inputValue: 'HA', width:100},
			        { boxLabel: 'Aseguramiento', name: 'tipoOficio', inputValue: 'AS', width:120},
			        { boxLabel: 'PLD', name: 'tipoOficio', inputValue: 'PL', width:50}
			    ],
			}],
	    },
	    {
	        xtype		:'fieldcontainer',	        
	        fieldLabel	: 'Situación',	        
	        allowBlank	: false,
	        collapsible	: false,
	        defaultType	: 'textfield',
	        width		: 300,
	        labelWidth	: 140,
	        items 		:[{
			    xtype		: 'radiogroup',
			    vertical	: true,
			    items		: [
			        { boxLabel: 'Pendiente', name: 'sitOficio', inputValue: 'PE', width:80},
			        { boxLabel: 'Terminado', name: 'sitOficio', inputValue: 'TE', width:80, checked:true},
			        { boxLabel: 'Enviado', name: 'sitOficio', inputValue: 'EN', width:80}
			    ],
			}],
	    },
		me.grid,
		{ 
			xtype: "tbspacer",
			height : 10
		},
		 {
			xtype 		: 'textfield',
			itemId 		: 'ruta',
			name 		: 'ruta',
			id 			: 'ruta',
			width 		: 405,
			formStatus 	: [gfi.component.BasicForm.STATUS_OTHER]
		}
		];		
	},

	//Acción para buscar casos especiales
	doBuscar : function (options)
	{
		var me = this;
		modelo = me.getValues();

		if ( (modelo['fhVencimientoIni'] != null && modelo['fhVencimientoIni'] != '') || 
			 (modelo['fhVencimientoFin'] != null && modelo['fhVencimientoFin'] != '') ||  
			 (modelo['sitOficio'] != null && modelo['sitOficio'] != '') ||
			 (modelo['idEmpresa'] != null && modelo['idEmpresa'] != '') ||
			 (modelo['tipoOficio'] != null && modelo['tipoOficio'] != '') ){
		
			options.model = me.getValues();
			me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscar);
		} else {
			me.grid.setCasoEspecialList(null);
			Ext.Msg.alert('AdmCtasWeb', 'Debe de seleccionar alg\u00fan filtro para poder realizar la b\u00fasqueda');
		}
		
	},
	
	completeBuscar : function(completed, options) {
		var me = this;
		Ext.getCmp('ruta').setValue("");
		if (options.error) {
			options.error.showMessage();
		} else {	
			
			me.grid.setListaOficiosResult(options.info.get('listaOficiosResult'));
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		}
	},
		
  //Complementamos los valores del Grid con la Forma.
	internalGetValues: function () {
        var me = this,	
        	model,
        	listaOficiosResultGrid = [];
        
        model = me.callParent(arguments);
        
        var updatedRecords = me.grid.getSelectionModel().getSelection();
        Ext.Array.each(updatedRecords, function (item) {
        	listaOficiosResultGrid.push(item.data);
        });

        model.listaOficiosResultGrid = listaOficiosResultGrid;

        return model;
    },	
	
	doGenerar: function(options) {
		var me = this;		
		console.log(me.grid.getStore().getUpdatedRecords().length);
		if (me.grid.getSelectionModel().hasSelection()) {
			options.model = me.getValues();
			me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeGenerar);
		}else{
			me.showMessage('text', 'Debe seleccionar al menos un oficio para poder generar el archivo', false, null);
		}		
	},

	completeGenerar: function(completed, options) {
		var me = this;
		console.log('archivoCnbv.completeGenerar... ', completed, options);		
		if (options.error) {
			options.error.showMessage();
		} else {		
			Ext.getCmp('ruta').setValue(options.info.get('ruta'));
			me.grid.setListaOficiosResult(options.info.get('listaOficiosResult'));
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		}
	},
	
	doImprimir : function (options)
	{
		var me = this;		
		console.log(me.grid.getStore().getUpdatedRecords().length);
		
			modelo = me.getValues(); 
			var url = me.getUrl(me.status, options.action);
			console.log('url.url... '+url);	
			var win = new Ext.Window({
				  title			: 'Descargan de archivo',
				  modal 		: true,
				  maximizable	: true,
				  width			: 150,
				  height		: 10,
				  plain			: true,
				  html			: Ext.String.format( '<iframe src="{0}" width="100%" height="100%" />',  
						                             url ),
				  listeners		: 
				  {
					  render : function(comp) {						  
						  var mask = new Ext.LoadMask(comp.el, {msg: "Descargando..."});
					      mask.show();
						  if ( navigator.appName == "Netscape") {
							  setTimeout(function (target) {target.destroy();}, 1500, mask);
						  }                    
					 
					  }
				  }
				});
			win.show();
			win.close();
			
	},

	completeImprimir : function (completed, options){
		var me = this;
		if(completed)
		{
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		} else {
			me.showErrorMessage(options.error || options.message);
		}
	},
	
	doLimpiar: function (options) {
		var me = this;		
		me.mainPanel.getForm().reset(options.reset);
		me.grid.setListaOficiosResult([]);
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
	
});
