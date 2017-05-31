Ext.define('gfi.bin.admctasweb.view.catalogos.RespContratoGrid',{
    extend: 'gfi.bin.component.grid.GenericGridMan',
    alias: 'widget.respContratogrid',
    
	requires: ['Ext.ux.data.PagingMemoryProxy',
	           'gfi.corp.component.statusbar.StatusBar',
	           'Ext.grid.plugin.RowEditing',	           
	           'gfi.bin.admctasweb.model.catalogos.RespContrato',
	           'gfi.corp.component.buscador.BuscadorTrigger'],

	model: 'gfi.bin.admctasweb.model.catalogos.RespContrato',
    
    hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    height: 250,
    
    config: {
        enableGlobalSearch: false,
        filterable: true,
        enableExport: true,
        name: 'RespContratoGrid',
        cveGrid: 'RespContratoGrid',
        limiteExport: 1000
    },
    	
	initComponent: function() {
		var me = this;
		var	store;
		
		store = Ext.create('gfi.bin.admctasweb.store.catalogos.RespContratoStore');
	    me.store = store;
	    me.columns = me.buildColums(me);	   
	    
	    //Construimos la TBar para el CRUD
	    me.tbar = me.biuldTbar(me);
	    
	    this.callParent();
	   	me.getSelectionModel().selectionMode = 'SINGLE';
	},
	
	plugins: [
	    {
	    	ptype: 'rowediting',
	    	clicksToEdit: 2
	    }
    ],
    
    listeners: {
        'selectionchange': function(view, records) {
        	var me = this;
        	
            me.down('#removerRegistro').setDisabled(!records.length);
        }
    },
	
    buildColums: function (me) {
    	return [{
 			text		: 'Num. Oficio',
 			dataIndex	: 'numOficio',
 			width		: 100,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'NUM_OFICIO',
 			filter		: {
            	field: 'NUM_OFICIO',
            	type: 'string'
            },
            readOnly 	: true
    	},{        	
 			text		: 'Tipo Oficio',
 			dataIndex	: 'tipoOficio',
 			width		: 100,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'TIPO_OFICIO',
 			filter		: {
            	field: 'TIPO_OFICIO',
            	type: 'string'
            },
            readOnly 	: true
 		},{
 			text		: 'ID Contrato',
 			dataIndex	: 'idContrato',
 			width		: 150,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'ID_CONTRATO',
 			filter		: {
            	field: 'ID_CONTRATO',
            	type: 'numeric'
            },
            editor: {
            	xtype: 'gfibuscadortrigger',
            	itemId: 'idCuenta',
	    		grupo: 'admctasweb', 
	    		clave: 'BUSCUENTAX',
	    		linkProperty: 'idContrato'
            }
 		},{        	
 			text		: 'Monto Inicial',
 			dataIndex	: 'montoInicial',
 			width		: 100,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'MONTO_INICIAL',
 			filter		: {
            	field: 'MONTO_INICIAL',
            	type: 'numeric'
            },
            editor: {
                allowBlank: true
            }
 		},{
 			xtype		: 'datecolumn',
        	format		: 'd/m/Y',
 			text		: 'Fh Alta',
 			dataIndex	: 'fhAlta',
 			width		: 100,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'FH_ALTA',
 			filter		: {
            	field: 'FH_ALTA',
            	type: 'date'
            }
 		},{
 			text		: 'Cve Usu Alta',
 			dataIndex	: 'cveUsuAlta',
 			width		: 100,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'CVE_USU_ALTA',
 			filter		: {
            	field: 'CVE_USU_ALTA',
            	type: 'string'
            }
 		},{
 			xtype		: 'datecolumn',
        	format		: 'd/m/Y',
 			text		: 'Fh Mod',
 			dataIndex	: 'fhMod',
 			width		: 100,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'FH_ULT_MOD',
 			filter		: {
            	field: 'FH_ULT_MOD',
            	type: 'date'
            }
 		},{
 			text		: 'Cve Usu Mod',
 			dataIndex	: 'cveUsuMod',
 			width		: 100,
 			hidden		: false,
 			sortable	: true,
 			sortField	: 'CVE_USU_MOD',
 			filter		: {
            	field: 'CVE_USU_MOD',
            	type: 'string'
            }
 		}];
    },
    
    biuldTbar: function(me) {
    	return [{
            text: 'Agregar Registro',
            icon: 'resources/images/drop-add.gif',
			plugins: [Ext.create('gfi.corp.component.security.ControlPlugin', {
            	secureItemId: 'gfi.bin.admctasweb.view.catalogos.RespContratoGridAgregarAction' })], 
            handler : function() {
                me.editingPlugin.cancelEdit();
                
                var respOficio = me.up('gfibasicform');
                var numOficio = respOficio.down('#respOfiOficio').value;
                var tipOficio = respOficio.down('#cboTipoOficio').value;
                // Creamos instancia del modelo
                var registro = Ext.create('gfi.bin.admctasweb.model.catalogos.RespContrato', {
                	numOficio:numOficio,
                	tipoOficio:tipOficio
                });

                me.getStore().insert(0,registro);
                me.editingPlugin.startEdit(registro, 1);
            }
        },{
            itemId: 'removerRegistro',
            text: 'Remover Registro',
            icon: 'resources/images/drop-no.png',
            plugins: [Ext.create('gfi.corp.component.security.ControlPlugin', {
	            secureItemId: 'gfi.bin.admctasweb.view.catalogos.RespContratoGridRemoverAction' })],            
            handler: function() {
                var sm = me.getSelectionModel();
                me.editingPlugin.cancelEdit();
                
                me.getStore().remove(sm.getSelection());
            },
            disabled: true
        },{
            text: 'Guardar',
            icon: 'resources/images/save.png',
            hidden: false,
            plugins: [Ext.create('gfi.corp.component.security.ControlPlugin', {
	            secureItemId: 'gfi.bin.admctasweb.view.catalogos.RespContratoGridGuardarAction' })],            
            handler: function() {
            	
            		var st = me.getStore();
                	st.sync({
                        success: function (batch, eOpts) {
                            Ext.Msg.alert('Status', 'Se guardaron los cambios correctamente');
                        },
                        failure: function (batch, eOpts) {
                            Ext.Msg.alert('Status', 'Error al guardar cambios');
                        }
                    });
					st.load();
            }
        }];
    }    
    
});