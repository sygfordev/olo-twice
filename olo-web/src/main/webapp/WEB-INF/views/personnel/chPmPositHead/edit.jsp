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
        <form class="layui-form" action="" lay-filter="chPmPositHead-edit-form">
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
	            <label class="layui-form-label">现任职务</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit4now" name="posit4now" value="${model.posit4now}"  autocomplete="off" placeholder="请输入现任职务" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现级别（正科|副科等的编号）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="positLevel4now" name="positLevel4now" value="${model.positLevel4now}"  autocomplete="off" placeholder="请输入现级别（正科|副科等的编号）" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现任职务开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit4nowStartTime" name="posit4nowStartTime" value='<fmt:formatDate value="${empty model.posit4nowStartTime?'':model.posit4nowStartTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入现任职务开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现任职务结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit4nowEndTime" name="posit4nowEndTime" value='<fmt:formatDate value="${empty model.posit4nowEndTime?'':model.posit4nowEndTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入现任职务结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现待遇级别（正科级|副科级等的编号）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treatLevel4now" name="treatLevel4now" value="${model.treatLevel4now}"  autocomplete="off" placeholder="请输入现待遇级别（正科级|副科级等的编号）" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现待遇级别开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treat4nowStartTime" name="treat4nowStartTime" value='<fmt:formatDate value="${empty model.treat4nowStartTime?'':model.treat4nowStartTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入现待遇级别开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现待遇级别结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treat4nowEndTime" name="treat4nowEndTime" value='<fmt:formatDate value="${empty model.treat4nowEndTime?'':model.treat4nowEndTime}" pattern="yyyy-MM-dd"/>' autocomplete="off" placeholder="请输入现待遇级别结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现待遇级别年限</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treat4nowYears" name="treat4nowYears" value="${model.treat4nowYears}"  autocomplete="off" placeholder="请输入现待遇级别年限" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正处开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onChuStartTime" name="onChuStartTime" value='<fmt:formatDate value="${empty model.onChuStartTime?'':model.onChuStartTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入任正处开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正处结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onChuEndTime" name="onChuEndTime" value='<fmt:formatDate value="${empty model.onChuEndTime?'':model.onChuEndTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入任正处结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副处开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onChuDetupyStartTime" name="onChuDetupyStartTime" value='<fmt:formatDate value="${empty model.onChuDetupyStartTime?'':model.onChuDetupyStartTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入任副处开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副处结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onChuDetupyEndTime" name="onChuDetupyEndTime" value='<fmt:formatDate value="${empty model.onChuDetupyEndTime?'':model.onChuDetupyEndTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入任副处结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正科开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onKeStartTime" name="onKeStartTime" value='<fmt:formatDate value="${empty model.onKeStartTime?'':model.onKeStartTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入任正科开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正科结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onKeEndTime" name="onKeEndTime" value='<fmt:formatDate value="${empty model.onKeEndTime?'':model.onKeEndTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入任正科结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副科开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onKeDetupyStartTime" name="onKeDetupyStartTime" value='<fmt:formatDate value="${empty model.onKeDetupyStartTime?'':model.onKeDetupyStartTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入任副科开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副科结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onKeDetupyEndTime" name="onKeDetupyEndTime" value='<fmt:formatDate value="${empty model.onKeDetupyEndTime?'':model.onKeDetupyEndTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入任副科结束时间" class="layui-input">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmPositHead-edit-submit" id="LAY-chPmPositHead-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chPmPositHead-edit-cancel" id="LAY-chPmPositHead-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chPmPositHead: 'chPmPositHead'
  }).use(['index', 'form', 'laydate', 'chPmPositHead'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmPositHead-edit-form');
  });
  </script>
</body>
</html>