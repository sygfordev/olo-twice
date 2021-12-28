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
          <form class="layui-form" action="" lay-filter="chSaAdjustRecord-add-form">
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">调资年月</label>
	            <div class="layui-input-inline">
	              <input type="text" id="adjustMonth" name="adjustMonth" lay-verify="required" autocomplete="off" placeholder="调资年月" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">上传岗位类别文件</label>
	            <div class="layui-upload-drag" id="adjust_upload">
				  <i class="layui-icon"></i>
				  <p>点击上传，或将文件拖拽到此处</p>
				  <div class="layui-hide" id="uploadDemoView">
				    <hr>
				    <label></label>
				  </div>
				</div>
            </div>
          </div>
		      
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chSaAdjustRecord-add-submit" id="LAY-chSaAdjustRecord-add-submit">立即提交</button>
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
    chSaAdjustRecord: 'salary/chSaAdjustRecord'
  }).use(['index','form','chSaAdjustRecord'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chSaAdjustRecord-add-form');
  });
  </script>
</body>
</html>