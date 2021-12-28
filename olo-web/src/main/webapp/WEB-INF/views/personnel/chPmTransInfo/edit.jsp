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
        <form class="layui-form" action="" lay-filter="chPmTransInfo-edit-form">
          <input type="hidden" name="id" value="${model.id}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">姓名</label>
	            <div class="layui-input-inline">
	              <input type="text" id="name" name="name" value="${model.name}" lay-verify="required|name" autocomplete="off" placeholder="请输入姓名" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">身份证号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="cardNo" name="cardNo" value="${model.cardNo}" lay-verify="required|cardNo" autocomplete="off" placeholder="请输入身份证号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">原部门</label>
	            <div class="layui-input-inline">
	              <select id="departBefore" name="departBefore" lay-verify="required" lay-filter="departBefore">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${hdlist}" varStatus="status">
	              		<option value="${item.hdpNo}" ${item.hdpNo eq model.departBefore?'selected':''}>${item.hdpName}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">原岗位</label>
	            <div class="layui-input-inline">
	              <select id="positBefore" name="positBefore" lay-verify="required" lay-filter="positBefore">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${stationTypeList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.positBefore?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">新部门</label>
	            <div class="layui-input-inline">
	              <select id="departAfter" name="departAfter" lay-verify="required" lay-filter="departAfter">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${hdlist}" varStatus="status">
	              		<option value="${item.hdpNo}" ${item.hdpNo eq model.departAfter?'selected':''}>${item.hdpName}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">新岗位</label>
	            <div class="layui-input-inline">
	              <select id="positAfter" name="positAfter" lay-verify="required" lay-filter="positAfter">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${stationTypeList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.positAfter?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">调动文号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="transDocno" name="transDocno" value="${model.transDocno}" lay-verify="transDocno" autocomplete="off" placeholder="请输入调动文号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">调动时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="transTime" name="transTime" value='<fmt:formatDate value="${empty model.transTime?'':model.transTime}" pattern="yyyy-MM-dd"/>' lay-verify="transTime" autocomplete="off" placeholder="请输入调动时间" class="layui-input time">
	            </div>
            </div>
            <%-- <div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?selected:""}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div> --%>
            
            
            
          </div>
		 <%--  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">原部门</label>
	            <div class="layui-input-inline">
	              <input type="text" id="departBefore" name="departBefore" value="${model.departBefore}"  autocomplete="off" placeholder="请输入原部门" class="layui-input">
	            </div>
            </div>
          </div> --%>
		  
		  <%-- <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">新部门</label>
	            <div class="layui-input-inline">
	              <input type="text" id="departAfter" name="departAfter" value="${model.departAfter}"  autocomplete="off" placeholder="请输入新部门" class="layui-input">
	            </div>
            </div>
          </div> --%>
		  
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea">${model.remark}</textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmTransInfo-edit-submit" id="LAY-chPmTransInfo-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chPmTransInfo-edit-cancel" id="LAY-chPmTransInfo-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chPmTransInfo: 'chPmTransInfo'
  }).use(['index', 'form', 'laydate', 'chPmTransInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmTransInfo-edit-form');
  });
  </script>
</body>
</html>