layui.define(['table','form','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laydate = layui.laydate,
	laypage = layui.laypage,
	form = layui.form;
	
	var mkhide = $("#oper").val()=="detail"?true:false;
	var extendRat = mkhide?'10%':'5%';
	//学历信息表-表格渲染
	table.render({
		elem: '#LAY-chPmEduInfo-manage',
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
			  {field: 'eduOrder', title: '学历顺序' ,sort: true,width:'6%'},
			  {field: 'eduSch', title: '学习院校' ,sort: true,width:'10%'},
			  {field: 'eduMaj', title: '学习专业' ,sort: true,width:'10%'},
			  {field: 'eduDegCn', title: '学习学位' ,sort: true,width:'8%'},
			  {field: 'eduTypeCn', title: '学习形式' ,sort: true,width:'8%'},
			  {field: 'eduLevCn', title: '学历' ,sort: true,width:'8%'},
			  {field: 'eduStartTime', title: '学习开始时间' ,templet: '<div>{{#if(d.eduStartTime){}} {{layui.util.toDateString(d.eduStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'10%'},
			  {field: 'eduEndTime', title: '学习结束时间（毕业）' ,templet: '<div>{{#if(d.eduEndTime){}} {{layui.util.toDateString(d.eduEndTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'10%'},
			  {field: 'eduMax', title: '是否最高学历' ,templet:maxFmt,sort: true,width:'5%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:extendRat},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:extendRat},
		      {field: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmEduInfo-manager-operator',width:'10%', hide:mkhide}
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
	var isMaxId = [0,1];
    var isMaxCn = ["否","是"];
    function maxFmt(d)
    {
    	if(!isMaxId.includes(d.eduMax)) return "未知";
    	var idx = isMaxId.indexOf(d.eduMax);
    	return isMaxCn[idx];
    }
	
	//监听工具条
	table.on('tool(LAY-chPmEduInfo-manage)', function(obj){
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
			  title: '编辑学历信息',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmEduInfo-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmEduInfo-manage'); //数据刷新
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
  form.on('submit(LAY-chPmEduInfo-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chPmEduInfo-manage', {where: field});
  });
  
  //教育Head添加监听
  form.on('submit(LAY-chPmEduHead-save)', function(data){
  	  var field = data.field;
	  $.ajax({
		  url:$("#basePath").val()+'personnel/chPmEduHead/add/do.jhtm',
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
  //教育Head修改监听
  form.on('submit(LAY-chPmEduHead-edit)', function(data){
  	  var field = data.field;
	  $.ajax({
		  url:$("#basePath").val()+'personnel/chPmEduHead/edit/do.jhtm',
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
	  	var checkStatus = table.checkStatus('LAY-chPmEduInfo-manage'),
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
						table.reload('LAY-chPmEduInfo-manage'); //数据刷新
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
			title: '添加学历信息',
			content: ['add.jhtm?wkId='+$("#workerId").val(),'no'],
			area: ['800px', '610px'],
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
  form.on('submit(LAY-chPmEduInfo-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmEduInfo-manage'); //重载表格
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
    
  form.on('submit(LAY-chPmEduInfo-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmEduInfo-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmEduInfo-manage'); //重载表格
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
  	elem: '#LAY-chPmEduInfo_xxx_date'
  });*/
  
  //循环监听所有需要获取select对应option.text下拉框
  var selectListenIds = ['eduDeg4now','eduLev4now','eduLev4sal',
	  					'eduType','eduLev','eduDeg'];
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
		eduSch: function(value){
			if(null == value)
				return '学习院校不能为空!';
			if(value.length > 100)
				return '学习院校长度不能超过100';
	    },
		eduMaj: function(value){
			if(null == value)
				return '学习专业不能为空!';
			if(value.length > 100)
				return '学习专业长度不能超过100';
	    },
		eduDeg: function(value){
			if(null == value)
				return '学习学位不能为空!';
			if(value.length > 10)
				return '学习学位长度不能超过10';
	    },
		eduType: function(value){
			if(null == value)
				return '教育类型不能为空!';
			if(value.length > 10)
				return '教育类型长度不能超过10';
	    },
		eduLev: function(value){
			if(null == value)
				return '学历不能为空!';
			if(value.length > 10)
				return '学历长度不能超过10';
	    },
		eduOrder: function(value){
			if(null == value)
				return '学历顺序不能为空!';
			if(value.length > 10)
				return '学历顺序长度不能超过10';
	    },
		eduStartTime: function(value){
			if(null == value)
				return '学习开始时间不能为空!';
			if(value.length > 26)
				return '学习开始时间长度不能超过26';
	    },
		eduEndTime: function(value){
			if(null == value)
				return '学习结束时间不能为空!';
			if(value.length > 26)
				return '学习结束时间长度不能超过26';
	    },
		eduMax: function(value){
			if(null == value)
				return '是否最高学历不能为空!';
			if(value.length > 10)
				return '是否最高学历长度不能超过10';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chPmEduInfo', {})
});