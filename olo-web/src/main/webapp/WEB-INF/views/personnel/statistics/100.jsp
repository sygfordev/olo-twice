<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<%
	String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
    String path = request.getContextPath();
    String basePath = base+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layuiAdmin 后台管理员</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
  <div class="layui-fluid">   
     <!-- 数据表格 -->
     <table id="LAY-statistics-100" lay-filter="LAY-statistics-100"></table>
  </div>
  <style type="text/css">
        .table-header-fixed {
            position: fixed;
            top: 0;
            z-index: 99
        }
    </style>
 <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    excel: 'layui_exts/excel'
  }).use(['index','table','laypage','excel'], function(){
  		var $ = layui.$,
  		excel = layui.excel,
  		table = layui.table;
  		
  		var date = new Date();
  		var time1 = date.toLocaleDateString(); //获取当前日期
  		var time2 = date.getFullYear()+"年"+(date.getMonth()+1)+"月";
  		//表格渲染
  		table.render({
  			elem: '#LAY-statistics-100',
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
    		  	{field: 'htitle', title: time2+'基层单位机构设置、定员及干部职数统计表',align: 'center',colspan:15}
    		  ],
    		  [
      		  	{field: 'sign', title: '填报单位：中央医院&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;填报时间：'+time1,align: 'center',colspan:15}
      		  ],
      		  [
      		  	{field: '序号', title: '序号',width:'8%',align: 'center',rowspan:2},
      		  	{field: '机构名称', title: '机构名称',width:'8%',align: 'center',rowspan:2},
      		  	{field: '在岗', title: '在岗' ,width:'8%',align: 'center',rowspan:2},
      		  	{field: '离职人数', title: '离职人数' ,width:'8%',align: 'center',colspan:6},
      		  	/* {field: '调研员', title: '调研员' ,width:'10%'},
      		  	{field: '协理员', title: '协理员' ,width:'10%'},
      		  	{field: '人力资源市场', title: '人力资源市场' ,width:'10%'},
      			{field: '内退人员', title: '内退人员' ,width:'10%'},
      			{field: '留职', title: '留职' ,width:'10%'},
      			{field: '其他', title: '其他' ,width:'10%'}, */
      			{field: '科级人数', title: '科级人数' ,width:'8%',align: 'center',rowspan:2},
      			{field: '名单', title: '名单' ,width:'8%',align: 'center',colspan:4},
      			/* {field: '正科人数', title: '正科人数' ,width:'10%'},
      			{field: '副科人数', title: '副科人数' ,width:'10%'},
      			{field: '正科级科员', title: '正科级科员' ,width:'10%'},
      			{field: '副科级科员', title: '副科级科员' ,width:'10%'}, */
      			{field: '其他', title: '其他' ,width:'8%',align: 'center',rowspan:2}
      		  ],
      		  [
      			{field: '调研员', title: '调研员' ,width:'6%',align: 'center'},
      		  	{field: '协理员', title: '协理员' ,width:'6%',align: 'center'},
      		  	{field: '人力资源市场', title: '人力资源市场' ,width:'6%',align: 'center'},
      			{field: '内退人员', title: '内退人员' ,width:'6%',align: 'center'},
      			{field: '留职', title: '留职' ,width:'6%',align: 'center'},
      			{field: '其他', title: '其他' ,width:'6%',align: 'center'},
      			{field: '正科人数', title: '正科人数' ,width:'6%',align: 'center'},
      			{field: '副科人数', title: '副科人数' ,width:'6%',align: 'center'},
      			{field: '正科级科员', title: '正科级科员' ,width:'6%',align: 'center'},
      			{field: '副科级科员', title: '副科级科员' ,width:'6%',align: 'center'}
      		  ]
  		    ],
  		  	toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
	  	    defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
	  	        title: '提示'
	  	        ,layEvent: 'LAYTABLE_TIPS'
	  	        ,icon: 'layui-icon-tips'
	  	    }],
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
	              	$("#LAY-statistics-100").next().find('tbody tr[data-index="' + index +
	              	'"]').css("background-color", 0 == tindex?"Wheat":"lightblue");
	            	// 法2
	            	// var i = index + 1;
	            	// $('tr').eq(i).css('background-color', 'lightblue');
	              	}
	          	});
  		    },
  		    page: false,
  		    text: {
  		        none: '暂无相关数据'
  		    }
  		});
  		
  	//头工具栏事件
  		table.on('toolbar(LAY-statistics-100)', function(obj){
  		    var checkStatus = table.checkStatus(obj.config.id);
  		    switch(obj.event){
  		      //导出
  		      case 'LAYTABLE_EXPORT_USER':
  		    	  //遍历列头，找到实际操作的列信息列表
  		    	  /* var headers = new Array(50);
  			  	  table.eachCols('LAY-statistics-100',function(index,item){
  			  	  	if(true != item.hide && "操作" != item.title && "checkbox" != item.type){
  			  	  		//console.log("index:"+index+"--item:"+JSON.stringify(item));
  			  	  		headers[index] = item.title;
  			  	  	}
  			  	  });
  			  	  var headers = headers.filter(s => $.trim(s).length > 0);
  		    	  layui.excel.exportExcel([headers], '表格导出.xlsx', 'xlsx'); */
  		    	/* $.ajax({
  			        url:'export.jhtm',
  			        method:'post',
  			        data:{"ids":ids},
  			        dataType:'json',
  			        success:function(data){
  						JSON.stringify(data);
  			        },
  			        error:function (data) {
  						layer.msg("导出失败！", {offset: '150px',icon: 2,time: 2000});
  			        }
  			    }); */
  			  window.location.href='<%=basePath%>personnel/statistics/100/ex'+'port.jhtm';
  		    	  break;
  		      //自定义头工具栏右侧图标 - 提示
  		      case 'LAYTABLE_TIPS':
  		        layer.alert('这是工具栏右侧自定义的一个图标按钮');
  		        break;
  		    };
  		});
  	});
  </script>
</body>
</html>