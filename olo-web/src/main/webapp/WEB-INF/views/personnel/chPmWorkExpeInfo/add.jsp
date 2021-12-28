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
          <form class="layui-form" action="" lay-filter="chPmWorkExpeInfo-add-form">
          <!-- 隐藏属性 -->
      	  <input type="hidden" id="workerId" name="workerId" value="${wkId}">
      	  
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkComName" name="wkComName" lay-verify="required|wkComName" autocomplete="off" placeholder="工作单位名称" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">部门名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkDepName" name="wkDepName" lay-verify="required|wkDepName" autocomplete="off" placeholder="工作部门名称" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">工作岗位</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkStation" name="wkStation" lay-verify="required|wkStation" autocomplete="off" placeholder="工作岗位" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">担任职务</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkPosit" name="wkPosit" lay-verify="required|wkPosit" autocomplete="off" placeholder="担任职务" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">职称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkTitle" name="wkTitle" lay-verify="required|wkTitle" autocomplete="off" placeholder="职称" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkStartTime" name="wkStartTime" lay-verify="required" autocomplete="off" placeholder="工作开始时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">调动文号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="transDocno" name="transDocno" lay-verify="transDocno" autocomplete="off" placeholder="调动文号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkEndTime" name="wkEndTime"  autocomplete="off" placeholder="工作结束时间" class="layui-input time">
	            </div>
            </div>
            <%-- <div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div> --%>
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmWorkExpeInfo-add-submit" id="LAY-chPmWorkExpeInfo-add-submit">立即提交</button>
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
    chPmWorkExpeInfo: 'chPmWorkExpeInfo'
  }).use(['index','form','chPmWorkExpeInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmWorkExpeInfo-add-form');
  });
  </script>
</body>
</html>