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
        <form class="layui-form" action="" lay-filter="chPmSpecProfe-edit-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="id" name="id" value="${model.id}">
          <input type="hidden" id="workerId" name="workerId" value="${model.workerId}">
          
		  <div class="layui-form-item">
            <div class="layui-inline">
	            <label class="layui-form-label">特殊工种名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="specPName" name="specPName" value="${model.specPName}" lay-verify="specPName" autocomplete="off" placeholder="特殊工种名称" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">特殊工种开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="specPStartTime" name="specPStartTime" value=<fmt:formatDate value="${model.specPStartTime}" pattern="yyyy-MM-dd"/> autocomplete="off" placeholder="特殊工种开始时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">特殊工种结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="specPEndTime" name="specPEndTime" value=<fmt:formatDate value="${model.specPEndTime}" pattern="yyyy-MM-dd"/> autocomplete="off" placeholder="特殊工种结束时间" class="layui-input time">
	            </div>
            </div>
            <%-- <div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" >
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmSpecProfe-edit-submit" id="LAY-chPmSpecProfe-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chPmSpecProfe-edit-cancel" id="LAY-chPmSpecProfe-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chPmSpecProfe: 'chPmSpecProfe'
  }).use(['index', 'form', 'laydate', 'chPmSpecProfe'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmSpecProfe-edit-form');
  });
  </script>
</body>
</html>