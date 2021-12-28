layui.define(['table','form','upload','element','laydate','laypage'], function(exports){
	var $ = layui.$,
	table = layui.table,
	element = layui.element,
	upload = layui.upload,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form;
	
	//单文件示例  选完文件后不自动上传
    /*var uploadSingle = upload.render({
        elem: '#uploadQR'
        , url: '/web/api/upload/upload?option=4'
        , accept: 'images'  // 允许上传的文件类型
        , size: 2048        // 最大允许上传的文件大小  单位 KB
        , auto: false
        , bindAction: '#startUploadQR'
        , choose: function (obj) {
            //预读本地文件示例，不支持ie8
            obj.preview(function (index, file, result) {
                $('#qrshow').attr('src', result); //图片链接（base64）
            });
        }
        , done: function (res, index, upload) {
            if (res.code == 0) {
                //上传成功
                $("#qrInput").val(res.data[0].fp_relative);
                var startDiv = $('#startDiv');
                startDiv.html('<span style="color: #5FB878;">上传成功</span>');
            } else {
                this.error(index, upload);
            }
        }
        , error: function (index, upload) {
            //演示失败状态，并实现重传
            var startDiv = $('#startDiv');
            startDiv.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload" style="width:50px;height:30px;text-align:center;line-height:30px;">重试</a>');
            startDiv.find('.demo-reload').on('click', function () {
                uploadSingle.upload();
            });
        }
    });*/
    

    //身份证上传
	var files4Idcard;
    var listView4Idcard = $('#imgList4Idcard');
    var idcardInst = upload.render({
        elem: '#idcard' //绑定元素
        ,url: 'upload/do.jhtm' //上传接口
        ,accept: 'images'  // 允许上传的文件类型
        ,data: {//额外传输的参数
        	workerId:$("#workerId").val(),
        	moduleCode: 0, 
        	moduleName: "身份证",
        	status:0
        }
        //,acceptMime: 'image/jpg,image/png'   // (只支持jpg和png格式，多个用逗号隔开),
        ,size: 5120        // 最大允许上传的文件大小  单位 KB
        ,auto: false //选择文件后不自动上传
        ,bindAction: '#idcardUpload' //指向一个按钮触发上传
        ,multiple: true   // 开启多文件上传
        ,number: 6    //  同时上传文件的最大个数
        ,choose: function (obj) {
        	var files4Idcard = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            var arr = Object.keys(files4Idcard);
            // 检查上传文件的个数
            if (arr.length <= 6) {
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td><img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" style="height: 66px;width:100px;"></td>'
                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                        , '<td>等待上传</td>'
                        , '<td>'
                        , '<button class="layui-btn demo-reload layui-hide">重传</button>'
                        , '<button class="layui-btn layui-btn-danger demo-delete">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });
                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files4Idcard[index]; //删除对应的文件
                        tr.remove();
                        //uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });
                    listView4Idcard.append(tr);
                });
            } else {
                // 超出上传最大文件
                layer.msg("上传文件最大不超过6个")
            }
        }
        ,done: function (res, index, upload) {
            console.log("res", res);
            if (res.statusCode == 200) { //上传成功
                // 上传成功后将图片路径拼接到input中，多个路径用","分割
                var inputVal = document.getElementById("imgInput4Idcard").value;
                var valData = "";
                if (inputVal) {
                    valData = inputVal + "," + res.data[0].fp_relative;
                } else {
                    valData = res.data[0].fp_relative;
                }
                document.getElementById("imgInput4Idcard").value = valData;
                var tr = listView4Idcard.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function (index, upload) {
            var tr = listView4Idcard.find('tr#upload-' + index)
                , tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });
    
    //学历文件
    var listView4EduLev = $('#imgList4EduLev');
    var eduLevInst = upload.render({
        elem: '#eduLev' //绑定元素
        ,url: 'upload/do.jhtm' //上传接口
        ,accept: 'images'  // 允许上传的文件类型
        ,data: {//额外传输的参数
        	workerId:$("#workerId").val(),
        	moduleCode: 1, 
        	moduleName: "学历文件",
        	status:0
        }
        //,acceptMime: 'image/jpg,image/png'   // (只支持jpg和png格式，多个用逗号隔开),
        ,size: 5120        // 最大允许上传的文件大小  单位 KB
        ,auto: false //选择文件后不自动上传
        ,bindAction: '#eduLevUpload' //指向一个按钮触发上传
        ,multiple: true   // 开启多文件上传
        ,number: 6    //  同时上传文件的最大个数
        ,choose: function (obj) {
        	var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            var arr = Object.keys(files);
            // 检查上传文件的个数
            if (arr.length <= 6) {
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td><img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" style="height: 66px;width:100px;"></td>'
                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                        , '<td>等待上传</td>'
                        , '<td>'
                        , '<button class="layui-btn demo-reload layui-hide">重传</button>'
                        , '<button class="layui-btn layui-btn-danger demo-delete">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });
                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        //uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });
                    listView4EduLev.append(tr);
                });
            } else {
                // 超出上传最大文件
                layer.msg("上传文件最大不超过6个")
            }
        }
        ,done: function (res, index, upload) {
            console.log("res", res);
            if (res.statusCode == 200) { //上传成功
                // 上传成功后将图片路径拼接到input中，多个路径用","分割
                var inputVal = document.getElementById("imgInput4EduLev").value;
                var valData = "";
                if (inputVal) {
                    valData = inputVal + "," + res.data[0].fp_relative;
                } else {
                    valData = res.data[0].fp_relative;
                }
                document.getElementById("imgInput4EduLev").value = valData;
                var tr = listView4EduLev.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function (index, upload) {
            var tr = listView4EduLev.find('tr#upload-' + index)
                , tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });
    
    //职称文件
    var listView4Title = $('#imgList4Title');
    var titleInst = upload.render({
        elem: '#title' //绑定元素
        ,url: 'upload/do.jhtm' //上传接口
        ,accept: 'images'  // 允许上传的文件类型
        ,data: {//额外传输的参数
        	workerId:$("#workerId").val(),
        	moduleCode: 2, 
        	moduleName: "职称文件",
        	status:0
        }
        //,acceptMime: 'image/jpg,image/png'   // (只支持jpg和png格式，多个用逗号隔开),
        ,size: 5120        // 最大允许上传的文件大小  单位 KB
        ,auto: false //选择文件后不自动上传
        ,bindAction: '#titleUpload' //指向一个按钮触发上传
        ,multiple: true   // 开启多文件上传
        ,number: 6    //  同时上传文件的最大个数
        ,choose: function (obj) {
        	var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            var arr = Object.keys(files);
            // 检查上传文件的个数
            if (arr.length <= 6) {
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td><img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" style="height: 66px;width:100px;"></td>'
                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                        , '<td>等待上传</td>'
                        , '<td>'
                        , '<button class="layui-btn demo-reload layui-hide">重传</button>'
                        , '<button class="layui-btn layui-btn-danger demo-delete">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });
                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        //uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });
                    listView4Title.append(tr);
                });
            } else {
                // 超出上传最大文件
                layer.msg("上传文件最大不超过6个")
            }
        }
        ,done: function (res, index, upload) {
            console.log("res", res);
            if (res.statusCode == 200) { //上传成功
                // 上传成功后将图片路径拼接到input中，多个路径用","分割
                var inputVal = document.getElementById("imgInput4Title").value;
                var valData = "";
                if (inputVal) {
                    valData = inputVal + "," + res.data[0].fp_relative;
                } else {
                    valData = res.data[0].fp_relative;
                }
                document.getElementById("imgInput4Title").value = valData;
                var tr = listView4Title.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function (index, upload) {
            var tr = listView4Title.find('tr#upload-' + index)
                , tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });
    
    //任职文件
    var listView4Posit = $('#imgList4Posit');
    var positInst = upload.render({
        elem: '#posit' //绑定元素
        ,url: 'upload/do.jhtm' //上传接口
        ,accept: 'images'  // 允许上传的文件类型
        ,data: {//额外传输的参数
        	workerId:$("#workerId").val(),
        	moduleCode: 3, 
        	moduleName: "任职文件",
        	status:0
        }
        //,acceptMime: 'image/jpg,image/png'   // (只支持jpg和png格式，多个用逗号隔开),
        ,size: 5120        // 最大允许上传的文件大小  单位 KB
        ,auto: false //选择文件后不自动上传
        ,bindAction: '#positUpload' //指向一个按钮触发上传
        ,multiple: true   // 开启多文件上传
        ,number: 6    //  同时上传文件的最大个数
        ,choose: function (obj) {
        	var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
            var arr = Object.keys(files);
            // 检查上传文件的个数
            if (arr.length <= 6) {
                //读取本地文件
                obj.preview(function (index, file, result) {
                    var tr = $(['<tr id="upload-' + index + '">'
                        , '<td><img src="' + result + '" alt="' + file.name + '" class="layui-upload-img" style="height: 66px;width:100px;"></td>'
                        , '<td>' + (file.size / 1014).toFixed(1) + 'kb</td>'
                        , '<td>等待上传</td>'
                        , '<td>'
                        , '<button class="layui-btn demo-reload layui-hide">重传</button>'
                        , '<button class="layui-btn layui-btn-danger demo-delete">删除</button>'
                        , '</td>'
                        , '</tr>'].join(''));

                    //单个重传
                    tr.find('.demo-reload').on('click', function () {
                        obj.upload(index, file);
                    });
                    //删除
                    tr.find('.demo-delete').on('click', function () {
                        delete files[index]; //删除对应的文件
                        tr.remove();
                        //uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
                    });
                    listView4Posit.append(tr);
                });
            } else {
                // 超出上传最大文件
                layer.msg("上传文件最大不超过6个")
            }
        }
        ,done: function (res, index, upload) {
            console.log("res", res);
            if (res.statusCode == 200) { //上传成功
                // 上传成功后将图片路径拼接到input中，多个路径用","分割
                var inputVal = document.getElementById("imgInput4Posit").value;
                var valData = "";
                if (inputVal) {
                    valData = inputVal + "," + res.data[0].fp_relative;
                } else {
                    valData = res.data[0].fp_relative;
                }
                document.getElementById("imgInput4Posit").value = valData;
                var tr = listView4Posit.find('tr#upload-' + index)
                    , tds = tr.children();
                tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
                tds.eq(3).html(''); //清空操作
                return delete this.files[index]; //删除文件队列已经上传成功的文件
            }
            this.error(index, upload);
        }
        ,error: function (index, upload) {
            var tr = listView4Posit.find('tr#upload-' + index)
                , tds = tr.children();
            tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
            tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
        }
    });
    
    window.hand4Edit=function(wkId)
    {
    	console.log("22:"+wkId);
    	if(null == wkId || "" == wkId){
    		layer.msg("职工编号为空！");
    		return;
    	}
    	$.ajax({
            url:'loadFileInfo.jhtm',
            method:'post',
            data:{wkId:wkId},
            dataType:'json',
            success:function(res){
            	console.log("succ:"+JSON.stringify(res));
				var code = res.statusCode;
				var msg = res.message;
				var all = res.data;
				layer.msg("加载成功!", {offset: '150px',icon: "200"==code?1:2,time: 2000});
				if("200" == code)
				{
					$.each(all,function(i,item){
						 console.log(i+"--"+JSON.stringify(item));
						 fillFile(item);
				 	})
				}
            },
            error:function (res) {
            	console.log("error:"+JSON.stringify(res));
				layer.msg(res.message, {offset: '150px',icon: 2,time: 2000});
            }
		});
    }
    
    /*function fillFile(module)
    {
    	$.each(module,function(i,item){
		    var code = item.moduleCode;
	    	var target = (0!=code ? 1!=code ? 2!=code ? $('#imgList4Posit'):$('#imgList4Title'):$('#imgList4EduLev'):$('#imgList4Idcard'));
	    	var fileType = item.fileType;
	    	var tr = $(['<tr id="upload-' + i + '">'
	            , '<td><img src="' + item.fileUrl + '" alt="' + item.fileName + '" class="layui-upload-img" style="height: 66px;width:100px;"></td>'
	            , '<td>' + (item.fileSize / 1014).toFixed(1) + 'kb</td>'
	            , '<td>等待上传</td>'
	            , '<td>'
	            , '<button class="layui-btn demo-reload layui-hide">重传</button>'
	            , '<button class="layui-btn layui-btn-danger demo-delete">删除</button>'
	            , '</td>'
	            , '</tr>'].join(''));

	        //单个重传
	        tr.find('.demo-reload').on('click', function () {
	            obj.upload(index, file);
	        });
	        //删除
	        tr.find('.demo-delete').on('click', function () {
	            delete files[index]; //删除对应的文件
	            tr.remove();
	        });
	        target.append(tr);
		});
    }*/
	exports('chPmFileUpload', {})
});