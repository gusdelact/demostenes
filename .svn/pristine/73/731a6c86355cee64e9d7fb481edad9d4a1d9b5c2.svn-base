Ext.define('gfi.bin.admctasweb.model.reportes.BitacoraExcepcionList', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'idEmpresa',				type	: 'int'},
		 {name	: 'idContrato',				type	: 'long'},
		 {name	: 'cveTipoExcepcion',		type	: 'string'},
		 {name	: 'fInicio',				type	: 'string'},
		 {name	: 'fFin',					type	: 'string'},
		 {name	: 'bitExcepcionList', 		type	: 'auto', defaultValue: null}//Lista de bitácora de excepción
	],

    validations: [
 		 {type: 'presence', field: 'cveTipoExcepcion', message: 'La clave de excepci\u00f3n es requerida'}
    ]
});