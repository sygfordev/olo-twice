layui.extend({
    selectN: './layui_exts/selectN',
    selectM: './layui_exts/selectM',
}).define(['table','form','laydate','laypage','selectN','selectM'], function(exports){
	var $ = layui.$,
	table = layui.table,
	laypage = layui.laypage,
	laydate = layui.laydate,
	form = layui.form,
	selectN = layui.selectN,
    selectM = layui.selectM;
	
	//社保补缴单-表格渲染
	table.render({
		elem: '#LAY-socialSupple-manage',
		url: 'supple/do.jhtm',
		parseData: function(res){
		    return {
		    	"code": "200"==res.retCode?0:1,
		    	"msg": res.retMsg,
		    	"count": res.count,
		    	"data": res.retData
		    };
		},
	    cols: 
	    [
	  	  [
			  {field: 'name', title: '姓名' ,fixed: 'left',width:'5%',align: 'center',rowspan:2},
			  {field: 'idcard', title: '身份证号' ,fixed: 'left',width:'12%',align: 'center',rowspan:2},
			  {field: 'social_ymonth', title: '补缴时间' ,width:'6%',align: 'center',rowspan:2},
			  {field: 'msize', title: '月数' ,width:'5%',align: 'center',rowspan:2},
			  {field: 'base', title: '基数' ,width:'5%',align: 'center',rowspan:2},
			  {field: '', title: '单位部分' ,width:'5%',align: 'center',colspan:10},
			  {field: '', title: '个人部分' ,width:'5%',align: 'center',colspan:10},
			  {field: 'total', title: '合计' ,width:'5%',align: 'center',rowspan:2},
			  {field: 'remark_4month', title: '月备注' ,width:'5%',align: 'center',rowspan:2}
		  ],
		  [
			  {field: 'c_pen', title: '养老' ,width:'5%',align: 'center'},
			  {field: 'c_medi', title: '医疗' ,width:'5%',align: 'center'},
			  {field: 'c_unemp', title: '失业' ,width:'5%',align: 'center'},
			  {field: 'c_injury', title: '工伤' ,width:'5%',align: 'center'},
			  {field: 'c_birth', title: '生育' ,width:'5%',align: 'center'},
			  {field: 'c_annuity', title: '年金' ,width:'5%',align: 'center'},
			  {field: 'c_ovp', title: '省统筹' ,width:'5%',align: 'center'},
			  {field: 'c_spmedi', title: '补充医疗' ,width:'5%',align: 'center'},
			  {field: 'c_serious', title: '大病' ,width:'5%',align: 'center'},
			  {field: 'sumb', title: '小计' ,width:'5%',align: 'center'},
			  {field: 'p_pen', title: '养老' ,width:'5%',align: 'center'},
			  {field: 'p_medi', title: '医疗' ,width:'5%',align: 'center'},
			  {field: 'p_unemp', title: '失业' ,width:'5%',align: 'center'},
			  {field: 'p_injury', title: '工伤' ,width:'5%',align: 'center'},
			  {field: 'p_birth', title: '生育' ,width:'5%',align: 'center'},
			  {field: 'p_annuity', title: '年金' ,width:'5%',align: 'center'},
			  {field: 'p_ovp', title: '省统筹' ,width:'5%',align: 'center'},
			  {field: 'p_spmedi', title: '补充医疗' ,width:'5%',align: 'center'},
			  {field: 'p_serious', title: '大病' ,width:'5%',align: 'center'},
			  {field: 'suma', title: '小计' ,width:'5%',align: 'center'}
		  ]
	    ],
	    toolbar: '#toolbarDemo', //开启头部工具栏，并为其绑定左侧模板
  	    defaultToolbar: ['filter','exports'],
	  	done:function (res,curr,count){
		    $('th').css({'font-size':'14','font-weight':'bold','color':'green','border-color':'#aaa'});
		    //$('td').css({'border-color':'#aaa','background-color': 'lightblue'});
		    var headertop = $(".layui-table-header").offset().top//获取表头到文档顶部的距离
	        $(window).scroll(function () {//开始监听滚动条                         
	            if (headertop - $(window).scrollTop() < 0) {//超过了
	               $(".layui-table-header").addClass('table-header-fixed')//添加样式，固定住表头
	            }else {//没超过
	                $(".layui-table-header").removeClass('table-header-fixed')//移除样式
	            }
	        })
	  	},
	    page: false,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
  });
  
  //监听搜索
  form.on('submit(LAY-socialSupple-search)', function(data){
  	  var field = data.field;
  	  //执行重载
  	  table.reload('LAY-socialSupple-manage', {where: field});
  });
  
  //头工具栏事件
  table.on('toolbar(LAY-socialSupple-manage)', function(obj){
	    var checkStatus = table.checkStatus(obj.config.id);
	    switch(obj.event){
	      //导出
	      case 'LAYTABLE_EXPORT_USER':
	    	  layer.alert('正在导出，请不要重复操作');
	    	  var invokeUrl = $("#basePath").val()+'/social/chSocialInfo/export4supple.jhtm?page=0&limit=1';
	    	  var formVal = form.val("socialSupple");
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
  
  exports('chSocialSupple', {})
});