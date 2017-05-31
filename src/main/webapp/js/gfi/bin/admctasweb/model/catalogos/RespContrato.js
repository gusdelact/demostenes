Ext.define('gfi.bin.admctasweb.model.catalogos.RespContrato', {
	extend: 'Ext.data.Model',
	
	fields: [
	         {name	: 'numOficio',		type	: 'string'},
	         {name	: 'tipoOficio',		type	: 'string'},
	         {name	: 'idContrato',		type	: 'Number'},
			 {name	: 'fhAlta', 		type	: 'date'	, dateFormat: 'time'  },
			 {name	: 'cveUsuAlta', 	type	: 'string'},
			 {name  : 'fhMod', 			type	: 'date'	, dateFormat: 'time'  },
			 {name	: 'cveUsuMod',		type	: 'string'},
			 {name	: 'montoInicial',	type	: 'Number'}
	         
	],
	
    validations: [
                 {type: 'presence', field: 'numOficio',		message: 'La clave de la empresa es requerida'},
                 {type: 'presence', field: 'tipoOficio',	message: 'La clave del tipo de validacion es requerida'},
                 {type: 'presence', field: 'idContrato',	message: 'El ID de validacion es requerida'},

          		 {type: 'length', field: 'idContrato', max:10, message: 'La longitud máxima para el contrato es de 10'}
           	]	
});