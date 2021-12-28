/**

 @Name：layuiAdmin 用户管理 管理员管理 角色管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */


layui.define(['table','form','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	form = layui.form;
	
	//用户管理-表格渲染
	table.render({
		elem: '#LAY-role-manage',
		url: 'index/do.jhtm',
		/*parseData: function(res){
		    return {
		    	"code": "200"==res.retCode?0:1,
		    	"msg": res.retMsg,
		    	"count": res.count,
		    	"data": res.list
		    };
		},*/
	    cols: 
	    [
	  	  [
	  		  {type: 'checkbox', fixed: 'left'},
		      {field: 'roleId', title: '角色ID', sort: true,width: '10%'},
		      {field: 'roleName', title: '角色名称',width: '15%'},
		      {field: 'roleLevel', title: '角色级别',sort:true,width: '10%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width: '10%'},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width: '15%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width: '15%'},
		      {title: '操作', align: 'center', fixed: 'right', toolbar: '#table-role-manager-operator',width: '25%'}
		  ]
	    ],
	    page: true,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});
  
	//监听工具条
	table.on('tool(LAY-role-manage)', function(obj){
	  var data = obj.data;
	  var event = obj.event;
	  switch(event)
	  {
	  case 'del':
		  layer.confirm('确定删除么', function(index){
  			  $.ajax({
	             url:'delete.jhtm',
	             method:'post',
	             data:{"roleId":data.roleId},
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
		  var tr = $(obj.tr);
		  layer.open({
			  type: 2,
			  title: '编辑角色',
			  content: ['edit.jhtm?roleId='+data.roleId,'no'],
			  maxmin: true,
			  area: ['800px', '550px']
		  });
		  break;
	  case 'rolePrivCfg':
	  	  layer.open({
	  		  type: 2,
	  		  title: '权限配置',
	  		  content: ['rolePrivCfg.jhtm?roleId='+data.roleId,'no'],
	  		  area: ['1000px', '800px'],
	  		  maxmin: true,
	  		  btn: ['保存','取消'],
			  yes: function(index, layero) {
					var frameId = $(layero).find("iframe").attr('id');
					var roleId = $(window.frames[frameId].document).find("#roleId").val();
					var selectedIds = $(window.frames[frameId].document).find("#selectedPrivIds").val();
					var isAppend = $(window.frames[frameId].document).find('input[name="isAppend"]:checked').val();
					$.ajax({
					      url: 'rolePrivCfg/do.jhtm',
					      method:'post',
					      data: {"roleId":roleId,"systemPrivList":selectedIds.split(",").map(Number),"isAppend":isAppend},
					      dataType: 'json',
					      success: function(res){
					    	  layer.msg(res.message, {offset: '150px',icon: "200"==res.statusCode?1:2,time: 2000});
					          layer.close(index);
					      },
			           	  error:function (res) {
							  layer.msg(null==res.message?"请求异常":res.message, {offset: '150px',icon: 2,time: 2000});
			           	  }
					});
			  }
 		  });
		  break;
	  case 'userRoleCfg':
  		  layer.open({
			type: 2,
			title: '用户配置',
			content: ['userRoleCfg.jhtm?roleId='+data.roleId,'no'],
			area: ['1000px', '800px'],
			maxmin: true,
			btn: ['保存', '取消'],
			yes: function(index, layero) {
				var frameId = $(layero).find("iframe").attr('id');
				var roleId = $(window.frames[frameId].document).find("#roleId").val();
				var selectedIds = $(window.frames[frameId].document).find("#selectedIds").val();
				//alert(selectedIds);
				//layer.close(index);
				$.ajax({
		           	url:'userRoleCfg/do.jhtm',
		           	method:'post',
		           	data:{roleId:roleId,selectedIds:selectedIds},
		           	dataType:'json',
		           	success:function(res){
						var code = res.statusCode;
						var msg = res.message;
						parent.layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
						if("200" == code) layer.close(index);
		           	},
		           	error:function (res) {
						layer.msg(res.message, {offset: '150px',icon: 2,time: 2000});
		           	}
		      });
			}
 		  });
		  break;
	  }
  });
  
	//监听搜索
	form.on('submit(LAY-role-search)', function(data){
      	var field = data.field;
      	//执行重载
      	table.reload('LAY-role-manage', {where: field});
	});

	//数据表格区域头部操作按钮事件
	var active = {
  		batchdel: function(){
    		var checkStatus = table.checkStatus('LAY-role-manage'),
    		checkData = checkStatus.data; //得到选中的数据
    		if(checkData.length === 0){
      			return layer.msg('请选择数据');
    		}
    		var ids = new Array();
    		for(var i=0;i<checkData.length;i++)
    		{
    			ids.push(checkData[i].roleId);
    		}
    		layer.confirm('确定删除吗？', function(index) {
	            //执行 Ajax 后重载
	           $.ajax({
		             url:'batchDel.jhtm',
		             method:'post',
		             data:{"ids":ids},
		             dataType:'json',
		             success:function(data){
						var code = data.statusCode;
						var msg = data.message;
						layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
						if("200" == code)
						{
							table.reload('LAY-role-manage'); //数据刷新
              				layer.close(index); //关闭弹层
						}
		             },
		             error:function (data) {
						layer.msg("删除失败！", {offset: '150px',icon: 2,time: 2000});
		             }
		        });
  			});
  		},
  		add: function(){
   			layer.open({
 				type: 2,
 				title: '添加角色',
 				content: ['add.jhtm','no'],
 				area: ['800px', '550px'],
 				maxmin: true,
 				btn: ['确定', '取消'],
 				yes: function(index, layero){
       				var iframeWindow = window['layui-layer-iframe'+ index],
       				submitID = 'LAY-role-add-submit',
       				submit = layero.find('iframe').contents().find('#'+ submitID);
       				//监听提交
       				iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
          				var field = data.field; //获取提交的字段
          				//提交 Ajax 成功后，静态更新表格中的数据
          				$.ajax({
				             url:'add/do.jhtm',
				             method:'post',
				             data:field,
				             dataType:'json',
				             success:function(data){
								var code = data.statusCode;
								var msg = data.message;
								layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
								if("200" == code)
								{
									table.reload('LAY-role-manage'); //数据刷新
		              				layer.close(index); //关闭弹层
								}
				             },
				             error:function (data) {
								layer.msg("添加失败！", {offset: '150px',icon: 2,time: 2000});
				             }
				        });
       				});
       				submit.trigger('click');
 				}
   			});
  		}
	}
	$('.layui-btn.layuiadmin-btn-admin').on('click', function(){
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	
	/* 监听提交 */
    form.on('submit(LAY-role-edit-submit)', function(data){
      $.ajax({
           url:'edit/do.jhtm',
           method:'post',
           data:data.field,
           dataType:'json',
           success:function(data){
			var code = data.statusCode;
			var msg = data.message;
			layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
			if("200" == code)
			{
				parent.layui.table.reload('LAY-role-manage'); //重载表格
				parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
	          	var index = parent.layer.getFrameIndex(window.name);
	          	parent.layer.close(index);
			}
           },
           error:function (data) {
			parent.layer.msg(data.message, {offset: '150px',icon: 2,time: 2000});
           }
      });
      return false;
    });
    
    form.on('submit(LAY-role-edit-cancel)', function(data){
    	var index = parent.layer.getFrameIndex(window.name);
      	parent.layer.close(index);
        return false;
    });
    /* 监听提交 */
    form.on('submit(LAY-role-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-role-manage'); //重载表格
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
    
  	//自定义验证
	form.verify({
	    roleName: function(value){
			  if(value.length > 30){
				  return '角色名称长度不能超过30';
			  }
	    },
	    remark: function(value){
			  if(value.length > 100){
				  return '备注长度不能超过100';
			  }
	    }
	});
	exports('systemRole', {})
});