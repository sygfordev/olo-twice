layui.define(['form','tree', 'layer', 'util'],function (exports) {
	var $ = layui.jquery,
	form = layui.form,
	tree = layui.tree,
  	layer = layui.layer,
  	util = layui.util;
	
	
	
	//渲染树形结构
	 var inst = tree.render({
	    elem: '#test9'
	    ,id: 'demoId1'
	    ,data: []
	    ,onlyIconControl: true
	    ,showCheckbox:true	//是否显示复选框
	    ,accordion:false 	//是否开启手风琴模式，默认 false
	    ,edit: ['add', 'update', 'del'] //若为true，则默认显示“改删”图标，若为 数组，则可自由配置操作图标的显示状态和顺序，目前支持的操作图标有：add、update、del
	    ,isJump:false		//是否允许点击节点时弹出新窗口跳转。默认 false，若开启，需在节点数据中设定 link 参数（值为 url 格式）
	    ,showLine:true		//是否开启连接线。默认 true，若设为 false，则节点左侧出现三角图标。
	    ,text: {//自定义各类默认文本，目前支持以下设定
	  		defaultNodeName: '未命名' //节点默认名称
		  	,none: '无数据' //数据为空时的提示文本
		}
	    ,click: function(obj){ //节点被点击的回调
	    	var data = obj.data;
	    	console.log(obj.data); 	//得到当前点击的节点数据
		    console.log(obj.state); //得到当前节点的展开状态：open、close、normal
		    console.log(obj.elem); 	//得到当前节点元素
		    console.log(obj.data.children); //当前节点下是否有子节点
	        //layer.msg('状态：'+ obj.state + '<br>节点数据：' + JSON.stringify(data));
	    }
	  	,oncheck: function(obj){//复选框被点击的回调
		    console.log(obj.data); //得到当前点击的节点数据
		    console.log(obj.checked); //得到当前节点的展开状态：open、close、normal
		    console.log(obj.elem); //得到当前节点元素
		  }
	  	 
	  	,customOperate: true
	  	,operate: function (obj) {//操作节点的回调
	  		var type = obj.type; //得到操作类型：add、update、del
			var data = obj.data; //得到当前节点的数据
			var elem = obj.elem; //得到当前节点元素
			//layer.msg('操作：'+ obj.type + '<br>节点数据：' + JSON.stringify(data) + '<br>节点元素：' + JSON.stringify(elem),{offset: '15px',icon: 1,time: 10000});

			//Ajax 操作
			var id = data.id; 	 //得到节点索引
			if (type === 'add') {//增加节点
				//$("#add-form input[name='parentId']").val(id);
				layer.open({
	 				type: 2,
	 				title: '添加机构',
	 				content: ['add.jhtm?parentId='+id,'no'],
	 				area: ['800px', '550px'],
	 				maxmin: true
	   			});
			} else if (type === 'update') { //修改节点
				layer.open({
	 				type: 2,
	 				title: '编辑机构',
	 				content: ['edit.jhtm?id='+id,'no'],
	 				area: ['800px', '550px'],
	 				maxmin: true
	   			});
			} else if (type === 'del') { //删除节点
				var ids = new Array();
				ids.push(id);
				if(1 == id)
				{
					layer.msg("根节点不允许删除！", {offset: '150px',icon:2,time: 2000});
					return;
				}
				//校验是否包含子机构或其下存在用户
				$.ajax({
					url:'check4Del.jhtm',
		           	method:'post',
		           	data:{ids:ids},
		           	dataType:'json',
		           	success:function(d){
				   		var code = d.statusCode;
						var msg = d.message;
						if("200" != code)
						{
							layer.msg(msg, {offset: '150px',icon:2,time: 2000});
							return;
						}
						var cMsg = (d.isExist4Sub||d.isExist4User)?"其下存在子机构或用户，删除机构时，其下用户及子机构将一并删除，删除后将无法恢复，确认删除么？"
								:"删除后，数据将无法恢复，确定要删除吗？"
						layer.confirm(cMsg, function(index) {
							$.ajax({
								url:'delete.jhtm',
					           	method:'post',
					           	data:{id:id},
					           	dataType:'json',
					           	success:function(data){
							   		var code = data.statusCode;
									var msg = data.message;
									if("200" == code)
									{
										layer.msg(msg, {offset: '150px',icon:1,time: 2000});
										layui.$("#btn_refresh").click();//触发父页面中的刷新按钮
									}else{
										layer.msg(msg, {offset: '150px',icon:2,time: 2000});
									}
					           },
					           error:function (data) {
							   		layer.msg(data.message, {offset: '150px',icon: 2,time: 2000});
					           }
							});
						});
		           },
		           error:function (d) {
				   		layer.msg(d.message, {offset: '150px',icon: 2,time: 2000});
		           }
				});
			}
		}
	});
	
	//按钮事件
	util.event('lay-watch-btn', {
	    getChecked: function(othis){
	    	var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据
	    	layer.alert(JSON.stringify(checkedData), {shade:0});
	      	console.log(checkedData);
	    }
	    ,setChecked: function(){
	      	tree.setChecked('demoId1', [1, 6]); //勾选指定节点
	    }
	    ,reload: function(){
	    	//重载实例
	    	/*var i = Math.ceil(Math.random()*10);
	    	console.log("随机数:"+i);
	    	tree.reload('demoId1', {
	    		data: i<5?data:(i<7?data1:data2)
	    	});*/
	    	obj.reload();
	    }
	    ,openedAll:function(){
	    	obj.reload(0);
	    }
	    ,closedAll:function(){
	    	obj.reload(1);
	    }
	});
	
	
	var obj = {
		    hello: function(str){
		    },
		    reload:function(type){
		    	$.ajax({
				    type: "POST",
				    url:'index/do.jhtm',
				    method:'post',
		            dataType:'json',
					async:false,
				    success: function (d) {
				    	if(200 != d.retCode){
				    		layer.msg(null == d.retMsg?"系统异常！":d.retMsg
				    				, {offset: '150px',icon: 2,time: 2000});
				    		return;
				    	}
				    	//重新赋值 并 重载树形结构
				    	//layer.msg(JSON.stringify(d.retData),{icon: 1});
				    	//console.log("树形返回数据："+JSON.stringify(d.retData));
				    	//inst.config.data = d.retData;
				    	var tdata = d.retData;
				    	if(0 == type || 1 == type){
				    		tdata = JSON.stringify(tdata);
					    	//tdata = tdata.replace(/"spread":".*?"/,'spread:'+(0==type)?true:false+'')
				    		//tdata = tdata.replace("spread.*?([,}$])", "spread\":\""+(0==type)?true:false+"\"$1")
				    		tdata = tdata.replace((1==type)?true:false, (0==type)?true:false);
				    		//(\w+)=(\w+)\[#\1=\2\]
				    		//"spread":true
					    	tdata = JSON.parse(tdata);
				    	}
				    	
				    	tree.reload('demoId1', {
				    		data: tdata
				    	});
				    },
			  		error:function (d) {
						layer.msg("加载失败！", {offset: '150px',icon: 2,time: 2000});
		            } 
				})
			}
	};
	
	//触发三个button按钮
    $('#btn-expand').click(function () {
        //treetable.expandAll('#LAY-branchOffice-manage');
    });

    $('#btn-fold').click(function () {
        //treetable.foldAll('#LAY-branchOffice-manage');
    });

    $('#btn-refresh').click(function () {
    	reload();
    });
    
	
	
	//---------表头按钮区域----------
	$('.layui-btn.layuiadmin-btn-admin').on('click', function(){
    	var type = $(this).data('type');
    	active[type] ? active[type].call(this) : '';
    });
	
	var active = {
		batchdel: function(){
			var checkStatus = table.checkStatus('LAY-branchOffice-manage'),
			checkData = checkStatus.data;
			if(checkData.length === 0){
	  			return layer.msg('请选择数据');
			}
			var ids = new Array();
			for(var i=0;i<checkData.length;i++)
			{
				ids.push(checkData[i].id);
			}
			layer.confirm('确定删除吗？', function(index) {
				$.ajax({
					url:'batchDel.jhtm',
		           	method:'post',
		           	data:{ids:ids},
		           	dataType:'json',
		           	success:function(data){
				   		var code = data.statusCode;
						var msg = data.message;
						if("200" == code)
						{
							branch.renderTable();
						}else{
							layer.msg(msg, {offset: '150px',icon:2,time: 2000});
						}
		           },
		           error:function (data) {
				   		layer.msg(data.message, {offset: '150px',icon: 2,time: 2000});
		           }
				});
			});
		},
  		add: function(){
  			var checkStatus = table.checkStatus('LAY-branchOffice-manage'),
			checkData = checkStatus.data;
			if(checkData.length === 0){
	  			return layer.msg('请选择所在机构');
			}
			var ids = new Array();
			for(var i=0;i<checkData.length;i++)
			{
				ids.push(checkData[i].id);
			}
			if(ids.length !=1) return layer.msg('请选择单个所在机构');
   			layer.open({
 				type: 2,
 				title: '添加机构',
 				content: ['add.jhtm?parentId='+ids[0],'no'],
 				area: ['800px', '550px'],
 				maxmin: true
   			});
  		}
    }
	
	//--------------添加页面添加按钮监听--------------------
	/* 监听提交 */
    form.on('submit(LAY-branchOffice-add-submit)', function(rdata){
      $.ajax({
           	url:'add/do.jhtm',
           	method:'post',
           	data:rdata.field,
           	dataType:'json',
			async:true,
           	success:function(d){
				var code = d.statusCode;
				var msg = d.message;
				layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
				if("200" == code)
				{
					parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
		          	var index = parent.layer.getFrameIndex(window.name);
		          	parent.layer.close(index);
		          	parent.layui.$("#btn_refresh").click();//触发父页面中的刷新按钮
				}
           },
           error:function (d) {
		   		parent.layer.msg(data.message, {offset: '150px',icon: 1,time: 2000});
           }
      });
      return false;
    });
    
	
	//----------------编辑页面操作按钮监听----------------
	/* 监听提交 */
	form.on('submit(LAY-branchOffice-edit-submit)', function(data){
	  	$.ajax({
	      		url:'edit/do.jhtm',
	           	method:'post',
	           	data:data.field,
	           	dataType:'json',
	           	success:function(d){
			   		var code = d.statusCode;
					var msg = d.message;
					if("200" == code)
					{
						parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
		          		var index = parent.layer.getFrameIndex(window.name);
		          		parent.layer.close(index);
		          		parent.layui.$("#btn_refresh").click();//触发父页面中的刷新按钮
					}else{
						layer.msg(msg, {offset: '150px',icon:2,time: 2000});
					}
	           },
	           error:function (d) {
			   		parent.layer.msg(d.message, {offset: '150px',icon: 2,time: 2000});
	           }
	    });
	    return false;
	});
  
	form.on('submit(LAY-branchOffice-edit-cancel)', function(data){
		var index = parent.layer.getFrameIndex(window.name);
	    parent.layer.close(index);
	    return false;
	});
  	//自定义验证
	form.verify({
		branchName: function(value){
			  if(value.length > 80){
				  return '机构名称长度不能超过80';
			  }
	    },
		cityNo: function(value){
			  if(value.length > 20){
				  return '城市编号长度不能超过20';
			  }
	    },
		parentId: function(value){
			  if(value.length > 19){
				  return '父级ID长度不能超过19';
			  }
	    },
		contact: function(value){
			  if(value.length > 50){
				  return '联系长度不能超过50';
			  }
	    },
		phone: function(value){
			  if(value.length > 50){
				  return '电话长度不能超过50';
			  }
	    },
		fax: function(value){
			  if(value.length > 50){
				  return '传真长度不能超过50';
			  }
	    },
		email: function(value){
			  if(value.length > 100){
				  return '邮箱长度不能超过100';
			  }
	    },
		address: function(value){
			  if(value.length > 200){
				  return '地址长度不能超过200';
			  }
	    },
		zip: function(value){
			  if(value.length > 10){
				  return '邮编长度不能超过10';
			  }
	    },
		remark: function(value){
			  if(value.length > 100){
				  return '备注长度不能超过100';
			  }
	    },
	});
	
	exports("branch",obj);
});