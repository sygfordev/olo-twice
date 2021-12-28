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
	        <label class="layui-form-label">职工姓名</label>
	        <div class="layui-input-block">
	          <input type="text" name="name" placeholder="请输入职工姓名" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">职工性别  0：女  1：男  -1：未知</label>
	        <div class="layui-input-block">
	          <input type="text" name="sex" placeholder="请输入职工性别  0：女  1：男  -1：未知" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">职工年龄</label>
	        <div class="layui-input-block">
	          <input type="text" name="age" placeholder="请输入职工年龄" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">卡类型（身份）</label>
	        <div class="layui-input-block">
	          <input type="text" name="cardType" placeholder="请输入卡类型（身份）" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">卡号（身份）</label>
	        <div class="layui-input-block">
	          <input type="text" name="cardNo" placeholder="请输入卡号（身份）" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">出生日期</label>
	        <div class="layui-input-block">
	          <input type="text" name="birthDay" placeholder="请输入出生日期" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">民族</label>
	        <div class="layui-input-block">
	          <input type="text" name="nation" placeholder="请输入民族" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">政治面貌</label>
	        <div class="layui-input-block">
	          <input type="text" name="politics" placeholder="请输入政治面貌" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">政治面貌加入时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="politicsInTime" placeholder="请输入政治面貌加入时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">首次参加工作时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="firstWorkTime" placeholder="请输入首次参加工作时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">工龄</label>
	        <div class="layui-input-block">
	          <input type="text" name="workedYear" placeholder="请输入工龄" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">进入本单位时间</label>
	        <div class="layui-input-block">
	          <input type="text" name="intoLocalCompTime" placeholder="请输入进入本单位时间" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">籍贯-省份</label>
	        <div class="layui-input-block">
	          <input type="text" name="nativePlaceProv" placeholder="请输入籍贯-省份" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">籍贯-城市</label>
	        <div class="layui-input-block">
	          <input type="text" name="nativePlaceCity" placeholder="请输入籍贯-城市" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">籍贯-区县</label>
	        <div class="layui-input-block">
	          <input type="text" name="nativePlaceArea" placeholder="请输入籍贯-区县" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">户口性质</label>
	        <div class="layui-input-block">
	          <input type="text" name="residenceType" placeholder="请输入户口性质" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">户口所在地-省份</label>
	        <div class="layui-input-block">
	          <input type="text" name="residenceBirthlandProv" placeholder="请输入户口所在地-省份" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">户口所在地-省份</label>
	        <div class="layui-input-block">
	          <input type="text" name="residenceBirthlandCity" placeholder="请输入户口所在地-省份" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">户口所在地-区县</label>
	        <div class="layui-input-block">
	          <input type="text" name="residenceBirthlandArea" placeholder="请输入户口所在地-区县" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现家庭详细住址-省份</label>
	        <div class="layui-input-block">
	          <input type="text" name="homeProv" placeholder="请输入现家庭详细住址-省份" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现家庭详细住址-城市</label>
	        <div class="layui-input-block">
	          <input type="text" name="homeCity" placeholder="请输入现家庭详细住址-城市" autocomplete="off" class="layui-input">
	        </div>
          </div>
		  <div class="layui-inline">
	        <label class="layui-form-label">现家庭详细住址-详细地址</label>
	        <div class="layui-input-block">
	          <input type="text" name="homeAddr" placeholder="请输入现家庭详细住址-详细地址" autocomplete="off" class="layui-input">
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
	        <label class="layui-form-label">调资类别</label>
	        <div class="layui-input-block">
	          <input type="text" name="salaryAdjustType" placeholder="请输入调资类别" autocomplete="off" class="layui-input">
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
            <button class="layui-btn layuiadmin-btn-admin" lay-submit lay-filter="LAY-chPmWorker-search" data-type="search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
      </div>
      
      <div class="layui-card-body">
      
      	<!-- 数据表格区域头部操作按钮 -->
        <div style="padding-bottom: 10px;">
          <div class="layui-btn-group">
          	<shiro:hasPermission name="chPmWorker:add">
          	<button class="layui-btn layuiadmin-btn-admin" data-type="add">添加</button>
          	</shiro:hasPermission>
          	<shiro:hasPermission name="chPmWorker:batchdel">
            <button class="layui-btn layuiadmin-btn-admin" data-type="batchdel">删除</button>
            </shiro:hasPermission>
          </div>
        </div>
        
        <!-- 数据表格 -->
        <table id="LAY-chPmWorker-manage" lay-filter="LAY-chPmWorker-manage"></table>
        
        <!-- 特殊模板数据处理 -->
        <script type="text/html" id="statusTpl">
          {{#  if(d.status == "0"){ }}
            <button class="layui-btn layui-btn-xs">正常</button>
          {{#  } else { }}
            <button class="layui-btn layui-btn-primary layui-btn-xs">禁用</button>
          {{#  } }}
        </script>
      	
        <!-- 操作模块 -->
        <script type="text/html" id="table-chPmWorker-manager-operator">
          <shiro:hasPermission name="chPmWorker:edit">
			<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit"><i class="layui-icon layui-icon-edit"></i>编辑</a>
		  </shiro:hasPermission>
		  <shiro:hasPermission name="chPmWorker:delete">
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
    chPmWorker: 'chPmWorker'
  }).use(['index', 'chPmWorker', 'table','laypage'], function(){
  		var $ = layui.$;
  	});
  </script>
</body>
</html>