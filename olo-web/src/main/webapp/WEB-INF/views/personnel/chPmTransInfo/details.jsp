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
  <style type="text/css">
  .layui-form-item .layui-input-inline{
  	padding-top:9px;
  }
  </style>
</head>
<body>
	
  <div class="layui-fluid">
    <div class="layui-card">
      <!-- <div class="layui-card-header">详情</div> -->
      <div class="layui-card-body" style="padding: 9px;">
      	<form class="layui-form">
		  <div class="layui-form-item">
          	<%-- <div class="layui-inline">
	            <label class="layui-form-label">编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="id" name="id" value="${model.id}"  autocomplete="off" placeholder="请输入自增主键" class="layui-input" readonly>
	            </div>
            </div> --%>
            <div class="layui-inline">
	            <label class="layui-form-label">姓名</label>
	            <div class="layui-input-inline">
	              ${model.name}
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">身份证号</label>
	            <div class="layui-input-inline">
	              ${model.cardNo}
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">原部门</label>
	            <div class="layui-input-inline">
	            	${model.departBeforeCn}
	              <%-- <select id="departBefore" name="departBefore" lay-verify="required" lay-filter="departBefore" disabled>
	              	<option value=""></option>
	              	<c:forEach var="item" items="${hdlist}" varStatus="status">
	              		<option value="${item.hdpNo}" ${item.hdpNo eq model.departBefore?'selected':''}>${item.hdpName}</option>
	              	</c:forEach>
	              </select> --%>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">原岗位</label>
	            <div class="layui-input-inline">
	            	${model.positBeforeCn}
	              <%-- <select id="positBefore" name="positBefore" lay-verify="required" lay-filter="positBefore" disabled>
	              	<option value=""></option>
	              	<c:forEach var="item" items="${stationTypeList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.positBefore?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select> --%>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">新部门</label>
	            <div class="layui-input-inline">
	            ${model.departAfterCn}
	              <%-- <select id="departAfter" name="departAfter" lay-verify="required" lay-filter="departAfter" disabled>
	              	<option value=""></option>
	              	<c:forEach var="item" items="${hdlist}" varStatus="status">
	              		<option value="${item.hdpNo}" ${item.hdpNo eq model.departAfter?'selected':''}>${item.hdpName}</option>
	              	</c:forEach>
	              </select> --%>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">新岗位</label>
	            <div class="layui-input-inline">
	            	${model.positAfterCn}
	              <%-- <select id="positAfter" name="positAfter" lay-verify="required" lay-filter="positAfter" disabled>
	              	<option value=""></option>
	              	<c:forEach var="item" items="${stationTypeList}" varStatus="status">
	              		<option value="${item.key}" ${item.key eq model.positAfter?'selected':''}>${item.value}</option>
	              	</c:forEach>
	              </select> --%>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">调动文号</label>
	            <div class="layui-input-inline">
	              ${model.transDocno}
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">调动时间</label>
	            <div class="layui-input-inline">
	              <fmt:formatDate value="${model.transTime}" pattern="yyyy-MM-dd"/>
	            </div>
            </div>
            <%-- <div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" disabled>
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?selected:""}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div> --%>
          </div>
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea" readonly>${model.remark}</textarea>
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
    chPmTransInfo: 'chPmTransInfo'
  }).use(['index', 'laydate', 'chPmTransInfo'], function(){
    var $ = layui.$;
    
  });
  </script>
</body>
</html>