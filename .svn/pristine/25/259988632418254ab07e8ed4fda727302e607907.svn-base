Ext.define('gfi.bin.component.bitacora.view.AuditoriaCorpGrid',{
    extend: 'gfi.bin.component.grid.GenericGridMan',
    alias: 'widget.auditoriacorpgrid',
    
	requires: ['Ext.ux.data.PagingMemoryProxy',
	           'Ext.grid.feature.Grouping',
	           'gfi.bin.component.bitacora.model.AuditoriaCorp'],

	model: 'gfi.bin.component.bitacora.model.AuditoriaCorp',
    
    title: 'Evento de Auditoria',
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
		
		store = Ext.create('gfi.bin.component.bitacora.store.AuditoriaCorpStore');
	    me.store = store;
	    me.columns = me.buildColums(me);	    
	    //me.getStore().load();
	    
	    this.callParent();
	   	me.getSelectionModel().selectionMode = 'MULTI';
	},
	
//	features : [{
//		ftype: 'grouping',
//		groupHeaderTpl : '{columnName}: {name} ({rows.length} Item{[values.rows.length > 1 ? "s" : ""]})',
//		hideGroupedHeader : true,
//		startCollapsed : false,
//		id : 'mediosGrouping'
//	}],  
	
    buildColums: function (me) {

    	return [{
 			text		: 'Id Auditoria',
 			dataIndex	: 'idAuditoria',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'ID_AUDITORIA',
 			filter		: {
            	field: 'ID_AUDITORIA',
            	type: 'numeric'
            }
 		},{
 			text		: 'Id Empresa',
 			dataIndex	: 'idEmpresa',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'ID_EMPRESA',
 			filter		: {
            	field: 'ID_EMPRESA',
            	type: 'numeric'
            }
 		},{
 			text		: 'Aplicaci\u00F3n',
 			dataIndex	: 'cveAplicacion',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'CVE_APLICACION',
 			filter		: {
            	field: 'CVE_APLICACION',
            	type: 'string'
            }
 		},{
 			text		: 'Medio',
 			dataIndex	: 'cveMedio',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'CVE_MEDIO',
 			filter		: {
            	field: 'CVE_MEDIO',
            	type: 'string'
            }
 		},{
 			text		: 'Tipo de Evento',
 			dataIndex	: 'cveTipoEvento',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'CVE_TIPO_EVENTO',
 			filter		: {
            	field: 'CVE_TIPO_EVENTO',
            	type: 'string'
            }
 		},{
 			text		: 'Clave Usuario',
 			dataIndex	: 'cveUsuario',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'CVE_USUARIO',
 			filter		: {
            	field: 'CVE_USUARIO',
            	type: 'string'
            }
 		},{
 			xtype		: 'datecolumn',
        	format		: 'd/m/Y',
 			text		: 'Fh Operación',
 			dataIndex	: 'fhOperacion',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'FH_OPERACION',
 			filter		: {
            	field: 'FH_OPERACION',
            	type: 'date'
            }
 		},{
 			xtype		: 'datecolumn',
        	format		: 'd/m/Y',
 			text		: 'Fh Registro',
 			dataIndex	: 'fhRegistro',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'FH_REGISTRO',
 			filter		: {
            	field: 'FH_REGISTRO',
            	type: 'date'
            }
 		},{
 			text		: 'Método',
 			dataIndex	: 'metodo',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'METODO',
 			filter		: {
            	field: 'METODO',
            	type: 'string'
            }
 		},{
 			text		: 'Nivel Auditoria',
 			dataIndex	: 'nivelAuditoria',
 			flex		: 1,
 			hidden		: true,
 			sortable	: true,
 			sortField	: 'NIVEL_AUDITORIA',
 			filter		: {
            	field: 'NIVEL_AUDITORIA',
            	type: 'string'
            }
 		},{
 			text		: 'Referencia',
 			dataIndex	: 'referencia',
 			flex		: 1,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'REFERENCIA',
 			filter		: {
            	field: 'REFERENCIA',
            	type: 'string'
            }
 		}];
    }
    
});