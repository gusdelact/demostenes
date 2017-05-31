Ext.define('gfi.bin.admctasweb.model.r29.Conversion', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'idCatalogo',				type	: 'number'},
		 {name	: 'descCatalogo',	type	: 'string'}

	],

    validations: [
         {type: 'presence', field: 'idCatalogo',			message: 'El número de Catalogo es requerido'},
         {type: 'presence', field: 'descCatalogo',			message: 'La descripción del Catalogo es requerida'}
		
 	]		
	
});