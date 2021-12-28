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
  <style type="text/css">
  	.layui-table-cell{
  	height:auto;
  	overflow:auto;
    text-overflow:clip;
    white-space:pre-wrap;
  	}
  </style>
</head>
<body>
  <div class="layui-fluid">   
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="st202Index">
      <!-- 隐藏属性 -->
      <input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
      <input id="modalityList" name="modalityList" type="hidden" value='${modalityList}'>
      <input id="depart1List" name="depart1List" type="hidden" value='${depart1List}'>
      <input id="depart2List" name="depart2List" type="hidden" value='${depart2List}'>
      <input id="wkareasList" name="wkareasList" type="hidden" value='${wkareasList}'>
      
      
        <div class="layui-form-item">
		  <div class="layui-inline">
          	<label class="layui-form-label">开始年月</label>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="startMonth" readonly="" name="startMonth" placeholder="开始年月">
	        </div>
	      </div>
	      <div class="layui-inline">
          	<label class="layui-form-label">结束年月</label>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="endMonth" readonly="" name="endMonth" placeholder="结束年月">
	        </div>
	      </div>
	      <div class="layui-inline">
          	<label class="layui-form-label">用工形式</label>
	        <div class="layui-input-block" id="wkModalityCns"></div>
	      </div>
	      <div class="layui-inline">
          	<label class="layui-form-label">工作地域</label>
	        <div class="layui-input-block" id="wkAreasCns"></div>
	      </div>
	      <div class="layui-inline">
          	<label class="layui-form-label">一级科室</label>
	        <div class="layui-input-block" id="hosDepart1levelCns"></div>
	      </div>
	      <div class="layui-inline">
          	<label class="layui-form-label">二级科室</label>
	        <div class="layui-input-block" id="hosDepart2levelCns"></div>
	      </div>
	   	</div>
	   	<div class="layui-form-item">
	   	  <div class="layui-inline">
          	<label class="layui-form-label">姓名</label>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="name" name="name" placeholder="请输入姓名">
	        </div>
	      </div>
	      <div class="layui-inline">
          	<label class="layui-form-label">身份证号</label>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="cardNo" name="cardNo" placeholder="请输入身份证号">
	        </div>
	      </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-social-202-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="export202">导出</button>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-social-202-manage" lay-filter="LAY-social-202-manage"></table>
        
      </div>
    </div>
  </div>

 <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    socialStatistics202: 'social/socialStatistics202'
  }).use(['index', 'socialStatistics202', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>