Ext.define('gfi.bin.admctasweb.model.catalogos.Destinatario', {
	extend: 'Ext.data.Model',
	
	fields: [
	         {name	: 'idDestinatario',		    	type	: 'long'},
	         {name	: 'cveEstatus',					type	: 'string'},
	         {name	: 'nombre',						type	: 'string'},
	         {name	: 'correo',	        			type	: 'string'},
	         {name	: 'situacion',					type	: 'string'},
			 {name	: 'fhAlta',						type	: 'string'},
			 {name	: 'cveUsuAlta', 				type	: 'string'},
			 {name	: 'fhUltMod',					type	: 'string'},
			 {name	: 'cveUsuMod',					type	: 'string'}
	         
	],
	
    validations: [
                 {type: 'presence', field: 'cveEstatus',		message: 'No ha seleccionado un Evento para la Notificación'},
          		 {type: 'presence', field: 'nombre', 			message: 'El Nombre es requerido'},
          		 {type: 'presence', field: 'correo', 			message: 'El correo es requerido'},
          		 {type: 'presence', field: 'situacion', 	    message: 'No ha seleccionado la Situación del Destinatario'},
          		 
          		 {type: 'length', field: 'nombre', max:120, message: 'La longitud máxima para el Nombre es de 120'},
          		 {type: 'length', field: 'correo', max:80, message: 'La longitud máxima para el Correo es de 80'},
                 {type: 'format', field: 'correo', matcher: /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/, message:"Email con formato Incorrecto"}

           	]		
 	
	
	
	
});