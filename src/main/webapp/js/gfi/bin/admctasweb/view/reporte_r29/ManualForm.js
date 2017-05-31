Ext.define('gfi.bin.admctasweb.view.reporte_r29.ManualForm', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.manualform',
	
//	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],
	
	requires : ['gfi.bin.admctasweb.model.r29.Manual', 'gfi.corp.component.buscador.BuscadorTrigger'] ,
	
	// Configuración del Componente.
	config : {
		header: 'Captura Manual ',
		formType: gfi.corp.component.form.Types.CATALOGO,  
		grupo: 'admctasweb', 
		clave: 'manual',
		modelClass : 'gfi.bin.admctasweb.model.r29.Manual',
		primaryKeys : ['idEmpresa','cvePeriodo','numIntento', 'numOficio','tipoOficio',  'idCotitular','idCuenta'],
		baseUrl: 'reporte_r29/manual/',
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
	        		        	fieldLabel : 'Institución:',
	        		        	name: 'institucion',
	        		        	itemId : 'idEmpresaCaptura',
	        		        	grupo: 'admctasweb', 
	        		        	clave: 'mapeo',
	        		        	editable : false,
	        		        	readOnly: true,
	        		        	linkProperty: 'cveSiti',
	        		        	controls: [{ 
	        		        		ref: 'txInstitucion', 
	        		        		selector: '#txInstitucion',
	        		        		linkProperty: 'descripcion'
	        		        	},{ 
	        		        		ref: 'idEmpresa', 
	        		        		selector: '#idEmpresa',
	        		        		linkProperty: 'cveCorporativa' 
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
	        		           formStatus : [gfi.component.BasicForm.STATUS_NEW
//	        		                         ,
//	        		                         gfi.component.BasicForm.STATUS_UPDATE
	        		                         ],
	        		        },{
	        		        	xtype: 'splitter'
	        		        }, {
	        		        	xtype : 'textfield',
	        		        	itemId : 'txInstitucion',
	        		        	name : 'txInstitucion',        		           	
	        		        	width : 305,
	        		        	editable : false,
	        		        	readOnly: true,
	        		        	listeners:{
	        		                change: function(field, newValue, oldValue){
	        		                    field.setValue(newValue.toUpperCase());
	        		                }
	        		             }
//	        		        ,
//	        		        	formStatus : [gfi.component.BasicForm.STATUS_NEW,
//	        		        	              gfi.component.BasicForm.STATUS_UPDATE],
	        		        },{
	        		        	xtype: 'splitter'
	        		        },{
	    	        			xtype : 'textfield',
	    	        			id: 'idEmpresa',
	        		        	itemId : 'idEmpresa',
	        		        	name : 'idEmpresa',    		   
	        		        	hidden : false,
	        		        	editable : false,
	        		        	//readOnly: true,
	        		        	//disabled : true,
	        		        	width : 96,
	        		        	listeners:{
        			                change: function(field, newValue, oldValue){
        			                	
        			                	var comboPeriodoExtraccionManual = Ext.getCmp('comboPeriodoExtraccionManual');
        			                	var storeComboPeriodoExtraccionManual = comboPeriodoExtraccionManual.getStore();
        			                	storeComboPeriodoExtraccionManual.reload();
        			                    
        			                }
        			             }
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
	        			items: [
//	        			        {
//	        						xtype: 'gfibuscadortrigger',
//	        						fieldLabel : 'Periodo:',
//	        						name: 'cvePeriodo',
//	        						itemId : 'busCvePeriodo',
//	        						grupo: 'admctasweb', 
//	        						clave: 'periodos',
//	        						editable : false,
//	        						linkProperty: 'cvePeriodo',
//	        						controls: [{
//	        							ref: 'descPeriodo', 
//	        							selector: '#descPeriodo',
//	        							linkProperty: 'descPeriodo'
//	        						},{
// 										ref: 'txnumIntento',
// 										selector: '#txnumIntento',
// 										linkProperty: 'numIntento'
// 									},{
//										ref: 'txsituacion',
//										selector: '#txsituacion',
//				 						linkProperty: 'situacion'
//	        						}],
//	        						onTriggerClick: function() {
//	        							capturas = me.down('#idEmpresa').value;
//	        							if(capturas == null || capturas == ''){
//	        								Ext.Msg.alert('AdmCtasWeb', 'Seleccione una Institución');
//	        							}
//	        							else{
//	        								filtro = new Ext.util.Filter({
//	        									property: 'idEmpresa',
//	        									operator: '=',
//	        									value:  capturas
//	        								});
//	        								this.setDefaultFilters(filtro);
//	        								this.search();
//	        							}
//	        						},         
//	        						labelWidth: 140,
//	        						width : 250,
//	        						formStatus : [gfi.component.BasicForm.STATUS_NEW
////	        						              ,
////	        						              gfi.component.BasicForm.STATUS_UPDATE
//	        						              ]
//	        					},
								{
									xtype : 'combo',
									id: 'comboPeriodoExtraccionManual',
									fieldLabel : 'Periodo',
									width : 320,
									emptyText : 'Selecciona Periodo...',
									//allowBlank : false,
									//blankText : 'El campo Periodo es requerido.',
									queryMode : 'local',
									labelWidth : 138,
									//name: 'cvePeriodo',
									//id : 'dfPeriodoGeneraIntento',
									store : 'gfi.bin.admctasweb.store.reportes.R29.StorePeriodos', 
									displayField : 'cvePeriodo',
									valueField : 'cvePeriodo',
									autoSelect : true,
									forceSelection : true,
									editable : false,
									listeners:{
	        			                change: function(field, newValue, oldValue){
	        			                    Ext.getCmp('cvePeriodo').setValue(newValue);
	        			                	//field.setValue(newValue.toUpperCase());
	        			                }
	        			             }
								},{
	        						xtype: 'splitter'
	        					},{
	        						xtype : 'textfield',
	        						id : 'cvePeriodo',
	        						itemId : 'cvePeriodo',
	        						name: 'cvePeriodo',
	        						editable : false,
		        		        	readOnly: true,
	        						listeners:{
	        			                change: function(field, newValue, oldValue){
	        			                    field.setValue(newValue.toUpperCase());
	        			                }
	        			             },
	        						width : 338
//	        						,
//        							formStatus : [gfi.component.BasicForm.STATUS_NEW,
//    				   					          gfi.component.BasicForm.STATUS_UPDATE]
	        					}
	        					]
	        		  },
	        		  {
	        			  xtype: 'container',
	        			  layout: 'hbox',
	        			  items: [{
	        				  		xtype: 'textfield',
	        				  		itemId: 'txnumIntento',
	        				  		name: 'numIntento',
	        				  		fieldLabel: 'Consecutivo:',
	        				  		emptyText : 'Valor calculado por el sistema',
	        				  		labelWidth: 140,
	        				  		editable : true,
		        		        	readOnly: false,
	        				  		width : 320
//	        				  		,
//	        				  		msgTarget: 'side'		    																	    
	        			  		},{
	        			  			xtype: 'splitter'
	        			  		},{
	        			  			xtype: 'textfield',
	        			  			itemId: 'txsituacion',
	        			  			id: 'situacionManualFormValida',
	        			  			name: 'situacion',
	        			  			fieldLabel: 'Situación:',
	        			  			emptyText : 'Valor asignado por el sistema',
	        			  			listeners:{
	        			                change: function(field, newValue, oldValue){
	        			                    field.setValue(newValue.toUpperCase());
	        			                }
	        			             },
	        			  			labelWidth: 70,
	        			  			width : 300,
	        			  			editable : true,
		        		        	readOnly: false
//	        			  			,
//	        			  			msgTarget: 'side' 
	        			  		}],
	        		  },{
				  			xtype: 'splitter'
				      },{
				  			xtype: 'container',
				  		    layout: 'hbox',
		        			items: [{
		        				xtype: 'gfibuscadortrigger',
		        				itemId: 'idCuenta',
  	        	        		name: 'idCuenta',
  	        	        		grupo: 'admctasweb', 
        						clave: 'BUSCUENTAX',
        						linkProperty: 'idContrato', 
  	        	        		fieldLabel: 'Número de Cuenta:',
  	        	        		editable : false,
        						labelWidth: 140,
  	        	        		width : 660, 
  	        	        		maxLength 	: 30,
	     			  			onTriggerClick: function() {
	        							capturas = me.down('#idEmpresa').value;
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
  	        	        		maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
  	        	        		msgTarget: 'side',
  	        	        		formStatus : [gfi.component.BasicForm.STATUS_NEW
//  	        	        		              ,
//  	        		                         gfi.component.BasicForm.STATUS_UPDATE
  	        		                         ],
  	        		                         
  	        		            onChange: function( textfield, newValue, oldValue, eOpts) {

  	       	        		            var cuenta = me.down('#idCuenta').value;
  			        					var empresa = me.down('#idEmpresa').value;
  			        					Ext.Ajax.request({
  			        						
  			        					params : {idCuenta : cuenta,
  			        						      idEmpresa: empresa},
  			        					url: 'reporte_r29/manual/buscarcontrato.htm',
  			        		           	success: function(response){
  			        				    var jsonData  = Ext.decode(response.responseText);
  			        				    
  			        				    var est = me.down('#txestado');
  			        				    console.log(est);
  			        				  
  			        				    me.down("#idCuenta").setValue(jsonData.info.idCuenta);	
  			        				    me.down("#txestado").setValue(jsonData.info.estado);
  			        				    me.down("#localidad").setValue(jsonData.info.localidad);
  			        				    me.down("#codigoPostal").setValue(jsonData.info.codigoPostal);
  			        				    me.down("#modalidad").setValue(jsonData.info.modalidad);
  			        				    me.down("#tipoNivel").setValue(jsonData.info.tipoNivel);
  			        				    me.down("#Producto").setValue(jsonData.info.producto);
  			        				    if(jsonData.info.persoTitular!=0){
  			        				    	me.down("#personaTit").setValue(jsonData.info.persoTitular);
  			        					    me.down("#rfcTit").setValue(jsonData.info.rfcTitular);
  	  			        				    me.down("#razonTit").setValue(jsonData.info.razonTitular);
  	  			        				    me.down("#nombreTit").setValue(jsonData.info.nombreTitular);
  	  			        				    me.down("#paternoTit").setValue(jsonData.info.paternoTitular);
  	  			        				    me.down("#maternoTit").setValue(jsonData.info.maternoTitular);
  	  			        
  			        				    }else {
  			        				    	me.down("#rfcTit").setValue('');
  			        				    	me.down("#razonTit").setValue('');
  			        				    	me.down("#nombreTit").setValue('');
  			        				    	me.down("#paternoTit").setValue('');
  			        				    	me.down("#maternoTit").setValue('');
  			        				    }
  			        				    if (jsonData.info.idCotitular!=0){
  			        				    	me.down("#idCoti").setValue(jsonData.info.idCotitular);
  			        				    	me.down("#rfcCot").setValue(jsonData.info.rfcCoti);
  	  			        				    me.down("#razonCot").setValue(jsonData.info.razonCoti);
  	  			        				    me.down("#nombreCot").setValue(jsonData.info.nombreCoti);
  	  			        				    me.down("#paternoCot").setValue(jsonData.info.paternoCoti);
  	  			        				    me.down("#maternoCot").setValue(jsonData.info.maternoCoti);
  	  			        				    me.down("#monedaAsegurada").setValue(jsonData.info.monedaCuenta);
  	  			        				    me.down("#sucursal").setValue(jsonData.info.idSucursal);      				    
  	  			        			    }else{
  	  			        		    	    me.down("#idCoti").setValue('');
  	  			        				 	me.down("#rfcCot").setValue('');
  	  			        			    	me.down("#razonCot").setValue('');
  	  			        			    	me.down("#nombreCot").setValue('');
  	  			        			    	me.down("#paternoCot").setValue('');
  	  			        			    	me.down("#maternoCot").setValue('');
  	  			        			    	me.down("#monedaAsegurada").setValue('');
  	  			        			    	me.down("#sucursal").setValue('');      				    
  			        				    } 
  			        				    if (jsonData.info.persoCoti!=0){
  			        				    	me.down("#personaCot").setValue(jsonData.info.persoCoti);
  			        				    }
  			        				    me.down("#rfcCot").setValue(jsonData.info.rfcCoti);
  			        				    me.down("#razonCot").setValue(jsonData.info.razonCoti);
  			        				    me.down("#nombreCot").setValue(jsonData.info.nombreCoti);
  			        				    me.down("#paternoCot").setValue(jsonData.info.paternoCoti);
  			        				    me.down("#maternoCot").setValue(jsonData.info.maternoCoti);
  			        				    me.down("#monedaAsegurada").setValue(jsonData.info.monedaCuenta);
  			        				    me.down("#sucursal").setValue(jsonData.info.idSucursal);      				    
  			        				    // campos de descripcion o nombre (String)
  			        				    me.down("#txSucursal").setValue(jsonData.info.txSucursal);
  			        				    me.down("#txmodalidad").setValue(jsonData.info.txModalidad);
  			        				    me.down("#txtipoNivelManualForm").setValue(jsonData.info.txTipoNivel);
  			        				    me.down("#txproducto").setValue(jsonData.info.txProducto);	
  			        				    me.down("#txMonedaAse").setValue(jsonData.info.txMonedaAsegurada);	
  			        				    me.down("#txPersonaTit").setValue(jsonData.info.txPersonaTitular);
  			        				    me.down("#txPersonaCot").setValue(jsonData.info.txPersonaCoti);	
  			        				   
  			        				   }
  			        				});
  	       	        		           
  	             		        }                    
		        				     
		        			}],
		        				
		        		},{
	        				xtype: 'splitter'
	        			},{
	        				xtype: 'splitter'
	        			},{
	        				xtype: 'splitter'
	        			},
		        
	        		  Ext.create('Ext.tab.Panel',
	        			  {	    		
	        			  	itemId: 'manualTab',
	        			  	frame : false,
	        			  	width: 750,
	        			  	height: 280,
	        			  	activeTab: 0,
	        			  	autoScroll : true,
	        			  	defaults:{
	        				  bodyStyle:'padding:10px'
	        			  	},
            
	        			  	items: [
	        			  	        {
	        			  	        	title: 'Cuenta',
	        			  	        	itemId: 'contratos',
	        			  	        	layout : 'vbox',
	        			  	        	items : [
	        			  	        	 {
    			  	        	        	 xtype : 'splitter'
    			  	        	         },{
    			  	        	        	 xtype: 'fieldcontainer',
    			  	        	        	 layout: 'hbox',
    			  	        	        	 items: [{
    			  	        	        		 xtype: 'gfibuscadortrigger', 
    			  	        	        		 fieldLabel : 'Sucursal',
    			  	        	        		 name : 'idSucursal',
    			  	        	        		 itemId : 'sucursal',
    			  	        	        		// linkProperty : 'nomSucursal',
    			  	        	        		 grupo: 'admctasweb', 
    			  	        	        		 clave: 'sucursal',
    			  	        	        		 editable : false,
    			  	        	        		 labelWidth : 140,													
    			  	        	        		 width : 250,
    			  	        	        		listeners:{
    			  	      		                change: function(field, newValue, oldValue){
    			  	      		                    field.setValue(newValue.toUpperCase());
    			  	      		                }
    			  	      		             },
    			  	        	        		 maxLength 	: 30,
    			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
			            		                 formStatus : [gfi.component.BasicForm.STATUS_NEW,
			            		                               gfi.component.BasicForm.STATUS_UPDATE],
			            		                 linkProperty: 'idSucursal',
			            		                 controls: [{
			            		                	 ref: 'txSucursal', 
			            		                	 selector: '#txSucursal',
			            		                	 linkProperty: 'nomSucursal'
			            		                 },{
			            		                	 ref: 'txestado', 
			            		                	 selector: '#txestado',
			            		                	 linkProperty: 'estado'
			            		                 },{
			            		                	 ref: 'localidad', 
			            		                	 selector: '#localidad',
			            		   	            	 linkProperty: 'localidad'
			            		                 },{
			            		                	 ref: 'codigoPostal', 
			            		                	 selector: '#codigoPostal',
			            		                	 linkProperty: 'codigoPostal'
			            		                 }]
    			  	        	        	 },
    			  	        	        	 {
    			  	        	        		 xtype: 'splitter'
    			  	        	        	 },{
    			  	        	        		 xtype : 'textfield',
    			  	        	        		 itemId : 'txSucursal',
    			  	        	        		 name : 'txSucursal',
    			  	        	        		editable : false,
    				        		        	readOnly: true,
    			  	        	        		listeners:{
    			  	      		                change: function(field, newValue, oldValue){
    			  	      		                    field.setValue(newValue.toUpperCase());
    			  	      		                }
    			  	      		             },
    			  	        	        		 width : 405,    			
//													 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//																   gfi.component.BasicForm.STATUS_UPDATE]
    			  	        	        	 }],		
    			  	        	         }, {
    			  	        	        	 xtype: 'fieldcontainer',
    			  	        	        	 layout: 'hbox',
    			  	        	        	 	items: [{
    			  	        	        	 		xtype: 'textfield',  
    			  	        	        	 		itemId: 'txestado',
    			  	        	        	 		name: 'estado',
    			  	        	        	 		fieldLabel: 'Estado:',
    			  	        	        	 		labelWidth: 140,
    			  	        	        	 		editable : false,
    			  		        		        	readOnly: true,
    			  	        	        	 		width : 250,
    			  	        	        	 		msgTarget: 'side'		    																	    
    			  	        	        	 	},{
    			  	        	        	 		xtype: 'splitter'
    			  	        	        	 	},
    			  	        	        	 	{
    			  	        	        	 		xtype: 'textfield',
    			  	        	        	 		itemId: 'localidad',
    			  	        	        	 		name: 'localidad',
    			  	        	        	 		fieldLabel: 'Localidad:',
    			  	        	        	 		labelWidth: 70,
    			  	        	        	 		width : 225,
    			  	        	        	 		editable : false,
    			  		        		        	readOnly: true,
    			  	        	        	 		msgTarget: 'side'		    																	    
    			  	        	        	 	},{
    			  	        	        	 		xtype: 'splitter'
    			  	        	        	 	},
    			  	        	        	 	{
    			  	        	        	 		xtype: 'textfield',
    			  	        	        	 		itemId: 'codigoPostal',
    			  	        	        	 		name: 'codigoPostal',
    			  	        	        	 		fieldLabel: 'CP:',
    			  	        	        	 		labelWidth: 40,
    			  	        	        	 		editable : false,
    			  		        		        	readOnly: true,
    			  	        	        	 		width : 175,
//														labelWidth: 140,
//														width : 140,
    			  	        	        	 		readOnly: true,
    			  	        	        	 		msgTarget: 'side'	
    			  	        	        	 	}],
    			  	        	         }	
    			  	        	         ,{
    			  	        	        	 xtype: 'fieldcontainer',
    			  	        	        	 layout: 'hbox',
    			  	        	        	 items: [{
    			  	        	        		 xtype: 'gfibuscadortrigger', 
    			  	        	        		 fieldLabel : 'Modalidad',
    			  	        	        		 name : 'modalidad',
    			  	        	        		 itemId : 'modalidad',
    			  	        	        		// linkProperty : 'descripcion',
    			  	        	        		 grupo: 'admctasweb', 
    			  	        	        		 clave: 'mapeo',
    			  	        	        		 editable : false,
    			  	        	        		 labelWidth : 140,													
    			  	        	        		 width : 250,
    			  	        	        		 maxLength 	: 2,
    			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caractereso ó digitos revasado.',
    			  	        	        		 defaultFilters: [
    			  	        	        		                  new Ext.util.Filter({
    			  	        	        		                	  property: 'idCatalogo',
    			  	        	        		                	  operator: '=',
    			  	        	        		                	  value: '4'
    			  	        	        		                  })
    			  	        	        		                  ],        		
    			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
    			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE],
    			  	        	        		 linkProperty: 'cveSiti',
    			  	        	        		 controls: [{
    			  	        	        			 ref: 'txModalidad', 
    			  	        	        			 selector: '#txmodalidad',
    			  	        	        			 linkProperty: 'descripcion'
    			  	        	        		 }]
    			  	        	        	 },
    			  	        	        	 {
    			  	        	        		 xtype: 'splitter'
    			  	        	        	 },{
    			  	        	        		 xtype : 'textfield',
    			  	        	        		 itemId : 'txmodalidad',
    			  	        	        		 name : 'txmodalidad',
    			  	        	        		 width : 405, 
    			  	        	        		editable : false,
    				        		        	readOnly: true,
//													 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//															   gfi.component.BasicForm.STATUS_UPDATE]
    			  	        	        	 }],		
    			  	        	         },
    			  	        	         {
    			  	        	        	 xtype: 'fieldcontainer',
    			  	        	        	 layout: 'hbox',
    			  	        	        	 items: [{
    			  	        	        		 xtype: 'gfibuscadortrigger', 
    			  	        	        		 fieldLabel : 'Tipo Nivel',
    			  	        	        		 name : 'tipoNivel',
    			  	        	        		 itemId : 'tipoNivel',
    			  	        	        		// linkProperty : 'descripcion',
    			  	        	        		 grupo: 'admctasweb', 
    			  	        	        		 clave: 'mapeo',
    			  	        	        		 editable : false,
    			  	        	        		 labelWidth : 140,													
    			  	        	        		 width : 250,
    			  	        	        		 maxLength 	: 3,
    			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
    			  	        	        		 defaultFilters: [
    			  	        	        		                  new Ext.util.Filter({
    			  	        	        		                	  property: 'idCatalogo',
    			  	        	        		                	  operator: '=',
    			  	        	        		                	  value: '14'
    			  	        	        		                  })
			            		                 ],        		
			            		                 formStatus : [gfi.component.BasicForm.STATUS_NEW,
			            		                               gfi.component.BasicForm.STATUS_UPDATE],
			            		                 linkProperty: 'cveSiti',
			            		                 controls: [{
			            		                	 ref: 'txtipoNivel', 
			            		                	 selector: '#txtipoNivelManualForm',
			            		                	 linkProperty: 'descripcion'
			            		                 }]
    			  	        	        	 },
    			  	        	        	 {
    			  	        	        		 xtype: 'splitter'
    			  	        	        	 },{
    			  	        	        		 xtype : 'textfield',
    			  	        	        		 itemId : 'txtipoNivelManualForm',
    			  	        	        		 name : 'txtipoNivel',
    			  	        	        		 width : 405,    
    			  	        	        		editable : false,
    				        		        	readOnly: true,
//													 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//														 	   gfi.component.BasicForm.STATUS_UPDATE]
    			  	        	        	 }],		
    			  	        	         },
    			  	           	         {
    			  	        	        	 xtype: 'fieldcontainer',
    			  	        	        	 layout: 'hbox',
    			  	        	        	 items: [{
    			  	        	        		 xtype: 'gfibuscadortrigger', 
    			  	        	        		 fieldLabel : 'Producto',
    			  	        	        		 name : 'producto',
    			  	        	        		 itemId : 'Producto',
//    			  	        	        		 linkProperty : 'descripcion',
    			  	        	        		 grupo: 'admctasweb', 
    			  	        	        		 clave: 'mapeo',
    			  	        	        		listeners:{
    			  	      		                change: function(field, newValue, oldValue){
    			  	      		                    field.setValue(newValue.toUpperCase());
    			  	      		                }
    			  	      		             },
    			  	        	        		 labelWidth : 140,													
    			  	        	        		 width : 250,
    			  	        	        		 maxLength 	: 3,
    			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres o digitos revasado.',
    			  	        	        		 defaultFilters: [
    			  	        	        		                  new Ext.util.Filter({
    			  	        	        		                	  property: 'idCatalogo',
    			  	        	        		                	  operator: '=',
    			  	        	        		                	  value: '5'
    			  	        	        		 })
			            		             ],        		
			            		             formStatus : [gfi.component.BasicForm.STATUS_NEW,
			            		                           gfi.component.BasicForm.STATUS_UPDATE],
			            		             linkProperty: 'cveSiti',
			            		             controls: [{
			            		            	 ref: 'txproducto', 
			            		            	 selector: '#txproducto',
			            		            	 linkProperty: 'descripcion'
			            		             }]
    			  	        	        	 },
    			  	        	        	 {
    			  	        	        		 xtype: 'splitter'
    			  	        	        	 },{
    			  	        	        		 xtype : 'textfield',
    			  	        	        		 itemId : 'txproducto',
    			  	        	        		 name : 'txproducto',
    			  	        	        		editable : false,
    				        		        	readOnly: true,
    			  	        	        		 width : 405,    			
//													 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//															   gfi.component.BasicForm.STATUS_UPDATE]
    			  	        	        	 }],		
    			  	        	         },{
    			  	        	        	 xtype: 'fieldcontainer',
    			  	        	        	 layout: 'hbox',
    			  	        	        	 items: [{
    			  	        	        		 xtype: 'gfibuscadortrigger', 
    			  	        	        		 fieldLabel : 'Moneda',
    			  	        	        		 name : 'monedaCuenta',
    			  	        	        		 itemId : 'monedaAsegurada',
//    			  	        	        		 linkProperty : 'descripcion',
    			  	        	        		 grupo: 'admctasweb', 
    			  	        	        		 clave: 'mapeo',
    			  	        	        		 editable : false,
    			  	        	        		 labelWidth : 140,													
    			  	        	        		 width : 250,
    			  	        	        		 maxLength 	: 1,
    			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
    			  	        	        		 defaultFilters: [
    			  	        	        		                  new Ext.util.Filter({
    			  	        	        		                	  property: 'idCatalogo',
    			  	        	        		                	  operator: '=',
    			  	        	        		                	  value: '6'
    			  	        	        		                  })
    			  	        	        		 ],        		
			            		                 formStatus : [gfi.component.BasicForm.STATUS_NEW,
			            		                               gfi.component.BasicForm.STATUS_UPDATE],
			            		                 linkProperty: 'cveSiti',
			            		                 controls: [{
			            		                	 ref: 'txMonedaAse', 
			            		                	 selector: '#txMonedaAse',
			            		                	 linkProperty: 'descripcion'
			            		                 }]
    			  	        	        	 },
    			  	        	        	 {
    			  	        	        		 xtype: 'splitter'
    			  	        	        	 },{
    			  	        	        		 xtype : 'textfield',
    			  	        	        		 itemId : 'txMonedaAse',
    			  	        	        		 name : 'txMonedaAse',
    			  	        	        		editable : false,
    				        		        	readOnly: true,
    			  	        	        		 width : 405,    			
//													 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//																   gfi.component.BasicForm.STATUS_UPDATE]
    			  	        	        	 }],		
    			  	        	         },{
    			  	        	        	 xtype: 'textfield',
    			  	        	        	 itemId: 'montoAsegurado',
    			  	        	        	 name: 'montoInicial',
    			  	        	        	 fieldLabel: 'Monto Inicial:',
    			  	        	        	 labelWidth: 140,
    			  	        	        	 width : 660,
    			  	        	        	maskRe: /[0-9]/,
//    			  	        	        	regex: new RegExp('^(([0-9]){25})$'),
    			  	        	        	 maxLength 	: 25,
    			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de digitos revasado.',
    			  	        	        	 msgTarget: 'side'		    																	    
    			  	        	         }] 
	        			  	        },
		                            
	        			  	        {
	        			  	        	title: 'Requerimiento',
		            
	        			  	        	layout : 'vbox',
	        			  	        	items : [
	        			  	        	         {
	        			  	        	        	 xtype : 'splitter'
	        			  	        	         },{
	        			  	        	        	 xtype: 'fieldcontainer',
	        			  	        	        	 layout: 'hbox',
	        			  	        	        	 items : [{
	        			  	        	        		 xtype: 'textfield',	
	        			  	        	        		 itemId: 'Medio:',
	        			  	        	        		 name: 'medioSolicitado',
	        			  	        	        		 fieldLabel: 'Medio:',
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 width : 250,
	        			  	        	        		 readOnly: true,
	        			  	        	        		 // disabled: true,
	        			  	        	        		 value: 100,
	        			  	        	        		 msgTarget: 'side'		    																	    
	        			  	        	        	 },{
	        			  	        	        		 xtype : 'splitter'
	        			  	        	        	 },{
	        			  	        	        		 xtype: 'textfield',	
	        			  	        	        		 itemId: 'manual:',
	        			  	        	        		 name: 'manual',
	        			  	        	        		 // fieldLabel: 'Medio:',
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 width : 404,
	        			  	        	        		 // disabled: false,
	        			  	        	        		editable : false,
	        				        		        	readOnly: true,
	        			  	        	        		 value: 'Captura Manual',
	        			  	        	        		 msgTarget: 'side'		    																	    
	        			  	        	        	 }]},
	        			  	        	        	 {
	        			  	        	        		 xtype: 'fieldcontainer',
	        			  	        	        		 layout: 'hbox',
	        			  	        	        		 items: [
	        			  	        	        		         {
	        			  	        	       		        	 	xtype: 'gfibuscadortrigger', 
	        			  	        	       		        	 	itemId: 'autoridad',
	        			  	        	       		        	 	grupo: 'admctasweb', 
	        			  	        	       		        	 	clave: 'mapeo',
	        			  	        	       		        	 	fieldLabel : 'Autoridad:',
	        			  	        	       		        	 	name: 'autoridad',
	        			  	        	       		        	 	linkProperty: 'cveSiti',
	        			  	        	       		        	 	controls: [{
	        			  	        	       		        	 		ref: 'txAutoridad', 
	        			  	        	       		        	 		selector: '#txAutoridad',
	        			  	        	       		           	 		linkProperty: 'descripcion'
	        			  	        	       		        	 	}],
	        			  	        	       		        	 	defaultFilters: [
	       	        			  	        	        		                  new Ext.util.Filter({
	       	        			  	        	        		                	  property: 'idCatalogo',
	       	        			  	        	        		                	  operator: '=',
	       	        			  	        	        		                	  value: '10'
	       	        			  	        	        		                  })
	       	        			  	        	        		                  ],
	        			  	        	       		        	 	labelWidth: 140,
	        			  	        	       		        	 	width : 250,
	        			  	        	       		        	 	disabled: false,
	        			  	        	       		        	 	readOnly: false,
	        			  	        	       		        	 	maxLength 	: 15,
	        			  	        	       		        	    maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres o digitos revasado.',
	        			  	        	       		        	 	formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			  	        	       		        	 	              gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        		         },{
	        			  	        	        		        	 xtype: 'splitter'
	        			  	        	        		         },{
	        			  	        	        		        	 xtype : 'textfield',
	        			  	        	        		        	 itemId : 'txAutoridad',
	        			  	        	        		        	 name : 'descripcion',
	        			  	        	        		        	 disabled: false,
	        			  	        	        		        	 readOnly: true,
	        			  	      	        		        	readOnly: true,
	        			  	        	        		        	listeners:{
	        			  	        	      		                change: function(field, newValue, oldValue){
	        			  	        	      		                    field.setValue(newValue.toUpperCase());
	        			  	        	      		                }
	        			  	        	      		             },
	        			  	        	        		        	 width : 405,
//	        			  	        	        		        	 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//	        			  	        	        		        	               gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        		         }]
	        			  	        	        	 },{
	        			  	        	        		 xtype: 'textfield',
	        			  	        	        		 itemId: 'oficio',
	        			  	        	        		 name: 'numOficio',
	        			  	        	        		 fieldLabel: 'Número de Oficio:',
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW
//	        			  	        	        		               ,
//	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE
	        			  	        	        		               ],
	        			  	        	        		listeners:{
	        			  	      		                change: function(field, newValue, oldValue){
	        			  	      		                    field.setValue(newValue.toUpperCase());
	        			  	      		                }
	        			  	      		             },
	        			  	        	        		 width : 660,
	        			  	        	        		 disabled: false,
	        			  	        	        		 // pasar un dato de un campo a otro JAPL
 	        			  	        	        		 onChange: function( textfield, newValue, oldValue, eOpts) {
	 	 	        		        						me.down('#oficioReqOp').setValue(me.down('#oficio').value);
	 		        		         	        		 }
	 	        			  	        	        	 ,maxLength 	: 30,
	        			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        		 msgTarget: 'side'		    																	    
	        			  	        	        	 },
	        			  	        	        	 
//	        			  	        	        	 {
//	        			  	        	        		 xtype: 'textfield',
//	        			  	        	        		 itemId: 'tipoOficio',
//	        			  	        	        		 name: 'tipoOficio',
//	        			  	        	        		 fieldLabel: 'Tipo Oficio:',
//	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW
////	        			  	        	        		               ,
////	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE
//	        			  	        	        		               ],
//	        			  	        	        		listeners:{
//	        			  	      		                change: function(field, newValue, oldValue){
//	        			  	      		                    field.setValue(newValue.toUpperCase());
//	        			  	      		                }
//	        			  	      		             },
//	        			  	        	        		 labelWidth: 140,
//	        			  	        	           		 disabled: false,
//	        			  	        	        		 maxLength 	: 2,
//	        			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
//	        			  	        	        		 msgTarget: 'side'		    																	    	
//	        			  	        	        	 },
	        			  	        	        	 
	        			  	        	        	 {
	        			  	        	        		 xtype : 'datefield',
	        			  	        	        		 itemId: 'fechaSolicitud',
	        			  	        	        		 name: 'fechaSolicitud',
	        			  	        	        		 fieldLabel: 'Fecha Solicitud:',
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 disabled: false,
	        			  	        	        		 format : 'Ymd',
	        			  	        	        		 // pasar un dato de un campo a otro JAPL
 	        			  	        	        		 onChange: function( textfield, newValue, oldValue, eOpts) {
	 	 	        		        						me.down('#oficioReqOp').setValue(me.down('#oficio').value);
	 		        		         	        		 },
	        			  	        	        		 msgTarget: 'side'		,
	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]    																	    
	        			  	        	        	 },{
	        			  	        	        		 xtype: 'textfield',
	        			  	        	        		 itemId: 'siara',
	        			  	        	        		 name: 'folioSiara',
	        			  	        	        		 fieldLabel: 'Folio Siara:',
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 disabled: false,
	        			  	        	        		 maxLength 	: 30,
	        			  	        	        		listeners:{
	    	        			  	  		                change: function(field, newValue, oldValue){
	    	        			  	  		                    field.setValue(newValue.toUpperCase());
	    	        			  	  		                },	
	        			  	        	        		},
	        			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
//	        			  	        	        		 readOnly: false,
	        			  	        	        		 
	        			  	        	        		 width : 660,
	        			  	        	        		 msgTarget: 'side'		,
	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW
	        			  	        	        		               ,
	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE
	        			  	        	        		               ]    																	    
	        			  	        	        	 },{
	        			  	        	        		 xtype: 'textfield',
	        			  	        	        		 itemId: 'monto',
	        			  	        	        		 name: 'montoSolicitado',
	        			  	        	        		 fieldLabel: 'Monto Requerido:',
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 disabled: false,
	        			  	        	        		maskRe: /[0-9]/,
//	        			  	        	        		 regex: new RegExp('^(([0-9]){25})$'),
//	        			  	        	        		 readOnly: false,
	        			  	        	        		 width : 660,
	        			  	        	        		 maxLength 	: 25,
	        			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de digitos revasado.',
	        			  	        	        		 msgTarget: 'side'	,
	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]	  
	        			  	        	        		 
	        			  	        	        	 }] 
	        			  	        },
	        			  	        {
	        			  	        	title: 'Titular',
	        			  	        	layout : 'vbox',
	        			  	        	items : [
	        			  	        	         {
	        			  	        	        	 xtype : 'splitter'
	        			  	        	         },{
	        			  	        	        	 xtype: 'fieldcontainer',
	        			  	        	        	 layout: 'hbox',
	        			  	        	        	 items: [{
	        			  	        	        		 xtype: 'gfibuscadortrigger',
	        			  	        	        		 fieldLabel : 'Persona:',
	        			  	        	        		 name: 'persoTitular',
	        			  	        	        		 itemId : 'personaTit',
	        			  	        	        		 grupo: 'admctasweb', 
	        			  	        	        		 clave: 'mapeo',
	        			  	        	        		 editable : false,
	        			  	        	        		 linkProperty: 'cveSiti',
	        			  	        	        		 controls: [{
	        			  	        	        			 ref: 'txPersonaTit', 
	        			  	        	        			 selector: '#txPersonaTit',
	        			  	        	        			 linkProperty: 'descripcion'
	        			  	        	        		 }],
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 width : 250,
	        			  	        	        		 maxLength 	: 1,
	        			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
	        			  	        	        		 defaultFilters: [
	        			  	        	        		                  new Ext.util.Filter({
	        			  	        	        		                	  property: 'idCatalogo',
	        			  	        	        		                	  operator: '=',
	        			  	        	        		                	  value: '8'
	        			  	        	        		                  })
	        			  	        	        		                  ],
	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 },{
	        			  	        	        		 xtype: 'splitter'
	        			  	        	        	 }, {
	        			  	        	        		 xtype : 'textfield',
	        			  	        	        		 itemId : 'txPersonaTit',
	        			  	        	        		 name : 'txPersonaTit',
	        			  	        	        		 disabled: false,
			  	        	        		        	 readOnly: true,
	        			  	        	        		listeners:{
	        			  	      		                change: function(field, newValue, oldValue){
	        			  	      		                    field.setValue(newValue.toUpperCase());
	        			  	      		                }
	        			  	      		             },
	        			  	        	        		 width : 405
//	        			  	        	        		 ,
//	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 }]
	        			  	        	         },{
	        			  	        	        	 xtype : 'splitter'
	        			  	        	         },{
	        			  	        	        	 xtype: 'fieldcontainer',
	        			  	        	        	 layout: 'hbox',
	        			  	        	        	 items: [{
	        			  	        	        		 xtype: 'gfibuscadortrigger',
	        			  	        	        		 fieldLabel : 'Caracter Titular:',
	        			  	        	        		 name: 'caracTitular',
	        			  	        	        		 itemId : 'caracterTit',
	        			  	        	        		 grupo: 'admctasweb', 
	        			  	        	        		 clave: 'mapeo',
	        			  	        	        		 emptyText		: 'Seleccionar',
	        			  	        	        		 editable : false,
	        			  	        	         		 maxLength 	: 13,
	        			  	        	         		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
	        			  	        	        		 linkProperty: 'cveSiti',
	        			  	        	        		 controls: [{
	        			  	        	        			 ref: 'txCaracterTit', 
	        			  	        	        			 selector: '#txCaracterTit',
	        			  	        	        			 linkProperty: 'descripcion'
	        			  	        	        		 }],
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 width : 250,
	        			  	        	        		 defaultFilters: [
	        			  	        	        		                  new Ext.util.Filter({
	        			  	        	        		                	  property: 'idCatalogo',
	        			  	        	        		                	  operator: '=',
	        			  	        	        		                	  value: '9'
	        			  	        	        		                  })
	        			  	        	        		                  ],
	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 },{
	        			  	        	        		 xtype: 'splitter'
	        			  	        	        	 }, {
	        			  	        	        		 xtype : 'textfield',
	        			  	        	        		 itemId : 'txCaracterTit',
	        			  	        	        		 name : 'txCaracterTit',
	        			  	        	        		 emptyText		: 'Debe Seleccionar clave de caracter CNBV del Titular',
	        			  	        	        		 width : 405,
	        			  	        	        		 disabled: false,
			  	        	        		        	 readOnly: true
//			  	        	        		        	 ,
//	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 }]
	        			  	        	         },{
	        			  	        	        	 xtype : 'splitter'
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'rfcTit',
	        			  	        	        	 name: 'rfcTitular',
	        			  	        	        	 fieldLabel: 'RFC:', 
	        			  	        	        	 labelWidth: 140,
//	        			  	        	        	 fieldStyle : 'text-transform: uppercase',

	        			  	        	        	 regex: new RegExp('^(([A-Z]|\_){1})(([A-Z]){3})([0-9]{6})((([A-Z]|[0-9]){3}))$'),
        			  	        	        		 maxLength 	: 13,
        			  	        	        		 maxLengthText : 'RFC Incorrecto.',
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 
	        			  	        	        	 itemId: 'razonTit',
	        			  	        	        	 name: 'razonTitular',
	        			  	        	        	 fieldLabel: 'Razon Social:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 width :  660,
	        			  	        	        	listeners:{
	        			  	  		                change: function(field, newValue, oldValue){
	        			  	  		                    field.setValue(newValue.toUpperCase());
	        			  	  		                }
	        			  	  		             },
	        			  	        	        	 maxLength 	: 250,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',       			  	        	        	
	        			  	        	        	 msgTarget: 'side'		    																	    
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'nombreTit',
	        			  	        	        	 name: 'nombreTitular',
	        			  	        	        	 fieldLabel: 'Nombre:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 width : 660,
	        			  	        	        	listeners:{
	        			  	  		                change: function(field, newValue, oldValue){
	        			  	  		                    field.setValue(newValue.toUpperCase());
	        			  	  		                }
	        			  	  		             },
	        			  	        	        	 maxLength 	: 100,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        	 msgTarget: 'side'		    ,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]																	    
	        			  	        	         },
	        			  	        	         {
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'paternoTit',
	        			  	        	        	 name: 'paternoTitular',
	        			  	        	        	 fieldLabel: 'Apellido Paterno:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 width :  660,
	        			  	        	        	listeners:{
	        			  	  		                change: function(field, newValue, oldValue){
	        			  	  		                    field.setValue(newValue.toUpperCase());
	        			  	  		                }
	        			  	  		             },
	        			  	        	        	 maxLength 	: 100,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        	 msgTarget: 'side'	,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]	    																	    
	        			  	        	         },	
	        			  	        	         {
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'maternoTit',
	        			  	        	        	 name: 'maternoTitular',
	        			  	        	        	 fieldLabel: 'Apellido Materno:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	listeners:{
	        			  	  		                change: function(field, newValue, oldValue){
	        			  	  		                    field.setValue(newValue.toUpperCase());
	        			  	  		                }
	        			  	  		             },
	        			  	        	        	 width : 660,
	        			  	        	        	 maxLength 	: 100,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        	 msgTarget: 'side'		 ,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]   																	    
	        			  	        	         }]		        
	        			  	        },
	        			  	        {
	        			  	        	title: 'Cotitulares',
	        			  	        	layout : 'vbox',
	        			  	        	items : [
	        			  	        	         {
	        			  	        	        	 xtype : 'splitter'
	        			  	        	         },{
	        			  	        	        	 xtype: 'fieldcontainer',
	        			  	        	        	 layout: 'hbox',
	        			  	        	        	 items: [{
	        			  	        	        		 xtype: 'gfibuscadortrigger',
	        			  	        	        		 fieldLabel : 'Persona:',
	        			  	        	        		 name: 'persoCoti',
	        			  	        	        		 itemId : 'personaCot',
	        			  	        	        		 
	        			  	        	        		 grupo: 'admctasweb', 
	        			  	        	        		 clave: 'mapeo',
	        			  	        	        		 editable : false,
	        			  	        	        	 	 maxLength 	: 1,
	        			  	        	        	 	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
	        			  	        	        		 linkProperty: 'cveSiti',
	        			  	        	        		 controls: [{
	        			  	        	        			 ref: 'txPersonaTit', 
	        			  	        	        			 selector: '#txPersonaCot',
	        			  	        	        			 linkProperty: 'descripcion'
	        			  	        	        		 }],
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 width : 250,
	        			  	        	        		 defaultFilters: [
	        			  	        	        		                  new Ext.util.Filter({
	        			  	        	        		                	  property: 'idCatalogo',
	        			  	        	        		                	  operator: '=',
	        			  	        	        		                	  value: '8'
	        			  	        	        		                  })
	        			  	        	        		                  ],
	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 },{
	        			  	        	        		 xtype: 'splitter'
	        			  	        	        	 }, {
	        			  	        	        		 xtype : 'textfield',
	        			  	        	        		 itemId : 'txPersonaCot',
	        			  	        	        		 name : 'txPersonaCot',
	        			  	        	        		 width : 405,
	        			  	        	        		 disabled: false,
			  	        	        		        	 readOnly: true
//	        			  	        	        		 ,
//	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 }]
	        			  	        	         },{
	        			  	        	        	 xtype : 'splitter'
	        			  	        	         },{
	        			  	        	        	 xtype: 'fieldcontainer',
	        			  	        	        	 layout: 'hbox',
	        			  	        	        	 items: [{
	        			  	        	        		 xtype: 'gfibuscadortrigger',
	        			  	        	        		 fieldLabel : 'Caracter Cotitular:',
	        			  	        	        		 name: 'caracCoti',
	        			  	        	        		 itemId : 'caracterCot',
	        			  	        	        		 emptyText		: 'Seleccione',
	        			  	        	        		 grupo: 'admctasweb', 
	        			  	        	        		 clave: 'mapeo',
	        			  	        	        		 editable : false,
	        			  	        	        		 maxLength 	: 13,
	        			  	        	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
	        			  	        	        		 linkProperty: 'cveSiti',
	        			  	        	        		 controls: [{
	        			  	        	        			 ref: 'txCaracterCot', 
	        			  	        	        			 selector: '#txCaracterCot',
	        			  	        	        			 linkProperty: 'descripcion'
	        			  	        	        		 }],
	        			  	        	        		 labelWidth: 140,
	        			  	        	        		 width : 250,
	        			  	        	        		 defaultFilters: [
	        			  	        	        		                  new Ext.util.Filter({
	        			  	        	        		                	  property: 'idCatalogo',
	        			  	        	        		                	  operator: '=',
	        			  	        	        		                	  value: '9'
	        			  	        	        		                  })
	        			  	        	        	     ],
	        			  	        	        	     formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			  	        	        	                   gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 },{
	        			  	        	        		 xtype: 'splitter'
	        			  	        	        	 }, {
	        			  	        	        		 xtype : 'textfield',
	        			  	        	        		 itemId : 'txCaracterCot',
	        			  	        	        		 emptyText		: 'Debe Seleccionar clave de caracter CNBV para Cotitular',
	        			  	        	        		 name : 'txCaracterCot',
	        			  	        	        		 disabled: false,
			  	        	        		        	 readOnly: true,
	        			  	        	        		 width : 405
//	        			  	        	        		 ,
//	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
//	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 }]
	        			  	        	         },{
	        			  	        	        	 xtype : 'splitter'
	        			  	        	         },{
	        			  	        	        	 xtype: 'gfibuscadortrigger',
	        			  	        	        	 itemId: 'idCoti',
	        			  	        	        	 name: 'idCotitular',
	        		  	        	        		 grupo: 'admctasweb', 
 	        		        						 clave: 'coti',
	        			  	        	        	 fieldLabel: 'Id Cotitular:',
	        			  	        	        	 linkProperty: 'idPersona',
	        			  	        	        	 labelWidth: 140,
	        			     			  			 disabled: false,
	        			     			  			 onTriggerClick: function() {
	        		        							capturas = me.down('#idCuenta').value;
	        		        							if(capturas == null || capturas == ''){
	        		        								Ext.Msg.alert('AdmCtasWeb', 'Seleccione una Cuenta');
	        		        							}
	        		        							else{
	        		        								filtro = new Ext.util.Filter({
	        		        									property: 'idContrato',
	        		        									operator: '=',
	        		        									value:  capturas
	        		        								});
	        		        								this.setDefaultFilters(filtro);
	        		        								this.search();
	        		        							}
	        		        						},
	        			    			  			 value : ' ',
	        			  	        	        	 maxLength 	: 8,
	        			  	        	        	 controls: [{
        			  	        	        			 ref: 'rfcCot', 
        			  	        	        			 selector: '#rfcCot',
        			  	        	        			 linkProperty: 'rfc'
        			  	        	        		 },{
        			  	        	        			 ref: 'razonCot', 
        			  	        	        			 selector: '#razonCot',
        			  	        	        			 linkProperty: 'nomRazonSocial'
        			  	        	        		 },{
        			  	        	        			 ref: 'nombreCot', 
        			  	        	        			 selector: '#nombreCot',
        			  	        	        			 linkProperty: 'nombrePersona'
        			  	        	        		 },{
        			  	        	        			 ref: 'paternoCot', 
        			  	        	        			 selector: '#paternoCot',
        			  	        	        			 linkProperty: 'apellidoPaterno'
        			  	        	        		 },{
        			  	        	        			 ref: 'maternoCot', 
        			  	        	        			 selector: '#maternoCot',
        			  	        	        			 linkProperty: 'apellidoMaterno'
        			  	        	        		 }],
        			  	        	        		  
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
	        			  	        	        	 msgTarget: 'side',
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW
//        			  	        	        		               ,
//        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE
        			  	        	        		               ]	    																	    
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'rfcCot',
	        			  	        	        	 name: 'rfcCoti',
	        			  	        	        	 fieldLabel: 'RFC:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 listeners:{
    	        			  	  		                valitychange: function(field, newValue, oldValue){
    	        			  	  		                field.setValue(newValue.toUpperCase());
//    	        			  	  		                capturas = me.down('#txCaracterCot').value;
//   	        			  	  		                	if(capturas == null || capturas == ''){
//   	        			  	  		                		Ext.Msg.alert('AdmCtasWeb', 'Seleccione el caracter de Cotitular');
//   	        			  	  		                	}
   	        			  	  		                    capturas = me.down('#razonCot').value;
	        			  	  		               
    	        			  	  		                    if(capturas == null || capturas == ''){
    	        			  	  		                        me.down("#personaCot").setValue('1');
    	        			  	  		                    	me.down("#txPersonaCot").setValue('Física Nacional');
    	        			  	  		                    }
    	        			  	  		                    else{
    	        			  	  		                    	me.down("#personaCot").setValue('2');
    	        			  	  		                    	me.down("#txPersonaCot").setValue('Moral Nacional'); 	        	        								
    	        	        								};    
    	        			  	  		             }},
	        			  	
//	        			  	        	        	 fieldStyle : 'text-transform: uppercase',
	        			  	        	        	 regex: new RegExp('^(([A-Z]|\_){1})(([A-Z]){3})([0-9]{6})((([A-Z]|[0-9]){3}))$'),
        			  	        	        		 maxLength 	: 13,
        			  	        	        		 maxLengthText : 'RFC Incorrecto.'		    																	    
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'razonCot',
	        			  	        	        	 name: 'razonCoti',
	        			  	        	        	 fieldLabel: 'Razon Social:',
	        			  	        	        	listeners:{
    	        			  	  		                change: function(field, newValue, oldValue){
    	        			  	  		                field.setValue(newValue.toUpperCase());
//    	        			  	  		                capturas = me.down('#txCaracterCot').value;
//    	        			  	  		                if(capturas == null || capturas == ''){
//    	    	        							      Ext.Msg.alert('AdmCtasWeb', 'Seleccione el caracter de Cotitular');
//    	        			  	  		                }
    	        			  	  		                    capturas = me.down('#razonCot').value;
    	        			  	  		                    if(capturas == null || capturas == ''){
    	        			  	  		                        me.down("#personaCot").setValue('1');
    	        			  	  		                    	me.down("#txPersonaCot").setValue('Física Nacional');
    	        			  	  		                    }
    	        			  	  		                    else{
    	        			  	  		                    	me.down("#personaCot").setValue('2');
    	        			  	  		                    	me.down("#txPersonaCot").setValue('Moral Nacional'); 	        	        								
    	        	        								};    
    	        			  	  		                }},
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 width :  660,
	        			  	        	        	 maxLength 	: 250,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        	 msgTarget: 'side'		,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]    																	    
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'nombreCot',
	        			  	        	        	 name: 'nombreCoti',
	        			  	        	        	 fieldLabel: 'Nombre Cotitular:',
	        			  	        	        	listeners:{
	        			  	  		                change: function(field, newValue, oldValue){
	        			  	  		                    field.setValue(newValue.toUpperCase());
	        			  	  		                }
	        			  	  		             },
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 width :  660,
	        			  	        	        	 maxLength 	: 100,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        	 msgTarget: 'side'		    																	    
	        			  	        	         },
	        			  	        	         {
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'paternoCot',
	        			  	        	        	 name: 'paternoCoti',
	        			  	        	        	 fieldLabel: 'Apellido Paterno:',
	        			  	        	        	listeners:{
    	        			  	  		                change: function(field, newValue, oldValue){
    	        			  	  		                capturas = me.down('#txCaracterCot').value;
//    	        			  	  		                if(capturas == null || capturas == ''){
//    	    	        							      Ext.Msg.alert('AdmCtasWeb', 'Seleccione el caracter de Cotitular');
//    	        			  	  		                }
    	        			  	  		                field.setValue(newValue.toUpperCase());
    	        			  	  		                capturas = me.down('#razonCot').value;
    	        			  	  		                    if(capturas == null || capturas == ''){
    	        			  	  		                        me.down("#personaCot").setValue('1');
    	        			  	  		                    	me.down("#txPersonaCot").setValue('Física Nacional');
    	        			  	  		                    }
    	        			  	  		                    else{
    	        			  	  		                    	me.down("#personaCot").setValue('2');
    	        			  	  		                    	me.down("#txPersonaCot").setValue('Moral Nacional'); 	        	        								
    	        	        								}; 
    	        			  	  		                }},
	        			  	        	        	 width :  660,
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 maxLength 	: 100,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        	 msgTarget: 'side'	,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]	    																	    
	        			  	        	         },	
	        			  	        	         {
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'maternoCot',
	        			  	        	        	 name: 'maternoCoti',
	        			  	        	        	 fieldLabel: 'Apellido Materno:',
	        			  	        	        	listeners:{
	        			  	  		                change: function(field, newValue, oldValue){
	        			  	  		                    field.setValue(newValue.toUpperCase());
	        			  	  		                }
	        			  	  		             },
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 width : 660,
	        			  	        	        	 maxLength 	: 100,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        	 msgTarget: 'side'		,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]    																	    
	        			  	        	         }] 
	        			  	        },
	        			  	        {
	        			  	        	title: 'Operación',
	        			  	        	layout : 'vbox',
	        			  	        	items : [
	        			  	        	         {
	        			  	        	        	 xtype : 'splitter'
	        			  	        	         },{
	        			  	        	        	 xtype: 'fieldcontainer',
	        			  	        	        	 layout: 'hbox',
	        			  	        	        	 items: [{
	        			  	        	        		 xtype: 'gfibuscadortrigger', 
	        			  	        	        		 fieldLabel : 'Tipo Operación',
	        			  	        	        		 name : 'operacion',
	        			  	        	        		 itemId : 'tipoOper',
//	        			  	        	        		 linkProperty : 'descripcion',
	        			  	        	        		 grupo: 'admctasweb', 
	        			  	        	        		 clave: 'mapeo',
	        			  	        	        		 editable : false,
	        			  	        	        		 labelWidth : 140,													
	        			  	        	        		 width : 250,
	        			  	        	        	 	 maxLength 	: 3,
	        			  	        	        	 	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
	        			  	        	        		 defaultFilters: [
	        			  	        	        		                  new Ext.util.Filter({
	        			  	        	        		                	  property: 'idCatalogo',
	        			  	        	        		                	  operator: '=',
	        			  	        	        		                	  value: '7'
	        			  	        	        		                  })
	        			  	        	        		                  ],        		
	        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
	        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE],
	        			  	        	        		               linkProperty: 'cveSiti',
	        			  	        	        		               controls: [{
	        			  	        	        		            	   ref: 'txtipoOpeer', 
	        			  	        	        		            	   selector: '#txtipoOper',
	        			  	        	        		            	   linkProperty: 'descripcion'
	        			  	        	        		               }]
	        			  	        	        	 },
	        			  	        	        	 {
	        			  	        	        		 xtype: 'splitter'
	        			  	        	        	 },{
	        			  	        	        		 xtype : 'textfield',
	        			  	        	        		 itemId : 'txtipoOper',
	        			  	        	        		 name :  'txtipoOper',
	        			  	        	        		listeners:{
	        			  	      		                change: function(field, newValue, oldValue){
	        			  	      		                    field.setValue(newValue.toUpperCase());
	        			  	      		                }
	        			  	      		             },
	        			  	        	        		 width : 405,    			
// 														 formStatus : [gfi.component.BasicForm.STATUS_NEW,
// 																	   gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 }],		
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'oficioReqOp',
	        			  	        	        	 name: 'oficioReqOpe',
	        			  	        	        	 defaults:{value:'textfield'},
	        			  	        	        	 fieldLabel: 'Número de Oficio Requerido:',
	        			  	        	           	 labelWidth: 140,
	        			  	        	           	 editable:false,
	        			  	        	           	 readOnly: true,
	        			  	        	           listeners:{
	        			  	 		                change: function(field, newValue, oldValue){
	        			  	 		                    field.setValue(newValue.toUpperCase());
	        			  	 		                }
	        			  	 		             },       			  	        	        	  
	        			  	        	        	 width : 660,
	        			  	        	        	 maxLength 	: 25,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        	 msgTarget: 'side'		,
	        			  	        	        	 formStatus : [gfi.component.BasicForm.STATUS_NEW
//	        			  	        	        	               ,
//        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE
        			  	        	        		               ]
	        			  	        	         },		
	        			  	        	         {
	        			  	        	        	 xtype : 'datefield',
	        			  	        	        	 itemId: 'fReqOper',
	        			  	        	        	 name: 'fecharRequerimiento',
	        			  	        	        	 fieldLabel: 'Fecha Requerimiento:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 disabled: false,
	        			  	        	        	 readOnly: false,
	        			  	        	        	 format : 'Ymd',
	        			  	        	        	 msgTarget: 'side'	,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]	    																	    
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'siara',
	        			  	        	        	 name: 'siaraIndirecto',
	        			  	        	        	 fieldLabel: 'Siara indirecto:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	listeners:{
	        			  	  		                change: function(field, newValue, oldValue){
	        			  	  		                    field.setValue(newValue.toUpperCase());
	        			  	  		                },		  
       			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW
       			  	        	        		               ,
    			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE
    			  	        	        		               ]  
	        			  	  		             },
	        			  	        	        	 width : 660,
	        			  	        	        	 maxLength 	: 30,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
	        			  	        	        	 msgTarget: 'side'		  ,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]  																	    
	        			  	        	         },	
	        			  	        	         {
	        			  	        	        	 xtype : 'datefield',
	        			  	        	        	 itemId: 'fAplicacion',
	        			  	        	        	 name: 'fechaAplicacion',
	        			  	        	        	 fieldLabel: 'Fecha Aplicacion:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 disabled: false,
	        			  	        	        	 readOnly: false,
	        			  	        	        	 format : 'Ymd',
	        			  	        	        	 msgTarget: 'side'		,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]    																	    
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'montoOpera',
	        			  	        	        	 name: 'montoOperacion',
	        			  	        	        	 fieldLabel: 'Monto Operación:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 width : 660,
	        			  	        	        	maskRe: /[0-9]/,
//	        			  	        	        	regex: new RegExp('^(([0-9]){25})$'),
	        			  	        	        	 maxLength 	: 25,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de digitos revasado.',
	        			  	        	        	 msgTarget: 'side'		,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]    																	    
	        			  	        	         },{
	        			  	        	        	 xtype: 'fieldcontainer',
	        			  	        	        	 layout: 'hbox',
	        			  	        	        	 items: [{
	        			  	        	        		 xtype: 'gfibuscadortrigger', 
	        			  	        	        		 fieldLabel : 'Moneda Operación:',
	        			  	        	        		 name : 'monedaOperacion',
	        			  	        	        		 itemId : 'monedaOpera',
//	        			  	        	        		 linkProperty : 'descripcion',
	        			  	        	        		 grupo: 'admctasweb', 
	        			  	        	        		 clave: 'mapeo',
	        			  	        	        		 editable : false,
	        			  	        	        		 labelWidth : 140,													
	        			  	        	        		 width : 250,
	        			  	        	        	     maxLength 	: 1,
	        			  	        	        	     maxLengthText : 'M\u00E1ximo n\u00FAmero de digitos revasado.',
	        			  	        	        		 defaultFilters: [
	        			  	        	        		                  new Ext.util.Filter({
	        			  	        	        		                	  property: 'idCatalogo',
	        			  	        	        		                	  operator: '=',
	        			  	        	        		                	  value: '6'
	        			  	        	        		                  })
	        			  	        	        		 ],        		
					            		                 formStatus : [gfi.component.BasicForm.STATUS_NEW,
					            		                               gfi.component.BasicForm.STATUS_UPDATE],
					            		                 linkProperty: 'cveSiti',
					            		                 controls: [{
					            		                	 ref: 'txMonedaOpera', 
					            		                	 selector: '#txMonedaOpera',
					            		                	 linkProperty: 'descripcion'
					            		                 }]
	        			  	        	        	 },
	        			  	        	        	 {
	        			  	        	        		 xtype: 'splitter'
	        			  	        	        	 },{
	        			  	        	        		 xtype : 'textfield',
	        			  	        	        		 itemId : 'txMonedaOpera',
	        			  	        	        		 name : 'txMonedaOpera',
	        			  	        	        		 width : 405,   
	        			  	        	        		 editable: false,
	        			  	        	        		 readOnly : true,
// 														 formStatus : [gfi.component.BasicForm.STATUS_NEW,
// 																	   gfi.component.BasicForm.STATUS_UPDATE]
	        			  	        	        	 }],		
	        			  	        	         },{
	        			  	        	        	 xtype: 'textfield',
	        			  	        	        	 itemId: 'saldo',
	        			  	        	        	 name: 'saldoOperacion',
	        			  	        	        	 fieldLabel: 'Saldo despúes Oper.:',
	        			  	        	        	 labelWidth: 140,
	        			  	        	        	 width : 660, 
	        			  	        	        	maskRe: /[0-9]/,
//	        			  	        	        	regex: new RegExp('^(([0-9]){1-25})$'),
	        			  	        	        	 maxLength 	: 25,
	        			  	        	        	 maxLengthText : 'M\u00E1ximo n\u00FAmero de dígitos revasado.',
	        			  	        	        	 msgTarget: 'side'		    	,
        			  	        	        		 formStatus : [gfi.component.BasicForm.STATUS_NEW,
        			  	        	        		               gfi.component.BasicForm.STATUS_UPDATE]																    
	        			  	        	         }] 
	        			  	        }
	                 ,
		        
	        			  ],
	        			  	     renderTo : Ext.getBody()
	        			  })
	        			  ]
	        			  
		        }
		
		];
		
	},
	
	// Se sobreescriben las Acciones de la Forma.
	
	// Completar Selección.
	completeSeleccionar: function (completed, options) {
		var me = this;
		var buscadorEmpresa = me.down('#idEmpresaCaptura'); 
		//var buscadorPeriodo = me.down('#busCvePeriodo'); 
		var buscadorPersonaTit = me.down('#personaTit'); 
		var buscadorCaracTit = me.down('#caracterTit'); 
		var buscadorPersoCoti = me.down('#personaCot'); 
		var buscadorCaracCoti = me.down('#caracterCot'); 
		var buscadorSucursal = me.down('#sucursal'); 
		var buscadorModalidad = me.down('#modalidad'); 
		var buscadorTipoNivel = me.down('#tipoNivel'); 
		var buscadorMonedaCuenta = me.down('#monedaAsegurada'); 
		var buscadorTipoOper = me.down('#tipoOper'); 
		var buscadorMonedaOpera = me.down('#monedaOpera'); 
		var buscadorProducto = me.down('#Producto'); 
		
		buscadorEmpresa.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('institucion')}) );
		//buscadorPeriodo.loadRecord( new Ext.util.Filter([{property: 'cvePeriodo',value: options.model.get('cvePeriodo')}, {property: 'idEmpresa',value: options.model.get('cvePeriodo')}]) );
		buscadorPersonaTit.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('persoTitular')}) );
		buscadorCaracTit.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('caracTitular')}) );
		buscadorPersoCoti.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('persoCoti')}) );
		buscadorCaracCoti.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('caracCoti')}) );
		buscadorSucursal.loadRecord( new Ext.util.Filter({property: 'idSucursal', value: options.model.get('idSucursal')}) );
		buscadorModalidad.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('modalidad')}) );
		buscadorTipoNivel.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('tipoNivel')}) );
		buscadorMonedaCuenta.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('monedaCuenta')}) );
		buscadorTipoOper.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('operacion')}) );
		buscadorMonedaOpera.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('monedaOperacion')}) );
		buscadorProducto.loadRecord( new Ext.util.Filter({property: 'cveSiti', value: options.model.get('producto')}) );
		
		me.actions.seleccionar.completeAction.call(me, completed, options);
    },
    
    
 // Editar - Validamos Estatus de la situación.
	doEditar : function(options) {
		var me = this;
		var sitOficio = Ext.getCmp('situacionManualFormValida').getValue();
		
		if (sitOficio === 'CA') {
			
			options.message = 'El registro tiene un estatus de cancelado no puede ser editado.';
			Ext.Msg.show({
				title : 'Aviso',
				msg : options.message,
				buttons : Ext.Msg.OK,
				icon : Ext.Msg.WARNING,
				modal : true
			});
			
		} else {
			
			var completed = me.actions.editar.doAction.call(me, options);
			me.actions.editar.completeAction.call(me,completed, options);

		}
	}
	
});

