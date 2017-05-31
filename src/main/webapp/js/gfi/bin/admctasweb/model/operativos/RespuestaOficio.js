Ext.define('gfi.bin.admctasweb.model.operativos.RespuestaOficio', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'numOficio',			type	: 'string'},
		 {name	: 'tipoOficio',			type	: 'string'},
		 {name	: 'idRespuesta',		type	: 'number'},
		 {name	: 'observaciones',		type	: 'string'},
		 {name	: 'tipoRequerimiento',	type	: 'string'},
		 {name	: 'tipoSolicitud',		type	: 'string'},
		 {name	: 'respuestaEnviada',	type	: 'string'},
		 //Se agrega para selección
		 {name	: 'idDireccion',		type	: 'string'},
		 {name	: 'apoderado',			type	: 'string'},
		 //Datos del oficio
		 {name	: 'claveAutoridad',	type	: 'string'},
		 {name	: 'tipoCaso',		type	: 'string'},
		 {name	: 'fechaOficio',	type	: 'string'},
		 {name	: 'direccion',		type	: 'string'},
		 {name	: 'gerencia',		type	: 'string'},
		 
		 //Solo se retornan cuando se realiza búsqueda
		 {name	: 'requerimientosList', 	type	: 'auto',	defaultValue: null},//Lista combo de requerimientos
		 {name	: 'solicitudesList', 		type	: 'auto',	defaultValue: null},//Lista de combo de solicitudes
		 {name	: 'direccionesList', 		type	: 'auto',	defaultValue: null}//Lista de combo de direcciones		 
		 
	],
	
    validations: [
                 {type: 'presence', field: 'numOficio',				message: 'No ha seleccionado un Número de Oficio'},
         		 {type: 'presence', field: 'tipoRequerimiento', 	message: 'No ha seleccionado un Tipo de Requerimiento'},
         		 //{type: 'presence', field: 'idDireccion', 		message: 'No ha seleccionado una Dirección'},
         		 {type: 'presence', field: 'observaciones', 	    message: 'El campo Observaciones es requerido'},
         		 {type: 'presence', field: 'apoderado', 	    	message: 'El campo Apoderado es requerido'},

          	]		
	
	
});