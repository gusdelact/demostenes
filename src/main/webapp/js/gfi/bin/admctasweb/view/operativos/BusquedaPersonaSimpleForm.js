Ext.define('gfi.bin.admctasweb.view.operativos.BusquedaPersonaSimpleForm', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.busquedasimpleform',
	
	config : {
		header: 'Persona',
		formType: gfi.FormTypes.CATALOGO,
		modelClass : 'gfi.bin.admctasweb.model.operativos.PersonaBusqueda',
		secured: false,		
	},

	buildItems: function() {
			return [
                	{
                		xtype : 'textfield',
                		fieldLabel : 'Nombre:',
                		allowBlank : false,
                		name: 'nombre',
                		labelWidth: 100,
                		width : 417,
            			formStatus : [gfi.component.BasicForm.STATUS_NEW,
            			              gfi.component.BasicForm.STATUS_UPDATE]
                	}, 
                	{
                		xtype : 'textfield',
                		fieldLabel : 'Apellido Paterno:',
                		name: 'paterno',
                		labelWidth: 100,
                		width : 417,
            			formStatus : [gfi.component.BasicForm.STATUS_NEW,
            			              gfi.component.BasicForm.STATUS_UPDATE]
                	}, 
                	{
                		xtype : 'textfield',
                		fieldLabel : 'Apellido Materno:',
                		name: 'materno',
                		labelWidth: 100,
                		width : 417,
            			formStatus : [gfi.component.BasicForm.STATUS_NEW,
            			              gfi.component.BasicForm.STATUS_UPDATE]
                	}, 
                	{
                		xtype : 'textfield',
                		fieldLabel : 'RFC:',
                		name: 'rfc',
                		labelWidth: 100,
                		width : 417,
            			formStatus : [gfi.component.BasicForm.STATUS_NEW,
            			              gfi.component.BasicForm.STATUS_UPDATE]
                	}, 
                	
						
			];
       
    },	
   			
});

