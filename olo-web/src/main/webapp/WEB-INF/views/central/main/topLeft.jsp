<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 头部区域   left  -->
<ul class="layui-nav layui-layout-left">
	<li class="layui-nav-item layadmin-flexible" lay-unselect><a
		href="javascript:;" layadmin-event="flexible" title="侧边伸缩"> <i
			class="layui-icon layui-icon-shrink-right" id="LAY_app_flexible"></i>
	</a></li>
	<li class="layui-nav-item layui-hide-xs" lay-unselect><a
		href="http://www.layui.com/admin/" target="_blank" title="前台"> <i
			class="layui-icon layui-icon-website"></i>
	</a></li>
	<li class="layui-nav-item" lay-unselect><a href="javascript:;"
		layadmin-event="refresh" title="刷新"> <i
			class="layui-icon layui-icon-refresh-3"></i>
	</a></li>
	<!-- 屏蔽搜索 -->
	<!-- <li class="layui-nav-item layui-hide-xs" lay-unselect><input
		type="text" placeholder="搜索..." autocomplete="off"
		class="layui-input layui-input-search" layadmin-event="serach"
		lay-action="template/search.html?keywords="></li> -->
</ul>