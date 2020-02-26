<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/13
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/layui-v2.5.6/css/layui.css" media="all">
</head>
<body>
<div id="pie" style="width: 600px;height:400px;float: left"></div>
<script src="${pageContext.request.contextPath }/layui-v2.5.6/layui.js"></script>
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath }/js/echarts.common.min.js"></script>
<script src="${pageContext.request.contextPath }/js/date.js"></script>

<input id="start" hidden="hidden" >
<input id="end" hidden="hidden">
<input id="parentName" hidden="hidden">
<form class="layui-form" action="" style="margin-top: 200px">


    <div class="layui-inline">
        <label class="layui-form-label">起始时间</label>
        <div class="layui-input-inline">
            <input type="text" name="startTime" id="startTime" lay-verify="datetime" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-inline">
        <label class="layui-form-label">终止时间</label>
        <div class="layui-input-inline">
            <input type="text" name="endTime" id="endTime" lay-verify="datetime" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <%--<button type="button" id="btn" class="layui-btn layui-btn-primary">导出统计图</button>--%>
        </div>
    </div>
</form>
</body>
<script>


    function start(){
        return new Date($("#start").val()).Format("yyyy-MM-dd");
    }
    function end(){

        return new Date($("#end").val()).Format("yyyy-MM-dd");
    }
    function parentName(){
        return $("#parentName").val();
    }
    (
        $.get("/depart/charts",
            function(data){
                console.log("ddfg")
                $("#start").val(data.time.start)
                $("#end").val(data.time.end)
                paint(data.map)
            })
    )

    var layer;
    layui.use('layer',function(){
        layer=layui.layer;
    });
    function paint(data ){
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('pie'))
        myChart.showLoading({
            text : "图表数据正在努力加载..."
        },1000);
        option = {
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'// 默认为直线，可选为：'line' | 'shadow'

                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    data:data.xAxis,
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '发放金额总数',
                    type: 'bar',
                    barWidth: '60%',
                    data:data.series
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option,true)
        myChart.hideLoading()
        myChart.on('click', function (params) {
                var xName=params.name;
                var end=$("#parentName").val(xName)
                layer.open({
                    type:2,
                    area:['500px','500px'],
                    btn: '关闭',
                    content:['/path/charts/DepartPie','yes']
                    ,yes: function(){
                        layer.closeAll();
                    }
                });

        });

        $("#btn").click(function(){
            var image = myChart.getDataURL({
                type:'png',
                // 导出的图片分辨率比例，默认为 1。
                //pixelRatio: number,
            });
            var $a = document.createElement('a');
            $a.setAttribute("href", image);
            $a.setAttribute("download", "本月部门薪资柱状图");
            $a.click();
        })
    }
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate;

        //日期
        laydate.render({
            elem: '#startTime'
            ,type: 'date'
            // ,min: '09:00:00'
            // ,max: '17:30:00'
        });
        laydate.render({
            elem: '#endTime'
            ,type: 'date'
            // ,min: '09:00:00'
            // ,max: '17:30:00'
        });

//创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //监听提交
        form.on('submit(demo1)', function(data){
            var obj=JSON.parse(JSON.stringify(data.field));
            $.ajax({
                url: "/depart/charts",
                type: 'post',
                data: {
                    start: obj.startTime
                    , end: obj.endTime

                },
                cache: false,
                async: false,   //问题的关键，明确是异步提交数据
                dataType: 'json',  //请求数据类型
                success: function (data) {
                    $("#start").val(data.time.start)
                    $("#end").val(data.time.end)
                    paint(data.map)
                },
                error: function () {

                }
            });
            return false;
        });

    });
</script>


</html>
