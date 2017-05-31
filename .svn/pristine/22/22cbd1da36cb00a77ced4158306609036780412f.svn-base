Ext.define('gfi.icb.store.tmpMenuStore', {
	extend : 'Ext.data.TreeStore',
	requires : ['gfi.corp.component.menu.model.DynamicMenuModel'],
	model : 'gfi.corp.component.menu.model.DynamicMenuModel',
    root: {
    	text : 'Administración de Cuentas Web',
        expanded: true,
        children: [
            { id: 1, text: "Cat\u00e1logos", expanded: true, children: [
                                                                        
                    { id:11, text: "Oficio", leaf: true , viewClass : "gfi.bin.admctasweb.view.catalogos.OficioView", type:'class'},
                    { id:12, text: "Persona", leaf: true , viewClass : "gfi.bin.admctasweb.view.catalogos.PersonaView", type:'class'},
                    { id:13, text: "Documento", leaf: true , viewClass : "gfi.bin.admctasweb.view.catalogos.DocumentoView", type:'class'},
                    { id:14, text: "Destinatario", leaf: true , viewClass : "gfi.bin.admctasweb.view.catalogos.DestinatarioView", type:'class'},
                    { id:15, text: "Direcciones Solicitantes", leaf: true , viewClass : "gfi.bin.admctasweb.view.catalogos.DireccionesSolicitantesView", type:'class'},
                    { id:16, text: "Autoridades", leaf: true , viewClass : "gfi.bin.admctasweb.view.catalogos.AutoridadesView", type:'class'},
                    { id:17, text: "Par\u00e1metros CNBV", leaf: true , viewClass : "gfi.bin.admctasweb.view.catalogos.ParametrosView", type:'class'},
                    { id:18, text: "Configuración de % de similitud", leaf: true , viewClass : "gfi.bin.admctasweb.view.catalogos.SimilaridadView", type:'class'},
                    { id:19, text: "Exclusiones en nombres de contrato", leaf: true , viewClass : "gfi.bin.admctasweb.view.operativos.ExclusionesView", type:'class'}
                    ]
            },
            { id: 2, text: "Procesos Operativos", expanded: true, children: [
                    { id:21, text: "Proceso de Registro", leaf: true , viewClass : "gfi.bin.admctasweb.view.operativos.RegistroForm", type:'class'},
                    { id:22, text: "Generación de Respuestas para la CNBV", leaf: true , viewClass : "gfi.bin.admctasweb.view.operativos.GeneracionRespuestasView", type:'class'},
                    { id:23, text: "Registro de Dictamen Jurídico", leaf: true , viewClass : "gfi.bin.admctasweb.view.operativos.RegistroDictamenJurView", type:'class'},
                    { id:24, text: "Impresión Dictamen Juridico", leaf: true , viewClass : "gfi.bin.admctasweb.view.operativos.ImpresionDictamenJurView", type:'class'},
                    { id:25, text: "Registro de Observaciones e Impresión de Respuestas", leaf: true , viewClass : "gfi.bin.admctasweb.view.operativos.RespuestasOficiosView", type:'class'},                   
                    { id:26, text: "Archivo para la CNBV", leaf: true , viewClass : "gfi.bin.admctasweb.view.operativos.ArchivoCnbvView", type:'class'},
                    { id:27, text: "Oficios con respuestas negativas", leaf: true , viewClass : "gfi.bin.admctasweb.view.operativos.OficiosRespuestasNegativasView", type:'class'}
                    ] 
            },
            { id: 3, text: "Reportes", expanded: true, children: [
                    { id:31, text: "Bit\u00e1cora de Documentos Eliminados", leaf: true , viewClass : "gfi.bin.admctasweb.view.reportes.BitacoraDoctosEliminadosView", type:'class'},
                    { id:32, text: "Estad\u00edstico de Casos Especiales", leaf: true , viewClass : "gfi.bin.admctasweb.view.reportes.CasosEspecialesView", type:'class'},
                    { id:33, text: "Bit\u00e1cora de Seguimiento", leaf: true , viewClass : "gfi.bin.admctasweb.view.reportes.BitacoraSeguimientoView", type:'class'},
                    { id:34, text: "Bit\u00e1cora de Excepciones", leaf: true , viewClass : "gfi.bin.admctasweb.view.reportes.BitacoraExcepcionView", type:'class'},
                    ] 
            },
            { id: 4, text: "Proceso Automático", expanded: true, children: [
                    {id:41, text:"Carga automática", leaf: true, viewClass : "gfi.bin.admctasweb.view.cargaautomatica.CargaAutomaticaView", type: 'class'}
                    ]
            },
            { id: 5, text: "Reporte R29", expanded: true, children: [
                    { id:51, text: "Conversion", leaf: true , viewClass : "gfi.bin.admctasweb.view.reporte_r29.ConversionView", type:'class'},
                    { id:52, text: "Control", leaf: true , viewClass : "gfi.bin.admctasweb.view.reporte_r29.ControlView", type:'class'},
                    { id:53, text: "Mapeo", leaf: true , viewClass : "gfi.bin.admctasweb.view.reporte_r29.MapeoView", type:'class'},
                    { id:54, text: "Periodo", leaf: true , viewClass : "gfi.bin.admctasweb.view.reporte_r29.PeriodoView", type:'class'},
                    { id:55, text: "Sucursal", leaf: true , viewClass : "gfi.bin.admctasweb.view.reporte_r29.SucursalView", type:'class'},
                    { id:56, text: "Captura Manual", leaf: true , viewClass : "gfi.bin.admctasweb.view.reporte_r29.ManualForm", type:'class'},
                    { id:57, text: "Bit\u00e1cora para la CNBV", leaf: true , viewClass : "gfi.bin.admctasweb.view.reporte_r29.BitacoraCnbvView", type:'class'},
                    { id:58, text: "Bit\u00e1cora de Estatus de CNBV", leaf: true , viewClass : "gfi.bin.admctasweb.view.reporte_r29.BitacoraErrorCnbvView", type:'class'},
					{ id:59, text: "Reporte R29", leaf: true , viewClass : "gfi.bin.admctasweb.view.reportes.R29.FormPrincipalExtraccionReporteR29", type:'class'}
                    ]
            },
        ]
    }
});