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

<div class="layui-btn-container">
  <button type="button" class="layui-btn" lay-demo="getChecked">获取选中数据</button>
  <button type="button" class="layui-btn" lay-demo="setChecked">设置节点勾选</button>
  <button type="button" class="layui-btn" lay-demo="reload">重载实例</button>
</div>

<div class="layui-form">
	<div class="layui-row layui-col-space10">
      <div class="layui-col-xs6 layui-col-sm5 layui-col-md4">
        <div class="layui-card" style="height: 100%;">
          <div class="layui-card-header">组织树</div>
          <div class="layui-card-body">
	          <div id="test1" class="div1"></div>
	          <div id="test2"></div>
          </div>
        </div>
      </div>
      <div class="layui-col-xs6 layui-col-sm7 layui-col-md8">
        <div class="layui-card" style="height: 100%;">
          <div class="layui-card-header">操作区域</div>
          <div class="layui-card-body"></div>
        </div>
      </div>
     </div>
</div>
<script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
<script>
layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
	  layui: 'layui' //主入口模块
  }).use(['tree', 'layer', 'util'], function(){
    var $ = layui.$
    ,tree = layui.tree
    ,layer = layui.layer
    ,util = layui.util
    ,index = 100;
    
    //数据源
    var data1 = [{
      title: '一级1'
      ,id: 1
      ,children: [{
        title: '二级1-1'
        ,id: 3
        ,href: 'https://www.layui.com/doc/'
        ,children: [{
          title: '三级1-1-3'
          ,id: 23
          ,children: [{
            title: '四级1-1-3-1'
            ,id: 24
            ,children: [{
              title: '五级1-1-3-1-1'
              ,id: 30
            },{
              title: '五级1-1-3-1-2'
              ,id: 31
            }]
          }]
        },{
          title: '三级1-1-1'
          ,id: 7
          ,checked: true
          ,children: [{
            title: '四级1-1-1-1'
            ,id: 15
            //,checked: true
            ,href: 'https://www.layui.com/doc/base/infrastructure.html'
          }]
        },{
          title: '三级1-1-2'
          ,id: 8
          ,children: [{
            title: '四级1-1-2-1'
            ,id: 32
          }]
        }]
      },{
        title: '二级1-2'
        ,id: 4
        ,spread: true
        ,children: [{
          title: '三级1-2-1'
          ,id: 9
          ,checked: true
          ,disabled: true
        },{
          title: '三级1-2-2'
          ,id: 10
        }]
      },{
        title: '二级1-3'
        ,id: 20
        ,children: [{
          title: '三级1-3-1'
          ,id: 21
        },{
          title: '三级1-3-2'
          ,id: 22
        }]
      }]
    },{
      title: '一级2'
      ,id: 2
      ,spread: true
      ,children: [{
        title: '二级2-1'
        ,id: 5
        ,spread: true
        ,children: [{
          title: '三级2-1-1'
          ,id: 11
        },{
          title: '三级2-1-2'
          ,id: 12
        }]
      },{
        title: '二级2-2'
        ,id: 6
        ,checked: true
        ,children: [{
          title: '三级2-2-1'
          ,id: 13
        },{
          title: '三级2-2-2'
          ,id: 14
          ,disabled: true
        }]
      }]
    },{
      title: '一级3'
      ,id: 16
      ,children: [{
        title: '二级3-1'
        ,id: 17
        ,fixed: true
        ,children: [{
          title: '三级3-1-1'
          ,id: 18
        },{
          title: '三级3-1-2'
          ,id: 19
        }]
      },{
        title: '二级3-2'
        ,id: 27
        ,children: [{
          title: '三级3-2-1'
          ,id: 28
        },{
          title: '三级3-2-2'
          ,id: 29
        }]
      }]
    }];
    
    //数据源
    var data2 = [{
      title: '控制台'
      ,id: '1000'
      ,spread: true
      ,checked: true
      ,children: [{
        title: '概览'
        ,id: '1001'
        ,spread: true
        ,checked: true
      },{
        title: '域名'
        ,id: '1002'
        ,spread: true
        ,checked: true
      }]
    }]

    tree.render({
      elem: '#test1'
      ,data: data1
      ,id: 'demoId1'
      ,click: function(obj){
        layer.msg(JSON.stringify(obj.data));
        console.log(obj);
      }
      ,oncheck: function(obj){
        //console.log(obj);
      }
      ,operate: function(obj){
        var type = obj.type;
        if(type == 'add'){
          //ajax操作，返回key值
          return index++;
        }else if(type == 'update'){
          console.log(obj.elem.find('.layui-tree-txt').html());
        }else if(type == 'del'){
          console.log(obj);
        };
      }
      ,showCheckbox: true  //是否显示复选框
      ,accordion: 0  //是否开启手风琴模式
      
      ,onlyIconControl: true //是否仅允许节点左侧图标控制展开收缩
      ,isJump: 0  //点击文案跳转地址
      ,edit: true  //操作节点图标
    });

    //按钮事件
    util.event('lay-demo', {
      getChecked: function(othis){
        var checkedData = tree.getChecked('demoId1');
        layer.alert(JSON.stringify(checkedData), {shade:0});
        console.log(checkedData);
      }
      ,setChecked: function(){
        tree.setChecked('demoId1', [1000, 1001, 1002]);
      }
      ,reload: function(){
        tree.reload('demoId1', {
          
        });
      }
    });

    tree.render({
      elem: '#test2'
      ,data: data1
      //,expandClick: false
      ,showLine: false //关闭连接线
      ,click: function(obj, state){
        console.log(obj);
      }
      ,oncheck: function(obj, checked, child){
        if(checked){
          console.log(obj[0]);
        }
      }
      ,onsearch: function(data, num){
        console.log(num);
      }
      ,dragstart: function(obj, parent){
        console.log(obj, parent);
      }
      ,dragend: function(state, obj, target){
        console.log(state, obj, target);
      }
    });
     
  });
</script>

</body>
</html>