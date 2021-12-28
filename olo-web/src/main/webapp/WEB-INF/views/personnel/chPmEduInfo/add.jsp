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
          <form class="layui-form" action="" lay-filter="chPmEduInfo-add-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="workerId" name="workerId" value="${wkId}">
          
          <input type="hidden" id="eduTypeCn" name="eduTypeCn" value="">
          <input type="hidden" id="eduLevCn" name="eduLevCn" value="">
          <input type="hidden" id="eduDegCn" name="eduDegCn" value="">
          
		  
		  <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">学习院校</label>
              <div class="layui-input-inline">
                <input type="text" id="eduSch" name="eduSch" lay-verify="required" autocomplete="off" placeholder="学习院校" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">学习专业</label>
              <div class="layui-input-inline">
                <input type="text" id="eduMaj" name="eduMaj" lay-verify="required" autocomplete="off" placeholder="学习专业" class="layui-input">
              </div>
            </div>
            
          </div>
		  
		  <div class="layui-form-item">
		  	<div class="layui-inline">
              <label class="layui-form-label">学习形式</label>
              <div class="layui-input-inline">
                <select id="eduType" name="eduType" lay-verify="required" lay-filter="eduType">
                	<option></option>
                  <c:forEach var="item" items="${eduTypeList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">学历</label>
              <div class="layui-input-inline">
                <select id="eduLev" name="eduLev" lay-verify="required" lay-filter="eduLev">
                	<option></option>
                  <c:forEach var="item" items="${eduLevList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
		  </div>
		  <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">学位</label>
              <div class="layui-input-inline">
                <select id="eduDeg" name="eduDeg" lay-verify="required" lay-filter="eduDeg">
                	<option></option>
                  <c:forEach var="item" items="${eduDegList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">是否最高学历</label>
              <div class="layui-input-inline">
                <select id="eduMax" name="eduMax" lay-verify="required">
                	<option></option>
                  <c:forEach var="item" items="${YNList}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
		 <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">学习时间-起</label>
              <div class="layui-input-inline">
                <input type="text" id="eduStartTime" name="eduStartTime" lay-verify="required" autocomplete="off" placeholder="学习开始时间" class="layui-input time">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">学习时间-止</label>
              <div class="layui-input-inline">
                <input type="text" id="eduEndTime" name="eduEndTime" lay-verify="required" autocomplete="off" placeholder="学习结束时间" class="layui-input time">
              </div>
            </div>
          </div>
        
          <%-- <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" lay-verify="required">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmEduInfo-add-submit" id="LAY-chPmEduInfo-add-submit">立即提交</button>
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
    chPmEduInfo: 'chPmEduInfo'
  }).use(['index','form','laydate','chPmEduInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chPmEduInfo-add-form');
  });
  </script>
</body>
</html>