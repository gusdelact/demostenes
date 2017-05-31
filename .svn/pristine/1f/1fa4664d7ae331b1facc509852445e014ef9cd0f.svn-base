Ext.define('gfi.bin.admctasweb.view.reportes.BitacoraSeguimientoChart', {
	extend: 'Ext.chart.Chart',
	alias: 'widget.bitacorachart',
	
    renderTo: Ext.getBody(),
    width: 400,
    height: 200,
    animate: true,
    
    //Inicializamos componente.
	initComponent: function() {
		var me = this,
			store;
		
		//Construimos el Store del Chart.
		store = me.buildStore();
		me.store = store;
	   
	    
	    this.callParent();
	},
    
    axes: [{
        type: 'Numeric',
        position: 'bottom',
        fields: ['data'],
        label: {
            renderer: Ext.util.Format.numberRenderer('0,0')
        },
        title: 'No. Oficios',
        grid: true,
        minimum: 0
    }, {
        type: 'Category',
        position: 'left',
        fields: ['name'],
        title: 'Estatus'
    }],
    series: [{
        type: 'bar',
        axis: 'bottom',
        highlight: true,
        tips: {
          trackMouse: true,
          width: 140,
          height: 28,
          renderer: function(storeItem, item) {
            this.setTitle(storeItem.get('name') + ': ' + storeItem.get('data') + ' views');
          }
        },
        label: {
          display: 'insideEnd',
            field: 'data',
            renderer: Ext.util.Format.numberRenderer('0'),
            orientation: 'horizontal',
            color: '#333',
            'text-anchor': 'middle'
        },
        xField: 'name',
        yField: 'data'
    }],

	//Construimos el Store del Grid.
	buildStore: function () {
	   
	    return Ext.create('Ext.data.Store', {
	    	fields: ['name', 'data'],
		    data: [
		        { 'name': 'Registrados',   'data':10 },
		        { 'name': 'Pend. Busqueda',   'data': 7 },
		        { 'name': 'Pend. Dictamen Jur.', 'data': 5 },
		        { 'name': 'Pend. Impresion',  'data': 2 },
		        { 'name': 'Pend. Envio',  'data':27 }
		    ]
	   });
	}
});