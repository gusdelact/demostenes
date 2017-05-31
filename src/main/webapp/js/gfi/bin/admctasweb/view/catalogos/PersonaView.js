Ext.define('gfi.bin.admctasweb.view.catalogos.PersonaView', {
	extend : 'gfi.corp.component.form.BasicForm',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],

	config : {
		header: 'Consulta de Personas',
		formType: gfi.corp.component.form.Types.CONSULTA,
		baseUrl: 'catalogos/personaView/',
		secured: false,		
		grupo: 'admctasweb',
		clave: 'personas',		
		primaryKeys: ['numOficio','tipoOficio','numConsec'],		
		modelClass: 'gfi.bin.admctasweb.model.catalogos.Persona'
	},
	
	getMinWidth : function() {
		return 490;
	},

	getWidth : function() {
		return 490;
	},
	
	buildItems : function() {
		return [ {
			xtype : 'textfield',
			fieldLabel : 'Número de Oficio:',
			name : 'numOficio',
			labelWidth: 140,
			width : 320,
			readOnly: true
		},{
			xtype : 'textfield',
			fieldLabel : 'Tipo de Oficio:',
			name : 'tipoOficio',
			labelWidth: 140,
			width : 180,
			readOnly: true
		},{
			xtype : 'textfield',
			fieldLabel : 'Número Consecutivo:',
			fieldStyle	: {textTransform: 'uppercase'},
			name : 'numConsec',
			maxLength 	: 3,
            maxLengthText : 'M\u00E1ximo 3 n\u00FAmero de caracteres revasado.',
            msgTarget 	: 'under',
			labelWidth: 140,
			width : 180,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'combobox',
			fieldLabel : 'Tipo de Persona:',
			name : 'tipoPersona',
			store:  Ext.create('Ext.data.Store', {
			    fields: ['abbr', 'name'],
			    data : [
			        {"abbr":"PF", "name":"PERSONA FISICA"},
			        {"abbr":"PM", "name":"PERSONA MORAL"}
			    ]
			}),
			displayField: 'name',
			valueField: 'abbr',
			labelWidth: 140,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Nombre o Razón Social:',
			name : 'nombre',
		    maxLength 	: 250,
	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        msgTarget 	: 'under',
			labelWidth: 140,
			width : 450,
			fieldStyle	: {textTransform: 'uppercase'},
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Apellido Paterno:',
			name : 'apPaterno',
			labelWidth: 140,
		    maxLength 	: 60,
	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        msgTarget 	: 'under',
			fieldStyle	: {textTransform: 'uppercase'},
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Apellido Materno:',
			name : 'apMaterno',
			labelWidth: 140,
		    maxLength 	: 60,
	        maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        msgTarget 	: 'under',
			fieldStyle	: {textTransform: 'uppercase'},
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'RFC:',
			name : 'rfc',
            maxLength 	: 14,
            maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
            msgTarget 	: 'under',
			labelWidth: 140,
			fieldStyle	: {textTransform: 'uppercase'},
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		},{
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
					disabled : true,
					readOnly : true
				}, {
					xtype : 'splitter'
				}, {
					xtype : 'datefield',
					fieldLabel : 'Fecha de Modificaci\u00f3n:',
					name : 'fhUltMod',
					labelWidth : 130,
					width : 215,
					format : 'd/m/Y',
					disabled : true,
					readOnly : true
				} ]
			}, {
				xtype : 'fieldcontainer',
				layout : 'hbox',
				items : [ {
					xtype : 'textfield',
					fieldLabel : 'Usuario de Creaci\u00f3n:',
					name : 'cveUsuAlta',
					labelWidth : 115,
					width : 200,
					disabled : true
				},{
					xtype : 'splitter'
				}, {
					xtype : 'textfield',
					fieldLabel : 'Usuario de Modificaci\u00f3n:',
					name : 'cveUsuMod',
					labelWidth : 130,
					width : 215,
					disabled : true
				} ]
			} ]
		}
		];
	}
});

