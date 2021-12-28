layui.define(['table','form','laypage','laydate'],function (exports) {
	var $ = layui.jquery,
	form = layui.form,
  	table = layui.table,
  	layer = layui.layer,
  	laypage = layui.laypage,
  	laydate = layui.laydate;
	
	//系统权限管理-表格渲染
	var inst = table.render({
		elem: '#LAY-systemPriv-manage',
		url: 'index/do.jhtm',
	    cols: 
	    [
	  	  [
	  		{type: 'checkbox', fixed: 'left'},
        	   /*{type: 'numbers', title: '序号',width: 50},*/
            {field: 'privId', title: '权限编号',width: '5%'},
            {field: 'privCnName', title: '中文名称',width: '10%'},
            {field: 'privEnName', title: '英文名称',width: '10%'},
            /*{field: 'privTarget', title: '权限目标'},*/
            {field: 'privType', title: '权限类型', templet:'#privTypeTpl',width: '10%'},
            {field: 'privMethod', title: '权限方法',width: '10%'},
            {field: 'privSuper', title: '上级权限',width: '5%'},
            {field: 'privPermissioin', title: '权限标识',width: '10%'},
            {field: 'privAction', title: '权限动作',width: '15%'},
            /*{field: 'privIcon', title: '权限标识'},*/
            {field: 'status', title: '状态', templet:'#statusTpl',width: '10%'},
            {title: '操作', align: 'center', fixed: 'right', toolbar: '#table-systemPriv-manager-operator',width: '15%'}
		  ]
	    ],
	    page: true,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});
	
	//导出操作对象
	var obj = {
			reload:function()
			{
				//执行重载
	  			table.reload('#LAY-systemPriv-manage');
			}
	}
	//监听工具条
	table.on('tool(LAY-systemPriv-manage)', function(obj){
		var data = obj.data;
		switch(obj.event)
		{
		case 'del':
			layer.confirm('确定删除么', function(index){
		  		$.ajax({
			    	url:'delete.jhtm',
			        method:'post',
			        data:{"privId":data.privId},
			        dataType:'json',
			        success:function(res){
						var code = res.statusCode;
						var msg = res.message;
						layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
						if("200" == code)
						{
							obj.del();
							layer.close(index);
						}
			        },
			        error:function (res) {
						layer.msg(res.message, {offset: '150px',icon: 2,time: 2000});
			        }
		  		});
			});
			break;
		case 'edit':
			layer.open({
				type: 2,
				title: '编辑权限',
				content: ['edit.jhtm?privId='+data.privId],
				area: ['800px', '550px'],
				maxmin: true
			});
			break;
		}
	});
	
	//监听搜索
	form.on('submit(LAY-systemPriv-search)', function(data){
      	var field = data.field;
      	//执行重载
      	table.reload('LAY-systemPriv-manage', {where: field});
	});
	
	//---------表头按钮区域----------
	$('.layui-btn.layuiadmin-btn-admin').on('click', function(){
    	var type = $(this).data('type');
    	active[type] ? active[type].call(this) : '';
    });
	
	var active = {
		batchdel: function(){
			var checkStatus = table.checkStatus('LAY-systemPriv-manage'),
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
				layer.msg("删除id列表："+ids);
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
   			layer.open({
 				type: 2,
 				title: '添加权限',
 				content: ['add.jhtm','no'],
 				area: ['800px', '550px'],
 				maxmin: true
   			});
  		}
    }
	
	//--------------添加页面添加按钮监听--------------------
	
	form.on('select(privType)',function(data){
		//	菜单:1	功能：2 
        var value=data.value;
        if("" == value){
        	//隐藏菜单等级和一二级菜单
        	$('#menuDiv').addClass('layui-hide');
        	$('#menuLevelDiv').addClass('layui-hide');
        	$('#menuLevelLabel').addClass('layui-hide');
        	/*$("#menu4Level1").empty();
        	$("#menu4Level1").append(new Option("",""));
        	layui.form.render("select");*/
        	return;
        }
        if(1 == value){//权限类型：菜单
        	$('#menuLevelDiv').removeClass('layui-hide');
        	$('#menuLevelLabel').removeClass('layui-hide');
        	$('#menuDiv').addClass('layui-hide');
        	
        	$("#menuLevel").attr("lay-verify","required");
    		$("#menu4Level1").removeAttr("lay-verify");
        	$("#menu4Level2").removeAttr("lay-verify");
        }else{//权限类型：功能
        	//隐藏菜单等级
        	$('#menuLevelDiv').addClass('layui-hide');
        	$('#menuLevelLabel').addClass('layui-hide');
        	
        	//显示一二级菜单
        	$('#menuDiv').removeClass('layui-hide');
        	$('#menu1Div').removeClass('layui-hide');
        	$('#menu1Label').removeClass('layui-hide');
        	$('#menu2Div').removeClass('layui-hide');
        	$('#menu2Label').removeClass('layui-hide');
        	
        	$("#menuLevel").removeAttr("lay-verify");
        	$("#menu4Level1").attr("lay-verify","required");
        	$("#menu4Level2").attr("lay-verify","required");
        }
        if(1 == value)return;
        $.ajax({
            url:'loadPriv4Menu.jhtm',
            method:'post',
            data:{menuLevel:1},
            dataType:"JSON",
            success:function(data){
            	var code = data.retCode;
    			var msg = data.retMsg;
    			if("200" != code){
    				layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
    				return;
    			}
                $("#menu4Level1").empty();
                $("#menu4Level1").append(new Option("",""));
                $("#menu4Level2").empty();
                $("#menu4Level2").append(new Option("",""));
                data.retData.forEach(function (obj) {
                	console.log(JSON.stringify(obj));
                    $("#menu4Level1").append(new Option(obj.privCnName,obj.privId));
                })
                layui.form.render("select");
            }
        });
    });
	
	form.on('select(menuLevel)',function(data){
		//1：一级菜单	2：二级菜单 
        var value=data.value;
        if(1 == value){//一级菜单
        	$('#menuDiv').addClass('layui-hide');
        	
        	$("#menu4Level1").removeAttr("lay-verify");
        }
        else if(2 == value){//二级菜单
        	//显示一二级菜单
        	$('#menuDiv').removeClass('layui-hide');
        	$('#menu1Div').removeClass('layui-hide');
        	$('#menu1Label').removeClass('layui-hide');
        	$('#menu2Div').addClass('layui-hide');
        	$('#menu2Label').addClass('layui-hide');
        	
        	$("#menu4Level1").attr("lay-verify","required");
        }
        if(1 == value)return;
        $.ajax({
            url:'loadPriv4Menu.jhtm',
            method:'post',
            data:{menuLevel:1},
            dataType:"JSON",
            success:function(data){
            	var code = data.retCode;
    			var msg = data.retMsg;
    			if("200" != code){
    				layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
    				return;
    			}
                $("#menu4Level1").empty();
                $("#menu4Level1").append(new Option("",""));
                data.retData.forEach(function (obj) {
                	console.log(JSON.stringify(obj));
                    $("#menu4Level1").append(new Option(obj.privCnName,obj.privId));
                })
                layui.form.render("select");
            }
        });
    });
	
	form.on('select(menu4Level1)',function(data){
        var value=data.value;
        //if(new RegExp("^[\u4e00-\u9fa5\]+$").test(value)){
        if("" == value){
        	$("#menu4Level2").empty();
        	$("#menu4Level2").append(new Option("",""));
        	layui.form.render("select");
        	return;
        }
        $.ajax({
            url:'loadPriv4Menu.jhtm',
            method:'post',
            data:{menuLevel:2,privSuper:value},
            dataType:"JSON",
            success:function(data){
            	var code = data.retCode;
    			var msg = data.retMsg;
    			if("200" != code){
    				layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
    				return;
    			}
                $("#menu4Level2").empty();
                $("#menu4Level2").append(new Option("",""));
                data.retData.forEach(function (obj) {
                	console.log(JSON.stringify(obj));
                    $("#menu4Level2").append(new Option(obj.privCnName,obj.privId));
                })
                layui.form.render("select");
            }
        });

    });
	/* 监听提交 */
    form.on('submit(LAY-systemPriv-add-submit)', function(data){
      $.ajax({
           url:'add/do.jhtm',
           method:'post',
           data:data.field,
           dataType:'json',
           success:function(data){
			var code = data.statusCode;
			var msg = data.message;
			layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
			if("200" == code)
			{
				parent.layui.table.reload('LAY-systemPriv-manage'); //重载表格
				//parent.layui.$("#LAY-systemPriv-search").click();
				parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
	          	var index = parent.layer.getFrameIndex(window.name);
	          	parent.layer.close(index);
			}
           },
           error:function (data) {
		   	parent.layer.msg(data.message, {offset: '150px',icon: 1,time: 2000});
           }
      });
      return false;
    });
    
	
	//----------------编辑页面操作按钮监听----------------
	/* 监听提交 */
	form.on('submit(LAY-systemPriv-edit-submit)', function(data){
	  	$.ajax({
	      		url:'edit/do.jhtm',
	           	method:'post',
	           	data:data.field,
	           	dataType:'json',
	           	success:function(data){
			   		var code = data.statusCode;
					var msg = data.message;
					if("200" == code)
					{
						parent.layui.$("#LAY-systemPriv-search").click();
						var field = {};
				      	/*field.page = tmp.page;
				      	field.limit = tmp.limit;
				      	field.doNotRenderPage = true;//不执行分页脚本刷新
						tree.renderTable();*/
						parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
		          		var index = parent.layer.getFrameIndex(window.name);
		          		parent.layer.close(index);
					}else{
						layer.msg(msg, {offset: '150px',icon:2,time: 2000});
					}
	           },
	           error:function (data) {
			   		parent.layer.msg(data.message, {offset: '150px',icon: 2,time: 2000});
	           }
	    });
	    return false;
	});
  
	form.on('submit(LAY-systemPriv-edit-cancel)', function(data){
		var index = parent.layer.getFrameIndex(window.name);
	    parent.layer.close(index);
	    return false;
	});
	//自定义验证
	form.verify({
		privCnName: function(value){
			  if(value.length > 30){
				  return '权限名称-中文长度不能超过30';
			  }
	    },
		privEnName: function(value){
			  if(value.length > 30){
				  return '权限名称-英文长度不能超过30';
			  }
	    },
		privTarget: function(value){
			  if(value.length > 30){
				  return '权限target长度不能超过30';
			  }
	    },
		remark: function(value){
			  if(value.length > 100){
				  return '备注长度不能超过100';
			  }
	    },
		privType: function(value){
			  if(value.length > 4){
				  return '权限类型长度不能超过4';
			  }
	    },
		privMethod: function(value){
			  if(value.length > 100){
				  return '权限方法长度不能超过100';
			  }
	    },
		privPermissioin: function(value){
			  if(value.length > 100){
				  return '权限长度不能超过100';
			  }
	    },
		privAction: function(value){
			  if(value.length > 100){
				  return '权限Action长度不能超过100';
			  }
	    },
		privIcon: function(value){
			  if(value.length > 50){
				  return '权限ICON长度不能超过50';
			  }
	    },
		privSuper: function(value){
			  if(value.length > 19){
				  return '上级权限长度不能超过19';
			  }
	    },
	});
	exports("systemPriv",obj);
});