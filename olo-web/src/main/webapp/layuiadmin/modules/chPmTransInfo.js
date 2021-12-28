layui.define(['table','form','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	//excel = layui.excel,
	form = layui.form;
	
	//调动信息-表格渲染
	table.render({
		elem: '#LAY-chPmTransInfo-manage',
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
			  {field: 'name', title: '姓名' ,sort: true,width:'8%'},
			  {field: 'cardNo', title: '身份证号' ,sort: true,width:'10%'},
			  {field: 'departBefore', title: '原部门' ,sort: true,width:'5%',hide:true},
			  {field: 'departBeforeCn', title: '原部门' ,width:'8%'},
			  {field: 'positBefore', title: '原岗位' ,sort: true,width:'8%',hide:true},
			  {field: 'positBeforeCn', title: '原岗位' ,sort: true,width:'8%'},
			  {field: 'departAfter', title: '新部门' ,sort: true,width:'5%',hide:true},
			  {field: 'departAfterCn', title: '新部门' ,width:'8%'},
			  {field: 'positAfter', title: '新岗位' ,sort: true,width:'8%',hide:true},
			  {field: 'positAfterCn', title: '新岗位' ,sort: true,width:'8%'},
			  {field: 'transDocno', title: '调动文号' ,sort: true,width:'8%'},
			  {field: 'transTime', title: '调动时间' ,sort: true,templet: '<div>{{#if(d.transTime){}} {{layui.util.toDateString(d.transTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'10%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmTransInfo-manager-operator',width:'15%'}
		  ]
	    ],
	    page: true,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    /*toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
	    defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
	        title: '提示'
	        ,layEvent: 'LAYTABLE_TIPS'
	        ,icon: 'layui-icon-tips'
	    }],*/
	    text: {
	        none: '暂无相关数据'
	    }
	});
	
	//头工具栏事件
	/*table.on('toolbar(LAY-chPmTransInfo-manage)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    switch(obj.event){
	      case 'getCheckData':
	        var data = checkStatus.data;
	        layer.alert(JSON.stringify(data));
	        break;
	      case 'getCheckLength':
	        var data = checkStatus.data;
	        layer.msg('选中了：'+ data.length + ' 个');
	        break;
	      case 'isAll':
	        layer.msg(checkStatus.isAll ? '全选': '未全选');
	        break;
	      //导出
	      case 'LAYTABLE_EXPORT':
	    	  //遍历列头，找到实际操作的列信息列表
	    	  var headers = new Array(50);
		  	  table.eachCols('LAY-chPmTransInfo-manage',function(index,item){
		  	  	if(true != item.hide && "操作" != item.title && "checkbox" != item.type){
		  	  		//console.log("index:"+index+"--item:"+JSON.stringify(item));
		  	  		headers[index] = item.title;
		  	  	}
		  	  });
		  	  var headers = headers.filter(s => $.trim(s).length > 0);
	    	  layui.excel.exportExcel([headers], '表格导出.xlsx', 'xlsx');
	    	  break;
	      //自定义头工具栏右侧图标 - 提示
	      case 'LAYTABLE_TIPS':
	        layer.alert('这是工具栏右侧自定义的一个图标按钮');
	        break;
	    };
	    //遍历列头，找到实际操作的列信息列表
	    table.eachCols('LAY-chPmTransInfo-manage',function(index,item){
	    	if(true != item.hide && "操作" != item.title){
	    	console.log("index:"+index+"--item:"+JSON.stringify(item));
	    	}
	    });
	});*/
	//监听工具条
	table.on('tool(LAY-chPmTransInfo-manage)', function(obj){
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
			  title: '编辑调动信息',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmTransInfo-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmTransInfo-manage'); //数据刷新
					  layer.close(index); //关闭弹层
				  });
				  submit.trigger('click');
			  },
			  success: function(layero, index){
			  }*/
		  });
	  }else if(obj.event == 'details')
	  {
		  layer.open({
			  type: 2,
			  title: '查看调动信息',
			  content: ['details.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  btn: ['关闭']
		  });
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-chPmTransInfo-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chPmTransInfo-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chPmTransInfo-manage'),
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
						table.reload('LAY-chPmTransInfo-manage'); //数据刷新
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
			title: '添加调动信息',
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
  form.on('submit(LAY-chPmTransInfo-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmTransInfo-manage'); //重载表格
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
    
  form.on('submit(LAY-chPmTransInfo-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmTransInfo-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmTransInfo-manage'); //重载表格
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
  	elem: '#LAY-chPmTransInfo_xxx_date'
  });*/
  
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
  //开始日期
  var insStart = laydate.render({
      elem: '#trans-start'
      ,min: -365*3
      ,done: function(value, date){
        //更新结束日期的最小日期
        insEnd.config.min = lay.extend({}, date, {
          month: date.month - 1
        });
        
        //自动弹出结束日期的选择器
       insEnd.config.elem[0].focus();
      }
  });
    
  //结束日期
  var insEnd = laydate.render({
      elem: '#trans-end'
      ,min: 0
      ,done: function(value, date){
        //更新开始日期的最大日期
        insStart.config.max = lay.extend({}, date, {
          month: date.month - 1
        });
      }
  });
  
  //循环监听所有需要获取select对应option.text下拉框
  var selectListenIds = ['departBefore','positBefore','departAfter','positAfter'];
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
    
  //自定义验证
  form.verify({
		id: function(value){
			if(null == value)
				return '自增主键不能为空!';
			if(value.length > 19)
				return '自增主键长度不能超过19';
	    },
		name: function(value){
			if(null == value)
				return '姓名不能为空!';
			if(value.length > 100)
				return '姓名长度不能超过100';
	    },
		cardNo: function(value){
			if(null == value)
				return '身份证号不能为空!';
			if(value.length > 25)
				return '身份证号长度不能超过25';
	    },
	    cardNo:[/(^$)|(^\d{15}$)|(^\d{17}(x|X|\d)$)/,'请输入正确的身份证号'],
		departBefore: function(value){
			if(null == value)
				return '原部门不能为空!';
			if(value.length > 10)
				return '原部门长度不能超过10';
	    },
		departBeforeCn: function(value){
			if(null == value)
				return '原部门不能为空!';
			if(value.length > 50)
				return '原部门长度不能超过50';
	    },
		positBefore: function(value){
			if(null == value)
				return '原岗位不能为空!';
			if(value.length > 100)
				return '原岗位长度不能超过100';
	    },
		departAfter: function(value){
			if(null == value)
				return '新部门不能为空!';
			if(value.length > 10)
				return '新部门长度不能超过10';
	    },
		departAfterCn: function(value){
			if(null == value)
				return '新部门不能为空!';
			if(value.length > 50)
				return '新部门长度不能超过50';
	    },
		positAfter: function(value){
			if(null == value)
				return '新岗位不能为空!';
			if(value.length > 100)
				return '新岗位长度不能超过100';
	    },
		transDocno: function(value){
			if(null == value)
				return '调动文号不能为空!';
			if(value.length > 20)
				return '调动文号长度不能超过20';
	    },
		transTime: function(value){
			if(null == value)
				return '调动时间不能为空!';
			if(value.length > 26)
				return '调动时间长度不能超过26';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chPmTransInfo', {})
});