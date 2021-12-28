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
	        <label class="layui-form-label">权限d编号</label>
	        <div class="layui-input-block">
	          <input type="text" name="privId" placeholder="请输入权限编号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">名称-中文</label>
	        <div class="layui-input-block">
	          <input type="text" name="privCnName" placeholder="请输入权限名称-中文" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">名称-英文</label>
	        <div class="layui-input-block">
	          <input type="text" name="privEnName" placeholder="请输入权限名称-英文" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">权限类型</label>
	        <div class="layui-input-block">
	          <select name="privType">
              	<option value="">--所有--</option>
              	<option value="1">菜单</option>
              	<option value="2">功能</option>
              </select>
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">权限方法</label>
	        <div class="layui-input-block">
	          <input type="text" name="privMethod" placeholder="请输入权限方法" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">权限</label>
	        <div class="layui-input-block">
	          <input type="text" name="privPermissioin" placeholder="请输入权限" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">权限Action</label>
	        <div class="layui-input-block">
	          <input type="text" name="privAction" placeholder="请输入权限Action" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">上级权限</label>
	        <div class="layui-input-block">
	          <!-- <input type="text" name="privSuper" placeholder="请输入上级权限" autocomplete="off" class="layui-input"> -->
	          <select name="privSuper">
              	<option value="">--所有--</option>
              	<c:forEach var="item" items="${superList}" varStatus="status">
              		<option value="${item.privId}">${item.privCnName}</option>
              	</c:forEach>
              </select>
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
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-systemPriv-search" data-type="search" id="LAY-systemPriv-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
            <button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-systemPriv-manage" lay-filter="LAY-systemPriv-manage"></table>
        <div id="Lay-page-div"></div>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
        <script type="text/html" id="privTypeTpl">
          {{#  if(d.privType == "1"){ }}
            <button class="layui-btn layui-btn-xs">菜单</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">功能</button>
          {{#  } }}
      	</script>
        <!-- 操作模块 -->
        <script type="text/html" id="table-systemPriv-manager-operator">
          <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
          {{#  if(d.role == '超级管理员'){ }}
            <a class="layui-btn layui-btn-disabled layui-btn-xs"><i class="layui-icon layui-icon-delete"></i>删除</a>
          {{#  } else { }}
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
          {{#  } }}
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
    systemPriv:'systemPriv'
  }).use(['layer','index','systemPriv'], function(){
	  
  });
 </script>
</body>
</html>