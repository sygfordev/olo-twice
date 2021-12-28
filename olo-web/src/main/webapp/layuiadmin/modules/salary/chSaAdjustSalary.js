layui.define(['table','form','upload','element','layer','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	upload = layui.upload,
	layer = layui.layer,
	laydate = layui.laydate,
	element = layui.element,
	laypage = layui.laypage,
	form = layui.form;
	
	//调资表-表格渲染
	table.render({
		elem: '#LAY-chSaAdjustSalary-manage',
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
			recordId: $("#recordId").val()
	    },
	    cols: 
	    [
	  	  [
	  		  /*{type: 'checkbox', fixed: 'left'},*/
	  		{type: 'numbers', title:'序号',fixed: 'left',width:'5%'},
			  {field: 'id', title: '自增主键' ,width:'8%',hide:true},
			  {field: 'name', title: '姓名' ,fixed: 'left',width:'8%'},
			  {field: 'cardNo', title: '身份证号',fixed: 'left',width:'12%'},
			  {field: 'salaryAdjustTypeCn', title: '调资类别' ,width:'8%'},
			  {field: 'hosDepart1levelCn', title: '医院一级科室' ,width:'8%'},
			  {field: 'hosDepart2levelCn', title: '医院二级科室' ,width:'8%'},
			  {field: 'edu4max', title: '最高学历' ,width:'8%'},
			  {field: 'edu4first', title: '第一学历' ,width:'8%'},
			  {field: 'edu4wage', title: '工资学历' ,width:'8%'},
			  {field: 'firstWorkTime', title: '首次参加工作时间' ,templet: '<div>{{#if(d.firstWorkTime){}} {{layui.util.toDateString(d.firstWorkTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'workedYear', title: '工龄' ,width:'8%'},
			  {field: 'posit4nowCn', title: '现行政职务' ,width:'8%'},
			  {field: 'posit4nowStartTime', title: '任职开始时间' ,templet: '<div>{{#if(d.posit4nowStartTime){}} {{layui.util.toDateString(d.posit4nowStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'posit4nowYears', title: '现行政级别年限' ,width:'8%'},
			  {field: 'positLevelCn', title: '行政级别' ,width:'8%'},
			  {field: 'positLevelCode', title: '行政级别代码' ,width:'8%'},
			  {field: 'onPrinPositStartTime', title: '任正职时间' ,templet: '<div>{{#if(d.onPrinPositStartTime){}} {{layui.util.toDateString(d.onPrinPositStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'onPrinPositYears', title: '任正职年限' ,width:'8%'},
			  {field: 'onDeptPositStartTime', title: '任副职时间' ,templet: '<div>{{#if(d.onDeptPositStartTime){}} {{layui.util.toDateString(d.onDeptPositStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'onDeptPositYears', title: '任副职年限' ,width:'8%'},
			  {field: 'title4max', title: '最高职称' ,width:'8%'},
			  {field: 'title4maxGotTime', title: '最高职称取得时间' ,templet: '<div>{{#if(d.title4maxGotTime){}} {{layui.util.toDateString(d.title4maxGotTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'title4maxGotYears', title: '最高职称取得年限' ,width:'8%'},
			  {field: 'title4maxClassCn', title: '职称序列' ,width:'8%'},
			  {field: 'title4maxLevelCn', title: '职称级别' ,width:'8%'},
			  {field: 'wagesId', title: '工资账人员编号' ,width:'8%'},
			  {field: 'wagesSeq', title: '工资账序号' ,width:'8%'},
			  {field: 'wagesName', title: '工资账姓名' ,width:'8%'},
			  {field: 'wagesModalityCn', title: '工资账用工形式' ,width:'8%'},
			  {field: 'wagesDepart', title: '工资账科室' ,width:'8%'},
			  {field: 'wagesStandardBef', title: '原岗位工资标准' ,width:'8%'},
			  {field: 'adjust4title', title: '职称调资' ,width:'8%'},
			  {field: 'adjust4posit', title: '职务调资' ,width:'8%'},
			  {field: 'adjust4edu', title: '学历调资' ,width:'8%'},
			  {field: 'wagesStandardAft', title: '调整后工资标准' ,width:'8%'},
			  {field: 'adjustProof', title: '调资依据' ,width:'8%'},
			  {field: 'adjustDiffe', title: '调资差额' ,width:'8%'},
			  {field: 'remark', title: '备注' ,width:'8%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'8%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'}
		      /*{field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chSaAdjustSalary-manager-operator',width:'18%'}*/
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
	table.on('tool(LAY-chSaAdjustSalary-manage)', function(obj){
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
			  title: '编辑调资表',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chSaAdjustSalary-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chSaAdjustSalary-manage'); //数据刷新
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
			  title: '查看调资表',
			  content: ['details.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  btn: ['关闭']
		  });
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-chSaAdjustSalary-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chSaAdjustSalary-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chSaAdjustSalary-manage'),
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
						table.reload('LAY-chSaAdjustSalary-manage'); //数据刷新
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
			title: '添加调资表',
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
  form.on('submit(LAY-chSaAdjustSalary-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSaAdjustSalary-manage'); //重载表格
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
    
  form.on('submit(LAY-chSaAdjustSalary-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  /*form.on('submit(LAY-chSaAdjustSalary-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSaAdjustSalary-manage'); //重载表格
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
  });*/
    
  laydate.render({
	  	elem: '#adjustMonth',
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
  
  //拖拽上传
  var uploadInst = upload.render({
    elem: '#adjust_upload'
    ,url: 'doImport.jhtm' //改成您自己的上传接口
    ,multiple: false
    ,exts: 'xls|xlsx'
    ,accept: 'file'
    ,auto: false
    ,bindAction: '#LAY-chSaAdjustSalary-add-submit'
    ,before: function(obj){
    	element.progress('demo', '0%'); //进度条复位
    },
    choose: function(obj){
    	var adjustMonth = $("#adjustMonth").val();
		if(!$.trim(adjustMonth))
		{
			 parent.layer.msg("请先选择调资年月！", {time: 2000});
			 return;
		}
        //var files = obj.pushFile();
        // 预读本地文件示例, 不支持 ie8
        obj.preview(function (index, file, result) {
            layui.$('#uploadDemoView').removeClass('layui-hide').find('label').html(file.name);
        });
    },
    progress: function(n, elem, res, index){
	    var percent = n + '%' //获取进度百分比
	    element.progress('demo', percent); //可配合 layui 进度条元素使用
	    
	    console.log(elem); //得到当前触发的元素 DOM 对象。可通过该元素定义的属性值匹配到对应的进度条。
	    console.log(res); //得到 progress 响应信息
	    console.log(index); //得到当前上传文件的索引，多文件上传时的进度条控制，如：
	    element.progress('demo-'+ index, n + '%'); //进度条
	},
  	data: {
  		adjustMonth: function () {
  			layer.load(2, {time: 30*60*1000});
            return $("#adjustMonth").val();
  		}
  	},
    done: function(res){
    	var code = null!=res?res.statusCode:null;
    	var msg = null != res?res.message:null;
    	if(null == msg) msg = "导入失败!";
    	layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 5000});
    	layer.closeAll('loading');
    	if("200"  == code){
    		$('#demoText').html(''); //置空上传失败的状态
    	}
    },
    error: function(){
    	//演示失败状态，并实现重传
        var demoText = $('#demoText');
        demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
        demoText.find('.demo-reload').on('click', function(){
          uploadInst.upload();
        });
    }
  });
  
  
  //调资表-表格渲染
	table.render({
		elem: '#LAY-chSaAdjustSalary-showList',
		url: 'showList/do.jhtm',
		/*parseData: function(res){
		    return {
		    	"code": "200"==res.retCode?0:1,
		    	"msg": res.retMsg,
		    	"count": res.count,
		    	"data": res.list
		    };
		},*/
		where: {
			recordId: $("#recordId").val()
	    },
	    cols: 
	    [
	  	  [
	  		  {type: 'numbers', title:'序号',fixed: 'left',width:'5%'},
			  {field: 'id', title: '自增主键' ,width:'8%',hide:true},
			  {field: 'name', title: '姓名' ,fixed: 'left',width:'8%'},
			  {field: 'cardNo', title: '身份证号',fixed: 'left',width:'12%'},
			  {field: 'salaryAdjustTypeCn', title: '调资类别' ,width:'8%'},
			  {field: 'hosDepart1levelCn', title: '医院一级科室' ,width:'8%'},
			  {field: 'hosDepart2levelCn', title: '医院二级科室' ,width:'8%'},
			  {field: 'edu4max', title: '最高学历' ,width:'8%'},
			  {field: 'edu4first', title: '第一学历' ,width:'8%'},
			  {field: 'edu4wage', title: '工资学历' ,width:'8%'},
			  {field: 'firstWorkTime', title: '首次参加工作时间' ,templet: '<div>{{#if(d.firstWorkTime){}} {{layui.util.toDateString(d.firstWorkTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'workedYear', title: '工龄' ,width:'8%'},
			  {field: 'posit4nowCn', title: '现行政职务' ,width:'8%'},
			  {field: 'posit4nowStartTime', title: '任职开始时间' ,templet: '<div>{{#if(d.posit4nowStartTime){}} {{layui.util.toDateString(d.posit4nowStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'posit4nowYears', title: '现行政级别年限' ,width:'8%'},
			  {field: 'positLevelCn', title: '行政级别' ,width:'8%'},
			  {field: 'positLevelCode', title: '行政级别代码' ,width:'8%'},
			  {field: 'onPrinPositStartTime', title: '任正职时间' ,templet: '<div>{{#if(d.onPrinPositStartTime){}} {{layui.util.toDateString(d.onPrinPositStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'onPrinPositYears', title: '任正职年限' ,width:'8%'},
			  {field: 'onDeptPositStartTime', title: '任副职时间' ,templet: '<div>{{#if(d.onDeptPositStartTime){}} {{layui.util.toDateString(d.onDeptPositStartTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'onDeptPositYears', title: '任副职年限' ,width:'8%'},
			  {field: 'title4max', title: '最高职称' ,width:'8%'},
			  {field: 'title4maxGotTime', title: '最高职称取得时间' ,templet: '<div>{{#if(d.title4maxGotTime){}} {{layui.util.toDateString(d.title4maxGotTime, "yyyy-MM-dd") }} {{#} else{}} {{#}}}</div>',width:'8%'},
			  {field: 'title4maxGotYears', title: '最高职称取得年限' ,width:'8%'},
			  {field: 'title4maxClassCn', title: '职称序列' ,width:'8%'},
			  {field: 'title4maxLevelCn', title: '职称级别' ,width:'8%'},
			  {field: 'wagesId', title: '工资账人员编号' ,width:'8%'},
			  {field: 'wagesSeq', title: '工资账序号' ,width:'8%'},
			  {field: 'wagesName', title: '工资账姓名' ,width:'8%'},
			  {field: 'wagesModalityCn', title: '工资账用工形式' ,width:'8%'},
			  {field: 'wagesDepart', title: '工资账科室' ,width:'8%'},
			  {field: 'wagesStandardBef', title: '原岗位工资标准' ,width:'8%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'8%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%',hide:true}
		  ]
	    ],
	    page: true,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});
  //自定义验证
  form.verify({
		name: function(value){
			if(null == value || "".equals(value))
				return '职工姓名不能为空!';
			if(value.length > 100)
				return '职工姓名长度不能超过100';
	    },
		cardNo: function(value){
			if(value.length > 25)
				return '卡号（身份）长度不能超过25';
	    },
		wagesId: function(value){
			if(null == value || "".equals(value))
				return '人员编号（工资出账）不能为空!';
			if(value.length > 20)
				return '人员编号（工资出账）长度不能超过20';
	    },
		wagesSeq: function(value){
			if(null == value || "".equals(value))
				return '工资账序号不能为空!';
			if(value.length > 20)
				return '工资账序号长度不能超过20';
	    },
		wagesName: function(value){
			if(null == value || "".equals(value))
				return '工资账姓名不能为空!';
			if(value.length > 50)
				return '工资账姓名长度不能超过50';
	    },
		wagesModalityCn: function(value){
			if(null == value || "".equals(value))
				return '工资账用工形式不能为空!';
			if(value.length > 50)
				return '工资账用工形式长度不能超过50';
	    },
		wagesDepart: function(value){
			if(value.length > 50)
				return '工资账科室长度不能超过50';
	    },
		wagesStandardBef: function(value){
			if(value.length > 10)
				return '原岗位工资标准长度不能超过10';
	    },
		salaryAdjustTypeCn: function(value){
			if(value.length > 10)
				return '调资类别长度不能超过10';
	    },
		hosDepart1levelCn: function(value){
			if(value.length > 50)
				return '医院一级科室长度不能超过50';
	    },
		hosDepart2levelCn: function(value){
			if(value.length > 50)
				return '医院二级科室长度不能超过50';
	    },
		edu4max: function(value){
			if(value.length > 50)
				return '最高学历长度不能超过50';
	    },
		edu4first: function(value){
			if(value.length > 50)
				return '第一学历长度不能超过50';
	    },
		edu4wage: function(value){
			if(value.length > 50)
				return '工资学历长度不能超过50';
	    },
		firstWorkTime: function(value){
			if(value.length > 26)
				return '首次参加工作时间长度不能超过26';
	    },
		workedYear: function(value){
			if(value.length > 10)
				return '工龄长度不能超过10';
	    },
		posit4nowCn: function(value){
			if(value.length > 50)
				return '现行政职务长度不能超过50';
	    },
		posit4nowStartTime: function(value){
			if(value.length > 26)
				return '任职开始时间长度不能超过26';
	    },
		posit4nowYears: function(value){
			if(value.length > 10)
				return '现行政级别年限长度不能超过10';
	    },
		positLevelCn: function(value){
			if(value.length > 50)
				return '行政级别长度不能超过50';
	    },
		positLevelCode: function(value){
			if(value.length > 50)
				return '行政级别代码长度不能超过50';
	    },
		onPrinPositStartTime: function(value){
			if(value.length > 26)
				return '任正职时间长度不能超过26';
	    },
		onPrinPositYears: function(value){
			if(value.length > 26)
				return '任正职年限长度不能超过26';
	    },
		onDeptPositStartTime: function(value){
			if(value.length > 26)
				return '任副职时间长度不能超过26';
	    },
		onDeptPositYears: function(value){
			if(value.length > 26)
				return '任副职年限长度不能超过26';
	    },
		title4max: function(value){
			if(value.length > 50)
				return '最高职称长度不能超过50';
	    },
		title4maxGotTime: function(value){
			if(value.length > 26)
				return '最高职称取得时间长度不能超过26';
	    },
		title4maxGotYears: function(value){
			if(value.length > 10)
				return '最高职称取得年限长度不能超过10';
	    },
		title4maxClassCn: function(value){
			if(value.length > 50)
				return '职称序列长度不能超过50';
	    },
		title4maxLevelCn: function(value){
			if(value.length > 50)
				return '职称级别长度不能超过50';
	    },
		adjust4title: function(value){
			if(value.length > 10)
				return '职称调资长度不能超过10';
	    },
		adjust4posit: function(value){
			if(value.length > 10)
				return '职务调资长度不能超过10';
	    },
		adjust4edu: function(value){
			if(value.length > 10)
				return '学历调资长度不能超过10';
	    },
		wagesStandardAft: function(value){
			if(value.length > 10)
				return '调整后工资标准长度不能超过10';
	    },
		adjustProof: function(value){
			if(value.length > 20)
				return '调资依据长度不能超过20';
	    },
		adjustDiffe: function(value){
			if(value.length > 10)
				return '调资差额长度不能超过10';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chSaAdjustSalary', {})
});