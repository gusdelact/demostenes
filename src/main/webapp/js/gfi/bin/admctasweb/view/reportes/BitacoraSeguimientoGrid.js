Ext.define('gfi.bin.admctasweb.view.reportes.BitacoraSeguimientoGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.bitacoraseguimientogrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.reportes.BitacoraSeguimiento'],
	
	model: 'gfi.bin.admctasweb.model.reportes.BitacoraSeguimiento',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	bitacoraList: null,
    	//numOficio: null
    },     

	//Inicializamos componente.
	initComponent: function() {
		var me = this, store;
		
		store = me.buildStore();
	    me.columns = me.buildColums(me);
	    me.store = store;
	    
	    this.callParent();
	},
	
	//Cargamos la lista de Personas del Modelo al Grid.
	setBitacora: function (value) {
		var me = this; 
		
		value = value || [];
	
		me.bitacoraList = value; 

		me.getStore().getProxy().data = me.bitacoraList;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
		return [
		{
			text : 'Clave Estatus',
			dataIndex : 'cveEstatus',
			sortable : true,
			flex : 1
		}, {
			text : 'Descripción',
			dataIndex : 'descripcion',
			sortable : true,
			flex : 2
		}, {
			text : 'Fecha Registro',
			dataIndex : 'fhRegistro',
			renderer : Ext.util.Format.dateRenderer('d/m/Y, g:i a'),
			sortable : true,
			flex : 1
		}, {
			text : 'Clave Usuario',
			dataIndex : 'cveUsuario',
			sortable : true,
			flex : 1
		} ];
    },
    
    // Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	    
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.reportes.BitacoraSeguimiento',
	    	remoteSort: false,
	    	proxy: {
	    		type: 'pagingmemory',
	    		data: me.bitacoraList,
	    		reader: {type: 'json'}
	       }
	   });
    }
    
});