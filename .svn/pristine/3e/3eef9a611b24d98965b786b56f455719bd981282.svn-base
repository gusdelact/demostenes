Ext.define('gfi.bin.admctasweb.view.operativos.ContratoCambiosGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.contratocambiosgrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.operativos.ContratoCambios'],
	
	model: 'gfi.bin.admctasweb.model.operativos.ContratoCambios',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	contratosCambiosList: null,
    	//numOficio: null
    },     
    	
	//Inicializamos componente.
	initComponent: function() {
		var me = this, store;
		
		store = me.buildStore();
	    me.columns = me.buildColums(me);
	    me.store = store;
	    
	  //Se agrega barra de herramientas para paginación
	    var bar = Ext.create('Ext.toolbar.Paging', {
	        store: me.store,
	        displayInfo: true
	    });
	    
	    me.bbar = bar;
	    
	    this.callParent();
	},
	
	//Cargamos la lista de Personas del Modelo al Grid.
	setContratos: function (value) {
		var me = this; 
		
		value = value || [];
		me.contratosCambiosList = value; 
		me.getStore().getProxy().data = me.contratosCambiosList;
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
	 			flex		: 1
    		},
     	   {
	 			text		: 'Fecha Operación',
	 			dataIndex	: 'fechaOperacion',
	 			renderer	: Ext.util.Format.dateRenderer('d/m/Y'),
	 			sortable	: true,
	 			flex		: 1
    		},
      	   {
	 			text		: 'Moneda',
	 			dataIndex	: 'moneda',
	 			sortable	: false,
	 			flex		: 1
    		},
     	   {
	 			text		: 'Situación',
	 			dataIndex	: 'situacionContrato',
	 			sortable	: true,
	 			flex		: 1
    		}];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	    
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.operativos.ContratoCambios',
//	    	defaultPageSize: 20,
	    	pageSize: 300,
	    	enablePaging: true,
	    	remoteSort: false,
	    	sorters: [{
	            property: 'idContrato',
	            direction: 'ASC'
	        }],
	    	proxy: {
	    		type: 'pagingmemory',
	    		data: me.contratosCambiosList,
	    		reader: {type: 'json'}
	       }
	   });
    }
    
});