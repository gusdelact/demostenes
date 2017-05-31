Ext.define('gfi.bin.admctasweb.view.reportes.BitacoraDoctosEliminadosView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.bitacoradoctoseliminados',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.reportes.BitacoraDoctosEliminadosGrid'],

	config : {
		header		: 'Bit\u00e1cora de Documentos Eliminados',
		formType	: gfi.FormTypes.CUSTOM,
		baseUrl		: 'reportes/bitDocsElim/',
		modelClass	: 'gfi.bin.admctasweb.model.reportes.BitacoraDoctosEliminados',
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
			}		
		},	
	},

	buildItems : function() {
    	var me = this;
    	
    	//Ligamos el Grid de Documentos Eliminados a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.reportes.BitacoraDoctosEliminadosGrid', {
    		layout: 'fit',
    		formStatus: [gfi.component.BasicForm.STATUS_NEW,
    		          	 gfi.component.BasicForm.STATUS_UPDATE]
		}));
		
		return [ 

		{
			xtype			: 'gfibuscadortrigger',
			grupo			: 'admctasweb',
			clave			: 'bitacoraDocsElimados',
			fieldLabel 		: 'N\u00famero de Oficio:',
			name			: 'numOficio',
			linkProperty	: 'numOficio',
			controls: [{
				ref: 'tipoOficio',
				selector: '#rdBDETipoOficio',
				linkProperty: 'tipoOficio'
			}],
			
			labelWidth		: 100,
			width 			: 330,
			maxLength 		: 30,
	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',
	        
	        listeners: {
		        change : function (field, newValue, oldValue){
		        	Ext.getCmp('BDEtipoGroup').reset();//Se resetea para evitar dejar valores en radios si se borra oficio
		        }											
			},	        
	        
		},		        

    	{
    		xtype: 'fieldset',
    		title: 'Tipo de Oficio',
    		collapsible: false,
    		width: 500,
    		items: [{
    		    xtype: 'radiogroup',
    		    id: "BDEtipoGroup",
    		    items: [
    		        { boxLabel: 'Judicial',  itemId : 'rdBDETipoOficio', name: 'tipoOficio', inputValue: 'JU', width:80},
    		        { boxLabel: 'Hacendario',  name: 'tipoOficio', inputValue: 'HA', width:100},
    		        { boxLabel: 'Aseguramiento', name: 'tipoOficio', inputValue: 'AS', width:120 },
    		        { boxLabel: 'PLD', name: 'tipoOficio', inputValue: 'PL', width:50 }
    		    ],
    		    //width: 800,
    		    formStatus : [gfi.component.BasicForm.SEARCH]
    	    }]
    	}, 
		
		
		
		
//		{
//		    xtype		: 'radiogroup',
//		    fieldLabel	: 'Tipo de Oficio',
//		    labelWidth	: 140,
//		    width		: 400,
//		    items		: [
//		        //{ boxLabel: 'TODOS',         name: 'tipoOficio', inputValue: '',   width:80, checked:true},
//		        { boxLabel: 'Judicial',      name: 'tipoOficio', inputValue: 'JU', width:80},
//		        { boxLabel: 'Hacendario',    name: 'tipoOficio', inputValue: 'HA', width:100},
//		        { boxLabel: 'Aseguramiento', name: 'tipoOficio', inputValue: 'AS', width:120},
//		        { boxLabel: 'PLD',           name: 'tipoOficio', inputValue: 'PL', width:50},
//		    ],
//	    },

		{
	        xtype		: 'fieldcontainer',
	        fieldLabel	: 'Rango de fechas',
	        layout		: 'hbox',
	        labelWidth	: 100,
	        items: [
	        		{
	        			xtype			: 'datefield',
	        			fieldLabel 		: 'Del',
	        			name 			: 'fInicio',
	        			labelWidth		: 20,
	        			width			: 195,
	        			//maxWidth		: 120,
	        			maxLength 		: 10,
	        	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        	        msgTarget 		: 'under',
	        		},
	        		{xtype: 'splitter'},
	        		{
	        			xtype			: 'datefield',
	        			fieldLabel 		: 'Al',
	        			name 			: 'fFin',
	        			labelWidth		: 20,
	        			width			: 195,
	        			//maxWidth		: 120,
	        			maxLength 		: 10,
	        	        maxLengthText	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        	        msgTarget 		: 'under',
	        		}
			]
		},
		me.grid,
		];		
	},

	//Acción para buscar en la bitácora de doctos eliminados
	doBuscar : function (options)
	{
		var me = this;
		modelo = me.getValues();

		if ( (modelo['numOficio'] != null && modelo['numOficio'] != '') || 
			 (modelo['tipoOficio'] != null && modelo['tipoOficio'] != '') ||  
			 (modelo['fInicio'] != null && modelo['fInicio'] != '') ||
			 (modelo['fFin'] != null && modelo['fFin'] != '') ){
		
			options.model = me.getValues();
			me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscar);
		} else {
			me.grid.setDocumentoEliminadoList(null);
			Ext.Msg.alert('AdmCtasWeb', 'Debe de seleccionar alg\u00fan filtro para poder realizar la b\u00fasqueda');
		}
		
	},
	
	completeBuscar : function(completed, options) {
		var me = this;

		if (options.message) {
			me.showTextMessage(options.message, false);
		}
		if (options.error) {
			options.error.showMessage();
		} else {	
			me.grid.setDocumentoEliminadoList(options.info.get('documentoEliminadoList'));
		}
		
	},
	
	doImprimir : function (options)
	{
		var me = this;
		modelo = me.getValues();

		if ( (modelo['numOficio'] != null && modelo['numOficio'] != '' && modelo['numOficio']!='undefined') || 
			 //(modelo['tipoOficio'] != null && modelo['tipoOficio'] != '') ||  
			 (modelo['fInicio'] != null && modelo['fInicio'] != '' && modelo['fInicio'] !='undefined') ||
			 (modelo['fFin'] != null && modelo['fFin'] != '' && modelo['fFin']!='undefined') ){
			
			var url = me.getUrl(me.status, options.action);
			
			var win = new Ext.Window({
				  title			: 'Visualizador PDF',
				  modal 		: true,
				  maximizable	: true,
				  width			: 700,
				  height		: 640,
				  plain			: true,
				  html			: Ext.String.format( '<iframe src="{0}?numOficio={1}&tipoOficio={2}&fInicio={3}&fFin={4}" width="100%" height="100%" />',  
						                             url, encodeURIComponent(modelo['numOficio']), modelo['tipoOficio'], modelo['fInicio'], modelo['fFin']),
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
			me.grid.setDocumentoEliminadoList(null);
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
//		me.grid.getStore().removeAll();
//		me.grid.getStore().sync();
		me.grid.setDocumentoEliminadoList([]);
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
