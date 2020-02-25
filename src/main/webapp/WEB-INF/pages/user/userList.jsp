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
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="addEmp">添加员工</button>
    </div>
</script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="attendancecord">缺勤</a>
    <a class="layui-btn layui-btn-xs" lay-event="salary">工资记录</a>
</script>
<script type="text/html" id="barDemo2">
    <a class="layui-btn layui-btn-xs" lay-event="leavecord">请假记录</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/html" id="checkboxTpl">
    <!-- 这里的 checked 的状态只是演示 -->
    <input type="checkbox" id="lockstatus"  name="{{d.userNo}}" value="{{d.status}}" title="离职" lay-filter="lockDemo" {{ d.status == 0? 'checked': '' }}>
</script>
<script type="text/html" id="switchTpl">
    <!-- 这里的 checked 的状态只是演示 -->
    <input type="checkbox" name="{{d.userNo}}" value="{{d.sex}}" lay-skin="switch" lay-text="女|男" lay-filter="sexDemo" {{ d.sex == 0 ? 'checked' : '' }}>
</script>
<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<input type="text" id="transfer" hidden="hidden">
<script>
        //从子窗口传递到父窗口
    function getrowselect(data) {
        console.log(data.dno+data.dname);
    }
    //给子窗口传递数据
    function getdata(){
        return $("#transfer").val();
    }
    var layer;

    function layuiTag(text){
        layui.use('layer',function(){
            layer=layui.layer;
            layer.msg(text, {icon: 6,time:1000, shade:0.4});
        });
    }
    layui.use('table', function(){
        var table = layui.table;
        var form = layui.form;
        table.render({
            elem: '#test'
            ,url:'/user/info'
            ,toolbar: "#toolbarDemo"
            ,title: '用户数据表'
            ,totalRow: true
            ,cellMinWidth: 100
            ,cols: [[
                {type:'checkbox'}
                ,{field:'userNo', title:'员工编号',sort:true}
                ,{field:'loginName', title:'登录名称',edit: 'text'}
                ,{field:'realName', title:'真实姓名', edit: 'text'}
                // ,{field:'status', title:'在职状态',event: 'status',sort: true}
                ,{field:'status', title:'是否在职', width:110, templet: '#checkboxTpl', unresize: true,sort: true}
                ,{field:'sex', title:'性别', width:85, templet: '#switchTpl', unresize: true,sort: true}
                ,{field:'address', title:'家庭住址', edit: 'text'}
                ,{field:'tel', title:'手机号码', edit: 'text'}
                ,{field:'email', title:'邮箱', edit: 'email'}
                ,{field:'basicSalary', title:'基础薪资', edit: 'basicSalary'}
                ,{field:'workAge', title:'工龄', edit:'text'}
                ,{field:'roleName', title:'身份', hide: true}
                ,{field:'departmentNo', title:'部门编号', hide: true,edit: 'text',event: 'setSign'}
                ,{field:'departmentName', title:'部门名称', lign: 'right',edit: 'text',event: 'department'}
                ,{fixed: 'right', width:250, align:'center', toolbar: '#barDemo'}
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
            ,done:function(){

            }
        });
        table.on('edit(status)',function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layui.alert(value+field)
        })

        //监听性别操作
        form.on('switch(sexDemo)', function(obj){
            var param;
            if(obj.elem.checked){
                param=0;
            }else{
                param=1;
            }
            $.get("/user/update",
                {
                    "userNo":this.name
                    ,"sex":param
                }
            )
            // layer.tips(this.value + ' ' + this.name + '：'+obj.elem.checked , obj.othis);
        });


        //监听锁定操作
        form.on('checkbox(lockDemo)', function(obj){
            var param;
            if(obj.elem.checked){
                param=0;
            }else{
                param=1;
            }
            $.get("/user/update",
                {
                    "userNo":this.name
                   ,status:param
                }
            )
            // layer.tips(this.value + ' ==' + this.name + '：'+ obj.elem.checked, obj.othis);
        });

        //监听单元格编辑
        table.on('edit(test)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            var telmail=/^1\d{10}$/;
            var regemail=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
            if(field=="tel"){
                if(!telmail.test(value)){
                    layuiTag("手机号码格式不正确，本次数据不予保存")
                    return false;
                }


            }if(field=="email"){
                if(!regemail.test(value)){
                    layuiTag("邮箱格式不正确,本次数据不予保存")
                    return false;
                }
            }
            var json={ field :value}
            $.get("/user/update",
                {    userNo:data.userNo
                    ,loginName:data.loginName
                    ,realName:data.realName
                    ,address:data.address
                    ,tel:data.tel
                    ,workAge:data.workAge
                    ,email:data.email
                    ,basicSalary:data.basicSalary
                },
                function(data){

                });
            $.get("/path/user/userList")
        });




        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'addEmp':
                    layer.open({
                        type:2,
                        area:['1000px','500px'],
                        content:['/path/user/addEmp','yes']
                    });

                    break;
            };
        });

        table.on('tool(test)', function(obj){
            var data = obj.data;
            if(obj.event === 'setSign'){
                alert(data.userNo)
            }
            if(obj.event === 'department'){
                $("#transfer").val(data.userNo)
                layer.open({
                    type:2,
                    area:['1000px','500px'],
                    content:['/path/layer/departmentList','yes']
                    ,success:function (layero,index) {

                    }
                    ,end: function(){
                        table.reload('test');
                    }
                });


            }
            if(obj.event === 'attendancecord'){
                $("#transfer").val(data.userNo)
                layer.open({
                    type:2,
                    area:['1000px','500px'],
                    content:['/path/layer/attendance','yes']
                    ,success:function (layero,index) {

                    }
                    ,end: function(){
                        table.reload('test');
                    }
                });
            }
            else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index)
                    $.get("/user/delete",
                        { userNo: data.userNo},
                        function(data){

                        });
                });
            }
            else if(obj.event === 'leavecord'){//查询用户的请假记录
                $("#transfer").val(data.userNo)
                layer.open({
                    type:2,
                    area:['1000px','500px'],
                    content:['/path/layer/leaveList','yes']
                    ,success:function (layero,index) {

                    }
                    ,end: function(){
                        table.reload('test');
                    }
                });

                // layer.alert('编辑行：<br>'+ JSON.stringify(data))
            }else if(obj.event === 'salary'){
                $("#transfer").val(data.userNo)
                layer.open({
                    type:2,
                    area:['1000px','500px'],
                    content:['/path/salary/salarylist','yes']
                    ,success:function (layero,index) {

                    }
                    ,end: function(){
                        table.reload('test');
                    }
                });
                // layer.alert('编辑行：<br>'+ JSON.stringify(data))
            }

        });
    });
</script>
</body>
</html>
