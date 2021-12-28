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
          <form class="layui-form" action="" lay-filter="alarmSet-edit-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="id" name="id" value="${model.id}">
          
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">警报类型<font color="red">*</font></label>
	            <div class="layui-input-inline">
	              <select id="alarmType" name="alarmType" lay-filter="alarmType">
	              	<c:forEach var="item" items="${alarmTypeList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.alarmType?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
		  	<div class="layui-inline">
	            <label class="layui-form-label">提前量<font color="red">*</font></label>
	            <div class="layui-input-inline">
	              <input type="text" id="alarmAdSize" name="alarmAdSize" value="${model.alarmAdSize}" lay-verify="required|number|positiveWholeNumberRequiredTrue|alarmAdSize" autocomplete="off" placeholder="提前天/周/月....数量" class="layui-input">
	            </div>
            </div>
          	<div class="layui-inline">
	            <label class="layui-form-label">提前单位<font color="red">*</font></label>
	            <div class="layui-input-inline">
	              <select id="alarmAdUnit" name="alarmAdUnit" lay-filter="alarmAdUnit">
	              	<c:forEach var="item" items="${timeUnitList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.alarmAdUnit?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select>
	            </div>
            </div>
          </div>
          
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">警报样板<font color="red">*</font></label>
            <div class="layui-input-block">
              <textarea id="alarmTeInfo" name="alarmTeInfo" lay-verify="alarmTeInfo" placeholder="请输入警报样板  如：尊敬的运营人员您好，员工{name}的合同{contractno}将于1天后到期，请及时处理！" class="layui-textarea">${model.alarmTeInfo}</textarea>
            </div>
          </div>
        
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea">${model.remark}</textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-alarmSet-edit-submit" id="LAY-alarmSet-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-alarmSet-edit-cancel" id="LAY-alarmSet-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    alarmSet: 'central/alarmSet'
  }).use(['index','form','alarmSet'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'alarmSet-edit-form');
  });
  </script>
</body>
</html>