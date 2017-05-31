Ext.define('gfi.bin.admctasweb.view.reportes.R29.FileDownloadReporteR29', {
    extend: 'Ext.Component',
    alias: 'widget.FileDownloadReporteR29',
    autoEl: {
        tag: 'iframe', 
        cls: 'x-hidden', 
        src: Ext.SSL_SECURE_URL
    },
    stateful: false,
    load: function(config){
        var e = this.getEl();
        e.dom.src = config.url + 
            (config.params ? '?' + Ext.urlEncode(config.params) : '');
        e.dom.onload = function() 
        {
            if(e.dom.contentDocument.body.childNodes[0].wholeText === '404') {
                Ext.Msg.show({
                    title: 'Falta archivo adjunto',
                    msg: 'El documento solicitado no se encuentra en el servidor.',
                    buttons: Ext.Msg.OK,
                    icon: Ext.MessageBox.ERROR
                });
            }
        };
    	Ext.getBody().unmask();
    }
});