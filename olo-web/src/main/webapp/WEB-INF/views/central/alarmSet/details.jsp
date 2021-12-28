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
      <!-- <div class="layui-card-header">详情</div> -->
      <div class="layui-card-body" style="padding: 15px;">
          <input type="hidden" name="id" value="${model.id}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">警报类型</label>
	            <div class="layui-input-inline">
	              <input type="text" id="alarmType" name="alarmType" value="${model.alarmType}"  autocomplete="off" placeholder="请输入警报类型" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">提前天/小时/分钟....数量</label>
	            <div class="layui-input-inline">
	              <input type="text" id="alarmAdSize" name="alarmAdSize" value="${model.alarmAdSize}"  autocomplete="off" placeholder="请输入提前天/小时/分钟....数量" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">提前单位</label>
	            <div class="layui-input-inline">
	              <input type="text" id="alarmAdUnit" name="alarmAdUnit" value="${model.alarmAdUnit}"  autocomplete="off" placeholder="请输入提前单位" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">报警样板</label>
	            <div class="layui-input-inline">
	              <input type="text" id="alarmTeInfo" name="alarmTeInfo" value="${model.alarmTeInfo}"  autocomplete="off" placeholder="请输入报警样板" class="layui-input" readonly>
	            </div>
            </div>
          </div>
          
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" readonly>
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
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea" readonly>${model.remark}</textarea>
            </div>
          </div>
      </div>
    </div>
  </div>

    
  <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '<%=basePath%>layuiadmin/'
  }).extend({
    index: 'lib/index', //主入口模块
    chPmAlarmSet: 'chPmAlarmSet'
  }).use(['index', 'laydate', 'chPmAlarmSet'], function(){
    var $ = layui.$;
    
  });
  </script>
</body>
</html>