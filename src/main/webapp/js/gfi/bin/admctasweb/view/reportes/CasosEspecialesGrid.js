Ext.define('gfi.bin.admctasweb.view.reportes.CasosEspecialesGrid', {
	extend : 'Ext.grid.Panel',
	alias: 'widget.casosespecialesgrid',
	
	requires: ['Ext.data.PagingMemoryProxy',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.admctasweb.model.reportes.CasosEspeciales'],
	
	model: 'gfi.bin.admctasweb.model.reportes.CasosEspeciales',
	
	hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 200,
    
    config: {
    	casoEspecialList: null,
    },     
    	
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
	
	//Cargamos la lista de Oficios de Casos Especiales del Modelo al Grid.
	setCasoEspecialList: function (value) {
		var me = this; 
		
		value = value || [];
		
		me.casoEspecialList = value;
		
		me.getStore().getProxy().data = me.casoEspecialList;
		me.getStore().currentPage = 1;
		me.getStore().load();
	},
	
	//Construimos las Columnas del Grid.
	buildColums: function (me) {
    	return [
		{
			xtype			: 'checkcolumn',
		    autoRender		: true,
		    width			: 80,
		    dataIndex		: 'tieneAcuse',
		    text			: '¿Tiene Acuse?',
		    editor : {
		        xtype		: 'checkboxfield',
		        boxLabel	: 'Box Label'
		    },
		    listeners: {
		    	beforecheckchange: function (column, recordIndex, checked, eOpts) {
	                var record = me.getStore().getAt(recordIndex);    
	                var acuse = record.get('acuseFlag');
	                
	                var cveEstatus = record.get('cveEstatus');
	                
	                //Si el acuse ya habia sido marcado en la BD No puede desmarcarse.
	                if(!checked && acuse == 'V') {
	                	Ext.Msg.alert('AdmCtasWeb', 'No puede desmarcar el Acuse una vez registrado en la BD.');
	                	return false;
	                	//record.set('tieneAcuse',true);
	                }
	                
	                if(cveEstatus==null || cveEstatus!='IMPRESC'){
	                	Ext.Msg.alert('AdmCtasWeb', 'No se puede marcar este acuse, no se ha completado la impresión de la respuesta.');
	                	return false;
	                }
	            }
	        }		    
		},
		{
 			text		: 'ImpresoFlag',
 			dataIndex	: 'acuseFlag',
 			flex		: 1,
 			minWidth	: 150,
 			hidden		: true
		},
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
 			flex		: 1
		},
		{
 			text		: 'Folio',
 			dataIndex	: 'numFolio',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Expediente',
 			dataIndex	: 'numExped',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Registro',
 			dataIndex	: 'numRegistro',
 			sortable	: true,
 			flex		: 1
		},
		{
 			text		: 'Fecha Recep.',
 			dataIndex	: 'fhRecepcion',
 			sortable	: true,
 			flex		: 1,
		},
		{
 			text		: 'Fecha Resp.',
 			dataIndex	: 'fhEnvio',
 			sortable	: true,
 			flex		: 1,
		},
		{
 			text		: 'Autoridad',
 			dataIndex	: 'nomAutoridad',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 120
		},
		{
 			text		: 'Empresa',
 			dataIndex	: 'descEmpresa',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 190
		},
		{
 			text		: 'Clave Estatus',
 			dataIndex	: 'cveEstatus',
 			sortable	: true,
 			flex		: 1,
 			minWidth	: 80
		},
		
		];
    },
    
    //Construimos el Store del Grid.
    buildStore: function () {
	    var me = this;

	    return Ext.create('Ext.data.Store', {
	    	model: 'gfi.bin.admctasweb.model.reportes.CasosEspeciales',
	    	remoteSort: false,
//	    	defaultPageSize: 20,
	    	pageSize: 15,
//	    	enablePaging: true,
	    	sorters: [{
	            property: 'fhRecepcion',
	            direction: 'ASC'
	        },{
	            property: 'numOficio',
	            direction: 'ASC'
	        }],
	        proxy: {
	    		type: 'pagingmemory',
	    		//type: 'memory',
	    		data: me.casoEspecialList,
	    		reader: {type: 'json'}
	        },
	   });
    },
    
    onCheckChange: function(column, rowIndex, checked, eOpts) {
        var record = processStore.getAt(rowIndex);    
        var columnIndex = column.getIndex();
        for (var i = 1; i <= 10; i++) {
            if (i <= columnIndex) {
                record.set('Phase'+i, true);
            } 
            else 
            {
                record.set('Phase'+i, false);
            }
        }
    }        
    
});