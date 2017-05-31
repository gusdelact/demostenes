Ext.define('gfi.bin.admctasweb.view.catalogos.DocumentoGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.documentogrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'Ext.grid.plugin.RowEditing',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.catalogos.Documento',
	           'gfi.bin.admctasweb.view.catalogos.CargaFile'],
	
	model: 'gfi.bin.admctasweb.model.catalogos.Documento',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	documentoList: null,
    	numOficio: null
    },     
	
	//Inicializamos componente.
	initComponent: function() {
		var me = this,
			store;
		
		store = me.buildStore();
	    me.columns = me.buildColums(me);
	    me.store = store;
	    
	    this.callParent();
	},
	
	//Cargamos la lista de Personas del Modelo al Grid.
	setDocumentos: function (value) {
		var me = this; 
		
		value = value || [];
	
		me.documentoList = value; 

		me.getStore().getProxy().data = me.documentoList;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
    	return [{
 			text		: 'Número de Documento',
 			dataIndex	: 'numDocto',
 			disabled	: false,
 			flex		: 1
 		},{
 			text		: 'Clave de Documento',
 			dataIndex	: 'cveDocto',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Nombre de Archivo',
 			dataIndex	: 'nomDocto',
 			sortable	: true,
 			flex		: 2
 		},{
 			text		: 'Fecha Alta',
 			dataIndex	: 'fhAlta',
 			sortable	: true,
 			flex		: 1
 		},{
 			text		: 'Usuario Alta',
 			dataIndex	: 'cveUsuAlta',
 			sortable	: true,
 			flex		: 1
 		}];
    },
    
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	   
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.catalogos.Documento',
	    	remoteSort: false,
	    	sorters: [{
	            property: 'numDocto',
	            direction: 'ASC'
	        }],
	    	proxy: {
	    		type: 'pagingmemory',
	    		data: me.documentoList,
	    		reader: {type: 'json'}
	       }
	   });
    },
   
    
});


