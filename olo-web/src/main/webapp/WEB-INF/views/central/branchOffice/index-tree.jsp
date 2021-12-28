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
  <title>树组件 - layui</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-status-bar-style" content="black"> 
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="format-detection" content="telephone=no">

  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
  
  <style>
    body{padding: 50px;}
    #test1,#test2{margin-bottom: 100px; width: 400px;}
    #LAY-component-grid-all .layui-card-body{display: flex; justify-content: center; flex-direction: column; 
    text-align: center; height: 200px;}
    #div1{height:5000px; background:red; border:5px solid yellow;}
  </style>
</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>基本演示</legend>
</fieldset>
<div class="layui-btn-container">
  <button type="button" class="layui-btn layui-btn-sm" lay-demo="getChecked">获取选中节点数据</button>
  <button type="button" class="layui-btn layui-btn-sm" lay-demo="setChecked">勾选指定节点</button>
  <button type="button" class="layui-btn layui-btn-sm" lay-demo="reload">重载实例</button>
</div>
 
<div id="test12" class="demo-tree-more"></div>
         
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>常规用法</legend>
</fieldset>
 
<div id="test1" class="demo-tree demo-tree-box"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>无连接线风格</legend>
</fieldset>
<div id="test13" class="demo-tree-more"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>仅节点左侧图标控制收缩</legend>
</fieldset>
 
<div id="test2" class="demo-tree"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>手风琴模式</legend>
</fieldset>
 
<div id="test4" class="demo-tree"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>点击节点新窗口跳转</legend>
</fieldset>
 
<div id="test5" class="demo-tree"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>开启复选框</legend>
</fieldset>
 
<div id="test7" class="demo-tree"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
  <legend>开启节点操作图标</legend>
</fieldset>
 
<!-- <div id="test9" class="demo-tree demo-tree-box" style="width: 200px; height: 300px; overflow: scroll;"></div> -->
<div id="test9" class="demo-tree"></div>


