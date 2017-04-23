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
        <a href="javascript:;" class="layui-btn layui-btn-small" id="analysis">
            职位数据分析
        </a>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>任务列表</legend>
        <div class="layui-field-box">
            <table class="site-table table-hover" id="tastTable">
                <thead>
                <tr>
                    <th></th>
                    <th>任务名</th>
                    <th>创建时间</th>
                    <th>爬取状态</th>
                    <th>已爬取条数</th>
                    <th>目标条数</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <#list taskList as task>
                <tr>
                    <td><input type="checkbox" name="analysis" data-id="${task.id}" lay-skin="primary"></td>
                    <td>${task.taskName}</td>
                    <td>${task.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                    <#if task.statu == 0>
                        <td style="text-align:center;"><i class="layui-icon" style="color:yellow;"></i>爬取中...</td>
                    <#elseif task.statu == 1>
                        <td style="text-align:center;"><i class="layui-icon" style="color:red;"></i>爬取失败</td>
                    <#else>
                        <td style="text-align:center;"><i class="layui-icon" style="color:green;"></i>爬取成功</td>
                    </#if>
                    <td>${task.nowCount}</td>
                    <td>${task.jobCount}</td>
                    <td>
                        <a href="clawerTaskResult?taskId=${task.id}">查看</a>
                        <a href="javascript:void(0);" data-id="${task.id}" class="del">删除</a>
                    </td>
                </tr>
                </#list>
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

    layui.use(['icheck', 'laypage','layer', 'form'], function() {

        var $ = layui.jquery,
                form = layui.form(),
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

        $('.del').on('click', function() {

            var button = $(this);

            layer.confirm('确定要删除吗？', {
                btn: ['确定', '取消'] //可以无限个按钮
                ,btn2: function(index, layero){

                }
            }, function(index, layero){
                var id = button.data('id');
                window.location.href="doDeleteTask?id=" + id;
                layer.close(index);
            });
        });

        $('#search').on('click', function() {
            window.location.reload(true);
        });
        
        $('#analysis').on('click', function () {

            var chk_value =[];
            $('input[name="analysis"]:checked').each(function(){
                chk_value.push($(this).val());
            });

            if (chk_value.length==0) {

                layer.open({
                    title: '未选中任何任务'
                    ,content: '一个任务都没选中，怎么做数据分析呢？？'
                });
            } else {


            }
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