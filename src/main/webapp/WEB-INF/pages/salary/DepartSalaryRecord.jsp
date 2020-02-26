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
    <title>公司部门工资支出记录</title>
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
            ,url:'/depart/salaryRecord'
            ,toolbar: true
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '请假记录表'
            ,totalRow: true
            ,cols: [[
                {field:'dname', title:'部门名称', width:120, fixed: 'left', sort: true}
                ,{field:'dno', title:'部门编号',hide:true, width:120, edit: 'text'}
                ,{field:'id', title:'部门薪水记录id',hide:true, width:120, edit: 'text'}
                ,{field:'medicare', title:'医疗保险总额', width:120, edit: 'text'}
                ,{field:'injury', title:'工伤保险总额', width:120, edit: 'text'}
                ,{field:'endowment', title:'养老保险总额', width:120, edit: 'text'}
                ,{field:'unemployment', title:'失业保险总额', width:120, edit: 'text'}
                ,{field:'reservedFund', title:'公积金总额', width:120, edit: 'text'}
                ,{field:'basicCount', title:'基础薪资', width:120, edit: 'text'}
                ,{field:'count', title:'总金额', width:120, edit: 'text'}
                ,{field:'sendTime', title:'下发时间', width:120, edit: 'text'}
                ,{fixed: 'right', width:250, align:'center', toolbar: '#barDemo'}
            ]]
            ,page: true

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
