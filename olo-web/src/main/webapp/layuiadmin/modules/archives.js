layui.define(['table','form','laypage','laydate'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form;
	
	//医院职工表-表格渲染
	table.render({
		elem: '#LAY-archives-manage',
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
	  		  {type: 'numbers', title: '序号',fixed: 'left'},
			  {field: 'id', title: '编号' ,sort: true,width:'5%',hide:true},
			  {field: 'name', title: '姓名' ,sort: true,width:'8%'},
			  {field: 'sex', title: '性别 ' ,sort: true,templet:sexFmt,width:'5%'},
			  {field: 'age', title: '职工年龄' ,sort: true,width:'8%'},
			  {field: 'cardNo', title: '身份证号' ,sort: true,width:'11%'},
			  {field: 'birthDay', title: '出生年月' ,sort: true,templet: '<div>{{#if(d.birthDay){}} {{layui.util.toDateString(d.birthDay, "yyyy-MM") }} {{#} else{}} {{#}}}</div>',width:'10%'},
			  {field: 'nationCn', title: '民族' ,sort: true,width:'5%'},
			  {field: 'politicsCn', title: '政治面貌' ,width:'7%'},
			  {field: 'politicsInTime', title: '加入时间' ,sort: true,templet: '<div>{{#if(d.politicsInTime){}} {{layui.util.toDateString(d.politicsInTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'firstWorkTime', title: '参加工作时间' ,sort: true,templet: '<div>{{#if(d.firstWorkTime){}} {{layui.util.toDateString(d.firstWorkTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'9%'},
			  {field: 'workedYear', title: '工龄' ,width:'5%',hide:true},
			  {field: 'intoLocalCompTime', title: '进入本单位时间' ,templet: '<div>{{#if(d.intoLocalCompTime){}} {{layui.util.toDateString(d.intoLocalCompTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			 /* {field: 'nativePlace', title: '籍贯' ,width:'5%'},
			  {field: 'residenceType', title: '户口性质' ,width:'5%'},
			  {field: 'residenceBirthland', title: '户口所在地' ,width:'5%'},
			  {field: 'homeAddress', title: '现家庭详细住址' ,width:'5%'},
			  {field: 'telphoneNo', title: '联系电话' ,width:'5%'},
			  {field: 'mailBox', title: '邮箱' ,width:'5%'},*/
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%'},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmWorker-manager-operator',width:'15%'}
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
	//格式化性别
    function sexFmt(d) {
        var str;
        switch(d.sex)
        {
        case 0:
        	str = "女";break;
        case 1:
        	str = "男";break;
        case -1:
        	str = "未知";break;
        }
        return str;
    }
    //民族字典
    /*var nationId = [0,1];
    var nationCn = ["汉族","回族"];
    function nationFmt(d)
    {
    	if(!nationId.includes(d.nation)) return "未知";
    	var idx = nationId.indexOf(d.nation);
    	return nationCn[idx];
    }*/
    
	//监听工具条
	table.on('tool(LAY-archives-manage)', function(obj){
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
	  } else if(obj.event === 'edit' || obj.event === "detail"){
		  //子页面打开新的tab页面
		  var l = parent === self ? layui : top.layui;
		  var title = (obj.event === 'edit')?"编辑人事档案":"查看人事档案";
		  l.index.openTabsPage('personnel/archives/edit.jhtm?id='+data.id+"&oper="+obj.event, title);
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-archives-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-archives-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
		  	var checkStatus = table.checkStatus('LAY-archives-manage'),
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
							table.reload('LAY-archives-manage'); //数据刷新
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
		  //子页面打开新的tab页面
		  var l = parent === self ? layui : top.layui;
		  l.index.openTabsPage('personnel/archives/add.jhtm', "新增人事档案");
	  }
  }
  
  //---------表头按钮触发激活----------
  $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
  	var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
  /*laydate.render({
  	elem: '#LAY-archives_xxx_date'
  });*/
  
  //医院支部
  form.on('select(hosBranch)',function(data){
      var value=data.value;
      if("" == value){
      	$("#hosDepart1level").empty();
      	$("#hosDepart1level").append(new Option("",""));
      	$("#hosDepart2level").empty();
      	$("#hosDepart2level").append(new Option("",""));
      	layui.form.render("select");
      	return;
      }
      hosFilFun(value,'loadHosDepByBrNo.jhtm','hosDepart1level');
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
    
  //自定义验证
  form.verify({
		name: function(value){
			if(null == value || "".equals(value))
				return '职工姓名不能为空!';
			if(value.length > 100)
				return '职工姓名长度不能超过100';
	    },
		sex: function(value){
			if(null == value || "".equals(value))
				return '职工性别 不能为空!';
			if(value.length > 10)
				return '职工性别长度不能超过10';
	    },
		age: function(value){
			if(null == value || "".equals(value))
				return '职工年龄不能为空!';
			if(value.length > 10)
				return '职工年龄长度不能超过10';
	    },
		cardType: function(value){
			if(null == value || "".equals(value))
				return '卡类型（身份）不能为空!';
			if(value.length > 10)
				return '卡类型（身份）长度不能超过10';
	    },
		cardNo: function(value){
			if(null == value || "".equals(value))
				return '卡号（身份）不能为空!';
			if(value.length > 25)
				return '卡号（身份）长度不能超过25';
	    },
		birthDay: function(value){
			if(null == value || "".equals(value))
				return '出生日期不能为空!';
			if(value.length > 26)
				return '出生日期长度不能超过26';
	    },
		nation: function(value){
			if(null == value || "".equals(value))
				return '民族不能为空!';
			if(value.length > 10)
				return '民族长度不能超过10';
	    },
		politics: function(value){
			if(value.length > 10)
				return '政治面貌长度不能超过10';
	    },
		politicsInTime: function(value){
			if(null == value || "".equals(value))
				return '政治面貌加入时间不能为空!';
			if(value.length > 26)
				return '政治面貌加入时间长度不能超过26';
	    },
		firstWorkTime: function(value){
			if(null == value || "".equals(value))
				return '首次参加工作时间不能为空!';
			if(value.length > 26)
				return '首次参加工作时间长度不能超过26';
	    },
		workedYear: function(value){
			if(value.length > 10)
				return '工龄长度不能超过10';
	    },
		intoLocalCompTime: function(value){
			if(value.length > 26)
				return '进入本单位时间长度不能超过26';
	    },
		nativePlace: function(value){
			if(value.length > 200)
				return '籍贯长度不能超过200';
	    },
		residenceType: function(value){
			if(value.length > 10)
				return '户口性质长度不能超过10';
	    },
		residenceBirthland: function(value){
			if(value.length > 10)
				return '户口所在地长度不能超过10';
	    },
		homeAddress: function(value){
			if(value.length > 200)
				return '现家庭详细住址长度不能超过200';
	    },
		telphoneNo: function(value){
			if(value.length > 11)
				return '联系电话长度不能超过11';
	    },
		mailBox: function(value){
			if(value.length > 11)
				return '邮箱长度不能超过11';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('archives', {})
});