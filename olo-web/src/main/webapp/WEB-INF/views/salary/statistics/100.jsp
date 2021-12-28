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
	        <label class="layui-form-label">姓名</label>
	        <div class="layui-input-inline">
	          <input type="text" id="name" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">身份证号</label>
	        <div class="layui-input-inline">
	          <input type="text" id="cardNo" name="cardNo" lay-verify="idcard" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">人员类别</label>
	        <div class="layui-input-inline">
	          <select id="wkModalityCn" name="wkModalityCn" >
              	<option value=""></option>
              	<c:forEach var="item" items="${modalityList}" varStatus="status">
              		<option value="${item.key}">${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">人员部门</label>
	        <div class="layui-input-inline">
	          <select id="departClassCn" name="departClassCn">
              	<option value=""></option>
              	<c:forEach var="item" items="${departClassList}" varStatus="status">
              		<option value="${item.key}">${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
          <div class="layui-inline">
		      <label class="layui-form-label">起始年月</label>
		      <div class="layui-input-inline">
		        <input class="layui-input" type="text" name="startMonth" id="startMonth"  autocomplete="off" placeholder="开始月份">
		      </div>
		      <label class="layui-form-label">结束年月</label>
		      <div class="layui-input-inline">
		        <input class="layui-input" type="text" name="endMonth" id="endMonth" autocomplete="off" placeholder="结束月份">
		      </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-payslip-statistics-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      	<div id="toolbarDemo"></div>
        <!-- 数据表格 -->
        <table id="LAY-payslip-statistics-manage" name="LAY-payslip-statistics-manage" lay-filter="LAY-payslip-statistics-manage"></table>
      </div>
    </div>
  </div>
  
 <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    paystatics100: 'salary/paystatics100'
  }).use(['index','paystatics100','table','laypage'], function(){
  		var $ = layui.$;
  		
  	});
  </script>
</body>
</html>