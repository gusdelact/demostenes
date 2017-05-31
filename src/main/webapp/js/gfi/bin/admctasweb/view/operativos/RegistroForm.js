Ext.define('gfi.bin.admctasweb.view.operativos.RegistroForm', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.registroForm',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger',
	            'gfi.bin.admctasweb.view.catalogos.OficioForm',
	            'gfi.bin.admctasweb.view.catalogos.PersonaForm',
	            'gfi.bin.admctasweb.view.catalogos.DocumentoForm'],
	
	//Configuración del Componente.
	config : {
		header: 'Registro de Oficios',
		pack: 'stretch',
		formType: gfi.FormTypes.CONSULTA,
		modelClass : 'gfi.bin.admctasweb.model.operativos.Registro',
		primaryKeys : ['numOficio','tipoOficio'],
		baseUrl: 'catalogos/oficio/',
		grupo: 'admctasweb', 
		clave: 'oficios',
		allowSearchAll : false,
		secured: false
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
    			id: "numOficioReg",
    			fieldLabel : 'Número de Oficio:',
    			labelWidth: 100,
        		width : 300,
        		readOnly: true
    		},{
	            xtype: 'tbspacer',
	            width: 50
	        },{
    		    xtype: 'radiogroup',
    		    id: "tipoOficioReg",
    		    items: [
    		        { boxLabel: 'Judicial',  itemId : 'rdTipoOficioReg', name: 'tipoOficio', inputValue: 'JU'},
    		        { boxLabel: 'Hacendario',  name: 'tipoOficio', inputValue: 'HA'},
    		        { boxLabel: 'Aseguramiento', name: 'tipoOficio', inputValue: 'AS' },
    		        { boxLabel: 'PLD', name: 'tipoOficio', inputValue: 'PL' }
    		    ],
    		    width: 500,
    		    formStatus : [gfi.component.BasicForm.SEARCH]
    	    }]
    	}, {
			xtype : 'tabpanel',
			itemId : 'maintabpanel',
			frame : false,
			defaults : {
				bodyPadding : 10,
				autoScroll : true
			},
			items : [ {
				title : 'Oficio',
				xtype : 'oficioform'
			}, {
				title : 'Persona',
				xtype : 'personaform'
			}, {
				title : 'Documento',
				xtype : 'documentoform'
			} ]
		} ];
	}
	
});

