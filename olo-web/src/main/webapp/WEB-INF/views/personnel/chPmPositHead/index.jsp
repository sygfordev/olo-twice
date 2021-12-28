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
	        <label class="layui-form-label">现任职务</label>
	        <div class="layui-input-block">
	          <input type="text" name="posit4now" placeholder="请输入现任职务" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现级别（正科|副科等的编号）</label>
	        <div class="layui-input-block">
	          <input type="text" name="positLevel4now" placeholder="请输入现级别（正科|副科等的编号）" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现任职务开始时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="posit4nowStartTime" placeholder="请输入现任职务开始时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现任职务结束时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="posit4nowEndTime" placeholder="请输入现任职务结束时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现待遇级别（正科级|副科级等的编号）</label>
	        <div class="layui-input-block">
	          <input type="text" name="treatLevel4now" placeholder="请输入现待遇级别（正科级|副科级等的编号）" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现待遇级别开始时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="treat4nowStartTime" placeholder="请输入现待遇级别开始时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现待遇级别结束时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="treat4nowEndTime" placeholder="请输入现待遇级别结束时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现待遇级别年限</label>
	        <div class="layui-input-block">
	          <input type="text" name="treat4nowYears" placeholder="请输入现待遇级别年限" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">任正处开始时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="onChuStartTime" placeholder="请输入任正处开始时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">任正处结束时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="onChuEndTime" placeholder="请输入任正处结束时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">任副处开始时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="onChuDetupyStartTime" placeholder="请输入任副处开始时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">任副处结束时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="onChuDetupyEndTime" placeholder="请输入任副处结束时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">任正科开始时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="onKeStartTime" placeholder="请输入任正科开始时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">任正科结束时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="onKeEndTime" placeholder="请输入任正科结束时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">任副科开始时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="onKeDetupyStartTime" placeholder="请输入任副科开始时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">任副科结束时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="onKeDetupyEndTime" placeholder="请输入任副科结束时间" autocomplete="off" class="layui-input">
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
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chPmPositHead-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmPositHead:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmPositHead:batchdel">
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmPositHead-manage" lay-filter="LAY-chPmPositHead-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmPositHead-manager-operator">
          <shiro:hasPermission name="chPmPositHead:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmPositHead:delete">
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
    chPmPositHead: 'chPmPositHead'
  }).use(['index', 'chPmPositHead', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>