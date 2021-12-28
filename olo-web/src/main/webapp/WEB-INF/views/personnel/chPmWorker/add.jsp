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
      <!-- <div class="layui-card-header">职工新增</div> -->
      <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" action="" lay-filter="chPmWorker-add-form">
        	<!-- 隐藏属性 -->
        	<input type="hidden" id="id" name="id" value=""/>
        	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>"/>
        	
        	<input type="hidden" id="nationCn" name="nationCn" value=""/>
        	<input type="hidden" id="politicsCn" name="politicsCn" value=""/>
        	<input type="hidden" id="nativePlaceProvCn" name="nativePlaceProvCn" value=""/>
        	<input type="hidden" id="nativePlaceCityCn" name="nativePlaceCityCn" value=""/>
        	<input type="hidden" id="nativePlaceAreaCn" name="nativePlaceAreaCn" value=""/>
        	<input type="hidden" id="residenceTypeCn" name="residenceTypeCn" value=""/>
        	<input type="hidden" id="residenceBirthlandProvCn" name="residenceBirthlandProvCn" value=""/>
        	<input type="hidden" id="residenceBirthlandCityCn" name="residenceBirthlandCityCn" value=""/>
        	<input type="hidden" id="residenceBirthlandAreaCn" name="residenceBirthlandAreaCn" value=""/>
        	<input type="hidden" id="homeProvCn" name="homeProvCn" value=""/>
        	<input type="hidden" id="homeCityCn" name="homeCityCn" value=""/>
        	<input type="hidden" id="salaryAdjustTypeCn" name="salaryAdjustTypeCn" value=""/>
        	
        	
        	<div class="layui-form-item">
	            <div class="layui-inline">
	              <label class="layui-form-label">姓名</label>
	              <div class="layui-input-inline">
	                <input type="text" id="name" name="name" lay-verify="required|name" autocomplete="off" class="layui-input">
	              </div>
	            </div>
	            <div class="layui-inline">
	              <label class="layui-form-label">身份证号</label>
	              <div class="layui-input-inline">
	                <input type="text" id="cardNo" name="cardNo" lay-verify="required|cardNo" autocomplete="off" class="layui-input">
	              </div>
	            </div>
	            <div class="layui-inline">
	              <label class="layui-form-label">性别</label>
	              <div class="layui-input-inline">
	                <select id="sex" name="sex" lay-verify="required|sex">
	                	<option></option>
	                  <c:forEach var="item" items="${sexList}" varStatus="status">
	                    <option value="${item.key}">${item.value}</option>
	                  </c:forEach>
	                </select>
	              </div>
	            </div>
          	</div>
          	<div class="layui-form-item">
			  	<div class="layui-inline">
		            <label class="layui-form-label">民族</label>
		            <div class="layui-input-inline">
		              <select id="nation" name="nation" lay-verify="required|nation" lay-filter="nation">
		              	  <option></option>
		                  <c:forEach var="item" items="${nationList}" varStatus="status">
		                    <option value="${item.key}">${item.value}</option>
		                  </c:forEach>
		              </select>
		            </div>
	            </div>
	          	<div class="layui-inline">
		            <label class="layui-form-label">出生日期</label>
		            <div class="layui-input-inline">
		              <input type="text" id="LAY-chPmWorker-birthDay" name="birthDay"  autocomplete="off" placeholder="出生年月" class="layui-input">
		            </div>
	            </div>
	            <div class="layui-inline">
		            <label class="layui-form-label">政治面貌</label>
		            <div class="layui-input-inline">
		              <select id="politics" name="politics" lay-verify="required|politics" lay-filter="politics">
		              	<option></option>
		                <c:forEach var="item" items="${politicsList}" varStatus="status">
		                	<option value="${item.key}">${item.value}</option>
		                </c:forEach>
		              </select>
		            </div>
	            </div>
          	</div>
          	<div class="layui-form-item">
	            <div class="layui-inline">
		            <label class="layui-form-label">户口性质</label>
		            <div class="layui-input-inline">
		              <select id="residenceType" name="residenceType" lay-verify="required|residenceType" lay-filter="residenceType">
		              	<option></option>
		              	<c:forEach var="item" items="${residenceList}" varStatus="status">
		                	<option value="${item.key}">${item.value}</option>
		                </c:forEach>
		              </select>
		            </div>
	            </div>
	            <div class="layui-inline">
	              	<label class="layui-form-label">联系电话</label>
	              	<div class="layui-input-inline">
	                	<input type="tel" name="telphoneNo" lay-verify="required|telphoneNo" placeholder="联系电话"  autocomplete="off" class="layui-input">
	              	</div>
	            </div>
	            <div class="layui-inline">
		            <label class="layui-form-label">加入时间</label>
		            <div class="layui-input-inline">
		              <input type="text" id="LAY-chPmWorker-politicsInTime" name="politicsInTime"  autocomplete="off" placeholder="政治面貌加入时间" class="layui-input time">
		            </div>
	            </div>
	        </div>
	        <div class="layui-form-item">
	            <label class="layui-form-label">籍贯</label>
	            <div class="layui-input-block">
	              <div class="layui-inline">
	                <select id="nativePlaceProv" name="nativePlaceProv" lay-verify="required|nativePlaceProv" lay-filter="nativePlaceProv">
	                  <option value="">请选择省</option>
	                  <c:forEach var="item" items="${provList}" varStatus="status">
		              	<option value="${item.provNo}">${item.provName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="nativePlaceCity" name="nativePlaceCity" lay-verify="required|nativePlaceCity" lay-filter="nativePlaceCity">
	                  <option value="">请选择市</option>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="nativePlaceArea" name="nativePlaceArea" lay-verify="required|nativePlaceArea" lay-filter="nativePlaceArea">
	                  <option value="">请选择区县</option>
	                </select>
	              </div>
	            </div>
          	</div>
	        <div class="layui-form-item">
	            <label class="layui-form-label">户口所在地</label>
	            <div class="layui-input-block">
	              <div class="layui-inline">
	                <select name="residenceBirthlandProv" lay-verify="required|residenceBirthlandProv" lay-filter="residenceBirthlandProv">
	                  <option value="">请选择省</option>
	                  <c:forEach var="item" items="${provList}" varStatus="status">
		              	<option value="${item.provNo}">${item.provName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="residenceBirthlandCity" name="residenceBirthlandCity" lay-verify="required|residenceBirthlandCity" lay-filter="residenceBirthlandCity">
	                  <option value="">请选择市</option>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="residenceBirthlandArea" name="residenceBirthlandArea" lay-verify="required|residenceBirthlandArea" lay-filter="residenceBirthlandArea">
	                  <option value="">请选择区县</option>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <input type="text" id="residencePoliceStation" name="residencePoliceStation" lay-verify="required|residencePoliceStation" autocomplete="off" placeholder="所属派出所" class="layui-input" style="width: 385px;">
	              </div>
	            </div>
	        </div>
	        <div class="layui-form-item">
	            <label class="layui-form-label">现家居住址</label>
	            <div class="layui-input-block">
	              <div class="layui-inline">
	                <select id="homeProv" name="homeProv" lay-verify="required|homeProv" lay-filter="homeProv">
	                  <option value="">请选择省</option>
	                  <c:forEach var="item" items="${provList}" varStatus="status">
		              	<option value="${item.provNo}">${item.provName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="homeCity" name="homeCity" lay-verify="required|homeCity" lay-filter="homeCity">
	                  <option value="">请选择市</option>
	                </select>
	              </div>
	              <div class="layui-inline">
		              <input type="text" id="homeAddr" name="homeAddr" lay-verify="required|homeAddr" autocomplete="off" placeholder="详细地址" class="layui-input" style="width: 385px;">
		          </div>
	            </div>
          	</div>
          	<div class="layui-form-item">
	            <div class="layui-inline">
		            <label class="layui-form-label">工作时间-起</label>
		            <div class="layui-input-inline">
		              <input type="text" id="LAY-chPmWorker-firstWorkTime" name="firstWorkTime" lay-verify="required|firstWorkTime" autocomplete="off" placeholder="参加工作时间" class="layui-input time">
		            </div>
	            </div>
	            <div class="layui-inline">
		            <label class="layui-form-label">本单位时间</label>
		            <div class="layui-input-inline">
		              <input type="text" id="LAY-chPmWorker-intoLocalCompTime" name="intoLocalCompTime" lay-verify="required|intoLocalCompTime" autocomplete="off" placeholder="进本单位时间" class="layui-input time">
		            </div>
	            </div>
	            <div class="layui-inline">
		            <label class="layui-form-label">调资类别</label>
		            <div class="layui-input-inline">
		              <select id="salaryAdjustType" name="salaryAdjustType" lay-verify="required|salaryAdjustType" lay-filter="salaryAdjustType">
		                  <option value="">请选择</option>
		                  <c:forEach var="item" items="${adjustTypeList}" varStatus="status">
			              	<option value="${item.key}">${item.value}</option>
			              </c:forEach>
		              </select>
		            </div>
	            </div>
	            <!-- <div class="layui-inline">
		            <label class="layui-form-label">加入时间</label>
		            <div class="layui-input-inline">
		              <input type="text" id="LAY-chPmWorker-politicsInTime" name="politicsInTime" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" placeholder="政治面貌加入时间" class="layui-input time">
		            </div>
	            </div> -->
	        </div>
 			
          	<div class="layui-form-item">
	            <!-- <div class="layui-inline">
	              <label class="layui-form-label">邮箱</label>
	              <div class="layui-input-inline">
	                <input type="text" name="mailBox" lay-verify="mailBox" autocomplete="off" class="layui-input">
	              </div>
	            </div> -->
	            <%-- <div class="layui-inline">
	              <label class="layui-form-label">状态</label>
	              <div class="layui-input-inline">
	                <select id="status" name="status">
	                  <c:forEach var="item" items="${statusList}" varStatus="status">
	                    <option value="${item.key}">${item.value}</option>
	                  </c:forEach>
	                </select>
	              </div>
	            </div> --%>
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
	                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmWorker-add-submit" id="LAY-chPmWorker-add-submit">立即提交</button>
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
    chPmWorker: 'chPmWorker'
  }).use(['index','form','laydate','chPmWorker'], function(){
    	var $ = layui.$,
    	form = layui.form,
    	laydate = layui.laydate;
    	
    	form.render(null, 'chPmWorker-add-form');
  });
  </script>
</body>
</html>