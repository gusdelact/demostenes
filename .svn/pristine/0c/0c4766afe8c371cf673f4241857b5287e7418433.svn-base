Ext.define('gfi.bin.admctasweb.view.catalogos.AutoridadesView', {
	extend 		: 'gfi.corp.component.form.BasicForm',
	requires 	: ['gfi.corp.component.buscador.BuscadorTrigger'],

	config	: {
		header		: 'Cat\u00e1logo de Autoridades',
		formType	: gfi.corp.component.form.Types.CATALOGO,		
		grupo		: 'admctasweb', 
		clave		: 'autoridades',
		modelClass 	: 'gfi.bin.admctasweb.model.catalogos.Autoridades',
		primaryKeys	: ['cveAutoridad'],
		baseUrl		: 'catalogos/autoridades/',
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

	getWidth 	: function() {
		return 600;
	},
	
	buildItems 	: function() {
		return [ 

		{
			xtype			: 'textfield',
			fieldLabel 		: 'Clave autoridad:',
			fieldStyle		: {textTransform: 'uppercase'},
			name 			: 'cveAutoridad',
		    maxLength 		: 6,
	        maxLengthText	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',			
			labelWidth		: 140,
			width 			: 490,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW]
			
		},
	    {
			xtype 			: 'textfield',
			fieldLabel 		: 'Nombre autoridad:',
			fieldStyle		: {textTransform: 'uppercase'},
			name 			: 'nomAutoridad',
		    maxLength 		: 50,
	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',
			labelWidth		: 140,
			width 			: 490,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW,
			           		   gfi.component.BasicForm.STATUS_UPDATE],
		}, 
		{
			xtype 			: 'combobox',
			fieldLabel 		: 'Situaci\u00f3n:',
			name 			: 'sitAutoridad',
			editable		: false,
			emptyText		: 'Seleccione',
			//allowBlank		: false,
			msgTarget 		: 'under',
			store			:  Ext.create('Ext.data.Store', {
			    fields			: ['abbr', 'name'],
			    data 			: [{"abbr":"AC", "name":"Activo"},
			         			   {"abbr":"IN", "name":"Inactivo"}]
			}),
			displayField	: 'name',
			valueField		: 'abbr',
			labelWidth		: 140,
			width 			: 490,
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
				}, 							
				{
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
					//disabled 	: true
					readOnly 	: true
				},							
				{
					xtype: 'tbspacer',
					width : 5
				}, 
				{
					xtype 		: 'textfield',
					fieldLabel 	: 'Usuario de Modificaci\u00f3n:',
					name 		: 'cveUsuMod',
					labelWidth 	: 130,
					width 		: 215,
					//disabled 	: true,
					readOnly 	: true
				} ]
			}],
		}
		
		];		
	}
});
