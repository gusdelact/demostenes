Ext.define('gfi.bin.component.bitacora.view.AuditoriaView',{ 
	extend: 'gfi.corp.component.form.BasicForm',
	alias: 'widget.auditoriaview',
	layout: 'fit',
	
	requires: ['Ext.ux.data.PagingMemoryProxy',
	           'gfi.bin.component.bitacora.view.AuditoriaCorpGrid',
	           'gfi.bin.component.bitacora.view.AuditoriaDetalleGrid'
	          ],
	          
    buildPanel : function(items, dockedItems) {
  		return Ext.create('Ext.form.Panel', {
  			dockedItems : dockedItems,
  			items : items,
  			autoScroll: true
  		});
  	},
	
	config: {
		baseUrl: 'bitacora/',  
		secured: true,
		displayStatus: false,
		formType: gfi.FormTypes.CUSTOM
	},
	
	userActions : [],
	
	initComponent: function () {
    	var me = this;
    	//me.setAlign('top');
    	me.callParent();
    },
    
    buildItems: function(){
    	var me = this;
    	
       	var items = [{
       		xtype: 'container',
       		layout: 'hbox',
       		height: 525,
       		items:[{
    			xtype	: 'auditoriacorpgrid',
    			itemId	: 'auditoriacorpgrid',
    			flex	: 2,
    			listeners: {
	    	        click: {
	    	            element: 'body',
	    	            fn: function(){
    	            		var rows = me.down('#auditoriacorpgrid').getSelectionModel().getSelection();
    	            		var idAuditoria = rows[0].data.idAuditoria;
    	            		
    	            		if(idAuditoria != undefined && idAuditoria != null && idAuditoria != ''){
    	            			var detalleGrid = me.down('#auditoriadetallegrid');		
    	            			
    	            			detalleGrid.getStore().proxy.extraParams = {idAuditoria: idAuditoria};
    	            			detalleGrid.getStore().load();
    	            		}
    	            	}
	    	        }
	    	    }
            },{
            	xtype	: 'auditoriadetallegrid',
    			itemId	: 'auditoriadetallegrid',
    			flex	: 1
            }]
       	}];
    	
    	return items;
    }
    
});