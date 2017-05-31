Ext.define('gfi.bin.admctasweb.model.operativos.Respuesta', {
	extend: 'Ext.data.Model',
	
	fields: [
	      {name	: 'numOficio',			type	: 'string'},
	      {name	: 'tipoOficio',			type	: 'string'},
	      {name	: 'numConsec',			type	: 'long'},
	      {name	: 'idContrato',			type	: 'long'},
	      {name	: 'tipoRespuesta',		type	: 'int'},
	      {name	: 'tipoCaso',			type	: 'string'},	         
	      {name	: 'sitEnvio',			type	: 'string'},
	      {name	: 'bMedioElec',			type	: 'string'},
	      {name	: 'nomTitular',			type	: 'string'},
	      {name	: 'tipoTitular',		type	: 'long'},
	      {name	: 'sitCuenta',			type	: 'long'},
	      {name	: 'fhAlta',				type	: 'date'},
	      {name	: 'cveUsuAlta',			type	: 'string'},
	      {name	: 'fhUltMod',			type	: 'date'},
	      {name	: 'cveUsuMod',			type	: 'string'},
	      {name	: 'tipoBusqueda',		type	: 'string'},
	      {name	: 'idPersona',			type	: 'long'},//Se agrega para respuestas positivas
	      {name	: 'descSitCuenta',		type	: 'string'},
	      {name	: 'descTipoRespuesta',	type	: 'string'}
	],

});