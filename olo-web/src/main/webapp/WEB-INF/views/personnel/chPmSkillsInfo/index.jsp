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
      	<!-- 隐藏树形 -->
      	<input type="hidden" id="oper" name="oper" value="${oper}">
      	<input type="hidden" id="workerId" name="workerId" value="${wkId}">
      	<!-- 技能能级Head  对象的主键 -->
       	<input type="hidden" id="id" name="id" value="${model.id}">
      	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
      	
      	<input type="hidden" id="skills4nowCn" name="skills4nowCn" value="${model.skills4nowCn}">
      	<input type="hidden" id="skillsClass4nowCn" name="skillsClass4nowCn" value="${model.skillsClass4nowCn}">
      	<input type="hidden" id="skillsLevel4nowCn" name="skillsLevel4nowCn" value="${model.skillsLevel4nowCn}">
      	<input type="hidden" id="treatLevel4nowCn" name="treatLevel4nowCn" value="${model.treatLevel4nowCn}">
      	
        
        <div class="layui-form-item">
            <div class="layui-inline">
	            <label class="layui-form-label">现技能等级</label>
	            <div class="layui-input-inline">
	              <select id="skills4now" name="skills4now" lay-verify="required" lay-filter="skills4now" ${oper eq 'detail'?'disabled':''}>
	              	<option value=""></option>
	                <c:forEach var="item" items="${skillsList}" varStatus="status">
	                    <option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.skills4now?'selected':''}>${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">现取得时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skills4nowGotTime" name="skills4nowGotTime" value='<fmt:formatDate type="date" value="${empty model.skills4nowGotTime?'':model.skills4nowGotTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} lay-verify="required" autocomplete="off" placeholder="现技能等级取得时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">现等级序列</label>
	            <div class="layui-input-inline">
	              <select id="skillsClass4now" name="skillsClass4now" lay-verify="required" lay-filter="skillsClass4now" ${oper eq 'detail'?'disabled':''}>
	              	<option value=""></option>
	                  <c:forEach var="item" items="${skillsClassList}" varStatus="status">
	                    <option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.skillsClass4now?'selected':''}>${item.value}</option>
	                  </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">现等级级别</label>
	            <div class="layui-input-inline">
	              <select id="skillsLevel4now" name="skillsLevel4now" lay-verify="required" lay-filter="skillsLevel4now" ${oper eq 'detail'?'disabled':''}>
	              	<option value=""></option>
	                  <c:forEach var="item" items="${skillsLevelList}" varStatus="status">
	                    <option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.skillsLevel4now?'selected':''}>${item.value}</option>
	                  </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">现待遇级别</label>
	            <div class="layui-input-inline">
	              <select id="treatLevel4now" name="treatLevel4now" lay-verify="required" lay-filter="treatLevel4now" ${oper eq 'detail'?'disabled':''}>
	              	<option value=""></option>
	                  <c:forEach var="item" items="${skillsLevelList}" varStatus="status">
	                    <option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.treatLevel4now?'selected':''}>${item.value}</option>
	                  </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">现技能初聘时间-起</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skills4nowHStartTime" name="skills4nowHStartTime" value='<fmt:formatDate type="date" value="${empty model.skills4nowHStartTime?'':model.skills4nowHStartTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} lay-verify="required" autocomplete="off" placeholder="现技能初聘开始时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">聘任时间-止</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skills4nowHEndTime" name="skills4nowHEndTime" value='<fmt:formatDate type="date" value="${empty model.skills4nowHEndTime?'':model.skills4nowHEndTime}" pattern='yyyy-MM-dd'/>' ${oper eq 'detail'?'disabled':''} lay-verify="required" autocomplete="off" placeholder="现技能聘任终止时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">是否最高等级</label>
	            <div class="layui-input-inline">
	              <select id="skillsMax4now" name="skillsMax4now" lay-verify="required" ${oper eq 'detail'?'disabled':''}>
	              	<option value=""></option>
	                  <c:forEach var="item" items="${YNList}" varStatus="status">
	                    <option value="${item.key}" ${(oper eq 'edit' or oper eq 'detail') && item.key eq model.skillsMax4now?'selected':''}>${item.value}</option>
	                  </c:forEach>
	              </select>
	            </div>
            </div>
          
          <div class="layui-inline ${oper eq 'detail'?'layui-hide':''}">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="${oper eq 'add'?'LAY-chPmSkillsHead-save':'LAY-chPmSkillsHead-edit'}" data-type="search">
              <i class="layui-icon layuiadmin-button-btn">${oper ne 'detail'? oper ne 'edit'?'保存':'修改':''}</i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;" class="${oper eq 'detail'?'layui-hide':''}">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmSkillsInfo:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmSkillsInfo:batchdel">
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmSkillsInfo-manage" lay-filter="LAY-chPmSkillsInfo-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmSkillsInfo-manager-operator">
          <shiro:hasPermission name="chPmSkillsInfo:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmSkillsInfo:delete">
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
    chPmSkillsInfo: 'chPmSkillsInfo'
  }).use(['index', 'chPmSkillsInfo','laydate', 'table','laypage'], function(){
  		var $ = layui.$,
  		laydate = layui.laydate;
  	});
  </script>
</body>
</html>