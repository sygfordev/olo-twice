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
          <form class="layui-form" action="" lay-filter="chPmPositHead-add-form">
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
	            <label class="layui-form-label">现任职务</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit4now" name="posit4now"  autocomplete="off" placeholder="现任职务" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现级别（正科|副科等的编号）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="positLevel4now" name="positLevel4now"  autocomplete="off" placeholder="现级别（正科|副科等的编号）" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现任职务开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit4nowStartTime" name="posit4nowStartTime"  autocomplete="off" placeholder="现任职务开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现任职务结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit4nowEndTime" name="posit4nowEndTime"  autocomplete="off" placeholder="现任职务结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现待遇级别（正科级|副科级等的编号）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treatLevel4now" name="treatLevel4now"  autocomplete="off" placeholder="现待遇级别（正科级|副科级等的编号）" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现待遇级别开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treat4nowStartTime" name="treat4nowStartTime"  autocomplete="off" placeholder="现待遇级别开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现待遇级别结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treat4nowEndTime" name="treat4nowEndTime"  autocomplete="off" placeholder="现待遇级别结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现待遇级别年限</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treat4nowYears" name="treat4nowYears"  autocomplete="off" placeholder="现待遇级别年限" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正处开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onChuStartTime" name="onChuStartTime"  autocomplete="off" placeholder="任正处开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正处结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onChuEndTime" name="onChuEndTime"  autocomplete="off" placeholder="任正处结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副处开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onChuDetupyStartTime" name="onChuDetupyStartTime"  autocomplete="off" placeholder="任副处开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副处结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onChuDetupyEndTime" name="onChuDetupyEndTime"  autocomplete="off" placeholder="任副处结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正科开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onKeStartTime" name="onKeStartTime"  autocomplete="off" placeholder="任正科开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正科结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onKeEndTime" name="onKeEndTime"  autocomplete="off" placeholder="任正科结束时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副科开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onKeDetupyStartTime" name="onKeDetupyStartTime"  autocomplete="off" placeholder="任副科开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副科结束时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onKeDetupyEndTime" name="onKeDetupyEndTime"  autocomplete="off" placeholder="任副科结束时间" class="layui-input">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmPositHead-add-submit" id="LAY-chPmPositHead-add-submit">立即提交</button>
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
    chPmPositHead: 'chPmPositHead'
  }).use(['index','form','chPmPositHead'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmPositHead-add-form');
  });
  </script>
</body>
</html>