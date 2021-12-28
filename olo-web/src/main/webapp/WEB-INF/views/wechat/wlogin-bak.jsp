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
  <title>工资单查询 </title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/login.css" media="all">
</head>
<body>

  <div class="layadmin-user-login layadmin-user-display-show" id="LAY-user-login" style="display: none;">

    <div class="layadmin-user-login-main">
      <div class="layadmin-user-login-box layadmin-user-login-header">
        <h2>焦煤集团中央医院</h2>
        <p>工资单查询</p>
      </div>
      <div class="layadmin-user-login-box layadmin-user-login-body layui-form">
      <form id="loginForm" action="#" method="post">
      	<!-- 隐藏属性 -->
      	<input type="hidden" id="token" name="token">
      	<input type="hidden" id="secretKey" name="secretKey" value="${secretKey}">
      	
        <div class="layui-form-item" id="cardnoDiv">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username" for="LAY-user-login-cardno"></label>
          <input type="text" name="cardNo" id="cardNo" lay-verify="required|cardNo" placeholder="身份证号" class="layui-input" value="410822197703301521">
        </div>
        <div class="layui-form-item layui-hide" id="monthDiv">
          <label class="layadmin-user-login-icon layui-icon layui-icon-username"></label>
          <input type="text" name="targetMonth" id="targetMonth" placeholder="查询月份" readonly="" class="layui-input">
        </div>
        <div class="layui-form-item" id="passwDiv">
          <label class="layadmin-user-login-icon layui-icon layui-icon-password" for="pssswd"></label>
          <input type="password" name="passwd" id="passwd" lay-verify="required|number|passwd" placeholder="查询密码" class="layui-input" value="">
        </div>
      </form>
      	<div class="layui-form-item" style="margin-bottom: 20px;">
          <a href="<%=basePath%>wechat/api/wsetpwd.jhtm" class="layadmin-user-jump-change layadmin-link" style="margin-top: 7px;">设置查询密码</a>
        </div>
        <div class="layui-form-item">
          <button class="layui-btn layui-btn-fluid login" id="check" lay-submit lay-filter="submit_check">校验</button>
          <button class="layui-btn layui-btn-fluid login layui-hide" id="login" lay-submit lay-filter="submit_login">查询</button>
        </div>
        
      </div>
    </div>
    
    <div class="layui-trans layadmin-user-login-footer">
      
      <p>© 2021 <a href="" target="_blank">焦煤中央医院</a></p>
    </div>
    
  </div>

  <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script src="<%=basePath%>layuiadmin/modules/CryptoJS/rollups/aes.js"></script>
  <script src="<%=basePath%>layuiadmin/modules/CryptoJS/rollups/mode-ecb.js"></script>
  <script src="<%=basePath%>layuiadmin/modules/CryptoJS/rollups/tripledes.js"></script>
  <script src="<%=basePath%>layuiadmin/modules/CryptoJS/EncDec.js"></script> 
  <script>
  layui.config({
    base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'laydate','user'], function(){
	  var $ = layui.$
	    ,setter = layui.setter
	    ,admin = layui.admin
	    ,form = layui.form
	    ,laydate = layui.laydate
	    ,router = layui.router()
	    ,search = router.search;

    form.render();
    
    laydate.render({
	  	elem: '#targetMonth',
	  	type: 'month'
	});
    //提交
    form.on('submit(submit_check)', function(obj){
    	var key = $("#secretKey").val();
    	$.ajax({   
             url:'<%=basePath%>wechat/api/doPlogin.jhtm',
             method:'post',       
             data:{
					cardNo:encryptByDES($("#cardNo").val(),key),
					passwd:encryptByDES($("#passwd").val(),key)
			 },        
             dataType:'json',         
             success:function(res){
				var code = res.statusCode;
				var msg = res.message;
				var token = res.data;
				if("200" == code)
				{
					//登入成功的提示与跳转
		          	layer.msg(msg, {offset: '15px',icon: 1,time: 1000});
					$("#token").val(token);
					
					//隐藏校验按钮和身份证号div
					$('#check').addClass("layui-hide");
					$('#cardnoDiv').addClass("layui-hide");
					$('#check').remove();
					$("#cardNo").removeAttr("lay-verify");
					
					//隐藏密码div
					$('#passwDiv').addClass("layui-hide");
					$("#passwd").removeAttr("lay-verify");
					
					$('#monthDiv').removeClass('layui-hide');
					$('#login').removeClass('layui-hide');
					$('#targetMonth').attr('lay-verify', 'required');
				}else
				{
					layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
				}
             },       
             error:function (data) {
				layer.msg("校验失败！", {offset: '150px',icon: 2,time: 2000});
             }  
        });
    });
    
    form.on('submit(submit_login)', function(obj){
    	$.ajax({   
             url:'<%=basePath%>wechat/api/checkToken.jhtm',
             method:'post',       
             data:{token:$("#token").val()},        
             dataType:'json',         
             success:function(res){
				var code = res.statusCode;
				var msg = res.message;
				if("200" == code)
				{
					location.href = '<%=basePath%>wechat/api/payslip.jhtm?token='+$("#token").val()+'&month='+$("#targetMonth").val();
				}else
				{
					layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
				}
             },       
             error:function (data) {
				layer.msg("校验失败,请刷新页面重试！", {offset: '150px',icon: 2,time: 2000});
             }  
        });
    });
    
    form.verify({
		cardNo: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '身份证号不能为空!';
			if(value.length > 25)
				return '身份证号长度不能超过25';
	    },
	    cardNo:[/(^$)|(^\d{15}$)|(^\d{17}(x|X|\d)$)/,'请输入正确的身份证号'],
	    passwd: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '访问密码不能为空!';
			if(value.length > 6)
				return '访问密码长度不能超过6位';
	    },
  	});
  });
</body>
</html>