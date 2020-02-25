<%--
  Created by IntelliJ IDEA.
  User: shenying
  Date: 2019/7/8
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html dir="ltr" lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <title>工资管理系统</title>
    <style type="text/css">

        a:visited{color: #f89406;}
        a:hover{color:pink;}
        a:active{color:#ccc;}
        a{text-decoration: none}
    </style>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/login/css/style.css" type="text/css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui-v2.5.6/css/layui.css"  media="all">
</head>

<body>
<div id="container">

    <!--
    服务器跳转时，路径相对的是服务器WebContent
    浏览器请求跳转时， 路径相对的当前的jsp

     在JSP  凡是有  action  src  href 都要去写绝对路径  加上工程名称的路径
    -->
    <form action="${pageContext.request.contextPath }/login/dologin" method="post">
        <div class="login">工资管理系统
        </div>
        <div class="username-text">用户名:</div>
        <div class="password-text">密码:</div>
        <div class="username-field">
            <input type="text" name="loginName" id="loginName" value="" />
        </div>
        <div class="password-field">
            <input type="password" name="password" id="password" value="" />
        </div>



        <div class="forgot-usr-pwd"></div>
        <button type="button" id="btn" class="layui-btn layui-btn-warm layui-btn-radius" style="margin-left:300px">GO</button>
    </form>
</div>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.all.js"></script>
<script src="${pageContext.request.contextPath }/js/layer.js"></script>
<script>
    var layer;
    function layuiTag(text){
        layui.use('layer',function(){
            layer=layui.layer;
            layer.msg(text, {icon: 6,time:1000, shade:0.4});
        });
    }

    $("#btn").click( function () {
        var loginName=$("#loginName").val();
        var password=$("#password").val();
        if(loginName==""||loginName==null){
            layuiTag("登录名不能为空")
            return false;
        }
        if(password==""||password==null){
            layuiTag("密码不能为空")
            return false;
        }
        $.post("/login/dologin", { loginName: loginName,
                password: password },
            function(data){
            layuiTag(data.info)
               if(data.tag==3){//当前用户符合登陆的请求，拿出用户的信息
                   if(data.obj.role.rno==001){//管理员
                       window.location.href="/login/main"

                   }else{
                       window.location.href="/path/main/main2"
                   }
               }
            });

    } );




</script>

</body>
</html>

