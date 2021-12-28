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
      <!-- <div class="layui-card-header">编辑</div> -->
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="comTabLog-edit-form">
          <input type="hidden" name="logId" value="${model.logId}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">机构编号</label>
	            <div class="layui-input-inline">
	              <input type="text" name="branchId" value="${model.branchId}" lay-verify="required|branchId" autocomplete="off" placeholder="请输入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">机构名称</label>
	            <div class="layui-input-inline">
	              <input type="text" name="branchName" value="${model.branchName}" lay-verify="required|branchName" autocomplete="off" placeholder="请输入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">用户编号</label>
	            <div class="layui-input-inline">
	              <input type="text" name="userId" value="${model.userId}" lay-verify="required|userId" autocomplete="off" placeholder="请输入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">用户名称</label>
	            <div class="layui-input-inline">
	              <input type="text" name="userName" value="${model.userName}" lay-verify="required|userName" autocomplete="off" placeholder="请输入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">操作魔铠ID</label>
	            <div class="layui-input-inline">
	              <input type="text" name="logModuleId" value="${model.logModuleId}" lay-verify="required|logModuleId" autocomplete="off" placeholder="请输入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">操作模块名称</label>
	            <div class="layui-input-inline">
	              <input type="text" name="logModuleName" value="${model.logModuleName}" lay-verify="required|logModuleName" autocomplete="off" placeholder="请输入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">操作IP</label>
	            <div class="layui-input-inline">
	              <input type="text" name="logIp" value="${model.logIp}" lay-verify="required|logIp" autocomplete="off" placeholder="请输入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">操作MAC</label>
	            <div class="layui-input-inline">
	              <input type="text" name="logMac" value="${model.logMac}" lay-verify="required|logMac" autocomplete="off" placeholder="请输入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">日志等级</label>
	            <div class="layui-input-inline">
	              <input type="text" name="logLevel" value="${model.logLevel}" lay-verify="required|logLevel" autocomplete="off" placeholder="请输入" class="layui-input">
	            </div>
            </div>
          </div>
          
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select name="status">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?selected:""}>${item.value}</option>
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-comTabLog-edit-submit" id="LAY-comTabLog-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-comTabLog-edit-cancel" id="LAY-comTabLog-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    comTabLog: 'comTabLog'
  }).use(['index', 'form', 'laydate', 'comTabLog'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'comTabLog-edit-form');
  });
  </script>
</body>
</html>