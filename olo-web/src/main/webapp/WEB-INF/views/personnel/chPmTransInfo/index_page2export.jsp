<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/taglibs.jsp"%>
<%
	String base = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
    String path = request.getContextPath();
    String basePath = base+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>layuiAdmin 后台管理员</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>
  <div class="layui-btn-group demoTable" style="margin-bottom: 10px;">
<button class="layui-btn" data-type="parseTable">立即转化为数据表格</button>
<button class="layui-btn" data-type="parseTable" onclick="convertToTable()">将数据转化为静态表格</button>
<button class="layui-btn" data-type="parseTableToExcel" onclick="exportToExcel()">导出Excel</button>
</div>

<table lay-filter="parse-table-demo">
<thead>
<tr>
<th lay-data="{field:'username', width:200}">昵称</th>
<th lay-data="{field:'joinTime', width:150}">加入时间</th>
<th lay-data="{field:'sign', minWidth: 180}">签名</th>
</tr> 
</thead>
<tbody>
<tr>
<td>贤心1</td>
<td>2016-11-28</td>
<td>人生就像是一场修行 A</td>
</tr>
<tr>
<td>贤心2</td>
<td>2016-11-29</td>
<td>人生就像是一场修行 B</td>
</tr>
<tr>
<td>贤心3</td>
<td>2016-11-30</td>
<td>人生就像是一场修行 C</td>
</tr>
</tbody>
</table>
<!--直接为table绑定数据-->
<table id="table_TravelagenceCheck" lay-filter="TravelagenceCheck"></table>
<!--引用layui.js  直接从layui官网下载2.4.5版本的layui.js-->
<script src="<%=basePath%>layuiadmin/layui/layui.js"></script>
<script>
var exportBillsData=[{
'nos':1,
'CompanyId':1001,
'CompanyName':"携程",
'Sex':'男'
},{
'nos':2,
'CompanyId':1002,
'CompanyName':"携程1",
'Sex':'女'
}];
layui.use('table', function(){
var table = layui.table;
});
//1.转化为静态表格-直接为layui-table绑定数据
function convertToTable(){
layui.use('table', function () {
var table = layui.table;
table.render({
elem: '#table_TravelagenceCheck'
, height: 480
, title: "协议贡献量明细"
, page: true
, size: 'sm'
, toolbar: "协议入住明细"
, defaultToolbar: ['filter', 'print', 'exports']
, limit: 200
, limits: [200]
//直接绑定data
, data: exportBillsData
, cols: [[ //表头
{ field: 'nos', title: "序号", width: '2%', type: 'numbers', unresize: true }
, { field: 'CompanyId', title: "协议编号", width: '10%', sort: true, unresize: true }
, { field: 'CompanyName', title: "协议名称", width: '12%', sort: true, unresize: true } 
, { field: 'Sex', title: "性别", width: '12%', sort: true, unresize: true } 
]]
}); 
});
}

//导出Excel-注明:table.exportFile方法是layui-table自带的,适用于2.4.5版本，2.3.0版本的layui.js没有这个方法
function exportToExcel(){
layui.use('table', function () {
var table = layui.table;
table.exportFile(["昵称", "加入时间", "签名"],[
['贤心1','2016-11-28','人生就像是一场修行 A'],
['贤心2','2019-11-28','人生就像是一场修行 B'],
['贤心3','2016-10-28','人生就像是一场修行 C']
],'本日将到订单列表.xls');
});
}
//导出Excel1
function exportToExcel1(){
layui.use('table', function () {
var table = layui.table;
table.exportFile(["#","账号", "类型", "姓名", "房型", "房号", "状态", "成交价", 
"余额", "信用金额", "预付金额", "到达", "离开", "手机", "房数", "联房", 
"VIP", "房价码", "市场码", "公司", "预订号", "备注"],[
['1','F1908090001','F','jackA302','三人房有窗 浪漫','A302','R','120','400','0','0','2019-08-09 09:33','2019-08-30 12:00','1734***2827','1','-','-','BASE','上门散客','-','R1908090001','-'],
['2','F1908210008','F','jackG202','双床房海景','G202','R','501','0','0','0','2019-08-22 09:16','2019-08-30 12:00','1734***2827','1','-','-','BASE','上门散客','-','R1908210003','-'],
['3','F1908210012','F','李先生/李先生','双床房海景','-','R','501','0','0','0','2019-08-22 12:00','2019-08-23 12:00','1777***3792','1','-','-','BASE','上门散客','-','R1908210004','-'],
['4','F1908210030','F','丁小帅','大床房1.5米床 有窗','-','R','31','0','0','0','2019-08-22 12:00','2019-08-23 14:00','1861***8299','1','-','-','VipGold','中央推荐入住','-','190822021002062','【客人留言:测试结果】[使用电子代金券:第1晚30抵30] [AliAPP订单]不赠送早餐;每日房价:2019-08-22:31.00'],
['5','F1908220001','F','沙奥','单人房早餐测试','-','R','230','0','0','230','2019-08-22 12:00','2019-08-23 13:00','1317***1407','1','-','-','VipCard','中央推荐入住','银联在线支付','190822021002069','[已支付成功](银联在线支付)[Android手机订单]不赠送早餐;每日房价:2019-08-22:230.00'],
['6','F1908220002','F','焦人杰','单人房早餐测试','D203','R','0','0','0','0','2019-08-22 16:43','2019-08-23 12:00','1363***7594','1','-','-','BASE','上门散客','-','R1908220001','自助机办理'],
['7','F1908220003','F','焦人杰','单人房早餐测试','D205','R','0','0','0','0','2019-08-22 16:43','2019-08-23 12:00','1363***7594','1','-','-','BASE','上门散客','-','R1908220002','自助机办理'],
['8','F1908220004','F','焦人杰','单人房早餐测试','D206','R','0','0','0','0','2019-08-22 16:43','2019-08-23 12:00','1363***7594','1','-','-','BASE','上门散客','-','R1908220003','自助机办理'],
['9','F1908210038','F','焦人杰','双床房海景','G208','R','0','0','0','0','2019-08-22 17:34','2019-08-23 17:34','1363***7594','1','-','-','BASE','上门散客','-','R1908210009','自助机办理'],
['10','F1908220010','F','test芬芳','单人房早餐测试','-','R','120','0','0','0','2019-08-23 10:28','2019-08-24 12:00','-','18','☆','-','BASE','上门散客','-','R1908220004','-'],
['11','F1908220011','F','test芬芳','单人房早餐测试','-','R','120','0','0','0','2019-08-23 10:28','2019-08-24 12:00','-','1','☆','-','BASE','上门散客','-','R1908220004','-'],
['12','F1908220012','F','test芬芳','单人房早餐测试','-','R','120','0','0','0','2019-08-23 10:28','2019-08-24 12:00','-','1','☆','-','BASE','上门散客','-','R1908220004','-'],
['13','F1908220008','F','沙奥','8.高级套房 2米床 有窗','-','R','430','0','0','430','2019-08-23 12:00','2019-08-24 13:00','1317***1407','1','-','☆','VipCard','中央会员预定','银联在线支付','190823021002006','[已支付成功](银联在线支付)[Android手机订单][使用电子代金券:1间房第1晚30抵30]不赠送早餐;每日房价:2019-08-23:430.00'],
['14','F1908220009','F','沙奥','8.高级套房 2米床 有窗','-','R','430','0','0','430','2019-08-23 12:00','2019-08-24 13:00','1317***1407','1','-','-','VipCard','中央推荐入住','银联在线支付','190823021002007']
],'本日将到订单列表.xls');
});
}
</script>
</body>
</html>