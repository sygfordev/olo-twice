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
          <form class="layui-form" action="" lay-filter="chSaPayslipImprecord-add-form">
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">批次号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="batchNo" name="batchNo"  autocomplete="off" placeholder="批次号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">批次类型名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="batchType" name="batchType" lay-verify="required|batchType" autocomplete="off" placeholder="批次类型名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">操作人</label>
	            <div class="layui-input-inline">
	              <input type="text" id="batchUser" name="batchUser" lay-verify="required|batchUser" autocomplete="off" placeholder="操作人" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">成功数量</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sucNum" name="sucNum" lay-verify="required|sucNum" autocomplete="off" placeholder="成功数量" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">失败数量</label>
	            <div class="layui-input-inline">
	              <input type="text" id="faiNum" name="faiNum" lay-verify="required|faiNum" autocomplete="off" placeholder="失败数量" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">异常数量</label>
	            <div class="layui-input-inline">
	              <input type="text" id="exeNum" name="exeNum" lay-verify="required|exeNum" autocomplete="off" placeholder="异常数量" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">已存在数量</label>
	            <div class="layui-input-inline">
	              <input type="text" id="existNum" name="existNum" lay-verify="required|existNum" autocomplete="off" placeholder="已存在数量" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">批次消息</label>
	            <div class="layui-input-inline">
	              <input type="text" id="batchMsg" name="batchMsg"  autocomplete="off" placeholder="批次消息" class="layui-input">
	            </div>
            </div>
          </div>
        
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chSaPayslipImprecord-add-submit" id="LAY-chSaPayslipImprecord-add-submit">立即提交</button>
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
    chSaPayslipImprecord: 'chSaPayslipImprecord'
  }).use(['index','form','chSaPayslipImprecord'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chSaPayslipImprecord-add-form');
  });
  </script>
</body>
</html>