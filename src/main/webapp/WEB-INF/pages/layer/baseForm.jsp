<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/1/28
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人基本资料</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/layui-v2.5.6/css/layui.css" media="all">
<body>
<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.all.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<form class="layui-form" action="" lay-filter="example">
    <div class="layui-form-item">
        <label class="layui-form-label">用户名</label>
        <div class="layui-input-block">
            <input type="text"  id="realName" lay-verify="title" autocomplete="off" placeholder="请输入真实名称" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登录名</label>
        <div class="layui-input-block">
            <input type="text" name="username" id="loginName" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" id="password"  placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">所属部门</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="text"  id="department" lay-verify="title" autocomplete="off" placeholder="请输入真实名称" class="layui-input">--%>
        <%--</div>--%>
    <%--</div>--%>

    <div class="layui-form-item">
        <label class="layui-form-label">在职状态</label>
        <div class="layui-input-block">
            <input readonly="readonly"  type="text"  id="status" lay-verify="title" autocomplete="off" placeholder="请输入真实名称" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">家庭住址</label>
        <div class="layui-input-block">
            <input type="text" id="address" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">个人邮箱</label>
        <div class="layui-input-inline">
            <input type="text" id="email" name="email" lay-verify="email" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">电话号码</label>
        <div class="layui-input-block">
            <input type="text" id="tel" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">工作年限</label>
        <div class="layui-input-block">
            <input readonly="readonly" type="text" id="workAge" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn layui-btn-normal" id="fix">修改密码</button>
            <button type="button" class="layui-btn layui-btn-normal" id="submit">提交</button>
            <button type="button" class="layui-btn layui-btn-normal" id="exit">退出</button>
        </div>
    </div>
    <input id="selects" hidden="hidden"></input>
    <input id="userNo" hidden="hidden"></input>
</form>
<script src="${pageContext.request.contextPath }/js/layer.js"></script>
<script>
    var layer;
    function layuiTag(text){
        layui.use('layer',function(){
            layer=layui.layer;
            layer.msg(text, {icon: 6,time:1000, shade:0.4});
        });
    }
    $("#fix").click(function(){
        layer.open({
                        type:1,
                        area:['700px','350px'],
                        content: "<div class=\"layui-form-item\">\n" +
                        "    <label class=\"layui-form-label\">输入原密码</label>\n" +
                        "    <div class=\"layui-input-block\">\n" +
                        "      <input type=\"password\" id=\"oldPwd\" placeholder=\"请输入密码\" autocomplete=\"off\" class=\"layui-input\">\n" +
                        "    </div>\n" +
                        "  </div><div class=\"layui-form-item\">\n" +
                        "    <label class=\"layui-form-label\">新密码</label>\n" +
                        "    <div class=\"layui-input-block\">\n" +
                        "      <input type=\"password\" id=\"newPwd\" placeholder=\"请输入密码\" autocomplete=\"off\" class=\"layui-input\">\n" +
                        "    </div>\n" +
                        "  </div><div class=\"layui-form-item\">\n" +
                        "    <label class=\"layui-form-label\">再次输入密码</label>\n" +
                        "    <div class=\"layui-input-block\">\n" +
                        "      <input type=\"password\" id=\"rePwd\" placeholder=\"请输入密码\" autocomplete=\"off\" class=\"layui-input\">\n" +
                        "    </div>\n" +
                        "  </div>"
                        ,btn: '提交'
                        ,btnAlign: 'c' //按钮居中
                        ,shade: 0 //不显示遮罩
                        ,yes: function(){
                            $.get(
                                "/user/pwd",
                                {oldPwd:$("#oldPwd").val(),
                                    newPwd:$("#newPwd").val(),
                                    rePwd:$("#rePwd").val(),
                                    userNo:$("#userNo").val()
                                },
                                function(data){
                                    layuiTag(data.info)
                                    if(data.tag==5){
                                        alert(data.info)
                                        closeFrame();
                                    }
                                }
                            )
                            }
                            ,end : function() {
                                layer.closeAll();
                                location.reload();
                            }
                }
                    )
    })
    $("#submit").click(function(){
        var loginName=$("#loginName").val();
        var realName=$("#realName").val();
        var address=$("#address").val();
        var email=$("#email").val();
        var tel=$("#tel").val();
        var telmail=/^1\d{10}$/;
        var regemail=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if(loginName==""||loginName==null){
            layuiTag("登录名不能为空")
            return false;
        }if(realName==""||realName==null){
            layuiTag("真实姓名不能为空")
            return false;
        }if(address==""||address==null){
            layuiTag("家庭住址不能为空")
            return false;
        }
        if(!regemail.test(email)){
            layuiTag("个人邮箱格式不正确")
            return false;
        }
        if(!telmail.test(tel)){
            layuiTag("手机号码格式不正确")
            return false;
        }
        $.post("/user/update",
            { loginName: loginName,
                realName:realName,
                address:address,
                tel:tel,
                email:email,
                userNo:$("#userNo").val()
                 },
            function(data){
                layuiTag(data.info)
            });
    })
    function closeFrame(){
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);//关闭当前页
    }
    $("#exit").click(function(){
        closeFrame();
        // window.parent.location.replace(location.href)//刷新父级页面
    })
    $(function($) {
        $.get(
            "/layer/baseinfo",
            function(data){
                console.log(data[0].userNo)
                $("#loginName").val(data[0].loginName)
                $("#userNo").val(data[0].userNo)
                $("#realName").val(data[0].realName)
                console.log(data[0].password)
                $("#email").val(data[0].email)
                console.log(data[0].address)
                $("#tel").val(data[0].tel)
                $("#address").val(data[0].address)
                if(data[0].status==1){
                    $("#status").val("在职状态")
                }else{
                    $("#status").val("离职状态")
                }
                // $("#realName").val(data[0].status)
                $("#workAge").val(data[0].workAge)
                console.log(data[0].role.name)
                console.log(data[0].role.rno)
                $("#selects").val(data[1]);
                // $.each( data[1], function(i, n){
                //     if(data[0].departmentNo==n.dno){
                //         $("#department").val(n.dname)
                //     }
                //
                // });
            })

    });
    //构造出选择框
    // $("#department").click(function(){
    //     $.get("/departments",function(data){
    //         var choose;
    //         $.each(data,function (i,n) {
    //             choose+="<option name='"+n.dno+"'>"+n.dname+"</option>"
    //         })
    //         var html="<select id='ddepartment'>"+choose+"</select>"
    //         alert(html)
    //         layer.open({
    //             type:1,
    //             area:['1000px','500px'],
    //             content: html
    //             }
    //         )
    //         $("#department").append(html)
    //
    //     })
    //
    // })

</script>
</body>
</html>
