layui.define(['table','form','laydate','layer','element','upload','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	layer = layui.layer,
	laypage = layui.laypage,
	laydate = layui.laydate,
	element = layui.element,
	upload = layui.upload,
	form = layui.form;
	
	table.render({
		elem: '#LAY-payslip-statistics-manage',
		url: 'dostatis.jhtm',
		where: {
			startMonth: $("#startMonth").val(),
			endMonth: $("#endMonth").val(),
			name: $("#name").val(),
			cardNo: $("#cardNo").val(),
			wkModalityCn: $("#wkModalityCn").val(),
			departClassCn: $("#departClassCn").val()
	    },
	    cols: [],
	    cellMinWidth:'100',
	    toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
  	    defaultToolbar: ['filter','exports', 'print'],
	    page: true,
	    limits: [10,15,20,30,50],
	    limit: 10,
	    done:function (res,curr,count){
		    	$('th').css({'font-size':'14','font-weight':'bold','color':'green','border-color':'#aaa'});
	    },
	    text: {
	        none: '暂无相关数据'
	    }
  });
	//头工具栏事件
  table.on('toolbar(LAY-payslip-statistics-manage)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    switch(obj.event){
	      //导出
	      case 'LAYTABLE_EXPORT_USER':
	    	  layer.alert('导出完成请不要重复操作');
	    	  window.location.href=$("#basePath").val()+'/salary/statistics/ex'+'port100.jhtm?startMonth='+$("#startMonth").val()+'&endMonth='+$("#endMonth").val()
	    	  +'&name='+$("#name").val()+"&cardNo="+$("#cardNo").val()+'&wkModalityCn='+$("#wkModalityCn").val()+
	    	  '&departClassCn='+$("#departClassCn").val();
	    	  break;
	      //自定义头工具栏右侧图标 - 提示
	      case 'LAYTABLE_TIPS':
	        layer.alert('这是工具栏右侧自定义的一个图标按钮');
	        break;
	    };
  });
  
  //监听搜索
  form.on('submit(LAY-payslip-statistics-search)', function(data){
  	  var field = data.field;
	  //加载表头
	  $.ajax(
	  {
	  		url:"loadHead.jhtm", 
	  		async: false,
	  		data:{startMonth:$("#startMonth").val(),endMonth:$("#endMonth").val()},
	  		dataType:"JSON",
	  		success:function(re){
				heads = re.heads;
	  			$("#startMonth").val(re.startMonth);
				$("#endMonth").val(re.endMonth);
				table.reload('LAY-payslip-statistics-manage', {
					cols: heads,
			        where:field
			    });
	  		}
	  });
  });
  
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
  });
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
  });
  
  //自定义验证
  form.verify({
	  idcard: function(value){
			if(null == value)
				return '身份证号不能为空!';
			if(value.length > 25)
				return '身份证号长度不能超过25';
	  },
	  idcard:[/(^$)|(^\d{15}$)|(^\d{17}(x|X|\d)$)/,'请输入正确的身份证号'],
  });
  exports('paystatics100', {})
});