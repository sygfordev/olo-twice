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
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
  <div class="layui-fluid">   
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
      	<!-- 字典主键 -->
      	
        <div class="layui-form-item">
		  <div class="layui-inline">
	        <label class="layui-form-label">字典名称</label>
	        <div class="layui-input-block">
	            <input type="hidden" id="dictId" name="dictId" value="${dictId}" readonly class="layui-input">
      			<input type="text" id="dictName" name="dictName" value="${dictName}" class="layui-input" readonly>
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">字典项Key</label>
	        <div class="layui-input-block">
	          <input type="text" id="itemKey" name="itemKey" placeholder="请输入字典项Key" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">字典项Val</label>
	        <div class="layui-input-block">
	          <input type="text" id="itemVal" name="itemVal" placeholder="请输入字典项Val" autocomplete="off" class="layui-input">
	        </div>
          </div>
       </div>
       <div class="layui-form-item">
          <div class="layui-inline">
	        <label class="layui-form-label">存在父项</label>
	        <div class="layui-input-block">
	          <select name="existSuper" lay-filter="existSuper">
	          	<option></option>
              	<c:forEach var="item" items="${yesNoList}" varStatus="status">
              		<option value="${item.key}">${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
          <div class="layui-inline layui-hide" id="superIdDiv">
	        <label class="layui-form-label">父项编号</label>
	        <div class="layui-input-block">
	          	<select id="superId" name="superId">
                  <c:forEach var="item" items="${superList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
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
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-systemDictItem-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="systemDictItem:add">
			<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
		  	</shiro:hasPermission>
          	<shiro:hasPermission name="systemDictItem:batchdel">
			<button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
		  	</shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-systemDictItem-manage" lay-filter="LAY-systemDictItem-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
        <script type="text/html" id="yesNoTpl">
          {{#  if(d.existSuper == "0"){ }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">否</button>
          {{#  } else { }}
			<button class="layui-btn layui-btn-xs">是</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-systemDictItem-manager-operator">
          <shiro:hasPermission name="systemDictItem:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="systemDictItem:del">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
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
    systemDictItem: 'systemDictItem'
  }).use(['index', 'systemDictItem', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>