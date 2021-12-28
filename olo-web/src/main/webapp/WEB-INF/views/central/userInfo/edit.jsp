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
      <!-- <div class="layui-card-header">添加用户</div> -->
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="user-add-form">
          <input type="hidden" name="userId" value="${model.userId}">
        	
          <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">账号</label>
	            <div class="layui-input-inline">
	            	<input type="text" name="account" value="${model.account}" disabled lay-verify="required|account" autocomplete="off" placeholder="请输入账号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">组织机构</label>
	            <div class="layui-input-inline">
	              <input type="text" id="LAY-user-branch-name" lay-filter="LAY-user-branch-name" readonly value="${model.branch.branchName}" autocomplete="off" placeholder="请选择组织机构" class="layui-input">
	              <input type="hidden" id="LAY-user-branch" lay-filter="LAY-user-branch" name="branch" value="${model.branch.id}" class="layui-input">
	            </div>
            </div>
          </div>
          
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">真实姓名</label>
              <div class="layui-input-inline">
                <input type="tel" name="userRealName" value="${model.userRealName}" lay-verify="required|realName" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">性别</label>
              <div class="layui-input-inline">
              	<c:forEach var="item" items="${sexList}" varStatus="sexStatus">
              		<input type="radio" name="userSex" value="${item.key}" title="${item.value}" ${model.userSex eq item.key? 'checked=""' :''}>
              	</c:forEach>
              </div>
            </div>
          </div>
          
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">失效日期</label>
              <div class="layui-input-inline">
                <input type="text" name="userExpireDate" id="LAY-user-expire-date" value="<fmt:formatDate type="date" value='${model.userExpireDate}' pattern='yyyy-MM-dd'/>" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">邮箱</label>
              <div class="layui-input-inline">
                <input type="text" name="userEmail" value="${model.userEmail}" lay-verify="email" autocomplete="off" class="layui-input">
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
            <div class="layui-inline">
              <label class="layui-form-label">手机号</label>
              <div class="layui-input-inline">
                <input type="tel" name="userMobile" value="${model.userMobile}" lay-verify="required|phone" autocomplete="off" class="layui-input">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-userinfo-edit-submit" id="LAY-userinfo-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-userinfo-edit-cancel" id="LAY-userinfo-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    userInfo: 'userInfo'
  }).use(['index', 'form', 'laydate', 'userInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'user-add-form');
  });
  </script>
</body>
</html>