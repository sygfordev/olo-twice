/**

 @Name：layuiAdmin 用户管理 管理员管理 角色管理
 @Author：star1029
 @Site：http://www.layui.com/admin/
 @License：LPPL
    
 */


layui.define(['table','form','laypage','laydate'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	setter = layui.setter,
	form = layui.form;
	
	form.render(null, 'user-add-form');
	laydate.render({
        elem: '#LAY-user-expire-date'
    });
	
	//用户管理-表格渲染
	table.render({
		elem: '#LAY-user-manage',
		url: 'index/do.jhtm',
		/*parseData: function(res){
		    return {
		    	"code": "200"==res.code?0:1,
		    	"msg": res.msg,
		    	"count": res.count,
		    	"data": res.data
		    };
		},*/
	    cols: 
	    [
	  	  [
	  		  {type: 'checkbox', fixed: 'left'},
		      {field: 'userId', title: '用户ID', sort: true,width: '5%'},
		      {field: 'account', title: '账户',width: '5%'},
		      {field: 'userRealName', title: '真实姓名',width: '8%'},
		      {field: 'userSex', title: '性别', templet: '#sexTpl',width: '5%'},
		      {field: 'userMobile', title: '手机号',width: '8%'},
		      {field: 'userEmail', title: '邮箱',width: '8%'},
		      {field: 'status', title:'状态', templet: '#statusTpl', width: '5%', align: 'center'},
		      {field: 'userExpireDate',title:'失效时间',sort:true, templet: '<div>{{ layui.util.toDateString(d.userExpireDate, "yyyy-MM-dd") }}</div>',width: '10%'},
		      {field: 'createTime',title:'创建时间',sort:true, templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width: '10%'},
		      {field: 'updateTime',title:'更新时间',sort:true, templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width: '10%'},
		      {title: '操作', width: 200, align: 'center', fixed: 'right', toolbar: '#table-user-manager-operator',width: '25%'}
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
	table.on('tool(LAY-user-manage)', function(obj){
	  var data = obj.data;
	  if(obj.event == 'del'){
		  layer.confirm('删除后数据无法恢复，确认删除么?', function(index){
  			  $.ajax({
	             url:'delete.jhtm',
	             method:'post',
	             data:{"userId":data.userId},
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
	  } else if(obj.event == 'edit'){
		  var tr = $(obj.tr);
		  layer.open({
			  type: 2,
			  title: '编辑用户',
			  content: ['edit.jhtm?userId='+data.userId,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  });
	  }else if(obj.event == 'editPwd')
	  {
		  layer.open({
			  type: 2,
			  title: '密码重置',
			  content: ['editPwd.jhtm?id='+data.userId,'no'],
			  maxmin: true,
			  area: ['800px', '350px']
		  });
	  }else if(obj.event == 'userRolePrivCfg')
	  {
		  //子页面打开新的tab页面
		  var l = parent === self ? layui : top.layui;
		  l.index.openTabsPage('central/userInfo/userRolePrivCfg.jhtm?userId='+data.userId, "角色配置");
	  }
  });
	
	//监听搜索
	form.on('submit(LAY-user-back-search)', function(data){
      	var field = data.field;
      	//执行重载
      	table.reload('LAY-user-manage', {where: field});
	});

	//---------表头按钮区域----------
	var active = {
  		batchdel: function(){
    		var checkStatus = table.checkStatus('LAY-user-manage'),
    		checkData = checkStatus.data; //得到选中的数据
    		if(checkData.length === 0){
      			return layer.msg('请选择数据');
    		}
    		var ids = new Array();
    		for(var i=0;i<checkData.length;i++)
    		{
    			ids.push(checkData[i].userId);
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
							table.reload('LAY-user-manage'); //数据刷新
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
 				title: '添加用户',
 				content: ['add.jhtm','no'],
 				area: ['800px', '550px'],
 				maxmin: true
   			});
  		}
	}
    $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
    	var type = $(this).data('type');
    	active[type] ? active[type].call(this) : '';
    });
	
	/* 监听提交 */
    form.on('submit(LAY-userinfo-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-user-manage'); //重载表格
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
    
    form.on('submit(LAY-userinfo-edit-cancel)', function(data){
    	var index = parent.layer.getFrameIndex(window.name);
      	parent.layer.close(index);
        return false;
    });
    
    $(document).on('click','#LAY-user-branch-name',function(){
    	var branchId = $('#LAY-user-branch').val();
    	var branchName = $('#LAY-user-branch-name').val();
 		layer.open({
 	    	type: 2,
 	        content: 'lookup.jhtm?branchId='+branchId+"&branchName="+encodeURI(branchName),
 	        area: ['400px', '500px'],
 	        resize:false,
 			btn: ['确定', '取消'],
 			yes: function(index, layero) {
				var frameId = $(layero).find("iframe").attr('id');
				var branchName = $(window.frames[frameId].document).find("#selectedName").val();
				var branchId = $(window.frames[frameId].document).find("#selectedId").val();
				$('#LAY-user-branch').val(branchId);
				$('#LAY-user-branch-name').val(branchName);
				layer.close(index);
			}
 	    });
 	});
    
    /* 监听提交 */
    form.on('submit(LAY-userinfo-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-user-manage'); //重载表格
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
		  account: function(value){
			  if(value.length > 20){
				  return '账户长度不能超过20';
			  }
	    },
	    realName: function(value){
			  if(value.length > 50){
				  return '真实名称长度不能超过50';
			  }
	    },
	    remark: function(value){
			  if(value.length > 100){
				  return '备注长度不能超过100';
			  }
	    },
	    pass: [/(.+){6,12}$/, '密码必须6到12位']
	});
	
    exports('userInfo', {})
});