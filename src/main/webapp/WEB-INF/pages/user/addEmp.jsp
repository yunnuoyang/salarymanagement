<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/10
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui-v2.5.6/css/layui.css"  media="all">
</head>
<body>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">登陆名称</label>
        <div class="layui-input-block">
            <input type="text" id="loginName" name="loginName" lay-verify="required" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">真实姓名</label>
        <div class="layui-input-block">
            <input type="text" id="realName" name="realName" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-block">
            <input type="text" id="address" name="address" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">基础薪资</label>
        <div class="layui-input-block">
            <input type="text" id="basic" name="basic" lay-verify="required" lay-reqtext="用户名是必填项，岂能为空？" placeholder="请输入" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-inline">
                <input type="tel" id="tel" name="tel" lay-verify="required|phone" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" id="email" name="email" lay-verify="email" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">部门</label>
        <div class="layui-input-block">
            <%--//先使用lay-ignore去除掉layform的影响，在为select添加layui的样式保持界面的美观--%>
            <select id="department"  lay-ignore   class="layui-input">
            </select>
        </div>
    </div>





    <div class="layui-form-item" pane="">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="1" title="男" checked="">
            <input type="radio" name="sex" value="0" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.all.js"></script>
<script>

    $(function($) {
        $.get("/department/info",
            function (value) {
                var departs=value.data
                $.each(departs,function(i,v){
                    $("#department").append("<option value='"+v.dno+"'>"+v.dname+"</option>")
                })
            }
        )
    });
    
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer



    
        //监听提交
        form.on('submit(demo1)', function(data){
            var json=JSON.parse(JSON.stringify(data.field));
            $.get("/storeInfo",
                {
                    "loginName":json.loginName
                    ,"realName":json.realName
                    ,"address":json.address
                    ,"sex":json.sex
                    ,"tel":json.tel
                    ,"email":json.email
                    ,"basic":json.basic
                    ,"departmentNo":$("#department option:selected").val()
                }
                ,
                function (data) {
                    layer.msg(data,{icon: 6,time:1000, shade:0.4})
                }
            )
            parent.layui.table.reload("test");
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index); //关闭当前页
            return false;
        });
    });
</script>
</body>
</html>
