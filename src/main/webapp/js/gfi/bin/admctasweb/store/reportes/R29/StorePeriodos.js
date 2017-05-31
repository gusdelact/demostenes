Ext.define('gfi.bin.admctasweb.store.reportes.R29.StorePeriodos', {
    extend: 'Ext.data.Store'
    , requires: [
        'Ext.data.Store'
      , 'gfi.bin.admctasweb.model.reportes.R29.Periodo'
    ]
    , model: 'gfi.bin.admctasweb.model.reportes.R29.Periodo'
    , autoLoad: false
    , proxy: {
        type: 'ajax',
        api: {
            read: 'reportes/AseguramientosTransferenciasDesbloqueosCuentas/obtenPeriodos.htm'
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
                  idEmpresa  : Ext.getCmp('idEmpresa').getValue()
            };
        }
    }
});