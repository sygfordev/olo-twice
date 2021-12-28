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
		elem: '#LAY-userRolePrivCfg-manage',
		url: 'userRolePrivCfg/loadList.jhtm',
		lodding:true,
		where: {userId: $('#userId').val()},
		method:'post',
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
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-role-manager-operator',width: '25%'}
		  ]
	    ],
	    text: {
	        none: '暂无相关数据'
	    }
	});
  
	//监听工具条
	table.on('tool(LAY-userRolePrivCfg-manage)', function(obj){
	  var data = obj.data;
	  if(obj.event === 'del'){
		  layer.confirm('确定删除么', function(index){
  			  $.ajax({
	             url:'userRoleDelete.jhtm',
	             method:'post',
	             data:{"userId":$("#userId").val(),"roleId":data.roleId},
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
	  } else if(obj.event === 'userRolePrivCfg'){
		  var tr = $(obj.tr);
		  layer.open({
			  type: 2,
			  title: '编辑用户权限',
			  content: ['userRolePrivEdit.jhtm?userId='+$('#userId').val()+'&roleId='+data.roleId,'no'],
			  maxmin: true,
			  area: ['1000px', '800px'],
			  btn: ['确定', '取消'],
			  yes: function(index, layero){
				  	var frameId = $(layero).find("iframe").attr('id');
				  	var userId = $(window.frames[frameId].document).find("#userId").val();
					var roleId = $(window.frames[frameId].document).find("#roleId").val();
					var selectedIds = $(window.frames[frameId].document).find("#selectedPrivIds").val();
					$.ajax({
					      url: 'userRolePrivEdit/do.jhtm',
					      method:'post',
					      data: {"userId":userId,"roleId":roleId,"systemPrivList":selectedIds.split(",").map(Number)},
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
	  }
  });
  
	//数据表格区域头部操作按钮事件
	var active = {
  		add: function(){
   			layer.open({
 				type: 2,
 				title: '配置角色',
 				content: ['userRoleAdd.jhtm?userId='+$('#userId').val(),'no'],
 				area: ['750px', '350px'],
 				maxmin: true,
 				btn: ['确定', '取消'],
 				yes: function(index, layero){
 					var frameId = $(layero).find("iframe").attr('id');
 					var roleId = $(window.frames[frameId].document).find("#selectedRoleId").val();
 					if(null == roleId) {
 						layer.msg("请选择角色编号！", {offset: '150px',icon:2,time: 2000});
 						return;
 					}
 					$.ajax({
			             url:'userRoleAdd/do.jhtm',
			             method:'post',
			             data:{"roleId":roleId,"userId":$('#userId').val()},
			             dataType:'json',
			             success:function(res){
							var code = res.statusCode;
							var msg = res.message;
							var isucc = "200"==code?true:false;
							layer.msg(msg, {offset: '150px',icon: isucc?1:2,time: 2000});
							if(isucc)
							{
								layer.close(index);
								table.reload('LAY-userRolePrivCfg-manage'); //数据刷新
							}
			             },
			             error:function (res) {
							layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
			             }
 					});
 				}
   			});
  		},
  		userRolePrivCfg: function()
  		{
  			var checkStatus = table.checkStatus('LAY-role-manage'),
    		checkData = checkStatus.data; //得到选中的数据
    		if(checkData.length == 0){
      			return layer.msg('请选择数据');
    		}
    		var ids = new Array();
    		for(var i=0;i<checkData.length;i++)
    		{
    			ids.push(checkData[i].roleId);
    		}
    		if(ids.length != 1){
      			return layer.msg('请选择单条用户');
    		}
  			layer.open({
 				type: 2,
 				title: '权限配置',
 				content: ['rolePrivCfg.jhtm?roleId='+ids[0],'no'],
 				area: ['1000px', '800px'],
 				maxmin: true,
 				btn: ['取消']//'保存'
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
	exports('userRolePrivCfg ', {})
});