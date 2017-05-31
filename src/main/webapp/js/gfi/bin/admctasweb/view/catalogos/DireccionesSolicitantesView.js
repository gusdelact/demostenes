Ext.define('gfi.bin.admctasweb.view.catalogos.DireccionesSolicitantesView', {
	extend 		: 'gfi.corp.component.form.BasicForm',
	requires 	: ['gfi.corp.component.buscador.BuscadorTrigger'],

	config	: {
		header		: 'Cat\u00e1logo de Direcciones Solicitantes',
		formType	: gfi.corp.component.form.Types.CATALOGO,		
		grupo		: 'admctasweb', 
		clave		: 'dirSolicitantes',
		modelClass 	: 'gfi.bin.admctasweb.model.catalogos.DireccionesSolicitantes',
		primaryKeys	: ['idConfiguracion'],
		baseUrl		: 'catalogos/direccionesSolicitantes/',
		secured		: false,
	},
	
	userActions	: {
		borrar		: {
			createButton	: false,
		},
	},
	
	getMinWidth	: function() {
		return 800;
	},

	getWidth 	: function() {
		return 600;
	},
	
	buildItems 	: function() {
		return [ 

		{
			xtype		: 'textfield',
			fieldLabel 	: 'Identificador:',
			name 		: 'idConfiguracion',
			labelWidth	: 140,
			width 		: 295,
			readOnly	: true
			
		},

		{
		    xtype		: 'radiogroup',
		    fieldLabel	: 'Tipo de Oficio',
		    labelWidth	: 140,
	        //allowBlank	: false,
		    msgTarget 	: 'title',
		    width		: 400,
		    items		: [
		        { boxLabel: 'Judicial',      name: 'tipoOficio', inputValue: 'JU', width:80},
		        { boxLabel: 'Hacendario',    name: 'tipoOficio', inputValue: 'HA', width:100},
		        { boxLabel: 'Aseguramiento', name: 'tipoOficio', inputValue: 'AS', width:120},
		        { boxLabel: 'PLD',           name: 'tipoOficio', inputValue: 'PL', width:50},
		    ],
		    formStatus	: [gfi.component.BasicForm.STATUS_NEW]
	    },

	    {
			xtype 			: 'textfield',
			fieldLabel 		: 'Direcci\u00f3n Solicitante:',
			fieldStyle		: {textTransform: 'uppercase'},
			name 			: 'direccion',
		    maxLength 		: 100,
	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',
			labelWidth		: 140,
			size 			: 100,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW,
			           		   gfi.component.BasicForm.STATUS_UPDATE],
		}, 
		{
			xtype 			: 'textfield',
			fieldLabel 		: 'Gerencia Solicitante:',
			fieldStyle		: {textTransform: 'uppercase'},
			name 			: 'gerencia',
		    maxLength 		: 100,
	        maxLengthText	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',
			labelWidth		: 140,
			size 			: 100,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW,
			           		   gfi.component.BasicForm.STATUS_UPDATE],
		}, 
		{
			xtype 			: 'textfield',
			fieldLabel 		: 'Subgerencia Solicitante:',
			fieldStyle		: {textTransform: 'uppercase'},
			name 			: 'subgerencia',
		    maxLength 		: 100,
	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',
			labelWidth		: 140,
			size 			: 100,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW,
			           		   gfi.component.BasicForm.STATUS_UPDATE],
		}, 
		{
			xtype 			: 'textfield',
			fieldLabel 		: 'Atenci\u00f3n a:',
			fieldStyle		: {textTransform: 'uppercase'},
			name 			: 'nombreAtencion',
		    maxLength 		: 100,
	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',
			labelWidth		: 140,
			size 			: 100,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW,
			           		   gfi.component.BasicForm.STATUS_UPDATE],
		}, 
		{
			xtype 			: 'textfield',
			fieldLabel 		: 'Puesto:',
			fieldStyle		: {textTransform: 'uppercase'},
			name 			: 'puestoAtencion',
		    maxLength 		: 100,
	        maxLengthText 	: 'M\u00E1ximo n\u00FAmero de caracteres alcanzado.',
	        msgTarget 		: 'under',
			labelWidth		: 140,
			size 			: 100,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW,
			           		   gfi.component.BasicForm.STATUS_UPDATE],
		},
		{
			xtype 			: 'combobox',
			fieldLabel 		: 'Nivel:',
			name 			: 'nivel',
			editable		: false,
			emptyText		: 'Seleccione',
//			allowBlank		: false,
			msgTarget 		: 'under',
//			blankText		: 'El nivel es requerido',
			store			:  Ext.create('Ext.data.Store', {
			    fields	: ['nivel'],
			    data 	: [{'nivel':'1'},
			               {'nivel':'2'},
			               {'nivel':'3'}]
			}),
			displayField	: 'nivel',
			valueField		: 'nivel',
			labelWidth		: 140,
			formStatus 		: [gfi.component.BasicForm.STATUS_NEW,
			           		   gfi.component.BasicForm.STATUS_UPDATE]
		},
		{
			xtype 			: 'combobox',
			fieldLabel 		: 'Situaci\u00f3n:',
			name 			: 'situacion',
			editable		: false,
			emptyText		: 'Seleccione',
//			allowBlank		: false,
			msgTarget 		: 'under',
//			blankText		: 'La situación es requerida',
			store			:  Ext.create('Ext.data.Store', {
				fields	: ['clave', 'descripcion'],
			    data 	: [{'clave':'AC', 'descripcion':'Activo'},
			         	   {'clave':'IN', 'descripcion':'Inactivo'}]
			}),
			displayField	: 'descripcion',
			valueField		: 'clave',
			labelWidth		: 140,
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
				}, {
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
					readOnly : true
				},							{
					xtype: 'tbspacer',
					width : 5
				}, 
				{
					xtype 		: 'textfield',
					fieldLabel 	: 'Usuario de Modificaci\u00f3n:',
					name 		: 'cveUsuMod',
					labelWidth 	: 130,
					width 		: 215,
					//disabled 	: true
					readOnly : true
				} ]
			}],
		}
		
		];		
	}
});

