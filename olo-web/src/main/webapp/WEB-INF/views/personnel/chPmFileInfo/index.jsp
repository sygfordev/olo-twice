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
      <div class="layui-form layui-card-header layuiadmin-card-header-auto ${oper eq 'detail'?'layui-hide':''}">
        <div class="layui-form-item">
		  <!-- 隐藏属性 -->
		  <input type="hidden" id="oper" name="oper" value="${oper}">
		  <input type="hidden" id="workerId" name="workerId" value="${wkId}">
		  <input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
		  
		  
		  <div class="layui-inline">
	        <label class="layui-form-label">上传模块</label>
	        <div class="layui-input-block">
	          <select id="moduleCode" name="moduleCode">
	              	  <option value=""></option>
	                  <c:forEach var="item" items="${uploadModuleList}" varStatus="status">
	                    <option value="${item.key}">${item.value}</option>
	                  </c:forEach>
	          </select>
	        </div>
          </div>
		  
		  <div class="layui-inline">
	        <label class="layui-form-label">文件类型</label>
	        <div class="layui-input-block">
	          <select id="fileType" name="fileType">
	          		<option value=""></option>
	              	<option value="image/jpeg">image/jpeg</option>  
	          </select>
	        </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
              <select name="status">
              	<option value="">--所有--</option>
              	<c:forEach var="item" items="${statusList}" varStatus="status">
              		<option value="${item.key}" ${status.index eq 0?'selected':''}>${item.value}</option>
              	</c:forEach>
              </select>
            </div>
          </div>
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chPmFileInfo-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;" class="${oper eq 'detail'?'layui-hide':''}">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmFileInfo:add">
          		<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmFileInfo:batchdel">
            	<button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmFileInfo-manage" lay-filter="LAY-chPmFileInfo-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="imgTpl">
          <img style="display: inline-block; width: 50%; height: 100%;" src= {{ d.fileUrl }}>
        </script> 
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmFileInfo-manager-operator">
		  <shiro:hasPermission name="chPmFileInfo:delete">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmFileInfo:download">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="download"><i class="layui-icon layui-icon-download"></i>下载</a>
		  </shiro:hasPermission>
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
    chPmFileInfo: 'chPmFileInfo'
  }).use(['index', 'chPmFileInfo', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>