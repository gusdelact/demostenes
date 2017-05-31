Ext.define('gfi.bin.component.bitacora.view.AuditoriaDetalleGrid',{
    extend: 'gfi.bin.component.grid.GenericGridMan',
    alias: 'widget.auditoriadetallegrid',
    
	requires: ['Ext.ux.data.PagingMemoryProxy',
	           'gfi.bin.component.bitacora.model.AuditoriaDetalle'],

	model: 'gfi.bin.component.bitacora.model.AuditoriaDetalle',
    
    title: 'Detalle del Cambio',
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 520,
    
    config: {
        enableGlobalSearch: true,
        filterable: true,
        enableExport: false,
        limiteExport: 10000
    },
    	
	initComponent: function() {
		var me = this;
		var	store;
		
		store = Ext.create('gfi.bin.component.bitacora.store.AuditoriaDetalleStore');
	    me.store = store;
	    me.columns = me.buildColums(me);	    
	    //me.getStore().load();
	    
	    this.callParent();
	   	me.getSelectionModel().selectionMode = 'MULTI';
	},
	
    buildColums: function (me) {

    	return [{
 			text		: 'Tipo de Auditoria',
 			dataIndex	: 'cveTipoAuditoria',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'CVE_TIPO_AUDITORIA',
 			filter		: {
            	field: 'CVE_TIPO_AUDITORIA',
            	type: 'string'
            }
 		},{
 			text		: 'Campo',
 			dataIndex	: 'campo',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'CAMPO',
 			filter		: {
            	field: 'CAMPO',
            	type: 'string'
            }
 		},{
 			text		: 'Valor',
 			dataIndex	: 'valor',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'VALOR',
 			filter		: {
            	field: 'VALOR',
            	type: 'string'
            }
 		}];
    }
    
});