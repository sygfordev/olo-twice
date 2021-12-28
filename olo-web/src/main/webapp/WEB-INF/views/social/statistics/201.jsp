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
  <style type="text/css">
  	.layui-table-cell{
  	height:auto;
  	overflow:auto;
    text-overflow:clip;
    white-space:pre-wrap;
  	}
  </style>
</head>
<body>
  <div class="layui-fluid">   
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="st201Index">
      <!-- 隐藏属性 -->
      <input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
      <input id="modalityList" name="modalityList" type="hidden" value='${modalityList}'>
      
        <div class="layui-form-item">
		  <div class="layui-inline">
          	<label class="layui-form-label">统计年份</label>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="year" readonly="" name="year" placeholder="统计年份">
	        </div>
	      </div>
	      <div class="layui-inline">
          	<label class="layui-form-label">用工形式</label>
	        <div class="layui-input-block" id="wkModalityCns"></div>
	      </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-social-201-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="export201">导出</button>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-social-201-manage" lay-filter="LAY-social-201-manage"></table>
        
      </div>
    </div>
  </div>

 <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    socialStatistics201: 'social/socialStatistics201'
  }).use(['index', 'socialStatistics201', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>