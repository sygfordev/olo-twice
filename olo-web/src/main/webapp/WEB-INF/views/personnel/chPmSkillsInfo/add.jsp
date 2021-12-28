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
          <form class="layui-form" action="" lay-filter="chPmSkillsInfo-add-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="workerId" name="workerId" value="${wkId}">
          
          <input type="hidden" id="skillsCn" name="skillsCn" value="">
          <input type="hidden" id="skillsClassCn" name="skillsClassCn" value="">
          <input type="hidden" id="skillsLevelCn" name="skillsLevelCn" value="">
          <input type="hidden" id="skillsOthCn" name="skillsOthCn" value="">
          
          	
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">技能等级</label>
	            <div class="layui-input-inline">
	              <select id="skills" name="skills" lay-verify="required" lay-filter="skills">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${skillsList}" varStatus="status">
	                	<option value="${item.key}">${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">取得时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsGotTime" name="skillsGotTime" lay-verify="required" autocomplete="off" placeholder="技能等级取得时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">等级序列</label>
	            <div class="layui-input-inline">
	              <select id="skillsClass" name="skillsClass" lay-verify="required" lay-filter="skillsClass">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${skillsClassList}" varStatus="status">
	                	<option value="${item.key}">${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">等级级别</label>
	            <div class="layui-input-inline">
	              <select id="skillsLevel" name="skillsLevel" lay-verify="required" lay-filter="skillsLevel">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${skillsLevelList}" varStatus="status">
	                	<option value="${item.key}">${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">是否最高等级</label>
	            <div class="layui-input-inline">
	              <select id="skillsMax" name="skillsMax" lay-verify="required">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${YNList}" varStatus="status">
	                	<option value="${item.key}">${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">证书编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsCertNo" name="skillsCertNo" lay-verify="required" autocomplete="off" placeholder="技能证书编号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">任职文号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsOnitNo" name="skillsOnitNo" lay-verify="required" autocomplete="off" placeholder="技能任职文号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">初聘文号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsHireNo" name="skillsHireNo" lay-verify="required" autocomplete="off" placeholder="技能初聘文号" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">初聘时间-起</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsHStartTime" name="skillsHStartTime" lay-verify="required" autocomplete="off" placeholder="技能初聘开始时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">聘任时间-止</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsHEndTime" name="skillsHEndTime" lay-verify="required" autocomplete="off" placeholder="技能初聘结束时间" class="layui-input time">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">聘任周期</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsHCycle" name="skillsHCycle" lay-verify="required|number" autocomplete="off" placeholder="技能聘任周期" class="layui-input">
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">其他技能</label>
	            <div class="layui-input-inline">
	              <select id="skillsOth" name="skillsOth" lay-filter="skillsOth">
	              	<option value=""></option>
	              	<c:forEach var="item" items="${skillsList}" varStatus="status">
	                	<option value="${item.key}">${item.value}</option>
	                </c:forEach>
	              </select>
	            </div>
            </div>
            <div class="layui-inline">
	            <label class="layui-form-label">其他技能取得时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="skillsOthGotTime" name="skillsOthGotTime"  autocomplete="off" placeholder="其他技能取得时间" class="layui-input time">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmSkillsInfo-add-submit" id="LAY-chPmSkillsInfo-add-submit">立即提交</button>
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
    chPmSkillsInfo: 'chPmSkillsInfo'
  }).use(['index','form','laydate','chPmSkillsInfo'], function(){
    var $ = layui.$,
    form = layui.form,
    laydate = layui.laydate;
    
    form.render(null, 'chPmSkillsInfo-add-form');
  });
  </script>
</body>
</html>