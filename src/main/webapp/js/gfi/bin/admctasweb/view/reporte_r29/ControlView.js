Ext.define('gfi.bin.admctasweb.view.reporte_r29.ControlView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.controlview',
	
//	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],
	
	requires : ['gfi.bin.admctasweb.model.r29.Control', 'gfi.corp.component.buscador.BuscadorTrigger'] ,
	
	// Configuración del Componente.
	config : {
		header: 'Captura Control de Periodo ',
		formType: gfi.corp.component.form.Types.CATALOGO,  
		grupo: 'admctasweb', 
		clave: 'periodos',
		modelClass : 'gfi.bin.admctasweb.model.r29.Control',
		primaryKeys : ['idEmpresa','cvePeriodo','numIntento'],
		baseUrl: 'reporte_r29/control/',
		secured: false
	},

	getWidth	: function() {
		return 850;
	},

	
	// Construimos los items de la Forma
	buildItems : function() {
		var me = this;
		var capturas;
		var filtro;
				
		return [
		        {
	        xtype: 'container',
	        layout: 'vbox',
   
	        items : [
	                 { 
	        		xtype: 'container',
	        		layout: 'hbox',
	        		items: [
	        		        { 
	        		        	xtype: 'gfibuscadortrigger',
	        		        	fieldLabel : 'Empresa:',
	        		        	name: 'idEmpresaCx',
	        		        	itemId : 'idEmpresaCx',
	        		        	grupo: 'admctasweb', 
	        		        	clave: 'mapeo',
	        		        	editable : false,
	        		        	linkProperty: 'cveCorporativa',
	        		        	controls: [{ 
	        		        		ref: 'txInstitucionCx', 
	        		        		selector: '#txInstitucionCx',
	        		        		linkProperty: 'descripcion'
	        		        	},{ 
	        		        		ref: 'cveSitiCx', 
	        		        		selector: '#cveSitiCx',
	        		        		linkProperty: 'cveSiti'
	        		        	}], 
	        		        	labelWidth: 140,
	        		        	width : 250,
	        		        	maxLength 	: 6,
	        		        	maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
  	        	 			   	defaultFilters: [ 
	        		        	                  new Ext.util.Filter({
	        		        	                	  property: 'idCatalogo',
	        		        	                	  operator: '=',
	        		        	                	  value: '2'
	        		        	                  }),
	        		        	                ], 
	        		           formStatus : [gfi.component.BasicForm.STATUS_NEW],
//	        		                         ,
//	        		                         gfi.component.BasicForm.STATUS_UPDATE],
	        		        },{
	        		        	xtype: 'splitter'
	        		        }, {
	        		        	xtype : 'textfield',
	        		        	itemId : 'txInstitucionCx',
	        		        	name : 'txInstitucionCx',        		           	
	        		        	width : 405,
	        		        	readOnly: true,
	        		        	editable: false
	        		        },{
	    	        			xtype : 'textfield',
	        		        	itemId : 'cveSitiCx',
	        		        	name : 'idSiti',    		   
	        		        	hidden : true,
	        		        	disabled : true,
	        		        	width : 0
//	        		        	formStatus : [gfi.component.BasicForm.STATUS_NEW,
//	        		        	              gfi.component.BasicForm.STATUS_UPDATE],
	        		        }] 
	        		}
	                 ,{
	        			xtype: 'tbspacer',
	        			width : 45,
	        		},{
			  			xtype: 'splitter'
			  		},{ 
	        			xtype: 'fieldcontainer',
	        			layout: 'hbox',
	        			items: [{
	        						xtype: 'gfibuscadortrigger',
	        						fieldLabel : 'Periodo:',
	        						name: 'cvePeriodoCx',
	        						itemId : 'busCvePeriodoCx',
	        						grupo: 'admctasweb', 
	        						clave: 'peri',
	        						editable : false,
	        						linkProperty: 'cvePeriodo',
	        						controls: [{
	        							ref: 'descPeriodoCx', 
	        							selector: '#descPeriodoCx',
	        							linkProperty: 'descPeriodo'
	        						}
//	        						,{
// 										ref: 'txnumIntentoCx',
// 										selector: '#txnumIntentoCx',
// 										linkProperty: 'numIntento'
// 									},{
//										ref: 'txsituacionCx',
//										selector: '#txsituacionCx',
//				 						linkProperty: 'situacion'
//	        						}
 									],
	        						onTriggerClick: function() {
	        							capturas = me.down('#idEmpresaCx').value;
	        							if(capturas == null || capturas == ''){
	        								Ext.Msg.alert('AdmCtasWeb', 'Seleccione una Institución');
	        							}
	        							else{
	        								filtro = new Ext.util.Filter({
	        									property: 'idEmpresa',
	        									operator: '=',
	        									value:  capturas
	        								});
	        								this.setDefaultFilters(filtro);
	        								this.search();
	        							}
	        						},         
	        						labelWidth: 140,
	        						width : 250,
	        						formStatus : [gfi.component.BasicForm.STATUS_NEW],
//	        						formStatus : [gfi.component.BasicForm.STATUS_NEW,
//	        						              gfi.component.BasicForm.STATUS_UPDATE]
	        					},{
	        						xtype: 'splitter'
	        					},{
	        						xtype : 'textfield',
	        						itemId : 'descPeriodoCx',
	        						name: 'descPeriodoCx',
	        						readOnly : true,
	        						width : 405,
	        						readOnly: true,
	        						editable: false
	        					}
	        					]}
	        			  ]
	        	},{
      			  xtype: 'container',
    			  layout: 'hbox',
    			  items: [{
    				  		xtype: 'textfield',
    				  		itemId: 'txnumIntentoCx',
    				  		name: 'numIntentoCx',
    				  		fieldLabel: 'Intento:',
    				  		labelWidth: 140,
    				  		readOnly: true,
    				  		editable: false,
    				  		width : 250,
    				  		value: 1,
//    				  		formStatus : [gfi.component.BasicForm.STATUS_NEW],
//    				  		formStatus : [gfi.component.BasicForm.STATUS_NEW,
//			   					          gfi.component.BasicForm.STATUS_UPDATE]
//    				  		,
//    				  		msgTarget: 'side'		    																	    
    			  		},{
    			  			xtype: 'splitter'
    			  		},{
    			  			xtype: 'textfield',
    			  			itemId: 'txsituacionCx',
    			  			name: 'situacionCx',
    			  			fieldLabel: 'Situación:',
    			  			labelWidth: 70,
    			  			width : 225,
    			  			maxLength 	: 2,
    			  			readOnly: true,
    			  			editable: false,
    			  			value : 'NP',
    			  			listeners:{
    			                change: function(field, newValue, oldValue){
    			                    field.setValue(newValue.toUpperCase());
    			                }
    			             }
//    			  		,
//    			  			formStatus : [gfi.component.BasicForm.STATUS_NEW,
//			   					          gfi.component.BasicForm.STATUS_UPDATE]
//    			  			,
//    			  			msgTarget: 'side' 
    			  		}],
    		  }
		
		];
		
	},
	
	// Se sobreescriben las Acciones de la Forma.
	
	// Completar Selección.
	completeSeleccionar: function (completed, options) {
		var me = this;
//		var buscadorEmpresa = me.down('#idEmpresaCaptura'); 
//		var buscadorPeriodo = me.down('#busCvePeriodo'); 
//		
//		buscadorEmpresa.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('institucion')}) );
//		buscadorPeriodo.loadRecord( new Ext.util.Filter([{property: 'cvePeriodo',value: options.model.get('cvePeriodo')},
//		                                                 {property: 'idEmpresa',value: options.model.get('cvePeriodo')}]) );

		me.actions.seleccionar.completeAction.call(me, completed, options);
    },

	
});

