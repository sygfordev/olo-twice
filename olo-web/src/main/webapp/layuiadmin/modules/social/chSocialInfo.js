layui.extend({
    selectN: './layui_exts/selectN',
    selectM: './layui_exts/selectM',
}).define(['table','form','laydate','upload','laypage','selectN','selectM'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form,
	upload = layui.upload,
	selectN = layui.selectN,
    selectM = layui.selectM;
	
	//社保信息-表格渲染
	table.render({
		elem: '#LAY-chSocialInfo-manage',
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
	  		  {type: 'numbers',title: '序号', width:'5%',fixed: 'left',align: 'center',rowspan:4},
			  {field: 'id', title: '自增主键',width:'10%',align: 'center',hide:true,rowspan:4},
			  {field: 'name', title: '姓名' ,fixed: 'left',width:'10%',align: 'center',rowspan:4},
			  {field: 'cardNo', title: '身份证号' ,fixed: 'left',width:'12%',align: 'center',rowspan:4},
			  {field: 'compName', title: '单位名称' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'wkModalityCn', title: '用工类别' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'workAreaCn', title: '工作地域' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'positCn', title: '职务' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'titleCn', title: '职称' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'hosBranchCn', title: '支部' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'hosDepart1levelCn', title: '一级科室' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'hosDepart2levelCn', title: '二级科室' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'socialYmonth', title: '社保年月',width:'10%',align: 'center',rowspan:4},
			  {field: '', title: $("#curMonth").val()+'月份社保',width:'10%',align: 'center',colspan:89},
			  {field: 'remark4year', title: '年备注' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'persRecapDiffe4addup', title: '本年个人实缴累计差额' ,width:'12%',align: 'center',rowspan:4},
			  {field: 'persCompleDiffe4addup', title: '本年个人补缴累计差额' ,width:'12%',align: 'center',rowspan:4},
			  {field: 'btimpNo', title: '导入编号' ,width:'10%',align: 'center',rowspan:4},
			  {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',align: 'center',width:'10%',rowspan:4},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',align: 'center',width:'10%',rowspan:4}
		  ],
		  [
			  {field: '', title: '养老保险' ,width:'10%',align: 'center',colspan:11},
			  {field: '', title: '医疗保险' ,width:'10%',align: 'center',colspan:11},
			  {field: '', title: '失业保险' ,width:'10%',align: 'center',colspan:11},
			  {field: '', title: '工伤保险' ,width:'10%',align: 'center',colspan:11},
			  {field: '', title: '生育保险' ,width:'10%',align: 'center',colspan:11},
			  {field: '', title: '年金' ,width:'10%',align: 'center',colspan:11},
			  {field: '', title: '省统筹' ,width:'10%',align: 'center',colspan:11},
			  {field: '', title: '补充医疗' ,width:'10%',align: 'center',colspan:11},
			  {field: 'remark4month', title: '月备注' ,width:'10%',align: 'center',rowspan:3}
		  ],
		  [
			  {field: 'sPenBase', title: '基数' ,width:'10%',align: 'center',rowspan:2},
			  {field: '', title: '单位应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: '', title: '个人应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: 'sPenCompRecapAmount', title: '单位实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sPenPersRecapAmount', title: '个人实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sPenCompRecapDiffe', title: '单位实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sPenPersRecapDiffe', title: '个人实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sPenCompOverpaid', title: '单位多缴' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sPenPersComple', title: '个人补缴' ,width:'10%',align: 'center',rowspan:2},
			  
			  {field: 'sMediBase', title: '基数' ,width:'10%',align: 'center',rowspan:2},
			  {field: '', title: '单位应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: '', title: '个人应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: 'sMediCompRecapAmount', title: '单位实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sMediPersRecapAmount', title: '个人实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sMediCompRecapDiffe', title: '单位实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sMediPersRecapDiffe', title: '个人实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sMediCompOverpaid', title: '单位多缴' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sMediPersComple', title: '个人补缴' ,width:'10%',align: 'center',rowspan:2},
			  
			  {field: 'sUnempBase', title: '基数' ,width:'10%',align: 'center',rowspan:2},
			  {field: '', title: '单位应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: '', title: '个人应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: 'sUnempCompRecapAmount', title: '单位实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sUnempPersRecapAmount', title: '个人实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sUnempCompRecapDiffe', title: '单位实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sUnempPersRecapDiffe', title: '个人实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sUnempCompOverpaid', title: '单位多缴' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sUnempPersComple', title: '个人补缴' ,width:'10%',align: 'center',rowspan:2},
			  
			  {field: 'sInjuryBase', title: '基数' ,width:'10%',align: 'center',rowspan:2},
			  {field: '', title: '单位应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: '', title: '个人应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: 'sInjuryCompRecapAmount', title: '单位实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sInjuryPersRecapAmount', title: '个人实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sInjuryCompRecapDiffe', title: '单位实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sInjuryPersRecapDiffe', title: '个人实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sInjuryCompOverpaid', title: '单位多缴' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sInjuryPersComple', title: '个人补缴' ,width:'10%',align: 'center',rowspan:2},
			  
			  {field: 'sBirthBase', title: '基数' ,width:'10%',align: 'center',rowspan:2},
			  {field: '', title: '单位应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: '', title: '个人应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: 'sBirthCompRecapAmount', title: '单位实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sBirthPersRecapAmount', title: '个人实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sBirthCompRecapDiffe', title: '单位实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sBirthPersRecapDiffe', title: '个人实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sBirthCompOverpaid', title: '单位多缴' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sBirthPersComple', title: '个人补缴' ,width:'10%',align: 'center',rowspan:2},
			  
			  {field: 'sAnnuityBase', title: '基数' ,width:'10%',align: 'center',rowspan:2},
			  {field: '', title: '单位应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: '', title: '个人应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: 'sAnnuityCompRecapAmount', title: '单位实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sAnnuityPersRecapAmount', title: '个人实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sAnnuityCompRecapDiffe', title: '单位实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sAnnuityPersRecapDiffe', title: '个人实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sAnnuityCompOverpaid', title: '单位多缴' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sAnnuityPersComple', title: '个人补缴' ,width:'10%',align: 'center',rowspan:2},
			  
			  {field: 'sOvpBase', title: '基数' ,width:'10%',align: 'center',rowspan:2},
			  {field: '', title: '单位应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: '', title: '个人应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: 'sOvpCompRecapAmount', title: '单位实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sOvpPersRecapAmount', title: '个人实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sOvpCompRecapDiffe', title: '单位实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sOvpPersRecapDiffe', title: '个人实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sOvpCompOverpaid', title: '单位多缴' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sOvpPersComple', title: '个人补缴' ,width:'10%',align: 'center',rowspan:2},
			  
			  {field: 'sSpMediBase', title: '基数' ,width:'10%',align: 'center',rowspan:2},
			  {field: '', title: '单位应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: '', title: '个人应缴部分' ,width:'10%',align: 'center',colspan:2},
			  {field: 'sSpMediCompRecapAmount', title: '单位实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sSpMediPersRecapAmount', title: '个人实缴金额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sSpMediCompRecapDiffe', title: '单位实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sSpMediPersRecapDiffe', title: '个人实缴差额' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sSpMediCompOverpaid', title: '单位多缴' ,width:'10%',align: 'center',rowspan:2},
			  {field: 'sSpMediPersComple', title: '个人补缴' ,width:'10%',align: 'center',rowspan:2},
		  ],
		  [
			  
			  {field: 'sPenCompRatio', title: '单位应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sPenCompAmount', title: '单位应缴金额' ,width:'10%',align: 'center'},
			  {field: 'sPenPersRatio', title: '个人应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sPenPersAmount', title: '个人应缴金额' ,width:'10%',align: 'center'},
			  
			  {field: 'sMediCompRatio', title: '单位应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sMediCompAmount', title: '单位应缴金额' ,width:'10%',align: 'center'},
			  {field: 'sMediPersRatio', title: '个人应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sMediPersAmount', title: '个人应缴金额' ,width:'10%',align: 'center'},
			  
			  {field: 'sUnempCompRatio', title: '单位应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sUnempCompAmount', title: '单位应缴金额' ,width:'10%',align: 'center'},
			  {field: 'sUnempPersRatio', title: '个人应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sUnempPersAmount', title: '个人应缴金额' ,width:'10%',align: 'center'},
			  
			  {field: 'sInjuryCompRatio', title: '单位应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sInjuryCompAmount', title: '单位应缴金额' ,width:'10%',align: 'center'},
			  {field: 'sInjuryPersRatio', title: '个人应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sInjuryPersAmount', title: '个人应缴金额',width:'10%',align: 'center'},
			  
			  {field: 'sBirthCompRatio', title: '单位应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sBirthCompAmount', title: '单位应缴金额' ,width:'10%',align: 'center'},
			  {field: 'sBirthPersRatio', title: '个人应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sBirthPersAmount', title: '个人应缴金额' ,width:'10%',align: 'center'},
			  
			  {field: 'sAnnuityCompRatio', title: '单位应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sAnnuityCompAmount', title: '单位应缴金额' ,width:'10%',align: 'center'},
			  {field: 'sAnnuityPersRatio', title: '个人应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sAnnuityPersAmount', title: '个人应缴金额' ,width:'10%',align: 'center'},
			  
			  {field: 'sOvpCompRatio', title: '单位应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sOvpCompAmount', title: '单位应缴金额' ,width:'10%',align: 'center'},
			  {field: 'sOvpPersRatio', title: '个人应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sOvpPersAmount', title: '个人应缴金额' ,width:'10%',align: 'center'},
			  
			  {field: 'sSpMediCompRatio', title: '单位应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sSpMediCompAmount', title: '单位应缴金额' ,width:'10%',align: 'center'},
			  {field: 'sSpMediPersRatio', title: '个人应缴比例' ,width:'10%',align: 'center'},
			  {field: 'sSpMediPersAmount', title: '个人应缴金额' ,width:'10%',align: 'center'}
		  ]
	    ],
	    toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
  	    defaultToolbar: ['filter','exports', 'print'],
	    page: true,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});
  
	//头工具栏事件
	table.on('toolbar(LAY-chSocialInfo-manage)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    switch(obj.event){
	      //导出
	      case 'LAYTABLE_EXPORT_USER':
	    	  layer.alert('正在导出，请不要重复操作');
	    	  var invokeUrl = $("#basePath").val()+'/social/chSocialInfo/ex'+'port.jhtm?page=0&limit=1';
	    	  var formVal = form.val("socialIndex");
	    	  $.each(formVal,function(key,val) {
	    		  if(null == val || '' == val || 'undefined' == val)
	    			  return true;//continue
	    		  invokeUrl += ("&"+key+"="+val);
	    	  });
	    	  window.location.href=invokeUrl;
	    	  break;
	      //自定义头工具栏右侧图标 - 提示
	      case 'LAYTABLE_TIPS':
	        layer.alert('这是工具栏右侧自定义的一个图标按钮');
	        break;
	    };
	});
    
	//监听工具条
	table.on('tool(LAY-chSocialInfo-manage)', function(obj){
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
			  title: '编辑社保信息',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chSocialInfo-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chSocialInfo-manage'); //数据刷新
					  layer.close(index); //关闭弹层
				  });
				  submit.trigger('click');
			  },
			  success: function(layero, index){
			  }*/
		  });
	  }else if(obj.event == 'details')
	  {
		  layer.open({
			  type: 2,
			  title: '查看社保信息',
			  content: ['details.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  btn: ['关闭']
		  });
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-chSocialInfo-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chSocialInfo-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chSocialInfo-manage'),
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
						table.reload('LAY-chSocialInfo-manage'); //数据刷新
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
			title: '添加社保信息',
			content: ['add.jhtm','no'],
			area: ['800px', '550px'],
			maxmin: true
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
										table.reload('LAY-chSocialInfo-manage'); //数据刷新
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
  
  /* 更新页面-------->监听提交 */
  form.on('submit(LAY-chSocialInfo-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSocialInfo-manage'); //重载表格
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
    
  form.on('submit(LAY-chSocialInfo-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chSocialInfo-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSocialInfo-manage'); //重载表格
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
    
  /*laydate.render({
  	elem: '#LAY-chSocialInfo_xxx_date'
  });*/
  
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
  	elem: '#socialYmonth',
  	type: 'month'
  });
  
  var selectStr = '['+
  		'{"id":"hosDepart1levelCns","dkey":"depart1List","max":10},'+
  		'{"id":"hosDepart2levelCns","dkey":"depart2List","max":10},'+
  		'{"id":"wkModalityCns","dkey":"modalityList","max":10},'+
  		'{"id":"compNames","dkey":"compNameList","max":10}'+
  		']';
  
  $(function(){
	  var keys = JSON.parse(selectStr);
	  var list = null;
	  $.each(keys,function(index,value) {
		  list = $("#"+value.dkey).val();
		  selectM({//职务
		      elem: '#'+value.id
		      ,data: (null == list || "" == list || "undefined" == list || "[]" == list)?[]:JSON.parse($("#"+value.dkey).val())
		      ,field: {idName:'key',titleName:'value'}
		      ,max:value.max
		      ,width:300
		      ,delimiter: ','
		  });
	  });
  })
  
  
  //自定义验证
  form.verify({
		name: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '姓名不能为空!';
			if(value.length > 100)
				return '姓名长度不能超过100';
	    },
		cardNo: function(value){
			if(value.length > 25)
				return '身份证号长度不能超过25';
	    },
		compName: function(value){
			if(value.length > 100)
				return '单位名称长度不能超过100';
	    },
		wkModalityCn: function(value){
			if(value.length > 50)
				return '用工形式(人员类别)名称长度不能超过50';
	    },
		workAreaCn: function(value){
			if(value.length > 50)
				return '工作地域-名称长度不能超过50';
	    },
		positCn: function(value){
			if(value.length > 50)
				return '职务-名称长度不能超过50';
	    },
		titleCn: function(value){
			if(value.length > 50)
				return '职称-名称长度不能超过50';
	    },
		hosBranchCn: function(value){
			if(value.length > 50)
				return '医院支部-名称长度不能超过50';
	    },
		hosDepart1levelCn: function(value){
			if(value.length > 50)
				return '医院一级科室-名称长度不能超过50';
	    },
		hosDepart2levelCn: function(value){
			if(value.length > 50)
				return '医院二级科室-名称长度不能超过50';
	    },
		socialYmonth: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '社保年月不能为空!';
			if(value.length > 20)
				return '社保年月长度不能超过20';
	    },
		sPenBase: function(value){
			if(value.length > 10)
				return '基数长度不能超过10';
	    },
		sPenCompRatio: function(value){
			if(value.length > 10)
				return '单位应缴比例长度不能超过10';
	    },
		sPenCompAmount: function(value){
			if(value.length > 10)
				return '单位应缴金额长度不能超过10';
	    },
		sPenCompRecapAmount: function(value){
			if(value.length > 10)
				return '单位实缴金额长度不能超过10';
	    },
		sPenCompRecapDiffe: function(value){
			if(value.length > 10)
				return '单位实缴差额长度不能超过10';
	    },
		sPenCompOverpaid: function(value){
			if(value.length > 10)
				return '单位多缴长度不能超过10';
	    },
		sPenPersRatio: function(value){
			if(value.length > 10)
				return '个人应缴比例长度不能超过10';
	    },
		sPenPersAmount: function(value){
			if(value.length > 10)
				return '个人应缴金额长度不能超过10';
	    },
		sPenPersRecapAmount: function(value){
			if(value.length > 10)
				return '个人实缴金额长度不能超过10';
	    },
		sPenPersRecapDiffe: function(value){
			if(value.length > 10)
				return '个人实缴差额长度不能超过10';
	    },
		sPenPersComple: function(value){
			if(value.length > 10)
				return '个人补缴长度不能超过10';
	    },
		sMediBase: function(value){
			if(value.length > 10)
				return '基数长度不能超过10';
	    },
		sMediCompRatio: function(value){
			if(value.length > 10)
				return '单位应缴比例长度不能超过10';
	    },
		sMediCompAmount: function(value){
			if(value.length > 10)
				return '单位应缴金额长度不能超过10';
	    },
		sMediCompRecapAmount: function(value){
			if(value.length > 10)
				return '单位实缴金额长度不能超过10';
	    },
		sMediCompRecapDiffe: function(value){
			if(value.length > 10)
				return '单位实缴差额长度不能超过10';
	    },
		sMediCompOverpaid: function(value){
			if(value.length > 10)
				return '单位多缴长度不能超过10';
	    },
		sMediPersRatio: function(value){
			if(value.length > 10)
				return '个人应缴比例长度不能超过10';
	    },
		sMediPersAmount: function(value){
			if(value.length > 10)
				return '个人应缴金额长度不能超过10';
	    },
		sMediPersRecapAmount: function(value){
			if(value.length > 10)
				return '个人实缴金额长度不能超过10';
	    },
		sMediPersRecapDiffe: function(value){
			if(value.length > 10)
				return '个人实缴差额长度不能超过10';
	    },
		sMediPersComple: function(value){
			if(value.length > 10)
				return '个人补缴长度不能超过10';
	    },
		sUnempBase: function(value){
			if(value.length > 10)
				return '基数长度不能超过10';
	    },
		sUnempCompRatio: function(value){
			if(value.length > 10)
				return '单位应缴比例长度不能超过10';
	    },
		sUnempCompAmount: function(value){
			if(value.length > 10)
				return '单位应缴金额长度不能超过10';
	    },
		sUnempCompRecapAmount: function(value){
			if(value.length > 10)
				return '单位实缴金额长度不能超过10';
	    },
		sUnempCompRecapDiffe: function(value){
			if(value.length > 10)
				return '单位实缴差额长度不能超过10';
	    },
		sUnempCompOverpaid: function(value){
			if(value.length > 10)
				return '单位多缴长度不能超过10';
	    },
		sUnempPersRatio: function(value){
			if(value.length > 10)
				return '个人应缴比例长度不能超过10';
	    },
		sUnempPersAmount: function(value){
			if(value.length > 10)
				return '个人应缴金额长度不能超过10';
	    },
		sUnempPersRecapAmount: function(value){
			if(value.length > 10)
				return '个人实缴金额长度不能超过10';
	    },
		sUnempPersRecapDiffe: function(value){
			if(value.length > 10)
				return '个人实缴差额长度不能超过10';
	    },
		sUnempPersComple: function(value){
			if(value.length > 10)
				return '个人补缴长度不能超过10';
	    },
		sInjuryBase: function(value){
			if(value.length > 10)
				return '基数长度不能超过10';
	    },
		sInjuryCompRatio: function(value){
			if(value.length > 10)
				return '单位应缴比例长度不能超过10';
	    },
		sInjuryCompAmount: function(value){
			if(value.length > 10)
				return '单位应缴金额长度不能超过10';
	    },
		sInjuryCompRecapAmount: function(value){
			if(value.length > 10)
				return '单位实缴金额长度不能超过10';
	    },
		sInjuryCompRecapDiffe: function(value){
			if(value.length > 10)
				return '单位实缴差额长度不能超过10';
	    },
		sInjuryCompOverpaid: function(value){
			if(value.length > 10)
				return '单位多缴长度不能超过10';
	    },
		sInjuryPersRatio: function(value){
			if(value.length > 10)
				return '个人应缴比例长度不能超过10';
	    },
		sInjuryPersAmount: function(value){
			if(value.length > 10)
				return '个人应缴金额长度不能超过10';
	    },
		sInjuryPersRecapAmount: function(value){
			if(value.length > 10)
				return '个人实缴金额长度不能超过10';
	    },
		sInjuryPersRecapDiffe: function(value){
			if(value.length > 10)
				return '个人实缴差额长度不能超过10';
	    },
		sInjuryPersComple: function(value){
			if(value.length > 10)
				return '个人补缴长度不能超过10';
	    },
		sBirthBase: function(value){
			if(value.length > 10)
				return '基数长度不能超过10';
	    },
		sBirthCompRatio: function(value){
			if(value.length > 10)
				return '单位应缴比例长度不能超过10';
	    },
		sBirthCompAmount: function(value){
			if(value.length > 10)
				return '单位应缴金额长度不能超过10';
	    },
		sBirthCompRecapAmount: function(value){
			if(value.length > 10)
				return '单位实缴金额长度不能超过10';
	    },
		sBirthCompRecapDiffe: function(value){
			if(value.length > 10)
				return '单位实缴差额长度不能超过10';
	    },
		sBirthCompOverpaid: function(value){
			if(value.length > 10)
				return '单位多缴长度不能超过10';
	    },
		sBirthPersRatio: function(value){
			if(value.length > 10)
				return '个人应缴比例长度不能超过10';
	    },
		sBirthPersAmount: function(value){
			if(value.length > 10)
				return '个人应缴金额长度不能超过10';
	    },
		sBirthPersRecapAmount: function(value){
			if(value.length > 10)
				return '个人实缴金额长度不能超过10';
	    },
		sBirthPersRecapDiffe: function(value){
			if(value.length > 10)
				return '个人实缴差额长度不能超过10';
	    },
		sBirthPersComple: function(value){
			if(value.length > 10)
				return '个人补缴长度不能超过10';
	    },
		sAnnuityBase: function(value){
			if(value.length > 10)
				return '基数长度不能超过10';
	    },
		sAnnuityCompRatio: function(value){
			if(value.length > 10)
				return '单位应缴比例长度不能超过10';
	    },
		sAnnuityCompAmount: function(value){
			if(value.length > 10)
				return '单位应缴金额长度不能超过10';
	    },
		sAnnuityCompRecapAmount: function(value){
			if(value.length > 10)
				return '单位实缴金额长度不能超过10';
	    },
		sAnnuityCompRecapDiffe: function(value){
			if(value.length > 10)
				return '单位实缴差额长度不能超过10';
	    },
		sAnnuityCompOverpaid: function(value){
			if(value.length > 10)
				return '单位多缴长度不能超过10';
	    },
		sAnnuityPersRatio: function(value){
			if(value.length > 10)
				return '个人应缴比例长度不能超过10';
	    },
		sAnnuityPersAmount: function(value){
			if(value.length > 10)
				return '个人应缴金额长度不能超过10';
	    },
		sAnnuityPersRecapAmount: function(value){
			if(value.length > 10)
				return '个人实缴金额长度不能超过10';
	    },
		sAnnuityPersRecapDiffe: function(value){
			if(value.length > 10)
				return '个人实缴差额长度不能超过10';
	    },
		sAnnuityPersComple: function(value){
			if(value.length > 10)
				return '个人补缴长度不能超过10';
	    },
		sOvpBase: function(value){
			if(value.length > 10)
				return '基数长度不能超过10';
	    },
		sOvpCompRatio: function(value){
			if(value.length > 10)
				return '单位应缴比例长度不能超过10';
	    },
		sOvpCompAmount: function(value){
			if(value.length > 10)
				return '单位应缴金额长度不能超过10';
	    },
		sOvpCompRecapAmount: function(value){
			if(value.length > 10)
				return '单位实缴金额长度不能超过10';
	    },
		sOvpCompRecapDiffe: function(value){
			if(value.length > 10)
				return '单位实缴差额长度不能超过10';
	    },
		sOvpCompOverpaid: function(value){
			if(value.length > 10)
				return '单位多缴长度不能超过10';
	    },
		sOvpPersRatio: function(value){
			if(value.length > 10)
				return '个人应缴比例长度不能超过10';
	    },
		sOvpPersAmount: function(value){
			if(value.length > 10)
				return '个人应缴金额长度不能超过10';
	    },
		sOvpPersRecapAmount: function(value){
			if(value.length > 10)
				return '个人实缴金额长度不能超过10';
	    },
		sOvpPersRecapDiffe: function(value){
			if(value.length > 10)
				return '个人实缴差额长度不能超过10';
	    },
		sOvpPersComple: function(value){
			if(value.length > 10)
				return '个人补缴长度不能超过10';
	    },
		sSpMediBase: function(value){
			if(value.length > 10)
				return '基数长度不能超过10';
	    },
		sSpMediCompRatio: function(value){
			if(value.length > 10)
				return '单位应缴比例长度不能超过10';
	    },
		sSpMediCompAmount: function(value){
			if(value.length > 10)
				return '单位应缴金额长度不能超过10';
	    },
		sSpMediCompRecapAmount: function(value){
			if(value.length > 10)
				return '单位实缴金额长度不能超过10';
	    },
		sSpMediCompRecapDiffe: function(value){
			if(value.length > 10)
				return '单位实缴差额长度不能超过10';
	    },
		sSpMediCompOverpaid: function(value){
			if(value.length > 10)
				return '单位多缴长度不能超过10';
	    },
		sSpMediPersRatio: function(value){
			if(value.length > 10)
				return '个人应缴比例长度不能超过10';
	    },
		sSpMediPersAmount: function(value){
			if(value.length > 10)
				return '个人应缴金额长度不能超过10';
	    },
		sSpMediPersRecapAmount: function(value){
			if(value.length > 10)
				return '个人实缴金额长度不能超过10';
	    },
		sSpMediPersRecapDiffe: function(value){
			if(value.length > 10)
				return '个人实缴差额长度不能超过10';
	    },
		sSpMediPersComple: function(value){
			if(value.length > 10)
				return '个人补缴长度不能超过10';
	    },
		remark4month: function(value){
			if(value.length > 100)
				return '月备注长度不能超过100';
	    },
		remark4year: function(value){
			if(value.length > 100)
				return '年备注长度不能超过100';
	    },
		persRecapDiffe4addup: function(value){
			if(value.length > 10)
				return '本年个人实缴累计差额长度不能超过10';
	    },
		persCompleDiffe4addup: function(value){
			if(value.length > 10)
				return '本年个人实缴累计差额长度不能超过10';
	    },
		btimpNo: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '导入编号不能为空!';
			if(value.length > 25)
				return '导入编号长度不能超过25';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chSocialInfo', {})
});