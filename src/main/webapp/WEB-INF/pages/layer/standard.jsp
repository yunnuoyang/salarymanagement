<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/13
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统设置</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui-v2.5.6/css/layui.css" media="all">
</head>
<body>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
</body>

<script>

    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'/standard'
            ,toolbar: true
            ,title: '系统标准设置表'
            ,totalRow: true
            ,cols: [[
                {field:'onLineDays', title:'月总工作日', width:120, fixed: 'left', sort: true}
                ,{field:'onLineHours', title:'日工作时间', width:120, edit: 'text'}
                ,{field:'id', title:'id', hide: true, width:120, edit: 'text'}
                ,{field:'workStartHour', title:'上班开始时间', width:120, edit: 'text'}
            ]]
            ,page: true
            ,response: {
                statusCode: 200 //重新规定成功的状态码为 200，table 组件默认为 0
            }
            ,parseData: function(res){ //将原始数据解析成 table 组件所规定的数据
                return {
                    "code": res.status, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.total, //解析数据长度
                    "data": res.data //解析数据列表
                };
            }
        });

        table.on('edit(test)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            $.get(
                "/settings/edit"
                ,{
                     id:data.id
                    ,onlineDays:data.onlineDays
                    ,onlineHours:data.onlineHours
                    ,workStartHour:data.workStartHour
                }
            )
        })
    });
</script>
</html>
