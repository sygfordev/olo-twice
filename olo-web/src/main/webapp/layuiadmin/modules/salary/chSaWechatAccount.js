layui.define(['table','form','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laydate = layui.laydate,
	laypage = layui.laypage,
	form = layui.form;
	
	//微信访问账户-表格渲染
	table.render({
		elem: '#LAY-chSaWechatAccount-manage',
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
			  {field: 'id', title: '自增主键' ,sort: true,width:'5%'},
			  {field: 'cardNo', title: '身份证号' ,width:'5%'},
			  {field: 'type', title: '账户类型 0微信公众号 1:其他' ,width:'5%'},
			  {field: 'passwd', title: '密码' ,sort: true,width:'5%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%'},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chSaWechatAccount-manager-operator',width:'15%'}
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
	table.on('tool(LAY-chSaWechatAccount-manage)', function(obj){
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
			  title: '编辑微信访问账户',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chSaWechatAccount-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chSaWechatAccount-manage'); //数据刷新
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
			  title: '查看微信访问账户',
			  content: ['details.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  btn: ['关闭']
		  });
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-chSaWechatAccount-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chSaWechatAccount-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chSaWechatAccount-manage'),
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
						table.reload('LAY-chSaWechatAccount-manage'); //数据刷新
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
			title: '添加微信访问账户',
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
  form.on('submit(LAY-chSaWechatAccount-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSaWechatAccount-manage'); //重载表格
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
    
  form.on('submit(LAY-chSaWechatAccount-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chSaWechatAccount-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSaWechatAccount-manage'); //重载表格
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
  
  /* 职工设置访问密码页面-------->监听提交 */
  form.on('submit(LAY-chSaWechatAccount-wSetPwd-submit)', function(data){
	var key = $("#secretKey").val();
  	$.ajax({
    	url:$("#basePath").val()+'wechat/api/wsetpwd/do.jhtm',
        method:'post',
        data:{
			cardNo:encryptByDES($("#cardNo").val(),key),
			passwd:encryptByDES($("#passwd").val(),key)
		}, 
        dataType:'json',
        success:function(data){
			var code = data.statusCode;
			var msg = data.message;
			if("200" == code)
				//layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
				layer.msg(msg,{time:3*1000},function(){
				     //location.reload($("#basePath").val()+'wechat/api/wlogin.jhtm');
				     window.location.href = $("#basePath").val()+'wechat/api/wlogin.jhtm';
				})
			else
				layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
        },
        error:function (data) {
			layer.msg(data.message, {offset: '150px',icon: 1,time: 2000});
        }
	});
    return false;
  });
    
  laydate.render({
  	elem: '#LAY-chSaWechatAccount_xxx_date'
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
    
  //自定义验证
  form.verify({
		cardNo: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '身份证号不能为空!';
			if(value.length > 25)
				return '身份证号长度不能超过25';
	    },
	    cardNo:[/(^$)|(^\d{15}$)|(^\d{17}(x|X|\d)$)/,'请输入正确的身份证号'],
		type: function(value){
			if(value.length > 10)
				return '账户类型 0微信公众号 1:其他长度不能超过10';
	    },
		passwd: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '访问密码不能为空!';
			if(value.length > 6)
				return '访问密码长度不能超过6位';
	    },
	    repasswd: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '确认密码不能为空!';
			if(value.length > 6)
				return '确认密码长度不能超过6位';
			if(value != $("#passwd").val())
				return '两次密码不一致';
	    },
  });
  exports('chSaWechatAccount', {})
});