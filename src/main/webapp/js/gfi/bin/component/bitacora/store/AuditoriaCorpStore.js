Ext.define('gfi.bin.component.bitacora.store.AuditoriaCorpStore', {
    extend: 'Ext.data.Store',    
    requires: ['gfi.bin.component.bitacora.model.AuditoriaCorp'],

	model: 'gfi.bin.component.bitacora.model.AuditoriaCorp',
	//groupField: 'cveMedio',
	
	proxy: {
		type: 'ajax',
		url:  './bitacora/corp/read',
		reader: { type: 'json', root:'data', totalProperty : 'totalCount'}
	}
});