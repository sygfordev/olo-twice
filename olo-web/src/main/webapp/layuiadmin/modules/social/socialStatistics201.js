layui.extend({
    selectN: './layui_exts/selectN',
    selectM: './layui_exts/selectM',
}).define(['table','form','laydate','laypage','selectN','selectM'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form,
	selectN = layui.selectN,
    selectM = layui.selectM;
	
  	//社保参保人数月统计表
	table.render({
		elem: '#LAY-social-201-manage',
		url: 'dostatis.jhtm',
		parseData: function(res){
		    return {
		    	"code": "200"==res.retCode?0:1,
		    	"msg": res.retMsg,
		    	"data": res.retData
		    };
		},
	    cols: 
	    [
	      [
	    	  {field: '', title: '中央医院社保征缴（应缴）汇总表',align:'center',colspan:38,width:'8%'},
	      ],
	      [
	    	  {field: 'social_ymonth', title: '社保年月',align:'center',rowspan:2,width:'8%'},
	    	  {field: 'wk_modality_cn', title: '用工形式',align:'center',rowspan:2,width:'8%'},
	    	  {field: '', title: '人数',align:'center',colspan:6,width:'8%'},
	    	  {field: '', title: '缴费基数',align:'center',colspan:6,width:'8%'},
	    	  {field: '', title: '单位应缴部分',align:'center',colspan:9,width:'8%'},
	    	  {field: '', title: '个人应缴部分',align:'center',colspan:9,width:'8%'},
	    	  {field: '', title: '上交财务',align:'center',colspan:3,width:'8%'}
	      ],
	      [
	    	  {field: 'num_pen', title: '养老',align:'center',width:'8%'},
	    	  {field: 'num_medi', title: '医疗',align:'center',width:'8%'},
	    	  {field: 'num_injury', title: '工伤',align:'center',width:'8%'},
	    	  {field: 'num_unemp', title: '失业',align:'center',width:'8%'},
	    	  {field: 'num_ovp', title: '省统筹',align:'center',width:'8%'},
	    	  {field: 'num_spmedi', title: '补充医疗',align:'center',width:'8%'},
	    	  {field: 'base_pen', title: '养老',align:'center',templet:function(data){return parseFloat(data.base_pen).toFixed(2);},width:'8%'},
	    	  {field: 'base_medi', title: '医疗',align:'center',templet:function(data){return parseFloat(data.base_medi).toFixed(2);},width:'8%'},
	    	  {field: 'base_injury', title: '工伤',align:'center',templet:function(data){return parseFloat(data.base_injury).toFixed(2);},width:'8%'},
	    	  {field: 'base_unemp', title: '失业',align:'center',templet:function(data){return parseFloat(data.base_unemp).toFixed(2);},width:'8%'},
	    	  {field: 'base_ovp', title: '省统筹',align:'center',templet:function(data){return parseFloat(data.base_ovp).toFixed(2);},width:'8%'},
	    	  {field: 'base_spmedi', title: '补充医疗',align:'center',templet:function(data){return parseFloat(data.base_spmedi).toFixed(2);},width:'8%'},
	    	  
	    	  {field: 'comp_total', title: '单位小计',align:'center',templet:function(data){return parseFloat(data.comp_total).toFixed(2);},width:'8%'},
	    	  {field: 'comp_pen', title: '养老',align:'center',templet:function(data){return parseFloat(data.comp_pen).toFixed(2);},width:'8%'},
	    	  {field: 'comp_medi', title: '医疗',align:'center',templet:function(data){return parseFloat(data.comp_medi).toFixed(2);},width:'8%'},
	    	  {field: 'comp_injury', title: '工伤',align:'center',templet:function(data){return parseFloat(data.comp_injury).toFixed(2);},width:'8%'},
	    	  {field: 'comp_unemp', title: '失业',align:'center',templet:function(data){return parseFloat(data.comp_unemp).toFixed(2);},width:'8%'},
	    	  {field: 'comp_ovp', title: '省统筹',align:'center',templet:function(data){return parseFloat(data.comp_ovp).toFixed(2);},width:'8%'},
	    	  {field: 'comp_spmedi', title: '补充医疗',align:'center',templet:function(data){return parseFloat(data.comp_spmedi).toFixed(2);},width:'8%'},
	    	  {field: 'comp_serious', title: '大病医疗',align:'center',templet:function(data){return parseFloat(data.comp_serious).toFixed(2);},width:'8%'},
	    	  {field: 'comp_overpaid', title: '补缴',align:'center',templet:function(data){return parseFloat(data.comp_overpaid).toFixed(2);},width:'8%'},
	    	  
	    	  {field: 'pers_total', title: '个人小计',align:'center',templet:function(data){return parseFloat(data.pers_total).toFixed(2);},width:'8%'},
	    	  {field: 'pers_pen', title: '养老',align:'center',templet:function(data){return parseFloat(data.pers_pen).toFixed(2);},width:'8%'},
	    	  {field: 'pers_medi', title: '医疗',align:'center',templet:function(data){return parseFloat(data.pers_medi).toFixed(2);},width:'8%'},
	    	  {field: 'pers_injury', title: '工伤',align:'center',templet:function(data){return parseFloat(data.pers_injury).toFixed(2);},width:'8%'},
	    	  {field: 'pers_unemp', title: '失业',align:'center',templet:function(data){return parseFloat(data.pers_unemp).toFixed(2);},width:'8%'},
	    	  {field: 'pers_ovp', title: '省统筹',align:'center',templet:function(data){return parseFloat(data.pers_ovp).toFixed(2);},width:'8%'},
	    	  {field: 'pers_spmedi', title: '补充医疗',align:'center',templet:function(data){return parseFloat(data.pers_spmedi).toFixed(2);},width:'8%'},
	    	  {field: 'pers_serious', title: '大病医疗',align:'center',templet:function(data){return parseFloat(data.pers_serious).toFixed(2);},width:'8%'},
	    	  {field: 'pers_comple', title: '补缴',align:'center',templet:function(data){return parseFloat(data.pers_comple).toFixed(2);},width:'8%'},
	    	  
	    	  {field: 'total_handin', title: '合计',align:'center',templet:function(data){return parseFloat(data.total_handin).toFixed(2);},width:'8%'},
	    	  {field: 'comp_handin', title: '单位上缴',align:'center',templet:function(data){return parseFloat(data.comp_handin).toFixed(2);},width:'8%'},
	    	  {field: 'pers_handin', title: '个人上缴',align:'center',templet:function(data){return parseFloat(data.pers_handin).toFixed(2);},width:'8%'}
	      ]
	    ],
	    page: false,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});

	//监听搜索
	form.on('submit(LAY-social-201-search)', function(data){
		  var field = data.field;
		  //执行重载
		  table.reload('LAY-social-201-manage', {where: field});
	});
  //---------表头按钮区域----------
  var active = {
	  export201: function(){
		  var invokeUrl = $("#basePath").val()+'/social/statistics/201/export.jhtm?1=1';
    	  var formVal = form.val("st201Index");
    	  var year = null;
    	  $.each(formVal,function(key,val) {
    		  if(null == val || '' == val || 'undefined' == val || ('year' != key && 'wkModalityCns' != key))
    			  return true;//continue
    		  if('year' == key) year = val;
    		  invokeUrl += ("&"+key+"="+val);
    	  });
    	  if(null == year){
    		  layer.alert('请选择统计年份');
    		  return;
    	  }
    	  window.location.href=invokeUrl;
	  }
  }
  
  //---------表头按钮触发激活----------
  $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
  	var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
  laydate.render({
  	elem: '#year',
  	type: 'year'
  });
  
  var selectStr = '['+
  		'{"id":"wkModalityCns","dkey":"modalityList","max":10}'+
  		']';
  
  $(function(){
	  var keys = JSON.parse(selectStr);
	  var list = null;
	  $.each(keys,function(index,value) {
		  list = $("#"+value.dkey).val();
		  selectM({//职务
		      elem: '#'+value.id
		      ,data: (null == list || "" == list || "undefined" == list)?[]:JSON.parse($("#"+value.dkey).val())
		      ,field: {idName:'key',titleName:'value'}
		      ,max:value.max
		      ,width:300
		      ,delimiter: ','
		  });	
	  });
  })
  
  exports('socialStatistics201', {})
});