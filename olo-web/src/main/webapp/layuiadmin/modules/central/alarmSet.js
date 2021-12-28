layui.define(['table','form','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laydate = layui.laydate,
	laypage = layui.laypage,
	form = layui.form;
	
	//警报设置-表格渲染
	table.render({
		elem: '#LAY-alarmSet-manage',
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
	  		  {type: 'checkbox', fixed: 'left',width:'3%'},
			  {field: 'id', title: '编号' ,sort: true,width:'7%'},
			  {field: 'alarmType', title: '警报类型' ,templet:alarmTypeFmt,sort: true,width:'10%'},
			  {field: 'alarmAdSize', title: '提前量' ,sort: true,width:'10%'},
			  {field: 'alarmAdUnit', title: '提前单位' ,templet:alarmAdUnitFmt,sort: true,width:'10%'},
			  {field: 'alarmTeInfo', title: '报警样板' ,sort: true,width:'20%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'10%'},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-alarmSet-manager-operator',width:'11%'}
		  ]
	    ],
	    page: true,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});
  
	function alarmTypeFmt(d)
    {
    	var result = '';
    	if(0==d.alarmType)
    		return '合同到期';
    	return result;
    }
	
	function alarmAdUnitFmt(d)
    {
    	var result = '';
    	if(0==d.alarmAdUnit)
    		return '天';
    	return result;
    }
	
	
	//监听工具条
	table.on('tool(LAY-alarmSet-manage)', function(obj){
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
			  title: '编辑警报设置',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-alarmSet-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-alarmSet-manage'); //数据刷新
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
			  title: '查看警报设置',
			  content: ['details.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  btn: ['关闭']
		  });
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-alarmSet-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-alarmSet-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-alarmSet-manage'),
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
						table.reload('LAY-alarmSet-manage'); //数据刷新
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
			title: '添加警报设置',
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
  form.on('submit(LAY-alarmSet-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-alarmSet-manage'); //重载表格
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
    
  form.on('submit(LAY-alarmSet-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-alarmSet-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-alarmSet-manage'); //重载表格
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
  	elem: '#LAY-alarmSet_xxx_date'
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
    
  //自定义验证
  form.verify({
		alarmType: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '警报类型不能为空!';
			if(value.length > 10)
				return '警报类型长度不能超过10';
	    },
		alarmAdSize: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '提前量不能为空!';
			if(value.length > 10)
				return '提前量长度不能超过10';
			/*if(/^[+]{0,1}(\d+)$/) {
				return '提前量必须为正整数！'
			}*/
	    },
		alarmAdUnit: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '提前单位不能为空!';
			if(value.length > 20)
				return '提前单位长度不能超过20';
	    },
		alarmTeInfo: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '警报样板不能为空!';
			if(value.length > 200)
				return '警报样板长度不能超过200';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
	    phoneRequiredFalse:[/(^$)|^1\d{10}$/,'请输入正确的手机号'],
        emailRequiredFalse:[/(^$)|^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,'邮箱格式不正确'],
        urlRequiredFalse:[/(^$)|(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/,'链接格式不正确'],
        numberRequiredFalse:[/(^$)|^\d+$/,'只能填写数字'],//非必填项，填了就会进行验证
        positiveNumberRequiredTrue:[/^(([0-9]+[\.]?[0-9]+)|[1-9])$/,'只能填写正数'],//必填，且只能输入正数
        positiveWholeNumberRequiredTrue:[/^[+]{0,1}(\d+)$/,'只能填写正整数'],//必填，且只能输入正整数
        dateRequiredFalse:[/(^$)|^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/,'日期格式不正确'],
        identityRequiredFalse:[/(^$)|(^\d{15}$)|(^\d{17}(x|X|\d)$)/,'请输入正确的身份证号']
  });
  exports('alarmSet', {})
});