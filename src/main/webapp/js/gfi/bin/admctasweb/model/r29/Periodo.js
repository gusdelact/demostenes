Ext.define('gfi.bin.admctasweb.model.r29.Periodo', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'idEmpresaP',				type	: 'string'},
		 {name	: 'cvePeriodoP',			type	: 'string'},
		 {name	: 'descPeriodoP',			type	: 'string'},
		 {name	: 'fechaFinal',				type	: 'string'},
		 {name	: 'fechaInicial',			type	: 'string'},
		 {name	: 'txInstitucionP',    		type	: 'string'},
		 {name	: 'estatus',        		type	: 'string'},
		 {name	: 'idEmpresa',				type	: 'string'},
		 {name	: 'cvePeriodo',		    	type	: 'string'},
		 {name	: 'descPeriodo',			type	: 'string'},
		 {name	: 'txInstitucion',    		type	: 'string'}
	],

    validations: [
         {type: 'presence', field: 'idEmpresaP',		    	message: 'La Institución es requerida'},
		 {type: 'presence', field: 'cvePeriodoP', 			message: 'El Periodo es requerido'},
		 {type: 'presence', field: 'fechaInicial',		 	message: 'La fecha Inicial es requerida'},
		 {type: 'presence', field: 'fechaFinal',		 	message: 'La fecha Final es requerida'},
		 {type: 'presence', field: 'descPeriodoP', 			message: 'La Descripción es requerida'}
				
 	]		
	
});