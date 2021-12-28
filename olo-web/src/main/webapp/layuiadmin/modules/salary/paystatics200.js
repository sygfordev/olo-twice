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
		elem: '#LAY-payslip-statistics200-manage',
		url: 'dostatis.jhtm',
		parseData: function(res){
		    return {
		    	"code": "200"==res.statusCode?0:1,
		    	"msg": res.message,
		    	//"count": res.count,
		    	"data": res.data
		    };
		},
		where: {
			ymonth: $("#ymonth").val()
	    },
	    cols: [],
	    cellMinWidth:'100',
	    toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
  	    defaultToolbar: ['filter','exports', 'print'],
	    page: false,
	    limits: [10,15,20,30,50],
	    limit: 10,
	    done:function (res,curr,count){
		    //$('th').css({'font-size':'14','font-weight':'bold','color':'green','border-color':'#aaa'});
	    	//渲染背景色
            $.each(res.data, function (index, item) {
            	var tindex = item.LAY_TABLE_INDEX;
            	if (item.modality == "总院小计" || item.modality == "基层小计" || item.modality == "全院合计") {
	              	$("#LAY-payslip-statistics200-manage").next().find('tbody tr[data-index="' + index +
	              	'"]').css("background-color", res.data.length-1 == tindex?"Wheat":"lightblue");
              	}
          	});
	    },
	    text: {
	        none: '暂无相关数据'
	    }
  });
	//头工具栏事件
  table.on('toolbar(LAY-payslip-statistics200-manage)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    switch(obj.event){
	      //导出
	      case 'LAYTABLE_EXPORT_USER':
	    	  var ymonth = $("#ymonth").val();
	    	  if(null == ymonth || '' == ymonth || 'undefined' == ymonth){
	    		  layer.alert('请选择薪资年月');
	    		  return;
	    	  }
	    	  layer.alert('导出完成请不要重复操作');
	    	  window.location.href=$("#basePath").val()+'/salary/statistics/ex'+'port200.jhtm?ymonth='+$("#ymonth").val();
	    	  break;
	      //自定义头工具栏右侧图标 - 提示
	      case 'LAYTABLE_TIPS':
	        layer.alert('这是工具栏右侧自定义的一个图标按钮');
	        break;
	    };
  });
  
  //监听搜索
  form.on('submit(LAY-payslip-statistics200-search)', function(data){
  	  var field = data.field;
	  //加载表头
	  $.ajax(
	  {
	  		url:"loadHead.jhtm", 
	  		async: false,
	  		data:{ymonth:$("#ymonth").val()},
	  		dataType:"JSON",
	  		success:function(re){
	  			var success = re.success;
				heads = re.heads;
	  			$("#ymonth").val(re.ymonth);
	  			if(!success) {
	  				layer.msg("表头信息加载失败", {offset: '150px',icon:2,time: 2000});
	  				return;
	  			}
				table.reload('LAY-payslip-statistics200-manage', {
					cols: heads,
			        where:field
			    });
	  		}
	  });
  });
  
  laydate.render({
	    elem: '#ymonth',
	    format: 'yyyy-MM',
	    type:"month"
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
  exports('paystatics200', {})
});