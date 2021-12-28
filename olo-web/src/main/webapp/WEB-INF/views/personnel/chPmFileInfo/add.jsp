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
  <title>表单组合</title>
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
          <form class="layui-form" action="#" lay-filter="chPmFileInfo-add-form" enctype="multipart/form-data">
          <!-- 隐藏属性 -->
          <input type="hidden" id="workerId" name="workerId" value="${wkId}">
          
          
		  <div class="layui-form-item">
		  <!-- <fieldset class="layui-elem-field layui-field-title">
		  	  <legend>身份证图片</legend>
			  <div class="layui-field-box layui-upload">
			  		<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                        <div id="uploadQRcode" class="layui-upload">
                        <button type="button" class="layui-btn" id="uploadQR">
                            <i class="layui-icon">&#xe67c;</i>上传客服二维码<span style="color: red;font-size: 20px;">*</span>
                        </button>
                        <div class="layui-upload-list">
                            <img id="qrshow" src="" alt="" class="layui-upload-img"
                                 style="height: 100px;width:100px;border:1px solid black;">
                        </div>
                        <div id="startDiv">
                            <button type="button" class="layui-btn" id="startUploadQR">开始上传</button>
                        </div>
                            <div style="color: #c2c2c2;margin:10px 0;">温馨提示: 每次最多上传一张图片, 单张图片的大小不超过2MB</div>
                    </div>
                    <input type="text" name="cli_qrcode" id="qrInput" style="display: none;" lay-verify="required">
                    </blockquote>
			  </div>
		  </fieldset> -->
		  <!-- 身份证 -->
		  <fieldset class="layui-elem-field layui-field-title">
		  	  <legend>身份证</legend>
			  <div class="layui-field-box layui-upload">
			  		<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                        <div id="uploadImg" class="layui-upload">
                        <button type="button" class="layui-btn" id="idcard">
                            <i class="layui-icon">&#xe67c;</i>上传图片<!-- <span style="color: red;font-size: 20px;">*</span> -->
                        </button>
                        <div class="layui-upload-list">
                            <table class="layui-table" style="text-align: center;">
                                <thead>
                                <tr>
                                    <th style="text-align: center;">图片预览</th>
                                    <th style="text-align: center;">大小</th>
                                    <th style="text-align: center;">状态</th>
                                    <th style="text-align: center;">操作</th>
                                </tr>
                                </thead>
                                <tbody id="imgList4Idcard"></tbody>
                            </table>
                        </div>
                        <button type="button" class="layui-btn" id="idcardUpload">开始上传</button>
                            <div style="color: #c2c2c2;margin:10px 0;">温馨提示: 每次最多上传六张图片, 单张图片的大小不超过5MB
                            </div>
                    </div>
                    <input type="text" name="imgInput4Idcard" id="imgInput4Idcard" style="display: none;" lay-verify="required">
                    </blockquote>
			  </div>
		  </fieldset>
		  <!-- 学历文件 -->
		  <fieldset class="layui-elem-field layui-field-title">
		  	  <legend>学历文件</legend>
			  <div class="layui-field-box layui-upload">
			  		<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                        <div id="uploadImg" class="layui-upload">
                        <button type="button" class="layui-btn" id="eduLev">
                            <i class="layui-icon">&#xe67c;</i>上传图片<!-- <span style="color: red;font-size: 20px;">*</span> -->
                        </button>
                        <div class="layui-upload-list">
                            <table class="layui-table" style="text-align: center;">
                                <thead>
                                <tr>
                                    <th style="text-align: center;">图片预览</th>
                                    <th style="text-align: center;">大小</th>
                                    <th style="text-align: center;">状态</th>
                                    <th style="text-align: center;">操作</th>
                                </tr>
                                </thead>
                                <tbody id="imgList4EduLev"></tbody>
                            </table>
                        </div>
                        <button type="button" class="layui-btn" id="eduLevUpload">开始上传</button>
                            <div style="color: #c2c2c2;margin:10px 0;">温馨提示: 每次最多上传六张图片, 单张图片的大小不超过5MB
                            </div>
                    </div>
                    <input type="text" name="imgInput4EduLev" id="imgInput4EduLev" style="display: none;" lay-verify="required">
                    </blockquote>
			  </div>
		  </fieldset>
		  <!-- 职称文件 -->
		  <fieldset class="layui-elem-field layui-field-title">
		  	  <legend>职称文件</legend>
			  <div class="layui-field-box layui-upload">
			  		<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                        <div id="uploadImg" class="layui-upload">
                        <button type="button" class="layui-btn" id="title">
                            <i class="layui-icon">&#xe67c;</i>上传图片<!-- <span style="color: red;font-size: 20px;">*</span> -->
                        </button>
                        <div class="layui-upload-list">
                            <table class="layui-table" style="text-align: center;">
                                <thead>
                                <tr>
                                    <th style="text-align: center;">图片预览</th>
                                    <th style="text-align: center;">大小</th>
                                    <th style="text-align: center;">状态</th>
                                    <th style="text-align: center;">操作</th>
                                </tr>
                                </thead>
                                <tbody id="imgList4Title"></tbody>
                            </table>
                        </div>
                        <button type="button" class="layui-btn" id="titleUpload">开始上传</button>
                            <div style="color: #c2c2c2;margin:10px 0;">温馨提示: 每次最多上传六张图片, 单张图片的大小不超过5MB
                            </div>
                    </div>
                    <input type="text" name="imgInput4Title" id="imgInput4Title" style="display: none;" lay-verify="required">
                    </blockquote>
			  </div>
		  </fieldset>
		  <!-- 任职文件 -->
		  <fieldset class="layui-elem-field layui-field-title">
		  	  <legend>任职文件</legend>
			  <div class="layui-field-box layui-upload">
			  		<blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
                        <div id="uploadImg" class="layui-upload">
                        <button type="button" class="layui-btn" id="posit">
                            <i class="layui-icon">&#xe67c;</i>上传图片<!-- <span style="color: red;font-size: 20px;">*</span> -->
                        </button>
                        <div class="layui-upload-list">
                            <table class="layui-table" style="text-align: center;">
                                <thead>
                                <tr>
                                    <th style="text-align: center;">图片预览</th>
                                    <th style="text-align: center;">大小</th>
                                    <th style="text-align: center;">状态</th>
                                    <th style="text-align: center;">操作</th>
                                </tr>
                                </thead>
                                <tbody id="imgList4Posit"></tbody>
                            </table>
                        </div>
                        <button type="button" class="layui-btn" id="positUpload">开始上传</button>
                            <div style="color: #c2c2c2;margin:10px 0;">温馨提示: 每次最多上传六张图片, 单张图片的大小不超过5MB
                            </div>
                    </div>
                    <input type="text" name="imgInput4Posit" id="imgInput4Posit" style="display: none;" lay-verify="required">
                    </blockquote>
			  </div>
		  </fieldset>
		  </div>
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <!-- <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmFileInfo-add-submit" id="LAY-chPmFileInfo-add-submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
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
    index: 'lib/index', //主入口模块
    chPmFileUpload: 'chPmFileUpload'
  }).use(['index','form','chPmFileUpload'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmFileInfo-add-form');
  });
</script>
</body>
</html>