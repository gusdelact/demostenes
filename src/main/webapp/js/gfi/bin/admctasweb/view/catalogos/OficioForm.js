Ext
		.define(
				'gfi.bin.admctasweb.view.catalogos.OficioForm',
				{
					extend : 'gfi.corp.component.form.BasicForm',
					alias : 'widget.oficioform',

					requires : [ 'gfi.corp.component.buscador.BuscadorTrigger' ],

					// Configuración del Componente.
					config : {
						header : 'Catálogo de Oficios',
						formType : gfi.FormTypes.CATALOGO,
						grupo : 'admctasweb',
						clave : 'oficios',
						modelClass : 'gfi.bin.admctasweb.model.catalogos.Oficio',
						primaryKeys : [ 'numOficio', 'tipoOficio' ],
						baseUrl : 'catalogos/oficio/',
						secured : false,
						pack : 'stretch',
						subFormConfig : {
							embedded : false,
							usePropertyName : false
						},
						userActions : [ {
							actionName : 'buscar',
							createButton : false
						} ]
					},

					// Construimos los items de la Forma
					buildItems : function() {
						return [
								{
									xtype : 'fieldset',
									title : 'Información de Estatus y Seguimiento',
									layout : 'hbox',
									collapsible : false,
									height : 47,
									width : 675,
									// disabled: true,
									items : [ {
										xtype : 'combobox',
										fieldLabel : 'Situación:',
										name : 'sitOficio',
										id : 'sitOficioOfi',
										store : Ext.create('Ext.data.Store', {
											fields : [ 'sit', 'sitname' ],
											data : [ {
												"sit" : "PE",
												"sitname" : "Pendiente"
											}, {
												"sit" : "TE",
												"sitname" : "Terminado"
											}, {
												"sit" : "EN",
												"sitname" : "Enviado"
											} ]
										}),
										displayField : 'sitname',
										valueField : 'sit',
										labelWidth : 60,
										width : 135,
										readOnly : true
									}, {
										xtype : 'tbspacer',
										width : 20
									}, {
										xtype : 'combobox',
										fieldLabel : 'Tipo Caso:',
										name : 'tipoCaso',
										id : 'tipoCasoOfi',
										store : Ext.create('Ext.data.Store', {
											fields : [ 'tip', 'tipCas' ],
											data : [ {
												"tip" : "PO",
												"tipCas" : "Positivo"
											}, {
												"tip" : "NE",
												"tipCas" : "Negativo"
											}, {
												"tip" : "",
												"tipCas" : "-"
											}, ]
										}),
										displayField : 'tipCas',
										valueField : 'tip',
										labelWidth : 60,
										width : 135,
										readOnly : true
									}, {
										xtype : 'tbspacer',
										width : 20
									}, {
										xtype : 'textfield',
										fieldLabel : 'Seguimiento:',
										name : 'descEstatus',
										labelWidth : 75,
										width : 340,
										readOnly : true
									}, {
										xtype : 'textfield',
										fieldLabel : 'cveSeguimiento:',
										name : 'cveEstatus',
										labelWidth : 50,
										width : 50,
										hidden : true,
										readOnly : true
									} ]
								},
								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'textfield',
												fieldLabel : 'Número de Oficio:',
												name : 'numOficio',
												labelWidth : 140,
												width : 320,
												validateBlank : true,
												formStatus : [ gfi.component.BasicForm.STATUS_NEW ]
											},
											{
												xtype : 'tbspacer',
												width : 20,
											},
											{
												xtype : 'radiogroup',
												id : "tipoOficioOfi",
												items : [ {
													boxLabel : 'Judicial',
													name : 'tipoOficio',
													inputValue : 'JU'
												}, {
													boxLabel : 'Hacendario',
													name : 'tipoOficio',
													inputValue : 'HA'
												}, {
													boxLabel : 'Aseguramiento',
													name : 'tipoOficio',
													inputValue : 'AS'
												}, {
													boxLabel : 'PLD',
													name : 'tipoOficio',
													inputValue : 'PL'
												} ],
												width : 500,
												formStatus : [ gfi.component.BasicForm.STATUS_NEW ]
											} ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Número de Folio:',
									name : 'numFolio',
									labelWidth : 140,
									width : 250,
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Número de Expediente:',
									name : 'numExped',
									labelWidth : 140,
									width : 250,
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Número de Registro:',
									name : 'numRegistro',
									labelWidth : 140,
									width : 250,
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ]
								},

								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'gfibuscadortrigger',
												id : 'busAutoridadReg',
												grupo : 'admctasweb',
												clave : 'autoridades',
												fieldLabel : 'Autoridad:',
												name : 'cveAutoridad',
												linkProperty : 'cveAutoridad',
												controls : [ {
													ref : 'txAutoridadOficioForm',
													selector : '#txAutoridadOficioForm',
													linkProperty : 'nomAutoridad'
												} ],
												labelWidth : 140,
												width : 250,
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											},
											{
												xtype : 'splitter'
											},
											{
												xtype : 'textfield',
												itemId : 'txAutoridadOficioForm',
												width : 405,
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											} ]
								},
								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'gfibuscadortrigger',
												id : 'busEmpresaReg',
												grupo : 'corp',
												clave : 'empresa',
												fieldLabel : 'Empresa Solicitada:',
												name : 'idEmpresa',
												linkProperty : 'idEmpresa',
												controls : [ {
													ref : 'txEmpresa',
													selector : '#txEmpresa',
													linkProperty : 'nombre'
												} ],
												labelWidth : 140,
												width : 250,
												defaultFilters : [ new Ext.util.Filter(
														{
															property : 'idEmpresa',
															operator : 'in',
															value : '1,2,83510'
														}) ],
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											},
											{
												xtype : 'splitter'
											},
											{
												xtype : 'textfield',
												itemId : 'txEmpresa',
												width : 405,
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											} ]
								},
								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'datefield',
												fieldLabel : 'Fecha Oficio:',
												name : 'fhOficio',
												labelWidth : 140,
												width : 250,
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											},
											{
												xtype : 'splitter'
											},
											{
												xtype : 'datefield',
												fieldLabel : 'Fecha Recepción:',
												name : 'fhRecepcion',
												labelWidth : 100,
												width : 210,
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											},
											{
												xtype : 'tbspacer',
												width : 85
											},
											{
												xtype : 'numberfield',
												fieldLabel : 'Dias Plazo:',
												name : 'numDiasPzo',
												width : 110,
												labelWidth : 60,
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											} ]
								},
								{
									xtype : 'gfibuscadortrigger',
									grupo : 'admctasweb',
									clave : 'dirSolicitantes',
									fieldLabel : 'Dirección Solicitante:',
									name : 'txDireccion',
									linkProperty : 'direccion',
									labelWidth : 140,
									width : 660,
									controls : [ {
										ref : 'gerencia',
										selector : '#txGer',
										linkProperty : 'gerencia',
									}, {
										ref : 'subgerencia',
										selector : '#txSub',
										linkProperty : 'subgerencia',
									}, {
										ref : 'nombreAtencion',
										selector : '#txAtNo',
										linkProperty : 'nombreAtencion',
									}, {
										ref : 'puestoAtencion',
										selector : '#txAtPu',
										linkProperty : 'puestoAtencion',
									} ],
									// Se sobreescribe evento del buscador para
									// setear como filtro al oficio
									onTriggerClick : function() {
										var me = this;

										var tipoOficio = Ext.getCmp(
												'tipoOficioOfi').getChecked()[0];

										if (tipoOficio == 'undefined'
												|| tipoOficio == null) {
											Ext.Msg
													.alert('AdmCtasWeb',
															'Seleccione un Tipo de Oficio');
										} else {
											var filtro = new Ext.util.Filter({
												property : 'tipoOficio',
												operator : 'like',
												value : tipoOficio.inputValue
											});
											me.setDefaultFilters(filtro);
											me.search();
										}
									},
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Gerencia Solicitante:',
									name : 'txGerencia',
									itemId : 'txGer',
									labelWidth : 140,
									size : 76,
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Subgerencia Solicitante:',
									name : 'txSubgeren',
									itemId : 'txSub',
									labelWidth : 140,
									size : 76,
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Atención a:',
									name : 'txAtnNom',
									itemId : 'txAtNo',
									labelWidth : 140,
									size : 76,
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Puesto:',
									name : 'txAtnPue',
									itemId : 'txAtPu',
									labelWidth : 140,
									size : 76,
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Folio Siara:',
									name : 'folioSiara',
									itemId : 'solicitudSiara',
									labelWidth : 140,
									size : 76,
									readOnly : true,
									editable : false,
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ]
								},
								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'gfibuscadortrigger',
												fieldLabel : 'Tipo Operación',
												id : 'busTipoOperacionReg',
												name : 'tipoOper',
												itemId : 'tipoOper',
												grupo : 'admctasweb',
												clave : 'mapeo',
												editable : false,
												labelWidth : 140,
												width : 250,
												maxLength : 3,
												maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
												defaultFilters : [ new Ext.util.Filter(
														{
															property : 'idCatalogo',
															operator : '=',
															value : '7'
														}) ],
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
												linkProperty : 'cveSiti',
												controls : [ {
													ref : 'txtipoOperOficioForm',
													selector : '#txtipoOperOficioForm',
													linkProperty : 'descripcion'
												} ]
											},
											{
												xtype : 'splitter'
											},
											{
												xtype : 'textfield',
												itemId : 'txtipoOperOficioForm',
												name : 'descTipoOperacion',
												listeners : {
													change : function(field,
															newValue, oldValue) {
														field.setValue(newValue
																.toUpperCase());
													}
												},
												width : 405
											} ],
								},
								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'gfibuscadortrigger',
												itemId : 'autoridad',
												grupo : 'admctasweb',
												clave : 'mapeo',
												fieldLabel : 'Autoridad Xml:',
												name : 'cveAutoridadXml',
												linkProperty : 'cveSiti',
												labelWidth : 140,
												width : 250,
												editable : false,
												maxLength : 15,
												maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres o digitos revasado.',
												defaultFilters : [ 
												        new Ext.util.Filter({
															property : 'idCatalogo',
															operator : '=',
															value : '10'
														}) 
												],
												controls : [ {
													ref : 'txAutoridadXmlOficioForm',
													selector : '#txAutoridadXmlOficioForm',
													linkProperty : 'descripcion'
												} ],

												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											},
											{
												xtype : 'splitter'
											},
											{
												xtype : 'textfield',
												itemId : 'txAutoridadXmlOficioForm',
												name : 'nombreAutXml',
												listeners : {
													change : function(field,
															newValue, oldValue) {
														field.setValue(newValue
																.toUpperCase());
													}
												},
												width : 405
											} ]
								},
								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'gfibuscadortrigger',
												fieldLabel : 'Caractér del Titular:',
												name : 'caracter',
												itemId : 'caracter',
												grupo : 'admctasweb',
												clave : 'mapeo',
												emptyText : 'Seleccionar',
												editable : false,
												maxLength : 13,
												maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres ó digitos revasado.',
												linkProperty : 'cveSiti',
												controls : [ {
													ref : 'descCaracter',
													selector : '#descCaracter',
													linkProperty : 'descripcion'
												} ],
												labelWidth : 140,
												width : 250,
												defaultFilters : [ new Ext.util.Filter(
														{
															property : 'idCatalogo',
															operator : '=',
															value : '9'
														}) ],
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											},
											{
												xtype : 'splitter'
											},
											{
												xtype : 'textfield',
												itemId : 'descCaracter',
												name : 'descCaracter',
												emptyText : 'Debe Seleccionar clave de caracter CNBV del Titular',
												width : 405,
												disabled : false,
												readOnly : true
											} ]
								},
								
