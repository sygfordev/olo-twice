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
  <title>layuiAdmin 后台管理员</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
  <div class="layui-fluid">   
    <div class="layui-card">
      <!-- 隐藏属性 -->
      <input id="basePath" name="basePath" type="hidden" value='<%=basePath%>'>
      <input id="depart1List" name="depart1List" type="hidden" value='${depart1List}'>
   	  <input id="depart2List" name="depart2List" type="hidden" value='${depart2List}'>
   	  <input id="modalityList" name="modalityList" type="hidden" value='${modalityList}'>
   	  <input id="compNameList" name="compNameList" type="hidden" value='${compNameList}'>
   	  <input id="curMonth" name="curMonth" type="hidden" value='${curMonth}'>
      <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="socialIndex">
        <div class="layui-form-item">
		  <div class="layui-inline">
          	<label class="layui-form-label">社保年月</label>
	        <div class="layui-input-inline">
	            <input type="text" class="layui-input" id="socialYmonth" readonly="" lay-verify="required" name="socialYmonth" placeholder="社保年月">
	        </div>
	      </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">姓名</label>
	        <div class="layui-input-block">
	          <input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">身份证号</label>
	        <div class="layui-input-block">
	          <input type="text" name="cardNo" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">用工类别</label>
	        <!-- <div class="layui-input-block">
	          <input type="text" name="wkModalityCn" placeholder="请输入用工类别" autocomplete="off" class="layui-input">
	        </div> -->
	        <div class="layui-input-block" id="wkModalityCns"></div>
          </div>
		  <!-- <div class="layui-inline">
	        <label class="layui-form-label">工作地域-名称</label>
	        <div class="layui-input-block">
	          <input type="text" name="workAreaCn" placeholder="请输入工作地域-名称" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">职务-名称</label>
	        <div class="layui-input-block">
	          <input type="text" name="positCn" placeholder="请输入职务-名称" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">职称-名称</label>
	        <div class="layui-input-block">
	          <input type="text" name="titleCn" placeholder="请输入职称-名称" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">医院支部-名称</label>
	        <div class="layui-input-block">
	          <input type="text" name="hosBranchCn" placeholder="请输入医院支部-名称" autocomplete="off" class="layui-input">
	        </div>
          </div> -->
		  <div class="layui-inline">
	        <label class="layui-form-label">一级科室</label>
	        <!-- <div class="layui-input-block">
	          <input type="text" name="hosDepart1levelCn" placeholder="请选择一级科室" autocomplete="off" class="layui-input">
	        </div> -->
	        <div class="layui-input-block" id="hosDepart1levelCns"></div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">二级科室</label>
	        <!-- <div class="layui-input-block">
	          <input type="text" name="hosDepart2levelCn" placeholder="请输入二级科室" autocomplete="off" class="layui-input">
	        </div> -->
	        <div class="layui-input-block" id="hosDepart2levelCns"></div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">单位名称</label>
	        <div class="layui-input-block" id="compNames"></div>
          </div>
		  <%-- <div class="layui-inline">
	        <label class="layui-form-label">社保年月</label>
	        <div class="layui-input-block">
	          <input type="text" name="socialYmonth" placeholder="请输入社保年月" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">基数</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenBase" placeholder="请输入基数" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenCompRatio" placeholder="请输入单位应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenCompAmount" placeholder="请输入单位应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenCompRecapAmount" placeholder="请输入单位实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenCompRecapDiffe" placeholder="请输入单位实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位多缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenCompOverpaid" placeholder="请输入单位多缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenPersRatio" placeholder="请输入个人应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenPersAmount" placeholder="请输入个人应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenPersRecapAmount" placeholder="请输入个人实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenPersRecapDiffe" placeholder="请输入个人实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人补缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sPenPersComple" placeholder="请输入个人补缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">基数</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediBase" placeholder="请输入基数" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediCompRatio" placeholder="请输入单位应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediCompAmount" placeholder="请输入单位应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediCompRecapAmount" placeholder="请输入单位实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediCompRecapDiffe" placeholder="请输入单位实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位多缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediCompOverpaid" placeholder="请输入单位多缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediPersRatio" placeholder="请输入个人应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediPersAmount" placeholder="请输入个人应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediPersRecapAmount" placeholder="请输入个人实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediPersRecapDiffe" placeholder="请输入个人实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人补缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sMediPersComple" placeholder="请输入个人补缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">基数</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempBase" placeholder="请输入基数" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempCompRatio" placeholder="请输入单位应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempCompAmount" placeholder="请输入单位应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempCompRecapAmount" placeholder="请输入单位实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempCompRecapDiffe" placeholder="请输入单位实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位多缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempCompOverpaid" placeholder="请输入单位多缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempPersRatio" placeholder="请输入个人应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempPersAmount" placeholder="请输入个人应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempPersRecapAmount" placeholder="请输入个人实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempPersRecapDiffe" placeholder="请输入个人实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人补缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sUnempPersComple" placeholder="请输入个人补缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">基数</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryBase" placeholder="请输入基数" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryCompRatio" placeholder="请输入单位应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryCompAmount" placeholder="请输入单位应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryCompRecapAmount" placeholder="请输入单位实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryCompRecapDiffe" placeholder="请输入单位实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位多缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryCompOverpaid" placeholder="请输入单位多缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryPersRatio" placeholder="请输入个人应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryPersAmount" placeholder="请输入个人应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryPersRecapAmount" placeholder="请输入个人实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryPersRecapDiffe" placeholder="请输入个人实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人补缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sInjuryPersComple" placeholder="请输入个人补缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">基数</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthBase" placeholder="请输入基数" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthCompRatio" placeholder="请输入单位应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthCompAmount" placeholder="请输入单位应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthCompRecapAmount" placeholder="请输入单位实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthCompRecapDiffe" placeholder="请输入单位实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位多缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthCompOverpaid" placeholder="请输入单位多缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthPersRatio" placeholder="请输入个人应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthPersAmount" placeholder="请输入个人应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthPersRecapAmount" placeholder="请输入个人实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthPersRecapDiffe" placeholder="请输入个人实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人补缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sBirthPersComple" placeholder="请输入个人补缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">基数</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityBase" placeholder="请输入基数" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityCompRatio" placeholder="请输入单位应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityCompAmount" placeholder="请输入单位应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityCompRecapAmount" placeholder="请输入单位实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityCompRecapDiffe" placeholder="请输入单位实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位多缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityCompOverpaid" placeholder="请输入单位多缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityPersRatio" placeholder="请输入个人应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityPersAmount" placeholder="请输入个人应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityPersRecapAmount" placeholder="请输入个人实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityPersRecapDiffe" placeholder="请输入个人实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人补缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sAnnuityPersComple" placeholder="请输入个人补缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">基数</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpBase" placeholder="请输入基数" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpCompRatio" placeholder="请输入单位应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpCompAmount" placeholder="请输入单位应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpCompRecapAmount" placeholder="请输入单位实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpCompRecapDiffe" placeholder="请输入单位实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位多缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpCompOverpaid" placeholder="请输入单位多缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpPersRatio" placeholder="请输入个人应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpPersAmount" placeholder="请输入个人应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpPersRecapAmount" placeholder="请输入个人实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpPersRecapDiffe" placeholder="请输入个人实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人补缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sOvpPersComple" placeholder="请输入个人补缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">基数</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediBase" placeholder="请输入基数" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediCompRatio" placeholder="请输入单位应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediCompAmount" placeholder="请输入单位应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediCompRecapAmount" placeholder="请输入单位实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediCompRecapDiffe" placeholder="请输入单位实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">单位多缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediCompOverpaid" placeholder="请输入单位多缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴比例</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediPersRatio" placeholder="请输入个人应缴比例" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人应缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediPersAmount" placeholder="请输入个人应缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴金额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediPersRecapAmount" placeholder="请输入个人实缴金额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人实缴差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediPersRecapDiffe" placeholder="请输入个人实缴差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人补缴</label>
	        <div class="layui-input-block">
	          <input type="text" name="sSpMediPersComple" placeholder="请输入个人补缴" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">月备注</label>
	        <div class="layui-input-block">
	          <input type="text" name="remark4month" placeholder="请输入月备注" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">年备注</label>
	        <div class="layui-input-block">
	          <input type="text" name="remark4year" placeholder="请输入年备注" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">本年个人实缴累计差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="persRecapDiffe4addup" placeholder="请输入本年个人实缴累计差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">本年个人实缴累计差额</label>
	        <div class="layui-input-block">
	          <input type="text" name="persCompleDiffe4addup" placeholder="请输入本年个人实缴累计差额" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">导入编号</label>
	        <div class="layui-input-block">
	          <input type="text" name="btimpNo" placeholder="请输入导入编号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">备注</label>
	        <div class="layui-input-block">
	          <input type="text" name="remark" placeholder="请输入备注" autocomplete="off" class="layui-input">
	        </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-block">
              <select name="status">
              	<option value="">--所有--</option>
              	<c:forEach var="item" items="${statusList}" varStatus="status">
              		<option value="${item.key}">${item.value}</option>
              	</c:forEach>
              </select>
            </div>
          </div> --%>
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chSocialInfo-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<%-- <shiro:hasPermission name="chSocialInfo:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chSocialInfo:batchdel">
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
            </shiro:hasPermission> --%>
            <shiro:hasPermission name="chSocialInfo:revoke">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="revoke">批次撤销</button>
          	</shiro:hasPermission>
          </div>
        </div>
        
        <div id="toolbarDemo"></div>
        <!-- 数据表格 -->
        <table id="LAY-chSocialInfo-manage" lay-filter="LAY-chSocialInfo-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chSocialInfo-manager-operator">
          <shiro:hasPermission name="chSocialInfo:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chSocialInfo:delete">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chSocialInfo:details">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="details"><i class="layui-icon layui-icon-read"></i>详情</a>
		  </shiro:hasPermission>
        </script>
      </div>
    </div>
  </div>

 <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
  <script>
  layui.config({
  	base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    chSocialInfo: 'social/chSocialInfo'
  }).use(['index', 'chSocialInfo', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>