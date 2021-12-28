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
        <form class="layui-form" action="" lay-filter="chPmSkillsHead-edit-form">
          <input type="hidden" name="id" value="${model.id}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职工编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="workerId" name="workerId" value="${model.workerId}"  autocomplete="off" placeholder="请输入职工编号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现技能等级</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skills4now" name="skills4now" value="${model.skills4now}"  autocomplete="off" placeholder="请输入现技能等级" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现技能等级取得时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skills4nowGotTime" name="skills4nowGotTime" value='<fmt:formatDate value="${empty model.skills4nowGotTime?'':model.skills4nowGotTime}" pattern="yyyy-MM-dd"/>' autocomplete="off" placeholder="请输入现技能等级取得时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现技能等级序列（技能分类）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsClass4now" name="skillsClass4now" value="${model.skillsClass4now}"  autocomplete="off" placeholder="请输入现技能等级序列（技能分类）" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现技能等级级别(五级|四级|三级|二级|一级)</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsLevel4now" name="skillsLevel4now" value="${model.skillsLevel4now}"  autocomplete="off" placeholder="请输入现技能等级级别(五级|四级|三级|二级|一级)" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现技能等级待遇级别(五级|四级|三级|二级|一级)</label>
	            <div class="layui-input-inline">
	              <input type="text" id="treatLevel4now" name="treatLevel4now" value="${model.treatLevel4now}"  autocomplete="off" placeholder="请输入现技能等级待遇级别(五级|四级|三级|二级|一级)" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现技能初聘开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skills4nowHStartTime" name="skills4nowHStartTime" value='<fmt:formatDate value="${empty model.skills4nowHStartTime?'':model.skills4nowHStartTime}" pattern="yyyy-MM-dd"/>' autocomplete="off" placeholder="请输入现技能初聘开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现技能聘任终止时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skills4nowHEndTime" name="skills4nowHEndTime" value='<fmt:formatDate value="${empty model.skills4nowHEndTime?'':model.skills4nowHEndTime}" pattern="yyyy-MM-dd"/>'  autocomplete="off" placeholder="请输入现技能聘任终止时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">是否现最高技能等级  0：否  1：是</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsMax4now" name="skillsMax4now" value="${model.skillsMax4now}"  autocomplete="off" placeholder="请输入是否现最高技能等级  0：否  1：是" class="layui-input">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmSkillsHead-edit-submit" id="LAY-chPmSkillsHead-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chPmSkillsHead-edit-cancel" id="LAY-chPmSkillsHead-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chPmSkillsHead: 'chPmSkillsHead'
  }).use(['index', 'form', 'laydate', 'chPmSkillsHead'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmSkillsHead-edit-form');
  });
  </script>
</body>
</html>