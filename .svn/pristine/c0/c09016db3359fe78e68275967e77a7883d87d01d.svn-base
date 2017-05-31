Ext.define('gfi.bin.admctasweb.view.catalogos.PersonaForm', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.personaform',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.catalogos.PersonaGrid'],

	config : {
		header: 'Catálogo de Personas',
		formType: gfi.FormTypes.LISTADO,
		baseUrl: 'catalogos/persona/',
		secured: false,	
		grupo: 'admctasweb',
		clave: 'oficios',		
		primaryKeys: ['numOficio','tipoOficio'],		
		modelClass: 'gfi.bin.admctasweb.model.catalogos.PersonaList',
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
			buttonText: 'Agregar Persona',
		},{
			actionName : 'editarItem',
			buttonText: 'Editar Persona',
		},{
			actionName : 'borrarItem',
			createButton : false
		},{
			actionName : 'borrarPersona',
			buttonText: 'Eliminar Persona',
			statusConfig:  gfi.component.BasicForm.STATUS_UPDATE,
			pack : 'start',
			url: 'delete.htm',
			sync: false
		}]
	},
	
	//Construimos los Items de la Forma.
	buildItems : function() {
    	var me = this;
    	
    	//Ligamos el Grid de Personas a la Forma.
    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.catalogos.PersonaGrid', {
    		layout: 'fit',
    		formStatus: [gfi.component.BasicForm.STATUS_NEW,
    		          	 gfi.component.BasicForm.STATUS_UPDATE]
		}));
    	
    	return [{
    		xtype: 'fieldset',
    		title: 'Oficio Seleccionado',
    		collapsible: false,
    		hidden: true, //me.subFormConfig ? me.subFormConfig.embedded : false,
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
		me.grid.setPersonas(options.model.get('personaList'));
		me.actions.seleccionar.completeAction.call(me, completed, options);
    },
	
    //Complementamos los valores del Grid con la Forma.
	internalGetValues: function () {
        var me = this,	
        	model,
        	personaList = [],
        	borrarList = [];
        
        model = me.callParent(arguments);
        
        //Obtenemos registros de personas.
        var records = me.grid.getStore().getRange();
        Ext.Array.each(records, function (item) {
        	item.set('numOficio', model.numOficio);
        	item.set('tipoOficio', model.tipoOficio);
        	personaList.push(item.data);
        });
        model.personaList = personaList; 
        
        //Obtenemos personas eliminadas.
        var deleteRec = me.grid.getStore().getRemovedRecords();
        Ext.Array.each(deleteRec, function (item) {
        	borrarList.push(item.data);
        });

        model.borrarList = borrarList;

        return model;
    },
    
    
    //ACCIONES PROPIAS
    
    //Borrar Persona
    doBorrarPersona : function(options) {
		var me = this;
		if(me.grid.getSelectionModel().hasSelection()){
	        Ext.MessageBox.confirm(
					me.getMessageTitle(), 
					'<p align="center">\u00BFEsta seguro de eliminar la Persona?</p>', 
					function(btn) {
						if (btn === 'yes') 
						{
							var row = me.grid.getSelectionModel().getSelection()[0];
							var persona = Ext.create('gfi.bin.admctasweb.model.catalogos.Persona', {
								   numOficio : 	Ext.getCmp('numOficioReg').getValue(),
								   tipoOficio :	Ext.getCmp('tipoOficioReg').getChecked()[0].getGroupValue(),
								   numConsec : row.get('numConsec')
							});				   
							
						   options.model = persona;
						   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeAceptar);			
						}
					}, me);
		}
		else{
        	options.message = 'Seleccione una Persona para Eliminar.';
        	Ext.Msg.show({
				title: 'AdmCtasWeb',
				msg: options.message,
				buttons: Ext.Msg.OK,	
				icon: Ext.Msg.WARNING,
				modal: true
			});			
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

