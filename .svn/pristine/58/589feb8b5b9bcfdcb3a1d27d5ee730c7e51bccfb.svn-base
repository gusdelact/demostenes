/* 
 @autor Oscar Fabian Cobos Ortiz
 */
Ext.define(
				'gfi.bin.admctasweb.view.reportes.R29.IntentosRealizadosGrid',
				{
					extend : 'Ext.grid.Panel',
					requires: [
					             'Ext.selection.CheckboxModel'
					],
					alias : 'widget.intentosRealizadosGrid',
					iconCls : 'icon-cuentasAsociadas',
					id : 'intentosRealizadosGrid',
					height : 200,
					loadMask : true,
					title : 'Registro de Intentos Realizados',
					store: 'gfi.bin.admctasweb.store.reportes.R29.StoreIntentosRealizadosGrid',
					stripeRows : true,
					multiSelect : false,
					enableColumnHide : false,
					plugins: [
					           Ext.create('Ext.grid.plugin.CellEditing', {
					               clicksToEdit: 1
					           })
					       ],
					columns : [
							{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Usuario Alta</span>",
								width : 250,
								align : 'center',
								dataIndex : 'cveUsuAlta',
								draggable : true,
								resizable : true,
								hidden : false 
							},
							{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Empresa</span>",
								width : 260,
								align : 'center',
								dataIndex : 'idEmpresa',
								draggable : true,
								resizable : true,
								hidden : false,
					            renderer : function(value){
					            	if(value == 1){
					            	
					            		return 'INTERACCIONES CASA DE BOLSA'
					            		
					            	}else{
					            		
					            		return 'BANCO INTERACCIONES'
					            	}
					            	
					                
					            }
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Periodo</span>",
								width : 180,
								align : 'center',
								dataIndex : 'cvePeriodo',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">No. Intento</span>",
								width : 180,
								align : 'center',
								dataIndex : 'numIntento',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Folio Grupo</span>",
								width : 105,
								align : 'center',
								dataIndex : 'idGrupo',
								draggable : true,
								resizable : true,
								hidden : false,
								flex : 1
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Estatus Periodo</span>",
								width : 105,
								align : 'center',
								dataIndex : 'sitPeriodo',
								draggable : true,
								resizable : true,
								hidden : false,
								flex : 1
							}],

					initComponent : function() {

						this.dockedItems = [ {
						 xtype: 'pagingtoolbar',
						 id: 'pagingtoolbarGridIntentosRealizados',
						 dock:'bottom',
						 store:'gfi.bin.admctasweb.store.reportes.R29.StoreIntentosRealizadosGrid',
						 displayInfo: true,
						 displayMsg: 'Registro {0} - {1} of {2}',
						 emptyMsg: "No existen registros de acuerdo a los parametros seleccionados"
						} ];

						this.callParent(arguments);
					}
				});
