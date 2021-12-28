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
        <form class="layui-form" action="" lay-filter="branchOffice-edit-form">
          <input type="hidden" name="id" value="${model.id}">
          
          <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">父级机构</label>
	            <div class="layui-input-inline">
	              <select id="parentId" name="parentId" disabled="disabled">
	                  <c:forEach var="item" items="${parents}" varStatus="status">
	                    <option value="${item.id}" ${item.id eq model.parentId?'selected':''}>${item.branchName}</option>
	                  </c:forEach>
                  </select>
	            </div>
            </div>
          </div>
          
		  <div class="layui-form-item">
		  	<div class="layui-inline">
	            <label class="layui-form-label">机构名称</label>
	            <div class="layui-input-inline">
	              <input type="text" name="branchName" value="${model.branchName}" autocomplete="off" placeholder="机构名称" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">城市编号</label>
	            <div class="layui-input-inline">
	              <input type="text" name="cityNo" value="${model.cityNo}" lay-verify="required|cityNo" autocomplete="off" placeholder="城市编号" class="layui-input">
	            </div>
            </div>
          </div>
		  
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">固定电话</label>
	            <div class="layui-input-inline">
	              <input type="text" name="phone" value="${model.phone}" lay-verify="required|phone" autocomplete="off" placeholder="电话" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">传真</label>
	            <div class="layui-input-inline">
	              <input type="text" name="fax" value="${model.fax}" lay-verify="required|fax" autocomplete="off" placeholder="传真" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
		  	<div class="layui-inline">
	            <label class="layui-form-label">邮箱</label>
	            <div class="layui-input-inline">
	              <input type="text" name="email" value="${model.email}" lay-verify="required|email" autocomplete="off" placeholder="邮箱" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">邮编</label>
	            <div class="layui-input-inline">
	              <input type="text" name="zip" value="${model.zip}" lay-verify="required|zip" autocomplete="off" placeholder="邮编" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
	            <label class="layui-form-label">地址</label>
	            <div class="layui-input-block">
	              <input type="text" name="address" value="${model.address}" lay-verify="required|address" autocomplete="off" placeholder="请输入地址" class="layui-input">
	            </div>
          </div>
          
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
			  <c:choose>
	              <c:when test="${model.parentId eq -1}">
	              	<select name="status" disabled="disabled">
	              </c:when>
	              <c:otherwise>
	              	<select name="status">
	              </c:otherwise>
              </c:choose>
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea">${model.remark}</textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-branchOffice-edit-submit" id="LAY-branchOffice-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-branchOffice-edit-cancel" id="LAY-branchOffice-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    branch:'branch'
  }).use(['layer','index','branch'], function(){
	  var $ = layui.$
	  form = layui.form,
	  branch = layui.branch;
	  
      form.render(null, 'branchOffice-edit-form');
      
  });
  </script>
</body>
</html>