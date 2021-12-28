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
        <div class="layui-form-item">
        		  <div class="layui-inline">
	        <label class="layui-form-label">自增主键</label>
	        <div class="layui-input-block">
	          <input type="text" name="id" placeholder="请输入自增主键" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">职工编号</label>
	        <div class="layui-input-block">
	          <input type="text" name="workerId" placeholder="请输入职工编号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称</label>
	        <div class="layui-input-block">
	          <input type="text" name="title4now" placeholder="请输入现职称" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称取得时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="title4nowGotTime" placeholder="请输入现职称取得时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称取得年限</label>
	        <div class="layui-input-block">
	          <input type="text" name="title4nowGotYears" placeholder="请输入现职称取得年限" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称序列（职称分类）</label>
	        <div class="layui-input-block">
	          <input type="text" name="titleClass4now" placeholder="请输入现职称序列（职称分类）" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称级别(士级|初级|中级|副高级|正高级)</label>
	        <div class="layui-input-block">
	          <input type="text" name="titleLevel4now" placeholder="请输入现职称级别(士级|初级|中级|副高级|正高级)" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称待遇级别(士级|初级|中级|副高级|正高级)</label>
	        <div class="layui-input-block">
	          <input type="text" name="treatLevel4now" placeholder="请输入现职称待遇级别(士级|初级|中级|副高级|正高级)" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称初聘开始时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="title4nowHStartTime" placeholder="请输入现职称初聘开始时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现职称聘任终止时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="title4nowHEndTime" placeholder="请输入现职称聘任终止时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">是否现最高职称  0：否  1：是</label>
	        <div class="layui-input-block">
	          <input type="text" name="titleMax4now" placeholder="请输入是否现最高职称  0：否  1：是" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">备注</label>
	        <div class="layui-input-block">
	          <input type="text" name="remark" placeholder="请输入备注" autocomplete="off" class="layui-input">
	        </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
              <select name="status">
              	<option value="">--所有--</option>
              	<c:forEach var="item" items="${statusList}" varStatus="status">
              		<option value="${item.key}">${item.value}</option>
              	</c:forEach>
              </select>
            </div>
          </div>
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chPmTitleHead-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmTitleHead:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmTitleHead:batchdel">
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmTitleHead-manage" lay-filter="LAY-chPmTitleHead-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmTitleHead-manager-operator">
          <shiro:hasPermission name="chPmTitleHead:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmTitleHead:delete">
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
    chPmTitleHead: 'chPmTitleHead'
  }).use(['index', 'chPmTitleHead', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>