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
      <div class="layui-form layui-card-header layuiadmin-card-header-auto" ><!-- style="white-space:nowrap!important;" -->
      	<!-- 隐藏属性 -->
        <input type="hidden" id="oper" name="oper" value="${oper}">
      	<input type="hidden" id="workerId" name="workerId" value="${wkId}">
      	<!-- 职务Head  对象的主键 -->
       	<input type="hidden" id="id" name="id" value="${model.id}">
       	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
       	
       	<input type="hidden" id="title4nowCn" name="title4nowCn" value="${model.title4nowCn}">
       	<input type="hidden" id="titleLevel4nowCn" name="titleLevel4nowCn" value="${model.titleLevel4nowCn}">
       	<input type="hidden" id="titleClass4nowCn" name="titleClass4nowCn" value="${model.titleClass4nowCn}">
       	<input type="hidden" id="treatLevel4nowCn" name="treatLevel4nowCn" value="${model.treatLevel4nowCn}">
       	
       	
        <div class="layui-form-item">
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称</label>
	        <div class="layui-input-inline">
	          <select id="title4now" name="title4now" lay-verify="required" lay-filter="title4now" ${oper eq 'detail'?'disabled':''}>
              	<option value=""></option>
              	<c:forEach var="item" items="${titleList}" varStatus="status">
              		<option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.title4now?'selected':''}>${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称级别</label>
	        <div class="layui-input-inline">
	          <select id="titleLevel4now" name="titleLevel4now" lay-verify="required" lay-filter="titleLevel4now" ${oper eq 'detail'?'disabled':''}>
              	<option value=""></option>
              	<c:forEach var="item" items="${titleLevelList}" varStatus="status">
              		<option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.titleLevel4now?'selected':''}>${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">现职称序列</label>
	        <div class="layui-input-inline">
	          <select id="titleClass4now" name="titleClass4now" lay-verify="required" lay-filter="titleClass4now" ${oper eq 'detail'?'disabled':''}>
              	<option value=""></option>
              	<c:forEach var="item" items="${titleClassList}" varStatus="status">
              		<option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.titleClass4now?'selected':''}>${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">现职称待遇级别</label>
	        <div class="layui-input-inline">
	          <select id="treatLevel4now" name="treatLevel4now" lay-verify="required" lay-filter="treatLevel4now" ${oper eq 'detail'?'disabled':''}>
              	<option value=""></option>
              	<c:forEach var="item" items="${titleLevelList}" varStatus="status">
              		<option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.treatLevel4now?'selected':''}>${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">现职称获取时间</label>
	        <div class="layui-input-inline">
	          <input type="text" id="title4nowGotTime" name="title4nowGotTime" value='<fmt:formatDate type="date" value="${empty model.title4nowGotTime?'':model.title4nowGotTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} lay-verify="required" placeholder="现职称获取时间" autocomplete="off" class="layui-input time">
	        </div>
          </div>
		  
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称初聘时间-起</label>
	        <div class="layui-input-inline">
	          <input type="text" id="title4nowHStartTime" name="title4nowHStartTime" value='<fmt:formatDate type="date" value="${empty model.title4nowHStartTime?'':model.title4nowHStartTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} placeholder="请输入现职称初聘开始时间" lay-verify="required" autocomplete="off" class="layui-input time" >
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">聘任时间--止</label>
	        <div class="layui-input-inline">
	          <input type="text" id="title4nowHEndTime" name="title4nowHEndTime" value='<fmt:formatDate type="date" value="${empty model.title4nowHEndTime?'':model.title4nowHEndTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} placeholder="请输入现职称初聘结束时间" lay-verify="required" autocomplete="off" class="layui-input time">
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">是否现最高职称</label>
	        <div class="layui-input-inline">
	          <select id="titleMax4now" name="titleMax4now" lay-verify="required" ${oper eq 'detail'?'disabled':''}>
              	<option value=""></option>
              	<c:forEach var="item" items="${YNList}" varStatus="status">
              		<option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.titleMax4now?'selected':''}>${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
          <div class="layui-inline ${oper eq 'detail'?'layui-hide':''}">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="${oper eq 'add'?'LAY-chPmTitleHead-save':'LAY-chPmTitleHead-edit'}" data-type="search">
              <i class="layui-icon layuiadmin-button-btn">${oper ne 'detail'? oper ne 'edit'?'保存':'修改':''}</i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;" class="${oper eq 'detail'?'layui-hide':''}">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmTitleInfo:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmTitleInfo:batchdel">
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmTitleInfo-manage" lay-filter="LAY-chPmTitleInfo-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmTitleInfo-manager-operator">
          <shiro:hasPermission name="chPmTitleInfo:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmTitleInfo:delete">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
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
    chPmTitleInfo: 'chPmTitleInfo'
  }).use(['index', 'chPmTitleInfo', 'laydate','table','laypage'], function(){
  		var $ = layui.$,
  		laydate = layui.laydate;
  	});
  </script>
</body>
</html>