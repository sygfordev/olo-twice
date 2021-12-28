layui.define(['table','form','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	form = layui.form;
	
	//职务信息头表-表格渲染
	table.render({
		elem: '#LAY-chPmPositHead-manage',
		url: 'index/do.jhtm',
		/*parseData: function(res){
		    return {
		    	"code": "200"==res.retCode?0:1,
		    	"msg": res.retMsg,
		    	"count": res.count,
		    	"data": res.list
		    };
		},*/
		where: {
			workerId: $("#workerId").val()
	    },
	    cols: 
	    [
	  	  [
	  		  {type: 'checkbox', fixed: 'left'},
			  {field: 'id', title: '自增主键' ,sort: true,width:'5%'},
			  {field: 'workerId', title: '职工编号' ,sort: true,width:'5%'},
			  {field: 'posit4now', title: '现任职务' ,sort: true,width:'5%'},
			  {field: 'positLevel4now', title: '现级别（正科|副科等的编号）' ,sort: true,width:'5%'},
			  {field: 'posit4nowStartTime', title: '现任职务开始时间' ,sort: true,width:'5%'},
			  {field: 'posit4nowEndTime', title: '现任职务结束时间' ,sort: true,width:'5%'},
			  {field: 'treatLevel4now', title: '现待遇级别（正科级|副科级等的编号）' ,sort: true,width:'5%'},
			  {field: 'treat4nowStartTime', title: '现待遇级别开始时间' ,sort: true,width:'5%'},
			  {field: 'treat4nowEndTime', title: '现待遇级别结束时间' ,sort: true,width:'5%'},
			  {field: 'treat4nowYears', title: '现待遇级别年限' ,sort: true,width:'5%'},
			  {field: 'onChuStartTime', title: '任正处开始时间' ,sort: true,width:'5%'},
			  {field: 'onChuEndTime', title: '任正处结束时间' ,sort: true,width:'5%'},
			  {field: 'onChuDetupyStartTime', title: '任副处开始时间' ,sort: true,width:'5%'},
			  {field: 'onChuDetupyEndTime', title: '任副处结束时间' ,sort: true,width:'5%'},
			  {field: 'onKeStartTime', title: '任正科开始时间' ,sort: true,width:'5%'},
			  {field: 'onKeEndTime', title: '任正科结束时间' ,sort: true,width:'5%'},
			  {field: 'onKeDetupyStartTime', title: '任副科开始时间' ,sort: true,width:'5%'},
			  {field: 'onKeDetupyEndTime', title: '任副科结束时间' ,sort: true,width:'5%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmPositHead-manager-operator',width:'15%'}
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
	table.on('tool(LAY-chPmPositHead-manage)', function(obj){
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
			  title: '编辑角色',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmPositHead-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmPositHead-manage'); //数据刷新
					  layer.close(index); //关闭弹层
				  });
				  submit.trigger('click');
			  },
			  success: function(layero, index){
			  }*/
		  });
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-chPmPositHead-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chPmPositHead-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chPmPositHead-manage'),
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
						table.reload('LAY-chPmPositHead-manage'); //数据刷新
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
			title: '添加职务信息头表',
			content: ['add.jhtm','no'],
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
  form.on('submit(LAY-chPmPositHead-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmPositHead-manage'); //重载表格
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
    
  form.on('submit(LAY-chPmPositHead-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmPositHead-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmPositHead-manage'); //重载表格
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
    
  laydate.render({
  	elem: '#LAY-chPmPositHead_xxx_date'
  });
    
  //自定义验证
  form.verify({
		workerId: function(value){
			if(null == value || "".equals(value))
				return '职工编号不能为空!';
			if(value.length > 19)
				return '职工编号长度不能超过19';
	    },
		posit4now: function(value){
			if(null == value || "".equals(value))
				return '现任职务不能为空!';
			if(value.length > 10)
				return '现任职务长度不能超过10';
	    },
		positLevel4now: function(value){
			if(null == value || "".equals(value))
				return '现级别（正科|副科等的编号）不能为空!';
			if(value.length > 10)
				return '现级别（正科|副科等的编号）长度不能超过10';
	    },
		posit4nowStartTime: function(value){
			if(null == value || "".equals(value))
				return '现任职务开始时间不能为空!';
			if(value.length > 26)
				return '现任职务开始时间长度不能超过26';
	    },
		posit4nowEndTime: function(value){
			if(null == value || "".equals(value))
				return '现任职务结束时间不能为空!';
			if(value.length > 26)
				return '现任职务结束时间长度不能超过26';
	    },
		treatLevel4now: function(value){
			if(null == value || "".equals(value))
				return '现待遇级别（正科级|副科级等的编号）不能为空!';
			if(value.length > 10)
				return '现待遇级别（正科级|副科级等的编号）长度不能超过10';
	    },
		treat4nowStartTime: function(value){
			if(null == value || "".equals(value))
				return '现待遇级别开始时间不能为空!';
			if(value.length > 26)
				return '现待遇级别开始时间长度不能超过26';
	    },
		treat4nowEndTime: function(value){
			if(null == value || "".equals(value))
				return '现待遇级别结束时间不能为空!';
			if(value.length > 26)
				return '现待遇级别结束时间长度不能超过26';
	    },
		treat4nowYears: function(value){
			if(null == value || "".equals(value))
				return '现待遇级别年限不能为空!';
			if(value.length > 10)
				return '现待遇级别年限长度不能超过10';
	    },
		onChuStartTime: function(value){
			if(null == value || "".equals(value))
				return '任正处开始时间不能为空!';
			if(value.length > 26)
				return '任正处开始时间长度不能超过26';
	    },
		onChuEndTime: function(value){
			if(null == value || "".equals(value))
				return '任正处结束时间不能为空!';
			if(value.length > 26)
				return '任正处结束时间长度不能超过26';
	    },
		onChuDetupyStartTime: function(value){
			if(null == value || "".equals(value))
				return '任副处开始时间不能为空!';
			if(value.length > 26)
				return '任副处开始时间长度不能超过26';
	    },
		onChuDetupyEndTime: function(value){
			if(null == value || "".equals(value))
				return '任副处结束时间不能为空!';
			if(value.length > 26)
				return '任副处结束时间长度不能超过26';
	    },
		onKeStartTime: function(value){
			if(null == value || "".equals(value))
				return '任正科开始时间不能为空!';
			if(value.length > 26)
				return '任正科开始时间长度不能超过26';
	    },
		onKeEndTime: function(value){
			if(null == value || "".equals(value))
				return '任正科结束时间不能为空!';
			if(value.length > 26)
				return '任正科结束时间长度不能超过26';
	    },
		onKeDetupyStartTime: function(value){
			if(null == value || "".equals(value))
				return '任副科开始时间不能为空!';
			if(value.length > 26)
				return '任副科开始时间长度不能超过26';
	    },
		onKeDetupyEndTime: function(value){
			if(null == value || "".equals(value))
				return '任副科结束时间不能为空!';
			if(value.length > 26)
				return '任副科结束时间长度不能超过26';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chPmPositHead', {})
});