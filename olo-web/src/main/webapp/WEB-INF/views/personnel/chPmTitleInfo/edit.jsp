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
        <!-- <div class="layui-card-header">修改</div> -->
        <div class="layui-card-body" style="padding: 15px;">
          <form class="layui-form" action="" lay-filter="chPmTitleInfo-edit-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="id" name="id" value="${model.id}">
          <input type="hidden" id="workerId" name="workerId" value="${model.workerId}">
          	
          <input type="hidden" id="titleCn" name="titleCn" value="${model.titleCn}">
          <input type="hidden" id="titleLevelCn" name="titleLevelCn" value="${model.titleLevelCn}">
          <input type="hidden" id="titleClassCn" name="titleClassCn" value="${model.titleClassCn}">
          <input type="hidden" id="titleOthCn" name="titleOthCn" value="${model.titleOthCn}">
		  
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职称</label>
	            <div class="layui-input-inline">
	              <select id="title" name="title" lay-verify="required" lay-filter="title">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${titleList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.title?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">职称级别</label>
	            <div class="layui-input-inline">
	              <select id="titleLevel" name="titleLevel" lay-verify="required" lay-filter="titleLevel">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${titleLevelList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.titleLevel?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">职称序列</label>
	            <div class="layui-input-inline">
	              <select id="titleClass" name="titleClass" lay-filter="titleClass">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${titleClassList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.titleClass?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">是否最高职称</label>
	            <div class="layui-input-inline">
	              <select id="titleMax" name="titleMax">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${YNList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.titleMax?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">职称证书编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleCertNo" name="titleCertNo" value="${model.titleCertNo}" lay-verify="titleCertNo" autocomplete="off" placeholder="职称证书编号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">职称任职文号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleOnitNo" name="titleOnitNo" value="${model.titleOnitNo}" lay-verify="titleOnitNo" autocomplete="off" placeholder="职称任职文号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">职称初聘文号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleHireNo" name="titleHireNo" value="${model.titleHireNo}" lay-verify="titleHireNo" autocomplete="off" placeholder="职称初聘文号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">初聘时间-起</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleHStartTime" name="titleHStartTime" value='<fmt:formatDate value="${empty model.titleHStartTime?'':model.titleHStartTime}" pattern="yyyy-MM-dd"/>' autocomplete="off" placeholder="职称初聘开始时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">聘任时间-止</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleHEndTime" name="titleHEndTime" value='<fmt:formatDate value="${empty model.titleHEndTime?'':model.titleHEndTime}" pattern="yyyy-MM-dd"/>' autocomplete="off" placeholder="职称初聘结束时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">职称聘任周期</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleHCycle" name="titleHCycle" value="${model.titleHCycle}" lay-verify="number" autocomplete="off" placeholder="职称聘任周期" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">其他职称</label>
	            <div class="layui-input-inline">
	              <select id="titleOth" name="titleOth" lay-filter="titleOth">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${titleList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.titleOth?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">其他职称取得时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleOthGotTime" name="titleOthGotTime" value='<fmt:formatDate value="${empty model.titleOthGotTime?'':model.titleOthGotTime}" pattern="yyyy-MM-dd"/>' autocomplete="off" placeholder="其他职称取得时间" class="layui-input time">
	            </div>
            </div>
            <%-- <div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" lay-verify="required">
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
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea">${model.remark}</textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmTitleInfo-edit-submit" id="LAY-chPmTitleInfo-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chPmTitleInfo-edit-cancel" id="LAY-chPmTitleInfo-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chPmTitleInfo: 'chPmTitleInfo'
  }).use(['index','form','chPmTitleInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmTitleInfo-edit-form');
  });
  </script>
</body>
</html>