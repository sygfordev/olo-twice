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
  <meta charset="UTF-8">
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
      <!-- <div class="layui-card-header">编辑角色</div> -->
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="role-edit-form">
          <input type="hidden" name="roleId" value="${model.roleId}">
        	
          <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">角色名称</label>
	            <div class="layui-input-inline">
	            	<input type="text" name="roleName" value="${model.roleName}" disabled lay-verify="required|roleName" autocomplete="off" placeholder="请输入角色名称" class="layui-input">
	            </div>
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">角色级别</label>
              <div class="layui-input-inline">
              	<input type="radio" name="roleLevel" value="高级" title="高级" ${model.roleLevel eq '高级'?'checked' : ''}>
              	<input type="radio" name="roleLevel" value="中级" title="中级" ${model.roleLevel eq '中级'?'checked' : ''}>
              	<input type="radio" name="roleLevel" value="初级" title="初级" ${model.roleLevel eq '初级'?'checked' : ''}>
              </div>
            </div>
          </div>
          
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select name="status">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea">${model.remark}</textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-role-edit-submit" id="LAY-role-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-role-edit-cancel" id="LAY-role-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>

    
  <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '<%=basePath%>layuiadmin/'
  }).extend({
    index: 'lib/index', //主入口模块
    systemRole: 'systemRole'
  }).use(['index', 'form', 'laydate', 'systemRole'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'role-edit-form');
  });
  </script>
</body>
</html>