layui.define(['table','form','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form;
	
	//用工及岗位表-表格渲染
	table.render({
		elem: '#LAY-chPmStation-manage',
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
			  {field: 'wagesId', title: '人员编号（工资出账）' ,sort: true,width:'5%'},
			  {field: 'identity', title: '个人身份  1:干部 2:工人' ,sort: true,width:'5%'},
			  {field: 'wkModality', title: '用工形式' ,sort: true,width:'5%'},
			  {field: 'wkType', title: '用工类型' ,sort: true,width:'5%'},
			  {field: 'laborRelNo', title: '劳动关系编号' ,sort: true,width:'5%'},
			  {field: 'laborRelCn', title: '劳动关系名称' ,sort: true,width:'5%'},
			  {field: 'stationSitu', title: '在岗情况' ,sort: true,width:'5%'},
			  {field: 'stationType', title: '岗位类型' ,sort: true,width:'5%'},
			  {field: 'stationStatus', title: '岗位状态' ,width:'5%'},
			  {field: 'stationSeqNo', title: '岗位序列编号' ,sort: true,width:'5%'},
			  {field: 'stationSeqCn', title: '岗位序列名称' ,sort: true,width:'5%'},
			  {field: 'isMajorPerson', title: '是否专业人员' ,sort: true,width:'5%'},
			  {field: 'inMajorNowCn', title: '现从事专业' ,width:'5%'},
			  {field: 'workArea', title: '工作地域' ,width:'5%'},
			  {field: 'hosBranch', title: '医院支部' ,width:'5%'},
			  {field: 'hosDepart1level', title: '医院一级科室' ,width:'5%'},
			  {field: 'hosDepart2level', title: '医院二级科室' ,width:'5%'},
			  {field: 'homeAddress', title: '现家庭详细住址' ,width:'5%'},
			  {field: 'telphoneNo', title: '联系电话' ,width:'5%'},
			  {field: 'mailBox', title: '邮箱' ,width:'5%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmStation-manager-operator',width:'15%'}
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
	table.on('tool(LAY-chPmStation-manage)', function(obj){
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
			  title: '编辑用工及岗位',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmStation-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmStation-manage'); //数据刷新
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
  form.on('submit(LAY-chPmStation-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chPmStation-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chPmStation-manage'),
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
						table.reload('LAY-chPmStation-manage'); //数据刷新
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
			title: '添加用工及岗位',
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
  form.on('submit(LAY-chPmStation-edit-submit)', function(data){
  	$.ajax({
    	url:'edit/do.jhtm',
        method:'post',
        data:data.field,
        dataType:'json',
        success:function(data){
			var code = data.statusCode;
			var msg = data.message;
			//layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
			if("200" == code)
			{
				//parent.layui.table.reload('LAY-chPmStation-manage'); //重载表格
				parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
		        //var index = parent.layer.getFrameIndex(window.name);
		        //parent.layer.close(index);
			}
        },
        error:function (data) {
			parent.layer.msg(data.message, {offset: '150px',icon: 2,time: 2000});
        }
  	});
    return false;
  });
    
  form.on('submit(LAY-chPmStation-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmStation-add-submit)', function(data){
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
				//parent.layui.table.reload('LAY-chPmStation-manage'); //重载表格
				//parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
	          	//var index = parent.layer.getFrameIndex(window.name);
	          	//parent.layer.close(index);
				
				$("#id").val(id);
				//调用父页面函数，删除当前tab页面
				//parent.tools.delTab(1);
			}else
			{
				parent.layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
			}
        },
        error:function (data) {
			parent.layer.msg(data.message, {offset: '150px',icon: 1,time: 2000});
        }
	});
    return false;
  });
    
  /*laydate.render({
  	elem: '#LAY-chPmStation_xxx_date'
  });*/
  
  //医院支部
  form.on('select(hosBranch)',function(data){
      var value=data.value;
      if("" == value){
      	$("#hosDepart1level").empty();
      	$("#hosDepart1level").append(new Option("",""));
      	$("#hosDepart2level").empty();
      	$("#hosDepart2level").append(new Option("",""));
      	
      	$("#hosBranchCn").val("");
      	$("#hosDepart1levelCn").val("");
      	$("#hosDepart2levelCn").val("");
      	
      	layui.form.render("select");
      	return;
      }
      $("#hosBranchCn").val(data.elem[data.elem.selectedIndex].text);
      hosFilFun(value,'loadHosDepByBrNo.jhtm','hosDepart1level');
  });
  
  //一级科室
  form.on('select(hosDepart1level)',function(data){
      var value=data.value;
      if("" == value){
      	$("#hosDepart2level").empty();
      	$("#hosDepart2level").append(new Option("",""));
      	
      	$("#hosDepart1levelCn").val("");
      	$("#hosDepart2levelCn").val("");
      	layui.form.render("select");
      	return;
      }
      $("#hosDepart1levelCn").val(data.elem[data.elem.selectedIndex].text);
      hosFilFun(value,'loadHosSecDepByDepNo.jhtm','hosDepart2level');
  });
  
  //支部-一级科室-二级科室
  function hosFilFun(code,method,target)
  {
	  $.ajax({
          url:$("#basePath").val()+'personnel/chPmHosBranch/'+method,
          method:'post',
          data:{code:code},
          dataType:"JSON",
          success:function(data){
          		var code = data.statusCode;
          		var msg = data.message;
          		var list = data.data;
          		if("200" != code){
          			layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
          			return;
          		}
          		$("#"+target).empty();
          		$("#"+target).append(new Option("",""));
          		if('hosDepart1level' == target)
          		{
          			$("#hosDepart2level").empty();
              		$("#hosDepart2level").append(new Option("",""));
          		}
          		list.forEach(function (obj) {
          			if('hosDepart1level' == target)
          				$("#"+target).append(new Option(obj.hdpName,obj.hdpNo));
          			else
          				$("#"+target).append(new Option(obj.hsdName,obj.hsdNo));
          		})
          		layui.form.render("select");
          }
      });
  }
  
  //循环监听所有需要获取select对应option.text下拉框
  var selectListenIds = ['identity','wkModality','wkType','stationSitu',
	  ,'stationType','stationStatus','inMajorNow','workArea',,'hosDepart2level'];
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
  
  //劳动关系
  form.on('select(laborRelNo)',function(data){
	  var key=data.value;
      var val = data.elem[data.elem.selectedIndex].text;
      if("" == key){
      	$("#laborRelCn").val("");
      	return;
      }
      $("#laborRelCn").val(val);
  });
  //岗位序列
  form.on('select(stationSeqNo)',function(data){
      var key=data.value;
      var val = data.elem[data.elem.selectedIndex].text;
      if("" == key){
      	$("#stationSeqCn").val("");
      	return;
      }
      $("#stationSeqCn").val(val);
  });
  
  //自定义验证
  form.verify({
		workerId: function(value){
			if(null == value)
				return '职工编号不能为空!';
			if(value.length > 19)
				return '职工编号长度不能超过19';
	    },
		wagesId: function(value){
			if(null == value)
				return '人员编号（工资出账）不能为空!';
			if(value.length > 20)
				return '人员编号（工资出账）长度不能超过20';
	    },
		identity: function(value){
			if(null == value)
				return '个人身份  1:干部 2:工人不能为空!';
			if(value.length > 10)
				return '个人身份  1:干部 2:工人长度不能超过10';
	    },
		wkModality: function(value){
			if(null == value)
				return '用工形式不能为空!';
			if(value.length > 10)
				return '用工形式长度不能超过10';
	    },
		wkType: function(value){
			if(null == value)
				return '用工类型不能为空!';
			if(value.length > 10)
				return '用工类型长度不能超过10';
	    },
		laborRelNo: function(value){
			if(null == value)
				return '劳动关系编号不能为空!';
			if(value.length > 10)
				return '劳动关系编号长度不能超过10';
	    },
		laborRelCn: function(value){
			if(null == value)
				return '劳动关系名称不能为空!';
			if(value.length > 100)
				return '劳动关系名称长度不能超过100';
	    },
		stationSitu: function(value){
			if(null == value)
				return '在岗情况不能为空!';
			if(value.length > 10)
				return '在岗情况长度不能超过10';
	    },
		stationType: function(value){
			if(null == value)
				return '岗位类型不能为空!';
			if(value.length > 10)
				return '岗位类型长度不能超过10';
	    },
		stationStatus: function(value){
			if(value.length > 10)
				return '岗位状态长度不能超过10';
	    },
		stationSeqNo: function(value){
			if(null == value)
				return '岗位序列编号不能为空!';
			if(value.length > 10)
				return '岗位序列编号长度不能超过10';
	    },
		stationSeqCn: function(value){
			if(null == value)
				return '岗位序列名称不能为空!';
			if(value.length > 25)
				return '岗位序列名称长度不能超过25';
	    },
		isMajorPerson: function(value){
			if(null == value)
				return '是否专业人员不能为空!';
			if(value.length > 10)
				return '是否专业人员长度不能超过10';
	    },
		inMajorNow: function(value){
			if(value.length > 10)
				return '现从事专业长度不能超过10';
	    },
	    inMajorNowCn: function(value){
			if(value.length > 50)
				return '现从事专业长度不能超过40';
	    },
		workArea: function(value){
			if(value.length > 26)
				return '工作地域长度不能超过26';
	    },
		hosBranch: function(value){
			if(value.length > 200)
				return '医院支部长度不能超过200';
	    },
		hosDepart1level: function(value){
			if(value.length > 10)
				return '医院一级科室长度不能超过10';
	    },
		hosDepart2level: function(value){
			if(value.length > 10)
				return '医院二级科室长度不能超过10';
	    },
		homeAddress: function(value){
			if(value.length > 200)
				return '现家庭详细住址长度不能超过200';
	    },
		telphoneNo: function(value){
			if(value.length > 11)
				return '联系电话长度不能超过11';
	    },
	    telphoneNo:[/(^$)|^1\d{10}$/,'请输入正确的手机号'],
		mailBox: function(value){
			if(value.length > 50)
				return '邮箱长度不能超过50';
	    },
	    mailBox:[/(^$)|^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,'邮箱格式不正确'],
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chPmStation', {})
});