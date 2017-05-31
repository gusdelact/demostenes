Ext.define('gfi.bin.admctasweb.view.reportes.CasosEspecialesView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.casosespeciales',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.reportes.CasosEspecialesGrid'],

	config : {
		header		: 'Estad\u00edstico de Casos Especiales',
		formType	: gfi.FormTypes.CUSTOM,
		baseUrl		: 'reportes/casosEspeciales/',
		modelClass 	: 'gfi.bin.admctasweb.model.reportes.CasosEspecialesList',
		pack		: 'stretch',
		secured		: false,

		userActions: {
			buscar: {
				createButton	: true,
				statusConfig	: 'Buscar',
				url				: 'buscar.htm',
				pack 			: 'start',
				sync			: false,
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH]
			},
			limpiar: {
				//actionName 		: 'limpiar',
				buttonText		: 'Limpiar',
				statusConfig	: gfi.component.BasicForm.STATUS_SEARCH,
				pack 			: 'start',
				sync			: false
			},			
			guardar: {
				buttonText		: 'Guardar Acuse',
				createButton	: true,
				statusConfig	: 'Buscar',
				url				: 'guardar.htm',
				pack 			: 'center',
				sync			: false,
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH]
			},
			imprimir: {
				createButton	: true,
				statusConfig	: 'Buscar',
				buttonText		: 'Exportar Excel',
				url				: 'imprimir.htm',
				sync			: true,
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH]
			},
			pintaPdF: {
				createButton	: true,
				statusConfig	: 'Buscar',
				buttonText		: 'Exportar PDF',
				url				: 'impPdf.htm',
				sync			: false,
				formStatus		: [gfi.component.BasicForm.STATUS_SEARCH]
			},
		},	
	},
	
	buildItems : function() {
    	var me = this;
    	
    	//Ligamos el Grid de Documentos Eliminados a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.reportes.CasosEspecialesGrid', {
    		layout		: 'fit',
    		formStatus	: [gfi.component.BasicForm.STATUS_NEW,
    		          	   gfi.component.BasicForm.STATUS_UPDATE]
		}));
		
		return [  

		          {
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items : [
  
	             {
	    		xtype: 'gfibuscadortrigger', 
	    		grupo: 'admctasweb', 
	    		clave: 'oficios',
	    		fieldLabel : 'Número de Oficio:',
	    		name: 'numOficio',
	    		linkProperty: 'numOficio',
	    		labelWidth: 120,
	    		width : 300,
	    		controls: [{
	    			ref: 'tipoOficio', 
	    			selector: '#rdTipoOficioCasosEsp',
	    			linkProperty: 'tipoOficio',
	    		}]
	    	},{
				xtype : 'tbspacer',
				width : 20,
			},{
			    xtype: 'radiogroup',
			    id: "tipoOficioCasosEsp",
			    items: [
			        { boxLabel: 'Judicial',  itemId : 'rdTipoOficioCasosEsp', name: 'tipoOficio', inputValue: 'JU'},
			        { boxLabel: 'Hacendario',  name: 'tipoOficio', inputValue: 'HA'},
			        { boxLabel: 'Aseguramiento', name: 'tipoOficio', inputValue: 'AS' },
			        { boxLabel: 'PLD', name: 'tipoOficio', inputValue: 'PL' }
			    ],
			    width: 500
		    }]
		},
		
		{
		    xtype	: 'fieldcontainer',
		    layout	: 'hbox',
		    items	: [{
		        		xtype		: 'gfibuscadortrigger', 
		        		grupo		: 'admctasweb', 
		        		clave		: 'personas',
		        		fieldLabel 	: 'Persona:',
		        		name		: 'numConsec',
		        		linkProperty: 'numConsec',
		        		controls	: [{
		        			ref			: 'nombrePersona', 
		        			selector	: '#nombrePersona',
		        			linkProperty: 'nombre'
		        		}],
		        		   		
		        		labelWidth	: 120,
		        		width 		: 245,
		        	}, {
			            xtype: 'splitter'
			        }, {
		    			xtype 		: 'textfield',
		    			itemId 		: 'nombrePersona',
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
	            		clave		: 'empresa',
	            		fieldLabel 	: 'Empresa:',
	            		name		: 'idEmpresa',
	            		linkProperty: 'idEmpresa',
	            		controls	: [{
	            			ref			: 'txEmpresa', 
	            			selector	: '#txEmpresa',
	            			linkProperty: 'nombre'
	            		}],
	            		defaultFilters: [
	             		 				new Ext.util.Filter({
	             		 					property: 'idEmpresa',
	             		 					operator: 'in',
	             		 					value: '1,2,83510'
	             		 				})
	             		     		],	            		
	            		labelWidth	: 120,
	            		width 		: 245,
	            	}, {
	    	            xtype: 'splitter'
	    	        }, {
	        			xtype 		: 'textfield',
	        			itemId 		: 'txEmpresa',
	        			width 		: 405,
	        			formStatus 	: [gfi.component.BasicForm.STATUS_OTHER]
	        		}]
		},{
	        xtype	: 'fieldcontainer',
	        layout	: 'hbox',
	        items	: [{
	            		xtype		: 'gfibuscadortrigger', 
	            		grupo		: 'admctasweb', 
	            		clave		: 'autoridades',
	            		fieldLabel	: 'Autoridad:',
	            		name		: 'cveAutoridad',
	            		linkProperty: 'cveAutoridad',
	            		controls	: [{
	            			ref			: 'txAutoridad', 
	            			selector	: '#txAutoridad',
	            			linkProperty: 'nomAutoridad'
	            		}],
	            		labelWidth	: 120,
	            		width 		: 245,
	            	}, {
	    	            xtype: 'splitter'
	    	        }, {
	        			xtype 		: 'textfield',
	        			itemId 		: 'txAutoridad',
	        			width 		: 405,
	        			formStatus	: [gfi.component.BasicForm.STATUS_OTHER]
	        		}]
		},{
	        xtype		: 'fieldcontainer',
	        fieldLabel	: 'Fecha Recepci\u00f3n',
	        layout		: 'hbox',
	        labelWidth	: 120,
	        items		: [
	    		{
	    			xtype			: 'datefield',
	    			fieldLabel 		: 'Del',
	    			name 			: 'fIniRecepcion',
	    			labelWidth		: 20,
	    			width			: 120,
	    			maxWidth		: 120,
	    			maxLength 		: 10,
	    	        maxLengthText	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	    	        msgTarget 		: 'under',
	    		},{
	    			xtype: 'tbspacer',
		            width : 15
		        },{
	    			xtype			: 'datefield',
	    			fieldLabel		: 'Al',
	    			name 			: 'fFinRecepcion',
	    			labelWidth		: 20,
	    			width			: 120,
	    			maxWidth		: 120,
	    			maxLength 		: 10,
	    	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	    	        msgTarget 		: 'under',
	    		}
			]
		},{
	        xtype		: 'fieldcontainer',
	        fieldLabel	: 'Fecha Respuesta',
	        layout		: 'hbox',
	        labelWidth	: 120,
	        items		: [
	        		{
	        			xtype			: 'datefield',
	        			fieldLabel		: 'Del',
	        			name 			: 'fIniRespuesta',
	        			labelWidth		: 20,
	        			width			: 120,
	        			maxWidth		: 120,
	        			maxLength 		: 10,
	        	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        	        msgTarget 		: 'under',
	        		},{
		    			xtype: 'tbspacer',
			            width : 15
			        },{
	        			xtype			: 'datefield',
	        			fieldLabel 		: 'Al',
	        			name 			: 'fFinRespuesta',
	        			labelWidth		: 20,
	        			width			: 120,
	        			maxWidth		: 120,
	        			maxLength 		: 10,
	        	        maxLengthText	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
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

		if ( (modelo['fIniRecepcion'] != null && modelo['fIniRecepcion'] != '') || 
			 (modelo['fFinRecepcion'] != null && modelo['fFinRecepcion'] != '') ||  
			 (modelo['fIniRespuesta'] != null && modelo['fIniRespuesta'] != '') ||
			 (modelo['fFinRespuesta'] != null && modelo['fFinRespuesta'] != '') ||
			 (modelo['idEmpresa'] != null && modelo['idEmpresa'] != '') ||
			 (modelo['cveAutoridad'] != null && modelo['cveAutoridad'] != '') || 
			 (modelo['numOficio'] != null && modelo['numOficio'] != '') ||
			 (modelo['tipoOficio'] != null && modelo['tipoOficio'] != '') ||
			 (modelo['numConsec'] != null && modelo['numConsec'] != '')){
		
			options.model = me.getValues();
			me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscar);
		} else {
			me.grid.setCasoEspecialList(null);
			Ext.Msg.alert('AdmCtasWeb', 'Debe de seleccionar alg\u00fan filtro para poder realizar la b\u00fasqueda');
		}
	},
	
	completeBuscar : function(completed, options) {
		var me = this;
		
		if (options.error) {
			options.error.showMessage();
		} else {	
			me.grid.setCasoEspecialList(options.info.get('casoEspecialList'));
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		}
	},
		
  //Complementamos los valores del Grid con la Forma.
	internalGetValues: function () {
        var me = this,	
        	model,
        	casoEspecialModList = [];
        
        model = me.callParent(arguments);
        
        var updatedRecords = me.grid.getStore().getUpdatedRecords();
        Ext.Array.each(updatedRecords, function (item) {
        	casoEspecialModList.push(item.data);
        });

        model.casoEspecialModList = casoEspecialModList;

        return model;
    },	
	
	doGuardar: function(options) {
		var me = this;
		
		options.model = me.getValues();
		me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeGuardar);
	},

	completeGuardar: function(completed, options) {
		var me = this;
		//console.log('CasosEspecialesView.completeGuardar... ', completed, options);
		
		if (options.error) {
			options.error.showMessage();
		} else {
			me.grid.setCasoEspecialList(options.info.get('casoEspecialList'));
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		}
	},
	
	doImprimir : function (options)
	{
		var me = this;
		modelo = me.getValues();
		
		if ( (modelo['fIniRecepcion'] != null && modelo['fIniRecepcion'] != '') || 
				 (modelo['fFinRecepcion'] != null && modelo['fFinRecepcion'] != '') ||  
				 (modelo['fIniRespuesta'] != null && modelo['fIniRespuesta'] != '') ||
				 (modelo['fFinRespuesta'] != null && modelo['fFinRespuesta'] != '') ||
				 (modelo['idEmpresa'] != null && modelo['idEmpresa'] != '') ||
				 (modelo['cveAutoridad'] != null && modelo['cveAutoridad'] != '') ||
				 (modelo['numOficio'] != null && modelo['numOficio'] != '') ||
				 (modelo['tipoOficio'] != null && modelo['tipoOficio'] != '') ||
				 (modelo['numConsec'] != null && modelo['numConsec'] != '')){
			
			var url = me.getUrl(me.status, options.action);
			
			var win = new Ext.Window({
				  title			: 'Visualizador PDF',
				  modal 		: true,
				  maximizable	: true,
				  width			: 150,
				  height		: 10,
				  plain			: true,
				  html			: Ext.String.format( '<iframe src="{0}?idEmpresa={1}&cveAutoridad={2}&tipoOficio={3}&fIniRecepcion={4}&fFinRecepcion={5}&fIniRespuesta={6}&fFinRespuesta={7}&numOficio={8}&numConsec={9}" width="100%" height="100%" />',  
						                             url, modelo['idEmpresa'], modelo['cveAutoridad'], modelo['tipoOficio'], modelo['fIniRecepcion'], modelo['fFinRecepcion'], modelo['fIniRespuesta'], modelo['fFinRespuesta'],modelo['numOficio'],modelo['numConsec']),
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
			win.hide();
		} else {
			me.grid.setCasoEspecialList(null);
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
	},
	
	doPintaPdF : function (options)
	{
		var me = this;
		modelo = me.getValues();
		
		if ( (modelo['fIniRecepcion'] != null && modelo['fIniRecepcion'] != '') || 
				 (modelo['fFinRecepcion'] != null && modelo['fFinRecepcion'] != '') ||  
				 (modelo['fIniRespuesta'] != null && modelo['fIniRespuesta'] != '') ||
				 (modelo['fFinRespuesta'] != null && modelo['fFinRespuesta'] != '') ||
				 (modelo['idEmpresa'] != null && modelo['idEmpresa'] != '') ||
				 (modelo['cveAutoridad'] != null && modelo['cveAutoridad'] != '') ||
				 (modelo['numOficio'] != null && modelo['numOficio'] != '') ||
				 (modelo['tipoOficio'] != null && modelo['tipoOficio'] != '') ||
				 (modelo['numConsec'] != null && modelo['numConsec'] != '')){
			
			var url = me.getUrl(me.status, options.action);
			
			var win = new Ext.Window({
				  title			: 'Visualizador PDF',
				  modal 		: true,
				  maximizable	: true,
				  width			: 800,
				  height		: 600,
				  plain			: true,
				  html			: Ext.String.format( '<iframe src="{0}?idEmpresa={1}&cveAutoridad={2}&tipoOficio={3}&fIniRecepcion={4}&fFinRecepcion={5}&fIniRespuesta={6}&fFinRespuesta={7}&numOficio={8}&numConsec={9}" width="100%" height="100%" />',  
                          url, modelo['idEmpresa'], modelo['cveAutoridad'], modelo['tipoOficio'], modelo['fIniRecepcion'], modelo['fFinRecepcion'], modelo['fIniRespuesta'], modelo['fFinRespuesta'],modelo['numOficio'],modelo['numConsec']),
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
			me.grid.setCasoEspecialList(null);
			Ext.Msg.alert('AdmCtasWeb', 'Debe de seleccionar alg\u00fan filtro para poder imprimir');
		}
	},

	completePintaPdF : function (completed, options){
		var me = this;
		if(completed)
		{
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		} else {
			me.showErrorMessage(options.error || options.message);
		}
	}
	
});
