Ext.define('gfi.bin.admctasweb.view.reporte_r29.PeriodoView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.periodoview',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],
	
	//Configuración del Componente.
	config : {
		header: 'Consulta de Periodo',
		formType: gfi.corp.component.form.Types.CATALOGO,
		grupo: 'admctasweb', 
		clave: 'peri',
		modelClass : 'gfi.bin.admctasweb.model.r29.Periodo',
		primaryKeys : ['idEmpresa','cvePeriodo'],
		baseUrl: 'reporte_r29/periodo/',
		secured: false
	},

	getWidth	: function() {
		return 850;
	},

	
	//Construimos los items de la Forma
	buildItems : function() {
		var me = this;
		var capturas;
		var filtro;
		
		return [{ 
    		xtype: 'container',
    		layout: 'hbox',
    		items: [
    		        { 
    		        	xtype: 'gfibuscadortrigger',
    		        	fieldLabel : 'Empresa:',
    		        	name: 'idEmpresaP',
    		        	itemId : 'idEmpresaP',
    		        	grupo: 'admctasweb', 
    		        	clave: 'mapeo',
    		        	editable : false,
    		        	linkProperty: 'cveCorporativa',
    		        	controls: [{ 
    		        		ref: 'txInstitucionP', 
    		        		selector: '#txInstitucionP',
    		        		linkProperty: 'descripcion'
    		        	},{ 
    		        		ref: 'cveSiti', 
    		        		selector: '#cveSiti',
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
    		           formStatus : [gfi.component.BasicForm.STATUS_NEW
//    		                         ,
//    		                         gfi.component.BasicForm.STATUS_UPDATE
    		                         ],
    		        },{
    		        	xtype: 'splitter'
    		        }, {
    		        	xtype : 'textfield',
    		        	itemId : 'txInstitucionP',
    		        	name : 'txInstitucionP',
    		        	readOnly: true,
    		        	editable: false,
    		        	width : 415
//    		        	,
//    		        	formStatus : [gfi.component.BasicForm.STATUS_NEW
////    		        	              ,
////    		        	              gfi.component.BasicForm.STATUS_UPDATE
//    		        	              ],
    		        },{
    		        	xtype : 'textfield',
    		        	itemId : 'cveSiti',
    		        	name : 'cveSiti', 
    		        	hidden : true,
    		        	width : 0,
    		        	formStatus : [gfi.component.BasicForm.STATUS_NEW,
    		        	              gfi.component.BasicForm.STATUS_UPDATE],
    		        }] 
    		},{
      	 		xtype: 'splitter'
      	 	},
      	 	{
	        	 xtype: 'fieldcontainer',
 	        	 layout: 'hbox',
 	        	 items: [{
 	        		 xtype: 'textfield', 
// 	        		 xtype: 'gfibuscadortrigger', 
 	        		 fieldLabel : 'Periodo:',
 	   			     name : 'cvePeriodoP',
 	        		 itemId : 'cvePeriodoP',
 	        		 grupo: 'admctasweb', 
 	        		 clave: 'peri',
 	        		 editable : true,
 	        		 labelWidth : 140,													
 	        		 width : 250,
 	        		 maxLength 	: 6,
 	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
	                 formStatus : [gfi.component.BasicForm.STATUS_NEW
////	                               ,
//	                               gfi.component.BasicForm.STATUS_UPDATE
	                               ],
	                 linkProperty: 'cvePeriodo',
	                 controls: [{
	                	 ref: 'descPeriodoP', 
	                	 selector: '#descPeriodoP',
	                	 linkProperty: 'descPeriodo'
	                 }
//	              	 ,{
//	                	 ref: 'fechaInicial', 
//	                	 selector: '#fechaInicial',
//	                	 linkProperty: 'fechaInicial'
//	                 },{
//	                	 ref: 'terminar', 
//	                	 selector: '#termminar',
//	                	 linkProperty: 'terminar'
//	                 }
	                 ],
	                 onTriggerClick: function() {
	                	 
							capturas = me.down('#cveSiti').value;

							if(capturas == null || capturas == ''){
								Ext.Msg.alert('AdmCtasWeb', 'Seleccione una Institución');
							}
							else{
								filtro = new Ext.util.Filter({
									property: 'idEmpresa',
									operator: '=',
									value:  capturas
								}
								);
								this.setDefaultFilters(filtro);
								this.search();
							}
						},  
 	        	 },
 	        	 {
 	        		 xtype: 'splitter'
 	        	 },{
 	    			xtype : 'textfield',
 	    			name : 'descPeriodoP',
 	    			labelWidth: 140,
 	    			width : 440,
 	    			listeners:{
 	                   change: function(field, newValue, oldValue){
 	                       field.setValue(newValue.toUpperCase());
 	                   }
 	                },
 	    			formStatus : [gfi.component.BasicForm.STATUS_NEW,
 	    			              gfi.component.BasicForm.STATUS_UPDATE]
 	        	 }],		
 	         }, {
 	        	 xtype: 'fieldcontainer',
 	        	 layout: 'hbox',
 	        	 	items: [{
 	   				xtype : 'datefield',
 					fieldLabel : 'Fecha Inicio:',
 					name : 'fechaInicial',
 					itemid: 'fechaInicial',
 					labelWidth: 140,
 					width : 250,
 					listeners:{
	 						onchage: function (value,date){
	 							var dateParsed = value;
	 							var endDate = me.down('#fechaFinal').value;
	 							var initDate = me.down('#fechaInicial').value;
	 							Ext.Msg.alert('AdmCtasWeb', 'Social'+endDate);
							if(endDate == null || endDate == ''){
								Ext.Msg.alert('AdmCtasWeb', 'Seleccione Fecha Final');
							}else{
								if(initDate>=endDate){
									Ext.Msg.alert('AdmCtasWeb', 'Seleccione fechas correctas');
								}
	 							} 
	 						}
	 					},
// 					editable:true,
// 					listeners:{
// 						select: function (value,date){
// 							dateValidate();    
// 						}
// 					},

 					formStatus : [gfi.component.BasicForm.STATUS_NEW,
 					              gfi.component.BasicForm.STATUS_UPDATE]
 	        	 	},{
 	        	 		xtype: 'splitter'
 	        	 	},{
 	        	 		xtype : 'datefield',
 	 					fieldLabel : 'Fecha Final:',
 	 					name : 'fechaFinal',
 	 					itemid: 'fechaFinal',
 	 					labelWidth: 140,
 	 					format 		: 'd/m/Y',
 	 					editable:true,
 	 					listeners:{
 	 						onchage: function (value,date){
 	 							var dateParsed = value;
 	 							var endDate = me.down('#fechaFinal').value;
 	 							var initDate = me.down('#fechaInicial').value;
 	 							Ext.Msg.alert('AdmCtasWeb', 'Social'+endDate);
    							if(initDate == null || initDate == ''){
    								Ext.Msg.alert('AdmCtasWeb', 'Seleccione Fecha Inicial');
    							}else{
    								if(initDate>=endDate){
										Ext.Msg.alert('AdmCtasWeb', 'Seleccione fechas correctas');
    								}
 	 							} 
 	 						}
 	 					},
 	 					formStatus : [gfi.component.BasicForm.STATUS_NEW,
 	 					              gfi.component.BasicForm.STATUS_UPDATE]
 	 	        	 	},{
 	 	        	 		xtype: 'splitter'
 	 	        	 	},{
 	 	        	 		xtype : 'textfield',
 	 	 					fieldLabel : '',
 	 	 					hidden : true,
 	 	 					name : 'fechaFinalxx',
 	 	 					itemid: 'fechaFinalxx',
 	 	 					labelWidth: 140,
 	 	 					format 		: 'd/m/Y',
 	 	 					width : 250,
 	 	 					formStatus : [gfi.component.BasicForm.STATUS_NEW,
 	 	 					              gfi.component.BasicForm.STATUS_UPDATE]	 
 	 	        	 	},{
 	 	 	 	    			xtype : 'textfield',
 	 	 	 	    			name : 'estatus',
 	 	 	 	    			value : '  ',
 	 	 	 	    			hidden : true,
 	 	 	 	    			labelWidth: 140,
 	 	 	 	    			width : 440,
 	 	 	 	    			formStatus : [gfi.component.BasicForm.STATUS_NEW,
 	 	 	 	    			              gfi.component.BasicForm.STATUS_UPDATE]
 	 	 	 	        	 }],
 	         }	
		];
	},
	
	//Se sobreescriben las Acciones de la Forma.
	
	//Completar Selección.
	completeSeleccionar: function (completed, options) {

		var me = this;
//		var buscadorEmpresa = me.down('#idEmpresa'); 
//		var buscadorPeriodo = me.down('#cvePeriodo'); 
//				
//		buscadorEmpresa.loadRecord( new Ext.util.Filter({property: 'idEmpresa', value: options.model.get('idEmpresa')}) );
//		buscadorSucursal.loadRecord( new Ext.util.Filter({property: 'cvePeriodo', value: options.model.get('cvePeriodo')}) );
//				
		me.actions.seleccionar.completeAction.call(me, completed, options);
    },
	
	dateValidate : function() {
		Ext.Msg.alert('AdmCtasWeb');
        var dateFld1 = Ext.getCmp('fechainicial');
        var dateFld2 = Ext.getCmp('fechaFinal');
        startValue = dateFld1.getValue();
        endValue = dateFld2.getValue();
       
        if (dateFld1 < dateFld2 ) {
        	me.down('#fechaFinal').setValue(null);
    		Ext.Msg.alert('AdmCtasWeb', 'Seleccione Fecha correcta');
//            dateFld2.setValue(null);
        }
    },
	
});

