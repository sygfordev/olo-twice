layui.define(['table','form','laydate','layer','element','upload','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	layer = layui.layer,
	laypage = layui.laypage,
	laydate = layui.laydate,
	element = layui.element,
	upload = layui.upload,
	form = layui.form;
	
	table.render({
		elem: '#LAY-payslip-statistics206-manage',
		url: 'dostatis.jhtm',
		parseData: function(res){
		    return {
		    	"code": "200"==res.statusCode?0:1,
		    	"msg": res.message,
		    	//"count": res.count,
		    	"data": res.data
		    };
		},
		where: {
			ymonth: $("#ymonth").val()
	    },
	    cols: 
	    [
	    	[
		    	  {field: '', title: $("#ymonth").val()+'工资汇总表',align:"center",colspan:23}
		    ],
	    	[
				  {field: 'sa_sum_project', title: '部门' ,fixed:'left',width:'10%'},
				  {field: 'size', title: '人数' ,fixed:'left',width:'10%'},
				  {field: 'wage_posit_totals', title: '岗位工资' ,templet:function(data){return parseFloat(data.wage_posit_totals).toFixed(2);},width:'10%'},
				  {field: 'wage_night_shifts', title: '夜班费' ,templet:function(data){return parseFloat(data.wage_night_shifts).toFixed(2);},width:'10%'},
				  {field: 'wage_overtime_totals', title: '加班工资' ,templet:function(data){return parseFloat(data.wage_night_shifts).toFixed(2);},width:'10%'},
				  {field: 'wage_yearg_totals', title: '年功工资' ,templet:function(data){return parseFloat(data.wage_yearg_totals).toFixed(2);},width:'10%'},
				  {field: 'allowance_hygiene_totals', title: '卫生津贴' ,templet:function(data){return parseFloat(data.allowance_hygiene_totals).toFixed(2);},width:'10%'},
				  {field: 'supp_4oth_totals', title: '其他' ,templet:function(data){return parseFloat(data.supp_4oth_totals).toFixed(2);},width:'10%'},
				  {field: 'wage_totals', title: '工资小计' ,templet:function(data){return parseFloat(data.wage_totals).toFixed(2);},width:'10%'},
			      {field: 'wage_jx_totals', title: '绩效工资合计',templet:function(data){return parseFloat(data.wage_jx_totals).toFixed(2);},width:'10%'},
			      {field: 'wage_pageable_totals', title: '应发合计',templet:function(data){return parseFloat(data.wage_pageable_totals).toFixed(2);},width:'10%'},
			      {field: 'bx_pension_totals', title: '养老保险',templet:function(data){return parseFloat(data.bx_pension_totals).toFixed(2);},width:'10%'},
			      {field: 'bx_medical_totals', title: '医疗保险',templet:function(data){return parseFloat(data.bx_medical_totals).toFixed(2);},width:'10%'},
			      {field: 'bx_unemp_totals', title: '失业保险',templet:function(data){return parseFloat(data.bx_unemp_totals).toFixed(2);},width:'10%'},
			      {field: 'bx_serious_totals', title: '大病保险',templet:function(data){return parseFloat(data.bx_serious_totals).toFixed(2);},width:'10%'},
			      {field: 'bx_housfund_totals', title: '住房公积金',templet:function(data){return parseFloat(data.bx_housfund_totals).toFixed(2);},width:'10%'},
			      {field: 'bx_annual_totals', title: '企业年金',templet:function(data){return parseFloat(data.bx_annual_totals).toFixed(2);},width:'10%'},
			      {field: 'bx_cut_totals', title: '社保扣款合计',templet:function(data){return parseFloat(data.bx_cut_totals).toFixed(2);},width:'10%'},
			      {field: 'cut_other_totals', title: '其他扣款',templet:function(data){return parseFloat(data.cut_other_totals).toFixed(2);},width:'10%'},
			      {field: 'cut_arrears_totals', title: '职工欠款',templet:function(data){return parseFloat(data.cut_arrears_totals).toFixed(2);},width:'10%'},
			      {field: 'cut_totals', title: '扣款合计',templet:function(data){return parseFloat(data.cut_totals).toFixed(2);},width:'10%'},
			      {field: 'tax_income_totals', title: '个税',templet:function(data){return parseFloat(data.tax_income_totals).toFixed(2);},width:'10%'},
			      {field: 'net_salary_totals', title: '实发合计',templet:function(data){return parseFloat(data.net_salary_totals).toFixed(2);},width:'10%'}
			  ]
	    ],
	    cellMinWidth:'100',
	    toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
  	    defaultToolbar: ['filter','exports', 'print'],
	    page: false,
	    limits: [10,15,20,30,50],
	    limit: 10,
	    done:function (res,curr,count){
		    //$('th').css({'font-size':'14','font-weight':'bold','color':'green','border-color':'#aaa'});
	    	//渲染背景色
            $.each(res.data, function (index, item) {
            	var tindex = item.LAY_TABLE_INDEX;
            	if (item.sa_sum_project == "合计") {
	              	$("#LAY-payslip-statistics206-manage").next().find('tbody tr[data-index="' + index +
	              	'"]').css("background-color", res.data.length-1 == tindex?"Wheat":"lightblue");
              	}
          	});
	    },
	    text: {
	        none: '暂无相关数据'
	    }
  });
	//头工具栏事件
  table.on('toolbar(LAY-payslip-statistics206-manage)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    switch(obj.event){
	      //导出
	      case 'LAYTABLE_EXPORT_USER':
	    	  var ymonth = $("#ymonth").val();
	    	  if(null == ymonth || '' == ymonth || 'undefined' == ymonth){
	    		  layer.alert('请选择薪资年月');
	    		  return;
	    	  }
	    	  layer.alert('导出完成请不要重复操作');
	    	  window.location.href=$("#basePath").val()+'/salary/statistics/ex'+'port206.jhtm?ymonth='+$("#ymonth").val();
	    	  break;
	      //自定义头工具栏右侧图标 - 提示
	      case 'LAYTABLE_TIPS':
	        layer.alert('这是工具栏右侧自定义的一个图标按钮');
	        break;
	    };
  });
  
  //监听搜索
  form.on('submit(LAY-payslip-statistics206-search)', function(data){
  	  var field = data.field;
  	var xx =  
    	  [
  	      [
  	    	  {field: '', title: $("#ymonth").val()+'工资汇总表',align:"center",colspan:23}
  	      ],
  	  	  [
  			  {field: 'sa_sum_project', title: '部门' ,width:'10%'},
  			  {field: 'size', title: '人数' ,width:'10%'},
  			  {field: 'wage_posit_totals', title: '岗位工资' ,templet:function(data){return parseFloat(data.wage_posit_totals).toFixed(2);},width:'10%'},
  			  {field: 'wage_night_shifts', title: '夜班费' ,templet:function(data){return parseFloat(data.wage_night_shifts).toFixed(2);},width:'10%'},
  			  {field: 'wage_overtime_totals', title: '加班工资' ,templet:function(data){return parseFloat(data.wage_night_shifts).toFixed(2);},width:'10%'},
  			  {field: 'wage_yearg_totals', title: '年功工资' ,templet:function(data){return parseFloat(data.wage_yearg_totals).toFixed(2);},width:'10%'},
  			  {field: 'allowance_hygiene_totals', title: '卫生津贴' ,templet:function(data){return parseFloat(data.allowance_hygiene_totals).toFixed(2);},width:'10%'},
  			  {field: 'supp_4oth_totals', title: '其他' ,templet:function(data){return parseFloat(data.supp_4oth_totals).toFixed(2);},width:'10%'},
  			  {field: 'wage_totals', title: '工资小计' ,templet:function(data){return parseFloat(data.wage_totals).toFixed(2);},width:'10%'},
  		      {field: 'wage_jx_totals', title: '绩效工资合计',templet:function(data){return parseFloat(data.wage_jx_totals).toFixed(2);},width:'10%'},
  		      {field: 'wage_pageable_totals', title: '应发合计',templet:function(data){return parseFloat(data.wage_pageable_totals).toFixed(2);},width:'10%'},
  		      {field: 'bx_pension_totals', title: '养老保险',templet:function(data){return parseFloat(data.bx_pension_totals).toFixed(2);},width:'10%'},
  		      {field: 'bx_medical_totals', title: '医疗保险',templet:function(data){return parseFloat(data.bx_medical_totals).toFixed(2);},width:'10%'},
  		      {field: 'bx_unemp_totals', title: '失业保险',templet:function(data){return parseFloat(data.bx_unemp_totals).toFixed(2);},width:'10%'},
  		      {field: 'bx_serious_totals', title: '大病保险',templet:function(data){return parseFloat(data.bx_serious_totals).toFixed(2);},width:'10%'},
  		      {field: 'bx_housfund_totals', title: '住房公积金',templet:function(data){return parseFloat(data.bx_housfund_totals).toFixed(2);},width:'10%'},
  		      {field: 'bx_annual_totals', title: '企业年金',templet:function(data){return parseFloat(data.bx_annual_totals).toFixed(2);},width:'10%'},
  		      {field: 'bx_cut_totals', title: '社保扣款合计',templet:function(data){return parseFloat(data.bx_cut_totals).toFixed(2);},width:'10%'},
  		      {field: 'cut_other_totals', title: '其他扣款',templet:function(data){return parseFloat(data.cut_other_totals).toFixed(2);},width:'10%'},
  		      {field: 'cut_arrears_totals', title: '职工欠款',templet:function(data){return parseFloat(data.cut_arrears_totals).toFixed(2);},width:'10%'},
  		      {field: 'cut_totals', title: '扣款合计',templet:function(data){return parseFloat(data.cut_totals).toFixed(2);},width:'10%'},
  		      {field: 'tax_income_totals', title: '个税',templet:function(data){return parseFloat(data.tax_income_totals).toFixed(2);},width:'10%'},
  		      {field: 'net_salary_totals', title: '实发合计',templet:function(data){return parseFloat(data.net_salary_totals).toFixed(2);},width:'10%'}
  		  ]
  	  ];
  	  table.reload('LAY-payslip-statistics206-manage', {where: field,cols:xx});
  });
  
  laydate.render({
	    elem: '#ymonth',
	    format: 'yyyy-MM',
	    type:"month"
  });
  
  //自定义验证
  form.verify({
	  idcard: function(value){
			if(null == value)
				return '身份证号不能为空!';
			if(value.length > 25)
				return '身份证号长度不能超过25';
	  },
	  idcard:[/(^$)|(^\d{15}$)|(^\d{17}(x|X|\d)$)/,'请输入正确的身份证号'],
  });
  exports('paystatics206', {})
});