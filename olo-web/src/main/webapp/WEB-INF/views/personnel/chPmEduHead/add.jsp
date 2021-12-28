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
          <form class="layui-form" action="" lay-filter="chPmEduHead-add-form">
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">自增主键</label>
	            <div class="layui-input-inline">
	              <input type="text" id="id" name="id"  autocomplete="off" placeholder="自增主键" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职工编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="workerId" name="workerId"  autocomplete="off" placeholder="职工编号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">学习学位</label>
	            <div class="layui-input-inline">
	              <input type="text" id="eduDeg4now" name="eduDeg4now"  autocomplete="off" placeholder="学习学位" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">学历（高中|大专|本科|硕士|博士等）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="eduLev4now" name="eduLev4now"  autocomplete="off" placeholder="学历" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工资学历</label>
	            <div class="layui-input-inline">
	              <input type="text" id="eduLev4sal" name="eduLev4sal"  autocomplete="off" placeholder="工资学历" class="layui-input">
	            </div>
            </div>
          </div>
        
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmEduHead-add-submit" id="LAY-chPmEduHead-add-submit">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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
    chPmEduHead: 'chPmEduHead'
  }).use(['index','form','chPmEduHead'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmEduHead-add-form');
  });
  </script>
</body>
</html>