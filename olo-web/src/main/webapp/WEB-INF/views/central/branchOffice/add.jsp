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
          <form id="add-form" class="layui-form" action="" lay-filter="branchOffice-add-form">
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">机构名称</label>
	            <div class="layui-input-inline">
	              <input type="hidden" name="parentId" value="${parentId}" class="layui-input">
	              <input type="text" name="branchName"  autocomplete="off" placeholder="机构名称" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">城市编号</label>
	            <div class="layui-input-inline">
	              <input type="text" name="cityNo" lay-verify="required|cityNo" autocomplete="off" placeholder="城市编号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">固定电话</label>
	            <div class="layui-input-inline">
	              <input type="text" name="phone" lay-verify="required|phone" autocomplete="off" placeholder="电话" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">传真</label>
	            <div class="layui-input-inline">
	              <input type="text" name="fax" lay-verify="required|fax" autocomplete="off" placeholder="传真" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">邮箱</label>
	            <div class="layui-input-inline">
	              <input type="text" name="email" lay-verify="required|email" autocomplete="off" placeholder="邮箱" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">邮编</label>
	            <div class="layui-input-inline">
	              <input type="text" name="zip" lay-verify="required|zip" autocomplete="off" placeholder="邮编" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
              <input type="text" name="address" lay-verify="required|address" autocomplete="off" placeholder="地址" class="layui-input">
            </div>
          </div>
        
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select name="status">
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
              <textarea name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-branchOffice-add-submit" id="LAY-branchOffice-add-submit">立即提交</button>
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
    branch: 'branch'
  }).use(['index', 'form', 'laydate', 'branch'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'branchOffice-add-form');
  });
  </script>
</body>
</html>