<script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
<script>
layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
	  layui: 'layui' //主入口模块
  }).use(['tree', 'layer', 'util'], function(){
	  var tree = layui.tree
	  ,layer = layui.layer
	  ,util = layui.util
	  
	  //模拟数据
	  ,data = [{
	    title: '一级1'
	    ,id: 1
	    ,field: 'name1'
	    ,checked: true
	    ,spread: true
	    ,children: [{
	      title: '二级1-1 可允许跳转'
	      ,id: 3
	      ,field: 'name11'
	      ,href: 'https://www.layui.com/'
	      ,children: [{
	        title: '三级1-1-3'
	        ,id: 23
	        ,field: ''
	        ,children: [{
	          title: '四级1-1-3-1'
	          ,id: 24
	          ,field: ''
	          ,children: [{
	            title: '五级1-1-3-1-1'
	            ,id: 30
	            ,field: ''
	          },{
	            title: '五级1-1-3-1-2'
	            ,id: 31
	            ,field: ''
	          }]
	        }]
	      },{
	        title: '三级1-1-1'
	        ,id: 7
	        ,field: ''
	        ,children: [{
	          title: '四级1-1-1-1 可允许跳转'
	          ,id: 15
	          ,field: ''
	          ,href: 'https://www.layui.com/doc/'
	        }]
	      },{
	        title: '三级1-1-2'
	        ,id: 8
	        ,field: ''
	        ,children: [{
	          title: '四级1-1-2-1'
	          ,id: 32
	          ,field: ''
	        }]
	      }]
	    },{
	      title: '二级1-2'
	      ,id: 4
	      ,spread: true
	      ,children: [{
	        title: '三级1-2-1'
	        ,id: 9
	        ,field: ''
	        ,disabled: true
	      },{
	        title: '三级1-2-2'
	        ,id: 10
	        ,field: ''
	      }]
	    },{
	      title: '二级1-3'
	      ,id: 20
	      ,field: ''
	      ,children: [{
	        title: '三级1-3-1'
	        ,id: 21
	        ,field: ''
	      },{
	        title: '三级1-3-2'
	        ,id: 22
	        ,field: ''
	      }]
	    }]
	  },{
	    title: '一级2'
	    ,id: 2
	    ,field: ''
	    ,spread: true
	    ,children: [{
	      title: '二级2-1'
	      ,id: 5
	      ,field: ''
	      ,spread: true
	      ,children: [{
	        title: '三级2-1-1'
	        ,id: 11
	        ,field: ''
	      },{
	        title: '三级2-1-2'
	        ,id: 12
	        ,field: ''
	      }]
	    },{
	      title: '二级2-2'
	      ,id: 6
	      ,field: ''
	      ,children: [{
	        title: '三级2-2-1'
	        ,id: 13
	        ,field: ''
	      },{
	        title: '三级2-2-2'
	        ,id: 14
	        ,field: ''
	        ,disabled: true
	      }]
	    }]
	  },{
	    title: '一级3'
	    ,id: 16
	    ,field: ''
	    ,children: [{
	      title: '二级3-1'
	      ,id: 17
	      ,field: ''
	      ,fixed: true
	      ,children: [{
	        title: '三级3-1-1'
	        ,id: 18
	        ,field: ''
	      },{
	        title: '三级3-1-2'
	        ,id: 19
	        ,field: ''
	      }]
	    },{
	      title: '二级3-2'
	      ,id: 27
	      ,field: ''
	      ,children: [{
	        title: '三级3-2-1'
	        ,id: 28
	        ,field: ''
	      },{
	        title: '三级3-2-2'
	        ,id: 29
	        ,field: ''
	      }]
	    }]
	  }]
	  
	  //模拟数据1
	  ,data1 = [{
	    title: '江西'
	    ,id: 1
	    ,children: [{
	      title: '南昌'
	      ,id: 1000
	      ,children: [{
	        title: '青山湖区'
	        ,id: 10001
	      },{
	        title: '高新区'
	        ,id: 10002
	      }]
	    },{
	      title: '九江'
	      ,id: 1001
	    },{
	      title: '赣州'
	      ,id: 1002
	    }]
	  },{
	    title: '广西'
	    ,id: 2
	    ,children: [{
	      title: '南宁'
	      ,id: 2000
	    },{
	      title: '桂林'
	      ,id: 2001
	    }]
	  },{
	    title: '陕西'
	    ,id: 3
	    ,children: [{
	      title: '西安'
	      ,id: 3000
	    },{
	      title: '延安'
	      ,id: 3001
	    }]
	  }]
	  
	  //模拟数据2
	  ,data2 = [{
	    title: '早餐'
	    ,id: 1
	    ,children: [{
	      title: '油条'
	      ,id: 5
	    },{
	      title: '包子'
	      ,id: 6
	    },{
	      title: '豆浆'
	      ,id: 7
	    }]
	  },{
	    title: '午餐'
	    ,id: 2
	    ,checked: true
	    ,children: [{
	      title: '藜蒿炒腊肉'
	      ,id: 8
	    },{
	      title: '西湖醋鱼'
	      ,id: 9
	    },{
	      title: '小白菜'
	      ,id: 10
	    },{
	      title: '海带排骨汤'
	      ,id: 11
	    }]
	  },{
	    title: '晚餐'
	    ,id: 3
	    ,children: [{
	      title: '红烧肉'
	      ,id: 12
	      ,fixed: true
	    },{
	      title: '番茄炒蛋'
	      ,id: 13
	    }]
	  },{
	    title: '夜宵'
	    ,id: 4
	    ,children: [{
	      title: '小龙虾'
	      ,id: 14
	      ,checked: true
	    },{
	      title: '香辣蟹'
	      ,id: 15
	      ,disabled: true
	    },{
	      title: '烤鱿鱼'
	      ,id: 16
	    }]
	  }];
	 
	  //基本演示
	  tree.render({
	    elem: '#test12'
	    ,data: data
	    ,showCheckbox: true  //是否显示复选框
	    ,id: 'demoId1'
	    ,isJump: true //是否允许点击节点时弹出新窗口跳转
	    ,click: function(obj){
	      var data = obj.data;  //获取当前点击的节点数据
	      layer.msg('状态：'+ obj.state + '<br>节点数据：' + JSON.stringify(data));
	    }
	  });
	  
	  //按钮事件
	  util.event('lay-demo', {
	    getChecked: function(othis){
	      var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据
	      
	      layer.alert(JSON.stringify(checkedData), {shade:0});
	      console.log(checkedData);
	    }
	    ,setChecked: function(){
	      tree.setChecked('demoId1', [12, 16]); //勾选指定节点
	    }
	    ,reload: function(){
	      //重载实例
	      tree.reload('demoId1', {
	        
	      });
	      
	    }
	  });
	 
	  //常规用法
	  tree.render({
	    elem: '#test1' //默认是点击节点可进行收缩
	    ,data: data1
	  });
	  
	  //无连接线风格
	  tree.render({
	    elem: '#test13'
	    ,data: data1
	    ,showLine: false  //是否开启连接线
	  });
	 
	  //仅节点左侧图标控制收缩
	  tree.render({
	    elem: '#test2'
	    ,data: data1
	    ,onlyIconControl: true  //是否仅允许节点左侧图标控制展开收缩
	    ,click: function(obj){
	      layer.msg(JSON.stringify(obj.data));
	    }
	  });
	  //手风琴模式
	  tree.render({
	    elem: '#test4'
	    ,data: [{
	      title: '优秀'
	      ,children: [{
	        title: '80 ~ 90'
	      },{
	        title: '90 ~ 100'
	      }]
	    },{
	      title: '良好'
	      ,children: [{
	        title: '70 ~ 80'
	      },{
	        title: '60 ~ 70'
	      }]
	    },{
	      title: '要努力奥'
	      ,children: [{
	        title: '0 ~ 60'
	      }]
	    }]
	    ,accordion: true 
	  });
	 
	  //点击节点新窗口跳转
	  tree.render({
	    elem: '#test5'
	    ,data: data
	    ,isJump: true  //link 为参数匹配
	  });
	 
	  //开启复选框
	  tree.render({
	    elem: '#test7'
	    ,data: data2
	    ,showCheckbox: true
	  });
	 
	  //开启节点操作图标
	  tree.render({
	    elem: '#test9'
	    ,data: data1
	    ,onlyIconControl: true
	    ,edit: ['add', 'update', 'del'] //操作节点的图标
	    ,click: function(obj){
	    	/* var data = obj.data;
	        layer.msg('状态：'+ obj.state + '<br>节点数据：' + JSON.stringify(data)); */
	    }
	  	,customOperate: true
	  	,operate: function (obj) {
	  		var type = obj.type; //得到操作类型：add、edit、del
			var data = obj.data; //得到当前节点的数据
			var elem = obj.elem; //得到当前节点元素
			layer.msg('操作：'+ obj.type + '<br>节点数据：' + JSON.stringify(data) + '<br>节点元素：' + JSON.stringify(elem),{offset: '15px',icon: 1,time: 10000});

			//Ajax 操作
			var id = data.id; //得到节点索引
			if (type === 'add') { //增加节点
				layer.open({
					type: 1,
					title: "新增",
					area: ['500px', '200px'],
					content: "aa"
				});
			} else if (type === 'update') { //修改节点
				layer.open({
					type: 1,
					title: "修改",
					area: ['500px', '200px'],
					content: "aa"
				});
			} else if (type === 'del') { //删除节点
				$.ajax({
					url: '/admin/dept/delete/' + id,
					type: 'get',
					dataType: 'json',
					success: function (data) {
						if (data.code == 0) {
							//关闭
							layer.close(index0pen);
							layer.msg('删除成功！', {icon: 1});
							setTimeout(function () {
								window.location.reload();
							}, 500);
						} else {
							layer.msg(data.msg, {icon: 2});
						}
					}
				});
			}
		}
	  });
	});
	</script>

	</body>
	</html>