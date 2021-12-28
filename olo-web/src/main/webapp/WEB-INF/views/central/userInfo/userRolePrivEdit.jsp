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
  <title>权限树扩展分享</title>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/layuiadmin/layui/css/layui.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/layuiadmin/layui/css/modules/authtree/auth-skin-default.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/layuiadmin/layui/css/modules/authtree/auth-skin-universal.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath%>/layuiadmin/layui/css/modules/authtree/template.css">
  <script type="text/javascript" src="<%=basePath%>/layuiadmin/layui/layui.js"></script>
  <script type="text/javascript" src="<%=basePath%>/layuiadmin/modules/userRolePrivEdit.js"></script>
</head>
<body>
<div class="layui-container">
  <div class="layui-row">
  	<form class="layui-form" lay-filter="authtree-submit-form">
  	<div class="layui-col-md11 layui-col-md-offset1">
      <!-- 隐藏暂存参数 -->
	  <input type="hidden" id="userId" name="userId" value="${model.userId}">
	  <input type="hidden" id="roleId" name="roleId" value="${systemRole.roleId}">
	  <input type="hidden" id="selectedPrivIds" name="selectedPrivIds">
	  
      <!-- <div class="layui-form">
        <div class="layui-form-item">
          <div class="layui-form-label">普通操作</div>
          <div class="layui-form-block">
            <button type="button" class="layui-btn layui-btn-primary" onclick="checkAll('#LAY-auth-tree-index')">全选</button>
            <button type="button" class="layui-btn layui-btn-primary" onclick="uncheckAll('#LAY-auth-tree-index')">全不选</button>
            <button type="button" class="layui-btn layui-btn-primary" onclick="showAll('#LAY-auth-tree-index')">全部展开</button>
            <button type="button" class="layui-btn layui-btn-primary" onclick="closeAll('#LAY-auth-tree-index')">全部隐藏</button>
          </div>
        </div>
      </div> -->
    </div>
    <div class="layui-col-md6 layui-col-md-offset1">
      <!-- <fieldset class="layui-elem-field layui-field-title">
        <legend>选择权限</legend>
      </fieldset> -->
      <!-- 此扩展能递归渲染一个权限树，点击深层次节点，父级节点中没有被选中的节点会被自动选中，单独点击父节点，子节点会全部 选中/去选中 -->
        <!-- <div class="layui-form-item">
          <label class="layui-form-label">角色名称</label>
          <div class="layui-input-block">
            <input class="layui-input" type="text" name="name" placeholder="请输入角色名称"/>
          </div>
        </div> -->
        <div class="layui-form-item">
          <!-- <label class="layui-form-label">选择权限</label> -->
          <div class="layui-input-block" style="width: 680px; height: 680px; overflow: auto; font-size: 0; margin-left: 210px">
            <div id="LAY-auth-tree-index"></div>
          </div>
        </div>
        <!-- <div class="layui-form-item">
          <div class="layui-input-block" style="margin-left: 310px">
            <button class="layui-btn" type="submit" lay-submit lay-filter="LAY-userRolePrivEdit-submit">提交</button>
            <button class="layui-btn layui-btn-primary" type="reset">重置</button>
          </div>
        </div> -->
    </div>
    </form>
  </div>
</div>
</body>
<!-- 状态模板 -->
<style type="text/css">
  .layui-layer-page .layui-layer-content {
    padding: 20px;
    line-height: 22px;
  }
</style>
<!--节点状态渲染模板-->
<script type="text/html" id="LAY-auth-tree-nodes">
  <table class="layui-table">
    <thead>
    <th>方法名</th>
    <th>描述</th>
    <th>节点</th>
    </thead>
    <tbody>
    {{# layui.each(d.data, function(index, item) { }}
    <tr>
      <td>{{item.func}}</td>
      <td>{{item.desc}}</td>
      <td><a class="LAY-auth-tree-show-detail" href="javascript:;" data-title="{{item.desc}}"
             data-content="{{item.data.join(']<br>[')}}">查看详情</a>({{item.data.length}})
      </td>
    </tr>
    {{# });}}
    </tbody>
  </table>
</script>
<!--列表转树渲染模板-->
<script type="text/html" id="LAY-auth-tree-list-convert">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>列表数据转权限树</legend>
  </fieldset>
  <form class="layui-form">
    <div class="layui-form-item"><label class="layui-form-label">多选权限</label>
      <div class="layui-input-block">
        <div id="{{d.layId}}"></div>
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-block">
        <button class="layui-btn" type="submit" lay-submit lay-filter="LAY-auth-tree-submit">提交</button>
        <button class="layui-btn layui-btn-primary" type="reset">重置</button>
      </div>
    </div>
  </form>
  <pre class="layui-code" id="LAY-auth-tree-convert-code" lay-title="返回的列表数据">{{d.code}}</pre>
  <pre class="layui-code" id="LAY-auth-tree-convert-code-answer" lay-title="转换的树状数据">{{d.codeAns}}</pre>
</script>
<!-- 树转单选树的DOM -->
<script type="text/html" id="LAY-auth-tree-convert-select">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>树转下拉树</legend>
  </fieldset>
  <form class="layui-form">
    <div class="layui-form-item">
      <label class="layui-form-label">转换结果</label>
      <div class="layui-input-block">
        <select name="authid" class="layui-input" lay-filter="{{d.layFilter}}">
          {{# layui.each(d.list, function(index, item) { }}
          <option value="{{item.value}}" {{item.checked?'selected':''}} {{item.disabled?'disabled':''}}>{{item.name}}</option>
          {{# });}}
        </select>
      </div>
    </div>
    <blockquote class="layui-elem-quote">注：如果返回的是列表数据而非树状数据，可以先进行『列表转树』操作。<br>往后如果对这方面的效率有需求，请联系我添加一个 『列表转下拉树』的功能！
    </blockquote>
    <pre class="layui-code">{{d.code}}</pre>
  </form>
</script>
<!--隐藏左侧操作的DOM-->
<script type="text/html" id="LAY-auth-tree-hide-choose">
  <fieldset class="layui-elem-field layui-field-title">
    <legend>隐藏左侧操作</legend>
  </fieldset>
  <form class="layui-form">
    <div class="layui-form-item"><label class="layui-form-label">下拉权限</label>
      <div class="layui-input-block">
        <div id="{{d.hideChooseDomId}}"></div>
      </div>
    </div>
    <div class="layui-form-item">
      <div class="layui-input-block">
        <button class="layui-btn" type="submit" lay-submit lay-filter="LAY-auth-tree-submit">提交</button>
        <button class="layui-btn layui-btn-primary" type="reset">重置</button>
      </div>
    </div>
  </form>
</script>
<!-- 加群交流layer模板 -->
<script type="text/html" id="LAY-auth-tree-group-add">
  <!-- <fieldset class="layui-elem-field layui-field-title">
    <legend>加群交流</legend>
  </fieldset>
   --><p style="text-align: center;"><b>群号：</b>789188686，<a href="https://jq.qq.com/?_wv=1027&k=5Cd08sE" target="_blank">点我加群</a>
  </p>
  <img src="https://raw.githubusercontent.com/wangerzi/layui-authtree/master/qq_group_qrcode.png"
       style="max-width: 100%;display:block;margin: 0 auto;">
</script>
</html>