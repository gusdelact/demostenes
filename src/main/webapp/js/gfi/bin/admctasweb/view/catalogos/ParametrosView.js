Ext.define('gfi.bin.admctasweb.view.catalogos.ParametrosView', {
	extend 		: 'gfi.corp.component.form.BasicForm',
	requires 	: ['gfi.corp.component.buscador.BuscadorTrigger'],

	config 	: {
		header		: 'Cat\u00e1logo de Par\u00e1metros de Oficios CNBV',
		formType	: gfi.corp.component.form.Types.CATALOGO,		
		grupo		: 'admctasweb', 
		clave		: 'parametros',
		modelClass 	: 'gfi.bin.admctasweb.model.catalogos.Parametros',
		primaryKeys	: ['cveGpoParam','cveParam'],
		baseUrl		: 'catalogos/parametros/',
		secured		: false,
	},
	
	userActions	: {
		borrar		: {
			createButton	: false,
		},
	},
	
	getMinWidth	: function() {
		return 600;
	},

	getWidth	: function() {
		return 600;
	},
	
	buildItems 	: function() {
		return [ 

		{
			xtype 			: 'textfield',
			fieldLabel 		: 'Clave grupo:',
			fieldStyle		: {textTransform: 'uppercase'},
			name 			: 'cveGpoParam',
		    maxLength 		: 10,
	        maxLengthText	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',			
			labelWidth 		: 140,
			width			: 578,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW]
			
		},
		{
			xtype			: 'textfield',
			fieldLabel 		: 'Clave parametro:',
			fieldStyle		: {textTransform: 'uppercase'},
			name 			: 'cveParam',
		    maxLength 		: 8,
	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',			
			labelWidth		: 140,
			width			: 578,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW]
			
		},
	    {
			xtype 			: 'textfield',
			fieldLabel 		: 'Par\u00e1metro:',
			name 			: 'descParam',
		    maxLength 		: 80,
	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',
			labelWidth 		: 140,
			width			: 578,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW,
			           		   gfi.component.BasicForm.STATUS_UPDATE],
		}, 
		{
			xtype 			: 'combobox',
			fieldLabel 		: 'Situaci\u00f3n:',
			name 			: 'sitParam',
			editable		: false,
			//allowBlank		:false,
			emptyText		: 'Seleccione',
			msgTarget 		: 'under',
			store			:  Ext.create('Ext.data.Store', {
			    fields			: ['abbr', 'name'],
			    data 			: [{"abbr":"AC", "name":"Activo"},
			         			   {"abbr":"IN", "name":"Inactivo"}]
			}),
			displayField	: 'name',
			valueField		: 'abbr',
			labelWidth		: 140,
			width			: 578,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW,
			           		   gfi.component.BasicForm.STATUS_UPDATE]
		},
		
        {
			xtype 		: 'fieldset',
			title 		: 'Fechas de Control',
			collapsible : true,
			collapsed 	: true,
			items 		: [ {
				xtype 		: 'fieldcontainer',
				layout 		: 'hbox',
				items 		: [ {
					xtype 		: 'datefield',
					fieldLabel 	: 'Fecha de Creaci\u00f3n:',
					name 		: 'fhAlta',
					labelWidth 	: 115,
					width 		: 200,
					format 		: 'd/m/Y',
					//disabled 	: true,
					readOnly 	: true
				},{
					xtype: 'tbspacer',
					width : 5
				}, 
				{
					xtype 		: 'datefield',
					fieldLabel 	: 'Fecha de Modificaci\u00f3n:',
					name 		: 'fhUltMod',
					labelWidth 	: 130,
					width 		: 215,
					format 		: 'd/m/Y',
					//disabled 	: true,
					readOnly 	: true
				} ]
			},
			{
				xtype 		: 'fieldcontainer',
				layout 		: 'hbox',
				items 		: [ , {
					xtype 		: 'textfield',
					fieldLabel 	: 'Usuario de Creaci\u00f3n:',
					name 		: 'cveUsuAlta',
					labelWidth 	: 115,
					width 		: 200,
					readOnly 	: true
					//disabled 	: true
				},{
					xtype: 'tbspacer',
					width : 5
				}, 
				{
					xtype 		: 'textfield',
					fieldLabel 	: 'Usuario de Modificaci\u00f3n:',
					name 		: 'cveUsuMod',
					labelWidth 	: 130,
					width 		: 215,
					readOnly 	: true
					//disabled 	: true
				} ]
			}],
		},		
		
		];		
	}
});
