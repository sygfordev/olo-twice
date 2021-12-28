<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<%
	String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String path = request.getContextPath();
	String basePath = base + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>layuiAdmin 组织机构</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<link rel="stylesheet"
	href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
<link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css"
	media="all">
</head>
<body>
	<div class="layui-fluid">
		<div class="layui-row layui-col-space15">
			<div class="layui-col-md4">
				<div class="layui-row layui-col-space15">
					<div class="layui-col-md12">
						<div class="layui-card">
							<div class="layui-card-header">机构管理</div>
							<div class="layui-card-body">
								<div id="branchTree"></div>
							</div>
						</div>
					</div>
					<div class="layui-col-md12">
						<div class="layui-card">
							<div class="layui-card-header">数据概览</div>
							<div class="layui-card-body">
								
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="layui-col-md8">
				<div class="layui-card" id="detailDiv">
					<div class="layui-card-header">版本信息</div>
					<div class="layui-card-body layui-text">
						${name}
					</div>
				</div>

				<div class="layui-card">
					<div class="layui-card-header">效果报告</div>
					<div class="layui-card-body layadmin-takerates">
						
					</div>
				</div>
			</div>

		</div>
	</div>

	<script src="<%=basePath%>layuiadmin/layui/layui.js?t=1"></script>
	<script>
	  layui.config({
	    base: '<%=basePath%>layuiadmin/' //静态资源所在路径
	  }).extend({
	    index: 'lib/index', //主入口模块
	    treetable: 'layui/lay/modules/treetable'
	  }).use(['index', 'tree','treetable'],function(){
		  var $ = layui.$,
		  layer = layui.layer;
		  
		  //树形结构加载
		  layui.tree({
				elem: '#branchTree',
			  	nodes: getData(),
			  	click: function(node){//点击回调
			  		layer.msg('当前节名称：'+ node.name + '<br>全部参数：'+ JSON.stringify(node));
				},
				//href:'http://www.baidu.com',
				//target:'detailDiv'
		  });
		  
		  //加载后台数据 
		  function getData(){
			  	var data = [];
			    $.ajax({
			    	url:'<%=basePath%>central/branchOffice/index/d'+'o.jhtm',
			        type: "post",
			        dataType:'json',
			        async:false,
			        success: function(resut){
			            data = resut.responseBody;
			        }
			    });
			    console.log(JSON.stringify(data));
			    return data;
		  }
	  });
	</script>
</body>
</html>