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
        <!-- <div class="layui-card-header">编辑</div> -->
        <div class="layui-card-body" style="padding: 15px;">
          <form class="layui-form" action="" lay-filter="systemDictItem-edit-form">
          <input type="hidden" id="id" name="id" value="${model.id}" class="layui-input">
          <input type="hidden" id="dictId" name="dictId" value="${model.dictId}" class="layui-input">
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">字典项Key</label>
	            <div class="layui-input-inline">
	              <input type="text" id="itemKey" name="itemKey" value="${model.itemKey}" lay-verify="required|itemKey" autocomplete="off" placeholder="字典项Key" class="layui-input">
	            </div>
	            <div class="layui-form-mid layui-word-aux"><font color="red">*</font> 请输入字典项Key</div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">字典项Val</label>
	            <div class="layui-input-inline">
	              <input type="text" id="itemVal" name="itemVal" value="${model.itemVal}" autocomplete="off" lay-verify="required|itemVal" placeholder="字典项Val" class="layui-input">
	            </div>
	            <div class="layui-form-mid layui-word-aux"><font color="red">*</font> 请输入字典项Val</div>
            </div>
          </div>
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">存在父项</label>
              <div class="layui-input-inline">
                <select id="existSuper" name="existSuper" lay-filter="existSuper" lay-verify="required|existSuper">
                  <c:forEach var="item" items="${yesNoList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.existSuper?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="layui-form-mid layui-word-aux"><font color="red">*</font> 请选择是否存在父字典项</div>
            </div>
          </div>
          
          <div class="layui-form-item layui-hide" id="superIdDiv">
          	<div class="layui-inline">
              <label class="layui-form-label">父项字典项</label>
              <div class="layui-input-inline">
                <select id="superId" name="superId">
                  <c:forEach var="item" items="${superList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.superId?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="layui-form-mid layui-word-aux"><font color="red">*</font> 请选择父字典项</div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">排序</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sortVal" name="sortVal" value="${model.sortVal}" lay-verify="required|sortVal" autocomplete="off" placeholder="排序" class="layui-input">
	            </div>
	            <div class="layui-form-mid layui-word-aux"><font color="red">*</font> 请输入排序</div>
            </div>
          </div>
		  
          <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" lay-verify="required|status">
                	<option value="">请选择</option>
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
              <div class="layui-form-mid layui-word-aux"><font color="red">*</font> 请选择状态</div>
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-systemDictItem-edit-submit" id="LAY-systemDictItem-edit-submit">提交</button>
                <button lay-submit=""  lay-filter="LAY-systemDictItem-edit-cancel" id="LAY-systemDictItem-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    systemDictItem: 'systemDictItem'
  }).use(['index','form','systemDictItem'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'systemDictItem-edit-form');
  });
  </script>
</body>
</html>