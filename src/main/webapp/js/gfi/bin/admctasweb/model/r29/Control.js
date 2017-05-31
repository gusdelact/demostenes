Ext.define('gfi.bin.admctasweb.model.r29.Control', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'idEmpresaCx',				type	: 'string'},
		 {name	: 'cvePeriodoCx',				type	: 'string'},
		 {name	: 'descPeriodoCx',				type	: 'string'},
		 {name	: 'numIntentoCx',				type	: 'string'},
		 {name	: 'situacionCx',		     	type	: 'string'},
		 {name	: 'txInstitucionCx',     		type	: 'string'},
		 {name	: 'idEmpresa',				type	: 'string'},
		 {name	: 'cvePeriodo',				type	: 'string'},
		 {name	: 'descPeriodo',				type	: 'string'},
		 {name	: 'numIntento',				type	: 'string'},
		 {name	: 'situacion',		     	type	: 'string'},
		 {name	: 'txInstitucion',     		type	: 'string'}

	],

    validations: [
         {type: 'presence', field: 'idEmpresaCx',	     		message: 'La Institución es requerida'},
		 {type: 'presence', field: 'cvePeriodoCx', 				message: 'El Periodo es requerido'},
		 {type: 'presence', field: 'numIntentoCx',				message: 'El Número de Intento es requerido'},
		 {type: 'presence', field: 'situacionCx',			 	message: 'La Situación es requerida'},
			
 	]		
	
});