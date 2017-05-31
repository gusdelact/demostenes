Ext.define('gfi.bin.admctasweb.view.operativos.PersonaCorpGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.personacorpgrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.operativos.PersonaCorporativa'],
	
	model: 'gfi.bin.admctasweb.model.operativos.PersonaCorporativa',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 250,
    
    config: {
    	personaList: null,
    	//numOficio: null
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
	
	//Cargamos la lista de Personas del Modelo al Grid.
	setPersonas: function (value) {
		var me = this; 
		
		value = value || [];
	
		me.personaList = value; 

		me.getStore().getProxy().data = me.personaList;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
    	return [{
	 			text		: 'Id Persona',
	 			dataIndex	: 'idPersona',
	 			sortable	: true,
	 			flex		: 1,
	            editor		: {
	            	xtype		: 'numberfield', 
	            	allowBlank	: false,
	            	maxLength	: 10
	            }
    		},{
	 			text		: 'Nombre / Razón Social / Nombre Contrato',
	 			dataIndex	: 'nombre',
	 			sortable	: true,
	 			flex		: 1,
	            editor		: {
	            	xtype		: 'textfield', 
	            	allowBlank	: true,
	            	maxLength	: 250
	            }
    		},
    		{
	 			text		: 'Situación',
	 			dataIndex	: 'situacion',
	 			sortable	: true,
	 			flex		: 1,
	            editor		: {
	            	xtype		: 'textfield', 
	            	allowBlank	: true,
	            	maxLength	: 2
	            }
    		},    		    		
    		{
 			text		: 'RFC',
 			dataIndex	: 'rfc',
 			sortable	: true,
 			flex		: 1,
            editor		: {
            	xtype		: 'textfield', 
            	allowBlank	: true,
            	maxLength	: 14
            			},
    		},
    		{
     			text		: 'Id Contrato',
     			dataIndex	: 'idContrato',
     			sortable	: true,
     			flex		: 1,
                editor		: {
                	xtype		: 'textfield', 
                	allowBlank	: true,
                	maxLength	: 14
                			},
        	}
    	];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	   
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.operativos.PersonaCorporativa',
//	    	defaultPageSize: 20,
	    	pageSize: 15,
//	    	enablePaging: true,
	    	remoteSort: false,
	    	sorters: [{
	            property: 'idPersona',
	            direction: 'ASC'
	        }],
	    	proxy: {
	    		type: 'pagingmemory',
	    		data: me.personaList,
	    		reader: {type: 'json'}
	       }
	   });
    }
    
});