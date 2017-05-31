Ext.define('gfi.bin.admctasweb.view.catalogos.DestinatarioView', {
	extend : 'gfi.corp.component.form.BasicForm',
	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],
	
	
	
	config : {
		header: 'Catálogo de Destinatario',
		formType: gfi.FormTypes.CATALOGO,
		grupo: 'admctasweb', 
		clave: 'destinatarios',
		modelClass : 'gfi.bin.admctasweb.model.catalogos.Destinatario',
		primaryKeys : ['idDestinatario'],
		baseUrl: 'catalogos/destinatario/',
		secured: false,
		
		userActions: {
			borrar: {//Se sobreescribe acción para ocultar el botón
				createButton: false,
			},
		},
		
	},
	
	getMinWidth : function() {
		return 600;
	},

	getWidth : function() {
		return 600;
	},
	
	/**
	 * Se sobreescribe evento de Seleccionar para obtener Descripción del estatus uo evento de notificación.
	 */
	completeSeleccionar: function (completed, options){
		var me = this;	
		
		var buscadorEventos = Ext.getCmp('DestBuscadorEventos');		
		buscadorEventos.loadRecord(new Ext.util.Filter({property: 'cveEstatus', value: options.model.get('cveEstatus')}));
		
		me.actions.seleccionar.completeAction.call(me, completed, options);
    },
		
	buildItems : function() {		
		return [ 
				{
					xtype : 'hiddenfield',
					fieldLabel : 'Id Destinatario:',
					name : 'idDestinatario',
					labelWidth : 140,
					size : 40,
					formStatus : [ gfi.component.BasicForm.STATUS_NEW,
							gfi.component.BasicForm.STATUS_UPDATE ]
				},

				{
					xtype : 'fieldcontainer',
					layout : 'hbox',
					items : [
							{
								xtype : 'gfibuscadortrigger',
								grupo : 'admctasweb',
								clave : 'estatusSeg',
								id : 'DestBuscadorEventos',
								fieldLabel : 'Evento:',
								name : 'cveEstatus',
								msgTarget : 'side',	
								linkProperty : 'cveEstatus',
								controls : [ {
									ref : 'txEstatus',
									selector : '#txEstatus',
									linkProperty : 'descripcion'
								} ],
								labelWidth : 140,
								width : 220,
								formStatus : [
										gfi.component.BasicForm.STATUS_NEW,
										gfi.component.BasicForm.STATUS_UPDATE ],
										
							},
							{
								xtype: 'tbspacer',
								width : 5
							}, 
							{
								xtype : 'textfield',
								name : 'descEstatus',
								itemId : 'txEstatus',
								msgTarget : 'under',	
								width : 325,
								formStatus : [
										gfi.component.BasicForm.STATUS_NEW,
										gfi.component.BasicForm.STATUS_UPDATE ]
							} ]
				},

				{
					xtype : 'textfield',
					fieldLabel : 'Nombre Destinatario:',
					name : 'nombre',
					msgTarget : 'under',
					labelWidth : 140,
					maxLength : 120, 
					maxLengthText : 'La longitud máxima para el Nombre es de {0}',
					width : 550,
					formStatus : [ gfi.component.BasicForm.STATUS_NEW,
							gfi.component.BasicForm.STATUS_UPDATE ]
				},

				{
					xtype : 'textfield',
					fieldLabel : 'Correo:',
					name : 'correo',
					msgTarget : 'under',
					labelWidth : 140,
					maxLength : 80,
					maxLengthText : 'La longitud máxima para el Correo es de {0}',
					width : 550,
					formStatus : [ gfi.component.BasicForm.STATUS_NEW,
							gfi.component.BasicForm.STATUS_UPDATE ]
				},

				{
					xtype : 'combobox',
					fieldLabel : 'Situación:',
					emptyText: 'Seleccione',
					editable : false,
					name : 'situacion',
					msgTarget : 'under',
					store : Ext.create('Ext.data.Store', {
						fields : [ 'sit', 'sitname' ],
						data : [ {
							"sit" : "AC",
							"sitname" : "Activo"
						}, {
							"sit" : "IN",
							"sitname" : "Inactivo"
						}
						//...
						]
					}),
					displayField : 'sitname',
					valueField : 'sit',
					labelWidth : 140,
					width : 550,
					formStatus : [ gfi.component.BasicForm.STATUS_NEW,
							gfi.component.BasicForm.STATUS_UPDATE ]
				}, 
				
				
				
				{
					xtype : 'fieldset',
					title : 'Fechas de Control',
					collapsible : true,
					collapsed : true,
					items : [ {
						xtype : 'fieldcontainer',
						layout : 'hbox',
						items : [ {
							xtype : 'datefield',
							fieldLabel : 'Fecha de Creaci\u00f3n:',
							name : 'fhAlta',
							labelWidth : 115,
							width : 200,
							format : 'd/m/Y',
							//disabled : true,
							readOnly : true
						}, 							
						{
							xtype: 'tbspacer',
							width : 5
						}, 
						{
							xtype : 'datefield',
							fieldLabel : 'Fecha de Modificaci\u00f3n:',
							name : 'fhUltMod',
							labelWidth : 130,
							width : 215,
							format : 'd/m/Y',
							//disabled : true,
							readOnly : true
						} ]
					},
					{
						xtype : 'fieldcontainer',
						layout : 'hbox',
						items : [ , {
							xtype : 'textfield',
							fieldLabel : 'Usuario de Creaci\u00f3n:',
							name : 'cveUsuAlta',
							labelWidth : 115,
							width : 200,
							//disabled : true,
							readOnly : true
						},{
							xtype: 'tbspacer',
							width : 5
						}, 
						{
							xtype : 'textfield',
							fieldLabel : 'Usuario de Modificaci\u00f3n:',
							name : 'cveUsuMod',
							labelWidth : 130,
							width : 215,
							//disabled : true,
							readOnly : true
						} ]
					} ]
				}
		];
	}
});

