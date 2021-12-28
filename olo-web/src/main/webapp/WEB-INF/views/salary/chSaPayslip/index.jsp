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
  <style>
  	.layui-table-main{
  		min-height:300px;
  	}
  </style>
</head>
<body>
  <div class="layui-fluid">
  	<!-- 隐藏属性 -->
   	<input id="basePath" name="basePath" type="hidden" value="<%=basePath%>">
   	<input id="branchList" name="branchList" type="hidden" value='${branchList}'>
   	<input id="depart1List" name="depart1List" type="hidden" value='${depart1List}'>
   	<input id="depart2List" name="depart2List" type="hidden" value='${depart2List}'>
   	<input id="modalityList" name="modalityList" type="hidden" value='${modalityList}'>
   	
   	<input id="positList" name="positList" type="hidden" value='${positList}'>
   	<input id="titleList" name="titleList" type="hidden" value='${titleList}'>
   	<input id="departClassList" name="departClassList" type="hidden" value='${departClassList}'>
   	<input id="departClassPopList" name="departClassPopList" type="hidden" value='${departClassPopList}'>
   	<input id="stationList" name="stationList" type="hidden" value='${stationList}'>
   	<input id="stationTypeList" name="stationTypeList" type="hidden" value='${stationTypeList}'>
   	<input id="stationStatusList" name="stationStatusList" type="hidden" value='${stationStatusList}'>
   	<input id="stationSeqList" name="stationSeqList" type="hidden" value='${stationSeqList}'>
   	<input id="titleClassList" name="titleClassList" type="hidden" value='${titleClassList}'>
   	<input id="skillsLevelList" name="skillsLevelList" type="hidden" value='${skillsLevelList}'>
   	<input id="eduLev4NowList" name="eduLev4NowList" type="hidden" value='${eduLev4NowList}'>
   	<input id="saSumProjectList" name="saSumProjectList" type="hidden" value='${saSumProjectList}'>
   	<input id="rptWkDepClassList" name="rptWkDepClassList" type="hidden" value='${rptWkDepClassList}'>
   	
    <div class="layui-card">
      <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="payIndex">
        <div class="layui-form-item">
		  <div class="layui-inline">
	        <label class="layui-form-label">姓名</label>
	        <div class="layui-input-block">
	          <input type="text" name="name" placeholder="请输入职工姓名" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">身份证号</label>
	        <div class="layui-input-block">
	          <input type="text" name="cardNo" placeholder="请输入身份证号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		</div>
		<div class="layui-collapse" lay-filter="test">
		<div class="layui-form-item layui-colla-item">
		  <h2 class="layui-colla-title" style="text-align:right;padding-right:30px;">更多</h2>
		  <div class="layui-colla-content">
		  <div class="layui-inline">
	        <label class="layui-form-label">职务</label>
	        <div class="layui-input-block" id="positCns"></div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">职称</label>
	        <div class="layui-input-block" id="titleCns"></div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">职称序列</label>
	        <div class="layui-input-block" id="titleClassCns"></div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">支部</label>
	        <div class="layui-input-block" id="hosBranchCns"></div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">一级科室</label>
	        <div class="layui-input-block" id="hosDepart1levelCns"></div>
	        <%-- <div class="layui-input-block">
	          <input id="hosDepart1levelCn" name="hosDepart1levelCn" class="layui-input" style="position:absolute;z-index:2;width:85%;"  value="" onkeyup="search('hosDepart1levelCn','depart1')" >
	          <select id="depart1" name="depart1" lay-filter="depart1" class="layui-select" autocomplete="off" lay-search>
                  <option value=""></option>
                  <c:forEach var="item" items="${depart1List}" varStatus="status">
                    <option value="${item.key}">${item.value}</option>
                  </c:forEach>
              </select>
	        </div> --%>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">二级科室</label>
	        <div class="layui-input-block" id="hosDepart2levelCns"></div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">人员类别</label>
	        <div class="layui-input-block" id="wkModalityCns"></div>
	        <%-- <div class="layui-input-block">
	          <input id="wkModalityCn" name="wkModalityCn" class="layui-input" style="position:absolute;z-index:2;width:85%;"  value="" onkeyup="search('wkModalityCn','peronType')" >
	          <select id ="peronType" name="peronType" lay-filter="peronType" class="layui-select" autocomplete="off" lay-search>
              	<option value=""></option>
              	<c:forEach var="item" items="${modalityList}" varStatus="status">
              		<option value="${item.key}">${item.value}</option>
              	</c:forEach>
              </select>
	        </div> --%>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">部门类别</label>
	        <div class="layui-input-block" id="departClassCns"></div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">部门类别属性</label>
	        <div class="layui-input-block" id="departClassPops"></div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">岗位</label>
	        <div class="layui-input-block" id="stationCns"></div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">岗位类型</label>
	        <div class="layui-input-block" id="stationTypeCns"></div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">岗位状态</label>
	        <div class="layui-input-block" id="stationStatusCns"></div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">岗位序列</label>
	        <div class="layui-input-block" id="stationSeqCns"></div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">现学历</label>
	        <div class="layui-input-block" id="eduLev4nowCns"></div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">技能等级级别</label>
	        <div class="layui-input-block" id="skillsLevelCns"></div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">工资汇总表项目</label>
	        <div class="layui-input-block" id="saSumProjects"></div>
          </div>
          <div class="layui-inline">
	        <label class="layui-form-label">报工系统部门分类</label>
	        <div class="layui-input-block" id="rptWkDepClasss"></div>
          </div>
		</div>
		</div>
		</div>
		<div class="layui-form-item">
		  <div class="layui-inline">
	        <!-- <label class="layui-form-label">工资月份</label>
	        <div class="layui-input-block">
	          <input type="text" id="netTargetYearmonth" name="netTargetYearmonth" placeholder="请选择工资月份" autocomplete="off" class="layui-input time">
	        </div> -->
	          <!-- <label class="layui-form-label">月份范围</label>
		      <div class="layui-inline" id="test6">
		        <div class="layui-input-inline">
		          <input type="text" autocomplete="off" id="startMonth" name="startMonth" class="layui-input" placeholder="开始月份">
		        </div>
		        <div class="layui-form-mid">-</div>
		        <div class="layui-input-inline">
		          <input type="text" autocomplete="off" id="endMonth" name="endMonth" class="layui-input" placeholder="结束月份">
		        </div>
		      </div> -->
		      <label class="layui-form-label">起始年月</label>
		      <div class="layui-input-inline">
		        <input class="layui-input" type="text" name="startMonth" id="startMonth" readonly="" autocomplete="off" placeholder="开始月份">
		      </div>
		      <label class="layui-form-label">结束年月</label>
		      <div class="layui-input-inline">
		        <input class="layui-input" type="text" name="endMonth" id="endMonth" readonly="" autocomplete="off" placeholder="结束月份">
		      </div>
          </div>
		  
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chSaPayslip-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chSaPayslip:revoke">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="revoke">批次撤销</button>
          	</shiro:hasPermission>
          </div>
        </div>
        
        <div id="toolbarDemo"></div>
        <!-- 数据表格 -->
        <table id="LAY-chSaPayslip-manage" lay-filter="LAY-chSaPayslip-manage" ></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chSaPayslip-manager-operator">
          <shiro:hasPermission name="chSaPayslip:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chSaPayslip:delete">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chSaPayslip:details">
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
    chSaPayslip: 'salary/chSaPayslip'
  }).use(['index', 'chSaPayslip', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>