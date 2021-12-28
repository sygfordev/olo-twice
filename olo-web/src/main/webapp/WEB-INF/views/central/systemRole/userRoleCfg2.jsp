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
  <title>角色添加</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
	
  <div class="layui-fluid" id="LAY-flow-demo">
  	<fieldset class="layui-elem-field layui-field-title">
        <legend>当前角色：${model.roleName}</legend>
    </fieldset>
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md5">
        <div class="layui-card">
          <div class="layui-card-header">可选用户</div>
          <div class="layui-card-body">
		    <select multiple="multiple" id="select1" name="select1"
		    	style="width: 360px; height: 490px; overflow: auto; border:0px">
                <c:forEach var="item" items="${list1}">
		     		<option value="${item.userId}">${item.userId}【${item.userRealName}】</option>
		     	</c:forEach>
            </select>
          </div>
        </div>
      </div>
      <div class="layui-col-md2" style="text-align: top;line-height: 100px;">
      	<button class="layui-btn layuiadmin-btn-admin" data-type="add" title="新增">新增<i class="layui-icon">&#xe602;</i></button>
        <br />
        <button class="layui-btn layuiadmin-btn-admin" data-type="rem" title="移除">移除<i class="layui-icon">&#xe603;</i></button>
      </div>
      <div class="layui-col-md5">
        <div class="layui-card">
          <div class="layui-card-header">已选用户</div>
          <div class="layui-card-body">
            <select multiple="multiple" id="select2" name="select2"
            	style="width: 360px; height: 490px; overflow: auto; border:0px">
		     	<c:forEach var="item" items="${list0}">
		     		 <option value="${item.userId}">${item.userId}【${item.userRealName}】</option>
		     	</c:forEach>
		    </select>
          </div>
        </div>
      </div>
    </div>
    <div class="layui-form-item layui-layout-admin">
      <div class="layui-input-block">
        <div class="layui-footer" style="left: 0;">
          <button class="layui-btn" lay-submit="" lay-filter="LAY-userRoleCfg-submit" id="LAY-userRoleCfg-submit">立即提交</button>
          <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
      </div>
    </div>
  </div>
    
  <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    userRoleCfg: 'userRoleCfg'
  }).use(['index', 'form','userRoleCfg'], function(){
    var $ = layui.$,
    form = layui.form;
  });
  </script>
</body>
</html>