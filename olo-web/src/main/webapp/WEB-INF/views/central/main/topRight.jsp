<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 头部区域   right  -->
<ul class="layui-nav layui-layout-right"
	lay-filter="layadmin-layout-right">
	<!-- 屏蔽消息 -->
	<!-- <li class="layui-nav-item" lay-unselect><a
		lay-href="views/app/message/index.html" layadmin-event="message"
		lay-text="消息中心"> <i class="layui-icon layui-icon-notice"></i> 如果有新消息，则显示小圆点
			<span class="layui-badge-dot"></span>
	</a></li> -->
	<li class="layui-nav-item layui-hide-xs" lay-unselect><a
		href="javascript:;" layadmin-event="theme"> <i
			class="layui-icon layui-icon-theme"></i>
	</a></li>
	<li class="layui-nav-item layui-hide-xs" lay-unselect><a
		href="javascript:;" layadmin-event="note"> <i
			class="layui-icon layui-icon-note"></i>
	</a></li>
	<li class="layui-nav-item layui-hide-xs" lay-unselect><a
		href="javascript:;" layadmin-event="fullscreen"> <i
			class="layui-icon layui-icon-screen-full"></i>
	</a></li>
	<li class="layui-nav-item" lay-unselect><a href="javascript:;">
			<cite>${oloCurrUserInfo.userRealName}</cite>
	</a>
		<dl class="layui-nav-child">
			<dd>
				<a lay-href="main/set4user.jhtm?userId=${oloCurrUserInfo.userId}">基本资料</a>
			</dd>
			<dd>
				<a lay-href="main/set4pwd.jhtm">修改密码</a>
			</dd>
			<hr>
			<dd layadmin-event="logout" style="text-align: center;">
				<a onclick="toLogin();">退出</a>
			</dd>
		</dl></li>

	<!-- <li class="layui-nav-item layui-hide-xs" lay-unselect><a
		href="javascript:;" layadmin-event="about"><i
			class="layui-icon layui-icon-more-vertical"></i></a></li> -->
	<li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm"
		lay-unselect><a href="javascript:;" layadmin-event="more"><i
			class="layui-icon layui-icon-more-vertical"></i></a></li>
</ul>

<script>
function toLogin()
{
	window.top.location='main/loginOut.jhtm';
}
</script>