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
	
	
	var heads = [];
	//加载表头
	$(function () {
		$.ajax(
			{
			url:"loadHead.jhtm", 
			async: false, 
			data:{},
			dataType:"JSON",
			success:function(re){
				heads = re.heads;
				$("#startYear").val(re.startYear);
			}
		})
    });
	
  	//社保参保人数月统计表
	table.render({
		elem: '#LAY-social-104-manage',
		url: 'dostatis.jhtm',
		parseData: function(res){
			curYear = res.startYear;
		    return {
		    	"code": "200"==res.retCode?0:1,
		    	"msg": res.retMsg,
		    	"data": res.retData
		    };
		},
	    cols: heads,
	    where: {
			startYear: $("#startYear").val()
	    },
	    done:function (res,curr,count){
		    $('th').css({'font-size':'14','font-weight':'bold','color':'green','border-color':'#aaa'});
		    //$('td').css({'border-color':'#aaa','background-color': 'lightblue'});
		    var headertop = $(".layui-table-header").offset().top//获取表头到文档顶部的距离
            $(window).scroll(function () {//开始监听滚动条                         
                if (headertop - $(window).scrollTop() < 0) {    //超过了                              
                   $(".layui-table-header").addClass('table-header-fixed')//添加样式，固定住表头
                }else {//没超过
                    $(".layui-table-header").removeClass('table-header-fixed')//移除样式
                }
            })
            //渲染背景色
            $.each(res.data, function (index, item) {
            	var tindex = item.LAY_TABLE_INDEX;
            	if (item.doStyle == true) {
             	// 法1
              	$("#LAY-social-104-manage").next().find('tbody tr[data-index="' + index +
              	'"]').css("background-color", 0 == tindex?"Wheat":"lightblue");
            	// 法2
            	// var i = index + 1;
            	// $('tr').eq(i).css('background-color', 'lightblue');
              	}
          	});
		    var data = res.data;
            var mergeIndex = 0;//定位需要添加合并属性的行数
            var mark = 1; //这里涉及到简单的运算，mark是计算每次需要合并的格子数
            var columsName = ['modality'];//需要合并的列名称
            var columsIndex = [0, 1];//需要合并的列索引值
            for (var k = 0; k < columsName.length; k++) { //这里循环所有要合并的列
                var trArr = $(".layui-table-body>.layui-table").find("tr");//所有行
                for (var i = 1; i < res.data.length; i++) { //这里循环表格当前的数据
                    var tdCurArr = trArr.eq(i).find("td").eq(columsIndex[k]);//获取当前行的当前列
                    var tdPreArr = trArr.eq(mergeIndex).find("td").eq(columsIndex[k]);//获取相同列的第一列
                    if (data[i][columsName[k]] === data[i - 1][columsName[k]]) { //后一行的值与前一行的值做比较，相同就需要合并
                        mark += 1;
                        tdPreArr.each(function () {//相同列的第一列增加rowspan属性
                            $(this).attr("rowspan", mark);
                        });
                        tdCurArr.each(function () {//当前行隐藏
                            $(this).css("display", "none");
                        });
                    } else {
                        mergeIndex = i;
                        mark = 1;//一旦前后两行的值不一样了，那么需要合并的格子数mark就需要重新计算
                    }
                }
                mergeIndex = 0;
                mark = 1;
            }
		},
	    page: false,
	    limits: [10,15,20,30,50,100],
	    limit: 10,
	    text: {
	        none: '暂无相关数据'
	    }
	});

	//监听搜索
	form.on('submit(LAY-social-104-search)', function(data){
		  /*var field = data.field;
		  var map = {};
		  $.each(field,function(key,val) {
			  if(null == val || '' == val || 'undefined' == val || key.endWith("List"))
    			  return true;//continue
    		  map[key] = val;
    	  });
		  //执行重载
		  table.reload('LAY-social-104-manage', {where: map});*/
		  var field = data.field;
	  	  var succFlag = false;
		  //加载表头
		  $.ajax(
		  {
		  		url:"loadHead.jhtm", 
		  		async: false,
		  		data:{startYear:$("#startYear").val()},
		  		dataType:"JSON",
		  		success:function(re){
					heads = re.heads;
		  			succFlag = true;
		  			$("#startYear").val(re.startYear);
		  		}
		  });
		  //若表头加载成功，则执行重载
		  if(succFlag){
			  var map = {};
			  $.each(field,function(key,val) {
				  if(null == val || '' == val || 'undefined' == val || key.endWith("List"))
	    			  return true;//continue
	    		  map[key] = val;
	    	  });
			  table.reload('LAY-social-104-manage', {where: map,cols:heads});
		  }
	});
	
	String.prototype.endWith=function(oString){  
		var reg=new RegExp(oString+"$");  
		return reg.test(this);     
	}  
  //---------表头按钮区域----------
  var active = {
	  export104: function(){
		  var invokeUrl = $("#basePath").val()+'/social/statistics/104/export.jhtm?1=1';
    	  var formVal = form.val("st104Index");
    	  var startYear = null;
    	  var endMonth = null;
    	  $.each(formVal,function(key,val) {
    		  if(null == val || '' == val || 'undefined' == val || key.endWith("List"))
    			  return true;//continue
    		  if('startYear' == key) startYear = val;
    		  invokeUrl += ("&"+key+"="+val);
    	  });
    	  if(null == startYear){
    		  layer.alert('请选择统计区间');
    		  return;
    	  }
    	  window.location.href=invokeUrl;
	  }
  }
  
  function contains(arr, obj) {
	  var i = arr.length;
	  while (i--) {
        if (arr[i] === obj) {
            return true;
        }
	  }
	  return false;
  }
  
  //---------表头按钮触发激活----------
  $('.layui-btn.layuiadmin-btn-admin').on('click', function(){
  	var type = $(this).data('type');
    active[type] ? active[type].call(this) : '';
  });
  
  laydate.render({
  	elem: '#startYear',
  	type: 'year'
  });
  
  var selectStr = '['+
  		'{"id":"wkModalityCns","dkey":"modalityList","max":10}'+
  		']';
  
  $(function(){
	  var keys = JSON.parse(selectStr);
	  var list = null;
	  $.each(keys,function(index,value) {
		  list = $("#"+value.dkey).val();
		  console.log(list.length);
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
  
  exports('socialStatistics104', {})
});