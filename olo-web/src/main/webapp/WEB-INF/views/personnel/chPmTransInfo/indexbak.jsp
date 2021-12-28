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
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        
		  <div class="layui-inline">
	        <label class="layui-form-label">姓名</label>
	        <div class="layui-input-block">
	          <input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">身份证号</label>
	        <div class="layui-input-block">
	          <input type="text" name="cardNo" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  
		  <div class="layui-inline">
	        <label class="layui-form-label">调动文号</label>
	        <div class="layui-input-block">
	          <input type="text" name="transDocno" placeholder="请输入调动文号" autocomplete="off" class="layui-input">
	        </div>
          </div>
          
          <div class="layui-inline">
          	<label class="layui-form-label">调动时间</label>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="trans-start" name="trans-start" placeholder="开始日期">
	        </div>
	        <div class="layui-form-mid">
	          -
	        </div>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="trans-end" name="trans-end" placeholder="结束日期">
	        </div>
	      </div>
          
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chPmTransInfo-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmTransInfo:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmTransInfo:batchdel">
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
            </shiro:hasPermission>
            <button class="layui-btn" data-type="parseTable">立即转化为数据表格</button>
			<button class="layui-btn" data-type="parseTable" onclick="convertToTable()">将数据转化为静态表格</button>
			<button class="layui-btn" data-type="parseTableToExcel" onclick="exportToExcel()">导出Excel</button>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmTransInfo-manage" lay-filter="LAY-chPmTransInfo-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmTransInfo-manager-operator">
          <shiro:hasPermission name="chPmTransInfo:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmTransInfo:delete">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmTransInfo:details">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="details"><i class="layui-icon layui-icon-read"></i>详情</a>
		  </shiro:hasPermission>
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
    chPmTransInfo: 'chPmTransInfo'
  }).use(['index', 'chPmTransInfo', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>