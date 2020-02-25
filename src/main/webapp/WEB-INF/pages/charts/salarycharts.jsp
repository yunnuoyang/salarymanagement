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
    $(
        $.get("/depart/charts",
            {
            },
            function(data){
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

        option = {
            color: ['#3398DB'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow'// 默认为直线，可选为：'line' | 'shadow'

                }
                ,formatter:function(params){
                    for(var i=0;i<params.length;i++){
                        var param = params[i];
                        var xName=param.name;
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

                    }

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
        myChart.setOption(option)
    }

</script>
</html>
