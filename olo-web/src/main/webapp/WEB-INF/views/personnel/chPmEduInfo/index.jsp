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
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
      	<!-- 隐藏属性 -->
       	<input type="hidden" id="oper" name="oper" value="${oper}">
       	<input type="hidden" id="workerId" name="workerId" value="${wkId}">
       	<!-- 教育经历Head  对象的主键 -->
       	<input type="hidden" id="id" name="id" value="${model.id}">
       	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
       	
       	<input type="hidden" id="eduDeg4nowCn" name="eduDeg4nowCn" value="${model.eduDeg4nowCn}">
       	<input type="hidden" id="eduLev4nowCn" name="eduLev4nowCn" value="${model.eduLev4nowCn}">
       	<input type="hidden" id="eduLev4salCn" name="eduLev4salCn" value="${model.eduLev4salCn}">
       	
        
        <div class="layui-form-item">
          <div class="layui-inline">
	        <label class="layui-form-label">现学位</label>
	        <div class="layui-input-block">
	          	<select id="eduDeg4now" name="eduDeg4now" lay-verify="required" lay-filter="eduDeg4now" ${oper eq 'detail'?'disabled':''}>
               	 <option></option>
                 <c:forEach var="item" items="${eduDegList}" varStatus="status">
                   <option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.eduDeg4now?'selected':''}>${item.value}</option>
                 </c:forEach>
                </select>
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现学历</label>
	        <div class="layui-input-block">
	          <select id="eduLev4now" name="eduLev4now" lay-verify="required" lay-filter="eduLev4now" ${oper eq 'detail'?'disabled':''}>
               	 <option></option>
                 <c:forEach var="item" items="${eduLevList}" varStatus="status">
                   <option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.eduLev4now?'selected':''}>${item.value}</option>
                 </c:forEach>
              </select>
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">工资学历</label>
	        <div class="layui-input-block">
	          <select id="eduLev4sal" name="eduLev4sal" lay-verify="required" lay-filter="eduLev4sal" ${oper eq 'detail'?'disabled':''}>
               	 <option></option>
                 <c:forEach var="item" items="${eduLevList}" varStatus="status">
                   <option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.eduLev4sal?'selected':''}>${item.value}</option>
                 </c:forEach>
              </select>
	        </div>
          </div>
          
          <div class="layui-inline ${oper eq 'detail'?'layui-hide':''}">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="${oper eq 'add'?'LAY-chPmEduHead-save':'LAY-chPmEduHead-edit'}" data-type="search">
              <i class="layui-icon layuiadmin-button-btn">${oper ne 'detail'? oper ne 'edit'?'保存':'修改':''}</i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;" class="${oper eq 'detail'?'layui-hide':''}">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmEduInfo:add">
          		<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmEduInfo:delete">
            	<button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmEduInfo-manage" lay-filter="LAY-chPmEduInfo-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        ${oper ne 'detail'?'
        <script type="text/html" id="table-chPmEduInfo-manager-operator">
          <shiro:hasPermission name="chPmEduInfo:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmEduInfo:delete">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
        </script>
        ':''}
      </div>
    </div>
  </div>

 <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    chPmEduInfo: 'chPmEduInfo'
  }).use(['index', 'chPmEduInfo', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>