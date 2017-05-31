Ext.define('gfi.bin.admctasweb.view.operativos.ArchivoCnbvGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.archivoCnbvGrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.operativos.OficioList'],
	
	model: 'gfi.bin.admctasweb.model.operativos.OficioList',
	
	hideHeaders: false,
    autoScroll: true,
    selModel: {
     selType: 'checkboxmodel',
      mode: 'MULTI',
      allowDeselect : false,
      checkOnly: true
  },    

    loadMask: true,
    height: 200,
    
    config: {
    	listaOficiosResult: null,
    },     
    	
	//Inicializamos componente.
	initComponent: function() {
		var me = this, store;
		
		store = me.buildStore();
	    me.columns = me.buildColums(me);
	    me.store = store;
	    

	    //Se agrega barra de herramientas para paginación
//	    var bar = Ext.create('Ext.toolbar.Paging', {
//	        pageSize: 15,
//	        store: me.store,
//	        displayInfo: true
//	    });
//
//	    me.bbar = bar;
//
	    this.callParent();
	},
	
	//Cargamos la lista de Oficios de Casos Especiales del Modelo al Grid.
	setListaOficiosResult: function (value) {
		var me = this; 
		
		value = value || [];
		
		me.listaOficiosResult = value;
		
		me.getStore().getProxy().data = me.listaOficiosResult;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
    	return [
//		{
//			xtype			: 'checkcolumn',
//		    autoRender		: true,
//		    width			: 80,
//		    dataIndex		: 'selected',
//		    text			: 'Seleccionar',
//		    editor : {
//		        xtype		: 'checkboxfield',
//		        boxLabel	: 'Box Label'
//		    },
//		    listeners: {
//		        checkchange: function (column, recordIndex, checked) {
//		            //alert("checked:" + checked);
//		            console.log("checked: " + checked);
//		            console.log("recordIndex: " + recordIndex);
//		            console.log("column: " + column);
//		        }
//		    },			    
//		},	
		
		{
 			text		: 'N\u00famero de Oficio',
 			dataIndex	: 'numOficio',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 150
		},
		{
 			text		: 'Tipo de Oficio',
 			dataIndex	: 'descTipoOficio',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 120
		},
		{
 			text		: 'Folio',
 			dataIndex	: 'numFolio',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 100
		},
		{
 			text		: 'Expediente',
 			dataIndex	: 'numExped',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 150
		},
		{
 			text		: 'Registro',
 			dataIndex	: 'numRegistro',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 120
		},
		{
 			text		: 'Autoridad',
 			dataIndex	: 'cveAutoridad',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 80
		},
		{
 			text		: 'Empresa',
 			dataIndex	: 'descEmpresa',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 190
		},
		{
 			text		: 'F. Oficio',
 			dataIndex	: 'fhOficio',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 100
		},{
 			text		: 'F. Recepción',
 			dataIndex	: 'fhRecepcion',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 100
		},{
 			text		: 'Plazo',
 			dataIndex	: 'numDiasPzo',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 60
		},
		{
 			text		: 'F. Vencimiento',
 			dataIndex	: 'fhVencimiento',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 100
		},
		{
 			text		: 'Situación',
 			dataIndex	: 'sitOficio',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 80
		},{
 			text		: 'Usuario',
 			dataIndex	: 'cveUsuAlta',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 100
		},{
 			text		: 'F. Reg.',
 			dataIndex	: 'fhRegAcu',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 100
		},
		];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	    
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.operativos.OficioList',
	    	pageSize: 1000,
	        proxy: {
	    		//type: 'pagingmemory',
	    		type: 'memory',
	    		data: me.listaOficiosResult,
	    		reader: {type: 'json'}
	        },
	   });
    }
    
});