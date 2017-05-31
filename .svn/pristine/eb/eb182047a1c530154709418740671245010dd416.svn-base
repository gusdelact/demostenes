Ext.define('gfi.bin.admctasweb.view.reporte_r29.SucursalView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.sucursalview',
	
//	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],
	requires : ['gfi.bin.admctasweb.model.r29.Sucursal', 'gfi.corp.component.buscador.BuscadorTrigger'] ,
	
	//Configuración del Componente.
	config : {
		header: 'Consulta de Sucursal',
		formType: gfi.corp.component.form.Types.CATALOGO,
		grupo: 'admctasweb', 
		clave: 'sucursal',
		modelClass : 'gfi.bin.admctasweb.model.r29.Sucursal',
		primaryKeys : ['idEmpresa','idSucursal'],
		baseUrl: 'reporte_r29/sucursal/',
		secured: false
	},

	getWidth	: function() {
		return 850;
	},

	
	//Construimos los items de la Forma
	buildItems : function() {
		return [{ 
    		xtype: 'container',
    		layout: 'hbox',
    		items: [
    		        { 
    		        	xtype: 'gfibuscadortrigger',
    		        	fieldLabel : 'Empresa:',
    		        	name: 'idEmpresa',
    		        	itemId : 'idEmpresa',
    		        	grupo: 'admctasweb', 
    		        	clave: 'mapeo',
    		        	editable : false,
    		        	linkProperty: 'cveCorporativa',
    		        	controls: [{ 
    		        		ref: 'txInstitucion', 
    		        		selector: '#txInstitucion',
    		        		linkProperty: 'descripcion'
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
    		           formStatus : [gfi.component.BasicForm.STATUS_NEW,
    		                         gfi.component.BasicForm.STATUS_UPDATE],
    		        },{
    		        	xtype: 'splitter'
    		        }, {
    		        	xtype : 'textfield',
    		        	itemId : 'txInstitucion',
    		        	name : 'txInstitucion',        		           	
    		        	width : 405,
    		        	readOnly: true,
    		        	editable: false,
//    		        	formStatus : [gfi.component.BasicForm.STATUS_NEW],
    		        }] 
    		},{
      	 		xtype: 'splitter'
      	 	},{
	        	 xtype: 'fieldcontainer',
  	        	 layout: 'hbox',
  	        	 items: [{
  	        		 xtype: 'textfield',  
//  	        	 xtype: 'gfibuscadortrigger', 
  	        		 fieldLabel : 'Sucursal',
  	        		 name : 'idSucursal',
  	        		 itemId : 'sucursal',
  	        		// linkProperty : 'nomSucursal',
  	        		 grupo: 'admctasweb', 
  	        		 clave: 'sucursal',
  	        		 editable : false,
  	        		 labelWidth : 140,													
  	        		 width : 250,
  	        		 maxLength 	: 30,
  	        		 maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
	                 formStatus : [gfi.component.BasicForm.STATUS_NEW,
	                               gfi.component.BasicForm.STATUS_UPDATE],
	                 linkProperty: 'idSucursal',
	                 controls: [{
	                	 ref: 'nomSucursal', 
	                	 selector: '#nomSucursal',
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
  	        		 itemId : 'nomSucursal',
  	        		 name : 'nomSucursal',
  	        		listeners:{
  	                  change: function(field, newValue, oldValue){
  	                      field.setValue(newValue.toUpperCase());
  	                  }
  	               },
  	        		 width : 405,    			
						 formStatus : [gfi.component.BasicForm.STATUS_NEW,
									   gfi.component.BasicForm.STATUS_UPDATE]
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
//  	        	 		readOnly: true,
  	        	 		width : 250,
  	        	 		formStatus : [gfi.component.BasicForm.STATUS_NEW,
  	        		                               gfi.component.BasicForm.STATUS_UPDATE],
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
  	        	 		formStatus : [gfi.component.BasicForm.STATUS_NEW,
  		                               gfi.component.BasicForm.STATUS_UPDATE],
//  	        	 		readOnly: true,
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
  	        	 		width : 175,
//							labelWidth: 140,
//							width : 140,
//  	        	 		readOnly: true,
  	        	 		formStatus : [gfi.component.BasicForm.STATUS_NEW,
  		                               gfi.component.BasicForm.STATUS_UPDATE],
  	        	 		msgTarget: 'side'	
  	        	 	}],
  	         }	
		];
	},
	
	//Se sobreescriben las Acciones de la Forma.
	
	//Completar Selección.
	completeSeleccionar: function (completed, options) {
	
		var me = this;
//		var buscadorEmpresa = me.down('#idCatalogo'); 
//		var buscadorSucursal = me.down('#sucursal'); 
//		
//		buscadorEmpresa.loadRecord( new Ext.util.Filter({property: 'cveCorporativa', value: options.model.get('institucion')}) );
//		buscadorSucursal.loadRecord( new Ext.util.Filter({property: 'idSucursal', value: options.model.get('idSucursal')}) );
				
		me.actions.seleccionar.completeAction.call(me, completed, options);
    }
	
});

