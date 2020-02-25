<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/18
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>申请假期</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui-v2.5.6/css/layui.css" media="all">
</head>
<body>
<form class="layui-form" action="" style="margin-top: 200px">
    <div class="layui-form-item">
        <label class="layui-form-label">申请原因</label>
        <div class="layui-input-block">
            <input type="text" name="reason" lay-verify="required" lay-reqtext="申请原因是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>


        <div class="layui-inline">
            <label class="layui-form-label">起始时间</label>
            <div class="layui-input-inline">
                <input type="text" name="startTime" id="startTime" lay-verify="datetime" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
    <div class="layui-inline">
            <label class="layui-form-label">终止时间</label>
            <div class="layui-input-inline">
                <input type="text" name="endTime" id="endTime" lay-verify="datetime" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#startTime'
            ,type: 'datetime'
            // ,min: '09:00:00'
            // ,max: '17:30:00'
        });
        laydate.render({
            elem: '#endTime'
            ,type: 'datetime'
            // ,min: '09:00:00'
            // ,max: '17:30:00'
        });

//创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //监听提交
        form.on('submit(demo1)', function(data){
            var obj=JSON.parse(JSON.stringify(data.field));

            $.get("/apply",
                { reason: obj.reason
                    , startTime: obj.startTime
                    , endTime: obj.endTime

                },function (data) {
                    layer.msg(data)
                });
            // return false;
        });

    });
</script>
</body>
</html>
