Ext.application({
	
	requires: ['gfi.utils.Constants',
	           'Ext.container.Viewport',
	           'gfi.corp.component.security.SecurityInspector',
	           'gfi.corp.component.monitorInactividad.MonitorInactividad',
	           'gfi.corp.component.form.BasicForm',
	           'Ext.window.MessageBox',
	           'gfi.corp.component.menu.controller.Menu',
	           'gfi.icb.controller.MyController'],
	name: 'gfi',
	appFolder : 'js/gfi',
	autoCreateViewport: true,
    
	controllers: [
       'gfi.corp.component.menu.controller.Menu',
       'gfi.icb.controller.MyController'
    ],
    
    init : function(application){
    	Ext.define('gfi.utils.CustomConstants',{});
    	//gfi.utils.CustomConstants.menuStoreClass = 'gfi.icb.store.tmpMenuStore';
    	gfi.SecurityInspector._logoutOnError = false;
    },
    
    launch : function(){
    	// Definen los separadores numericos
    	Ext.util.Format.thousandSeparator = ',';
    	Ext.util.Format.decimalSeparator = '.';
    	
    	if(!gfi.SecurityInspector._SecurityInfoReady){
    		Ext.getBody().mask(' Cargando informacion de seguridad ... ');
    	}
    	
    	//Definición de Timeout Global 10 minutos - Junio 2015
    	Ext.Ajax.timeout = 600000;

    	Ext.override(Ext.form.Basic, {
    	    timeout: Ext.Ajax.timeout / 1000
    	});
    	Ext.override(Ext.data.proxy.Server, {
    	    timeout: Ext.Ajax.timeout
    	});
    	Ext.override(Ext.data.Connection, {
    	    timeout: Ext.Ajax.timeout
    	});
    	
    	
    	var updateHeaders = {
    			funcion :function(){
//    				gfi.SecurityInspector.appName = 'extjs-template';
    				
    				//Recargamos la informacion del header
    		    	Ext.ComponentQuery.query('#__mainTitlePanel')[0].reload();

    		    	//Actualizamos tiempo de inactividad el moniror
    		    	gfi.MonitorInactividad.setMaxTiempoInactividad(gfi.SecurityInspector.maxInactiveInterval * 1000);
    	    	}, 
    	    	onError : function(params,  error){
    	    		console.log(" ocurrio un error al actualizar el header / establecer tiempo inactividad "+ error);
    	    	},
    	    	scope : this
		};
    	
    	gfi.SecurityInspector.executeOnReady(updateHeaders );
    	
    	console.log('Application launched ');
    }
});