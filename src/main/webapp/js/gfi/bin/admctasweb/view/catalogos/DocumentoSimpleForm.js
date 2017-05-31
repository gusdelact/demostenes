Ext.define('gfi.bin.admctasweb.view.catalogos.DocumentoSimpleForm', {
	extend : 'gfi.corp.component.form.BasicForm',
	alias: 'widget.documentosimpleform',
	
	//Configuración del Componente.
	config : {
		header: 'Documento',
		formType: gfi.FormTypes.CATALOGO,
		modelClass : 'gfi.bin.admctasweb.model.catalogos.Documento',
		secured: false,
	},
	
	//Construimos los items de la Forma
	buildItems : function() {
		return [
		{
			xtype : 'textfield',
			fieldLabel : 'Número de Oficio:',
			name : 'numOficio',
			labelWidth: 140,
			width : 320,
			readOnly: true
		},{
			xtype : 'textfield',
			fieldLabel : 'Tipo de Oficio:',
			name : 'tipoOficio',
			labelWidth: 140,
			width : 200,
			readOnly: true
		},{
			xtype : 'numberfield',
			fieldLabel : 'Numero de Documento:',
			name : 'numDocto',
			labelWidth: 140,
			width : 200,
			readOnly: true
		}, {
			xtype : 'combobox',
			fieldLabel : 'Clave de Documento:',
			name : 'cveDocto',
			store:  Ext.create('Ext.data.Store', {
			    fields: ['cveDocto', 'claveDoc'],
			    data : [
			        {"cveDocto":"OF", "claveDoc":"Oficio"},
			        {"cveDocto":"DA", "claveDoc":"Documento de Autoridad"},
			        {"cveDocto":"EN", "claveDoc":"Enviado a SITI"}
			    ]
			}),
			valueField: 'cveDocto',
			displayField: 'claveDoc',
			labelWidth: 140,
			width : 320,
			formStatus : [gfi.component.BasicForm.STATUS_NEW,
			              gfi.component.BasicForm.STATUS_UPDATE]
		},{
	        xtype: 'fieldcontainer',
	        layout: 'hbox',
	        items: [{
						xtype : 'textfield',
						fieldLabel : 'Nombre de Documento:',
						name : 'nomDocto',
						id	 : 'nomDoctoId',
						labelWidth: 140,
						width : 320,
						formStatus : [gfi.component.BasicForm.STATUS_NEW,
						              gfi.component.BasicForm.STATUS_UPDATE]
					}, {
	    	            xtype: 'splitter'
	    	        }, {
	    				xtype : 'filefield',
	    				name : 'file',
	    				id	 : 'fileId',
	    				buttonOnly : true,
	    				buttonText : 'Archivo',
	    				listeners:{
	    		            change:function( thiss, value, eOpts ){
	    		        		var name = value.substring(12);
	    		        		Ext.ComponentManager.get('nomDoctoId').setValue(name);
	    		            }
	    		         },
	    				formStatus : [gfi.component.BasicForm.STATUS_NEW,
	    				              gfi.component.BasicForm.STATUS_UPDATE]
	    			}]
		}
		];
	},
	
	//Sobreescribimos la accion Aceptar para hacer el Submit de la forma con el Archivo.
	doAceptar : function(completed, options) {
		var me = this;
		var form = me.mainPanel.getForm();
		if (form.isValid()) {
			form.submit({
				url : 'catalogos/documento/carga.htm',
				waitMsg : 'Cargando archivo...',
				success: function(form, action) {
					console.log("Carga Correcta de Archivo.");
					me.window.close();
		        },
		        // Si no se pasa el Parametro "succes" entra aqui.
		        failure: function(form, action) {
		        	me.window.close();
		        }
			});
		}
	}
	
});

