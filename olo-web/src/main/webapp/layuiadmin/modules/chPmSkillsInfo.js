layui.define(['table','form','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form;
	
	var mkhide = $("#oper").val()=="detail"?true:false;
	var extendRat = mkhide?'10%':'5%';
	//技能等级（技术工种）表-表格渲染
	table.render({
		elem: '#LAY-chPmSkillsInfo-manage',
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
			  {field: 'skillsCn', title: '技能等级' ,sort: true,width:'8%'},
			  {field: 'skillsGotTime', title: '技能等级取得时间' ,templet: '<div>{{#if(d.skillsGotTime){}} {{layui.util.toDateString(d.skillsGotTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'5%'},
			  {field: 'skillsClassCn', title: '技能等级序列' ,sort: true,width:'8%'},
			  {field: 'skillsLevelCn', title: '技能等级级别' ,sort: true,width:'8%'},
			  {field: 'skillsMax', title: '是否最高职称 ' ,sort: true,templet:maxFmt,width:'5%'},
			  {field: 'skillsOrder', title: '技能顺序' ,sort: true,width:'5%'},
			  {field: 'skillsCertNo', title: '技能证书编号' ,sort: true,width:'8%'},
			  {field: 'skillsOnitNo', title: '技能任职文号' ,sort: true,width:'8%'},
			  {field: 'skillsHireNo', title: '技能初聘文号' ,sort: true,width:'8%'},
			  {field: 'skillsHStartTime', title: '技能初聘开始时间' ,sort: true,templet: '<div>{{#if(d.skillsHStartTime){}} {{layui.util.toDateString(d.skillsHStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'5%'},
			  {field: 'skillsHEndTime', title: '技能初聘结束时间' ,sort: true,templet: '<div>{{#if(d.skillsHEndTime){}} {{layui.util.toDateString(d.skillsHEndTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'5%'},
			  {field: 'skillsHCycle', title: '技能聘任周期' ,sort: true,width:'5%'},
			  {field: 'skillsOthCn', title: '其他技能' ,sort: true,width:'5%',hide:true},
			  {field: 'skillsOthGotTime', title: '其他技能取得时间' ,templet: '<div>{{#if(d.skillsOthGotTime){}} {{layui.util.toDateString(d.skillsOthGotTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',sort: true,width:'5%',hide:true},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmSkillsInfo-manager-operator',width:'15%',hide:mkhide}
		  ]
	    ],
	    page: true,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});
	
	var isMaxId = [0,1];
    var isMaxCn = ["否","是"];
    function maxFmt(d)
    {
    	if(!isMaxId.includes(d.skillsMax)) return "未知";
    	var idx = isMaxId.indexOf(d.skillsMax);
    	return isMaxCn[idx];
    }
    
	//监听工具条
	table.on('tool(LAY-chPmSkillsInfo-manage)', function(obj){
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
			  title: '编辑技能等级',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '650px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmSkillsInfo-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmSkillsInfo-manage'); //数据刷新
					  layer.close(index); //关闭弹层
				  });
				  submit.trigger('click');
			  },
			  success: function(layero, index){
			  }*/
		  });
	  }
  });
  
  //监听head保存
  form.on('submit(LAY-chPmSkillsHead-save)', function(data){
  	  var field = data.field;
  	  $.ajax({
		  url:$("#basePath").val()+'personnel/chPmSkillsHead/add/do.jhtm',
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
  
  //监听head修改
  form.on('submit(LAY-chPmSkillsHead-edit)', function(data){
  	  var field = data.field;
  	  $.ajax({
		  url:$("#basePath").val()+'personnel/chPmSkillsHead/edit/do.jhtm',
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
	  	var checkStatus = table.checkStatus('LAY-chPmSkillsInfo-manage'),
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
						table.reload('LAY-chPmSkillsInfo-manage'); //数据刷新
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
			title: '添加技能等级',
			content: ['add.jhtm?wkId='+$("#workerId").val(),'no'],
			area: ['800px', '650px'],
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
  form.on('submit(LAY-chPmSkillsInfo-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmSkillsInfo-manage'); //重载表格
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
    
  form.on('submit(LAY-chPmSkillsInfo-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmSkillsInfo-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmSkillsInfo-manage'); //重载表格
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
  	elem: '#LAY-chPmSkillsInfo_xxx_date'
  });*/
  
  //循环监听所有需要获取select对应option.text下拉框
  var selectListenIds = ['skills4now','skillsClass4now','skillsLevel4now','treatLevel4now',
	  ,'skills','skillsClass','skillsLevel','skillsOth'];
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
		skills: function(value){
			if(null == value)
				return '技能等级不能为空!';
			if(value.length > 10)
				return '技能等级长度不能超过10';
	    },
		skillsGotTime: function(value){
			if(null == value)
				return '技能等级取得时间不能为空!';
			if(value.length > 26)
				return '技能等级取得时间长度不能超过26';
	    },
		skillsClass: function(value){
			if(null == value)
				return '技能等级序列（技能分类）不能为空!';
			if(value.length > 10)
				return '技能等级序列（技能分类）长度不能超过10';
	    },
		skillsLevel: function(value){
			if(null == value)
				return '技能等级级别(五级|四级|三级|二级|一级)不能为空!';
			if(value.length > 10)
				return '技能等级级别(五级|四级|三级|二级|一级)长度不能超过10';
	    },
		skillsMax: function(value){
			if(null == value)
				return '是否最高职称 0：否  1：是不能为空!';
			if(value.length > 10)
				return '是否最高职称 0：否  1：是长度不能超过10';
	    },
		skillsOrder: function(value){
			if(null == value)
				return '技能顺序不能为空!';
			if(value.length > 10)
				return '技能顺序长度不能超过10';
	    },
		skillsCertNo: function(value){
			if(null == value)
				return '技能证书编号不能为空!';
			if(value.length > 100)
				return '技能证书编号长度不能超过100';
	    },
		skillsOnitNo: function(value){
			if(null == value)
				return '技能任职文号不能为空!';
			if(value.length > 100)
				return '技能任职文号长度不能超过100';
	    },
		skillsHireNo: function(value){
			if(null == value)
				return '技能初聘文号不能为空!';
			if(value.length > 100)
				return '技能初聘文号长度不能超过100';
	    },
		skillsHStartTime: function(value){
			if(null == value)
				return '技能初聘开始时间不能为空!';
			if(value.length > 26)
				return '技能初聘开始时间长度不能超过26';
	    },
		skillsHEndTime: function(value){
			if(null == value)
				return '技能初聘结束时间不能为空!';
			if(value.length > 26)
				return '技能初聘结束时间长度不能超过26';
	    },
		skillsHCycle: function(value){
			if(null == value)
				return '技能聘任周期不能为空!';
			if(value.length > 10)
				return '技能聘任周期长度不能超过10';
	    },
		skillsOth: function(value){
			if(null == value)
				return '其他技能不能为空!';
			if(value.length > 10)
				return '其他技能长度不能超过10';
	    },
		skillsOthGotTime: function(value){
			if(null == value)
				return '其他技能取得时间不能为空!';
			if(value.length > 26)
				return '其他技能取得时间长度不能超过26';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
	    number:[/(^$)|^\d+$/,'只能填写数字'],
  });
  exports('chPmSkillsInfo', {})
});