layui.define(['table','form','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form;
	
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
  
  exports('socialStatistics200', {})
});