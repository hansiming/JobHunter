<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Table</title>
    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="css/global.css" media="all">
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link rel="stylesheet" href="css/table.css" />
</head>

<body>
<div class="admin-main">

    <blockquote class="layui-elem-quote">
        <a href="javascript:;" class="layui-btn layui-btn-small" id="search">
            <i class="layui-icon">&#x1002;</i> 刷新
        </a>
        <a href="javascript:window.history.back();" class="layui-btn layui-btn-small">
             返回
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>分析结果</legend>
        <div class="layui-field-box" id="main" style="height:400px">

        </div>
    </fieldset>
</div>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="plugins/layui/layui.js"></script>
<script type="text/javascript" src="echarts/echarts.js"></script>
<script>


    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r != null) return unescape(r[2]); return null; //返回参数值
    }

    // 路径配置
    require.config({
        paths: {
            echarts: 'echarts'
        }
    });
    
    // 使用
    require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {

                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main'));

                myChart.showLoading({
                    text : '数据分析中',
                    effect: 'whirling'
                });

                setTimeout(function (){

                    $.get('getDataAnalysisResultByUUID?uuid=' + getUrlParam("uuid")).done(function (data) {

                        var result = JSON.parse(data);
                        var ecConfig = require('echarts/config');


                        function eConsole(param) {

                            if (param.seriesIndex == 1 || param.seriesIndex == 2) {
                                var url = result.results[param.seriesIndex].urls[param.dataIndex];
                                window.open(url)
                            }
                        }

                        myChart.on(ecConfig.EVENT.CLICK, eConsole);
                        myChart.setOption({
                            title: {
                                text: '平均薪资分析',
                                subtext: '数据来自网络'
                            },
                            tooltip: {
                                trigger: 'axis',
                                axisPointer: {
                                    type: 'shadow'
                                }
                            },
                            legend: {
                                data: result.category
                            },
                            grid: {
                                left: '3%',
                                right: '4%',
                                bottom: '3%',
                                containLabel: true
                            },
                            xAxis: {
                                type: 'value',
                                boundaryGap: [0, 0.01]
                            },
                            yAxis: {
                                type: 'category',
                                data: result.taskNames
                            },
                            series: result.results

                        });

                        myChart.hideLoading();
                    });
                }, 2000)
            }
    );

    layui.config({
        base: 'plugins/layui/modules/'
    });

    layui.use(['icheck', 'laypage','layer'], function() {
        var $ = layui.jquery,
                laypage = layui.laypage,
                layer = parent.layer === undefined ? layui.layer : parent.layer;
        $('input').iCheck({
            checkboxClass: 'icheckbox_flat-green'
        });

        $('#search').on('click', function() {
            window.location.reload(true);
        });

        $('#import').on('click', function() {
            var that = this;
            var index = layer.tips('只想提示地精准些', that,{tips: [1, 'white']});
            $('#layui-layer'+index).children('div.layui-layer-content').css('color','#000000');
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