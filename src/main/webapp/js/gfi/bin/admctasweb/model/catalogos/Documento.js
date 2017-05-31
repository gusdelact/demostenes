Ext.define('gfi.bin.admctasweb.model.catalogos.Documento', {
	extend: 'Ext.data.Model',
	
	fields: [
	      {name	: 'numOficio',		type	: 'string'},
	      {name	: 'tipoOficio',		type	: 'string'},
	      {name	: 'numDocto',		type	: 'int'},
	      {name	: 'cveDocto',		type	: 'string'},	         
	      {name	: 'nomDocto',		type	: 'string'},
	      {name	: 'fhAlta',			type	: 'string'},
	      {name	: 'cveUsuAlta',		type	: 'string'},
	      {name	: 'fhUltMod',		type	: 'string'},
	      {name	: 'cveUsuMod',		type	: 'string'}
	],

});