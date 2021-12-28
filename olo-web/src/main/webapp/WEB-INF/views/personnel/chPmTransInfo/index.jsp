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
	        <label class="layui-form-label">姓名</label>
	        <div class="layui-input-block">
	          <input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">身份证号</label>
	        <div class="layui-input-block">
	          <input type="text" name="cardNo" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  
		  <div class="layui-inline">
	        <label class="layui-form-label">调动文号</label>
	        <div class="layui-input-block">
	          <input type="text" name="transDocno" placeholder="请输入调动文号" autocomplete="off" class="layui-input">
	        </div>
          </div>
          
          <div class="layui-inline">
          	<label class="layui-form-label">调动时间</label>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="trans-start" name="trans-start" placeholder="开始日期">
	        </div>
	        <div class="layui-form-mid">
	          -
	        </div>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="trans-end" name="trans-end" placeholder="结束日期">
	        </div>
	      </div>
          
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chPmTransInfo-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmTransInfo:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmTransInfo:batchdel">
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">批量删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        <!-- 表格头部工具 -->
        <div id="toolbarDemo"></div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmTransInfo-manage" lay-filter="LAY-chPmTransInfo-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmTransInfo-manager-operator">
          <shiro:hasPermission name="chPmTransInfo:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmTransInfo:delete">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmTransInfo:details">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="details"><i class="layui-icon layui-icon-read"></i>详情</a>
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
    excel: 'layui_exts/excel',
    chPmTransInfo: 'chPmTransInfo'
  }).use(['index', 'chPmTransInfo', 'table','laypage','excel'], function(){
  		var $ = layui.$,
  		table = layui.table,
  		excel = layui.excel;
  		
	  	//头工具栏事件
		/* table.on('toolbar(LAY-chPmTransInfo-manage)', function(obj){
		    var checkStatus = table.checkStatus(obj.config.id);
		    switch(obj.event){
		      case 'getCheckData':
		        var data = checkStatus.data;
		        layer.alert(JSON.stringify(data));
		      break;
		      case 'getCheckLength':
		        var data = checkStatus.data;
		        layer.msg('选中了：'+ data.length + ' 个');
		      break;
		      case 'isAll':
		        layer.msg(checkStatus.isAll ? '全选': '未全选');
		      break;
		      
		      //自定义头工具栏右侧图标 - 提示
		      case 'LAYTABLE_TIPS':
		        layer.alert('这是工具栏右侧自定义的一个图标按钮');
		      break;
		    };
		}); */
  	});
  </script>
</body>
</html>