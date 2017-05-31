Ext.define('gfi.bin.component.bitacora.store.AuditoriaDetalleStore', {
    extend: 'Ext.data.Store',    
    requires: ['gfi.bin.component.bitacora.model.AuditoriaDetalle'],

	remoteSort: true,
	model: 'gfi.bin.component.bitacora.model.AuditoriaDetalle',
	
	proxy: {
		type: 'ajax',
		url:  './bitacora/detalle/read',
		reader: { type: 'json', root:'data', totalProperty : 'totalCount'}
	}
});