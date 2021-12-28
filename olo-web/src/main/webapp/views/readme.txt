1、layui 在子页面打开tab页
	var href ='views/list/productdetail.html?productKey=' + data.productKey;
	var l = parent === self ? layui : top.layui;
	l.index.openTabsPage(href, "产品详情");