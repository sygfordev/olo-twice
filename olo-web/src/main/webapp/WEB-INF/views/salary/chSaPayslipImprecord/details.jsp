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
	            <label class="layui-form-label">批次号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="batchNo" name="batchNo" value="${model.batchNo}"  autocomplete="off" placeholder="请输入批次号" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">批次类型名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="batchType" name="batchType" value="${model.batchType}" lay-verify="required|batchType" autocomplete="off" placeholder="请输入批次类型名称" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">操作人</label>
	            <div class="layui-input-inline">
	              <input type="text" id="batchUser" name="batchUser" value="${model.batchUser}" lay-verify="required|batchUser" autocomplete="off" placeholder="请输入操作人" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">成功数量</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sucNum" name="sucNum" value="${model.sucNum}" lay-verify="required|sucNum" autocomplete="off" placeholder="请输入成功数量" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">失败数量</label>
	            <div class="layui-input-inline">
	              <input type="text" id="faiNum" name="faiNum" value="${model.faiNum}" lay-verify="required|faiNum" autocomplete="off" placeholder="请输入失败数量" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">异常数量</label>
	            <div class="layui-input-inline">
	              <input type="text" id="exeNum" name="exeNum" value="${model.exeNum}" lay-verify="required|exeNum" autocomplete="off" placeholder="请输入异常数量" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">已存在数量</label>
	            <div class="layui-input-inline">
	              <input type="text" id="existNum" name="existNum" value="${model.existNum}" lay-verify="required|existNum" autocomplete="off" placeholder="请输入已存在数量" class="layui-input" readonly>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">批次消息</label>
	            <div class="layui-input-inline">
	              <input type="text" id="batchMsg" name="batchMsg" value="${model.batchMsg}"  autocomplete="off" placeholder="请输入批次消息" class="layui-input" readonly>
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
    chSaPayslipImprecord: 'chSaPayslipImprecord'
  }).use(['index', 'laydate', 'chSaPayslipImprecord'], function(){
    var $ = layui.$;
    
  });
  </script>
</body>
</html>