layui.define(['table','form','laypage','treetable'],function (exports) {
	var $ = layui.jquery,
	form = layui.form,
  	table = layui.table,
  	layer = layui.layer,
  	laypage = layui.laypage,
  	treetable = layui.treetable;
	
	var tmp = {
			page : 1,
			limit : 10
	};
	
	var tree = {
       renderTable : function(params) {
    	   if(undefined==params || "" == params 
   				|| null == params){
    		   	params = {};
   				params.page = tmp.page;
   				params.limit = tmp.limit;
   		   }
    	   layer.load(2);
           treetable.render({
               treeColIndex: 1,//树形图标显示在第几列
               treeSpid: 1,//最上级的父级id
               treeIdName: 'id',//id字段的名称
               treePidName: 'parentId',//pid字段的名称
               treeDefaultClose: false,//是否默认折叠
               treeLinkage: true,//父级展开时是否自动展开所有子级
               elem: '#LAY-branchOffice-manage',
               url: 'index/do.jhtm',
               where:params,
               cols: [[
            	   {type: 'checkbox', fixed: 'left'},
               	   /*{type: 'numbers', title: '序号',width: 50},*/
                   {field: 'id', title: '机构编号'},
                   {field: 'branchName', title: '机构名称'},
                   {field: 'cityNo', title: '城市编号'},
                   {field: 'parentId', title: '父级ID'},
                   {field: 'email', title: '邮箱'},
                   {field: 'status', title: '状态', templet:'#statusTpl'},
                   {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-branchOffice-manager-operator'}
               ]],
               done: function (rData) {
                   layer.closeAll('loading');
                   if(true != params.doNotRenderPage || true == params.search)
                	   tree.pageRender(rData.count,params);
               }
           });
       },
       pageRender:function(rCount,rWhere)
       {
    	   laypage.render({
    		   elem: 'pageDiv',
    		   count: rCount,
    		   layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
    		   jump: function(obj, first){
    			   tmp.page = obj.curr;
    			   tmp.limit = obj.limit;
    			   rWhere.page = obj.curr;
    			   rWhere.limit = obj.limit;
    			   rWhere.doNotRenderPage = true;
    			   rWhere.search = false;
    			   //首次不执行
    			   if(!first){
    				   tree.renderTable(rWhere);
    			   }
    		   }
    	   });
       }
	}
	
	//监听搜索
	form.on('submit(LAY-branchOffice-search)', function(data){
		var field = data.field;
      	field.page = tmp.page;
      	field.limit = tmp.limit;
      	field.doNotRenderPage = true;//不执行分页脚本刷新
      	field.search = true;//不执行分页脚本刷新
      	tree.renderTable(field)
	});
	
	//触发三个button按钮
    $('#btn-expand').click(function () {
        treetable.expandAll('#LAY-branchOffice-manage');
    });

    $('#btn-fold').click(function () {
        treetable.foldAll('#LAY-branchOffice-manage');
    });

    $('#btn-refresh').click(function () {
    	tree.renderTable();
    });
    
    //监听工具条
	table.on('tool(LAY-branchOffice-manage)', function(obj){
	  var data = obj.data;
	  if(obj.event === 'del'){
		  layer.confirm('确定删除么', function(index){
  			  $.ajax({
	             url:'delete.jhtm',
	             method:'post',
	             data:{"id":data.id},
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
	  } else if(obj.event === 'edit'){
		  var tr = $(obj.tr);
		  layer.open({
			  type: 2,
			  title: '编辑机构',
			  content: ['edit.jhtm?id='+data.id],
			  maxmin: true,
			  area: ['800px', '550px']
		  });
	  }
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
    form.on('submit(LAY-branchOffice-add-submit)', function(data){
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
				//parent.layui.table.reload('LAY-branchOffice-manage'); //重载表格
				parent.layui.$("#btn-refresh").click();//触发父页面中的刷新按钮
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
	form.on('submit(LAY-branchOffice-edit-submit)', function(data){
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
						var field = {};
				      	field.page = tmp.page;
				      	field.limit = tmp.limit;
				      	field.doNotRenderPage = true;//不执行分页脚本刷新
						tree.renderTable();
						//parent.layui.$("#btn-refresh").click();//触发父页面中的刷新按钮
						//branch.renderTable();//通过这种方式虽然可以重新调用接口，但页面刷新不了
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
	
	exports("branch",tree);
});