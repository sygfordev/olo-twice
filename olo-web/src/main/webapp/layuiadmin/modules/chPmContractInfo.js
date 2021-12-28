layui.define(['table','form','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form;
	
	var mkhide = $("#oper").val()=="detail"?true:false;
	var extendRat = mkhide?'10%':'5%';
	//合同信息-表格渲染
	table.render({
		elem: '#LAY-chPmContractInfo-manage',
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
	  		  {type: 'checkbox', fixed: 'left',hide:mkhide},
			  {field: 'id', title: '主键' ,sort: true,width:'5%',hide:true},
			  {field: 'contOrder', title: '合同顺序' ,sort: true,width:'8%'},
			  {field: 'workerId', title: '职工编号' ,sort: true,width:'8%'},
			  {field: 'contClassCn', title: '合同类别' ,sort: true,width:'8%'},
			  {field: 'contCycleCn', title: '合同期限' ,sort: true,width:'5%'},
			  {field: 'contNo', title: '合同编号' ,sort: true,width:'8%'},
			  {field: 'contStartTime', title: '合同开始时间' ,templet: '<div>{{#if(d.contStartTime){}} {{layui.util.toDateString(d.contStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'10%'},
			  {field: 'contEndTime', title: '合同结束时间' ,templet: '<div>{{#if(d.contEndTime){}} {{layui.util.toDateString(d.contEndTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'10%'},
			  {field: 'contExpireWarnTime', title: '合同到期预警时间' ,templet: '<div>{{#if(d.contExpireWarnTime){}} {{layui.util.toDateString(d.contExpireWarnTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'10%',hide:true},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmContractInfo-manager-operator',width:'15%',hide:mkhide}
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
	table.on('tool(LAY-chPmContractInfo-manage)', function(obj){
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
			  title: '编辑合同',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmContractInfo-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmContractInfo-manage'); //数据刷新
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
  form.on('submit(LAY-chPmContractInfo-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chPmContractInfo-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chPmContractInfo-manage'),
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
						table.reload('LAY-chPmContractInfo-manage'); //数据刷新
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
			title: '添加合同信息',
			content: ['add.jhtm?wkId='+$("#workerId").val(),'no'],
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
  form.on('submit(LAY-chPmContractInfo-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmContractInfo-manage'); //重载表格
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
    
  form.on('submit(LAY-chPmContractInfo-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmContractInfo-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmContractInfo-manage'); //重载表格
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
  	elem: '#LAY-chPmContractInfo_xxx_date'
  });*/
  //$("#selectID option:[value='XX']").attr("disabled","disabled")

  //循环监听所有需要获取select对应option.text下拉框
  var selectListenIds = ['contClass','contCycle'];
  selectListenIds.forEach((elem, index) => {
      form.on('select('+elem+')',function(data){
    	  var key=data.value;
          var val = data.elem[data.elem.selectedIndex].text;
          if("" == key){
        	  $("#"+elem+"Cn").val("");
        	  if('contClass' == elem){
        		  $("#contCycle").empty();
        	      $("#contCycle").append(new Option("",""));
        	      $("#contCycleCn").val("");
        		  //$("#"+elem).find("option:contains('')").attr("selected",true);
        		  /*$("#contCycle").each(function (i, j) {
        		        $(j).find("option:selected").removeAttr("selected");
        		        $(j).find("option:selected").removeClass("layui-this");
        		        $(j).find("option").first().attr("selected", true);
        		  })*/
        	  }
          	  return;
          }
          $("#"+elem+"Cn").val(val);
          if('contClass' != elem) return;
          $.ajax({
              url:'loadCycleByClass.jhtm',
              method:'post',
              data:{itemKey:key},
              dataType:"JSON",
              success:function(data){
              	var code = data.statusCode;
      			var msg = data.message;
      			var list = data.data;
      			if("200" != code){
      				layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
      				return;
      			}
      			$("#contCycle").empty();
      			$("#contCycle").append(new Option("",""));
                list.forEach(function (obj) {
                	//console.log(JSON.stringify(obj));
                	$("#contCycle").append(new Option(obj.item_val,obj.item_key));
                })
                layui.form.render("select");
              }
          });
      });
  });
    
  //自定义验证
  form.verify({
		workerId: function(value){
			if(null == value || "".equals(value))
				return '职工编号不能为空!';
			if(value.length > 19)
				return '职工编号长度不能超过19';
	    },
		contClass: function(value){
			if(null == value || "".equals(value))
				return '合同类别  固定期限|无固定期限不能为空!';
			if(value.length > 10)
				return '合同类别  固定期限|无固定期限长度不能超过10';
	    },
		contCycle: function(value){
			if(null == value || "".equals(value))
				return '合同期限（合同周期）  无固定期限|1年|2年等不能为空!';
			if(value.length > 10)
				return '合同期限（合同周期）  无固定期限|1年|2年等长度不能超过10';
	    },
		contNo: function(value){
			if(null == value || "".equals(value))
				return '合同编号不能为空!';
			if(value.length > 50)
				return '合同编号长度不能超过50';
	    },
		contStartTime: function(value){
			if(null == value || "".equals(value))
				return '合同开始时间不能为空!';
			if(value.length > 26)
				return '合同开始时间长度不能超过26';
	    },
		contEndTime: function(value){
			if(null == value || "".equals(value))
				return '合同结束时间不能为空!';
			if(value.length > 26)
				return '合同结束时间长度不能超过26';
	    },
		contExpireWarnTime: function(value){
			if(null == value || "".equals(value))
				return '合同到期预警时间不能为空!';
			if(value.length > 26)
				return '合同到期预警时间长度不能超过26';
	    },
		contOrder: function(value){
			if(null == value || "".equals(value))
				return '合同顺序不能为空!';
			if(value.length > 10)
				return '合同顺序长度不能超过10';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chPmContractInfo', {})
});