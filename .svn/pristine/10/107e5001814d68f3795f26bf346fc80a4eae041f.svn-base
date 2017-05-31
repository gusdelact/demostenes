Ext.define('gfi.bin.admctasweb.view.reportes.BitacoraExcepcionView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.casosespeciales',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.reportes.BitacoraExcepcionGrid'],

	config : {
		header		: 'Bitácora de Excepciones',
		formType	: gfi.FormTypes.CUSTOM,
		baseUrl		: 'reportes/bitacoraExcepcion/',
		modelClass 	: 'gfi.bin.admctasweb.model.reportes.BitacoraExcepcionList',
		pack		: 'stretch',
		secured		: false,

		userActions: {
			limpiar: {
				//actionName 		: 'limpiar',
				buttonText		: 'Limpiar',
				statusConfig	: gfi.component.BasicForm.STATUS_SEARCH,
				pack 			: 'start',
				sync			: false
			},
			buscar: {
				createButton	: true,
				statusConfig	: 'Buscar',
				url				: 'buscar.htm',
				pack 			: 'center',
				sync			: false,
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH]
			},
			imprimir: {
				createButton	: true,
				statusConfig	: 'Buscar',
				url				: 'imprimir.htm',
				pack 			: 'center',
				sync			: true,
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH]
			},
		},	
	},
	
	buildItems : function() {
    	var me = this;
    	
    	//Ligamos el Grid de Documentos Eliminados a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.reportes.BitacoraExcepcionGrid', {
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
	            			ref			: 'txEmpresa', 
	            			selector	: '#txEmpresa',
	            			linkProperty: 'nombre'
	            		}],
	            		labelWidth	: 140,
	            		width 		: 250,
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
	        			itemId 		: 'txEmpresa',
	        			width 		: 405,
	        			formStatus 	: [gfi.component.BasicForm.STATUS_OTHER]
	        		}]
		},
		{
	        xtype	: 'fieldcontainer',
	        layout	: 'hbox',
	        items	: [{
	            		xtype		: 'gfibuscadortrigger', 
	            		grupo		: 'corpo', 
	            		clave		: 'contrato',
	            		fieldLabel 	: 'Contrato:',
	            		name		: 'idContrato',
	            		linkProperty: 'idContrato',
	            		controls	: [{
	            			ref			: 'titular', 
	            			selector	: '#titular',
	            			linkProperty: 'titular'
	            		}],
	            		labelWidth	: 140,
	            		width 		: 250,
	            	}, {
	    	            xtype: 'splitter'
	    	        }, {
	        			xtype 		: 'textfield',
	        			itemId 		: 'titular',
	        			width 		: 405,
	        			formStatus 	: [gfi.component.BasicForm.STATUS_OTHER]
	        		}]
		},
		{
	        xtype	: 'fieldcontainer',
	        layout	: 'hbox',
	        items	: [{
	        			//xtype			: 'textfield',
	            		xtype			: 'gfibuscadortrigger', 
	            		grupo			: 'admctasweb', 
	            		clave			: 'excepcion',
	            		fieldLabel 		: 'Excepci\u00f3n:',
	            		name			: 'cveTipoExcepcion',
	            		linkProperty	: 'cveExcepcion',
	            		controls		: [{
	            			ref				: 'descExcepcion', 
	            			selector		: '#descExcepcion',
	            			linkProperty	: 'descExcepcion'
	            		}],
	            		labelWidth		: 140,
	            		width 			: 250,
	            		onTriggerClick	: function() {
	            			var me = this;
							me.setDefaultFilters([
								new Ext.util.Filter(
										{
											property 	: 'bloqueoAuditoria',
											operator 	: '=',
											value 		: 'V'
							 			})
							 	]);
							me.search();
	                    },
	            	}, {
	    	            xtype: 'splitter'
	    	        }, {
	        			xtype 		: 'textfield',
	        			itemId 		: 'descExcepcion',
	        			width 		: 405,
	        			formStatus 	: [gfi.component.BasicForm.STATUS_OTHER]
	        		}]
		},
		{
	        xtype		: 'fieldcontainer',
	        fieldLabel	: 'Rango de fechas',
	        layout		: 'hbox',
	        labelWidth	: 140,
	        items		: [
	    		{
	    			xtype			: 'datefield',
	    			fieldLabel 		: 'Del',
	    			name 			: 'fInicio',
	    			labelWidth		: 20,
	    			width			: 120,
	    			maxWidth		: 120,
	    			maxLength 		: 10,
	    	        maxLengthText	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	    	        msgTarget 		: 'under',
	    		},
	    		{xtype: 'splitter'},
	    		{
	    			xtype			: 'datefield',
	    			fieldLabel		: 'Al',
	    			name 			: 'fFin',
	    			labelWidth		: 20,
	    			width			: 120,
	    			maxWidth		: 120,
	    			maxLength 		: 10,
	    	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	    	        msgTarget 		: 'under',
	    		}
			]
		},		
		me.grid,
		];		
	},

	//Acción para buscar casos especiales
	doBuscar : function (options)
	{
		var me = this;
		modelo = me.getValues();

		if ( (modelo['idEmpresa'] != null && modelo['idEmpresa'] != '') || 
			 (modelo['idContrato'] != null && modelo['idContrato'] != '') ||  
			 (modelo['cveTipoExcepcion'] != null && modelo['cveTipoExcepcion'] != '') ||
			 (modelo['fInicio'] != null && modelo['fInicio'] != '') ||
			 (modelo['fFin'] != null && modelo['fFin'] != '') ){
			
			if (modelo['cveTipoExcepcion'] != null && modelo['cveTipoExcepcion'] != '') {		
				options.model = me.getValues();
				me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscar);
			} else {
				Ext.Msg.alert('AdmCtasWeb', 'La clave de excepci\u00f3n es requerida');
			}
		} else {
			me.grid.setBitExcepcionList(null);
			Ext.Msg.alert('AdmCtasWeb', 'Debe de seleccionar alg\u00fan filtro para poder realizar la b\u00fasqueda');
		}
	},
	
	completeBuscar : function(completed, options) {
		var me = this;
		
		if (options.error) {
			options.error.showMessage();
		} else {	
			me.grid.setBitExcepcionList(options.info.get('bitExcepcionList'));
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		}
	},
		
	doImprimir : function (options)
	{
		var me = this;
		modelo = me.getValues();

		if ( (modelo['idEmpresa'] != null && modelo['idEmpresa'] != '') || 
			 (modelo['idContrato'] != null && modelo['idContrato'] != '') ||  
			 (modelo['cveTipoExcepcion'] != null && modelo['cveTipoExcepcion'] != '') ||
			 (modelo['fInicio'] != null && modelo['fInicio'] != '') ||
			 (modelo['fFin'] != null && modelo['fFin'] != '') ){
				
			if (modelo['cveTipoExcepcion'] != null && modelo['cveTipoExcepcion'] != '') {		
		
				var url = me.getUrl(me.status, options.action);
				
				var win = new Ext.Window({
					  title			: 'Visualizador PDF',
					  modal 		: true,
					  maximizable	: true,
					  width			: 700,
					  height		: 640,
					  plain			: true,
					  html			: Ext.String.format( '<iframe src="{0}?idEmpresa={1}&idContrato={2}&cveTipoExcepcion={3}&fInicio={4}&fFin={5}" width="100%" height="100%" />',  
							                             url, modelo['idEmpresa'], modelo['idContrato'], modelo['cveTipoExcepcion'],modelo['fInicio'], modelo['fFin']),
					  listeners		: 
					  {
						  render : function(comp) {
							  
							  var mask = new Ext.LoadMask(comp.el, {msg: "Cargando Reporte..."});
						      mask.show();
							  if ( navigator.appName == "Netscape") {
								  setTimeout(function (target) {target.destroy();}, 1500, mask);
							  }                    
						 
						  }
					  }
					});
				win.show();
			} else {
				Ext.Msg.alert('AdmCtasWeb', 'La clave de excepci\u00f3n es requerida');
			}
		} else {
			me.grid.setBitExcepcionList(null);
			Ext.Msg.alert('AdmCtasWeb', 'Debe de seleccionar alg\u00fan filtro para poder imprimir');
		}
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

	//Limpia los campos de la Forma y el Grid.
    doLimpiar: function (options) {
		var me = this;
		
		me.mainPanel.getForm().reset(options.reset);
		me.grid.getStore().removeAll();
		me.grid.getStore().sync();
		
		return true;
	}

});
