<%@ page import="com.joinx.salary.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/11
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui-v2.5.6/css/layui.css" media="all">
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="permit">通过</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="back">返回</a>
</script>
<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>

</body>
<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'/allNoResultLeaves'
            ,toolbar: true
            ,title: '请假记录表'
            ,totalRow: true
            ,cols: [[
                {field:'realName', title:'员工姓名', width:120, fixed: 'left', sort: true}
                ,{field:'lNo', title:'请假编号', width:120, edit: 'text'}
                ,{field:'startTime', title:'请假开始时间', width:120, edit: 'text'}
                ,{field:'endTime', title:'请假终止时间', width:120, edit: 'text'}
                ,{field:'reason', title:'请假原因', width:120, edit: 'text'}
                ,{field:'applyTime', title:'申请时间', width:120, edit: 'text'}
                ,{field:'operateTime', title:'处理时间', width:120, edit: 'text'}
                ,{field:'operateResult', title:'处理结果', width:120, edit: 'text'}
                ,{fixed: 'right', width:250, align:'center', toolbar: '#barDemo'}
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

        table.on('tool(test)', function(obj){
            var data=obj.data;
            if(obj.event=='permit'){

                $.get("/leave/operate",
                    {
                        "lno":data.lNo
                        ,"operateResult":"2"
                    }
                    ,function (data) {
                        layer.msg(data)
                    }
                )
            }
            if(obj.event=='back'){
                alert(data.lNo)
                $.get("/leave/operate",
                    {
                        "lno":data.lNo
                        ,"operateResult":"1"
                    }
                    ,function (data) {
                     layer.msg(data)
                    }
                )
            }
        })
    });
</script>
</html>
