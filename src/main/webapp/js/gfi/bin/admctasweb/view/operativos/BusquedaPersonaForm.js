Ext.define('gfi.bin.admctasweb.view.operativos.BusquedaPersonaForm', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.busquedaPersona',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger,', 'gfi.bin.admctasweb.view.operativos.PersonaCorpGrid'],
	
		config : {
			header: null,
			formType: gfi.FormTypes.CUSTOM,
			baseUrl: 'operativos/respuesta/',
			secured: false,				
			primaryKeys: ['numConsec'],		
			modelClass: 'gfi.bin.admctasweb.model.operativos.PersonaBusqueda',
			pack: 'stretch',
			subFormConfig :{
				embedded: true,
				usePropertyName: false
			},

			userActions: {
				buscarPersonas: {
					//buttonText: 'Buscar personas',
					createButton: false,
					statusConfig: 'Buscar',
					url: 'buscarpersonas.htm',
					sync: false,
				},				
				buscarContratos: {
					buttonText: 'Buscar Contratos',
					statusConfig: 'Buscar',
					url: 'buscarcontratos.htm',
					sync: false,
					pack: 'center',
				},			
				asociarPersonaOficio: {
					buttonText: 'La Persona Solicitada es Cliente',
					statusConfig: 'Buscar',
					url: 'asociaroficio.htm',
					sync: false,
					
				},
				buscarPersonasCustom: {
					//buttonText: 'Buscar personas',
					createButton: false,
					statusConfig: 'Buscar',
					url: 'buscarpersonas.htm',
					sync: false,
				},				
			
				buscarRespuestas: {
					//buttonText: 'Buscar respuestas',
					createButton: false,
					statusConfig: 'Buscar',
					url: 'read.htm',
					sync: false,
				},				
				
				cambiarCliente: {
					buttonText: 'Desasociar el Número de Cliente',
					//createButton: false,
					statusConfig: 'Buscar',
					url: 'cambiarcliente.htm',
					sync: false,
					
				},				
				
				registrarRespNeg: {
					buttonText: 'Respuesta Negativa',
					statusConfig: 'Buscar',
					url: 'negativa.htm',
					sync: false,
					pack: 'center',
				},				
				registrarRespPositiva: {
					buttonText: 'Respuesta Positiva',
					statusConfig: 'Buscar',
					url: 'cuenta.htm',
					sync: false,
					pack: 'center',
				},				
				
				
				limpiar : {
					createButton: false,
					//subFormExecution: true,
					validateStatus: false,
					secured: false,
				},
				
				obtenerSimilaridad : {
					//buttonText: 'Buscar personas',
					createButton: false,
					statusConfig: 'Buscar',
					url: 'obtenersimilaridad.htm',
					sync: false,
				}
				
				
			}
		},
	    	
		 initComponent: function () {
		        var me = this;
		        
		         me.callParent();
		        if (me.actions.asociarPersonaOficio && me.actions.asociarPersonaOficio.button) {
		            me.actions.asociarPersonaOficio.button.setDisabled(true);
		        }
		        
		        if (me.actions.cambiarCliente && me.actions.cambiarCliente.button) {
		            me.actions.cambiarCliente.button.setDisabled(true);
		        }
		        
		        if (me.actions.obtenerSimilaridad) {
		        	me.executeAction('obtenerSimilaridad');
		        }	
		        
		        Ext.Ajax.timeout = 120000;
		  },
		
		getMinWidth : function() {
			return 600;
		},

		getWidth : function() {
			return 600;
		},
			
		//Acción para buscar personas
		doBuscarPersonas : function (options)
		{
			var me = this;
			var nombre = Ext.getCmp('nombre').getValue();
		
			if(nombre=='' || nombre==null){
				me.showMessage('text', 'No ha seleccionado una Persona del Oficio', false, null);
			}
			else{
				var modelo = me.getValues();
				modelo['rfc'] = '';//Se cambia el rfc porque solo se debe buscar por persona
				options.model = modelo;
				me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscarPersonas);			
			}
			
		},
		
		completeBuscarPersonas : function(completed, options) {
			var me = this;
			
			if (options.error) {
				options.error.showMessage();
			} else {	
				me.grid.setPersonas(options.info.get('personaList'));
			}
		},
		
		//Accion para buscar Contratos de personas
		doBuscarContratos : function (options)
		{
			var me = this;
			if (me.grid.getSelectionModel().hasSelection()) {
				   var row = me.grid.getSelectionModel().getSelection()[0];
				   //me.showMessage('text', row.get('idPersona'), false, null);				   
				   
				   //Se crea manualmente el modelo para enviar solo el id de la persona
				   var persona = Ext.create('gfi.bin.admctasweb.model.operativos.PersonaBusqueda', {
					   idPersona: row.get('idPersona'),
					   idContrato: row.get('idContrato')
				   });				   
				   
				   options.model = persona;
				   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscarContratos);			
			}
			else
				me.showMessage('text', 'Seleccione una persona', false, null);
		},
		
		completeBuscarContratos : function(completed, options) {
			
			if (options.error) {
				options.error.showMessage();
			} 
			else {	

				var tab = Ext.getCmp('mainTab');
				tab.setActiveTab(1);
				
				var grid = Ext.getCmp('gridContrato');				
				grid.setContratos(options.info.get('contratosList'));
							
				var grid2 = Ext.getCmp('gridContratoCambios');				
				grid2.setContratos(options.info.get('contratosCambiosList'));
				
			}
		},
		
		doAsociarPersonaOficio : function(options) {			
			var me = this;
			
			if(!me.oficioPendiente())
			{
				me.showMessage('text', 'El Oficio no se encuentra PENDIENTE', false, null);
				return;
			}
			
			if (me.grid.getSelectionModel().hasSelection()) {
				   var row = me.grid.getSelectionModel().getSelection()[0];
				   
				   //Se crea manualmente el modelo para enviar llave de la Persona del Oficio				   
				   var persona = Ext.create('gfi.bin.admctasweb.model.catalogos.Persona', {
					   numOficio : 	Ext.getCmp('oficio').getValue(),
					   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
					   numConsec :	Ext.getCmp('numConsecPersona').getValue(),
					   idPersona: 	row.get('idPersona'),
				   });				   
				   
				   options.model = persona;
				   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeAsociarPersonaOficio);			
			}
			else
				me.showMessage('text', 'Seleccione una persona', false, null);			
		},
		
		completeAsociarPersonaOficio : function(completed, options) {
			var me = this;
			if(completed)
			{
				if (options.message) {
					me.showTextMessage(options.message, false);
				}
				//cliente
				var mod = options.model;
				Ext.getCmp('cliente').setValue(mod['idPersona']);
				//var field= Ext.ComponentQuery.query('#btnAsociarPersonaOficio')[0];
				//field.setDisabled(true);	
				
		        if (me.actions.asociarPersonaOficio && me.actions.asociarPersonaOficio.button) {	            		            
		            me.actions.asociarPersonaOficio.button.setDisabled(true);
		        }
		        
		        if (me.actions.cambiarCliente && me.actions.cambiarCliente.button) {	            		            
		            me.actions.cambiarCliente.button.setDisabled(false);
		        }
				
				
			}
			else
			{
				//me.showErrorMessage(options.error || options.message);
				me.showErrorMessage(options.message);
			}
		},
		
		doBuscarPersonasCustom : function (options)
		{
			var mep = this;
			var options_ = options;
			
			var nombre = Ext.getCmp('nombre').getValue();
		
			if(nombre=='' || nombre==null){
				mep.showMessage('text', 'No ha seleccionado una Persona del Oficio', false, null);
				return;
			}
			
			if(!mep.oficioPendiente())
			{
				mep.showMessage('text', 'El Oficio no se encuentra PENDIENTE', false, null);
				return;
			}
			
		    var popup = Ext.create('gfi.bin.admctasweb.view.operativos.BusquedaPersonaSimpleForm',{
		        commitData : false,
		        modal : true,
	            completeCerrar : function(completed, options){
		            if(completed){
					    if(options.originalAction.actionName=='aceptar'){
					    	var modelo = mep.getValues();//Modelo de pantalla normal
					    	
					    	//Si ningun campo es válido se realiza búsqueda normal
					    	if((options.model.get('nombre') == '' || options.model.get('nombre') == null || options.model.get('nombre') == 'undefined')&&
					    	   (options.model.get('paterno') == '' || options.model.get('paterno') == null || options.model.get('paterno') == 'undefined')&&
					    	   (options.model.get('materno') == '' || options.model.get('materno') == null || options.model.get('materno') == 'undefined')&&
					    	   (options.model.get('rfc') == '' || options.model.get('rfc') == null || options.model.get('rfc') == 'undefined')
					    	)
					    		options_.model = modelo;
					    	else{
					    		options_.model = options.model;
					    		options_.model.set('similaridad', modelo['similaridad']);
					    	}
						    
							mep.sendRequest(mep.getUrl(mep.status, options_.action), options_.model, options_.action, mep.completeBuscarPersonasCustom);
					    }
						else{
							options_.model = mep.getValues();
							mep.sendRequest(mep.getUrl(mep.status, options_.action), options_.model, options_.action, mep.completeBuscarPersonasCustom);
						}
				    }
	            },

		    });
		    
		    var modelo = mep.getValues();
		    var modeloact = Ext.create('gfi.bin.admctasweb.model.operativos.PersonaBusqueda',{

		    	nombre : modelo['nombre'],
		    	paterno :  modelo['paterno'],
		    	materno : modelo['materno'],
		    	rfc : modelo['rfc'],
		    
		    });
		    
		    popup.executeAction('seleccionar',{model:modeloact});
		    
	        popup.showWindow({
	        	initialStatus : gfi.component.BasicForm.STATUS_UPDATE,
	        	maximizable: false,
	        	//closable:false,
	        });
						
		},
		
		completeBuscarPersonasCustom : function(completed, options) {
			var me = this;
			
			if (options.error) {
				options.error.showMessage();
			} else {	
				me.grid.setPersonas(options.info.get('personaList'));
			}
		},
		
		doRegistrarRespPositiva : function (options){
			
		   var me = this;
		   
			//Verifica selección de Oficio y persona
			var consecutivo = Ext.getCmp('numConsecPersona').getValue();
			if(consecutivo == null || consecutivo == ''){
				me.showMessage('text', 'No ha consultado la Persona de un Oficio', false, null);
				return;
			}		
			
			if(!me.oficioPendiente())
			{
				me.showMessage('text', 'El Oficio no se encuentra PENDIENTE', false, null);
				return;
			}
		   
			//Para el caso que se tenga que mandar la cuenta que viene de la persona o 0 si no viene
//			var cuenta = Ext.getCmp('cuenta').getValue();
//			if (cuenta == null || cuenta == '')
//				cuenta = 0;
			
		   var respuesta = Ext.create('gfi.bin.admctasweb.model.operativos.Respuesta', {
			   numOficio : 	Ext.getCmp('oficio').getValue(),
			   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
			   numConsec :	consecutivo,
			   idContrato: 	0,//Modificacion para enviar la cuenta en 0
			   tipoRespuesta : 1,
		   });				   
		
		   options.model = respuesta;
		   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeRegistrarRespPositiva);			
			
		},
		
		completeRegistrarRespPositiva : function(completed, options){
			var me = this;
			if(completed)
			{
				if (options.message) {
					me.showTextMessage(options.message, false);
				}
				
				//me.executeAction('buscarRespuestas');
				var info = options.info.raw;//Se accede a este objeto porque el retorno no coincide con el modelo de esta pantalla (PersonaBusqueda)				
//				var grid = Ext.getCmp('gridRespuestas');				
//				grid.setRespuestas(info['respuestaList']);				
				
				//Se actualiza situacion del oficio
				var buscadorEventos = Ext.getCmp('oficio');
				buscadorEventos.loadRecord(new Ext.util.Filter({property: 'numOficio', value: info['numOficio']}));
				
				
			}
			else
			{
				me.showErrorMessage(options.error || options.message);
			}			
		},
		
		//Acción para buscar respuestas generadas
		doBuscarRespuestas : function(options)
		{
			var me = this;
			var respuesta = Ext.create('gfi.bin.admctasweb.model.operativos.RespuestaList', {
			   numOficio : 	Ext.getCmp('oficio').getValue(),
			   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
			});				   
		
		   options.model = respuesta;
		   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeBuscarRespuestas);			
		   
		},
		
		completeBuscarRespuestas : function(completed, options) 
		{
			var me = this;
			if(completed)
			{
				var info = options.info.raw;//Se accede a este objeto porque el retorno no coincide con el modelo de esta pantalla (PersonaBusqueda)
											//De otra forma se podría obtener de la forma : options.info.get('respuestaList')
				var grid = Ext.getCmp('gridRespuestas');				
				grid.setRespuestas(info['respuestaList']);
				//grid.setRespuestas(options.info.get('respuestaList'));
			}
			else
			{
				me.showErrorMessage(options.error || options.message);
			}	
		},
		
		doLimpiar: function (options) {
			var me = this;
			
			//Salvar similaridad cargada
			var similaridad = Ext.getCmp('similaridad').getValue();
			
			me.mainPanel.getForm().reset(options.reset);
//			me.grid.getStore().removeAll();
//			me.grid.getStore().sync();
			me.grid.setPersonas([]);
			Ext.getCmp('cuentaContainer').hide();
			
			//Se restaura valor de similaridad
			Ext.getCmp('similaridad').setValue(similaridad);

			return true;
		},
		
		completeLimpiar: function (completed, options) {
			var me = this;
			
			if (completed) {			
				if (options.reset) {
					me.changeStatus(gfi.component.BasicForm.STATUS_SEARCH);
				}
				
		        if (me.actions.asociarPersonaOficio && me.actions.asociarPersonaOficio.button) {	            		            
		            me.actions.asociarPersonaOficio.button.setDisabled(true);
		        }
		        
		        if (me.actions.cambiarCliente && me.actions.cambiarCliente.button) {	            		            
		            me.actions.cambiarCliente.button.setDisabled(true);
		        }
				
				
				/*
				 * Inicializa los grids de la metadata... Recarga la configuración incial del catálogo
				 */
				me.clearGridMetadata();
			}
		},
		
		doCambiarCliente : function(options) {
		   var me = this;
		   
			var consecutivo = Ext.getCmp('numConsecPersona').getValue();
			if(consecutivo == null || consecutivo == ''){
				me.showMessage('text', 'No ha consultado la Persona de un Oficio', false, null);
				return;
			}		
			
			if(!me.oficioPendiente())
			{
				me.showMessage('text', 'El Oficio no se encuentra PENDIENTE', false, null);
				return;
			}
			
		   //Se crea manualmente el modelo para enviar llave de la Persona del Oficio				   
		   var persona = Ext.create('gfi.bin.admctasweb.model.catalogos.Persona', {
			   numOficio : 	Ext.getCmp('oficio').getValue(),
			   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
			   numConsec :	Ext.getCmp('numConsecPersona').getValue(),
		   });
		   
		   options.model = persona;
		   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeCambiarCliente);			
		},
		
		completeCambiarCliente : function(completed, options) {
			var me = this;
			if(completed)
			{
				if (options.message) {
					me.showTextMessage(options.message, false);
				}
				
				
				Ext.getCmp('cliente').setValue('');

				//cliente
				//var field= Ext.ComponentQuery.query('#btnAsociarPersonaOficio')[0];
				//field.setDisabled(true);	
		        if (me.actions.asociarPersonaOficio && me.actions.asociarPersonaOficio.button) {	            		            
		            me.actions.asociarPersonaOficio.button.setDisabled(false);
		        }
		        
		        if (me.actions.cambiarCliente && me.actions.cambiarCliente.button) {	            		            
		            me.actions.cambiarCliente.button.setDisabled(true);
		        }
				
				
			}
			else
			{
				//me.showErrorMessage(options.error || options.message);
				me.showErrorMessage(options.message);
			}
		},
		
		//Registra una respuesta negativa
		doRegistrarRespNeg : function (options)
		{
			var me = this;
			//Verifica selección de Oficio y persona
			var consecutivo = Ext.getCmp('numConsecPersona').getValue();
			if(consecutivo == null || consecutivo == ''){
				me.showMessage('text', 'No ha consultado la Persona de un Oficio', false, null);
				return;
			}
			
			if(!me.oficioPendiente())
			{
				me.showMessage('text', 'El Oficio no se encuentra PENDIENTE', false, null);
				return;
			}
			
//			var cuenta = Ext.getCmp('cuenta').getValue();
//			if(cuenta != null && cuenta != '')
//			{
//				me.showMessage('text', 'La Persona tiene asociada una Cuenta, solo puede registrar una Respuesta Positiva', false, null);
//				return;
//			}
			
			//Verifica si se asoció persona al oficio
			var cliente = Ext.getCmp('cliente').getValue();
			if(cliente != null && cliente != '')
			{
				me.showMessage('text', 'La Persona esta marcada como Cliente del Grupo. Verifique.', false, null);
				
			}			
			else{
				   var respuesta = Ext.create('gfi.bin.admctasweb.model.operativos.Respuesta', {
					   numOficio : 	Ext.getCmp('oficio').getValue(),
					   tipoOficio :	Ext.getCmp('tipoGroup').getChecked()[0].getGroupValue(),
					   numConsec :	consecutivo,
					   idContrato: 	0,//Se envia cero porque no se selecciona contrato como tal para respuesta negativa
					   tipoRespuesta : 1
				   });				   
				
				   options.model = respuesta;
				   me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeRegistrarRespNeg);			
			}
			
		},
		
		completeRegistrarRespNeg : function(completed, options)
		{
			var me = this;
			if(completed)
			{
				if (options.message) {
					me.showTextMessage(options.message, false);
				}
				//me.executeAction('buscarRespuestas');
				var info = options.info.raw;//Se accede a este objeto porque el retorno no coincide con el modelo de esta pantalla (PersonaBusqueda)				
//				var grid = Ext.getCmp('gridRespuestas');				
//				grid.setRespuestas(info['respuestaList']);				
				
				var buscadorEventos = Ext.getCmp('oficio');
				buscadorEventos.loadRecord(new Ext.util.Filter({property: 'numOficio', value: info['numOficio']}));
				
				
			}
			else
			{
				me.showErrorMessage(options.message);
			}
			//options.action.superclass.completeAction.call(me, completed, options);
		},
		
		doObtenerSimilaridad : function (options)
		{
			var me = this;
			var similaridad = Ext.create('gfi.bin.admctasweb.model.catalogos.Similaridad', {
				 similaridad : 0
			});				   
			
			options.model = similaridad;
			me.sendRequest(me.getUrl(me.status, options.action), options.model, options.action, me.completeObtenerSimilaridad);			
			
		},
		
		completeObtenerSimilaridad : function(completed, options)
		{
			var me = this;
			if(completed)
			{
				if (options.message) {
					me.showTextMessage(options.message, false);
				}
				var info = options.info.raw;//Se accede a este objeto porque el retorno no coincide con el modelo de esta pantalla (PersonaBusqueda)				
				Ext.getCmp('similaridad').setValue(info['similaridad']);								
			}
			else
			{
				me.showErrorMessage(options.error || options.message);
			}			
		},
		
		
		buildItems: function() {
			
	    	var me = this;
	    	
	    	//Ligamos el Grid de Personas a la Forma.
	    	me.bindGrid(Ext.create('gfi.bin.admctasweb.view.operativos.PersonaCorpGrid', {
	    		layout: 'fit',
	    		id: 'gridPersonas',
	    		formStatus: [gfi.component.BasicForm.STATUS_NEW,
	    		          	 gfi.component.BasicForm.STATUS_UPDATE]
			}));
			
			
			return [
			        //Iniciar aquí conjunto de dos controles
//			        {
//                		xtype: 'fieldcontainer',
//                		layout: 'hbox',
//                		width: 800,
//                		items: [
                	
			        {
	                	xtype: 'gfibuscadortrigger', 
	                	grupo: 'admctasweb', 
	                	clave: 'personas',
	                	fieldLabel : 'Consecutivo:',
	                	//name: 'cveEstatus',
	                	emptyText : 'SELECCIONE UNA PERSONA',
	                	id: 'numConsecPersona',
	                	linkProperty: 'numConsec',
	                	controls: [
	                		        {
	                		        	ref: 'nombre', 
				                		selector: '#txNombre',
				                		linkProperty: 'nombre'
				                	},
			                		{
			                			ref: 'paterno', 
			                			selector: '#txApPat',
			                			linkProperty: 'paterno'
			                		},
			                		{
			                			ref: 'materno', 
			                			selector: '#txApMat',
			                			linkProperty: 'materno'
			                		},
			                		{
			                			ref: 'rfc', 
			                			selector: '#txRFC',
			                			linkProperty: 'rfc'
			                		},
			                		{
			                			ref: 'tipoPersona', 
			                			selector: '#rdtipoPersona',
			                			linkProperty: 'tipoPersona'
			                		},{
			                			ref: 'idPersona', 
			                			selector: '#txCliente',
			                			linkProperty: 'idPersona'
			                		},{
			                			ref: 'cuenta', 
			                			selector: '#txCuenta',
			                			linkProperty: 'cuenta'
			                		}
			            ],
                		labelWidth: 100,
                		width : 417,
                		//Se sobreescribe evento de seleccionar persona del oficio para ejecutar búsqueda automática de persona corporativa
                	    selectRecord: function (model, format){
                	    	
                			var grid1 = Ext.getCmp('gridContrato');
                			grid1.getStore().removeAll();
                			grid1.getStore().sync();
                			
                			var grid2 = Ext.getCmp('gridContratoCambios');
                			grid2.getStore().removeAll();
                			grid2.getStore().sync();
                	    	
                	    	
                 			var meb = this, result;              		
                 			meb.setEventInfo();	                		
	                		result = meb.fireEvent('onSelectRecord', model, meb.eventInfo);
	                		
	                		if (result) {
	                			meb.selectedRecord = model;
	                			meb.displayRecord(meb.getResultText(model, format), model, meb.eventInfo);
	                			meb.displayControls(model, meb.eventInfo);
	                		}
	                		
	                		var cuenta = model.get('cuenta');
	                		if(cuenta == '' || cuenta == null)
	                		{	                			
	                			Ext.getCmp('cuentaContainer').hide();
	                		}
	                		else
	                		{
	                			Ext.getCmp('cuentaContainer').show();
	                		}
	                		
                			if(Ext.getCmp('sitOficio').getValue()=='PE')
                				me.executeAction('buscarPersonas');
                			else
                			{
                    			me.grid.getStore().removeAll();
                    			me.grid.getStore().sync();	                    			
                			}
	                		
	                		var idPersona = model.get('idPersona');
	                		if(idPersona == '' || idPersona == null)
	                		{
	            		        if (me.actions.asociarPersonaOficio && me.actions.asociarPersonaOficio.button) {	            		            
	            		            me.actions.asociarPersonaOficio.button.setDisabled(false);
	            		        }
	            		        
	            		        if (me.actions.cambiarCliente && me.actions.cambiarCliente.button) {	            		            
	            		            me.actions.cambiarCliente.button.setDisabled(true);
	            		        }
	                			
	                		}
	                		else
	                		{
	            		        if (me.actions.asociarPersonaOficio && me.actions.asociarPersonaOficio.button) {	            		            
	            		            me.actions.asociarPersonaOficio.button.setDisabled(true);
	            		        }
	            		        
	            		        if (me.actions.cambiarCliente && me.actions.cambiarCliente.button) {	            		            
	            		            me.actions.cambiarCliente.button.setDisabled(false);
	            		        }
	                			
	                		}
	                		
	                		
	                		return result;
                	    },
                	    //Se sobreescribe evento del buscador para setear como filtro al oficio
	                    onTriggerClick: function() {
	                    	var me = this;
	                    	
	                    	var oficio = Ext.getCmp('oficio').getValue();
	                    	if(oficio == null || oficio == ''){
	                    		Ext.Msg.alert('AdmCtasWeb', 'Seleccione un Oficio');
	                    	}
	                    	else{
		                    	var filtro = new Ext.util.Filter({
		                            property: 'numOficio',
		                            operator: '=',
		                            value: oficio
		                        });
		                    	me.setDefaultFilters(filtro);
		                    	me.search();
	                    	}
	                    },
//                	},
//                	
//	                	{
//	        	            xtype: 'tbspacer',
//	        	            width: 30
//	        	        },
//                	
//	                	{
//	            		    xtype: 'radiogroup',
//	            		    id: "opcionesGroup",
//	            		    items: [
//	            		        { boxLabel: 'Personas',  itemId : 'rdObjetivoBusqueda', name: 'objetivoBusqueda', inputValue: ''},
//	            		        { boxLabel: 'Contratos',  name: 'objetivoBusqueda', inputValue: 'PM'},
//	            		        { boxLabel: 'Ambos',  name: 'objetivoBusqueda', inputValue: 'PM'}
//	            		    ],
//	            		    width: 335,
//	            		    formStatus : [gfi.component.BasicForm.SEARCH]
//	    		        },
//                	
//                	
//     		       		]
			        },
                	//Terminar aquí conjunto de dos controles
                	
                	
                	
                	{
                		xtype : 'textfield',
                		fieldLabel : 'Nombre:',
                		name: 'nombre',
                		itemId : 'txNombre',
                		id:'nombre',
                		labelWidth: 100,
                		width : 417,
                		formStatus : [gfi.component.BasicForm.SEARCH]
                		
                	}, 
                	{
                		xtype : 'textfield',
                		fieldLabel : 'Apellido Paterno:',
                		name: 'paterno',
                		itemId : 'txApPat',
                		labelWidth: 100,
                		width : 417,
                		formStatus : [gfi.component.BasicForm.SEARCH]
                		
                	}, 
                	{
                		xtype : 'textfield',
                		fieldLabel : 'Apellido Materno:',
                		name: 'materno',
                		itemId : 'txApMat',
                		labelWidth: 100,
                		width : 417,
                		formStatus : [gfi.component.BasicForm.SEARCH]
                		
                	}, 
                	
                	{
                        xtype: 'fieldcontainer',
                        layout: 'hbox',
                        items: [                               
                        {
                    		xtype : 'textfield',
                    		fieldLabel : 'RFC:',
                    		name: 'rfc',
                    		itemId : 'txRFC',
                    		labelWidth: 100,
                    		width : 235,
                    		formStatus : [gfi.component.BasicForm.SEARCH]
                		}, {
                            xtype: 'splitter'
                        }, {
                    		xtype : 'textfield',
                    		fieldLabel : 'Cliente:',
                    		id: 'cliente',
                    		//name : 'idPersona',
                    		itemId : 'txCliente',
                    		labelWidth: 40,
                    		width : 177,
                    		formStatus : [gfi.component.BasicForm.SEARCH]
                		}
                        ]
                    },
                    
                    //Solo se debe mostrar si trae una cuenta
                	{
                        xtype: 'fieldcontainer',
                        id : 'cuentaContainer',
                        hidden : true,
                        layout: 'hbox',
                        items: [
                        {
                        	xtype : 'textfield',
                    		fieldLabel : 'Cuenta:',
                    		//name: 'cuenta',
                    		id : 'cuenta',
                    		itemId : 'txCuenta',
                    		labelWidth: 100,
                    		width : 417,
                    		fieldStyle: 'color: red; border-color: red;',
                    		labelStyle : 'color: red;',                  		
                    		formStatus : [gfi.component.BasicForm.SEARCH]
                		},
                		
//                		{
//                            xtype: 'splitter'
//                        }, {
//                    		xtype : 'button',
//                    		text : 'Registrar Respuesta Positiva',
//                    		width : 178,
//                    	    handler: function() { me.executeAction('registrarRespPositiva');}
//                		}
                        ]
                    },	
                        
                	{
                		xtype: 'fieldset',
                		title: 'Tipo de Persona',
                		collapsible: false,
                		width: 418,
                		items: [{
                		    xtype: 'radiogroup',
                		    id: "tipoPersonaGroup",
                		    items: [
                		        { boxLabel: 'Persona Física',  itemId : 'rdtipoPersona', name: 'tipoPersona', inputValue: 'PF'},
                		        { boxLabel: 'Persona Moral',  name: 'tipoPersona', inputValue: 'PM'}
                		    ],
                		    width: 395,
                		    formStatus : [gfi.component.BasicForm.SEARCH]
                	    }]
                	}, 
                    
                	{
                        xtype: 'fieldcontainer',
                        layout: 'hbox',
                        items: [
                                
                        {
                        	xtype : 'numberfield',
                    		fieldLabel : '% Coincidencia:',
                    		id : 'similaridad',
                    		name: 'similaridad',
                    		labelWidth: 98,
                            //value: 90,
                            maxValue: 100,
                            minValue: 1
                		}, {
                            xtype: 'splitter'
                        }, {
                    		xtype : 'button',
                    		text : 'Buscar Persona',
                    		arrowAlign: 'bottom',
                    		width : 178,
                    	    handler: function() { me.executeAction('buscarPersonasCustom');}
                		}
                        ]
                    },	
                    me.grid,
                      
        ];
       
    },	
    //Verifica si el oficio está en estatus pendiente
    oficioPendiente : function()
    {
    	return Ext.getCmp('sitOficio').getValue()=='PE';    	
    }
   			
});

