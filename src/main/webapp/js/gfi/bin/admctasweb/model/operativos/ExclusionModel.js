Ext.define('gfi.bin.admctasweb.model.operativos.ExclusionModel', {
	extend: 'Ext.data.Model',
	
	fields: [
	         {name	: 'idExclusion',	    	type	: 	'number'},
	         {name	: 'descripcionExclusion',	type	: 	'string'},
	         {name	: 'activo',   				type	: 	'boolean'}
	],
	
	validations: [
	         {type: 'presence', field: 'descripcionExclusion',	message: 'Debe introducir la descripcion'},
	         {type: 'length', field: 'descripcionExclusion', max:50, message: 'La longitud máxima para la descripcion es de 50'}
	]
});