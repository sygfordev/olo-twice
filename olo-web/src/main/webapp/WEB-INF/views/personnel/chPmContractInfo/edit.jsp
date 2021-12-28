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
          <form class="layui-form" action="" lay-filter="chPmContractInfo-edit-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="id" name="id" value="${model.id}">
          <input type="hidden" id="workerId" name="workerId" value="${model.workerId}">
          
          <input type="hidden" id="contClassCn" name="contClassCn" value="${model.contClassCn}">
          <input type="hidden" id="contCycleCn" name="contCycleCn" value="${model.contCycleCn}">
          
          
		  <div class="layui-form-item">
            <div class="layui-inline">
	            <label class="layui-form-label">合同类别</label>
	            <div class="layui-input-inline">
		            <select id="contClass" name="contClass" lay-verify="required" lay-filter="contClass">
		            	<option value=""></option>
	                  	<c:forEach var="item" items="${contClassList}" varStatus="status">
	                    	<option value="${item.key}" ${item.key eq model.contClass?'selected':''}>${item.value}</option>
	                  	</c:forEach>
	                </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">合同期限</label>
	            <div class="layui-input-inline">
	              <select id="contCycle" name="contCycle" lay-verify="required" lay-filter="contCycle">
		            	<option value=""></option>
	                  	<c:forEach var="item" items="${contCycleList}" varStatus="status">
	                    	<option value="${item.key}" ${item.key eq model.contCycle?'selected':''}>${item.value}</option>
	                  	</c:forEach>
	                </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">合同编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="contNo" name="contNo" value="${model.contNo}" lay-verify="required" autocomplete="off" placeholder="合同编号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="contStartTime" name="contStartTime" value='<fmt:formatDate type="date" value="${empty model.contStartTime?'':model.contStartTime}" pattern='yyyy-MM-dd'/>' lay-verify="required" autocomplete="off" placeholder="合同开始时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="contEndTime" name="contEndTime" value='<fmt:formatDate type="date" value="${empty model.contEndTime?'':model.contEndTime}" pattern='yyyy-MM-dd'/>' autocomplete="off" placeholder="合同结束时间" class="layui-input time">
	            </div>
            </div>
            <!-- <div class="layui-inline">
	            <label class="layui-form-label">到期预警</label>
	            <div class="layui-input-inline">
	              <input type="text" id="contExpireWarnTime" name="contExpireWarnTime" value="<fmt:formatDate type="date" value='${model.contExpireWarnTime}' pattern='yyyy-MM-dd'/>" lay-verify="required" autocomplete="off" placeholder="合同到期预警时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">合同顺序</label>
	            <div class="layui-input-inline">
	              <input type="text" id="contOrder" name="contOrder"  autocomplete="off" placeholder="合同顺序" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div> -->
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmContractInfo-edit-submit" id="LAY-chPmContractInfo-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chPmContractInfo-edit-cancel" id="LAY-chPmContractInfo-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chPmContractInfo: 'chPmContractInfo'
  }).use(['index','form','laydate','chPmContractInfo'], function(){
    var $ = layui.$,
    laydate = layui.laydate,
    form = layui.form;
    
    form.render(null, 'chPmContractInfo-edit-form');
  });
  </script>
</body>
</html>