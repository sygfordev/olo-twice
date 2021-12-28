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
  <title>表单组合</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
	
  <div class="layui-fluid">
    <div class="layui-card">
      <!-- <div class="layui-card-header">添加角色</div> -->
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="userRole-add-form">
          <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">用户编号：</label>
	            <div class="layui-input-inline">
	              <input type="text" name="userId" value="${model.userId}" class="layui-input" readonly>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">真实姓名：</label>
	            <div class="layui-input-inline">
	              <input type="text" name="userId" value="${model.userRealName}" class="layui-input" readonly>
	            </div>
            </div>
          </div>
          <div class="layui-form-item">
            <label class="layui-form-label">可用角色：</label>
            <div class="layui-input-inline">
              <select  id="selectedRoleId" name="selectedRoleId">
				<c:forEach var="item" items="${list}">
					<option value="${item.roleId}">${item.roleName}</option>
				</c:forEach>
			  </select>
            </div>
            <div class="layui-form-mid layui-word-aux">${msg}</div>
          </div>
        </form>
      </div>
    </div>
  </div>

    
  <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    userInfo: 'userInfo'
  }).use(['index', 'form','userInfo'], function(){
    var $ = layui.$
    ,form = layui.form;
    
    form.render(null, 'userRole-add-form');
  });
  </script>
</body>
</html>