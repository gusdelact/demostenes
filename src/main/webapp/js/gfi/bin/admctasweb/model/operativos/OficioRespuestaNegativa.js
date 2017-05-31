Ext.define('gfi.bin.admctasweb.model.operativos.OficioRespuestaNegativa', {
	extend: 'Ext.data.Model',
	
	fields: [
		 {name	: 'numOficio',			type	: 'string'},
		 {name	: 'tipoOficio',			type	: 'string'},
		 {name	: 'txNomArch',			type	: 'string'},
		 {name	: 'txNomAcu',			type	: 'string'}
	],
	
    validations: [
         {type: 'presence', field: 'numOficio',		message: 'El Número de Oficio es requerido'},
		 {type: 'presence', field: 'tipoOficio', 	message: 'El Tipo de Oficio es requerido'},
		 {type: 'presence', field: 'txNomAcu',		message: 'El número de folio es requerido'}
 	]
});