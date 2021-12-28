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
          <form class="layui-form" action="" lay-filter="chPmIncdecInfo-edit-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="id" name="id" value="${model.id}">
          <input type="hidden" id="oper" name="oper" value="${oper}">
          <input type="hidden" id="workerId" name="workerId" value="${wkId}">
          
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">进入时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="entryTime" name="entryTime" value='<fmt:formatDate value="${empty model.entryTime?'':model.entryTime}" pattern="yyyy-MM-dd"/>' ${oper eq 'detail'?'disabled':''} lay-verify="required" autocomplete="off" placeholder="进入时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">进入渠道</label>
	            <div class="layui-input-inline">
	              <select id="entryChan" name="entryChan" lay-verify="required" ${oper eq 'detail'?'disabled':''}>
	              	<option value=""></option>
	              	<c:forEach var="item" items="${entryChanList}" varStatus="status">
	                	<option value="${item.key}" ${item.key eq model.entryChan?'selected':''}>${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">离职时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="quitTime" name="quitTime" value='<fmt:formatDate value="${empty model.quitTime?'':model.quitTime}" pattern="yyyy-MM-dd"/>' ${oper eq 'detail'?'disabled':''} autocomplete="off" placeholder="离职时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">离职原因</label>
	            <div class="layui-input-inline">
	              <select id="quitReason" name="quitReason" ${oper eq 'detail'?'disabled':''}>
	              	<option value=""></option>
	              	<c:forEach var="item" items="${quitReasonList}" varStatus="status">
	                	<option value="${item.key}" ${item.key eq model.quitReason?'selected':''}>${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
            <%-- <div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" ${oper eq 'detail'?'disabled':''}>
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div> --%>
            
          </div>
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea" ${oper eq 'detail'?'disabled':''}>${model.remark}</textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin ${oper eq 'detail'?'layui-hide':''}">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmIncdecInfo-edit-submit" id="LAY-chPmIncdecInfo-edit-submit">修改</button>
                <!-- <button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
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
    chPmIncdecInfo: 'chPmIncdecInfo'
  }).use(['index','form','chPmIncdecInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmIncdecInfo-edit-form');
  });
  </script>
</body>
</html>