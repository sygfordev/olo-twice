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
          <form class="layui-form" action="" lay-filter="chPmFamilyMemberInfo-add-form">
          <!-- 隐藏属性 -->
      	  <input type="hidden" id="workerId" name="workerId" value="${wkId}">
      	  <input type="hidden" id="memRelationCn" name="memRelationCn" value="">
		  
		  <div class="layui-form-item">
            <div class="layui-inline">
	            <label class="layui-form-label">成员姓名</label>
	            <div class="layui-input-inline">
	              <input type="text" id="memName" name="memName" lay-verify="required|memName" autocomplete="off" placeholder="家庭成员姓名" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">成员关系</label>
	            <div class="layui-input-inline">
	              <select id="memRelation" name="memRelation" lay-verify="required" lay-filter="memRelation">
	              	<option value=""></option>
	             	<c:forEach var="item" items="${homeMemRelList}" varStatus="status">
	              		<option value="${item.key}">${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">工作单位</label>
	            <div class="layui-input-inline">
	              <input type="text" id="memWkCom" name="memWkCom" lay-verify="required|memWkCom" autocomplete="off" placeholder="家庭成员工作单位" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">联系电话</label>
	            <div class="layui-input-inline">
	              <input type="text" id="memTelno" name="memTelno" lay-verify="required|memTelno" autocomplete="off" placeholder="家庭成员联系电话" class="layui-input">
	            </div>
            </div>
          </div>
          <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">详细住址</label>
	            <div class="layui-input-inline">
	              <input type="text" id="memAddr" name="memAddr" lay-verify="required|memAddr" autocomplete="off" placeholder="现详细住址" class="layui-input" style="width:270%">
	            </div>
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
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmFamilyMemberInfo-add-submit" id="LAY-chPmFamilyMemberInfo-add-submit">立即提交</button>
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
    chPmFamilyMemberInfo: 'chPmFamilyMemberInfo'
  }).use(['index','form','chPmFamilyMemberInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmFamilyMemberInfo-add-form');
  });
  </script>
</body>
</html>