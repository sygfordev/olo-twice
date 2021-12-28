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
  <title>layuiAdmin 后台管理员</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
  <div class="layui-fluid">   
    <div class="layui-card">
      <!-- <div class="layui-form layui-card-header layuiadmin-card-header-auto">
		  <div class="layui-inline">
	        <label class="layui-form-label">调资年月</label>
	        <div class="layui-input-block">
	          <input type="text" name="adjustMonth" placeholder="请输入调资年月" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">操作人</label>
	        <div class="layui-input-block">
	          <input type="text" name="adjustUser" placeholder="请输入操作人" autocomplete="off" class="layui-input">
	        </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chSaAdjustRecord-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div> -->
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chSaAdjustRecord:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">创建调资</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chSaAdjustRecord:uploadFormula">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="uploadFormula">上传薪级分类</button>
          	</shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chSaAdjustRecord-manage" lay-filter="LAY-chSaAdjustRecord-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
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
  }).use(['index', 'chSaAdjustRecord', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>