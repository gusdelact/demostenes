Ext.define('gfi.bin.admctasweb.model.catalogos.Autoridades', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'cveAutoridad',	type	: 'string'},
		 {name	: 'nomAutoridad',	type	: 'string'},
		 {name	: 'sitAutoridad',	type	: 'string'},
		 {name	: 'fhAlta',			type	: 'string'},
		 {name	: 'cveUsuAlta', 	type	: 'string'},
		 {name	: 'fhUltMod',		type	: 'string'},
		 {name	: 'cveUsuMod',		type	: 'string'}

	],
	
    validations: [
		 {type: 'presence', field: 'cveAutoridad', message: 'La Clave de Autoridad es requerida'},
		 {type: 'presence', field: 'nomAutoridad', message: 'El Nombre de Autoridad es requerido'},
		 {type: 'presence', field: 'sitAutoridad', message: 'La Situaci\u00f3n de Autoridad es requerida'}
 	]		
	
});