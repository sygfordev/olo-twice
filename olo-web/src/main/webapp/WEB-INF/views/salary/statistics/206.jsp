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
  <title>工资统计</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
  <div class="layui-fluid">   
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto" ><!-- style="white-space:nowrap!important;" -->
      	<!-- 隐藏属性 -->
        <input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
       	
        <div class="layui-form-item">
          <div class="layui-inline">
		      <label class="layui-form-label">薪资年月</label>
		      <div class="layui-input-inline">
		        <input class="layui-input" type="text" name="ymonth" id="ymonth"  autocomplete="off" placeholder="薪资年月">
		      </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-payslip-statistics206-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      	<div id="toolbarDemo"></div>
        <!-- 数据表格 -->
        <table id="LAY-payslip-statistics206-manage" name="LAY-payslip-statistics206-manage" lay-filter="LAY-payslip-statistics206-manage"></table>
      </div>
    </div>
  </div>
  
 <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    paystatics206: 'salary/paystatics206'
  }).use(['index','paystatics206','table','laypage'], function(){
  		var $ = layui.$;
  		
  	});
  </script>
</body>
</html>