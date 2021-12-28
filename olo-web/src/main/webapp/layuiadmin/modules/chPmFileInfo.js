layui.define(['table','form','upload','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	upload = layui.upload,
	form = layui.form;
	
	var mkhide = $("#oper").val()=="detail"?true:false;
	var extendRat = mkhide?'15%':'10%';
	//文件信息-表格渲染
	table.render({
		elem: '#LAY-chPmFileInfo-manage',
		url: 'index/do.jhtm',
		where: {
			workerId: $("#workerId").val()
	    },
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
	  		  {type: 'checkbox', fixed: 'left',hide:mkhide},
			  {field: 'id', title: '主键' ,sort: true,width:'5%'},
			  {field: 'workerId', title: '职工编号' ,sort: true,width:'10%',hide:true},
			  {field: 'moduleCode', title: '模块编码' ,sort: true,width:'10%',hide:true},
			  {field: 'moduleName', title: '模块名称' ,sort: true,width:extendRat},
			  {field: 'fileType', title: '文件类型' ,sort: true,width:extendRat},
			  {field: 'fileSize', title: '文件大小' ,sort: true,width:'10%',templet: function(d){return Math.round((d.fileSize/1024))+"KB"}},
			  {field: 'fileUrl', title: '文件内容' ,sort: true,width:'15%',templet: '#imgTpl',hide:true},
		      {field: 'status', title: '状态',templet: '#statusTpl',sort:true,width:'10%',hide:true},
		      {field: 'createTime', title: '创建时间',templet: '<div>{{ layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'15%'},
		      {field: 'updateTime', title: '更新时间',templet: '<div>{{ layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss") }}</div>',width:'15%'},
		      {title: '操作', width: 150, align: 'center', fixed: 'right', toolbar: '#table-chPmFileInfo-manager-operator',width:'15%',hide:mkhide}
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
	table.on('tool(LAY-chPmFileInfo-manage)', function(obj){
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
			  title: '编辑文件信息',
			  content: ['edit.jhtm?id='+data.id,'no'],
			  maxmin: true,
			  area: ['800px', '550px'],
		  	  /*btn: ['确定', '取消'],
			  yes: function(index, layero){
				  var iframeWindow = window['layui-layer-iframe'+ index]
				  submitID = 'LAY-chPmFileInfo-edit-submit'
				  submit = layero.find('iframe').contents().find('#'+ submitID);
				  //监听提交
				  iframeWindow.layui.form.on('submit('+ submitID +')', function(data){
					  var field = data.field; //获取提交的字段
					  //提交 Ajax 成功后，静态更新表格中的数据
					  //$.ajax({});
					  table.reload('LAY-chPmFileInfo-manage'); //数据刷新
					  layer.close(index); //关闭弹层
				  });
				  submit.trigger('click');
			  },
			  success: function(layero, index){
			  }*/
		  });
	  } else if(obj.event === 'download')
	  {
		  
		  layer.open({
			  type: 2,
			  title: '文件下载',
			  content: [$("#basePath").val()+'/personnel/chPmFileInfo/download.jhtm?id='+data.id,'no'],
			  maxmin: false,
			  area: ['800px', '550px'],
			  time:100,
			  timeout : 1000
		  });
		  
		  /*$.ajax({
	             url:'download.jhtm',
	             method:'GET',	             
	             data:{"id":data.id},
	             success:function(res){
					var code = res.statusCode;
					var msg = res.message;
					layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
					if("200" == code)
					{
						layer.close(index);
					}
	             },
	             error:function (res) {
					layer.msg(res.message, {offset: '150px',icon: 2,time: 2000});
	             }
		  });*/
		  /*var ajaxTimeoutTest = $.ajax({
				url:'download.jhtm',  //请求的URL
				timeout : 1000, //超时时间设置，单位毫秒
				type : 'GET',  //请求方式，get或post
				data:{"id":data.id},  //请求所传参数，json格式
				success:function(data){
					alert("成功");
				},
				complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
					if(status=='timeout'){//超时,status还有success,error等值的情况
						ajaxTimeoutTest.abort();
						alert("超时");
					}
				}
			});*/
	  }
  });
  
  //监听搜索
  form.on('submit(LAY-chPmFileInfo-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-chPmFileInfo-manage', {where: field});
  });
  
  //---------表头按钮区域----------
  var active = {
	  batchdel: function(){
	  	var checkStatus = table.checkStatus('LAY-chPmFileInfo-manage'),
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
						table.reload('LAY-chPmFileInfo-manage'); //数据刷新
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
			title: '添加文件信息',
			content: ['add1.jhtm?wkId='+$("#workerId").val(),'no'],
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
  form.on('submit(LAY-chPmFileInfo-edit-submit)', function(data){
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
				parent.layui.table.reload('LAY-chPmFileInfo-manage'); //重载表格
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
    
  form.on('submit(LAY-chPmFileInfo-edit-cancel)', function(data){
  	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
    return false;
  });
  
  
  /* 添加页面-------->监听提交 */
  form.on('submit(LAY-chPmFileInfo-add-submit)', function(data){
  	/*$.ajax({
    	url:'add/do.jhtm',
        method:'post',
        data:data.field,
        dataType:'json',
        success:function(data){
			var code = data.statusCode;
			var msg = data.message;
			//layer.msg(msg, {offset: '150px',icon: "200"==code?1:2,time: 2000});
			if("200" == code)
			{
				//parent.layui.table.reload('LAY-chPmFileInfo-manage'); //重载表格
				parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
	          	//var index = parent.layer.getFrameIndex(window.name);
	          	//parent.layer.close(index);
			}
        },
        error:function (data) {
			parent.layer.msg(data.message, {offset: '150px',icon: 1,time: 2000});
        }
	});*/
    return false;
  });
    
  /*laydate.render({
  	elem: '#LAY-chPmFileInfo_xxx_date'
  });*/
  
  //列表页添加页面的拖拽上传
  //拖拽上传
  var fileUP = upload.render({
    	elem: '#test10'
    	,url: 'add/do.jhtm' //改成您自己的上传接口
    	,multiple: true
    	,auto: false
    	,bindAction: '#LAY-chPmFileInfo-add-submit'
    	,before: function(input){
    		//返回的参数item，即为当前的input DOM对象
    		console.log('文件上传中');
    		layer.msg($("#moduleCode").val()+"--"+$("#moduleCode").find("option:selected").text());
    		return;
    	}
    	,data: {//额外传输的参数
		  	  workerId: function () {
		            return $("#workerId").val();
		        },
		  	  moduleCode: function(){
		  		  return $("#moduleCode").val();
		  	  },
		  	  moduleName: function(){
		  		  return $("#moduleCode").find("option:selected").text();
		  	  },
		  	  status:0
    	}
    	,choose: function (obj) {
    		var code = $("#moduleCode").val();
    		if(!$.trim(code))
    		{
    			 parent.layer.msg("请先选择文件模块！", {time: 2000});
    			 return;
    		}
    	}
    	,done: function(res){
    	},
    	allDone: function (res) {
    		var succ = res.successful;
    		var fail = res.aborted;
    		var total = res.total;
    		var msg = "成功:"+total+"个,失败:"+fail+",共:"+total+"个";
    		parent.layui.table.reload('LAY-chPmFileInfo-manage'); //重载表格
			parent.layer.msg(msg, {offset: '150px',icon: 1,time: 2000});
	        var index = parent.layer.getFrameIndex(window.name);
	        parent.layer.close(index);
      }
  });
  
  
  
  
  
  //多图片上传--身份证图片
  var files;
  upload.render({
      elem: '#idcard-upload-more'   //绑定元素
      , url: 'upload/do.jhtm'     //上传接口
      //****************传输限制
      , size: 600                    //传输大小100k
      , exts: 'jpg|png|gif|'        //可传输文件的后缀
      , accept: 'file'              //video audio images
      //****************传输操作相关设置
      , data: {//额外传输的参数
    	  workerId:$("#workerId").val(),
    	  moduleCode: 0, 
    	  moduleName: "身份证",
    	  status:0
      }
      , auto: true 				//自动上传,默认是打开的
      , bindAction: '#idcardUpload'	//auto为false时，点击触发上传
      , field:"myFiles"
      , multiple: true                             //多文件上传
      , number: 10                               //multiple:true时有效
      , choose:function(obj)
      {
    	  files = obj.pushFile();
    	  //预读本地文件示例，不支持ie8
    	  obj.preview(function(index, file, result){
    		  $('#idcard-upload-more-list').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
    	  });
      }
      , before: function(obj){
          	  
      }
      , done: function (res) {                      //传输完成的回调
          console.log("上传完毕："+JSON.stringify(res));
          //$('#myPic').attr("src", res.Src);
      }
      , error: function () {                         //传输失败的回调
          //请求异常回调
      }
  });
  //多图片上传--学历文件
  upload.render({
	  elem: '#eduLev-upload-more'
	  ,url: 'upload/do.jhtm'
	  ,size: 600
	  ,exts: 'jpg|png|gif|'
	  ,accept: 'file'
	  ,data: {//额外传输的参数
		  workerId:$("#workerId").val(),
		  moduleCode: 1, 
		  moduleName: "学历文件",
		  status:0
	  }
  	  ,auto: true 
  	  ,bindAction: '#eduLevUpload'
  	  ,field:"myFiles"
      ,multiple: true
      ,choose:function(obj)
      {
    	  files = obj.pushFile();
    	  console.log(files);
      }
      ,before: function(obj){
          //预读本地文件示例，不支持ie8
    	  obj.preview(function(index, file, result){
    		  $('#eduLev-upload-more-list').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
          });
      }
      ,done: function (res) {                      //传输完成的回调
          console.log("上传完毕："+JSON.stringify(res));
          //$('#myPic').attr("src", res.Src);
      }
  });
  //多图片上传--职称文件
  upload.render({
	  elem: '#title-upload-more'
	  ,url: 'upload/do.jhtm'
	  ,size: 600
	  ,exts: 'jpg|png|gif|'
	  ,accept: 'file'
	  ,data: {//额外传输的参数
		  workerId:$("#workerId").val(),
		  moduleCode: 2, 
		  moduleName: "职称文件",
		  status:0
	  }
  	  ,auto: true 
  	  ,bindAction: '#titleUpload'
  	  ,field:"myFiles"
      ,multiple: true
      ,choose:function(obj)
      {
    	  files = obj.pushFile();
    	  console.log(files);
      }
      ,before: function(obj){
          //预读本地文件示例，不支持ie8
    	  obj.preview(function(index, file, result){
    		  $('#title-upload-more-list').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
          });
      }
      ,done: function (res) {                      //传输完成的回调
          console.log("上传完毕："+JSON.stringify(res));
          //$('#myPic').attr("src", res.Src);
      }
  });
  //多图片上传--任职文件
  upload.render({
	  elem: '#posit-upload-more'
	  ,url: 'upload/do.jhtm'
	  ,size: 600
	  ,exts: 'jpg|png|gif|'
	  ,accept: 'file'
	  ,data: {//额外传输的参数
		  workerId:$("#workerId").val(),
		  moduleCode: 3, 
		  moduleName: "任职文件",
		  status:0
	  }
  	  ,auto: true 
  	  ,bindAction: '#positUpload'
  	  ,field:"myFiles"
      ,multiple: true
      ,choose:function(obj)
      {
    	  files = obj.pushFile();
    	  console.log(files);
      }
      ,before: function(obj){
          //预读本地文件示例，不支持ie8
    	  obj.preview(function(index, file, result){
    		  $('#posit-upload-more-list').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
          });
      }
      ,done: function (res) {                      //传输完成的回调
          console.log("上传完毕："+JSON.stringify(res));
          //$('#myPic').attr("src", res.Src);
      }
  });
    
  //自定义验证
  form.verify({
		workerId: function(value){
			if(null == value)
				return '职工编号不能为空!';
			if(value.length > 19)
				return '职工编号长度不能超过19';
	    },
		moduleCode: function(value){
			if(null == value)
				return '模块编码不能为空!';
			if(value.length > 10)
				return '模块编码长度不能超过10';
	    },
		moduleName: function(value){
			if(null == value)
				return '模块名称不能为空!';
			if(value.length > 50)
				return '模块名称长度不能超过50';
	    },
		fileContent: function(value){
			if(null == value)
				return '文件内容不能为空!';
			if(value.length > 65,535)
				return '文件内容长度不能超过65,535';
	    },
		fileType: function(value){
			if(null == value)
				return '文件类型（文件后缀）不能为空!';
			if(value.length > 10)
				return '文件类型（文件后缀）长度不能超过10';
	    },
		fileSize: function(value){
			if(null == value)
				return '文件大小不能为空!';
			if(value.length > 19)
				return '文件大小长度不能超过19';
	    },
		remark: function(value){
			if(value.length > 100)
				return '备注长度不能超过100';
	    },
  });
  exports('chPmFileInfo', {})
});