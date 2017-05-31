Ext.define('gfi.bin.admctasweb.view.catalogos.SimilaridadView', {
	extend : 'gfi.corp.component.form.BasicForm',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],
	
	config : {
		header: 'Catálogo de Similitud',
		formType: gfi.FormTypes.CATALOGO,
		grupo: 'admctasweb', 
		clave: 'similaridad',
		modelClass : 'gfi.bin.admctasweb.model.catalogos.Similaridad',
		primaryKeys : ['similaridad'],
		baseUrl: 'catalogos/similaridad/',
		secured: false,
		
		//Solo se permite la Opción de Modificar porque debe existir un solo registro en la BD
		userActions: {
			borrar: {//Se sobreescribe acción para ocultar el botón
				createButton: false,
			},
			
			crear: {//Se sobreescribe acción para ocultar el botón
				createButton: false,
			},			
		},		
	},
	
	getMinWidth : function() {
		return 600;
	},

	getWidth : function() {
		return 600;
	},
	
	buildItems : function() {
		return [ 
                {
                	xtype : 'numberfield',
            		fieldLabel : '% Coincidencia:',
            		name: 'similaridad',
            		labelWidth: 100,
                    //value: 90,
                    maxValue: 100,
                    minValue: 1,
                    formStatus : [gfi.component.BasicForm.STATUS_UPDATE],
        		},	
        		
        		{
					xtype : 'fieldset',
					title : 'Fechas de Control',
					collapsible : true,
					collapsed : true,
					items : [ {
						xtype : 'fieldcontainer',
						layout : 'hbox',
						items : [ {
							xtype : 'datefield',
							fieldLabel : 'Fecha de Creaci\u00f3n:',
							name : 'fhAlta',
							labelWidth : 115,
							width : 200,
							format : 'd/m/Y',
							//disabled : true,
							readOnly : true
						},{
							xtype: 'tbspacer',
							width : 5
						}, 
						{
							xtype : 'datefield',
							fieldLabel : 'Fecha de Modificaci\u00f3n:',
							name : 'fhUltMod',
							labelWidth : 130,
							width : 215,
							format : 'd/m/Y',
							//disabled : true,
							readOnly : true
						} ]
					},
					{
						xtype : 'fieldcontainer',
						layout : 'hbox',
						items : [ , {
							xtype : 'textfield',
							fieldLabel : 'Usuario de Creaci\u00f3n:',
							name : 'cveUsuAlta',
							labelWidth : 115,
							width : 200,
							readOnly : true
						},{
							xtype: 'tbspacer',
							width : 5
						}, 
						{
							xtype : 'textfield',
							fieldLabel : 'Usuario de Modificaci\u00f3n:',
							name : 'cveUsuMod',
							labelWidth : 130,
							width : 215,
							readOnly : true
						} ]
					} ]
				}        		
		];
	}
});
