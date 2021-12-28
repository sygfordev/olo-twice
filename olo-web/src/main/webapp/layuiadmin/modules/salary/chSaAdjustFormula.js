layui.define(['table','form','upload','element','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	upload = layui.upload,
	laydate = layui.laydate,
	element = layui.element,
	laypage = layui.laypage,
	form = layui.form;
	
	//调资-公式表-表格渲染
	table.render({
		elem: '#LAY-chSaAdjustFormula-manage',
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
			  {field: 'target', title: '目标：职称/职务/学历' ,sort: true,width:'5%'},
			  {field: 'targetLevelCn', title: '目标等级' ,sort: true,width:'5%'},
			  {field: 'formula2down', title: '2年及以下' ,width:'5%'},
			  {field: 'formula3to4', title: '3-4年' ,width:'5%'},
			  {field: 'formula5to6', title: '5-6年' ,width:'5%'},
			  {field: 'formula7to8', title: '7-8年' ,width:'5%'},
			  {field: 'formula9to10', title: '9-10年' ,width:'5%'},
			  {field: 'formula11to12', title: '11-12年' ,width:'5%'},
			  {field: 'formula13up', title: '13年以上' ,width:'5%'},
			  {field: 'gratdations', title: '级差' ,width:'5%'},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'5%'},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chSaAdjustFormula-manager-operator',width:'15%'}
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
	table.on('tool(LAY-chSaAdjustFormula-manage)', function(obj){
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
			  title: '编辑调资-公式表',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chSaAdjustFormula-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chSaAdjustFormula-manage'); //数据刷新
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
			  title: '查看调资-公式表',
			  content: ['details.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  btn: ['关闭']
		  });
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-chSaAdjustFormula-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chSaAdjustFormula-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chSaAdjustFormula-manage'),
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
						table.reload('LAY-chSaAdjustFormula-manage'); //数据刷新
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
			title: '添加调资-公式表',
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
  form.on('submit(LAY-chSaAdjustFormula-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSaAdjustFormula-manage'); //重载表格
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
    
  form.on('submit(LAY-chSaAdjustFormula-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chSaAdjustFormula-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSaAdjustFormula-manage'); //重载表格
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
  
  laydate.render({
  	elem: '#LAY-chSaAdjustFormula_xxx_date'
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
    elem: '#formula_upload'
    ,url: 'doImport.jhtm' //改成您自己的上传接口
    ,multiple: false
    ,exts: 'xls|xlsx'
    ,accept: 'file'
    ,auto: false
    ,bindAction: '#LAY-chSaAdjustFormula-upload-submit'
    ,before: function(obj){
    	element.progress('demo', '0%'); //进度条复位
    },
    choose: function(obj){
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
  		formulaType: function () {
  			layer.load(2, {time: 30*60*1000});
            return $("#formulaType").val();
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
  
  //调资-公式表-表格渲染
  table.render({
		elem: '#LAY-chSaAdjustFormula-showList',
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
			formulaType: $("#formulaType").val(),
			uniqueKey: $("#uniqueKey").val()
	    },
	    cols: 
	    [
	  	  [
	  		  {type: 'checkbox', fixed: 'left',hide:true},
	  		  {type: 'numbers', title:'序号', width:'8%',fixed: 'left'},
			  {field: 'id', title: '自增主键' ,sort: true,width:'8%',hide:true},
			  {field: 'target', title: '职称/职务/学历' ,width:'10%'},
			  {field: 'targetLevelCn', title: '等级' ,width:'8%'},
			  {field: 'formula2down', title: '2年及以下' ,width:'8%'},
			  {field: 'formula3to4', title: '3-4年' ,width:'8%'},
			  {field: 'formula5to6', title: '5-6年' ,width:'8%'},
			  {field: 'formula7to8', title: '7-8年' ,width:'8%'},
			  {field: 'formula9to10', title: '9-10年' ,width:'8%'},
			  {field: 'formula11to12', title: '11-12年' ,width:'8%'},
			  {field: 'formula13up', title: '13年以上' ,width:'8%'},
			  {field: 'gratdations', title: '级差' ,width:'9%'},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},
		      /*{field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'10%'},*/
		      {title: '操作', width: 150, align: 'center', fixed: 'right', width:'15%',hide:true}
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
		target: function(value){
			if(null == value || "".equals(value))
				return '目标：职称/职务/学历不能为空!';
			if(value.length > 20)
				return '目标：职称/职务/学历长度不能超过20';
	    },
		targetLevelCn: function(value){
			if(null == value || "".equals(value))
				return '目标等级不能为空!';
			if(value.length > 20)
				return '目标等级长度不能超过20';
	    },
		formula2down: function(value){
			if(value.length > 10)
				return '2年及以下长度不能超过10';
	    },
		formula3to4: function(value){
			if(value.length > 10)
				return '3-4年长度不能超过10';
	    },
		formula5to6: function(value){
			if(value.length > 10)
				return '5-6年长度不能超过10';
	    },
		formula7to8: function(value){
			if(value.length > 10)
				return '7-8年长度不能超过10';
	    },
		formula9to10: function(value){
			if(value.length > 10)
				return '9-10年长度不能超过10';
	    },
		formula11to12: function(value){
			if(value.length > 10)
				return '11-12年长度不能超过10';
	    },
		formula13up: function(value){
			if(value.length > 10)
				return '13年以上长度不能超过10';
	    },
		gratdations: function(value){
			if(value.length > 10)
				return '级差长度不能超过10';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chSaAdjustFormula', {})
});