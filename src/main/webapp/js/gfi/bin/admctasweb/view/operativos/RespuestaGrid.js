Ext.define('gfi.bin.admctasweb.view.operativos.RespuestaGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.prespuestagrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'Ext.grid.plugin.RowEditing',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.operativos.Respuesta'],
	
	model: 'gfi.bin.admctasweb.model.operativos.Respuesta',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	respuestaList: null,
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
	setRespuestas: function (value) {
		var me = this; 
		
		value = value || [];
	
		me.respuestaList = value; 

		me.getStore().getProxy().data = me.respuestaList;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
    	return [{
 			text		: 'Número Consecutivo',
 			dataIndex	: 'numConsec',
 			sortable	: true,
 			flex		: 1,
 		},{
 			text		: 'Contrato',
 			dataIndex	: 'idContrato',
 			sortable	: true,
 			flex		: 1,
 		},{
 			text		: 'Tipo Respuesta',
 			dataIndex	: 'descTipoRespuesta',
 			sortable	: true,
 			flex		: 1,
 			hidden		: true
 		},{
 			text		: 'Tipo Caso',
 			dataIndex	: 'tipoCaso',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Situación Envio',
 			dataIndex	: 'sitEnvio',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Medio Electrónico',
 			dataIndex	: 'bMedioElec',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Nombre Titular',
 			dataIndex	: 'nomTitular',
 			sortable	: true,
 			flex		: 2
 		},{
 			text		: 'Tipo Titular',
 			dataIndex	: 'tipoTitular',
 			sortable	: true,
 			flex		: 1,
 			hidden		: true,
 		},
 		{
 			text		: 'Situación Cuenta',
 			dataIndex	: 'descSitCuenta',
 			sortable	: true,
 			flex		: 1
 		}];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	   
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.operativos.Respuesta',
	    	
	    	pageSize: 15,
	    	remoteSort: false,
	    	sorters: [{
	            property: 'numOficio',
	            direction: 'ASC'
	        }],
	    	proxy: {
	    		type: 'pagingmemory',
	    		data: me.respuestaList,
	    		reader: {type: 'json'}
	       }
	   });
    }
    
});