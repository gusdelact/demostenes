Ext.define('gfi.bin.admctasweb.view.reportes.BitacoraExcepcionGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.casosespecialesgrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.reportes.BitacoraExcepcion'],
	
	model: 'gfi.bin.admctasweb.model.reportes.BitacoraExcepcion',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	bitExcepcionList: null,
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
	
	//Cargamos la lista de bitácora de excepciones del Modelo al Grid.
	setBitExcepcionList: function (value) {
		var me = this; 
		
		value = value || [];
		
		me.bitExcepcionList = value;
		
		me.getStore().getProxy().data = me.bitExcepcionList;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
    	return [
    	{
 			text		: 'Contrato',
 			dataIndex	: 'idContrato',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 150
		},		
		{
 			text		: 'Titular',
 			dataIndex	: 'titular',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Fecha',
 			dataIndex	: 'fExcepcion',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Estatus',
 			dataIndex	: 'situacion',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Usuario',
 			dataIndex	: 'descUsuario',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Observaciones',
 			dataIndex	: 'motivo',
 			sortable	: true,
 			flex		: 1,
		},
		];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;

	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.reportes.BitacoraExcepcion',
	    	remoteSort: false,
//	    	defaultPageSize: 20,
	    	pageSize: 15,
//	    	enablePaging: true,
	    	sorters: [{
	            property: 'idContrato',
	            direction: 'ASC'
	        }],
	        proxy: {
	    		type: 'pagingmemory',
	    		//type: 'memory',
	    		data: me.bitExcepcionList,
	    		reader: {type: 'json'}
	        },
	   });
    }
    
});