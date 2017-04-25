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

                $.get('getDataAnalysisResultByUUID?uuid=' + getUrlParam("uuid")).done(function (data) {
                    myChart.setOption({
                        title: {
                            x: 'center',
                            text: 'ECharts例子个数统计',
                            subtext: 'Rainbow bar example',
                            link: 'http://echarts.baidu.com/doc/example.html'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataView: {show: true, readOnly: false},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        calculable: true,
                        grid: {
                            borderWidth: 0,
                            y: 80,
                            y2: 60
                        },
                        xAxis: [
                            {
                                type: 'category',
                                show: false,
                                data: ['Line', 'Bar', 'Scatter', 'K', 'Pie', 'Radar', 'Chord', 'Force', 'Map', 'Gauge', 'Funnel']
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                show: false
                            }
                        ],
                        series: [
                            {
                                name: 'ECharts例子个数统计',
                                type: 'bar',
                                itemStyle: {
                                    normal: {
                                        color: function(params) {
                                            // build a color map as your need.
                                            var colorList = [
                                                '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                                                '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                                                '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                                            ];
                                            return colorList[params.dataIndex]
                                        },
                                        label: {
                                            show: true,
                                            position: 'top',
                                            formatter: '{b}\n{c}'
                                        }
                                    }
                                },
                                data: [12,21,10,4,12,5,6,5,25,23,7],
                                markPoint: {
                                    tooltip: {
                                        trigger: 'item',
                                        backgroundColor: 'rgba(0,0,0,0)',
                                        formatter: function(params){
                                            return '<img src="'
                                                    + params.data.symbol.replace('image://', '')
                                                    + '"/>';
                                        }
                                    },
                                    data: [
                                        {xAxis:0, y: 350, name:'Line', symbolSize:20, symbol: 'image://../asset/ico/折线图.png'},
                                        {xAxis:1, y: 350, name:'Bar', symbolSize:20, symbol: 'image://../asset/ico/柱状图.png'},
                                        {xAxis:2, y: 350, name:'Scatter', symbolSize:20, symbol: 'image://../asset/ico/散点图.png'},
                                        {xAxis:3, y: 350, name:'K', symbolSize:20, symbol: 'image://../asset/ico/K线图.png'},
                                        {xAxis:4, y: 350, name:'Pie', symbolSize:20, symbol: 'image://../asset/ico/饼状图.png'},
                                        {xAxis:5, y: 350, name:'Radar', symbolSize:20, symbol: 'image://../asset/ico/雷达图.png'},
                                        {xAxis:6, y: 350, name:'Chord', symbolSize:20, symbol: 'image://../asset/ico/和弦图.png'},
                                        {xAxis:7, y: 350, name:'Force', symbolSize:20, symbol: 'image://../asset/ico/力导向图.png'},
                                        {xAxis:8, y: 350, name:'Map', symbolSize:20, symbol: 'image://../asset/ico/地图.png'},
                                        {xAxis:9, y: 350, name:'Gauge', symbolSize:20, symbol: 'image://../asset/ico/仪表盘.png'},
                                        {xAxis:10, y: 350, name:'Funnel', symbolSize:20, symbol: 'image://../asset/ico/漏斗图.png'},
                                    ]
                                }
                            }
                        ]
                    });
                });

                // 为echarts对象加载数据
                myChart.setOption(option);
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