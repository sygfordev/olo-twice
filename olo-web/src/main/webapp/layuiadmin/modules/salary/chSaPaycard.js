layui.extend({
    selectN: './layui_exts/selectN',
    selectM: './layui_exts/selectM',
  }).define(['table','form','laydate','element','upload','laypage','selectN','selectM'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	element = layui.element,
	upload = layui.upload,
	form = layui.form;
	
	//工资卡表-表格渲染
	table.render({
		elem: '#LAY-chSaPaycard-manage',
		url: 'index/do.jhtm',
		where: {
			startMonth: $("#startMonth").val(),
			endMonth: $("#endMonth").val(),
			name: $("#name").val(),
			cardNo: $("#cardNo").val()
	    },
	    cols: 
	    [
	  	  [
			  {field: 'item', title: '项目',fixed: 'left',width:'8%'},
			  {field: 'Jan', title: '一月' ,width:'6%'},
			  {field: 'Feb', title: '二月' ,width:'6%'},
			  {field: 'Mar', title: '三月' ,width:'6%'},
			  {field: 'Apr', title: '四月' ,width:'6%'},
			  {field: 'May', title: '五月' ,width:'6%'},
			  {field: 'Jun', title: '六月' ,width:'6%'},
			  {field: 'Jul', title: '七月' ,width:'6%'},
			  {field: 'Aug', title: '八月' ,width:'6%'},
			  {field: 'Sep', title: '九月' ,width:'6%'},
			  {field: 'Oct', title: '十月' ,width:'6%'},
			  {field: 'Nov', title: '十一月' ,width:'6%'},
			  {field: 'Dec', title: '十二月' ,width:'6%'},
		      {field: 'monthly', title: '月均',width:'6%'},
			  {field: 'statisticalInterval', title: '统计区间' ,width:'7%'},
			  {field: 'high', title: '高位数' ,width:'6%'},
			  {field: 'median', title: '中位数' ,width:'6%'},
			  {field: 'low', title: '低位数' ,width:'6%'}
		  ]
	    ],
	    page: false,
	    limits: [33],
	    limit: 33,
	    height: 'full-110',
	    toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
  	    defaultToolbar: ['exports'],
	    text: {
	        none: '暂无相关数据'
	    }
	});
	
	//头工具栏事件
	table.on('toolbar(LAY-chSaPaycard-manage)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    switch(obj.event){
	      //导出
	      case 'LAYTABLE_EXPORT_USER':
	    	  layer.alert('正在导出，请不要重复操作');
	    	  var name = null;
	    	  var cardNo = null;
	    	  var startMonth = null;
	    	  var endMonth = null;
	    	  var invokeUrl =$("#basePath").val()+'salary/chSaPaycard/export.jhtm?page=0';
	    	  var formVal = form.val("payIndex");
	    	  $.each(formVal,function(key,val) {
	    		  if(null == val || '' == val || 'undefined' == val)
	    			  return true;//continue
	    		  invokeUrl += ("&"+key+"="+val);
	    		  if('startMonth' == key) startMonth = val;
	    		  if('endMonth' == key) endMonth = val;
	    		  if('name' == key) name = val;
	    		  if('cardNo' == key) cardNo = val;
	    	  });
	    	  if(null == name){
	    		  layer.alert('请填写姓名');
	    		  return;
	    	  }
	    	  if(null == cardNo){
	    		  layer.alert('请填写身份证号');
	    		  return;
	    	  }
	    	  if(null == startMonth && null == endMonth){
	    		  layer.alert('请选择时间段');
	    		  return;
	    	  }
	    	  window.location.href=invokeUrl;
	    	  break;
	      //自定义头工具栏右侧图标 - 提示
	      case 'LAYTABLE_TIPS':
	        layer.alert('这是工具栏右侧自定义的一个图标按钮');
	        break;
	    };
	});
		
  
  //监听搜索
	  form.on('submit(LAY-chSaPaycard-search)', function(data){
	  	  var field = data.field;
	  	  //执行重载
	  	  table.reload('LAY-chSaPaycard-manage', {where: field});
	  });
  //---------表头按钮区域----------
  var active = {
	  revoke: function(){
	  	layer.open({
			type: 2,
			title: '批次撤销',
			content: ['revoke.jhtm','no'],
			area: ['800px', '550px'],
			maxmin: true,
			btn: ['确定','取消'],
			yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  var btimpNo = layero.find('iframe').contents().find('#btimpNo').val();
				  if(null == btimpNo || 'undefined' == btimpNo || '' == btimpNo)
				  {
					  return layer.msg("批次编号为空！");
				  }
				  layer.confirm('撤销后数据无法恢复,确定撤销吗？', function(ind) {
				        $.ajax({
					        url:'revoke/do.jhtm',
					        method:'post',
					        data:{"btimpNo":btimpNo},
					        dataType:'json',
					        success:function(data){
								var code = data.statusCode;
								var msg = data.message;
								layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
								if("200" == code)
								{
									table.reload('LAY-chSaPaycard-manage'); //数据刷新
				          			layer.close(index); //关闭弹层
								}
					        },
					        error:function (data) {
								layer.msg("撤销失败！", {offset: '150px',icon: 2,time: 2000});
					        }
					    });
				  });
				  //监听提交
				  /*iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  
					  
				  });
				  submit.trigger('click');*/
		   },
		   success: function(layero, index){
		   
		   }
		});
	  }
  }
  
  //---------表头按钮触发激活----------
  $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
  	var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
    
  laydate.render({
  	elem: '#netTargetYearmonth',
  	type: 'month'
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
 
  laydate.render({
	    elem: '#startMonth',
	    format: 'yyyy-MM',
	    type:"month",
	    done: function (value, date, endDate) {
	        var startDate = new Date(value).getTime();
	        var endTime = new Date($('#endMonth').val()).getTime();
	        if (endTime < startDate) {
	            layer.msg('结束时间不能小于开始时间');
	            $('#startMonth').val('');
	        }
	    }
  })
  laydate.render({ //结束时间
	    elem: '#endMonth',
	    format: 'yyyy-MM',
	    type:"month",
	    done: function (value, date, endDate) {
	        var startDate = new Date($('#startMonth').val()).getTime();
	        var endTime = new Date(value).getTime();
	        if (endTime < startDate) {
	            layer.msg('结束时间不能小于开始时间');
	            $('#endMonth').val('');
	        }
	    }
  })
  
  //自定义验证
  form.verify({
		name: function(value){
			if(null == value || "".equals(value))
				return '姓名不能为空!';
			if(value.length > 100)
				return '姓名长度不能超过100';
	    },
		cardNo: function(value){
			if(value.length > 25)
				return '身份证号长度不能超过25';
	    }
  });
  exports('chSaPaycard', {})
});