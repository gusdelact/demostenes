Ext.define('gfi.bin.admctasweb.model.operativos.PersonaBusqueda', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'nombre',			type	: 'string'},
		 {name	: 'paterno',		type	: 'string'},
		 {name	: 'materno',		type	: 'string'},
		 {name	: 'rfc',			type	: 'string'},	         
		 {name	: 'tipoPersona',	type	: 'string'},
		 {name	: 'similaridad',	type	: 'number'},
		 {name	: 'numConsec',		type	: 'number'},
		 {name : 'objetivoBusqueda', type : 'string'},
		 {name	: 'personaList', 	type	: 'auto',	defaultValue: null},//Lista de personas de la busqueda
		 //Estos campos solo se envian para la consulta de contratos
		 
		 {name	: 'idPersona',		type	: 'number'},
		 {name	: 'idContrato',		type	: 'number'},//Se agrega idContrato por si el registro fué obtenido de la tabla Contrato
		 
		 {name	: 'contratosList', 	type	: 'auto',	defaultValue: null},//Lista contratos
		 {name	: 'contratosCambiosList', 	type	: 'auto',	defaultValue: null}//Lista de contratos de cambios
		 
		 
		 
	],

});