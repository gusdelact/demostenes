Ext.define('gfi.bin.admctasweb.store.reportes.R29.StoreIntentosRealizadosGrid', {
    extend: 'Ext.data.Store'
    , requires: [
        'Ext.data.Store'
      , 'gfi.bin.admctasweb.model.reportes.R29.Intento'
    ]
    , model: 'gfi.bin.admctasweb.model.reportes.R29.Intento'
    , autoLoad: false
    , proxy: {
        type: 'ajax',
        api: {
            read: 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/obtenIntentosRealizados.htm'
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
                  idEmpresa  : Ext.getCmp('cbEmpresaIntentosRealizadosForm').getValue(),
                  cvePeriodo :  Ext.getCmp('dfPeriodoIntentosRealizadosForm').getValue()
            };            
        }
    }
});