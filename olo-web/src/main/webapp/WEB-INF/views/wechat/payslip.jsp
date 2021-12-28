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
  <title>焦煤集团中央医院</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
  <style type="text/css">
  	.layui-table-cell{
  	height:auto;
  	overflow:auto;
    text-overflow:clip;
    white-space:pre-wrap;
  	}
  	tbody tr .layui-table-cell {
	   height: auto;
	   line-height: 100px;
	}
  </style>
</head>
<body>
  <div class="layui-fluid">   
    <div class="layui-card">
      <div class="layui-card-body">
      	<!-- 隐藏属性 -->
      	<input type="hidden" id="token" name="token" value="${token}">
      	<input type="hidden" id="month" name="month" value="${month}">
        <!-- 数据表格 -->
        <table id="LAY-payslips-manage" lay-filter="LAY-payslips-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
      </div>
    </div>
  </div>

 <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
  }).use(['index', 'table','laypage'], function(){
  		var $ = layui.$,
  		table = layui.table;
  		
  		var heads = [];
  		//加载表头
  		$(function () {
  			$.ajax(
  				{
  				url:"payslip/loadTabHead.jhtm", 
  				async: false, 
  				data:{startMonth:$("#month").val(),endMonth:$("#month").val()},
  				dataType:"JSON",
  				success:function(re){
  					heads = re.heads;
  					table.render({
  			  			elem: '#LAY-payslips-manage',
  			  			url: 'payslip/do.jhtm',
  			  			parseData: function(res){
  			  			    return {
  			  			    	"code": "200"==res.retCode?0:1,
  			  			    	"msg": res.retMsg,
  			  			    	"data": res.retData
  			  			    };
  			  			},
  			  			where: {
  			  				token: $("#token").val(),
  			  				month: $("#month").val()
  			  		    },
  			  		    cols:heads,
  			  		    page: false,
  			  		    text: {
  			  		        none: '暂无相关数据'
  			  		    },
	  			  		done: function (res, curr, count) {
	  			  			$('layui-table[lay-even] tr:nth-child(even)').css({ 'background-color': '#c3cedd'});//81D3F8
	  			  			$('th').css({ 'background-color':'#009688', 'color': 'white', 'font-weight': '500', 'font-size': '15px', 'line-height':'10px' });
	  			  		}
  			  		});
  				},
  				error:function (data) {
  					layer.msg("系统异常，请重试！", {offset: '150px',icon: 2,time: 2000});
  	            }
  			})
  	    });
  	});
  </script>
</body>
</html>