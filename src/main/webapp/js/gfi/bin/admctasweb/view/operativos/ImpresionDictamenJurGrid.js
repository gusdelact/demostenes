Ext.define('gfi.bin.admctasweb.view.operativos.ImpresionDictamenJurGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.impresiondictamenjurgrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'Ext.grid.plugin.RowEditing',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.operativos.ImpresionDictamenJur'],
	
	model: 'gfi.bin.admctasweb.model.operativos.ImpresionDictamenJur',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	impresionDictamenList: null,
    	numOficio: null
    },     
    
    //Configuramos el plugIn de Edición de Rows.
	plugins: [{
		ptype: 'rowediting',
		pluginId: 'roweditor',
		autoCancel: false,
		errorSummary: true,
		clicksToEdit: 2
	}],
	
	//Inicializamos componente.
	initComponent: function() {
		var me = this,
			store;
		
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
	
	//Cargamos la lista de Personas del Modelo al Grid.
	setImpresionDictamen: function (value) {
		var me = this; 
		
		value = value || [];
	
		me.impresionDictamenList = value; 

		me.getStore().getProxy().data = me.impresionDictamenList;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
    	return [{
 			text		: 'Número Oficio',
 			dataIndex	: 'numOficio',
 			sortable	: true,
 			flex		: 1,
 		},{
 			text		: 'Tipo Oficio',
 			dataIndex	: 'tipoOficio',
 			sortable	: true,
 			flex		: 1,
 		},{
 			text		: 'Número Consecutivo',
 			dataIndex	: 'numConsec',
 			sortable	: true,
 			flex		: 1,
 		},{
 			text		: 'Nombre Titular',
 			dataIndex	: 'nombTitular',
 			sortable	: true,
 			flex		: 2
 		},{
 			text		: 'Impreso por Contraloría',
 			dataIndex	: 'impresoContr',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Fecha Alta',
 			dataIndex	: 'fhAlta',
 			sortable	: true,
 			flex		: 1
 		}];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	   
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.operativos.ImpresionDictamenJur',
	    	
	    	pageSize: 15,
	    	remoteSort: false,
	    	sorters: [{
	            property: 'numOficio',
	            direction: 'ASC'
	        }],
	    	proxy: {
	    		type: 'pagingmemory',
	    		data: me.impresionDictamenList,
	    		reader: {type: 'json'}
	       }
	   });
    }
    
});