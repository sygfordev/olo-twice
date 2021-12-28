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
  <title>角色添加</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
<input type="hidden" id="roleId" value="${model.roleId}">
<input type="hidden" id="selectedIds" value="">
<div class="layui-btn-container">
  <button type="button" class="layui-btn" data-type="getData">获取右侧数据</button>
  <button type="button" class="layui-btn" data-type="reload">重载实例</button>
</div>
<div id="test7" class="demo-transfer" style="margin-left:12px;"></div>
<script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
<script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    systemRole: 'userRoleCfg'
  }).use(['index', 'userRoleCfg', 'table','laypage'], function(){
  		var $ = layui.$;
  });
</script>
</body>
</html>