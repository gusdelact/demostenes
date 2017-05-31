Ext.define('gfi.bin.admctasweb.model.reportes.BitacoraDoctosEliminados', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'numOficio',					type	: 'string'},
		 {name	: 'tipoOficio',					type	: 'string'},
		 {name	: 'fInicio',					type	: 'string'},
		 {name	: 'fFin',						type	: 'string'},
		 {name	: 'documentoEliminadoList', 	type	: 'auto', defaultValue: null}//Lista de documentos eliminados
	],
		
});