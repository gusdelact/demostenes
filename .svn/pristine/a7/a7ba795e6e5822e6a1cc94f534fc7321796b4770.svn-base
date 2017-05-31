Ext.define('gfi.bin.component.grid.GenericGridMan',{
    extend: 'Ext.grid.Panel',
    alias: 'widget.genericgridman',
    
	requires: ['Ext.ux.data.PagingMemoryProxy',
	           'Ext.ux.grid.FiltersFeature',
	           'gfi.corp.component.statusbar.StatusBar',
	           'gfi.bin.component.exporter.GridExporterPDF',
	           'gfi.bin.component.exporter.GridExporterXLS'],

    hideHeaders: false,
    autoScroll: true,
    selType: 'rowmodel',
    loadMask: true,
    created: false,

    cveGrid: '',
    enableGlobalSearch: true,
    enableExport: false,
    limiteExport: 10000,
    itemsPerPage: 50,
    height: 250,
    filterable: true,
    
    config: {
        model: null,
        store: null,
        columns: []
    },
   
    features:[{
    	ftype: 'filters',
        encode: true, 
        local: false,
        filters: [],

        getFilterData : function () {
            var items = this.getFilterItems(),
                filters = [],
                n, nlen, item, d, i, len;

            for (n = 0, nlen = items.length; n < nlen; n++) {
                item = items[n];
                if (item.active) {
                    d = [].concat(item.serialize());
                    for (i = 0, len = d.length; i < len; i++) {
                        filters.push({
                            property: item.field||item.dataIndex,
                            value: d[i].value,
                            operator: d[i].comparison||'eq',
                            type: d[i].type
                        });
                    }
                }
            }
            
            cpoBusqueda = this.grid.query('textfield[name="busquedaGeneral"]');
            if(cpoBusqueda.length > 0 && cpoBusqueda[0].getValue()){
            	filters.push({
                    property: 'BUSQUEDA_GRAL',
                    value: cpoBusqueda[0].getValue(),
                    operator: 'eq',
                    type: 'string'
                });
            }
            
            return filters;
        },
        
 	    buildQuery : function (filters) {
 	        var p = {}, i, f, tmp,
 	            len = filters.length;

 	        if (!this.encode){
 	        	var min = [],
 				length = filters.length,
 				index = 0;
 		
     			for (; index < length; index++) {
     				min[index] = {
     						property : filters[index].property,
     						value    : filters[index].value.value,
     						operator : filters[index].operator,
     						type : filters[index].type
     				};
     			}
     			p = Ext.encode(min);
 	        } else {
 	            tmp = [];
 	            for (i = 0; i < len; i++) {
 	                f = filters[i];
 	                tmp.push({
 	                		operator: f.operator,
 	                    	property: f.property,
 	                    	value : f.value,
 	                    	type : f.type 
 	                	});
 	            }
 	            if (tmp.length > 0){
 	                p[this.paramPrefix] = Ext.JSON.encode(tmp);
 	            }
 	        }
 	        return p;
 	    }
    }],
 
	initComponent: function() {
		var me = this;
	    me.dockedItems = me.buildDockedItems();

	    if(me.getStore()){
		    Ext.apply(me.getStore().getProxy(),{encodeSorters:function(sorters){
		    	var min = [],
				length = sorters.length,
				index = 0,
				res;
				for (; index < length; index++) {
				    columna = me.query('gridcolumn[dataIndex="'+sorters[index].property+'"]')[0];
					min[index] = {
							property : columna.sortField||sorters[index].property,
							direction: sorters[index].direction
					};
				}
				res = this.applyEncoding(min);
				return res;
			}});	
	    }
	 
	    me.addEvents('importechange');
	 
	    this.callParent();
	    
	},
	
	
    createColumns: function (columnsNames) {
    	var me = this;
    	columns = [];
    	modelFields = [];
    	filtros = [];
    	
	    Ext.Array.each(columnsNames, function(col) {
	    	column = {
	    		text		: col.header,
				header		: col.header,
				dataIndex	: col.dataIndex,
				flex		: 1,
	    		hidden		: col.hidden
			};
	    	
	    	field = {name:	col.dataIndex};
	    	
	    	tipoFiltro= me.tipoFiltro(col.tipoDato);
	    	
	    	filter = {
		        dataIndex: col.dataIndex,
		        type: tipoFiltro
		    };
	    	
			columns.push(column);
			modelFields.push(field);
			filtros.push(filter);

		});
		
    	me.getStore().model.setFields(modelFields);
    	me.getStore().pageSize = me.itemsPerPage;
    	
    	me.reconfigure(me.getStore(), columns);
    	
    	if(me.filterable){
    		me.filters.addFilters(filtros);
    	}

    	me.created= true;
    },
	
	showColumns: function(columns){
		var me = this;

		if(columns=='ALL'){
			Ext.each(me.columns, function(col){
				if(!col.isVisible()){
				    col.setVisible(true);
				}
	    	}, this);
		}
		else{
			
			Ext.each(me.columns, function(col){
				if(columns.indexOf(col.dataIndex)==-1){
					if(col.isVisible()){
						col.setVisible(false);
					}
				}
				else{
					if(!col.isVisible()){
					    col.setVisible(true);
					}
				}
				
	    	}, this);
		}

	},
	
		
	clearFilters: function(){
		me.store.clearFilter(true);
	},
    
    buildDockedItems: function(){
		var me = this;	
		itemsStatusBar = [];
		
		//Componente para realizar exportación del Grid a Excel.
		me.exporterbuttonXLS = Ext.create('gfi.bin.component.exporter.GridExporterXLS',{
			 gridExp: me, 
	      	 nombre : me.name,
	      	 limite : me.limiteExport,
	      	 hidden : !me.enableExport
	    });
		
		//Componente para realizar exportación del Grid a PDF.
		me.exporterbuttonPDF = Ext.create('gfi.bin.component.exporter.GridExporterPDF',{
			 gridExp: me, 
	      	 nombre : me.name,
	      	 limite : me.limiteExport,
	      	 hidden : !me.enableExport
	    });
		
		me.statusbar = Ext.create('gfi.corp.component.statusbar.StatusBar', {
	        store: me.store,
	        enableOverflow : false,
	        dock: 'bottom',
	        displayMsg: '{0} - {1} de {2}',
			displayInfo : true,
			items: itemsStatusBar
	    });
		
		me.statusbar.add(me.exporterbuttonXLS);
		me.statusbar.add(me.exporterbuttonPDF);
		
		items = [me.statusbar];
		
		if(me.enableGlobalSearch){
			itemsToolBar = [];
			
			if(me.enableGlobalSearch){
				itemsToolBar.push({
						xtype: 'triggerfield',
						triggerCls: 'x-form-search-trigger',
				        width: 150,
				        name: 'busquedaGeneral',
				        emptyText: 'Búsqueda General',
				        onTriggerClick: function() {
				        	me.getStore().load();
				        },
			            listeners: {
					        specialkey: function(field, e){
			                    if (e.getKey() == e.ENTER) {
			                    	field.onTriggerClick();
			                    }
			                },
				        	scope: this
				        }
					} , '-');	
			}

			items.push( Ext.create('Ext.toolbar.Toolbar',{
						layout: 'hbox',
				        dock: 'top',
				        items: itemsToolBar
					}));
		}		
		
		
		items.push(me.dockedItems);
		return items;
	},
	
	getSelection: function(){
		selModel = this.getSelectionModel().getSelection();
		selModelSelection=[];
		Ext.Array.each(selModel , function(registro){
			selModelSelection.push(registro.data);
		});
		
		return (this.savedSelection||[]).concat(selModelSelection);
	},
	
	clearSelection: function(){
		this.savedSelection = [];
		this.getSelectionModel().deselectAll();
	},
	
	tipoFiltro:function(tipoCol){
		tipo = 'string';
		switch (tipoCol) {
	    	case "NUMBER":
    	        tipo = 'numeric';
    	        break;
	    	case "DATE":
    	        tipo = 'date';
    	        break;
		}
		return tipo;
	}, 
	
	getActiveHeader: function(){
		var header = this.view.headerCt.getMenu().activeHeader;
		return header?header.dataIndex:null;
	},
	
	getFiltersAsObject:function(){
		var me = this;
		return me.filters.buildQuery(me.filters.getFilterData());
	}
});