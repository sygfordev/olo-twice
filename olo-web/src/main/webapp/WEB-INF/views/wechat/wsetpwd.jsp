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
  <title>设置访问密码</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
	
  <div class="layui-fluid">
    <div class="layui-card">
        <!-- <div class="layui-card-header">添加</div> -->
        <div class="layui-card-body" style="padding: 15px;">
          <form class="layui-form" action="" lay-filter="chSaWechatAccount-add-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="type" name="type" value="0">
          <input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
          <input type="hidden" id="secretKey" name="secretKey" value="${secretKey}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">身份证号<font color="red">*</font></label>
	            <div class="layui-input-inline">
	              <input type="text" id="cardNo" name="cardNo" lay-verify="required|cardNo" autocomplete="off" placeholder="身份证号" class="layui-input" value="">
	            </div>
            </div>
          </div>
		  <!-- <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">账户类型 0微信公众号 1:其他</label>
	            <div class="layui-input-inline">
	              <input type="text" id="type" name="type" lay-verify="required|type" autocomplete="off" placeholder="账户类型 0微信公众号 1:其他" class="layui-input">
	            </div>
            </div>
          </div> -->
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">访问密码<font color="red">*</font></label>
	            <div class="layui-input-inline">
	              <input type="password" id="passwd" name="passwd" lay-verify="required|number|passwd" autocomplete="off" placeholder="访问密码" class="layui-input" value="">
	            </div>
	            <div class="layui-form-mid layui-word-aux">请填写6位数字密码</div>
            </div>
          </div>
          <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">确认密码<font color="red">*</font></label>
	            <div class="layui-input-inline">
	              <input type="password" id="repasswd" name="repasswd" lay-verify="required|number|repasswd" autocomplete="off" placeholder="确认密码" class="layui-input" value="">
	            </div>
	            <div class="layui-form-mid layui-word-aux">请填写6位数字密码</div>
            </div>
          </div>
          
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chSaWechatAccount-wSetPwd-submit" id="LAY-chSaWechatAccount-wSetPwd-submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
              </div>
            </div>
          </div>
        </form>
      </div>
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
    index: 'lib/index', //主入口模块
    chSaWechatAccount: 'salary/chSaWechatAccount'
  }).use(['index','form','chSaWechatAccount'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chSaWechatAccount-add-form');
  });
  </script>
</body>
</html>