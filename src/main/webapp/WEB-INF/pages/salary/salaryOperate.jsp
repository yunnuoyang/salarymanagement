<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/8
  Time: 9:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息表</title>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui-v2.5.6/css/layui.css" media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="barDemo2">
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="recaculate">重新计算工资</a>
</script>

<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>

<script>

    layui.use('table', function(){
        var table = layui.table;
        var form = layui.form;
        table.render({
            elem: '#test'
            ,url:'/salary/operate'
            ,toolbar: "#toolbarDemo"
            ,title: '用户数据表'
            ,totalRow: true
            ,cellMinWidth: 100
            ,cols: [[
                {type:'checkbox'}
                ,{field:'userNo', title:'员工编号',sort:true}
                ,{field:'loginName', title:'登录名称'}
                ,{field:'basic', title:'基础薪资'}
                // ,{field:'status', title:'在职状态',event: 'status',sort: true}
                ,{field:'performance', title:'绩效',edit: 'text', width:110, unresize: true,sort: true}
                ,{field:'wage', title:'罚金', width:85,edit: 'text' ,unresize: true,sort: true}
                ,{field:'permitTime', title:'下发时间', edit: 'text'}
                ,{field:'id', title:'下发时间', hide:true}
                ,{field:'salaryNo', title:'工资编号', edit: 'text'}
                ,{field:'status', title:'处理状态', edit: 'text'}
                ,{fixed: 'right', width:150, align:'center', toolbar: '#barDemo2'}
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
                    "data": res.data //解析数据列
                };
            }
            ,height: 600
            ,done:function(res){
            }
        });
        table.on('tool(test)', function(obj){
            var data=obj.data;
            if(obj.event=='recaculate'){
                    $.get(
                        "/salary/update"
                        ,{
                            id:data.id
                            ,  performance:data.performance
                            ,  status:data.status="未处理"?0:1
                            ,  sno:data.salaryNo
                            ,  eno:data.userNo
                        }
                        ,function(data) {

                            layer.msg(data,{icon: 6,time:1000, shade:0.4})
                }
                    )

            }
        })
    });
</script>
</body>
</html>
