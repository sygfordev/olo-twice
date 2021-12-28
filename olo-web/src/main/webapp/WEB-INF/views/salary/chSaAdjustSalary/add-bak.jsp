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
          <form class="layui-form" action="" lay-filter="chSaAdjustSalary-add-form">
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职工姓名</label>
	            <div class="layui-input-inline">
	              <input type="text" id="name" name="name"  autocomplete="off" placeholder="职工姓名" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">卡号（身份）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="cardNo" name="cardNo" lay-verify="required|cardNo" autocomplete="off" placeholder="卡号（身份）" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">人员编号（工资出账）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wagesId" name="wagesId"  autocomplete="off" placeholder="人员编号（工资出账）" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工资账序号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wagesSeq" name="wagesSeq"  autocomplete="off" placeholder="工资账序号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工资账姓名</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wagesName" name="wagesName"  autocomplete="off" placeholder="工资账姓名" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工资账用工形式</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wagesModalityCn" name="wagesModalityCn"  autocomplete="off" placeholder="工资账用工形式" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工资账科室</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wagesDepart" name="wagesDepart" lay-verify="required|wagesDepart" autocomplete="off" placeholder="工资账科室" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">原岗位工资标准</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wagesStandardBef" name="wagesStandardBef" lay-verify="required|wagesStandardBef" autocomplete="off" placeholder="原岗位工资标准" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">调资类别</label>
	            <div class="layui-input-inline">
	              <input type="text" id="salaryAdjustTypeCn" name="salaryAdjustTypeCn" lay-verify="required|salaryAdjustTypeCn" autocomplete="off" placeholder="调资类别" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">医院一级科室</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosDepart1levelCn" name="hosDepart1levelCn" lay-verify="required|hosDepart1levelCn" autocomplete="off" placeholder="医院一级科室" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">医院二级科室</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosDepart2levelCn" name="hosDepart2levelCn" lay-verify="required|hosDepart2levelCn" autocomplete="off" placeholder="医院二级科室" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">最高学历</label>
	            <div class="layui-input-inline">
	              <input type="text" id="edu4max" name="edu4max" lay-verify="required|edu4max" autocomplete="off" placeholder="最高学历" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">第一学历</label>
	            <div class="layui-input-inline">
	              <input type="text" id="edu4first" name="edu4first" lay-verify="required|edu4first" autocomplete="off" placeholder="第一学历" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工资学历</label>
	            <div class="layui-input-inline">
	              <input type="text" id="edu4wage" name="edu4wage" lay-verify="required|edu4wage" autocomplete="off" placeholder="工资学历" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">首次参加工作时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="firstWorkTime" name="firstWorkTime" lay-verify="required|firstWorkTime" autocomplete="off" placeholder="首次参加工作时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工龄</label>
	            <div class="layui-input-inline">
	              <input type="text" id="workedYear" name="workedYear" lay-verify="required|workedYear" autocomplete="off" placeholder="工龄" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现行政职务</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit4nowCn" name="posit4nowCn" lay-verify="required|posit4nowCn" autocomplete="off" placeholder="现行政职务" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任职开始时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit4nowStartTime" name="posit4nowStartTime" lay-verify="required|posit4nowStartTime" autocomplete="off" placeholder="任职开始时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现行政级别年限</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit4nowYears" name="posit4nowYears" lay-verify="required|posit4nowYears" autocomplete="off" placeholder="现行政级别年限" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">行政级别</label>
	            <div class="layui-input-inline">
	              <input type="text" id="positLevelCn" name="positLevelCn" lay-verify="required|positLevelCn" autocomplete="off" placeholder="行政级别" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">行政级别代码</label>
	            <div class="layui-input-inline">
	              <input type="text" id="positLevelCode" name="positLevelCode" lay-verify="required|positLevelCode" autocomplete="off" placeholder="行政级别代码" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正职时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onPrinPositStartTime" name="onPrinPositStartTime" lay-verify="required|onPrinPositStartTime" autocomplete="off" placeholder="任正职时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任正职年限</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onPrinPositYears" name="onPrinPositYears" lay-verify="required|onPrinPositYears" autocomplete="off" placeholder="任正职年限" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副职时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onDeptPositStartTime" name="onDeptPositStartTime" lay-verify="required|onDeptPositStartTime" autocomplete="off" placeholder="任副职时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">任副职年限</label>
	            <div class="layui-input-inline">
	              <input type="text" id="onDeptPositYears" name="onDeptPositYears" lay-verify="required|onDeptPositYears" autocomplete="off" placeholder="任副职年限" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">最高职称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4max" name="title4max" lay-verify="required|title4max" autocomplete="off" placeholder="最高职称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">最高职称取得时间</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4maxGotTime" name="title4maxGotTime" lay-verify="required|title4maxGotTime" autocomplete="off" placeholder="最高职称取得时间" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">最高职称取得年限</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4maxGotYears" name="title4maxGotYears" lay-verify="required|title4maxGotYears" autocomplete="off" placeholder="最高职称取得年限" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职称序列</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4maxClassCn" name="title4maxClassCn" lay-verify="required|title4maxClassCn" autocomplete="off" placeholder="职称序列" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职称级别</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title4maxLevelCn" name="title4maxLevelCn" lay-verify="required|title4maxLevelCn" autocomplete="off" placeholder="职称级别" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职称调资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="adjust4title" name="adjust4title" lay-verify="required|adjust4title" autocomplete="off" placeholder="职称调资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职务调资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="adjust4posit" name="adjust4posit" lay-verify="required|adjust4posit" autocomplete="off" placeholder="职务调资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">学历调资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="adjust4edu" name="adjust4edu" lay-verify="required|adjust4edu" autocomplete="off" placeholder="学历调资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">调整后工资标准</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wagesStandardAft" name="wagesStandardAft" lay-verify="required|wagesStandardAft" autocomplete="off" placeholder="调整后工资标准" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">调资依据</label>
	            <div class="layui-input-inline">
	              <input type="text" id="adjustProof" name="adjustProof" lay-verify="required|adjustProof" autocomplete="off" placeholder="调资依据" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">调资差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="adjustDiffe" name="adjustDiffe" lay-verify="required|adjustDiffe" autocomplete="off" placeholder="调资差额" class="layui-input">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chSaAdjustSalary-add-submit" id="LAY-chSaAdjustSalary-add-submit">立即提交</button>
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
    chSaAdjustSalary: 'chSaAdjustSalary'
  }).use(['index','form','chSaAdjustSalary'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chSaAdjustSalary-add-form');
  });
  </script>
</body>
</html>