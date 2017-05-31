/* 
 @autor Oscar Fabian Cobos Ortiz
 */
Ext.define(
				'gfi.bin.admctasweb.view.reportes.R29.BitacoraExtraccionGrid',
				{
					extend : 'Ext.grid.Panel',
					requires: [
					             'Ext.selection.CheckboxModel'
					],
					alias : 'widget.bitacoraExtraccionGrid',
					iconCls : 'icon-cuentasAsociadas',
					id : 'bitacoraExtraccionGrid',
					height : 200,
					loadMask : true,
					title : 'Registros',
					store : 'gfi.bin.admctasweb.store.reportes.R29.StoreBitacoraExtraccionGrid',
					stripeRows : true,
					multiSelect : false,
					enableColumnHide : false,
					plugins: [
					           Ext.create('Ext.grid.plugin.CellEditing', {
					               clicksToEdit: 1
					           })
					       ],
					columns : [{
			            xtype: 'rownumberer'
			        },
							{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Reporte</span>",
								width : 105,
								align : 'center',
								dataIndex : 'reporte',
								draggable : true,
								resizable : true,
								hidden : false
							},
							{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Medio Solicitud</span>",
								width : 105,
								align : 'center',
								dataIndex : 'medioSol',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Autoridad</span>",
								width : 105,
								align : 'center',
								dataIndex : 'autoridad',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Descripción</span>",
								width : 105,
								align : 'center',
								dataIndex : 'descripcion',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">No. Oficio</span>",
								width : 180,
								align : 'center',
								dataIndex : 'numOficio',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Fecha Solicitud</span>",
								width : 105,
								align : 'center',
								dataIndex : 'fechaSolicitud',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Folio Siara</span>",
								width : 180,
								align : 'center',
								dataIndex : 'folioSiara',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Monto Solicitado</span>",
								width : 105,
								align : 'center',
								dataIndex : 'montoSolicitado',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Persona Titular</span>",
								width : 105,
								align : 'center',
								dataIndex : 'persoTitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Carácter de Titular</span>",
								width : 180,
								align : 'center',
								dataIndex : 'caracTitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Rfc Titular</span>",
								width : 105,
								align : 'center',
								dataIndex : 'rfcTitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Razon Titular</span>",
								width : 200,
								align : 'center',
								dataIndex : 'razonTitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Nombre Titular</span>",
								width : 130,
								align : 'center',
								dataIndex : 'nombreTitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Ape. Paterno Titular</span>",
								width : 130,
								align : 'center',
								dataIndex : 'paternoTitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Ape. Materno Titular</span>",
								width : 130,
								align : 'center',
								dataIndex : 'maternoTitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Persona Cotitular</span>",
								width : 105,
								align : 'center',
								dataIndex : 'persoCotitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:red;font-weight:bold;font-size:11px;\">Carácter de Cotitular</span>",
								width : 160,
								align : 'center',
								dataIndex : 'caracCotitular',
								draggable : true,
								resizable : true,
								hidden : false,
								editor: {
                                        xtype:'textfield',
                                        id: 'txtEditableCaracCoti',
                                        maxLength : 13,
                                        allowBlank:false,					                
                                        listeners:{
                                                    blur: function(){
                                                               var valorCampoCaracteristicasCotitular = Ext.getCmp('txtEditableCaracCoti').getValue();

                                                               var idEmpresa   = Ext.getCmp('hiddenTxtIdEmpresaBitacoraExtraccion').getValue();
                                                               var cvePeriodo  = Ext.getCmp('hiddenTxtCvePeriodoBitacoraExtraccion').getValue();
                                                               var numIntento  = Ext.getCmp('hiddenTxtNumIntentoBitacoraExtraccion').getValue();
                                                               var numOficio   = Ext.getCmp('hiddenTxtNumOficioBitacoraExtraccion').getValue();
                                                               var tipoOficio  = Ext.getCmp('hiddenTxtTipoOficioBitacoraExtraccion').getValue();
                                                               var idCotitular = Ext.getCmp('hiddenTxtIdCotitularBitacoraExtraccion').getValue();
                                                               var idCuenta    = Ext.getCmp('hiddenTxtIdCuentaBitacoraExtraccion').getValue();
                                                               

                                                               if(valorCampoCaracteristicasCotitular == ""){
                                                                       Ext.Msg.alert('INFO','<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">El campo Caracteristicas Cotitular ha quedado vacio, favor de registrar la información.</span>');                                                       	   
                                                               }else{
                                                                       /*Confirmamos proceso de salvado*/
                                                                       Ext.Msg.show({
                                                                       title: 'Advertencia',
                                                                       msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;\">Se procederá a la actualizacion del campo Caracteristicas Cotitular del registro seleccionado con el valor de <span style=\"color:red;font-weight:bold;font-size:12px;\">'+valorCampoCaracteristicasCotitular+'</span>.<br>¿Desea continuar con el proceso?</span>',
                                                                       width: 400,
                                                                       closable: false,
                                                                       buttons: Ext.Msg.YESNO,
                                                                       buttonText:
                                                                               {
                                                                                   yes: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Si</span>',
                                                                                   no: '<span style=\"color:#190707;font-weight:bold;font-size:11px;\">No</span>'
                                                                               },
                                                                       multiline: false,
                                                                       fn: function (btnText) {
                                                                           if (btnText === "yes") {  

                                                                                                    Ext.Ajax.request({
                                                                                                            url : 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/actualizaRegistroCaracteristicasCotitular.htm',
                                                                                                            loadMask : true,
                                                                                                            scope : this,
                                                                                                            success : function(response) {
                                                                                                            	
                                                                                                             var objResponse = Ext.JSON.decode(response.responseText);
                   																							 
                   																							 Ext.Msg.show({
                   										                                                        title: 'INFO',
                   										                                                        msg: '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">' + objResponse.messages[1]+ '',
                   										                                                        buttons: Ext.Msg.OK,
                   										                                                        icon: Ext.Msg.INFO
                   										                                                    });
                                                                                                            	
                                                                                                            	

                                                                                                            },
                                                                                                            failure : function(response) {
                                                                                                                    Ext.Msg
                                                                                                                                    .alert(
                                                                                                                                                    'ERROR',
                                                                                                                                                    '<span style=\"color:#190707;font-weight:bold;font-size:9px;text-align:center\">Fallo en al petición de actualización del registro</span>'
                                                                                                                                                                    + response.responseText);
                                                                                                            },
                                                                                                            params : {
                                                                                                                    valorCampoCaracteristicasCotitular : valorCampoCaracteristicasCotitular,
                                                                                                                    idEmpresa   : idEmpresa,
                                                                                                                    cvePeriodo  : cvePeriodo,
                                                                                                                    numIntento  : numIntento,
                                                                                                                    numOficio   : numOficio,
                                                                                                                    tipoOficio  : tipoOficio,
                                                                                                                    idCotitular : idCotitular,
                                                                                                                    idCuenta    :  idCuenta
                                                                                                            }
                                                                                                    });


                                                                           }
                                                                           else if (btnText === "no") {
                                                                               /*Cierra pantalla de aviso*/

                                                                           }
                                                                       },
                                                                       icon: Ext.Msg.QUESTION
                                                                   });

                                                               }
                                                      }
                                             }
                                    }
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Rfc Cotitular</span>",
								width : 105,
								align : 'center',
								dataIndex : 'rfcCotitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Razon Cotitular</span>",
								width : 105,
								align : 'center',
								dataIndex : 'razonCotitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Nombre Cotitular</span>",
								width : 130,
								align : 'center',
								dataIndex : 'nombreCotitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Ape. Paterno Cotitular</span>",
								width : 180,
								align : 'center',
								dataIndex : 'paternoCotitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Ape. Materno Cotitular</span>",
								width : 180,
								align : 'center',
								dataIndex : 'maternoCotitular',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Id Sucursal</span>",
								width : 105,
								align : 'center',
								dataIndex : 'idSucursal',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Estado</span>",
								width : 105,
								align : 'center',
								dataIndex : 'estado',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Localidad</span>",
								width : 105,
								align : 'center',
								dataIndex : 'localidad',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Codigo Postal</span>",
								width : 105,
								align : 'center',
								dataIndex : 'codigoPostal',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Modalidad</span>",
								width : 105,
								align : 'center',
								dataIndex : 'modalidad',
								draggable : true,
								resizable : true,
								hidden : false
							},
//							,{
//								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Monto Solicitado</span>",
//								width : 105,
//								align : 'center',
//								dataIndex : 'MONTO_SOLICITADO',
//								draggable : true,
//								resizable : true,
//								hidden : false
//							},
							
							{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Tipo Nivel</span>",
								width : 105,
								align : 'center',
								dataIndex : 'tipoNivel',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Id Cuenta</span>",
								width : 105,
								align : 'center',
								dataIndex : 'idCuenta',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Producto</span>",
								width : 105,
								align : 'center',
								dataIndex : 'producto',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Moneda Cuenta</span>",
								width : 105,
								align : 'center',
								dataIndex : 'monedaCuenta',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Monto Inicial</span>",
								width : 105,
								align : 'center',
								dataIndex : 'montoInicial',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Operación</span>",
								width : 105,
								align : 'center',
								dataIndex : 'operacion',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Oficio Req. Operación</span>",
								width : 200,
								align : 'center',
								dataIndex : 'oficioReqOpe',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Fecha Requerimiento</span>",
								width : 130,
								align : 'center',
								dataIndex : 'fRequerimiento',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Siara Indirecto</span>",
								width : 160,
								align : 'center',
								dataIndex : 'siaraIndirecto',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Fecha Aplicación</span>",
								width : 105,
								align : 'center',
								dataIndex : 'fAplicacion',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Monto de Operación</span>",
								width : 130,
								align : 'center',
								dataIndex : 'montoAplicacion',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Moneda Operación</span>",
								width : 130,
								align : 'center',
								dataIndex : 'monedaOperacion',
								draggable : true,
								resizable : true,
								hidden : false
							},{
								header : "<span style=\"color:#190707;font-weight:bold;font-size:11px;\">Saldo de Operación</span>",
								width : 150,
								align : 'center',
								dataIndex : 'saldoOperacion',
								draggable : true,
								resizable : true,
								hidden : false
							}],

					initComponent : function() {

						this.dockedItems = [ {
						// xtype: 'pagingtoolbar',
						// id: 'pagingtoolbarGridIntentosRealizados',
						// dock:'bottom',
						// store:
						// 'gfi.fundacion.reconocimientos.store.empleado.StoreEmpleado',
						// displayInfo: true,
						// displayMsg: 'Registro {0} - {1} of {2}',
						// emptyMsg: "No existen registros para mostrar"
						} ];

						this.callParent(arguments);
					}
				});
