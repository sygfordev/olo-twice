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
          <form class="layui-form" action="" lay-filter="chPmPositInfo-add-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="workerId" name="workerId" value="${wkId}">
          
          <input type="hidden" id="positCn" name="positCn" value="">
          <input type="hidden" id="conPositCn" name="conPositCn" value="">
          <input type="hidden" id="positLevelCn" name="positLevelCn" value="">
          <input type="hidden" id="treatLevelCn" name="treatLevelCn" value="">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职务</label>
	            <div class="layui-input-inline">
	              	<select id="posit" name="posit" lay-verify="required" lay-filter="posit">
	              	  <option value=""></option>
	                  <c:forEach var="item" items="${positList}" varStatus="status">
	                    <option value="${item.key}">${item.value}</option>
	                  </c:forEach>
	                </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">任职部门</label>
	            <div class="layui-input-inline">
	              <input type="text" id="positDepart" name="positDepart" lay-verify="positDepart" autocomplete="off" placeholder="任职部门" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">任职文号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onPositDocno" name="onPositDocno" lay-verify="onPositDocno" autocomplete="off" placeholder="任职文号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">任职时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onPositStartTime" name="onPositStartTime" lay-verify="onPositStartTime" autocomplete="off" placeholder="任职时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">是否最高职务</label>
	            <div class="layui-input-inline">
	              <select id="positMax" name="positMax" lay-verify="required">
	              	  <option value=""></option>
	                  <c:forEach var="item" items="${YNList}" varStatus="status">
	                    <option value="${item.key}">${item.value}</option>
	                  </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">兼任职务</label>
	            <div class="layui-input-inline">
	              <select id="conPosit" name="conPosit" lay-verify="conPosit" lay-filter="conPosit">
	              	  <option value=""></option>
	                  <c:forEach var="item" items="${positList}" varStatus="status">
	                    <option value="${item.key}">${item.value}</option>
	                  </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">兼职部门</label>
	            <div class="layui-input-inline">
	              <input type="text" id="conPositDepart" name="conPositDepart" lay-verify="conPositDepart" autocomplete="off" placeholder="兼职部门" class="layui-input">
	            </div>
            </div>
		  	<div class="layui-inline">
	            <label class="layui-form-label">兼任时间-始</label>
	            <div class="layui-input-inline">
	              <input type="text" id="conPositStartTime" name="conPositStartTime" autocomplete="off" placeholder="兼职开始时间" class="layui-input time">
	            </div>
            </div>
          	<div class="layui-inline">
	            <label class="layui-form-label">行政级别</label>
	            <div class="layui-input-inline">
	              <select id="positLevel" name="positLevel" lay-verify="positLevel" lay-filter="positLevel">
	              	  <option value=""></option>
	                  <c:forEach var="item" items="${positLevelList}" varStatus="status">
	                    <option value="${item.key}">${item.value}</option>
	                  </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">待遇级别</label>
	            <div class="layui-input-inline">
	              <select id="treatLevel" name="treatLevel" lay-verify="treatLevel" lay-filter="treatLevel">
	                  <option value=""></option>
		              <c:forEach var="item" items="${positLevelList}" varStatus="status">
		              	<option value="${item.key}">${item.value}级</option>
		              </c:forEach>
	              </select>
	            </div>
            </div>
            <%-- <div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" lay-verify="required">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
              </div>
          	</div> --%>
          
          <div class="layui-form-item">
          	<div class="layui-form-item layui-form-text">
	            <label class="layui-form-label">备注</label>
	            <div class="layui-input-block">
	              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
	            </div>
	        </div>
          </div>   
          
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmPositInfo-add-submit" id="LAY-chPmPositInfo-add-submit">立即提交</button>
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
    chPmPositInfo: 'chPmPositInfo'
  }).use(['index','form','chPmPositInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmPositInfo-add-form');
  });
  </script>
</body>
</html>