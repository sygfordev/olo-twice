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
	form = layui.form,
	selectN = layui.selectN,
    selectM = layui.selectM;
	
	var heads = [];
	//加载表头
	$(function () {
		$.ajax(
			{
			url:"index/loadTabHead.jhtm", 
			async: false, 
			data:{startMonth:$("#startMonth").val(),endMonth:$("#endMonth").val()},
			dataType:"JSON",
			success:function(re){
				heads = re.heads;
				$("#startMonth").val(re.startMonth);
				$("#endMonth").val(re.endMonth);
			}
		})
    });
	//薪资-工资单-表格渲染
	table.render({
		elem: '#LAY-chSaPaycard-manage',
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
			startMonth: $("#startMonth").val(),
			endMonth: $("#endMonth").val()
	    },
	    cols: heads,
	    toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
  	    defaultToolbar: ['filter','exports', 'print'],
  	    cellMinWidth:'100',
	    page: true,
	    limits: [10,15,20,30,50],
	    limit: 10,
	    done:function (res,curr,count){
		    $('th').css({'font-size':'14','font-weight':'bold','color':'green','border-color':'#aaa'});
	    	//$('layui-table[lay-even] tr:nth-child(even)').css({ 'background-color': '#c3cedd'});
	  		//$('th').css({ 'background-color':'#009688', 'color': 'white', 'font-weight': '500', 'font-size': '15px', 'line-height':'10px' });
	    },
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
	    	  var startMonth = null;
	    	  var endMonth = null;
	    	  var invokeUrl = $("#basePath").val()+'/salary/chSaPaycard/ex'+'port.jhtm?page=0&limit=1';
	    	  var formVal = form.val("payIndex");
	    	  $.each(formVal,function(key,val) {
	    		  if(null == val || '' == val || 'undefined' == val)
	    			  return true;//continue
	    		  invokeUrl += ("&"+key+"="+val);
	    		  if('startMonth' == key) startMonth = val;
	    		  if('endMonth' == key) endMonth = val;
	    	  });
	    	  if(null == startMonth && null == endMonth){
	    		  layer.alert('请选择时间段');
	    		  return;
	    	  }
	    	  //console.log("url:"+invokeUrl);
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
  	  var succFlag = false;
	  //加载表头
	  $.ajax(
	  {
	  		url:"index/loadTabHead.jhtm", 
	  		async: false,
	  		data:{startMonth:$("#startMonth").val(),endMonth:$("#endMonth").val()},
	  		dataType:"JSON",
	  		success:function(re){
				heads = re.heads;
	  			succFlag = true;
	  			$("#startMonth").val(re.startMonth);
				$("#endMonth").val(re.endMonth);
	  		}
	  });
	  //若表头加载成功，则执行重载
	  if(succFlag)
		table.reload('LAY-chSaPaycard-manage', {where: field,cols: heads});
  	  
  });
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chSaPaycard-manage'),
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
						table.reload('LAY-chSaPaycard-manage'); //数据刷新
	          			layer.close(index); //关闭弹层
					}
		        },
		        error:function (data) {
					layer.msg("删除失败！", {offset: '150px',icon: 2,time: 2000});
		        }
		    });
		});
	  },
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
  
  
  var selectStr = '['+
  		'{"id":"positCns","dkey":"positList","max":10},'+
  		'{"id":"titleCns","dkey":"titleList","max":10},'+
  		'{"id":"hosBranchCns","dkey":"branchList","max":10},'+
  		'{"id":"hosDepart1levelCns","dkey":"depart1List","max":10},'+
  		'{"id":"hosDepart2levelCns","dkey":"depart2List","max":10},'+
  		'{"id":"wkModalityCns","dkey":"modalityList","max":10},'+
  		'{"id":"departClassCns","dkey":"departClassList","max":10},'+
  		'{"id":"departClassPops","dkey":"departClassPopList","max":10},'+
  		'{"id":"stationCns","dkey":"stationList","max":10},'+
  		'{"id":"stationTypeCns","dkey":"stationTypeList","max":10},'+
  		'{"id":"stationStatusCns","dkey":"stationStatusList","max":10},'+
  		'{"id":"stationSeqCns","dkey":"stationSeqList","max":10},'+
  		'{"id":"titleClassCns","dkey":"titleClassList","max":10},'+
  		'{"id":"skillsLevelCns","dkey":"skillsLevelList","max":10},'+
  		'{"id":"eduLev4nowCns","dkey":"eduLev4NowList","max":10},'+
  		'{"id":"saSumProjects","dkey":"saSumProjectList","max":10},'+
  		'{"id":"rptWkDepClasss","dkey":"rptWkDepClassList","max":10}'+
  		']';
  
  $(function(){
	  var keys = JSON.parse(selectStr);
	  var list = null;
	  $.each(keys,function(index,value) {
		  list = $("#"+value.dkey).val();
		  selectM({//职务
		      elem: '#'+value.id
		      ,data: (null == list || "" == list || "undefined" == list)?[]:JSON.parse($("#"+value.dkey).val())
		      ,field: {idName:'key',titleName:'value'}
		      ,max:value.max
		      ,width:300
		      ,delimiter: ','
		  });
	  });
  })
  
  
  //自定义验证
  form.verify({
		workerId: function(value){
			if(value.length > 19)
				return '职工编号长度不能超过19';
	    },
		name: function(value){
			if(null == value || "".equals(value))
				return '职工姓名不能为空!';
			if(value.length > 100)
				return '职工姓名长度不能超过100';
	    },
		cardNo: function(value){
			if(value.length > 25)
				return '身份证号长度不能超过25';
	    },
		bankCardNo: function(value){
			if(value.length > 25)
				return '银行卡号长度不能超过25';
	    },
		wkModality: function(value){
			if(value.length > 10)
				return '用工形式长度不能超过10';
	    },
		wkModalityCn: function(value){
			if(value.length > 50)
				return '用工形式-名称长度不能超过50';
	    },
		wagesId: function(value){
			if(null == value || "".equals(value))
				return '人员编号（工资出账）不能为空!';
			if(value.length > 20)
				return '人员编号（工资出账）长度不能超过20';
	    },
		posit: function(value){
			if(value.length > 10)
				return '职务长度不能超过10';
	    },
		positCn: function(value){
			if(value.length > 50)
				return '职务-名称长度不能超过50';
	    },
		title: function(value){
			if(value.length > 10)
				return '职称长度不能超过10';
	    },
		titleCn: function(value){
			if(value.length > 50)
				return '职称-名称长度不能超过50';
	    },
		hosBranch: function(value){
			if(value.length > 10)
				return '医院支部长度不能超过10';
	    },
		hosBranchCn: function(value){
			if(value.length > 50)
				return '医院支部-名称长度不能超过50';
	    },
		hosDepart1level: function(value){
			if(value.length > 10)
				return '医院一级科室长度不能超过10';
	    },
		hosDepart1levelCn: function(value){
			if(value.length > 50)
				return '医院一级科室-名称长度不能超过50';
	    },
		hosDepart2level: function(value){
			if(value.length > 10)
				return '医院二级科室长度不能超过10';
	    },
		hosDepart2levelCn: function(value){
			if(value.length > 50)
				return '医院二级科室-名称长度不能超过50';
	    },
		manwkStandard: function(value){
			if(value.length > 5)
				return '标准工数长度不能超过5';
	    },
		manwkAttend: function(value){
			if(value.length > 5)
				return '出勤工数长度不能超过5';
	    },
		manwkMiner: function(value){
			if(value.length > 5)
				return '矿工工数长度不能超过5';
	    },
		manwkSick: function(value){
			if(value.length > 5)
				return '病假工数长度不能超过5';
	    },
		manwkMaternity: function(value){
			if(value.length > 5)
				return '产假工数长度不能超过5';
	    },
		manwkPrivpassion: function(value){
			if(value.length > 5)
				return '事假工数长度不能超过5';
	    },
		manwkOvertime: function(value){
			if(value.length > 5)
				return '加班工数长度不能超过5';
	    },
		wageStandard4posit: function(value){
			if(value.length > 10)
				return '岗位工资标准长度不能超过10';
	    },
		wageDay4posit: function(value){
			if(value.length > 10)
				return '岗位日工资长度不能超过10';
	    },
		wageAttend: function(value){
			if(value.length > 10)
				return '出勤工资长度不能超过10';
	    },
		wageSick: function(value){
			if(value.length > 10)
				return '病假工资长度不能超过10';
	    },
		wageNightShift: function(value){
			if(value.length > 10)
				return '夜班费用长度不能超过10';
	    },
		wageOvertime: function(value){
			if(value.length > 10)
				return '加班工资长度不能超过10';
	    },
		wageYearg: function(value){
			if(value.length > 10)
				return '年功工资长度不能超过10';
	    },
		allowanceHygiene: function(value){
			if(value.length > 10)
				return '卫生津贴长度不能超过10';
	    },
		wageErrorCorrent: function(value){
			if(value.length > 10)
				return '纠错工资长度不能超过10';
	    },
		supp4tel: function(value){
			if(value.length > 10)
				return '电补长度不能超过10';
	    },
		supp4oth: function(value){
			if(value.length > 10)
				return '其他长度不能超过10';
	    },
		wageTotal: function(value){
			if(value.length > 10)
				return '工资合计长度不能超过10';
	    },
		wageJxComb: function(value){
			if(value.length > 10)
				return '经合办绩效长度不能超过10';
	    },
		wageJxComb1: function(value){
			if(value.length > 10)
				return '经合办绩效1长度不能超过10';
	    },
		wageJxComb2: function(value){
			if(value.length > 10)
				return '经合办绩效2长度不能超过10';
	    },
		wageJxComb3: function(value){
			if(value.length > 10)
				return '经合办绩效3长度不能超过10';
	    },
		wageJxComb4: function(value){
			if(value.length > 10)
				return '经合办绩效4长度不能超过10';
	    },
		wageJxSubstrate: function(value){
			if(value.length > 10)
				return '基层分院绩效长度不能超过10';
	    },
		wageJxAircon: function(value){
			if(value.length > 10)
				return '空调班绩效长度不能超过10';
	    },
		wageJxRadiology: function(value){
			if(value.length > 10)
				return '放射科介入长度不能超过10';
	    },
		wageJxOperatroom: function(value){
			if(value.length > 10)
				return '手术室介入长度不能超过10';
	    },
		wageJxYearlySalary: function(value){
			if(value.length > 10)
				return '年薪制人员绩效长度不能超过10';
	    },
		supp4toCountryside: function(value){
			if(value.length > 10)
				return '分级诊疗办公室下乡补助长度不能超过10';
	    },
		reward1: function(value){
			if(value.length > 10)
				return '奖励1长度不能超过10';
	    },
		reward2: function(value){
			if(value.length > 10)
				return '奖励2长度不能超过10';
	    },
		reward3: function(value){
			if(value.length > 10)
				return '奖励3长度不能超过10';
	    },
		reward4: function(value){
			if(value.length > 10)
				return '奖励4长度不能超过10';
	    },
		reward5: function(value){
			if(value.length > 10)
				return '奖励5长度不能超过10';
	    },
		reward6: function(value){
			if(value.length > 10)
				return '奖励6长度不能超过10';
	    },
		reward7: function(value){
			if(value.length > 10)
				return '奖励7长度不能超过10';
	    },
		reward8: function(value){
			if(value.length > 10)
				return '奖励8长度不能超过10';
	    },
		reward9: function(value){
			if(value.length > 10)
				return '奖励9长度不能超过10';
	    },
		reward10: function(value){
			if(value.length > 10)
				return '奖励10长度不能超过10';
	    },
		reward11: function(value){
			if(value.length > 10)
				return '奖励11长度不能超过10';
	    },
		reward12: function(value){
			if(value.length > 10)
				return '奖励12长度不能超过10';
	    },
		reward13: function(value){
			if(value.length > 10)
				return '奖励13长度不能超过10';
	    },
		reward14: function(value){
			if(value.length > 10)
				return '奖励14长度不能超过10';
	    },
		reward15: function(value){
			if(value.length > 10)
				return '奖励15长度不能超过10';
	    },
		wageJxTotal: function(value){
			if(value.length > 10)
				return '绩效工资合计长度不能超过10';
	    },
		wagePayableTotal: function(value){
			if(value.length > 10)
				return '应发工资合计长度不能超过10';
	    },
		bxPension: function(value){
			if(value.length > 10)
				return '养老保险长度不能超过10';
	    },
		bxMedical: function(value){
			if(value.length > 10)
				return '医疗保险长度不能超过10';
	    },
		bxUnemploy: function(value){
			if(value.length > 10)
				return '失业保险长度不能超过10';
	    },
		bxSeriousIllness: function(value){
			if(value.length > 10)
				return '大病保险长度不能超过10';
	    },
		bxHousfund: function(value){
			if(value.length > 10)
				return '住房公积金长度不能超过10';
	    },
		bxAnnualCorpGold: function(value){
			if(value.length > 10)
				return '年企业金长度不能超过10';
	    },
		bxTotal: function(value){
			if(value.length > 10)
				return '保险合计长度不能超过10';
	    },
		cutWater2elect: function(value){
			if(value.length > 10)
				return '水电费长度不能超过10';
	    },
		cutHygiene: function(value){
			if(value.length > 10)
				return '卫生费长度不能超过10';
	    },
		cutArrears: function(value){
			if(value.length > 10)
				return '职工欠款长度不能超过10';
	    },
		cutTotal: function(value){
			if(value.length > 10)
				return '扣款合计长度不能超过10';
	    },
		taxableWage: function(value){
			if(value.length > 10)
				return '计税工资长度不能超过10';
	    },
		taxIncomePersonal: function(value){
			if(value.length > 10)
				return '个税长度不能超过10';
	    },
		netSalary: function(value){
			if(value.length > 10)
				return '实发工资长度不能超过10';
	    },
		netTargetYearmonth: function(value){
			if(value.length > 10)
				return '目标年月（工资对账年月）长度不能超过10';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
	    btimpNo: function(value){
			if(value.length > 100)
				return '备注长度不能超过20';
	    }
  });
  exports('chSaPaycard', {})
});