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
        <!-- <form class="layui-form" action="" lay-filter="branch-search-form"> -->
          <div class="layui-inline">
	        <label class="layui-form-label">机构编号</label>
	        <div class="layui-input-block">
	          <input type="text" name="id" placeholder="请输入机构编号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">机构名称</label>
	        <div class="layui-input-block">
	          <input type="text" name="branchName" placeholder="请输入机构名称" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">城市编号</label>
	        <div class="layui-input-block">
	          <input type="text" name="cityNo" placeholder="请输入城市编号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">父级机构</label>
	        <div class="layui-input-block">
	          <select name="parentId">
	             <option value=""}>--所有--</option>
               <c:forEach var="item" items="${parents}" varStatus="status">
                 <option value="${item.id}"}>${item.branchName}</option>
               </c:forEach>
              </select>
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">电话</label>
	        <div class="layui-input-block">
	          <input type="text" name="phone" placeholder="请输入电话" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">邮箱</label>
	        <div class="layui-input-block">
	          <input type="text" name="email" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
	        </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
              <select name="status">
              	<option value="">--所有--</option>
              	<c:forEach var="item" items="${statusList}" varStatus="status">
              		<option value="${item.key}">${item.value}</option>
              	</c:forEach>
              </select>
            </div>
          </div>
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-branchOffice-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
            <!-- <button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
          </div>
        </div>
        <!-- </form> -->
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 0px;">
          <div class="layui-btn-container">
             <div class="layui-btn-group">
               <button class="layui-btn layuiadmin-btn-admin" data-type="add" id="btn-add">增加</button>
               <button class="layui-btn layuiadmin-btn-admin" data-type="expand" id="btn-expand">展开</button>
               <button class="layui-btn layuiadmin-btn-admin" data-type="fold" id="btn-fold">折叠</button>
               <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel" id="btn-batdel">批量删除</button>
               <button class="layui-btn layuiadmin-btn-admin" data-type="refresh" id="btn-refresh">刷新</button>
             </div>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-branchOffice-manage" class="layui-table" lay-filter="LAY-branchOffice-manage"></table>
        <div id="pageDiv"></div>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else {d.status == "1"}}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-branchOffice-manager-operator">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
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
    branch:'branch'
  }).use(['layer','index','branch'], function(){
	  var $ = layui.$
	  branch = layui.branch;
	  
	  branch.renderTable();
	  //branch.pageRender();
  });
  </script>
</body>
</html>