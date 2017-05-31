Ext.define('gfi.bin.admctasweb.view.operativos.RespuestaForm', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.respuestaform',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.operativos.RespuestaGrid'],

	config : {
		header: null,
		formType: gfi.FormTypes.CUSTOM,
		baseUrl: 'operativos/respuesta/',
		secured: false,	
//		grupo: 'admctasweb',
//		clave: 'oficios',		
		primaryKeys: ['numOficio','tipoOficio'],		
		modelClass: 'gfi.bin.admctasweb.model.operativos.RespuestaList',
		pack: 'stretch',
		
		subFormConfig :{
			embedded: true,
			usePropertyName: false
		},

		userActions: {
			borrarRespuesta: {
				buttonText: 'Eliminar Respuesta',
				//createButton: false,
				pack : 'start',
				statusConfig: 'Buscar',
				url: 'update.htm',
				sync: false,
			},				
		
		},
	},
	
	
	getMinWidth : function() {
		return 600;
	},

	getWidth : function() {
		return 600;
	},
	
	
	//Construimos los Items de la Forma.
	buildItems : function() {
    	var me = this;
    	
    	//Ligamos el Grid de Personas a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.operativos.RespuestaGrid', {
    		layout: 'fit',
    		id: 'gridRespuestas',
    		height: 280,
    		formStatus: [gfi.component.BasicForm.STATUS_NEW,
    		          	 gfi.component.BasicForm.STATUS_UPDATE]
		}));
    	
    	return [ 
    	        me.grid
    	];
	},
	
	//Acciones que se ejecutan al completar la seleccion del Oficio.
//	completeSeleccionar: function (completed, options){
//		var me = this;		
//		me.grid.setRespuestas(options.model.get('respuestaList'));
//		me.actions.seleccionar.completeAction.call(me, completed, options);
//    },
//	
//    //Complementamos los valores del Grid con la Forma.
//	internalGetValues: function () {
//        var me = this,	
//        	model,
//        	borrarList = [];
//        
//        model = me.callParent(arguments);
//        
//        var deleteRec = me.grid.getStore().getRemovedRecords();
//        Ext.Array.each(deleteRec, function (item) {
//        	borrarList.push(item.data);
//        });
//
//        model.borrarList = borrarList;
//
//        return model;
//    },
    
    doBorrarRespuesta : function(options) {
		var me = this;
		if(me.grid.getSelectionModel().hasSelection()){
			
			if(!me.oficioPendiente())
			{
				me.showMessage('text', 'El Oficio no se encuentra PENDIENTE', false, null);
				return;
			}
			
			
	        Ext.MessageBox.confirm(
					me.getMessageTitle(), 
					'<p align="center">\u00BFEsta seguro de eliminar la Respuesta?</p>', 
					function(btn) {
						if (btn === 'yes') 
						{
							var row = me.grid.getSelectionModel().getSelection()[0];
							var respuesta = Ext.create('gfi.bin.admctasweb.model.operativos.Respuesta', {
								   numOficio : 	Ext.getCmp('oficio').getValue(),
								   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
								   numConsec : row.get('numConsec'),
								   idContrato : row.get('idContrato')
							});				   
							
						   options.model = respuesta;
						   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBorrarRespuesta);			
						}
					}, me);
		}
		else{
        	options.message = 'Seleccione una Respuesta para Eliminar.';
        	Ext.Msg.show({
				title: 'AdmCtasWeb',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.WARNING,
				modal: true
			});			
		}
    },
	
    completeBorrarRespuesta : function(completed, options){
		var me = this;
		if(completed)
		{
			//var info = options.info;
			me.grid.setRespuestas(options.info.get('respuestaList'));
			
			//grid.setRespuestas(options.info.get('respuestaList'));
		}
		else
		{
			me.showErrorMessage(options.error || options.message);
		}		
    },
	
	
	
    //OVERRIDE de Acciones.
    
    //Editar
    doBorrarItem : function(options) {
        var me = this;
        var record = me.grid.getSelectionModel().getSelection()[0];
        
        if (record == null || record == 'undefined') {
        	options.message = 'Debe seleccionar un registro para poder Eliminar.';
        	Ext.Msg.show({
				title: 'Error',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.ERROR,	
				modal: true
			});
        	return;
        }
        
        Ext.MessageBox.confirm(
				me.getMessageTitle(), 
				'<p align="center">\u00BFEsta seguro de eliminar el elemento?</p>', 
				function(btn) {
					if (btn === 'yes') {
						me.grid.getStore().remove(record);
						completed = true;
					}
				}, this);
    },
    
    //Boton Aceptar General.
    doAceptar : function(options) {
    	var me = this;
    	
    	options.model = me.getValues();
    	
		me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeAceptar);
    },

    //Verifica si el oficio está en estatus pendiente
    oficioPendiente : function()
    {
    	return Ext.getCmp('sitOficio').getValue()=='PE';    	
    }
    
    
});

