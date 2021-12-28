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
          <form class="layui-form" action="" lay-filter="chPmOtherInfo-add-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="id" name="id" value="">
          <input type="hidden" id="workerId" name="workerId" value="${wkId}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">是否疫情防控一线医护人员</label>
	            <div class="layui-input-inline">
	              <select id="epidsPrecFlHcStaff" name="epidsPrecFlHcStaff">
	                  <c:forEach var="item" items="${YNList}" varStatus="status">
	                  	<option value="${item.key}">${item.value}</option>
	                  </c:forEach>
                  </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">荣誉金取得时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="honMoneyGotTime" name="honMoneyGotTime" autocomplete="off" placeholder="荣誉金取得时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">核减工龄年限</label>
	            <div class="layui-input-inline">
	              <input type="text" id="caeWkYears" name="caeWkYears" lay-verify="number|caeWkYears" autocomplete="off" placeholder="核减工龄年限" class="layui-input">
	            </div>
            </div>
          </div>
          <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">是否残疾人</label>
	            <div class="layui-input-inline">
	              <select id="isDisability" name="isDisability" lay-filter="isDisability">
	                  <c:forEach var="item" items="${YNUList}" varStatus="status">
	                  	<option value="${item.key}">${item.value}</option>
	                  </c:forEach>
                  </select>
	            </div>
            </div>
            <div class="layui-inline layui-hide" id="disabilityLevDiv">
	            <label class="layui-form-label">残疾鉴定级别</label>
	            <div class="layui-input-inline">
	              <input type="text" id="disabilityLev" name="disabilityLev" lay-verify="disabilityLev" autocomplete="off" placeholder="残疾鉴定级别" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">是否工伤</label>
	            <div class="layui-input-inline">
	              <select id="isInjryOnJob" name="isInjryOnJob" lay-filter="isInjryOnJob">
	                  <c:forEach var="item" items="${YNUList}" varStatus="status">
	                  	<option value="${item.key}">${item.value}</option>
	                  </c:forEach>
                  </select>
	            </div>
            </div>
            <div class="layui-inline layui-hide" id="injryLevDiv">
	            <label class="layui-form-label">工伤鉴定级别</label>
	            <div class="layui-input-inline">
	              <input type="text" id="injryLev" name="injryLev" lay-verify="injryLev" autocomplete="off" placeholder="工伤鉴定级别" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注1</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>
		  <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注2</label>
            <div class="layui-input-block">
              <textarea id="remark2" name="remark2" lay-verify="remark2" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>
        
          <%-- <div class="layui-form-item">
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
          </div> --%>
          
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmOtherInfo-add-submit" id="LAY-chPmOtherInfo-add-submit">立即提交</button>
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
    chPmOtherInfo: 'chPmOtherInfo'
  }).use(['index','form','chPmOtherInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmOtherInfo-add-form');
  });
  </script>
</body>
</html>