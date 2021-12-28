layui.define(['table','form','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form;
	
	var mkhide = $("#oper").val()=="detail"?true:false;
	var extendRat = mkhide?'10%':'5%';
	//职务信息表-表格渲染
	table.render({
		elem: '#LAY-chPmPositInfo-manage',
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
			  {field: 'id', title: '自增主键' ,sort: true,width:'5%',hide:true},
			  {field: 'workerId', title: '职工编号' ,sort: true,width:'5%',hide:true},
			  {field: 'onPositOrder', title: '任职顺序' ,sort: true,width:'8%'},
			  {field: 'positCn', title: '职务' ,sort: true,width:'8%'},
			  {field: 'positDepart', title: '任职部门' ,sort: true,width:'8%'},
			  {field: 'onPositStartTime', title: '任职时间' ,templet: '<div>{{#if(d.onPositStartTime){}} {{layui.util.toDateString(d.onPositStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'10%'},
			  {field: 'onPositDocno', title: '任职文号' ,sort: true,width:'8%'},
			  {field: 'conPositCn', title: '兼任职务' ,sort: true,width:'8%'},
			  {field: 'conPositDepart', title: '兼职部门' ,sort: true,width:'8%'},
			  {field: 'conPositStartTime', title: '兼职时间' ,templet: '<div>{{#if(d.conPositStartTime){}} {{layui.util.toDateString(d.conPositStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'10%'},
			  {field: 'positLevelCn', title: '行政级别' ,sort: true,width:'8%'},
			  {field: 'treatLevelCn', title: '待遇级别' ,sort: true,width:'8%'},
			  {field: 'positMax', title: '是否最高职务' ,sort: true,width:'5%',hide:true},
			  {field: 'onPositEndTime', title: '任职结束时间' ,templet: '<div>{{#if(d.onPositEndTime){}} {{layui.util.toDateString(d.onPositEndTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'5%',hide:true},
			  {field: 'conPositEndTime', title: '兼职结束时间' ,templet: '<div>{{#if(d.conPositEndTime){}} {{layui.util.toDateString(d.conPositEndTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'5%',hide:true},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmPositInfo-manager-operator',width:'15%',hide:mkhide}
		  ]
	    ],
	    page: true,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});
  
	//*************************templet设置********************************************
	/*//格式化职务
	var positId = [1,2,3,4,5,6,7,,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33];
    var positCn = ["副主任","主任","副科长","科长","副部长","部长","分院副院长","分院院长","支部副书记","支部书记","工会副主席","支会主席","副护士长","护士长","技师长","副科级科员"
    	,"正科级科员","协理员（副科级）","协理员（正科级）","调研员（副处）","调研员（正处）","院长助理","副总师","总师","副院长","工会主席","纪委副书记","纪委书记","党委副书记","党委书记","院领导","副董事长","董事长"];
    function positFmt(d) {
    	if(!positId.includes(d.posit)) return "未知";
    	var idx = positId.indexOf(d.posit);
    	return positCn[idx];
    }
    //民族字典
    var nationId = [0,1];
    var nationCn = ["汉族","回族"];
    function nationFmt(d)
    {
    	if(!nationId.includes(d.nation)) return "未知";
    	var idx = nationId.indexOf(d.nation);
    	return nationCn[idx];
    }*/
	//监听工具条
	table.on('tool(LAY-chPmPositInfo-manage)', function(obj){
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
			  title: '编辑职务信息',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmPositInfo-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmPositInfo-manage'); //数据刷新
					  layer.close(index); //关闭弹层
				  });
				  submit.trigger('click');
			  },
			  success: function(layero, index){
			  }*/
		  });
	  }
  });
  
  //职务Head添加监听
  form.on('submit(LAY-chPmPositHead-save)', function(data){
	  var field = data.field;
	  $.ajax({
		  url:$("#basePath").val()+'personnel/chPmPositHead/add/do.jhtm',
		  method:'post',
		  data:data.field,
		  dataType:'json',
		  success:function(res){
			  var code = res.statusCode;
			  var msg = res.message;
			  layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
		  },
		  error:function (res) {
			  layer.msg(res.message, {offset: '150px',icon: 2,time: 2000});
		  }
	  });
  });
  
  //职务Head修改监听
  form.on('submit(LAY-chPmPositHead-edit)', function(data){
	  var field = data.field;
	  $.ajax({
		  url:$("#basePath").val()+'personnel/chPmPositHead/edit/do.jhtm',
		  method:'post',
		  data:data.field,
		  dataType:'json',
		  success:function(res){
			  var code = res.statusCode;
			  var msg = res.message;
			  layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
		  },
		  error:function (res) {
			  layer.msg(res.message, {offset: '150px',icon: 2,time: 2000});
		  }
	  });
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chPmPositInfo-manage'),
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
						table.reload('LAY-chPmPositInfo-manage'); //数据刷新
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
			title: '添加职务信息',
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
  form.on('submit(LAY-chPmPositInfo-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmPositInfo-manage'); //重载表格
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
    
  form.on('submit(LAY-chPmPositInfo-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmPositInfo-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmPositInfo-manage'); //重载表格
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
  	elem: '#LAY-chPmPositInfo_xxx_date'
  });*/
  
  //循环监听所有需要获取select对应option.text下拉框
  var selectListenIds = ['posit4now','positLevel4now','treatLevel4now',
	  					'posit','conPosit','positLevel','treatLevel'];
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
		workerId: function(value){
			if(null == value)
				return '职工编号不能为空!';
			if(value.length > 19)
				return '职工编号长度不能超过19';
	    },
		posit: function(value){
			if(value.length > 10)
				return '职务长度不能超过10';
	    },
		positDepart: function(value){
			if(value.length > 100)
				return '任职部门长度不能超过100';
	    },
		positLevel: function(value){
			if(value.length > 10)
				return '行政级别长度不能超过10';
	    },
		positMax: function(value){
			if(value.length > 10)
				return '是否最高职务长度不能超过10';
	    },
		onPositStartTime: function(value){
			if(value.length > 26)
				return '任职开始时间长度不能超过26';
	    },
		onPositEndTime: function(value){
			if(value.length > 26)
				return '任职开始时间长度不能超过26';
	    },
		onPositDocno: function(value){
			if(value.length > 50)
				return '任职文号长度不能超过50';
	    },
		conPosit: function(value){
			if(value.length > 10)
				return '兼任职务长度不能超过10';
	    },
		conPositDepart: function(value){
			if(value.length > 100)
				return '兼职部门长度不能超过100';
	    },
		conPositStartTime: function(value){
			if(value.length > 26)
				return '兼职开始时间长度不能超过26';
	    },
		conPositEndTime: function(value){
			if(value.length > 26)
				return '兼职结束时间长度不能超过26';
	    },
		treatLevel: function(value){
			if(value.length > 10)
				return '待遇级别长度不能超过10';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chPmPositInfo', {})
});