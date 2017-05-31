Ext.define('gfi.bin.admctasweb.view.reportes.BitacoraDoctosEliminadosGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.bitacoradoctoseliminadosgrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.catalogos.DocumentoEliminado'],
	
	model: 'gfi.bin.admctasweb.model.catalogos.DocumentoEliminado',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	documentoEliminadoList: null,
    },     
    	
	//Inicializamos componente.
	initComponent: function() {
		var me = this, store;

		store = me.buildStore();
	    me.columns = me.buildColums(me);
	    me.store = store;

	    //Se agrega barra de herramientas para paginación
	    var bar = Ext.create('Ext.toolbar.Paging', {
	        pageSize: 15,
	        store: me.store,
	        displayInfo: true
	    });

	    me.bbar = bar;

	    this.callParent();
	},
	
	//Cargamos la lista de Documentos Eliminados del Modelo al Grid.
	setDocumentoEliminadoList: function (value) {
		var me = this; 
		
		value = value || [];
		
		me.documentoEliminadoList = value;
		
		me.getStore().getProxy().data = me.documentoEliminadoList;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
    	return [
		{
 			text		: 'Número de Oficio',
 			dataIndex	: 'numOficio',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Tipo de Oficio',
 			dataIndex	: 'descTipoOficio',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Clave de Documento',
 			dataIndex	: 'descCveDocto',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Documento',
 			dataIndex	: 'nomDocto',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Fecha de Eliminación',
 			dataIndex	: 'fhElim',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Usuario Eliminación',
 			dataIndex	: 'cveUsuElim',
 			sortable	: true,
 			flex		: 1
		},
		];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;

	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.catalogos.DocumentoEliminado',
	    	remoteSort: false,
//	    	defaultPageSize: 20,
	    	pageSize: 15,
//	    	enablePaging: true,
	    	sorters: [{
	            property: 'numOficio',
	            direction: 'ASC'
	        }],
	    	proxy: {
	    		type: 'pagingmemory',
	    		//type: 'memory',
	    		data: me.documentoEliminadoList,
	    		reader: {type: 'json'}
	        },
	   });
    }
    
});