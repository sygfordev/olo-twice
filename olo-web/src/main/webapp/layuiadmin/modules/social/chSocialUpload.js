layui.extend({
    selectN: './layui_exts/selectN',
    selectM: './layui_exts/selectM',
}).define(['table','form','laydate','upload','element','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form,
	element = layui.element,
	upload = layui.upload;
	
  laydate.render({
  	elem: '#socialYmonth',
  	type: 'month'
  });
  
  //拖拽上传
  var uploadInst = upload.render({
    elem: '#social_upload'
    ,url: 'doImport.jhtm' //改成您自己的上传接口
    ,multiple: false
    ,exts: 'xls|xlsx'
    ,accept: 'file'
    ,auto: false
    ,bindAction: '#chSocialInfo-upload-submit'
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
  		socialYmonth: function () {
  			layer.load(2, {time: 30*60*1000});
            return $("#socialYmonth").val();
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
		socialYmonth: function(value){
			if(null == value || "undefined" == value || "" == value)
				return '社保年月不能为空!';
			if(value.length > 20)
				return '社保年月长度不能超过20';
		}
  });
  exports('chSocialUpload', {})
});