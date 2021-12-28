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
        <form class="layui-form" action="" lay-filter="chPmEduHead-edit-form">
          <input type="hidden" name="Id" value="${model.Id}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">自增主键</label>
	            <div class="layui-input-inline">
	              <input type="text" id="id" name="id" value="${model.id}"  autocomplete="off" placeholder="请输入自增主键" class="layui-input">
	            </div>
            </div>
          </div>
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
	            <label class="layui-form-label">学习学位</label>
	            <div class="layui-input-inline">
	              <input type="text" id="eduDeg4now" name="eduDeg4now" value="${model.eduDeg4now}"  autocomplete="off" placeholder="请输入学习学位" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">学历（高中|大专|本科|硕士|博士等）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="eduLev4now" name="eduLev4now" value="${model.eduLev4now}"  autocomplete="off" placeholder="请输入学历" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工资学历</label>
	            <div class="layui-input-inline">
	              <input type="text" id="eduLev4sal" name="eduLev4sal" value="${model.eduLev4sal}"  autocomplete="off" placeholder="请输入工资学历" class="layui-input">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmEduHead-edit-submit" id="LAY-chPmEduHead-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chPmEduHead-edit-cancel" id="LAY-chPmEduHead-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chPmEduHead: 'chPmEduHead'
  }).use(['index', 'form', 'laydate', 'chPmEduHead'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmEduHead-edit-form');
  });
  </script>
</body>
</html>