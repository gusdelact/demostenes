Ext.define('gfi.bin.admctasweb.view.reporte_r29.ConversionView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.conversionview',
	
	requires : ['gfi.bin.admctasweb.model.r29.Conversion', 'gfi.corp.component.buscador.BuscadorTrigger'] ,
	
	//Configuración del Componente.
	config : {
		header: 'Consulta de Conversion',
		formType: gfi.corp.component.form.Types.CATALOGO,
		grupo: 'admctasweb',
		clave: 'conver',
		modelClass : 'gfi.bin.admctasweb.model.r29.Conversion',
		primaryKeys : ['idCatalogo'],
		baseUrl: 'reporte_r29/conversion/',
		secured: false
	},

	getWidth	: function() {
		return 850;
	},

	
	//Construimos los items de la Forma
	buildItems : function() {
		return [
		        {
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items : [{
	        	xtype: 'textfield',
	        	// se quita como buscador para permitir su captura JAP
//				xtype: 'gfibuscadortrigger',
				fieldLabel : 'Catalogo:',
				itemId: 'idCatalogo',
				name : 'idCatalogo',
				editable : true,
				grupo: 'admctasweb', 
	        	clave: 'conver',
				linkProperty: 'idCatalogo',
	        	controls: [{ 
	        		ref: 'descCatalogo', 
	        		selector: '#descCatalogo',
	        		linkProperty: 'descCatalogo'
	        	}],
				labelWidth : 140,
				width : 320,
				validateBlank : true,
				maxLength 	: 2,
				maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasados.',
		        formStatus : [gfi.component.BasicForm.STATUS_NEW,
		                      gfi.component.BasicForm.STATUS_UPDATE]
	        }]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Descripción:',
			name : 'descCatalogo',
			itemId : 'descCatalogo',
			labelWidth: 140,
			editable : true,
//			fieldStyle : 'text-transform: uppercase',
			listeners:{
                change: function(field, newValue, oldValue){
                    field.setValue(newValue.toUpperCase());
                }
             },

			width : 660,
			maxLength 	: 50,
			maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	           formStatus : [ gfi.component.BasicForm.STATUS_NEW,
	                          gfi.component.BasicForm.STATUS_UPDATE]
		},
		];
	},
	
	//Se sobreescriben las Acciones de la Forma.
	
	//Completar Selección.
	completeSeleccionar: function (completed, options) {
		var me = this;
//		var buscadorAut = Ext.getCmp('#idCatalogo');
//		
//		buscadorAut.loadRecord( new Ext.util.Filter({property: 'idCatalogo', value: options.model.get('idCatalogo')}) );
//		
		me.actions.seleccionar.completeAction.call(me, completed, options);
    }
	
	
});

