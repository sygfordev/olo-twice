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
	
	
	var arr=new Array();
	arr=[100,101,102,103];
	$.each(arr,function(index,value){
		table.render({
			elem: '#LAY-social-'+value+'-manage',
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
		    	  {field: 'social_ymonth', title: '查询时间',align:'center',rowspan:3,width:'8%'},
		    	  {field: '', title: '档期缴费工资基数',align:'center',colspan:6,width:'8%'},
		    	  {field: '', title: '养老保险费',align:'center',colspan:18,width:'8%'},
		      ],
		      [
		    	  {field: '', title: '单位',align:'center',colspan:3,width:'8%'},
		    	  {field: '', title: '个人',align:'center',colspan:3,width:'8%'},
		    	  {field: '', title: '当期征缴情况',align:'center',colspan:9,width:'8%'},
		    	  {field: '', title: '累计征缴情况',align:'center',colspan:9,width:'8%'}
		      ],
		  	  [
				  {field: 'base_comp_total', title: '缴费工资总额' ,align:'center',templet:function(data){return parseFloat(data.base_comp_total).toFixed(2);},width:'8%'},
				  {field: 'base_comp_laohe', title: '劳合属地' ,align:'center',templet:function(data){return parseFloat(data.base_comp_laohe).toFixed(2);},width:'8%'},
				  {field: 'base_comp_laowu', title: '劳务属地' ,align:'center',templet:function(data){return parseFloat(data.base_comp_laowu).toFixed(2);},width:'8%'},
				  {field: 'base_pers_total', title: '缴费工资总额' ,align:'center',templet:function(data){return parseFloat(data.base_pers_total).toFixed(2);},width:'8%'},
				  {field: 'base_pers_laohe', title: '劳合属地' ,align:'center',templet:function(data){return parseFloat(data.base_pers_laohe).toFixed(2);},width:'8%'},
				  {field: 'base_pers_laowu', title: '劳务属地' ,align:'center',templet:function(data){return parseFloat(data.base_pers_laowu).toFixed(2);},width:'8%'},
				  {field: 'amount', title: '当期应缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount).toFixed(2);},width:'8%'},
			      {field: 'amount_laohe', title: '劳合属地当期应缴金额',align:'center',templet:function(data){return parseFloat(data.amount_laohe).toFixed(2);},width:'8%'},
			      {field: 'amount_laohe_comp', title: '其中：单位部分档期应缴金额',align:'center',templet:function(data){return parseFloat(data.amount_laohe_comp).toFixed(2);},width:'10%'},
			      {field: 'amount_laowu', title: '劳务属地当期应缴金额',align:'center',templet:function(data){return parseFloat(data.amount_laowu).toFixed(2);},width:'10%'},
			      {field: 'amount_laowu_comp', title: '其中：单位部分档期应缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount_laowu_comp).toFixed(2);},width:'10%'},
			      {field: 'amount_recap', title: '当期实缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount_recap).toFixed(2);},width:'8%'},
			      {field: 'amount_recap_laohe', title: '劳合属地当期实缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount_recap_laohe).toFixed(2);},width:'10%'},
			      {field: 'amount_recap_laowu', title: '劳务属地当期实缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount_recap_laowu).toFixed(2);},width:'10%'},
			      {field: 'rate_dangqi', title: '征缴率' ,align:'center',width:'8%'},
			      
			      {field: 'amount_addup', title: '累计应缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount_addup).toFixed(2);},width:'10%'},
			      {field: 'amount_addup_laohe', title: '劳合属地累计应缴金额',align:'center',templet:function(data){return parseFloat(data.amount_addup_laohe).toFixed(2);},width:'10%'},
			      {field: 'amount_addup_laohe_comp', title: '其中：单位部分累计应缴金额',align:'center',templet:function(data){return parseFloat(data.amount_addup_laohe_comp).toFixed(2);},width:'10%'},
			      {field: 'amount_addup_laowu', title: '劳务属地累计应缴金额',align:'center',templet:function(data){return parseFloat(data.amount_addup_laowu).toFixed(2);},width:'10%'},
			      {field: 'amount_addup_laowu_comp', title: '其中：单位部分累计应缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount_addup_laowu_comp).toFixed(2);},width:'10%'},
			      {field: 'amount_addup_recap', title: '累计实缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount_addup_recap).toFixed(2);},width:'10%'},
			      {field: 'amount_addup_recap_laohe', title: '劳合属地累计实缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount_addup_recap_laohe).toFixed(2);},width:'10%'},
			      {field: 'amount_addup_recap_laowu', title: '劳务属地累计实缴金额' ,align:'center',templet:function(data){return parseFloat(data.amount_addup_recap_laowu).toFixed(2);},width:'10%'},
			      {field: 'rate_addup', title: '征缴率' ,align:'center',width:'8%'}
			  ]
		    ],
		    page: false,
		    limits: [10,15,20,30,50,100],
		    limit: 10,
		    text: {
		        none: '暂无相关数据'
		    }
		});
	});
	
  
  //监听搜索
  form.on('submit(LAY-social-100-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-social-100-manage', {where: field});
  });
  //监听搜索
  form.on('submit(LAY-social-101-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-social-101-manage', {where: field});
  });
  //监听搜索
  form.on('submit(LAY-social-102-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-social-102-manage', {where: field});
  });
  //监听搜索
  form.on('submit(LAY-social-103-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-social-103-manage', {where: field});
  });
  
  //返回搜索
