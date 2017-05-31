Ext.define('gfi.bin.admctasweb.view.operativos.ExclusionesView', {
	extend : 'gfi.corp.component.form.BasicForm',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],

	config : {
		header		: 'Exclusiones en b\u00fasqueda de nombres de contratos',
		formType	: gfi.FormTypes.CATALOGO,
		baseUrl		: 'operativos/exclusiones/',
		pack		: 'stretch',
		secured		: false,
		grupo: 'admctasweb',
		clave: 'exclusiones',
		primaryKeys: ['idExclusion'],
		modelClass 	: 'gfi.bin.admctasweb.model.operativos.ExclusionModel'
	},
	
	buildItems : function() {
		return [
		{
			xtype : 'hiddenfield',
			name : 'idExclusion',
			readOnly : true
		},
		{
			xtype : 'textfield',
    		fieldLabel : 'Descripci\u00f3n de exclusi\u00f3n:',
    		name: 'descripcionExclusion',
    		labelWidth: 150,
    		width : 500,
            formStatus : [gfi.component.BasicForm.STATUS_NEW,
                          gfi.component.BasicForm.STATUS_UPDATE]
		},
		{
			xtype : 'checkbox',
    		fieldLabel : 'Situaci\u00f3n',
    		name: 'activo',
    		inputValue: '1',
    		labelWidth: 150,
            formStatus : [gfi.component.BasicForm.STATUS_NEW,
                          gfi.component.BasicForm.STATUS_UPDATE]
		}
		];
	}
});