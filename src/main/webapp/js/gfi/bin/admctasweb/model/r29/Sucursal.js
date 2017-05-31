Ext.define('gfi.bin.admctasweb.model.r29.Sucursal', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'idEmpresa',				type	: 'int'},
		 {name	: 'idSucursal',				type	: 'string'},
		 {name	: 'estado',					type	: 'string'},
		 {name	: 'localidad',				type	: 'string'},
		 {name	: 'codigoPostal',			type	: 'string'},
		 {name	: 'nomSucursal',			type	: 'string'},
		 {name	: 'txInstitucion',			type	: 'string'}

	],

    validations: [
         {type: 'presence', field: 'idEmpresa',			message: 'La Institución es requerida'},
		 {type: 'presence', field: 'idSucursal',			message: 'El Número de Sucursal es requerido'}
	     
 	]		
	
});