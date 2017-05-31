Ext.define('gfi.bin.admctasweb.view.catalogos.DocumentoView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.documentoview',
	
	//Configuración del Componente.
	config : {
		header: 'Consulta de Documentos',
		formType: gfi.FormTypes.Consulta,
		baseUrl: 'catalogos/documentoView/',
		modelClass : 'gfi.bin.admctasweb.model.catalogos.Documento',
		primaryKeys: ['numOficio','tipoOficio','numDocto'],
		grupo: 'admctasweb',
		clave: 'documentos',	
		secured: false,
	},
	
	getMinWidth : function() {
		return 490;
	},

	getWidth : function() {
		return 490;
	},
	
	//Construimos los items de la Forma
	buildItems : function() {
		return [
		{
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
			width : 200,
			readOnly: true
		},{
			xtype : 'numberfield',
			fieldLabel : 'Numero de Documento:',
			name : 'numDocto',
			labelWidth: 140,
			width : 200,
			readOnly: true
		}, {
			xtype : 'combobox',
			fieldLabel : 'Clave de Documento:',
			name : 'cveDocto',
			store:  Ext.create('Ext.data.Store', {
			    fields: ['cveDocto', 'claveDoc'],
			    data : [
			        {"cveDocto":"OF", "claveDoc":"Oficio"},
			        {"cveDocto":"DA", "claveDoc":"Documento de Autoridad"},
			        {"cveDocto":"EN", "claveDoc":"Enviado a SITI"}
			    ]
			}),
			valueField: 'cveDocto',
			displayField: 'claveDoc',
			labelWidth: 140,
			width : 320,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		},{
			xtype : 'textfield',
			fieldLabel : 'Nombre de Documento:',
			name : 'nomDocto',
			id	 : 'nomDoctoId',
			labelWidth: 140,
			width : 320,
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
				items : [ , {
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
	},
	
	
});

