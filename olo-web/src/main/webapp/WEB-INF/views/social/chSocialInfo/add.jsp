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
          <form class="layui-form" action="" lay-filter="chSocialInfo-add-form">
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">姓名</label>
	            <div class="layui-input-inline">
	              <input type="text" id="name" name="name"  autocomplete="off" placeholder="姓名" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">身份证号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="cardNo" name="cardNo" lay-verify="required|cardNo" autocomplete="off" placeholder="身份证号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="compName" name="compName" lay-verify="required|compName" autocomplete="off" placeholder="单位名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">用工形式(人员类别)名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkModalityCn" name="wkModalityCn" lay-verify="required|wkModalityCn" autocomplete="off" placeholder="用工形式(人员类别)名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工作地域-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="workAreaCn" name="workAreaCn" lay-verify="required|workAreaCn" autocomplete="off" placeholder="工作地域-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职务-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="positCn" name="positCn" lay-verify="required|positCn" autocomplete="off" placeholder="职务-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职称-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="titleCn" name="titleCn" lay-verify="required|titleCn" autocomplete="off" placeholder="职称-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">医院支部-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosBranchCn" name="hosBranchCn" lay-verify="required|hosBranchCn" autocomplete="off" placeholder="医院支部-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">医院一级科室-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosDepart1levelCn" name="hosDepart1levelCn" lay-verify="required|hosDepart1levelCn" autocomplete="off" placeholder="医院一级科室-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">医院二级科室-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosDepart2levelCn" name="hosDepart2levelCn" lay-verify="required|hosDepart2levelCn" autocomplete="off" placeholder="医院二级科室-名称" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">社保年月</label>
	            <div class="layui-input-inline">
	              <input type="text" id="socialYmonth" name="socialYmonth"  autocomplete="off" placeholder="社保年月" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenBase" name="sPenBase" lay-verify="required|sPenBase" autocomplete="off" placeholder="基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompRatio" name="sPenCompRatio" lay-verify="required|sPenCompRatio" autocomplete="off" placeholder="单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompAmount" name="sPenCompAmount" lay-verify="required|sPenCompAmount" autocomplete="off" placeholder="单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompRecapAmount" name="sPenCompRecapAmount" lay-verify="required|sPenCompRecapAmount" autocomplete="off" placeholder="单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompRecapDiffe" name="sPenCompRecapDiffe" lay-verify="required|sPenCompRecapDiffe" autocomplete="off" placeholder="单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenCompOverpaid" name="sPenCompOverpaid" lay-verify="required|sPenCompOverpaid" autocomplete="off" placeholder="单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersRatio" name="sPenPersRatio" lay-verify="required|sPenPersRatio" autocomplete="off" placeholder="个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersAmount" name="sPenPersAmount" lay-verify="required|sPenPersAmount" autocomplete="off" placeholder="个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersRecapAmount" name="sPenPersRecapAmount" lay-verify="required|sPenPersRecapAmount" autocomplete="off" placeholder="个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersRecapDiffe" name="sPenPersRecapDiffe" lay-verify="required|sPenPersRecapDiffe" autocomplete="off" placeholder="个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sPenPersComple" name="sPenPersComple" lay-verify="required|sPenPersComple" autocomplete="off" placeholder="个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediBase" name="sMediBase" lay-verify="required|sMediBase" autocomplete="off" placeholder="基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompRatio" name="sMediCompRatio" lay-verify="required|sMediCompRatio" autocomplete="off" placeholder="单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompAmount" name="sMediCompAmount" lay-verify="required|sMediCompAmount" autocomplete="off" placeholder="单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompRecapAmount" name="sMediCompRecapAmount" lay-verify="required|sMediCompRecapAmount" autocomplete="off" placeholder="单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompRecapDiffe" name="sMediCompRecapDiffe" lay-verify="required|sMediCompRecapDiffe" autocomplete="off" placeholder="单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediCompOverpaid" name="sMediCompOverpaid" lay-verify="required|sMediCompOverpaid" autocomplete="off" placeholder="单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersRatio" name="sMediPersRatio" lay-verify="required|sMediPersRatio" autocomplete="off" placeholder="个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersAmount" name="sMediPersAmount" lay-verify="required|sMediPersAmount" autocomplete="off" placeholder="个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersRecapAmount" name="sMediPersRecapAmount" lay-verify="required|sMediPersRecapAmount" autocomplete="off" placeholder="个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersRecapDiffe" name="sMediPersRecapDiffe" lay-verify="required|sMediPersRecapDiffe" autocomplete="off" placeholder="个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sMediPersComple" name="sMediPersComple" lay-verify="required|sMediPersComple" autocomplete="off" placeholder="个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempBase" name="sUnempBase" lay-verify="required|sUnempBase" autocomplete="off" placeholder="基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompRatio" name="sUnempCompRatio" lay-verify="required|sUnempCompRatio" autocomplete="off" placeholder="单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompAmount" name="sUnempCompAmount" lay-verify="required|sUnempCompAmount" autocomplete="off" placeholder="单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompRecapAmount" name="sUnempCompRecapAmount" lay-verify="required|sUnempCompRecapAmount" autocomplete="off" placeholder="单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompRecapDiffe" name="sUnempCompRecapDiffe" lay-verify="required|sUnempCompRecapDiffe" autocomplete="off" placeholder="单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempCompOverpaid" name="sUnempCompOverpaid" lay-verify="required|sUnempCompOverpaid" autocomplete="off" placeholder="单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersRatio" name="sUnempPersRatio" lay-verify="required|sUnempPersRatio" autocomplete="off" placeholder="个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersAmount" name="sUnempPersAmount" lay-verify="required|sUnempPersAmount" autocomplete="off" placeholder="个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersRecapAmount" name="sUnempPersRecapAmount" lay-verify="required|sUnempPersRecapAmount" autocomplete="off" placeholder="个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersRecapDiffe" name="sUnempPersRecapDiffe" lay-verify="required|sUnempPersRecapDiffe" autocomplete="off" placeholder="个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sUnempPersComple" name="sUnempPersComple" lay-verify="required|sUnempPersComple" autocomplete="off" placeholder="个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryBase" name="sInjuryBase" lay-verify="required|sInjuryBase" autocomplete="off" placeholder="基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompRatio" name="sInjuryCompRatio" lay-verify="required|sInjuryCompRatio" autocomplete="off" placeholder="单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompAmount" name="sInjuryCompAmount" lay-verify="required|sInjuryCompAmount" autocomplete="off" placeholder="单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompRecapAmount" name="sInjuryCompRecapAmount" lay-verify="required|sInjuryCompRecapAmount" autocomplete="off" placeholder="单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompRecapDiffe" name="sInjuryCompRecapDiffe" lay-verify="required|sInjuryCompRecapDiffe" autocomplete="off" placeholder="单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryCompOverpaid" name="sInjuryCompOverpaid" lay-verify="required|sInjuryCompOverpaid" autocomplete="off" placeholder="单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersRatio" name="sInjuryPersRatio" lay-verify="required|sInjuryPersRatio" autocomplete="off" placeholder="个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersAmount" name="sInjuryPersAmount" lay-verify="required|sInjuryPersAmount" autocomplete="off" placeholder="个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersRecapAmount" name="sInjuryPersRecapAmount" lay-verify="required|sInjuryPersRecapAmount" autocomplete="off" placeholder="个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersRecapDiffe" name="sInjuryPersRecapDiffe" lay-verify="required|sInjuryPersRecapDiffe" autocomplete="off" placeholder="个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sInjuryPersComple" name="sInjuryPersComple" lay-verify="required|sInjuryPersComple" autocomplete="off" placeholder="个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthBase" name="sBirthBase" lay-verify="required|sBirthBase" autocomplete="off" placeholder="基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompRatio" name="sBirthCompRatio" lay-verify="required|sBirthCompRatio" autocomplete="off" placeholder="单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompAmount" name="sBirthCompAmount" lay-verify="required|sBirthCompAmount" autocomplete="off" placeholder="单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompRecapAmount" name="sBirthCompRecapAmount" lay-verify="required|sBirthCompRecapAmount" autocomplete="off" placeholder="单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompRecapDiffe" name="sBirthCompRecapDiffe" lay-verify="required|sBirthCompRecapDiffe" autocomplete="off" placeholder="单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthCompOverpaid" name="sBirthCompOverpaid" lay-verify="required|sBirthCompOverpaid" autocomplete="off" placeholder="单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersRatio" name="sBirthPersRatio" lay-verify="required|sBirthPersRatio" autocomplete="off" placeholder="个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersAmount" name="sBirthPersAmount" lay-verify="required|sBirthPersAmount" autocomplete="off" placeholder="个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersRecapAmount" name="sBirthPersRecapAmount" lay-verify="required|sBirthPersRecapAmount" autocomplete="off" placeholder="个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersRecapDiffe" name="sBirthPersRecapDiffe" lay-verify="required|sBirthPersRecapDiffe" autocomplete="off" placeholder="个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sBirthPersComple" name="sBirthPersComple" lay-verify="required|sBirthPersComple" autocomplete="off" placeholder="个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityBase" name="sAnnuityBase" lay-verify="required|sAnnuityBase" autocomplete="off" placeholder="基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompRatio" name="sAnnuityCompRatio" lay-verify="required|sAnnuityCompRatio" autocomplete="off" placeholder="单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompAmount" name="sAnnuityCompAmount" lay-verify="required|sAnnuityCompAmount" autocomplete="off" placeholder="单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompRecapAmount" name="sAnnuityCompRecapAmount" lay-verify="required|sAnnuityCompRecapAmount" autocomplete="off" placeholder="单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompRecapDiffe" name="sAnnuityCompRecapDiffe" lay-verify="required|sAnnuityCompRecapDiffe" autocomplete="off" placeholder="单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityCompOverpaid" name="sAnnuityCompOverpaid" lay-verify="required|sAnnuityCompOverpaid" autocomplete="off" placeholder="单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersRatio" name="sAnnuityPersRatio" lay-verify="required|sAnnuityPersRatio" autocomplete="off" placeholder="个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersAmount" name="sAnnuityPersAmount" lay-verify="required|sAnnuityPersAmount" autocomplete="off" placeholder="个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersRecapAmount" name="sAnnuityPersRecapAmount" lay-verify="required|sAnnuityPersRecapAmount" autocomplete="off" placeholder="个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersRecapDiffe" name="sAnnuityPersRecapDiffe" lay-verify="required|sAnnuityPersRecapDiffe" autocomplete="off" placeholder="个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sAnnuityPersComple" name="sAnnuityPersComple" lay-verify="required|sAnnuityPersComple" autocomplete="off" placeholder="个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpBase" name="sOvpBase" lay-verify="required|sOvpBase" autocomplete="off" placeholder="基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompRatio" name="sOvpCompRatio" lay-verify="required|sOvpCompRatio" autocomplete="off" placeholder="单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompAmount" name="sOvpCompAmount" lay-verify="required|sOvpCompAmount" autocomplete="off" placeholder="单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompRecapAmount" name="sOvpCompRecapAmount" lay-verify="required|sOvpCompRecapAmount" autocomplete="off" placeholder="单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompRecapDiffe" name="sOvpCompRecapDiffe" lay-verify="required|sOvpCompRecapDiffe" autocomplete="off" placeholder="单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpCompOverpaid" name="sOvpCompOverpaid" lay-verify="required|sOvpCompOverpaid" autocomplete="off" placeholder="单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersRatio" name="sOvpPersRatio" lay-verify="required|sOvpPersRatio" autocomplete="off" placeholder="个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersAmount" name="sOvpPersAmount" lay-verify="required|sOvpPersAmount" autocomplete="off" placeholder="个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersRecapAmount" name="sOvpPersRecapAmount" lay-verify="required|sOvpPersRecapAmount" autocomplete="off" placeholder="个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersRecapDiffe" name="sOvpPersRecapDiffe" lay-verify="required|sOvpPersRecapDiffe" autocomplete="off" placeholder="个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sOvpPersComple" name="sOvpPersComple" lay-verify="required|sOvpPersComple" autocomplete="off" placeholder="个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediBase" name="sSpMediBase" lay-verify="required|sSpMediBase" autocomplete="off" placeholder="基数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompRatio" name="sSpMediCompRatio" lay-verify="required|sSpMediCompRatio" autocomplete="off" placeholder="单位应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompAmount" name="sSpMediCompAmount" lay-verify="required|sSpMediCompAmount" autocomplete="off" placeholder="单位应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompRecapAmount" name="sSpMediCompRecapAmount" lay-verify="required|sSpMediCompRecapAmount" autocomplete="off" placeholder="单位实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompRecapDiffe" name="sSpMediCompRecapDiffe" lay-verify="required|sSpMediCompRecapDiffe" autocomplete="off" placeholder="单位实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">单位多缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediCompOverpaid" name="sSpMediCompOverpaid" lay-verify="required|sSpMediCompOverpaid" autocomplete="off" placeholder="单位多缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴比例</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersRatio" name="sSpMediPersRatio" lay-verify="required|sSpMediPersRatio" autocomplete="off" placeholder="个人应缴比例" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人应缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersAmount" name="sSpMediPersAmount" lay-verify="required|sSpMediPersAmount" autocomplete="off" placeholder="个人应缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴金额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersRecapAmount" name="sSpMediPersRecapAmount" lay-verify="required|sSpMediPersRecapAmount" autocomplete="off" placeholder="个人实缴金额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人实缴差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersRecapDiffe" name="sSpMediPersRecapDiffe" lay-verify="required|sSpMediPersRecapDiffe" autocomplete="off" placeholder="个人实缴差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个人补缴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="sSpMediPersComple" name="sSpMediPersComple" lay-verify="required|sSpMediPersComple" autocomplete="off" placeholder="个人补缴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">月备注</label>
	            <div class="layui-input-inline">
	              <input type="text" id="remark4month" name="remark4month" lay-verify="required|remark4month" autocomplete="off" placeholder="月备注" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">年备注</label>
	            <div class="layui-input-inline">
	              <input type="text" id="remark4year" name="remark4year" lay-verify="required|remark4year" autocomplete="off" placeholder="年备注" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">本年个人实缴累计差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="persRecapDiffe4addup" name="persRecapDiffe4addup" lay-verify="required|persRecapDiffe4addup" autocomplete="off" placeholder="本年个人实缴累计差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">本年个人实缴累计差额</label>
	            <div class="layui-input-inline">
	              <input type="text" id="persCompleDiffe4addup" name="persCompleDiffe4addup" lay-verify="required|persCompleDiffe4addup" autocomplete="off" placeholder="本年个人实缴累计差额" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">导入编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="btimpNo" name="btimpNo"  autocomplete="off" placeholder="导入编号" class="layui-input">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chSocialInfo-add-submit" id="LAY-chSocialInfo-add-submit">立即提交</button>
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
    chSocialInfo: 'chSocialInfo'
  }).use(['index','form','chSocialInfo'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chSocialInfo-add-form');
  });
  </script>
</body>
</html>