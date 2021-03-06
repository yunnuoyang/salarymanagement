<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/10
  Time: 12:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>部门列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui-v2.5.6/css/layui.css" media="all">
</head>
<body>
<table class="layui-hide" id="test" lay-filter="test"></table>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="choose">选中</a>
</script>
</body>
<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>

<script>

    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'/department/info'
            ,toolbar: true
            ,title: '部门数据表'
            ,totalRow: true
            ,cols: [[
                {field:'dno', title:'部门编号', width:80, fixed: 'left', unresize: true, sort: true, totalRowText: '合计行'}
                ,{field:'dname', title:'部门名称', width:120, edit: 'text'}
                ,{fixed: 'right', width:150, align:'center', toolbar: '#barDemo'}
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
            if(obj.event=='choose'){
                window.parent.getrowselect(data);
                var userNo=window.parent.getdata();

                $.get("/user/update",
                    {
                        "userNo":userNo
                        ,"departmentNo":data.dno
                    }
                )
                $.get("/path/user/userList"
                )
                //刷新父级数据表格
                parent.layui.table.reload("test");
                var index = parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);//关闭当前页
            }
        })
    });
</script>
</html>
