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
  <style type="text/css">
  .layui-form-item .layui-input-inline{
  	padding-top:9px;
  }
  </style>
</head>
<body>
	
  <div class="layui-fluid">
    <div class="layui-card">
      <!-- <div class="layui-card-header">详情</div> -->
      <div class="layui-card-body" style="padding: 15px;">
          <input type="hidden" name="id" value="${model.id}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">批次号</label>
	            <div class="layui-input-inline">
	            	${model.batchNo}
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">批次类型</label>
	            <div class="layui-input-inline">
	           		${model.batchTypeCn}
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">操作人</label>
	            <div class="layui-input-inline">
	            	${model.batchUser}
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">成功数量</label>
	            <div class="layui-input-inline">
	            	${model.sucNum}
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">失败数量</label>
	            <div class="layui-input-inline">
	            	${model.faiNum}
	            </div>
            </div>
            
            
          </div>
		  <div class="layui-form-item layui-form-text">
	            <label class="layui-form-label">导入消息</label>
	            <div class="layui-input-block">
	              <textarea type="text" id="batchMsg" name="batchMsg" class="layui-textarea" readonly>${model.batchMsg}</textarea>
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
    chPmImportRecord: 'chPmImportRecord'
  }).use(['index', 'laydate', 'chPmImportRecord'], function(){
    var $ = layui.$;
    
  });
  </script>
</body>
</html>