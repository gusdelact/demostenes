/**
 * @author LUGL4884
 * Componente que realiza la exportacion a PDF de un GenericGrid
 */
Ext.define('gfi.bin.component.exporter.GridExporterPDF', {
	extend: 'Ext.button.Button',
    alias: 'widget.gridexporterpdf',
    icon : 'resources/images/pdf.png',
    tooltip : 'Exportar a PDF',
    
    tipo : 'PDF',
    formatter : 'json',
    nombre : 'GridPDF',
    limite : 10000, 
    gridExp : null,
    
    //Constructor del Componente
    constructor: function(options) {
    	var me = this;
		
		me.callParent(arguments);	
    },

    setComponent: function(gridExp, options) {
        this.gridExp = gridExp;     
    },

    handler: function() {
    	var me = this;
    	
    	var compGrid = this.gridExp;
		var store = compGrid.getStore();
		
		//Obtenermos los filtros del Grid.
		var filterItems = compGrid.filters.getFilterData();
		var filter = me.armaFiltros(filterItems);
		
		//Obtenemos los Sorters del Grid.
		var sortItems = store.sorters.items;
		var sort = me.armaOrden(sortItems, compGrid); 
		
		//Obtenemos los parametros extra si son necesarios.
		var extParam = Ext.JSON.encode(store.proxy.extraParams);
		
		//Generamos el submit para la descarga del reporte.
		me.downloadFile({
			url: './exporterman/exportar.htm',
			params: {
				filter		: filter,
				sort		: sort,
				limit		: this.limite,
				page		: 1,
				tipo 		: this.tipo,
				nombre		: this.nombre,
				cveGrid		: compGrid.cveGrid,
				extParam	: extParam
			}	    		
		});		
		
		console.log(filter);
		console.log(sort);
    },
    
    downloadFile: function(config){
	    config = config || {};
	    var url = config.url,
	        method = config.method || 'POST',// Either GET or POST. Default is POST.
	        params = config.params || {};

	    // Create form panel. It contains a basic form that we need for the file download.
	    var form = Ext.create('Ext.form.Panel', {
	        standardSubmit: true,
	        url: url,
	        method: method
	    });

	    // Call the submit to begin the file download.
	    form.submit({
	        target: '_blank', // Avoids leaving the page. 
	        params: params
	    });

	    // Clean-up the form after 100 milliseconds.
	    // Once the submit is called, the browser does not care anymore with the form object.
	    Ext.defer(function(){
	        form.close();
	    }, 100);

	},
	
	armaFiltros: function (filterItems){
		var filterArray = [];
		var tmpFilter;
		var i;
		
		for (i = 0; i < filterItems.length; i++) {
			tmpFilter = filterItems[i];
			
			filterArray.push({
				operator: tmpFilter.operator,
             	property: tmpFilter.property,
             	value : tmpFilter.value,
             	type : tmpFilter.type 
		    });
		}
		
		return Ext.JSON.encode(filterArray);
	},
	
	armaOrden: function (sortItems, compGrid){
		var sortArray = [];
		var tmpSort;
		var i;
		
		for (i = 0; i < sortItems.length; i++) {
			tmpSort = sortItems[i];
			
			columna = compGrid.query('gridcolumn[dataIndex="'+tmpSort.property+'"]')[0];
			
		    sortArray.push({
	     		direction: tmpSort.direction,
	         	property: columna.sortField
		    });
		}
		
		return Ext.JSON.encode(sortArray);
	}
    
});