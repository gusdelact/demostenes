Ext.define('gfi.bin.admctasweb.view.catalogos.DocumentoForm', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.documentoform',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.catalogos.DocumentoGrid'],

	config : {
		header: 'Catálogo de Documentos',
		formType: gfi.FormTypes.LISTADO,
		baseUrl: 'catalogos/documento/',
		secured: false,	
		grupo: 'admctasweb',
		clave: 'oficios',		
		primaryKeys: ['numOficio','tipoOficio'],		
		modelClass: 'gfi.bin.admctasweb.model.catalogos.DocumentoList',
		pack: 'stretch',
		subFormConfig :{
			embedded: false,
			usePropertyName: false
		},
		userActions : [ {
			actionName : 'buscar',
			createButton : false
		},{
			actionName : 'agregarItem',
			buttonText: 'Agregar Documento',
		},{
			actionName : 'editarItem',
			buttonText: 'Editar Documento',
		},{
			actionName : 'borrarItem',
			createButton : false
		},{
			actionName : 'borrarDocumento',
			buttonText: 'Eliminar Documento',
			statusConfig:  gfi.component.BasicForm.STATUS_UPDATE,
			pack : 'start',
			url: 'delete.htm',
			sync: false
		},{
			actionName : 'visualizarDocumento',
			buttonText: 'Visualizar',
			statusConfig:  gfi.component.BasicForm.STATUS_ACTIVE,
			pack : 'start',
			url: 'visualizar.htm',
			sync: true
		}]
	},
	
	//Construimos los Items de la Forma.
	buildItems : function() {
    	var me = this;
    	
    	//Ligamos el Grid de Personas a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.catalogos.DocumentoGrid', {
    		layout: 'fit',
    		formStatus: [gfi.component.BasicForm.STATUS_NEW,
    		          	 gfi.component.BasicForm.STATUS_UPDATE]
		}));
    	
    	return [{
    		xtype: 'fieldset',
    		title: 'Oficio Seleccionado',
    		collapsible: false,
    		hidden: true,//me.subFormConfig ? me.subFormConfig.embedded : false,
    		items: [{
    			xtype : 'textfield',
    			name : 'numOficio',
    			fieldLabel : 'Número de Oficio:',
    			readOnly: true
    		},{
    			xtype : 'textfield',
    			name : 'tipoOficio',
    			fieldLabel : 'Tipo de Oficio:',
    			readOnly: true
    		}]
    	}, 
    	me.grid
    	];
	},
	
	//Acciones que se ejecutan al completar la seleccion del Oficio.
	completeSeleccionar: function (completed, options){
		var me = this;		
		me.grid.setDocumentos(options.model.get('documentoList'));
		me.actions.seleccionar.completeAction.call(me, completed, options);
    },
	
    //Complementamos los valores del Grid con la Forma.
	internalGetValues: function () {
        var me = this,	
        	model,
        	records = me.grid.getStore().getRange(),   //getModifiedRecord();
        	documentoList = [];
        
        model = me.callParent(arguments);
        Ext.Array.each(records, function (item) {
        	item.set('numOficio', model.numOficio);
        	item.set('tipoOficio', model.tipoOficio);
        	documentoList.push(item.data);
        });
        model.documentoList = documentoList; 

        return model;
    },
    
    //OVERRIDE de Acciones para el PopUp de la Forma de Documentos.
    
    //AgregarItem
    doAgregarItem : function() {
        var me = this;
    
	    var popup = Ext.create('gfi.bin.admctasweb.view.catalogos.DocumentoSimpleForm',{
	        commitData : false,
	        modal : true
	    });
	     
	    var model = Ext.create('gfi.bin.admctasweb.model.catalogos.Documento',{
	    	numOficio : me.getRecord() ? me.getRecord().get('numOficio'):0,
	    	tipoOficio : me.getRecord().get('tipoOficio')
	    });
	    
	    // console.log(model);
	    popup.executeAction('seleccionar',{model:model});
	    
	    // popup.loadRecord(model);
        popup.showWindow({
        	initialStatus : gfi.component.BasicForm.STATUS_NEW
        });
    },
    
    //EditarItem
    doEditarItem : function(options) {
        var me = this;
        
        if(me.grid.getSelectionModel().hasSelection()){
	        var record = me.grid.getSelectionModel().getSelection()[0];
	    
		    var popup = Ext.create('gfi.bin.admctasweb.view.catalogos.DocumentoSimpleForm',{
		        commitData : false,
		        modal : true
		    });
		    
		    var model = Ext.create('gfi.bin.admctasweb.model.catalogos.Documento',{
		    	numOficio	: me.getRecord().get('numOficio'),
		    	tipoOficio	: me.getRecord().get('tipoOficio'),
		    	
		    	numDocto	: record.get('numDocto'),
		    	cveDocto	: record.get('cveDocto'),
		    	nomDocto	: record.get('nomDocto'),
		    	fhAlta		: record.get('fhAlta'),
		    	cveUsuAlta	: record.get('cveUsuAlta')
		    });
		    
		    // console.log(model);
		    popup.executeAction('seleccionar',{model:model});
		    
		    // popup.loadRecord(model);
	        popup.showWindow({
	        	initialStatus : gfi.component.BasicForm.STATUS_UPDATE
	        });
        }else {
        	options.message = 'Seleccione un Documento para Modificar.';
        	Ext.Msg.show({
				title: 'AdmCtasWeb',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.WARNING,
				modal: true
			});			
		}
    },
    
    //Borrar Documento
    doBorrarDocumento : function(options) {
		var me = this;
		if(me.grid.getSelectionModel().hasSelection()){
	        Ext.MessageBox.confirm(
					me.getMessageTitle(), 
					'<p align="center">\u00BFEsta seguro de eliminar el Documento?</p>', 
					function(btn) {
						if (btn === 'yes') 
						{
							var row = me.grid.getSelectionModel().getSelection()[0];
							var documento = Ext.create('gfi.bin.admctasweb.model.catalogos.Documento', {
								   numOficio : 	Ext.getCmp('numOficioReg').getValue(),
								   tipoOficio :	Ext.getCmp('tipoOficioReg').getChecked()[0].getGroupValue(),
								   numDocto : row.get('numDocto')
							});				   
							
						   options.model = documento;
						   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeAceptar);			
						}
					}, me);
		}
		else{
        	options.message = 'Seleccione un Documento a Eliminar.';
        	Ext.Msg.show({
				title: 'AdmCtasWeb',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.WARNING,
				modal: true
			});			
		}
    },
    
    //Visualizar Documento
    doVisualizarDocumento : function(options) {
		var me = this;
		if(me.grid.getSelectionModel().hasSelection()){
			var row = me.grid.getSelectionModel().getSelection()[0];
			var url = me.getUrl(me.status, options.action);
			
			var numOficio = Ext.getCmp('numOficioReg').getValue();
			var tipoOficio = Ext.getCmp('tipoOficioReg').getChecked()[0].getGroupValue();
			var nomDocto = row.get('nomDocto');
			var fhAlta = row.get('fhAlta');
			
			//Creamos la ventana para Visualizar el Documento.
			var win = new Ext.Window({
				  title: 'Visualizador de Documentos',
				  modal : true,
				  maximizable: true,
				  width: 300,
				  height: 200,
				  plain:true,
				  
				  html: Ext.String.format( '<iframe src="{0}?numOficio={1}&tipoOficio={2}&nomDocto={3}&fhAlta={4}" width="100%" height="100%" />', url, numOficio, tipoOficio, nomDocto, fhAlta),
						  						
				  listeners: {
				      render: function(comp) {
				          var mask = new Ext.LoadMask(comp.el, {msg: "Cargando Documento..."});
				          mask.show();
						  if ( navigator.appName == "Netscape") {
							  setTimeout(function (target) {
								  target.destroy();
						      }, 1500, mask);
						  }                    
				     }
				  }  
				});
			win.show();	
			win.hide();
		}
		else{
        	options.message = 'Seleccione un Documento para Visualizar.';
        	Ext.Msg.show({
				title: 'AdmCtasWeb',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.WARNING,
				modal: true
			});			
		}
    },
    
    //Completar Visualizar Documento.
    completeVisualizarDocumento : function (completed, options){
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
	
	//Editar - Validamos Estatus del Oficio.
    doEditar : function(options) {
        var me = this;
        var sitOficio = Ext.getCmp('sitOficioOfi').getValue();
        
        if(sitOficio == 'PE') {
        	var completed = me.actions.editar.doAction.call(me, options);
            me.actions.editar.completeAction.call(me, completed, options);
        }else { 
        	options.message = 'Solo puede Modificar Oficios en Estatus PENDIENTE';
        	Ext.Msg.show({
				title: 'AdmCtasWeb',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.WARNING,
				modal: true
			});
        }
    }
	
});

