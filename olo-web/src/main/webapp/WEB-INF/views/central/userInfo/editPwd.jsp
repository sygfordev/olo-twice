<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <title>密码重置</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
	
  <div class="layui-fluid">
    <div class="layui-card">
      <!-- <div class="layui-card-header">添加用户</div> -->
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="">
          <input type="hidden" name="userId" value="${model.userId}" class="layui-input">
          <div class="layui-form-item">
             <label class="layui-form-label">新密码</label>
             <div class="layui-input-inline">
               <input type="password" name="userPassword" lay-verify="pass" lay-verType="tips" autocomplete="off" id="LAY_password" class="layui-input">
             </div>
             <div class="layui-form-mid layui-word-aux">6到12个字符</div>
          </div>
          <div class="layui-form-item">
             <label class="layui-form-label">确认密码</label>
             <div class="layui-input-inline">
               <input type="password" name="repassword" lay-verify="repass" lay-verType="tips" autocomplete="off" class="layui-input">
             </div>
             <div class="layui-form-mid layui-word-aux">请保持两次密码一致</div>
           </div>
                     
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" id="setpwd2commit" lay-submit lay-filter="setpwd2commit">确认修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">清空</button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
	
<script src="<%=basePath%>layuiadmin/layui/layui.js"></script>  
<script>
layui.config({
   base: '<%=basePath%>layuiadmin/' //静态资源所在路径
 }).extend({
   index: 'lib/index' //主入口模块
 }).use(['index','form'],function(){
  var $ = layui.$
  ,jquery = layui.jquery
  ,layer = layui.layer
  ,setter = layui.setter
  ,form = layui.form;
	//自定义验证
  	form.verify({
	    nickname: function(value, item){ //value：表单的值、item：表单的DOM对象
	      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
	        return '用户名不能有特殊字符';
	      }
	      if(/(^\_)|(\__)|(\_+$)/.test(value)){
	        return '用户名首尾不能出现下划线\'_\'';
	      }
	      if(/^\d+\d+\d$/.test(value)){
	        return '用户名不能全为数字';
	      }
	    }
    
	    //我们既支持上述函数式的方式，也支持下述数组的形式
	    //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
	    ,pass: [
	      /^[\S]{6,12}$/
	      ,'密码必须6到12位，且不能出现空格'
	    ]
    
	    //确认密码
	    ,repass: function(value){
	      if(value !== $('#LAY_password').val()){
	        return '两次密码输入不一致';
	      }
	    }
  });
  	//设置密码
    form.on('submit(setpwd2commit)', function(obj){
	  	//将提交按钮置为不可用
	  	$('#setpwd2commit').attr('disabled',"disabled");
	  	$('#setpwd2commit').attr('class',"layui-btn layui-btn-disabled");
      	//提交修改
      	$.ajax({
         	url:'editPwd/do.jhtm',
         	method:'post',
         	data:obj.field,
         	dataType:'JSON',
         	success:function(data){
  				var code = data.statusCode;
  				var msg = data.message;
  				var isucc = "200"==code?true:false;
  				parent.layer.msg(msg, {offset: '150px',icon: isucc?1:2,time: 2000});
  				/* layer.load(0, {shade: false,time: 1000});
  				parent.layui.admin.events.closeThisTabs(); */
  				if(isucc){
  					var index = parent.layer.getFrameIndex(window.name);
	          		parent.layer.close(index);
	          	}
        	},
        	error:function (data) {
  				layer.msg(data.message, {offset: '150px',icon: 2,time: 2000});
        	}
      	});
      	return false;
    });
 });
</script>
</body>
</html>