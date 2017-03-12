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
        <legend>任务列表</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover" id="tastTable">
                <thead>
                <tr>
                    <th>任务名</th>
                    <th>创建时间</th>
                    <th>爬取路径</th>
                    <th>爬取状态</th>
                    <th>爬取条数</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>中华英才网</td>
                    <td>2017-03-05 16:25:47</td>
                    <td>http://www.chinahr.com/sou/</td>
                    <td style="text-align:center;"><i class="layui-icon" style="color:yellow;"></i>爬取中...</td>
                    <td>74</td>
                    <td>
                        <a href="/detail-19" target="_blank">预览</a>
                        <a href="/manage/article_edit_19">编辑</a>
                        <a href="javascript:;" data-id="19" data-opt="del">删除</a>
                    </td>
                </tr>
                </tbody>
            </table>
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

    layui.use(['icheck', 'laypage','layer'], function() {
        var $ = layui.jquery,
                laypage = layui.laypage,
                layer = parent.layer === undefined ? layui.layer : parent.layer;
        $('input').iCheck({
            checkboxClass: 'icheckbox_flat-green'
        });

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
                    //layer.msg('第 '+ obj.curr +' 页');
                }
            }
        });

        $('#search').on('click', function() {
            parent.layer.alert('你点击了搜索按钮')
        });

        $('#add').on('click', function() {
            $.get('temp/edit-form.html', null, function(form) {
                layer.open({
                    type: 1,
                    title: '添加表单',
                    content: form,
                    btn: ['保存', '取消'],
                    area: ['600px', '400px'],
                    maxmin: true,
                    yes: function(index) {
                        console.log(index);
                    },
                    full: function(elem) {
                        var win = window.top === window.self ? window : parent.window;
                        $(win).on('resize', function() {
                            var $this = $(this);
                            elem.width($this.width()).height($this.height()).css({
                                top: 0,
                                left: 0
                            });
                            elem.children('div.layui-layer-content').height($this.height() - 95);
                        });
                    }
                });
            });
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