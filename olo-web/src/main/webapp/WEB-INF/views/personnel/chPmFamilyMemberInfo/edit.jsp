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
  <meta charset="UTF-8">
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
      <!-- <div class="layui-card-header">编辑</div> -->
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="chPmFamilyMemberInfo-edit-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="id" name="id" value="${model.id}">
      	  <input type="hidden" id="workerId" name="workerId" value="${model.workerId}">
      	  
      	  <input type="hidden" id="memRelationCn" name="memRelationCn" value="${model.memRelationCn}">
          
		  <div class="layui-form-item">
            <div class="layui-inline">
	            <label class="layui-form-label">成员姓名</label>
	            <div class="layui-input-inline">
	              <input type="text" id="memName" name="memName" value="${model.memName}" lay-verify="required|memName" autocomplete="off" placeholder="家庭成员姓名" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">成员关系</label>
	            <div class="layui-input-inline">
	              <select id="memRelation" name="memRelation" lay-verify="required" lay-filter="memRelation">
	              	<option value=""></option>
	             	<c:forEach var="item" items="${homeMemRelList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.memRelation?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">工作单位</label>
	            <div class="layui-input-inline">
	              <input type="text" id="memWkCom" name="memWkCom" value="${model.memWkCom}" lay-verify="required|memWkCom" autocomplete="off" placeholder="家庭成员工作单位" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">联系电话</label>
	            <div class="layui-input-inline">
	              <input type="text" id="memTelno" name="memTelno" value="${model.memTelno}" lay-verify="required|memTelno" autocomplete="off" placeholder="家庭成员联系电话" class="layui-input">
	            </div>
            </div>
          </div>
          <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">详细住址</label>
	            <div class="layui-input-inline">
	              <input type="text" id="memAddr" name="memAddr" value="${model.memAddr}" lay-verify="required|memAddr" autocomplete="off" placeholder="现详细住址" class="layui-input" style="width:270%">
	            </div>
            </div>
          </div>
          <%-- <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div> --%>
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea">${model.remark}</textarea>
            </div>
          </div>      
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmFamilyMemberInfo-edit-submit" id="LAY-chPmFamilyMemberInfo-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chPmFamilyMemberInfo-edit-cancel" id="LAY-chPmFamilyMemberInfo-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    base: '<%=basePath%>layuiadmin/'
  }).extend({
    index: 'lib/index', //主入口模块
    chPmFamilyMemberInfo: 'chPmFamilyMemberInfo'
  }).use(['index', 'form', 'laydate', 'chPmFamilyMemberInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmFamilyMemberInfo-edit-form');
  });
  </script>
</body>
</html>