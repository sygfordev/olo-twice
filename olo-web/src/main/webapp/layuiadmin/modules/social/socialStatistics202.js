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
		elem: '#LAY-social-202-manage',
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
	    	  {field: '', title: '社保计划表',align:'center',colspan:36,width:'8%'},
	      ],
	      [
	    	  {field: 'name', title: '姓名',align:'center',rowspan:2,width:'8%'},
	    	  {field: 'card_no', title: '身份证号',align:'center',rowspan:2,width:'12%'},
	    	  {field: 'start_time', title: '查询开始时间',align:'center',rowspan:2,width:'8%'},
	    	  {field: 'end_time', title: '查询结束时间',align:'center',rowspan:2,width:'8%'},
	    	  {field: 'work_area_cn', title: '工作地域',align:'center',rowspan:2,width:'8%'},
	    	  {field: 'hos_depart_1level_cn', title: '一级科室',align:'center',rowspan:2,width:'10%'},
	    	  {field: 'hos_depart_2level_cn', title: '二级科室',align:'center',rowspan:2,width:'10%'},
	    	  {field: 'size', title: '人数',align:'center',rowspan:2,width:'8%'},
	    	  {field: '', title: '单位应缴金额',align:'center',colspan:7,width:'8%'},
	    	  {field: '', title: '个人应缴金额',align:'center',colspan:7,width:'8%'},
	    	  {field: '', title: '单位实缴金额',align:'center',colspan:7,width:'8%'},
	    	  {field: '', title: '个人实缴金额',align:'center',colspan:7,width:'8%'}
	      ],
	      [
	    	  //单位应缴
	    	  {field: 'comp_pen', title: '养老',align:'center',templet:function(data){return parseFloat(data.comp_pen).toFixed(2);},width:'8%'},
	    	  {field: 'comp_medi', title: '医疗',align:'center',templet:function(data){return parseFloat(data.comp_medi).toFixed(2);},width:'8%'},
	    	  {field: 'comp_injury', title: '工伤',align:'center',templet:function(data){return parseFloat(data.comp_injury).toFixed(2);},width:'8%'},
	    	  {field: 'comp_unemp', title: '失业',align:'center',templet:function(data){return parseFloat(data.comp_unemp).toFixed(2);},width:'8%'},
	    	  {field: 'comp_ovp', title: '省统筹',align:'center',templet:function(data){return parseFloat(data.comp_ovp).toFixed(2);},width:'8%'},
	    	  {field: 'comp_spmedi', title: '补充医疗',align:'center',templet:function(data){return parseFloat(data.comp_spmedi).toFixed(2);},width:'8%'},
	    	  {field: 'comp_serious', title: '大病医疗',align:'center',templet:function(data){return parseFloat(data.comp_serious).toFixed(2);},width:'8%'},
	    	  //个人应缴
	    	  {field: 'pers_pen', title: '养老',align:'center',templet:function(data){return parseFloat(data.pers_pen).toFixed(2);},width:'8%'},
	    	  {field: 'pers_medi', title: '医疗',align:'center',templet:function(data){return parseFloat(data.pers_medi).toFixed(2);},width:'8%'},
	    	  {field: 'pers_injury', title: '工伤',align:'center',templet:function(data){return parseFloat(data.pers_injury).toFixed(2);},width:'8%'},
	    	  {field: 'pers_unemp', title: '失业',align:'center',templet:function(data){return parseFloat(data.pers_unemp).toFixed(2);},width:'8%'},
	    	  {field: 'pers_ovp', title: '省统筹',align:'center',templet:function(data){return parseFloat(data.pers_ovp).toFixed(2);},width:'8%'},
	    	  {field: 'pers_spmedi', title: '补充医疗',align:'center',templet:function(data){return parseFloat(data.pers_spmedi).toFixed(2);},width:'8%'},
	    	  {field: 'pers_serious', title: '大病医疗',align:'center',templet:function(data){return parseFloat(data.pers_serious).toFixed(2);},width:'8%'},
	    	  //单位实缴
	    	  {field: 'comp_recap_pen', title: '养老',align:'center',templet:function(data){return parseFloat(data.comp_recap_pen).toFixed(2);},width:'8%'},
	    	  {field: 'comp_recap_medi', title: '医疗',align:'center',templet:function(data){return parseFloat(data.comp_recap_medi).toFixed(2);},width:'8%'},
	    	  {field: 'comp_recap_injury', title: '工伤',align:'center',templet:function(data){return parseFloat(data.comp_recap_injury).toFixed(2);},width:'8%'},
	    	  {field: 'comp_recap_unemp', title: '失业',align:'center',templet:function(data){return parseFloat(data.comp_recap_unemp).toFixed(2);},width:'8%'},
	    	  {field: 'comp_recap_ovp', title: '省统筹',align:'center',templet:function(data){return parseFloat(data.comp_recap_ovp).toFixed(2);},width:'8%'},
	    	  {field: 'comp_recap_spmedi', title: '补充医疗',align:'center',templet:function(data){return parseFloat(data.comp_recap_spmedi).toFixed(2);},width:'8%'},
	    	  {field: 'comp_recap_serious', title: '大病医疗',align:'center',templet:function(data){return parseFloat(data.comp_recap_serious).toFixed(2);},width:'8%'},
	    	  //个人实缴
	    	  {field: 'pers_recap_pen', title: '养老',align:'center',templet:function(data){return parseFloat(data.pers_recap_pen).toFixed(2);},width:'8%'},
	    	  {field: 'pers_recap_medi', title: '医疗',align:'center',templet:function(data){return parseFloat(data.pers_recap_medi).toFixed(2);},width:'8%'},
	    	  {field: 'pers_recap_injury', title: '工伤',align:'center',templet:function(data){return parseFloat(data.pers_recap_injury).toFixed(2);},width:'8%'},
	    	  {field: 'pers_recap_unemp', title: '失业',align:'center',templet:function(data){return parseFloat(data.pers_recap_unemp).toFixed(2);},width:'8%'},
	    	  {field: 'pers_recap_ovp', title: '省统筹',align:'center',templet:function(data){return parseFloat(data.pers_recap_ovp).toFixed(2);},width:'8%'},
	    	  {field: 'pers_recap_spmedi', title: '补充医疗',align:'center',templet:function(data){return parseFloat(data.pers_recap_spmedi).toFixed(2);},width:'8%'},
	    	  {field: 'pers_recap_serious', title: '大病医疗',align:'center',templet:function(data){return parseFloat(data.pers_recap_serious).toFixed(2);},width:'8%'}
	      ]
	    ],
	    done:function (res,curr,count){
		    	$('th').css({'font-size':'14','font-weight':'bold','color':'green','border-color':'#aaa'});
		    	//$('td').css({'border-color':'#aaa','background-color': 'lightblue'});
		    	var headertop = $(".layui-table-header").offset().top//获取表头到文档顶部的距离
            $(window).scroll(function () {//开始监听滚动条                         
                if (headertop - $(window).scrollTop() < 0) {    //超过了                              
                   $(".layui-table-header").addClass('table-header-fixed')//添加样式，固定住表头
                }else {//没超过
                    $(".layui-table-header").removeClass('table-header-fixed')//移除样式
                }
            })
            //渲染背景色
            $.each(res.data, function (index, item) {
            	var tindex = item.LAY_TABLE_INDEX;
            	if (item.doStyle == true) {
             	// 法1
              	$("#LAY-social-202-manage").next().find('tbody tr[data-index="' + index +
              	'"]').css("background-color", 0 == tindex?"Wheat":"lightblue");
            	// 法2
            	// var i = index + 1;
            	// $('tr').eq(i).css('background-color', 'lightblue');
              	}
          	});
		},
	    page: false,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});

	//监听搜索
	form.on('submit(LAY-social-202-search)', function(data){
		  var field = data.field;
		  var map = {};
		  $.each(field,function(key,val) {
			  if(null == val || '' == val || 'undefined' == val || key.endWith("List"))
    			  return true;//continue
    		  map[key] = val;
    	  });
		  //执行重载
		  table.reload('LAY-social-202-manage', {where: map});
	});
	
	String.prototype.endWith=function(oString){  
		var reg=new RegExp(oString+"$");  
		return reg.test(this);     
	}  
  //---------表头按钮区域----------
  var active = {
	  export202: function(){
		  var invokeUrl = $("#basePath").val()+'/social/statistics/202/export.jhtm?1=1';
    	  var formVal = form.val("st202Index");
    	  var startMonth = null;
    	  var endMonth = null;
    	  $.each(formVal,function(key,val) {
    		  if(null == val || '' == val || 'undefined' == val || key.endWith("List"))
    			  return true;//continue
    		  if('startMonth' == key) startMonth = val;
    		  if('endMonth' == key) endMonth = val;
    		  invokeUrl += ("&"+key+"="+val);
    	  });
    	  if(null == startMonth && null == endMonth){
    		  layer.alert('请选择统计区间');
    		  return;
    	  }
    	  window.location.href=invokeUrl;
	  }
  }
  
  function contains(arr, obj) {
	  var i = arr.length;
	  while (i--) {
        if (arr[i] === obj) {
            return true;
        }
	  }
	  return false;
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
  		'{"id":"wkModalityCns","dkey":"modalityList","max":10},'+
  		'{"id":"hosDepart1levelCns","dkey":"depart1List","max":10},'+
  		'{"id":"hosDepart2levelCns","dkey":"depart2List","max":10},'+
  		'{"id":"wkAreasCns","dkey":"wkareasList","max":10}'+
  		']';
  
  $(function(){
	  var keys = JSON.parse(selectStr);
	  var list = null;
	  $.each(keys,function(index,value) {
		  list = $("#"+value.dkey).val();
		  console.log(list.length);
		  selectM({//职务
		      elem: '#'+value.id
		      ,data: (null == list || "" == list || "undefined" == list || "[]" == list)?[]:JSON.parse($("#"+value.dkey).val())
		      ,field: {idName:'key',titleName:'value'}
		      ,max:value.max
		      ,width:300
		      ,delimiter: ','
		  });	
	  });
  })
  
  //开始日期
  var insStart = laydate.render({
      elem: '#startMonth'
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
      elem: '#endMonth'
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
  
  exports('socialStatistics202', {})
});