Ext.define('gfi.bin.admctasweb.view.reporte_r29.MapeoView', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.mapeoview',
	
	requires : ['gfi.bin.admctasweb.model.r29.Mapeo', 'gfi.corp.component.buscador.BuscadorTrigger'] ,
	
	//Configuración del Componente.
	config : {
		header: 'Consulta de Mapeo',
		formType: gfi.corp.component.form.Types.CATALOGO,
		grupo: 'admctasweb', 
		clave: 'mapeo',
		modelClass : 'gfi.bin.admctasweb.model.r29.Mapeo',
		primaryKeys : ['idCatalogo','cveCorporativa'],
		baseUrl: 'reporte_r29/mapeo/',
		secured: false
	},

	getWidth	: function() {
		return 850;
	},

	
	//Construimos los items de la Forma
	buildItems : function() {
		return [{
			 xtype: 'fieldcontainer',
		     layout: 'hbox',
			 items : [{
		        	xtype: 'gfibuscadortrigger',
					fieldLabel : 'Catalogo:',
					itemId: 'idCatalogo',
					name : 'idCatalogo',
					grupo: 'admctasweb', 
		        	clave: 'conver',
					editable : true,
					listeners:{
		                change: function(field, newValue, oldValue){
		                    field.setValue(newValue.toUpperCase());
		                }
		             },
		        	linkProperty: 'idCatalogo',
		        	controls: [{ 
		        		ref: 'descripcionAutoridadBuscador', 
		        		selector: '#descripcionAutoridadBuscador',
		        		linkProperty: 'descripcion'
		        	}
		        	],
					labelWidth : 140,
					width : 320,
					validateBlank : true,
					maxLength 	: 2,
					maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasados.',
			           formStatus : [gfi.component.BasicForm.STATUS_NEW,
			                         gfi.component.BasicForm.STATUS_UPDATE]
		        },
				{
					xtype : 'splitter'
				},
				{
					xtype : 'textfield',
					itemId : 'descripcionAutoridadBuscador',
					name : 'descripcion',
					width : 405,
					formStatus : [
							gfi.component.BasicForm.STATUS_NEW,
							gfi.component.BasicForm.STATUS_UPDATE ]
				}]
			}, {
			xtype : 'textfield',
			fieldLabel : 'Corporativa:',
			name : 'cveCorporativa',
			labelWidth: 140,
			width : 320,
			listeners:{
                change: function(field, newValue, oldValue){
                    field.setValue(newValue.toUpperCase());
                }
             },
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}, {
			xtype : 'textfield',
			fieldLabel : 'Siti:',
			name : 'cveSiti',
			labelWidth: 140, 
			width : 320,
			listeners:{
                change: function(field, newValue, oldValue){
                    field.setValue(newValue.toUpperCase());
                }
             },
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		}		
//		,
//		{
//			xtype : 'textfield',
//			fieldLabel : 'Descripción:',
//			name : 'descripcion',
//			labelWidth: 140,
//			listeners:{
//                change: function(field, newValue, oldValue){
//                    field.setValue(newValue.toUpperCase());
//                }
//             },
//			width : 660,
//			formStatus : [gfi.component.BasicForm.STATUS_NEW,
//			              gfi.component.BasicForm.STATUS_UPDATE]
//		//}]
//	   }
     ];
	},
	
	//Se sobreescriben las Acciones de la Forma.
	
	//Completar Selección.
	completeSeleccionar: function (completed, options) {

		var me = this;
//		var buscadorAut = Ext.getCmp('#idCatalogo');
//		
//		buscadorAut.loadRecord( new Ext.util.Filter({property: 'idCatalogo', value: options.model.get('idCatalogo')}) );
//		
		me.actions.seleccionar.completeAction.call(me, completed, options);
    }
		
});

