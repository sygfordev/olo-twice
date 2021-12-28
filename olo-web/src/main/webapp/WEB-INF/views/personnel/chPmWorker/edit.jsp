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
        <form class="layui-form" action="" lay-filter="chPmWorker-edit-form">
        	<!-- 隐藏属性 -->
        	<input type="hidden" id="id" name="id" value="${model.id}"/>
        	<input type="hidden" id="oper" name="oper" value="${oper}"/>
        	<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>"/>
        	
        	<input type="hidden" id="nationCn" name="nationCn" value="${model.nationCn}"/>
        	<input type="hidden" id="politicsCn" name="politicsCn" value="${model.politicsCn}"/>
        	<input type="hidden" id="nativePlaceProvCn" name="nativePlaceProvCn" value="${model.nativePlaceProvCn}"/>
        	<input type="hidden" id="nativePlaceCityCn" name="nativePlaceCityCn" value="${model.nativePlaceCityCn}"/>
        	<input type="hidden" id="nativePlaceAreaCn" name="nativePlaceAreaCn" value="${model.nativePlaceAreaCn}"/>
        	<input type="hidden" id="residenceTypeCn" name="residenceTypeCn" value="${model.residenceTypeCn}"/>
        	<input type="hidden" id="residenceBirthlandProvCn" name="residenceBirthlandProvCn" value="${model.residenceBirthlandProvCn}"/>
        	<input type="hidden" id="residenceBirthlandCityCn" name="residenceBirthlandCityCn" value="${model.residenceBirthlandCityCn}"/>
        	<input type="hidden" id="residenceBirthlandAreaCn" name="residenceBirthlandAreaCn" value="${model.residenceBirthlandAreaCn}"/>
        	<input type="hidden" id="homeProvCn" name="homeProvCn" value="${model.homeProvCn}"/>
        	<input type="hidden" id="homeCityCn" name="homeCityCn" value="${model.homeCityCn}"/>
        	<input type="hidden" id="salaryAdjustTypeCn" name="salaryAdjustTypeCn" value="${model.salaryAdjustTypeCn}"/>
        	
        	
        	<div class="layui-form-item">
	            <div class="layui-inline">
	              <label class="layui-form-label">姓名</label>
	              <div class="layui-input-inline">
	                <input type="text" id="name" name="name" value="${model.name}" ${oper eq 'detail'?'disabled':''} lay-verify="required|name" autocomplete="off" class="layui-input">
	              </div>
	            </div>
	            <div class="layui-inline">
	              <label class="layui-form-label">身份证号</label>
	              <div class="layui-input-inline">
	                <input type="text" id="cardNo" name="cardNo" value="${model.cardNo}" ${oper eq 'detail'?'disabled':''} lay-verify="required|cardNo" autocomplete="off" class="layui-input">
	              </div>
	            </div>
	            <div class="layui-inline">
	              <label class="layui-form-label">性别</label>
	              <div class="layui-input-inline">
	                <select id="sex" name="sex" lay-verify="required|sex" ${oper eq 'detail'?'disabled':''}>
	                	<option></option>
	                  <c:forEach var="item" items="${sexList}" varStatus="status">
	                    <option value="${item.key}" ${item.key eq model.sex?'selected':''}>${item.value}</option>
	                  </c:forEach>
	                </select>
	              </div>
	            </div>
          	</div>
          	<div class="layui-form-item">
			  	<div class="layui-inline">
		            <label class="layui-form-label">民族</label>
		            <div class="layui-input-inline">
		              <select id="nation" name="nation" lay-verify="required|nation" lay-filter="nation" ${oper eq 'detail'?'disabled':''}>
		              	  <option></option>
		                  <c:forEach var="item" items="${nationList}" varStatus="status">
		                    <option value="${item.key}" ${item.key eq model.nation?'selected':''}>${item.value}</option>
		                  </c:forEach>
		              </select>
		            </div>
	            </div>
	          	<div class="layui-inline">
		            <label class="layui-form-label">出生日期</label>
		            <div class="layui-input-inline">
		              <input type="text" id="LAY-chPmWorker-birthDay" name="birthDay" value='<fmt:formatDate value="${empty model.birthDay?'':model.birthDay}" pattern="yyyy-MM"/>' ${oper eq 'detail'?'disabled':''} autocomplete="off" placeholder="出生年月" class="layui-input">
		            </div>
	            </div>
	            <div class="layui-inline">
		            <label class="layui-form-label">政治面貌</label>
		            <div class="layui-input-inline">
		              <select id="politics" name="politics" lay-verify="required|politics" lay-filter="politics" ${oper eq 'detail'?'disabled':''}>
		              	<option></option>
		                <c:forEach var="item" items="${politicsList}" varStatus="status">
		                	<option value="${item.key}" ${item.key eq model.politics?'selected':''}>${item.value}</option>
		                </c:forEach>
		              </select>
		            </div>
	            </div>
          	</div>
          	<div class="layui-form-item">
	            <div class="layui-inline">
		            <label class="layui-form-label">户口性质</label>
		            <div class="layui-input-inline">
		              <select id="residenceType" name="residenceType" lay-verify="required|residenceType" lay-filter="residenceType" ${oper eq 'detail'?'disabled':''}>
		              	<option></option>
		              	<c:forEach var="item" items="${residenceList}" varStatus="status">
		                	<option value="${item.key}" ${item.key eq model.residenceType?'selected':''}>${item.value}</option>
		                </c:forEach>
		              </select>
		            </div>
	            </div>
	            <div class="layui-inline">
	              	<label class="layui-form-label">联系电话</label>
	              	<div class="layui-input-inline">
	                	<input type="tel" name="telphoneNo" value="${model.telphoneNo}" ${oper eq 'detail'?'disabled':''} lay-verify="required|telphoneNo" placeholder="联系电话"  autocomplete="off" class="layui-input">
	              	</div>
	            </div>
	            <div class="layui-inline">
		            <label class="layui-form-label">加入时间</label>
		            <div class="layui-input-inline">
		              <input type="text" id="LAY-chPmWorker-politicsInTime" name="politicsInTime" value='<fmt:formatDate value="${empty model.politicsInTime?'':model.politicsInTime}" pattern="yyyy-MM-dd"/>' ${oper eq 'detail'?'disabled':''} autocomplete="off" placeholder="政治面貌加入时间" class="layui-input time">
		            </div>
	            </div>
	        </div>
	        <div class="layui-form-item">
	            <label class="layui-form-label">籍贯</label>
	            <div class="layui-input-block">
	              <div class="layui-inline">
	                <select id="nativePlaceProv" name="nativePlaceProv" lay-verify="required|nativePlaceProv" lay-filter="nativePlaceProv" ${oper eq 'detail'?'disabled':''}>
	                  <option value="">请选择省</option>
	                  <c:forEach var="item" items="${provList}" varStatus="status">
		              	<option value="${item.provNo}" ${item.provNo eq model.nativePlaceProv?'selected':''}>${item.provName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="nativePlaceCity" name="nativePlaceCity" lay-verify="required|nativePlaceCity" lay-filter="nativePlaceCity" ${oper eq 'detail'?'disabled':''}>
	                  <option value="">请选择市</option>
	                  <c:forEach var="item" items="${nativeCityList}" varStatus="status">
		              	<option value="${item.cityNo}" ${item.cityNo eq model.nativePlaceCity?'selected':''}>${item.cityName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="nativePlaceArea" name="nativePlaceArea" lay-verify="required|nativePlaceArea" lay-filter="nativePlaceArea" ${oper eq 'detail'?'disabled':''}>
	                  <option value="">请选择区县</option>
	                  <c:forEach var="item" items="${nativeAreaList}" varStatus="status">
		              	<option value="${item.areaNo}" ${item.areaNo eq model.nativePlaceArea?'selected':''}>${item.areaName}</option>
		              </c:forEach>
	                </select>
	              </div>
	            </div>
          	</div>
	        <div class="layui-form-item">
	            <label class="layui-form-label">户口所在地</label>
	            <div class="layui-input-block">
	              <div class="layui-inline">
	                <select name="residenceBirthlandProv" lay-verify="required|residenceBirthlandProv" lay-filter="residenceBirthlandProv" ${oper eq 'detail'?'disabled':''}>
	                  <option value="">请选择省</option>
	                  <c:forEach var="item" items="${provList}" varStatus="status">
		              	<option value="${item.provNo}" ${item.provNo eq model.residenceBirthlandProv?'selected':''}>${item.provName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="residenceBirthlandCity" name="residenceBirthlandCity" lay-verify="required|residenceBirthlandCity" lay-filter="residenceBirthlandCity" ${oper eq 'detail'?'disabled':''}>
	                  <option value="">请选择市</option>
	                  <c:forEach var="item" items="${residenceCityList}" varStatus="status">
		              	<option value="${item.cityNo}" ${item.cityNo eq model.residenceBirthlandCity?'selected':''}>${item.cityName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="residenceBirthlandArea" name="residenceBirthlandArea" lay-verify="required|residenceBirthlandArea" lay-filter="residenceBirthlandArea" ${oper eq 'detail'?'disabled':''}>
	                  <option value="">请选择区县</option>
	                  <c:forEach var="item" items="${residenceAreaList}" varStatus="status">
		              	<option value="${item.areaNo}" ${item.areaNo eq model.residenceBirthlandArea?'selected':''}>${item.areaName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <input type="text" id="residencePoliceStation" name="residencePoliceStation" lay-verify="required|residencePoliceStation" value="${model.residencePoliceStation}" autocomplete="off" placeholder="所属派出所" class="layui-input" style="width: 385px;" ${oper eq 'detail'?'disabled':''}>
	              </div>
	            </div>
	        </div>
	        <div class="layui-form-item">
	            <label class="layui-form-label">现家居住址</label>
	            <div class="layui-input-block">
	              <div class="layui-inline">
	                <select id="homeProv" name="homeProv" lay-verify="required|homeProv" lay-filter="homeProv" ${oper eq 'detail'?'disabled':''}>
	                  <option value="">请选择省</option>
	                  <c:forEach var="item" items="${provList}" varStatus="status">
		              	<option value="${item.provNo}" ${item.provNo eq model.homeProv?'selected':''}>${item.provName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
	                <select id="homeCity" name="homeCity" lay-verify="required|homeCity" lay-filter="homeCity" ${oper eq 'detail'?'disabled':''}>
	                  <option value="">请选择市</option>
	                  <c:forEach var="item" items="${homeCityList}" varStatus="status">
		              	<option value="${item.cityNo}" ${item.cityNo eq model.homeCity?'selected':''}>${item.cityName}</option>
		              </c:forEach>
	                </select>
	              </div>
	              <div class="layui-inline">
		              <input type="text" id="homeAddr" name="homeAddr" value="${model.homeAddr}" ${oper eq 'detail'?'disabled':''} lay-verify="required|homeAddr" autocomplete="off" placeholder="详细地址" class="layui-input" style="width: 385px;">
		          </div>
	            </div>
          	</div>
          	<div class="layui-form-item">
	            <div class="layui-inline">
		            <label class="layui-form-label">工作时间-起</label>
		            <div class="layui-input-inline">
		              <input type="text" id="LAY-chPmWorker-firstWorkTime" name="firstWorkTime" value='<fmt:formatDate value="${empty model.firstWorkTime?'':model.firstWorkTime}" pattern="yyyy-MM-dd"/>' ${oper eq 'detail'?'disabled':''} lay-verify="required|firstWorkTime" autocomplete="off" placeholder="参加工作时间" class="layui-input time">
		            </div>
	            </div>
	            <div class="layui-inline">
		            <label class="layui-form-label">本单位时间</label>
		            <div class="layui-input-inline">
		              <input type="text" id="LAY-chPmWorker-intoLocalCompTime" name="intoLocalCompTime" value='<fmt:formatDate value="${empty model.intoLocalCompTime?'':model.intoLocalCompTime}" pattern="yyyy-MM-dd"/>' ${oper eq 'detail'?'disabled':''} lay-verify="required|intoLocalCompTime" autocomplete="off" placeholder="进本单位时间" class="layui-input time">
		            </div>
	            </div>
	            <div class="layui-inline">
		            <label class="layui-form-label">调资类别</label>
		            <div class="layui-input-inline">
		              <select id="salaryAdjustType" name="salaryAdjustType" lay-verify="required|salaryAdjustType" lay-filter="salaryAdjustType" ${oper eq 'detail'?'disabled':''}>
		                  <option value="">请选择</option>
		                  <c:forEach var="item" items="${adjustTypeList}" varStatus="status">
			              	<option value="${item.key}" ${item.key eq model.salaryAdjustType?'selected':''}>${item.value}</option>
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
	                <select id="status" name="status" ${oper eq 'detail'?'disabled':''}>
	                  <c:forEach var="item" items="${statusList}" varStatus="status">
	                    <option value="${item.key}" ${item.key eq model.status?'selected':''}>${item.value}</option>
	                  </c:forEach>
	                </select>
	              </div>
	            </div> --%>
          	</div>
          	<div class="layui-form-item layui-form-text">
	            <label class="layui-form-label">备注</label>
	            <div class="layui-input-block">
	              <textarea id="remark" name="remark" lay-verify="remark" placeholder="请输入内容" class="layui-textarea" ${oper eq 'detail'?'disabled':''}>${model.remark}</textarea>
	            </div>
         	</div> 
          
          	<div class="layui-form-item layui-layout-admin ${oper eq 'detail'?'layui-hide':''}">
	            <div class="layui-input-block">
	              <div class="layui-footer" style="left: 0;">
	                <button class="layui-btn" lay-submit="" lay-filter="LAY-chPmWorker-edit-submit" id="LAY-chPmWorker-edit-submit">修改</button>
	                <!-- <button lay-submit=""  lay-filter="LAY-chPmWorker-edit-cancel" id="LAY-chPmWorker-edit-cancel" class="layui-btn layui-btn-primary">取消</button> -->
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
    	
    	form.render(null, 'chPmWorker-edit-form');
  });
  </script>
</body>
</html>