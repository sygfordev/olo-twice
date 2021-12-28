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
  <title>角色管理</title>
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
          	<label class="layui-form-label">用户编号：</label>
          	<div class="layui-input-block">
            	${model.userId}
            	<input type="hidden" id="userId" name="userId" value="${model.userId}" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">真实姓名：</label>
            <div class="layui-input-block">
            	${model.userRealName}
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">机构：</label>
            <div class="layui-input-block">
            	${model.branch.id}【${model.branch.branchName}】
            </div>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
            <button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-userRolePrivCfg-manage" lay-filter="LAY-userRolePrivCfg-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
        <script type="text/html" id="sexTpl">
          {{#  if(d.userSex == "1"){ }}
            	男
          {{#  } else if(d.userSex == "0") { }}
           		女
          {{#  } }}
        </script>
        
        
        <!-- 操作模块 -->
        <script type="text/html" id="table-role-manager-operator">
		  <shiro:hasPermission name="userInfo:userRoleDelete">
		  	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="userInfo:userRolePrivEdit">
		  	<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="userRolePrivCfg"><i class="layui-icon layui-icon-set-fill"></i>权限配置</a>
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
    userRolePrivCfg: 'userRolePrivCfg'
  }).use(['index', 'userRolePrivCfg', 'table','laypage'], function(){
  		var $ = layui.$;
  	    
  });
  </script>
</body>
</html>