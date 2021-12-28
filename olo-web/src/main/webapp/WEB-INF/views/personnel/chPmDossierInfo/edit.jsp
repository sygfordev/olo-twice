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
          <form class="layui-form" action="" lay-filter="chPmDossierInfo-edit-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="id" name="id" value="${model.id}">
          <input type="hidden" id="oper" name="oper" value="${oper}">
          <input type="hidden" id="workerId" name="workerId" value="${wkId}">
          
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">档案编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="dossierNo" name="dossierNo" value="${model.dossierNo}" ${oper eq 'detail'?'disabled':''} lay-verify="required|dossierNo" autocomplete="off" placeholder="人事档案编号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">档案存放地</label>
	            <div class="layui-input-inline">
	              <select id="dossierStorage" name="dossierStorage" lay-verify="required" ${oper eq 'detail'?'disabled':''}>
	              	<option value=""></option>
	                <c:forEach var="item" items="${dosStorageList}" varStatus="status">
	                    <option value="${item.key}" ${item.key eq model.dossierStorage?'selected':''}>${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
          </div>
        
          <%-- <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" ${oper eq 'detail'?'disabled':''}>
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div> --%>
          
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea" ${oper eq 'detail'?'disabled':''}>${model.remark}</textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin ${oper eq 'detail'?'layui-hide':''}"">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmDossierInfo-edit-submit" id="LAY-chPmDossierInfo-edit-submit">修改</button>
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
    chPmDossierInfo: 'chPmDossierInfo'
  }).use(['index','form','chPmDossierInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmDossierInfo-edit-form');
  });
  </script>
</body>
</html>