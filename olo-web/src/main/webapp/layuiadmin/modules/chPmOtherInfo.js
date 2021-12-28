layui.define(['table','form','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form;
	
	//其他信息-表格渲染
	table.render({
		elem: '#LAY-chPmOtherInfo-manage',
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
			  {field: 'epidsPrecFlHcStaff', title: '是否疫情防控一线医护人员  0:否 1:是' ,width:'5%'},
			  {field: 'honMoneyGotTime', title: '荣誉金取得时间' ,width:'5%'},
			  {field: 'caeWkYears', title: '核减工龄年限' ,width:'5%'},
			  {field: 'remark2', title: '备注2' ,width:'5%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmOtherInfo-manager-operator',width:'15%'}
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
	table.on('tool(LAY-chPmOtherInfo-manage)', function(obj){
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
			  title: '编辑其他信息',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmOtherInfo-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmOtherInfo-manage'); //数据刷新
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
  form.on('submit(LAY-chPmOtherInfo-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chPmOtherInfo-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chPmOtherInfo-manage'),
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
						table.reload('LAY-chPmOtherInfo-manage'); //数据刷新
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
			title: '添加其他信息',
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
  form.on('submit(LAY-chPmOtherInfo-edit-submit)', function(data){
  	$.ajax({
    	url:'edit/do.jhtm',
        method:'post',
        data:data.field,
        dataType:'json',
        success:function(data){
			var code = data.statusCode;
			var msg = data.message;
			parent.layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
        },
        error:function (data) {	
			parent.layer.msg(data.message, {offset: '150px',icon: 2,time: 2000});
        }
  	});
    return false;
  });
    
  form.on('submit(LAY-chPmOtherInfo-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmOtherInfo-add-submit)', function(data){
  	$.ajax({
    	url:'add/do.jhtm',
        method:'post',
        data:data.field,
        dataType:'json',
        success:function(data){
			var code = data.statusCode;
			var msg = data.message;
			var id = data.data;
			//layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
			parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
			if("200" == code)
			{
				//parent.layui.table.reload('LAY-chPmOtherInfo-manage'); //重载表格
				//parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
	          	//var index = parent.layer.getFrameIndex(window.name);
	          	//parent.layer.close(index);
				
				$("#id").val(id);
				//调用父页面函数，删除tab
				//parent.tools.delTab(10);
			}
        },
        error:function (data) {
			parent.layer.msg(data.message, {offset: '150px',icon: 1,time: 2000});
        }
	});
    return false;
  });
    
  //循环遍历渲染日期组件（所有class包含time的组件）
  $(function(){
	  $('.time').each(function(index){
	        var _this=this;
	        laydate.render({
	            elem: _this,
	            done: function(value, date){
	 
	            }
	        });
	  }); 
  })
  /*laydate.render({
  	elem: '#LAY-chPmOtherInfo_xxx_date'
  });*/
  
  form.on('select(isDisability)',function(data){
	  var value=data.value;
      if("" == value){
      	$("#disabilityLev").val("");
      	return;
      }
      if(1 == value){
      	$('#disabilityLevDiv').removeClass('layui-hide');
      }else
      {
      	$('#disabilityLevDiv').addClass('layui-hide');
      	$("#disabilityLev").val("");
      }
  });
  
  form.on('select(isInjryOnJob)',function(data){
	  var value=data.value;
      if("" == value){
      	$("#injryLev").val("");
      	return;
      }
      if(1 == value){
      	$('#injryLevDiv').removeClass('layui-hide');
      }else
      {
      	$('#injryLevDiv').addClass('layui-hide');
      	$("#injryLev").val("");
      }
  });
    
  //自定义验证
  form.verify({
		workerId: function(value){
			if(null == value)
				return '职工编号不能为空!';
			if(value.length > 19)
				return '职工编号长度不能超过19';
	    },
		epidsPrecFlHcStaff: function(value){
			if(value.length > 10)
				return '是否疫情防控一线医护人员长度不能超过10';
	    },
		honMoneyGotTime: function(value){
			if(value.length > 26)
				return '荣誉金取得时间长度不能超过26';
	    },
		caeWkYears: function(value){
			if(value.length > 10)
				return '核减工龄年限长度不能超过10';
	    },
	    number:[/(^$)|^\d+$/,'只能填写数字'],
	    disabilityLev: function(value){
	    	if(value.length >50)
	    		return '残疾鉴定级别长度不能超过50';
	    },
	    injryLev: function(value){
	    	if(value.length >50)
	    		return '工伤鉴定级别长度不能超过50';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注1长度不能超过100';
	    },
		remark2: function(value){
			if(value.length > 100)
				return '备注2长度不能超过100';
	    },
  });
  exports('chPmOtherInfo', {})
});