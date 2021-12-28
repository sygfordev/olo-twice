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
          <form class="layui-form" action="" lay-filter="chSaPayslip-add-form">
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职工编号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="workerId" name="workerId" lay-verify="required|workerId" autocomplete="off" placeholder="职工编号" class="layui-input">
	            </div>
            </div>
          </div>
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
	            <label class="layui-form-label">身份证号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="cardNo" name="cardNo" lay-verify="required|cardNo" autocomplete="off" placeholder="身份证号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">银行卡号</label>
	            <div class="layui-input-inline">
	              <input type="text" id="bankCardNo" name="bankCardNo" lay-verify="required|bankCardNo" autocomplete="off" placeholder="银行卡号" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">用工形式</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkModality" name="wkModality" lay-verify="required|wkModality" autocomplete="off" placeholder="用工形式" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">用工形式-名称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wkModalityCn" name="wkModalityCn" lay-verify="required|wkModalityCn" autocomplete="off" placeholder="用工形式-名称" class="layui-input">
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
	            <label class="layui-form-label">职务</label>
	            <div class="layui-input-inline">
	              <input type="text" id="posit" name="posit" lay-verify="required|posit" autocomplete="off" placeholder="职务" class="layui-input">
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
	            <label class="layui-form-label">职称</label>
	            <div class="layui-input-inline">
	              <input type="text" id="title" name="title" lay-verify="required|title" autocomplete="off" placeholder="职称" class="layui-input">
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
	            <label class="layui-form-label">医院支部</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosBranch" name="hosBranch" lay-verify="required|hosBranch" autocomplete="off" placeholder="医院支部" class="layui-input">
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
	            <label class="layui-form-label">医院一级科室</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosDepart1level" name="hosDepart1level" lay-verify="required|hosDepart1level" autocomplete="off" placeholder="医院一级科室" class="layui-input">
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
	            <label class="layui-form-label">医院二级科室</label>
	            <div class="layui-input-inline">
	              <input type="text" id="hosDepart2level" name="hosDepart2level" lay-verify="required|hosDepart2level" autocomplete="off" placeholder="医院二级科室" class="layui-input">
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
	            <label class="layui-form-label">标准工数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="manwkStandard" name="manwkStandard" lay-verify="required|manwkStandard" autocomplete="off" placeholder="标准工数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">出勤工数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="manwkAttend" name="manwkAttend" lay-verify="required|manwkAttend" autocomplete="off" placeholder="出勤工数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">矿工工数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="manwkMiner" name="manwkMiner" lay-verify="required|manwkMiner" autocomplete="off" placeholder="矿工工数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">病假工数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="manwkSick" name="manwkSick" lay-verify="required|manwkSick" autocomplete="off" placeholder="病假工数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">产假工数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="manwkMaternity" name="manwkMaternity" lay-verify="required|manwkMaternity" autocomplete="off" placeholder="产假工数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">事假工数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="manwkPrivpassion" name="manwkPrivpassion" lay-verify="required|manwkPrivpassion" autocomplete="off" placeholder="事假工数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">加班工数</label>
	            <div class="layui-input-inline">
	              <input type="text" id="manwkOvertime" name="manwkOvertime" lay-verify="required|manwkOvertime" autocomplete="off" placeholder="加班工数" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">岗位工资标准</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageStandard4posit" name="wageStandard4posit" lay-verify="required|wageStandard4posit" autocomplete="off" placeholder="岗位工资标准" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">岗位日工资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageDay4posit" name="wageDay4posit" lay-verify="required|wageDay4posit" autocomplete="off" placeholder="岗位日工资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">出勤工资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageAttend" name="wageAttend" lay-verify="required|wageAttend" autocomplete="off" placeholder="出勤工资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">病假工资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageSick" name="wageSick" lay-verify="required|wageSick" autocomplete="off" placeholder="病假工资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">夜班费用</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageNightShift" name="wageNightShift" lay-verify="required|wageNightShift" autocomplete="off" placeholder="夜班费用" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">加班工资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageOvertime" name="wageOvertime" lay-verify="required|wageOvertime" autocomplete="off" placeholder="加班工资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">年功工资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageYearg" name="wageYearg" lay-verify="required|wageYearg" autocomplete="off" placeholder="年功工资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">卫生津贴</label>
	            <div class="layui-input-inline">
	              <input type="text" id="allowanceHygiene" name="allowanceHygiene" lay-verify="required|allowanceHygiene" autocomplete="off" placeholder="卫生津贴" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">纠错工资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageErrorCorrent" name="wageErrorCorrent" lay-verify="required|wageErrorCorrent" autocomplete="off" placeholder="纠错工资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">电补</label>
	            <div class="layui-input-inline">
	              <input type="text" id="supp4tel" name="supp4tel" lay-verify="required|supp4tel" autocomplete="off" placeholder="电补" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">其他</label>
	            <div class="layui-input-inline">
	              <input type="text" id="supp4oth" name="supp4oth" lay-verify="required|supp4oth" autocomplete="off" placeholder="其他" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">工资合计</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageTotal" name="wageTotal" lay-verify="required|wageTotal" autocomplete="off" placeholder="工资合计" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">经合办绩效</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxComb" name="wageJxComb" lay-verify="required|wageJxComb" autocomplete="off" placeholder="经合办绩效" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">经合办绩效1</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxComb1" name="wageJxComb1" lay-verify="required|wageJxComb1" autocomplete="off" placeholder="经合办绩效1" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">经合办绩效2</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxComb2" name="wageJxComb2" lay-verify="required|wageJxComb2" autocomplete="off" placeholder="经合办绩效2" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">经合办绩效3</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxComb3" name="wageJxComb3" lay-verify="required|wageJxComb3" autocomplete="off" placeholder="经合办绩效3" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">经合办绩效4</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxComb4" name="wageJxComb4" lay-verify="required|wageJxComb4" autocomplete="off" placeholder="经合办绩效4" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">基层分院绩效</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxSubstrate" name="wageJxSubstrate" lay-verify="required|wageJxSubstrate" autocomplete="off" placeholder="基层分院绩效" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">空调班绩效</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxAircon" name="wageJxAircon" lay-verify="required|wageJxAircon" autocomplete="off" placeholder="空调班绩效" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">放射科介入</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxRadiology" name="wageJxRadiology" lay-verify="required|wageJxRadiology" autocomplete="off" placeholder="放射科介入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">手术室介入</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxOperatroom" name="wageJxOperatroom" lay-verify="required|wageJxOperatroom" autocomplete="off" placeholder="手术室介入" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">年薪制人员绩效</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxYearlySalary" name="wageJxYearlySalary" lay-verify="required|wageJxYearlySalary" autocomplete="off" placeholder="年薪制人员绩效" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">分级诊疗办公室下乡补助</label>
	            <div class="layui-input-inline">
	              <input type="text" id="supp4toCountryside" name="supp4toCountryside" lay-verify="required|supp4toCountryside" autocomplete="off" placeholder="分级诊疗办公室下乡补助" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励1</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward1" name="reward1" lay-verify="required|reward1" autocomplete="off" placeholder="奖励1" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励2</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward2" name="reward2" lay-verify="required|reward2" autocomplete="off" placeholder="奖励2" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励3</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward3" name="reward3" lay-verify="required|reward3" autocomplete="off" placeholder="奖励3" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励4</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward4" name="reward4" lay-verify="required|reward4" autocomplete="off" placeholder="奖励4" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励5</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward5" name="reward5" lay-verify="required|reward5" autocomplete="off" placeholder="奖励5" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励6</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward6" name="reward6" lay-verify="required|reward6" autocomplete="off" placeholder="奖励6" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励7</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward7" name="reward7" lay-verify="required|reward7" autocomplete="off" placeholder="奖励7" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励8</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward8" name="reward8" lay-verify="required|reward8" autocomplete="off" placeholder="奖励8" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励9</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward9" name="reward9" lay-verify="required|reward9" autocomplete="off" placeholder="奖励9" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励10</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward10" name="reward10" lay-verify="required|reward10" autocomplete="off" placeholder="奖励10" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励11</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward11" name="reward11" lay-verify="required|reward11" autocomplete="off" placeholder="奖励11" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励12</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward12" name="reward12" lay-verify="required|reward12" autocomplete="off" placeholder="奖励12" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励13</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward13" name="reward13" lay-verify="required|reward13" autocomplete="off" placeholder="奖励13" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励14</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward14" name="reward14" lay-verify="required|reward14" autocomplete="off" placeholder="奖励14" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">奖励15</label>
	            <div class="layui-input-inline">
	              <input type="text" id="reward15" name="reward15" lay-verify="required|reward15" autocomplete="off" placeholder="奖励15" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">绩效工资合计</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wageJxTotal" name="wageJxTotal" lay-verify="required|wageJxTotal" autocomplete="off" placeholder="绩效工资合计" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">应发工资合计</label>
	            <div class="layui-input-inline">
	              <input type="text" id="wagePayableTotal" name="wagePayableTotal" lay-verify="required|wagePayableTotal" autocomplete="off" placeholder="应发工资合计" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">养老保险</label>
	            <div class="layui-input-inline">
	              <input type="text" id="bxPension" name="bxPension" lay-verify="required|bxPension" autocomplete="off" placeholder="养老保险" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">医疗保险</label>
	            <div class="layui-input-inline">
	              <input type="text" id="bxMedical" name="bxMedical" lay-verify="required|bxMedical" autocomplete="off" placeholder="医疗保险" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">失业保险</label>
	            <div class="layui-input-inline">
	              <input type="text" id="bxUnemploy" name="bxUnemploy" lay-verify="required|bxUnemploy" autocomplete="off" placeholder="失业保险" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">大病保险</label>
	            <div class="layui-input-inline">
	              <input type="text" id="bxSeriousIllness" name="bxSeriousIllness" lay-verify="required|bxSeriousIllness" autocomplete="off" placeholder="大病保险" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">住房公积金</label>
	            <div class="layui-input-inline">
	              <input type="text" id="bxHousfund" name="bxHousfund" lay-verify="required|bxHousfund" autocomplete="off" placeholder="住房公积金" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">年企业金</label>
	            <div class="layui-input-inline">
	              <input type="text" id="bxAnnualCorpGold" name="bxAnnualCorpGold" lay-verify="required|bxAnnualCorpGold" autocomplete="off" placeholder="年企业金" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">保险合计</label>
	            <div class="layui-input-inline">
	              <input type="text" id="bxTotal" name="bxTotal" lay-verify="required|bxTotal" autocomplete="off" placeholder="保险合计" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">水电费</label>
	            <div class="layui-input-inline">
	              <input type="text" id="cutWater2elect" name="cutWater2elect" lay-verify="required|cutWater2elect" autocomplete="off" placeholder="水电费" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">卫生费</label>
	            <div class="layui-input-inline">
	              <input type="text" id="cutHygiene" name="cutHygiene" lay-verify="required|cutHygiene" autocomplete="off" placeholder="卫生费" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">职工欠款</label>
	            <div class="layui-input-inline">
	              <input type="text" id="cutArrears" name="cutArrears" lay-verify="required|cutArrears" autocomplete="off" placeholder="职工欠款" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">扣款合计</label>
	            <div class="layui-input-inline">
	              <input type="text" id="cutTotal" name="cutTotal" lay-verify="required|cutTotal" autocomplete="off" placeholder="扣款合计" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">计税工资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="taxableWage" name="taxableWage" lay-verify="required|taxableWage" autocomplete="off" placeholder="计税工资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">个税</label>
	            <div class="layui-input-inline">
	              <input type="text" id="taxIncomePersonal" name="taxIncomePersonal" lay-verify="required|taxIncomePersonal" autocomplete="off" placeholder="个税" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">实发工资</label>
	            <div class="layui-input-inline">
	              <input type="text" id="netSalary" name="netSalary" lay-verify="required|netSalary" autocomplete="off" placeholder="实发工资" class="layui-input">
	            </div>
            </div>
          </div>
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">目标年月（工资对账年月）</label>
	            <div class="layui-input-inline">
	              <input type="text" id="netTargetYearmonth" name="netTargetYearmonth" lay-verify="required|netTargetYearmonth" autocomplete="off" placeholder="目标年月（工资对账年月）" class="layui-input">
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
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chSaPayslip-add-submit" id="LAY-chSaPayslip-add-submit">立即提交</button>
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
    chSaPayslip: 'chSaPayslip'
  }).use(['index','form','chSaPayslip'], function(){
    var $ = layui.$,
    form = layui.form;
    
    form.render(null, 'chSaPayslip-add-form');
  });
  </script>
</body>
</html>