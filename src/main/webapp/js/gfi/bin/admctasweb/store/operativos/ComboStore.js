Ext.define('gfi.bin.admctasweb.store.operativos.ComboStore', {
	extend : 'Ext.data.Store',		
	requires : ['gfi.bin.admctasweb.model.operativos.Item'],
	
	model : 'gfi.bin.admctasweb.model.operativos.Item',
	autoLoad: true,
	proxy: {
		type: 'memory',
		reader: {type: 'json'}
   },

	

});