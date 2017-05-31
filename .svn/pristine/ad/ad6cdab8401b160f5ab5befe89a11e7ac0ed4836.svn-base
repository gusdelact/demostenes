Ext.define('gfi.bin.admctasweb.view.catalogos.PersonaGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.personagrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'Ext.grid.plugin.RowEditing',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.catalogos.Persona'],
	
	model: 'gfi.bin.admctasweb.model.catalogos.Persona',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	personaList: null,
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
//	    var bar = Ext.create('Ext.toolbar.Paging', {
//	        pageSize: 20,
//	        store: me.store,
//	        displayInfo: true
//	    });
//	    
//	    me.bbar = bar;
	    
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
 			text		: 'Numero Consecutivo',
 			dataIndex	: 'numConsec',
 			disabled	: false,
 			flex		: 1,
 		},{
 			text		: 'Tipo de Persona',
 			dataIndex	: 'tipoPersona',
 			sortable	: true,
 			flex		: 1,
            editor		: {
            	xtype		: 'combobox', 
            	store:  Ext.create('Ext.data.Store', {
    			    fields: ['tipoPersona'],
    			    data : [
    			        {"tipoPersona":"PM"},
    			        {"tipoPersona":"PF"},
    			    ]
    			}),
    			queryMode: 'local', 
    			displayField: 'tipoPersona', 
    			valueField: 'tipoPersona',
            	allowBlank	: false,
            	maxLength	: 2
            }
 		},{
 			text		: 'Nombre / Razón Social',
 			dataIndex	: 'nombre',
 			sortable	: true,
 			flex		: 2,
            editor		: {
            	xtype		: 'textfield', 
            	allowBlank	: false,
            	maxLength	: 250
            }
 		},{
 			text		: 'Apellido Paterno',
 			dataIndex	: 'apPaterno',
 			sortable	: true,
 			flex		: 1,
            editor		: {
            	xtype		: 'textfield', 
            	allowBlank	: true,
            	maxLength	: 60
            }
 		},{
 			text		: 'Apellido Materno',
 			dataIndex	: 'apMaterno',
 			sortable	: true,
 			flex		: 1,
            editor		: {
            	xtype		: 'textfield', 
            	allowBlank	: true,
            	maxLength	: 60
            }
 		},{
 			text		: 'RFC',
 			dataIndex	: 'rfc',
 			sortable	: true,
 			flex		: 1,
            editor		: {
            	xtype		: 'textfield', 
            	allowBlank	: true,
            	maxLength	: 14
            }
 		},{
 			text		: 'CUENTA',
 			dataIndex	: 'cuenta',
 			sortable	: true,
 			flex		: 1,
            editor		: {
            	xtype		: 'textfield', 
            	allowBlank	: true,
            	maxLength	: 30
            }
 		}];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;
	   
	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.catalogos.Persona',
	    	//pageSize: 20,
	    	remoteSort: false,
	    	sorters: [{
	            property: 'numConsec',
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