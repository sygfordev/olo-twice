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
      	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
      	
      	
        <div class="layui-form-item">
		  <div class="layui-inline">
	        <label class="layui-form-label">姓名</label>
	        <div class="layui-input-block">
	          <input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  
		  <div class="layui-inline">
	        <label class="layui-form-label">个人身份</label>
	        <div class="layui-input-block">
	          	<select id="identity" name="identity">
                	<option></option>
                  <c:forEach var="item" items="${identityList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">用工方式</label>
	        <div class="layui-input-block">
	          	<select id="wkModality" name="wkModality">
                	<option></option>
                  <c:forEach var="item" items="${wkModalityList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">是否在职</label>
	        <div class="layui-input-block">
	          	<select id="stationSitu" name="stationSitu">
	          		<option value=""></option>
                	<option value="0">在职</option>
                	<option value="1">离职</option>
                </select>
	        </div>
          </div>
       </div>
       <div class="layui-form-item">
       	  <div class="layui-inline">
	        <label class="layui-form-label">身份证号</label>
	        <div class="layui-input-block">
	          <input type="text" name="cardNo" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">工作地域</label>
	        <div class="layui-input-block">
	          	<select id="workArea" name="workArea">
                	<option></option>
                  <c:forEach var="item" items="${workAreaList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
	        </div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">医院支部</label>
	        <div class="layui-input-block">
	          	<select id="hosBranch" name="hosBranch" lay-filter="hosBranch">
                	<option></option>
                  <c:forEach var="item" items="${hbranchs}" varStatus="status">
                    <option value="${item.hbhNo}">${item.hbhName}</option>
                  </c:forEach>
                </select>
	        </div>
          </div>  
		  <div class="layui-inline">
	        <label class="layui-form-label">一级科室</label>
	        <div class="layui-input-block">
	          	<select id="hosDepart1level" name="hosDepart1level" lay-filter="hosDepart1level">
                	<option></option>
                </select>
	        </div>
          </div>
		  
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-archives-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn">查询</i>
            </button>
            <!-- <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-archives-export" data-type="search">
              <i class="layui-icon layui-icon-export layuiadmin-button-btn">导出</i>
            </button> -->
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="archives:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="archives:batchdel">
			<button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
			</shiro:hasPermission>
          </div>
        </div>
        <!-- 数据表格 -->
        <table id="LAY-archives-manage" lay-filter="LAY-archives-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmWorker-manager-operator">
          <shiro:hasPermission name="archives:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="archives:delete">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="archives:detail">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail"><i class="layui-icon layui-icon-detail"></i>详情</a>
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
    archives: 'archives'
  }).use(['index', 'archives', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>