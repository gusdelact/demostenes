Ext.define('gfi.bin.admctasweb.model.operativos.ImpresionDictamenJurList', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'numOficio',					type	: 'string'},
		 {name	: 'tipoOficio',					type	: 'string'},
		 {name	: 'numConsec',					type	: 'long'},
         {name	: 'impresionDictamenList',		type	: 'auto', defaultValue: null}
	]	
});