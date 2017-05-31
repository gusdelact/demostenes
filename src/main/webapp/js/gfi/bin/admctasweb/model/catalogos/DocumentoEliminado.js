Ext.define('gfi.bin.admctasweb.model.catalogos.DocumentoEliminado', {
	extend: 'Ext.data.Model',
	
	fields: [
	      {name	: 'numOficio',		type	: 'string'},
	      {name	: 'tipoOficio',		type	: 'string'},
	      {name	: 'descTipoOficio',	type	: 'string'},
	      {name	: 'cveDocto',		type	: 'string'},
	      {name	: 'descCveDocto',	type	: 'string'},
	      {name	: 'nomDocto',		type	: 'string'},
	      {name	: 'fhElim',			type	: 'string'},
	      {name	: 'cveUsuElim',		type	: 'string'}
	],

});