//								{
//									xtype : 'textfield',
//									fieldLabel : 'Monto Inicial $',
//									name : 'montoInicial',
//									itemId : 'montoInicial',
//									maskRe : /[0-9]/,
//									labelWidth : 140,
//									maxLength : 25,
//									maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
//									msgTarget : 'under',
//									formStatus : [
//											gfi.component.BasicForm.STATUS_NEW,
//											gfi.component.BasicForm.STATUS_UPDATE ],
//									width : 660
//								},
								
								{
									xtype : 'textfield',
									fieldLabel : 'Monto Requerido $',
									name : 'montoRequerido',
									itemId : 'montoRequerido',
									maskRe : /[0-9]/,
									labelWidth : 140,
									maxLength : 25,
									maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
									msgTarget : 'under',
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ],
									width : 660
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Número Oficio Requerido Operacíon',
									name : 'referencia2',
									itemId : 'numOfiRequeridoOperacion',
									labelWidth : 140,
									maxLength : 25,
									maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
									msgTarget : 'under',
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ],
									width : 660
								},

								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'datefield',
												fieldLabel : 'Fecha Requerimiento:',
												name : 'fRequerimiento',
												format : 'Ymd',
												labelWidth : 140,
												width : 250,
												readOnly : true,
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											},
											{
												xtype : 'splitter'
											},
											{
												xtype : 'datefield',
												fieldLabel : 'Fecha Aplicación:',
												name : 'fAplicacion',
												format : 'Ymd',
												labelWidth : 100,
												width : 210,
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ]
											} ]
								},
								{
									xtype : 'textfield',
									fieldLabel : 'Monto Operación $',
									name : 'montoSolicitado',
									itemId : 'montoSolicitado',
									id : 'montoSolicitado',
									maskRe : /[0-9]/,
									labelWidth : 140,
									maxLength : 25,
									maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
									msgTarget : 'under',
									formStatus : [
											gfi.component.BasicForm.STATUS_NEW,
											gfi.component.BasicForm.STATUS_UPDATE ],
									width : 660
								},
								{
									xtype : 'fieldcontainer',
									layout : 'hbox',
									items : [
											{
												xtype : 'gfibuscadortrigger',
												fieldLabel : 'Moneda Operación',
												name : 'monedaRequerida',
												// id :
												// 'descMonedaRequerimiento',
												itemId : 'descMonedaRequerimiento',
												linkProperty : 'descripcion',
												grupo : 'admctasweb',
												clave : 'mapeo',
												editable : false,
												labelWidth : 140,
												width : 250,
												defaultFilters : [ new Ext.util.Filter(
														{
															property : 'idCatalogo',
															operator : '=',
															value : '6'
														}) ],
												formStatus : [
														gfi.component.BasicForm.STATUS_NEW,
														gfi.component.BasicForm.STATUS_UPDATE ],
												linkProperty : 'cveSiti',
												controls : [ {
													ref : 'txMonedaform',
													selector : '#txMonedaform',
													linkProperty : 'descripcion'
												} ]
											}, {
												xtype : 'splitter'
											}, {
												xtype : 'textfield',
												itemId : 'txMonedaform',
												name : 'descMoneda',
												width : 405,
											// formStatus :
											// [gfi.component.BasicForm.STATUS_NEW,
											// gfi.component.BasicForm.STATUS_UPDATE]
											} ],
								}
