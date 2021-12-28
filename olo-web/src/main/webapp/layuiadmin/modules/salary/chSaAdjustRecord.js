layui.define(['table','form','laydate','upload','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laydate = layui.laydate,
	laypage = layui.laypage,
	upload = layui.upload,
	form = layui.form;
	
	//调资记录表-表格渲染
	table.render({
		elem: '#LAY-chSaAdjustRecord-manage',
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
	  		  {type: 'numbers',title:'序号', fixed: 'left',width:'10%'},
			  {field: 'id', title: '自增主键' ,sort: false,width:'15%',hide:true},
			  {field: 'adjustMonth', title: '调资年月' ,width:'15%'},
			  {field: '', title: '员工岗位类别划分' ,templet: adjustFmt1,width:'15%'},
			  {field: 'adjustFormulas', title: '薪级分类', templet: formulaFmt,width:'20%'},
			  {field: '', title: '调资详情' ,templet: adjustFmt2,width:'20%'},
			  {field: 'adjustUser', title: '操作人' ,sort: false,width:'20%'},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'20%'}
		  ]
	    ],
	    page: true,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});
	
	
    function formulaFmt(d)
    {
    	var result = '';
    	$.each(d.usedAdjustMap, function (i, item){
    		var uniqueKey = null;
    		var adjustType = null;
    		for(var key in item){
    	        if('formula_type'==key)
    	        	adjustType = item[key];
    	        if('unique_key'==key)
    	        	uniqueKey = item[key];
    	    }
    		result += '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="formula'+i+'">'+adjustType+'</a>';
    	});
    	return result;
    }
    
    function adjustFmt1(d)
    {
    	return '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="adjustResult0">查看详情</a>';
    }
    
    function adjustFmt2(d)
    {
    	return '<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="adjustResult1">查看详情</a>';
    }
  
	//监听工具条
	table.on('tool(LAY-chSaAdjustRecord-manage)', function(obj){
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
			  title: '编辑调资记录表',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chSaAdjustRecord-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chSaAdjustRecord-manage'); //数据刷新
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
			  title: '查看调资记录表',
			  content: ['details.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  btn: ['关闭']
		  });
	  }else if(obj.event.startsWith("formula")){
		  var uniqueKey = null;
		  var adjustType = null;
		  
		  var index = obj.event.slice(7, obj.event.length);
		  $.each(obj.data.usedAdjustMap, function (i, item){
			  if(parseInt(index)!=i)return true;
			  for(var key in item){
				  if('formula_type'==key)
					adjustType = item[key];
	    	      if('unique_key'==key)
	    	      	uniqueKey = item[key];
	    	  }
		  });
		  //子页面打开新的tab页面
		  var l = parent === self ? layui : top.layui;
		  var title = "薪级分类("+adjustType+")";
		  l.index.openTabsPage('salary/chSaAdjustFormula/showList.jhtm?formulaType='+adjustType+"&uniqueKey="+uniqueKey, title);
	  }else if(obj.event.startsWith("adjustResult")){
		  var index = obj.event.slice(12, obj.event.length);
		  //子页面打开新的tab页面
		  var l = parent === self ? layui : top.layui;
		  var title = "0"==index?"岗位类别":"调资结果";
		  var url = 'salary/chSaAdjustSalary/'+("0"==index?'showList.jhtm':'index.jhtm')+'?recordId='+obj.data.id;
		  l.index.openTabsPage(url, title);
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-chSaAdjustRecord-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chSaAdjustRecord-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chSaAdjustRecord-manage'),
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
						table.reload('LAY-chSaAdjustRecord-manage'); //数据刷新
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
	  	/*layer.open({
			type: 2,
			title: '新增调资',
			content: ['add.jhtm','no'],
			area: ['800px', '550px'],
			maxmin: true
		});*/
		  //子页面打开新的tab页面
		  var l = parent === self ? layui : top.layui;
		  var title = "新增调资";
		  l.index.openTabsPage('salary/chSaAdjustSalary/add.jhtm', title);
	  },
	  uploadFormula: function(){
		  //子页面打开新的tab页面
		  var l = parent === self ? layui : top.layui;
		  var title = "上传薪级分类";
		  l.index.openTabsPage('salary/chSaAdjustFormula/upload.jhtm', title);
	  }
  }
  
  //---------表头按钮触发激活----------
  $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
  	var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
  /* 更新页面-------->监听提交 */
  form.on('submit(LAY-chSaAdjustRecord-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSaAdjustRecord-manage'); //重载表格
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
    
  form.on('submit(LAY-chSaAdjustRecord-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chSaAdjustRecord-add-submit)', function(data){
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
				parent.layui.table.reload('LAY-chSaAdjustRecord-manage'); //重载表格
				//parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
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
  	elem: '#LAY-chSaAdjustRecord_xxx_date'
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
  });
  
  laydate.render({
  	elem: '#adjustMonth',
  	type: 'month'
  });
  
  //拖拽上传
  var uploadInst = upload.render({
    elem: '#adjust_upload'
    ,url: 'doImport.jhtm' //改成您自己的上传接口
    ,multiple: false
    ,exts: 'xls|xlsx'
    ,accept: 'file'
    ,auto: false
    ,bindAction: '#LAY-chSaAdjustRecord-add-submit'
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
  		netTargetYearmonth: function () {
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
  
  //自定义验证
  form.verify({
		adjustMonth: function(value){
			if(value.length > 20)
				return '调资年月长度不能超过20';
	    },
		adjustFormulas: function(value){
			if(value.length > 80)
				return '调资公式列表长度不能超过80';
	    },
		adjustAccount: function(value){
			if(null == value || "".equals(value))
				return '操作账户不能为空!';
			if(value.length > 20)
				return '操作账户长度不能超过20';
	    },
		adjustUser: function(value){
			if(null == value || "".equals(value))
				return '操作人不能为空!';
			if(value.length > 50)
				return '操作人长度不能超过50';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chSaAdjustRecord', {})
});