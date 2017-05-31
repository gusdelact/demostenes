Ext.define('gfi.bin.admctasweb.view.operativos.OficiosRespuestasNegativasView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.oficiosRespuestasNegativasView',
	
	//Configuración del Componente.
	config : {
		header: 'Oficios con respuesta negativa',
		pack: 'stretch',
		formType: gfi.FormTypes.CATALOGO,
		modelClass : 'gfi.bin.admctasweb.model.operativos.OficioRespuestaNegativa',
		primaryKeys : ['numOficio','tipoOficio'],
		baseUrl: 'operativos/oficiosrespuestasnegativas/',
		grupo: 'admctasweb', 
		clave: 'oficios',
		secured: false,
		userActions : [{
			actionName : 'crear',
			createButton : false
		},
		{
			actionName : 'borrar',
			createButton : false
		}]
	},
	
	initComponent: function () {
		var me = this;
        me.callParent();
        
 		Ext.ComponentManager.get('rnFileId').disable();
 		Ext.ComponentManager.get('rnNomDoctoId').setReadOnly(true);
 		
    	var filtro = [new Ext.util.Filter({
            property: 'tipoCaso',
            operator: '=',
            value: 'NE'
        }),
        new Ext.util.Filter({
            property: 'sitOficio',
            operator: '=',
            value: 'EN'
        })];                 	
    	me.buscador.setDefaultFilters(filtro);
 		
	},
	
	//Construimos los Items de la Forma.
	buildItems : function() {
		return [ {
    		xtype: 'fieldset',
    		title: 'Oficio Seleccionado',
    		layout: 'hbox',
    		collapsible: false,
    		items: [{
	    			xtype : 'textfield',
	    			name : 'numOficio',
	    			emptyText : 'Seleccione un Oficio',
	    			id: "rnNumOficioReg",
	    			fieldLabel : 'Número de Oficio:',
	    			labelWidth: 100,
	        		width : 300,
	        		readOnly: true
    			},{
		            xtype: 'tbspacer',
		            width: 50
		        },{
	    		    xtype: 'radiogroup',
	    		    id: "rnTipoOficioReg",
	    		    items: [
	    		        { boxLabel: 'Judicial',  name: 'tipoOficio', inputValue: 'JU'},
	    		        { boxLabel: 'Hacendario',  name: 'tipoOficio', inputValue: 'HA'},
	    		        { boxLabel: 'Aseguramiento', name: 'tipoOficio', inputValue: 'AS' },
	    		        { boxLabel: 'PLD', name: 'tipoOficio', inputValue: 'PL' }
	    		    ],
	    		    width: 500,
	    		    formStatus : [gfi.component.BasicForm.SEARCH]
		        }]
    	}, 
    	{
    		xtype: 'fieldcontainer',
			layout: 'hbox',
			items: [
				{
				    xtype: 'tbspacer',
				    width: 300
				},
				{
					xtype : 'textfield',
					fieldLabel : 'Numero de folio:',
					name : 'txNomAcu',
					id : 'rnIdNomAcu',
					labelWidth: 100,
					width : 250,
					formStatus : [gfi.component.BasicForm.STATUS_UPDATE,
							      gfi.component.BasicForm.SEARCH]
				}
			]
		},
    	{
    		xtype: 'fieldcontainer',
			layout: 'hbox',
			items: [
			{
				xtype : 'textfield',
				fieldLabel : 'Nombre archivo:',
				name : 'txNomArch',
				labelWidth: 100,
				width : 250,
				formStatus : [gfi.component.BasicForm.SEARCH]
			},
			{
	            xtype: 'tbspacer',
	            width: 50
	        },
			{
	        	xtype: 'fieldcontainer',
				layout: 'hbox',
				items: [
				{
					xtype : 'textfield',
					fieldLabel : 'Archivo a cargar:',
					name : 'nomDocto',
					id	 : 'rnNomDoctoId',
					labelWidth: 100,
					width : 320
				},
				{
					xtype: 'tbspacer',
					width : 5
				},
				{
					xtype : 'filefield',
					name : 'file',
					id	 : 'rnFileId',
					buttonOnly : true,
					buttonText : 'Seleccionar',
//					fieldLabel : 'Archivo a cargar',
					msgTarget : 'side',
					anchor: '100%',
					allowBlank: false,
					formStatus : [gfi.component.BasicForm.STATUS_UPDATE],
//					disabled : true,
					listeners:{
						change:function( thiss, value, eOpts ){
							var name = value.substring(12);
					    	Ext.ComponentManager.get('rnNomDoctoId').setValue(name);
					    }
					},
					}]
				}],
    	}];
	},
	
	doAceptar : function(options){//era completeAceptar
		var me = this;
		
		var form = me.mainPanel.getForm();
		if (form.isValid()) {
			form.submit({
				url : 'operativos/oficiosrespuestasnegativas/update.htm',
				waitMsg : 'Cargando archivo...',
				success: function(form, action) {
					console.log("Carga Correcta de Archivo.");
//					me.window.close();
//					console.log('Success');
		        },
		        // Si no se pasa el Parametro "succes" entra aqui.
		        failure: function(form, action) {
//		        	me.window.close();
//		        	console.log('Failure');
		        	me.showMessage('text', action.result['mensaje'], false, null);
		        }
			});
		}
		
		me.actions.aceptar.button.setDisabled(true);
		me.actions.cancelar.button.setDisabled(true);
		me.actions.editar.button.setDisabled(false);
		me.actions.buscar.button.setDisabled(false);
		
		Ext.ComponentManager.get('rnIdNomAcu').setReadOnly(true);
//		Ext.ComponentManager.get('rnFileId').disable();
		Ext.ComponentManager.get('rnNomDoctoId').setValue('');
		Ext.ComponentManager.get('rnNomDoctoId').setReadOnly(true);
		Ext.ComponentManager.get('rnNomDoctoId').disable();
		
		me.status = 'Activo';
	},
		
	completeEditar : function(completed, options){
		var me = this;
		
		Ext.ComponentManager.get('rnFileId').enable();
		Ext.ComponentManager.get('rnNomDoctoId').setReadOnly(false);
		
		me.actions.editar.completeAction.call(me, completed, options);
	},
	
	completeCancelar : function(completed, options){
		var me = this;
		
		Ext.ComponentManager.get('rnFileId').disable();
		Ext.ComponentManager.get('rnNomDoctoId').setValue('');
		Ext.ComponentManager.get('rnNomDoctoId').setReadOnly(true);
		
		me.actions.cancelar.completeAction.call(me, completed, options);
	}
});