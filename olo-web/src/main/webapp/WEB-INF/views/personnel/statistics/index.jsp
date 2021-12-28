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
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>卡片面板</legend>
	</fieldset>
	<div class="layui-bg-gray" style="padding: 30px;">
	  <div class="layui-row layui-col-space15">
	    <div class="layui-col-md6">
	      <div class="layui-card">
	        <div class="layui-card-header">编制报表</div>
	        <div class="layui-card-body">
				<a id="bianzhi" onclick="onAClick(100);">中央医院机构编制统计表</a><br>
	          	......
	        </div>
	      </div>
	    </div>
	    <div class="layui-col-md6">
	      <div class="layui-card">
	        <div class="layui-card-header">职称报表</div>
	        <div class="layui-card-body">
	          	<!-- <a id="title" onclick="onAClick(200);">职称比例统计表</a><br> -->
	          	......
	        </div>
	      </div>
	    </div>
	  </div>
	</div> 
  </div>
  
 <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
  }).use(['index','table','laypage'], function(){
  		var $ = layui.$;
  		

  		window.onAClick = function(id){
  			//子页面打开新的tab页面
  		  	var l = parent === self ? layui : top.layui;
  		  	var title = (id === 100)?"中央医院机构编制统计表":"职称比例统计表";
  		  	l.index.openTabsPage('personnel/statistics/'+id+'/tostatis.jhtm', title);
  		}
  	});
  </script>
</body>
</html>