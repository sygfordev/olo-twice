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
  <meta charset="UTF-8">
  <title>表单组合</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
<div class="layui-fluid">
	<div class="layui-fluid" id="LAY-flow-demo">
	<form class="layui-form">
	    <div>
	    	<label>已选节点：</label>
	        <input type="text" id="selectedName" name="selectedName" value="${curBranchName}" readonly>
	        <input type="hidden" id="selectedId" name="selectedId" value="${curBranchId}">
	    </div>
	    <div id="demo"></div>
	</form>
    </div>
</div>

<script src="<%=basePath%>layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '<%=basePath%>layuiadmin/'
  }).extend({
    index: 'lib/index'//主入口模块
  }).use(['index', 'form','tree'], function(){
  	var $ = layui.$,
    layer = layui.layer,
    tree = layui.tree,
    index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    
  	$.ajax({
             url:'<%=basePath%>central/userInfo/lookup/d'+'o.jhtm',
             method:'post',
             dataType:'json',
             success:function(data){
				var code = data.retCode;
				var msg = data.retMsg;
				if("200" == code)
				{
				   	tree.render({
					    elem: '#demo'
					    ,id: 'demoId2'
					    ,data: data.responseBody
					    ,onlyIconControl: true
					    //,showCheckbox:true	//是否显示复选框
					    ,accordion:false 	//是否开启手风琴模式，默认 false
					    //,edit: ['add', 'update', 'del'] //若为true，则默认显示“改删”图标，若为 数组，则可自由配置操作图标的显示状态和顺序，目前支持的操作图标有：add、update、del
					    ,isJump:false		//是否允许点击节点时弹出新窗口跳转。默认 false，若开启，需在节点数据中设定 link 参数（值为 url 格式）
					    ,showLine:true		//是否开启连接线。默认 true，若设为 false，则节点左侧出现三角图标。
					    ,text: {//自定义各类默认文本，目前支持以下设定
					  		defaultNodeName: '未命名' //节点默认名称
						  	,none: '无数据' //数据为空时的提示文本
						}
				   		,click: function(obj){ //节点被点击的回调
					    	var data = obj.data;
					    	$("#selectedId").val(data.id);
					    	$("#selectedName").val(data.title);
					    }
				   	});
				}else
				{
					layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
				}
             },
             error:function (data) {
				layer.msg("获取组织机构失败！", {offset: '150px',icon: 2,time: 2000});
             }
    });
  });
  </script>
</body>
</html>