Ext.define('gfi.bin.admctasweb.view.reportes.BitacoraSeguimientoView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.bitacoraseguimiento',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.catalogos.PersonaGrid'],

	config : {
		header: 'Bitácora de seguimiento de Oficios',
		formType: gfi.FormTypes.CUSTOM,
		baseUrl: 'reportes/bitacoraseguimiento/',
		modelClass: 'gfi.bin.admctasweb.model.reportes.BitacoraSeguimientoList',
		pack: 'stretch',
		secured: false,		
		userActions : [ {
			actionName : 'buscarSeguimiento',
			buttonText: 'Buscar',
			pack : 'start',
			url: 'buscarseguimiento.htm',
			sync: false,
			formStatus: [gfi.component.BasicForm.STATUS_SEARCH]
		},{
			actionName : 'limpiar',
			buttonText: 'Limpiar',
			statusConfig: gfi.component.BasicForm.STATUS_SEARCH,
			pack : 'start',
			sync: false
		},{
			actionName : 'verDetalleSeg',
			buttonText: 'Detalle Seguimiento',
			statusConfig: gfi.component.BasicForm.STATUS_SEARCH,
			pack : 'center',
			url: 'verdetalleseg.htm',
			sync: false
		},{
			actionName : 'imprimir',
			buttonText: 'Imprimir',
			statusConfig: gfi.component.BasicForm.STATUS_SEARCH,
			pack : 'center',
			url: 'reporte.htm',
			sync: false
		}]
	},
	
	//Construimos los Items de la Forma.
	buildItems : function() {
    	var me = this;
    	
    	//Ligamos el Grid de Personas a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.reportes.OficioSeguimientoGrid', {
    		layout: 'fit',
    		formStatus: [gfi.component.BasicForm.STATUS_NEW,
    		          	 gfi.component.BasicForm.STATUS_UPDATE]
		}));
    	
    	return [ {
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items : [{
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
	    			selector: '#rdTipoOficioBitSeg',
	    			linkProperty: 'tipoOficio',
	    		}]
	    	},{
				xtype : 'tbspacer',
				width : 20,
			},{
			    xtype: 'radiogroup',
			    id: "tipoOficioBitSeg",
			    items: [
			        { boxLabel: 'Judicial',  itemId : 'rdTipoOficioBitSeg', name: 'tipoOficio', inputValue: 'JU'},
			        { boxLabel: 'Hacendario',  name: 'tipoOficio', inputValue: 'HA'},
			        { boxLabel: 'Aseguramiento', name: 'tipoOficio', inputValue: 'AS' },
			        { boxLabel: 'PLD', name: 'tipoOficio', inputValue: 'PL' }
			    ],
			    width: 500
		    }]
		},{
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items: [{
	        	xtype: 'gfibuscadortrigger', 
	    		grupo: 'admctasweb', 
	    		clave: 'estatusSeg',
	    		fieldLabel : 'Estatus Seguimiento:',
	    		name: 'cveEstatus',
	    		linkProperty: 'cveEstatus',
	    		labelWidth: 120,
	    		width : 300,
	    		defaultFilters: [
					new Ext.util.Filter({
						property: 'ordenProceso',
						operator: '<>',
						value: null
					})
	    		],
        		controls: [{
        			ref: 'txDescripcionSeg', 
        			selector: '#txDescripcionSeg',
        			linkProperty: 'descripcion'
        		}]
        	},{
	            xtype: 'tbspacer',
	            width: 5
	        }, {
    			xtype : 'textfield',
    			itemId : 'txDescripcionSeg',
    			width : 260
    		}]
		},{
			xtype: 'fieldset',
			title: 'Fechas de Recepción',
			collapsible: false,
			width : 415,
			items : [ {
		        xtype: 'fieldcontainer',
		        layout: 'hbox',
		        items: [{
					xtype : 'datefield',
					fieldLabel : 'Fecha Inicio:',
					name : 'fhRecepIni',
					labelWidth: 70,
					width : 180
				}, {
		            xtype: 'tbspacer',
		            width : 30
		        }, {
					xtype : 'datefield',
					fieldLabel : 'Fecha Final:',
					name : 'fhRecepFin',
					labelWidth: 70,
					width : 180
				}]
		    }]
		},{
			xtype: 'fieldset',
			title: 'Fechas de Envío',
			collapsible: false,
			width : 415,
			items : [ {
		        xtype: 'fieldcontainer',
		        layout: 'hbox',
		        items: [{
					xtype : 'datefield',
					fieldLabel : 'Fecha Inicio:',
					name : 'fhEnvIni',
					labelWidth: 70,
					width : 180
				}, {
		            xtype: 'tbspacer',
		            width : 30
		        }, {
					xtype : 'datefield',
					fieldLabel : 'Fecha Final:',
					name : 'fhEnvFin',
					labelWidth: 70,
					width : 180
				}]
		    }]
		},{
			xtype: 'fieldset',
			title: 'Fechas de Vencimiento',
			collapsible: false,
			width : 415,
			items : [ {
		        xtype: 'fieldcontainer',
		        layout: 'hbox',
		        items: [{
					xtype : 'datefield',
					fieldLabel : 'Fecha Inicio:',
					name : 'fhVenIni',
					labelWidth: 70,
					width : 180
				}, {
		            xtype: 'tbspacer',
		            width : 30
		        }, {
					xtype : 'datefield',
					fieldLabel : 'Fecha Final:',
					name : 'fhVenFin',
					labelWidth: 70,
					width : 180
				}]
		    }]
		}, 
    	me.grid
    	];
	},
	
	//Acciones que se ejecutan al completar la seleccion del Oficio.
	completeSeleccionar: function (completed, options){
		var me = this;		
		me.grid.setOficios(options.model.get('oficioList'));
		me.actions.seleccionar.completeAction.call(me, completed, options);
    },
    
    //ACCIONES PROPIAS
    
    //Acción para Buscar Oficios Seguimiento
	doBuscarSeguimiento : function (options)
	{
		var me = this;
		
		options.model = me.getValues();
		me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscarSeguimiento);	
	},
	
	//Completar Buscar Oficios Seguimiento
	completeBuscarSeguimiento : function(completed, options) {
		var me = this;
		
		if (options.error) {
			options.error.showMessage();
		} else {	
			me.grid.setOficios(options.info.get('oficioList'));
		}
	},
	
	//Muestra la Traza de Seguimiento del Oficio.
    doVerDetalleSeg : function(options) {
        var me = this;
        var record = me.grid.getSelectionModel().getSelection()[0];
        
        if(me.grid.getSelectionModel().hasSelection()) {
        	var popup = Ext.create('gfi.bin.admctasweb.view.reportes.BitacoraSeguimientoEstatus',{
    	        commitData : false,
    	        modal : true
    	    });
    	    
    	    var model = Ext.create('gfi.bin.admctasweb.model.reportes.BitacoraSeguimientoList',{
    	    	numOficio	: record.get('numOficio'),
    	    	tipoOficio	: record.get('tipoOficio')
    	    });
    	    
    	    options.model = model;
    	    popup.executeAction('buscarBitacora',{model:model});
    	    
    	    // popup.loadRecord(model);
            popup.showWindow({
            	initialStatus : gfi.component.BasicForm.STATUS_SEARCH
            });
        }else{
        	options.message = 'Seleccione un Oficio.';
        	Ext.Msg.show({
				title: 'AdmCtasWeb',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.WARNING,
				modal: true
			});			
		}
    },
    
    //Realiza el Llamado para la generación del Reporte en PDF.
    doImprimir : function(options)	{
		var me = this;		
		modelo = me.getValues();	
		//Esta concatena la base url con la definida para la accion (/reportes/bitacoraseguimiento/reporte.htm)
		var url = me.getUrl(me.status, options.action);
		
		var win = new Ext.Window({
		  title: 'Visualizador PDF',
		  modal : true,
		  maximizable: true,
		  width: 700,
		  height:  640,
		  plain:true,
		  html: Ext.String.format( '<iframe src="{0}?numOficio={1}&tipoOficio={2}&cveEstatus={3}&fhRecepIni={4}&fhRecepFin={5}&fhEnvIni={6}&fhEnvFin={7}&fhVenIni={8}&fhVenFin={9}" width="100%" height="100%" />', url,
				  encodeURIComponent(modelo['numOficio']), modelo['tipoOficio'], modelo['cveEstatus'], modelo['fhRecepIni'], modelo['fhRecepFin'], modelo['fhEnvIni'], modelo['fhEnvFin'], modelo['fhVenIni'], modelo['fhVenFin']),
				  						
		  listeners: {
		      render: function(comp) {
		          var mask = new Ext.LoadMask(comp.el, {msg: "Cargando Documento..."});
		          mask.show();
		          if ( navigator.appName == "Netscape"){
					setTimeout(
						function (target) {
							target.destroy();
						}, 1500, mask
					);
				  }                    
		     }
		  }
		});
		win.show();	
	},
	
	//Completa la accion de Impormir el Reporte.
	completeImprimir : function (completed, options){
		var me = this;
		if(completed) {
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		}
		else {
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

