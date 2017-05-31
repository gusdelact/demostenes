Ext.define('gfi.icb.view.TestV1',{
	extend : 'Ext.Panel',
	
	requires : ['gfi.corp.component.buscador.BuscadorTrigger'],
	
	html : 'Test... Prueba componentes',


	initComponent: function() {
		this.items= [
		    	// Buscador de monedas - Configuracion mínima
		    	{
		    		xtype: 'gfibuscadortrigger', 
		    		grupo: 'corp', 
		    		clave: 'moneda'
		    	},
		    	// Buscador de emisoras - Referencia a otros controles y captura habilitada sobre emisora y serie
		    	{ 
		    		xtype: 'gfibuscadortrigger',
		    		grupo : 'bursatil',
		    		clave : 'emisora', 
		    		linkProperty: 'emisora',
		    		fieldLabel: 'Emisoras',
		    		controls: [{
		    			ref: 'serie', 
		    			selector: '#edSerie',
		    			linkProperty: 'serie'
		    		}, {
		    			ref: 'tipoValor', 
		    			selector: '#edTipoValor',
		    			valueFormat: 'TipoValor: [tipoValor]'
		    		}]
		    	}, {
		    		xtype: 'textfield',
		    		itemId: 'edSerie'
		    	}, {
		    		xtype: 'textfield',
		    		itemId: 'edTipoValor'
		    	},
		    	// Buscador de Moneda - Forma alterna de crear el componente con captura habilitada sobre descMoneda
		    	Ext.create('gfi.corp.component.buscador.BuscadorTrigger', {
		    		grupo : 'corp',
		    		clave : 'moneda', 
		    		itemId: 'busPba',
		    		linkProperty: 'descMoneda',
		    		emptyText: 'Seleccion Moneda'
		    	}),
		    	{
		    		xtype: 'gfibuscadortrigger', 
		    		grupo: 'corpo', 
		    		clave: 'empresa'
		    	},
		    	];
		this.callParent();
	}  

});