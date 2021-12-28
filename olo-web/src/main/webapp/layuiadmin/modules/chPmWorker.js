layui.define(['table','form','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form;
	
	//职工信息表-表格渲染
	table.render({
		elem: '#LAY-chPmWorker-manage',
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
			  {field: 'name', title: '职工姓名' ,sort: true,width:'5%'},
			  {field: 'sex', title: '职工性别  0：女  1：男  -1：未知' ,sort: true,width:'5%'},
			  {field: 'age', title: '职工年龄' ,sort: true,width:'5%'},
			  {field: 'cardType', title: '卡类型（身份）' ,sort: true,width:'5%'},
			  {field: 'cardNo', title: '卡号（身份）' ,sort: true,width:'5%'},
			  {field: 'birthDay', title: '出生日期' ,sort: true,width:'5%'},
			  {field: 'nation', title: '民族' ,sort: true,width:'5%'},
			  {field: 'politics', title: '政治面貌' ,width:'5%'},
			  {field: 'politicsInTime', title: '政治面貌加入时间' ,sort: true,width:'5%'},
			  {field: 'firstWorkTime', title: '首次参加工作时间' ,sort: true,width:'5%'},
			  {field: 'workedYear', title: '工龄' ,sort: true,width:'5%'},
			  {field: 'intoLocalCompTime', title: '进入本单位时间' ,width:'5%'},
			  {field: 'nativePlaceProv', title: '籍贯-省份' ,sort: true,width:'5%'},
			  {field: 'nativePlaceCity', title: '籍贯-城市' ,sort: true,width:'5%'},
			  {field: 'nativePlaceArea', title: '籍贯-区县' ,width:'5%'},
			  {field: 'residenceType', title: '户口性质' ,width:'5%'},
			  {field: 'residenceBirthlandProv', title: '户口所在地-省份' ,sort: true,width:'5%'},
			  {field: 'residenceBirthlandCity', title: '户口所在地-省份' ,sort: true,width:'5%'},
			  {field: 'residenceBirthlandArea', title: '户口所在地-区县' ,width:'5%'},
			  {field: 'homeProv', title: '现家庭详细住址-省份' ,sort: true,width:'5%'},
			  {field: 'homeCity', title: '现家庭详细住址-城市' ,sort: true,width:'5%'},
			  {field: 'homeAddr', title: '现家庭详细住址-详细地址' ,width:'5%'},
			  {field: 'telphoneNo', title: '联系电话' ,width:'5%'},
			  {field: 'mailBox', title: '邮箱' ,width:'5%'},
			  {field: 'salaryAdjustType', title: '调资类别' ,sort: true,width:'5%'},
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
  
	//监听工具条
	table.on('tool(LAY-chPmWorker-manage)', function(obj){
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
			  title: '编辑基本信息',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmWorker-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmWorker-manage'); //数据刷新
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
  form.on('submit(LAY-chPmWorker-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chPmWorker-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chPmWorker-manage'),
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
						table.reload('LAY-chPmWorker-manage'); //数据刷新
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
			title: '添加基本信息',
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
  form.on('submit(LAY-chPmWorker-edit-submit)', function(data){
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
				//parent.layui.table.reload('LAY-chPmWorker-manage'); //重载表格
				//parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
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
    
  form.on('submit(LAY-chPmWorker-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmWorker-add-submit)', function(data){
  	$.ajax({
    	url:'add/do.jhtm',
        method:'post',
        data:data.field,
        dataType:'json',
        success:function(data){
			var code = data.statusCode;
			var msg = data.message;
			var wkId = data.data;
			//layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
			parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
			if("200" == code)
			{
				//parent.layui.table.reload('LAY-chPmWorker-manage'); //重载表格
				//parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
	          	//var index = parent.layer.getFrameIndex(window.name);
	          	//parent.layer.close(index);
				
				$("#id").val(wkId);
				//调用父页面函数，将生成的职工主键编号传入父页面
				parent.tools.setWorkerId(wkId);
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
  	elem: '#LAY-chPmWorker_xxx_date'
  });*/
  //年月选择器
  laydate.render({
      elem: '#LAY-chPmWorker-birthDay'
      ,type: 'month'
  });
  
  //循环监听所有需要获取select对应option.text下拉框
  var selectListenIds = ['nation','politics','residenceType','nativePlaceArea','residenceBirthlandArea'
	  ,'homeCity','salaryAdjustType'];
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
  
  
  //省份级联城市-籍贯
  form.on('select(nativePlaceProv)',function(data){
      var value=data.value;
      if("" == value){
      	$("#nativePlaceCity").empty();
      	$("#nativePlaceCity").append(new Option("",""));
      	
      	$("#nativePlaceProvCn").val("");
      	$("#nativePlaceCityCn").val("");
      	
      	layui.form.render("select");
      	return;
      }
      $("#nativePlaceProvCn").val(data.elem[data.elem.selectedIndex].text);
      provFilFun(value,'nativePlaceCity');
  });
  //城市级联区县-籍贯
  form.on('select(nativePlaceCity)',function(data){
      var value=data.value;
      if("" == value){
      	$("#nativePlaceArea").empty();
      	$("#nativePlaceArea").append(new Option("",""));
      	
      	$("#nativePlaceCityCn").val("");
      	$("#nativePlaceAreaCn").val("");
      	
      	layui.form.render("select");
      	return;
      }
      $("#nativePlaceCityCn").val(data.elem[data.elem.selectedIndex].text);
      cityFilFun(value,'nativePlaceArea');
  });
  //省份级联城市-户口所在地
  form.on('select(residenceBirthlandProv)',function(data){
      var value=data.value;
      if("" == value){
      	$("#residenceBirthlandCity").empty();
      	$("#residenceBirthlandCity").append(new Option("",""));
      	
      	$("#residenceBirthlandProvCn").val("");
      	$("#residenceBirthlandCityCn").val("");
      	layui.form.render("select");
      	return;
      }
      $("#residenceBirthlandProvCn").val(data.elem[data.elem.selectedIndex].text);
      provFilFun(value,'residenceBirthlandCity');
  });
  //城市级联区县-户口所在地
  form.on('select(residenceBirthlandCity)',function(data){
      var value=data.value;
      if("" == value){
      	$("#residenceBirthlandArea").empty();
      	$("#residenceBirthlandArea").append(new Option("",""));
      	
      	$("#residenceBirthlandCityCn").val("");
      	$("#residenceBirthlandAreaCn").val("");
      	
      	layui.form.render("select");
      	return;
      }
      $("#residenceBirthlandCityCn").val(data.elem[data.elem.selectedIndex].text);
      cityFilFun(value,'residenceBirthlandArea');
  });
  //省份级联城市-现家庭居住地址
  form.on('select(homeProv)',function(data){
      var value=data.value;
      if("" == value){
      	$("#homeCity").empty();
      	$("#homeCity").append(new Option("",""));
      	
      	$("#homeProvCn").val("");
      	$("#homeCityCn").val("");
      	layui.form.render("select");
      	return;
      }
      $("#homeProvCn").val(data.elem[data.elem.selectedIndex].text);
      provFilFun(value,'homeCity');
  });
  
  //通过省份编号级联城市列表
  function provFilFun(provNo,target)
  {
	  $.ajax({
          url:$("#basePath").val()+'central/sysProvince/loadCityListByProvNo.jhtm',
          method:'post',
          data:{provNo:provNo},
          dataType:"JSON",
          success:function(data){
          	var code = data.retCode;
  			var msg = data.retMsg;
  			var list = data.retData;
  			if("200" != code){
  				layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
  				return;
  			}
  			$("#"+target).empty();
  			$("#"+target).append(new Option("",""));
            list.forEach(function (obj) {
            	//console.log(JSON.stringify(obj));
            	$("#"+target).append(new Option(obj.cityName,obj.cityNo));
            })
            layui.form.render("select");
          }
      });
  }
  
  //通过城市编号级联区县列表
  function cityFilFun(cityNo,target)
  {
	  $.ajax({
          url:$("#basePath").val()+'central/sysProvince/loadAreaListByCityNo.jhtm',
          method:'post',
          data:{cityNo:cityNo},
          dataType:"JSON",
          success:function(data){
          	var code = data.retCode;
  			var msg = data.retMsg;
  			var list = data.retData;
  			if("200" != code){
  				layer.msg(msg, {offset: '150px',icon: 2,time: 2000});
  				return;
  			}
  			$("#"+target).empty();
  			$("#"+target).append(new Option("",""));
            list.forEach(function (obj) {
            	$("#"+target).append(new Option(obj.areaName,obj.areaNo));
            })
            layui.form.render("select");
          }
      });
  }
    
  //自定义验证
  form.verify({
		name: function(value){
			if(null == value)
				return '职工姓名不能为空!';
			if(value.length > 100)
				return '职工姓名长度不能超过100';
	    },
		sex: function(value){
			if(null == value)
				return '职工性别  0：女  1：男  -1：未知不能为空!';
			if(value.length > 10)
				return '职工性别  0：女  1：男  -1：未知长度不能超过10';
	    },
		age: function(value){
			if(null == value)
				return '职工年龄不能为空!';
			if(value.length > 10)
				return '职工年龄长度不能超过10';
	    },
		cardType: function(value){
			if(null == value)
				return '卡类型（身份）不能为空!';
			if(value.length > 10)
				return '卡类型（身份）长度不能超过10';
	    },
	    //同一属性可多次校验
		cardNo: function(value){
			if(null == value)
				return '卡号（身份）不能为空!';
			if(value.length > 25)
				return '卡号（身份）长度不能超过25';
	    },
	    cardNo:[/(^$)|(^\d{15}$)|(^\d{17}(x|X|\d)$)/,'请输入正确的身份证号'],
		birthDay: function(value){
			if(null == value)
				return '出生日期不能为空!';
			if(value.length > 26)
				return '出生日期长度不能超过26';
	    },
		nation: function(value){
			if(null == value)
				return '民族不能为空!';
			if(value.length > 10)
				return '民族长度不能超过10';
	    },
		politics: function(value){
			if(value.length > 10)
				return '政治面貌长度不能超过10';
	    },
		politicsInTime: function(value){
			if(null == value)
				return '政治面貌加入时间不能为空!';
			if(value.length > 26)
				return '政治面貌加入时间长度不能超过26';
	    },
		firstWorkTime: function(value){
			if(null == value)
				return '首次参加工作时间不能为空!';
			if(value.length > 26)
				return '首次参加工作时间长度不能超过26';
	    },
		workedYear: function(value){
			if(null == value)
				return '工龄不能为空!';
			if(value.length > 10)
				return '工龄长度不能超过10';
	    },
		intoLocalCompTime: function(value){
			if(value.length > 26)
				return '进入本单位时间长度不能超过26';
	    },
		nativePlaceProv: function(value){
			if(null == value)
				return '籍贯-省份不能为空!';
			if(value.length > 10)
				return '籍贯-省份长度不能超过10';
	    },
		nativePlaceCity: function(value){
			if(null == value)
				return '籍贯-城市不能为空!';
			if(value.length > 10)
				return '籍贯-城市长度不能超过10';
	    },
		nativePlaceArea: function(value){
			if(value.length > 10)
				return '籍贯-区县长度不能超过10';
	    },
		residenceType: function(value){
			if(value.length > 10)
				return '户口性质长度不能超过10';
	    },
		residenceBirthlandProv: function(value){
			if(null == value)
				return '户口所在地-省份不能为空!';
			if(value.length > 10)
				return '户口所在地-省份长度不能超过10';
	    },
		residenceBirthlandCity: function(value){
			if(null == value)
				return '户口所在地-省份不能为空!';
			if(value.length > 10)
				return '户口所在地-省份长度不能超过10';
	    },
		residenceBirthlandArea: function(value){
			if(value.length > 10)
				return '户口所在地-区县长度不能超过10';
	    },
	    residencePoliceStation: function(value){
	    	if(value.length > 50)
				return '所处派出所长度不能超过50';
	    },
		homeProv: function(value){
			if(null == value)
				return '现家庭详细住址-省份不能为空!';
			if(value.length > 10)
				return '现家庭详细住址-省份长度不能超过10';
	    },
		homeCity: function(value){
			if(null == value)
				return '现家庭详细住址-城市不能为空!';
			if(value.length > 10)
				return '现家庭详细住址-城市长度不能超过10';
	    },
		homeAddr: function(value){
			if(value.length > 100)
				return '现家庭详细住址-详细地址长度不能超过100';
	    },
		telphoneNo: function(value){
			if(value.length > 11)
				return '联系电话长度不能超过11';
	    },
	    telphoneNo:[/(^$)|^1\d{10}$/,'请输入正确的手机号'],
		mailBox: function(value){
			if(value.length > 11)
				return '邮箱长度不能超过11';
	    },
		salaryAdjustType: function(value){
			if(null == value)
				return '调资类别不能为空!';
			if(value.length > 10)
				return '调资类别长度不能超过10';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
	    //phone:[/(^$)|^1\d{10}$/,'请输入正确的手机号'],
	    //email:[/(^$)|^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/,'邮箱格式不正确'],
	    //url:[/(^$)|(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/,'链接格式不正确'],
	    //number:[/(^$)|^\d+$/,'只能填写数字'],
	    //date:[/(^$)|^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/,'日期格式不正确'],
	    //identity:[/(^$)|(^\d{15}$)|(^\d{17}(x|X|\d)$)/,'请输入正确的身份证号']
  });
  exports('chPmWorker', {})
});