<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  <title>设置我的资料</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/layui/css/layui.css" media="all">
  <link rel="stylesheet" href="<%=basePath%>layuiadmin/style/admin.css" media="all">
</head>
<body>

  <div class="layui-fluid">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md12">
        <div class="layui-card">
          <div class="layui-card-header">设置我的资料</div>
          <div class="layui-card-body" pad15>
            <!-- <div class="layui-form" lay-filter=""> -->
            <form class="layui-form" action="#">
              <input type="hidden" name="userId" value="${model.userId}">
              <div class="layui-form-item">
                <label class="layui-form-label">我的角色</label>
                <div class="layui-input-inline">
                  <select name="role" lay-verify="">
                  	<c:forEach var="item" items="${roles}" varStatus="roleStatus">
                  	<option value="${item.roleId}" ${0 eq roleStatus.index?'selected':''}>${item.roleName}</option>
                  	</c:forEach>
                  </select> 
                </div>
                <div class="layui-form-mid layui-word-aux">当前已拥有的角色</div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">账户</label>
                <div class="layui-input-inline">
                  <input type="text" name="account" value="${model.account}" readonly class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">不可修改。一般用于后台登入名</div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">真实名称</label>
                <div class="layui-input-inline">
                  <input type="text" name="userRealName" value="${model.userRealName}" lay-verify="nickname" autocomplete="off" placeholder="请输入真实名称" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                <c:forEach var="item" items="${sexList}" varStatus="sexStatus">
                  <input type="radio" name="userSex" value="${item.key}" title="${item.value}" ${item.key eq model.userSex?'checked':''}>
                </c:forEach>
                </div>
              </div>
              <!-- <div class="layui-form-item">
                <label class="layui-form-label">头像</label>
                <div class="layui-input-inline">
                  <input name="avatar" lay-verify="required" id="LAY_avatarSrc" placeholder="图片地址" value="http://cdn.layui.com/avatar/168.jpg" class="layui-input">
                </div>
                <div class="layui-input-inline layui-btn-container" style="width: auto;">
                  <button type="button" class="layui-btn layui-btn-primary" id="LAY_avatarUpload">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                  </button>
                  <button class="layui-btn layui-btn-primary" layadmin-event="avartatPreview">查看图片</button >
                </div>
             </div> -->
              <div class="layui-form-item">
                <label class="layui-form-label">手机</label>
                <div class="layui-input-inline">
                  <input type="text" name="userMobile" value="${model.userMobile}" lay-verify="phone" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-inline">
                  <input type="text" name="userEmail" value="${model.userEmail}" lay-verify="email" autocomplete="off" class="layui-input">
                </div>
              </div>
              <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                  <textarea name="remark" placeholder="请输入内容" class="layui-textarea">${model.remark}</textarea>
                </div>
              </div>
              <div class="layui-form-item">
                <div class="layui-input-block">
                  <button class="layui-btn" id="setuser2commit" lay-submit lay-filter="setuser2commit">确认修改</button>
                  <!-- <button type="reset" class="layui-btn layui-btn-primary">重新填写</button> -->
                </div>
              </div>
            <!-- </div> -->
            </form>
            
          </div>
        </div>
      </div>
    </div>
  </div>

  <script src="<%=basePath%>layuiadmin/layui/layui.js"></script>  
  <script>
  layui.config({
    base: '<%=basePath%>layuiadmin/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use(['index', 'set']);
  </script>
</body>
</html>