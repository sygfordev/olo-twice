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
  <title>工资卡</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
  <style>
  	.layui-table-main{
  		min-height:300px;
  	}
  </style>
</head>
<body>
  <div class="layui-fluid">
   	
  	<!-- 隐藏属性 -->
   	<input id="basePath" name="basePath" type="hidden" value="<%=basePath%>">
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="payIndex">
        <div class="layui-form-item">
		  <div class="layui-inline">
	        <label class="layui-form-label">姓名</label>
	        <div class="layui-input-block">
	          <input type="text" name="name" placeholder="请输入职工姓名" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">身份证号</label>
	        <div class="layui-input-block">
	          <input type="text" name="cardNo" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
	        </div>
          </div>
          
		  <div class="layui-inline">
		      <label class="layui-form-label">起始年月</label>
		      <div class="layui-input-inline">
		        <input class="layui-input" type="text" name="startMonth" id="startMonth" readonly="" autocomplete="off" placeholder="开始月份">
		      </div>
		      <label class="layui-form-label">结束年月</label>
		      <div class="layui-input-inline">
		        <input class="layui-input" type="text" name="endMonth" id="endMonth" readonly="" autocomplete="off" placeholder="结束月份">
		      </div>
          </div>
		  
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chSaPaycard-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
            
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chSaPaycard-export" data-type="export">
              <i class="layui-icon layui-icon-export layuiadmin-button-btn"></i>
            </button>
          </div>
		</div>
		<div class="layui-form-item">
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chSaPaycard:revoke">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="revoke">批次撤销</button>
          	</shiro:hasPermission>
          </div>
        </div>
        
        <div id="toolbarDemo"></div>
        <!-- 数据表格 -->
        <table id="LAY-chSaPaycard-manage" lay-filter="LAY-chSaPaycard-manage" ></table>
        
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
    chSaPaycard: 'salary/chSaPaycard'
  }).use(['index', 'chSaPaycard', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>