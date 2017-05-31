Ext.define('gfi.bin.admctasweb.view.reportes.OficioSeguimientoGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.oficioseguimientogrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'Ext.grid.plugin.RowEditing',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.reportes.OficioSeguimiento'],
	
	model: 'gfi.bin.admctasweb.model.reportes.OficioSeguimiento',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	oficioList: null,
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
		
		//Construimos el Store del Grid.
		store = me.buildStore();
	    me.columns = me.buildColums(me);
	    me.store = store;
	    
	    //Se agrega barra de herramientas para paginación.
	    var bar = Ext.create('Ext.toolbar.Paging', {
	        pageSize: 20,
	        store: me.store,
	        displayInfo: true
	    });
	    
	    me.bbar = bar;
	    
	    this.callParent();
	},
	
	//Cargamos la lista de Personas del Modelo al Grid.
	setOficios: function (value) {
		var me = this; 
		
		value = value || [];
	
		me.oficioList = value; 

		me.getStore().getProxy().data = me.oficioList;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
    	return [{
 			text		: 'Número de Oficio',
 			dataIndex	: 'numOficio',
 			sortable	: true,
 			flex		: 1,
 		},{
 			text		: 'Tipo Oficio',
 			dataIndex	: 'tipoOficio',
 			sortable	: true,
 			flex		: 1,
 		},{
 			text		: 'Folio',
 			dataIndex	: 'numFolio',
 			sortable	: true,
 			flex		: 1,
 			hidden		: true
 		},{
 			text		: 'Expediente',
 			dataIndex	: 'numExped',
 			sortable	: true,
 			flex		: 1,
 			hidden		: true
 		},{
 			text		: 'Registro',
 			dataIndex	: 'numRegistro',
 			sortable	: true,
 			flex		: 1,
 			hidden		: true
 		},{
 			text		: 'Autoridad',
 			dataIndex	: 'cveAutoridad',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Empresa',
 			dataIndex	: 'idEmpresa',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Fecha Recepción',
 			dataIndex	: 'fhRecepcion',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Fecha Envio',
 			dataIndex	: 'fhEnvio',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Fecha Vencimiento',
 			dataIndex	: 'fhVencimiento',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Tipo de Caso',
 			dataIndex	: 'tipoCaso',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Estatus Seguimiento',
 			dataIndex	: 'txEstatus',
 			sortable	: true,
 			flex		: 2
 		},{
 			text		: 'Estatus Pendiente',
 			dataIndex	: 'txEstatusPend',
 			sortable	: true,
 			flex		: 2
 		}];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	   
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.reportes.OficioSeguimiento',
	    	pageSize: 20,
	    	remoteSort: false,
	    	sorters: [{
	            property: 'fhRecepcion',
	            direction: 'ASC'
	        },{
	            property: 'numOficio',
	            direction: 'ASC'
	        }],
	    	proxy: {
	    		type: 'pagingmemory',
	    		data: me.oficioList,
	    		reader: {type: 'json'}
	       }
	   });
    }
    
});