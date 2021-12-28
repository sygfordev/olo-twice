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
      <div class="layui-form layui-card-header layuiadmin-card-header-auto">
        <div class="layui-form-item">
        		  <div class="layui-inline">
	        <label class="layui-form-label">自增主键</label>
	        <div class="layui-input-block">
	          <input type="text" name="id" placeholder="请输入自增主键" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">职工编号</label>
	        <div class="layui-input-block">
	          <input type="text" name="workerId" placeholder="请输入职工编号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">人员编号（工资出账）</label>
	        <div class="layui-input-block">
	          <input type="text" name="wagesId" placeholder="请输入人员编号（工资出账）" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">个人身份  1:干部 2:工人</label>
	        <div class="layui-input-block">
	          <input type="text" name="identity" placeholder="请输入个人身份  1:干部 2:工人" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">用工形式</label>
	        <div class="layui-input-block">
	          <input type="text" name="wkModality" placeholder="请输入用工形式" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">用工类型</label>
	        <div class="layui-input-block">
	          <input type="text" name="wkType" placeholder="请输入用工类型" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">劳动关系编号</label>
	        <div class="layui-input-block">
	          <input type="text" name="laborRelNo" placeholder="请输入劳动关系编号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">劳动关系名称</label>
	        <div class="layui-input-block">
	          <input type="text" name="laborRelCn" placeholder="请输入劳动关系名称" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">在岗情况</label>
	        <div class="layui-input-block">
	          <input type="text" name="stationSitu" placeholder="请输入在岗情况" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">岗位类型</label>
	        <div class="layui-input-block">
	          <input type="text" name="stationType" placeholder="请输入岗位类型" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">岗位状态</label>
	        <div class="layui-input-block">
	          <input type="text" name="stationStatus" placeholder="请输入岗位状态" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">岗位序列编号</label>
	        <div class="layui-input-block">
	          <input type="text" name="stationSeqNo" placeholder="请输入岗位序列编号" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">岗位序列名称</label>
	        <div class="layui-input-block">
	          <input type="text" name="stationSeqCn" placeholder="请输入岗位序列名称" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">是否专业人员</label>
	        <div class="layui-input-block">
	          <input type="text" name="isMajorPerson" placeholder="请输入是否专业人员" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现从事专业</label>
	        <div class="layui-input-block">
	          <input type="text" name="inMajorNow" placeholder="请输入现从事专业" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">工作地域</label>
	        <div class="layui-input-block">
	          <input type="text" name="workArea" placeholder="请输入工作地域" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">医院支部</label>
	        <div class="layui-input-block">
	          <input type="text" name="hosBranch" placeholder="请输入医院支部" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">医院一级科室</label>
	        <div class="layui-input-block">
	          <input type="text" name="hosDepart1level" placeholder="请输入医院一级科室" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">医院二级科室</label>
	        <div class="layui-input-block">
	          <input type="text" name="hosDepart2level" placeholder="请输入医院二级科室" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现家庭详细住址</label>
	        <div class="layui-input-block">
	          <input type="text" name="homeAddress" placeholder="请输入现家庭详细住址" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">联系电话</label>
	        <div class="layui-input-block">
	          <input type="text" name="telphoneNo" placeholder="请输入联系电话" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">邮箱</label>
	        <div class="layui-input-block">
	          <input type="text" name="mailBox" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
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
          </div>
          
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chPmStation-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmStation:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmStation:batchdel">
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmStation-manage" lay-filter="LAY-chPmStation-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmStation-manager-operator">
          <shiro:hasPermission name="chPmStation:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmStation:delete">
			<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del"><i class="layui-icon layui-icon-delete"></i>删除</a>
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
    chPmStation: 'chPmStation'
  }).use(['index', 'chPmStation', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>