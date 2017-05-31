Ext.define('gfi.bin.admctasweb.view.operativos.ImpresionDictamenJurView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.impresiondictamenjurview',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.catalogos.PersonaGrid'],

	config : {
		header: 'Impresión de Dictamen Jurídico',
		formType: gfi.FormTypes.LISTADO,
		grupo: 'admctasweb',
		clave: 'oficios',
		primaryKeys: ['numOficio','tipoOficio'],
		baseUrl: 'operativos/impresiondictamenjur/',
		modelClass: 'gfi.bin.admctasweb.model.operativos.ImpresionDictamenJurList',
		pack: 'stretch',
		secured: false,		
		userActions : [{
			actionName : 'agregarItem',
			createButton : false
		},{
			actionName : 'editarItem',
			createButton : false
		},{
			actionName : 'borrarItem',
			createButton : false
		},{
			actionName : 'editar',
			createButton : false
		},{
			actionName : 'aceptar',
			createButton : false
		},{
			actionName : 'cancelar',
			createButton : false
		},{
			actionName : 'visualizar',
			buttonText: 'Visualizar',
			statusConfig: gfi.component.BasicForm.STATUS_ACTIVE,
			pack : 'start',
			url: 'visualizar.htm',
			sync: false
		},{
			actionName : 'imprimir',
			buttonText: 'Imprimir',
			statusConfig: gfi.component.BasicForm.STATUS_ACTIVE,
			pack : 'start',
			url: 'imprimir.htm',
			sync: false
		}]
	},
	
	//Construimos los Items de la Forma.
	buildItems : function() {
    	var me = this;
    	
    	//Ligamos el Grid de Personas a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.operativos.ImpresionDictamenJurGrid', {
    		layout: 'fit',
    		formStatus: [gfi.component.BasicForm.STATUS_NEW,
    		          	 gfi.component.BasicForm.STATUS_UPDATE]
		}));
    	
    	return [ {
			xtype : 'textfield',
			fieldLabel : 'Número de Oficio:',
			name : 'numOficio',
			labelWidth : 140,
			width : 320,
			validateBlank : true,
			formStatus : [ gfi.component.BasicForm.STATUS_NEW ]
		},{
			xtype: 'fieldset',
			title: 'Tipo de Oficio',
			collapsible: false,
			width : 320,
			items : [{
				xtype : 'radiogroup',
				id: 'ImpDicTipoOficioId',
				columns : 2,
				vertical : true,
				items : [ {
					boxLabel : 'Judicial',
					name : 'tipoOficio',
					inputValue : 'JU'
				}, {
					boxLabel : 'Hacendario',
					name : 'tipoOficio',
					inputValue : 'HA'
				}, {
					boxLabel : 'Aseguramiento',
					name : 'tipoOficio',
					inputValue : 'AS'
				}, {
					boxLabel : 'PLD',
					name : 'tipoOficio',
					inputValue : 'PL'
				} ],
				width : 250,
				formStatus : [ gfi.component.BasicForm.STATUS_NEW ]
			}]
		},
    	me.grid
    	];
	},
	
	//Acciones que se ejecutan al completar la seleccion del Oficio.
	completeSeleccionar: function (completed, options){
		var me = this;		
		me.grid.setImpresionDictamen(options.model.get('impresionDictamenList'));
		me.actions.seleccionar.completeAction.call(me, completed, options);
    },
    
    //ACCIONES PROPIAS
    
    //Realiza el Llamado para la visualización del Reporte en PDF.
	doVisualizar : function(options) {
        var me = this;
        var url = me.getUrl(me.status, options.action);
        var record = me.grid.getSelectionModel().getSelection()[0];
        
        if(me.grid.getSelectionModel().hasSelection()) {
    		var win = new Ext.Window({
    		  title: 'Visualizador PDF',
    		  modal : true,
    		  maximizable: true,
    		  width: 700,
    		  height:  640,
    		  plain:true,
    		  html: Ext.String.format( '<iframe src="{0}?numOficio={1}&tipoOficio={2}&numConsec={3}" width="100%" height="100%" />', url,
    				  encodeURIComponent(record.get('numOficio')), record.get('tipoOficio'), record.get('numConsec')),
    				  						
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
        }else{
        	options.message = 'Seleccione un Dictamen Jurídico.';
        	Ext.Msg.show({
				title: 'AdmCtasWeb',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.WARNING,
				modal: true
			});			
		}
    },
    
	//Completa la accion de Visualizar el Reporte.
	completeVisualizar : function (completed, options){
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
	
	//Realiza el Llamado para la generación del Reporte en PDF.
	doImprimir : function(options) {
        var me = this;
        var url = me.getUrl(me.status, options.action);
        var record = me.grid.getSelectionModel().getSelection()[0];
        
        if(me.grid.getSelectionModel().hasSelection()) {
        	Ext.MessageBox.confirm(
				me.getMessageTitle(), 
				'<p align="center">¿Esta Seguro de Imprimir el Dictamen Jurídico? al hacerlo ya no prodrá ser Modificado.</p>', 
				function(btn) {
					if (btn === 'yes') {
						var win = new Ext.Window({
			    		  title: 'Visualizador PDF',
			    		  modal : true,
			    		  maximizable: true,
			    		  width: 700,
			    		  height:  640,
			    		  plain:true,
			    		  html: Ext.String.format( '<iframe src="{0}?numOficio={1}&tipoOficio={2}&numConsec={3}" width="100%" height="100%" />', url,
			    				  encodeURIComponent(record.get('numOficio')), record.get('tipoOficio'), record.get('numConsec')),
			    				  						
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
					}
				}
			,me);	
        }else{
        	options.message = 'Seleccione un Dictamen Jurídico.';
        	Ext.Msg.show({
				title: 'AdmCtasWeb',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.WARNING,
				modal: true
			});			
		}
    },
    
	//Completa la accion de Imprimir el Reporte.
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
	}
    
	
});

