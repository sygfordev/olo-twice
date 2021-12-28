禁用按钮状态：
$('#Btn').addClass("layui-btn-disabled").attr("disabled",true);
恢复按钮状态：
$('#Btn').removeClass("layui-btn-disabled").attr("disabled",false);


layer.msg('文本内容')  当要对文本内容进行换行时，要加上</br>，才能够实现，示例如下：
layer.msg('操作失败</br>失败原因是XXX')
经尝试，加上/r/n ，/n，<br>都不能实现上面的效果
另外，layer.alert方法内要实现文本换行效果，跟layer.msg()一致