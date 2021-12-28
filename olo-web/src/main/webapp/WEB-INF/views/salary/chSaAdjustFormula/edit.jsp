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
        <form class="layui-form" action="" lay-filter="chSaAdjustFormula-edit-form">
          <input type="hidden" name="id" value="${model.id}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">目标：职称/职务/学历</label>
	            <div class="layui-input-inline">
	              <input type="text" id="target" name="target" value="${model.target}"  autocomplete="off" placeholder="请输入目标：职称/职务/学历" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">目标等级</label>
	            <div class="layui-input-inline">
	              <input type="text" id="targetLevelCn" name="targetLevelCn" value="${model.targetLevelCn}"  autocomplete="off" placeholder="请输入目标等级" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">2年及以下</label>
	            <div class="layui-input-inline">
	              <input type="text" id="formula2down" name="formula2down" value="${model.formula2down}" lay-verify="required|formula2down" autocomplete="off" placeholder="请输入2年及以下" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">3-4年</label>
	            <div class="layui-input-inline">
	              <input type="text" id="formula3to4" name="formula3to4" value="${model.formula3to4}" lay-verify="required|formula3to4" autocomplete="off" placeholder="请输入3-4年" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">5-6年</label>
	            <div class="layui-input-inline">
	              <input type="text" id="formula5to6" name="formula5to6" value="${model.formula5to6}" lay-verify="required|formula5to6" autocomplete="off" placeholder="请输入5-6年" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">7-8年</label>
	            <div class="layui-input-inline">
	              <input type="text" id="formula7to8" name="formula7to8" value="${model.formula7to8}" lay-verify="required|formula7to8" autocomplete="off" placeholder="请输入7-8年" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">9-10年</label>
	            <div class="layui-input-inline">
	              <input type="text" id="formula9to10" name="formula9to10" value="${model.formula9to10}" lay-verify="required|formula9to10" autocomplete="off" placeholder="请输入9-10年" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">11-12年</label>
	            <div class="layui-input-inline">
	              <input type="text" id="formula11to12" name="formula11to12" value="${model.formula11to12}" lay-verify="required|formula11to12" autocomplete="off" placeholder="请输入11-12年" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">13年以上</label>
	            <div class="layui-input-inline">
	              <input type="text" id="formula13up" name="formula13up" value="${model.formula13up}" lay-verify="required|formula13up" autocomplete="off" placeholder="请输入13年以上" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">级差</label>
	            <div class="layui-input-inline">
	              <input type="text" id="gratdations" name="gratdations" value="${model.gratdations}" lay-verify="required|gratdations" autocomplete="off" placeholder="请输入级差" class="layui-input">
	            </div>
            </div>
          </div>
          
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status">
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?selected:""}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chSaAdjustFormula-edit-submit" id="LAY-chSaAdjustFormula-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chSaAdjustFormula-edit-cancel" id="LAY-chSaAdjustFormula-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chSaAdjustFormula: 'chSaAdjustFormula'
  }).use(['index', 'form', 'laydate', 'chSaAdjustFormula'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chSaAdjustFormula-edit-form');
  });
  </script>
</body>
</html>