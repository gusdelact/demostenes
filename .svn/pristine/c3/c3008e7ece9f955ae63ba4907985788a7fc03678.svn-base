Ext.define('gfi.bin.admctasweb.view.reportes.Panel', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.panel',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'gfi.bin.admctasweb.view.reportes.BitacoraSeguimientoChart',
	           'gfi.corp.component.statusbar.StatusBar'],

	config : {
		header: 'Bitácora de seguimiento de Oficios',
		formType: gfi.FormTypes.CUSTOM,
		baseUrl: 'reportes/bitacoraseguimiento/',
		modelClass: 'gfi.bin.admctasweb.model.reportes.BitacoraSeguimientoList',
		pack: 'stretch',
		secured: false
	},
	
	//Construimos los Items de la Forma.
	buildItems : function() {
    	var me = this;
    	
    	return [{
    		xtype: 'bitacorachart'
    	}
    	];
	}
	
});

