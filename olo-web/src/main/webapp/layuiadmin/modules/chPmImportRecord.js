layui.define(['table','form','layer','laydate','laypage','upload'], function(exports){
	var $ = layui.$,
	table = layui.table,
	layer = layui.layer,
	laypage = layui.laypage,
	laydate = layui.laydate,
	upload = layui.upload,
	form = layui.form;
	
	//人事档案导入记录表-表格渲染
	table.render({
		elem: '#LAY-chPmImportRecord-manage',
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
			  {field: 'id', title: '编号' ,sort: true,width:'5%'},
			  {field: 'batchNo', title: '批次号' ,sort: true,width:'10%'},
			  {field: 'batchType', title: '批次类型' ,sort: true,width:'10%',hide:true},
			  {field: 'batchTypeCn', title: '批次类型名称' ,width:'10%'},
			  {field: 'batchUser', title: '操作人' ,width:'10%'},
			  {field: 'sucNum', title: '成功数量' ,width:'10%'},
			  {field: 'faiNum', title: '失败数量' ,width:'10%'},
			  {field: 'batchMsg', title: '批次消息' ,sort: true,width:'5%',hide:true},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'15%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'15%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmImportRecord-manager-operator',width:'20%'}
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
	table.on('tool(LAY-chPmImportRecord-manage)', function(obj){
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
			  title: '编辑导入记录',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmImportRecord-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmImportRecord-manage'); //数据刷新
					  layer.close(index); //关闭弹层
				  });
				  submit.trigger('click');
			  },
			  success: function(layero, index){
			  }*/
		  });
	  }else if(obj.event === 'details'){
		  var tr = $(obj.tr);
		  layer.open({
			  type: 2,
			  title: '查看导入记录',
			  content: ['details.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  btn: ['关闭']
		  });
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-chPmImportRecord-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chPmImportRecord-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chPmImportRecord-manage'),
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
						table.reload('LAY-chPmImportRecord-manage'); //数据刷新
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
			title: '人事档案导入',
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
  form.on('submit(LAY-chPmImportRecord-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmImportRecord-manage'); //重载表格
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
    
  form.on('submit(LAY-chPmImportRecord-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  /*form.on('submit(LAY-chPmImportRecord-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmImportRecord-manage'); //重载表格
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
  });*/
  
  form.on('submit(LAY-chPmImportRecord-add-submit)', function(data){
	  //eg1
	  //var index = layer.load();
	  //eg2
	  //var index = layer.load(1); //换了种风格
	  //eg3
	  var index = layer.load(2, {time: 60*1000}); //又换了种风格，并且设定最长等待10秒 
	  //关闭
	  //layer.close(index); 
	  //将提交按钮置为不可操作
	  $('#LAY-chPmImportRecord-add-submit').addClass("layui-btn-disabled").attr("disabled",true);
  });  
  
  laydate.render({
  	elem: '#LAY-chPmImportRecord_xxx_date'
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
  
  var selectListenIds = ['batchType'];
  selectListenIds.forEach((elem, index) => {
      form.on('select('+elem+')',function(data){
    	  var key=data.value;
          var val = data.elem[data.elem.selectedIndex].text;
          if("" == key){
        	  $("#"+elem+"Cn").val("");
          	return;
          }
          $("#"+elem+"Cn").val(val);
      });
  });
  
  //导入文件
  upload.render({
    	elem: '#test-upload-type1'
    	,url: 'doImport.jhtm' //改成您自己的上传接口
    	,accept: 'file' //普通文件
    	,exts: 'xls|xlsx' //只允许上传excel
    	//,size: 2048	
    	,multiple: false
    	,auto: false
    	,bindAction: '#LAY-chPmImportRecord-add-submit'
    	,before: function(input){
    		//返回的参数item，即为当前的input DOM对象
    		console.log('文件上传中');
    		//layer.msg($("#batchType").val()+"--"+$("#batchType").find("option:selected").text());
    		return;
    	},data: {//额外传输的参数
    		  batchType: function () {
		            return $("#batchType").val();
		        },
		      batchTypeCn: function(){
		  		  return $("#batchType").find("option:selected").text();
		  	  },
		  	  status:0
    	},choose: function (obj) {
    		var code = $("#batchType").val();
    		if(!$.trim(code))
    		{
    			 parent.layer.msg("请先选择导入模块！", {time: 2000});
    			 return;
    		}
    	},done: function(res){
    		console.log(JSON.stringify(res));
    		var status = res.statusCode;
    		var msg = res.message;
    		if(200 == status){
    			parent.layui.table.reload('LAY-chPmImportRecord-manage'); //重载表格
    			parent.layer.msg(msg, {offset: '150px',icon: 1,time: 4000});
    			var index = parent.layer.getFrameIndex(window.name);
    	        parent.layer.close(index);
    		}else{
    			parent.layer.msg(msg, {offset: '150px',icon: 2,time: 4000});
    			layer.closeAll('loading');
    			//将提交按钮置变为可操作
        		$('#LAY-chPmImportRecord-add-submit').removeClass("layui-btn-disabled").attr("disabled",false);
    		}
    		/*setTimeout(function(){
    		  layer.closeAll('loading');
    		}, 2000);*/
    		
    	},allDone: function (res) {
    		var succ = res.successful;
    		var fail = res.aborted;
    		var total = res.total;
    		var msg = "成功:"+total+"个,失败:"+fail+",共:"+total+"个";
    	},error: function (index, upload) {
            console.log("error");
            layer.closeAll('loading');
            $('#LAY-chPmImportRecord-add-submit').removeClass("layui-btn-disabled").attr("disabled",false);
        }
  });
    
  //自定义验证
  form.verify({
		batchNo: function(value){
			if(null == value)
				return '批次号不能为空!';
			if(value.length > 20)
				return '批次号长度不能超过20';
	    },
		batchType: function(value){
			if(null == value)
				return '批次类型不能为空!';
			if(value.length > 10)
				return '批次类型长度不能超过10';
	    },
		batchTypeCn: function(value){
			if(value.length > 50)
				return '批次类型名称长度不能超过50';
	    },
		batchUser: function(value){
			if(value.length > 100)
				return '操作人长度不能超过100';
	    },
		sucNum: function(value){
			if(value.length > 19)
				return '成功数量长度不能超过19';
	    },
		faiNum: function(value){
			if(value.length > 19)
				return '失败数量长度不能超过19';
	    },
		batchMsg: function(value){
			if(null == value)
				return '批次消息不能为空!';
			if(value.length > 500)
				return '批次消息长度不能超过500';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chPmImportRecord', {})
});