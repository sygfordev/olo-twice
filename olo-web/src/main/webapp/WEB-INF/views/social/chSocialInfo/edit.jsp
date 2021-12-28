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
        <form class="layui-form" action="" lay-filter="chSocialInfo-edit-form">
          <input type="hidden" name="id" value="${model.id}">
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">姓名</label>
	            <div class="layui-input-inline">
	              <input type="text" id="name" name="name" value="${model.name}"  autocomplete="off" placeholder="请输入姓名" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">身份证号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="cardNo" name="cardNo" value="${model.cardNo}" lay-verify="required|cardNo" autocomplete="off" placeholder="请输入身份证号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="compName" name="compName" value="${model.compName}" lay-verify="required|compName" autocomplete="off" placeholder="请输入单位名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">用工形式(人员类别)名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkModalityCn" name="wkModalityCn" value="${model.wkModalityCn}" lay-verify="required|wkModalityCn" autocomplete="off" placeholder="请输入用工形式(人员类别)名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工作地域-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="workAreaCn" name="workAreaCn" value="${model.workAreaCn}" lay-verify="required|workAreaCn" autocomplete="off" placeholder="请输入工作地域-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职务-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="positCn" name="positCn" value="${model.positCn}" lay-verify="required|positCn" autocomplete="off" placeholder="请输入职务-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职称-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleCn" name="titleCn" value="${model.titleCn}" lay-verify="required|titleCn" autocomplete="off" placeholder="请输入职称-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">医院支部-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosBranchCn" name="hosBranchCn" value="${model.hosBranchCn}" lay-verify="required|hosBranchCn" autocomplete="off" placeholder="请输入医院支部-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">医院一级科室-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosDepart1levelCn" name="hosDepart1levelCn" value="${model.hosDepart1levelCn}" lay-verify="required|hosDepart1levelCn" autocomplete="off" placeholder="请输入医院一级科室-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">医院二级科室-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosDepart2levelCn" name="hosDepart2levelCn" value="${model.hosDepart2levelCn}" lay-verify="required|hosDepart2levelCn" autocomplete="off" placeholder="请输入医院二级科室-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">社保年月</label>
	            <div class="layui-input-inline">
	              <input type="text" id="socialYmonth" name="socialYmonth" value="${model.socialYmonth}"  autocomplete="off" placeholder="请输入社保年月" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenBase" name="sPenBase" value="${model.sPenBase}" lay-verify="required|sPenBase" autocomplete="off" placeholder="请输入基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompRatio" name="sPenCompRatio" value="${model.sPenCompRatio}" lay-verify="required|sPenCompRatio" autocomplete="off" placeholder="请输入单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompAmount" name="sPenCompAmount" value="${model.sPenCompAmount}" lay-verify="required|sPenCompAmount" autocomplete="off" placeholder="请输入单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompRecapAmount" name="sPenCompRecapAmount" value="${model.sPenCompRecapAmount}" lay-verify="required|sPenCompRecapAmount" autocomplete="off" placeholder="请输入单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompRecapDiffe" name="sPenCompRecapDiffe" value="${model.sPenCompRecapDiffe}" lay-verify="required|sPenCompRecapDiffe" autocomplete="off" placeholder="请输入单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompOverpaid" name="sPenCompOverpaid" value="${model.sPenCompOverpaid}" lay-verify="required|sPenCompOverpaid" autocomplete="off" placeholder="请输入单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersRatio" name="sPenPersRatio" value="${model.sPenPersRatio}" lay-verify="required|sPenPersRatio" autocomplete="off" placeholder="请输入个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersAmount" name="sPenPersAmount" value="${model.sPenPersAmount}" lay-verify="required|sPenPersAmount" autocomplete="off" placeholder="请输入个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersRecapAmount" name="sPenPersRecapAmount" value="${model.sPenPersRecapAmount}" lay-verify="required|sPenPersRecapAmount" autocomplete="off" placeholder="请输入个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersRecapDiffe" name="sPenPersRecapDiffe" value="${model.sPenPersRecapDiffe}" lay-verify="required|sPenPersRecapDiffe" autocomplete="off" placeholder="请输入个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersComple" name="sPenPersComple" value="${model.sPenPersComple}" lay-verify="required|sPenPersComple" autocomplete="off" placeholder="请输入个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediBase" name="sMediBase" value="${model.sMediBase}" lay-verify="required|sMediBase" autocomplete="off" placeholder="请输入基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompRatio" name="sMediCompRatio" value="${model.sMediCompRatio}" lay-verify="required|sMediCompRatio" autocomplete="off" placeholder="请输入单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompAmount" name="sMediCompAmount" value="${model.sMediCompAmount}" lay-verify="required|sMediCompAmount" autocomplete="off" placeholder="请输入单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompRecapAmount" name="sMediCompRecapAmount" value="${model.sMediCompRecapAmount}" lay-verify="required|sMediCompRecapAmount" autocomplete="off" placeholder="请输入单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompRecapDiffe" name="sMediCompRecapDiffe" value="${model.sMediCompRecapDiffe}" lay-verify="required|sMediCompRecapDiffe" autocomplete="off" placeholder="请输入单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompOverpaid" name="sMediCompOverpaid" value="${model.sMediCompOverpaid}" lay-verify="required|sMediCompOverpaid" autocomplete="off" placeholder="请输入单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersRatio" name="sMediPersRatio" value="${model.sMediPersRatio}" lay-verify="required|sMediPersRatio" autocomplete="off" placeholder="请输入个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersAmount" name="sMediPersAmount" value="${model.sMediPersAmount}" lay-verify="required|sMediPersAmount" autocomplete="off" placeholder="请输入个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersRecapAmount" name="sMediPersRecapAmount" value="${model.sMediPersRecapAmount}" lay-verify="required|sMediPersRecapAmount" autocomplete="off" placeholder="请输入个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersRecapDiffe" name="sMediPersRecapDiffe" value="${model.sMediPersRecapDiffe}" lay-verify="required|sMediPersRecapDiffe" autocomplete="off" placeholder="请输入个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersComple" name="sMediPersComple" value="${model.sMediPersComple}" lay-verify="required|sMediPersComple" autocomplete="off" placeholder="请输入个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempBase" name="sUnempBase" value="${model.sUnempBase}" lay-verify="required|sUnempBase" autocomplete="off" placeholder="请输入基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompRatio" name="sUnempCompRatio" value="${model.sUnempCompRatio}" lay-verify="required|sUnempCompRatio" autocomplete="off" placeholder="请输入单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompAmount" name="sUnempCompAmount" value="${model.sUnempCompAmount}" lay-verify="required|sUnempCompAmount" autocomplete="off" placeholder="请输入单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompRecapAmount" name="sUnempCompRecapAmount" value="${model.sUnempCompRecapAmount}" lay-verify="required|sUnempCompRecapAmount" autocomplete="off" placeholder="请输入单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompRecapDiffe" name="sUnempCompRecapDiffe" value="${model.sUnempCompRecapDiffe}" lay-verify="required|sUnempCompRecapDiffe" autocomplete="off" placeholder="请输入单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompOverpaid" name="sUnempCompOverpaid" value="${model.sUnempCompOverpaid}" lay-verify="required|sUnempCompOverpaid" autocomplete="off" placeholder="请输入单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersRatio" name="sUnempPersRatio" value="${model.sUnempPersRatio}" lay-verify="required|sUnempPersRatio" autocomplete="off" placeholder="请输入个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersAmount" name="sUnempPersAmount" value="${model.sUnempPersAmount}" lay-verify="required|sUnempPersAmount" autocomplete="off" placeholder="请输入个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersRecapAmount" name="sUnempPersRecapAmount" value="${model.sUnempPersRecapAmount}" lay-verify="required|sUnempPersRecapAmount" autocomplete="off" placeholder="请输入个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersRecapDiffe" name="sUnempPersRecapDiffe" value="${model.sUnempPersRecapDiffe}" lay-verify="required|sUnempPersRecapDiffe" autocomplete="off" placeholder="请输入个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersComple" name="sUnempPersComple" value="${model.sUnempPersComple}" lay-verify="required|sUnempPersComple" autocomplete="off" placeholder="请输入个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryBase" name="sInjuryBase" value="${model.sInjuryBase}" lay-verify="required|sInjuryBase" autocomplete="off" placeholder="请输入基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompRatio" name="sInjuryCompRatio" value="${model.sInjuryCompRatio}" lay-verify="required|sInjuryCompRatio" autocomplete="off" placeholder="请输入单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompAmount" name="sInjuryCompAmount" value="${model.sInjuryCompAmount}" lay-verify="required|sInjuryCompAmount" autocomplete="off" placeholder="请输入单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompRecapAmount" name="sInjuryCompRecapAmount" value="${model.sInjuryCompRecapAmount}" lay-verify="required|sInjuryCompRecapAmount" autocomplete="off" placeholder="请输入单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompRecapDiffe" name="sInjuryCompRecapDiffe" value="${model.sInjuryCompRecapDiffe}" lay-verify="required|sInjuryCompRecapDiffe" autocomplete="off" placeholder="请输入单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompOverpaid" name="sInjuryCompOverpaid" value="${model.sInjuryCompOverpaid}" lay-verify="required|sInjuryCompOverpaid" autocomplete="off" placeholder="请输入单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersRatio" name="sInjuryPersRatio" value="${model.sInjuryPersRatio}" lay-verify="required|sInjuryPersRatio" autocomplete="off" placeholder="请输入个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersAmount" name="sInjuryPersAmount" value="${model.sInjuryPersAmount}" lay-verify="required|sInjuryPersAmount" autocomplete="off" placeholder="请输入个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersRecapAmount" name="sInjuryPersRecapAmount" value="${model.sInjuryPersRecapAmount}" lay-verify="required|sInjuryPersRecapAmount" autocomplete="off" placeholder="请输入个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersRecapDiffe" name="sInjuryPersRecapDiffe" value="${model.sInjuryPersRecapDiffe}" lay-verify="required|sInjuryPersRecapDiffe" autocomplete="off" placeholder="请输入个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersComple" name="sInjuryPersComple" value="${model.sInjuryPersComple}" lay-verify="required|sInjuryPersComple" autocomplete="off" placeholder="请输入个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthBase" name="sBirthBase" value="${model.sBirthBase}" lay-verify="required|sBirthBase" autocomplete="off" placeholder="请输入基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompRatio" name="sBirthCompRatio" value="${model.sBirthCompRatio}" lay-verify="required|sBirthCompRatio" autocomplete="off" placeholder="请输入单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompAmount" name="sBirthCompAmount" value="${model.sBirthCompAmount}" lay-verify="required|sBirthCompAmount" autocomplete="off" placeholder="请输入单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompRecapAmount" name="sBirthCompRecapAmount" value="${model.sBirthCompRecapAmount}" lay-verify="required|sBirthCompRecapAmount" autocomplete="off" placeholder="请输入单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompRecapDiffe" name="sBirthCompRecapDiffe" value="${model.sBirthCompRecapDiffe}" lay-verify="required|sBirthCompRecapDiffe" autocomplete="off" placeholder="请输入单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompOverpaid" name="sBirthCompOverpaid" value="${model.sBirthCompOverpaid}" lay-verify="required|sBirthCompOverpaid" autocomplete="off" placeholder="请输入单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersRatio" name="sBirthPersRatio" value="${model.sBirthPersRatio}" lay-verify="required|sBirthPersRatio" autocomplete="off" placeholder="请输入个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersAmount" name="sBirthPersAmount" value="${model.sBirthPersAmount}" lay-verify="required|sBirthPersAmount" autocomplete="off" placeholder="请输入个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersRecapAmount" name="sBirthPersRecapAmount" value="${model.sBirthPersRecapAmount}" lay-verify="required|sBirthPersRecapAmount" autocomplete="off" placeholder="请输入个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersRecapDiffe" name="sBirthPersRecapDiffe" value="${model.sBirthPersRecapDiffe}" lay-verify="required|sBirthPersRecapDiffe" autocomplete="off" placeholder="请输入个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersComple" name="sBirthPersComple" value="${model.sBirthPersComple}" lay-verify="required|sBirthPersComple" autocomplete="off" placeholder="请输入个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityBase" name="sAnnuityBase" value="${model.sAnnuityBase}" lay-verify="required|sAnnuityBase" autocomplete="off" placeholder="请输入基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompRatio" name="sAnnuityCompRatio" value="${model.sAnnuityCompRatio}" lay-verify="required|sAnnuityCompRatio" autocomplete="off" placeholder="请输入单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompAmount" name="sAnnuityCompAmount" value="${model.sAnnuityCompAmount}" lay-verify="required|sAnnuityCompAmount" autocomplete="off" placeholder="请输入单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompRecapAmount" name="sAnnuityCompRecapAmount" value="${model.sAnnuityCompRecapAmount}" lay-verify="required|sAnnuityCompRecapAmount" autocomplete="off" placeholder="请输入单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompRecapDiffe" name="sAnnuityCompRecapDiffe" value="${model.sAnnuityCompRecapDiffe}" lay-verify="required|sAnnuityCompRecapDiffe" autocomplete="off" placeholder="请输入单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompOverpaid" name="sAnnuityCompOverpaid" value="${model.sAnnuityCompOverpaid}" lay-verify="required|sAnnuityCompOverpaid" autocomplete="off" placeholder="请输入单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersRatio" name="sAnnuityPersRatio" value="${model.sAnnuityPersRatio}" lay-verify="required|sAnnuityPersRatio" autocomplete="off" placeholder="请输入个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersAmount" name="sAnnuityPersAmount" value="${model.sAnnuityPersAmount}" lay-verify="required|sAnnuityPersAmount" autocomplete="off" placeholder="请输入个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersRecapAmount" name="sAnnuityPersRecapAmount" value="${model.sAnnuityPersRecapAmount}" lay-verify="required|sAnnuityPersRecapAmount" autocomplete="off" placeholder="请输入个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersRecapDiffe" name="sAnnuityPersRecapDiffe" value="${model.sAnnuityPersRecapDiffe}" lay-verify="required|sAnnuityPersRecapDiffe" autocomplete="off" placeholder="请输入个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersComple" name="sAnnuityPersComple" value="${model.sAnnuityPersComple}" lay-verify="required|sAnnuityPersComple" autocomplete="off" placeholder="请输入个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpBase" name="sOvpBase" value="${model.sOvpBase}" lay-verify="required|sOvpBase" autocomplete="off" placeholder="请输入基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompRatio" name="sOvpCompRatio" value="${model.sOvpCompRatio}" lay-verify="required|sOvpCompRatio" autocomplete="off" placeholder="请输入单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompAmount" name="sOvpCompAmount" value="${model.sOvpCompAmount}" lay-verify="required|sOvpCompAmount" autocomplete="off" placeholder="请输入单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompRecapAmount" name="sOvpCompRecapAmount" value="${model.sOvpCompRecapAmount}" lay-verify="required|sOvpCompRecapAmount" autocomplete="off" placeholder="请输入单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompRecapDiffe" name="sOvpCompRecapDiffe" value="${model.sOvpCompRecapDiffe}" lay-verify="required|sOvpCompRecapDiffe" autocomplete="off" placeholder="请输入单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompOverpaid" name="sOvpCompOverpaid" value="${model.sOvpCompOverpaid}" lay-verify="required|sOvpCompOverpaid" autocomplete="off" placeholder="请输入单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersRatio" name="sOvpPersRatio" value="${model.sOvpPersRatio}" lay-verify="required|sOvpPersRatio" autocomplete="off" placeholder="请输入个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersAmount" name="sOvpPersAmount" value="${model.sOvpPersAmount}" lay-verify="required|sOvpPersAmount" autocomplete="off" placeholder="请输入个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersRecapAmount" name="sOvpPersRecapAmount" value="${model.sOvpPersRecapAmount}" lay-verify="required|sOvpPersRecapAmount" autocomplete="off" placeholder="请输入个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersRecapDiffe" name="sOvpPersRecapDiffe" value="${model.sOvpPersRecapDiffe}" lay-verify="required|sOvpPersRecapDiffe" autocomplete="off" placeholder="请输入个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersComple" name="sOvpPersComple" value="${model.sOvpPersComple}" lay-verify="required|sOvpPersComple" autocomplete="off" placeholder="请输入个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediBase" name="sSpMediBase" value="${model.sSpMediBase}" lay-verify="required|sSpMediBase" autocomplete="off" placeholder="请输入基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompRatio" name="sSpMediCompRatio" value="${model.sSpMediCompRatio}" lay-verify="required|sSpMediCompRatio" autocomplete="off" placeholder="请输入单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompAmount" name="sSpMediCompAmount" value="${model.sSpMediCompAmount}" lay-verify="required|sSpMediCompAmount" autocomplete="off" placeholder="请输入单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompRecapAmount" name="sSpMediCompRecapAmount" value="${model.sSpMediCompRecapAmount}" lay-verify="required|sSpMediCompRecapAmount" autocomplete="off" placeholder="请输入单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompRecapDiffe" name="sSpMediCompRecapDiffe" value="${model.sSpMediCompRecapDiffe}" lay-verify="required|sSpMediCompRecapDiffe" autocomplete="off" placeholder="请输入单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompOverpaid" name="sSpMediCompOverpaid" value="${model.sSpMediCompOverpaid}" lay-verify="required|sSpMediCompOverpaid" autocomplete="off" placeholder="请输入单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersRatio" name="sSpMediPersRatio" value="${model.sSpMediPersRatio}" lay-verify="required|sSpMediPersRatio" autocomplete="off" placeholder="请输入个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersAmount" name="sSpMediPersAmount" value="${model.sSpMediPersAmount}" lay-verify="required|sSpMediPersAmount" autocomplete="off" placeholder="请输入个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersRecapAmount" name="sSpMediPersRecapAmount" value="${model.sSpMediPersRecapAmount}" lay-verify="required|sSpMediPersRecapAmount" autocomplete="off" placeholder="请输入个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersRecapDiffe" name="sSpMediPersRecapDiffe" value="${model.sSpMediPersRecapDiffe}" lay-verify="required|sSpMediPersRecapDiffe" autocomplete="off" placeholder="请输入个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersComple" name="sSpMediPersComple" value="${model.sSpMediPersComple}" lay-verify="required|sSpMediPersComple" autocomplete="off" placeholder="请输入个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">月备注</label>
	            <div class="layui-input-inline">
	              <input type="text" id="remark4month" name="remark4month" value="${model.remark4month}" lay-verify="required|remark4month" autocomplete="off" placeholder="请输入月备注" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">年备注</label>
	            <div class="layui-input-inline">
	              <input type="text" id="remark4year" name="remark4year" value="${model.remark4year}" lay-verify="required|remark4year" autocomplete="off" placeholder="请输入年备注" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">本年个人实缴累计差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="persRecapDiffe4addup" name="persRecapDiffe4addup" value="${model.persRecapDiffe4addup}" lay-verify="required|persRecapDiffe4addup" autocomplete="off" placeholder="请输入本年个人实缴累计差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">本年个人实缴累计差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="persCompleDiffe4addup" name="persCompleDiffe4addup" value="${model.persCompleDiffe4addup}" lay-verify="required|persCompleDiffe4addup" autocomplete="off" placeholder="请输入本年个人实缴累计差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">导入编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="btimpNo" name="btimpNo" value="${model.btimpNo}"  autocomplete="off" placeholder="请输入导入编号" class="layui-input">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chSocialInfo-edit-submit" id="LAY-chSocialInfo-edit-submit">修改</button>
                <button lay-submit=""  lay-filter="LAY-chSocialInfo-edit-cancel" id="LAY-chSocialInfo-edit-cancel" class="layui-btn layui-btn-primary">取消</button>
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
    chSocialInfo: 'chSocialInfo'
  }).use(['index', 'form', 'laydate', 'chSocialInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chSocialInfo-edit-form');
  });
  </script>
</body>
</html>