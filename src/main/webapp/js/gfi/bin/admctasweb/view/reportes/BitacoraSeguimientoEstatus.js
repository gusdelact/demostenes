Ext.define('gfi.bin.admctasweb.view.reportes.BitacoraSeguimientoEstatus', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.bitacoraseguimientoestatus',
	
	//Configuración del Componente.
	config : {
		header: 'Proceso de Seguimiento del Oficio',
		formType: gfi.FormTypes.CUSTOM,
		baseUrl: 'reportes/bitacoraseguimiento/',
		modelClass : 'gfi.bin.admctasweb.model.reportes.BitacoraSeguimientoList',
		secured: false,
		userActions : [ {
			actionName : 'buscarBitacora',
			pack : 'start',
			url: 'buscarbitacora.htm',
			createButton : false,
			sync: false,
			formStatus: [gfi.component.BasicForm.STATUS_SEARCH]
		}]
	},
	
	getMinWidth : function() {
		return 610;
	},

	getWidth : function() {
		return 610;
	},
	
	//Construimos los items de la Forma
	buildItems : function() {
		var me = this;
    	
    	//Ligamos el Grid de Bitacora a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.reportes.BitacoraSeguimientoGrid', {
    		layout: 'fit',
    		formStatus: [gfi.component.BasicForm.STATUS_NEW,
    		          	 gfi.component.BasicForm.STATUS_UPDATE]
		}));
    	
		return [ {
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items: [{
				xtype : 'textfield',
				fieldLabel : 'Número Oficio:',
				name : 'numOficio',
				id: 'bitSegOficio',
				readOnly: true,
				labelWidth: 85,
				width : 230
			}, {
	            xtype: 'tbspacer',
	            width : 15
	        }, {
				xtype : 'textfield',
				fieldLabel : 'Tipo Oficio:',
				name : 'tipoOficio',
				id: 'bitSegTipoOficio',
				readOnly: true,
				labelWidth: 65,
				width : 100
			}]
	    },
		   me.grid
		];
	},
	
	//Acción para Buscar Oficios Seguimiento
	doBuscarBitacora : function (options)
	{
		var me = this;
		me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscarBitacora);
	},
	
	//Completar Buscar Oficios Seguimiento
	completeBuscarBitacora : function(completed, options) {
		var me = this;
		
		if (options.error) {
			options.error.showMessage();
		} else {	
			me.grid.setBitacora(options.info.get('bitacoraList'));
			
			Ext.getCmp('bitSegOficio').setValue(options.info.get('numOficio'));
			Ext.getCmp('bitSegTipoOficio').setValue(options.info.get('tipoOficio'));
		}
	}
	
});

