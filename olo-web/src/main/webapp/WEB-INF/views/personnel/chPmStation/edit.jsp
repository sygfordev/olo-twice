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
          <form class="layui-form" action="" lay-filter="chPmStation-edit-form">
          <!-- 隐藏属性 -->
          <input type="hidden" id="id" name="id" value="${model.id}" class="layui-input">
		  <input type="hidden" id="oper" name="oper" value="${oper}" class="layui-input">
		  <input type="hidden" id="workerId" name="workerId" value="${wkId}" class="layui-input">
		  <input type="hidden" id="basePath" name="basePath" value="<%=basePath%>" class="layui-input">
		  
		  <input type="hidden" id="laborRelCn" name="laborRelCn" value="${model.laborRelCn}">
		  <input type="hidden" id="stationSeqCn" name="stationSeqCn" value="${model.stationSeqCn}">
		  <input type="hidden" id="identityCn" name="identityCn" value="${model.identityCn}">
		  <input type="hidden" id="wkModalityCn" name="wkModalityCn" value="${model.wkModalityCn}">
		  <input type="hidden" id="wkTypeCn" name="wkTypeCn"  value="${model.wkTypeCn}">
		  <input type="hidden" id="stationSituCn" name="stationSituCn" value="${model.stationSituCn}">
		  <input type="hidden" id="stationTypeCn" name="stationTypeCn" value="${model.stationTypeCn}">
		  <!-- <input type="hidden" id="inMajorNowCn" name="inMajorNowCn"> -->
		  <input type="hidden" id="stationStatusCn" name="stationStatusCn" value="${model.stationStatusCn}">
		  <input type="hidden" id="workAreaCn" name="workAreaCn" value="${model.workAreaCn}">
		  <input type="hidden" id="hosBranchCn" name="hosBranchCn" value="${model.hosBranchCn}">
		  <input type="hidden" id="hosDepart1levelCn" name="hosDepart1levelCn" value="${model.hosDepart1levelCn}">
		  <input type="hidden" id="hosDepart2levelCn" name="hosDepart2levelCn" value="${model.hosDepart2levelCn}">
		  
		  
		  <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">人员编号</label>
              <div class="layui-input-inline">
                <input type="text" id="wagesId" name="wagesId" value="${model.wagesId}" ${oper eq 'detail'?'disabled':''} lay-verify="required|wagesId" autocomplete="off" placeholder="人员编号（工资出账）" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">个人身份</label>
              <div class="layui-input-inline">
                <select id="identity" name="identity" lay-verify="required|identity" ${oper eq 'detail'?'disabled':''} lay-filter="identity">
                	<option></option>
                  <c:forEach var="item" items="${identityList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.identity?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">用工方式</label>
              <div class="layui-input-inline">
                <select id="wkModality" name="wkModality" lay-verify="required|wkModality" ${oper eq 'detail'?'disabled':''} lay-filter="wkModality">
                	<option></option>
                  <c:forEach var="item" items="${wkModalityList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.wkModality?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">用工类型</label>
              <div class="layui-input-inline">
                <select id="wkType" name="wkType" lay-verify="required|wkType" ${oper eq 'detail'?'disabled':''} lay-filter="wkType">
                	<option></option>
                  <c:forEach var="item" items="${wkTypeList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.wkType?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">劳动关系</label>
              <div class="layui-input-inline">
                <select id="laborRelNo" name="laborRelNo" lay-verify="required|laborRelNo" lay-filter="laborRelNo" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${laborRelList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.laborRelNo?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">在岗情况</label>
              <div class="layui-input-inline">
                <select id="stationSitu" name="stationSitu" lay-verify="required|stationSitu" lay-filter="stationSitu" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${stationSituList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.stationSitu?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
          
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">岗位类型</label>
              <div class="layui-input-inline">
                <select id="stationType" name="stationType" lay-verify="required|stationType" lay-filter="stationType" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${stationTypeList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.stationType?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">岗位状态</label>
              <div class="layui-input-inline">
                <select id="stationStatus" name="stationStatus" lay-verify="required|stationStatus" lay-filter="stationStatus" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${stationStatusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.stationStatus?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">岗位序列</label>
              <div class="layui-input-inline">
                <select id="stationSeqNo" name="stationSeqNo" lay-verify="required|stationSeqNo" lay-filter="stationSeqNo" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${stationSeqList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.stationSeqNo?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">是否专业技术人员</label>
              <div class="layui-input-inline">
                <select id="isMajorPerson" name="isMajorPerson" lay-verify="required|isMajorPerson" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${YNList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.isMajorPerson?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">现从事专业</label>
              <div class="layui-input-inline">
                <%-- <select id="inMajorNow" name="inMajorNow" lay-filter="inMajorNow" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${inMajorList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.inMajorNow?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select> --%>
                
                <input type="text" id="inMajorNowCn" name="inMajorNowCn"  autocomplete="off" value="${model.inMajorNowCn}" lay-verify="inMajorNowCn" placeholder="现从事专业" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">工作地域</label>
              <div class="layui-input-inline">
                <select id="workArea" name="workArea" lay-filter="workArea" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${workAreaList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.workArea?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">医院支部</label>
              <div class="layui-input-inline">
                <select id="hosBranch" name="hosBranch" lay-filter="hosBranch" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${hbranchs}" varStatus="status">
                    <option value="${item.hbhNo}" ${item.hbhNo eq model.hosBranch?'selected':''}>${item.hbhName}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">一级科室</label>
              <div class="layui-input-inline">
                <select id="hosDepart1level" name="hosDepart1level" lay-filter="hosDepart1level" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
               	  <c:forEach var="item" items="${hdepLevel1}" varStatus="status">
                    <option value="${item.hdpNo}" ${item.hdpNo eq model.hosDepart1level?'selected':''}>${item.hdpName}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">二级科室</label>
              <div class="layui-input-inline">
                <select id="hosDepart2level" name="hosDepart2level" lay-filter="hosDepart2level" ${oper eq 'detail'?'disabled':''}>
                	<option></option>
                  <c:forEach var="item" items="${hdepLevel2}" varStatus="status">
                    <option value="${item.hsdNo}" ${item.hsdNo eq model.hosDepart2level?'selected':''}>${item.hsdName}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          
		  <div class="layui-form-item">
          	<div class="layui-inline">
	            <label class="layui-form-label">现家庭详细住址</label>
	            <div class="layui-input-inline">
	              <input type="text" id="homeAddress" name="homeAddress" value="${model.homeAddress}" ${oper eq 'detail'?'disabled':''} autocomplete="off" placeholder="详细地址" class="layui-input" style="width: 838px;">
	            </div>
            </div>
          </div>
          <div class="layui-form-item">
            <div class="layui-inline">
              <label class="layui-form-label">联系电话</label>
              <div class="layui-input-inline">
                <input type="text" id="telphoneNo" name="telphoneNo" value="${model.telphoneNo}" ${oper eq 'detail'?'disabled':''} lay-verify="telphoneNo" autocomplete="off" placeholder="联系电话" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
              <label class="layui-form-label">邮箱</label>
              <div class="layui-input-inline">
                <input type="text" id="mailBox" name="mailBox" value="${model.mailBox}" ${oper eq 'detail'?'disabled':''} lay-verify="mailBox" autocomplete="off" placeholder="邮箱" class="layui-input">
              </div>
            </div>
          </div>
          <%-- <div class="layui-form-item">
          	<div class="layui-inline">
              <label class="layui-form-label">状态</label>
              <div class="layui-input-inline">
                <select id="status" name="status" lay-verify="required|status" ${oper eq 'detail'?'disabled':''}>
                  <c:forEach var="item" items="${statusList}" varStatus="status">
                    <option value="${item.key}" ${item.key eq model.status?'selected':''}>${item.value}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div> --%>
          
          <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea" ${oper eq 'detail'?'disabled':''}>${model.remark}</textarea>
            </div>
          </div>        
          <div class="layui-form-item layui-layout-admin ${oper eq 'detail'?'layui-hide':''}">
            <div class="layui-input-block">
              <div class="layui-footer" style="left: 0;">
                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmStation-edit-submit" id="LAY-chPmStation-edit-submit">修改</button>
                <!-- <button type="reset" class="layui-btn layui-btn-primary">重置</button> -->
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
    chPmStation: 'chPmStation'
  }).use(['index','form','laydate','chPmStation'], function(){
    var $ = layui.$,
    laydate = layui.laydate,
    form = layui.form;
    
    form.render(null, 'chPmStation-edit-form');
  });
  </script>
</body>
</html>