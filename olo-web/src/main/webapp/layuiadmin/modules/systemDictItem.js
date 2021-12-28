layui.define(['table','form','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	form = layui.form,
	laydate = layui.laydate;
	
	//系统字典项-表格渲染
	table.render({
		elem: '#LAY-systemDictItem-manage',
		url: 'index/do.jhtm?dictId='+$("#dictId").val(),
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
			  {field: 'id', title: '编号' ,sort: true,width:'5%'},
			  /*{field: 'dictId', title: '字典编号' ,sort: true,width:'5%'},*/
			  {field: 'dictName', title: '字典名称' ,sort: true,width:'8%'},
			  {field: 'itemKey', title: '字典项Key' ,sort: true,width:'7%'},
			  {field: 'itemVal', title: '字典项Val' ,sort: true,width:'8%'},
			  {field: 'existSuper', title: '是否存在父项' ,templet:'#yesNoTpl',sort: true,width:'6%'},
			  /*{field: 'superId', title: '父项编号' ,sort: true,width:'10%'},*/
			  {field: 'superName', title: '父项名称' ,sort: false,width:'6%'},
			  {field: 'sortVal', title: '排序' ,sort: true,width:'5%'},
			  {field: 'creator', title: '创建人员' ,sort: true,width:'7%'},
			  {field: 'updator', title: '更新人员' ,sort: true,width:'7%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'6%'},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-systemDictItem-manager-operator',width:'15%'}
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
	table.on('tool(LAY-systemDictItem-manage)', function(obj){
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
						//obj.del();
						obj.update({status:1});//修改一个字段
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
			  title: '编辑字典项',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-systemDictItem-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-systemDictItem-manage'); //数据刷新
					  layer.close(index); //关闭弹层
				  });
				  submit.trigger('click');
			  },
			  success: function(layero, index){
			  }*/
		  });
	  }
  });
  
	form.on('select(existSuper)',function(data){
		//	0:否	1：是 
        var value=data.value;
        if(1 == value){
        	$('#superIdDiv').removeClass('layui-hide');
        }else
        {
        	$('#superIdDiv').addClass('layui-hide');
        }
	});
	
	$(document).ready(function(){
		var exist = $('#existSuper').val();
		if(1 == exist)$('#superIdDiv').removeClass('layui-hide');
	});
  //监听搜索
  form.on('submit(LAY-systemDictItem-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-systemDictItem-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-systemDictItem-manage'),
		checkData = checkStatus.data; //得到选中的数据
		if(checkData.length === 0){
	  		return layer.msg('请选择数据');
		}
		var ids = new Array();
		for(var i=0;i<checkData.length;i++)
		{
			ids.push(checkData[i].id);
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
						table.reload('LAY-systemDictItem-manage'); //数据刷新
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
			title: '添加字典项',
			content: ['add.jhtm?dictId='+$('#dictId').val(),'no'],
			area: ['800px', '550px'],
			maxmin: true
		});
	  }
  }
  
  //---------表头按钮触发激活----------
  $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
  	var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
  /* 更新页面-------->监听提交 */
  form.on('submit(LAY-systemDictItem-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-systemDictItem-manage'); //重载表格
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
    
  form.on('submit(LAY-systemDictItem-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-systemDictItem-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-systemDictItem-manage'); //重载表格
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
    
  /*laydate.render({
  	elem: '#LAY-systemDictItem_xxx_date'
  });*/
    
  //自定义验证
  form.verify({
		dictId: function(value){
			if(value.length > 19){
				return '字典id长度不能超过19';
			}
	    },
		itemKey: function(value){
			if(value.length > 100){
				return '字典项Key长度不能超过100';
			}
	    },
		itemVal: function(value){
			if(value.length > 200){
				return '字典项Val长度不能超过200';
			}
	    },
		sortVal: function(value){
			if(value.length > 10){
				return '排序长度不能超过10';
			}
	    },
		remark: function(value){
			if(value.length > 255){
				return '描述长度不能超过255';
			}
	    }
  });
  exports('systemDictItem', {})
});