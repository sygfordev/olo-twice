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
        <!-- <div class="layui-card-header">添加</div> -->
        <div class="layui-card-body" style="padding: 15px;">
          <form class="layui-form" action="" lay-filter="systemPriv-add-form">
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">权限名称</label>
	            <div class="layui-input-inline">
	              <input type="text" name="privCnName" autocomplete="off" placeholder="权限名称-中文" class="layui-input" lay-verify="required|privCnName">
	            </div>
	            
	            <label class="layui-form-label">权限类型</label>
	            <div class="layui-input-inline">
	              <select name="privType" lay-filter="privType" lay-verify="required|privType">
	              	<option></option>
	              	<c:forEach var="item" items="${typeList}" varStatus="status">
	              		<option value="${item.key}">${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">英文名称</label>
	            <div class="layui-input-inline">
	              <input type="text" name="privEnName" lay-verify="" autocomplete="off" placeholder="权限名称-英文" class="layui-input">
	            </div>
	            <label class="layui-form-label layui-hide" id="menuLevelLabel">菜单等级</label>
	            <div class="layui-input-inline layui-hide" id="menuLevelDiv">
	              <select name="menuLevel" id="menuLevel" lay-filter="menuLevel">
	              	<c:forEach var="item" items="${menuLevelList}" varStatus="status">
	              		<option></option>
	              		<option value="${item.key}">${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
          </div>
          <div class="layui-form-item layui-hide" id="menuDiv">
		  	<div class="layui-inline">
          		<label class="layui-form-label layui-hide" id="menu1Label">一级菜单</label>
	            <div class="layui-input-inline layui-hide" id="menu1Div">
	              <select name="menu4Level1" id="menu4Level1" lay-filter="menu4Level1">
	              	<option></option>
	              </select>
	            </div>
	            <label class="layui-form-label layui-hide" id="menu2Label">二级菜单</label>
	            <div class="layui-input-inline layui-hide" id="menu2Div">
	              <select name="menu4Level2" id="menu4Level2">
	              	<option></option>
	              </select>
	            </div>
            </div>
          </div>
          <div class="layui-form-item">
          	<div class="layui-inline">
          		<label class="layui-form-label" id="privIconLabelDiv">权限ICON</label>
	            <div class="layui-input-inline" id="privIconLabelDiv">
	              <input type="text" name="privIcon" autocomplete="off" placeholder="权限ICON" class="layui-input">
	            </div>
	            <label class="layui-form-label">权限状态</label>
	            <div class="layui-input-inline">
	              <select name="status" lay-verify="required|status">
	                <c:forEach var="item" items="${statusList}" varStatus="status">
	                  <option value="${item.key}">${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
          </div>
          
          <div class="layui-form-item">
          	<div class="layui-inline">
          		<label class="layui-form-label">权限动作</label>
	            <div class="layui-input-inline">
	              <input type="text" name="privAction" lay-verify="required|privAction" autocomplete="off" placeholder="权限动作" class="layui-input">
	            </div>
	            <label class="layui-form-label">权限标识</label>
	            <div class="layui-input-inline">
	              <input type="text" name="privPermissioin" lay-verify="required|privPermissioin" autocomplete="off" placeholder="权限标识" class="layui-input">
	            </div>
	        </div>
	      </div>
          <div class="layui-form-item">
	            <label class="layui-form-label">权限方法</label>
	            <div class="layui-input-block">
	              <input type="text" name="privMethod" lay-verify="required|privMethod" autocomplete="off" placeholder="权限方法" class="layui-input">
	            </div>
          </div>
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注信息</label>
            <div class="layui-input-block">
              <textarea name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-systemPriv-add-submit" id="LAY-systemPriv-add-submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
    base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    systemPriv: 'systemPriv'
  }).use(['index', 'form', 'laydate', 'systemPriv'], function(){
    var $ = layui.$,
    form = layui.form;
    form.render(null, 'systemPriv-add-form');
  });
  </script>
</body>
</html>