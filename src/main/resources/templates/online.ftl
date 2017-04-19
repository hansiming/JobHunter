<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Table</title>
    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/table.css" />
    <link rel="stylesheet" href="css/global.css" media="all">
</head>

<body>
<div class="admin-main">
    <form class="layui-form">
        <blockquote class="layui-elem-quote">
            <div class="layui-input-inline">
                <input type="text" id="keyWord" lay-verify="required" autocomplete="off" placeholder="你想要查询的职位" class="layui-input">
            </div>
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
            <div class="layui-input-inline" style="margin: 0 20px;">
                <input type="checkbox" id="lagou" title="拉勾网" checked="">
                <input type="checkbox" id="job51" title="前程无忧" checked="">
                <input type="checkbox" id="chinahr" title="中华英才网" checked="">
            </div>
            <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
                <i class="layui-icon">&#xe615;</i> 搜索
            </a>
        </blockquote>
    </form>
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
                    <th>发布地址</th>
                </tr>
                </thead>
                <tbody id="resultBody">
                <tr>
                </tr>
                </tbody>
            </table>
            <div id="progress" class="layui-progress layui-progress-big" lay-showPercent="yes" lay-filter="myProgress" style="margin-top: 40px">
                <div class="layui-progress-bar layui-bg-green" lay-percent="0%"></div>
            </div>
        </div>
    </fieldset>
    <div class="admin-table-page">
        <div id="page" class="page">
        </div>
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

        var element = layui.element();

        var $ = layui.jquery,
                laypage = layui.laypage,
                layer = parent.layer === undefined ? layui.layer : parent.layer;

        //page
        laypage({
            cont: 'page',
            pages: 25 //总页数
            ,
            groups: 5 //连续显示分页数
            ,
            jump: function(obj, first) {
                //得到了当前页，用于向服务端请求对应数据
                var curr = obj.curr;
                if(!first) {
                    clawer(curr);
                }
            }
        });


        $("#progress").hide();
        var progressTime = 0;
        var clock;

        function progressFun() {

            element.progress('myProgress', progressTime + '%');
            progressTime += 10;
            if(progressTime >= 110) {
                clearInterval(clock);
                progressTime = 0;
            }
        }

        function clawer(page) {

            $("#resultBody").empty();
            //$("#progress").show();
            //clock = window.setInterval(progressFun, 300);

            var keyWord = $("#keyWord").val();
            var area = $("#areaSelect").find("option:selected").text();;
            var lagou = $("#lagou").is(':checked') == true ? true : false;
            var job51 = $("#job51").is(':checked') == true ? true : false;
            var chinahr = $("#chinahr").is(':checked') == true ? true : false;
            var page = 1;

            $.ajax({
                type : 'POST',
                url : 'doClawer',
                data : {keyWord : keyWord, area : area, lagou : lagou, job51 : job51, chinahr : chinahr, page : page},
                dataType : 'json',
                success : function (data) {
                    $("#progress").hide();
                    $.each(data, function(index, value){

                        var taskId = value["taskId"];
                        var domain = taskId == 0 ? "拉勾网" : taskId == 1 ? "前程无忧" : "中华英才网";
                        $("#resultBody").append("<tr><td><a href='" + value["url"] + "' target='view_window'>" + value["jobName"] + "</a></td>"
                                + "<td>" + value["companyName"] + "</td>" + "<td>" + value["maxMoney"] + "</td>"
                                + "<td>" + value["addressName"] + "</td>" + "<td>" + value["createDate"] + "</td>"
                                + "<td>" + domain + "</td></tr>");
                    });
                }
            });
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