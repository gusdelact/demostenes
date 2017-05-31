Ext.define('gfi.bin.component.bitacora.model.AuditoriaCorp', {			
	extend: 'Ext.data.Model',
	
	fields: [
         {name	: 'idAuditoria',		type	: 'long'},
         {name	: 'idEmpresa',			type	: 'int'},
         {name	: 'cveAplicacion',		type	: 'string'},
         {name	: 'cveMedio',			type	: 'string'},
         {name	: 'cveTipoEvento',		type	: 'string'},
         {name	: 'cveUsuario',			type	: 'string'},
         {name	: 'fhOperacion',		type	: 'date', 		dateFormat: 'time'},
         {name	: 'fhRegistro',			type	: 'date',		dateFormat: 'time'},
         {name	: 'metodo',				type	: 'string'},
         {name	: 'nivelAuditoria',		type	: 'string'},
         {name	: 'referencia',			type	: 'string'}
	]
});