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
  <title>树组件 - layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">

  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
  
  <style>
    body{padding: 50px;}
    #test1,#test2{margin-bottom: 100px; width: 400px;}
    #LAY-component-grid-all .layui-card-body{display: flex; justify-content: center; flex-direction: column; 
    text-align: center; height: 200px;}
    #div1{height:5000px; background:red; border:5px solid yellow;}
  </style>
</head>
<body>
<div class="layui-btn-container">
  <button type="button" class="layui-btn layuiadmin-btn-admin" lay-watch-btn="getChecked">获取选中节点数据</button>
  <button type="button" class="layui-btn layuiadmin-btn-admin" lay-watch-btn="setChecked">勾选指定节点</button>
  <button type="button" class="layui-btn layuiadmin-btn-admin" lay-watch-btn="openedAll" >舒展全部</button>
  <button type="button" class="layui-btn layuiadmin-btn-admin" lay-watch-btn="closedAll" >收起全部</button>
  <button type="button" class="layui-btn layuiadmin-btn-admin" lay-watch-btn="reload" id="btn_refresh" style="display:none;">重载数据</button>
</div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>组织机构</legend>
</fieldset>
 
<!-- <div id="test9" class="demo-tree demo-tree-box" style="width: 200px; height: 300px; overflow: scroll;"></div> -->
<div id="test9" class="layui-tree"></div>

<script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
<script>
layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
	  index : 'lib/index', //主入口模块
	  branch: 'branch'
  }).use(['index','branch','tree', 'util'], function(){
	  var branch = layui.branch;
	  //branch.hello('World!'); //弹出 Hello World!
	  branch.reload(); //
});
</script>
</body>
</html>