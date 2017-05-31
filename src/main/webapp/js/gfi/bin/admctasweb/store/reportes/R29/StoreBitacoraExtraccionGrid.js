Ext.define('gfi.bin.admctasweb.store.reportes.R29.StoreBitacoraExtraccionGrid', {
    extend: 'Ext.data.Store'
    , requires: [
        'Ext.data.Store'
      , 'gfi.bin.admctasweb.model.reportes.R29.Bitacora'
    ]
    , model: 'gfi.bin.admctasweb.model.reportes.R29.Bitacora'
    , autoLoad: false
    , proxy: {
        type: 'ajax',
        api: {
            read: 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/buscaRegistroEnBitacora.htm'
        },
        reader: {
            type: 'json',
            root: 'data'
        }
    },
    listeners: {
        exception: function (operation) {
            Ext.MessageBox.show({
                title: 'REMOTE EXCEPTION',
                msg: operation.getError(),
                icon: Ext.MessageBox.ERROR,
                buttons: Ext.Msg.OK
            });
        },
        beforeload: function (store) {
            store.getProxy().extraParams = {
            	
            	 idEmpresa  : Ext.getCmp('hiddenTxtIdEmpresa').getValue(),
            	 cvePeriodo : Ext.getCmp('hiddenTxtCvePeriodo').getValue(),
            	 numIntento : Ext.getCmp('hiddenTxtNumIntento').getValue()
            	 
            };
        }
    }
});