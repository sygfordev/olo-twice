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
        <form class="layui-form" action="" lay-filter="chPmTitleHead-edit-form">
          <input type="hidden" name="id" value="${model.id}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职工编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="workerId" name="workerId" value="${model.workerId}"  autocomplete="off" placeholder="请输入职工编号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现职称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4now" name="title4now" value="${model.title4now}"  autocomplete="off" placeholder="请输入现职称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现职称取得时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4nowGotTime" name="title4nowGotTime" value='<fmt:formatDate value="${empty model.title4nowGotTime?'':model.title4nowGotTime}" pattern="yyyy-MM-dd"/>' autocomplete="off" placeholder="请输入现职称取得时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现职称取得年限</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4nowGotYears" name="title4nowGotYears" value="${model.title4nowGotYears}"  autocomplete="off" placeholder="请输入现职称取得年限" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现职称序列（职称分类）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleClass4now" name="titleClass4now" value="${model.titleClass4now}"  autocomplete="off" placeholder="请输入现职称序列（职称分类）" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现职称级别(士级|初级|中级|副高级|正高级)</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleLevel4now" name="titleLevel4now" value="${model.titleLevel4now}"  autocomplete="off" placeholder="请输入现职称级别(士级|初级|中级|副高级|正高级)" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现职称待遇级别(士级|初级|中级|副高级|正高级)</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treatLevel4now" name="treatLevel4now" value="${model.treatLevel4now}"  autocomplete="off" placeholder="请输入现职称待遇级别(士级|初级|中级|副高级|正高级)" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现职称初聘开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4nowHStartTime" name="title4nowHStartTime" value='<fmt:formatDate value="${empty model.title4nowHStartTime?'':model.title4nowHStartTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入现职称初聘开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现职称聘任终止时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4nowHEndTime" name="title4nowHEndTime" value='<fmt:formatDate value="${empty model.title4nowHEndTime?'':model.title4nowHEndTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入现职称聘任终止时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">是否现最高职称  0：否  1：是</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleMax4now" name="titleMax4now" value="${model.titleMax4now}"  autocomplete="off" placeholder="请输入是否现最高职称  0：否  1：是" class="layui-input">
	            </div>
            </div>
          </div>
          
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status">
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
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea">${model.remark}</textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmTitleHead-edit-submit" id="LAY-chPmTitleHead-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chPmTitleHead-edit-cancel" id="LAY-chPmTitleHead-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chPmTitleHead: 'chPmTitleHead'
  }).use(['index', 'form', 'laydate', 'chPmTitleHead'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmTitleHead-edit-form');
  });
  </script>
</body>
</html>