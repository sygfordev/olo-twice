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
          <form class="layui-form" action="" lay-filter="chPmFileInfo-add-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="workerId" name="workerId" value="${wkId}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">文件模块</label>
	            <div class="layui-input-inline">
	                <select id="moduleCode" name="moduleCode" lay-verify="required">
	                	<option value=""></option>
	                  <c:forEach var="item" items="${uploadModuleList}" varStatus="status">
	                    <option value="${item.key}">${item.value}</option>
	                  </c:forEach>
	                </select>
	            </div>
            </div>
          </div>
          <div class="layui-form-item">
          	<div class="layui-inline">
          		<label class="layui-form-label">上传文件</label>
	          	<div class="layui-upload-drag" id="test10">
				  <i class="layui-icon"></i>
				  <p>点击上传，或将文件拖拽到此处</p>
				  <div class="layui-hide" id="uploadDemoView">
				    <hr>
				    <img src="" alt="上传成功后渲染" style="max-width: 196px">
				  </div>
				</div>
			</div>
          </div>
		     
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmFileInfo-add-submit" id="LAY-chPmFileInfo-add-submit">立即提交</button>
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
    chPmFileInfo: 'chPmFileInfo'
  }).use(['index','form','chPmFileInfo'], function(){
    var $ = layui.$,
    upload = layui.upload,
    table = layui.table,
    form = layui.form;
    
    form.render(null, 'chPmFileInfo-add-form');
  });
  </script>
</body>
</html>