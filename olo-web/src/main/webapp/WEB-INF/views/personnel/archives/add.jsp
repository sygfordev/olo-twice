<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<%
	String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
    String path = request.getContextPath();
    String basePath = base+path+"/";
%>
<!DOCTYPE html>
<html style="height:100%;">
<head>
  <meta charset="utf-8">
  <title>表单组合</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body style="height:100%;">
	
  <div class="layui-fluid">
    <div class="layui-card">
        <!-- <div class="layui-card-header">简洁风格</div> -->
        <div class="layui-card-body">
        	<div class="layui-progress layui-progress-big" lay-filter="demo" lay-showPercent="true">
			  <div class="layui-progress-bar" lay-percent="0%"></div>
			</div>
			<!-- 隐藏属性 -->
			<input type="hidden" id="wkId" name="wkId" value="">
			<input type="hidden" id="base" name="base" value="<%=base%>">
			<input type="hidden" id="basePath" name="basePath" value="<%=basePath%>">
			
            <div class="layui-tab layui-tab-brief" lay-filter="archivesTab">
              <ul class="layui-tab-title">
                <li lay-id=0 class="layui-this">基本信息</li>
                <li lay-id=1>用工及岗位</li>
                <li lay-id=2>学历信息</li>
                <li lay-id=3>职务信息</li>
                <li lay-id=4>职称信息</li>
                <li lay-id=5>技能等级</li>
                <li lay-id=6>特殊工种</li>
                <li lay-id=7>合同信息</li>
                <li lay-id=8>工作经历</li>
                <li lay-id=9>家庭成员</li>
                <li lay-id=10>其他信息</li>
                <li lay-id=11>人事档案</li>
                <li lay-id=12>增减情况</li>
                <li lay-id=13>上传文件</li>
              </ul>
              <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                	<!-- scrolling="no" -->
                	<iframe id='tab0' name="tab0" class="iframe" src="#" frameborder="0" width="100%" height="700px;">
                		
                	</iframe>
                </div>
                <div class="layui-tab-item">
               		<iframe id='tab1' name="tab1" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
                <div class="layui-tab-item">
               		<iframe id='tab2' name="tab2" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab3' name="tab3" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab4' name="tab4" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab5' name="tab5" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab6' name="tab6" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab7' name="tab7" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab8' name="tab8" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab9' name="tab9" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab10' name="tab10" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab11' name="tab11" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab12' name="tab12" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
               	<div class="layui-tab-item">
               		<iframe id='tab13' name="tab13" class="iframe" src="" frameborder="0" width="100%" height="700px;">
               		
               		</iframe>
               	</div>
              </div>
            </div>
        </div>
    </div>
  </div>

    
  <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index', //主入口模块
    archives: 'archives'
  }).use(['index','form','archives'], function(){
	  	var $ = layui.$
	    ,admin = layui.admin
	    ,element = layui.element
	    ,router = layui.router();

	    element.render();
	    
	    //tab模块数组
	    var modules = ['chPmWorker','chPmStation','chPmEduInfo','chPmPositInfo','chPmTitleInfo',
	    	'chPmSkillsInfo','chPmSpecProfe','chPmContractInfo','chPmWorkExpeInfo',
	    	'chPmFamilyMemberInfo','chPmOtherInfo','chPmDossierInfo','chPmIncdecInfo','chPmFileInfo'];
	    var status = [0,0,0,0,0,0,0,0,0,0,0,0,0,0];
	    var rat = Math.round(100/modules.length);
		//var tIndex = ['学历信息','职务信息','职称信息','技能等级','特殊工种','合同信息','工作经历','家庭成员'];
	    
    	element.on('tab(archivesTab)', function(obj){
    		//if(obj.index==0)_tools.setWorkerId(234);
    		
    		var wkId = $("#wkId").val();
    	    if(0 != obj.index && (null == wkId 	
    	    		|| "" == wkId))
    	    {
    	    	layer.msg("请先完善基本信息");
    	    	element.tabChange('archivesTab', 0);
    	    	return;
    	    }
    	    //判断是否已经加载过该tab页
    	    if(1 == status[obj.index]) return;
    	    //重新定义iframe的请求路径
    	    var targetTab = 'tab'+obj.index;
    	    //var url = $("#base").val()+$("#"+targetTab).attr("src");
    	    var url = $("#basePath").val()+"/personnel/"+modules[obj.index];
    	    
    	    let indexArray = [2,3,4,5,6,7,8,9];
    	    url = (indexArray.includes(obj.index))?url+"/index.jhtm":url+"/add.jhtm";
    	    $("#"+targetTab).attr("src",url+"?wkId="+$("#wkId").val());
    	    //layer.msg(obj.index + '：' + this.innerHTML);
    	    if(0 == status[obj.index])status[obj.index] = 1;
    	});
    	
    	var _tools = {
   	    	setWorkerId: function(wkId){
   	    		if(null == wkId || "" == wkId
   	    				|| "undefined" == wkId)
   	    			return;
   	    		$("#wkId").val(wkId);
   	    		//element.tabDelete('archivesTab', 0);
   	    		
   	    		//删除模块数据和状态数据 
   	    		//modules.splice(0,1);
   	    		//status.splice(0,1);
   	    		
   	    		var per = (modules.length-status.length)>0?((modules.length-status.length)*rat)+"%":"100%";
   	    		//element.progress('demo', per);
   	    		//$(".layui-progress-bar").attr("lay-percent",per);
   	    	},
   	    	getWorkerId: function(){
   	    		//layer.msg("get func1");
   	    		var wkId = $("#wkId").val();
   	    		return wkId;
   	    	},
   	    	delTab: function(target){
   	    		element.tabDelete('archivesTab',target);
   	    		status.splice(target,1);//删除该模块的状态数据
   	    		//layer.msg("status.len:"+status.length);
   	    		var per = (modules.length-status.length)>0?((modules.length-status.length)*rat)+"%":"100%";
   	    		element.progress('demo', per);
   	    		$(".layui-progress-bar").attr("lay-percent",per);
   	    		
   	    		//判断是否为最后一录入项
   	    		if(status.length == modules.length)
   	    		{
   	    			layer.confirm('录入完毕，是否关闭该页面？', function(index){
   	    				//删除菜单tab页
   	    	    	 	var topAdmin = parent === self ? admin : parent.layui.admin; //关闭tab页
   	    	    	    topAdmin.closeThisTabs();
   	    			});
   	    		}
   	    	},
   	    	
   		}
   		window.tools = _tools;
    	
    	//切换到指定tab页上
    	element.tabChange('archivesTab', 0);
    	//element.progress('demo', '30%');
    	//删除指定tab页
    	//element.tabDelete('archivesTab', 2);
    	//element.progress('demo', '50%');
  });
  </script>
</body>
</html>