//								,
//								{
//									xtype : 'textfield',
//									fieldLabel : 'Saldo después Oper $',
//									name : 'saldoDespuesOper',
//									itemId : 'saldoOper',
//									maskRe : /[0-9]/,
//									labelWidth : 140,
//									maxLength : 25,
//									readOnly : true,
//									maxLengthText : 'M\u00E1ximo n\u00FAmero de caracteres revasado.',
//									msgTarget : 'under',
//									formStatus : [
//											gfi.component.BasicForm.STATUS_NEW,
//											gfi.component.BasicForm.STATUS_UPDATE ],
//									width : 660
//								} 
								
								];
					},

					// Se sobreescriben las Acciones de la Forma.

					// Completar Selección.
					completeSeleccionar : function(completed, options) {
						var me = this;
						var buscadorAut = Ext.getCmp('busAutoridadReg');
						var buscadorEmp = Ext.getCmp('busEmpresaReg');

						buscadorAut.loadRecord(new Ext.util.Filter({
							property : 'cveAutoridad',
							value : options.model.get('cveAutoridad')
						}));
						buscadorEmp.loadRecord(new Ext.util.Filter({
							property : 'idEmpresa',
							value : options.model.get('idEmpresa')
						}));

						me.actions.seleccionar.completeAction.call(me,
								completed, options);
					},

					// Editar - Validamos Estatus del Oficio.
					doEditar : function(options) {
						var me = this;
						var sitOficio = Ext.getCmp('sitOficioOfi').getValue();

						if (sitOficio == 'PE') {
							var completed = me.actions.editar.doAction.call(me,
									options);
							me.actions.editar.completeAction.call(me,
									completed, options);
						} else {
							options.message = 'Solo puede Modificar Oficios en Estatus PENDIENTE';
							Ext.Msg.show({
								title : 'AdmCtasWeb',
								msg : options.message,
								buttons : Ext.Msg.OK,
								icon : Ext.Msg.WARNING,
								modal : true
							});
						}
					}

				});
