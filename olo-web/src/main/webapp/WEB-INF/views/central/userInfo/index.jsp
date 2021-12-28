<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
	String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String path = request.getContextPath();
	String basePath = base + path + "/";
%>
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
	<shiro:hasPermission name="userInfo:index">
		<div class="layui-fluid">
			<div class="layui-card">
				<div class="layui-form layui-card-header layuiadmin-card-header-auto">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">账户</label>
							<div class="layui-input-block">
								<input type="text" name="account" placeholder="请输入"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">真实姓名</label>
							<div class="layui-input-block">
								<input type="text" name="userRealName" placeholder="请输入"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">性别</label>
							<div class="layui-input-block">
								<select name="userSex">
									<option value="">--所有--</option>
									<c:forEach var="item" items="${sexList}" varStatus="sexStatus">
										<option value="${item.key}">${item.value}</option>
									</c:forEach>
								</select>
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
							<button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-user-back-search" data-type="search">
								<i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
							</button>
						</div>

					</div>
				</div>

				<div class="layui-card-body">

					<!-- 数据表格区域头部操作按钮 -->
					<div style="padding-bottom: 10px;">
						<div class="layui-btn-group">
							<shiro:hasPermission name="userInfo:add">
							<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
							</shiro:hasPermission>
							<shiro:hasPermission name="userInfo:delete">
							<button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
							</shiro:hasPermission>
						</div>
					</div>

					<!-- 数据表格 -->
					<table id="LAY-user-manage" lay-filter="LAY-user-manage"></table>

					<!-- 特殊模板数据处理 -->
					<script type="text/html" id="statusTpl">
          			{{#  if(d.status == "0"){ }}
            			<button class="layui-btn layui-btn-xs">正常</button>
          			{{#  } else { }}
            			<button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          			{{#  } }}
        			</script>
					<script type="text/html" id="sexTpl">
          			{{#  if(d.userSex == "1"){ }}
            			男
          			{{#  } else if(d.userSex == "0") { }}
           				女
         			{{#  } }}
        			</script>


					<!-- 操作模块 -->
					<script type="text/html" id="table-user-manager-operator">
					<shiro:hasPermission name="userInfo:edit">
          				<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="userInfo:delete">
						<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="userInfo:editPwd">
						<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="editPwd"><i class="layui-icon layui-icon-key"></i>密码重置</a>
					</shiro:hasPermission>
					<shiro:hasPermission name="userInfo:userRolePrivCfg">
						<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="userRolePrivCfg"><i class="layui-icon layui-icon-set-fill"></i>角色配置</a>
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
			index : 'lib/index', //主入口模块
			userInfo : 'userInfo'
		}).use([ 'index', 'userInfo', 'table', 'laypage' ], function() {

		});
		</script>
	</shiro:hasPermission>
</body>
</html>