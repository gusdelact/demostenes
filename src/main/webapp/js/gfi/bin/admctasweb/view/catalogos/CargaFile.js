Ext.define('gfi.bin.admctasweb.view.catalogos.CargaFile', {
	extend : 'Ext.window.Window',
	alias  : 'widget.cargafile',
	
	title  : 'Selecciona Archivo',
	bodyPadding : 5,
	layout: 'fit',
	
	initComponent : function(){
		var me = this;
		
		this.nmStore = this.nameStore;
		this.datos = this.data;
		this.catalogo   = this.tagCt;
		this.tipoAction = this.tipo;
		
		//Items de la Ventana emergente.
		this.items = [{
			xtype : 'form',
			items : me.buildFields(this.datos)
		}];
		
		//Botones de la Forma.
		this.buttons = [ {
			text : 'Ok',
			action : 'setFileName',
			scope : this,
			scale : 'small',
			handler : this.setFileName
		}, {
			text : 'Cancelar',
			scope : this,
			scale : 'small',
			handler : this.close
		} ];
		
		me.callParent();
	},

	//Función que construye los campos de la Forma.
	buildFields : function(data){	
		return [ {
			xtype 	: 'filefield',
			id	 	: 'idFile',
			fieldLabel : 'Archivo'
		}, {
			xtype : 'textfield',
			name : 'nomArch',
			fieldLabel : 'Tipo de Oficio'
		} ];
	},
	
	//Funcion Seleccionar
	setFileName : function(button){
		alert('Entro a Seleccionar');
		var fil = Ext.ComponentManager.get('idFile').value;
		var name = fil.substring(12);
		alert(name);
		
		var win = button.up('window');
		
		win.close();
		
	}
	
});