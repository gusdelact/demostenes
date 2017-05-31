Ext.define('gfi.bin.admctasweb.model.reportes.R29.Intento', {
  requires: ['Ext.data.Model']
  ,extend: 'Ext.data.Model'
  ,fields: [
    {name:'cveUsuAlta',    type:'string'},
    {name:'idEmpresa',     type:'string'},
    {name:'cvePeriodo',    type:'int'},
    {name:'numIntento',    type:'int'},
    {name:'fechaInicial',  type:'date', dateFormat: 'Y-m-d'},
    {name:'fechaFinal',    type:'date', dateFormat: 'Y-m-d'},
    {name:'idGrupo',       type:'int'},
    {name:'sitPeriodo',       type:'string'}
  ]
});