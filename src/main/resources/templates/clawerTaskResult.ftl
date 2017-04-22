<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Table</title>
    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/table.css" />
    <link rel="stylesheet" href="css/global.css" media="all">

    <style>
        .loadingWrap{
            position:fixed;
            top:0;
            left:0;
            width:100%;
            height:100%;
            z-index:300;
            background-image:url(images/loading.gif);
            background-repeat:no-repeat;
            background-position:center center;
            background-color:#000;
            background-color:rgba(0,0,0,0.5);
            filter:alpha(opacity=50);
        }
    </style>
</head>

<body>
<div class="admin-main">
    <blockquote class="layui-elem-quote">
        <a href="javascript:window.history.back();" class="layui-btn layui-btn-small">
             返回
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>任务列表</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover" id="resultTable">
                <thead>
                <tr>
                    <th>职位名称</th>
                    <th>公司名称</th>
                    <th>薪资</th>
                    <th>地址</th>
                    <th>发布时间</th>
                </tr>
                </thead>
                <tbody id="resultBody">
                <tr>
                </tr>
                </tbody>
            </table>
            <div class="loadingWrap"></div>
        </div>
    </fieldset>>
    </div>
</div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script>

    layui.config({
        base: 'plugins/layui/modules/'
    });

    layui.use(['icheck', 'laypage','layer', 'form', 'layedit', 'laydate', 'element'], function() {

        var form = layui.form(),
                layer = layui.layer,
                layedit = layui.layedit,
                laydate = layui.laydate;

        var $ = layui.jquery,
                laypage = layui.laypage,
                layer = parent.layer === undefined ? layui.layer : parent.layer;


        //$(".loadingWrap").hide();
        $(".loadingWrap").show();

        $("#resultBody").empty();

        $.ajax({
            type : 'POST',
            url : 'clawerResult',
            data : {taskId : getUrlParam('taskId')},
            dataType : 'json',
            success : function (data) {
                $("#progress").hide();
                $.each(data, function(index, value){
                    $(".loadingWrap").hide();
                    $("#resultBody").append("<tr><td><a href='" + value["url"] + "' target='view_window'>" + value["jobName"] + "</a></td>"
                            + "<td>" + value["companyName"] + "</td>" + "<td>" + value["maxMoney"] + "</td>"
                            + "<td>" + value["addressName"] + "</td>" + "<td>" + value["createDate"] + "</td></tr>");
                });
            }
        });

        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg);  //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }

        $('#search').on('click', function() {

            clawer(1);
        });

        $('.site-table tbody tr').on('click', function(event) {
            var $this = $(this);
            var $input = $this.children('td').eq(0).find('input');
            $input.on('ifChecked', function(e) {
                $this.css('background-color', '#EEEEEE');
            });
            $input.on('ifUnchecked', function(e) {
                $this.removeAttr('style');
            });
            $input.iCheck('toggle');
        }).find('input').each(function() {
            var $this = $(this);
            $this.on('ifChecked', function(e) {
                $this.parents('tr').css('background-color', '#EEEEEE');
            });
            $this.on('ifUnchecked', function(e) {
                $this.parents('tr').removeAttr('style');
            });
        });
        $('#selected-all').on('ifChanged', function(event) {
            var $input = $('.site-table tbody tr td').find('input');
            $input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
        });
    });
</script>
</body>

</html>