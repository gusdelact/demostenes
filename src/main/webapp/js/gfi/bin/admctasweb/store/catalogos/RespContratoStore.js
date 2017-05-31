Ext.define('gfi.bin.admctasweb.store.catalogos.RespContratoStore', {
    extend: 'Ext.data.Store',    
    requires: ['gfi.bin.admctasweb.model.catalogos.RespContrato'],

	remoteSort: true,
	model: 'gfi.bin.admctasweb.model.catalogos.RespContrato',
	autoSync: false,
	
	proxy: {
		type: 'ajax',
		
		api: {
            read : './catalogos/respContrato/read',
            create : './catalogos/respContrato/create/',
            update : './catalogos/respContrato/update/',
            destroy : './catalogos/respContrato/delete/',
        },
        
        actionMethods: {
            destroy : 'POST',
            read : 'GET',
            create : 'POST',
            update : 'POST'
        },
        
		writer : {
			writeAllFields : true,
			allowSingle : false
		},
		
		reader: { type: 'json', root:'data', totalProperty : 'totalCount'}
	}
});