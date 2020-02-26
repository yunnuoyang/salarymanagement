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
    <title>五险一金设置</title>
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
            ,url:'/setting/insuranceSetting'
            ,toolbar: true
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '请假记录表'
            ,totalRow: true
            ,cols: [[
                {field:'id', title:'部门名称',hide:true, width:120, fixed: 'left', sort: true}
                ,{field:'description', title:'所属', width:200}
                ,{field:'endowmentInsuranceRate', title:'养老保险缴纳比例',hide:true, width:120, edit: 'text'}
                ,{field:'mdeicareRate', title:'医疗保险缴纳比例', width:120, edit: 'text'}
                ,{field:'unemploymentRate', title:'失业保险缴纳比例', width:120, edit: 'text'}
                ,{field:'injuryInsuranceRate', title:'工伤保险缴纳比例', width:120, edit: 'text'}
                ,{field:'rearInsuranceRate', title:'生育保险缴纳比例', width:120, edit: 'text'}
                ,{field:'reservedFundRate', title:'公积金缴纳比例', width:120, edit: 'text'}
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
        });
        table.on('edit(test)',function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            alert(obj.id)
            // layer.confirm('确定在后台修改此税率吗？', {icon: 3, title:'提示'}, function(index){
            //
            //     alert("aa")
            //     layer.close(index);
            // });
            $.get(
                "/setting/editInsurance"
                ,{
                    id:data.id
                    ,endowmentInsuranceRate:data.endowmentInsuranceRate
                    ,mdeicareRate:data.mdeicareRate
                    ,unemploymentRate:data.unemploymentRate
                    ,injuryInsuranceRate:data.injuryInsuranceRate
                    ,rearInsuranceRate:data.rearInsuranceRate
                    ,reservedFundRate:data.reservedFundRate
                }
            )

            // table.reload('test');
            // var value = obj.value //得到修改后的值
            //     ,data = obj.data //得到所在行所有键值
            //     ,field = obj.field; //得到字段

        })
    });
</script>
</html>