//  form.on('submit(LAY-social-2-staticIndex)', function(data){
//	  var topAdmin = parent === self ? admin : parent.layui.admin; // 关闭tab页
//	  topAdmin.closeThisTabs();
//	  
//	  //parent.layui.admin.events.closeThisTabs();
//      
////      var l = parent === self ? layui : top.layui;
////	  var title = (obj.event === 'edit')?"编辑人事档案":"查看人事档案";
////	  l.index.openTabsPage('personnel/archives/edit.jhtm?id='+data.id+"&oper="+obj.event, title);
//  });
  	//社保参保人数月统计表
	table.render({
		elem: '#LAY-social-200-manage',
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
	    	  {field: 'social_ymonth', title: '社保年月',align:'center',rowspan:3,width:'8%'},
	    	  {field: '', title: '单位人数',align:'center',colspan:8,width:'8%'},
	    	  {field: '', title: '基本养老保险',align:'center',colspan:5,width:'8%'},
	    	  {field: '', title: '基本医疗保险',align:'center',colspan:5,width:'8%'},
	    	  {field: '', title: '工伤保险',align:'center',colspan:5,width:'8%'},
	    	  {field: '', title: '失业保险',align:'center',colspan:5,width:'8%'},
	    	  {field: '', title: '生育保险',align:'center',colspan:5,width:'8%'}
	      ],
	      [
	    	  {field: '', title: '期初人数',align:'center',colspan:4,width:'8%'},
	    	  {field: '', title: '期末人数',align:'center',colspan:4,width:'8%'},
	    	  {field: '', title: '参保人员',align:'center',colspan:4,width:'8%'},
	    	  {field: 'rate_pen', title: '参保率',align:'center',rowspan:2,width:'8%'},
	    	  {field: '', title: '参保人员',align:'center',colspan:4,width:'8%'},
	    	  {field: 'rate_medi', title: '参保率',align:'center',rowspan:2,width:'8%'},
	    	  {field: '', title: '参保人员',align:'center',colspan:4,width:'8%'},
	    	  {field: 'rate_injury', title: '参保率',align:'center',rowspan:2,width:'8%'},
	    	  {field: '', title: '参保人员',align:'center',colspan:4,width:'8%'},
	    	  {field: 'rate_unemp', title: '参保率',align:'center',rowspan:2,width:'8%'},
	    	  {field: '', title: '参保人员',align:'center',colspan:4,width:'8%'},
	    	  {field: 'rate_birth', title: '参保率',align:'center',rowspan:2,width:'8%'}
	      ],
	  	  [
	  		  {field: 'size_laohe_bf', title: '在册劳合' ,align:'center',width:'8%'},
			  {field: 'size_retire_bf', title: '退休人员' ,align:'center',width:'8%'},
			  {field: 'size_laowu_bf', title: '劳务工' ,align:'center',width:'8%'},
			  {field: 'size_total_bf', title: '合计' ,align:'center',width:'8%'},
			  {field: 'size_laohe', title: '在册劳合' ,align:'center',width:'8%'},
			  {field: 'size_retire', title: '退休人员' ,align:'center',width:'8%'},
			  {field: 'size_laowu', title: '劳务工' ,align:'center',width:'8%'},
			  {field: 'size_total', title: '合计' ,align:'center',width:'8%'},
			  //养老
			  {field: 'size_pen', title: '省直' ,align:'center',width:'8%'},
			  {field: 'size_pen_laohe', title: '属地劳合' ,align:'center',width:'8%'},
			  {field: 'size_pen_laowu', title: '属地劳务' ,align:'center',width:'8%'},
		      {field: 'size_pen_total', title: '人员合计',align:'center',width:'8%'},
		      //医疗
		      {field: 'size_medi', title: '焦煤焦作市' ,align:'center',width:'8%'},
			  {field: 'size_medi_laohe', title: '属地劳合' ,align:'center',width:'8%'},
			  {field: 'size_medi_laowu', title: '属地劳务' ,align:'center',width:'8%'},
		      {field: 'size_medi_total', title: '人员合计',align:'center',width:'8%'},
		      
		      //工伤
		      {field: 'size_injury', title: '省直' ,align:'center',width:'8%'},
			  {field: 'size_injury_laohe', title: '属地劳合' ,align:'center',width:'8%'},
			  {field: 'size_injury_laowu', title: '属地劳务' ,align:'center',width:'8%'},
		      {field: 'size_injury_total', title: '人员合计',align:'center',width:'8%'},
		      
		      //失业
		      {field: 'size_unemp', title: '焦煤焦作市' ,align:'center',width:'8%'},
			  {field: 'size_unemp_laohe', title: '属地劳合' ,align:'center',width:'8%'},
			  {field: 'size_unemp_laowu', title: '属地劳务' ,align:'center',width:'8%'},
		      {field: 'size_unemp_total', title: '人员合计',align:'center',width:'8%'},
		      
		      //生育
		      {field: 'size_birth', title: '焦煤焦作市' ,align:'center',width:'8%'},
			  {field: 'size_birth_laohe', title: '属地劳合' ,align:'center',width:'8%'},
			  {field: 'size_birth_laowu', title: '属地劳务' ,align:'center',width:'8%'},
		      {field: 'size_birth_total', title: '人员合计',align:'center',width:'8%'}
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
	form.on('submit(LAY-social-200-search)', function(data){
		  var field = data.field;
		  //执行重载
		  table.reload('LAY-social-200-manage', {where: field});
	});
  //---------表头按钮区域----------
  var active = {
	  export100: function(){
		  var invokeUrl = $("#basePath").val()+'/social/statistics/100/export.jhtm?1=1';
		  var formVal = form.val("st100Index");
		  var start = null;
		  var end = null;
		  $.each(formVal,function(key,val) {
			  if(null == val || '' == val || 'undefined' == val)
				  return true;//continue
			  if('select-start' == key) start = val;
			  if('select-end' == key) end = val;
			  invokeUrl += ("&"+key+"="+val);
		  });
		  if(null == start && null == end){
			  layer.alert('请选择时间段');
			  return;
		  }
		  window.location.href=invokeUrl;
	  },
	  export101: function(){
		  var invokeUrl = $("#basePath").val()+'/social/statistics/101/export.jhtm?1=1';
		  var formVal = form.val("st101Index");
		  var start = null;
		  var end = null;
		  $.each(formVal,function(key,val) {
			  if(null == val || '' == val || 'undefined' == val)
				  return true;//continue
			  if('select-start' == key) start = val;
			  if('select-end' == key) end = val;
			  invokeUrl += ("&"+key+"="+val);
		  });
		  if(null == start && null == end){
			  layer.alert('请选择时间段');
			  return;
		  }
		  window.location.href=invokeUrl;
	  },
	  export102: function(){
		  var invokeUrl = $("#basePath").val()+'/social/statistics/102/export.jhtm?1=1';
		  var formVal = form.val("st102Index");
		  var start = null;
		  var end = null;
		  $.each(formVal,function(key,val) {
			  if(null == val || '' == val || 'undefined' == val)
				  return true;//continue
			  if('select-start' == key) start = val;
			  if('select-end' == key) end = val;
			  invokeUrl += ("&"+key+"="+val);
		  });
		  if(null == start && null == end){
			  layer.alert('请选择时间段');
			  return;
		  }
		  window.location.href=invokeUrl;
	  },
	  export103: function(){
		  var invokeUrl = $("#basePath").val()+'/social/statistics/103/export.jhtm?1=1';
		  var formVal = form.val("st103Index");
		  var start = null;
		  var end = null;
		  $.each(formVal,function(key,val) {
			  if(null == val || '' == val || 'undefined' == val)
				  return true;//continue
			  if('select-start' == key) start = val;
			  if('select-end' == key) end = val;
			  invokeUrl += ("&"+key+"="+val);
		  });
		  if(null == start && null == end){
			  layer.alert('请选择时间段');
			  return;
		  }
		  window.location.href=invokeUrl;
	  },
	  export200: function(){
		  var invokeUrl = $("#basePath").val()+'/social/statistics/200/export.jhtm?1=1';
    	  var formVal = form.val("st200Index");
    	  var start = null;
    	  var end = null;
    	  $.each(formVal,function(key,val) {
    		  if(null == val || '' == val || 'undefined' == val)
    			  return true;//continue
    		  if('select-start' == key) start = val;
    		  if('select-end' == key) end = val;
    		  invokeUrl += ("&"+key+"="+val);
    	  });
    	  if(null == start && null == end){
    		  layer.alert('请选择时间段');
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
  
  //开始日期
  var insStart = laydate.render({
      elem: '#select-start'
      ,min: -365*2
      ,type:'month'
      ,done: function(value, date){
        //更新结束日期的最小日期
        insEnd.config.min = lay.extend({}, date, {
          month: date.month - 1
        });
        
        //自动弹出结束日期的选择器
       insEnd.config.elem[0].focus();
      }
  });
    
  //结束日期
  var insEnd = laydate.render({
      elem: '#select-end'
      ,min: 0
      ,max:365*2
      ,type:'month'
      ,done: function(value, date){
        //更新开始日期的最大日期
        insStart.config.max = lay.extend({}, date, {
          month: date.month - 1
        });
      }
  });
  
  //循环遍历渲染日期组件（所有class包含time的组件）
  $(function(){
	  $('.time').each(function(index){
	        var _this=this;
	        laydate.render({
	            elem: _this,
	            done: function(value, date){
	 
	            }
	        });
	  }); 
  })
  
  laydate.render({
  	elem: '#year',
  	type: 'year'
  });
  
  var selectStr = '['+
  		'{"id":"hosDepart1levelCns","dkey":"depart1List","max":10},'+
  		'{"id":"hosDepart2levelCns","dkey":"depart2List","max":10},'+
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
  
  exports('socialStatistics', {})
});