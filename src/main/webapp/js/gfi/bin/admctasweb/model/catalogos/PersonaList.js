Ext.define('gfi.bin.admctasweb.model.catalogos.PersonaList', {
	extend: 'Ext.data.Model',
	
	fields: [
		{name	: 'numOficio',		type	: 'string'},
		{name	: 'tipoOficio',		type	: 'string'},
		{name	: 'personaList', 	type	: 'auto',	defaultValue: null}
	]
	
});