layui.define(['form','laypage','transfer', 'layer', 'util'], function(exports){
	var $ = layui.$,
	laypage = layui.laypage,
	form = layui.form,
	transfer = layui.transfer,
	layer = layui.layer,
	util = layui.util;
	
	function reload()
	{
		$.ajax({
           url:'userRoleCfg/loadData.jhtm',
           method:'post',
           data:{roleId:$("#roleId").val()},
           dataType:'json',
           success:function(data){
				var code = data.retCode;
				var msg = data.retMsg;
				//layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
				if("200" == code)
				{
					transfer.reload('key123', {
						title: ['可选', '已选']
			        	,data: data.retData[1]
			        	,value: data.retData[0]
						,width:450
						,height:600
			        	,showSearch: true
					});
					var userIds=new Array();
			    	var selected = transfer.getData('key123');
			    	for(var p in selected){
			    		userIds[p]=selected[p].value;
			    	}
			    	$("#selectedIds").val(userIds);
				}
           },
           error:function (data) {
		   		layer.msg(data.message, {offset: '150px',icon: 1,time: 2000});
           }
      });
	}
	$(document).ready(function(){
		reload();
	})
	//实例调用
	transfer.render({
	    elem: '#test7'
	    ,data: []
	    ,value:[]
	    ,id: 'key123' //定义唯一索引
	    ,title: ['可选', '已选']
		,width:450
		,height:600
	  	,showSearch: true
	    ,onchange:function(obj,index){
//	    	var arr = ['左边', '右边'];
//	        layer.alert('来自 <strong>'+ arr[index] + '</strong> 的数据：'+ JSON.stringify(obj)); //获得被穿梭时的数据
	    	var userIds=new Array();
	    	var selected = transfer.getData('key123');
	    	for(var p in selected){
	    		userIds[p]=selected[p].value;
	    		//alert(p+ " " + selected[p].title+""+selected[p].value);
	    	}
	    	//layer.alert('右侧数据：'+ JSON.stringify(selected)); //获得被穿梭时的数据
	    	$("#selectedIds").val(userIds);
	    }
	    ,text: {
	    	none: '无数据' //没有数据时的文案
	    	,searchNone: '无匹配数据' //搜索无匹配数据时的文案
	    }
	})
	//批量办法定事件
	util.event('data-type', {
	    getData: function(othis){
	    	var getData = transfer.getData('key123'); //获取右侧数据
	    	layer.alert(JSON.stringify(getData));
	    }
	    ,reload:function(){
	    	reload();
	    }
	});
	exports('userRoleCfg', {})
});