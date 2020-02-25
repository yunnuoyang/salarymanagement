var layer;
function layuiTag(text){
    layui.use('layer',function(){
        layer=layui.layer;
        layer.msg(text, {icon: 6,time:1000, shade:0.4});
    });
}