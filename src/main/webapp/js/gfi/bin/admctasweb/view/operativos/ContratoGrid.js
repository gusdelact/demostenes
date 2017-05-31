Ext.define('gfi.bin.admctasweb.view.operativos.ContratoGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.contratogrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.operativos.Contrato'],
	
	model: 'gfi.bin.admctasweb.model.operativos.Contrato',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	contratoList: null,
    	//numOficio: null
    },     
    
    //Configuramos el plugIn de Edición de Rows.
//	plugins: [{
//		ptype: 'rowediting',
//		pluginId: 'roweditor',
//		autoCancel: false,
//		errorSummary: true,
//		clicksToEdit: 2
//	}],
	
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
	
		me.contratoList = value; 

		me.getStore().getProxy().data = me.contratoList;
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
	 			text		: 'Nombre Contrato',
	 			dataIndex	: 'nombreContrato',
	 			sortable	: true,
	 			flex		: 1
    		},
     	   {
	 			text		: 'Tipo Contrato',
	 			dataIndex	: 'tipoContrato',
	 			sortable	: true,
	 			flex		: 1
    		},
      	   {
	 			text		: 'Producto',
	 			dataIndex	: 'producto',
	 			sortable	: true,
	 			flex		: 1
    		},
       	   {
	 			text		: 'Fecha Alta',
	 			dataIndex	: 'fechaAlta',
	 			renderer	: Ext.util.Format.dateRenderer('d/m/Y'),
	 			sortable	: true,
	 			flex		: 1
    		},
       	   {
	 			text		: 'Fecha Baja',
	 			dataIndex	: 'fechaBaja',
	 			renderer	: Ext.util.Format.dateRenderer('d/m/Y'),
	 			sortable	: true,
	 			flex		: 1
    		},
        	   {
	 			text		: 'Cliente',
	 			dataIndex	: 'cliente',
	 			sortable	: true,
	 			flex		: 1
    		},
     	   {
	 			text		: 'Situación',
	 			dataIndex	: 'situacionContrato',
	 			sortable	: true,
	 			flex		: 1
    		},
      	   {
	 			text		: 'Tipo Contratante',
	 			dataIndex	: 'tipoContratante',
	 			sortable	: true,
	 			flex		: 1
    		}];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	    
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.operativos.Contrato',
	    	pageSize: 300,
	    	enablePaging: true,
	    	remoteSort: false,
	    	sorters: [{
	            property: 'idContrato',
	            direction: 'ASC'
	        }],
	    	proxy: {
	    		type: 'pagingmemory',
	    		data: me.contratoList,
	    		reader: {type: 'json'}
	       }
	   });
    }
    
});