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
	  <legend>统计面板</legend>
	</fieldset>
	<div class="layui-bg-gray" style="padding: 30px;">
	  <div class="layui-row layui-col-space15">
	    <div class="layui-col-md6">
	      <div class="layui-card">
	        <div class="layui-card-header">汇总报表</div>
	        <div class="layui-card-body">
				<a onclick="onAClick(100);">养老保险按月汇总表</a><br><br>
				<a onclick="onAClick(101);">医疗保险按月汇总表</a><br><br>
				<a onclick="onAClick(102);">失业保险按月汇总表</a><br><br>
				<a onclick="onAClick(103);">工伤保险按月汇总表</a><br><br>
	          	<a onclick="onAClick(104);">用工形式应缴汇总表</a><br>
	        </div>
	      </div>
	    </div>
	    <div class="layui-col-md6">
	      <div class="layui-card">
	        <div class="layui-card-header">其他报表</div>
	        <div class="layui-card-body">
	          	<a onclick="onAClick(200);">社保参保人数月统计表</a><br><br>
	          	<a onclick="onAClick(201);">年度社保征缴明细表</a><br><br>
	          	<a onclick="onAClick(202);">社保计划表</a><br>
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
  		
  		var kvm = new Map();
  		kvm.set(100,"养老保险按月汇总表");
  		kvm.set(101,"医疗保险按月汇总表");
  		kvm.set(102,"失业保险按月汇总表");
  		kvm.set(103,"工伤保险按月汇总表");
  		kvm.set(104,"用工形式应缴汇总表");
  		kvm.set(200,"社保参保人数月统计表");
  		kvm.set(201,"年度社保征缴明细表");
  		kvm.set(202,"社保计划表");
		
  		window.onAClick = function(id){
  			//子页面打开新的tab页面
  		  	var l = parent === self ? layui : top.layui;
  		  	l.index.openTabsPage('social/statistics/'+id+'/tostatis.jhtm', kvm.get(id));
  		}
  	});
  </script>
</body>
</html>