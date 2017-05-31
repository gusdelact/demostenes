Ext.define('gfi.bin.admctasweb.view.cargaautomatica.CargaAutomaticaView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.cargaAutomatica',

	config : {
		header: 'Carga automática de archivos',
		formType: gfi.FormTypes.CUSTOM,
		baseUrl: 'procesoautomatico/carga/',
		//pack: 'stretch',
		secured: false,
		sizeable : false,
		resizable : false,
		userActions: {
			ejecutarCargaAutomatica: {
				//buttonText: 'Ejecutar carga automática',
				createButton: false,
				statusConfig: 'Buscar',
				url: 'ejecuta.htm',
				pack : 'start',
				sync: false,
			}
		}
	},

	getMinWidth : function() {
		return 600;
	},

	getWidth : function() {
		return 600;
	},
	
	//Construimos los Items de la Forma.
	buildItems : function() {
    	
		var me = this;
		var descripcion = '<p>El proceso automatizado de carga y registro de oficios realiza el registro de los oficios que se encuentren en el repositorio intermedio</p>' +
						  '<p>Una vez iniciado el proceso, generará la descompresión de los archivos ZIP ubicados ' + 
						  'en la estructura del repositorio intermedio indicado</p>' +
						  '<p>El sistema realizará la descompresión del archivo y leerá los documentos asociados a cada uno de los oficios para Ingresar los datos correspondientes.</p>';
    	
    	return [ 
    	        
				{
					xtype : 'panel',
					frame : true,
					//title: 'Descripción',
					height : '100%',				
					anchorSize : '100%',
					html : descripcion,
					formStatus : [gfi.component.BasicForm.SEARCH ]
				},
				{ 
					xtype: "tbspacer",
					height : 10
				},
				
            	{
                    xtype: 'fieldcontainer',
                    layout: 'hbox',
                    items: [
    				{ 
    					xtype: "tbspacer",
    					width : 160
    				},         		
    				{
                		xtype : 'button',
                		text : 'Ejecutar carga automática',
                		width : 220,
                	    handler: function() { me.executeAction('ejecutarCargaAutomatica');}
            		}
                    ]
                },	

    	];
	},
	
	//ACCIONES PROPIAS
    
    //Accion para ejecutar carga automática
    doEjecutarCargaAutomatica : function(options)	{
		var me = this;
		me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeEjecutarCargaAutomatica);			
	},
	
	//Completa la accion de Impormir el Reporte.
	completeEjecutarCargaAutomatica : function (completed, options){
		var me = this;
		if(completed) {
			if (options.message) {
				me.showTextMessage(options.message, false);
			}
		}
		else {
			me.showErrorMessage(options.message);
		}					
	}
});