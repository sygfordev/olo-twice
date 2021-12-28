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
      <div class="layui-form layui-card-header layuiadmin-card-header-auto"><!-- style="white-space:nowrap!important;" -->
      	<!-- 隐藏树形 -->
      	<input type="hidden" id="oper" name="oper" value="${oper}">
      	<input type="hidden" id="workerId" name="workerId" value="${wkId}">
      	<!-- 职务Head  对象的主键 -->
       	<input type="hidden" id="id" name="id" value="${model.id}">
      	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
      	
      	<input type="hidden" id="posit4nowCn" name="posit4nowCn" value="${model.posit4nowCn}">
      	<input type="hidden" id="positLevel4nowCn" name="positLevel4nowCn" value="${model.positLevel4nowCn}">
      	<input type="hidden" id="treatLevel4nowCn" name="treatLevel4nowCn" value="${model.treatLevel4nowCn}">
      	
      	
        <div class="layui-form-item">
		  <div class="layui-inline">
	        <label class="layui-form-label">现行政职务</label>
	        <div class="layui-input-inline">
	          <select id="posit4now" name="posit4now" lay-verify="required" lay-filter="posit4now" ${oper eq 'detail'?'disabled':''}>
              	<option value=""></option>
              	<c:forEach var="item" items="${positList}" varStatus="status">
              		<option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.posit4now?'selected':''}>${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现任职部门</label>
	        <div class="layui-input-inline">
	        	<input id="positDepart4now" name="positDepart4now" value="${model.positDepart4now}" ${oper eq 'detail'?'disabled':''} placeholder="请输入现任职部门" autocomplete="off" class="layui-input" lay-verify="required">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职务任职时间</label>
	        <div class="layui-input-inline">
	          <input type="text" id="posit4nowStartTime" name="posit4nowStartTime" value='<fmt:formatDate type="date" value="${empty model.posit4nowStartTime?'':model.posit4nowStartTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} placeholder="请输入现职务任职时间" autocomplete="off" class="layui-input time">
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">现职务级别</label>
	        <div class="layui-input-inline">
	          <select id="positLevel4now" name="positLevel4now" lay-verify="required" lay-filter="positLevel4now" ${oper eq 'detail'?'disabled':''}>
              	<option value=""></option>
              	<c:forEach var="item" items="${positLevelList}" varStatus="status">
              		<option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.positLevel4now?'selected':''}>${item.value}</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">现待遇级别</label>
	        <div class="layui-input-inline">
	          <select id="treatLevel4now" name="treatLevel4now" lay-verify="required" lay-filter="treatLevel4now" ${oper eq 'detail'?'disabled':''}>
              	<option value=""></option>
              	<c:forEach var="item" items="${positLevelList}" varStatus="status">
              		<option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.treatLevel4now?'selected':''}>${item.value}级</option>
              	</c:forEach>
              </select>
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现待遇任职时间</label>
	        <div class="layui-input-inline">
	          <input type="text" id="treat4nowStartTime" name="treat4nowStartTime" value='<fmt:formatDate type="date" value="${empty model.treat4nowStartTime?'':model.treat4nowStartTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} placeholder="请输入任职开始时间" autocomplete="off" class="layui-input time">
	        </div>
          </div>
          <div class="layui-inline">
		        <label class="layui-form-label">任正处时间</label>
		        <div class="layui-input-inline">
		          <input type="text" id="onChuStartTime" name="onChuStartTime" value='<fmt:formatDate type="date" value="${empty model.onChuStartTime?'':model.onChuStartTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} placeholder="请输入任正处时间" autocomplete="off" class="layui-input time">
		        </div>
          </div>
          <div class="layui-inline">
		        <label class="layui-form-label">任副处时间</label>
		        <div class="layui-input-inline">
		          <input type="text" id="onChuDetupyStartTime" name="onChuDetupyStartTime" value='<fmt:formatDate type="date" value="${empty model.onChuDetupyStartTime?'':model.onChuDetupyStartTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} placeholder="请输入任副处时间" autocomplete="off" class="layui-input time">
		        </div>
          </div>
          <div class="layui-inline">
		        <label class="layui-form-label">任正科时间</label>
		        <div class="layui-input-inline">
		          <input type="text" id="onKeStartTime" name="onKeStartTime" value='<fmt:formatDate type="date" value="${empty model.onKeStartTime?'':model.onKeStartTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} placeholder="请输入任正科时间" autocomplete="off" class="layui-input time">
		        </div>
          </div>
          <div class="layui-inline">
		        <label class="layui-form-label">任副科时间</label>
		        <div class="layui-input-inline">
		          <input type="text" id="onKeDetupyStartTime" name="onKeDetupyStartTime" value='<fmt:formatDate type="date" value="${empty model.onKeDetupyStartTime?'':model.onKeDetupyStartTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} placeholder="请输入任副科时间" autocomplete="off" class="layui-input time">
		        </div>
          </div>
          <div class="layui-inline ${oper eq 'detail'?'layui-hide':''}">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="${oper eq 'add'?'LAY-chPmPositHead-save':'LAY-chPmPositHead-edit'}" data-type="search">
              <i class="layui-icon layuiadmin-button-btn">${oper ne 'detail'? oper ne 'edit'?'保存':'修改':''}</i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;" class="${oper eq 'detail'?'layui-hide':''}">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmPositInfo:add">
          		<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmPositInfo:batchdel">
            	<button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmPositInfo-manage" lay-filter="LAY-chPmPositInfo-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmPositInfo-manager-operator">
          <shiro:hasPermission name="chPmPositInfo:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmPositInfo:delete">
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
    chPmPositInfo: 'chPmPositInfo'
  }).use(['index', 'chPmPositInfo','laydate','table','laypage'], function(){
	  	var $ = layui.$;
  		
	  	/* laydate.render({
	  		elem: '#LAY-chPmPositInfo_xxx_date'
	  	}); */
  	});
  </script>
</body>
</html>