<%@ page language="java" contentType="text/html; charset=utf-8"
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
  <title>登入 </title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="layuiadmin/style/admin.css" media="all">
  <link rel="stylesheet" href="layuiadmin/style/login.css" media="all">
</head>
<body>

  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>焦煤集团中央医院</h2>
        <p>人事管理系统</p>
      </div>
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
      <form id="loginForm" action="#" method="post">
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-username"></label>
          <input type="text" name="userName" id="LAY-user-login-username" lay-verify="required" placeholder="用户名" class="layui-input" value="">
        </div>
        <div class="layui-form-item">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="LAY-user-login-password"></label>
          <input type="password" name="userPwd" id="LAY-user-login-password" lay-verify="required" placeholder="密码" class="layui-input" value="">
        </div>
        <!-- <div class="layui-form-item">
          <div class="layui-row">
            <div class="layui-col-xs7">
              <label class="layadmin-user-login-icon layui-icon layui-icon-vercode" for="LAY-user-login-vercode"></label>
              <input type="text" name="vercode" id="LAY-user-login-vercode" lay-verify="required" placeholder="图形验证码" class="layui-input" value="aa">
            </div>
            <div class="layui-col-xs5">
              <div style="margin-left: 10px;">
                <img src="https://www.oschina.net/action/user/captcha" class="layadmin-user-login-codeimg" id="LAY-user-get-vercode">
              </div>
            </div>
          </div>
        </div> -->
      </form>
        <div class="layui-form-item layui-hide" style="margin-bottom: 20px;">
          <input type="checkbox" name="remember" lay-skin="primary" title="记住密码">
          <a href="forget.html" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">忘记密码？</a>
        </div>
        <div class="layui-form-item">
          <button class="layui-btn layui-btn-fluid login" lay-submit lay-filter="login-submit">登 入</button>
        </div>
        <div class="layui-trans layui-form-item layadmin-user-login-other layui-hide">
          <label>社交账号登入</label>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
          <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>
          
          <a href="reg.html" class="layadmin-user-jump-change layadmin-link">注册帐号</a>
        </div>
      </div>
    </div>
    
    <div class="layui-trans layadmin-user-login-footer layui-hide">
      
      <p>© 2018 <a href="http://www.layui.com/" target="_blank">layui.com</a></p>
      <p>
        <span><a href="http://www.layui.com/admin/#get" target="_blank">获取授权</a></span>
        <span><a href="http://www.layui.com/admin/pro/" target="_blank">在线演示</a></span>
        <span><a href="http://www.layui.com/admin/" target="_blank">前往官网</a></span>
      </p>
    </div>
    
    <!--<div class="ladmin-user-login-theme">
      <script type="text/html" template>
        <ul>
          <li data-theme=""><img src="{{ layui.setter.base }}style/res/bg-none.jpg"></li>
          <li data-theme="#03152A" style="background-color: #03152A;"></li>
          <li data-theme="#2E241B" style="background-color: #2E241B;"></li>
          <li data-theme="#50314F" style="background-color: #50314F;"></li>
          <li data-theme="#344058" style="background-color: #344058;"></li>
          <li data-theme="#20222A" style="background-color: #20222A;"></li>
        </ul>
      </script>
    </div>-->
    
  </div>

  <script src="layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: 'layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'user'], function(){
	  var $ = layui.$
	    ,setter = layui.setter
	    ,admin = layui.admin
	    ,form = layui.form
	    ,router = layui.router()
	    ,search = router.search;

    form.render();
	
    //添加回车登录事件
    $('body').keyup(function(e){
        if(e.keyCode===13){
             $('.login').click();
        }
    })
    
    //提交
    form.on('submit(login-submit)', function(obj){
    	$.ajax({   
             url:'login/do.jhtm?newid=' + Math.random(),       
             method:'post',       
             data:obj.field,        
             dataType:'json',         
             success:function(res){
				var code = res.statusCode;
				var msg = res.message;
				if("200" == code)
				{
					//登入成功的提示与跳转
		          	layer.msg(msg, {offset: '15px',icon: 1,time: 1000}, function(){location.href = 'index.jhtm';});
				}else
				{
					layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
				}
             },       
             error:function (data) {
				layer.msg("登录失败！", {offset: '150px',icon: 2,time: 2000});
             }  
        });
    });
  });
  </script>
</body>
</html>