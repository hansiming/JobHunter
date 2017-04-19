<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>工作寻</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
</head>

<body>
<form class="layui-form" action="doAddTask" method="POST">
    <div style="margin: 15px;">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend>任务主要信息</legend>
        </fieldset>

        <div class="layui-form-item">
            <label class="layui-form-label">任务名称</label>
            <div class="layui-input-inline">
                <input type="text" name="taskName" lay-verify="required" autocomplete="off" placeholder="任务名称" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">爬取数量</label>
            <div class="layui-input-inline">
                <input type="text" name="jobCount" lay-verify="number" placeholder="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">线程数</label>
                <div class="layui-input-inline">
                    <input type="number" name="threadCount" lay-verify="number" value="1" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid layui-word-aux">*线程数推荐5-10个</div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">模板选择</label>
            <div class="layui-input-inline">
                <select id="areaSelect" lay-filter="aihao">
                    <option value=""></option>
                    <option value="0" selected="">北京</option>
                    <option value="1">上海</option>
                    <option value="2">广州</option>
                    <option value="3">杭州</option>
                    <option value="4">成都</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">经验要求</label>
            <div class="layui-input-inline">
                <select id="experience" lay-filter="aihao">
                    <option value=""></option>
                    <option value="0" selected="">不限</option>
                    <option value="1">应届毕业生</option>
                    <option value="2">1-3年</option>
                    <option value="3">3-5年</option>
                    <option value="4">5-10年</option>
                    <option value="5">10年及以上</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function() {
        var form = layui.form(),
                layer = layui.layer,
                layedit = layui.layedit,
                laydate = layui.laydate;

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');
        //自定义验证规则
        form.verify({
            title: function(value) {
                if(value.length < 5) {
                    return '标题至少得5个字符啊';
                }
            },
            pass: [/(.+){6,12}$/, '密码必须6到12位'],
            content: function(value) {
                layedit.sync(editIndex);
            }
        });

        //监听提交
        form.on('submit(demo1)', function(data) {

            console.log(typeof data.field);

            $.ajax({
                type:'POST',
                url: 'doAddTask',
                timeout: 3000,
                data: JSON.stringify(data.field),
                dataType: 'json',
                contentType: 'application/json;charse=UTF-8',
                success: function(data){
                    if(data.status == 1){
                        showInfo(data.info);
                        window.location.href="main";
                    }else{
                        showInfo(data.info);
                    }
                }
            });
            return false;
        });
    });

    function showInfo(info) {

        layer.alert(info, {
            title: '最终的提交信息'
        });
    }
</script>
</body>

</html>